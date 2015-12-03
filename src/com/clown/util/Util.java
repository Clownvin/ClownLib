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
	
	public static boolean isUpperCase(String string) {
		char[] chars = string.toCharArray();
		for (char c : chars) {
			if (Character.isAlphabetic(c) && !Character.isUpperCase(c)) {
				return false;
			}
		}
		return true;
	}
}
