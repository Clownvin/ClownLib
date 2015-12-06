package com.clown.binaryutils;

public final class BinaryString extends BinaryObject<BinaryString> {
	
	static {
		BinaryObjectFactory.putBuilder(new BinaryString(""));
	}
	
	private String value = "";
	
	@Override
	public int getIdentifier() {
		return 0;
	}
	
	public BinaryString(String value) {
		this.value = value;
	}

	@Override
	public BinaryString clone() {
		return new BinaryString(value);
	}
	
	public String getString() {
		return value;
	}
	
	public String setString(String value) {
		this.value = value;
		return value;
	}
	
	@Override
	public BinaryString fromBytes(byte[] bytes) {
		int type = BinaryOperations.bytesToInteger(bytes);
		if (type != getIdentifier()) {
			throw new RuntimeException("Invalid identifier for type "+toString());
		}
		char[] buffer = new char[BinaryOperations.bytesToInteger(bytes, 4)];
		for (int i = 8; i < 8 + buffer.length; i++) {
			buffer[i - 8] = (char)bytes[i];
		}
		return new BinaryString(String.valueOf(buffer));
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
	public int sizeOf() {
		return value.length() + 8;
	}

	@Override
	public int[] getTypes() {
		return null;
	}

	@Override
	public String[] getIdentifiers() {
		return null;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof String) {
			return ((String)other).equals(value);
		}
		if (other instanceof BinaryString) {
			return ((BinaryString)other).getString().equals(value);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "BinaryString";
	}
}
