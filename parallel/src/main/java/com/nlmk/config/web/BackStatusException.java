package com.nlmk.config.web;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.Set;

import static org.springframework.http.HttpStatus.GATEWAY_TIMEOUT;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

/**
 * Исключение с сохранением http-статуса.
 */
@Getter
public class BackStatusException extends RuntimeException {

    /**
     * Ошибки по которым можно отправить запрос повторно.
     */
    private static final Set<HttpStatus> RETRY_STATUSES = Set.of(SERVICE_UNAVAILABLE, GATEWAY_TIMEOUT);

    private final HttpStatus status;

    private final String error;

    private final String description;

    public BackStatusException(final HttpStatus status, final String error, final String description) {
        this.status = status;
        this.error = error;
        this.description = description;
    }

    public BackStatusException(final Throwable cause,
                               final HttpStatus status,
                               final String error,
                               final String description) {
        super(cause);
        this.status = status;
        this.error = error;
        this.description = description;
    }

    public boolean isRetryException() {
        return RETRY_STATUSES.contains(status);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        BackStatusException that = (BackStatusException) o;
        return status == that.status
                && Objects.equals(error, that.error)
                && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, error, description);
    }

}
