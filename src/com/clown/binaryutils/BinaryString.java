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
		char[] buffer = new char[BinaryOperations.bytesToInteger(bytes)];
		System.out.println("Buffer length: "+buffer.length);
		for (int i = 4; i < 4 + buffer.length; i++) {
			buffer[i - 4] = (char)bytes[i];
		}
		return new BinaryString(String.valueOf(buffer));
	}
	
	@Override
	public byte[] toBytes() {
		byte[] bytes = new byte[value.length() + 4];
		int idx = 0;
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
		return value.length() + 4;
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
}
