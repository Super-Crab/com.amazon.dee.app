package com.amazon.alexa.fitness.context;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaFitnessContextProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005HÆ\u0003J%\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/context/FitnessData;", "", "session", "Lcom/amazon/alexa/fitness/context/Session;", "metrics", "", "(Lcom/amazon/alexa/fitness/context/Session;Ljava/util/List;)V", "getMetrics", "()Ljava/util/List;", "getSession", "()Lcom/amazon/alexa/fitness/context/Session;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessData {
    @NotNull
    private final List<Object> metrics;
    @Nullable
    private final Session session;

    public FitnessData(@Nullable Session session, @NotNull List<? extends Object> metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        this.session = session;
        this.metrics = metrics;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FitnessData copy$default(FitnessData fitnessData, Session session, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            session = fitnessData.session;
        }
        if ((i & 2) != 0) {
            list = fitnessData.metrics;
        }
        return fitnessData.copy(session, list);
    }

    @Nullable
    public final Session component1() {
        return this.session;
    }

    @NotNull
    public final List<Object> component2() {
        return this.metrics;
    }

    @NotNull
    public final FitnessData copy(@Nullable Session session, @NotNull List<? extends Object> metrics) {
        Intrinsics.checkParameterIsNotNull(metrics, "metrics");
        return new FitnessData(session, metrics);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof FitnessData)) {
                return false;
            }
            FitnessData fitnessData = (FitnessData) obj;
            return Intrinsics.areEqual(this.session, fitnessData.session) && Intrinsics.areEqual(this.metrics, fitnessData.metrics);
        }
        return true;
    }

    @NotNull
    public final List<Object> getMetrics() {
        return this.metrics;
    }

    @Nullable
    public final Session getSession() {
        return this.session;
    }

    public int hashCode() {
        Session session = this.session;
        int i = 0;
        int hashCode = (session != null ? session.hashCode() : 0) * 31;
        List<Object> list = this.metrics;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FitnessData(session=");
        outline107.append(this.session);
        outline107.append(", metrics=");
        return GeneratedOutlineSupport1.outline95(outline107, this.metrics, ")");
    }
}
