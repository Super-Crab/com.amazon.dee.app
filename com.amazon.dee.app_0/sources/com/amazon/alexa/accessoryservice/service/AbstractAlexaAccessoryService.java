package com.amazon.alexa.accessoryservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessoryclient.common.connection.BundleSink;
import com.amazon.alexa.accessoryclient.common.connection.Constants;
import com.amazon.alexa.accessoryclient.common.connection.DelineatedBundleSink;
import com.amazon.alexa.accessoryclient.common.connection.DelineatedBundleSource;
import com.amazon.alexa.accessoryclient.common.connection.MessageSink;
import com.amazon.alexa.accessoryclient.common.connection.MessageSourceHandler;
import com.amazon.alexa.accessoryclient.common.util.MetricsConstants;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: AbstractAlexaAccessoryService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\b&\u0018\u0000 #2\u00020\u0001:\u0003#$%B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0003H\u0002J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001bH\u0016J\b\u0010\u001d\u001a\u00020\u001bH$J\b\u0010\u001e\u001a\u00020\rH\u0002J\u0018\u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\rH\u0002R\u0012\u0010\b\u001a\u00020\tX¤\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000fX¤\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006&"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/AbstractAlexaAccessoryService;", "Landroid/app/Service;", Constants.Keys.MAJOR_VERSION_KEY, "", Constants.Keys.MINOR_VERSION_KEY, "minimumClientMajorVersion", "minimumClientMinorVersion", "(IIII)V", "inputStreamSink", "Lcom/amazon/alexa/accessoryclient/common/connection/DelineatedBundleSink;", "getInputStreamSink", "()Lcom/amazon/alexa/accessoryclient/common/connection/DelineatedBundleSink;", "messenger", "Landroid/os/Messenger;", "outputStreamSource", "Lcom/amazon/alexa/accessoryclient/common/connection/DelineatedBundleSource;", "getOutputStreamSource", "()Lcom/amazon/alexa/accessoryclient/common/connection/DelineatedBundleSource;", "isValidClient", "", "clientMajorVersion", "clientMinorVersion", "onBind", "Landroid/os/IBinder;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "onCreate", "", "onDestroy", "onServiceDestroyed", "prepareInputStream", "prepareOutputStream", "clientUuid", "", "connectionMessenger", "Companion", "ConnectionOutputStreamSink", "MessageSinkInputStreamDispatcher", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public abstract class AbstractAlexaAccessoryService extends Service {
    public static final Companion Companion = new Companion(null);
    private static final String TAG = "AbstractAlexaAccessoryService: ";
    private final int majorVersion;
    private final Messenger messenger = prepareInputStream();
    private final int minimumClientMajorVersion;
    private final int minimumClientMinorVersion;
    private final int minorVersion;

    /* compiled from: AbstractAlexaAccessoryService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/AbstractAlexaAccessoryService$Companion;", "", "()V", "TAG", "", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractAlexaAccessoryService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0014\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u00060\u0010j\u0002`\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/AbstractAlexaAccessoryService$ConnectionOutputStreamSink;", "Lcom/amazon/alexa/accessoryclient/common/connection/BundleSink;", "clientMessenger", "Landroid/os/Messenger;", "what", "", "uuid", "", "(Lcom/amazon/alexa/accessoryservice/service/AbstractAlexaAccessoryService;Landroid/os/Messenger;ILjava/lang/String;)V", "initializeBaseBundle", "Landroid/os/Bundle;", "getInitializeBaseBundle", "()Landroid/os/Bundle;", "dispose", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handleBundle", "bundle", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class ConnectionOutputStreamSink implements BundleSink {
        private final Messenger clientMessenger;
        final /* synthetic */ AbstractAlexaAccessoryService this$0;
        private final String uuid;
        private final int what;

        public ConnectionOutputStreamSink(@NotNull AbstractAlexaAccessoryService abstractAlexaAccessoryService, Messenger clientMessenger, @NotNull int i, String uuid) {
            Intrinsics.checkParameterIsNotNull(clientMessenger, "clientMessenger");
            Intrinsics.checkParameterIsNotNull(uuid, "uuid");
            this.this$0 = abstractAlexaAccessoryService;
            this.clientMessenger = clientMessenger;
            this.what = i;
            this.uuid = uuid;
        }

        private final Bundle getInitializeBaseBundle() {
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.Keys.MAJOR_VERSION_KEY, this.this$0.majorVersion);
            bundle.putInt(Constants.Keys.MINOR_VERSION_KEY, this.this$0.minorVersion);
            return bundle;
        }

        @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSink
        public void dispose(@NotNull Exception e) {
            Intrinsics.checkParameterIsNotNull(e, "e");
            throw new IllegalStateException("Not used");
        }

        @Override // com.amazon.alexa.accessoryclient.common.connection.BundleSink
        public void handleBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            Bundle initializeBaseBundle = this.what == 0 ? getInitializeBaseBundle() : new Bundle();
            initializeBaseBundle.putBundle("data", bundle);
            Message message = new Message();
            message.what = this.what;
            message.setData(initializeBaseBundle);
            try {
                this.clientMessenger.send(message);
            } catch (RemoteException e) {
                this.this$0.getInputStreamSink().dispose(e, this.uuid);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AbstractAlexaAccessoryService.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/alexa/accessoryservice/service/AbstractAlexaAccessoryService$MessageSinkInputStreamDispatcher;", "Lcom/amazon/alexa/accessoryclient/common/connection/MessageSink;", "(Lcom/amazon/alexa/accessoryservice/service/AbstractAlexaAccessoryService;)V", "dispose", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "handleInitialize", "message", "Landroid/os/Message;", "clientUuid", "", "handleMessage", "AlexaAccessoryAndroidService_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public final class MessageSinkInputStreamDispatcher implements MessageSink {
        public MessageSinkInputStreamDispatcher() {
        }

        private final void handleInitialize(Message message, String str) {
            GeneratedOutlineSupport1.outline171(MetricsConstants.SERVICE_CONNECTION, MetricsConstants.SERVICE_CONNECTION_INITIALIZE, true, null);
            boolean isValidClient = AbstractAlexaAccessoryService.this.isValidClient(message.getData().getInt(Constants.Keys.MAJOR_VERSION_KEY), message.getData().getInt(Constants.Keys.MINOR_VERSION_KEY));
            if (isValidClient) {
                AbstractAlexaAccessoryService.this.getInputStreamSink().dispose(new IOException("Client is connecting, should clear any stale state regarding this client"), str);
                AbstractAlexaAccessoryService abstractAlexaAccessoryService = AbstractAlexaAccessoryService.this;
                Messenger messenger = message.replyTo;
                Intrinsics.checkExpressionValueIsNotNull(messenger, "message.replyTo");
                abstractAlexaAccessoryService.prepareOutputStream(str, messenger);
            } else {
                Logger.e("AbstractAlexaAccessoryService:  Denying client connection " + str + " because it is not a valid client");
                AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.SERVICE_CONNECTION, "clientVersionUnsupported", true, null);
            }
            Message message2 = new Message();
            message2.what = message.what;
            Bundle outline13 = GeneratedOutlineSupport1.outline13(Constants.Keys.CLIENT_CONNECTION_ACCEPTED, isValidClient);
            outline13.putInt(Constants.Keys.MAJOR_VERSION_KEY, AbstractAlexaAccessoryService.this.majorVersion);
            outline13.putInt(Constants.Keys.MINOR_VERSION_KEY, AbstractAlexaAccessoryService.this.minorVersion);
            message2.setData(outline13);
            try {
                message.replyTo.send(message2);
            } catch (RemoteException e) {
                Logger.e("AbstractAlexaAccessoryService:  Unable to ack success for connection initialize for client " + str + ", releasing this client.");
                AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.SERVICE_CONNECTION, MetricsConstants.SERVICE_CONNECTION_FAILED, true, null);
                AbstractAlexaAccessoryService.this.getInputStreamSink().dispose(e, str);
            }
        }

        @Override // com.amazon.alexa.accessoryclient.common.connection.MessageSink
        public void dispose(@NotNull Exception e) {
            Intrinsics.checkParameterIsNotNull(e, "e");
            throw new IllegalStateException("Not used");
        }

        @Override // com.amazon.alexa.accessoryclient.common.connection.MessageSink
        public void handleMessage(@NotNull Message message) {
            Intrinsics.checkParameterIsNotNull(message, "message");
            com.amazon.alexa.accessoryclient.common.util.Logger.INSTANCE.v(new AbstractAlexaAccessoryService$MessageSinkInputStreamDispatcher$handleMessage$1(message));
            String string = message.getData().getString("uuid");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            int i = message.what;
            if (i == 0) {
                handleInitialize(message, string);
            } else if (i == 1) {
                Logger.d("AbstractAlexaAccessoryService:  Client requested disconnect: " + string);
                AccessoryMetricsServiceHolder.getInstance().get().recordOccurrence(MetricsConstants.SERVICE_CONNECTION, "disconnectRequested", true, null);
                AbstractAlexaAccessoryService.this.getInputStreamSink().dispose(new IOException(GeneratedOutlineSupport1.outline72("Client requested disconnect: ", string)), string);
            } else if (i != 2) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AbstractAlexaAccessoryService:  received unrecognized command for what=");
                outline107.append(message.what);
                outline107.append(" from ");
                outline107.append("client ");
                outline107.append(string);
                outline107.append(", ignoring the message ");
                outline107.append(message);
                Logger.e(outline107.toString());
            } else {
                DelineatedBundleSink inputStreamSink = AbstractAlexaAccessoryService.this.getInputStreamSink();
                Bundle bundle = message.getData().getBundle("data");
                if (bundle == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(bundle, "message.data.getBundle(C…_LEVEL_BUNDLE_DATA_KEY)!!");
                inputStreamSink.handleBundle(bundle, string);
            }
        }
    }

    public AbstractAlexaAccessoryService(int i, int i2, int i3, int i4) {
        this.majorVersion = i;
        this.minorVersion = i2;
        this.minimumClientMajorVersion = i3;
        this.minimumClientMinorVersion = i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isValidClient(int i, int i2) {
        int i3 = this.minimumClientMajorVersion;
        boolean z = i > i3 || (i == i3 && i2 >= this.minimumClientMinorVersion);
        Logger.d("AbstractAlexaAccessoryService:  Determining client connection validity with client version " + i + '.' + i2 + " and service version " + this.majorVersion + '.' + this.minorVersion + " isValid: " + z);
        return z;
    }

    private final Messenger prepareInputStream() {
        MessageSourceHandler messageSourceHandler = new MessageSourceHandler();
        messageSourceHandler.setMessageSink(new MessageSinkInputStreamDispatcher());
        return new Messenger(messageSourceHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void prepareOutputStream(String str, Messenger messenger) {
        getOutputStreamSource().setDelineatedBundleSink(new ConnectionOutputStreamSink(this, messenger, 2, str), str);
    }

    @NotNull
    protected abstract DelineatedBundleSink getInputStreamSink();

    @NotNull
    protected abstract DelineatedBundleSource getOutputStreamSource();

    @Override // android.app.Service
    @NotNull
    public IBinder onBind(@NotNull Intent intent) {
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        Preconditions.mainThread();
        IBinder binder = this.messenger.getBinder();
        Intrinsics.checkExpressionValueIsNotNull(binder, "messenger.binder");
        return binder;
    }

    @Override // android.app.Service
    public void onCreate() {
        Logger.d("AbstractAlexaAccessoryService:  onCreate");
    }

    @Override // android.app.Service
    public void onDestroy() {
        Logger.d("AbstractAlexaAccessoryService:  onDestroy");
        onServiceDestroyed();
    }

    protected abstract void onServiceDestroyed();
}
