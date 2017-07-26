package com.smacc.codingchallenge.dao;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.smacc.codingchallenge.dto.FailureResponse;
import com.smacc.codingchallenge.dto.AWSEmailSuccessResponse;
import com.smacc.codingchallenge.dto.SendEmailMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

/**
 *
 */
public class AWSSESClientWrapper implements EmailClient<AWSSESClientWrapper> {
    private static final Logger LOGGER =
        LoggerFactory.getLogger(AWSSESClientWrapper.class);

    private final AmazonSimpleEmailService amazonSimpleEmailService;
    private final String verifiedFromAddress;

    public AWSSESClientWrapper(
        final AmazonSimpleEmailService amazonSimpleEmailService,
        final String verifiedFromAddress) {

        this.amazonSimpleEmailService = amazonSimpleEmailService;
        this.verifiedFromAddress = verifiedFromAddress;
    }

    public Response sendEmail(
        final SendEmailMessageRequest sendEmailMessageRequest) {
        final String bodyText = sendEmailMessageRequest.getBody();
        final String emailContent = bodyText == null ? "" : bodyText;

        final Message message = new Message().withBody(
            new Body().withText(new Content().withData(emailContent))
        ).withSubject(new Content().withData(
            sendEmailMessageRequest.getSubject())
        );

        final SendEmailRequest sendEmailRequest = new SendEmailRequest()
            .withMessage(message)
            .withSource(verifiedFromAddress)
            .withDestination(new Destination()
                .withToAddresses(sendEmailMessageRequest.getToList())
                .withCcAddresses(sendEmailMessageRequest.getCcList())
                .withBccAddresses(sendEmailMessageRequest.getBccList())
            );

        try {
            SendEmailResult sendEmailResult =
                amazonSimpleEmailService.sendEmail(sendEmailRequest);

            return AWSEmailSuccessResponse
                .withMessageId(sendEmailResult.getMessageId());
        }
        catch (Exception e) {
            LOGGER.error("Error transmitting email: ", e.getMessage(), e);

            return FailureResponse.withException(e);
        }
    }
}
