package com.clown.binaryutils;

import java.util.ArrayList;
import java.util.Arrays;

public final class BinaryArrayList<T extends BinaryObject<?>> extends BinaryObject<BinaryArrayList<T>> {
	
	static {
		BinaryObjectFactory.putBuilder(new BinaryArrayList<>(null));
	}
	
	public ArrayList<T> arrayList = new ArrayList<T>(); // Public so operations can be performed on it like normal.
	
	public BinaryArrayList(ArrayList<T> arrayList) {
		this.arrayList = arrayList;
	}
	
	public ArrayList<T> getArrayList() {
		return arrayList;
	}
	
	public ArrayList<T> setArrayList(ArrayList<T> arrayList) {
		this.arrayList = arrayList;
		return arrayList;
	}
	
	@Override
	public BinaryArrayList<T> fromBytes(byte[] bytes) {
		if (BinaryOperations.bytesToInteger(bytes) != getIdentifier()) {
			throw new RuntimeException("Invalid identifier for type "+toString());
		}
		int bufferSize = BinaryOperations.bytesToInteger(bytes, 4);
		if (bufferSize == 0) {
			return new BinaryArrayList<T>(new ArrayList<T>());
		}
		int idx = 8;
		ArrayList<T> newList = new ArrayList<T>();
		try {
			for (; idx < bytes.length;) {
				T object = BinaryObjectFactory.getObject(Arrays.copyOfRange(bytes, idx, bytes.length));
				newList.add(object);
				idx += object.sizeOf();
			}
		} catch (BuilderMissingException e) {
			e.printStackTrace();
		}
		return new BinaryArrayList<T>(newList);
	}
	
	@Override
	public byte[] toBytes() {
		if (arrayList.size() == 0) {
			byte[] bytes = new byte[8];
			int idx = 0;
			for (byte b : BinaryOperations.toBytes(getIdentifier())) {
				bytes[idx++] = b;
			}
			for (byte b : BinaryOperations.toBytes(0)) {
				bytes[idx++] = b;
			}
			return bytes;
		}
		byte[] bytes = new byte[sizeOf()];
		int idx = 0;
		for (byte b : BinaryOperations.toBytes(getIdentifier())) {
			bytes[idx++] = b;
		}
		for (byte b : BinaryOperations.toBytes(bytes.length)) {
			bytes[idx++] = b;
		}
		for (int i = 0; i < arrayList.size(); i++) {
			for (byte b : arrayList.get(i).toBytes()) {
				bytes[idx++] = b;
			}
		}
		return bytes;
	}
	
	@Override
	public int sizeOf() {
		int size = 8; // 4 for arraylist type, 4 for length
		for (int i = 0; i < arrayList.size(); i++) {
			size += arrayList.get(i).sizeOf();
		}
		return size;
	}
	
	@Override
	public int getIdentifier() {
		return 5;
	}

	@Override
	public BinaryArrayList<T> clone() {
		return new BinaryArrayList<T>(arrayList);
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
		if (other instanceof BinaryArrayList) {
			return ((BinaryArrayList<?>)other).getArrayList().equals(arrayList);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "BinaryArrayList";
	}
}
