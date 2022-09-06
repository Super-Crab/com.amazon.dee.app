package com.amazon.whisperjoin.devicesetupserviceandroidclient.util.jackson;

import com.amazon.devicesetupservice.v1.FirstPartyProvisionerDetails;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonSubTypes({@JsonSubTypes.Type(FirstPartyProvisionerDetails.class)})
@JsonTypeInfo(property = "__type", use = JsonTypeInfo.Id.NAME)
/* loaded from: classes13.dex */
public interface ProvisionerDetailsMixin {
}
