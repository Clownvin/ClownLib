package com.clown.io;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public final class InteractableManager extends KeyAdapter {
	private static final char[] CHAR_ARRAY = new char[] { '1', '2', '3', '4',
			'5', '6', '7', '8', '9', '0', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
			'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
			'u', 'v', 'w', 'x', 'y', 'z', ' ', 'A', 'B', 'C', 'D', 'E', 'F',
			'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
			'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '_', '@', '.', '!', '@', '#',
			'$', '%', '^', '&', '*', '(', ')', '-', '+', '=', '\'', '?', '\\',
			'{', '}', '[', ']', ':', ';', '"', '<', ',', '>', '/'};
	private final ArrayList<Interactable> interactables = new ArrayList<Interactable>();

	public void addInteractable(Interactable button) {
		interactables.add(button);
	}

	public void removeInteractable(Interactable button) {
		interactables.remove(button);
	}

	public Interactable getInteractableAtPoint(int x, int y) {
		for (Interactable interactable : interactables) {
			if (interactable.isVisible())
				if (x >= interactable.getX()
						&& x <= interactable.getX() + interactable.getWidth()
						&& y >= interactable.getY()
						&& y <= interactable.getY() + interactable.getHeight()
						&& interactable.isVisible()) {
					return interactable;
				}
		}
		return null;
	}

	public void resetMouseEffects() {
		for (Interactable interactable : interactables) {
			interactable.setMouseState(MouseState.NONE);
		}
	}

	public void resetClickedMouseEffects() {
		for (Interactable interactable : interactables) {
			interactable.resetClickedMouseEffects();
		}
	}
	
	@Override 
	public void keyPressed(KeyEvent evt) {
		Interactable Interactable = getFocusedInteractable();
		if (Interactable != null) {
			for (char c : CHAR_ARRAY)
				if (c == evt.getKeyChar()) {
					Interactable.setText(Interactable.getText()
							+ evt.getKeyChar());
					return;
				}
			if (evt.getKeyCode() == KeyEvent.VK_BACK_SPACE && Interactable.getText().length() > 0) {
				Interactable.setText(Interactable.getText().substring(0,
						Interactable.getText().length() - 1));
			}
		}
	}

	public Interactable getFocusedInteractable() {
		for (Interactable Interactable : interactables)
			if (Interactable.getFocus())
				return Interactable;
		return null;
	}
}
