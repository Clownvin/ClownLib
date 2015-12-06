package com.clown.binaryutils;

public final class BinaryFloat extends BinaryObject<BinaryFloat> {
	public static final int IDENTIFIER = 4;

	static {
		BinaryObjectFactory.putBuilder(new BinaryFloat(0.0f));
	}

	public float value = 0.0f; // Public so operations can be performed on it
								// like normal.

	public BinaryFloat(float value) {
		this.value = value;
	}

	@Override
	public BinaryFloat clone() {
		return new BinaryFloat(value);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Float) {
			return ((Float) other).equals(value);
		}
		if (other instanceof BinaryFloat) {
			return ((BinaryFloat) other).getFloat() == value;
		}
		return false;
	}

	@Override
	public BinaryFloat fromBytes(byte[] bytes) {
		if (BinaryOperations.bytesToInteger(bytes) != getIdentifier()) {
			throw new RuntimeException("Invalid identifier for type " + toString());
		}
		return new BinaryFloat(BinaryOperations.bytesToFloat(bytes, 4));
	}

	public float getFloat() {
		return value;
	}

	@Override
	public int getIdentifier() {
		return IDENTIFIER;
	}

	@Override
	public String[] getIdentifiers() {
		return null;
	}

	@Override
	public int[] getTypes() {
		return null;
	}

	public float setFloat(float value) {
		this.value = value;
		return value;
	}

	@Override
	public int sizeOf() {
		return 8;
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
		return "BinaryFloat";
	}

}
