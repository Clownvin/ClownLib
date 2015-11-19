package com.clown.util;

import java.util.Arrays;

public class StackList<T> {
	protected static final Object[] EMPTY_ARRAYDATA = new Object[0];
	protected volatile Object[] arrayData;
	protected volatile int pointer = 0;

	public void add(T object) {
		if (pointer == arrayData.length) {
			setCapacity(arrayData.length + 1);
		}
		arrayData[pointer++] = object;
	}

	public void setCapacity(int newCapacity) {
		if (newCapacity >= 0) {
			if (newCapacity > pointer) {
				arrayData = Arrays.copyOf(arrayData, newCapacity);
			} else {
				throw new IllegalArgumentException("new capacity cannot be less than or equal to current pointer");
			}
		} else {
			throw new IllegalArgumentException("new capacity must be greater than or equal to zero");
		}
	}

	public int size() {
		return pointer;
	}

	@SuppressWarnings("unchecked")
	public T peek(int index) {
		if (index >= pointer) {
			throw new IllegalArgumentException("index cannot be greater than or equal to pointer");
		}
		return (T) arrayData[pointer - index];
	}

	@SuppressWarnings("unchecked")
	public T remove() {
		T data = (T) arrayData[--pointer];
		arrayData[pointer] = null;
		return data;
	}
}
