package com.amazon.alexa.response.payload;

import com.amazon.alexa.response.payload.type.SafetyErrorResponseType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import edu.umd.cs.findbugs.annotations.CheckForNull;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Generated;
/* loaded from: classes10.dex */
public final class SafetyErrorResponsePayload {
    @CheckForNull
    private final String message;
    @CheckForNull
    private final SafetyErrorResponseType type;

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    /* loaded from: classes10.dex */
    public static class SafetyErrorResponsePayloadBuilder {
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private String message;
        @SuppressFBWarnings(justification = "generated code")
        @Generated
        private SafetyErrorResponseType type;

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        SafetyErrorResponsePayloadBuilder() {
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public SafetyErrorResponsePayload build() {
            return new SafetyErrorResponsePayload(this.type, this.message);
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public SafetyErrorResponsePayloadBuilder message(String str) {
            this.message = str;
            return this;
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SafetyErrorResponsePayload.SafetyErrorResponsePayloadBuilder(type=");
            outline107.append(this.type);
            outline107.append(", message=");
            return GeneratedOutlineSupport1.outline91(outline107, this.message, ")");
        }

        @SuppressFBWarnings(justification = "generated code")
        @Generated
        public SafetyErrorResponsePayloadBuilder type(SafetyErrorResponseType safetyErrorResponseType) {
            this.type = safetyErrorResponseType;
            return this;
        }
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    SafetyErrorResponsePayload(SafetyErrorResponseType safetyErrorResponseType, String str) {
        this.type = safetyErrorResponseType;
        this.message = str;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public static SafetyErrorResponsePayloadBuilder builder() {
        return new SafetyErrorResponsePayloadBuilder();
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SafetyErrorResponsePayload)) {
            return false;
        }
        SafetyErrorResponsePayload safetyErrorResponsePayload = (SafetyErrorResponsePayload) obj;
        SafetyErrorResponseType type = getType();
        SafetyErrorResponseType type2 = safetyErrorResponsePayload.getType();
        if (type != null ? !type.equals(type2) : type2 != null) {
            return false;
        }
        String message = getMessage();
        String message2 = safetyErrorResponsePayload.getMessage();
        return message != null ? message.equals(message2) : message2 == null;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public String getMessage() {
        return this.message;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public SafetyErrorResponseType getType() {
        return this.type;
    }

    @SuppressFBWarnings(justification = "generated code")
    @Generated
    public int hashCode() {
        SafetyErrorResponseType type = getType();
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SafetyErrorResponsePayload(type=");
        outline107.append(getType());
        outline107.append(", message=");
        outline107.append(getMessage());
        outline107.append(")");
        return outline107.toString();
    }
}
