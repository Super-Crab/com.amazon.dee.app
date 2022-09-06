package com.amazon.alexa.fitness.algorithm;

import com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessAlgorithm.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\u0010\tJ\r\u0010\u000e\u001a\u00060\u0003j\u0002`\u0004HÆ\u0003J\u0015\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006HÆ\u0003J-\u0010\u0010\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u00042\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0015\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithmProvider;", "", "algorithmId", "", "Lcom/amazon/alexa/fitness/algorithm/AlgorithmId;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lkotlin/Function1;", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", "Lcom/amazon/alexa/fitness/algorithm/FitnessAlgorithm;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getAlgorithmId", "()Ljava/lang/String;", "getBuild", "()Lkotlin/jvm/functions/Function1;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final class FitnessAlgorithmProvider {
    @NotNull
    private final String algorithmId;
    @NotNull
    private final Function1<SessionConfiguration, FitnessAlgorithm> build;

    /* JADX WARN: Multi-variable type inference failed */
    public FitnessAlgorithmProvider(@NotNull String algorithmId, @NotNull Function1<? super SessionConfiguration, ? extends FitnessAlgorithm> build) {
        Intrinsics.checkParameterIsNotNull(algorithmId, "algorithmId");
        Intrinsics.checkParameterIsNotNull(build, "build");
        this.algorithmId = algorithmId;
        this.build = build;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FitnessAlgorithmProvider copy$default(FitnessAlgorithmProvider fitnessAlgorithmProvider, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = fitnessAlgorithmProvider.algorithmId;
        }
        if ((i & 2) != 0) {
            function1 = fitnessAlgorithmProvider.build;
        }
        return fitnessAlgorithmProvider.copy(str, function1);
    }

    @NotNull
    public final String component1() {
        return this.algorithmId;
    }

    @NotNull
    public final Function1<SessionConfiguration, FitnessAlgorithm> component2() {
        return this.build;
    }

    @NotNull
    public final FitnessAlgorithmProvider copy(@NotNull String algorithmId, @NotNull Function1<? super SessionConfiguration, ? extends FitnessAlgorithm> build) {
        Intrinsics.checkParameterIsNotNull(algorithmId, "algorithmId");
        Intrinsics.checkParameterIsNotNull(build, "build");
        return new FitnessAlgorithmProvider(algorithmId, build);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof FitnessAlgorithmProvider)) {
                return false;
            }
            FitnessAlgorithmProvider fitnessAlgorithmProvider = (FitnessAlgorithmProvider) obj;
            return Intrinsics.areEqual(this.algorithmId, fitnessAlgorithmProvider.algorithmId) && Intrinsics.areEqual(this.build, fitnessAlgorithmProvider.build);
        }
        return true;
    }

    @NotNull
    public final String getAlgorithmId() {
        return this.algorithmId;
    }

    @NotNull
    public final Function1<SessionConfiguration, FitnessAlgorithm> getBuild() {
        return this.build;
    }

    public int hashCode() {
        String str = this.algorithmId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Function1<SessionConfiguration, FitnessAlgorithm> function1 = this.build;
        if (function1 != null) {
            i = function1.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FitnessAlgorithmProvider(algorithmId=");
        outline107.append(this.algorithmId);
        outline107.append(", build=");
        outline107.append(this.build);
        outline107.append(")");
        return outline107.toString();
    }
}
