package com.amazon.alexa.response.payload;

import com.amazon.alexa.response.payload.type.ErrorResponseType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes10.dex */
public final class ErrorResponsePayload {
    @CheckForNull
    private final String message;
    @CheckForNull
    private final ErrorResponseType type;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes10.dex */
    public static class ErrorResponsePayloadBuilder {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String message;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private ErrorResponseType type;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        ErrorResponsePayloadBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ErrorResponsePayload build() {
            return new ErrorResponsePayload(this.type, this.message);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ErrorResponsePayloadBuilder message(String str) {
            this.message = str;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ErrorResponsePayload.ErrorResponsePayloadBuilder(type=");
            outline107.append(this.type);
            outline107.append(", message=");
            return GeneratedOutlineSupport1.outline91(outline107, this.message, ")");
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public ErrorResponsePayloadBuilder type(ErrorResponseType errorResponseType) {
            this.type = errorResponseType;
            return this;
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    ErrorResponsePayload(ErrorResponseType errorResponseType, String str) {
        this.type = errorResponseType;
        this.message = str;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static ErrorResponsePayloadBuilder builder() {
        return new ErrorResponsePayloadBuilder();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ErrorResponsePayload)) {
            return false;
        }
        ErrorResponsePayload errorResponsePayload = (ErrorResponsePayload) obj;
        ErrorResponseType type = getType();
        ErrorResponseType type2 = errorResponsePayload.getType();
        if (type != null ? !type.equals(type2) : type2 != null) {
            return false;
        }
        String message = getMessage();
        String message2 = errorResponsePayload.getMessage();
        return message != null ? message.equals(message2) : message2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getMessage() {
        return this.message;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public ErrorResponseType getType() {
        return this.type;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        ErrorResponseType type = getType();
        int i = 43;
        int hashCode = type == null ? 43 : type.hashCode();
        String message = getMessage();
        int i2 = (hashCode + 59) * 59;
        if (message != null) {
            i = message.hashCode();
        }
        return i2 + i;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ErrorResponsePayload(type=");
        outline107.append(getType());
        outline107.append(", message=");
        outline107.append(getMessage());
        outline107.append(")");
        return outline107.toString();
    }
}
