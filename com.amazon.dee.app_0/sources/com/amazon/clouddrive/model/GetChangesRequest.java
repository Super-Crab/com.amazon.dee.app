package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetChangesRequest implements CloudDriveRequest {
    private String checkpoint;
    private Integer chunkSize;
    private String includePurged;
    private Integer limit;
    private Integer maxNodes;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetChangesRequest) && compareTo((CloudDriveRequest) ((GetChangesRequest) obj)) == 0;
    }

    public String getCheckpoint() {
        return this.checkpoint;
    }

    public Integer getChunkSize() {
        return this.chunkSize;
    }

    public String getIncludePurged() {
        return this.includePurged;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public Integer getMaxNodes() {
        return this.maxNodes;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getIncludePurged() == null ? 0 : getIncludePurged().hashCode()) + 1 + (getChunkSize() == null ? 0 : getChunkSize().hashCode()) + (getCheckpoint() == null ? 0 : getCheckpoint().hashCode()) + (getMaxNodes() == null ? 0 : getMaxNodes().hashCode());
        if (getLimit() != null) {
            i = getLimit().hashCode();
        }
        return hashCode + i;
    }

    public void setCheckpoint(String str) {
        this.checkpoint = str;
    }

    public void setChunkSize(Integer num) {
        this.chunkSize = num;
    }

    public void setIncludePurged(String str) {
        this.includePurged = str;
    }

    public void setLimit(Integer num) {
        this.limit = num;
    }

    public void setMaxNodes(Integer num) {
        this.maxNodes = num;
    }

    public GetChangesRequest withCheckpoint(String str) {
        setCheckpoint(str);
        return this;
    }

    public GetChangesRequest withChunkSize(Integer num) {
        setChunkSize(num);
        return this;
    }

    public GetChangesRequest withIncludePurged(String str) {
        setIncludePurged(str);
        return this;
    }

    public GetChangesRequest withLimit(Integer num) {
        setLimit(num);
        return this;
    }

    public GetChangesRequest withMaxNodes(Integer num) {
        setMaxNodes(num);
        return this;
    }

    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof GetChangesRequest)) {
            return 1;
        }
        GetChangesRequest getChangesRequest = (GetChangesRequest) cloudDriveRequest;
        String includePurged = getIncludePurged();
        String includePurged2 = getChangesRequest.getIncludePurged();
        if (includePurged != includePurged2) {
            if (includePurged == null) {
                return -1;
            }
            if (includePurged2 == null) {
                return 1;
            }
            int compareTo = includePurged.compareTo(includePurged2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        Integer chunkSize = getChunkSize();
        Integer chunkSize2 = getChangesRequest.getChunkSize();
        if (chunkSize != chunkSize2) {
            if (chunkSize == null) {
                return -1;
            }
            if (chunkSize2 == null) {
                return 1;
            }
            int compareTo2 = chunkSize.compareTo(chunkSize2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String checkpoint = getCheckpoint();
        String checkpoint2 = getChangesRequest.getCheckpoint();
        if (checkpoint != checkpoint2) {
            if (checkpoint == null) {
                return -1;
            }
            if (checkpoint2 == null) {
                return 1;
            }
            int compareTo3 = checkpoint.compareTo(checkpoint2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        Integer maxNodes = getMaxNodes();
        Integer maxNodes2 = getChangesRequest.getMaxNodes();
        if (maxNodes != maxNodes2) {
            if (maxNodes == null) {
                return -1;
            }
            if (maxNodes2 == null) {
                return 1;
            }
            int compareTo4 = maxNodes.compareTo(maxNodes2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        Integer limit = getLimit();
        Integer limit2 = getChangesRequest.getLimit();
        if (limit != limit2) {
            if (limit == null) {
                return -1;
            }
            if (limit2 == null) {
                return 1;
            }
            int compareTo5 = limit.compareTo(limit2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        return 0;
    }
}
