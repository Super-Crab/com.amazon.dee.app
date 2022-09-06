package com.amazon.alexa.voice.tta.metrics;

import android.os.SystemClock;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
/* compiled from: EventTime.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\t\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes11.dex */
final /* synthetic */ class EventTime$DefaultClock$systemElapsedRealtime$1 extends FunctionReference implements Function0<Long> {
    public static final EventTime$DefaultClock$systemElapsedRealtime$1 INSTANCE = new EventTime$DefaultClock$systemElapsedRealtime$1();

    EventTime$DefaultClock$systemElapsedRealtime$1() {
        super(0);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "elapsedRealtime";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(SystemClock.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "elapsedRealtime()J";
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Long mo12560invoke() {
        return Long.valueOf(mo12560invoke());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [long, java.lang.Long] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public final Long mo12560invoke() {
        return SystemClock.elapsedRealtime();
    }
}
