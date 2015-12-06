package com.clown.binaryutils;

import java.util.ArrayList;
import java.util.Arrays;

public final class BinaryArrayList<T extends BinaryObject<?>> extends BinaryObject<BinaryArrayList<T>> {
	
	static {
		BinaryObjectFactory.putBuilder(new BinaryArrayList<>(null));
	}
	
	private ArrayList<T> arrayList = new ArrayList<T>();
	
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
		int bufferSize = BinaryOperations.bytesToInteger(bytes);
		if (bufferSize == 0) {
			return new BinaryArrayList<T>(new ArrayList<T>());
		}
		int idx = 4;
		int typeIdentifier = BinaryOperations.bytesToInteger(bytes, idx);
		idx += 4;
		ArrayList<T> newList = new ArrayList<T>();
		try {
			for (; idx < bytes.length;) {
				T object = BinaryObjectFactory.getObject(typeIdentifier, Arrays.copyOfRange(bytes, idx, bytes.length));
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
			byte[] bytes = new byte[4];
			int idx = 0;
			for (byte b : BinaryOperations.toBytes(0)) {
				bytes[idx++] = b;
			}
			return bytes;
		}
		byte[] bytes = new byte[sizeOf()];
		int idx = 0;
		for (byte b : BinaryOperations.toBytes(bytes.length)) {
			bytes[idx++] = b;
		}
		for (byte b : BinaryOperations.toBytes(arrayList.get(0).getIdentifier())) {
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
		int size = 8; // 4 for length, 4 for type
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
}
