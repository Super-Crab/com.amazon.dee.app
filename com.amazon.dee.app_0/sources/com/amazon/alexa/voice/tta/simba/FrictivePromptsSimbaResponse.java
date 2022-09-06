package com.amazon.alexa.voice.tta.simba;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: GetFrictivePromptsAndDomainsApi.kt */
@Parcelize
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\nJ\t\u0010\u0012\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\bHÆ\u0003J5\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\bHÆ\u0001J\t\u0010\u0017\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bHÖ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001d\u001a\u00020\bHÖ\u0001J\u0019\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\t\u001a\u0004\u0018\u00010\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006#"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/FrictivePromptsSimbaResponse;", "Lcom/amazon/alexa/voice/tta/simba/SimbaBaseResponse;", "Landroid/os/Parcelable;", "response", "Lcom/amazon/alexa/voice/tta/simba/FrictivePromptsResponse;", "statusCode", "", "errorCode", "", "errorDescription", "(Lcom/amazon/alexa/voice/tta/simba/FrictivePromptsResponse;ILjava/lang/String;Ljava/lang/String;)V", "getErrorCode", "()Ljava/lang/String;", "getErrorDescription", "getResponse", "()Lcom/amazon/alexa/voice/tta/simba/FrictivePromptsResponse;", "getStatusCode", "()I", "component1", "component2", "component3", "component4", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class FrictivePromptsSimbaResponse implements SimbaBaseResponse, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @Nullable
    private final String errorCode;
    @Nullable
    private final String errorDescription;
    @NotNull
    private final FrictivePromptsResponse response;
    private final int statusCode;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object createFromParcel(@NotNull Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new FrictivePromptsSimbaResponse((FrictivePromptsResponse) FrictivePromptsResponse.CREATOR.createFromParcel(in), in.readInt(), in.readString(), in.readString());
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object[] newArray(int i) {
            return new FrictivePromptsSimbaResponse[i];
        }
    }

    public FrictivePromptsSimbaResponse(@NotNull FrictivePromptsResponse response, int i, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.response = response;
        this.statusCode = i;
        this.errorCode = str;
        this.errorDescription = str2;
    }

    public static /* synthetic */ FrictivePromptsSimbaResponse copy$default(FrictivePromptsSimbaResponse frictivePromptsSimbaResponse, FrictivePromptsResponse frictivePromptsResponse, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            frictivePromptsResponse = frictivePromptsSimbaResponse.response;
        }
        if ((i2 & 2) != 0) {
            i = frictivePromptsSimbaResponse.getStatusCode();
        }
        if ((i2 & 4) != 0) {
            str = frictivePromptsSimbaResponse.getErrorCode();
        }
        if ((i2 & 8) != 0) {
            str2 = frictivePromptsSimbaResponse.getErrorDescription();
        }
        return frictivePromptsSimbaResponse.copy(frictivePromptsResponse, i, str, str2);
    }

    @NotNull
    public final FrictivePromptsResponse component1() {
        return this.response;
    }

    public final int component2() {
        return getStatusCode();
    }

    @Nullable
    public final String component3() {
        return getErrorCode();
    }

    @Nullable
    public final String component4() {
        return getErrorDescription();
    }

    @NotNull
    public final FrictivePromptsSimbaResponse copy(@NotNull FrictivePromptsResponse response, int i, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        return new FrictivePromptsSimbaResponse(response, i, str, str2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof FrictivePromptsSimbaResponse)) {
                return false;
            }
            FrictivePromptsSimbaResponse frictivePromptsSimbaResponse = (FrictivePromptsSimbaResponse) obj;
            return Intrinsics.areEqual(this.response, frictivePromptsSimbaResponse.response) && getStatusCode() == frictivePromptsSimbaResponse.getStatusCode() && Intrinsics.areEqual(getErrorCode(), frictivePromptsSimbaResponse.getErrorCode()) && Intrinsics.areEqual(getErrorDescription(), frictivePromptsSimbaResponse.getErrorDescription());
        }
        return true;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseResponse
    @Nullable
    public String getErrorCode() {
        return this.errorCode;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseResponse
    @Nullable
    public String getErrorDescription() {
        return this.errorDescription;
    }

    @NotNull
    public final FrictivePromptsResponse getResponse() {
        return this.response;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseResponse
    public int getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        FrictivePromptsResponse frictivePromptsResponse = this.response;
        int i = 0;
        int statusCode = (getStatusCode() + ((frictivePromptsResponse != null ? frictivePromptsResponse.hashCode() : 0) * 31)) * 31;
        String errorCode = getErrorCode();
        int hashCode = (statusCode + (errorCode != null ? errorCode.hashCode() : 0)) * 31;
        String errorDescription = getErrorDescription();
        if (errorDescription != null) {
            i = errorDescription.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FrictivePromptsSimbaResponse(response=");
        outline107.append(this.response);
        outline107.append(", statusCode=");
        outline107.append(getStatusCode());
        outline107.append(", errorCode=");
        outline107.append(getErrorCode());
        outline107.append(", errorDescription=");
        outline107.append(getErrorDescription());
        outline107.append(")");
        return outline107.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        this.response.writeToParcel(parcel, 0);
        parcel.writeInt(this.statusCode);
        parcel.writeString(this.errorCode);
        parcel.writeString(this.errorDescription);
    }
}
