package com.amazon.alexa.fitness.api.afits;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/CreateFitnessSessionRequest;", "", "fitnessSession", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "(Lcom/amazon/alexa/fitness/api/afits/FitnessSession;)V", "getFitnessSession", "()Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class CreateFitnessSessionRequest {
    @NotNull
    private final FitnessSession fitnessSession;

    public CreateFitnessSessionRequest(@NotNull FitnessSession fitnessSession) {
        Intrinsics.checkParameterIsNotNull(fitnessSession, "fitnessSession");
        this.fitnessSession = fitnessSession;
    }

    public static /* synthetic */ CreateFitnessSessionRequest copy$default(CreateFitnessSessionRequest createFitnessSessionRequest, FitnessSession fitnessSession, int i, Object obj) {
        if ((i & 1) != 0) {
            fitnessSession = createFitnessSessionRequest.fitnessSession;
        }
        return createFitnessSessionRequest.copy(fitnessSession);
    }

    @NotNull
    public final FitnessSession component1() {
        return this.fitnessSession;
    }

    @NotNull
    public final CreateFitnessSessionRequest copy(@NotNull FitnessSession fitnessSession) {
        Intrinsics.checkParameterIsNotNull(fitnessSession, "fitnessSession");
        return new CreateFitnessSessionRequest(fitnessSession);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            return (obj instanceof CreateFitnessSessionRequest) && Intrinsics.areEqual(this.fitnessSession, ((CreateFitnessSessionRequest) obj).fitnessSession);
        }
        return true;
    }

    @NotNull
    public final FitnessSession getFitnessSession() {
        return this.fitnessSession;
    }

    public int hashCode() {
        FitnessSession fitnessSession = this.fitnessSession;
        if (fitnessSession != null) {
            return fitnessSession.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateFitnessSessionRequest(fitnessSession=");
        outline107.append(this.fitnessSession);
        outline107.append(")");
        return outline107.toString();
    }
}
