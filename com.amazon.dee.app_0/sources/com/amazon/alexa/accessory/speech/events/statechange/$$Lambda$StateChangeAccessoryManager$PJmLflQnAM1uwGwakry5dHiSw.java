package com.amazon.alexa.accessory.speech.events.statechange;

import com.amazon.alexa.accessory.internal.util.Logger;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$StateChangeAccessoryManager$PJmL-flQnAM1uwG-wakry5dHiSw  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$StateChangeAccessoryManager$PJmLflQnAM1uwGwakry5dHiSw implements Consumer {
    public static final /* synthetic */ $$Lambda$StateChangeAccessoryManager$PJmLflQnAM1uwGwakry5dHiSw INSTANCE = new $$Lambda$StateChangeAccessoryManager$PJmLflQnAM1uwGwakry5dHiSw();

    private /* synthetic */ $$Lambda$StateChangeAccessoryManager$PJmLflQnAM1uwGwakry5dHiSw() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Logger.e("%s: monitorEventsForSession error querying deviceInformation", (Throwable) obj, StateChangeAccessoryManager.TAG);
    }
}
