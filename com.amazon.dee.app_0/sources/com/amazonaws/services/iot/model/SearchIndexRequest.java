package com.amazonaws.services.iot.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazonaws.AmazonWebServiceRequest;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
/* loaded from: classes13.dex */
public class SearchIndexRequest extends AmazonWebServiceRequest implements Serializable {
    private String indexName;
    private Integer maxResults;
    private String nextToken;
    private String queryString;
    private String queryVersion;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof SearchIndexRequest)) {
            return false;
        }
        SearchIndexRequest searchIndexRequest = (SearchIndexRequest) obj;
        if ((searchIndexRequest.getIndexName() == null) ^ (getIndexName() == null)) {
            return false;
        }
        if (searchIndexRequest.getIndexName() != null && !searchIndexRequest.getIndexName().equals(getIndexName())) {
            return false;
        }
        if ((searchIndexRequest.getQueryString() == null) ^ (getQueryString() == null)) {
            return false;
        }
        if (searchIndexRequest.getQueryString() != null && !searchIndexRequest.getQueryString().equals(getQueryString())) {
            return false;
        }
        if ((searchIndexRequest.getNextToken() == null) ^ (getNextToken() == null)) {
            return false;
        }
        if (searchIndexRequest.getNextToken() != null && !searchIndexRequest.getNextToken().equals(getNextToken())) {
            return false;
        }
        if ((searchIndexRequest.getMaxResults() == null) ^ (getMaxResults() == null)) {
            return false;
        }
        if (searchIndexRequest.getMaxResults() != null && !searchIndexRequest.getMaxResults().equals(getMaxResults())) {
            return false;
        }
        if ((searchIndexRequest.getQueryVersion() == null) ^ (getQueryVersion() == null)) {
            return false;
        }
        return searchIndexRequest.getQueryVersion() == null || searchIndexRequest.getQueryVersion().equals(getQueryVersion());
    }

    public String getIndexName() {
        return this.indexName;
    }

    public Integer getMaxResults() {
        return this.maxResults;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getQueryString() {
        return this.queryString;
    }

    public String getQueryVersion() {
        return this.queryVersion;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getIndexName() == null ? 0 : getIndexName().hashCode()) + 31) * 31) + (getQueryString() == null ? 0 : getQueryString().hashCode())) * 31) + (getNextToken() == null ? 0 : getNextToken().hashCode())) * 31) + (getMaxResults() == null ? 0 : getMaxResults().hashCode())) * 31;
        if (getQueryVersion() != null) {
            i = getQueryVersion().hashCode();
        }
        return hashCode + i;
    }

    public void setIndexName(String str) {
        this.indexName = str;
    }

    public void setMaxResults(Integer num) {
        this.maxResults = num;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setQueryString(String str) {
        this.queryString = str;
    }

    public void setQueryVersion(String str) {
        this.queryVersion = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        if (getIndexName() != null) {
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("indexName: ");
            outline1072.append(getIndexName());
            outline1072.append(",");
            outline107.append(outline1072.toString());
        }
        if (getQueryString() != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("queryString: ");
            outline1073.append(getQueryString());
            outline1073.append(",");
            outline107.append(outline1073.toString());
        }
        if (getNextToken() != null) {
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("nextToken: ");
            outline1074.append(getNextToken());
            outline1074.append(",");
            outline107.append(outline1074.toString());
        }
        if (getMaxResults() != null) {
            StringBuilder outline1075 = GeneratedOutlineSupport1.outline107("maxResults: ");
            outline1075.append(getMaxResults());
            outline1075.append(",");
            outline107.append(outline1075.toString());
        }
        if (getQueryVersion() != null) {
            StringBuilder outline1076 = GeneratedOutlineSupport1.outline107("queryVersion: ");
            outline1076.append(getQueryVersion());
            outline107.append(outline1076.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }

    public SearchIndexRequest withIndexName(String str) {
        this.indexName = str;
        return this;
    }

    public SearchIndexRequest withMaxResults(Integer num) {
        this.maxResults = num;
        return this;
    }

    public SearchIndexRequest withNextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public SearchIndexRequest withQueryString(String str) {
        this.queryString = str;
        return this;
    }

    public SearchIndexRequest withQueryVersion(String str) {
        this.queryVersion = str;
        return this;
    }
}
