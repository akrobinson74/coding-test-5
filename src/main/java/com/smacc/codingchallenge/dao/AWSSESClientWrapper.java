package com.smacc.codingchallenge.dao;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.smacc.codingchallenge.dto.SendEmailMessageRequest;

/**
 *
 */
public class AWSSESClientWrapper implements EmailClient<AWSSESClientWrapper> {
    private final AmazonSimpleEmailService amazonSimpleEmailService;
    private final String verifiedFromAddress;

    public AWSSESClientWrapper(
        final AmazonSimpleEmailService amazonSimpleEmailService,
        final String verifiedFromAddress) {
        this.amazonSimpleEmailService = amazonSimpleEmailService;
        this.verifiedFromAddress = verifiedFromAddress;
    }

    public void sendEmail(
        final SendEmailMessageRequest sendEmailMessageRequest) {
        final String bodyText = sendEmailMessageRequest.getBody();
        final String emailContent = bodyText == null ? "" : bodyText;
        final String[] recipients =
            (String[]) sendEmailMessageRequest.getToList().toArray();

        final Message message = new Message().withBody(
            new Body().withText(new Content().withData(emailContent))
        ).withSubject(new Content().withData(
            sendEmailMessageRequest.getSubject())
        );
        final SendEmailRequest sendEmailRequest = new SendEmailRequest()
            .withSource(verifiedFromAddress)
            .withDestination(new Destination()
                .withToAddresses(sendEmailMessageRequest.getToList())
                .withCcAddresses(sendEmailMessageRequest.getCcList())
                .withBccAddresses(sendEmailMessageRequest.getBccList())
            );
    }
}
