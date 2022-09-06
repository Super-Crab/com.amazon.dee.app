package com.amazon.alexa.featureservice.repo.provider;

import com.amazon.alexa.featureservice.repo.model.Feature;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import java.util.List;
/* loaded from: classes7.dex */
public interface FeatureDataRepo {
    Flowable<List<Feature>> allFeatureUpdates();

    Single<Integer> deleteAllFeatures();

    Single<Integer> deleteFeature(Feature feature);

    Completable insertFeature(Feature feature);

    Single<List<Feature>> refreshFeatures(List<String> list);

    Single<Feature> singleFeatureUpdates(String str);
}
