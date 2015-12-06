package com.clown.binaryutils;

public final class BinaryLong extends BinaryObject<BinaryLong> {
	public static final int IDENTIFIER = 2;

	static {
		BinaryObjectFactory.putBuilder(new BinaryLong(0));
	}

	public long value = 0; // Public so operations can be performed on it like
							// normal.

	public BinaryLong(long value) {
		this.value = value;
	}

	@Override
	public BinaryLong clone() {
		return new BinaryLong(value);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Long) {
			return ((Long) other).equals(value);
		}
		if (other instanceof BinaryLong) {
			return ((BinaryLong) other).getLong() == value;
		}
		return false;
	}

	@Override
	public BinaryLong fromBytes(byte[] bytes) {
		if (BinaryOperations.bytesToInteger(bytes) != getIdentifier()) {
			throw new RuntimeException("Invalid identifier for type " + toString());
		}
		return new BinaryLong(BinaryOperations.bytesToLong(bytes, 4));
	}

	@Override
	public int getIdentifier() {
		return IDENTIFIER;
	}

	@Override
	public String[] getIdentifiers() {
		return null;
	}

	public long getLong() {
		return value;
	}

	@Override
	public int[] getTypes() {
		return null;
	}

	public long setLong(long value) {
		this.value = value;
		return value;
	}

	@Override
	public int sizeOf() {
		return 12;
	}

	@Override
	public byte[] toBytes() {
		byte[] bytes = new byte[sizeOf()];
		int idx = 0;
		for (byte b : BinaryOperations.toBytes(getIdentifier())) {
			bytes[idx++] = b;
		}
		for (byte b : BinaryOperations.toBytes(value)) {
			bytes[idx++] = b;
		}
		return bytes;
	}

	@Override
	public String toString() {
		return "BinaryLong";
	}
}
