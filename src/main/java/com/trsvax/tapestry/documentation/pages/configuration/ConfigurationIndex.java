package com.trsvax.tapestry.documentation.pages.configuration;

import java.util.Collection;

import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.def.ServiceDef;
import org.apache.tapestry5.ioc.internal.ConfigurationType;

import com.trsvax.tapestry.documentation.services.DocumentationResources;

public class ConfigurationIndex {
	
	public static Class<ConfigurationView> view = ConfigurationView.class;
		
	@Inject
	DocumentationResources<ServiceDef> documentationResources;

	@Property
	Collection<ServiceDef> collection;
	@Property
	ServiceDef serviceDef;
	
	@BeginRender
	void beginRender() {
		collection = documentationResources.getConfigurations();
	}
	
	public String getJavaDoc() {
		return documentationResources.getDocumentation(serviceDef).getJavaDoc();
	}
	
	public ConfigurationType getConfigType() {
		return documentationResources.getConfigurationType(serviceDef);
	}
	
			    
}			  
