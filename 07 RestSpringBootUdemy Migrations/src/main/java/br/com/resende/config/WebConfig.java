package br.com.resende.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.resende.serialization.converter.YamlJackson2HttpMessageConverter;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer{

	private static final MediaType MEDIA_TYPE_YML = MediaType.valueOf("application/x-yaml");
	
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new YamlJackson2HttpMessageConverter());
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
//		configurer.favorParameter(false)					//via extensao 
//					.ignoreAcceptHeader(false)				//localhost:8080/api/person/v1/1.xml
//					.defaultContentType(MediaType.APPLICATION_JSON)
//					.mediaType("json", MediaType.APPLICATION_JSON)
//					.mediaType("xml", MediaType.APPLICATION_XML);
		
//		configurer.favorPathExtension(false)	//via queryParameter
//				.favorParameter(true)			//localhost:8080/api/person/v1/1?mediaType=xml
//				.parameterName("mediaType")
//				.ignoreAcceptHeader(true)
//				.useRegisteredExtensionsOnly(false)
//				.defaultContentType(MediaType.APPLICATION_JSON)
//				.mediaType("json", MediaType.APPLICATION_JSON)
//				.mediaType("xml", MediaType.APPLICATION_XML);
		
		configurer.favorPathExtension(false)	//via HeaderParameter
				.favorParameter(false)			//localhost:8080/api/person/v1/1
				.ignoreAcceptHeader(false)
				.useRegisteredExtensionsOnly(false)
				.defaultContentType(MediaType.APPLICATION_JSON)
				.mediaType("json", MediaType.APPLICATION_JSON)
				.mediaType("xml", MediaType.APPLICATION_XML)
				.mediaType("x-yaml", MEDIA_TYPE_YML);
		
	}
	
}
