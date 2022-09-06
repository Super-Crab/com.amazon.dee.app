package com.amazonaws.services.logs.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SearchedLogStream implements Serializable {
    private String logStreamName;
    private Boolean searchedCompletely;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SearchedLogStream)) {
            return false;
        }
        SearchedLogStream searchedLogStream = (SearchedLogStream) obj;
        if ((searchedLogStream.getLogStreamName() == null) ^ (getLogStreamName() == null)) {
            return false;
        }
        if (searchedLogStream.getLogStreamName() != null && !searchedLogStream.getLogStreamName().equals(getLogStreamName())) {
            return false;
        }
        if ((searchedLogStream.getSearchedCompletely() == null) ^ (getSearchedCompletely() == null)) {
            return false;
        }
        return searchedLogStream.getSearchedCompletely() == null || searchedLogStream.getSearchedCompletely().equals(getSearchedCompletely());
    }

    public String getLogStreamName() {
        return this.logStreamName;
    }

    public Boolean getSearchedCompletely() {
        return this.searchedCompletely;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getLogStreamName() == null ? 0 : getLogStreamName().hashCode()) + 31) * 31;
        if (getSearchedCompletely() != null) {
            i = getSearchedCompletely().hashCode();
        }
        return hashCode + i;
    }

    public Boolean isSearchedCompletely() {
        return this.searchedCompletely;
    }

    public void setLogStreamName(String str) {
        this.logStreamName = str;
    }

    public void setSearchedCompletely(Boolean bool) {
        this.searchedCompletely = bool;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getLogStreamName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("logStreamName: ");
            outline1072.append(getLogStreamName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getSearchedCompletely() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("searchedCompletely: ");
            outline1073.append(getSearchedCompletely());
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SearchedLogStream withLogStreamName(String str) {
        this.logStreamName = str;
        return this;
    }

    public SearchedLogStream withSearchedCompletely(Boolean bool) {
        this.searchedCompletely = bool;
        return this;
    }
}
