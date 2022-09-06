package com.amazon.alexa.mode.util;

import android.util.Log;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.eventbus.subscriber.SimpleMultiFilterSubscriber;
import com.amazon.alexa.mode.Constants;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class DriveModePreferences {
    private static final String TAG = "DriveModePreferences";
    SimpleMultiFilterSubscriber driveModeSettingsRequestSubscriber;
    SimpleMultiFilterSubscriber driveModeSettingsUpdateSubscriber;
    private EventBus eventBus;
    private PrefsDialogHelper prefsDialogHelper;

    public DriveModePreferences(EventBus eventBus, PrefsDialogHelper prefsDialogHelper) {
        this.eventBus = eventBus;
        this.prefsDialogHelper = prefsDialogHelper;
        subscribeToDriveModeSettingsRequest();
        subscribeToDriveModeSettingsUpdate();
    }

    private void postDriveModeSettingsResponse(String str, int i) {
        try {
            Message build = new Message.Builder().setEventType(Constants.DRIVE_MODE_USER_SETTINGS_RESPONSE_EVENT).setPayload(new JSONObject().put(str, i).toString()).build();
            if (this.eventBus == null) {
                return;
            }
            this.eventBus.publish(build);
        } catch (JSONException e) {
            Log.e(TAG, "Exception while publishing user settings", e);
        }
    }

    private void subscribeToDriveModeSettingsRequest() {
        if (this.eventBus != null) {
            this.driveModeSettingsRequestSubscriber = new SimpleMultiFilterSubscriber();
            this.driveModeSettingsRequestSubscriber.subscribe(new EventTypeMessageFilter(Constants.DRIVE_MODE_USER_SETTINGS_REQUEST_EVENT), new MessageHandler() { // from class: com.amazon.alexa.mode.util.-$$Lambda$DriveModePreferences$52qie35wrE9J6y6Zn6iRd5xnrMk
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    DriveModePreferences.this.lambda$subscribeToDriveModeSettingsRequest$0$DriveModePreferences(message);
                }
            });
            this.eventBus.subscribe(this.driveModeSettingsRequestSubscriber);
        }
    }

    private void subscribeToDriveModeSettingsUpdate() {
        if (this.eventBus != null) {
            this.driveModeSettingsUpdateSubscriber = new SimpleMultiFilterSubscriber();
            this.driveModeSettingsUpdateSubscriber.subscribe(new EventTypeMessageFilter(Constants.DRIVE_MODE_USER_SETTINGS_UPDATE_EVENT), new MessageHandler() { // from class: com.amazon.alexa.mode.util.-$$Lambda$DriveModePreferences$7cqQBh5EtBykkLTsjQU344pFoG4
                @Override // com.amazon.alexa.eventbus.api.MessageHandler
                public final void handle(Message message) {
                    DriveModePreferences.this.lambda$subscribeToDriveModeSettingsUpdate$1$DriveModePreferences(message);
                }
            });
            this.eventBus.subscribe(this.driveModeSettingsUpdateSubscriber);
        }
    }

    public /* synthetic */ void lambda$subscribeToDriveModeSettingsRequest$0$DriveModePreferences(Message message) {
        String payloadAsString = message.getPayloadAsString();
        postDriveModeSettingsResponse(payloadAsString, this.prefsDialogHelper.getPreferenceValue(payloadAsString));
    }

    public /* synthetic */ void lambda$subscribeToDriveModeSettingsUpdate$1$DriveModePreferences(Message message) {
        try {
            JSONObject jSONObject = new JSONObject(message.getPayloadAsString());
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                int i = jSONObject.getInt(str);
                this.prefsDialogHelper.setPreferenceValue(str, i);
                String str2 = "Updated " + str + " with value: " + i;
            }
        } catch (JSONException e) {
            Log.e(TAG, "Exception while updating user settings", e);
        }
    }

    public void resetPreferencesToDefaults() {
        this.prefsDialogHelper.setPreferenceValue(Constants.DRIVE_MODE_AUTO_INGRESS_SETTING, 1);
        this.prefsDialogHelper.setPreferenceValue(Constants.DRIVE_MODE_NOTIFICATION_SETTING, 1);
        this.prefsDialogHelper.setPreferenceValue(Constants.DRIVE_MODE_AUTO_SWITCH_SETTING, 1);
        this.prefsDialogHelper.setPreferenceValue(Constants.DRIVE_MODE_DARK_MODE_SETTING, 1);
        this.prefsDialogHelper.setDriveModeConfiguredPref(0);
        this.prefsDialogHelper.setStandAloneModeConfiguredPref(0);
    }
}
