package com.amazon.alexa.accessory.speechapi.voicesdk;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.speechapi.AlexaConnection;
import com.amazon.alexa.accessory.speechapi.AmbientSoundDispatcher;
import com.amazon.alexa.accessory.speechapi.context.MessageContextProvider;
import com.amazon.alexa.accessory.speechapi.events.AccessoryEventSender;
import com.amazon.alexa.accessory.speechapi.events.AccessoryStateReportGenerator;
import com.amazon.alexa.accessory.speechapi.events.ApiCallbacks;
import com.amazon.alexa.accessory.speechapi.events.MessageEvent;
import com.amazon.alexa.accessory.speechapi.listeners.AlertsListener;
import com.amazon.alexa.accessory.speechapi.listeners.AudioPlaybackStatusListener;
import com.amazon.alexa.accessory.speechapi.listeners.Caption;
import com.amazon.alexa.accessory.speechapi.listeners.CaptionFormat;
import com.amazon.alexa.accessory.speechapi.listeners.CaptionListener;
import com.amazon.alexa.accessory.speechapi.listeners.CardExtras;
import com.amazon.alexa.accessory.speechapi.listeners.CardListener;
import com.amazon.alexa.accessory.speechapi.listeners.ConnectionListener;
import com.amazon.alexa.accessory.speechapi.listeners.ReadinessListener;
import com.amazon.alexa.accessory.speechapi.listeners.SettingsListener;
import com.amazon.alexa.accessory.speechapi.listeners.StateListener;
import com.amazon.alexa.accessory.speechapi.speech.AccessorySink;
import com.amazon.alexa.accessory.speechapi.speech.DialogRequest;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProvider;
import com.amazon.alexa.accessory.speechapi.speech.UserSpeechProviderMetadata;
import com.amazon.alexa.accessory.speechapi.voicesdk.ambient_sound.VoxAmbientSoundDispatcherInstance;
import com.amazon.alexa.accessory.speechapi.voicesdk.capabilityagent.VoxReportAccessoryStateDependencyHolder;
import com.amazon.alexa.accessory.speechapi.voicesdk.context.VoxAlexaContextProvider;
import com.amazon.alexa.accessory.speechapi.voicesdk.events.VoxAlexaEventKt;
import com.amazon.alexa.accessory.speechapi.voicesdk.mapper.SpeechApiModelMapper;
import com.amazon.alexa.accessory.speechapi.voicesdk.speech.VoxAudioSink;
import com.amazon.alexa.accessory.speechapi.voicesdk.speech.VoxDataSink;
import com.amazon.alexa.accessory.speechapi.voicesdk.speech.VoxUserSpeechProvider;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import com.amazon.alexa.api.AlexaApiCallbacks;
import com.amazon.alexa.api.AlexaAudioChannel;
import com.amazon.alexa.api.AlexaCaptionListener;
import com.amazon.alexa.api.AlexaCardExtras;
import com.amazon.alexa.api.AlexaCardListener;
import com.amazon.alexa.api.AlexaConnectingFailedReason;
import com.amazon.alexa.api.AlexaDialogRequest;
import com.amazon.alexa.api.AlexaServicesConnection;
import com.amazon.alexa.api.AlexaSettingsListener;
import com.amazon.alexa.api.ApiCallFailure;
import com.amazon.alexa.api.CaptionResponse;
import com.amazon.alexa.api.compat.AlexaAudioPlaybackStatusListener;
import com.amazon.alexa.api.compat.AlexaReadinessListener;
import com.amazon.alexa.api.compat.AlexaReadyState;
import com.amazon.alexa.api.compat.AlexaServices;
import com.amazon.alexa.api.compat.AlexaServicesApis;
import com.amazon.alexa.api.compat.AlexaState;
import com.amazon.alexa.api.compat.AlexaStateListener;
import com.amazon.alexa.api.compat.AlexaUserSpeechProvider;
import com.amazon.alexa.api.compat.alerts.AlertType;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: VoxAlexaConnection.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0082\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 u2\u00020\u0001:\u0001uB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0016J\b\u00100\u001a\u00020-H\u0016J\b\u00101\u001a\u00020-H\u0016J\b\u00102\u001a\u000203H\u0016J\b\u00104\u001a\u000203H\u0016J\u0010\u00105\u001a\u00020-2\u0006\u00106\u001a\u00020'H\u0016J\u0010\u00107\u001a\u00020-2\u0006\u00108\u001a\u00020\u0007H\u0016J\u0010\u00109\u001a\u00020-2\u0006\u00108\u001a\u00020\u000bH\u0016J\u0010\u0010:\u001a\u00020-2\u0006\u00108\u001a\u00020\u000eH\u0016J\u0010\u0010;\u001a\u00020-2\u0006\u00108\u001a\u00020\u0011H\u0016J\u0010\u0010<\u001a\u00020-2\u0006\u00108\u001a\u00020\u0014H\u0016J\u0010\u0010=\u001a\u00020-2\u0006\u0010>\u001a\u00020\u001dH\u0016J\u0010\u0010?\u001a\u00020-2\u0006\u00108\u001a\u00020 H\u0016J\u0010\u0010@\u001a\u00020-2\u0006\u00108\u001a\u00020#H\u0016J\u0010\u0010A\u001a\u00020-2\u0006\u00108\u001a\u00020*H\u0016J\b\u0010B\u001a\u00020-H\u0016J\u0010\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020FH\u0002J\n\u0010G\u001a\u0004\u0018\u00010HH\u0016J\b\u0010I\u001a\u00020JH\u0016J\b\u0010K\u001a\u00020JH\u0016J\u0010\u0010L\u001a\u00020-2\u0006\u0010M\u001a\u00020NH\u0002J\u0010\u0010O\u001a\u00020-2\u0006\u00108\u001a\u00020\u0007H\u0016J\u0010\u0010P\u001a\u00020-2\u0006\u00108\u001a\u00020\u000bH\u0016J\u0010\u0010Q\u001a\u00020-2\u0006\u00108\u001a\u00020\u000eH\u0016J\u0010\u0010R\u001a\u00020-2\u0006\u00108\u001a\u00020\u0011H\u0016J\u0010\u0010S\u001a\u00020-2\u0006\u00108\u001a\u00020\u0014H\u0016J\u0010\u0010T\u001a\u00020-2\u0006\u0010>\u001a\u00020\u001dH\u0016J\u0010\u0010U\u001a\u00020-2\u0006\u00108\u001a\u00020 H\u0016J\u0010\u0010V\u001a\u00020-2\u0006\u00108\u001a\u00020#H\u0016J\u0010\u0010W\u001a\u00020-2\u0006\u00108\u001a\u00020*H\u0016J\u0018\u0010X\u001a\u00020-2\u0006\u00106\u001a\u00020'2\u0006\u0010Y\u001a\u00020ZH\u0016J\u0018\u0010[\u001a\u00020-2\u0006\u00106\u001a\u00020'2\u0006\u0010\\\u001a\u00020]H\u0016J\u0018\u0010^\u001a\u00020-2\u0006\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020bH\u0016J\u001c\u0010c\u001a\u00020-2\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0dH\u0007J\u0010\u0010e\u001a\u00020-2\u0006\u0010f\u001a\u00020JH\u0016J,\u0010g\u001a\u00020-2\"\u0010h\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0006j\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f`\tH\u0007J\u001c\u0010i\u001a\u00020-2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0dH\u0007J\u001c\u0010j\u001a\u00020-2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120dH\u0007J\u001c\u0010k\u001a\u00020-2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0dH\u0007J \u0010l\u001a\u00020-2\u0006\u0010m\u001a\u00020n2\u0006\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020rH\u0016J\u001c\u0010s\u001a\u00020-2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0dH\u0007J\u001c\u0010t\u001a\u00020-2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0dH\u0007R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\tX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0006j\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f`\tX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u0006j\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f`\tX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u0010\u001a\u001e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0006j\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u0012`\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0006j\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0015`\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\u001f\u001a\u001e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u0006j\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!`\tX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010\"\u001a\u001e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0\u0006j\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$`\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010&\u001a\u001e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(0\u0006j\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020(`\tX\u0082\u000e¢\u0006\u0002\n\u0000R*\u0010)\u001a\u001e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+0\u0006j\u000e\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020+`\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006v"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/VoxAlexaConnection;", "Lcom/amazon/alexa/accessory/speechapi/AlexaConnection;", "connection", "Lcom/amazon/alexa/api/AlexaServicesConnection;", "(Lcom/amazon/alexa/api/AlexaServicesConnection;)V", "alertsListenerMap", "Ljava/util/HashMap;", "Lcom/amazon/alexa/accessory/speechapi/listeners/AlertsListener;", "Lcom/amazon/alexa/api/compat/alerts/AlertsListener;", "Lkotlin/collections/HashMap;", "audioPlaybackStatusListenerMap", "Lcom/amazon/alexa/accessory/speechapi/listeners/AudioPlaybackStatusListener;", "Lcom/amazon/alexa/api/compat/AlexaAudioPlaybackStatusListener;", "captionListenerMap", "Lcom/amazon/alexa/accessory/speechapi/listeners/CaptionListener;", "Lcom/amazon/alexa/api/AlexaCaptionListener;", "cardListenerMap", "Lcom/amazon/alexa/accessory/speechapi/listeners/CardListener;", "Lcom/amazon/alexa/api/AlexaCardListener;", "connectionListenerMap", "Lcom/amazon/alexa/accessory/speechapi/listeners/ConnectionListener;", "Lcom/amazon/alexa/api/AlexaServicesConnection$ConnectionListener;", "contextProviderLock", "", JoinPoint.SYNCHRONIZATION_LOCK, "mainThreadHandler", "Landroid/os/Handler;", "messageContextProviderMap", "", "Lcom/amazon/alexa/accessory/speechapi/context/MessageContextProvider;", "Lcom/amazon/alexa/accessory/speechapi/voicesdk/context/VoxAlexaContextProvider;", "readinessListenerMap", "Lcom/amazon/alexa/accessory/speechapi/listeners/ReadinessListener;", "Lcom/amazon/alexa/api/compat/AlexaReadinessListener;", "settingsListenerMap", "Lcom/amazon/alexa/accessory/speechapi/listeners/SettingsListener;", "Lcom/amazon/alexa/api/AlexaSettingsListener;", "speechProviderLock", "speechProviderMap", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProvider;", "Lcom/amazon/alexa/api/compat/AlexaUserSpeechProvider;", "stateListenerMap", "Lcom/amazon/alexa/accessory/speechapi/listeners/StateListener;", "Lcom/amazon/alexa/api/compat/AlexaStateListener;", "bindAmbientSoundDispatcher", "", "ambientSoundDispatcher", "Lcom/amazon/alexa/accessory/speechapi/AmbientSoundDispatcher;", "cancelUserInteraction", EmulateConnection.EXTRA_CONNECT, "createAccessoryAudioSink", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "createAccessoryDataSink", "deRegisterUserSpeechProvider", "userSpeechProvider", "deregisterAlertsListener", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "deregisterAudioPlaybackStatusListener", "deregisterCaptionListener", "deregisterCardListener", "deregisterConnectionListener", "deregisterMessageContextProvider", "messageContextProvider", "deregisterReadinessListener", "deregisterSettingsListener", "deregisterStateListener", Metrics.DISCONNECT, "getConnectionFailedReason", "Lcom/amazon/alexa/accessory/speechapi/listeners/ConnectionListener$ConnectingFailedReason;", "connectionFailedReason", "Lcom/amazon/alexa/api/AlexaConnectingFailedReason;", "getLocale", "Ljava/util/Locale;", "isConnected", "", "isConnecting", "recordAlexaConnectionFailedReason", "metricName", "", "registerAlertsListener", "registerAudioPlaybackStatusListener", "registerCaptionListener", "registerCardListener", "registerConnectionListener", "registerMessageContextProvider", "registerReadinessListener", "registerSettingsListener", "registerStateListener", "registerUserSpeechProvider", "metadata", "Lcom/amazon/alexa/accessory/speechapi/speech/UserSpeechProviderMetadata;", "requestDialog", "dialogRequest", "Lcom/amazon/alexa/accessory/speechapi/speech/DialogRequest;", "sendEvent", "event", "Lcom/amazon/alexa/accessory/speechapi/events/MessageEvent;", "apiCallbacks", "Lcom/amazon/alexa/accessory/speechapi/events/ApiCallbacks;", "setAlertsListenerMap", "", "setAllowsBackgroundActivityStarts", "allowsBackgroundActivityStarts", "setAudioPlaybackStatusListenerMap", "playbackStatusListenerMap", "setCaptionListenerMap", "setCardListenerMap", "setReadinessListenerMap", "setReportAccessoryStateDependencies", "reportGenerator", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryStateReportGenerator;", "accessoryEventSender", "Lcom/amazon/alexa/accessory/speechapi/events/AccessoryEventSender;", "featureChecker", "Lcom/amazon/alexa/accessory/utils/feature/FeatureChecker;", "setSettingsListenerMap", "setStateListenerMap", "Companion", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoxAlexaConnection implements AlexaConnection {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "VoxAlexaConnection:";
    private HashMap<AlertsListener, com.amazon.alexa.api.compat.alerts.AlertsListener> alertsListenerMap;
    private HashMap<AudioPlaybackStatusListener, AlexaAudioPlaybackStatusListener> audioPlaybackStatusListenerMap;
    private HashMap<CaptionListener, AlexaCaptionListener> captionListenerMap;
    private HashMap<CardListener, AlexaCardListener> cardListenerMap;
    private final AlexaServicesConnection connection;
    private HashMap<ConnectionListener, AlexaServicesConnection.ConnectionListener> connectionListenerMap;
    private final Object contextProviderLock;
    private final Object lock;
    private final Handler mainThreadHandler;
    private Map<MessageContextProvider, VoxAlexaContextProvider> messageContextProviderMap;
    private HashMap<ReadinessListener, AlexaReadinessListener> readinessListenerMap;
    private HashMap<SettingsListener, AlexaSettingsListener> settingsListenerMap;
    private final Object speechProviderLock;
    private HashMap<UserSpeechProvider, AlexaUserSpeechProvider> speechProviderMap;
    private HashMap<StateListener, AlexaStateListener> stateListenerMap;

    /* compiled from: VoxAlexaConnection.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/VoxAlexaConnection$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[AlexaConnectingFailedReason.values().length];

        static {
            $EnumSwitchMapping$0[AlexaConnectingFailedReason.UNKNOWN.ordinal()] = 1;
            $EnumSwitchMapping$0[AlexaConnectingFailedReason.TIMEOUT.ordinal()] = 2;
            $EnumSwitchMapping$0[AlexaConnectingFailedReason.NO_ALEXA_SERVICES_TO_CONNECT_TO.ordinal()] = 3;
            $EnumSwitchMapping$0[AlexaConnectingFailedReason.NO_ALEXA_SERVICES_ACCOUNT_REGISTERED.ordinal()] = 4;
            $EnumSwitchMapping$0[AlexaConnectingFailedReason.ALEXA_SERVICES_DISABLED.ordinal()] = 5;
            $EnumSwitchMapping$0[AlexaConnectingFailedReason.CONNECTION_OBJECT_RELEASED.ordinal()] = 6;
            $EnumSwitchMapping$0[AlexaConnectingFailedReason.UNAUTHORIZED.ordinal()] = 7;
        }
    }

    public VoxAlexaConnection(@NotNull AlexaServicesConnection connection) {
        Intrinsics.checkParameterIsNotNull(connection, "connection");
        this.connection = connection;
        this.mainThreadHandler = new Handler(Looper.getMainLooper());
        this.lock = new Object();
        this.stateListenerMap = new HashMap<>();
        this.alertsListenerMap = new HashMap<>();
        this.settingsListenerMap = new HashMap<>();
        this.cardListenerMap = new HashMap<>();
        this.captionListenerMap = new HashMap<>();
        this.readinessListenerMap = new HashMap<>();
        this.contextProviderLock = new Object();
        this.speechProviderLock = new Object();
        this.messageContextProviderMap = new HashMap();
        this.connectionListenerMap = new HashMap<>();
        this.speechProviderMap = new HashMap<>();
        this.audioPlaybackStatusListenerMap = new HashMap<>();
        this.connection.setAllowsBackgroundActivityStarts(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ConnectionListener.ConnectingFailedReason getConnectionFailedReason(AlexaConnectingFailedReason alexaConnectingFailedReason) {
        switch (WhenMappings.$EnumSwitchMapping$0[alexaConnectingFailedReason.ordinal()]) {
            case 1:
                return ConnectionListener.ConnectingFailedReason.UNKNOWN;
            case 2:
                return ConnectionListener.ConnectingFailedReason.TIMEOUT;
            case 3:
                return ConnectionListener.ConnectingFailedReason.NO_ALEXA_SERVICES_TO_CONNECT_TO;
            case 4:
                return ConnectionListener.ConnectingFailedReason.NO_ALEXA_SERVICES_ACCOUNT_REGISTERED;
            case 5:
                return ConnectionListener.ConnectingFailedReason.ALEXA_SERVICES_DISABLED;
            case 6:
                return ConnectionListener.ConnectingFailedReason.CONNECTION_OBJECT_RELEASED;
            case 7:
                return ConnectionListener.ConnectingFailedReason.UNAUTHORIZED;
            default:
                return ConnectionListener.ConnectingFailedReason.UNKNOWN;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void recordAlexaConnectionFailedReason(String str) {
        AccessoryMetricsServiceHolder.getInstance().get().recordCounter(str, "alexa_accessories", 1.0d, null);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void bindAmbientSoundDispatcher(@NotNull AmbientSoundDispatcher ambientSoundDispatcher) {
        Intrinsics.checkParameterIsNotNull(ambientSoundDispatcher, "ambientSoundDispatcher");
        Logger.d("VoxAlexaConnection: vox bindAmbientSoundDispatcher");
        VoxAmbientSoundDispatcherInstance.INSTANCE.bind(ambientSoundDispatcher);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void cancelUserInteraction() {
        AlexaServices.Recognizer.cancelUserInteraction(this.connection);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void connect() {
        this.connection.connect();
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    @NotNull
    public AccessorySink createAccessoryAudioSink() {
        return new VoxAudioSink(null, 1, null);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    @NotNull
    public AccessorySink createAccessoryDataSink() {
        return new VoxDataSink(null, 1, null);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deRegisterUserSpeechProvider(@NotNull UserSpeechProvider userSpeechProvider) {
        Intrinsics.checkParameterIsNotNull(userSpeechProvider, "userSpeechProvider");
        synchronized (this.speechProviderLock) {
            AlexaUserSpeechProvider remove = this.speechProviderMap.remove(userSpeechProvider);
            if (remove != null) {
                AlexaServicesApis.UserSpeechProviders.deregister(this.connection, remove);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterAlertsListener(@NotNull AlertsListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.lock) {
            com.amazon.alexa.api.compat.alerts.AlertsListener remove = this.alertsListenerMap.remove(listener);
            if (remove != null) {
                AlexaServices.Alerts.deregisterListener(this.connection, remove);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterAudioPlaybackStatusListener(@NotNull AudioPlaybackStatusListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.lock) {
            AlexaAudioPlaybackStatusListener it2 = this.audioPlaybackStatusListenerMap.remove(listener);
            if (it2 != null) {
                AlexaServicesConnection alexaServicesConnection = this.connection;
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                AlexaServicesApis.AudioPlaybackControl.deregister(alexaServicesConnection, it2);
                Logger.d("VoxAlexaConnection: deregistered AlexaAudioPlaybackStatusListener");
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterCaptionListener(@NotNull CaptionListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.lock) {
            AlexaCaptionListener remove = this.captionListenerMap.remove(listener);
            if (remove != null) {
                AlexaServicesApis.Caption.deregisterCaptionListener(this.connection, remove);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterCardListener(@NotNull CardListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.lock) {
            AlexaCardListener it2 = this.cardListenerMap.remove(listener);
            if (it2 != null) {
                AlexaServicesConnection alexaServicesConnection = this.connection;
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                AlexaServices.Cards.deregisterAlexaCardRendererListener(alexaServicesConnection, it2);
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterConnectionListener(@NotNull ConnectionListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.lock) {
            AlexaServicesConnection.ConnectionListener remove = this.connectionListenerMap.remove(listener);
            if (remove != null) {
                this.connection.deregisterListener(remove);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterMessageContextProvider(@NotNull MessageContextProvider messageContextProvider) {
        Intrinsics.checkParameterIsNotNull(messageContextProvider, "messageContextProvider");
        synchronized (this.contextProviderLock) {
            VoxAlexaContextProvider remove = this.messageContextProviderMap.remove(messageContextProvider);
            if (remove != null) {
                AlexaServices.ContextProvider.deregister(this.connection, remove);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterReadinessListener(@NotNull ReadinessListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.lock) {
            AlexaReadinessListener remove = this.readinessListenerMap.remove(listener);
            if (remove != null) {
                AlexaServices.Readiness.deregister(this.connection, remove);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterSettingsListener(@NotNull SettingsListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.lock) {
            AlexaSettingsListener remove = this.settingsListenerMap.remove(listener);
            if (remove != null) {
                AlexaServices.Settings.deregisterListener(this.connection, remove);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void deregisterStateListener(@NotNull StateListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        synchronized (this.lock) {
            AlexaStateListener remove = this.stateListenerMap.remove(listener);
            if (remove != null) {
                AlexaServices.Recognizer.deregisterListener(this.connection, remove);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void disconnect() {
        this.connection.disconnect();
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    @Nullable
    public Locale getLocale() {
        return AlexaServices.Settings.getLocale(this.connection);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public boolean isConnected() {
        return this.connection.isConnected();
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public boolean isConnecting() {
        return this.connection.isConnecting();
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerAlertsListener(@NotNull final AlertsListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        com.amazon.alexa.api.compat.alerts.AlertsListener alertsListener = new com.amazon.alexa.api.compat.alerts.AlertsListener() { // from class: com.amazon.alexa.accessory.speechapi.voicesdk.VoxAlexaConnection$registerAlertsListener$alertsListener$1
            @Override // com.amazon.alexa.api.compat.alerts.AlertsListener
            public void onAlertFinished(@Nullable String str, @Nullable AlertType alertType) {
                AlertsListener alertsListener2 = AlertsListener.this;
                if (str == null) {
                    Intrinsics.throwNpe();
                }
                if (alertType == null) {
                    Intrinsics.throwNpe();
                }
                alertsListener2.onAlertFinished(str, AlertsListener.AlertType.valueOf(alertType.name()));
            }

            @Override // com.amazon.alexa.api.compat.alerts.AlertsListener
            public void onAlertStarted(@Nullable String str, @Nullable AlertType alertType) {
                AlertsListener alertsListener2 = AlertsListener.this;
                if (str == null) {
                    Intrinsics.throwNpe();
                }
                if (alertType == null) {
                    Intrinsics.throwNpe();
                }
                alertsListener2.onAlertStarted(str, AlertsListener.AlertType.valueOf(alertType.name()));
            }
        };
        synchronized (this.lock) {
            if (this.alertsListenerMap.containsKey(listener)) {
                return;
            }
            AlexaServices.Alerts.registerListener(this.connection, alertsListener);
            this.alertsListenerMap.put(listener, alertsListener);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerAudioPlaybackStatusListener(@NotNull final AudioPlaybackStatusListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        AlexaAudioPlaybackStatusListener alexaAudioPlaybackStatusListener = new AlexaAudioPlaybackStatusListener() { // from class: com.amazon.alexa.accessory.speechapi.voicesdk.VoxAlexaConnection$registerAudioPlaybackStatusListener$alexaAudioPlaybackStatusListener$1
            @Override // com.amazon.alexa.api.compat.AlexaAudioPlaybackStatusListener
            public void onAudioPlaybackStatusChanged(@NotNull Map<AlexaAudioChannel, Boolean> audioPlaybackStatus) {
                Intrinsics.checkParameterIsNotNull(audioPlaybackStatus, "audioPlaybackStatus");
                HashMap hashMap = new HashMap();
                ArrayList arrayList = new ArrayList(audioPlaybackStatus.size());
                for (Map.Entry<AlexaAudioChannel, Boolean> entry : audioPlaybackStatus.entrySet()) {
                    arrayList.add((Boolean) hashMap.put(SpeechApiModelMapper.from(entry.getKey()), entry.getValue()));
                }
                AudioPlaybackStatusListener.this.onAudioPlaybackStatusChanged(hashMap);
            }
        };
        synchronized (this.lock) {
            if (this.audioPlaybackStatusListenerMap.containsKey(listener)) {
                Logger.d("VoxAlexaConnection: AlexaAudioPlaybackStatusListener already registered. Do nothing");
                return;
            }
            AlexaServicesApis.AudioPlaybackControl.register(this.connection, alexaAudioPlaybackStatusListener);
            Logger.d("VoxAlexaConnection: registered AlexaAudioPlaybackStatusListener");
            this.audioPlaybackStatusListenerMap.put(listener, alexaAudioPlaybackStatusListener);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerCaptionListener(@NotNull final CaptionListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        AlexaCaptionListener alexaCaptionListener = new AlexaCaptionListener() { // from class: com.amazon.alexa.accessory.speechapi.voicesdk.VoxAlexaConnection$registerCaptionListener$alexaCaptionListener$1
            @Override // com.amazon.alexa.api.AlexaCaptionListener
            public final void onReceivedCaption(CaptionResponse captionResponse) {
                CaptionListener captionListener = CaptionListener.this;
                Intrinsics.checkExpressionValueIsNotNull(captionResponse, "captionResponse");
                String content = captionResponse.getContent();
                Intrinsics.checkExpressionValueIsNotNull(content, "captionResponse.content");
                captionListener.onReceivedCaption(new Caption(content, CaptionFormat.valueOf(captionResponse.getType().name())));
            }
        };
        synchronized (this.lock) {
            if (this.captionListenerMap.containsKey(listener)) {
                return;
            }
            AlexaServicesApis.Caption.registerCaptionListener(this.connection, alexaCaptionListener);
            this.captionListenerMap.put(listener, alexaCaptionListener);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerCardListener(@NotNull final CardListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        AlexaCardListener alexaCardListener = new AlexaCardListener() { // from class: com.amazon.alexa.accessory.speechapi.voicesdk.VoxAlexaConnection$registerCardListener$alexaCardListener$1
            @Override // com.amazon.alexa.api.AlexaCardListener
            public final void onReceivedRenderCard(String cardData, AlexaCardExtras cardExtras) {
                CardListener cardListener = CardListener.this;
                Intrinsics.checkExpressionValueIsNotNull(cardData, "cardData");
                Intrinsics.checkExpressionValueIsNotNull(cardExtras, "cardExtras");
                cardListener.onReceivedRenderCard(cardData, new CardExtras(cardExtras.getMarketplace(), cardExtras.getLocale()));
            }
        };
        synchronized (this.lock) {
            if (this.cardListenerMap.containsKey(listener)) {
                return;
            }
            AlexaServices.Cards.registerAlexaCardRendererListener(this.connection, alexaCardListener);
            this.cardListenerMap.put(listener, alexaCardListener);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerConnectionListener(@NotNull final ConnectionListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        AlexaServicesConnection.ConnectionListener connectionListener = new AlexaServicesConnection.ConnectionListener() { // from class: com.amazon.alexa.accessory.speechapi.voicesdk.VoxAlexaConnection$registerConnectionListener$connectionListener$1
            @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
            public void onConnected() {
                listener.onConnected();
            }

            @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
            public void onConnectingFailed(@NotNull AlexaConnectingFailedReason alexaConnectingFailedReason, @NotNull String message) {
                ConnectionListener.ConnectingFailedReason connectionFailedReason;
                Intrinsics.checkParameterIsNotNull(alexaConnectingFailedReason, "alexaConnectingFailedReason");
                Intrinsics.checkParameterIsNotNull(message, "message");
                VoxAlexaConnection voxAlexaConnection = VoxAlexaConnection.this;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("VoxAlexaConnectionFailedReason:");
                outline107.append(alexaConnectingFailedReason.name());
                voxAlexaConnection.recordAlexaConnectionFailedReason(outline107.toString());
                ConnectionListener connectionListener2 = listener;
                connectionFailedReason = VoxAlexaConnection.this.getConnectionFailedReason(alexaConnectingFailedReason);
                connectionListener2.onConnectingFailed(connectionFailedReason, message);
            }

            @Override // com.amazon.alexa.api.ConnectionListenerLifecycles.ConnectionListener
            public void onDisconnected() {
                listener.onDisconnected();
            }
        };
        synchronized (this.lock) {
            if (this.connectionListenerMap.containsKey(listener)) {
                return;
            }
            this.connection.registerListener(this.mainThreadHandler, connectionListener);
            this.connectionListenerMap.put(listener, connectionListener);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerMessageContextProvider(@NotNull MessageContextProvider messageContextProvider) {
        Intrinsics.checkParameterIsNotNull(messageContextProvider, "messageContextProvider");
        VoxAlexaContextProvider voxAlexaContextProvider = new VoxAlexaContextProvider(messageContextProvider);
        AlexaServices.ContextProvider.register(this.connection, voxAlexaContextProvider);
        synchronized (this.contextProviderLock) {
            if (this.messageContextProviderMap.containsKey(messageContextProvider)) {
                return;
            }
            this.messageContextProviderMap.put(messageContextProvider, voxAlexaContextProvider);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerReadinessListener(@NotNull final ReadinessListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        AlexaReadinessListener alexaReadinessListener = new AlexaReadinessListener() { // from class: com.amazon.alexa.accessory.speechapi.voicesdk.VoxAlexaConnection$registerReadinessListener$alexaReadinessListener$1
            @Override // com.amazon.alexa.api.compat.AlexaReadinessListener
            public void onReadinessChanged(@Nullable AlexaReadyState alexaReadyState) {
                ReadinessListener readinessListener = ReadinessListener.this;
                if (alexaReadyState == null) {
                    Intrinsics.throwNpe();
                }
                readinessListener.onReadinessChanged(SpeechApiModelMapper.from(alexaReadyState));
            }
        };
        synchronized (this.lock) {
            if (this.readinessListenerMap.containsKey(listener)) {
                return;
            }
            AlexaServices.Readiness.register(this.connection, alexaReadinessListener);
            this.readinessListenerMap.put(listener, alexaReadinessListener);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerSettingsListener(@NotNull final SettingsListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        AlexaSettingsListener alexaSettingsListener = new AlexaSettingsListener() { // from class: com.amazon.alexa.accessory.speechapi.voicesdk.VoxAlexaConnection$registerSettingsListener$alexaSettingsListener$1
            @Override // com.amazon.alexa.api.AlexaSettingsListener
            public final void onLocaleChanged(Locale locale) {
                SettingsListener settingsListener = SettingsListener.this;
                Intrinsics.checkExpressionValueIsNotNull(locale, "locale");
                settingsListener.onLocaleChanged(locale);
            }
        };
        synchronized (this.lock) {
            if (this.settingsListenerMap.containsKey(listener)) {
                return;
            }
            AlexaServices.Settings.registerListener(this.connection, alexaSettingsListener);
            this.settingsListenerMap.put(listener, alexaSettingsListener);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerStateListener(@NotNull final StateListener listener) {
        Intrinsics.checkParameterIsNotNull(listener, "listener");
        AlexaStateListener alexaStateListener = new AlexaStateListener() { // from class: com.amazon.alexa.accessory.speechapi.voicesdk.VoxAlexaConnection$registerStateListener$alexaStateListener$1
            @Override // com.amazon.alexa.api.compat.AlexaStateListener
            public void onAlexaStateChanged(@Nullable AlexaState alexaState) {
                StateListener stateListener = StateListener.this;
                if (alexaState == null) {
                    Intrinsics.throwNpe();
                }
                stateListener.onAlexaStateChanged(StateListener.AlexaState.valueOf(alexaState.name()));
            }
        };
        synchronized (this.lock) {
            if (this.stateListenerMap.containsKey(listener)) {
                return;
            }
            AlexaServices.Recognizer.registerListener(this.connection, alexaStateListener);
            this.stateListenerMap.put(listener, alexaStateListener);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void registerUserSpeechProvider(@NotNull UserSpeechProvider userSpeechProvider, @NotNull UserSpeechProviderMetadata metadata) {
        Intrinsics.checkParameterIsNotNull(userSpeechProvider, "userSpeechProvider");
        Intrinsics.checkParameterIsNotNull(metadata, "metadata");
        VoxUserSpeechProvider voxUserSpeechProvider = new VoxUserSpeechProvider(userSpeechProvider);
        synchronized (this.speechProviderLock) {
            if (this.speechProviderMap.containsKey(userSpeechProvider)) {
                return;
            }
            AlexaServicesApis.UserSpeechProviders.register(this.connection, voxUserSpeechProvider, SpeechApiModelMapper.from(metadata));
            this.speechProviderMap.put(userSpeechProvider, voxUserSpeechProvider);
        }
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void requestDialog(@NotNull UserSpeechProvider userSpeechProvider, @NotNull DialogRequest dialogRequest) {
        Intrinsics.checkParameterIsNotNull(userSpeechProvider, "userSpeechProvider");
        Intrinsics.checkParameterIsNotNull(dialogRequest, "dialogRequest");
        AlexaUserSpeechProvider alexaUserSpeechProvider = this.speechProviderMap.get(userSpeechProvider);
        if (alexaUserSpeechProvider == null) {
            return;
        }
        AlexaDialogRequest alexaDialogRequest = AlexaDialogRequest.builder().setInvocationType(dialogRequest.getInvocationType()).build();
        AlexaServicesConnection alexaServicesConnection = this.connection;
        Intrinsics.checkExpressionValueIsNotNull(alexaDialogRequest, "alexaDialogRequest");
        AlexaServicesApis.UserSpeechRecognizer.requestDialog(alexaServicesConnection, alexaUserSpeechProvider, alexaDialogRequest);
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void sendEvent(@NotNull MessageEvent event, @NotNull final ApiCallbacks apiCallbacks) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(apiCallbacks, "apiCallbacks");
        AlexaApiCallbacks alexaApiCallbacks = new AlexaApiCallbacks() { // from class: com.amazon.alexa.accessory.speechapi.voicesdk.VoxAlexaConnection$sendEvent$alexaApiCallbacks$1
            @Override // com.amazon.alexa.api.AlexaApiCallbacks
            public void onFailure(@NotNull ApiCallFailure apiCallFailure) {
                Intrinsics.checkParameterIsNotNull(apiCallFailure, "apiCallFailure");
                ApiCallbacks.this.onFailure(apiCallFailure.getException());
            }

            @Override // com.amazon.alexa.api.AlexaApiCallbacks
            public void onSuccess() {
                ApiCallbacks.this.onSuccess();
            }
        };
        AlexaServices.EventSender.send(this.connection, VoxAlexaEventKt.toAlexaEvent(event), false, alexaApiCallbacks);
    }

    @VisibleForTesting
    public final void setAlertsListenerMap(@NotNull Map<AlertsListener, ? extends com.amazon.alexa.api.compat.alerts.AlertsListener> alertsListenerMap) {
        Intrinsics.checkParameterIsNotNull(alertsListenerMap, "alertsListenerMap");
        this.alertsListenerMap = (HashMap) alertsListenerMap;
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void setAllowsBackgroundActivityStarts(boolean z) {
        this.connection.setAllowsBackgroundActivityStarts(z);
    }

    @VisibleForTesting
    public final void setAudioPlaybackStatusListenerMap(@NotNull HashMap<AudioPlaybackStatusListener, AlexaAudioPlaybackStatusListener> playbackStatusListenerMap) {
        Intrinsics.checkParameterIsNotNull(playbackStatusListenerMap, "playbackStatusListenerMap");
        this.audioPlaybackStatusListenerMap = playbackStatusListenerMap;
    }

    @VisibleForTesting
    public final void setCaptionListenerMap(@NotNull Map<CaptionListener, ? extends AlexaCaptionListener> captionListenerMap) {
        Intrinsics.checkParameterIsNotNull(captionListenerMap, "captionListenerMap");
        this.captionListenerMap = (HashMap) captionListenerMap;
    }

    @VisibleForTesting
    public final void setCardListenerMap(@NotNull Map<CardListener, ? extends AlexaCardListener> cardListenerMap) {
        Intrinsics.checkParameterIsNotNull(cardListenerMap, "cardListenerMap");
        this.cardListenerMap = (HashMap) cardListenerMap;
    }

    @VisibleForTesting
    public final void setReadinessListenerMap(@NotNull Map<ReadinessListener, ? extends AlexaReadinessListener> readinessListenerMap) {
        Intrinsics.checkParameterIsNotNull(readinessListenerMap, "readinessListenerMap");
        this.readinessListenerMap = (HashMap) readinessListenerMap;
    }

    @Override // com.amazon.alexa.accessory.speechapi.AlexaConnection
    public void setReportAccessoryStateDependencies(@NotNull AccessoryStateReportGenerator reportGenerator, @NotNull AccessoryEventSender accessoryEventSender, @NotNull FeatureChecker featureChecker) {
        Intrinsics.checkParameterIsNotNull(reportGenerator, "reportGenerator");
        Intrinsics.checkParameterIsNotNull(accessoryEventSender, "accessoryEventSender");
        Intrinsics.checkParameterIsNotNull(featureChecker, "featureChecker");
        VoxReportAccessoryStateDependencyHolder.INSTANCE.init(reportGenerator, accessoryEventSender, featureChecker);
    }

    @VisibleForTesting
    public final void setSettingsListenerMap(@NotNull Map<SettingsListener, ? extends AlexaSettingsListener> settingsListenerMap) {
        Intrinsics.checkParameterIsNotNull(settingsListenerMap, "settingsListenerMap");
        this.settingsListenerMap = (HashMap) settingsListenerMap;
    }

    @VisibleForTesting
    public final void setStateListenerMap(@NotNull Map<StateListener, ? extends AlexaStateListener> stateListenerMap) {
        Intrinsics.checkParameterIsNotNull(stateListenerMap, "stateListenerMap");
        this.stateListenerMap = (HashMap) stateListenerMap;
    }
}
