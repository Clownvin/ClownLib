package com.clown.binaryutils;

import java.util.Hashtable;

public abstract class BinaryObject<T> implements ByteFormatted<BinaryObject<T>> {
	protected final Hashtable<String, BinaryObject<?>> memberTable = new Hashtable<String, BinaryObject<?>>();
	
	@Override
	public BinaryObject<T> fromBytes(byte[] bytes) {
		int[] types = getTypes();
		String[] identifiers = getIdentifiers();
		memberTable.clear();
		if (types.length != identifiers.length) {
			throw new RuntimeException("Identifiers length not equal to types length for "+toString()+".");
		}
		int offset = 0;
		byte[] block;
		for (int i = 0; i < types.length; i++) {
			block = new byte[BinaryOperations.bytesToInteger(bytes, offset)];
			offset += 4;
			for (int j = 0; j < block.length; j++) {
				block[j] = bytes[offset++];
			}
			BinaryObject<?> object;
			try {
				object = BinaryObjectFactory.getObject(types[i], block);
			} catch (BuilderMissingException e) {
				throw new RuntimeException(e.getMessage());
			}
			putMemberTable(identifiers[i], object);
		}
		return (BinaryObject<T>) this.clone();
	}
	
	@Override
	public byte[] toBytes() {
		byte[] bytes = new byte[sizeOf()];
		int idx = 0;
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
	
	@Override
	public int sizeOf() {
		int size = 0;
		for (String key : memberTable.keySet()) {
			size += 4 + memberTable.get(key).sizeOf();
		}
		return size;
	}
	
	private final void putMemberTable(String identifier, BinaryObject<?> member) {
		memberTable.put(identifier, member);
	}
	
	public abstract BinaryObject<T> clone();
	
	public abstract int[] getTypes();
	
	public abstract String[] getIdentifiers();
}
