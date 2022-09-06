package com.amazon.alexa.featureservice.dependencies;

import android.content.Context;
import com.amazon.alexa.featureservice.database.dao.FeatureFlagDao;
import com.amazon.alexa.featureservice.database.dao.RxFeatureDao;
import com.amazon.alexa.featureservice.database.roomdb.FeatureAppDatabase;
import com.amazon.alexa.featureservice.dependencies.annotation.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
@Module
/* loaded from: classes7.dex */
public class DatabaseModule {
    @Provides
    @Singleton
    public FeatureAppDatabase provideRoomDatabase(@ApplicationContext Context context) {
        return FeatureAppDatabase.getInstance(context);
    }

    @Provides
    @Singleton
    public FeatureFlagDao providesFeatureDao(FeatureAppDatabase featureAppDatabase) {
        return featureAppDatabase.featureDao();
    }

    @Provides
    @Singleton
    public RxFeatureDao providesRxFeatureDao(FeatureFlagDao featureFlagDao) {
        return new RxFeatureDao(featureFlagDao);
    }
}
