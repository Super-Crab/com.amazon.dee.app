package com.amazon.alexa.handsfree.devices.constants;

import androidx.annotation.NonNull;
import com.magiear.handsfree.util.Common;
/* loaded from: classes8.dex */
public enum VoiceApp {
    QUEBEC("com.quicinc.voice.activation", 401010, 400001),
    MIKE("com.motorola.motoalexa", Integer.MAX_VALUE, Integer.MAX_VALUE),
    METRO(Common.HANDSFREE_ASSISTANT_PACKAGE_NAME, 31, 1);
    
    public static final int K7_SUPPORTED_APP_VERSION = 26;
    public static final int LG_CAYMAN_SUPPORTED_APP_VERSION = 21;
    public static final int REALME_SALA_SUPPORTED_APP_VERSION = 24;
    private final int m1PSVMinVersion;
    private final String mPackageName;
    private final int mTrueTurnkeyAudioMinVersion;

    VoiceApp(@NonNull String str, @NonNull int i, @NonNull int i2) {
        this.mPackageName = str;
        this.m1PSVMinVersion = i;
        this.mTrueTurnkeyAudioMinVersion = i2;
    }

    @NonNull
    public int getMinTrueTurnkeyAudioVersion() {
        return this.mTrueTurnkeyAudioMinVersion;
    }

    @NonNull
    public int getMinVersion1PSV() {
        return this.m1PSVMinVersion;
    }

    @NonNull
    public String getPackageName() {
        return this.mPackageName;
    }
}
