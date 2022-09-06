package com.amazon.alexa;

import android.content.SharedPreferences;
import com.amazon.alexa.utils.Provider;
import dagger.Lazy;
/* compiled from: OfflinePromptsModule.java */
/* loaded from: classes.dex */
public class mZG implements Provider<SharedPreferences> {
    public final /* synthetic */ Lazy zZm;

    public mZG(QYP qyp, Lazy lazy) {
        this.zZm = lazy;
    }

    @Override // com.amazon.alexa.utils.Provider
    /* renamed from: get */
    public SharedPreferences mo2864get() {
        return (SharedPreferences) this.zZm.mo358get();
    }
}
