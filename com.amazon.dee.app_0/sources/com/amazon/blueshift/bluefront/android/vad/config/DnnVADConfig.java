package com.amazon.blueshift.bluefront.android.vad.config;

import com.google.common.base.Optional;
/* loaded from: classes11.dex */
public final class DnnVADConfig extends VADConfig {
    public static final int DEFAULT_ENDPOINTING_THRESHOLD = 60;
    public static final int DEFAULT_STARTPOINTING_THRESHOLD = 15;
    private Optional<Float> mThreshold;

    public DnnVADConfig() {
        super(15, 60);
        this.mThreshold = Optional.absent();
    }

    public Optional<Float> getThreshold() {
        return this.mThreshold;
    }

    public DnnVADConfig(Float f, int i, int i2) {
        super(i, i2);
        this.mThreshold = Optional.absent();
        this.mThreshold = Optional.fromNullable(f);
    }

    public DnnVADConfig(Float f, int i, int i2, int i3) {
        super(i, i2, i3);
        this.mThreshold = Optional.absent();
        this.mThreshold = Optional.fromNullable(f);
    }
}
