package com.trsvax.tapestry.documentation.services;

import java.util.Collection;
import java.util.Map.Entry;

import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.def.ServiceDef;
import org.apache.tapestry5.ioc.internal.ConfigurationType;

public interface DocumentationResources<T> {

	public Documentation getDocumentation(T object);
	
	public BeanModel<?> getDisplayConfigurationModel(ServiceDef serviceDef, Messages messages);
	
	public ConfigurationType getConfigurationType(ServiceDef serviceDef);
	
	public Collection<ServiceDef> getConfigurations();
	
	public Entry<ServiceDef, Object> getConfiguration(ServiceDef serviceDef);
}
