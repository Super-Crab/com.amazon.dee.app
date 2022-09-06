package com.amazon.clouddrive.extended.model;

import com.amazon.clouddrive.model.CloudDriveResponse;
import com.amazon.clouddrive.model.SourceInfo;
import java.util.List;
import java.util.Objects;
/* loaded from: classes11.dex */
public class ListSourcesResponse implements CloudDriveResponse {
    private List<SourceInfo> sources;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ListSourcesResponse) {
            return Objects.equals(getSources(), ((ListSourcesResponse) obj).getSources());
        }
        return false;
    }

    public List<SourceInfo> getSources() {
        return this.sources;
    }

    public int hashCode() {
        return Objects.hash(getSources());
    }

    public void setSources(List<SourceInfo> list) {
        this.sources = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveResponse cloudDriveResponse) {
        if (cloudDriveResponse == null) {
            return -1;
        }
        if (cloudDriveResponse == this) {
            return 0;
        }
        if (!(cloudDriveResponse instanceof ListSourcesResponse)) {
            return 1;
        }
        List<SourceInfo> sources = getSources();
        List<SourceInfo> sources2 = ((ListSourcesResponse) cloudDriveResponse).getSources();
        if (sources != sources2) {
            if (sources == null) {
                return -1;
            }
            if (sources2 == null) {
                return 1;
            }
            if (sources instanceof Comparable) {
                int compareTo = ((Comparable) sources).compareTo(sources2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!sources.equals(sources2)) {
                int hashCode = sources.hashCode();
                int hashCode2 = sources2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
