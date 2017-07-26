package com.smacc.codingchallenge;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 *
 */
public class EmailServiceAppConfiguration extends Configuration {

    @JsonProperty("fromAddress")
    private String fromAddress;

    @JsonProperty("swagger")
    private SwaggerBundleConfiguration swaggerBundleConfiguration;

    public String getFromAddress() {
        return fromAddress;
    }

    public SwaggerBundleConfiguration getSwaggerBundleConfiguration() {
        return swaggerBundleConfiguration;
    }
}
