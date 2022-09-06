package com.amazon.alexa;
/* compiled from: DAVSOfflinePrompts.java */
/* loaded from: classes.dex */
public enum ZFm {
    LOST_CONNECTION("offline_prompt_lost_connection.mp3"),
    NOT_CONNECTED("offline_prompt_not_connected.mp3"),
    ALEXA_DOWN("offline_prompt_alexa_down.mp3"),
    NETWORK_LOW_BANDWIDTH("error_offline_network_has_low_bandwidth.mp3"),
    NETWORK_TRANSITION_AUTO("error_offline_network_transition_auto.mp3"),
    NETWORK_TRANSITION_NON_AUTO("error_offline_network_transition_non_auto.mp3"),
    CONNECTIVITY_ISSUE_AUTO("error_offline_network_transition_auto.mp3"),
    CONNECTIVITY_ISSUE_NON_AUTO("error_offline_network_transition_non_auto.mp3");
    
    public final String filename;

    ZFm(String str) {
        this.filename = str;
    }

    public String getFileName() {
        return this.filename;
    }
}
