package com.amazon.alexa.featureservice.repo.provider;

import com.amazon.alexa.featureservice.database.dao.RxFeatureDao;
import com.amazon.alexa.featureservice.remote.client.FeatureServiceClient;
import com.amazon.alexa.featureservice.remote.model.FeatureServiceResponse;
import com.amazon.alexa.featureservice.repo.mapper.FeatureCollectionMapper;
import com.amazon.alexa.featureservice.repo.model.Feature;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class FeatureDataRepoImpl implements FeatureDataRepo {
    private FeatureCollectionMapper featureCollectionMapper;
    private RxFeatureDao featureDao;
    private FeatureServiceClient featureServiceClient;

    @Inject
    public FeatureDataRepoImpl(FeatureServiceClient featureServiceClient, RxFeatureDao rxFeatureDao, FeatureCollectionMapper featureCollectionMapper) {
        this.featureServiceClient = featureServiceClient;
        this.featureDao = rxFeatureDao;
        this.featureCollectionMapper = featureCollectionMapper;
    }

    @Override // com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo
    public Flowable<List<Feature>> allFeatureUpdates() {
        return this.featureDao.getAll().map(new Function() { // from class: com.amazon.alexa.featureservice.repo.provider.-$$Lambda$FeatureDataRepoImpl$wcKFHzZbiK4TE9HaUBRS1UDe3kU
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FeatureDataRepoImpl.this.lambda$allFeatureUpdates$3$FeatureDataRepoImpl((List) obj);
            }
        });
    }

    @Override // com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo
    public Single<Integer> deleteAllFeatures() {
        return this.featureDao.deleteAll();
    }

    @Override // com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo
    public Single<Integer> deleteFeature(Feature feature) {
        return this.featureDao.delete(this.featureCollectionMapper.getFeatureDataMapper().toDatabaseModel(feature));
    }

    @Override // com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo
    public Completable insertFeature(Feature feature) {
        return this.featureDao.insert(this.featureCollectionMapper.getFeatureDataMapper().toDatabaseModel(feature));
    }

    public /* synthetic */ List lambda$allFeatureUpdates$3$FeatureDataRepoImpl(List list) throws Throwable {
        return this.featureCollectionMapper.fromDatabaseModel((List<com.amazon.alexa.featureservice.database.entity.Feature>) list);
    }

    public /* synthetic */ List lambda$refreshFeatures$0$FeatureDataRepoImpl(FeatureServiceResponse featureServiceResponse) throws Throwable {
        return this.featureCollectionMapper.fromRemoteModel(featureServiceResponse.getFeatureMetaData());
    }

    public /* synthetic */ void lambda$refreshFeatures$1$FeatureDataRepoImpl(List list) throws Throwable {
        for (com.amazon.alexa.featureservice.database.entity.Feature feature : this.featureCollectionMapper.toDatabaseModel((List<Feature>) list)) {
            this.featureDao.insert(feature);
        }
    }

    public /* synthetic */ Feature lambda$singleFeatureUpdates$2$FeatureDataRepoImpl(com.amazon.alexa.featureservice.database.entity.Feature feature) throws Throwable {
        return this.featureCollectionMapper.getFeatureDataMapper().fromDatabaseModel(feature);
    }

    @Override // com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo
    public Single<List<Feature>> refreshFeatures(List<String> list) {
        return this.featureServiceClient.getFeatures(list).map(new Function() { // from class: com.amazon.alexa.featureservice.repo.provider.-$$Lambda$FeatureDataRepoImpl$AagDvd2QYI27Avy7CH689E9Ke_E
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FeatureDataRepoImpl.this.lambda$refreshFeatures$0$FeatureDataRepoImpl((FeatureServiceResponse) obj);
            }
        }).doOnSuccess(new Consumer() { // from class: com.amazon.alexa.featureservice.repo.provider.-$$Lambda$FeatureDataRepoImpl$MYq895c9n_5P0tIUcfCz83rxrP8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                FeatureDataRepoImpl.this.lambda$refreshFeatures$1$FeatureDataRepoImpl((List) obj);
            }
        });
    }

    @Override // com.amazon.alexa.featureservice.repo.provider.FeatureDataRepo
    public Single<Feature> singleFeatureUpdates(String str) {
        return this.featureDao.getAsMaybe(str).map(new Function() { // from class: com.amazon.alexa.featureservice.repo.provider.-$$Lambda$FeatureDataRepoImpl$R-mr4x9ciCqANlRDgpHA5KpF5lc
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return FeatureDataRepoImpl.this.lambda$singleFeatureUpdates$2$FeatureDataRepoImpl((com.amazon.alexa.featureservice.database.entity.Feature) obj);
            }
        }).toSingle();
    }
}
