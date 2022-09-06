package com.amazon.alexa;

import android.content.Context;
import com.amazon.alexa.utils.PackageNameProvider;
/* compiled from: WakeWordModule.java */
/* loaded from: classes.dex */
public class yrG implements PackageNameProvider {
    public final /* synthetic */ Context zZm;

    public yrG(iPU ipu, Context context) {
        this.zZm = context;
    }

    @Override // com.amazon.alexa.utils.PackageNameProvider
    public String getPackageName() {
        return this.zZm.getPackageName();
    }
}
