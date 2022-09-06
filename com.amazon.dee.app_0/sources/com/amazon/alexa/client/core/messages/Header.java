package com.amazon.alexa.client.core.messages;

import androidx.annotation.Nullable;
import com.amazon.alexa.client.core.messages.AutoValue_Header;
import com.google.auto.value.AutoValue;
import com.google.gson.JsonElement;
import java.util.Collections;
import java.util.Map;
@AutoValue
/* loaded from: classes6.dex */
public abstract class Header {

    @AutoValue.Builder
    /* loaded from: classes6.dex */
    public static abstract class Builder {
        public abstract Header build();

        public abstract Builder setAdditionalFields(Map<String, JsonElement> map);

        public abstract Builder setCorrelationToken(@Nullable CorrelationToken correlationToken);

        public abstract Builder setDialogRequestIdentifier(DialogRequestIdentifier dialogRequestIdentifier);

        public abstract Builder setMessageIdentifier(MessageIdentifier messageIdentifier);

        public abstract Builder setName(Name name);

        public abstract Builder setNamespace(Namespace namespace);

        public abstract Builder setPayloadVersion(@Nullable PayloadVersion payloadVersion);
    }

    public static Builder builder() {
        return new AutoValue_Header.Builder().setAdditionalFields(Collections.emptyMap()).setMessageIdentifier(MessageIdentifier.createRandom());
    }

    @Nullable
    public abstract Map<String, JsonElement> getAdditionalFields();

    @Nullable
    public abstract CorrelationToken getCorrelationToken();

    @Nullable
    public abstract DialogRequestIdentifier getDialogRequestIdentifier();

    public abstract MessageIdentifier getMessageIdentifier();

    public abstract Name getName();

    public abstract Namespace getNamespace();

    @Nullable
    public abstract PayloadVersion getPayloadVersion();

    public boolean hasAdditionalFields() {
        return getAdditionalFields() != null && !getAdditionalFields().isEmpty();
    }

    public boolean hasCorrelationToken() {
        return getCorrelationToken() != null && !getCorrelationToken().getValue().isEmpty();
    }

    public boolean hasDialogRequestIdentifier() {
        return (getDialogRequestIdentifier() == null || getDialogRequestIdentifier() == DialogRequestIdentifier.NONE) ? false : true;
    }

    public boolean hasPayloadVersion() {
        return getPayloadVersion() != null && !getPayloadVersion().getValue().isEmpty();
    }
}
