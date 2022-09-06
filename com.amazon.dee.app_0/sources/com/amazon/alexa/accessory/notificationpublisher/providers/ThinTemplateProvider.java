package com.amazon.alexa.accessory.notificationpublisher.providers;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class ThinTemplateProvider extends BaseTemplateProvider {
    private static final String TAG = "ThinTemplateProvider";
    private static ThinTemplateProvider instance;

    ThinTemplateProvider(@NonNull Context context) {
        super(context);
        create();
    }

    public static synchronized ThinTemplateProvider getInstance() {
        ThinTemplateProvider thinTemplateProvider;
        synchronized (ThinTemplateProvider.class) {
            if (instance != null) {
                thinTemplateProvider = instance;
            } else {
                Log.e(TAG, "getInstance called before calling init(). Throw an exception.");
                throw new IllegalStateException("ThinTemplateProvider is not initialized, init() must be called before calling this method.");
            }
        }
        return thinTemplateProvider;
    }

    public static void init(@NonNull Context context) {
        if (instance == null) {
            Log.d(TAG, "First time init");
            if (context != null) {
                instance = new ThinTemplateProvider(context);
            } else {
                Log.e(TAG, "Context is null, throw exception");
                throw new IllegalArgumentException("Cannot initialize TemplateProvider with a null Context.");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider
    public void create() {
        super.create();
        BaseTemplateProvider.transformerTemplate = BaseTemplateProvider.otgTransformerTemplateHolder;
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider
    public JSONObject getCustomGenericParserTemplateByAppId(String str) {
        if (BaseTemplateProvider.parserTemplate == null) {
            Log.i(TAG, "getCustomGenericParserTemplateByAppId - parserTemplate is null, re-init all templates");
            create();
        }
        return getTemplateJSONObjectByKey(BaseTemplateProvider.parserTemplate.optJSONObject(BaseTemplateProvider.CUSTOM_GENERIC_KEY), str);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider
    public JSONObject getCustomGenericTransformerTemplateByAppId(String str) {
        if (BaseTemplateProvider.transformerTemplate == null) {
            Log.i(TAG, "getCustomGenericTransformerTemplateByAppId - customGenericParserTemplate is null, re-init all templates");
            create();
        }
        return getTemplateJSONObjectByKey(BaseTemplateProvider.transformerTemplate.optJSONObject(BaseTemplateProvider.CUSTOM_GENERIC_KEY), str);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider
    public JSONObject getParserTemplateByAppId(String str) {
        if (BaseTemplateProvider.parserTemplate == null) {
            Log.i(TAG, "getParserTemplateByAppId - parserTemplate is null, re-init all templates");
            create();
        }
        return getTemplateJSONObjectByKey(BaseTemplateProvider.parserTemplate.optJSONObject(ProcessNotificationModule.NotificationType.COMMS.name()), str);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider
    public JSONObject getTransformerTemplateByType(ProcessNotificationModule.NotificationType notificationType) {
        if (BaseTemplateProvider.transformerTemplate == null) {
            Log.i(TAG, "getTransformerTemplateByType - transformerTemplate is null, re-init all templates");
            create();
        }
        return getTemplateJSONObjectByKey(BaseTemplateProvider.transformerTemplate, notificationType.name());
    }
}
