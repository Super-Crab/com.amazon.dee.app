package com.amazon.clouddrive.model;

import java.util.Map;
/* loaded from: classes11.dex */
public class BaseSourceInfo implements Comparable<BaseSourceInfo> {
    private String creationTime;
    private String lastModifiedTime;
    private String lastSeenTime;
    private String sourceApplicationName;
    private String sourceId;
    private SourceStatus sourceStatus;
    private Map<String, String> sourceVersionHistory;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof BaseSourceInfo) && compareTo((BaseSourceInfo) obj) == 0;
    }

    public String getCreationTime() {
        return this.creationTime;
    }

    public String getLastModifiedTime() {
        return this.lastModifiedTime;
    }

    public String getLastSeenTime() {
        return this.lastSeenTime;
    }

    public String getSourceApplicationName() {
        return this.sourceApplicationName;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public SourceStatus getSourceStatus() {
        return this.sourceStatus;
    }

    public Map<String, String> getSourceVersionHistory() {
        return this.sourceVersionHistory;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getSourceStatus() == null ? 0 : getSourceStatus().hashCode()) + 1 + (getLastModifiedTime() == null ? 0 : getLastModifiedTime().hashCode()) + (getSourceApplicationName() == null ? 0 : getSourceApplicationName().hashCode()) + (getCreationTime() == null ? 0 : getCreationTime().hashCode()) + (getSourceId() == null ? 0 : getSourceId().hashCode()) + (getSourceVersionHistory() == null ? 0 : getSourceVersionHistory().hashCode());
        if (getLastSeenTime() != null) {
            i = getLastSeenTime().hashCode();
        }
        return hashCode + i;
    }

    public void setCreationTime(String str) {
        this.creationTime = str;
    }

    public void setLastModifiedTime(String str) {
        this.lastModifiedTime = str;
    }

    public void setLastSeenTime(String str) {
        this.lastSeenTime = str;
    }

    public void setSourceApplicationName(String str) {
        this.sourceApplicationName = str;
    }

    public void setSourceId(String str) {
        this.sourceId = str;
    }

    public void setSourceStatus(SourceStatus sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public void setSourceVersionHistory(Map<String, String> map) {
        this.sourceVersionHistory = map;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    public int compareTo(BaseSourceInfo baseSourceInfo) {
        if (baseSourceInfo == null) {
            return -1;
        }
        if (baseSourceInfo == this) {
            return 0;
        }
        SourceStatus sourceStatus = getSourceStatus();
        SourceStatus sourceStatus2 = baseSourceInfo.getSourceStatus();
        if (sourceStatus != sourceStatus2) {
            if (sourceStatus == null) {
                return -1;
            }
            if (sourceStatus2 == null) {
                return 1;
            }
            int compareTo = sourceStatus.compareTo(sourceStatus2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String lastModifiedTime = getLastModifiedTime();
        String lastModifiedTime2 = baseSourceInfo.getLastModifiedTime();
        if (lastModifiedTime != lastModifiedTime2) {
            if (lastModifiedTime == null) {
                return -1;
            }
            if (lastModifiedTime2 == null) {
                return 1;
            }
            int compareTo2 = lastModifiedTime.compareTo(lastModifiedTime2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String sourceApplicationName = getSourceApplicationName();
        String sourceApplicationName2 = baseSourceInfo.getSourceApplicationName();
        if (sourceApplicationName != sourceApplicationName2) {
            if (sourceApplicationName == null) {
                return -1;
            }
            if (sourceApplicationName2 == null) {
                return 1;
            }
            int compareTo3 = sourceApplicationName.compareTo(sourceApplicationName2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        String creationTime = getCreationTime();
        String creationTime2 = baseSourceInfo.getCreationTime();
        if (creationTime != creationTime2) {
            if (creationTime == null) {
                return -1;
            }
            if (creationTime2 == null) {
                return 1;
            }
            int compareTo4 = creationTime.compareTo(creationTime2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        String sourceId = getSourceId();
        String sourceId2 = baseSourceInfo.getSourceId();
        if (sourceId != sourceId2) {
            if (sourceId == null) {
                return -1;
            }
            if (sourceId2 == null) {
                return 1;
            }
            int compareTo5 = sourceId.compareTo(sourceId2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        Map<String, String> sourceVersionHistory = getSourceVersionHistory();
        Map<String, String> sourceVersionHistory2 = baseSourceInfo.getSourceVersionHistory();
        if (sourceVersionHistory != sourceVersionHistory2) {
            if (sourceVersionHistory == null) {
                return -1;
            }
            if (sourceVersionHistory2 == null) {
                return 1;
            }
            if (sourceVersionHistory instanceof Comparable) {
                int compareTo6 = ((Comparable) sourceVersionHistory).compareTo(sourceVersionHistory2);
                if (compareTo6 != 0) {
                    return compareTo6;
                }
            } else if (!sourceVersionHistory.equals(sourceVersionHistory2)) {
                int hashCode = sourceVersionHistory.hashCode();
                int hashCode2 = sourceVersionHistory2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        String lastSeenTime = getLastSeenTime();
        String lastSeenTime2 = baseSourceInfo.getLastSeenTime();
        if (lastSeenTime != lastSeenTime2) {
            if (lastSeenTime == null) {
                return -1;
            }
            if (lastSeenTime2 == null) {
                return 1;
            }
            int compareTo7 = lastSeenTime.compareTo(lastSeenTime2);
            if (compareTo7 != 0) {
                return compareTo7;
            }
        }
        return 0;
    }
}
