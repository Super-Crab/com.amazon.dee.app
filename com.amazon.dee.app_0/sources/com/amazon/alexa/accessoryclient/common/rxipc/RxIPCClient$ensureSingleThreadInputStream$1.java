package com.amazon.alexa.accessoryclient.common.rxipc;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;
/* compiled from: RxIPCClient.kt */
@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
/* loaded from: classes6.dex */
final /* synthetic */ class RxIPCClient$ensureSingleThreadInputStream$1 extends MutablePropertyReference0 {
    RxIPCClient$ensureSingleThreadInputStream$1(RxIPCClient rxIPCClient) {
        super(rxIPCClient);
    }

    @Override // kotlin.reflect.KProperty0
    @Nullable
    public Object get() {
        return RxIPCClient.access$getEnsuredOutputStreamThread$p((RxIPCClient) this.receiver);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "ensuredOutputStreamThread";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(RxIPCClient.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getEnsuredOutputStreamThread()Ljava/lang/Thread;";
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(@Nullable Object obj) {
        ((RxIPCClient) this.receiver).ensuredOutputStreamThread = (Thread) obj;
    }
}
