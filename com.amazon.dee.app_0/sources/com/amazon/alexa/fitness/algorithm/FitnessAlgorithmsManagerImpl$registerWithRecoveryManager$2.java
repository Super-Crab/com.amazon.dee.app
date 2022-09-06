package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.fitness.api.fitnessSdk.Session;
import com.amazon.alexa.fitness.logs.ILog;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.apache.commons.lang3.SerializationUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FitnessAlgorithmsManagerImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "session", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "recoverableState", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAlgorithmsManagerImpl$registerWithRecoveryManager$2 extends Lambda implements Function2<Session, byte[], Unit> {
    final /* synthetic */ FitnessAlgorithmsManagerImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FitnessAlgorithmsManagerImpl$registerWithRecoveryManager$2(FitnessAlgorithmsManagerImpl fitnessAlgorithmsManagerImpl) {
        super(2);
        this.this$0 = fitnessAlgorithmsManagerImpl;
    }

    @Override // kotlin.jvm.functions.Function2
    /* renamed from: invoke */
    public /* bridge */ /* synthetic */ Unit mo12248invoke(Session session, byte[] bArr) {
        invoke2(session, bArr);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke  reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull Session session, @Nullable byte[] bArr) {
        ILog iLog;
        Intrinsics.checkParameterIsNotNull(session, "session");
        FitnessAlgorithmRecoverableState fitnessAlgorithmRecoverableState = null;
        if (bArr != null) {
            try {
                Object deserialize = SerializationUtils.deserialize(bArr);
                if (deserialize == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.algorithm.FitnessAlgorithmRecoverableState");
                }
                fitnessAlgorithmRecoverableState = (FitnessAlgorithmRecoverableState) deserialize;
            } catch (Exception unused) {
            }
        }
        if (fitnessAlgorithmRecoverableState == null) {
            iLog = this.this$0.log;
            ILog.DefaultImpls.error$default(iLog, "FitnessAlgorithmsManager", "no algorithm recovery state available", null, 4, null);
            return;
        }
        this.this$0.recover(session, fitnessAlgorithmRecoverableState);
    }
}
