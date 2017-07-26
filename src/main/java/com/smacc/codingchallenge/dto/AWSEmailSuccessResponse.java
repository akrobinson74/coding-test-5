package com.smacc.codingchallenge.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

/**
 *
 */
public abstract class AWSEmailSuccessResponse extends ResponseBuilder {

    public static Response withMessageId(final String messageId) {
        return Response.ok().entity(new ResponseWithId(messageId)).build();
    }

    private static class ResponseWithId {
        @JsonProperty("msg")
        private final String msg;

        public ResponseWithId(final String messageId) {
            msg = String.format("Email queued via AWS with message id: %s",
                messageId);
        }
    }
}
