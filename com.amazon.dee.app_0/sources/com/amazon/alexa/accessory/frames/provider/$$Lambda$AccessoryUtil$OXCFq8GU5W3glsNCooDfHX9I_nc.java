package com.amazon.alexa.accessory.frames.provider;

import com.amazon.alexa.accessory.frames.utils.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.frames.provider.-$$Lambda$AccessoryUtil$OXCFq8GU5W3glsNCooDfHX9I_nc  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AccessoryUtil$OXCFq8GU5W3glsNCooDfHX9I_nc implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryUtil$OXCFq8GU5W3glsNCooDfHX9I_nc INSTANCE = new $$Lambda$AccessoryUtil$OXCFq8GU5W3glsNCooDfHX9I_nc();

    private /* synthetic */ $$Lambda$AccessoryUtil$OXCFq8GU5W3glsNCooDfHX9I_nc() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(AccessoryUtil.TAG, "forwardATCommand - CRITICAL ERROR: ", (Throwable) obj);
    }
}
