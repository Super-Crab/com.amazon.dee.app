package com.amazon.alexa.voice.tta.simba;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: GetSearchResultsApi.kt */
@Parcelize
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\rHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\u0019\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SearchQuery;", "Landroid/os/Parcelable;", "queryId", "", "queryText", "(Ljava/lang/String;Ljava/lang/String;)V", "getQueryId", "()Ljava/lang/String;", "getQueryText", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SearchQuery implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @NotNull
    private final String queryId;
    @NotNull
    private final String queryText;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object createFromParcel(@NotNull Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            return new SearchQuery(in.readString(), in.readString());
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object[] newArray(int i) {
            return new SearchQuery[i];
        }
    }

    public SearchQuery(@NotNull String queryId, @NotNull String queryText) {
        Intrinsics.checkParameterIsNotNull(queryId, "queryId");
        Intrinsics.checkParameterIsNotNull(queryText, "queryText");
        this.queryId = queryId;
        this.queryText = queryText;
    }

    public static /* synthetic */ SearchQuery copy$default(SearchQuery searchQuery, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = searchQuery.queryId;
        }
        if ((i & 2) != 0) {
            str2 = searchQuery.queryText;
        }
        return searchQuery.copy(str, str2);
    }

    @NotNull
    public final String component1() {
        return this.queryId;
    }

    @NotNull
    public final String component2() {
        return this.queryText;
    }

    @NotNull
    public final SearchQuery copy(@NotNull String queryId, @NotNull String queryText) {
        Intrinsics.checkParameterIsNotNull(queryId, "queryId");
        Intrinsics.checkParameterIsNotNull(queryText, "queryText");
        return new SearchQuery(queryId, queryText);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SearchQuery)) {
                return false;
            }
            SearchQuery searchQuery = (SearchQuery) obj;
            return Intrinsics.areEqual(this.queryId, searchQuery.queryId) && Intrinsics.areEqual(this.queryText, searchQuery.queryText);
        }
        return true;
    }

    @NotNull
    public final String getQueryId() {
        return this.queryId;
    }

    @NotNull
    public final String getQueryText() {
        return this.queryText;
    }

    public int hashCode() {
        String str = this.queryId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.queryText;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchQuery(queryId=");
        outline107.append(this.queryId);
        outline107.append(", queryText=");
        return GeneratedOutlineSupport1.outline91(outline107, this.queryText, ")");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.queryId);
        parcel.writeString(this.queryText);
    }
}
