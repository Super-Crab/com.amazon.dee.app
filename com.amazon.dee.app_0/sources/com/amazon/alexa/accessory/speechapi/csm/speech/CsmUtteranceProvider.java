package com.amazon.alexa.accessory.speechapi.csm.speech;

import amazon.speech.simclient.settings.Settings;
import amazon.speech.simclient.settings.SettingsClient;
import amazon.speech.simclient.settings.SettingsData;
import amazon.speech.simclient.settings.SettingsStatusCallback;
import android.content.Context;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.csm.context.CsmContextProvider;
import com.amazon.alexa.accessory.speechapi.csm.metrics.MetricsConstants;
import com.amazon.alexa.accessory.speechapi.speech.DialogRequest;
import com.amazon.alexa.accessory.speechapi.speech.DialogTurnStopCallback;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import com.amazon.alexa.devices.speechrecognizer.AudioEventListener;
import com.amazon.alexa.devices.speechrecognizer.UtteranceProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CsmUtteranceProvider.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\b\u0000\u0018\u0000 +2\u00020\u0001:\u0002+,B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0012\u0010\u0018\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0011H\u0002J\u0016\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001f\u001a\u00020\u0012J\u000e\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\"J&\u0010#\u001a\u00020\u001b2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00110%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\fH\u0016J\u0012\u0010)\u001a\u00020\u00152\b\u0010\u001e\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010*\u001a\u00020\u001bH\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u00060\u000eR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u000f\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0010j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmUtteranceProvider;", "Lcom/amazon/alexa/devices/speechrecognizer/UtteranceProvider;", "context", "Landroid/content/Context;", "userSpeechProvider", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;", "csmContextProvider", "Lcom/amazon/alexa/accessory/speechapi/csm/context/CsmContextProvider;", "settingsClient", "Lamazon/speech/simclient/settings/SettingsClient;", "(Landroid/content/Context;Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;Lcom/amazon/alexa/accessory/speechapi/csm/context/CsmContextProvider;Lamazon/speech/simclient/settings/SettingsClient;)V", "audioEventListener", "Lcom/amazon/alexa/devices/speechrecognizer/AudioEventListener;", "dialogInteractionCallback", "Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmUtteranceProvider$DialogInteractionCallback;", "dialogTurnStopCallbackMap", "Ljava/util/HashMap;", "", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogTurnStopCallback;", "Lkotlin/collections/HashMap;", "isAlexaInteracting", "", "lockDialogTurnStopCallbackMap", "", "listen", "token", "recordMetric", "", "metricName", "registerDialogTurnStopCallback", "utteranceId", "dialogTurnStopCallback", "requestDialog", "dialogRequest", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogRequest;", "startUtteranceRecognition", "wakewords", "", "preRoll", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "stopAudioCapture", "stopUtteranceRecognition", "Companion", "DialogInteractionCallback", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmUtteranceProvider implements UtteranceProvider {
    private AudioEventListener audioEventListener;
    private final CsmContextProvider csmContextProvider;
    private final DialogInteractionCallback dialogInteractionCallback;
    private final HashMap<String, DialogTurnStopCallback> dialogTurnStopCallbackMap;
    private boolean isAlexaInteracting;
    private final Object lockDialogTurnStopCallbackMap;
    private final SettingsClient settingsClient;
    private final UserSpeechProvider userSpeechProvider;
    public static final Companion Companion = new Companion(null);
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    /* compiled from: CsmUtteranceProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmUtteranceProvider$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: CsmUtteranceProvider.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmUtteranceProvider$DialogInteractionCallback;", "Lamazon/speech/simclient/settings/SettingsStatusCallback;", "(Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmUtteranceProvider;)V", "onResult", "", "settingsData", "Lamazon/speech/simclient/settings/SettingsData;", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    private final class DialogInteractionCallback implements SettingsStatusCallback {
        public DialogInteractionCallback() {
        }

        @Override // amazon.speech.simclient.settings.SettingsStatusCallback
        public void onResult(@NotNull SettingsData settingsData) {
            Intrinsics.checkParameterIsNotNull(settingsData, "settingsData");
            if (Intrinsics.areEqual(settingsData.getName(), Settings.INTERACTING)) {
                Logger.d(CsmUtteranceProvider.TAG + " received Interaction status: " + settingsData.asBoolean());
                if (settingsData.asBoolean() || !CsmUtteranceProvider.this.isAlexaInteracting) {
                    return;
                }
                CsmUtteranceProvider.this.isAlexaInteracting = false;
                CsmUtteranceProvider.this.userSpeechProvider.onDialogFinished();
            }
        }
    }

    public CsmUtteranceProvider(@NotNull Context context, @NotNull UserSpeechProvider userSpeechProvider, @NotNull CsmContextProvider csmContextProvider, @NotNull SettingsClient settingsClient) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(userSpeechProvider, "userSpeechProvider");
        Intrinsics.checkParameterIsNotNull(csmContextProvider, "csmContextProvider");
        Intrinsics.checkParameterIsNotNull(settingsClient, "settingsClient");
        this.userSpeechProvider = userSpeechProvider;
        this.csmContextProvider = csmContextProvider;
        this.settingsClient = settingsClient;
        this.lockDialogTurnStopCallbackMap = new Object();
        this.dialogTurnStopCallbackMap = new HashMap<>();
        this.dialogInteractionCallback = new DialogInteractionCallback();
    }

    public static final /* synthetic */ AudioEventListener access$getAudioEventListener$p(CsmUtteranceProvider csmUtteranceProvider) {
        AudioEventListener audioEventListener = csmUtteranceProvider.audioEventListener;
        if (audioEventListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioEventListener");
        }
        return audioEventListener;
    }

    private final void recordMetric(String str) {
        GeneratedOutlineSupport1.outline171(str, "alexa_accessories", true, null);
    }

    @Override // com.amazon.alexa.devices.speechrecognizer.UtteranceProvider
    public boolean listen(@Nullable String str) {
        Logger.d(TAG + " listen");
        AudioEventListener audioEventListener = this.audioEventListener;
        if (audioEventListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioEventListener");
        }
        this.userSpeechProvider.onDialogTurnRequested(new CsmNextDialogTurn(audioEventListener, this.userSpeechProvider, this));
        return true;
    }

    public final boolean registerDialogTurnStopCallback(@NotNull String utteranceId, @NotNull DialogTurnStopCallback dialogTurnStopCallback) {
        Intrinsics.checkParameterIsNotNull(utteranceId, "utteranceId");
        Intrinsics.checkParameterIsNotNull(dialogTurnStopCallback, "dialogTurnStopCallback");
        Logger.d(TAG + " registerDialogTurnStopCallback for utteranceId: " + utteranceId);
        synchronized (this.lockDialogTurnStopCallbackMap) {
            if (this.dialogTurnStopCallbackMap.containsKey(utteranceId)) {
                Logger.d(TAG + " DialogTurnStopCallback already present for utteranceId: " + utteranceId);
                return false;
            }
            this.dialogTurnStopCallbackMap.put(utteranceId, dialogTurnStopCallback);
            return true;
        }
    }

    public final void requestDialog(@NotNull DialogRequest dialogRequest) {
        Intrinsics.checkParameterIsNotNull(dialogRequest, "dialogRequest");
        Logger.d(TAG + " requesting Dialog");
        if (this.audioEventListener == null) {
            Logger.e(TAG + " AudioEventListener is not initialized. Cannot start speech recognition.");
            return;
        }
        if (this.isAlexaInteracting) {
            this.userSpeechProvider.onDialogFinished();
        }
        this.csmContextProvider.setContext();
        AudioEventListener audioEventListener = this.audioEventListener;
        if (audioEventListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioEventListener");
        }
        this.userSpeechProvider.onDialogRequested(new CsmDialogTurn(dialogRequest, audioEventListener, this.userSpeechProvider, this));
        this.isAlexaInteracting = true;
        recordMetric(MetricsConstants.CsmSpeechMetrics.DIALOG_REQUESTED);
    }

    @Override // com.amazon.alexa.devices.speechrecognizer.UtteranceProvider
    public void startUtteranceRecognition(@NotNull List<String> wakewords, int i, @NotNull AudioEventListener listener) {
        Intrinsics.checkParameterIsNotNull(wakewords, "wakewords");
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Logger.d(TAG + " startUtteranceRecognition");
        this.audioEventListener = listener;
        this.userSpeechProvider.resumeWakeWordDetection();
        this.settingsClient.registerCallback(Settings.INTERACTING, this.dialogInteractionCallback, true);
    }

    @Override // com.amazon.alexa.devices.speechrecognizer.UtteranceProvider
    public boolean stopAudioCapture(@Nullable String str) {
        Logger.d(TAG + " stopAudioCapture");
        if (str == null || str.length() == 0) {
            Logger.e(TAG + " received stopAudioCapture with Empty or Null utteranceId");
            return false;
        }
        synchronized (this.lockDialogTurnStopCallbackMap) {
            DialogTurnStopCallback remove = this.dialogTurnStopCallbackMap.remove(str);
            if (remove == null) {
                Logger.e(TAG + " No CsmDialogTurnStopCallback for utteranceID: " + str);
                return false;
            }
            remove.stopRecording();
            return true;
        }
    }

    @Override // com.amazon.alexa.devices.speechrecognizer.UtteranceProvider
    public void stopUtteranceRecognition() {
        Logger.d(TAG + " stopUtteranceRecognition");
        this.userSpeechProvider.pauseWakeWordDetection();
        this.settingsClient.unregisterCallback(Settings.INTERACTING, this.dialogInteractionCallback);
    }

    public /* synthetic */ CsmUtteranceProvider(Context context, UserSpeechProvider userSpeechProvider, CsmContextProvider csmContextProvider, SettingsClient settingsClient, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, userSpeechProvider, csmContextProvider, (i & 8) != 0 ? new SettingsClient(context) : settingsClient);
    }
}
