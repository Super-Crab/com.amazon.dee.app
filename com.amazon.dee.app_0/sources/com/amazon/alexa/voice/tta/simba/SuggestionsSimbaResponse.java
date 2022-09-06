package com.amazon.alexa.voice.tta.simba;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: GetSuggestionsRequestApi.kt */
@Parcelize
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B/\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\tHÆ\u0003J;\u0010\u0017\u001a\u00020\u00002\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\tHÆ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001e\u001a\u00020\tHÖ\u0001J\u0019\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0007HÖ\u0001R\u0016\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\n\u001a\u0004\u0018\u00010\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006$"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SuggestionsSimbaResponse;", "Lcom/amazon/alexa/voice/tta/simba/SimbaBaseResponse;", "Landroid/os/Parcelable;", "suggestions", "", "Lcom/amazon/alexa/voice/tta/simba/Suggestion;", "statusCode", "", "errorCode", "", "errorDescription", "(Ljava/util/List;ILjava/lang/String;Ljava/lang/String;)V", "getErrorCode", "()Ljava/lang/String;", "getErrorDescription", "getStatusCode", "()I", "getSuggestions", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SuggestionsSimbaResponse implements SimbaBaseResponse, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @Nullable
    private final String errorCode;
    @Nullable
    private final String errorDescription;
    private final int statusCode;
    @NotNull
    private final List<Suggestion> suggestions;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object createFromParcel(@NotNull Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            int readInt = in.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            while (readInt != 0) {
                arrayList.add((Suggestion) Suggestion.CREATOR.createFromParcel(in));
                readInt--;
            }
            return new SuggestionsSimbaResponse(arrayList, in.readInt(), in.readString(), in.readString());
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object[] newArray(int i) {
            return new SuggestionsSimbaResponse[i];
        }
    }

    public SuggestionsSimbaResponse(@NotNull List<Suggestion> suggestions, int i, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(suggestions, "suggestions");
        this.suggestions = suggestions;
        this.statusCode = i;
        this.errorCode = str;
        this.errorDescription = str2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SuggestionsSimbaResponse copy$default(SuggestionsSimbaResponse suggestionsSimbaResponse, List list, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = suggestionsSimbaResponse.suggestions;
        }
        if ((i2 & 2) != 0) {
            i = suggestionsSimbaResponse.getStatusCode();
        }
        if ((i2 & 4) != 0) {
            str = suggestionsSimbaResponse.getErrorCode();
        }
        if ((i2 & 8) != 0) {
            str2 = suggestionsSimbaResponse.getErrorDescription();
        }
        return suggestionsSimbaResponse.copy(list, i, str, str2);
    }

    @NotNull
    public final List<Suggestion> component1() {
        return this.suggestions;
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
    public final SuggestionsSimbaResponse copy(@NotNull List<Suggestion> suggestions, int i, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(suggestions, "suggestions");
        return new SuggestionsSimbaResponse(suggestions, i, str, str2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SuggestionsSimbaResponse)) {
                return false;
            }
            SuggestionsSimbaResponse suggestionsSimbaResponse = (SuggestionsSimbaResponse) obj;
            return Intrinsics.areEqual(this.suggestions, suggestionsSimbaResponse.suggestions) && getStatusCode() == suggestionsSimbaResponse.getStatusCode() && Intrinsics.areEqual(getErrorCode(), suggestionsSimbaResponse.getErrorCode()) && Intrinsics.areEqual(getErrorDescription(), suggestionsSimbaResponse.getErrorDescription());
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

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseResponse
    public int getStatusCode() {
        return this.statusCode;
    }

    @NotNull
    public final List<Suggestion> getSuggestions() {
        return this.suggestions;
    }

    public int hashCode() {
        List<Suggestion> list = this.suggestions;
        int i = 0;
        int statusCode = (getStatusCode() + ((list != null ? list.hashCode() : 0) * 31)) * 31;
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
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SuggestionsSimbaResponse(suggestions=");
        outline107.append(this.suggestions);
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
        List<Suggestion> list = this.suggestions;
        parcel.writeInt(list.size());
        for (Suggestion suggestion : list) {
            suggestion.writeToParcel(parcel, 0);
        }
        parcel.writeInt(this.statusCode);
        parcel.writeString(this.errorCode);
        parcel.writeString(this.errorDescription);
    }
}
