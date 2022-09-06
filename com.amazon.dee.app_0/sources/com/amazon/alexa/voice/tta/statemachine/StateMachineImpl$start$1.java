package com.amazon.alexa.voice.tta.statemachine;

import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference0;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.Nullable;
/* compiled from: StateMachineImpl.kt */
@Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
/* loaded from: classes11.dex */
final /* synthetic */ class StateMachineImpl$start$1 extends MutablePropertyReference0 {
    StateMachineImpl$start$1(StateMachineImpl stateMachineImpl) {
        super(stateMachineImpl);
    }

    @Override // kotlin.reflect.KProperty0
    @Nullable
    public Object get() {
        return StateMachineImpl.access$getInitialState$p((StateMachineImpl) this.receiver);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public String getName() {
        return "initialState";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(StateMachineImpl.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public String getSignature() {
        return "getInitialState()Lcom/amazon/alexa/voice/tta/statemachine/State;";
    }

    @Override // kotlin.reflect.KMutableProperty0
    public void set(@Nullable Object obj) {
        ((StateMachineImpl) this.receiver).initialState = (State) obj;
    }
}
