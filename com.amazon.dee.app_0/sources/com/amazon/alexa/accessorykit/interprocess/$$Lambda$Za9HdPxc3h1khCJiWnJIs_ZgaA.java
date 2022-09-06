package com.amazon.alexa.accessorykit.interprocess;

import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessorykit.interprocess.DefaultCallNotificationStateMonitor;
import io.reactivex.rxjava3.functions.Function;
/* compiled from: lambda */
/* renamed from: com.amazon.alexa.accessorykit.interprocess.-$$Lambda$Za9HdPxc3h1khC-JiWnJIs_ZgaA  reason: invalid class name */
/* loaded from: classes6.dex */
public final /* synthetic */ class $$Lambda$Za9HdPxc3h1khCJiWnJIs_ZgaA implements Function {
    public static final /* synthetic */ $$Lambda$Za9HdPxc3h1khCJiWnJIs_ZgaA INSTANCE = new $$Lambda$Za9HdPxc3h1khCJiWnJIs_ZgaA();

    private /* synthetic */ $$Lambda$Za9HdPxc3h1khCJiWnJIs_ZgaA() {
    }

    @Override // io.reactivex.rxjava3.functions.Function
    /* renamed from: apply */
    public final Object mo10358apply(Object obj) {
        return DefaultCallNotificationStateMonitor.CallNotificationStatus.fromState((StateOuterClass.State) obj);
    }
}
