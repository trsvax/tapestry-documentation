package com.trsvax.tapestry.documentation.services;

import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.ioc.def.ServiceDef;
import org.apache.tapestry5.services.ValueEncoderFactory;

public class ServiceDefValueEncoderFactory implements ValueEncoderFactory<ServiceDef> {
	
	private final CatchAllServiceConfigurationListener configurationListener;
	
	public ServiceDefValueEncoderFactory(CatchAllServiceConfigurationListener configurationListener) {
		this.configurationListener = configurationListener;
	}

	@Override
	public ValueEncoder<ServiceDef> create(Class<ServiceDef> type) {
		return new ValueEncoder<ServiceDef>() {

			@Override
			public String toClient(ServiceDef value) {
				return value.getServiceId();
			}

			@Override
			public ServiceDef toValue(String clientValue) {
				return configurationListener.getConfigurationByID(clientValue).getKey();
			}
		};
	}

	

	

}
