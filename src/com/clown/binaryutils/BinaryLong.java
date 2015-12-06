package com.clown.binaryutils;

public final class BinaryLong extends BinaryObject<BinaryLong> {
	
	static {
		BinaryObjectFactory.putBuilder(new BinaryLong(0));
	}
	
	private long value = 0;
	
	public BinaryLong(long value) {
		this.value = value;
	}
	
	public long getLong() {
		return value;
	}
	
	public long setLong(long value) {
		this.value = value;
		return value;
	}
	
	@Override
	public byte[] toBytes() {
		return BinaryOperations.toBytes(value);
	}
	
	@Override
	public BinaryLong fromBytes(byte[] bytes) {
		return new BinaryLong(BinaryOperations.bytesToLong(bytes));
	}
	
	@Override
	public int sizeOf() {
		return 8;
	}
	
	@Override
	public int getIdentifier() {
		return 2;
	}

	@Override
	public BinaryLong clone() {
		return new BinaryLong(value);
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
		if (other instanceof Long) {
			return ((Long)other).equals(value);
		}
		if (other instanceof BinaryLong) {
			return ((BinaryLong)other).getLong() == value;
		}
		return false;
	}
}
