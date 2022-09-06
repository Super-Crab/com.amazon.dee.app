package com.amazon.alexa.mode.statemachine;

import android.util.Log;
import io.reactivex.rxjava3.functions.Consumer;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.mode.statemachine.-$$Lambda$StateMachineEventObserver$qkA9fiJ34uBNnvwOzr26lAbJLVE  reason: invalid class name */
/* loaded from: classes9.dex */
public final /* synthetic */ class $$Lambda$StateMachineEventObserver$qkA9fiJ34uBNnvwOzr26lAbJLVE implements Consumer {
    public static final /* synthetic */ $$Lambda$StateMachineEventObserver$qkA9fiJ34uBNnvwOzr26lAbJLVE INSTANCE = new $$Lambda$StateMachineEventObserver$qkA9fiJ34uBNnvwOzr26lAbJLVE();

    private /* synthetic */ $$Lambda$StateMachineEventObserver$qkA9fiJ34uBNnvwOzr26lAbJLVE() {
    }

    @Override // io.reactivex.rxjava3.functions.Consumer
    public final void accept(Object obj) {
        Log.e("AccessoryObserver", "Unexpected error: " + ((Throwable) obj));
    }
}
