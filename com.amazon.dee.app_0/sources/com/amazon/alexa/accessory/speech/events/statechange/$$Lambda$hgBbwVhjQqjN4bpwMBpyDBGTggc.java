package com.amazon.alexa.accessory.speech.events.statechange;

import android.util.Pair;
import com.amazon.alexa.accessory.User;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import io.reactivex.rxjava3.functions.BiFunction;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.speech.events.statechange.-$$Lambda$hgBbwVhjQqjN4bpwMBpyDBGTggc  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$hgBbwVhjQqjN4bpwMBpyDBGTggc implements BiFunction {
    public static final /* synthetic */ $$Lambda$hgBbwVhjQqjN4bpwMBpyDBGTggc INSTANCE = new $$Lambda$hgBbwVhjQqjN4bpwMBpyDBGTggc();

    private /* synthetic */ $$Lambda$hgBbwVhjQqjN4bpwMBpyDBGTggc() {
    }

    @Override // io.reactivex.rxjava3.functions.BiFunction
    public final Object apply(Object obj, Object obj2) {
        return new Pair((DeviceRegistration) obj, (User) obj2);
    }
}
