package com.amazon.alexa.accessory.internal.bluetooth;

import io.reactivex.rxjava3.functions.Predicate;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessory.internal.bluetooth.-$$Lambda$DefaultAccessoryScanner$ZV8deflIPPjEEcjrg8eJ0bRs1lc  reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class $$Lambda$DefaultAccessoryScanner$ZV8deflIPPjEEcjrg8eJ0bRs1lc implements Predicate {
    public static final /* synthetic */ $$Lambda$DefaultAccessoryScanner$ZV8deflIPPjEEcjrg8eJ0bRs1lc INSTANCE = new $$Lambda$DefaultAccessoryScanner$ZV8deflIPPjEEcjrg8eJ0bRs1lc();

    private /* synthetic */ $$Lambda$DefaultAccessoryScanner$ZV8deflIPPjEEcjrg8eJ0bRs1lc() {
    }

    @Override // io.reactivex.rxjava3.functions.Predicate
    public final boolean test(Object obj) {
        return DefaultAccessoryScanner.lambda$filterOutKnownAccessory$0((Boolean) obj);
    }
}
