package com.amazon.alexa.tarazed.service;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DefaultAlexaTarazedService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0082@"}, d2 = {"enableTarazedPermissionIfFeatureEnabled", "", "event", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 16})
@DebugMetadata(c = "com.amazon.alexa.tarazed.service.DefaultAlexaTarazedService", f = "DefaultAlexaTarazedService.kt", i = {0, 0}, l = {207}, m = "enableTarazedPermissionIfFeatureEnabled", n = {"this", "event"}, s = {"L$0", "L$1"})
/* loaded from: classes10.dex */
public final class DefaultAlexaTarazedService$enableTarazedPermissionIfFeatureEnabled$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DefaultAlexaTarazedService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultAlexaTarazedService$enableTarazedPermissionIfFeatureEnabled$1(DefaultAlexaTarazedService defaultAlexaTarazedService, Continuation continuation) {
        super(continuation);
        this.this$0 = defaultAlexaTarazedService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.enableTarazedPermissionIfFeatureEnabled(null, this);
    }
}
