package com.amazon.alexa.featureservice.database.dao;

import com.amazon.alexa.featureservice.database.entity.Feature;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Inject;
/* loaded from: classes7.dex */
public class RxFeatureDao {
    private FeatureFlagDao featureDao;

    @Inject
    public RxFeatureDao(FeatureFlagDao featureFlagDao) {
        this.featureDao = featureFlagDao;
    }

    public Single<Integer> delete(final Feature feature) {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.featureservice.database.dao.-$$Lambda$RxFeatureDao$bQhftJyIZhoNvmOKOoohdJJu-Wk
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return RxFeatureDao.this.lambda$delete$3$RxFeatureDao(feature);
            }
        });
    }

    public Single<Integer> deleteAll() {
        return Single.fromCallable(new Callable() { // from class: com.amazon.alexa.featureservice.database.dao.-$$Lambda$RxFeatureDao$Z5_dFbjjFMxnZt5XXtvsig4C9g0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return RxFeatureDao.this.lambda$deleteAll$4$RxFeatureDao();
            }
        });
    }

    public Flowable<Feature> get(final String str) {
        return Flowable.fromCallable(new Callable() { // from class: com.amazon.alexa.featureservice.database.dao.-$$Lambda$RxFeatureDao$vY8rzVZG-NKSm79Gi6qpsQYJZ8c
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return RxFeatureDao.this.lambda$get$2$RxFeatureDao(str);
            }
        });
    }

    public Flowable<List<Feature>> getAll() {
        return Flowable.fromCallable(new Callable() { // from class: com.amazon.alexa.featureservice.database.dao.-$$Lambda$RxFeatureDao$f5EATCYhAeqVu0H2SYo42H-pM2c
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return RxFeatureDao.this.lambda$getAll$1$RxFeatureDao();
            }
        });
    }

    public Maybe<Feature> getAsMaybe(final String str) {
        return Maybe.fromCallable(new Callable() { // from class: com.amazon.alexa.featureservice.database.dao.-$$Lambda$RxFeatureDao$MB6rKOo6hyQTKn-CxH9IJTTTKi0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return RxFeatureDao.this.lambda$getAsMaybe$5$RxFeatureDao(str);
            }
        });
    }

    public Completable insert(final Feature feature) {
        return Completable.fromCallable(new Callable() { // from class: com.amazon.alexa.featureservice.database.dao.-$$Lambda$RxFeatureDao$-f-7RdTW-HXW30q7zjZYgB28TLE
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return RxFeatureDao.this.lambda$insert$0$RxFeatureDao(feature);
            }
        });
    }

    public /* synthetic */ Integer lambda$delete$3$RxFeatureDao(Feature feature) throws Exception {
        return Integer.valueOf(this.featureDao.delete(feature));
    }

    public /* synthetic */ Integer lambda$deleteAll$4$RxFeatureDao() throws Exception {
        return Integer.valueOf(this.featureDao.deleteAll());
    }

    public /* synthetic */ Feature lambda$get$2$RxFeatureDao(String str) throws Exception {
        return this.featureDao.get(str);
    }

    public /* synthetic */ List lambda$getAll$1$RxFeatureDao() throws Exception {
        return this.featureDao.getAll();
    }

    public /* synthetic */ Feature lambda$getAsMaybe$5$RxFeatureDao(String str) throws Exception {
        return this.featureDao.get(str);
    }

    public /* synthetic */ Long lambda$insert$0$RxFeatureDao(Feature feature) throws Exception {
        return Long.valueOf(this.featureDao.insert(feature));
    }
}
