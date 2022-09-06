package com.amazon.alexa.mobilytics.event.serializer.handlers;

import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.mobilytics.BuildConfig;
import com.amazon.alexa.mobilytics.configuration.ApplicationConfiguration;
import com.amazon.alexa.mobilytics.event.EventType;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.identity.auth.accounts.CentralAccountManagerCommunication;
import com.google.common.base.Preconditions;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.JSONObject;
@Singleton
/* loaded from: classes9.dex */
public class ApplicationDataHandler implements DataHandler {
    private static final String KEY = "application";
    private static final String TAG = Log.tag(ApplicationDataHandler.class);
    private final ApplicationConfiguration applicationConfiguration;

    @Inject
    public ApplicationDataHandler(@NonNull ApplicationConfiguration applicationConfiguration) {
        this.applicationConfiguration = (ApplicationConfiguration) Preconditions.checkNotNull(applicationConfiguration);
    }

    @Override // com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler
    @Nullable
    public Pair<String, JSONObject> process(@NonNull MobilyticsEvent mobilyticsEvent) throws JSONException {
        JSONObject put = new JSONObject().put(ModelTransformer.KEY_FIRMWARE_VERSION_NAME, this.applicationConfiguration.versionName()).put("versionCode", this.applicationConfiguration.versionCode());
        if (mobilyticsEvent.getEventType().equals(EventType.OPERATIONAL)) {
            put.put(CentralAccountManagerCommunication.GetAccountAction.KEY_PACKAGE_NAME, this.applicationConfiguration.packageName()).put("title", this.applicationConfiguration.title()).put(SettingsStorageModule.FILTER_SETTINGS_APP_ID_KEY, this.applicationConfiguration.id()).put("sdk", new JSONObject().put("name", "AlexaMobilyticsAndroid").put("version", BuildConfig.VERSION_NAME));
        }
        return Pair.create("application", put);
    }
}
