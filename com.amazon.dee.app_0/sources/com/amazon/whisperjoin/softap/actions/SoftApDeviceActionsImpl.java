package com.amazon.whisperjoin.softap.actions;

import android.content.Context;
import android.util.Log;
import com.amazon.whispercloak.Payload;
import com.amazon.whispercloak.SecureChannel;
import com.amazon.whispercloak.error.SecureChannelInitializationError;
import com.amazon.whisperjoin.softap.exceptions.SoftAPChannelUnsecuredException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidBssidException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidSsidException;
import com.amazon.whisperjoin.softap.helpers.RegistrationTokenHelper;
import com.amazon.whisperjoin.softap.helpers.WifiConfigurationHelper;
import com.amazon.whisperjoin.softap.observables.SoftApConnectionSubscriber;
import com.amazon.whisperjoin.softap.pojos.DeviceDetails;
import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
import com.amazon.whisperjoin.softap.pojos.RegistrationToken;
import com.amazon.whisperjoin.softap.pojos.WifiConfiguration;
import com.amazon.whisperjoin.softap.security.DefaultSecureChannelProvider;
import com.amazon.whisperjoin.softap.security.SecureChannelProvider;
import com.amazon.whisperjoin.softap.security.SecureChannelWrapper;
import com.amazon.whisperjoin.softap.serializer.SerializerFactory;
import com.amazon.whisperjoin.softap.wifi.SoftApNetworkStateController;
import com.amazon.whisperjoin.softap.wifi.SoftApNetworkStateControllerImpl;
import com.google.common.base.Optional;
import com.google.common.base.Strings;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Single;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
/* loaded from: classes13.dex */
public class SoftApDeviceActionsImpl implements SoftApDeviceActions {
    private static final long CONNECTION_NOTIFICATION_DELAY_MS = 5000;
    private static final String TAG = "SoftApDeviceActionsImpl";
    private Optional<SecureChannel> optionalSecureChannel;
    private final SecureChannelProvider secureChannelProvider;
    private final SoftApNetworkStateController softApNetworkStateController;
    private final SoftApOperationFactory softApOperationFactory;

    public SoftApDeviceActionsImpl(Context context) {
        this(new DefaultSecureChannelProvider(), new SoftApOperationFactoryImpl(), new SoftApNetworkStateControllerImpl(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Single<SecureChannel> createSecureChannel(List<Integer> list) {
        Optional<SecureChannelWrapper> createNewSecureChannel = this.secureChannelProvider.createNewSecureChannel(list);
        if (!createNewSecureChannel.isPresent()) {
            return Single.just(null);
        }
        SecureChannelWrapper secureChannelWrapper = createNewSecureChannel.get();
        final SecureChannel secureChannel = secureChannelWrapper.getSecureChannel();
        return this.softApOperationFactory.sendPublicKey(secureChannel.getPemEncodedPublicKey(), secureChannelWrapper.getScheme()).flatMap(new Func1<Void, Single<? extends String>>() { // from class: com.amazon.whisperjoin.softap.actions.SoftApDeviceActionsImpl.6
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Single<String> mo13102call(Void r1) {
                String unused = SoftApDeviceActionsImpl.TAG;
                return SoftApDeviceActionsImpl.this.softApOperationFactory.getPublicKey();
            }
        }).map(new Func1<String, SecureChannel>() { // from class: com.amazon.whisperjoin.softap.actions.SoftApDeviceActionsImpl.5
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public SecureChannel mo13102call(String str) {
                try {
                    String unused = SoftApDeviceActionsImpl.TAG;
                    secureChannel.init(str);
                    return secureChannel;
                } catch (SecureChannelInitializationError e) {
                    Log.e(SoftApDeviceActionsImpl.TAG, "Failed to initialize secure channel", e);
                    throw new RuntimeException(e);
                }
            }
        }).toObservable().doOnError(new Action1<Throwable>() { // from class: com.amazon.whisperjoin.softap.actions.SoftApDeviceActionsImpl.4
            @Override // rx.functions.Action1
            public void call(Throwable th) {
                Log.e(SoftApDeviceActionsImpl.TAG, "Failed to setup secure channel", th);
            }
        }).toSingle();
    }

    private byte[] createSecureMessage(Object obj) {
        return this.optionalSecureChannel.get().encrypt(new Payload(SerializerFactory.getInstance().getByteSerializer(obj.getClass()).serialize(obj), null));
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApDeviceActions
    public Observable<Void> connect(final String str) throws SoftAPInvalidSsidException {
        if (!Strings.isNullOrEmpty(str)) {
            String.format("Connecting to Soft AP with SSID %s", str);
            return Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.whisperjoin.softap.actions.SoftApDeviceActionsImpl.1
                @Override // rx.functions.Action1
                public void call(Subscriber<? super Void> subscriber) {
                    SoftApDeviceActionsImpl.this.softApNetworkStateController.connectToSoftAp(new SoftApConnectionSubscriber(subscriber), str);
                }
            }).delay(5000L, TimeUnit.MILLISECONDS);
        }
        SoftAPInvalidSsidException softAPInvalidSsidException = new SoftAPInvalidSsidException("SSID cannot be null or empty");
        Log.e(TAG, "SSID is null or empty", softAPInvalidSsidException);
        throw softAPInvalidSsidException;
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApDeviceActions
    public void disconnect() {
        this.optionalSecureChannel = Optional.absent();
        this.softApNetworkStateController.restoreNetworkState();
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApDeviceActions
    public Single<DeviceDetails> getDeviceDetails() {
        return this.softApOperationFactory.getDeviceDetails().flatMap(new Func1<DeviceDetails, Single<? extends DeviceDetails>>() { // from class: com.amazon.whisperjoin.softap.actions.SoftApDeviceActionsImpl.3
            @Override // rx.functions.Func1
            /* renamed from: call  reason: avoid collision after fix types in other method */
            public Single<? extends DeviceDetails> mo13102call(final DeviceDetails deviceDetails) {
                return SoftApDeviceActionsImpl.this.createSecureChannel(deviceDetails.getSchemes()).map(new Func1<SecureChannel, DeviceDetails>() { // from class: com.amazon.whisperjoin.softap.actions.SoftApDeviceActionsImpl.3.1
                    @Override // rx.functions.Func1
                    /* renamed from: call  reason: avoid collision after fix types in other method */
                    public DeviceDetails mo13102call(SecureChannel secureChannel) {
                        SoftApDeviceActionsImpl.this.optionalSecureChannel = Optional.of(secureChannel);
                        return deviceDetails;
                    }
                });
            }
        });
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApDeviceActions
    public Single<Void> provisionDevice(WifiConfiguration wifiConfiguration) throws SoftAPChannelUnsecuredException {
        if (this.optionalSecureChannel.isPresent()) {
            return this.softApOperationFactory.secureProvisionDevice(createSecureMessage(WifiConfigurationHelper.toWhisperjoinWifiConfiguration(wifiConfiguration)));
        }
        SoftAPChannelUnsecuredException softAPChannelUnsecuredException = new SoftAPChannelUnsecuredException("Channel must be secured to provision device");
        Log.e(TAG, "Unsecured channel", softAPChannelUnsecuredException);
        throw softAPChannelUnsecuredException;
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApDeviceActions
    public Single<Void> saveProvisioningData(ProvisioningData provisioningData) {
        return this.softApOperationFactory.saveProvisioningData(provisioningData);
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApDeviceActions
    public Single<Void> saveRegistrationToken(RegistrationToken registrationToken) throws SoftAPChannelUnsecuredException {
        if (this.optionalSecureChannel.isPresent()) {
            return this.softApOperationFactory.secureSaveRegistrationToken(createSecureMessage(RegistrationTokenHelper.toWhisperjoinRegistrationToken(registrationToken)));
        }
        SoftAPChannelUnsecuredException softAPChannelUnsecuredException = new SoftAPChannelUnsecuredException("Channel must be secured to save registration token");
        Log.e(TAG, "Unsecured channel", softAPChannelUnsecuredException);
        throw softAPChannelUnsecuredException;
    }

    SoftApDeviceActionsImpl(SecureChannelProvider secureChannelProvider, SoftApOperationFactory softApOperationFactory, SoftApNetworkStateController softApNetworkStateController) {
        this.secureChannelProvider = secureChannelProvider;
        this.softApOperationFactory = softApOperationFactory;
        this.softApNetworkStateController = softApNetworkStateController;
        softApNetworkStateController.saveNetworkState();
    }

    @Override // com.amazon.whisperjoin.softap.actions.SoftApDeviceActions
    public Observable<Void> connect(final String str, final String str2) throws SoftAPInvalidSsidException, SoftAPInvalidBssidException {
        if (!Strings.isNullOrEmpty(str)) {
            if (!Strings.isNullOrEmpty(str2)) {
                String.format("Connecting to Soft AP with SSID %s and BSSID %s", str, str2);
                return Observable.create(new Observable.OnSubscribe<Void>() { // from class: com.amazon.whisperjoin.softap.actions.SoftApDeviceActionsImpl.2
                    @Override // rx.functions.Action1
                    public void call(Subscriber<? super Void> subscriber) {
                        SoftApDeviceActionsImpl.this.softApNetworkStateController.connectToSoftAp(new SoftApConnectionSubscriber(subscriber), str, str2);
                    }
                }).delay(5000L, TimeUnit.MILLISECONDS);
            }
            SoftAPInvalidBssidException softAPInvalidBssidException = new SoftAPInvalidBssidException("BSSID cannot be null or empty");
            Log.e(TAG, "BSSID is null or empty", softAPInvalidBssidException);
            throw softAPInvalidBssidException;
        }
        SoftAPInvalidSsidException softAPInvalidSsidException = new SoftAPInvalidSsidException("SSID cannot be null or empty");
        Log.e(TAG, "SSID is null or empty", softAPInvalidSsidException);
        throw softAPInvalidSsidException;
    }
}
