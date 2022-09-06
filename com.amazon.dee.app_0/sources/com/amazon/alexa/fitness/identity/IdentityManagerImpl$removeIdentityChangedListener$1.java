package com.amazon.alexa.fitness.identity;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* compiled from: IdentityManagerImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0010\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lkotlin/Function0;", "", "Lcom/amazon/alexa/fitness/identity/IdentityManagerIdentityChangedListener;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class IdentityManagerImpl$removeIdentityChangedListener$1 extends Lambda implements Function1<Function0<? extends Unit>, Boolean> {
    final /* synthetic */ Function0 $listener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IdentityManagerImpl$removeIdentityChangedListener$1(Function0 function0) {
        super(1);
        this.$listener = function0;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Boolean mo12165invoke(Function0<? extends Unit> function0) {
        return Boolean.valueOf(invoke2((Function0<Unit>) function0));
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final boolean invoke2(@NotNull Function0<Unit> it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        return Intrinsics.areEqual(it2, this.$listener);
    }
}
