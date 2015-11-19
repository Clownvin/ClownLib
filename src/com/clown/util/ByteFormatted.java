package com.clown.util;

public interface ByteFormatted<T> {
	public T fromBytes(byte[] bytes);

	public int sizeOf();

	public byte[] toBytes();
}
