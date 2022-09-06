package com.amazon.alexa.voice.tta.simba;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: UpdateInteractionApi.kt */
@Parcelize
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B+\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0004HÆ\u0003J7\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004HÆ\u0001J\t\u0010\u0015\u001a\u00020\u0006HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0004HÖ\u0001J\u0019\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0006HÖ\u0001R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\b\u001a\u0004\u0018\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006!"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/UpdateInteractionSimbaResponse;", "Lcom/amazon/alexa/voice/tta/simba/SimbaBaseResponse;", "Landroid/os/Parcelable;", "response", "", "statusCode", "", "errorCode", "errorDescription", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getErrorCode", "()Ljava/lang/String;", "getErrorDescription", "getResponse", "getStatusCode", "()I", "component1", "component2", "component3", "component4", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class UpdateInteractionSimbaResponse implements SimbaBaseResponse, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @Nullable
    private final String errorCode;
    @Nullable
    private final String errorDescription;
    @Nullable
    private final String response;
    private final int statusCode;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object createFromParcel(@NotNull Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new UpdateInteractionSimbaResponse(in.readString(), in.readInt(), in.readString(), in.readString());
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object[] newArray(int i) {
            return new UpdateInteractionSimbaResponse[i];
        }
    }

    public UpdateInteractionSimbaResponse(@Nullable String str, int i, @Nullable String str2, @Nullable String str3) {
        this.response = str;
        this.statusCode = i;
        this.errorCode = str2;
        this.errorDescription = str3;
    }

    public static /* synthetic */ UpdateInteractionSimbaResponse copy$default(UpdateInteractionSimbaResponse updateInteractionSimbaResponse, String str, int i, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = updateInteractionSimbaResponse.response;
        }
        if ((i2 & 2) != 0) {
            i = updateInteractionSimbaResponse.getStatusCode();
        }
        if ((i2 & 4) != 0) {
            str2 = updateInteractionSimbaResponse.getErrorCode();
        }
        if ((i2 & 8) != 0) {
            str3 = updateInteractionSimbaResponse.getErrorDescription();
        }
        return updateInteractionSimbaResponse.copy(str, i, str2, str3);
    }

    @Nullable
    public final String component1() {
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
    public final UpdateInteractionSimbaResponse copy(@Nullable String str, int i, @Nullable String str2, @Nullable String str3) {
        return new UpdateInteractionSimbaResponse(str, i, str2, str3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof UpdateInteractionSimbaResponse)) {
                return false;
            }
            UpdateInteractionSimbaResponse updateInteractionSimbaResponse = (UpdateInteractionSimbaResponse) obj;
            return Intrinsics.areEqual(this.response, updateInteractionSimbaResponse.response) && getStatusCode() == updateInteractionSimbaResponse.getStatusCode() && Intrinsics.areEqual(getErrorCode(), updateInteractionSimbaResponse.getErrorCode()) && Intrinsics.areEqual(getErrorDescription(), updateInteractionSimbaResponse.getErrorDescription());
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

    @Nullable
    public final String getResponse() {
        return this.response;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseResponse
    public int getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        String str = this.response;
        int i = 0;
        int statusCode = (getStatusCode() + ((str != null ? str.hashCode() : 0) * 31)) * 31;
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("UpdateInteractionSimbaResponse(response=");
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
        parcel.writeString(this.response);
        parcel.writeInt(this.statusCode);
        parcel.writeString(this.errorCode);
        parcel.writeString(this.errorDescription);
    }
}
