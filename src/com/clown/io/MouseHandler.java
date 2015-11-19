package com.clown.io;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.clown.util.OperatingSystem;

public final class MouseHandler extends MouseAdapter {
	private final InteractableManager interactableManager;
	private boolean pressed = false;
	private OperatingSystem operatingSystem;
	private int lastX, lastY;
	private MouseDragAffected currentComponent = null;

	public MouseHandler(final InteractableManager interactableManager) {
		this.interactableManager = interactableManager;
		operatingSystem = OperatingSystem.getOperatingSystem();
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		interactableManager.resetMouseEffects();
		interactableManager.resetClickedMouseEffects();
		Interactable interactable = interactableManager.getInteractableAtPoint(
				evt.getX() + operatingSystem.getXDiff(), evt.getY()
						+ operatingSystem.getYDiff());
		if (interactable != null)
			interactable.setMouseState(MouseState.PRESSED);
		else
			return;
		if (evt.getX() != lastX || evt.getY() != lastY) {
			lastX = evt.getX();
			lastY = evt.getY();
		}
		if (interactable.getMouseDragAffected()) {
			currentComponent = (MouseDragAffected) interactable;
		}
		pressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent evt) {
		interactableManager.resetMouseEffects();
		Interactable interactable = interactableManager.getInteractableAtPoint(
				evt.getX() + operatingSystem.getXDiff(), evt.getY()
						+ operatingSystem.getYDiff());
		if (interactable != null) {
			interactable.setReleased(true);
			interactable.setMouseState(MouseState.MOUSEOVER);
		} else {
			return;
		}
		if (evt.getX() != lastX || evt.getY() != lastY) {
			lastX = evt.getX();
			lastY = evt.getY();
		}
		currentComponent = null;
		pressed = false;
	}

	@Override
	public void mouseMoved(MouseEvent evt) {
		interactableManager.resetMouseEffects();
		Interactable interactable = interactableManager.getInteractableAtPoint(
				evt.getX() + operatingSystem.getXDiff(), evt.getY()
						+ operatingSystem.getYDiff());
		if (interactable != null) {
			interactable.setMouseState(MouseState.MOUSEOVER);
			if (pressed)
				interactable.setMouseState(MouseState.PRESSED);
		} else {
			return;
		}
		if (evt.getX() != lastX || evt.getY() != lastY) {
			lastX = evt.getX();
			lastY = evt.getY();
		}
	}

	@Override
	public void mouseDragged(MouseEvent evt) {
		interactableManager.resetMouseEffects();
		Interactable interactable = null;
		if (currentComponent == null) {
		interactable = interactableManager.getInteractableAtPoint(
				evt.getX() + operatingSystem.getXDiff(), evt.getY()
						+ operatingSystem.getYDiff());
		} else {
			interactable = currentComponent;
		}
		if (interactable != null) {
			interactable.setMouseState(MouseState.MOUSEOVER);
			if (pressed)
				interactable.setMouseState(MouseState.PRESSED);
			int new_mx = evt.getX(), new_my = evt.getY();
			if (interactable.getMouseDragAffected())
				((MouseDragAffected) interactable).handleMouseDrag(new_mx,
						new_my, lastX, lastY);
		} else {
			return;
		}
		lastX = evt.getX();
		lastY = evt.getY();
	}
}
