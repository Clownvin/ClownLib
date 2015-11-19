package com.clown.util;

public final class IDTagSystem {
	public final class IDTag {
		private final int id;

		private IDTag(final int id) {
			this.id = id;
		}

		public int getID() {
			return id;
		}

		public void returnTag() {
			IDTagSystem.this.returnTag(this);
		}
		
		@Override
		public String toString() {
			return "IDTag: "+id;
		}
	}

	private final CycleList<IDTag> tags = new CycleList<IDTag>();

	public IDTagSystem(final int maxTags) {
		tags.prepare(maxTags);
		for (int i = 0; i < maxTags; i++) {
			tags.add(new IDTag(i));
		}
	}

	public IDTag getTag() {
		return tags.removeNext();
	}

	private void returnTag(IDTag tag) {
		if (!tags.contains(tag)) {
			tags.add(tag);
		} else {
			throw new RuntimeException("returnTag called for tag that has already been returned!");
		}
	}
}
