package com.amazon.whisperjoin.devicesetupserviceandroidclient.util.jackson;

import com.amazon.devicesetupservice.pwsync.v1.DistressDiscoveryOutputParameters;
import com.amazon.devicesetupservice.pwsync.v1.LocalNotificationOutputParameters;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonSubTypes({@JsonSubTypes.Type(DistressDiscoveryOutputParameters.class), @JsonSubTypes.Type(LocalNotificationOutputParameters.class)})
@JsonTypeInfo(property = "__type", use = JsonTypeInfo.Id.NAME)
/* loaded from: classes13.dex */
public interface DiscoveryOutputParametersMixin {
}
