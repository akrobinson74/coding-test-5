package com.smacc.codingchallenge;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smacc.codingchallenge.api.rest.EmailServiceResource;
import com.smacc.codingchallenge.dao.AWSSESClientWrapper;
import com.smacc.codingchallenge.dao.EmailClient;
import com.smacc.codingchallenge.services.EmailService;
import io.dropwizard.jackson.Jackson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
@Configuration
public class SpringConfig {

    private static String fromAddress;

    public static void setFromAddress(final String fromAddress) {
        SpringConfig.fromAddress = fromAddress;
    }

    public static String getFromAddress() {
        return fromAddress;
    }

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder.defaultClient();
     }

    @Bean
    public EmailService emailService(final ObjectMapper objectMapper) {
        return new EmailService(
            new ArrayList<EmailClient>(Arrays.asList(new AWSSESClientWrapper(
                amazonSimpleEmailService(), getFromAddress()))
            )
        );
    }

    @Bean
    public EmailServiceResource emailServiceResource(
        final EmailService emailService) {
        return new EmailServiceResource(emailService);
    }

    @Bean
    public ObjectMapper mapper() {return Jackson.newObjectMapper();}
}
