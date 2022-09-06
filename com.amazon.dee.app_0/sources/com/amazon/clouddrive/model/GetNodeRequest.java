package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class GetNodeRequest implements CloudDriveRequest {
    private String assetMapping;
    private String fields;
    private String id;
    private Boolean tempLink;

    public GetNodeRequest(String str) {
        this.id = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof GetNodeRequest) && compareTo((CloudDriveRequest) ((GetNodeRequest) obj)) == 0;
    }

    public String getAssetMapping() {
        return this.assetMapping;
    }

    public String getFields() {
        return this.fields;
    }

    public String getId() {
        return this.id;
    }

    public Boolean getTempLink() {
        return this.tempLink;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getId() == null ? 0 : getId().hashCode()) + 1 + (getFields() == null ? 0 : getFields().hashCode());
        if (getAssetMapping() != null) {
            i = getAssetMapping().hashCode();
        }
        return hashCode + i + (getTempLink().booleanValue() ? 1 : 0);
    }

    public void setAssetMapping(String str) {
        this.assetMapping = str;
    }

    public void setFields(String str) {
        this.fields = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setTempLink(Boolean bool) {
        this.tempLink = bool;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    public int compareTo(CloudDriveRequest cloudDriveRequest) {
        if (cloudDriveRequest == null) {
            return -1;
        }
        if (cloudDriveRequest == this) {
            return 0;
        }
        if (!(cloudDriveRequest instanceof GetNodeRequest)) {
            return 1;
        }
        GetNodeRequest getNodeRequest = (GetNodeRequest) cloudDriveRequest;
        String id = getId();
        String id2 = getNodeRequest.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int compareTo = id.compareTo(id2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String fields = getFields();
        String fields2 = getNodeRequest.getFields();
        if (fields != fields2) {
            if (fields == null) {
                return -1;
            }
            if (fields2 == null) {
                return 1;
            }
            int compareTo2 = fields.compareTo(fields2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String assetMapping = getAssetMapping();
        String assetMapping2 = getNodeRequest.getAssetMapping();
        if (assetMapping != assetMapping2) {
            if (assetMapping == null) {
                return -1;
            }
            if (assetMapping2 == null) {
                return 1;
            }
            int compareTo3 = assetMapping.compareTo(assetMapping2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        Boolean tempLink = getTempLink();
        Boolean tempLink2 = getNodeRequest.getTempLink();
        if (tempLink != tempLink2) {
            if (tempLink == null) {
                return -1;
            }
            if (tempLink2 == null) {
                return 1;
            }
            int compareTo4 = tempLink.compareTo(tempLink2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        return 0;
    }
}
