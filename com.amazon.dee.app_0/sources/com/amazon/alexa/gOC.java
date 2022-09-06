package com.amazon.alexa;

import androidx.annotation.RawRes;
/* compiled from: SoundName.java */
/* loaded from: classes.dex */
public enum gOC {
    WAKESOUND(R.raw.avs_med_ui_wakesound),
    WAKESOUND_TOUCH(R.raw.avs_med_ui_wakesound_touch),
    ENDPOINTING(R.raw.avs_med_ui_endpointing),
    ENDPOINTING_TOUCH(R.raw.avs_med_ui_endpointing_touch),
    MUTE_ON(R.raw.avs_med_state_privacy_mode_on),
    MUTE_OFF(R.raw.avs_med_state_privacy_mode_off);
    
    public final int resId;

    gOC(@RawRes int i) {
        this.resId = i;
    }

    @RawRes
    public int zZm() {
        return this.resId;
    }
}
