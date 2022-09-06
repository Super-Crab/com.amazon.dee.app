package com.amazon.alexa.externalnotifications.capability.dependencies;

import com.amazon.alexa.externalnotifications.capability.models.NotificationId;
import com.amazon.alexa.externalnotifications.capability.utils.DateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.Date;
import javax.inject.Singleton;
@Module
/* loaded from: classes7.dex */
public class GsonModule {
    @Provides
    @Singleton
    public Gson providesGson() {
        return new GsonBuilder().registerTypeHierarchyAdapter(NotificationId.class, NotificationId.getTypeAdapter()).registerTypeAdapter(Date.class, new DateAdapter()).registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create();
    }
}
