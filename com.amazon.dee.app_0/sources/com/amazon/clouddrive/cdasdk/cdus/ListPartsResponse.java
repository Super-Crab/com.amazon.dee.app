package com.amazon.clouddrive.cdasdk.cdus;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
/* loaded from: classes11.dex */
public class ListPartsResponse {
    @JsonProperty("last-modified")
    private String lastModified;
    @JsonProperty("results")
    private List<PartResult> results;
    @JsonProperty("total-parts")
    private Long totalParts;

    protected boolean canEqual(Object obj) {
        return obj instanceof ListPartsResponse;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ListPartsResponse)) {
            return false;
        }
        ListPartsResponse listPartsResponse = (ListPartsResponse) obj;
        if (!listPartsResponse.canEqual(this)) {
            return false;
        }
        Long totalParts = getTotalParts();
        Long totalParts2 = listPartsResponse.getTotalParts();
        if (totalParts != null ? !totalParts.equals(totalParts2) : totalParts2 != null) {
            return false;
        }
        String lastModified = getLastModified();
        String lastModified2 = listPartsResponse.getLastModified();
        if (lastModified != null ? !lastModified.equals(lastModified2) : lastModified2 != null) {
            return false;
        }
        List<PartResult> results = getResults();
        List<PartResult> results2 = listPartsResponse.getResults();
        return results != null ? results.equals(results2) : results2 == null;
    }

    public String getLastModified() {
        return this.lastModified;
    }

    public List<PartResult> getResults() {
        return this.results;
    }

    public Long getTotalParts() {
        return this.totalParts;
    }

    public int hashCode() {
        Long totalParts = getTotalParts();
        int i = 43;
        int hashCode = totalParts == null ? 43 : totalParts.hashCode();
        String lastModified = getLastModified();
        int hashCode2 = ((hashCode + 59) * 59) + (lastModified == null ? 43 : lastModified.hashCode());
        List<PartResult> results = getResults();
        int i2 = hashCode2 * 59;
        if (results != null) {
            i = results.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("last-modified")
    public void setLastModified(String str) {
        this.lastModified = str;
    }

    @JsonProperty("results")
    public void setResults(List<PartResult> list) {
        this.results = list;
    }

    @JsonProperty("total-parts")
    public void setTotalParts(Long l) {
        this.totalParts = l;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ListPartsResponse(totalParts=");
        outline107.append(getTotalParts());
        outline107.append(", lastModified=");
        outline107.append(getLastModified());
        outline107.append(", results=");
        outline107.append(getResults());
        outline107.append(")");
        return outline107.toString();
    }
}
