package com.amazon.alexa.fitness.api.afits;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J*\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0006HÖ\u0001R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/GetAllFitnessSessionResponse;", "", "fitnessSessions", "", "Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "nextToken", "", "([Lcom/amazon/alexa/fitness/api/afits/FitnessSession;Ljava/lang/String;)V", "getFitnessSessions", "()[Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "[Lcom/amazon/alexa/fitness/api/afits/FitnessSession;", "getNextToken", "()Ljava/lang/String;", "component1", "component2", "copy", "([Lcom/amazon/alexa/fitness/api/afits/FitnessSession;Ljava/lang/String;)Lcom/amazon/alexa/fitness/api/afits/GetAllFitnessSessionResponse;", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class GetAllFitnessSessionResponse {
    @NotNull
    private final FitnessSession[] fitnessSessions;
    @Nullable
    private final String nextToken;

    public GetAllFitnessSessionResponse(@NotNull FitnessSession[] fitnessSessions, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(fitnessSessions, "fitnessSessions");
        this.fitnessSessions = fitnessSessions;
        this.nextToken = str;
    }

    public static /* synthetic */ GetAllFitnessSessionResponse copy$default(GetAllFitnessSessionResponse getAllFitnessSessionResponse, FitnessSession[] fitnessSessionArr, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            fitnessSessionArr = getAllFitnessSessionResponse.fitnessSessions;
        }
        if ((i & 2) != 0) {
            str = getAllFitnessSessionResponse.nextToken;
        }
        return getAllFitnessSessionResponse.copy(fitnessSessionArr, str);
    }

    @NotNull
    public final FitnessSession[] component1() {
        return this.fitnessSessions;
    }

    @Nullable
    public final String component2() {
        return this.nextToken;
    }

    @NotNull
    public final GetAllFitnessSessionResponse copy(@NotNull FitnessSession[] fitnessSessions, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(fitnessSessions, "fitnessSessions");
        return new GetAllFitnessSessionResponse(fitnessSessions, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof GetAllFitnessSessionResponse)) {
                return false;
            }
            GetAllFitnessSessionResponse getAllFitnessSessionResponse = (GetAllFitnessSessionResponse) obj;
            return Intrinsics.areEqual(this.fitnessSessions, getAllFitnessSessionResponse.fitnessSessions) && Intrinsics.areEqual(this.nextToken, getAllFitnessSessionResponse.nextToken);
        }
        return true;
    }

    @NotNull
    public final FitnessSession[] getFitnessSessions() {
        return this.fitnessSessions;
    }

    @Nullable
    public final String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        FitnessSession[] fitnessSessionArr = this.fitnessSessions;
        int i = 0;
        int hashCode = (fitnessSessionArr != null ? Arrays.hashCode(fitnessSessionArr) : 0) * 31;
        String str = this.nextToken;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetAllFitnessSessionResponse(fitnessSessions=");
        outline107.append(Arrays.toString(this.fitnessSessions));
        outline107.append(", nextToken=");
        return GeneratedOutlineSupport1.outline91(outline107, this.nextToken, ")");
    }
}
