package com.clown.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

public final class BasicIO {
	private BasicIO() {
		// Prevent instantiation.
	}

	public static boolean isEOLChar(int i) {
		return i == 0x0D || i == 0x0A || i == 0x0B || i == 0x0C;
	}

	// I think it's fine that it'll keep reading once it reaches
	// End of stream, just in case more info is put into stream
	public static char[] readLine(InputStream stream) {
		char[] chars = new char[1];
		int idx = 0;
		try {
			while (isEOLChar((chars[idx++] = (char) stream.read()))) {
				idx--;
			}
			chars = Arrays.copyOf(chars, idx + 1);
			while (!isEOLChar((chars[idx++] = (char) stream.read()))) {
				chars = Arrays.copyOf(chars, idx + 1);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return chars;
		}
		return chars;
	}

	public static String readUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
			URL url = new URL(urlString);
			reader = new BufferedReader(new InputStreamReader(url.openStream()));
			StringBuffer buffer = new StringBuffer();
			int read;
			char[] chars = new char[1024];
			while ((read = reader.read(chars)) != -1)
				buffer.append(chars, 0, read);

			return buffer.toString();
		} finally {
			if (reader != null)
				reader.close();
		}
	}
}
