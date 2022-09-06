package com.amazon.photos.discovery.internal.worker;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
/* compiled from: ScanDeletedWorker.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
final /* synthetic */ class ScanDeletedWorker$mainTask$result$1 extends FunctionReference implements Function0<Boolean> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public ScanDeletedWorker$mainTask$result$1(ScanDeletedWorker scanDeletedWorker) {
        super(0, scanDeletedWorker);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "isStopped";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ScanDeletedWorker.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "isStopped()Z";
    }

    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12560invoke() {
        return Boolean.valueOf(mo12560invoke());
    }

    /* JADX WARN: Type inference failed for: r0v2, types: [boolean, java.lang.Boolean] */
    @Override // kotlin.jvm.functions.Function0
    /* renamed from: invoke  reason: collision with other method in class */
    public final Boolean mo12560invoke() {
        return ((ScanDeletedWorker) this.receiver).isStopped();
    }
}
