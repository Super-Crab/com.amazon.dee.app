package com.amazon.whisperjoin.softap;

import android.content.Context;
import android.util.Log;
import com.amazon.whisperjoin.softap.actions.SoftApDeviceActions;
import com.amazon.whisperjoin.softap.actions.SoftApDeviceActionsImpl;
import com.amazon.whisperjoin.softap.callbacks.DeviceConnectionEstablishedCallback;
import com.amazon.whisperjoin.softap.callbacks.DeviceConnectionStateChangedListener;
import com.amazon.whisperjoin.softap.callbacks.OnDeviceDetailsFetchedCallback;
import com.amazon.whisperjoin.softap.callbacks.OnProvisioningDataSavedCallback;
import com.amazon.whisperjoin.softap.callbacks.OnRegistrationTokenSavedCallback;
import com.amazon.whisperjoin.softap.callbacks.OnWifiConfigurationSavedCallback;
import com.amazon.whisperjoin.softap.exceptions.SoftAPChannelUnsecuredException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidBssidException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPInvalidSsidException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPUnableToEstablishConnectionException;
import com.amazon.whisperjoin.softap.exceptions.SoftAPUnexpectedConnectionLostException;
import com.amazon.whisperjoin.softap.observables.rx.transforms.BasicServiceCall;
import com.amazon.whisperjoin.softap.observables.rx.transforms.SoftApServiceCall;
import com.amazon.whisperjoin.softap.pojos.DeviceDetails;
import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
import com.amazon.whisperjoin.softap.pojos.RegistrationToken;
import com.amazon.whisperjoin.softap.pojos.WifiConfiguration;
import java.util.concurrent.TimeUnit;
import rx.Scheduler;
import rx.SingleSubscriber;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
/* loaded from: classes13.dex */
public class AsyncSoftApActionsController implements SoftApActionsController {
    private static final long ATTEMPT_CONNECTION_TIMEOUT = TimeUnit.MINUTES.toMillis(5);
    private static final String TAG = "AsyncSoftApActionsController";
    private Subscription establishConnectionSubscription;
    private final Scheduler observeScheduler;
    private final Scheduler serialWorkScheduler;
    private final SoftApDeviceActions softApDeviceActions;
    private final BasicServiceCall softApServiceCall;

    public AsyncSoftApActionsController(Context context) {
        this(new SoftApDeviceActionsImpl(context), Schedulers.newThread(), AndroidSchedulers.mainThread());
    }

    @Override // com.amazon.whisperjoin.softap.SoftApActionsController
    public void disconnect() {
        this.softApDeviceActions.disconnect();
        Subscription subscription = this.establishConnectionSubscription;
        if (subscription != null && !subscription.isUnsubscribed()) {
            this.establishConnectionSubscription.unsubscribe();
        }
        this.establishConnectionSubscription = null;
    }

    @Override // com.amazon.whisperjoin.softap.SoftApActionsController
    public void establishNetworkConnection(String str, final DeviceConnectionEstablishedCallback deviceConnectionEstablishedCallback, final DeviceConnectionStateChangedListener deviceConnectionStateChangedListener) {
        try {
            this.establishConnectionSubscription = this.softApDeviceActions.connect(str).timeout(ATTEMPT_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS).subscribeOn(this.serialWorkScheduler).observeOn(this.observeScheduler).subscribe((Subscriber<? super Void>) new Subscriber<Void>() { // from class: com.amazon.whisperjoin.softap.AsyncSoftApActionsController.1
                @Override // rx.Observer
                public void onCompleted() {
                    String unused = AsyncSoftApActionsController.TAG;
                }

                @Override // rx.Observer
                public void onError(Throwable th) {
                    Log.e(AsyncSoftApActionsController.TAG, "Connected Device Error", th);
                    if (th instanceof SoftAPUnexpectedConnectionLostException) {
                        Log.e(AsyncSoftApActionsController.TAG, "Connection dropped", th);
                        deviceConnectionStateChangedListener.onConnectionLost(th);
                        return;
                    }
                    Log.e(AsyncSoftApActionsController.TAG, "Connection failed", th);
                    deviceConnectionEstablishedCallback.unableToEstablishConnection(new SoftAPUnableToEstablishConnectionException(th));
                }

                @Override // rx.Observer
                public void onNext(Void r2) {
                    String unused = AsyncSoftApActionsController.TAG;
                    deviceConnectionEstablishedCallback.onConnectionEstablished(r2);
                }
            });
        } catch (SoftAPInvalidSsidException e) {
            deviceConnectionEstablishedCallback.unableToEstablishConnection(e);
        }
    }

    @Override // com.amazon.whisperjoin.softap.SoftApActionsController
    public void getDeviceDetails(final OnDeviceDetailsFetchedCallback onDeviceDetailsFetchedCallback) {
        this.softApDeviceActions.getDeviceDetails().compose(this.softApServiceCall.single()).subscribe(new SingleSubscriber<DeviceDetails>() { // from class: com.amazon.whisperjoin.softap.AsyncSoftApActionsController.3
            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                Log.e(AsyncSoftApActionsController.TAG, "Error fetching Device Details", th);
                onDeviceDetailsFetchedCallback.unableToFetchDeviceDetails(th);
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(DeviceDetails deviceDetails) {
                onDeviceDetailsFetchedCallback.onDeviceDetailsFetched(deviceDetails);
            }
        });
    }

    @Override // com.amazon.whisperjoin.softap.SoftApActionsController
    public void provisionDevice(WifiConfiguration wifiConfiguration, final OnWifiConfigurationSavedCallback onWifiConfigurationSavedCallback) {
        try {
            this.softApDeviceActions.provisionDevice(wifiConfiguration).compose(this.softApServiceCall.single()).subscribe(new SingleSubscriber<Void>() { // from class: com.amazon.whisperjoin.softap.AsyncSoftApActionsController.6
                @Override // rx.SingleSubscriber
                public void onError(Throwable th) {
                    Log.e(AsyncSoftApActionsController.TAG, "Error saving Wifi configuration", th);
                    onWifiConfigurationSavedCallback.onErrorSavingWifiConfiguration(th);
                }

                @Override // rx.SingleSubscriber
                public void onSuccess(Void r1) {
                    String unused = AsyncSoftApActionsController.TAG;
                    onWifiConfigurationSavedCallback.onWifiConfigurationSaved();
                }
            });
        } catch (SoftAPChannelUnsecuredException e) {
            onWifiConfigurationSavedCallback.onErrorSavingWifiConfiguration(e);
        }
    }

    @Override // com.amazon.whisperjoin.softap.SoftApActionsController
    public void saveProvisioningData(ProvisioningData provisioningData, final OnProvisioningDataSavedCallback onProvisioningDataSavedCallback) {
        this.softApDeviceActions.saveProvisioningData(provisioningData).compose(this.softApServiceCall.single()).subscribe(new SingleSubscriber<Void>() { // from class: com.amazon.whisperjoin.softap.AsyncSoftApActionsController.4
            @Override // rx.SingleSubscriber
            public void onError(Throwable th) {
                Log.e(AsyncSoftApActionsController.TAG, "Error saving provisioning data", th);
                onProvisioningDataSavedCallback.onErrorSavingProvisioningData(th);
            }

            @Override // rx.SingleSubscriber
            public void onSuccess(Void r1) {
                String unused = AsyncSoftApActionsController.TAG;
                onProvisioningDataSavedCallback.onProvisioningDataSaved();
            }
        });
    }

    @Override // com.amazon.whisperjoin.softap.SoftApActionsController
    public void saveRegistrationToken(RegistrationToken registrationToken, final OnRegistrationTokenSavedCallback onRegistrationTokenSavedCallback) {
        try {
            this.softApDeviceActions.saveRegistrationToken(registrationToken).compose(this.softApServiceCall.single()).subscribe(new SingleSubscriber<Void>() { // from class: com.amazon.whisperjoin.softap.AsyncSoftApActionsController.5
                @Override // rx.SingleSubscriber
                public void onError(Throwable th) {
                    Log.e(AsyncSoftApActionsController.TAG, "Error saving registration token", th);
                    onRegistrationTokenSavedCallback.onErrorSavingRegistrationToken(th);
                }

                @Override // rx.SingleSubscriber
                public void onSuccess(Void r1) {
                    String unused = AsyncSoftApActionsController.TAG;
                    onRegistrationTokenSavedCallback.onRegistrationTokenSaved();
                }
            });
        } catch (SoftAPChannelUnsecuredException e) {
            onRegistrationTokenSavedCallback.onErrorSavingRegistrationToken(e);
        }
    }

    AsyncSoftApActionsController(SoftApDeviceActions softApDeviceActions, Scheduler scheduler, Scheduler scheduler2) {
        this.establishConnectionSubscription = null;
        this.softApDeviceActions = softApDeviceActions;
        this.serialWorkScheduler = scheduler;
        this.observeScheduler = scheduler2;
        this.softApServiceCall = new SoftApServiceCall(scheduler, scheduler2);
    }

    @Override // com.amazon.whisperjoin.softap.SoftApActionsController
    public void establishNetworkConnection(String str, String str2, final DeviceConnectionEstablishedCallback deviceConnectionEstablishedCallback, final DeviceConnectionStateChangedListener deviceConnectionStateChangedListener) {
        try {
            this.establishConnectionSubscription = this.softApDeviceActions.connect(str, str2).timeout(ATTEMPT_CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS).subscribeOn(this.serialWorkScheduler).observeOn(this.observeScheduler).subscribe((Subscriber<? super Void>) new Subscriber<Void>() { // from class: com.amazon.whisperjoin.softap.AsyncSoftApActionsController.2
                @Override // rx.Observer
                public void onCompleted() {
                    String unused = AsyncSoftApActionsController.TAG;
                }

                @Override // rx.Observer
                public void onError(Throwable th) {
                    Log.e(AsyncSoftApActionsController.TAG, "Connected Device Error", th);
                    if (th instanceof SoftAPUnexpectedConnectionLostException) {
                        Log.e(AsyncSoftApActionsController.TAG, "Connection dropped", th);
                        deviceConnectionStateChangedListener.onConnectionLost(th);
                        return;
                    }
                    Log.e(AsyncSoftApActionsController.TAG, "Connection failed", th);
                    deviceConnectionEstablishedCallback.unableToEstablishConnection(new SoftAPUnableToEstablishConnectionException(th));
                }

                @Override // rx.Observer
                public void onNext(Void r2) {
                    String unused = AsyncSoftApActionsController.TAG;
                    deviceConnectionEstablishedCallback.onConnectionEstablished(r2);
                }
            });
        } catch (SoftAPInvalidBssidException | SoftAPInvalidSsidException e) {
            deviceConnectionEstablishedCallback.unableToEstablishConnection(e);
        }
    }
}
