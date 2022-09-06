package com.amazon.comms.calling.sipclient;

import com.amazon.comms.calling.service.DynamicAcousticParams;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/* loaded from: classes11.dex */
public final class SipHeaders implements Iterable<Map.Entry<String, String>> {
    public static final String SIP_HEADER_ALEXA_CALLEE_ID = "X-Alexa-CalleeId";
    public static final String SIP_HEADER_ALEXA_CALLER_ID = "X-Alexa-CallerId";
    public static final String SIP_HEADER_ALEXA_CALL_ID = "X-Alexa-CallId";
    public static final String SIP_HEADER_ALEXA_CALL_TOKEN = "X-Alexa-CallToken";
    public static final String SIP_HEADER_ALEXA_CALL_TOKEN_VERSION = "X-Alexa-CallTokenVersion";
    public static final String SIP_HEADER_AUTH_TOKEN = "X-authtoken";
    public static final String SIP_HEADER_CALL_ID = "Call-ID";
    public static final String SIP_HEADER_CALL_PROVIDER = "X-Alexa-CallProvider";
    public static final String SIP_HEADER_CALL_RECONNECTION = "X-Alexa-CallReconnection";
    public static final String SIP_HEADER_CALL_TOKEN = "X-Alexa-CallToken";
    public static final String SIP_HEADER_CALL_TOKEN_VERSION = "X-Alexa-CallTokenVersion";
    public static final String SIP_HEADER_CONTACT = "Contact";
    public static final String SIP_HEADER_DEVICE_TYPE = "X-DeviceType";
    public static final String SIP_HEADER_FOR_CHECK_IN = "X-Alexa-IsCheckIn";
    public static final String SIP_HEADER_FOR_DROP_IN = "X-Alexa-IsDropIn";
    public static final String SIP_HEADER_JOIN = "Join";
    public static final String SIP_HEADER_PREVIOUS_SIP_CALL_ID = "X-Alexa-PreviousSipCallId";
    public static final String SIP_HEADER_TURN_CREDENTIAL = "X-Callee-Turn-Credential";
    public static final String SIP_HEADER_TURN_CREDENTIAL_OLD = "X-Callee-Credential";
    public static final String SIP_HEADER_TURN_URL = "X-Callee-Turn-Server";
    public static final String SIP_HEADER_TURN_USERNAME = "X-Callee-Turn-User";
    private final Map<String, String> headers;
    public static final String SIP_HEADER_AEC = "X-Alexa-enableAEC";
    public static final String SIP_HEADER_RES = "X-Alexa-enableRES";
    public static final String SIP_HEADER_TX_AGC = "X-Alexa-enableTxAGC";
    public static final String SIP_HEADER_RX_AGC = "X-Alexa-enableRxAGC";
    public static final String SIP_HEADER_TX_NR = "X-Alexa-enableTxNR";
    public static final String SIP_HEADER_RX_NR = "X-Alexa-enableRxNR";
    public static final Map<String, DynamicAcousticParams.AcousticParamId> DYNAMIC_ACOUSTIC_PARAMS_SIP_HEADERS = ImmutableMap.builder().mo7828put(SIP_HEADER_AEC, DynamicAcousticParams.AcousticParamId.ACOUSTIC_ECHO_CANCELLATION).mo7828put(SIP_HEADER_RES, DynamicAcousticParams.AcousticParamId.RESIDUAL_ECHO_SUPPRESSION).mo7828put(SIP_HEADER_TX_AGC, DynamicAcousticParams.AcousticParamId.MIC_AUTOMATIC_GAIN_CONTROL).mo7828put(SIP_HEADER_RX_AGC, DynamicAcousticParams.AcousticParamId.SPEAKER_AUTOMATIC_GAIN_CONTROL).mo7828put(SIP_HEADER_TX_NR, DynamicAcousticParams.AcousticParamId.MIC_NOISE_REDUCTION).mo7828put(SIP_HEADER_RX_NR, DynamicAcousticParams.AcousticParamId.SPEAKER_NOISE_REDUCTION).mo7826build();
    public static final List<String> CALL_TOKEN_SIP_HEADERS = Arrays.asList("X-Alexa-CallToken", "X-Alexa-CallTokenVersion");
    public static final SipHeaders EMPTY = new SipHeaders();

    public SipHeaders() {
        this.headers = new TreeMap();
    }

    public void clear() {
        this.headers.clear();
    }

    public boolean contains(String str) {
        return this.headers.containsKey(str);
    }

    public String get(String str, String str2) {
        return this.headers.containsKey(str) ? this.headers.get(str) : str2;
    }

    @Override // java.lang.Iterable
    public Iterator<Map.Entry<String, String>> iterator() {
        return ImmutableSet.copyOf((Collection) this.headers.entrySet()).mo8029iterator();
    }

    public String put(String str, String str2) {
        return this.headers.put(str, str2);
    }

    public void putAll(Map<String, String> map) {
        if (map != null) {
            this.headers.putAll(map);
        }
    }

    public String remove(String str) {
        return this.headers.remove(str);
    }

    public String toString() {
        return this.headers.toString();
    }

    public void putAll(SipHeaders sipHeaders) {
        if (sipHeaders != null) {
            this.headers.putAll(sipHeaders.headers);
        }
    }

    public SipHeaders(Map<String, String> map) {
        this();
        putAll(map);
    }

    public SipHeaders(SipHeaders sipHeaders) {
        this(sipHeaders.headers);
    }
}
