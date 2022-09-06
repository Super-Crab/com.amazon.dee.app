package com.amazon.alexa.accessory.internal.session;

import android.annotation.SuppressLint;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessoryCapabilities;
import com.amazon.alexa.accessory.AccessoryConnection;
import com.amazon.alexa.accessory.AccessoryInquiryRecord;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessory.AccessoryTransport;
import com.amazon.alexa.accessory.capabilities.device.DeviceCapability;
import com.amazon.alexa.accessory.internal.connection.QueueTransportDispatcher;
import com.amazon.alexa.accessory.internal.connection.UnsupportedTransportReceiver;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.internal.util.functions.Supplier;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.repositories.device.MemoryDeviceRepository;
import com.amazon.alexa.accessory.repositories.device.v2.DeviceRepositoryV2;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.transport.TransportReceiver;
import com.amazon.alexa.accessory.utils.feature.AccessoryFeature;
import com.amazon.alexa.accessory.utils.feature.FeatureChecker;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
/* loaded from: classes.dex */
public final class DefaultAccessoryInquirySession implements AccessoryInquirySession {
    private static final String TAG = "DefaultAccessoryInquirySession";
    private final Accessory accessory;
    private final AccessoryCapabilities capabilities;
    private AccessoryConnection connection;
    private final TransportReceiver defaultReceiver;
    private final MemoryDeviceRepository deviceRepository = new MemoryDeviceRepository();
    private final QueueTransportDispatcher dispatcher = new QueueTransportDispatcher();
    private final FeatureChecker featureChecker;
    private boolean released;
    private final AccessoryTransport.Factory transportFactory;

    public DefaultAccessoryInquirySession(Accessory accessory, AccessoryTransport.Factory factory, FeatureChecker featureChecker) {
        this.accessory = accessory;
        this.transportFactory = factory;
        this.featureChecker = featureChecker;
        QueueTransportDispatcher queueTransportDispatcher = this.dispatcher;
        this.capabilities = new AccessoryCapabilities(queueTransportDispatcher, queueTransportDispatcher);
        this.defaultReceiver = new UnsupportedTransportReceiver(this.dispatcher, ControlStream.MessageAuthenticationMode.FORCE_UNAUTHENTICATED, new Supplier() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$DefaultAccessoryInquirySession$U5Ke5kV9PqNxjDbZJO5XMq28Itg
            @Override // com.amazon.alexa.accessory.internal.util.functions.Supplier
            public final Object get() {
                return DefaultAccessoryInquirySession.this.lambda$new$0$DefaultAccessoryInquirySession();
            }
        }, AccessorySessionType.INQUIRY_SESSION);
        AccessoryCapabilities accessoryCapabilities = this.capabilities;
        MemoryDeviceRepository memoryDeviceRepository = this.deviceRepository;
        accessoryCapabilities.add(new DeviceCapability(memoryDeviceRepository, memoryDeviceRepository, new DeviceCapability.Callback() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$HVQl02dpSp9r6PEqSIjHEWbeKLw
            @Override // com.amazon.alexa.accessory.capabilities.device.DeviceCapability.Callback
            public final void onInvalidInformation() {
                DefaultAccessoryInquirySession.this.release();
            }
        }));
    }

    private void attachCapabilities(AccessoryConnection accessoryConnection) {
        this.dispatcher.setDispatcher(accessoryConnection.getDispatcher());
        this.capabilities.initialize();
        accessoryConnection.setReceiver(new TransportReceiver() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$DefaultAccessoryInquirySession$eJFj9hBgV-QE_D7Xi_FJQmziZ_w
            @Override // com.amazon.alexa.accessory.transport.TransportReceiver
            public final void onDataReceived(SizedSource sizedSource, int i) {
                DefaultAccessoryInquirySession.this.lambda$attachCapabilities$3$DefaultAccessoryInquirySession(sizedSource, i);
            }
        });
    }

    @Override // com.amazon.alexa.accessory.internal.session.AccessoryInquirySession
    @SuppressLint({"CheckResult"})
    public void connect() {
        Preconditions.mainThread();
        if (!this.released) {
            if (this.connection != null) {
                Logger.d("Session is already being established... connect() was called more than once!");
                return;
            }
            Logger.d("Connecting inquiry session");
            AccessorySessionOptions accessorySessionOptions = new AccessorySessionOptions();
            if (this.featureChecker.hasAccess(AccessoryFeature.VERSION_NOTIFICATION_RESPONSE)) {
                Logger.d("%s: Setting forceConnection flag for the connection", TAG);
                accessorySessionOptions.setForceConnection(true);
            }
            this.connection = new AccessoryConnection(this.transportFactory.createTransport(this.accessory, accessorySessionOptions), null, accessorySessionOptions, this.featureChecker);
            this.connection.addConnectionListener(new AccessoryConnection.SimpleConnectionListener());
            attachCapabilities(this.connection);
            this.connection.open();
            queryInquiryRecord().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$DefaultAccessoryInquirySession$D3nvUnwlNwraL4f4a43w6rQt-YI
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryInquirySession.this.lambda$connect$1$DefaultAccessoryInquirySession((AccessoryInquiryRecord) obj);
                }
            }, new Consumer() { // from class: com.amazon.alexa.accessory.internal.session.-$$Lambda$DefaultAccessoryInquirySession$pHG5BIxW6OSGyHHaefN1d97p-aU
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DefaultAccessoryInquirySession.this.lambda$connect$2$DefaultAccessoryInquirySession((Throwable) obj);
                }
            });
            return;
        }
        throw new IllegalStateException("This accessory session was released!");
    }

    public /* synthetic */ void lambda$attachCapabilities$3$DefaultAccessoryInquirySession(SizedSource sizedSource, int i) throws Exception {
        if (!this.capabilities.handleData(sizedSource, i)) {
            this.defaultReceiver.onDataReceived(sizedSource, i);
        }
    }

    public /* synthetic */ void lambda$connect$1$DefaultAccessoryInquirySession(AccessoryInquiryRecord accessoryInquiryRecord) throws Throwable {
        release();
    }

    public /* synthetic */ void lambda$connect$2$DefaultAccessoryInquirySession(Throwable th) throws Throwable {
        release();
    }

    public /* synthetic */ DeviceRepositoryV2 lambda$new$0$DefaultAccessoryInquirySession() {
        return this.deviceRepository;
    }

    @Override // com.amazon.alexa.accessory.internal.session.AccessoryInquirySession
    public Single<AccessoryInquiryRecord> queryInquiryRecord() {
        return this.deviceRepository.queryDeviceInformationSet().firstOrError().map($$Lambda$bHzS_PUAyMmv2vMEXvXKO3eeeWE.INSTANCE);
    }

    @Override // com.amazon.alexa.accessory.internal.session.AccessoryInquirySession
    public void release() {
        Preconditions.mainThread();
        if (this.released) {
            return;
        }
        this.released = true;
        AccessoryConnection accessoryConnection = this.connection;
        if (accessoryConnection != null) {
            accessoryConnection.close();
            this.connection = null;
        }
        this.capabilities.release();
        this.deviceRepository.release();
        Logger.d("Released inquiry session");
    }
}
