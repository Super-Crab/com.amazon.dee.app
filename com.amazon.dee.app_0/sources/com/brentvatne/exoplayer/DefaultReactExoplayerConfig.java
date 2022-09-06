package com.brentvatne.exoplayer;

import android.content.Context;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;
/* loaded from: classes.dex */
public class DefaultReactExoplayerConfig implements ReactExoplayerConfig {
    private final DefaultBandwidthMeter bandwidthMeter;

    public DefaultReactExoplayerConfig(Context context) {
        this.bandwidthMeter = new DefaultBandwidthMeter.Builder(context).build();
    }

    @Override // com.brentvatne.exoplayer.ReactExoplayerConfig
    public LoadErrorHandlingPolicy buildLoadErrorHandlingPolicy(int i) {
        return new DefaultLoadErrorHandlingPolicy(i);
    }

    @Override // com.brentvatne.exoplayer.ReactExoplayerConfig
    public DefaultBandwidthMeter getBandwidthMeter() {
        return this.bandwidthMeter;
    }
}
