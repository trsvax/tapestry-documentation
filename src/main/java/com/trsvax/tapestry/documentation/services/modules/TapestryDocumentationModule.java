package com.trsvax.tapestry.documentation.services.modules;

import java.util.List;
import java.util.Map;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.OrderedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.InjectService;
import org.apache.tapestry5.ioc.annotations.Local;
import org.apache.tapestry5.ioc.annotations.Marker;
import org.apache.tapestry5.ioc.annotations.Primary;
import org.apache.tapestry5.ioc.annotations.ServiceId;
import org.apache.tapestry5.ioc.def.ServiceDef;
import org.apache.tapestry5.ioc.services.ChainBuilder;
import org.apache.tapestry5.ioc.services.StrategyBuilder;
import org.apache.tapestry5.services.BindingFactory;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.ValueEncoderFactory;

import com.trsvax.tapestry.documentation.services.CatchAllServiceConfigurationListener;
import com.trsvax.tapestry.documentation.services.DocumentResourcesImpl;
import com.trsvax.tapestry.documentation.services.Documentation;
import com.trsvax.tapestry.documentation.services.DocumentationChain;
import com.trsvax.tapestry.documentation.services.DocumentationResources;
import com.trsvax.tapestry.documentation.services.DocumentationStrategy;
import com.trsvax.tapestry.documentation.services.ObjectDocumentation;
import com.trsvax.tapestry.documentation.services.ServiceDefValueEncoderFactory;
import com.trsvax.tapestry.documentation.services.documentation.BindingFactoryDocumentation;
import com.trsvax.tapestry.documentation.services.documentation.ServiceDefDocumentation;

public class TapestryDocumentationModule {
	private final static String servivePrefix = "TapestryDocumentationModule";
	
    public static void bind(ServiceBinder binder) {
        binder.bind(DocumentationStrategy.class,BindingFactoryDocumentation.class).withId(BindingFactoryDocumentation.ServiceID);
        binder.bind(DocumentationResources.class,DocumentResourcesImpl.class);
        binder.bind(CatchAllServiceConfigurationListener.class);

    }
    
    @Contribute(DocumentationChain.class)
    public static void configureDocumentationChain(OrderedConfiguration<DocumentationChain<?>> configuration,
    		@InjectService(servivePrefix + "DocumentationStrategy") final DocumentationStrategy<Object> documentationStrategy) {
    	configuration.add("TapestryDocumentation", new DocumentationChain<Object>() {

			@Override
			public Documentation getDocumentation(Object object) {
				return documentationStrategy.getDocumentation(object);
			}
		},"after:*");
    }
	
    @Contribute(DocumentationStrategy.class)
    @Local
    @SuppressWarnings("rawtypes")
    public static void configureDocumentationStrategy(
    		MappedConfiguration<Class, DocumentationStrategy> configuration,
    		@InjectService(BindingFactoryDocumentation.ServiceID) DocumentationStrategy bindingFactory)  {
    	configuration.addInstance(ServiceDef.class, ServiceDefDocumentation.class);
    	configuration.add(BindingFactory.class, bindingFactory);
    	configuration.addInstance(Object.class, ObjectDocumentation.class);
    }
    
    @ServiceId(servivePrefix + "DocumentationStrategy")
    @SuppressWarnings("rawtypes")
    public static DocumentationStrategy<?> build(@Local Map<Class, DocumentationStrategy> configuration,
    		  @InjectService("StrategyBuilder") StrategyBuilder builder)
    		{
    		 
    		   return builder.build(DocumentationStrategy.class,configuration);
    		}
    
    /*
    @Contribute(ServiceConfigurationListenerHub.class)
	public static void configureServiceConfigurationListener(OrderedConfiguration<ServiceConfigurationListener> configuration,
	CatchAllServiceConfigurationListener listener)
	{
    	configuration.add("CatchAll", listener);
	}
	*/
    
    @SuppressWarnings("rawtypes")
	public static void contributeValueEncoderSource(MappedConfiguration<Class, ValueEncoderFactory> configuration) {
		configuration.addInstance(ServiceDef.class, ServiceDefValueEncoderFactory.class);
	}
	
    @SuppressWarnings("rawtypes")
	@Marker(Primary.class)
    public static DocumentationChain build(List<DocumentationChain> commands,
    		  @InjectService("ChainBuilder")
    		  ChainBuilder chainBuilder)
    		{
    		   return chainBuilder.build(DocumentationChain.class, commands);
    		}
    
	public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration) {
		configuration.add(new LibraryMapping("documentation", "com.trsvax.tapestry.documentation"));
	}

}
