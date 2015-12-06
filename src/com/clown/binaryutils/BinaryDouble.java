package com.clown.binaryutils;

public final class BinaryDouble extends BinaryObject<BinaryDouble> {
	
	static {
		BinaryObjectFactory.putBuilder(new BinaryDouble(0.0d));
	}
	
	private double value = 0.0d;
	
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
		return new BinaryDouble(BinaryOperations.bytesToDouble(bytes));
	}
	
	@Override
	public byte[] toBytes() {
		return BinaryOperations.toBytes(value);
	}
	
	@Override
	public int sizeOf() {
		return 8;
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
}
