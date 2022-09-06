package com.amazon.alexa.voice.tta.simba;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: GetSuggestionsRequestApi.kt */
@Parcelize
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003JK\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0003HÖ\u0001J\u0019\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u0019HÖ\u0001R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006%"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/Suggestion;", "Landroid/os/Parcelable;", "resultId", "", "title", "description", JsonFields.ACTION_TYPE, "actionData", "domainId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getActionData", "()Ljava/lang/String;", "getActionType", "getDescription", "getDomainId", "getResultId", "getTitle", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class Suggestion implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @Nullable
    private final String actionData;
    @NotNull
    private final String actionType;
    @Nullable
    private final String description;
    @Nullable
    private final String domainId;
    @NotNull
    private final String resultId;
    @NotNull
    private final String title;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object createFromParcel(@NotNull Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new Suggestion(in.readString(), in.readString(), in.readString(), in.readString(), in.readString(), in.readString());
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object[] newArray(int i) {
            return new Suggestion[i];
        }
    }

    public Suggestion(@NotNull String str, @NotNull String str2, @Nullable String str3, @NotNull String str4, @Nullable String str5, @Nullable String str6) {
        GeneratedOutlineSupport1.outline169(str, "resultId", str2, "title", str4, JsonFields.ACTION_TYPE);
        this.resultId = str;
        this.title = str2;
        this.description = str3;
        this.actionType = str4;
        this.actionData = str5;
        this.domainId = str6;
    }

    public static /* synthetic */ Suggestion copy$default(Suggestion suggestion, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = suggestion.resultId;
        }
        if ((i & 2) != 0) {
            str2 = suggestion.title;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = suggestion.description;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = suggestion.actionType;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = suggestion.actionData;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = suggestion.domainId;
        }
        return suggestion.copy(str, str7, str8, str9, str10, str6);
    }

    @NotNull
    public final String component1() {
        return this.resultId;
    }

    @NotNull
    public final String component2() {
        return this.title;
    }

    @Nullable
    public final String component3() {
        return this.description;
    }

    @NotNull
    public final String component4() {
        return this.actionType;
    }

    @Nullable
    public final String component5() {
        return this.actionData;
    }

    @Nullable
    public final String component6() {
        return this.domainId;
    }

    @NotNull
    public final Suggestion copy(@NotNull String resultId, @NotNull String title, @Nullable String str, @NotNull String actionType, @Nullable String str2, @Nullable String str3) {
        Intrinsics.checkParameterIsNotNull(resultId, "resultId");
        Intrinsics.checkParameterIsNotNull(title, "title");
        Intrinsics.checkParameterIsNotNull(actionType, "actionType");
        return new Suggestion(resultId, title, str, actionType, str2, str3);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Suggestion)) {
                return false;
            }
            Suggestion suggestion = (Suggestion) obj;
            return Intrinsics.areEqual(this.resultId, suggestion.resultId) && Intrinsics.areEqual(this.title, suggestion.title) && Intrinsics.areEqual(this.description, suggestion.description) && Intrinsics.areEqual(this.actionType, suggestion.actionType) && Intrinsics.areEqual(this.actionData, suggestion.actionData) && Intrinsics.areEqual(this.domainId, suggestion.domainId);
        }
        return true;
    }

    @Nullable
    public final String getActionData() {
        return this.actionData;
    }

    @NotNull
    public final String getActionType() {
        return this.actionType;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final String getDomainId() {
        return this.domainId;
    }

    @NotNull
    public final String getResultId() {
        return this.resultId;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    public int hashCode() {
        String str = this.resultId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.title;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.description;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.actionType;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.actionData;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.domainId;
        if (str6 != null) {
            i = str6.hashCode();
        }
        return hashCode5 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Suggestion(resultId=");
        outline107.append(this.resultId);
        outline107.append(", title=");
        outline107.append(this.title);
        outline107.append(", description=");
        outline107.append(this.description);
        outline107.append(", actionType=");
        outline107.append(this.actionType);
        outline107.append(", actionData=");
        outline107.append(this.actionData);
        outline107.append(", domainId=");
        return GeneratedOutlineSupport1.outline91(outline107, this.domainId, ")");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.resultId);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.actionType);
        parcel.writeString(this.actionData);
        parcel.writeString(this.domainId);
    }
}
