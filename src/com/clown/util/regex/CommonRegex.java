package com.clown.util.regex;

//A collection of regex patterns for common words/phrases.
public final class CommonRegex {
	public static final String YOU = "((yo)?u)";
	public static final String HELLO = "((hi)|(hel+o)|(hey))";
	public static final String WHAT = "(wh?at)";
	public static final String ARE = "((are)|(a?r))";
	public static final String WHY = "(y|(why))";
	public static final String ROBOT = "((ro)?bot)";
	public static final String MICROPHONE = "(mic(rophone)?)";
	private CommonRegex() {
		//To prevent instantiation.
	}
}
