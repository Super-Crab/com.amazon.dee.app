package com.amazon.alexa.featureservice.repo.mapper;

import com.amazon.alexa.featureservice.remote.model.FeatureMetaData;
import com.amazon.alexa.featureservice.repo.model.Feature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class FeatureCollectionMapper implements RemoteMapper<List<Feature>, Map<String, FeatureMetaData>>, DatabaseMapper<List<Feature>, List<com.amazon.alexa.featureservice.database.entity.Feature>> {
    FeatureDataMapper featureDataMapper;

    @Inject
    public FeatureCollectionMapper(FeatureDataMapper featureDataMapper) {
        this.featureDataMapper = featureDataMapper;
    }

    public FeatureDataMapper getFeatureDataMapper() {
        return this.featureDataMapper;
    }

    @Override // com.amazon.alexa.featureservice.repo.mapper.DatabaseMapper
    public List<Feature> fromDatabaseModel(List<com.amazon.alexa.featureservice.database.entity.Feature> list) {
        ArrayList arrayList = new ArrayList();
        for (com.amazon.alexa.featureservice.database.entity.Feature feature : list) {
            arrayList.add(this.featureDataMapper.fromDatabaseModel(feature));
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.featureservice.repo.mapper.RemoteMapper
    public List<Feature> fromRemoteModel(Map<String, FeatureMetaData> map) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, FeatureMetaData> entry : map.entrySet()) {
            Feature fromRemoteModel = this.featureDataMapper.fromRemoteModel(entry.getValue());
            fromRemoteModel.setFeatureName(entry.getKey());
            arrayList.add(fromRemoteModel);
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.featureservice.repo.mapper.DatabaseMapper
    public List<com.amazon.alexa.featureservice.database.entity.Feature> toDatabaseModel(List<Feature> list) {
        ArrayList arrayList = new ArrayList();
        for (Feature feature : list) {
            arrayList.add(this.featureDataMapper.toDatabaseModel(feature));
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.featureservice.repo.mapper.RemoteMapper
    public Map<String, FeatureMetaData> toRemoteModel(List<Feature> list) {
        HashMap hashMap = new HashMap();
        for (Feature feature : list) {
            hashMap.put(feature.getFeatureName(), this.featureDataMapper.toRemoteModel(feature));
        }
        return hashMap;
    }
}
