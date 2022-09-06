package com.amazon.alexa.featureservice.storage;

import android.content.Context;
import com.amazon.alexa.featureservice.dao.FeatureDao;
import com.amazon.alexa.featureservice.database.entity.Feature;
import com.amazon.alexa.featureservice.database.roomdb.FeatureAppDatabase;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
/* loaded from: classes7.dex */
public class FeatureServiceStorage {
    private final ExecutorService executorService;
    private final FeatureDao featureDao;

    public FeatureServiceStorage(Context context, ExecutorService executorService) {
        this.featureDao = FeatureAppDatabase.getInstance(context).userDao();
        this.executorService = executorService;
    }

    public Future clearAll() {
        return this.executorService.submit(new Runnable() { // from class: com.amazon.alexa.featureservice.storage.-$$Lambda$FeatureServiceStorage$nKDQHV-RTdeKuu5hJuYYg0dSUXk
            @Override // java.lang.Runnable
            public final void run() {
                FeatureServiceStorage.this.lambda$clearAll$2$FeatureServiceStorage();
            }
        });
    }

    public /* synthetic */ void lambda$clearAll$2$FeatureServiceStorage() {
        this.featureDao.deleteAll();
    }

    public /* synthetic */ Feature lambda$load$4$FeatureServiceStorage(String str) throws Exception {
        return this.featureDao.get(str);
    }

    public /* synthetic */ List lambda$loadAll$3$FeatureServiceStorage() throws Exception {
        return this.featureDao.getAll();
    }

    public /* synthetic */ void lambda$remove$1$FeatureServiceStorage(Feature feature) {
        this.featureDao.delete(feature);
    }

    public /* synthetic */ void lambda$save$0$FeatureServiceStorage(List list) {
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            this.featureDao.insert((Feature) it2.next());
        }
    }

    public Future<Feature> load(final String str) {
        return this.executorService.submit(new Callable() { // from class: com.amazon.alexa.featureservice.storage.-$$Lambda$FeatureServiceStorage$VhRyJ7qy7HJkGcK3dK7VQzVX6RI
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return FeatureServiceStorage.this.lambda$load$4$FeatureServiceStorage(str);
            }
        });
    }

    public Future<List<Feature>> loadAll() {
        return this.executorService.submit(new Callable() { // from class: com.amazon.alexa.featureservice.storage.-$$Lambda$FeatureServiceStorage$-7B_0q9cFxIZ7gmleOd9wD32wQU
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return FeatureServiceStorage.this.lambda$loadAll$3$FeatureServiceStorage();
            }
        });
    }

    public Future remove(final Feature feature) {
        return this.executorService.submit(new Runnable() { // from class: com.amazon.alexa.featureservice.storage.-$$Lambda$FeatureServiceStorage$KItsNmYjwBNiHbAxbq_9vW-BWjQ
            @Override // java.lang.Runnable
            public final void run() {
                FeatureServiceStorage.this.lambda$remove$1$FeatureServiceStorage(feature);
            }
        });
    }

    public Future save(final List<Feature> list) {
        return this.executorService.submit(new Runnable() { // from class: com.amazon.alexa.featureservice.storage.-$$Lambda$FeatureServiceStorage$q19VtaRS6USsIb5_QI4zbH3oNpo
            @Override // java.lang.Runnable
            public final void run() {
                FeatureServiceStorage.this.lambda$save$0$FeatureServiceStorage(list);
            }
        });
    }
}
