package com.trsvax.tapestry.documentation.pages.service;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.services.ServiceActivity;
import org.apache.tapestry5.ioc.services.ServiceActivityScoreboard;

public class ServiceIndex {
	
	@Inject
	@Property
	ServiceActivityScoreboard scoreboard;
	
	@Property
	ServiceActivity serviceActivity;

}
