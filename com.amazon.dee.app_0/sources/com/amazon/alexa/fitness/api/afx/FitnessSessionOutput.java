package com.amazon.alexa.fitness.api.afx;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B)\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\bJ\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0006HÆ\u0003J3\u0010\u0011\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/FitnessSessionOutput;", "", "sessions", "", "Lcom/amazon/alexa/fitness/api/afx/AFITSFitnessSession;", "nextToken", "", "error", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/String;)V", "getError", "()Ljava/lang/String;", "getNextToken", "getSessions", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FitnessSessionOutput {
    @Nullable
    private final String error;
    @Nullable
    private final String nextToken;
    @Nullable
    private final List<AFITSFitnessSession> sessions;

    public FitnessSessionOutput(@Nullable List<AFITSFitnessSession> list, @Nullable String str, @Nullable String str2) {
        this.sessions = list;
        this.nextToken = str;
        this.error = str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FitnessSessionOutput copy$default(FitnessSessionOutput fitnessSessionOutput, List list, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = fitnessSessionOutput.sessions;
        }
        if ((i & 2) != 0) {
            str = fitnessSessionOutput.nextToken;
        }
        if ((i & 4) != 0) {
            str2 = fitnessSessionOutput.error;
        }
        return fitnessSessionOutput.copy(list, str, str2);
    }

    @Nullable
    public final List<AFITSFitnessSession> component1() {
        return this.sessions;
    }

    @Nullable
    public final String component2() {
        return this.nextToken;
    }

    @Nullable
    public final String component3() {
        return this.error;
    }

    @NotNull
    public final FitnessSessionOutput copy(@Nullable List<AFITSFitnessSession> list, @Nullable String str, @Nullable String str2) {
        return new FitnessSessionOutput(list, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof FitnessSessionOutput)) {
                return false;
            }
            FitnessSessionOutput fitnessSessionOutput = (FitnessSessionOutput) obj;
            return Intrinsics.areEqual(this.sessions, fitnessSessionOutput.sessions) && Intrinsics.areEqual(this.nextToken, fitnessSessionOutput.nextToken) && Intrinsics.areEqual(this.error, fitnessSessionOutput.error);
        }
        return true;
    }

    @Nullable
    public final String getError() {
        return this.error;
    }

    @Nullable
    public final String getNextToken() {
        return this.nextToken;
    }

    @Nullable
    public final List<AFITSFitnessSession> getSessions() {
        return this.sessions;
    }

    public int hashCode() {
        List<AFITSFitnessSession> list = this.sessions;
        int i = 0;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        String str = this.nextToken;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.error;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FitnessSessionOutput(sessions=");
        outline107.append(this.sessions);
        outline107.append(", nextToken=");
        outline107.append(this.nextToken);
        outline107.append(", error=");
        return GeneratedOutlineSupport1.outline91(outline107, this.error, ")");
    }
}
