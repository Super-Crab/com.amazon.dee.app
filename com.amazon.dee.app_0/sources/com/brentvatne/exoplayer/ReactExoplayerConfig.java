package com.brentvatne.exoplayer;

import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
/* loaded from: classes.dex */
public interface ReactExoplayerConfig {
    LoadErrorHandlingPolicy buildLoadErrorHandlingPolicy(int i);

    DefaultBandwidthMeter getBandwidthMeter();
}
