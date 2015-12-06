package com.clown.binaryutils;

public interface ByteFormatted<T> {
	public T fromBytes(byte[] bytes);

	public int getIdentifier();

	public int sizeOf();

	public byte[] toBytes();
}
