package com.amazon.whisperjoin.devicesetupserviceandroidclient.util.jackson;

import com.amazon.devicesetupservice.v1.AuthMaterialIdentifier;
import com.amazon.devicesetupservice.v1.BarcodeIdentifier;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonSubTypes({@JsonSubTypes.Type(AuthMaterialIdentifier.class), @JsonSubTypes.Type(BarcodeIdentifier.class)})
@JsonTypeInfo(property = "__type", use = JsonTypeInfo.Id.NAME)
/* loaded from: classes13.dex */
public interface ProvisioneeIdentifierMixin {
}
