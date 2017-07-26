package com.smacc.codingchallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 */
public abstract class FailureResponse extends ResponseBuilder {
    public static Response withException(final Exception e) {
        return Response.serverError().entity(new FailureWithException(e))
            .build();
    }

    public static Response forRequest(
        final SendEmailMessageRequest sendEmailMessageRequest) {
        return Response.serverError()
            .entity(new FailureForRequest(sendEmailMessageRequest)).build();
    }

    private static class FailureWithException {
        @JsonProperty("error")
        private final String error;
        @JsonProperty("stackTrace")
        private final StackTraceElement[] stackTraceElements;

        public FailureWithException(final Exception e) {
            error = e.getMessage();
            stackTraceElements = e.getStackTrace();
        }
    }

    private static class FailureForRequest {
        @JsonProperty("error")
        private final String error;
        @JsonProperty("failedRequest")
        private final SendEmailMessageRequest sendEmailMessageRequest;

        public FailureForRequest(
            final SendEmailMessageRequest sendEmailMessageRequest) {
            this.error = String.format(
                "Unable to transmit message for request: %s|%s|%s",
                sendEmailMessageRequest.getFrom(),
                String.join(";", sendEmailMessageRequest.getToList()),
                sendEmailMessageRequest.getSubject()
            );
            this.sendEmailMessageRequest = sendEmailMessageRequest;
        }
    }
}
