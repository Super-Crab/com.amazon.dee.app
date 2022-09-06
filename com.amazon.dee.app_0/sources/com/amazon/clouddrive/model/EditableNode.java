package com.amazon.clouddrive.model;

import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class EditableNode implements IEditableNode, Comparable<EditableNode> {
    private CollectionProperties collectionProperties;
    private ContentProperties contentProperties;
    private String description;
    private String id;
    private String kind;
    private List<String> labels;
    private String name;
    private Map<String, List<String>> parentMap;
    private List<String> parents;
    private Map<String, Map<String, String>> properties;
    private Settings settings;
    private String status;
    private Map<String, Map<String, String>> subKindProperties;
    private List<String> subKinds;
    private String symbolicNodeHeroId;
    private List<String> transforms;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof EditableNode) && compareTo((EditableNode) obj) == 0;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public CollectionProperties getCollectionProperties() {
        return this.collectionProperties;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public ContentProperties getContentProperties() {
        return this.contentProperties;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public String getDescription() {
        return this.description;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public String getId() {
        return this.id;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public String getKind() {
        return this.kind;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public List<String> getLabels() {
        return this.labels;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public String getName() {
        return this.name;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public Map<String, List<String>> getParentMap() {
        return this.parentMap;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public List<String> getParents() {
        return this.parents;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public Map<String, Map<String, String>> getProperties() {
        return this.properties;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public Settings getSettings() {
        return this.settings;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public String getStatus() {
        return this.status;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public Map<String, Map<String, String>> getSubKindProperties() {
        return this.subKindProperties;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public List<String> getSubKinds() {
        return this.subKinds;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public String getSymbolicNodeHeroId() {
        return this.symbolicNodeHeroId;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public List<String> getTransforms() {
        return this.transforms;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getName() == null ? 0 : getName().hashCode()) + 1 + (getDescription() == null ? 0 : getDescription().hashCode()) + (getParents() == null ? 0 : getParents().hashCode()) + (getId() == null ? 0 : getId().hashCode()) + (getContentProperties() == null ? 0 : getContentProperties().hashCode()) + (getProperties() == null ? 0 : getProperties().hashCode()) + (getSubKindProperties() == null ? 0 : getSubKindProperties().hashCode()) + (getKind() == null ? 0 : getKind().hashCode()) + (getSubKinds() == null ? 0 : getSubKinds().hashCode()) + (getLabels() == null ? 0 : getLabels().hashCode()) + (getStatus() == null ? 0 : getStatus().hashCode()) + (getCollectionProperties() == null ? 0 : getCollectionProperties().hashCode()) + (getParentMap() == null ? 0 : getParentMap().hashCode()) + (getSettings() == null ? 0 : getSettings().hashCode()) + (getTransforms() == null ? 0 : getTransforms().hashCode());
        if (getSymbolicNodeHeroId() != null) {
            i = getSymbolicNodeHeroId().hashCode();
        }
        return hashCode + i;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setCollectionProperties(CollectionProperties collectionProperties) {
        this.collectionProperties = collectionProperties;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setContentProperties(ContentProperties contentProperties) {
        this.contentProperties = contentProperties;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setDescription(String str) {
        this.description = str;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setId(String str) {
        this.id = str;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setKind(String str) {
        this.kind = str;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setLabels(List<String> list) {
        this.labels = list;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setName(String str) {
        this.name = str;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setParentMap(Map<String, List<String>> map) {
        this.parentMap = map;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setParents(List<String> list) {
        this.parents = list;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setProperties(Map<String, Map<String, String>> map) {
        this.properties = map;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setStatus(String str) {
        this.status = str;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setSubKindProperties(Map<String, Map<String, String>> map) {
        this.subKindProperties = map;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setSubKinds(List<String> list) {
        this.subKinds = list;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setSymbolicNodeHeroId(String str) {
        this.symbolicNodeHeroId = str;
    }

    @Override // com.amazon.clouddrive.model.IEditableNode
    public void setTransforms(List<String> list) {
        this.transforms = list;
    }

    @Override // java.lang.Comparable
    public int compareTo(EditableNode editableNode) {
        if (editableNode == null) {
            return -1;
        }
        if (editableNode == this) {
            return 0;
        }
        String name = getName();
        String name2 = editableNode.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int compareTo = name.compareTo(name2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String description = getDescription();
        String description2 = editableNode.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int compareTo2 = description.compareTo(description2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        List<String> parents = getParents();
        List<String> parents2 = editableNode.getParents();
        if (parents != parents2) {
            if (parents == null) {
                return -1;
            }
            if (parents2 == null) {
                return 1;
            }
            if (parents instanceof Comparable) {
                int compareTo3 = ((Comparable) parents).compareTo(parents2);
                if (compareTo3 != 0) {
                    return compareTo3;
                }
            } else if (!parents.equals(parents2)) {
                int hashCode = parents.hashCode();
                int hashCode2 = parents2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        String id = getId();
        String id2 = editableNode.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int compareTo4 = id.compareTo(id2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        ContentProperties contentProperties = getContentProperties();
        ContentProperties contentProperties2 = editableNode.getContentProperties();
        if (contentProperties != contentProperties2) {
            if (contentProperties == null) {
                return -1;
            }
            if (contentProperties2 == null) {
                return 1;
            }
            int compareTo5 = contentProperties.compareTo(contentProperties2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        Map<String, Map<String, String>> properties = getProperties();
        Map<String, Map<String, String>> properties2 = editableNode.getProperties();
        if (properties != properties2) {
            if (properties == null) {
                return -1;
            }
            if (properties2 == null) {
                return 1;
            }
            if (properties instanceof Comparable) {
                int compareTo6 = ((Comparable) properties).compareTo(properties2);
                if (compareTo6 != 0) {
                    return compareTo6;
                }
            } else if (!properties.equals(properties2)) {
                int hashCode3 = properties.hashCode();
                int hashCode4 = properties2.hashCode();
                if (hashCode3 < hashCode4) {
                    return -1;
                }
                if (hashCode3 > hashCode4) {
                    return 1;
                }
            }
        }
        Map<String, Map<String, String>> subKindProperties = getSubKindProperties();
        Map<String, Map<String, String>> subKindProperties2 = editableNode.getSubKindProperties();
        if (subKindProperties != subKindProperties2) {
            if (subKindProperties == null) {
                return -1;
            }
            if (subKindProperties2 == null) {
                return 1;
            }
            if (subKindProperties instanceof Comparable) {
                int compareTo7 = ((Comparable) subKindProperties).compareTo(subKindProperties2);
                if (compareTo7 != 0) {
                    return compareTo7;
                }
            } else if (!subKindProperties.equals(subKindProperties2)) {
                int hashCode5 = subKindProperties.hashCode();
                int hashCode6 = subKindProperties2.hashCode();
                if (hashCode5 < hashCode6) {
                    return -1;
                }
                if (hashCode5 > hashCode6) {
                    return 1;
                }
            }
        }
        String kind = getKind();
        String kind2 = editableNode.getKind();
        if (kind != kind2) {
            if (kind == null) {
                return -1;
            }
            if (kind2 == null) {
                return 1;
            }
            int compareTo8 = kind.compareTo(kind2);
            if (compareTo8 != 0) {
                return compareTo8;
            }
        }
        List<String> subKinds = getSubKinds();
        List<String> subKinds2 = editableNode.getSubKinds();
        if (subKinds != subKinds2) {
            if (subKinds == null) {
                return -1;
            }
            if (subKinds2 == null) {
                return 1;
            }
            if (subKinds instanceof Comparable) {
                int compareTo9 = ((Comparable) subKinds).compareTo(subKinds2);
                if (compareTo9 != 0) {
                    return compareTo9;
                }
            } else if (!subKinds.equals(subKinds2)) {
                int hashCode7 = subKinds.hashCode();
                int hashCode8 = subKinds2.hashCode();
                if (hashCode7 < hashCode8) {
                    return -1;
                }
                if (hashCode7 > hashCode8) {
                    return 1;
                }
            }
        }
        List<String> labels = getLabels();
        List<String> labels2 = editableNode.getLabels();
        if (labels != labels2) {
            if (labels == null) {
                return -1;
            }
            if (labels2 == null) {
                return 1;
            }
            if (labels instanceof Comparable) {
                int compareTo10 = ((Comparable) labels).compareTo(labels2);
                if (compareTo10 != 0) {
                    return compareTo10;
                }
            } else if (!labels.equals(labels2)) {
                int hashCode9 = labels.hashCode();
                int hashCode10 = labels2.hashCode();
                if (hashCode9 < hashCode10) {
                    return -1;
                }
                if (hashCode9 > hashCode10) {
                    return 1;
                }
            }
        }
        String status = getStatus();
        String status2 = editableNode.getStatus();
        if (status != status2) {
            if (status == null) {
                return -1;
            }
            if (status2 == null) {
                return 1;
            }
            int compareTo11 = status.compareTo(status2);
            if (compareTo11 != 0) {
                return compareTo11;
            }
        }
        CollectionProperties collectionProperties = getCollectionProperties();
        CollectionProperties collectionProperties2 = editableNode.getCollectionProperties();
        if (collectionProperties != collectionProperties2) {
            if (collectionProperties == null) {
                return -1;
            }
            if (collectionProperties2 == null) {
                return 1;
            }
            int compareTo12 = collectionProperties.compareTo(collectionProperties2);
            if (compareTo12 != 0) {
                return compareTo12;
            }
        }
        Map<String, List<String>> parentMap = getParentMap();
        Map<String, List<String>> parentMap2 = editableNode.getParentMap();
        if (parentMap != parentMap2) {
            if (parentMap == null) {
                return -1;
            }
            if (parentMap2 == null) {
                return 1;
            }
            if (parentMap instanceof Comparable) {
                int compareTo13 = ((Comparable) parentMap).compareTo(parentMap2);
                if (compareTo13 != 0) {
                    return compareTo13;
                }
            } else if (!parentMap.equals(parentMap2)) {
                int hashCode11 = parentMap.hashCode();
                int hashCode12 = parentMap2.hashCode();
                if (hashCode11 < hashCode12) {
                    return -1;
                }
                if (hashCode11 > hashCode12) {
                    return 1;
                }
            }
        }
        Settings settings = getSettings();
        Settings settings2 = editableNode.getSettings();
        if (settings != settings2) {
            if (settings == null) {
                return -1;
            }
            if (settings2 == null) {
                return 1;
            }
            int compareTo14 = settings.compareTo(settings2);
            if (compareTo14 != 0) {
                return compareTo14;
            }
        }
        List<String> transforms = getTransforms();
        List<String> transforms2 = editableNode.getTransforms();
        if (transforms != transforms2) {
            if (transforms == null) {
                return -1;
            }
            if (transforms2 == null) {
                return 1;
            }
            if (transforms instanceof Comparable) {
                int compareTo15 = ((Comparable) transforms).compareTo(transforms2);
                if (compareTo15 != 0) {
                    return compareTo15;
                }
            } else if (!transforms.equals(transforms2)) {
                int hashCode13 = transforms.hashCode();
                int hashCode14 = transforms2.hashCode();
                if (hashCode13 < hashCode14) {
                    return -1;
                }
                if (hashCode13 > hashCode14) {
                    return 1;
                }
            }
        }
        String symbolicNodeHeroId = getSymbolicNodeHeroId();
        String symbolicNodeHeroId2 = editableNode.getSymbolicNodeHeroId();
        if (symbolicNodeHeroId != symbolicNodeHeroId2) {
            if (symbolicNodeHeroId == null) {
                return -1;
            }
            if (symbolicNodeHeroId2 == null) {
                return 1;
            }
            int compareTo16 = symbolicNodeHeroId.compareTo(symbolicNodeHeroId2);
            if (compareTo16 != 0) {
                return compareTo16;
            }
        }
        return 0;
    }
}
