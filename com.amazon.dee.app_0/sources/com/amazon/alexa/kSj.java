package com.amazon.alexa;

import androidx.annotation.RawRes;
/* compiled from: OfflinePrompts.java */
/* loaded from: classes.dex */
public enum kSj {
    LOST_CONNECTION(R.raw.offline_prompt_lost_connection),
    NOT_CONNECTED(R.raw.offline_prompt_not_connected),
    ALEXA_DOWN(R.raw.offline_prompt_alexa_down),
    NETWORK_LOW_BANDWIDTH(R.raw.error_offline_network_has_low_bandwidth),
    NETWORK_TRANSITION_AUTO(R.raw.error_offline_network_transition_auto),
    NETWORK_TRANSITION_NON_AUTO(R.raw.error_offline_network_transition_non_auto),
    CONNECTIVITY_ISSUE_AUTO(R.raw.error_offline_network_transition_auto),
    CONNECTIVITY_ISSUE_NON_AUTO(R.raw.error_offline_network_transition_non_auto);
    
    @RawRes
    public final int resourceId;

    kSj(@RawRes int i) {
        this.resourceId = i;
    }

    @RawRes
    public int zZm() {
        return this.resourceId;
    }
}
