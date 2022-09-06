package com.amazon.alexa.dnssd.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
/* loaded from: classes7.dex */
public class StartDiscoveryMessage {
    public String serviceType;
    public Integer timeout;
}
