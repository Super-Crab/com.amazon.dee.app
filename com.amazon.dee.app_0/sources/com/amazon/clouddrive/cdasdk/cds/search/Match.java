package com.amazon.clouddrive.cdasdk.cds.search;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class Match {
    @JsonProperty("count")
    private Long count;
    @JsonProperty("match")
    private String match;
    @JsonProperty("searchData")
    private SearchData searchData;

    protected boolean canEqual(Object obj) {
        return obj instanceof Match;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Match)) {
            return false;
        }
        Match match = (Match) obj;
        if (!match.canEqual(this)) {
            return false;
        }
        String match2 = getMatch();
        String match3 = match.getMatch();
        if (match2 != null ? !match2.equals(match3) : match3 != null) {
            return false;
        }
        Long count = getCount();
        Long count2 = match.getCount();
        if (count != null ? !count.equals(count2) : count2 != null) {
            return false;
        }
        SearchData searchData = getSearchData();
        SearchData searchData2 = match.getSearchData();
        return searchData != null ? searchData.equals(searchData2) : searchData2 == null;
    }

    public Long getCount() {
        return this.count;
    }

    public String getMatch() {
        return this.match;
    }

    public SearchData getSearchData() {
        return this.searchData;
    }

    public int hashCode() {
        String match = getMatch();
        int i = 43;
        int hashCode = match == null ? 43 : match.hashCode();
        Long count = getCount();
        int hashCode2 = ((hashCode + 59) * 59) + (count == null ? 43 : count.hashCode());
        SearchData searchData = getSearchData();
        int i2 = hashCode2 * 59;
        if (searchData != null) {
            i = searchData.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("count")
    public void setCount(Long l) {
        this.count = l;
    }

    @JsonProperty("match")
    public void setMatch(String str) {
        this.match = str;
    }

    @JsonProperty("searchData")
    public void setSearchData(SearchData searchData) {
        this.searchData = searchData;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Match(match=");
        outline107.append(getMatch());
        outline107.append(", count=");
        outline107.append(getCount());
        outline107.append(", searchData=");
        outline107.append(getSearchData());
        outline107.append(")");
        return outline107.toString();
    }
}
