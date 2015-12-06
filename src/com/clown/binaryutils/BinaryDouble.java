package com.clown.binaryutils;

public final class BinaryDouble extends BinaryObject<BinaryDouble> {
	
	static {
		BinaryObjectFactory.putBuilder(new BinaryDouble(0.0d));
	}
	
	public double value = 0.0d; // Public so operations can be performed on it like normal.
	
	@Override
	public int getIdentifier() {
		return 3;
	}
	
	public BinaryDouble(double value) {
		this.value = value;
	}
	
	public double getDouble() {
		return value;
	}
	
	public double setDouble(double value) {
		this.value = value;
		return value;
	}
	
	@Override
	public BinaryDouble fromBytes(byte[] bytes) {
		if (BinaryOperations.bytesToInteger(bytes) != getIdentifier()) {
			throw new RuntimeException("Invalid identifier for type "+toString());
		}
		return new BinaryDouble(BinaryOperations.bytesToDouble(bytes, 4));
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
	public int sizeOf() {
		return 12;
	}

	@Override
	public BinaryDouble clone() {
		return new BinaryDouble(value);
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
		if (other instanceof Double) {
			return ((Double) other).equals(value);
		}
		if (other instanceof BinaryDouble) {
			return ((BinaryDouble) other).getDouble() == value;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "BinaryDouble";
	}
}
