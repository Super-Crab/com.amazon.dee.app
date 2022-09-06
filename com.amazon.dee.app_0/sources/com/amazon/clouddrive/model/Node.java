package com.amazon.clouddrive.model;

import java.util.List;
/* loaded from: classes11.dex */
public class Node extends EditableNode implements INode {
    private List<String> accessRuleIds;
    private List<Node> assets;
    private String createdBy;
    private String createdDate;
    private String eTagRequest;
    private String eTagResponse;
    private Boolean exclusivelyTrashed;
    private Boolean isProtectedFolder;
    private Boolean isRoot;
    private Boolean isShared;
    private String modifiedDate;
    private Boolean recursivelyTrashed;
    private String tempLink;
    private long version;

    @Override // com.amazon.clouddrive.model.EditableNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof Node) && compareTo((EditableNode) ((Node) obj)) == 0;
    }

    @Override // com.amazon.clouddrive.model.INode
    public List<String> getAccessRuleIds() {
        return this.accessRuleIds;
    }

    @Override // com.amazon.clouddrive.model.INode
    public List<Node> getAssets() {
        return this.assets;
    }

    @Override // com.amazon.clouddrive.model.INode
    public String getCreatedBy() {
        return this.createdBy;
    }

    @Override // com.amazon.clouddrive.model.INode
    public String getCreatedDate() {
        return this.createdDate;
    }

    @Override // com.amazon.clouddrive.model.INode
    public String getETagRequest() {
        return this.eTagRequest;
    }

    @Override // com.amazon.clouddrive.model.INode
    public String getETagResponse() {
        return this.eTagResponse;
    }

    @Override // com.amazon.clouddrive.model.INode
    public String getModifiedDate() {
        return this.modifiedDate;
    }

    @Override // com.amazon.clouddrive.model.INode
    public String getTempLink() {
        return this.tempLink;
    }

    @Override // com.amazon.clouddrive.model.INode
    public long getVersion() {
        return this.version;
    }

    @Override // com.amazon.clouddrive.model.EditableNode
    public int hashCode() {
        int i = 0;
        int version = ((int) getVersion()) + 1 + (getETagResponse() == null ? 0 : getETagResponse().hashCode()) + (getAssets() == null ? 0 : getAssets().hashCode()) + (isShared() == null ? 0 : isShared().hashCode()) + (isRoot() == null ? 0 : isRoot().hashCode()) + (getETagRequest() == null ? 0 : getETagRequest().hashCode()) + (isExclusivelyTrashed() == null ? 0 : isExclusivelyTrashed().hashCode()) + (getCreatedDate() == null ? 0 : getCreatedDate().hashCode()) + (isRecursivelyTrashed() == null ? 0 : isRecursivelyTrashed().hashCode()) + (getModifiedDate() == null ? 0 : getModifiedDate().hashCode()) + (getCreatedBy() == null ? 0 : getCreatedBy().hashCode()) + (getTempLink() == null ? 0 : getTempLink().hashCode()) + (isProtectedFolder() == null ? 0 : isProtectedFolder().hashCode());
        if (getAccessRuleIds() != null) {
            i = getAccessRuleIds().hashCode();
        }
        return ((version + i) * 31) + super.hashCode();
    }

    @Override // com.amazon.clouddrive.model.INode
    public Boolean isExclusivelyTrashed() {
        return this.exclusivelyTrashed;
    }

    @Override // com.amazon.clouddrive.model.INode
    public Boolean isProtectedFolder() {
        return this.isProtectedFolder;
    }

    @Override // com.amazon.clouddrive.model.INode
    public Boolean isRecursivelyTrashed() {
        return this.recursivelyTrashed;
    }

    @Override // com.amazon.clouddrive.model.INode
    public Boolean isRoot() {
        return this.isRoot;
    }

    @Override // com.amazon.clouddrive.model.INode
    public Boolean isShared() {
        return this.isShared;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setAccessRuleIds(List<String> list) {
        this.accessRuleIds = list;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setAssets(List<Node> list) {
        this.assets = list;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setCreatedBy(String str) {
        this.createdBy = str;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setCreatedDate(String str) {
        this.createdDate = str;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setETagRequest(String str) {
        this.eTagRequest = str;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setETagResponse(String str) {
        this.eTagResponse = str;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setExclusivelyTrashed(Boolean bool) {
        this.exclusivelyTrashed = bool;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setIsProtectedFolder(Boolean bool) {
        this.isProtectedFolder = bool;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setIsRoot(Boolean bool) {
        this.isRoot = bool;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setIsShared(Boolean bool) {
        this.isShared = bool;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setModifiedDate(String str) {
        this.modifiedDate = str;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setRecursivelyTrashed(Boolean bool) {
        this.recursivelyTrashed = bool;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setTempLink(String str) {
        this.tempLink = str;
    }

    @Override // com.amazon.clouddrive.model.INode
    public void setVersion(long j) {
        this.version = j;
    }

    @Override // com.amazon.clouddrive.model.EditableNode, java.lang.Comparable
    public int compareTo(EditableNode editableNode) {
        if (editableNode == null) {
            return -1;
        }
        if (editableNode == this) {
            return 0;
        }
        if (!(editableNode instanceof Node)) {
            return 1;
        }
        Node node = (Node) editableNode;
        if (getVersion() < node.getVersion()) {
            return -1;
        }
        if (getVersion() > node.getVersion()) {
            return 1;
        }
        String eTagResponse = getETagResponse();
        String eTagResponse2 = node.getETagResponse();
        if (eTagResponse != eTagResponse2) {
            if (eTagResponse == null) {
                return -1;
            }
            if (eTagResponse2 == null) {
                return 1;
            }
            int compareTo = eTagResponse.compareTo(eTagResponse2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        List<Node> assets = getAssets();
        List<Node> assets2 = node.getAssets();
        if (assets != assets2) {
            if (assets == null) {
                return -1;
            }
            if (assets2 == null) {
                return 1;
            }
            if (assets instanceof Comparable) {
                int compareTo2 = ((Comparable) assets).compareTo(assets2);
                if (compareTo2 != 0) {
                    return compareTo2;
                }
            } else if (!assets.equals(assets2)) {
                int hashCode = assets.hashCode();
                int hashCode2 = assets2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        Boolean isShared = isShared();
        Boolean isShared2 = node.isShared();
        if (isShared != isShared2) {
            if (isShared == null) {
                return -1;
            }
            if (isShared2 == null) {
                return 1;
            }
            int compareTo3 = isShared.compareTo(isShared2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        Boolean isRoot = isRoot();
        Boolean isRoot2 = node.isRoot();
        if (isRoot != isRoot2) {
            if (isRoot == null) {
                return -1;
            }
            if (isRoot2 == null) {
                return 1;
            }
            int compareTo4 = isRoot.compareTo(isRoot2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        String eTagRequest = getETagRequest();
        String eTagRequest2 = node.getETagRequest();
        if (eTagRequest != eTagRequest2) {
            if (eTagRequest == null) {
                return -1;
            }
            if (eTagRequest2 == null) {
                return 1;
            }
            int compareTo5 = eTagRequest.compareTo(eTagRequest2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        Boolean isExclusivelyTrashed = isExclusivelyTrashed();
        Boolean isExclusivelyTrashed2 = node.isExclusivelyTrashed();
        if (isExclusivelyTrashed != isExclusivelyTrashed2) {
            if (isExclusivelyTrashed == null) {
                return -1;
            }
            if (isExclusivelyTrashed2 == null) {
                return 1;
            }
            int compareTo6 = isExclusivelyTrashed.compareTo(isExclusivelyTrashed2);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        String createdDate = getCreatedDate();
        String createdDate2 = node.getCreatedDate();
        if (createdDate != createdDate2) {
            if (createdDate == null) {
                return -1;
            }
            if (createdDate2 == null) {
                return 1;
            }
            int compareTo7 = createdDate.compareTo(createdDate2);
            if (compareTo7 != 0) {
                return compareTo7;
            }
        }
        Boolean isRecursivelyTrashed = isRecursivelyTrashed();
        Boolean isRecursivelyTrashed2 = node.isRecursivelyTrashed();
        if (isRecursivelyTrashed != isRecursivelyTrashed2) {
            if (isRecursivelyTrashed == null) {
                return -1;
            }
            if (isRecursivelyTrashed2 == null) {
                return 1;
            }
            int compareTo8 = isRecursivelyTrashed.compareTo(isRecursivelyTrashed2);
            if (compareTo8 != 0) {
                return compareTo8;
            }
        }
        String modifiedDate = getModifiedDate();
        String modifiedDate2 = node.getModifiedDate();
        if (modifiedDate != modifiedDate2) {
            if (modifiedDate == null) {
                return -1;
            }
            if (modifiedDate2 == null) {
                return 1;
            }
            int compareTo9 = modifiedDate.compareTo(modifiedDate2);
            if (compareTo9 != 0) {
                return compareTo9;
            }
        }
        String createdBy = getCreatedBy();
        String createdBy2 = node.getCreatedBy();
        if (createdBy != createdBy2) {
            if (createdBy == null) {
                return -1;
            }
            if (createdBy2 == null) {
                return 1;
            }
            int compareTo10 = createdBy.compareTo(createdBy2);
            if (compareTo10 != 0) {
                return compareTo10;
            }
        }
        String tempLink = getTempLink();
        String tempLink2 = node.getTempLink();
        if (tempLink != tempLink2) {
            if (tempLink == null) {
                return -1;
            }
            if (tempLink2 == null) {
                return 1;
            }
            int compareTo11 = tempLink.compareTo(tempLink2);
            if (compareTo11 != 0) {
                return compareTo11;
            }
        }
        Boolean isProtectedFolder = isProtectedFolder();
        Boolean isProtectedFolder2 = node.isProtectedFolder();
        if (isProtectedFolder != isProtectedFolder2) {
            if (isProtectedFolder == null) {
                return -1;
            }
            if (isProtectedFolder2 == null) {
                return 1;
            }
            int compareTo12 = isProtectedFolder.compareTo(isProtectedFolder2);
            if (compareTo12 != 0) {
                return compareTo12;
            }
        }
        List<String> accessRuleIds = getAccessRuleIds();
        List<String> accessRuleIds2 = node.getAccessRuleIds();
        if (accessRuleIds != accessRuleIds2) {
            if (accessRuleIds == null) {
                return -1;
            }
            if (accessRuleIds2 == null) {
                return 1;
            }
            if (accessRuleIds instanceof Comparable) {
                int compareTo13 = ((Comparable) accessRuleIds).compareTo(accessRuleIds2);
                if (compareTo13 != 0) {
                    return compareTo13;
                }
            } else if (!accessRuleIds.equals(accessRuleIds2)) {
                int hashCode3 = accessRuleIds.hashCode();
                int hashCode4 = accessRuleIds2.hashCode();
                if (hashCode3 < hashCode4) {
                    return -1;
                }
                if (hashCode3 > hashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(editableNode);
    }
}
