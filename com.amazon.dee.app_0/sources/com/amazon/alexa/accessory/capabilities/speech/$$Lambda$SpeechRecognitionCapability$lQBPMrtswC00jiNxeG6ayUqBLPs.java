package com.amazon.alexa.accessory.capabilities.speech;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.capabilities.speech.-$$Lambda$SpeechRecognitionCapability$lQBPMrtswC00jiNxeG6ayUqBLPs  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$SpeechRecognitionCapability$lQBPMrtswC00jiNxeG6ayUqBLPs implements Consumer {
    public static final /* synthetic */ $$Lambda$SpeechRecognitionCapability$lQBPMrtswC00jiNxeG6ayUqBLPs INSTANCE = new $$Lambda$SpeechRecognitionCapability$lQBPMrtswC00jiNxeG6ayUqBLPs();

    private /* synthetic */ $$Lambda$SpeechRecognitionCapability$lQBPMrtswC00jiNxeG6ayUqBLPs() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("Failed to query device features to get display content. Continuing assuming there is no display.", (Throwable) obj);
    }
}
