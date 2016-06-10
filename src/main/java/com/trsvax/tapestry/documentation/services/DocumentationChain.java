package com.trsvax.tapestry.documentation.services;

public interface DocumentationChain<T> {
	
	public Documentation getDocumentation(T object);
}
