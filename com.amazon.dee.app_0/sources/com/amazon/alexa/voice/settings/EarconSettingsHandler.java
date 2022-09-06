package com.amazon.alexa.voice.settings;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.api.AlexaAttentionSystemSettingsListener;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.voice.metrics.VoxMetricEvent;
import com.amazon.alexa.voice.metrics.VoxMetricEventName;
import com.amazon.alexa.voice.metrics.VoxMetricEventProcessingService;
import com.amazon.alexa.voice.sdk.AttentionSystemManager;
import com.amazon.alexa.voice.sdk.NoOpAlexaServiceConnectionListener;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public class EarconSettingsHandler implements SettingsHandler {
    @VisibleForTesting
    static final String EARCON_SETTING_REQUEST_EVENT = "voice::wakewordEarconEnabledSettingsRequest";
    @VisibleForTesting
    static final String EARCON_SETTING_RESPONSE_EVENT = "voice::wakewordEarconEnabledSettingsResponse";
    @VisibleForTesting
    static final String KEY_EOR_ENABLED = "eorEnabled";
    @VisibleForTesting
    static final String KEY_SOR_ENABLED = "sorEnabled";
    private static final String TAG = "EarconSettingsHandler";
    private final AttentionSystemManager attentionSystemManager;
    private final AlexaServicesConnection.ConnectionListener connectionListener;
    private final EarconSettingsListener earconSettingsListener;
    private final EventBus eventBus;
    private final AtomicBoolean shouldPublishMetrics = new AtomicBoolean();
    private UUID subscriptionId;

    /* loaded from: classes11.dex */
    private class AMPDEarconAlexaServiceConnectionListener extends NoOpAlexaServiceConnectionListener {
        private AMPDEarconAlexaServiceConnectionListener() {
        }

        @Override // com.amazon.alexa.voice.sdk.NoOpAlexaServiceConnectionListener, com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            EarconSettingsHandler.this.attentionSystemManager.setWakeSoundEnabled(true);
            EarconSettingsHandler.this.attentionSystemManager.setEndpointSoundEnabled(true);
            EarconSettingsHandler.this.eventBus.unsubscribe(EarconSettingsHandler.this.subscriptionId);
            EarconSettingsHandler.this.stopSubscription();
        }
    }

    /* loaded from: classes11.dex */
    private class DefaultEarconAlexaServiceConnectionListener extends NoOpAlexaServiceConnectionListener {
        private DefaultEarconAlexaServiceConnectionListener() {
        }

        @Override // com.amazon.alexa.voice.sdk.NoOpAlexaServiceConnectionListener, com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onConnected() {
            EarconSettingsHandler.this.attentionSystemManager.registerAttentionSystemSettingsListener(EarconSettingsHandler.this.earconSettingsListener);
        }

        @Override // com.amazon.alexa.voice.sdk.NoOpAlexaServiceConnectionListener, com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
        public void onDisconnected() {
            EarconSettingsHandler.this.attentionSystemManager.deregisterAttentionSystemSettingsListener(EarconSettingsHandler.this.earconSettingsListener);
        }
    }

    /* loaded from: classes11.dex */
    class EarconSettingsListener implements AlexaAttentionSystemSettingsListener {
        private final VoxMetricEventProcessingService metricEventProcessingService;
        private final AtomicBoolean shouldPublishMetrics;

        EarconSettingsListener(AtomicBoolean atomicBoolean, VoxMetricEventProcessingService voxMetricEventProcessingService) {
            this.shouldPublishMetrics = atomicBoolean;
            this.metricEventProcessingService = voxMetricEventProcessingService;
        }

        @Override // com.amazon.alexa.api.AlexaAttentionSystemSettingsListener
        public void onEndpointSoundEnabledChanged(boolean z) {
            EarconSettingsHandler.this.publishEarconSettingsInfo();
            if (this.shouldPublishMetrics.getAndSet(false)) {
                this.metricEventProcessingService.process(VoxMetricEvent.occurNow(z ? VoxMetricEventName.VOX_EARCON_ENDPOINT_SOUND_ENABLED : VoxMetricEventName.VOX_EARCON_ENDPOINT_SOUND_DISABLED));
            }
        }

        @Override // com.amazon.alexa.api.AlexaAttentionSystemSettingsListener
        public void onWakeSoundEnabledChanged(boolean z) {
            EarconSettingsHandler.this.publishEarconSettingsInfo();
            if (this.shouldPublishMetrics.getAndSet(false)) {
                this.metricEventProcessingService.process(VoxMetricEvent.occurNow(z ? VoxMetricEventName.VOX_EARCON_WAKE_SOUND_ENABLED : VoxMetricEventName.VOX_EARCON_WAKE_SOUND_DISABLED));
            }
        }
    }

    public EarconSettingsHandler(EventBus eventBus, AttentionSystemManager attentionSystemManager, VoxMetricEventProcessingService voxMetricEventProcessingService, Context context) {
        this.eventBus = eventBus;
        this.attentionSystemManager = attentionSystemManager;
        this.earconSettingsListener = new EarconSettingsListener(this.shouldPublishMetrics, voxMetricEventProcessingService);
        this.connectionListener = AMPDInformationProvider.getInstance(context).isAMPDDevice() ? new AMPDEarconAlexaServiceConnectionListener() : new DefaultEarconAlexaServiceConnectionListener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishEarconSettingsInfo() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(KEY_SOR_ENABLED, this.attentionSystemManager.isWakeSoundEnabled());
            jSONObject.put(KEY_EOR_ENABLED, this.attentionSystemManager.isEndpointSoundEnabled());
            VoiceSettings.publishPayload(this.eventBus, EARCON_SETTING_RESPONSE_EVENT, jSONObject);
        } catch (JSONException e) {
            Log.e(TAG, "Error constructing earcon settings info", e);
        }
    }

    private void setEndpointSoundEnabled(boolean z) {
        this.attentionSystemManager.setEndpointSoundEnabled(z);
    }

    private void setWakeSoundEnabled(boolean z) {
        this.attentionSystemManager.setWakeSoundEnabled(z);
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
            publishEarconSettingsInfo();
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(payloadAsString);
            if (jSONObject.has(KEY_SOR_ENABLED)) {
                setWakeSoundEnabled(jSONObject.getBoolean(KEY_SOR_ENABLED));
                this.shouldPublishMetrics.set(true);
            } else if (jSONObject.has(KEY_EOR_ENABLED)) {
                setEndpointSoundEnabled(jSONObject.getBoolean(KEY_EOR_ENABLED));
                this.shouldPublishMetrics.set(true);
            }
        } catch (JSONException unused) {
            GeneratedOutlineSupport1.outline162("Invalid message payload received ", payloadAsString, TAG);
        }
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public boolean isEventTypeSupported(String str) {
        return EARCON_SETTING_REQUEST_EVENT.equals(str);
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public void startSubscription(UUID uuid) {
        this.subscriptionId = uuid;
        this.attentionSystemManager.registerAlexaServicesConnectionListener(this.connectionListener);
    }

    @Override // com.amazon.alexa.voice.settings.SettingsHandler
    public void stopSubscription() {
        this.subscriptionId = null;
        this.attentionSystemManager.deregisterAlexaServicesConnectionListener(this.connectionListener);
    }
}
