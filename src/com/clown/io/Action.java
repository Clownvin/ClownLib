package com.clown.io;

import com.clown.gui.GUIComponent;

public abstract class Action {
	protected GUIComponent component;

	public void perform() {

	}

	public Action setComponent(GUIComponent component) {
		this.component = component;
		return this;
	}
}
