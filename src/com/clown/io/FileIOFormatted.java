package com.clown.io;

import com.clown.util.ByteFormatted;

/**
 * 
 * @author Calvin
 *
 * @param <T>
 *            any class that extends Object is valid
 */
public interface FileIOFormatted<T> extends ByteFormatted<T> {
	/**
	 * A function used to get the filename of this object
	 * 
	 * @return the filename of this object
	 */
	public String getFileName();

	/**
	 * A function used to get the <code>FileType</code> attributed to this
	 * object.
	 * 
	 * @return the <code>FileType</code> attributed to this object
	 */
	public FileType getFileType();
}
