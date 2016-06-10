package com.trsvax.tapestry.documentation.services;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Primary;
import org.apache.tapestry5.ioc.def.ServiceDef;
import org.apache.tapestry5.ioc.internal.ConfigurationType;
import org.apache.tapestry5.services.BeanBlockContribution;
import org.apache.tapestry5.services.BeanModelSource;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.javascript.JavaScriptModuleConfiguration;
import org.apache.tapestry5.services.messages.ComponentMessagesSource;

public class DocumentResourcesImpl implements DocumentationResources<Object> {
	
	private final DocumentationChain<Object> documentationChain;
	private final CatchAllServiceConfigurationListener configurationListener;
	private final BeanModelSource source;
	
	public DocumentResourcesImpl(@Primary DocumentationChain<Object> documentationChain,
			CatchAllServiceConfigurationListener configurationListener,
			BeanModelSource source) {
		this.documentationChain = documentationChain;
		this.configurationListener = configurationListener;
		this.source = source;
	}

	@Override
	public Documentation getDocumentation(Object object) {
		Documentation documentation = documentationChain.getDocumentation(object);
		if ( documentation != null ) {
			return documentation;
		}
		return NO_DOCUMENTATION;
	}

	@Override
	public ConfigurationType getConfigurationType(ServiceDef serviceDef) {
		Entry<ServiceDef,Object> e =  configurationListener.getConfigurationByID(serviceDef.getServiceId());
		Class<?> type = e.getValue().getClass();
		
		if ( Map.class.isAssignableFrom(type) ) {
			return ConfigurationType.MAPPED;
		} else if ( List.class.isAssignableFrom(type)) {
			return ConfigurationType.ORDERED;
		} 
		return ConfigurationType.UNORDERED;
	}

	@Override
	public BeanModel<?> getDisplayConfigurationModel(ServiceDef serviceDef, Messages messages) {
		String serviceID = serviceDef.getServiceId();
		BeanModel<?> model = null;
		if ( "BeanBlockSource".equals(serviceID)) {
			model = source.createDisplayModel(BeanBlockContribution.class, messages);
		}
		if ( "ComponentMessagesSource".equals(serviceID) ) {
			model = source.createDisplayModel(ComponentMessagesSource.class, messages);
			model.exclude("folder");
		}
		
		if ( "ComponentClassResolver".equals(serviceID)) {
			model = source.createDisplayModel(LibraryMapping.class, messages);
		}
		
		if ( "ModuleManager".equals(serviceID)) {
			model = source.createDisplayModel(JavaScriptModuleConfiguration.class, messages);
		}
		return model;
	}
	
	private static final Documentation NO_DOCUMENTATION = new Documentation() {
		
		@Override
		public String getJavaDoc() {
			return "";
		}
	};

	@Override
	public Collection<ServiceDef> getConfigurations() {
		return configurationListener.getConfigurations();
	}

	@Override
	public Entry<ServiceDef, Object> getConfiguration(ServiceDef serviceDef) {
		return configurationListener.getConfigurationByID(serviceDef.getServiceId());
	}
}
