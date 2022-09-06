package com.amazon.alexa.fitness.repository;

import com.google.common.base.Function;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.SerializationUtils;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionSummaryCacheImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lcom/amazon/alexa/fitness/repository/PendingSessionSummaryMap;", "it", "", "apply"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes8.dex */
final class SessionSummaryCacheImpl$toSessionSummary$1<F, T> implements Function<T, V> {
    public static final SessionSummaryCacheImpl$toSessionSummary$1 INSTANCE = new SessionSummaryCacheImpl$toSessionSummary$1();

    SessionSummaryCacheImpl$toSessionSummary$1() {
    }

    @Override // com.google.common.base.Function
    @Nullable
    /* renamed from: apply */
    public final PendingSessionSummaryMap mo8172apply(@Nullable byte[] bArr) {
        if (bArr == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(bArr, "it!!");
        try {
            Object deserialize = SerializationUtils.deserialize(bArr);
            if (deserialize == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.repository.PendingSessionSummaryMap");
            }
            return (PendingSessionSummaryMap) deserialize;
        } catch (Exception unused) {
            return null;
        }
    }
}
