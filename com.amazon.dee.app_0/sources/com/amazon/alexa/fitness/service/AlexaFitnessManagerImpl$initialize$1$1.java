package com.amazon.alexa.fitness.service;

import com.amazon.alexa.fitness.service.AlexaFitnessManagerImpl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AlexaFitnessManagerImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final /* synthetic */ class AlexaFitnessManagerImpl$initialize$1$1 extends FunctionReference implements Function0<Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public AlexaFitnessManagerImpl$initialize$1$1(AlexaFitnessManagerImpl.Companion companion) {
        super(0, companion);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "initializeComponentsIfFeatureEnabled";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(AlexaFitnessManagerImpl.Companion.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "initializeComponentsIfFeatureEnabled()V";
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12560invoke() {
        mo12560invoke();
        return Unit.INSTANCE;
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final void mo12560invoke() {
        ((AlexaFitnessManagerImpl.Companion) this.receiver).initializeComponentsIfFeatureEnabled();
    }
}
