package com.clown.util;

public final class Util {
	private Util() {
		// No need to instantiate this, ever.
	}
	
	public static byte[] toBytes(String string) {
		byte[] bytes = new byte[string.length()];
		int idx = 0;
		for (char c : string.toCharArray()) {
			bytes[idx++] = (byte)c;
		}
		return bytes;
	}
}
