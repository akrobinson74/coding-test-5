package com.smacc.codingchallenge;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.smacc.codingchallenge.api.rest.EmailServiceResource;
import com.smacc.codingchallenge.dao.AWSSESClientWrapper;
import com.smacc.codingchallenge.dao.EmailClient;
import com.smacc.codingchallenge.services.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 */
@Configuration
public class SpringConfig {
    private static final String FROM_ADDRESS = "";

    @Bean
    public EmailService emailService() {
        return new EmailService(
            new ArrayList<EmailClient>(
                Arrays.asList(new AWSSESClientWrapper(
                    amazonSimpleEmailService(), FROM_ADDRESS
                ))));
    }

    @Bean
    private AmazonSimpleEmailServiceClient amazonSimpleEmailService() {
        AmazonSimpleEmailServiceClient amazonSimpleEmailServiceClient =
            new AmazonSimpleEmailServiceClient(DefaultAWSCredentialsProviderChain.getInstance().getCredentials());
        return AmazonSimpleEmailServiceClientBuilder.standard()
            .withRegion(Regions.US_WEST_2).build();
    }

    @Bean
    public EmailServiceResource emailServiceResource(
        final EmailService emailService) {
        return new EmailServiceResource(emailService);
    }
}
