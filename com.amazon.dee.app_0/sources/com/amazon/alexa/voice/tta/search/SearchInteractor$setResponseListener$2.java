package com.amazon.alexa.voice.tta.search;

import com.amazon.alexa.voice.tta.permissions.Permission;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SearchInteractor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "Lcom/amazon/alexa/voice/tta/permissions/Permission;", "Lkotlin/ParameterName;", "name", "permission", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final /* synthetic */ class SearchInteractor$setResponseListener$2 extends FunctionReference implements Function1<Permission, Unit> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public SearchInteractor$setResponseListener$2(SearchInteractor searchInteractor) {
        super(1, searchInteractor);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "onPermissionRequired";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(SearchInteractor.class);
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "onPermissionRequired(Lcom/amazon/alexa/voice/tta/permissions/Permission;)V";
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(Permission permission) {
        invoke2(permission);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull Permission p1) {
        Intrinsics.checkParameterIsNotNull(p1, "p1");
        ((SearchInteractor) this.receiver).onPermissionRequired(p1);
    }
}
