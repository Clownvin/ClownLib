package com.clown.binaryutils;

import java.util.Hashtable;

public abstract class BinaryObject<T> implements ByteFormatted<BinaryObject<T>> {
	protected Hashtable<String, BinaryObject<?>> memberTable = new Hashtable<String, BinaryObject<?>>();

	@Override
	public abstract BinaryObject<T> clone();

	@Override
	public BinaryObject<T> fromBytes(byte[] bytes) {
		int[] types = getTypes();
		String[] identifiers = getIdentifiers();
		memberTable.clear();
		if (types.length != identifiers.length) {
			throw new RuntimeException("Identifiers length not equal to types length for " + toString() + ".");
		}
		if (BinaryOperations.bytesToInteger(bytes) != getIdentifier()) {
			throw new RuntimeException("Invalid identifier for type " + toString());
		}
		int offset = 4;
		byte[] block;
		for (int i = 0; i < types.length; i++) {
			block = new byte[BinaryOperations.bytesToInteger(bytes, offset)];
			offset += 4;
			for (int j = 0; j < block.length; j++) {
				block[j] = bytes[offset++];
			}
			BinaryObject<?> object;
			try {
				object = BinaryObjectFactory.getObject(block);
			} catch (BuilderMissingException e) {
				throw new RuntimeException(e.getMessage());
			}
			setMember(identifiers[i], object);
		}
		return this.clone();
	}

	public abstract String[] getIdentifiers();

	public final BinaryObject<?> getMember(String identifier) {
		return memberTable.get(identifier);
	}

	public abstract int[] getTypes();

	public final void setMember(String identifier, BinaryObject<?> member) {
		memberTable.put(identifier, member);
	}

	@Override
	public int sizeOf() {
		int size = 4; // 4 for identifier
		for (String key : memberTable.keySet()) {
			size += 4 + memberTable.get(key).sizeOf();
		}
		return size;
	}

	@Override
	public byte[] toBytes() {
		byte[] bytes = new byte[sizeOf()];
		int idx = 0;
		for (byte b : BinaryOperations.toBytes(getIdentifier())) {
			bytes[idx++] = b;
		}
		for (String key : memberTable.keySet()) {
			BinaryObject<?> member = memberTable.get(key);
			for (byte b : BinaryOperations.toBytes(member.sizeOf())) {
				bytes[idx++] = b;
			}
			for (byte b : member.toBytes()) {
				bytes[idx++] = b;
			}
		}
		return bytes;
	}
}
