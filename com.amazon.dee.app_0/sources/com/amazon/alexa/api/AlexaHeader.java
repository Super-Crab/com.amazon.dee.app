package com.amazon.alexa.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amazon.alexa.client.annotations.Nullable;
import com.amazon.alexa.utils.validation.Preconditions;
import java.util.UUID;
/* loaded from: classes6.dex */
public class AlexaHeader implements Parcelable {
    public static final Parcelable.Creator<AlexaHeader> CREATOR = new Parcelable.Creator<AlexaHeader>() { // from class: com.amazon.alexa.api.AlexaHeader.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: createFromParcel */
        public AlexaHeader mo792createFromParcel(Parcel parcel) {
            return new AlexaHeader(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        /* renamed from: newArray */
        public AlexaHeader[] mo793newArray(int i) {
            return new AlexaHeader[i];
        }
    };
    @Nullable
    private final String correlationToken;
    private final String messageId;
    private final String name;
    private final String namespace;
    @Nullable
    private final String payloadVersion;

    /* loaded from: classes6.dex */
    public static class Builder {
        @Nullable
        private String correlationToken;
        private String messageId;
        private String name;
        private String namespace;
        @Nullable
        private String payloadVersion;

        private Builder() {
            this.messageId = AlexaHeader.generateMessageId();
        }

        public AlexaHeader build() {
            return new AlexaHeader(this);
        }

        public Builder setCorrelationToken(@Nullable String str) {
            this.correlationToken = str;
            return this;
        }

        public Builder setMessageId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.messageId = str;
            }
            return this;
        }

        public Builder setName(String str) {
            this.name = str;
            return this;
        }

        public Builder setNamespace(String str) {
            this.namespace = str;
            return this;
        }

        public Builder setPayloadVersion(@Nullable String str) {
            this.payloadVersion = str;
            return this;
        }
    }

    private AlexaHeader(Parcel parcel) {
        this.namespace = parcel.readString();
        this.name = parcel.readString();
        this.messageId = parcel.readString();
        this.correlationToken = parcel.readString();
        this.payloadVersion = parcel.readString();
    }

    private AlexaHeader(Builder builder) {
        this(builder.namespace, builder.name, builder.messageId, builder.correlationToken, builder.payloadVersion);
    }

    private AlexaHeader(String str, String str2, String str3, String str4, String str5) {
        Preconditions.isFalse(TextUtils.isEmpty(str), "The provided namespace was null or empty");
        Preconditions.isFalse(TextUtils.isEmpty(str2), "The provided name was null or empty");
        this.namespace = str;
        this.name = str2;
        this.messageId = str3;
        this.correlationToken = str4;
        this.payloadVersion = str5;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static AlexaHeader create(String str, String str2) {
        return create(str, str2, generateMessageId(), null, null);
    }

    public static AlexaHeader create(String str, String str2, String str3) {
        return TextUtils.isEmpty(str3) ? create(str, str2, generateMessageId(), null, null) : create(str, str2, str3, null, null);
    }

    static AlexaHeader create(String str, String str2, String str3, String str4, String str5) {
        return TextUtils.isEmpty(str3) ? new AlexaHeader(str, str2, generateMessageId(), str4, str5) : new AlexaHeader(str, str2, str3, str4, str5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String generateMessageId() {
        return UUID.randomUUID().toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getCorrelationToken() {
        return this.correlationToken;
    }

    public String getMessageId() {
        return this.messageId;
    }

    public String getName() {
        return this.name;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getPayloadVersion() {
        return this.payloadVersion;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.namespace);
        parcel.writeString(this.name);
        parcel.writeString(this.messageId);
        parcel.writeString(this.correlationToken);
        parcel.writeString(this.payloadVersion);
    }
}
