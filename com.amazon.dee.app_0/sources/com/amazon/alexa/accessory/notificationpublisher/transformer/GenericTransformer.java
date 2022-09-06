package com.amazon.alexa.accessory.notificationpublisher.transformer;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.notificationpublisher.FeatureToggleModule;
import com.amazon.alexa.accessory.notificationpublisher.ProcessNotificationModule;
import com.amazon.alexa.accessory.notificationpublisher.R;
import com.amazon.alexa.accessory.notificationpublisher.providers.AccessoryProvider;
import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.InputConfigGestureStringBuilder;
import com.amazon.alexa.accessory.notificationpublisher.utils.JSONHelpers;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.deecomms.common.network.okhttp.OkHttpClientWrapper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
import java.util.Locale;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class GenericTransformer extends BaseTransformer {
    private static final String TAG = "GenericTransformer";
    private static final String notificationAnnouncement = "%s. ";
    private String content;
    private Context context;

    public GenericTransformer(@NonNull Context context, @NonNull BaseTemplateProvider baseTemplateProvider, @NonNull JSONObject jSONObject) {
        super(baseTemplateProvider, jSONObject, ProcessNotificationModule.NotificationType.OTHER);
        String str = "";
        this.content = str;
        String str2 = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Construct GenericTransformer -- ");
        outline107.append(jSONObject.toString());
        Log.d(str2, outline107.toString());
        this.context = context;
        this.content = !jSONObject.isNull("content") ? jSONObject.optString("content") : str;
    }

    private String buildAnnouncement() {
        if (this.isInvitation) {
            if (FeatureAccessChecker.hasOtgVipFilterAccess()) {
                return buildInvitationAnnouncement();
            }
            String accessoryPositiveActionString = InputConfigGestureStringBuilder.getAccessoryPositiveActionString();
            String accessoryNegativeActionString = InputConfigGestureStringBuilder.getAccessoryNegativeActionString();
            String string = this.context.getString(R.string.notification_invitation_instruction);
            return String.format(Locale.US, this.context.getString(R.string.invitation_announcement), accessoryPositiveActionString, this.appName) + String.format(Locale.getDefault(), string, accessoryNegativeActionString);
        }
        String format = String.format(Locale.getDefault(), this.context.getString(R.string.remote_view_instruction), new Object[0]);
        if (Strings.isNullOrEmpty(this.content)) {
            return String.format(Locale.US, notificationAnnouncement, this.appName) + format;
        }
        return String.format(Locale.US, notificationAnnouncement, this.appName);
    }

    private String buildInvitationAnnouncement() {
        String accessoryPositiveActionString = InputConfigGestureStringBuilder.getAccessoryPositiveActionString();
        String accessoryNegativeActionString = InputConfigGestureStringBuilder.getAccessoryNegativeActionString();
        return String.format(Locale.US, this.context.getString(R.string.invitation_announcement), accessoryPositiveActionString, this.appName) + String.format(Locale.US, this.context.getString(R.string.notification_invitation_instruction), accessoryNegativeActionString);
    }

    @Override // com.amazon.alexa.accessory.notificationpublisher.transformer.BaseTransformer
    public JSONObject transform() {
        String format;
        JSONObject jSONObject = new JSONObject();
        try {
            Log.i(TAG, "transform - uuid: " + this.parsedNotification.optString("uuid"));
            if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureToggleModule.getInstance().hasConnectedEnabledDevices()) {
                this.parsedNotification.put("positiveGesture", InputConfigGestureStringBuilder.getAccessoryPositiveActionString());
                this.parsedNotification.put("negativeGesture", InputConfigGestureStringBuilder.getAccessoryNegativeActionString());
            }
            if (!Strings.isNullOrEmpty(this.appName)) {
                if (AccessoryProvider.getConnectedAccessoryList().size() != 0) {
                    jSONObject.put(OkHttpClientWrapper.ANNOUNCEMENT_CLIENT, buildAnnouncement());
                }
                String format2 = String.format(Locale.getDefault(), this.context.getString(R.string.remote_view_instruction), new Object[0]);
                if (Strings.isNullOrEmpty(this.content)) {
                    format = String.format(Locale.US, notificationAnnouncement, this.appName) + format2;
                } else {
                    format = String.format(Locale.US, this.context.getString(R.string.notification_content), this.appName, this.content);
                }
                jSONObject.put(NotificationConstants.DETAILED_CONTENT, format);
                JSONHelpers.mergeJSONObjects(this.parsedNotification, jSONObject, BaseTransformer.FIELDS_TO_KEEP);
                try {
                    jSONObject.put(NotificationConstants.DETAILED_CONTENT, replaceDetailedContent(format).getContent());
                } catch (Exception e) {
                    Log.e(TAG, "transform Error.", e);
                }
                return jSONObject;
            }
            Log.w(TAG, "transform - missing app name, discard. uuid: " + this.parsedNotification.optString("uuid"));
            return null;
        } catch (Exception e2) {
            Log.w(TAG, "Exception.", e2);
            return null;
        }
    }
}
