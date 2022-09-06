package com.amazon.alexa.accessory.capabilities.crypto;

import android.os.Handler;
import android.os.Looper;
import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.capabilities.crypto.KeyExchangeCapability;
import com.amazon.alexa.accessory.capabilities.crypto.metrics.KeyExchangeMetrics;
import com.amazon.alexa.accessory.capabilities.crypto.state.KeyExchangeState;
import com.amazon.alexa.accessory.capabilities.crypto.state.TimeoutCondition;
import com.amazon.alexa.accessory.capabilities.crypto.state.TransitionResponse;
import com.amazon.alexa.accessory.capabilities.crypto.state.event.KeyExchangeEvent;
import com.amazon.alexa.accessory.capabilities.speech.AccessoryIdentifierProvider;
import com.amazon.alexa.accessory.crypto.CryptoKeyDataStore;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.MetricsConstants;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.repositories.crypto.KeyExchangeProvider;
import com.amazon.alexa.accessory.streams.control.ControlMessageHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.amazon.alexa.accessory.transport.TransportFeature;
import com.amazon.alexa.accessory.transport.TransportFeaturesRepository;
import com.amazon.dee.app.dependencies.ServiceModule;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public class KeyExchangeCapability extends AccessoryCapability {
    private BufferedMessage bufferedMessage;
    private final IrrecoverableErrorCallback callback;
    private KeyExchangeState currentState;
    private final CryptoKeyDataStore dataStore;
    private final AccessoryIdentifierProvider identifierProvider;
    private volatile boolean isDisposed;
    private final KeyExchangeProvider keyExchangeProvider;
    private final Object lock;
    private final ControlMessageHandler messageHandler;
    private final KeyExchangeMetrics metrics;
    private volatile ControlStream stream;
    private CancellableRunnable timeoutRunnable;
    private volatile Disposable transportFeaturesDisposable;
    private final TransportFeaturesRepository transportFeaturesRepository;
    private final Handler uiThreadHandler;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class BufferedMessage {
        private final Accessories.Command command;
        private final Object message;
        private final ControlStream stream;

        private BufferedMessage(ControlStream controlStream, Accessories.Command command, Object obj) {
            this.stream = controlStream;
            this.command = command;
            this.message = obj;
        }
    }

    /* loaded from: classes.dex */
    public interface IrrecoverableErrorCallback {
        void onIrrecoverableError();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static final class StreamData {
        private final Accessories.Command command;
        private final Accessories.Response response;
        private final ControlStream stream;

        private StreamData(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
            Preconditions.notNull(controlStream, "stream");
            Preconditions.notNull(command, "command");
            Preconditions.notNull(response, "response");
            this.stream = controlStream;
            this.command = command;
            this.response = response;
        }
    }

    public KeyExchangeCapability(AccessoryIdentifierProvider accessoryIdentifierProvider, KeyExchangeProvider keyExchangeProvider, TransportFeaturesRepository transportFeaturesRepository, CryptoKeyDataStore cryptoKeyDataStore) {
        this(accessoryIdentifierProvider, keyExchangeProvider, transportFeaturesRepository, cryptoKeyDataStore, $$Lambda$KeyExchangeCapability$zG70vxSH8_mhPN3tTEz2TBtVcGM.INSTANCE);
    }

    static /* synthetic */ void lambda$new$0() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onTransportFeaturesAvailable(Set<TransportFeature> set) {
        synchronized (this.lock) {
            performTransition(this.currentState.nextState(KeyExchangeEvent.TRANSPORT_FEATURES_DISCOVERED, set, this.identifierProvider.getIdentifier(), this.dataStore, this.metrics), null);
            if (this.bufferedMessage != null) {
                final BufferedMessage bufferedMessage = this.bufferedMessage;
                this.bufferedMessage = null;
                this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.crypto.-$$Lambda$KeyExchangeCapability$ciWe5Lqdl84C5216T6VJDGLjP6Q
                    @Override // java.lang.Runnable
                    public final void run() {
                        KeyExchangeCapability.this.lambda$onTransportFeaturesAvailable$1$KeyExchangeCapability(bufferedMessage);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void performTransition(KeyExchangeState keyExchangeState, StreamData streamData) {
        Logger.d("KeyExchange: Setting state from %s to %s", this.currentState, keyExchangeState);
        CancellableRunnable cancellableRunnable = this.timeoutRunnable;
        if (cancellableRunnable != null) {
            cancellableRunnable.cancel();
            this.timeoutRunnable = null;
        }
        this.currentState = keyExchangeState;
        this.uiThreadHandler.post(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.crypto.-$$Lambda$KeyExchangeCapability$MBoQom7P_4_cXJX-9W1DOy_xLBM
            @Override // java.lang.Runnable
            public final void run() {
                KeyExchangeCapability.this.lambda$performTransition$2$KeyExchangeCapability();
            }
        });
        TimeoutCondition timeoutCondition = this.currentState.getTimeoutCondition();
        if (timeoutCondition != null) {
            Logger.d("Scheduling a timeout, %dms until %s", Long.valueOf(timeoutCondition.getTimeoutMs()), timeoutCondition.getOnTimeoutState());
            final KeyExchangeState onTimeoutState = timeoutCondition.getOnTimeoutState();
            this.timeoutRunnable = new CancellableRunnable(this.lock, new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.crypto.-$$Lambda$KeyExchangeCapability$NmcSr8U-kLrkqFWkFJ9oikJZPA0
                @Override // java.lang.Runnable
                public final void run() {
                    KeyExchangeCapability.this.lambda$performTransition$3$KeyExchangeCapability(onTimeoutState);
                }
            });
            this.uiThreadHandler.postDelayed(this.timeoutRunnable, timeoutCondition.getTimeoutMs());
        }
        if (streamData != null) {
            streamData.stream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(streamData.command).setResponse(streamData.response).mo10084build()));
            Logger.d("KeyExchange: Sending response to command " + streamData.command.name() + RealTimeTextConstants.COLON_SPACE + streamData.response);
        }
        if (KeyExchangeState.IRRECOVERABLE_ERROR == this.currentState) {
            Handler handler = this.uiThreadHandler;
            final IrrecoverableErrorCallback irrecoverableErrorCallback = this.callback;
            irrecoverableErrorCallback.getClass();
            handler.post(new Runnable() { // from class: com.amazon.alexa.accessory.capabilities.crypto.-$$Lambda$rf74TFYK8kTwr2byUYtK-mepKv4
                @Override // java.lang.Runnable
                public final void run() {
                    KeyExchangeCapability.IrrecoverableErrorCallback.this.onIrrecoverableError();
                }
            });
        }
    }

    public /* synthetic */ void lambda$onTransportFeaturesAvailable$1$KeyExchangeCapability(BufferedMessage bufferedMessage) {
        try {
            this.messageHandler.onMessageReceived(bufferedMessage.stream, bufferedMessage.command, bufferedMessage.message);
        } catch (Exception e) {
            Logger.e("Failed to handle buffered message from the accessory", e);
            throw new RuntimeException(e);
        }
    }

    public /* synthetic */ void lambda$performTransition$2$KeyExchangeCapability() {
        KeyExchangeProvider keyExchangeProvider = this.keyExchangeProvider;
        KeyExchangeState keyExchangeState = this.currentState;
        keyExchangeProvider.provideIsAwaitingDerivedKeys((keyExchangeState == KeyExchangeState.HAS_KEYS || keyExchangeState == KeyExchangeState.NO_KEYS_REQUIRED) ? false : true);
    }

    public /* synthetic */ void lambda$performTransition$3$KeyExchangeCapability(KeyExchangeState keyExchangeState) {
        if (this.isDisposed) {
            Logger.d("KeyExchange: Aborting timeout because KeyExchange capability was disposed.");
            this.timeoutRunnable = null;
            return;
        }
        if (KeyExchangeState.IRRECOVERABLE_ERROR == keyExchangeState) {
            this.metrics.completeWithError(MetricsConstants.KeyExchange.IrrecoverableErrorCause.TIMEOUT);
        }
        performTransition(keyExchangeState, null);
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        this.isDisposed = true;
        if (this.transportFeaturesDisposable != null) {
            this.transportFeaturesDisposable.dispose();
        }
        if (this.stream != null) {
            accessoryDescriptor.remove(this.stream);
        }
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        synchronized (this.lock) {
            if (this.currentState == null) {
                if (!this.isDisposed) {
                    this.metrics.onStart();
                    Logger.d("KeyExchange: Initializing capability");
                    this.stream = new ControlStream(accessoryDescriptor.getAuthenticationAgnosticDispatcher(), ControlStream.MessageAuthenticationMode.FORCE_UNAUTHENTICATED);
                    HashSet<Accessories.Command> hashSet = new HashSet();
                    for (KeyExchangeState keyExchangeState : KeyExchangeState.values()) {
                        hashSet.addAll(keyExchangeState.getAllSupportedCommands());
                    }
                    for (Accessories.Command command : hashSet) {
                        this.stream.addMessageHandler(command, this.messageHandler);
                    }
                    accessoryDescriptor.add(this.stream);
                    performTransition(KeyExchangeState.INITIALIZING, null);
                } else {
                    throw new IllegalStateException("Cannot initialize disposed KeyExchange Capability.");
                }
            } else {
                throw new IllegalStateException(String.format("Cannot re-initialize the KeyExchange Capability. Current State: %s", this.currentState));
            }
        }
        this.transportFeaturesDisposable = this.transportFeaturesRepository.queryTransportFeatures().subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.capabilities.crypto.-$$Lambda$KeyExchangeCapability$Ajzov1Svd9Rv9RwTBF_UIuEb8h0
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                KeyExchangeCapability.this.onTransportFeaturesAvailable((Set) obj);
            }
        });
    }

    public KeyExchangeCapability(AccessoryIdentifierProvider accessoryIdentifierProvider, KeyExchangeProvider keyExchangeProvider, TransportFeaturesRepository transportFeaturesRepository, CryptoKeyDataStore cryptoKeyDataStore, IrrecoverableErrorCallback irrecoverableErrorCallback) {
        this.lock = new Object();
        this.isDisposed = false;
        this.messageHandler = new ControlMessageHandler() { // from class: com.amazon.alexa.accessory.capabilities.crypto.KeyExchangeCapability.1
            @Override // com.amazon.alexa.accessory.streams.control.ControlMessageHandler
            public void onMessageReceived(ControlStream controlStream, Accessories.Command command, Object obj) {
                Logger.d("KeyExchange: Received %s while in state %s", command, KeyExchangeCapability.this.currentState);
                synchronized (KeyExchangeCapability.this.lock) {
                    if (KeyExchangeState.INITIALIZING == KeyExchangeCapability.this.currentState && KeyExchangeCapability.this.bufferedMessage == null) {
                        KeyExchangeCapability.this.bufferedMessage = new BufferedMessage(controlStream, command, obj);
                        return;
                    }
                    TransitionResponse response = KeyExchangeCapability.this.currentState.getResponse(command, obj, KeyExchangeCapability.this.identifierProvider.getIdentifier(), KeyExchangeCapability.this.dataStore, KeyExchangeCapability.this.metrics);
                    KeyExchangeCapability.this.performTransition(response.getNewState(), new StreamData(controlStream, command, response.getResponse()));
                }
            }
        };
        Preconditions.notNull(accessoryIdentifierProvider, "identifierProvider");
        Preconditions.notNull(keyExchangeProvider, "keyExchangeProvider");
        Preconditions.notNull(transportFeaturesRepository, "transportFeaturesRepository");
        Preconditions.notNull(cryptoKeyDataStore, ServiceModule.DATA_STORE);
        Preconditions.notNull(irrecoverableErrorCallback, "callback");
        this.uiThreadHandler = new Handler(Looper.getMainLooper());
        this.identifierProvider = accessoryIdentifierProvider;
        this.keyExchangeProvider = keyExchangeProvider;
        this.transportFeaturesRepository = transportFeaturesRepository;
        this.dataStore = cryptoKeyDataStore;
        this.callback = irrecoverableErrorCallback;
        this.metrics = new KeyExchangeMetrics();
    }
}
