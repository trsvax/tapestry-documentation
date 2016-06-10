package com.trsvax.tapestry.documentation.services.documentation;

import java.util.HashMap;
import java.util.Map;

import org.apache.tapestry5.ioc.def.ServiceDef;
import org.slf4j.Logger;

import com.trsvax.tapestry.documentation.services.Documentation;
import com.trsvax.tapestry.documentation.services.DocumentationStrategy;

public class ServiceDefDocumentation implements DocumentationStrategy<ServiceDef> {
	
	private final Logger logger;
	
	public ServiceDefDocumentation(Logger logger) {
		this.logger = logger;
	}


	@Override
	public Documentation getDocumentation(final ServiceDef serviceDef) {
		logger.info("tapestry {}",serviceDef.getServiceId());
		return new Documentation() {
			
			@Override
			public String getJavaDoc() {
				return ServiceDefDocumentation.this.getJavaDoc(serviceDef);
			}
		};
	}
	
	private String getJavaDoc(ServiceDef serviceDef) {
		String prefix = Tapestry;
		String key = serviceDef.getServiceId();
		if ( "ComponentClassTransformWorker".equals(key)) {
			key += "2";
		}
		if ( "InjectionProvider".equals(key)) {
			key += "2";
		}
		if ( "InternalJavaScriptStack".equals(key)) {
			key = "JavaScriptStack";
		}
		if ( "MasterDispatcher".equals(key)) {
			key = "Dispatcher";
		}
		if ( "MasterStackTraceElementAnalyzer".equals(key)) {
			key = "StackTraceElementAnalyzer";
		}
		
		
		
		if ( JavaDocMap.containsKey(key) ) {
			prefix = JavaDocMap.get(key);
		}
		return prefix + key;
	}
	
	private static final String Tapestry = "http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/services/";
	private static final String TapestryInternal = "http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/internal/services/";
	private static final String TapestryIOC = "http://tapestry.apache.org/5.3/apidocs/org/apache/tapestry5/ioc/services/";
	private static final String TapestryCompatibility = "http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/services/compatibility/";
	private static final String TapestryTransform = "http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/services/transform/";
	private static final String TapestryLinkTransform = "http://tapestry.apache.org/current/apidocs/org/apache/tapestry5/services/linktransform/";
	private static final String TapestryMessages = "http://tapestry.apache.org/5.3/apidocs/org/apache/tapestry5/services/messages/";
	private static final String TapestryTemplates = "http://tapestry.apache.org/5.3/apidocs/org/apache/tapestry5/services/templates/";
	private static final String TapestryAssets = "http://tapestry.apache.org/5.3/apidocs/org/apache/tapestry5/services/assets/";
	private static final String TapestryInternalJavascript = "http://tapestry.apache.org/5.3/apidocs/org/apache/tapestry5/internal/services/javascript/";
	private static final String TapestryHibernate = "http://tapestry.apache.org/5.3/apidocs/org/apache/tapestry5/hibernate/";
	private static final String TapestryJavascript = "http://tapestry.apache.org/5.3/apidocs/org/apache/tapestry5/services/javascript/";
	private static final String TapestryInternalMeta = "http://tapestry.apache.org/5.3/apidocs/org/apache/tapestry5/internal/services/meta/";
	private static final String TapestryMeta = "http://tapestry.apache.org/5.3/apidocs/org/apache/tapestry5/services/meta/";
	private static final String TapestryBindings = "http://tapestry.apache.org/5.3/apidocs/org/apache/tapestry5/internal/bindings/";
	private static final String TapestryIOCInternal = "http://tapestry.apache.org/5.3/apidocs/org/apache/tapestry5/ioc/internal/services/";

	
	private static final Map<String,String> JavaDocMap;
		
	  static {
		  	JavaDocMap = new HashMap<String,String>();
	 	  	JavaDocMap.put("AssetDispatcher", TapestryInternal);
		  	JavaDocMap.put("ApplicationDefaults", TapestryIOC);
		  	JavaDocMap.put("Compatibility", TapestryCompatibility);
		  	JavaDocMap.put("ComponentClassTransformWorker2", TapestryTransform);
		  	JavaDocMap.put("ComponentEventLinkTransformer", TapestryLinkTransform);
		  	JavaDocMap.put("ComponentInstantiatorSource", TapestryInternal);
		  	JavaDocMap.put("ComponentMessagesSource",TapestryMessages);
		  	//JavaDocMap.put("ComponentOverride",TapestryMessages);
		  	
		  	JavaDocMap.put("ComponentTemplateLocator", TapestryTemplates);
		  	JavaDocMap.put("CompressionAnalyzer", TapestryAssets);
		  	JavaDocMap.put("ContentTypeAnalyzer", TapestryAssets);
		  	JavaDocMap.put("CoreJavaScriptStack", TapestryInternalJavascript);
		  	JavaDocMap.put("DefaultDataTypeAnalyzer", TapestryInternal);
		  	JavaDocMap.put("FactoryDefaults", TapestryIOC);
		  	JavaDocMap.put("HibernateEntityPackageManager", TapestryHibernate);
		  	JavaDocMap.put("HibernateSessionSource", TapestryHibernate);
		  	JavaDocMap.put("IgnoredPathsFilter", TapestryInternal);
		  	JavaDocMap.put("InjectionProvider2", TapestryTransform);
		  	JavaDocMap.put("JavaScriptStack", TapestryJavascript);
		  	JavaDocMap.put("JavaScriptStackSource", TapestryJavascript);
		  	JavaDocMap.put("LinkSource", TapestryInternal);
		  	JavaDocMap.put("MasterObjectProvider", TapestryIOC);
		  	JavaDocMap.put("MetaWorkerImpl", TapestryInternalMeta);
		  	JavaDocMap.put("MetaWorker", TapestryMeta);
		  	//JavaDocMap.put("ModuleManager", TapestryJavascript);
		  	JavaDocMap.put("PageRenderLinkTransformer", TapestryLinkTransform);
		  	JavaDocMap.put("PersistentFieldManager", TapestryInternal);
		  	JavaDocMap.put("PropBindingFactory", TapestryBindings);
		  	JavaDocMap.put("RegistryStartup", TapestryIOCInternal);
		  	JavaDocMap.put("ServiceLifecycleSource", TapestryIOC);
		  	JavaDocMap.put("ServiceOverride", TapestryIOC);
		  	JavaDocMap.put("StreamableResourceSource", TapestryAssets);
		  	JavaDocMap.put("SymbolSource", TapestryIOC);
		  	JavaDocMap.put("TemplateParser", TapestryInternal);
		  	JavaDocMap.put("TypeCoercer", TapestryIOC);


	  };

}
