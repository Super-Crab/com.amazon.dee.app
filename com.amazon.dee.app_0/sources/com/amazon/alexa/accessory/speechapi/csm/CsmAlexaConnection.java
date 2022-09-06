package com.amazon.alexa.accessory.speechapi.csm;

import amazon.alexa.locale.AlexaLocaleListener;
import amazon.speech.simclient.event.EventClient;
import amazon.speech.simclient.event.EventResult;
import amazon.speech.simclient.event.EventStatusCallback;
import amazon.speech.simclient.settings.Settings;
import amazon.speech.simclient.settings.SettingsClient;
import amazon.speech.simclient.settings.SettingsStatusCallback;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.AmbientSoundDispatcher;
import com.amazon.alexa.accessory.speechapi.context.MessageContextProvider;
import com.amazon.alexa.accessory.speechapi.csm.ambient_sound.CsmAmbientSoundDispatcherInstance;
import com.amazon.alexa.accessory.speechapi.csm.context.CsmContextProvider;
import com.amazon.alexa.accessory.speechapi.csm.events.CsmEvent;
import com.amazon.alexa.accessory.speechapi.csm.events.CsmEventKt;
import com.amazon.alexa.accessory.speechapi.csm.factories.AlexaFactoryProvider;
import com.amazon.alexa.accessory.speechapi.csm.factories.AlexaFactoryProviderInterface;
import com.amazon.alexa.accessory.speechapi.csm.metrics.MetricsConstants;
import com.amazon.alexa.accessory.speechapi.csm.reportstate.CsmReportAccessoryStateDependencyHolder;
import com.amazon.alexa.accessory.speechapi.csm.speech.CsmAudioSink;
import com.amazon.alexa.accessory.speechapi.csm.speech.CsmDataSink;
import com.amazon.alexa.accessory.speechapi.csm.speech.CsmUtteranceProvider;
import com.amazon.alexa.accessory.speechapi.events.AccessoryEventSender;
import com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator;
import com.amazon.alexa.accessory.speechapi.events.ApiCallbacks;
import com.amazon.alexa.accessory.speechapi.events.MessageEvent;
import com.amazon.alexa.accessory.speechapi.listeners.AlertsListener;
import com.amazon.alexa.accessory.speechapi.listeners.AudioPlaybackStatusListener;
import com.amazon.alexa.accessory.speechapi.listeners.CaptionListener;
import com.amazon.alexa.accessory.speechapi.listeners.CardListener;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
import com.amazon.alexa.accessory.speechapi.listeners.ReadinessListener;
import com.amazon.alexa.accessory.speechapi.listeners.SettingsListener;
import com.amazon.alexa.accessory.speechapi.listeners.StateListener;
import com.amazon.alexa.accessory.speechapi.speech.AccessorySink;
import com.amazon.alexa.accessory.speechapi.speech.DialogRequest;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProviderMetadata;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.devices.Alexa;
import com.amazon.alexa.devices.AlexaException;
import com.amazon.alexa.devices.speechrecognizer.SpeechRecognizerComponent;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CsmAlexaConnection.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 v2\u00020\u0001:\u0002vwBA\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u000200H\u0016J\b\u00104\u001a\u000200H\u0016J\b\u00105\u001a\u000206H\u0016J\b\u00107\u001a\u000206H\u0016J\u0010\u00108\u001a\u0002002\u0006\u00109\u001a\u00020,H\u0016J\u0010\u0010:\u001a\u0002002\u0006\u0010;\u001a\u00020<H\u0016J\u0010\u0010=\u001a\u0002002\u0006\u0010;\u001a\u00020>H\u0016J\u0010\u0010?\u001a\u0002002\u0006\u0010;\u001a\u00020@H\u0016J\u0010\u0010A\u001a\u0002002\u0006\u0010;\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u0002002\u0006\u0010;\u001a\u00020DH\u0016J\u0010\u0010E\u001a\u0002002\u0006\u0010F\u001a\u00020GH\u0016J\u0010\u0010H\u001a\u0002002\u0006\u0010;\u001a\u00020IH\u0016J\u0010\u0010J\u001a\u0002002\u0006\u0010;\u001a\u00020#H\u0016J\u0010\u0010K\u001a\u0002002\u0006\u0010;\u001a\u00020(H\u0016J\b\u0010L\u001a\u000200H\u0016J\n\u0010M\u001a\u0004\u0018\u00010NH\u0016J\b\u0010O\u001a\u00020\u001aH\u0016J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\u0018\u0010P\u001a\u0002002\u0006\u0010Q\u001a\u00020R2\u0006\u0010S\u001a\u00020TH\u0002J\u0018\u0010U\u001a\u0002002\u0006\u0010V\u001a\u00020T2\u0006\u0010W\u001a\u00020\u001aH\u0002J\u0010\u0010X\u001a\u0002002\u0006\u0010;\u001a\u00020<H\u0016J\u0010\u0010Y\u001a\u0002002\u0006\u0010;\u001a\u00020>H\u0016J\u0010\u0010Z\u001a\u0002002\u0006\u0010;\u001a\u00020@H\u0016J\u0010\u0010[\u001a\u0002002\u0006\u0010;\u001a\u00020BH\u0016J\u0010\u0010\\\u001a\u0002002\u0006\u0010;\u001a\u00020DH\u0016J\u0010\u0010]\u001a\u0002002\u0006\u0010F\u001a\u00020GH\u0016J\u0010\u0010^\u001a\u0002002\u0006\u0010;\u001a\u00020IH\u0016J\u0010\u0010_\u001a\u0002002\u0006\u0010;\u001a\u00020#H\u0016J\u0010\u0010`\u001a\u0002002\u0006\u0010;\u001a\u00020(H\u0016J\u0018\u0010a\u001a\u0002002\u0006\u00109\u001a\u00020,2\u0006\u0010b\u001a\u00020cH\u0016J\u0018\u0010d\u001a\u0002002\u0006\u0010e\u001a\u00020,2\u0006\u0010f\u001a\u00020gH\u0016J\u0018\u0010h\u001a\u0002002\u0006\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020lH\u0016J\u0010\u0010m\u001a\u0002002\u0006\u0010n\u001a\u00020\u001aH\u0016J \u0010o\u001a\u0002002\u0006\u0010p\u001a\u00020q2\u0006\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020uH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010'\u001a\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0\"X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010*\u001a\u001e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0+j\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-`.X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006x"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/CsmAlexaConnection;", "Lcom/amazon/alexa/accessory/speechapi/AlexaConnection;", "context", "Landroid/content/Context;", "alexaFactoryProvider", "Lcom/amazon/alexa/accessory/speechapi/csm/factories/AlexaFactoryProviderInterface;", "settingsClient", "Lamazon/speech/simclient/settings/SettingsClient;", "localeHelperWrapper", "Lcom/amazon/alexa/accessory/speechapi/csm/AlexaLocaleHelperWrapper;", "csmContextProvider", "Lcom/amazon/alexa/accessory/speechapi/csm/context/CsmContextProvider;", "eventClient", "Lamazon/speech/simclient/event/EventClient;", "(Landroid/content/Context;Lcom/amazon/alexa/accessory/speechapi/csm/factories/AlexaFactoryProviderInterface;Lamazon/speech/simclient/settings/SettingsClient;Lcom/amazon/alexa/accessory/speechapi/csm/AlexaLocaleHelperWrapper;Lcom/amazon/alexa/accessory/speechapi/csm/context/CsmContextProvider;Lamazon/speech/simclient/event/EventClient;)V", "getAlexaFactoryProvider", "()Lcom/amazon/alexa/accessory/speechapi/csm/factories/AlexaFactoryProviderInterface;", "getContext", "()Landroid/content/Context;", "csmConnectionCallback", "Lcom/amazon/alexa/accessory/speechapi/csm/CsmConnectionCallback;", "handler", "Landroid/os/Handler;", "instance", "Lcom/amazon/alexa/devices/Alexa;", "isConnecting", "", "getLocaleHelperWrapper", "()Lcom/amazon/alexa/accessory/speechapi/csm/AlexaLocaleHelperWrapper;", JoinPoint.SYNCHRONIZATION_LOCK, "", "getSettingsClient", "()Lamazon/speech/simclient/settings/SettingsClient;", "settingsListenerMap", "", "Lcom/amazon/alexa/accessory/speechapi/listeners/SettingsListener;", "Lamazon/alexa/locale/AlexaLocaleListener;", "speechRecognizerComponent", "Lcom/amazon/alexa/devices/speechrecognizer/SpeechRecognizerComponent;", "stateListenerMap", "Lcom/amazon/alexa/accessory/speechapi/listeners/StateListener;", "Lamazon/speech/simclient/settings/SettingsStatusCallback;", "utteranceProviderMap", "Ljava/util/HashMap;", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;", "Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmUtteranceProvider;", "Lkotlin/collections/HashMap;", "bindAmbientSoundDispatcher", "", "ambientSoundDispatcher", "Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher;", "cancelUserInteraction", EmulateConnection.EXTRA_CONNECT, "createAccessoryAudioSink", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "createAccessoryDataSink", "deRegisterUserSpeechProvider", "userSpeechProvider", "deregisterAlertsListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/alexa/accessory/speechapi/listeners/AlertsListener;", "deregisterAudioPlaybackStatusListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/AudioPlaybackStatusListener;", "deregisterCaptionListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/CaptionListener;", "deregisterCardListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/CardListener;", "deregisterConnectionListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/ConnectionListener;", "deregisterMessageContextProvider", "messageContextProvider", "Lcom/amazon/alexa/accessory/speechapi/context/MessageContextProvider;", "deregisterReadinessListener", "Lcom/amazon/alexa/accessory/speechapi/listeners/ReadinessListener;", "deregisterSettingsListener", "deregisterStateListener", Metrics.DISCONNECT, "getLocale", "Ljava/util/Locale;", "isConnected", "notifyAlexaConnectionFailedReason", "connectingFailedReason", "Lcom/amazon/alexa/accessory/speechapi/listeners/ConnectionListener$ConnectingFailedReason;", "message", "", "recordOccurrenceMetric", "metricName", "isSuccess", "registerAlertsListener", "registerAudioPlaybackStatusListener", "registerCaptionListener", "registerCardListener", "registerConnectionListener", "registerMessageContextProvider", "registerReadinessListener", "registerSettingsListener", "registerStateListener", "registerUserSpeechProvider", "metadata", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProviderMetadata;", "requestDialog", "alexaUserSpeechProvider", "alexaDialogRequest", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogRequest;", "sendEvent", "event", "Lcom/amazon/alexa/accessory/speechapi/events/MessageEvent;", "apiCallbacks", "Lcom/amazon/alexa/accessory/speechapi/events/ApiCallbacks;", "setAllowsBackgroundActivityStarts", "allowsBackgroundActivityStarts", "setReportAccessoryStateDependencies", "reportGenerator", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryStateReportGenerator;", "accessoryEventSender", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryEventSender;", "featureChecker", "Lcom/amazon/alexa/accessory/utils/feature/FeatureChecker;", "Companion", "SpeechRecognizerConnectionCallback", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmAlexaConnection implements AlexaConnection {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "CsmAlexaConnection:";
    @NotNull
    private final AlexaFactoryProviderInterface alexaFactoryProvider;
    @NotNull
    private final Context context;
    private final CsmConnectionCallback csmConnectionCallback;
    private final CsmContextProvider csmContextProvider;
    private final EventClient eventClient;
    private final Handler handler;
    private Alexa instance;
    private boolean isConnecting;
    @NotNull
    private final AlexaLocaleHelperWrapper localeHelperWrapper;
    private final Object lock;
    @NotNull
    private final SettingsClient settingsClient;
    private final Map<SettingsListener, AlexaLocaleListener> settingsListenerMap;
    private SpeechRecognizerComponent speechRecognizerComponent;
    private final Map<StateListener, SettingsStatusCallback> stateListenerMap;
    private final HashMap<UserSpeechProvider, CsmUtteranceProvider> utteranceProviderMap;

    /* compiled from: CsmAlexaConnection.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/CsmAlexaConnection$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: CsmAlexaConnection.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0002H\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/CsmAlexaConnection$SpeechRecognizerConnectionCallback;", "Lcom/amazon/alexa/devices/Alexa$ComponentCallback;", "Lcom/amazon/alexa/devices/speechrecognizer/SpeechRecognizerComponent;", "(Lcom/amazon/alexa/accessory/speechapi/csm/CsmAlexaConnection;)V", "onFailure", "", "e", "Lcom/amazon/alexa/devices/AlexaException;", "onSuccess", "localSpeechRecognizerComponent", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    private final class SpeechRecognizerConnectionCallback implements Alexa.ComponentCallback<SpeechRecognizerComponent> {
        public SpeechRecognizerConnectionCallback() {
        }

        @Override // com.amazon.alexa.devices.Alexa.ComponentCallback
        public void onFailure(@NotNull AlexaException e) {
            Intrinsics.checkParameterIsNotNull(e, "e");
            Logger.e("CsmAlexaConnection: Failed to get the SpeechRecognizerComponent!", e);
            synchronized (CsmAlexaConnection.this.lock) {
                CsmAlexaConnection.this.isConnecting = false;
                CsmAlexaConnection.this.notifyAlexaConnectionFailedReason(ConnectionListener.ConnectingFailedReason.UNKNOWN, "Failed to get the SpeechRecognizerComponent!");
                Unit unit = Unit.INSTANCE;
            }
            CsmAlexaConnection.this.recordOccurrenceMetric(MetricsConstants.CsmAlexaConnection.CSM_ALEXA_CONNECTION_SUCCESS, false);
        }

        @Override // com.amazon.alexa.devices.Alexa.ComponentCallback
        public void onSuccess(@NotNull SpeechRecognizerComponent localSpeechRecognizerComponent) {
            Intrinsics.checkParameterIsNotNull(localSpeechRecognizerComponent, "localSpeechRecognizerComponent");
            Logger.d("CsmAlexaConnection: Retrieved SpeechRecognizerComponent.");
            synchronized (CsmAlexaConnection.this.lock) {
                CsmAlexaConnection.this.speechRecognizerComponent = localSpeechRecognizerComponent;
                CsmAlexaConnection.this.isConnecting = false;
                Unit unit = Unit.INSTANCE;
            }
            CsmAlexaConnection.this.csmConnectionCallback.registerCSMConnectionCallback();
            CsmAlexaConnection.this.recordOccurrenceMetric(MetricsConstants.CsmAlexaConnection.CSM_ALEXA_CONNECTION_SUCCESS, true);
        }
    }

    @JvmOverloads
    public CsmAlexaConnection(@NotNull Context context) {
        this(context, null, null, null, null, null, 62, null);
    }

    @JvmOverloads
    public CsmAlexaConnection(@NotNull Context context, @NotNull AlexaFactoryProviderInterface alexaFactoryProviderInterface) {
        this(context, alexaFactoryProviderInterface, null, null, null, null, 60, null);
    }

    @JvmOverloads
    public CsmAlexaConnection(@NotNull Context context, @NotNull AlexaFactoryProviderInterface alexaFactoryProviderInterface, @NotNull SettingsClient settingsClient) {
        this(context, alexaFactoryProviderInterface, settingsClient, null, null, null, 56, null);
    }

    @JvmOverloads
    public CsmAlexaConnection(@NotNull Context context, @NotNull AlexaFactoryProviderInterface alexaFactoryProviderInterface, @NotNull SettingsClient settingsClient, @NotNull AlexaLocaleHelperWrapper alexaLocaleHelperWrapper) {
        this(context, alexaFactoryProviderInterface, settingsClient, alexaLocaleHelperWrapper, null, null, 48, null);
    }

    @JvmOverloads
    public CsmAlexaConnection(@NotNull Context context, @NotNull AlexaFactoryProviderInterface alexaFactoryProviderInterface, @NotNull SettingsClient settingsClient, @NotNull AlexaLocaleHelperWrapper alexaLocaleHelperWrapper, @NotNull CsmContextProvider csmContextProvider) {
        this(context, alexaFactoryProviderInterface, settingsClient, alexaLocaleHelperWrapper, csmContextProvider, null, 32, null);
    }

    @JvmOverloads
    public CsmAlexaConnection(@NotNull Context context, @NotNull AlexaFactoryProviderInterface alexaFactoryProvider, @NotNull SettingsClient settingsClient, @NotNull AlexaLocaleHelperWrapper localeHelperWrapper, @NotNull CsmContextProvider csmContextProvider, @NotNull EventClient eventClient) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(alexaFactoryProvider, "alexaFactoryProvider");
        Intrinsics.checkParameterIsNotNull(settingsClient, "settingsClient");
        Intrinsics.checkParameterIsNotNull(localeHelperWrapper, "localeHelperWrapper");
        Intrinsics.checkParameterIsNotNull(csmContextProvider, "csmContextProvider");
        Intrinsics.checkParameterIsNotNull(eventClient, "eventClient");
        this.context = context;
        this.alexaFactoryProvider = alexaFactoryProvider;
        this.settingsClient = settingsClient;
        this.localeHelperWrapper = localeHelperWrapper;
        this.csmContextProvider = csmContextProvider;
        this.eventClient = eventClient;
        this.lock = new Object();
        this.csmConnectionCallback = new CsmConnectionCallback(this.settingsClient);
        this.handler = new Handler(Looper.getMainLooper());
        this.utteranceProviderMap = new HashMap<>();
        this.stateListenerMap = new HashMap();
        this.settingsListenerMap = new HashMap();
    }

    public static final /* synthetic */ SpeechRecognizerComponent access$getSpeechRecognizerComponent$p(CsmAlexaConnection csmAlexaConnection) {
        SpeechRecognizerComponent speechRecognizerComponent = csmAlexaConnection.speechRecognizerComponent;
        if (speechRecognizerComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("speechRecognizerComponent");
        }
        return speechRecognizerComponent;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyAlexaConnectionFailedReason(ConnectionListener.ConnectingFailedReason connectingFailedReason, String str) {
        this.csmConnectionCallback.notifyAlexaConnectionFailedReason(connectingFailedReason, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordOccurrenceMetric(String str, boolean z) {
        GeneratedOutlineSupport1.outline171(str, "alexa_accessories", z, null);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void bindAmbientSoundDispatcher(@NotNull AmbientSoundDispatcher ambientSoundDispatcher) {
        Intrinsics.checkParameterIsNotNull(ambientSoundDispatcher, "ambientSoundDispatcher");
        CsmAmbientSoundDispatcherInstance.INSTANCE.bind(ambientSoundDispatcher);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void cancelUserInteraction() {
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void connect() {
        Logger.i("CsmAlexaConnection: Connecting to Alexa...");
        synchronized (this.lock) {
            if (this.instance != null) {
                return;
            }
            this.isConnecting = true;
            Logger.i("CsmAlexaConnection: Creating the AlexaFactory...");
            try {
                this.instance = this.alexaFactoryProvider.getAlexa(this.context);
                Logger.i("CsmAlexaConnection: Retrieving SpeechRecognizerComponent ...");
                Alexa alexa = this.instance;
                if (alexa != null) {
                    SpeechRecognizerComponent speechRecognizerComponent = (SpeechRecognizerComponent) alexa.getComponent(SpeechRecognizerComponent.class, new SpeechRecognizerConnectionCallback());
                }
            } catch (AlexaException e) {
                this.isConnecting = false;
                recordOccurrenceMetric(MetricsConstants.CsmAlexaConnection.CSM_ALEXA_CONNECTION_SUCCESS, false);
                notifyAlexaConnectionFailedReason(ConnectionListener.ConnectingFailedReason.UNKNOWN, "Cannot create Alexa instance from factory.");
                Logger.e("CsmAlexaConnection: Cannot create Alexa instance from factory.", e);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    @NotNull
    public AccessorySink createAccessoryAudioSink() {
        SpeechRecognizerComponent speechRecognizerComponent = this.speechRecognizerComponent;
        if (speechRecognizerComponent == null) {
            Intrinsics.throwUninitializedPropertyAccessException("speechRecognizerComponent");
        }
        return new CsmAudioSink(speechRecognizerComponent);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    @NotNull
    public AccessorySink createAccessoryDataSink() {
        return new CsmDataSink(null, 1, null);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deRegisterUserSpeechProvider(@NotNull UserSpeechProvider userSpeechProvider) {
        Intrinsics.checkParameterIsNotNull(userSpeechProvider, "userSpeechProvider");
        Logger.d("CsmAlexaConnection: deRegisterUserSpeechProvider");
        synchronized (this.lock) {
            CsmUtteranceProvider remove = this.utteranceProviderMap.remove(userSpeechProvider);
            if (remove == null) {
                Logger.d("CsmAlexaConnection: No UtteranceProvider to deregister");
            } else if (this.speechRecognizerComponent == null) {
                Logger.e("CsmAlexaConnection: Cannot deregister UtteranceProvider. SpeechRecognizerComponent is not initialized");
            } else {
                Logger.d("CsmAlexaConnection: Deregister UtteranceProvider.");
                SpeechRecognizerComponent speechRecognizerComponent = this.speechRecognizerComponent;
                if (speechRecognizerComponent == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("speechRecognizerComponent");
                }
                if (!speechRecognizerComponent.deregisterUtteranceProvider(remove)) {
                    Logger.e("CsmAlexaConnection: Failed to deregister UtteranceProvider ");
                }
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterAlertsListener(@NotNull AlertsListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterAudioPlaybackStatusListener(@NotNull AudioPlaybackStatusListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterCaptionListener(@NotNull CaptionListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterCardListener(@NotNull CardListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterConnectionListener(@NotNull ConnectionListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Logger.d("CsmAlexaConnection: deregisterConnectionListener");
        this.csmConnectionCallback.removeConnectionListener(listener);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterMessageContextProvider(@NotNull MessageContextProvider messageContextProvider) {
        Intrinsics.checkParameterIsNotNull(messageContextProvider, "messageContextProvider");
        this.csmContextProvider.unregisterContextProvider(messageContextProvider);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterReadinessListener(@NotNull ReadinessListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterSettingsListener(@NotNull SettingsListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.lock) {
            this.settingsListenerMap.remove(listener);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterStateListener(@NotNull StateListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.lock) {
            SettingsStatusCallback remove = this.stateListenerMap.remove(listener);
            if (remove != null) {
                this.settingsClient.unregisterCallback(Settings.LISTENING, remove);
                this.settingsClient.unregisterCallback(Settings.THINKING, remove);
                this.settingsClient.unregisterCallback(Settings.SPEAKING, remove);
                this.settingsClient.unregisterCallback(Settings.INTERACTING, remove);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void disconnect() {
        Logger.d("CsmAlexaConnection: Destroying the CsmAlexaConnection.");
        synchronized (this.lock) {
            Iterator it2 = new HashSet(this.utteranceProviderMap.keySet()).iterator();
            while (it2.hasNext()) {
                UserSpeechProvider userSpeechProvider = (UserSpeechProvider) it2.next();
                Intrinsics.checkExpressionValueIsNotNull(userSpeechProvider, "userSpeechProvider");
                deRegisterUserSpeechProvider(userSpeechProvider);
            }
            this.utteranceProviderMap.clear();
            Alexa alexa = this.instance;
            if (alexa != null) {
                alexa.destroy();
            }
            this.instance = null;
            this.csmContextProvider.clearContext();
            this.csmConnectionCallback.unregisterCSMConnectionCallback();
            Unit unit = Unit.INSTANCE;
        }
    }

    @NotNull
    public final AlexaFactoryProviderInterface getAlexaFactoryProvider() {
        return this.alexaFactoryProvider;
    }

    @NotNull
    public final Context getContext() {
        return this.context;
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    @Nullable
    public Locale getLocale() {
        return this.localeHelperWrapper.getAlexaLocale(this.context);
    }

    @NotNull
    public final AlexaLocaleHelperWrapper getLocaleHelperWrapper() {
        return this.localeHelperWrapper;
    }

    @NotNull
    public final SettingsClient getSettingsClient() {
        return this.settingsClient;
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public boolean isConnected() {
        Alexa alexa = this.instance;
        if (alexa != null) {
            return alexa.isConnected();
        }
        return false;
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public boolean isConnecting() {
        return this.isConnecting;
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerAlertsListener(@NotNull AlertsListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerAudioPlaybackStatusListener(@NotNull AudioPlaybackStatusListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerCaptionListener(@NotNull CaptionListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerCardListener(@NotNull CardListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerConnectionListener(@NotNull ConnectionListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        Logger.d("CsmAlexaConnection: registerConnectionListener");
        this.csmConnectionCallback.addAndNotifyConnectionListener(listener, this.instance != null);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerMessageContextProvider(@NotNull MessageContextProvider messageContextProvider) {
        Intrinsics.checkParameterIsNotNull(messageContextProvider, "messageContextProvider");
        this.csmContextProvider.registerContextProvider(messageContextProvider);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerReadinessListener(@NotNull ReadinessListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerSettingsListener(@NotNull final SettingsListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        final Context context = this.context;
        AlexaLocaleListener alexaLocaleListener = new AlexaLocaleListener(context) { // from class: com.amazon.alexa.accessory.speechapi.csm.CsmAlexaConnection$registerSettingsListener$alexaLocaleListener$1
            @Override // amazon.alexa.locale.AlexaLocaleListener, amazon.alexa.locale.AlexaLocaleHelper.IAlexaLocaleListener
            public void onAlexaLocaleChanged() {
                Map map;
                Locale alexaLocale = CsmAlexaConnection.this.getLocaleHelperWrapper().getAlexaLocale(CsmAlexaConnection.this.getContext());
                if (alexaLocale != null) {
                    synchronized (CsmAlexaConnection.this.lock) {
                        map = CsmAlexaConnection.this.settingsListenerMap;
                        if (map.containsKey(listener)) {
                            listener.onLocaleChanged(alexaLocale);
                        }
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
        };
        synchronized (this.lock) {
            if (this.settingsListenerMap.containsKey(listener)) {
                return;
            }
            this.settingsListenerMap.put(listener, alexaLocaleListener);
            this.localeHelperWrapper.registerListener(this.context, alexaLocaleListener);
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerStateListener(@NotNull StateListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        CsmAlexaConnection$registerStateListener$callback$1 csmAlexaConnection$registerStateListener$callback$1 = new CsmAlexaConnection$registerStateListener$callback$1(this, listener);
        synchronized (this.lock) {
            if (this.stateListenerMap.containsKey(listener)) {
                return;
            }
            this.stateListenerMap.put(listener, csmAlexaConnection$registerStateListener$callback$1);
            this.settingsClient.registerCallback(Settings.LISTENING, csmAlexaConnection$registerStateListener$callback$1, true);
            this.settingsClient.registerCallback(Settings.THINKING, csmAlexaConnection$registerStateListener$callback$1, true);
            this.settingsClient.registerCallback(Settings.SPEAKING, csmAlexaConnection$registerStateListener$callback$1, true);
            this.settingsClient.registerCallback(Settings.INTERACTING, csmAlexaConnection$registerStateListener$callback$1, true);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerUserSpeechProvider(@NotNull UserSpeechProvider userSpeechProvider, @NotNull UserSpeechProviderMetadata metadata) {
        Intrinsics.checkParameterIsNotNull(userSpeechProvider, "userSpeechProvider");
        Intrinsics.checkParameterIsNotNull(metadata, "metadata");
        Logger.d("CsmAlexaConnection: registerUserSpeechProvider");
        synchronized (this.lock) {
            if (this.utteranceProviderMap.get(userSpeechProvider) != null) {
                return;
            }
            if (this.speechRecognizerComponent == null) {
                Logger.e("CsmAlexaConnection: Cannot register UtteranceProvider. SpeechRecognizerComponent is not initialized");
                return;
            }
            Logger.d("CsmAlexaConnection: Creating instance of UtteranceProvider");
            CsmUtteranceProvider csmUtteranceProvider = new CsmUtteranceProvider(this.context, userSpeechProvider, this.csmContextProvider, null, 8, null);
            SpeechRecognizerComponent speechRecognizerComponent = this.speechRecognizerComponent;
            if (speechRecognizerComponent == null) {
                Intrinsics.throwUninitializedPropertyAccessException("speechRecognizerComponent");
            }
            if (speechRecognizerComponent.registerUtteranceProvider(csmUtteranceProvider)) {
                Logger.d("CsmAlexaConnection: Registered UtteranceProvider");
                this.utteranceProviderMap.put(userSpeechProvider, csmUtteranceProvider);
            } else {
                Logger.e("CsmAlexaConnection: Failed to register UtteranceProvider ");
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void requestDialog(@NotNull UserSpeechProvider alexaUserSpeechProvider, @NotNull DialogRequest alexaDialogRequest) {
        Intrinsics.checkParameterIsNotNull(alexaUserSpeechProvider, "alexaUserSpeechProvider");
        Intrinsics.checkParameterIsNotNull(alexaDialogRequest, "alexaDialogRequest");
        CsmUtteranceProvider csmUtteranceProvider = this.utteranceProviderMap.get(alexaUserSpeechProvider);
        if (csmUtteranceProvider == null) {
            Logger.d("CsmAlexaConnection: Cannot request dialog. CsmUtteranceProvider is null for " + alexaUserSpeechProvider);
            return;
        }
        Logger.d("CsmAlexaConnection: csmUtteranceProvider  requestDialog");
        csmUtteranceProvider.requestDialog(alexaDialogRequest);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void sendEvent(@NotNull MessageEvent event, @NotNull final ApiCallbacks apiCallbacks) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(apiCallbacks, "apiCallbacks");
        EventStatusCallback eventStatusCallback = new EventStatusCallback() { // from class: com.amazon.alexa.accessory.speechapi.csm.CsmAlexaConnection$sendEvent$eventStatusCallback$1
            @Override // amazon.speech.simclient.event.EventStatusCallback
            public final void onResult(EventResult eventResult) {
                if (eventResult == EventResult.SUCCESS) {
                    ApiCallbacks.this.onSuccess();
                } else {
                    ApiCallbacks.this.onFailure(new Exception());
                }
            }
        };
        CsmEvent csmEvent = CsmEventKt.toCsmEvent(event);
        this.eventClient.sendEvent(csmEvent.getEventMetadata(), csmEvent.getPayload(), eventStatusCallback);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void setAllowsBackgroundActivityStarts(boolean z) {
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void setReportAccessoryStateDependencies(@NotNull AccessoryStateReportGenerator reportGenerator, @NotNull AccessoryEventSender accessoryEventSender, @NotNull FeatureChecker featureChecker) {
        Intrinsics.checkParameterIsNotNull(reportGenerator, "reportGenerator");
        Intrinsics.checkParameterIsNotNull(accessoryEventSender, "accessoryEventSender");
        Intrinsics.checkParameterIsNotNull(featureChecker, "featureChecker");
        CsmReportAccessoryStateDependencyHolder.INSTANCE.init(reportGenerator, accessoryEventSender, featureChecker);
    }

    public /* synthetic */ CsmAlexaConnection(Context context, AlexaFactoryProviderInterface alexaFactoryProviderInterface, SettingsClient settingsClient, AlexaLocaleHelperWrapper alexaLocaleHelperWrapper, CsmContextProvider csmContextProvider, EventClient eventClient, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new AlexaFactoryProvider() : alexaFactoryProviderInterface, (i & 4) != 0 ? new SettingsClient(context) : settingsClient, (i & 8) != 0 ? new AlexaLocaleHelperWrapper() : alexaLocaleHelperWrapper, (i & 16) != 0 ? new CsmContextProvider(context, null, 2, null) : csmContextProvider, (i & 32) != 0 ? new EventClient(context) : eventClient);
    }
}
