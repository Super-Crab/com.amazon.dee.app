package com.amazon.alexa.accessory.frames.provider;

import com.amazon.alexa.accessory.frames.utils.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.frames.provider.-$$Lambda$AccessoryUtil$U7yOZukRYcF3p0ay4C5KvDQUlFs  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$AccessoryUtil$U7yOZukRYcF3p0ay4C5KvDQUlFs implements Consumer {
    public static final /* synthetic */ $$Lambda$AccessoryUtil$U7yOZukRYcF3p0ay4C5KvDQUlFs INSTANCE = new $$Lambda$AccessoryUtil$U7yOZukRYcF3p0ay4C5KvDQUlFs();

    private /* synthetic */ $$Lambda$AccessoryUtil$U7yOZukRYcF3p0ay4C5KvDQUlFs() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e(AccessoryUtil.TAG, "forwardATCommand - CRITICAL ERROR: ", (Throwable) obj);
    }
}
