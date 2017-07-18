package com.smacc.codingchallenge.api.rest;

import com.smacc.codingchallenge.dao.SendEmailMessageRequest;
import com.smacc.codingchallenge.services.EmailService;
import io.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 */
@Api("/email")
@Path("/email")
public class EmailServiceResource {

    private final EmailService emailService;

    public EmailServiceResource(EmailService emailService) {
        this.emailService = emailService;
    }

    @POST
    @ApiOperation(
        value="Send an email",
        notes="Triggers the transmission of an email through this service")
    @ApiResponses(value = {
        @ApiResponse(code=202, message="Request accepted for processing"),
        @ApiResponse(code=400, message="Invalid request supplied"),
        @ApiResponse(code=500, message="Error encountered"),
    })
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendMessage(
        @ApiParam(
            value="Request describing what and to whom email should be sent",
            required=true)
        final SendEmailMessageRequest request) {

        return emailService.transmitMessage(request);
    }

    @GET
    @ApiOperation("Endpoint confirming the resource is available")
    @Path("/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response status() {
        return Response.ok("I'm still here :)").build();
    }
}
