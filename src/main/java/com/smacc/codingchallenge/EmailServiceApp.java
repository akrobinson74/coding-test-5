package com.smacc.codingchallenge;

import com.smacc.codingchallenge.api.rest.EmailServiceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

/**
 *
 */
public class EmailServiceApp extends Application<EmailServiceAppConfiguration> {

    @Override
    public void initialize(
        final Bootstrap<EmailServiceAppConfiguration> bootstrap) {

        bootstrap.addBundle(new SwaggerBundle<EmailServiceAppConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                final EmailServiceAppConfiguration emailServiceAppConfiguration) {
                return emailServiceAppConfiguration.swaggerBundleConfiguration;
            }
        });
    }

    public static void main(final String[] args) throws Exception {
        new EmailServiceApp().run(args);
    }

    @Override
    public void run(
        final EmailServiceAppConfiguration emailServiceAppConfiguration,
        final Environment environment) throws Exception {

        final EmailServiceResource emailServiceResource =
            new EmailServiceResource();

        environment.jersey().register(emailServiceResource);
    }
}
