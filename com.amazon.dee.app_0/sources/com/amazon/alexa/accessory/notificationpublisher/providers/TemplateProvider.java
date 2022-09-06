package com.amazon.alexa.accessory.notificationpublisher.providers;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.servicerequest.GetFocusFilterTemplatesFromS3RequestSender;
import com.amazon.alexa.accessory.notificationpublisher.storage.StorageWrapper;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class TemplateProvider extends BaseTemplateProvider {
    private static final String TAG = "TemplateProvider";
    private static TemplateProvider instance;
    private Context context;

    private TemplateProvider(@NonNull Context context) {
        super(context);
        this.context = context;
        create();
    }

    public static synchronized TemplateProvider getInstance() {
        TemplateProvider templateProvider;
        synchronized (TemplateProvider.class) {
            if (instance != null) {
                templateProvider = instance;
            } else {
                Log.e(TAG, "getInstance called before calling init(). Throw an exception.");
                throw new IllegalStateException("TemplateProvider is not initialized, init() must be called before calling this method.");
            }
        }
        return templateProvider;
    }

    private static JSONObject getTransformerTemplate() {
        return FeatureAccessChecker.hasOtgVipFilterAccess() ? BaseTemplateProvider.otgTransformerTemplateHolder : BaseTemplateProvider.transformerTemplateHolder;
    }

    public static synchronized void init(@NonNull Context context) {
        synchronized (TemplateProvider.class) {
            if (instance == null) {
                Log.d(TAG, "First time init");
                if (context != null) {
                    instance = new TemplateProvider(context);
                } else {
                    Log.e(TAG, "Context is null, throw exception");
                    throw new IllegalArgumentException("Cannot initialize TemplateProvider with a null Context.");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider
    public void create() {
        super.create();
        StorageWrapper storageWrapper = new StorageWrapper();
        String localStorageEtagKey = GetFocusFilterTemplatesFromS3RequestSender.getLocalStorageEtagKey(GetFocusFilterTemplatesFromS3RequestSender.TemplateType.PARSE.name());
        String localStorageEtagKey2 = GetFocusFilterTemplatesFromS3RequestSender.getLocalStorageEtagKey(GetFocusFilterTemplatesFromS3RequestSender.TemplateType.TRANSFORM.name());
        String localStorageEtagKey3 = GetFocusFilterTemplatesFromS3RequestSender.getLocalStorageEtagKey(GetFocusFilterTemplatesFromS3RequestSender.TemplateType.IGNORED.name());
        storageWrapper.removeLocal(localStorageEtagKey);
        storageWrapper.removeLocal(localStorageEtagKey2);
        storageWrapper.removeLocal(localStorageEtagKey3);
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
        if (getTransformerTemplate() == null) {
            Log.i(TAG, "getCustomGenericTransformerTemplateByAppId - customGenericParserTemplate is null, re-init all templates");
            create();
        }
        return getTemplateJSONObjectByKey(getTransformerTemplate().optJSONObject(BaseTemplateProvider.CUSTOM_GENERIC_KEY), str);
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
        if (getTransformerTemplate() == null) {
            Log.i(TAG, "getTransformerTemplateByType - transformerTemplate is null, re-init all templates");
            create();
        }
        return getTemplateJSONObjectByKey(getTransformerTemplate(), notificationType.name());
    }
}
