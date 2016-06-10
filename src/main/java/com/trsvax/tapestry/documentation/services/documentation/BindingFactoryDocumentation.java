package com.trsvax.tapestry.documentation.services.documentation;

import org.apache.tapestry5.internal.bindings.ComponentBindingFactory;
import org.apache.tapestry5.internal.bindings.RenderVariableBindingFactory;
import org.apache.tapestry5.services.BindingFactory;

import com.trsvax.tapestry.documentation.services.Documentation;
import com.trsvax.tapestry.documentation.services.DocumentationStrategy;


public class BindingFactoryDocumentation implements DocumentationStrategy<BindingFactory> {

	public final static String ServiceID = "BindingFactoryDocumentation";
	
	@Override
	public Documentation getDocumentation(final BindingFactory object) {
		return new Documentation() {
			
			@Override
			public String getJavaDoc() {
				if ( object.toString().contains("ValidateBindingFactor") ) {
					return validate;
				}
				if ( ComponentBindingFactory.class.isAssignableFrom(object.getClass())) {
					return componentBinding;
				}
				if ( RenderVariableBindingFactory.class.isAssignableFrom(object.getClass())) {
					return render;
				}
				return url;
			}
		};
	}

	private static final String render = "http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/internal/bindings/RenderVariableBinding.html";
	private static final String validate = "http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/internal/bindings/ValidateBindingFactory.html";
	private static final String componentBinding = "http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/internal/bindings/ComponentBinding.html";
	private static final String url = "http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/internal/bindings/AbstractBinding.html";
}
