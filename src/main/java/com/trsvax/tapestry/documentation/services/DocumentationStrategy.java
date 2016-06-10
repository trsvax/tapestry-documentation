package com.trsvax.tapestry.documentation.services;

public interface DocumentationStrategy<T>  {
	
	public Documentation getDocumentation(T object);

}
