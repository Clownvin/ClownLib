package com.clown.binaryutils;

public final class BinaryString extends BinaryObject<BinaryString> {
	public static final int IDENTIFIER = 0;

	static {
		BinaryObjectFactory.putBuilder(new BinaryString(""));
	}

	public String value = ""; // Public so operations can be performed on it
								// like normal.

	public BinaryString(String value) {
		this.value = value;
	}

	@Override
	public BinaryString clone() {
		return new BinaryString(value);
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof String) {
			return ((String) other).equals(value);
		}
		if (other instanceof BinaryString) {
			return ((BinaryString) other).getString().equals(value);
		}
		return false;
	}

	@Override
	public BinaryString fromBytes(byte[] bytes) {
		int type = BinaryOperations.bytesToInteger(bytes);
		if (type != getIdentifier()) {
			throw new RuntimeException("Invalid identifier for type " + toString());
		}
		char[] buffer = new char[BinaryOperations.bytesToInteger(bytes, 4)];
		for (int i = 8; i < 8 + buffer.length; i++) {
			buffer[i - 8] = (char) bytes[i];
		}
		return new BinaryString(String.valueOf(buffer));
	}

	@Override
	public int getIdentifier() {
		return IDENTIFIER;
	}

	@Override
	public String[] getIdentifiers() {
		return null;
	}

	public String getString() {
		return value;
	}

	@Override
	public int[] getTypes() {
		return null;
	}

	public String setString(String value) {
		this.value = value;
		return value;
	}

	@Override
	public int sizeOf() {
		return value.length() + 8;
	}

	@Override
	public byte[] toBytes() {
		byte[] bytes = new byte[sizeOf()]; // Type + length
		int idx = 0;
		for (byte b : BinaryOperations.toBytes(getIdentifier())) {
			bytes[idx++] = b;
		}
		for (byte b : BinaryOperations.toBytes(value.length())) {
			bytes[idx++] = b;
		}
		for (byte b : value.getBytes()) {
			bytes[idx++] = b;
		}
		return bytes;
	}

	@Override
	public String toString() {
		return "BinaryString";
	}
}
