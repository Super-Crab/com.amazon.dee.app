package com.amazon.clouddrive.cdasdk.cds.common;

import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class EditableNodeInfo {
    @JsonProperty("accessRuleIds")
    private List<String> accessRuleIds;
    @JsonProperty("assetProperties")
    private AssetProperties assetProperties;
    @JsonProperty("childAssetTypeInfo")
    private List<ChildAssetTypeInfo> childAssetTypeInfo;
    @JsonProperty("clientProperties")
    private ClientProperties clientProperties;
    @JsonProperty("collectionProperties")
    private CollectionProperties collectionProperties;
    @JsonProperty("contentProperties")
    private ContentProperties contentProperties;
    @JsonProperty("description")
    private String description;
    @JsonProperty("familyId")
    private String familyId;
    @JsonProperty("groupPermissions")
    private List<GroupPermission> groupPermissions;
    @JsonProperty("id")
    private String id;
    @JsonProperty("keywords")
    private List<String> keywords;
    @JsonProperty(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME)
    private String kind;
    @JsonProperty("labels")
    private List<String> labels;
    @JsonProperty("name")
    private String name;
    @JsonProperty("parentMap")
    private Map<String, List<String>> parentMap;
    @JsonProperty("parents")
    private List<String> parents;
    @JsonProperty("properties")
    private Map<String, Map<String, String>> properties;
    @JsonProperty("protectedFolder")
    private Boolean protectedFolder;
    @JsonProperty("restricted")
    private Boolean restricted;
    @JsonProperty("secondaryProperties")
    private Map<String, Map<String, String>> secondaryProperties;
    @JsonProperty("settings")
    private Settings settings;
    @JsonProperty("sourceId")
    private String sourceId;
    @JsonProperty("status")
    private String status;
    @JsonProperty("subKindProperties")
    private SubKindProperties subKindProperties;
    @JsonProperty("subKinds")
    private List<String> subKinds;
    @JsonProperty("symbolicNodeHeroId")
    private String symbolicNodeHeroId;
    @JsonProperty(PhotoSearchCategory.THINGS)
    private List<String> things;
    @JsonProperty("transforms")
    private List<String> transforms;
    @JsonProperty("xAccntParentMap")
    private Map<String, List<String>> xAccntParentMap;
    @JsonProperty("xAccntParents")
    private List<String> xAccntParents;

    protected boolean canEqual(Object obj) {
        return obj instanceof EditableNodeInfo;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EditableNodeInfo)) {
            return false;
        }
        EditableNodeInfo editableNodeInfo = (EditableNodeInfo) obj;
        if (!editableNodeInfo.canEqual(this)) {
            return false;
        }
        String id = getId();
        String id2 = editableNodeInfo.getId();
        if (id != null ? !id.equals(id2) : id2 != null) {
            return false;
        }
        String familyId = getFamilyId();
        String familyId2 = editableNodeInfo.getFamilyId();
        if (familyId != null ? !familyId.equals(familyId2) : familyId2 != null) {
            return false;
        }
        String name = getName();
        String name2 = editableNodeInfo.getName();
        if (name != null ? !name.equals(name2) : name2 != null) {
            return false;
        }
        String description = getDescription();
        String description2 = editableNodeInfo.getDescription();
        if (description != null ? !description.equals(description2) : description2 != null) {
            return false;
        }
        List<String> labels = getLabels();
        List<String> labels2 = editableNodeInfo.getLabels();
        if (labels != null ? !labels.equals(labels2) : labels2 != null) {
            return false;
        }
        String kind = getKind();
        String kind2 = editableNodeInfo.getKind();
        if (kind != null ? !kind.equals(kind2) : kind2 != null) {
            return false;
        }
        List<String> subKinds = getSubKinds();
        List<String> subKinds2 = editableNodeInfo.getSubKinds();
        if (subKinds != null ? !subKinds.equals(subKinds2) : subKinds2 != null) {
            return false;
        }
        List<String> parents = getParents();
        List<String> parents2 = editableNodeInfo.getParents();
        if (parents != null ? !parents.equals(parents2) : parents2 != null) {
            return false;
        }
        List<String> xAccntParents = getXAccntParents();
        List<String> xAccntParents2 = editableNodeInfo.getXAccntParents();
        if (xAccntParents != null ? !xAccntParents.equals(xAccntParents2) : xAccntParents2 != null) {
            return false;
        }
        Map<String, List<String>> parentMap = getParentMap();
        Map<String, List<String>> parentMap2 = editableNodeInfo.getParentMap();
        if (parentMap != null ? !parentMap.equals(parentMap2) : parentMap2 != null) {
            return false;
        }
        Map<String, List<String>> xAccntParentMap = getXAccntParentMap();
        Map<String, List<String>> xAccntParentMap2 = editableNodeInfo.getXAccntParentMap();
        if (xAccntParentMap != null ? !xAccntParentMap.equals(xAccntParentMap2) : xAccntParentMap2 != null) {
            return false;
        }
        Boolean restricted = getRestricted();
        Boolean restricted2 = editableNodeInfo.getRestricted();
        if (restricted != null ? !restricted.equals(restricted2) : restricted2 != null) {
            return false;
        }
        Boolean protectedFolder = getProtectedFolder();
        Boolean protectedFolder2 = editableNodeInfo.getProtectedFolder();
        if (protectedFolder != null ? !protectedFolder.equals(protectedFolder2) : protectedFolder2 != null) {
            return false;
        }
        Map<String, Map<String, String>> properties = getProperties();
        Map<String, Map<String, String>> properties2 = editableNodeInfo.getProperties();
        if (properties != null ? !properties.equals(properties2) : properties2 != null) {
            return false;
        }
        Map<String, Map<String, String>> secondaryProperties = getSecondaryProperties();
        Map<String, Map<String, String>> secondaryProperties2 = editableNodeInfo.getSecondaryProperties();
        if (secondaryProperties != null ? !secondaryProperties.equals(secondaryProperties2) : secondaryProperties2 != null) {
            return false;
        }
        ContentProperties contentProperties = getContentProperties();
        ContentProperties contentProperties2 = editableNodeInfo.getContentProperties();
        if (contentProperties != null ? !contentProperties.equals(contentProperties2) : contentProperties2 != null) {
            return false;
        }
        String status = getStatus();
        String status2 = editableNodeInfo.getStatus();
        if (status != null ? !status.equals(status2) : status2 != null) {
            return false;
        }
        Settings settings = getSettings();
        Settings settings2 = editableNodeInfo.getSettings();
        if (settings != null ? !settings.equals(settings2) : settings2 != null) {
            return false;
        }
        List<String> transforms = getTransforms();
        List<String> transforms2 = editableNodeInfo.getTransforms();
        if (transforms != null ? !transforms.equals(transforms2) : transforms2 != null) {
            return false;
        }
        CollectionProperties collectionProperties = getCollectionProperties();
        CollectionProperties collectionProperties2 = editableNodeInfo.getCollectionProperties();
        if (collectionProperties != null ? !collectionProperties.equals(collectionProperties2) : collectionProperties2 != null) {
            return false;
        }
        String sourceId = getSourceId();
        String sourceId2 = editableNodeInfo.getSourceId();
        if (sourceId != null ? !sourceId.equals(sourceId2) : sourceId2 != null) {
            return false;
        }
        List<String> keywords = getKeywords();
        List<String> keywords2 = editableNodeInfo.getKeywords();
        if (keywords != null ? !keywords.equals(keywords2) : keywords2 != null) {
            return false;
        }
        ClientProperties clientProperties = getClientProperties();
        ClientProperties clientProperties2 = editableNodeInfo.getClientProperties();
        if (clientProperties != null ? !clientProperties.equals(clientProperties2) : clientProperties2 != null) {
            return false;
        }
        List<String> things = getThings();
        List<String> things2 = editableNodeInfo.getThings();
        if (things != null ? !things.equals(things2) : things2 != null) {
            return false;
        }
        List<String> accessRuleIds = getAccessRuleIds();
        List<String> accessRuleIds2 = editableNodeInfo.getAccessRuleIds();
        if (accessRuleIds != null ? !accessRuleIds.equals(accessRuleIds2) : accessRuleIds2 != null) {
            return false;
        }
        List<ChildAssetTypeInfo> childAssetTypeInfo = getChildAssetTypeInfo();
        List<ChildAssetTypeInfo> childAssetTypeInfo2 = editableNodeInfo.getChildAssetTypeInfo();
        if (childAssetTypeInfo != null ? !childAssetTypeInfo.equals(childAssetTypeInfo2) : childAssetTypeInfo2 != null) {
            return false;
        }
        AssetProperties assetProperties = getAssetProperties();
        AssetProperties assetProperties2 = editableNodeInfo.getAssetProperties();
        if (assetProperties != null ? !assetProperties.equals(assetProperties2) : assetProperties2 != null) {
            return false;
        }
        List<GroupPermission> groupPermissions = getGroupPermissions();
        List<GroupPermission> groupPermissions2 = editableNodeInfo.getGroupPermissions();
        if (groupPermissions != null ? !groupPermissions.equals(groupPermissions2) : groupPermissions2 != null) {
            return false;
        }
        SubKindProperties subKindProperties = getSubKindProperties();
        SubKindProperties subKindProperties2 = editableNodeInfo.getSubKindProperties();
        if (subKindProperties != null ? !subKindProperties.equals(subKindProperties2) : subKindProperties2 != null) {
            return false;
        }
        String symbolicNodeHeroId = getSymbolicNodeHeroId();
        String symbolicNodeHeroId2 = editableNodeInfo.getSymbolicNodeHeroId();
        return symbolicNodeHeroId != null ? symbolicNodeHeroId.equals(symbolicNodeHeroId2) : symbolicNodeHeroId2 == null;
    }

    public List<String> getAccessRuleIds() {
        return this.accessRuleIds;
    }

    public AssetProperties getAssetProperties() {
        return this.assetProperties;
    }

    public List<ChildAssetTypeInfo> getChildAssetTypeInfo() {
        return this.childAssetTypeInfo;
    }

    public ClientProperties getClientProperties() {
        return this.clientProperties;
    }

    public CollectionProperties getCollectionProperties() {
        return this.collectionProperties;
    }

    public ContentProperties getContentProperties() {
        return this.contentProperties;
    }

    public String getDescription() {
        return this.description;
    }

    public String getFamilyId() {
        return this.familyId;
    }

    public List<GroupPermission> getGroupPermissions() {
        return this.groupPermissions;
    }

    public String getId() {
        return this.id;
    }

    public List<String> getKeywords() {
        return this.keywords;
    }

    public String getKind() {
        return this.kind;
    }

    public List<String> getLabels() {
        return this.labels;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, List<String>> getParentMap() {
        return this.parentMap;
    }

    public List<String> getParents() {
        return this.parents;
    }

    public Map<String, Map<String, String>> getProperties() {
        return this.properties;
    }

    public Boolean getProtectedFolder() {
        return this.protectedFolder;
    }

    public Boolean getRestricted() {
        return this.restricted;
    }

    public Map<String, Map<String, String>> getSecondaryProperties() {
        return this.secondaryProperties;
    }

    public Settings getSettings() {
        return this.settings;
    }

    public String getSourceId() {
        return this.sourceId;
    }

    public String getStatus() {
        return this.status;
    }

    public SubKindProperties getSubKindProperties() {
        return this.subKindProperties;
    }

    public List<String> getSubKinds() {
        return this.subKinds;
    }

    public String getSymbolicNodeHeroId() {
        return this.symbolicNodeHeroId;
    }

    public List<String> getThings() {
        return this.things;
    }

    public List<String> getTransforms() {
        return this.transforms;
    }

    public Map<String, List<String>> getXAccntParentMap() {
        return this.xAccntParentMap;
    }

    public List<String> getXAccntParents() {
        return this.xAccntParents;
    }

    public int hashCode() {
        String id = getId();
        int i = 43;
        int hashCode = id == null ? 43 : id.hashCode();
        String familyId = getFamilyId();
        int hashCode2 = ((hashCode + 59) * 59) + (familyId == null ? 43 : familyId.hashCode());
        String name = getName();
        int hashCode3 = (hashCode2 * 59) + (name == null ? 43 : name.hashCode());
        String description = getDescription();
        int hashCode4 = (hashCode3 * 59) + (description == null ? 43 : description.hashCode());
        List<String> labels = getLabels();
        int hashCode5 = (hashCode4 * 59) + (labels == null ? 43 : labels.hashCode());
        String kind = getKind();
        int hashCode6 = (hashCode5 * 59) + (kind == null ? 43 : kind.hashCode());
        List<String> subKinds = getSubKinds();
        int hashCode7 = (hashCode6 * 59) + (subKinds == null ? 43 : subKinds.hashCode());
        List<String> parents = getParents();
        int hashCode8 = (hashCode7 * 59) + (parents == null ? 43 : parents.hashCode());
        List<String> xAccntParents = getXAccntParents();
        int hashCode9 = (hashCode8 * 59) + (xAccntParents == null ? 43 : xAccntParents.hashCode());
        Map<String, List<String>> parentMap = getParentMap();
        int hashCode10 = (hashCode9 * 59) + (parentMap == null ? 43 : parentMap.hashCode());
        Map<String, List<String>> xAccntParentMap = getXAccntParentMap();
        int hashCode11 = (hashCode10 * 59) + (xAccntParentMap == null ? 43 : xAccntParentMap.hashCode());
        Boolean restricted = getRestricted();
        int hashCode12 = (hashCode11 * 59) + (restricted == null ? 43 : restricted.hashCode());
        Boolean protectedFolder = getProtectedFolder();
        int hashCode13 = (hashCode12 * 59) + (protectedFolder == null ? 43 : protectedFolder.hashCode());
        Map<String, Map<String, String>> properties = getProperties();
        int hashCode14 = (hashCode13 * 59) + (properties == null ? 43 : properties.hashCode());
        Map<String, Map<String, String>> secondaryProperties = getSecondaryProperties();
        int hashCode15 = (hashCode14 * 59) + (secondaryProperties == null ? 43 : secondaryProperties.hashCode());
        ContentProperties contentProperties = getContentProperties();
        int hashCode16 = (hashCode15 * 59) + (contentProperties == null ? 43 : contentProperties.hashCode());
        String status = getStatus();
        int hashCode17 = (hashCode16 * 59) + (status == null ? 43 : status.hashCode());
        Settings settings = getSettings();
        int hashCode18 = (hashCode17 * 59) + (settings == null ? 43 : settings.hashCode());
        List<String> transforms = getTransforms();
        int hashCode19 = (hashCode18 * 59) + (transforms == null ? 43 : transforms.hashCode());
        CollectionProperties collectionProperties = getCollectionProperties();
        int hashCode20 = (hashCode19 * 59) + (collectionProperties == null ? 43 : collectionProperties.hashCode());
        String sourceId = getSourceId();
        int hashCode21 = (hashCode20 * 59) + (sourceId == null ? 43 : sourceId.hashCode());
        List<String> keywords = getKeywords();
        int hashCode22 = (hashCode21 * 59) + (keywords == null ? 43 : keywords.hashCode());
        ClientProperties clientProperties = getClientProperties();
        int hashCode23 = (hashCode22 * 59) + (clientProperties == null ? 43 : clientProperties.hashCode());
        List<String> things = getThings();
        int hashCode24 = (hashCode23 * 59) + (things == null ? 43 : things.hashCode());
        List<String> accessRuleIds = getAccessRuleIds();
        int hashCode25 = (hashCode24 * 59) + (accessRuleIds == null ? 43 : accessRuleIds.hashCode());
        List<ChildAssetTypeInfo> childAssetTypeInfo = getChildAssetTypeInfo();
        int hashCode26 = (hashCode25 * 59) + (childAssetTypeInfo == null ? 43 : childAssetTypeInfo.hashCode());
        AssetProperties assetProperties = getAssetProperties();
        int hashCode27 = (hashCode26 * 59) + (assetProperties == null ? 43 : assetProperties.hashCode());
        List<GroupPermission> groupPermissions = getGroupPermissions();
        int hashCode28 = (hashCode27 * 59) + (groupPermissions == null ? 43 : groupPermissions.hashCode());
        SubKindProperties subKindProperties = getSubKindProperties();
        int hashCode29 = (hashCode28 * 59) + (subKindProperties == null ? 43 : subKindProperties.hashCode());
        String symbolicNodeHeroId = getSymbolicNodeHeroId();
        int i2 = hashCode29 * 59;
        if (symbolicNodeHeroId != null) {
            i = symbolicNodeHeroId.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("accessRuleIds")
    public void setAccessRuleIds(List<String> list) {
        this.accessRuleIds = list;
    }

    @JsonProperty("assetProperties")
    public void setAssetProperties(AssetProperties assetProperties) {
        this.assetProperties = assetProperties;
    }

    @JsonProperty("childAssetTypeInfo")
    public void setChildAssetTypeInfo(List<ChildAssetTypeInfo> list) {
        this.childAssetTypeInfo = list;
    }

    @JsonProperty("clientProperties")
    public void setClientProperties(ClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

    @JsonProperty("collectionProperties")
    public void setCollectionProperties(CollectionProperties collectionProperties) {
        this.collectionProperties = collectionProperties;
    }

    @JsonProperty("contentProperties")
    public void setContentProperties(ContentProperties contentProperties) {
        this.contentProperties = contentProperties;
    }

    @JsonProperty("description")
    public void setDescription(String str) {
        this.description = str;
    }

    @JsonProperty("familyId")
    public void setFamilyId(String str) {
        this.familyId = str;
    }

    @JsonProperty("groupPermissions")
    public void setGroupPermissions(List<GroupPermission> list) {
        this.groupPermissions = list;
    }

    @JsonProperty("id")
    public void setId(String str) {
        this.id = str;
    }

    @JsonProperty("keywords")
    public void setKeywords(List<String> list) {
        this.keywords = list;
    }

    @JsonProperty(ReactBridgeSerializer.CONTACT_DISCRIMINATOR_PROPERTY_NAME)
    public void setKind(String str) {
        this.kind = str;
    }

    @JsonProperty("labels")
    public void setLabels(List<String> list) {
        this.labels = list;
    }

    @JsonProperty("name")
    public void setName(String str) {
        this.name = str;
    }

    @JsonProperty("parentMap")
    public void setParentMap(Map<String, List<String>> map) {
        this.parentMap = map;
    }

    @JsonProperty("parents")
    public void setParents(List<String> list) {
        this.parents = list;
    }

    @JsonProperty("properties")
    public void setProperties(Map<String, Map<String, String>> map) {
        this.properties = map;
    }

    @JsonProperty("protectedFolder")
    public void setProtectedFolder(Boolean bool) {
        this.protectedFolder = bool;
    }

    @JsonProperty("restricted")
    public void setRestricted(Boolean bool) {
        this.restricted = bool;
    }

    @JsonProperty("secondaryProperties")
    public void setSecondaryProperties(Map<String, Map<String, String>> map) {
        this.secondaryProperties = map;
    }

    @JsonProperty("settings")
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    @JsonProperty("sourceId")
    public void setSourceId(String str) {
        this.sourceId = str;
    }

    @JsonProperty("status")
    public void setStatus(String str) {
        this.status = str;
    }

    @JsonProperty("subKindProperties")
    public void setSubKindProperties(SubKindProperties subKindProperties) {
        this.subKindProperties = subKindProperties;
    }

    @JsonProperty("subKinds")
    public void setSubKinds(List<String> list) {
        this.subKinds = list;
    }

    @JsonProperty("symbolicNodeHeroId")
    public void setSymbolicNodeHeroId(String str) {
        this.symbolicNodeHeroId = str;
    }

    @JsonProperty(PhotoSearchCategory.THINGS)
    public void setThings(List<String> list) {
        this.things = list;
    }

    @JsonProperty("transforms")
    public void setTransforms(List<String> list) {
        this.transforms = list;
    }

    @JsonProperty("xAccntParentMap")
    public void setXAccntParentMap(Map<String, List<String>> map) {
        this.xAccntParentMap = map;
    }

    @JsonProperty("xAccntParents")
    public void setXAccntParents(List<String> list) {
        this.xAccntParents = list;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EditableNodeInfo(id=");
        outline107.append(getId());
        outline107.append(", familyId=");
        outline107.append(getFamilyId());
        outline107.append(", name=");
        outline107.append(getName());
        outline107.append(", description=");
        outline107.append(getDescription());
        outline107.append(", labels=");
        outline107.append(getLabels());
        outline107.append(", kind=");
        outline107.append(getKind());
        outline107.append(", subKinds=");
        outline107.append(getSubKinds());
        outline107.append(", parents=");
        outline107.append(getParents());
        outline107.append(", xAccntParents=");
        outline107.append(getXAccntParents());
        outline107.append(", parentMap=");
        outline107.append(getParentMap());
        outline107.append(", xAccntParentMap=");
        outline107.append(getXAccntParentMap());
        outline107.append(", restricted=");
        outline107.append(getRestricted());
        outline107.append(", protectedFolder=");
        outline107.append(getProtectedFolder());
        outline107.append(", properties=");
        outline107.append(getProperties());
        outline107.append(", secondaryProperties=");
        outline107.append(getSecondaryProperties());
        outline107.append(", contentProperties=");
        outline107.append(getContentProperties());
        outline107.append(", status=");
        outline107.append(getStatus());
        outline107.append(", settings=");
        outline107.append(getSettings());
        outline107.append(", transforms=");
        outline107.append(getTransforms());
        outline107.append(", collectionProperties=");
        outline107.append(getCollectionProperties());
        outline107.append(", sourceId=");
        outline107.append(getSourceId());
        outline107.append(", keywords=");
        outline107.append(getKeywords());
        outline107.append(", clientProperties=");
        outline107.append(getClientProperties());
        outline107.append(", things=");
        outline107.append(getThings());
        outline107.append(", accessRuleIds=");
        outline107.append(getAccessRuleIds());
        outline107.append(", childAssetTypeInfo=");
        outline107.append(getChildAssetTypeInfo());
        outline107.append(", assetProperties=");
        outline107.append(getAssetProperties());
        outline107.append(", groupPermissions=");
        outline107.append(getGroupPermissions());
        outline107.append(", subKindProperties=");
        outline107.append(getSubKindProperties());
        outline107.append(", symbolicNodeHeroId=");
        outline107.append(getSymbolicNodeHeroId());
        outline107.append(")");
        return outline107.toString();
    }
}
