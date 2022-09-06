package com.amazon.alexa.featureservice.repo.mapper;

import com.amazon.alexa.featureservice.remote.model.FeatureMetaData;
import com.amazon.alexa.featureservice.repo.model.Feature;
/* loaded from: classes7.dex */
public class FeatureDataMapper implements RemoteMapper<Feature, FeatureMetaData>, DatabaseMapper<Feature, com.amazon.alexa.featureservice.database.entity.Feature> {
    @Override // com.amazon.alexa.featureservice.repo.mapper.DatabaseMapper
    public Feature fromDatabaseModel(com.amazon.alexa.featureservice.database.entity.Feature feature) {
        Feature feature2 = new Feature();
        feature2.setFeatureName(feature.getFeatureName());
        feature2.setTreatment(feature.getTreatment());
        feature2.setAllocationId(feature.getAllocationId());
        feature2.setTriggerOnUse(feature.isShouldRecordTrigger());
        return feature2;
    }

    @Override // com.amazon.alexa.featureservice.repo.mapper.RemoteMapper
    public Feature fromRemoteModel(FeatureMetaData featureMetaData) {
        Feature feature = new Feature();
        feature.setAllocationId(featureMetaData.getAllocationId() == null ? "" : featureMetaData.getAllocationId());
        feature.setTriggerOnUse(featureMetaData.isTriggerOnUse());
        feature.setTreatment(featureMetaData.getTreatment());
        return feature;
    }

    @Override // com.amazon.alexa.featureservice.repo.mapper.DatabaseMapper
    public com.amazon.alexa.featureservice.database.entity.Feature toDatabaseModel(Feature feature) {
        com.amazon.alexa.featureservice.database.entity.Feature feature2 = new com.amazon.alexa.featureservice.database.entity.Feature();
        feature2.setFeatureName(feature.getFeatureName());
        feature2.setAllocationId(feature.getAllocationId() == null ? "" : feature.getAllocationId());
        feature2.setShouldRecordTrigger(feature.isTriggerOnUse());
        feature2.setTreatment(feature.getTreatment());
        return feature2;
    }

    @Override // com.amazon.alexa.featureservice.repo.mapper.RemoteMapper
    public FeatureMetaData toRemoteModel(Feature feature) {
        FeatureMetaData featureMetaData = new FeatureMetaData();
        featureMetaData.setAllocationId(feature.getAllocationId());
        featureMetaData.setTreatment(feature.getTreatment());
        featureMetaData.setTriggerOnUse(feature.isTriggerOnUse());
        return featureMetaData;
    }
}
