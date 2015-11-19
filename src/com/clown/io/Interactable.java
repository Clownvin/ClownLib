package com.clown.io;

import java.awt.Graphics;

public interface Interactable extends Parentable {
	public void performAction();

	public void draw(Graphics graphics);

	public int getX();

	public int getY();

	public int getWidth();

	public int getHeight();

	public Interactable setMouseState(MouseState mouseState);

	public Interactable setReleased(boolean released);

	public Interactable resetClickedMouseEffects();

	public boolean getFocus();

	public String getText();

	public Interactable setText(String text);

	public boolean getMouseDragAffected();
}