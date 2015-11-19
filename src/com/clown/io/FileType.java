package com.clown.io;

/**
 * 
 * @author Calvin Gene Hall
 *
 */

public enum FileType {
	USER("./Data/Users/", ".user"), CONFIGURATION("./Data/Configuration/", ".cfg"), MODERATION("./Data/Moderations/",
			".moderation"), MODEL("./Data/Models/", ".3dm");
	private final String path;
	private final String extension;

	/**
	 * Enum constructor that constructs a new <code>FileType</code> with path
	 * and extension set to the values given.
	 * 
	 * @param path
	 *            string representation of filepath to this
	 *            <code>FileType</code>
	 * @param extension
	 *            string representation of the extionsion of this FileType
	 */
	private FileType(String path, String extension) {
		this.path = path;
		this.extension = extension;
	}

	/**
	 * This method returns the <code>String</code> representation of the
	 * extension associated with this <code>FileType</code>
	 * 
	 * @return the extension associated with this <code>FileType</code>
	 */
	public String getExtension() {
		return extension;
	}

	/**
	 * This method returns the <code>String</code> representation of the
	 * filepath
	 * 
	 * @return the filepath associated with this <code>FileType</code>
	 */
	public String getPath() {
		return path;
	}
}
