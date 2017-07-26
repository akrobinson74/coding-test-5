package com.smacc.codingchallenge;

import com.smacc.codingchallenge.api.rest.EmailServiceResource;
import com.smacc.codingchallenge.services.EmailService;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 */
public class EmailServiceApp extends Application<EmailServiceAppConfiguration> {

    private static final Logger LOGGER =
        LoggerFactory.getLogger(EmailServiceApp.class);

    @Override
    public void initialize(
        final Bootstrap<EmailServiceAppConfiguration> bootstrap) {

        bootstrap.addBundle(new SwaggerBundle<EmailServiceAppConfiguration>() {
            @Override
            protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(
                final EmailServiceAppConfiguration emailServiceAppConfiguration) {
                return emailServiceAppConfiguration.getSwaggerBundleConfiguration();
            }
        });
    }

    public static void main(final String[] args) throws Exception {
        new EmailServiceApp().run(args);
    }

    @Override
    public void run(
        final EmailServiceAppConfiguration configuration,
        final Environment environment) throws Exception {

        SpringConfig.setFromAddress(configuration.getFromAddress());

        final ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(SpringConfig.class);

        final EmailServiceResource emailServiceResource =
            new EmailServiceResource(
                applicationContext.getBean(EmailService.class));

        environment.jersey().register(emailServiceResource);
    }
}
