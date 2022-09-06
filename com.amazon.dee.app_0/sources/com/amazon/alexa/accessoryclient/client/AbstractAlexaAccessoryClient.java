package com.amazon.alexa.accessoryclient.client;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import androidx.core.app.NotificationCompat;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsService;
import com.amazon.alexa.accessoryclient.common.connection.BundleSink;
import com.amazon.alexa.accessoryclient.common.connection.BundleSource;
import com.amazon.alexa.accessoryclient.common.connection.Constants;
import com.amazon.alexa.accessoryclient.common.connection.MessageSink;
import com.amazon.alexa.accessoryclient.common.connection.MessageSource;
import com.amazon.alexa.accessoryclient.common.connection.MessageSourceHandler;
import com.amazon.alexa.accessoryclient.common.util.Logger;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import com.amazon.alexa.accessoryclient.common.util.Preconditions;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.device.messaging.ADMRegistrationConstants;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.aspectj.lang.JoinPoint;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AbstractAlexaAccessoryClient.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\b&\u0018\u0000 H2\u00020\u0001:\tFGHIJKLMNB7\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\fJ\b\u0010.\u001a\u00020\u0012H\u0002J\b\u0010/\u001a\u000200H\u0002J\b\u00101\u001a\u000200H\u0002J\u0006\u00102\u001a\u000203J\u0014\u00102\u001a\u0002032\n\u00104\u001a\u000605j\u0002`6H\u0002J\b\u00107\u001a\u000203H\u0002J\u0014\u00108\u001a\u0002032\n\u00104\u001a\u000605j\u0002`6H\u0002J\u0012\u00109\u001a\u0002032\b\b\u0002\u0010:\u001a\u00020;H\u0007J\u0018\u0010<\u001a\u0002032\u0006\u0010=\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020?H\u0002J\b\u0010@\u001a\u000203H\u0002J\u0010\u0010A\u001a\u0002032\u0006\u0010B\u001a\u00020\u0007H\u0002J\b\u0010C\u001a\u000203H\u0002J\b\u0010D\u001a\u000203H\u0002J\b\u0010E\u001a\u00020\u0012H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0016\u001a\u00020\u0017X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u001f\u001a\u00020 X¤\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient;", "", "context", "Landroid/content/Context;", "accessoryMetricsService", "Lcom/amazon/alexa/accessory/metrics/AccessoryMetricsService;", "servicePkg", "", "serviceClassName", Constants.Keys.MAJOR_VERSION_KEY, "", Constants.Keys.MINOR_VERSION_KEY, "(Landroid/content/Context;Lcom/amazon/alexa/accessory/metrics/AccessoryMetricsService;Ljava/lang/String;Ljava/lang/String;II)V", "componentName", "Landroid/content/ComponentName;", "handlerThread", "Landroid/os/HandlerThread;", "hasUnexpectedlyDisconnected", "", "initializeOutputStreamSink", "Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$QueuedConnectionOutputStreamSink;", "initialized", "inputStreamSink", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSink;", "getInputStreamSink", "()Lcom/amazon/alexa/accessoryclient/common/connection/BundleSink;", "inputStreamSource", "Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$ConnectionInputStreamSource;", JoinPoint.SYNCHRONIZATION_LOCK, "messageSourceHandler", "Lcom/amazon/alexa/accessoryclient/common/connection/MessageSourceHandler;", "outputStreamSource", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSource;", "getOutputStreamSource", "()Lcom/amazon/alexa/accessoryclient/common/connection/BundleSource;", "serviceConnection", "Landroid/content/ServiceConnection;", "serviceConnectionListeners", "", "Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$ServiceConnectionListener;", "serviceHasAckedHandshake", "serviceIntent", "Landroid/content/Intent;", "teardownOutputStreamSink", "terminated", "uuid", EmulateConnection.EXTRA_CONNECT, "createBaseBundle", "Landroid/os/Bundle;", "createInitializeBundle", Metrics.DISCONNECT, "", ADMRegistrationConstants.CALL_EXCEPTION, "Ljava/lang/Exception;", "Lkotlin/Exception;", "establishConnectionHandshake", "haltInputOutputStreams", "initialize", "delayConnectionMillis", "", "onConnectedToService", "name", "serviceBinder", "Landroid/os/IBinder;", "prepareInputAndOutputStreams", Metrics.RECONNECT, "message", "record1MinuteMetrics", "sendDisconnectingNotificationToService", "serviceProcessIsAlive", "ClientFailedToBindServiceException", "ClientVersionNotSupportedByServiceException", "Companion", "ConnectionInputStreamSource", "QueuedConnectionOutputStreamSink", "ServiceConnectionDelegate", "ServiceConnectionListener", "UnexpectedlyDisconnectedFromServiceException", "UserTerminatedClientException", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class AbstractAlexaAccessoryClient {
    public static final Companion Companion = new Companion(null);
    private static final long SIXTY_SECONDS_MS = 60000;
    private static final String TAG = "AlexaAccessoryClient: ";
    private final AccessoryMetricsService accessoryMetricsService;
    private final ComponentName componentName;
    private final Context context;
    private final HandlerThread handlerThread;
    private boolean hasUnexpectedlyDisconnected;
    private QueuedConnectionOutputStreamSink initializeOutputStreamSink;
    private boolean initialized;
    private ConnectionInputStreamSource inputStreamSource;
    private final Object lock;
    private final int majorVersion;
    private MessageSourceHandler messageSourceHandler;
    private final int minorVersion;
    private final String serviceClassName;
    private final ServiceConnection serviceConnection;
    private final List<ServiceConnectionListener> serviceConnectionListeners;
    private boolean serviceHasAckedHandshake;
    private final Intent serviceIntent;
    private final String servicePkg;
    private QueuedConnectionOutputStreamSink teardownOutputStreamSink;
    private boolean terminated;
    private final String uuid;

    /* compiled from: AbstractAlexaAccessoryClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$ClientFailedToBindServiceException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ClientFailedToBindServiceException extends Exception {
    }

    /* compiled from: AbstractAlexaAccessoryClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00060\u0001j\u0002`\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$ClientVersionNotSupportedByServiceException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "clientMajorVersion", "", "clientMinorVersion", "serviceMajorVersion", "serviceMinorVersion", "(IIII)V", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ClientVersionNotSupportedByServiceException extends Exception {
        public ClientVersionNotSupportedByServiceException(int i, int i2, int i3, int i4) {
            super("Client version " + i + '.' + i2 + " was denied in its request to connect to service version " + i3 + '.' + i4);
        }
    }

    /* compiled from: AbstractAlexaAccessoryClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$Companion;", "", "()V", "SIXTY_SECONDS_MS", "", "TAG", "", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: AbstractAlexaAccessoryClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001\u0016Be\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00126\u0010\b\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\t\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011¢\u0006\u0002\u0010\u0012J\u0010\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R>\u0010\b\u001a2\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$ConnectionInputStreamSource;", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSource;", "messageSource", "Lcom/amazon/alexa/accessoryclient/common/connection/MessageSource;", "clientUuid", "", "accessoryMetricsService", "Lcom/amazon/alexa/accessory/metrics/AccessoryMetricsService;", "onConnectionDeniedCallback", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "serviceMajorVersion", "serviceMinorVersion", "", "onHandshakeAckedCallback", "Lkotlin/Function0;", "(Lcom/amazon/alexa/accessoryclient/common/connection/MessageSource;Ljava/lang/String;Lcom/amazon/alexa/accessory/metrics/AccessoryMetricsService;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;)V", "bundleSink", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSink;", "setBundleSink", "ClientInputStreamMessageSink", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class ConnectionInputStreamSource implements BundleSource {
        private final AccessoryMetricsService accessoryMetricsService;
        private BundleSink bundleSink;
        private final String clientUuid;
        private final Function2<Integer, Integer, Unit> onConnectionDeniedCallback;
        private final Function0<Unit> onHandshakeAckedCallback;

        /* compiled from: AbstractAlexaAccessoryClient.kt */
        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$ConnectionInputStreamSource$ClientInputStreamMessageSink;", "Lcom/amazon/alexa/accessoryclient/common/connection/MessageSink;", "(Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$ConnectionInputStreamSource;)V", "dispose", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handleMessage", "message", "Landroid/os/Message;", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
        /* loaded from: classes6.dex */
        public final class ClientInputStreamMessageSink implements MessageSink {
            public ClientInputStreamMessageSink() {
                ConnectionInputStreamSource.this = r1;
            }

            @Override // com.amazon.alexa.accessoryclient.common.connection.MessageSink
            public void dispose(@NotNull Exception e) {
                Intrinsics.checkParameterIsNotNull(e, "e");
                throw new UnsupportedOperationException("Not used");
            }

            @Override // com.amazon.alexa.accessoryclient.common.connection.MessageSink
            public void handleMessage(@NotNull Message message) {
                Intrinsics.checkParameterIsNotNull(message, "message");
                Logger.INSTANCE.v(new AbstractAlexaAccessoryClient$ConnectionInputStreamSource$ClientInputStreamMessageSink$handleMessage$1(this, message));
                int i = message.what;
                if (i != 0) {
                    if (i != 2) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaAccessoryClient:  Client ");
                        outline107.append(ConnectionInputStreamSource.this.clientUuid);
                        outline107.append(" received unrecognized message channel ");
                        outline107.append("of what=");
                        outline107.append(message);
                        outline107.append(".what ignoring this message: ");
                        outline107.append(message);
                        com.amazon.alexa.accessory.internal.util.Logger.e(outline107.toString());
                        return;
                    }
                    BundleSink access$getBundleSink$p = ConnectionInputStreamSource.access$getBundleSink$p(ConnectionInputStreamSource.this);
                    Bundle bundle = message.getData().getBundle("data");
                    if (bundle == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(bundle, "message.data.getBundle(C…_LEVEL_BUNDLE_DATA_KEY)!!");
                    access$getBundleSink$p.handleBundle(bundle);
                    return;
                }
                ConnectionInputStreamSource.this.onHandshakeAckedCallback.mo12560invoke();
                int i2 = message.getData().getInt(Constants.Keys.MAJOR_VERSION_KEY);
                int i3 = message.getData().getInt(Constants.Keys.MINOR_VERSION_KEY);
                if (!message.getData().getBoolean(Constants.Keys.CLIENT_CONNECTION_ACCEPTED)) {
                    StringBuilder outline113 = GeneratedOutlineSupport1.outline113("AlexaAccessoryClient:  Service has acknowledged and denied connection request ", "from ");
                    outline113.append(ConnectionInputStreamSource.this.clientUuid);
                    outline113.append(". Service version: ");
                    outline113.append(i2);
                    outline113.append('.');
                    outline113.append(i3);
                    com.amazon.alexa.accessory.internal.util.Logger.e(outline113.toString());
                    ConnectionInputStreamSource.this.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_CONNECTION, "clientVersionUnsupported", true, null);
                    ConnectionInputStreamSource.this.onConnectionDeniedCallback.mo12248invoke(Integer.valueOf(i2), Integer.valueOf(i3));
                    return;
                }
                StringBuilder outline1132 = GeneratedOutlineSupport1.outline113("AlexaAccessoryClient:  Service has acknowledged and accepted connection request ", "from ");
                outline1132.append(ConnectionInputStreamSource.this.clientUuid);
                outline1132.append(". Service version: ");
                outline1132.append(i2);
                outline1132.append('.');
                outline1132.append(i3);
                com.amazon.alexa.accessory.internal.util.Logger.d(outline1132.toString());
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public ConnectionInputStreamSource(@NotNull MessageSource messageSource, @NotNull String clientUuid, @NotNull AccessoryMetricsService accessoryMetricsService, @NotNull Function2<? super Integer, ? super Integer, Unit> onConnectionDeniedCallback, @NotNull Function0<Unit> onHandshakeAckedCallback) {
            Intrinsics.checkParameterIsNotNull(messageSource, "messageSource");
            Intrinsics.checkParameterIsNotNull(clientUuid, "clientUuid");
            Intrinsics.checkParameterIsNotNull(accessoryMetricsService, "accessoryMetricsService");
            Intrinsics.checkParameterIsNotNull(onConnectionDeniedCallback, "onConnectionDeniedCallback");
            Intrinsics.checkParameterIsNotNull(onHandshakeAckedCallback, "onHandshakeAckedCallback");
            this.clientUuid = clientUuid;
            this.accessoryMetricsService = accessoryMetricsService;
            this.onConnectionDeniedCallback = onConnectionDeniedCallback;
            this.onHandshakeAckedCallback = onHandshakeAckedCallback;
            messageSource.setMessageSink(new ClientInputStreamMessageSink());
        }

        public static final /* synthetic */ BundleSink access$getBundleSink$p(ConnectionInputStreamSource connectionInputStreamSource) {
            BundleSink bundleSink = connectionInputStreamSource.bundleSink;
            if (bundleSink == null) {
                Intrinsics.throwUninitializedPropertyAccessException("bundleSink");
            }
            return bundleSink;
        }

        @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSource
        public void setBundleSink(@NotNull BundleSink bundleSink) {
            Intrinsics.checkParameterIsNotNull(bundleSink, "bundleSink");
            this.bundleSink = bundleSink;
        }
    }

    /* compiled from: AbstractAlexaAccessoryClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B3\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\u0010\rJ\u0014\u0010\u0013\u001a\u00020\f2\n\u0010\u0014\u001a\u00060\u0015j\u0002`\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\bH\u0002J\u0010\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\bH\u0016J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\bH\u0002J\u0012\u0010\u001d\u001a\u00020\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u001f\u001a\u00020\fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$QueuedConnectionOutputStreamSink;", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSink;", "Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$ServiceConnectionListener;", "what", "", "inputStreamMessenger", "Landroid/os/Messenger;", "baseBundle", "Landroid/os/Bundle;", "failedToSendMessageCallback", "Lkotlin/Function1;", "Landroid/os/RemoteException;", "", "(ILandroid/os/Messenger;Landroid/os/Bundle;Lkotlin/jvm/functions/Function1;)V", JoinPoint.SYNCHRONIZATION_LOCK, "", "outputStreamMessenger", "queue", "Ljava/util/Queue;", "dispose", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "drainQueue", "", "enqueue", "bundle", "handleBundle", "handleBundleInternal", "onConnected", "messenger", "onDisconnected", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class QueuedConnectionOutputStreamSink implements BundleSink, ServiceConnectionListener {
        private final Bundle baseBundle;
        private final Function1<RemoteException, Unit> failedToSendMessageCallback;
        private final Messenger inputStreamMessenger;
        private final Object lock;
        private Messenger outputStreamMessenger;
        private final Queue<Bundle> queue;
        private final int what;

        /* JADX WARN: Multi-variable type inference failed */
        public QueuedConnectionOutputStreamSink(int i, @NotNull Messenger inputStreamMessenger, @NotNull Bundle baseBundle, @NotNull Function1<? super RemoteException, Unit> failedToSendMessageCallback) {
            Intrinsics.checkParameterIsNotNull(inputStreamMessenger, "inputStreamMessenger");
            Intrinsics.checkParameterIsNotNull(baseBundle, "baseBundle");
            Intrinsics.checkParameterIsNotNull(failedToSendMessageCallback, "failedToSendMessageCallback");
            this.what = i;
            this.inputStreamMessenger = inputStreamMessenger;
            this.baseBundle = baseBundle;
            this.failedToSendMessageCallback = failedToSendMessageCallback;
            this.queue = new LinkedList();
            this.lock = new Object();
        }

        private final boolean drainQueue() {
            Preconditions.Companion.hasLock(this.lock);
            com.amazon.alexa.accessory.internal.util.Logger.v("AlexaAccessoryClient:  OutputStream what=" + this.what + " flushing to service");
            ArrayList arrayList = new ArrayList(this.queue);
            this.queue.clear();
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                Bundle bundle = (Bundle) it2.next();
                Intrinsics.checkExpressionValueIsNotNull(bundle, "bundle");
                if (!handleBundleInternal(bundle)) {
                    return false;
                }
            }
            return true;
        }

        private final void enqueue(Bundle bundle) {
            Preconditions.Companion.hasLock(this.lock);
            Logger.INSTANCE.v(new AbstractAlexaAccessoryClient$QueuedConnectionOutputStreamSink$enqueue$1(this, bundle));
            this.queue.add(bundle);
        }

        private final boolean handleBundleInternal(Bundle bundle) {
            Preconditions.Companion.hasLock(this.lock);
            if (this.outputStreamMessenger == null) {
                enqueue(bundle);
                return true;
            }
            Message message = new Message();
            int i = this.what;
            message.what = i;
            if (i == 0) {
                message.replyTo = this.inputStreamMessenger;
            }
            Bundle bundle2 = new Bundle(this.baseBundle);
            bundle2.putBundle("data", bundle);
            message.setData(bundle2);
            try {
                Messenger messenger = this.outputStreamMessenger;
                if (messenger == null) {
                    Intrinsics.throwNpe();
                }
                messenger.send(message);
                return true;
            } catch (RemoteException e) {
                com.amazon.alexa.accessory.internal.util.Logger.e("AlexaAccessoryClient:  QueuedConnectionOutputStream failed to send message", e);
                this.failedToSendMessageCallback.mo12165invoke(e);
                return false;
            }
        }

        @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSink
        public void dispose(@NotNull Exception e) {
            Intrinsics.checkParameterIsNotNull(e, "e");
            throw new UnsupportedOperationException("Unused");
        }

        @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSink
        public void handleBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            synchronized (this.lock) {
                handleBundleInternal(bundle);
            }
        }

        @Override // com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.ServiceConnectionListener
        public boolean onConnected(@Nullable Messenger messenger) {
            boolean drainQueue;
            synchronized (this.lock) {
                this.outputStreamMessenger = messenger;
                drainQueue = drainQueue();
            }
            return drainQueue;
        }

        @Override // com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.ServiceConnectionListener
        public void onDisconnected() {
            synchronized (this.lock) {
                this.queue.clear();
                this.outputStreamMessenger = null;
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractAlexaAccessoryClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$ServiceConnectionDelegate;", "Landroid/content/ServiceConnection;", "(Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient;)V", MetricsConstants.CLIENT_CONNECTION_ON_BINDING_DIED, "", "name", "Landroid/content/ComponentName;", MetricsConstants.CLIENT_CONNECTION_ON_NULL_BINDING, "onServiceConnected", NotificationCompat.CATEGORY_SERVICE, "Landroid/os/IBinder;", MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED, "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class ServiceConnectionDelegate implements ServiceConnection {
        public ServiceConnectionDelegate() {
            AbstractAlexaAccessoryClient.this = r1;
        }

        @Override // android.content.ServiceConnection
        public void onBindingDied(@NotNull ComponentName name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            AbstractAlexaAccessoryClient abstractAlexaAccessoryClient = AbstractAlexaAccessoryClient.this;
            abstractAlexaAccessoryClient.reconnect("onBindingDied for componentName " + name);
            AbstractAlexaAccessoryClient.this.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_CONNECTION, MetricsConstants.CLIENT_CONNECTION_ON_BINDING_DIED, true, null);
        }

        @Override // android.content.ServiceConnection
        public void onNullBinding(@NotNull ComponentName name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            AbstractAlexaAccessoryClient abstractAlexaAccessoryClient = AbstractAlexaAccessoryClient.this;
            abstractAlexaAccessoryClient.reconnect("onNullBinding with componentName " + name);
            AbstractAlexaAccessoryClient.this.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_CONNECTION, MetricsConstants.CLIENT_CONNECTION_ON_NULL_BINDING, true, null);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(@NotNull ComponentName name, @NotNull IBinder service) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            Intrinsics.checkParameterIsNotNull(service, "service");
            AbstractAlexaAccessoryClient.this.onConnectedToService(name, service);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(@NotNull ComponentName name) {
            Intrinsics.checkParameterIsNotNull(name, "name");
            AbstractAlexaAccessoryClient abstractAlexaAccessoryClient = AbstractAlexaAccessoryClient.this;
            abstractAlexaAccessoryClient.reconnect("onServiceDisconnected with componentName " + name);
            AbstractAlexaAccessoryClient.this.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_CONNECTION, MetricsConstants.CLIENT_CONNECTION_ON_SERVICE_DISCONNECTED, true, null);
        }
    }

    /* compiled from: AbstractAlexaAccessoryClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\bb\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$ServiceConnectionListener;", "", "onConnected", "", "messenger", "Landroid/os/Messenger;", "onDisconnected", "", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public interface ServiceConnectionListener {
        boolean onConnected(@Nullable Messenger messenger);

        void onDisconnected();
    }

    /* compiled from: AbstractAlexaAccessoryClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$UnexpectedlyDisconnectedFromServiceException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class UnexpectedlyDisconnectedFromServiceException extends Exception {
    }

    /* compiled from: AbstractAlexaAccessoryClient.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/accessoryclient/client/AbstractAlexaAccessoryClient$UserTerminatedClientException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class UserTerminatedClientException extends Exception {
    }

    public AbstractAlexaAccessoryClient(@NotNull Context context, @NotNull AccessoryMetricsService accessoryMetricsService, @NotNull String servicePkg, @NotNull String serviceClassName, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(accessoryMetricsService, "accessoryMetricsService");
        Intrinsics.checkParameterIsNotNull(servicePkg, "servicePkg");
        Intrinsics.checkParameterIsNotNull(serviceClassName, "serviceClassName");
        this.context = context;
        this.accessoryMetricsService = accessoryMetricsService;
        this.servicePkg = servicePkg;
        this.serviceClassName = serviceClassName;
        this.majorVersion = i;
        this.minorVersion = i2;
        this.serviceConnection = new ServiceConnectionDelegate();
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.uuid = uuid;
        this.handlerThread = new HandlerThread(this.uuid);
        this.componentName = new ComponentName(this.servicePkg, this.serviceClassName);
        Intent intent = new Intent();
        intent.setComponent(this.componentName);
        this.serviceIntent = intent;
        this.serviceConnectionListeners = new ArrayList();
        this.lock = new Object();
    }

    public final boolean connect() {
        Preconditions.Companion.hasLock(this.lock);
        establishConnectionHandshake();
        if (!this.context.bindService(this.serviceIntent, this.serviceConnection, 9)) {
            this.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_CONNECTION, MetricsConstants.CLIENT_CONNECTION_BINDING_FAILED, true, null);
            disconnect(new ClientFailedToBindServiceException());
            return false;
        }
        return true;
    }

    private final Bundle createBaseBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("uuid", this.uuid);
        return bundle;
    }

    private final Bundle createInitializeBundle() {
        Bundle createBaseBundle = createBaseBundle();
        createBaseBundle.putInt(Constants.Keys.MAJOR_VERSION_KEY, this.majorVersion);
        createBaseBundle.putInt(Constants.Keys.MINOR_VERSION_KEY, this.minorVersion);
        return createBaseBundle;
    }

    private final void establishConnectionHandshake() {
        QueuedConnectionOutputStreamSink queuedConnectionOutputStreamSink = this.initializeOutputStreamSink;
        if (queuedConnectionOutputStreamSink == null) {
            Intrinsics.throwUninitializedPropertyAccessException("initializeOutputStreamSink");
        }
        queuedConnectionOutputStreamSink.handleBundle(new Bundle());
    }

    private final void haltInputOutputStreams(Exception exc) {
        Preconditions.Companion.hasLock(this.lock);
        getInputStreamSink().dispose(exc);
        for (ServiceConnectionListener serviceConnectionListener : this.serviceConnectionListeners) {
            serviceConnectionListener.onDisconnected();
        }
    }

    public static /* synthetic */ void initialize$default(AbstractAlexaAccessoryClient abstractAlexaAccessoryClient, long j, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                j = 5000;
            }
            abstractAlexaAccessoryClient.initialize(j);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: initialize");
    }

    public final void onConnectedToService(ComponentName componentName, IBinder iBinder) {
        synchronized (this.lock) {
            com.amazon.alexa.accessory.internal.util.Logger.d("AlexaAccessoryClient:  " + this.uuid + " was connected to service " + componentName);
            this.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_CONNECTION, MetricsConstants.CLIENT_CONNECTION_SUCCESS, true, null);
            Messenger messenger = new Messenger(iBinder);
            Iterator<ServiceConnectionListener> it2 = this.serviceConnectionListeners.iterator();
            while (it2.hasNext() && it2.next().onConnected(messenger)) {
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    private final void prepareInputAndOutputStreams() {
        Preconditions.Companion.hasLock(this.lock);
        this.handlerThread.start();
        Looper looper = this.handlerThread.getLooper();
        Intrinsics.checkExpressionValueIsNotNull(looper, "handlerThread.looper");
        this.messageSourceHandler = new MessageSourceHandler(looper);
        MessageSourceHandler messageSourceHandler = this.messageSourceHandler;
        if (messageSourceHandler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("messageSourceHandler");
        }
        Messenger messenger = new Messenger(messageSourceHandler);
        Bundle createBaseBundle = createBaseBundle();
        this.initializeOutputStreamSink = new QueuedConnectionOutputStreamSink(0, messenger, createInitializeBundle(), new AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$1(this));
        this.teardownOutputStreamSink = new QueuedConnectionOutputStreamSink(1, messenger, createBaseBundle, AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$2.INSTANCE);
        QueuedConnectionOutputStreamSink queuedConnectionOutputStreamSink = new QueuedConnectionOutputStreamSink(2, messenger, createBaseBundle, new AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$mainOutputStreamSink$1(this));
        List<ServiceConnectionListener> list = this.serviceConnectionListeners;
        QueuedConnectionOutputStreamSink queuedConnectionOutputStreamSink2 = this.initializeOutputStreamSink;
        if (queuedConnectionOutputStreamSink2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("initializeOutputStreamSink");
        }
        list.add(queuedConnectionOutputStreamSink2);
        QueuedConnectionOutputStreamSink queuedConnectionOutputStreamSink3 = this.teardownOutputStreamSink;
        if (queuedConnectionOutputStreamSink3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("teardownOutputStreamSink");
        }
        list.add(queuedConnectionOutputStreamSink3);
        list.add(queuedConnectionOutputStreamSink);
        MessageSourceHandler messageSourceHandler2 = this.messageSourceHandler;
        if (messageSourceHandler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("messageSourceHandler");
        }
        this.inputStreamSource = new ConnectionInputStreamSource(messageSourceHandler2, this.uuid, this.accessoryMetricsService, new AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$4(this), new AbstractAlexaAccessoryClient$prepareInputAndOutputStreams$5(this));
        ConnectionInputStreamSource connectionInputStreamSource = this.inputStreamSource;
        if (connectionInputStreamSource == null) {
            Intrinsics.throwUninitializedPropertyAccessException("inputStreamSource");
        }
        connectionInputStreamSource.setBundleSink(getInputStreamSink());
        getOutputStreamSource().setBundleSink(queuedConnectionOutputStreamSink);
    }

    public final void reconnect(String str) {
        synchronized (this.lock) {
            if (this.initialized && !this.terminated) {
                this.hasUnexpectedlyDisconnected = true;
                com.amazon.alexa.accessory.internal.util.Logger.e("AlexaAccessoryClient:  Client " + this.uuid + " unexpectedly disconnected due to '" + str + "'. Attempting reconnection");
                try {
                    this.context.unbindService(this.serviceConnection);
                } catch (Exception e) {
                    com.amazon.alexa.accessory.internal.util.Logger.e("AlexaAccessoryClient:  caught exception when calling unbindService in reconnect", e);
                    this.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_CONNECTION, MetricsConstants.CLIENT_CONNECTION_UNBINDING_RECONNECT_FAILED, true, null);
                }
                haltInputOutputStreams(new UnexpectedlyDisconnectedFromServiceException());
                connect();
                this.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_CONNECTION, MetricsConstants.CLIENT_CONNECTION_RECONNECT_ATTEMPT, true, null);
                return;
            }
            com.amazon.alexa.accessory.internal.util.Logger.d("Ignoring internally-activated reconnect for client " + this.uuid + " because it is not initialized or it is terminated. initialized: " + this.initialized + " terminated: " + this.terminated);
        }
    }

    public final void record1MinuteMetrics() {
        new Handler(this.handlerThread.getLooper()).postDelayed(new Runnable() { // from class: com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient$record1MinuteMetrics$1
            /* JADX WARN: Removed duplicated region for block: B:14:0x0032 A[Catch: all -> 0x007c, TryCatch #0 {, blocks: (B:4:0x0007, B:6:0x0013, B:8:0x001b, B:12:0x0026, B:14:0x0032, B:16:0x003a, B:20:0x0044), top: B:27:0x0007 }] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() {
                /*
                    r6 = this;
                    com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient r0 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.this
                    java.lang.Object r0 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.access$getLock$p(r0)
                    monitor-enter(r0)
                    kotlin.Pair r1 = new kotlin.Pair     // Catch: java.lang.Throwable -> L7c
                    com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient r2 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.this     // Catch: java.lang.Throwable -> L7c
                    boolean r2 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.access$getInitialized$p(r2)     // Catch: java.lang.Throwable -> L7c
                    r3 = 1
                    r4 = 0
                    if (r2 == 0) goto L25
                    com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient r2 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.this     // Catch: java.lang.Throwable -> L7c
                    boolean r2 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.access$getTerminated$p(r2)     // Catch: java.lang.Throwable -> L7c
                    if (r2 != 0) goto L25
                    com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient r2 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.this     // Catch: java.lang.Throwable -> L7c
                    boolean r2 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.access$getServiceHasAckedHandshake$p(r2)     // Catch: java.lang.Throwable -> L7c
                    if (r2 == 0) goto L25
                    r2 = r3
                    goto L26
                L25:
                    r2 = r4
                L26:
                    java.lang.Boolean r2 = java.lang.Boolean.valueOf(r2)     // Catch: java.lang.Throwable -> L7c
                    com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient r5 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.this     // Catch: java.lang.Throwable -> L7c
                    boolean r5 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.access$getInitialized$p(r5)     // Catch: java.lang.Throwable -> L7c
                    if (r5 == 0) goto L43
                    com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient r5 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.this     // Catch: java.lang.Throwable -> L7c
                    boolean r5 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.access$getTerminated$p(r5)     // Catch: java.lang.Throwable -> L7c
                    if (r5 != 0) goto L43
                    com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient r5 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.this     // Catch: java.lang.Throwable -> L7c
                    boolean r5 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.access$getHasUnexpectedlyDisconnected$p(r5)     // Catch: java.lang.Throwable -> L7c
                    if (r5 == 0) goto L43
                    goto L44
                L43:
                    r3 = r4
                L44:
                    java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)     // Catch: java.lang.Throwable -> L7c
                    r1.<init>(r2, r3)     // Catch: java.lang.Throwable -> L7c
                    monitor-exit(r0)
                    java.lang.Object r0 = r1.component1()
                    java.lang.Boolean r0 = (java.lang.Boolean) r0
                    boolean r0 = r0.booleanValue()
                    java.lang.Object r1 = r1.component2()
                    java.lang.Boolean r1 = (java.lang.Boolean) r1
                    boolean r1 = r1.booleanValue()
                    com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient r2 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.this
                    com.amazon.alexa.accessory.metrics.AccessoryMetricsService r2 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.access$getAccessoryMetricsService$p(r2)
                    r3 = 0
                    java.lang.String r4 = "AccessoryClientConnection"
                    java.lang.String r5 = "connectionAckedWithinSixtySeconds"
                    r2.recordOccurrence(r4, r5, r0, r3)
                    com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient r0 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.this
                    com.amazon.alexa.accessory.metrics.AccessoryMetricsService r0 = com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient.access$getAccessoryMetricsService$p(r0)
                    java.lang.String r2 = "AccessoryClientConnection"
                    java.lang.String r4 = "connectionUnexpectedlyDisconnectedWithinSixtySeconds"
                    r0.recordOccurrence(r2, r4, r1, r3)
                    return
                L7c:
                    r1 = move-exception
                    monitor-exit(r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient$record1MinuteMetrics$1.run():void");
            }
        }, 60000L);
    }

    private final void sendDisconnectingNotificationToService() {
        QueuedConnectionOutputStreamSink queuedConnectionOutputStreamSink = this.teardownOutputStreamSink;
        if (queuedConnectionOutputStreamSink == null) {
            Intrinsics.throwUninitializedPropertyAccessException("teardownOutputStreamSink");
        }
        queuedConnectionOutputStreamSink.handleBundle(new Bundle());
    }

    private final boolean serviceProcessIsAlive() {
        String str;
        Object systemService;
        Object obj;
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = false;
        try {
            str = this.context.getPackageManager().getServiceInfo(this.componentName, 128).processName;
            systemService = this.context.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        } catch (Exception e) {
            com.amazon.alexa.accessory.internal.util.Logger.e("AlexaAccessoryClient:  Caught exception when determining if the service's process is alive", e);
        }
        if (systemService != null) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
            Intrinsics.checkExpressionValueIsNotNull(runningAppProcesses, "(context.getSystemServic…     .runningAppProcesses");
            Iterator<T> it2 = runningAppProcesses.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                if (Intrinsics.areEqual(((ActivityManager.RunningAppProcessInfo) obj).processName, str)) {
                    break;
                }
            }
            if (obj != null) {
                z = true;
            }
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaAccessoryClient:  Took ");
            outline107.append(System.currentTimeMillis() - currentTimeMillis);
            outline107.append("ms to determine if service's process is alive: ");
            outline107.append(z);
            outline107.append('.');
            com.amazon.alexa.accessory.internal.util.Logger.e(outline107.toString());
            return z;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.app.ActivityManager");
    }

    public final void disconnect() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AlexaAccessoryClient:  Disconnect requested for client ");
        outline107.append(this.uuid);
        outline107.append(". Terminating client.");
        com.amazon.alexa.accessory.internal.util.Logger.d(outline107.toString());
        this.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_CONNECTION, "disconnectRequested", true, null);
        disconnect(new UserTerminatedClientException());
    }

    @NotNull
    protected abstract BundleSink getInputStreamSink();

    @NotNull
    protected abstract BundleSource getOutputStreamSource();

    @JvmOverloads
    public final void initialize() {
        initialize$default(this, 0L, 1, null);
    }

    @JvmOverloads
    public final void initialize(final long j) {
        com.amazon.alexa.accessory.internal.util.Logger.d("AlexaAccessoryClient:  initialize called with delayConnectionMillis " + j);
        synchronized (this.lock) {
            if (!this.initialized && !this.terminated) {
                this.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_CONNECTION, MetricsConstants.CLIENT_CONNECTION_REQUEST, true, null);
                this.initialized = true;
                prepareInputAndOutputStreams();
                Runnable runnable = new Runnable() { // from class: com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient$initialize$$inlined$synchronized$lambda$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Object obj;
                        boolean z;
                        String str;
                        boolean connect;
                        obj = AbstractAlexaAccessoryClient.this.lock;
                        synchronized (obj) {
                            z = AbstractAlexaAccessoryClient.this.terminated;
                            if (z) {
                                return;
                            }
                            StringBuilder sb = new StringBuilder();
                            sb.append("AlexaAccessoryClient:  Connect requested for client ");
                            str = AbstractAlexaAccessoryClient.this.uuid;
                            sb.append(str);
                            sb.append(". Connecting client");
                            com.amazon.alexa.accessory.internal.util.Logger.d(sb.toString());
                            connect = AbstractAlexaAccessoryClient.this.connect();
                            if (connect) {
                                AbstractAlexaAccessoryClient.this.record1MinuteMetrics();
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                    }
                };
                if (j > 0) {
                    boolean serviceProcessIsAlive = serviceProcessIsAlive();
                    if (serviceProcessIsAlive) {
                        com.amazon.alexa.accessory.internal.util.Logger.d("AlexaAccessoryClient:  Ignoring delay, binding service instantly.");
                    }
                    if (!serviceProcessIsAlive) {
                        new Handler(this.handlerThread.getLooper()).postDelayed(runnable, j);
                        return;
                    }
                }
                runnable.run();
                return;
            }
            com.amazon.alexa.accessory.internal.util.Logger.d("Cannot initialize client " + this.uuid + " more than once");
        }
    }

    public final void disconnect(final Exception exc) {
        synchronized (this.lock) {
            if (this.initialized && !this.terminated) {
                this.terminated = true;
                com.amazon.alexa.accessory.internal.util.Logger.e("AlexaAccessoryClient:  Terminating client " + this.uuid + RealTimeTextConstants.COLON_SPACE + exc);
                sendDisconnectingNotificationToService();
                haltInputOutputStreams(exc);
                MessageSourceHandler messageSourceHandler = this.messageSourceHandler;
                if (messageSourceHandler == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("messageSourceHandler");
                }
                messageSourceHandler.post(new Runnable() { // from class: com.amazon.alexa.accessoryclient.client.AbstractAlexaAccessoryClient$disconnect$$inlined$synchronized$lambda$1
                    @Override // java.lang.Runnable
                    public final void run() {
                        Context context;
                        ServiceConnection serviceConnection;
                        try {
                            context = AbstractAlexaAccessoryClient.this.context;
                            serviceConnection = AbstractAlexaAccessoryClient.this.serviceConnection;
                            context.unbindService(serviceConnection);
                        } catch (Exception e) {
                            com.amazon.alexa.accessory.internal.util.Logger.e("AlexaAccessoryClient:  caught exception when calling unbindService in disconnect", e);
                            AbstractAlexaAccessoryClient.this.accessoryMetricsService.recordOccurrence(MetricsConstants.CLIENT_CONNECTION, MetricsConstants.CLIENT_CONNECTION_UNBINDING_DISCONNECT_FAILED, true, null);
                        }
                    }
                });
                this.handlerThread.quitSafely();
                return;
            }
            com.amazon.alexa.accessory.internal.util.Logger.d("Cannot disconnect client " + this.uuid + ". Clients cannot be disconnected if they are not initialized or they are terminated. initialized: " + this.initialized + " terminated: " + this.terminated);
        }
    }
}
