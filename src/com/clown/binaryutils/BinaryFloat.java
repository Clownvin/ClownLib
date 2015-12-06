package com.clown.binaryutils;

public final class BinaryFloat extends BinaryObject<BinaryFloat> {
	
	static {
		BinaryObjectFactory.putBuilder(new BinaryFloat(0.0f));
	}
	
	private float value = 0.0f;
	
	public BinaryFloat(float value) {
		this.value = value;
	}
	
	public float getFloat() {
		return value;
	}
	
	public float setFloat(float value) {
		this.value = value;
		return value;
	}
	
	@Override
	public byte[] toBytes() {
		return BinaryOperations.toBytes(value);
	}
	
	@Override
	public BinaryFloat fromBytes(byte[] bytes) {
		System.out.println(bytes.length);
		return new BinaryFloat(BinaryOperations.bytesToFloat(bytes));
	}
	
	@Override
	public int getIdentifier() {
		return 4;
	}
	
	@Override
	public int sizeOf() {
		return 4;
	}

	@Override
	public BinaryFloat clone() {
		return new BinaryFloat(value);
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
		if (other instanceof Float) {
			return ((Float) other).equals(value);
		}
		if (other instanceof BinaryFloat) {
			return ((BinaryFloat) other).getFloat() == value;
		}
		return false;
	}

}
