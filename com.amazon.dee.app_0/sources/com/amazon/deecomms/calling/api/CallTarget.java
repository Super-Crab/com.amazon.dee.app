package com.amazon.deecomms.calling.api;

import com.amazon.comms.calling.sipclient.SipHeaders;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
@JsonSubTypes({@JsonSubTypes.Type(name = SipHeaders.SIP_HEADER_CONTACT, value = ContactCallTarget.class), @JsonSubTypes.Type(name = "Device", value = DeviceCallTarget.class), @JsonSubTypes.Type(name = "Group", value = GroupCallTarget.class), @JsonSubTypes.Type(name = "RawAddress", value = RawAddressTarget.class)})
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = "type", use = JsonTypeInfo.Id.NAME)
/* loaded from: classes12.dex */
public abstract class CallTarget {
}
