package com.smacc.codingchallenge.services;

import com.smacc.codingchallenge.dao.EmailClient;
import com.smacc.codingchallenge.dto.FailureResponse;
import com.smacc.codingchallenge.dto.SendEmailMessageRequest;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 */
public class EmailService {
    private final List<EmailClient> emailClientList;

    public EmailService(
        final List<EmailClient> emailClientList) {

        this.emailClientList = emailClientList;
    }

    public Response transmitMessage(
        final SendEmailMessageRequest sendEmailMessageRequest) {

        Response result = null;
        for (EmailClient emailClient : emailClientList) {
            if ((result = emailClient.sendEmail(sendEmailMessageRequest))
                .getStatusInfo().equals(Response.Status.OK)) {
                break;
            }
        }

        if (result == null)
            result = FailureResponse.forRequest(sendEmailMessageRequest);

        return result;
    }
}
