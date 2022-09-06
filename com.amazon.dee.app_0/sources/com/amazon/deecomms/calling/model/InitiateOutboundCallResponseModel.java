package com.amazon.deecomms.calling.model;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.enums.BeginCallStatusCode;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
/* loaded from: classes12.dex */
public class InitiateOutboundCallResponseModel {
    @JsonProperty("message")
    private String message;
    @JsonProperty("payload")
    private String payload;
    @JsonProperty("status")
    private BeginCallStatusCode status;

    public InitiateOutboundCallResponseModel(@NonNull @JsonProperty("message") String str, @NonNull @JsonProperty("status") BeginCallStatusCode beginCallStatusCode, @NonNull @JsonProperty("payload") String str2) {
        this.message = str;
        this.status = beginCallStatusCode;
        this.payload = str2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InitiateOutboundCallResponseModel)) {
            return false;
        }
        InitiateOutboundCallResponseModel initiateOutboundCallResponseModel = (InitiateOutboundCallResponseModel) obj;
        return Objects.equal(this.message, initiateOutboundCallResponseModel.message) && Objects.equal(this.status, initiateOutboundCallResponseModel.status) && Objects.equal(this.payload, initiateOutboundCallResponseModel.payload);
    }

    public String getMessage() {
        return this.message;
    }

    public String getPayload() {
        return this.payload;
    }

    public BeginCallStatusCode getStatus() {
        return this.status;
    }

    public int hashCode() {
        return Objects.hashCode(this.message, this.status, this.payload);
    }

    @NonNull
    public String toString() {
        StringBuilder outline1 = GeneratedOutlineSupport.outline1("Status : ");
        outline1.append(this.status);
        outline1.append("\nPayload : ");
        outline1.append(this.payload);
        return outline1.toString();
    }
}
