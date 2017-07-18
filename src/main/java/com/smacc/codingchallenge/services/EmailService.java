package com.smacc.codingchallenge.services;

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
        return null;
    }
}
