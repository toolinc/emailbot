// Copyright 2014 Tool Inc.
package com.tool.emailbot.domain;

import com.tool.emailbot.common.AssertionConcern;

import java.util.ResourceBundle;

/**
 * Thrown when a problem occurs.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class EmailbotException extends Exception {
    
    /**
     * Constructs a new EmailbotException exception with the specified detail message.
     *
     * @param message the detail message
     */
    public EmailbotException(String message) {
        super(message);
    }

    /**
     * Builder of {@link EmailbotException}
     * instances.
     *
     * @author Jovani Rico (jovanimtzrico@gmail.com)
     */
    public static class Builder extends AssertionConcern {

    private static final String PATH = "EmailbotExceptionMessages";
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle(PATH);
    private String message;

    private Builder() {
    }

    public Builder setMessage(String message) {
	assertArgumentNotNull(message, "Message cannot be null.");
	message = BUNDLE.getString(message);
	return this;
    }

    public Builder setMessage(String message, Object... params) {
	assertArgumentNotNull(message, "Message cannot be null.");
	assertArgumentNotNull(params, "Parameters cannot be null.");
	message = String.format(BUNDLE.getString(message), params);
	return this;
    }

    /**
     * Creates a instances of {@link EmailbotException} given the specified
     * characteristics on the {@link EmailbotException.Builder}.
     *
     * @return a new instance {@link EmailbotException}
     */
    public EmailbotException build() {
	return new EmailbotException(message);
    }

    /**
     * Provides a new builder.
     *
     * @return a new instance of {@link EmailbotException.Builder}
     */
    public static Builder newBuilder() {
	return new Builder();
    }
}
}
