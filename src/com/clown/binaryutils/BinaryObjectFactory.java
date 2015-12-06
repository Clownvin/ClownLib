package com.clown.binaryutils;

import java.util.Hashtable;

public class BinaryObjectFactory {
	private static final Hashtable<Integer, BinaryObject<?>> builderTable = new Hashtable<Integer, BinaryObject<?>>();
	
	@SuppressWarnings("unchecked")
	public static <T extends BinaryObject<?>> T getObject(int identifier, byte[] bytes) throws BuilderMissingException {
		if (!builderTable.containsKey(identifier)) {
			throw new BuilderMissingException("Builder missing for indentifier "+identifier);
		}
		return (T) builderTable.get(identifier).fromBytes(bytes);
	}
	
	public static void putBuilder(BinaryObject<?> builder) {
		if (builderTable.containsKey(builder.getIdentifier())) {
			throw new RuntimeException("Builder identifier "+builder.getIdentifier()+" is already put for "+builderTable.get(builder.getIdentifier())+", not "+builder);
		}
		builderTable.put(builder.getIdentifier(), builder);
	}
}
