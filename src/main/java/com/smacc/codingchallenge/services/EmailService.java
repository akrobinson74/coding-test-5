package com.smacc.codingchallenge.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smacc.codingchallenge.dao.EmailClient;
import com.smacc.codingchallenge.dto.SendEmailMessageRequest;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 */
public class EmailService {
    private final List<EmailClient> emailClientList;

    public EmailService(List<EmailClient> emailClientList) {
        this.emailClientList = emailClientList;
    }

    public Response transmitMessage(
        final SendEmailMessageRequest sendEmailMessageRequest) {

        Response result = null;
        for (EmailClient emailClient : emailClientList) {
            if ((result = emailClient.sendEmail(sendEmailMessageRequest))
                .getStatusInfo().equals(Response.Status.OK)) {
                return result;
            }
        }

        try {
            result = Response.serverError().entity(
                "Unable to transmit message for:\n" + new ObjectMapper()
                        .writeValueAsString(sendEmailMessageRequest)).build();
        }
        catch (JsonProcessingException e) {
            result = Response.serverError().entity(
                "Unable to transmit message: " +
                    sendEmailMessageRequest.getSubject()).build();
        }
        finally {
            return result;
        }
    }
}
