package com.blogapplication.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
		info = @Info(
				title = "Blog Application",
				description = "For Writing Blogs",
				summary = "User can post blog and comment on it",
				termsOfService = "T&C",
				contact = @Contact(name = "Dheeru" , email = "sdheerendra382@gmail.com"),
				license = @License(name = "License Number"),
				version = "v1"
		),
		servers = {
				@Server(
						description = "Dev",
						url = "http://localhost:8090"
				),
				@Server(
						description = "Test",
						url = "http://localhost:8090"
				)
		},
		security = @SecurityRequirement(name = "auth")
)
@SecurityScheme(
		name = "auth",
		in = SecuritySchemeIn.HEADER,
		type = SecuritySchemeType.HTTP,
		scheme = "bearer",
		bearerFormat = "JWT"
)
public class OpenApiConfig {

}
