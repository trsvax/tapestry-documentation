package com.trsvax.tapestry.documentation.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.tapestry5.ioc.def.ServiceDef;
import org.apache.tapestry5.ioc.services.ServiceConfigurationListener;
import org.slf4j.Logger;

public class CatchAllServiceConfigurationListener implements ServiceConfigurationListener {
	private final Logger logger;
		
	private Map<ServiceDef,Object> configurations = new HashMap<ServiceDef,Object>();
	
	public CatchAllServiceConfigurationListener(Logger logger) {
		this.logger = logger;
	}
	
	
	public Collection<ServiceDef> getConfigurations() {
		return configurations.keySet();
	}
	
	public Entry<ServiceDef, Object> getConfigurationByID(String id) {	
		for ( Entry<ServiceDef, Object> e : configurations.entrySet() ) {
			if ( e.getKey().getServiceId().equals(id)) {
				return e;
			}
		}
		
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onMappedConfiguration(ServiceDef serviceDef, Map map) {
		logger.info("id: {} {}",serviceDef.getServiceId(),map);
		configurations.put(serviceDef, map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onOrderedConfiguration(ServiceDef serviceDef, List list) {
		configurations.put(serviceDef,list);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void onUnorderedConfiguration(ServiceDef serviceDef, Collection collection) {
		configurations.put(serviceDef,collection);
		
	}

}
