package com.amazon.alexa.api;

import java.util.Objects;
/* loaded from: classes6.dex */
public class TextAlexaDialogExtras {
    private final String invocationType;
    private final boolean suppressSpeechResponse;

    /* loaded from: classes6.dex */
    public static class Builder {
        private String invocationType;
        private boolean suppressSpeechResponse;

        private Builder() {
        }

        public TextAlexaDialogExtras build() {
            return new TextAlexaDialogExtras(this);
        }

        public Builder setInvocationType(String str) {
            this.invocationType = str;
            return this;
        }

        public Builder suppressSpeechResponse(boolean z) {
            this.suppressSpeechResponse = z;
            return this;
        }
    }

    private TextAlexaDialogExtras(Builder builder) {
        this.suppressSpeechResponse = builder.suppressSpeechResponse;
        this.invocationType = builder.invocationType;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TextAlexaDialogExtras.class != obj.getClass()) {
            return false;
        }
        TextAlexaDialogExtras textAlexaDialogExtras = (TextAlexaDialogExtras) obj;
        return this.suppressSpeechResponse == textAlexaDialogExtras.suppressSpeechResponse && Objects.equals(this.invocationType, textAlexaDialogExtras.invocationType);
    }

    public String getInvocationType() {
        return this.invocationType;
    }

    public int hashCode() {
        return Objects.hash(Boolean.valueOf(this.suppressSpeechResponse), this.invocationType);
    }

    public boolean suppressSpeechResponse() {
        return this.suppressSpeechResponse;
    }
}
