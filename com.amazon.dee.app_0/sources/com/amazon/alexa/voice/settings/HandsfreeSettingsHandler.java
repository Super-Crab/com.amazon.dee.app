package com.amazon.alexa.voice.settings;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.voice.R;
import com.amazon.alexa.voice.ftue.FtuePreference;
import com.amazon.alexa.voice.metrics.VoiceMetricsConstants;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventName;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.permissions.VoicePermissionsAuthority;
import com.amazon.alexa.voice.wakeword.AbiCompatibilityInterface;
import com.amazon.alexa.voice.wakeword.WakewordPermissionRequestActivity;
import com.amazon.alexa.voice.wakeword.WakewordPreference;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class HandsfreeSettingsHandler implements SettingsHandler {
    @VisibleForTesting
    static final String HANDSFREE_SETTING_VALUE = "handsfreeEnabled";
    @VisibleForTesting
    static final String KEY_HAS_SUPPORTED_ARCHITECTURE = "hasCompatibleArchitecture";
    @VisibleForTesting
    static final String KEY_NEEDS_SETUP = "needsSetup";
    @VisibleForTesting
    static final String KEY_PAGE_TITLE = "pageTitle";
    @VisibleForTesting
    static final String KEY_SETTING_DESCRIPTION = "settingDescription";
    @VisibleForTesting
    static final String KEY_SETTING_ENABLED = "settingEnabled";
    @VisibleForTesting
    static final String KEY_SETTING_TITLE = "settingTitle";
    private static final String TAG = "HandsfreeSettingsHandler";
    @VisibleForTesting
    static final String VOICE_ELEMENTS_MESSAGE_TO_VOICE_NATIVE = "voice::wakewordUpdateSettingsRequest";
    @VisibleForTesting
    static final String VOICE_NATIVE_MESSAGE_TO_VOICE_ELEMENTS = "voice::wakewordUpdateSettingsResponse";
    private final AbiCompatibilityInterface compatibilityInterface;
    private final Context context;
    private final EventBus eventBus;
    private final FtuePreference ftuePreference;
    private UUID subscriptionId;
    private final VoicePermissionsAuthority voicePermissionsAuthority;
    private final VoxMetricEventProcessingService voxMetricEventProcessingService;
    private final WakewordPreference wakewordPreference;

    public HandsfreeSettingsHandler(EventBus eventBus, Context context, WakewordPreference wakewordPreference, FtuePreference ftuePreference, VoicePermissionsAuthority voicePermissionsAuthority, VoxMetricEventProcessingService voxMetricEventProcessingService, AbiCompatibilityInterface abiCompatibilityInterface) {
        this.eventBus = eventBus;
        this.context = context;
        this.wakewordPreference = wakewordPreference;
        this.ftuePreference = ftuePreference;
        this.voicePermissionsAuthority = voicePermissionsAuthority;
        this.voxMetricEventProcessingService = voxMetricEventProcessingService;
        this.compatibilityInterface = abiCompatibilityInterface;
    }

    private boolean hasNecessaryPermission() {
        return this.voicePermissionsAuthority.hasMinimumPermissions();
    }

    private boolean isAbiCompatible() {
        return this.compatibilityInterface.isCompatible();
    }

    private boolean isHandsfreeEnabled() {
        return this.wakewordPreference.isWakewordEnabled();
    }

    private void publishHandsfreeInfo() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(KEY_PAGE_TITLE, this.context.getString(R.string.voice_ui_od_handsfree_settings_page_title));
            jSONObject.put(KEY_SETTING_DESCRIPTION, this.context.getString(R.string.voice_ui_od_handsfree_settings_description));
            jSONObject.put(KEY_SETTING_TITLE, this.context.getString(R.string.voice_ui_od_handsfree_settings_title));
            boolean z = true;
            jSONObject.put(KEY_SETTING_ENABLED, isHandsfreeEnabled() && hasNecessaryPermission());
            jSONObject.put(KEY_HAS_SUPPORTED_ARCHITECTURE, isAbiCompatible());
            if (this.ftuePreference.hasCompletedWakewordFtue()) {
                z = false;
            }
            jSONObject.put(KEY_NEEDS_SETUP, z);
            VoiceSettings.publishPayload(this.eventBus, VOICE_NATIVE_MESSAGE_TO_VOICE_ELEMENTS, jSONObject);
        } catch (JSONException e) {
            Log.e(TAG, "Error constructing Handsfree settings info", e);
        }
    }

    private void setHandsfreeEnabled(boolean z) {
        if (!z) {
            this.voxMetricEventProcessingService.process(VoxMetricEvent.occurNow(VoxMetricEventName.VOX_HANDSFREE_SETTINGS_DISABLED));
            this.wakewordPreference.enableWakeword(z);
        } else if (hasNecessaryPermission() && isAbiCompatible()) {
            this.wakewordPreference.enableWakeword(z);
        } else {
            WakewordPermissionRequestActivity.requestMinimumPermission(this.context, VoiceMetricsConstants.COMPONENT_VOICE_SETTINGS);
        }
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    @Nullable
    public UUID getSubscriptionId() {
        return this.subscriptionId;
    }

    @Override // com.amazon.alexa.eventbus.api.MessageHandler
    public void handle(@NonNull Message message) {
        String payloadAsString = message.getPayloadAsString();
        if (TextUtils.isEmpty(payloadAsString)) {
            publishHandsfreeInfo();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(payloadAsString);
            if (!jSONObject.has(HANDSFREE_SETTING_VALUE)) {
                return;
            }
            setHandsfreeEnabled(jSONObject.getBoolean(HANDSFREE_SETTING_VALUE));
            publishHandsfreeInfo();
        } catch (JSONException unused) {
            GeneratedOutlineSupport1.outline162("Invalid message payload received ", payloadAsString, TAG);
        }
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public boolean isEventTypeSupported(String str) {
        return VOICE_ELEMENTS_MESSAGE_TO_VOICE_NATIVE.equals(str);
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public void startSubscription(UUID uuid) {
        this.subscriptionId = uuid;
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public void stopSubscription() {
        this.subscriptionId = null;
    }
}
