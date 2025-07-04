package com.dct.model.exception;

/**
 * Custom class for proactive exceptions in the application <p>
 *
 * {@link BaseException#entityName}: Name of the class that throws the exception <p>
 * {@link BaseException#errorKey}: The i18n message key used for language internationalization <p>
 * {@link BaseException#args}: Parameters accompanying the message of the `errorKey` if required <p>
 * {@link BaseException#error}: The original cause of the exception is wrapped by this custom exception
 *
 * @author thoaidc
 */
@SuppressWarnings("unused")
public abstract class BaseException extends RuntimeException {

    private final Integer code;
    private final String entityName;
    private final String errorKey;
    private final Object[] args;
    private final Throwable error;

    protected BaseException(Integer code, String entityName, String errorKey, Object[] args, Throwable error) {
        super(entityName + '-' + errorKey, error);
        this.code = code;
        this.entityName = entityName;
        this.errorKey = errorKey;
        this.args = args;
        this.error = error;
    }

    public String getEntityName() {
        return entityName;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public Object[] getArgs() {
        return args;
    }

    public Throwable getError() {
        return error;
    }

    public Integer getCode() {
        return code;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer code;
        private String entityName;
        private String errorKey;
        private Object[] args;
        private Throwable error;

        public Builder code(Integer code) {
            this.code = code;
            return this;
        }

        public Builder entityName(String entityName) {
            this.entityName = entityName;
            return this;
        }

        public Builder errorKey(String errorKey) {
            this.errorKey = errorKey;
            return this;
        }

        public Builder args(Object[] args) {
            this.args = args;
            return this;
        }

        public Builder error(Throwable error) {
            this.error = error;
            return this;
        }

        public BaseAuthenticationException authenticationException() {
            return new BaseAuthenticationException(code, entityName, errorKey, args, error);
        }

        public BaseBadRequestException badRequestException() {
            return new BaseBadRequestException(code, entityName, errorKey, args, error);
        }

        public BaseBadRequestAlertException badRequestAlertException() {
            return new BaseBadRequestAlertException(code, entityName, errorKey, args, error);
        }

        public BaseIllegalArgumentException illegalArgumentException() {
            return new BaseIllegalArgumentException(code, entityName, errorKey, args, error);
        }
    }
}
