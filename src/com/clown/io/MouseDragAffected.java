package com.clown.io;

public interface MouseDragAffected extends Interactable {
	public void handleMouseDrag(int mx, int my, int lastX, int lastY);
	public boolean canReleaseFocus();
	public final boolean mouseDragAffected = true;
}