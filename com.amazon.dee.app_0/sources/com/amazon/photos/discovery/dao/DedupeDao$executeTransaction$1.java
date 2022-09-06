package com.amazon.photos.discovery.dao;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: DedupeDao.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/amazon/photos/discovery/dao/EditDao;", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DedupeDao$executeTransaction$1 extends Lambda implements Function1<EditDao, Unit> {
    final /* synthetic */ Function1 $transaction;
    final /* synthetic */ DedupeDao this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DedupeDao$executeTransaction$1(DedupeDao dedupeDao, Function1 function1) {
        super(1);
        this.this$0 = dedupeDao;
        this.$transaction = function1;
    }

    @Override // kotlin.jvm.functions.Function1
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12165invoke(EditDao editDao) {
        invoke2(editDao);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull EditDao it2) {
        Intrinsics.checkParameterIsNotNull(it2, "it");
        this.$transaction.mo12165invoke(this.this$0);
    }
}
