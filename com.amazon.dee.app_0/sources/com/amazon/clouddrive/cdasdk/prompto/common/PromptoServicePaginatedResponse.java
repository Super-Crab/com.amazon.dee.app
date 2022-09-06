package com.amazon.clouddrive.cdasdk.prompto.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class PromptoServicePaginatedResponse {
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("nextToken")
    private String nextToken;

    protected boolean canEqual(Object obj) {
        return obj instanceof PromptoServicePaginatedResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PromptoServicePaginatedResponse)) {
            return false;
        }
        PromptoServicePaginatedResponse promptoServicePaginatedResponse = (PromptoServicePaginatedResponse) obj;
        if (!promptoServicePaginatedResponse.canEqual(this)) {
            return false;
        }
        String nextToken = getNextToken();
        String nextToken2 = promptoServicePaginatedResponse.getNextToken();
        if (nextToken != null ? !nextToken.equals(nextToken2) : nextToken2 != null) {
            return false;
        }
        Integer count = getCount();
        Integer count2 = promptoServicePaginatedResponse.getCount();
        return count != null ? count.equals(count2) : count2 == null;
    }

    public Integer getCount() {
        return this.count;
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public int hashCode() {
        String nextToken = getNextToken();
        int i = 43;
        int hashCode = nextToken == null ? 43 : nextToken.hashCode();
        Integer count = getCount();
        int i2 = (hashCode + 59) * 59;
        if (count != null) {
            i = count.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("count")
    public void setCount(Integer num) {
        this.count = num;
    }

    @JsonProperty("nextToken")
    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PromptoServicePaginatedResponse(nextToken=");
        outline107.append(getNextToken());
        outline107.append(", count=");
        outline107.append(getCount());
        outline107.append(")");
        return outline107.toString();
    }
}
