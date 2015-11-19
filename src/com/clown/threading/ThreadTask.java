package com.clown.threading;

public interface ThreadTask {
	public void doTask();

	public void end();

	public boolean reachedEnd();
}
