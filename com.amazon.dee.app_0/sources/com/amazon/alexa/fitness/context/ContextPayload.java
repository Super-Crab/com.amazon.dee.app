package com.amazon.alexa.fitness.context;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AlexaFitnessContextProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/fitness/context/ContextPayload;", "", "fitnessData", "Lcom/amazon/alexa/fitness/context/FitnessData;", "(Lcom/amazon/alexa/fitness/context/FitnessData;)V", "getFitnessData", "()Lcom/amazon/alexa/fitness/context/FitnessData;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ContextPayload {
    @SerializedName(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME)
    @NotNull
    private final FitnessData fitnessData;

    public ContextPayload(@NotNull FitnessData fitnessData) {
        Intrinsics.checkParameterIsNotNull(fitnessData, "fitnessData");
        this.fitnessData = fitnessData;
    }

    public static /* synthetic */ ContextPayload copy$default(ContextPayload contextPayload, FitnessData fitnessData, int i, Object obj) {
        if ((i & 1) != 0) {
            fitnessData = contextPayload.fitnessData;
        }
        return contextPayload.copy(fitnessData);
    }

    @NotNull
    public final FitnessData component1() {
        return this.fitnessData;
    }

    @NotNull
    public final ContextPayload copy(@NotNull FitnessData fitnessData) {
        Intrinsics.checkParameterIsNotNull(fitnessData, "fitnessData");
        return new ContextPayload(fitnessData);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof ContextPayload) && Intrinsics.areEqual(this.fitnessData, ((ContextPayload) obj).fitnessData);
        }
        return true;
    }

    @NotNull
    public final FitnessData getFitnessData() {
        return this.fitnessData;
    }

    public int hashCode() {
        FitnessData fitnessData = this.fitnessData;
        if (fitnessData != null) {
            return fitnessData.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ContextPayload(fitnessData=");
        outline107.append(this.fitnessData);
        outline107.append(")");
        return outline107.toString();
    }
}
