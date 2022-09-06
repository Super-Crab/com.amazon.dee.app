package com.amazon.alexa.fitness.api.afits;

import com.amazon.alexa.voice.tta.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/api/afits/GetPersonIdBackFillStatusResponse;", "", "status", "", Constants.IntentParameters.START_TIMESTAMP, "completionTimestamp", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCompletionTimestamp", "()Ljava/lang/String;", "getStartTimestamp", "getStatus", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class GetPersonIdBackFillStatusResponse {
    @Nullable
    private final String completionTimestamp;
    @Nullable
    private final String startTimestamp;
    @NotNull
    private final String status;

    public GetPersonIdBackFillStatusResponse(@NotNull String status, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        this.status = status;
        this.startTimestamp = str;
        this.completionTimestamp = str2;
    }

    public static /* synthetic */ GetPersonIdBackFillStatusResponse copy$default(GetPersonIdBackFillStatusResponse getPersonIdBackFillStatusResponse, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = getPersonIdBackFillStatusResponse.status;
        }
        if ((i & 2) != 0) {
            str2 = getPersonIdBackFillStatusResponse.startTimestamp;
        }
        if ((i & 4) != 0) {
            str3 = getPersonIdBackFillStatusResponse.completionTimestamp;
        }
        return getPersonIdBackFillStatusResponse.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.status;
    }

    @Nullable
    public final String component2() {
        return this.startTimestamp;
    }

    @Nullable
    public final String component3() {
        return this.completionTimestamp;
    }

    @NotNull
    public final GetPersonIdBackFillStatusResponse copy(@NotNull String status, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        return new GetPersonIdBackFillStatusResponse(status, str, str2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof GetPersonIdBackFillStatusResponse)) {
                return false;
            }
            GetPersonIdBackFillStatusResponse getPersonIdBackFillStatusResponse = (GetPersonIdBackFillStatusResponse) obj;
            return Intrinsics.areEqual(this.status, getPersonIdBackFillStatusResponse.status) && Intrinsics.areEqual(this.startTimestamp, getPersonIdBackFillStatusResponse.startTimestamp) && Intrinsics.areEqual(this.completionTimestamp, getPersonIdBackFillStatusResponse.completionTimestamp);
        }
        return true;
    }

    @Nullable
    public final String getCompletionTimestamp() {
        return this.completionTimestamp;
    }

    @Nullable
    public final String getStartTimestamp() {
        return this.startTimestamp;
    }

    @NotNull
    public final String getStatus() {
        return this.status;
    }

    public int hashCode() {
        String str = this.status;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.startTimestamp;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.completionTimestamp;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("GetPersonIdBackFillStatusResponse(status=");
        outline107.append(this.status);
        outline107.append(", startTimestamp=");
        outline107.append(this.startTimestamp);
        outline107.append(", completionTimestamp=");
        return GeneratedOutlineSupport1.outline91(outline107, this.completionTimestamp, ")");
    }
}
