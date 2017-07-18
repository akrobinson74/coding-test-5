package com.smacc.codingchallenge.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *
 */
public class SendEmailMessageRequest {
    private final List<String> bccList;
    private final String body;
    private final List<String> ccList;
    private final String from;
    private final String subject;
    private final List<String> toList;

    @JsonCreator
    public SendEmailMessageRequest(
        @JsonProperty("bccList") final List<String> bccList,
        @JsonProperty("body") final String body,
        @JsonProperty("ccList") final List<String> ccList,
        @JsonProperty("from") final String from,
        @JsonProperty("subject") final String subject,
        @JsonProperty("toList") final List<String> toList) {

        this.bccList = bccList;
        this.body = body;
        this.ccList = ccList;
        this.from = from;
        this.subject = subject;
        this.toList = toList;
    }

    public List<String> getBccList() {
        return bccList;
    }

    public String getBody() {
        return body;
    }

    public List<String> getCcList() {
        return ccList;
    }

    public String getFrom() {
        return from;
    }

    public String getSubject() {
        return subject;
    }

    public List<String> getToList() {
        return toList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (! (o instanceof SendEmailMessageRequest)) return false;

        SendEmailMessageRequest that = (SendEmailMessageRequest) o;

        if (bccList != null ? ! bccList.equals(that.bccList) : that.bccList != null)
            return false;
        if (body != null ? ! body.equals(that.body) : that.body != null)
            return false;
        if (ccList != null ? ! ccList.equals(that.ccList) : that.ccList != null)
            return false;
        if (! from.equals(that.from)) return false;
        if (subject != null ? ! subject.equals(that.subject) : that.subject != null)
            return false;
        return toList != null ? toList.equals(that.toList) : that.toList == null;
    }

    @Override
    public int hashCode() {
        int result = bccList != null ? bccList.hashCode() : 0;
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (ccList != null ? ccList.hashCode() : 0);
        result = 31 * result + from.hashCode();
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (toList != null ? toList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SendEmailMessageRequest{" +
            "bccList=" + bccList +
            ", body='" + body + '\'' +
            ", ccList=" + ccList +
            ", from='" + from + '\'' +
            ", subject='" + subject + '\'' +
            ", toList=" + toList +
            '}';
    }
}
