package com.amazon.dee.app.dependencies;

import android.content.Context;
import com.amazon.dee.app.services.datastore.DataStoreHelper;
import com.amazon.dee.app.services.datastore.DataStoreService;
import com.amazon.dee.app.services.datastore.DefaultDataStoreService;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes12.dex */
public class DataStoreModule {
    @Provides
    @ApplicationScope
    public DataStoreHelper provideDataStoreHelper(Context context) {
        return new DataStoreHelper(context);
    }

    @Provides
    @ApplicationScope
    public DataStoreService provideDataStoreService(DataStoreHelper dataStoreHelper) {
        return new DefaultDataStoreService(dataStoreHelper.getWritableDatabase());
    }

    @Provides
    @ApplicationScope
    public com.amazon.alexa.protocols.datastore.DataStoreService provideProtocolsDataStoreService(DataStoreHelper dataStoreHelper) {
        return new DefaultDataStoreService(dataStoreHelper.getWritableDatabase());
    }
}
