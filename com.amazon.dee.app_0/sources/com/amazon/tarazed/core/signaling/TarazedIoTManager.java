package com.amazon.tarazed.core.signaling;

import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.fitness.metrics.MetricsOperation;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.amazon.tarazed.core.logging.TarazedSessionLogger;
import com.amazon.tarazed.core.metrics.TarazedMetricsHelper;
import com.amazon.tarazed.core.notifier.TarazedSessionNotifier;
import com.amazon.tarazed.core.sessionclient.model.createcredentials.SignalingCredentials;
import com.amazon.tarazed.core.signaling.events.EmptySerializable;
import com.amazon.tarazed.core.signaling.events.TarazedEvent;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.internal.StaticCredentialsProvider;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttLastWillAndTestament;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttManager;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback;
import com.amazonaws.mobileconnectors.iot.AWSIotMqttQos;
import com.amazonaws.org.eclipse.paho.client.mqttv3.MqttException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.json.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TarazedIoTManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 42\u00020\u0001:\u00014B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ#\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010 \u001a\u00020\fH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0006\u0010\"\u001a\u00020\fJ\u0014\u0010#\u001a\u00060$j\u0002`%2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u0019\u0010&\u001a\u00060\u0018j\u0002`\u00192\u0006\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b'J\r\u0010(\u001a\u00020\u001bH\u0001¢\u0006\u0002\b)J\u0006\u0010*\u001a\u00020\u001fJ\u001c\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020-2\f\b\u0002\u0010.\u001a\u00060/j\u0002`0J\u000e\u00101\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u00102\u001a\u00020\u001fH\u0002J\b\u00103\u001a\u00020\u001fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\f8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0013\u001a\u00020\f8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\u000e\u001a\u0004\b\u0015\u0010\u0010\"\u0004\b\u0016\u0010\u0012R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n\u0018\u00010\u0018j\u0004\u0018\u0001`\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u00065"}, d2 = {"Lcom/amazon/tarazed/core/signaling/TarazedIoTManager;", "", "eventDispatcher", "Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;", "logger", "Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;", "metricsHelper", "Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;", "sessionNotifier", "Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;", "(Lcom/amazon/tarazed/core/signaling/TarazedEventDispatcher;Lcom/amazon/tarazed/core/logging/TarazedSessionLogger;Lcom/amazon/tarazed/core/metrics/TarazedMetricsHelper;Lcom/amazon/tarazed/core/notifier/TarazedSessionNotifier;)V", "initialConnectionSucceeded", "", "initialConnectionSucceeded$annotations", "()V", "getInitialConnectionSucceeded$TarazedMobileCore_release", "()Z", "setInitialConnectionSucceeded$TarazedMobileCore_release", "(Z)V", "lwtEventHandlerSet", "lwtEventHandlerSet$annotations", "getLwtEventHandlerSet$TarazedMobileCore_release", "setLwtEventHandlerSet$TarazedMobileCore_release", "mqttManager", "Lcom/amazonaws/mobileconnectors/iot/AWSIotMqttManager;", "Lcom/amazon/tarazed/core/signaling/api/AWSIotMqttManager;", "reconnectTimer", "Lkotlinx/coroutines/Job;", "signalingCredentials", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/SignalingCredentials;", EmulateConnection.EXTRA_CONNECT, "", "setLwt", "(Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/SignalingCredentials;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", Metrics.DISCONNECT, "getCredentialsProvider", "Lcom/amazonaws/internal/StaticCredentialsProvider;", "Lcom/amazon/tarazed/core/signaling/api/StaticCredentialsProvider;", "initIotMqtt", "initIotMqtt$TarazedMobileCore_release", "initReconnectTimer", "initReconnectTimer$TarazedMobileCore_release", "pingBrowser", "sendEvent", "event", "", "qos", "Lcom/amazonaws/mobileconnectors/iot/AWSIotMqttQos;", "Lcom/amazon/tarazed/core/signaling/api/AWSIotMqttQos;", "setCredentials", "setLWT", "subscribe", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class TarazedIoTManager {
    private static final String EVENT_TYPE_LWT_DISCONNECT = "lwtDisconnect";
    private static final String EVENT_TYPE_PING = "ping";
    private static final String METRIC_CONNECT_PREFIX = "Connect";
    private static final String METRIC_DESERIALIZATION_FAILED = "DeserializationFailed";
    private static final String METRIC_IOT_MESSAGE_SEND_FAILED = "IotMessageSendFailed";
    private static final String METRIC_IOT_MESSAGE_SENT = "IotMessageSent";
    private static final String METRIC_MQTT_CONNECTION_ATTEMPTED = "MqttConnectionAttempted";
    private static final String METRIC_MQTT_CONNECTION_FAILED = "MqttConnectionFailed";
    private static final String METRIC_MQTT_CONNECTION_LATENCY = "MqttConnectionLatency";
    private static final String METRIC_MQTT_CONNECT_NETWORK_EXCEPTION = "MqttConnectNetworkException";
    private static final String METRIC_RECONNECT_TIMEOUT = "ReconnectTimeout";
    private static final String TAG = "TarazedIoTManager";
    private final TarazedEventDispatcher eventDispatcher;
    private boolean initialConnectionSucceeded;
    private final TarazedSessionLogger logger;
    private boolean lwtEventHandlerSet;
    private final TarazedMetricsHelper metricsHelper;
    private AWSIotMqttManager mqttManager;
    private Job reconnectTimer;
    private final TarazedSessionNotifier sessionNotifier;
    private SignalingCredentials signalingCredentials;
    public static final Companion Companion = new Companion(null);
    private static long MAX_RECONNECT_WAIT_MS = 30000;

    /* compiled from: TarazedIoTManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0010\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R$\u0010\u0006\u001a\u00020\u00078\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\b\u0010\u0002\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/tarazed/core/signaling/TarazedIoTManager$Companion;", "", "()V", "EVENT_TYPE_LWT_DISCONNECT", "", "EVENT_TYPE_PING", "MAX_RECONNECT_WAIT_MS", "", "MAX_RECONNECT_WAIT_MS$annotations", "getMAX_RECONNECT_WAIT_MS$TarazedMobileCore_release", "()J", "setMAX_RECONNECT_WAIT_MS$TarazedMobileCore_release", "(J)V", "METRIC_CONNECT_PREFIX", "METRIC_DESERIALIZATION_FAILED", "METRIC_IOT_MESSAGE_SEND_FAILED", "METRIC_IOT_MESSAGE_SENT", "METRIC_MQTT_CONNECTION_ATTEMPTED", "METRIC_MQTT_CONNECTION_FAILED", "METRIC_MQTT_CONNECTION_LATENCY", "METRIC_MQTT_CONNECT_NETWORK_EXCEPTION", "METRIC_RECONNECT_TIMEOUT", "TAG", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @VisibleForTesting
        public static /* synthetic */ void MAX_RECONNECT_WAIT_MS$annotations() {
        }

        public final long getMAX_RECONNECT_WAIT_MS$TarazedMobileCore_release() {
            return TarazedIoTManager.MAX_RECONNECT_WAIT_MS;
        }

        public final void setMAX_RECONNECT_WAIT_MS$TarazedMobileCore_release(long j) {
            TarazedIoTManager.MAX_RECONNECT_WAIT_MS = j;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TarazedIoTManager(@NotNull TarazedEventDispatcher eventDispatcher, @NotNull TarazedSessionLogger logger, @NotNull TarazedMetricsHelper metricsHelper, @NotNull TarazedSessionNotifier sessionNotifier) {
        Job launch$default;
        Intrinsics.checkParameterIsNotNull(eventDispatcher, "eventDispatcher");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(metricsHelper, "metricsHelper");
        Intrinsics.checkParameterIsNotNull(sessionNotifier, "sessionNotifier");
        this.eventDispatcher = eventDispatcher;
        this.logger = logger;
        this.metricsHelper = metricsHelper;
        this.sessionNotifier = sessionNotifier;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, CoroutineStart.LAZY, new TarazedIoTManager$reconnectTimer$1(null), 1, null);
        this.reconnectTimer = launch$default;
    }

    public static /* synthetic */ Object connect$default(TarazedIoTManager tarazedIoTManager, SignalingCredentials signalingCredentials, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return tarazedIoTManager.connect(signalingCredentials, z, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StaticCredentialsProvider getCredentialsProvider(SignalingCredentials signalingCredentials) {
        return new StaticCredentialsProvider(new BasicSessionCredentials(signalingCredentials.getAccessKey(), signalingCredentials.getSecretKey(), signalingCredentials.getSessionToken()));
    }

    @VisibleForTesting
    public static /* synthetic */ void initialConnectionSucceeded$annotations() {
    }

    @VisibleForTesting
    public static /* synthetic */ void lwtEventHandlerSet$annotations() {
    }

    public static /* synthetic */ void sendEvent$default(TarazedIoTManager tarazedIoTManager, String str, AWSIotMqttQos aWSIotMqttQos, int i, Object obj) {
        if ((i & 2) != 0) {
            aWSIotMqttQos = AWSIotMqttQos.QOS0;
        }
        tarazedIoTManager.sendEvent(str, aWSIotMqttQos);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setLWT() {
        this.logger.i(TAG, "Setting MQTT LWT");
        String stringify = Json.Default.stringify(TarazedEvent.Companion.serializer(EmptySerializable.Companion.serializer()), new TarazedEvent(EVENT_TYPE_LWT_DISCONNECT, new EmptySerializable()));
        SignalingCredentials signalingCredentials = this.signalingCredentials;
        AWSIotMqttLastWillAndTestament aWSIotMqttLastWillAndTestament = new AWSIotMqttLastWillAndTestament(signalingCredentials != null ? signalingCredentials.getPublishTopic() : null, stringify, AWSIotMqttQos.QOS0);
        AWSIotMqttManager aWSIotMqttManager = this.mqttManager;
        if (aWSIotMqttManager != null) {
            aWSIotMqttManager.setMqttLastWillAndTestament(aWSIotMqttLastWillAndTestament);
        }
        if (!this.lwtEventHandlerSet) {
            this.eventDispatcher.registerHandler(new TarazedBrowserLwtEventHandler(this, this.logger, this.metricsHelper, this.sessionNotifier));
            this.lwtEventHandlerSet = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void subscribe() {
        this.logger.i(TAG, "Subscribing to IoT topic");
        AWSIotMqttManager aWSIotMqttManager = this.mqttManager;
        if (aWSIotMqttManager != null) {
            SignalingCredentials signalingCredentials = this.signalingCredentials;
            aWSIotMqttManager.subscribeToTopic(signalingCredentials != null ? signalingCredentials.getSubscribeTopic() : null, AWSIotMqttQos.QOS0, new AWSIotMqttNewMessageCallback() { // from class: com.amazon.tarazed.core.signaling.TarazedIoTManager$subscribe$1
                @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttNewMessageCallback
                public final void onMessageArrived(String str, byte[] data) {
                    TarazedSessionLogger tarazedSessionLogger;
                    TarazedSessionLogger tarazedSessionLogger2;
                    TarazedEventDispatcher tarazedEventDispatcher;
                    Intrinsics.checkExpressionValueIsNotNull(data, "data");
                    String str2 = new String(data, Charsets.UTF_8);
                    tarazedSessionLogger = TarazedIoTManager.this.logger;
                    tarazedSessionLogger.d("TarazedIoTManager", "Received IoT event: " + str2);
                    try {
                        tarazedEventDispatcher = TarazedIoTManager.this.eventDispatcher;
                        tarazedEventDispatcher.dispatchEvent(str2);
                    } catch (SerializationException e) {
                        TarazedIoTManager.this.metricsHelper.addCount("TarazedIoTManager", "DeserializationFailed", 1.0d);
                        tarazedSessionLogger2 = TarazedIoTManager.this.logger;
                        tarazedSessionLogger2.e("TarazedIoTManager", "Failed to deserialize event: " + str2, e);
                    }
                }
            });
        }
    }

    @Nullable
    public final Object connect(@NotNull final SignalingCredentials signalingCredentials, final boolean z, @NotNull Continuation<? super Unit> continuation) {
        Continuation intercepted;
        Object coroutine_suspended;
        intercepted = IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation);
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(intercepted, 1);
        this.signalingCredentials = signalingCredentials;
        this.mqttManager = initIotMqtt$TarazedMobileCore_release(signalingCredentials);
        if (z) {
            setLWT();
        }
        StaticCredentialsProvider credentialsProvider = getCredentialsProvider(signalingCredentials);
        this.metricsHelper.addCount(TAG, METRIC_MQTT_CONNECTION_ATTEMPTED, 1.0d);
        this.metricsHelper.startMetricTimer(TAG, METRIC_MQTT_CONNECTION_LATENCY);
        AWSIotMqttManager aWSIotMqttManager = this.mqttManager;
        if (aWSIotMqttManager != null) {
            aWSIotMqttManager.connect(credentialsProvider, new AWSIotMqttClientStatusCallback() { // from class: com.amazon.tarazed.core.signaling.TarazedIoTManager$connect$$inlined$suspendCancellableCoroutine$lambda$1
                @Override // com.amazonaws.mobileconnectors.iot.AWSIotMqttClientStatusCallback
                /* renamed from: invoke */
                public final void onStatusChanged(AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus aWSIotMqttClientStatus, Throwable th) {
                    TarazedSessionLogger tarazedSessionLogger;
                    TarazedSessionLogger tarazedSessionLogger2;
                    TarazedSessionLogger tarazedSessionLogger3;
                    TarazedSessionLogger tarazedSessionLogger4;
                    TarazedSessionLogger tarazedSessionLogger5;
                    Job job;
                    TarazedSessionLogger tarazedSessionLogger6;
                    Job job2;
                    Job job3;
                    TarazedSessionLogger tarazedSessionLogger7;
                    tarazedSessionLogger = this.logger;
                    tarazedSessionLogger.i("TarazedIoTManager", "AWS IoT connection status changed: " + aWSIotMqttClientStatus);
                    if (this.getInitialConnectionSucceeded$TarazedMobileCore_release()) {
                        if (aWSIotMqttClientStatus == AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Reconnecting) {
                            job3 = this.reconnectTimer;
                            if (!job3.isActive()) {
                                tarazedSessionLogger7 = this.logger;
                                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IoT connection dropped, waiting ");
                                outline107.append(TarazedIoTManager.Companion.getMAX_RECONNECT_WAIT_MS$TarazedMobileCore_release());
                                outline107.append("ms before ending session");
                                tarazedSessionLogger7.i("TarazedIoTManager", outline107.toString());
                                TarazedIoTManager tarazedIoTManager = this;
                                tarazedIoTManager.reconnectTimer = tarazedIoTManager.initReconnectTimer$TarazedMobileCore_release();
                                return;
                            }
                        }
                        if (aWSIotMqttClientStatus != AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected) {
                            return;
                        }
                        job = this.reconnectTimer;
                        if (!job.isActive()) {
                            return;
                        }
                        tarazedSessionLogger6 = this.logger;
                        tarazedSessionLogger6.i("TarazedIoTManager", "IoT connection re-established");
                        job2 = this.reconnectTimer;
                        Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
                        AWSIotMqttManager aWSIotMqttManager2 = this.mqttManager;
                        if (aWSIotMqttManager2 == null) {
                            return;
                        }
                        aWSIotMqttManager2.resetReconnect();
                        return;
                    }
                    if (th != null) {
                        boolean z2 = false;
                        if (th instanceof MqttException) {
                            MqttException mqttException = (MqttException) th;
                            if ((((short) mqttException.getReasonCode()) == 0 && (th.getCause() instanceof UnknownHostException)) || ((short) mqttException.getReasonCode()) == 32103) {
                                z2 = true;
                            }
                        }
                        if (th instanceof IOException) {
                            tarazedSessionLogger5 = this.logger;
                            tarazedSessionLogger5.e("TarazedIoTManager", "Connection to MQTT failed due to network issue", th);
                            this.metricsHelper.addIoExceptionCount("TarazedIoTManager", MetricsOperation.CONNECT, (IOException) th, 1.0d);
                        } else if (z2) {
                            tarazedSessionLogger4 = this.logger;
                            tarazedSessionLogger4.e("TarazedIoTManager", "Connection to MQTT failed due to MQTT network error", th);
                            this.metricsHelper.addCountHighPriority("TarazedIoTManager", "MqttConnectNetworkException", 1.0d);
                        } else {
                            tarazedSessionLogger3 = this.logger;
                            tarazedSessionLogger3.e("TarazedIoTManager", "Connection to MQTT failed", th);
                            this.metricsHelper.addCountHighPriority("TarazedIoTManager", "MqttConnectionFailed", 1.0d);
                            CancellableContinuation cancellableContinuation = CancellableContinuation.this;
                            Result.Companion companion = Result.Companion;
                            cancellableContinuation.resumeWith(Result.m10378constructorimpl(ResultKt.createFailure(th)));
                            return;
                        }
                    }
                    if (aWSIotMqttClientStatus != AWSIotMqttClientStatusCallback.AWSIotMqttClientStatus.Connected) {
                        return;
                    }
                    tarazedSessionLogger2 = this.logger;
                    tarazedSessionLogger2.i("TarazedIoTManager", "Successfully connected to AWS IoT");
                    this.metricsHelper.stopMetricTimer("TarazedIoTManager", "MqttConnectionLatency");
                    this.setInitialConnectionSucceeded$TarazedMobileCore_release(true);
                    this.subscribe();
                    CancellableContinuation cancellableContinuation2 = CancellableContinuation.this;
                    Unit unit = Unit.INSTANCE;
                    Result.Companion companion2 = Result.Companion;
                    cancellableContinuation2.resumeWith(Result.m10378constructorimpl(unit));
                }
            });
        }
        Object result = cancellableContinuationImpl.getResult();
        coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (result == coroutine_suspended) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public final boolean disconnect() {
        this.logger.i(TAG, "Disconnecting from IoT");
        this.initialConnectionSucceeded = false;
        this.lwtEventHandlerSet = false;
        AWSIotMqttManager aWSIotMqttManager = this.mqttManager;
        if (aWSIotMqttManager != null) {
            return aWSIotMqttManager.disconnect();
        }
        return true;
    }

    public final boolean getInitialConnectionSucceeded$TarazedMobileCore_release() {
        return this.initialConnectionSucceeded;
    }

    public final boolean getLwtEventHandlerSet$TarazedMobileCore_release() {
        return this.lwtEventHandlerSet;
    }

    @VisibleForTesting
    @NotNull
    public final AWSIotMqttManager initIotMqtt$TarazedMobileCore_release(@NotNull SignalingCredentials signalingCredentials) {
        Intrinsics.checkParameterIsNotNull(signalingCredentials, "signalingCredentials");
        return new AWSIotMqttManager(signalingCredentials.getClientId(), signalingCredentials.getEndpoint());
    }

    @VisibleForTesting
    @NotNull
    public final Job initReconnectTimer$TarazedMobileCore_release() {
        Job launch$default;
        launch$default = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new TarazedIoTManager$initReconnectTimer$1(this, null), 3, null);
        return launch$default;
    }

    public final void pingBrowser() {
        sendEvent$default(this, Json.Default.stringify(TarazedEvent.Companion.serializer(EmptySerializable.Companion.serializer()), new TarazedEvent("ping", new EmptySerializable())), null, 2, null);
    }

    public final void sendEvent(@NotNull String event, @NotNull AWSIotMqttQos qos) {
        Intrinsics.checkParameterIsNotNull(event, "event");
        Intrinsics.checkParameterIsNotNull(qos, "qos");
        if (this.initialConnectionSucceeded) {
            TarazedSessionLogger tarazedSessionLogger = this.logger;
            tarazedSessionLogger.d(TAG, "Sending IoT event: " + event);
            AWSIotMqttManager aWSIotMqttManager = this.mqttManager;
            if (aWSIotMqttManager != null) {
                SignalingCredentials signalingCredentials = this.signalingCredentials;
                aWSIotMqttManager.publishString(event, signalingCredentials != null ? signalingCredentials.getPublishTopic() : null, qos);
            }
            this.metricsHelper.incrementMetricCounter(TAG, METRIC_IOT_MESSAGE_SENT, 1.0d);
            return;
        }
        TarazedSessionLogger tarazedSessionLogger2 = this.logger;
        tarazedSessionLogger2.d(TAG, "IoT is not connected, cannot send event: " + event);
        this.metricsHelper.addCountHighPriority(TAG, METRIC_IOT_MESSAGE_SEND_FAILED, 1.0d);
    }

    public final void setCredentials(@NotNull SignalingCredentials signalingCredentials) {
        Intrinsics.checkParameterIsNotNull(signalingCredentials, "signalingCredentials");
        StaticCredentialsProvider credentialsProvider = getCredentialsProvider(signalingCredentials);
        AWSIotMqttManager aWSIotMqttManager = this.mqttManager;
        if (aWSIotMqttManager != null) {
            aWSIotMqttManager.setCredentialsProvider(credentialsProvider);
        }
    }

    public final void setInitialConnectionSucceeded$TarazedMobileCore_release(boolean z) {
        this.initialConnectionSucceeded = z;
    }

    public final void setLwtEventHandlerSet$TarazedMobileCore_release(boolean z) {
        this.lwtEventHandlerSet = z;
    }
}
