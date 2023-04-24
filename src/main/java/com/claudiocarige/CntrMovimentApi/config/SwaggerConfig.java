package com.claudiocarige.CntrMovimentApi.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	 public Docket detalheApi() {
	    Docket docket = new Docket(DocumentationType.SWAGGER_2);

	    docket
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.claudiocarige.CntrMovimentApi.resources"))
	      .paths(PathSelectors.any())
	      .build()
	      .securityContexts(Arrays.asList(securityContext()))
	      .securitySchemes(Arrays.asList(apiKey()))
	      .apiInfo(this.informacoesApi().build())
	      .consumes(new HashSet<String>(Arrays.asList("application/json")))
	      .produces(new HashSet<String>(Arrays.asList("application/json")));

	    return docket;
	}
	

	  private ApiInfoBuilder informacoesApi() {

		    ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

		    apiInfoBuilder.title("Moviment Container API");
		    apiInfoBuilder.description("Web API Restfull para Controle de movimentação de Container.");
		    apiInfoBuilder.version("1.0");
		    apiInfoBuilder.termsOfServiceUrl("Termo de uso: Open Source");
		    apiInfoBuilder.license("Licença - Sua Empresa");
		    apiInfoBuilder.contact(this.contact());

		    return apiInfoBuilder;

		  }
	  
	private Contact contact() {
		return new Contact(
				"Cláudio Carigé", 
				"https://portfolio-lojapetfront-9sdxuajrz-claudiocarige.vercel.app/",
				"ccarige@gmail.com");
	}
	
	public ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}
	
	private SecurityContext securityContext () {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.any())
				.build();
	}
	private List<SecurityReference> defaultAuth(){
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] scopes = new AuthorizationScope[1];
		scopes[0] = authorizationScope;
		SecurityReference reference = new SecurityReference("JWT", scopes);
		List<SecurityReference> auths = new ArrayList<>();
		auths.add(reference);
		return auths;
	}
}
