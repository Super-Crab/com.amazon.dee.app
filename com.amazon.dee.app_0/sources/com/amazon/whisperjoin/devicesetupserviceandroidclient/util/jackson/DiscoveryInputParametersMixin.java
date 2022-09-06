package com.amazon.whisperjoin.devicesetupserviceandroidclient.util.jackson;

import com.amazon.devicesetupservice.smarthome.SmartHomeDiscoveryInputParameters;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonSubTypes({@JsonSubTypes.Type(SmartHomeDiscoveryInputParameters.class)})
@JsonTypeInfo(property = "__type", use = JsonTypeInfo.Id.NAME)
/* loaded from: classes13.dex */
public interface DiscoveryInputParametersMixin {
}
