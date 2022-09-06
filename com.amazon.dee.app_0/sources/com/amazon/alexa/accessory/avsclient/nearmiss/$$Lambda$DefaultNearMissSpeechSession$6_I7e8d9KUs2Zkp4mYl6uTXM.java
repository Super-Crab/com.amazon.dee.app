package com.amazon.alexa.accessory.avsclient.nearmiss;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.avsclient.nearmiss.-$$Lambda$DefaultNearMissSpeechSession$6_I7-e8d9KUs2-Z-kp4mYl6uTXM  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultNearMissSpeechSession$6_I7e8d9KUs2Zkp4mYl6uTXM implements Consumer {
    public static final /* synthetic */ $$Lambda$DefaultNearMissSpeechSession$6_I7e8d9KUs2Zkp4mYl6uTXM INSTANCE = new $$Lambda$DefaultNearMissSpeechSession$6_I7e8d9KUs2Zkp4mYl6uTXM();

    private /* synthetic */ $$Lambda$DefaultNearMissSpeechSession$6_I7e8d9KUs2Zkp4mYl6uTXM() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.d("Failed to upload near miss!", (Throwable) obj);
    }
}
