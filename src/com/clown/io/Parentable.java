package com.clown.io;

public interface Parentable {
	public boolean isVisible();

	public boolean hasParent();

	public Parentable getGUIParent();
}
