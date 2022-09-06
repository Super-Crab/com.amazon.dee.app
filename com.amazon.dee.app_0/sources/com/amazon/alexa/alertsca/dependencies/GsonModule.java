package com.amazon.alexa.alertsca.dependencies;

import com.amazon.alexa.alertsca.AlertRecord;
import com.amazon.alexa.alertsca.AlertRecordAdapter;
import com.amazon.alexa.alertsca.AlertsToken;
import com.amazon.alexa.alertsca.utils.DateAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.Date;
import javax.inject.Singleton;
@Module
/* loaded from: classes6.dex */
public class GsonModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Provides
    @Singleton
    public static Gson providesGson() {
        return new GsonBuilder().registerTypeHierarchyAdapter(AlertsToken.class, AlertsToken.getTypeAdapter()).registerTypeAdapter(Date.class, new DateAdapter()).registerTypeHierarchyAdapter(AlertRecord.class, new AlertRecordAdapter()).registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create();
    }
}
