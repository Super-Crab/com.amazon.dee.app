package com.amazon.alexa.accessory.notificationpublisher.transformer;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.R;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.transformer.BaseTransformer;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.InputConfigGestureStringBuilder;
import com.amazon.alexa.accessory.notificationpublisher.utils.JSONHelpers;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.google.common.base.Strings;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class TypeTransformer extends BaseTransformer {
    public static final String CUSTOM_GENERIC_SUB_TYPE = "CustomGeneric";
    private static final String TAG = "TypeTransformer";
    private Context context;
    private JSONArray template;

    public TypeTransformer(@NonNull Context context, @NonNull JSONObject jSONObject, @NonNull ProcessNotificationModule.NotificationType notificationType, @NonNull BaseTemplateProvider baseTemplateProvider) {
        super(baseTemplateProvider, jSONObject, notificationType);
        JSONObject transformerTemplateByType;
        String str = TAG;
        Log.d(str, "Construct TypeTransformer -- " + notificationType + " " + jSONObject.toString());
        this.context = context;
        if (CUSTOM_GENERIC_SUB_TYPE.equals(jSONObject.optString("subType"))) {
            transformerTemplateByType = baseTemplateProvider.getCustomGenericTransformerTemplateByAppId(jSONObject.optString("packageIdentifier"));
        } else {
            transformerTemplateByType = baseTemplateProvider.getTransformerTemplateByType(notificationType);
        }
        this.template = transformerTemplateByType.optJSONArray(getKeyForTransformer());
    }

    private String getNotificationInvitationInstruction() {
        if (AccessoryProvider.getConnectedAccessoryList().size() > 0) {
            return String.format(Locale.getDefault(), this.context.getString(R.string.notification_invitation_instruction), InputConfigGestureStringBuilder.getAccessoryNegativeActionString());
        }
        return "";
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.transformer.BaseTransformer
    public JSONObject transform() {
        try {
            Log.i(TAG, "transform - uuid: " + this.parsedNotification.optString("uuid"));
            boolean z = false;
            boolean z2 = AccessoryProvider.getConnectedAccessoryList().size() > 0;
            if (z2 && FeatureAccessChecker.hasOtgVipFilterAccess()) {
                this.parsedNotification.put("positiveGesture", InputConfigGestureStringBuilder.getAccessoryPositiveActionString());
                this.parsedNotification.put("negativeGesture", InputConfigGestureStringBuilder.getAccessoryNegativeActionString());
            }
            if (this.template == null) {
                Log.w(TAG, "transform -- No template found, discard");
                return null;
            }
            if (BaseTransformer.APP_NAME_TTS_MAP.containsKey(this.appName)) {
                this.parsedNotification.put("appNameForTts", BaseTransformer.APP_NAME_TTS_MAP.mo7740get(this.appName));
            }
            JSONObject transformByTemplate = transformByTemplate(this.template);
            if (transformByTemplate != null && !transformByTemplate.isNull(OkHttpClientWrapper.ANNOUNCEMENT_CLIENT)) {
                JSONHelpers.mergeJSONObjects(this.parsedNotification, transformByTemplate, BaseTransformer.FIELDS_TO_KEEP);
                String optString = transformByTemplate.optString(OkHttpClientWrapper.ANNOUNCEMENT_CLIENT);
                boolean z3 = z2 && FeatureAccessChecker.hasRemoveRepeatingContactSaysAccess() && !Strings.isNullOrEmpty(transformByTemplate.optString(NotificationConstants.CONTENT_WITHOUT_PREFIX)) && !Strings.isNullOrEmpty(transformByTemplate.optString(NotificationConstants.CONTENT_PREFIX));
                Log.i(TAG, "allowDetailedContentWithoutPrefix: " + z3);
                String format = String.format(Locale.getDefault(), this.context.getString(R.string.remote_view_instruction), new Object[0]);
                if (transformByTemplate.isNull(NotificationConstants.DETAILED_CONTENT)) {
                    transformByTemplate.put(OkHttpClientWrapper.ANNOUNCEMENT_CLIENT, optString + (this.isInvitation ? getNotificationInvitationInstruction() : format));
                    transformByTemplate.put(NotificationConstants.DETAILED_CONTENT, optString + format);
                    z3 = false;
                } else {
                    transformByTemplate.put(OkHttpClientWrapper.ANNOUNCEMENT_CLIENT, optString + (this.isInvitation ? getNotificationInvitationInstruction() : ""));
                }
                if (this.type.equals(ProcessNotificationModule.NotificationType.COMMS)) {
                    try {
                        BaseTransformer.ReplacedContent replaceDetailedContent = replaceDetailedContent(transformByTemplate.getString(NotificationConstants.DETAILED_CONTENT));
                        transformByTemplate.put(NotificationConstants.DETAILED_CONTENT, replaceDetailedContent.getContent());
                        if (replaceDetailedContent.getTemplateId() == 3) {
                            z = true;
                        }
                        transformByTemplate.put(NotificationConstants.PREFIX_REMOVED, z);
                        if (!replaceDetailedContent.isValidTemplate() && z3) {
                            transformByTemplate.put(NotificationConstants.DETAILED_CONTENT, transformByTemplate.optString(NotificationConstants.CONTENT_WITHOUT_PREFIX));
                            transformByTemplate.put(NotificationConstants.IS_DETAILED_CONTENT_WITHOUT_PREFIX, true);
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "transform Error.", e);
                    }
                }
                return transformByTemplate;
            }
            Log.w(TAG, "transform -- Invalid transformation, discard.uuid: " + transformByTemplate.optString("uuid"));
            return null;
        } catch (Exception e2) {
            Log.w(TAG, "transform -- Exception.", e2);
            return null;
        }
    }
}
