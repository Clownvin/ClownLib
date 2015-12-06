package com.clown.binaryutils;

public final class BinaryInteger extends BinaryObject<BinaryInteger> {
	
	static {
		BinaryObjectFactory.putBuilder(new BinaryInteger(0));
	}
	
	private int value = 0;
	
	public BinaryInteger(int value) {
		this.value = value;
	}
	
	public int getInteger() {
		return value;
	}
	
	public int setInteger(int value) {
		this.value = value;
		return value;
	}
	
	@Override
	public int getIdentifier() {
		return 1;
	}

	@Override
	public BinaryInteger clone() {
		return new BinaryInteger(value);
	}
	
	@Override
	public byte[] toBytes() {
		return BinaryOperations.toBytes(value);
	}
	
	@Override
	public BinaryInteger fromBytes(byte[] bytes) {
		return new BinaryInteger(BinaryOperations.bytesToInteger(bytes));
	}
	
	@Override
	public int sizeOf() {
		return 4;
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
		if (other instanceof Integer) {
			return ((Integer)other).equals(value);
		}
		if (other instanceof BinaryInteger) {
			return ((BinaryInteger)other).getInteger() == value;
		}
		return false;
	}
}
