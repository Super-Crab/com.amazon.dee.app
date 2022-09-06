package com.amazon.alexa;

import com.amazon.alexa.utils.Provider;
import com.amazon.alexa.wakeword.pryon.PryonConfigProvider;
import com.amazon.pryon.android.asr.PryonLite5000;
/* compiled from: WakeWordModule.java */
/* loaded from: classes.dex */
public class QoN implements Provider<PryonLite5000.Config> {
    public QoN(iPU ipu) {
    }

    @Override // com.amazon.alexa.utils.Provider
    /* renamed from: get */
    public PryonLite5000.Config mo2864get() {
        return PryonConfigProvider.createPryonConfig();
    }
}
