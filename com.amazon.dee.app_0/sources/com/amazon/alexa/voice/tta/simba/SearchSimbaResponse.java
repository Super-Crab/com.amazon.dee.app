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
/* compiled from: GetSearchResultsApi.kt */
@Parcelize
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u00012\u00020\u0002B7\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\rJ\t\u0010\u0017\u001a\u00020\u0004HÆ\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u000bHÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\u000bHÆ\u0003JE\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\t\u0010\u001d\u001a\u00020\tHÖ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!HÖ\u0003J\t\u0010\"\u001a\u00020\tHÖ\u0001J\t\u0010#\u001a\u00020\u000bHÖ\u0001J\u0019\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\tHÖ\u0001R\u0016\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\f\u001a\u0004\u0018\u00010\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006)"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SearchSimbaResponse;", "Lcom/amazon/alexa/voice/tta/simba/SimbaBaseResponse;", "Landroid/os/Parcelable;", "searchQuery", "Lcom/amazon/alexa/voice/tta/simba/SearchQuery;", "searchResults", "", "Lcom/amazon/alexa/voice/tta/simba/SearchResult;", "statusCode", "", "errorCode", "", "errorDescription", "(Lcom/amazon/alexa/voice/tta/simba/SearchQuery;Ljava/util/List;ILjava/lang/String;Ljava/lang/String;)V", "getErrorCode", "()Ljava/lang/String;", "getErrorDescription", "getSearchQuery", "()Lcom/amazon/alexa/voice/tta/simba/SearchQuery;", "getSearchResults", "()Ljava/util/List;", "getStatusCode", "()I", "component1", "component2", "component3", "component4", "component5", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class SearchSimbaResponse implements SimbaBaseResponse, Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @Nullable
    private final String errorCode;
    @Nullable
    private final String errorDescription;
    @NotNull
    private final SearchQuery searchQuery;
    @NotNull
    private final List<SearchResult> searchResults;
    private final int statusCode;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object createFromParcel(@NotNull Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            SearchQuery searchQuery = (SearchQuery) SearchQuery.CREATOR.createFromParcel(in);
            int readInt = in.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            while (readInt != 0) {
                arrayList.add((SearchResult) SearchResult.CREATOR.createFromParcel(in));
                readInt--;
            }
            return new SearchSimbaResponse(searchQuery, arrayList, in.readInt(), in.readString(), in.readString());
        }

        @Override // android.os.Parcelable.Creator
        @NotNull
        public final Object[] newArray(int i) {
            return new SearchSimbaResponse[i];
        }
    }

    public SearchSimbaResponse(@NotNull SearchQuery searchQuery, @NotNull List<SearchResult> searchResults, int i, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(searchQuery, "searchQuery");
        Intrinsics.checkParameterIsNotNull(searchResults, "searchResults");
        this.searchQuery = searchQuery;
        this.searchResults = searchResults;
        this.statusCode = i;
        this.errorCode = str;
        this.errorDescription = str2;
    }

    public static /* synthetic */ SearchSimbaResponse copy$default(SearchSimbaResponse searchSimbaResponse, SearchQuery searchQuery, List list, int i, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            searchQuery = searchSimbaResponse.searchQuery;
        }
        List<SearchResult> list2 = list;
        if ((i2 & 2) != 0) {
            list2 = searchSimbaResponse.searchResults;
        }
        List list3 = list2;
        if ((i2 & 4) != 0) {
            i = searchSimbaResponse.getStatusCode();
        }
        int i3 = i;
        if ((i2 & 8) != 0) {
            str = searchSimbaResponse.getErrorCode();
        }
        String str3 = str;
        if ((i2 & 16) != 0) {
            str2 = searchSimbaResponse.getErrorDescription();
        }
        return searchSimbaResponse.copy(searchQuery, list3, i3, str3, str2);
    }

    @NotNull
    public final SearchQuery component1() {
        return this.searchQuery;
    }

    @NotNull
    public final List<SearchResult> component2() {
        return this.searchResults;
    }

    public final int component3() {
        return getStatusCode();
    }

    @Nullable
    public final String component4() {
        return getErrorCode();
    }

    @Nullable
    public final String component5() {
        return getErrorDescription();
    }

    @NotNull
    public final SearchSimbaResponse copy(@NotNull SearchQuery searchQuery, @NotNull List<SearchResult> searchResults, int i, @Nullable String str, @Nullable String str2) {
        Intrinsics.checkParameterIsNotNull(searchQuery, "searchQuery");
        Intrinsics.checkParameterIsNotNull(searchResults, "searchResults");
        return new SearchSimbaResponse(searchQuery, searchResults, i, str, str2);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SearchSimbaResponse)) {
                return false;
            }
            SearchSimbaResponse searchSimbaResponse = (SearchSimbaResponse) obj;
            return Intrinsics.areEqual(this.searchQuery, searchSimbaResponse.searchQuery) && Intrinsics.areEqual(this.searchResults, searchSimbaResponse.searchResults) && getStatusCode() == searchSimbaResponse.getStatusCode() && Intrinsics.areEqual(getErrorCode(), searchSimbaResponse.getErrorCode()) && Intrinsics.areEqual(getErrorDescription(), searchSimbaResponse.getErrorDescription());
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
    public final SearchQuery getSearchQuery() {
        return this.searchQuery;
    }

    @NotNull
    public final List<SearchResult> getSearchResults() {
        return this.searchResults;
    }

    @Override // com.amazon.alexa.voice.tta.simba.SimbaBaseResponse
    public int getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        SearchQuery searchQuery = this.searchQuery;
        int i = 0;
        int hashCode = (searchQuery != null ? searchQuery.hashCode() : 0) * 31;
        List<SearchResult> list = this.searchResults;
        int statusCode = (getStatusCode() + ((hashCode + (list != null ? list.hashCode() : 0)) * 31)) * 31;
        String errorCode = getErrorCode();
        int hashCode2 = (statusCode + (errorCode != null ? errorCode.hashCode() : 0)) * 31;
        String errorDescription = getErrorDescription();
        if (errorDescription != null) {
            i = errorDescription.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SearchSimbaResponse(searchQuery=");
        outline107.append(this.searchQuery);
        outline107.append(", searchResults=");
        outline107.append(this.searchResults);
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
        this.searchQuery.writeToParcel(parcel, 0);
        List<SearchResult> list = this.searchResults;
        parcel.writeInt(list.size());
        for (SearchResult searchResult : list) {
            searchResult.writeToParcel(parcel, 0);
        }
        parcel.writeInt(this.statusCode);
        parcel.writeString(this.errorCode);
        parcel.writeString(this.errorDescription);
    }
}
