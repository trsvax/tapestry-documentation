package com.trsvax.tapestry.documentation.services;

public class ObjectDocumentation implements DocumentationStrategy<Object>{

	@Override
	public Documentation getDocumentation(Object object) {
		return null;
	}
	
	public String toString() {
		return "No Documetation Handler";
	}
	

}
