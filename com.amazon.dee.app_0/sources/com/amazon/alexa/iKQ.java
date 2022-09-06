package com.amazon.alexa;

import androidx.annotation.NonNull;
import com.amazon.alexa.Lzl;
import com.amazon.alexa.client.alexaservice.audioprovider.AlexaAudioSource;
import javax.inject.Singleton;
/* compiled from: DialogTurnDataProvider.java */
@Singleton
/* loaded from: classes.dex */
public class iKQ {
    public Lzl zZm(shl shlVar, AlexaAudioSource alexaAudioSource) {
        return new C0223nzJ(shlVar, alexaAudioSource, (ZZq) null);
    }

    public Lzl zZm(shl shlVar, AlexaAudioSource alexaAudioSource, @NonNull ZZq zZq) {
        return new C0223nzJ(shlVar, Lzl.zZm.UNVERIFIED, null, null, alexaAudioSource, zZq);
    }

    public Lzl zZm(shl shlVar, cIy ciy) {
        return new C0223nzJ(shlVar, ciy, (cIy) null);
    }

    public Lzl zZm(String str) {
        return new XND(str);
    }
}
