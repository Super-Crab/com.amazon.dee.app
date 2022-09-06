package com.amazon.clouddrive.model;

import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public interface IEditableNode {
    CollectionProperties getCollectionProperties();

    ContentProperties getContentProperties();

    String getDescription();

    String getId();

    String getKind();

    List<String> getLabels();

    String getName();

    Map<String, List<String>> getParentMap();

    List<String> getParents();

    Map<String, Map<String, String>> getProperties();

    Settings getSettings();

    String getStatus();

    Map<String, Map<String, String>> getSubKindProperties();

    List<String> getSubKinds();

    String getSymbolicNodeHeroId();

    List<String> getTransforms();

    void setCollectionProperties(CollectionProperties collectionProperties);

    void setContentProperties(ContentProperties contentProperties);

    void setDescription(String str);

    void setId(String str);

    void setKind(String str);

    void setLabels(List<String> list);

    void setName(String str);

    void setParentMap(Map<String, List<String>> map);

    void setParents(List<String> list);

    void setProperties(Map<String, Map<String, String>> map);

    void setSettings(Settings settings);

    void setStatus(String str);

    void setSubKindProperties(Map<String, Map<String, String>> map);

    void setSubKinds(List<String> list);

    void setSymbolicNodeHeroId(String str);

    void setTransforms(List<String> list);
}
