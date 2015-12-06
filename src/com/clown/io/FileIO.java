package com.clown.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.clown.binaryutils.BinaryOperations;

/**
 * 
 * @author Calvin Gene Hall
 *
 */

public final class FileIO {
	private static final FileIO SINGLETON = new FileIO();

	/**
	 * Checks whether or not the folder path exists, and if not, makes it exist.
	 * 
	 * @param path
	 */
	public static void checkFilePath(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			folder.mkdirs();
		}
	}

	/**
	 * Returns the static singleton object of this class.
	 * 
	 * @return
	 */
	public static FileIO getSingleton() {
		return SINGLETON;
	}

	// TODO revise so len is added in writeFile, not as a convention required by
	// FMWs
	/**
	 * This nifty little method reads data from a file given only a template
	 * object and a filename.
	 * 
	 * @param template
	 *            an instantiated type of the destination object type.
	 * @param fileName
	 *            the filename of the object to be read.
	 * @return returns a new object of the template type that is obtained by
	 *         calling <code>template.fromBytes()</code>
	 * @throws IOException
	 *             only when there is any IOExceptions.
	 */
	public static <T extends FileIOFormatted<T>> T readFile(T template, String fileName) throws IOException {
		if (template == null)
			throw new IllegalArgumentException("Template must be a valid, instantiated object of the desired type.");
		File file = new File(template.getFileType().getPath() + fileName + template.getFileType().getExtension());
		if (file.exists() && !file.isDirectory()) {
			try {
				FileInputStream in = new FileInputStream(file);
				byte[] lenBytes = new byte[4];
				in.read(lenBytes);
				int len = BinaryOperations.bytesToInteger(lenBytes);
				byte[] bytes = new byte[len];
				bytes[0] = lenBytes[0];
				bytes[1] = lenBytes[1];
				bytes[2] = lenBytes[2];
				bytes[3] = lenBytes[3];
				in.read(bytes, 4, len - 4);
				in.close();
				return template.fromBytes(bytes);
			} catch (IOException e) {
				throw e;
			}
		} else {
			throw new IOException("file does not exist or is directory");
		}
	}

	// TODO Document
	/**
	 * Takes an object that extends <code>FileIOFormatted</code> and writes out
	 * the bytes obtained by calling <code>FileIOFormatted.toBytes()</code>.
	 * 
	 * @param object
	 *            the object to be written to memory.
	 */
	public static <T extends FileIOFormatted<T>> void writeFile(T object) {
		if (object == null)
			throw new IllegalArgumentException("Exception @ FileManager.writeFile: Object cannot be null!");
		File file = new File(
				object.getFileType().getPath() + object.getFileName() + object.getFileType().getExtension());
		checkFilePath(object.getFileType().getPath());
		try {
			FileOutputStream out = new FileOutputStream(file);
			out.write(object.toBytes());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// TODO Document
	/**
	 * A private constructor, to prevent initialization of this class except for
	 * by functions called within this class.
	 */
	private FileIO() {
		// So can't be instantiated
	}
}
