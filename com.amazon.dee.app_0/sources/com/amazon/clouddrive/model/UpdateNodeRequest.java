package com.amazon.clouddrive.model;

import com.amazon.clouddrive.utils.Optional;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class UpdateNodeRequest implements CloudDriveRequest {
    private String mId;
    private Optional<String> mName = Optional.absent();
    private Optional<String> mDescription = Optional.absent();
    private Optional<List<String>> mLabels = Optional.absent();
    private Optional<Settings> mSettings = Optional.absent();
    private Optional<List<String>> mSubKinds = Optional.absent();
    private Optional<String> mSymbolicNodeHeroId = Optional.absent();
    private Optional<Map<String, Map<String, String>>> mSubKindProperties = Optional.absent();

    public UpdateNodeRequest(String str) {
        this.mId = str;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof UpdateNodeRequest) && compareTo((CloudDriveRequest) ((UpdateNodeRequest) obj)) == 0;
    }

    public Optional<String> getDescription() {
        return this.mDescription;
    }

    public String getId() {
        return this.mId;
    }

    public Optional<List<String>> getLabels() {
        return this.mLabels;
    }

    public Optional<String> getName() {
        return this.mName;
    }

    public Optional<Settings> getSettings() {
        return this.mSettings;
    }

    public Optional<Map<String, Map<String, String>>> getSubKindProperties() {
        return this.mSubKindProperties;
    }

    public Optional<List<String>> getSubKinds() {
        return this.mSubKinds;
    }

    public Optional<String> getSymbolicNodeHeroId() {
        return this.mSymbolicNodeHeroId;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (!getName().isPresent() ? 0 : getName().hashCode()) + 1 + (!getDescription().isPresent() ? 0 : getDescription().hashCode()) + (getId() == null ? 0 : getId().hashCode()) + (!getLabels().isPresent() ? 0 : getLabels().hashCode()) + (!getSettings().isPresent() ? 0 : getSettings().hashCode()) + (!getSubKinds().isPresent() ? 0 : getSubKinds().hashCode()) + (!getSymbolicNodeHeroId().isPresent() ? 0 : getSymbolicNodeHeroId().hashCode());
        if (getSubKindProperties().isPresent()) {
            i = getSubKindProperties().hashCode();
        }
        return hashCode + i;
    }

    public void setDescription(String str) {
        this.mDescription = Optional.of(str);
    }

    public void setId(String str) {
        this.mId = str;
    }

    public void setLabels(List<String> list) {
        this.mLabels = Optional.of(list);
    }

    public void setName(String str) {
        this.mName = Optional.of(str);
    }

    public void setSettings(Settings settings) {
        this.mSettings = Optional.of(settings);
    }

    public void setSubKindProperties(Map<String, Map<String, String>> map) {
        this.mSubKindProperties = Optional.of(map);
    }

    public void setSubKinds(List<String> list) {
        this.mSubKinds = Optional.of(list);
    }

    public void setSymbolicNodeHeroId(String str) {
        this.mSymbolicNodeHeroId = Optional.of(str);
    }

    public UpdateNodeRequest withDescription(String str) {
        setDescription(str);
        return this;
    }

    public UpdateNodeRequest withLabels(List<String> list) {
        setLabels(list);
        return this;
    }

    public UpdateNodeRequest withName(String str) {
        setName(str);
        return this;
    }

    public UpdateNodeRequest withSubKindProperties(Map<String, Map<String, String>> map) {
        setSubKindProperties(map);
        return this;
    }

    public UpdateNodeRequest withSubKinds(List<String> list) {
        setSubKinds(list);
        return this;
    }

    public UpdateNodeRequest withSymbolicNodeHeroId(String str) {
        setSymbolicNodeHeroId(str);
        return this;
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
        if (!(cloudDriveRequest instanceof UpdateNodeRequest)) {
            return 1;
        }
        UpdateNodeRequest updateNodeRequest = (UpdateNodeRequest) cloudDriveRequest;
        Optional<String> name = getName();
        Optional<String> name2 = updateNodeRequest.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            if (name instanceof Comparable) {
                int compareTo = ((Comparable) name).compareTo(name2);
                if (compareTo != 0) {
                    return compareTo;
                }
            } else if (!name.equals(name2)) {
                int hashCode = name.hashCode();
                int hashCode2 = name2.hashCode();
                if (hashCode < hashCode2) {
                    return -1;
                }
                if (hashCode > hashCode2) {
                    return 1;
                }
            }
        }
        Optional<String> description = getDescription();
        Optional<String> description2 = updateNodeRequest.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            if (description instanceof Comparable) {
                int compareTo2 = ((Comparable) description).compareTo(description2);
                if (compareTo2 != 0) {
                    return compareTo2;
                }
            } else if (!description.equals(description2)) {
                int hashCode3 = description.hashCode();
                int hashCode4 = description2.hashCode();
                if (hashCode3 < hashCode4) {
                    return -1;
                }
                if (hashCode3 > hashCode4) {
                    return 1;
                }
            }
        }
        String id = getId();
        String id2 = updateNodeRequest.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int compareTo3 = id.compareTo(id2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        Optional<List<String>> labels = getLabels();
        Optional<List<String>> labels2 = updateNodeRequest.getLabels();
        if (labels != labels2) {
            if (labels == null) {
                return -1;
            }
            if (labels2 == null) {
                return 1;
            }
            if (labels instanceof Comparable) {
                int compareTo4 = ((Comparable) labels).compareTo(labels2);
                if (compareTo4 != 0) {
                    return compareTo4;
                }
            } else if (!labels.equals(labels2)) {
                int hashCode5 = labels.hashCode();
                int hashCode6 = labels2.hashCode();
                if (hashCode5 < hashCode6) {
                    return -1;
                }
                if (hashCode5 > hashCode6) {
                    return 1;
                }
            }
        }
        Optional<Settings> settings = getSettings();
        Optional<Settings> settings2 = updateNodeRequest.getSettings();
        if (settings != settings2) {
            if (settings == null) {
                return -1;
            }
            if (settings2 == null) {
                return 1;
            }
            if (settings instanceof Comparable) {
                int compareTo5 = ((Comparable) settings).compareTo(settings2);
                if (compareTo5 != 0) {
                    return compareTo5;
                }
            } else if (!settings.equals(settings2)) {
                int hashCode7 = settings.hashCode();
                int hashCode8 = settings2.hashCode();
                if (hashCode7 < hashCode8) {
                    return -1;
                }
                if (hashCode7 > hashCode8) {
                    return 1;
                }
            }
        }
        Optional<List<String>> subKinds = getSubKinds();
        Optional<List<String>> subKinds2 = updateNodeRequest.getSubKinds();
        if (subKinds != subKinds2) {
            if (subKinds == null) {
                return -1;
            }
            if (subKinds2 == null) {
                return 1;
            }
            if (subKinds instanceof Comparable) {
                int compareTo6 = ((Comparable) subKinds).compareTo(subKinds2);
                if (compareTo6 != 0) {
                    return compareTo6;
                }
            } else if (!subKinds.equals(subKinds2)) {
                int hashCode9 = subKinds.hashCode();
                int hashCode10 = subKinds2.hashCode();
                if (hashCode9 < hashCode10) {
                    return -1;
                }
                if (hashCode9 > hashCode10) {
                    return 1;
                }
            }
        }
        Optional<String> symbolicNodeHeroId = getSymbolicNodeHeroId();
        Optional<String> symbolicNodeHeroId2 = updateNodeRequest.getSymbolicNodeHeroId();
        if (symbolicNodeHeroId != symbolicNodeHeroId2) {
            if (symbolicNodeHeroId == null) {
                return -1;
            }
            if (symbolicNodeHeroId2 == null) {
                return 1;
            }
            if (symbolicNodeHeroId instanceof Comparable) {
                int compareTo7 = ((Comparable) symbolicNodeHeroId).compareTo(symbolicNodeHeroId2);
                if (compareTo7 != 0) {
                    return compareTo7;
                }
            } else if (!symbolicNodeHeroId.equals(symbolicNodeHeroId2)) {
                int hashCode11 = symbolicNodeHeroId.hashCode();
                int hashCode12 = symbolicNodeHeroId2.hashCode();
                if (hashCode11 < hashCode12) {
                    return -1;
                }
                if (hashCode11 > hashCode12) {
                    return 1;
                }
            }
        }
        Optional<Map<String, Map<String, String>>> subKindProperties = getSubKindProperties();
        Optional<Map<String, Map<String, String>>> subKindProperties2 = updateNodeRequest.getSubKindProperties();
        if (subKindProperties != subKindProperties2) {
            if (subKindProperties == null) {
                return -1;
            }
            if (subKindProperties2 == null) {
                return 1;
            }
            if (subKindProperties instanceof Comparable) {
                int compareTo8 = ((Comparable) subKindProperties).compareTo(subKindProperties2);
                if (compareTo8 != 0) {
                    return compareTo8;
                }
            } else if (!subKindProperties.equals(subKindProperties2)) {
                int hashCode13 = subKindProperties.hashCode();
                int hashCode14 = subKindProperties2.hashCode();
                if (hashCode13 < hashCode14) {
                    return -1;
                }
                if (hashCode13 > hashCode14) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
