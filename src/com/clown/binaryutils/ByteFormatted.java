package com.clown.binaryutils;

public interface ByteFormatted <T> {
	public byte[] toBytes();
	
	public T fromBytes(byte[] bytes);
	
	public int sizeOf();
	
	public int getIdentifier();
}
