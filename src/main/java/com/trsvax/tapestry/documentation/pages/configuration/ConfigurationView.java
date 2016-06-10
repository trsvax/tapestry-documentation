package com.trsvax.tapestry.documentation.pages.configuration;

import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.tapestry5.PropertyConduit;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.PageActivationContext;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.Messages;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.def.ServiceDef;
import org.apache.tapestry5.ioc.internal.ConfigurationType;
import org.apache.tapestry5.services.BeanModelSource;

import com.trsvax.tapestry.documentation.services.DocumentationResources;

public class ConfigurationView {
		
	@Property
	@PageActivationContext
	ServiceDef serviceDef;
	
	@SuppressWarnings("rawtypes")
	@Property
	Collection configuration;
	
	@Property
	Entry<String,Object> entry;
	
	@Property
	Object row;
	
	@Property
	boolean map;
	
	@Property
	ConfigurationType configurationType;

	
	@Inject
	private BeanModelSource source;
	
	@Inject
	private Messages messages;
	
	@Inject
	DocumentationResources<Object> documentationResources;
	
	
	@BeginRender
	void beginRender() {
		Entry<ServiceDef,Object> e =  documentationResources.getConfiguration(serviceDef);
		serviceDef = e.getKey();
		configurationType = documentationResources.getConfigurationType(serviceDef);
		map = false;
		switch (configurationType) {
		case MAPPED:
			Map<?, ?> m = (Map<?, ?>) e.getValue();
			configuration = m.entrySet();
			map = true;
			break;
		case ORDERED:
		case UNORDERED:
			configuration = (Collection<?>) e.getValue();
			break;
		}
	}
	
	public BeanModel<?> getModel() {
		BeanModel<?> model = documentationResources.getDisplayConfigurationModel(serviceDef,messages);
		if ( model == null ) {
			if ( configuration.size() > 0 ) {
			Class<?> clazz = configuration.iterator().next().getClass();
			model = source.createDisplayModel(clazz, messages);
			}
		}
		model.add("toString", toStringConduit);
		return model;
	}
	
	public String getJavaDoc() {
		return documentationResources.getDocumentation(entry.getValue()).getJavaDoc();
	}
	
	public String getJavaDocRow() {
		return documentationResources.getDocumentation(row).getJavaDoc();
	}

	static final PropertyConduit toStringConduit = new PropertyConduit() {
			
			@Override
			public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
				return null;
			}
			
			@Override
			public void set(Object arg0, Object arg1) {
				// can't set toString			
			}
			
			@SuppressWarnings("rawtypes")
			@Override
			public Class getPropertyType() {
				return String.class;
			}
			
			@Override
			public Object get(Object arg0) {
				return arg0.toString();
			}
		};
	
}
