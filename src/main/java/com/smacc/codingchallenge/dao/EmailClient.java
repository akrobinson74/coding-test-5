package com.smacc.codingchallenge.dao;

import com.smacc.codingchallenge.dto.SendEmailMessageRequest;

import javax.ws.rs.core.Response;

/**
 *
 */
public interface EmailClient<T> {
    Response sendEmail(SendEmailMessageRequest sendEmailMessageRequest);
}
