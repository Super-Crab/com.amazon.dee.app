package com.amazon.dee.sdk.iotsoftap.impl;

import android.content.Context;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.amazon.dee.sdk.iotsoftap.ErrorCodeMapper;
import com.amazon.dee.sdk.iotsoftap.IoTSoftApConfigurationProvider;
import com.amazon.dee.sdk.iotsoftap.IoTSoftApPresenter;
import com.amazon.dee.sdk.iotsoftap.IoTSoftApResponseCallback;
import com.amazon.dee.sdk.iotsoftap.RegistrationTokenProvider;
import com.amazon.whisperjoin.softap.AsyncSoftApActionsController;
import com.amazon.whisperjoin.softap.SoftApActionsController;
import com.amazon.whisperjoin.softap.callbacks.DeviceConnectionEstablishedCallback;
import com.amazon.whisperjoin.softap.callbacks.DeviceConnectionStateChangedListener;
import com.amazon.whisperjoin.softap.callbacks.OnDeviceDetailsFetchedCallback;
import com.amazon.whisperjoin.softap.callbacks.OnProvisioningDataSavedCallback;
import com.amazon.whisperjoin.softap.callbacks.OnRegistrationTokenSavedCallback;
import com.amazon.whisperjoin.softap.callbacks.OnWifiConfigurationSavedCallback;
import com.amazon.whisperjoin.softap.pojos.DeviceDetails;
import com.amazon.whisperjoin.softap.pojos.ProvisioningData;
import com.amazon.whisperjoin.softap.pojos.RegistrationToken;
import com.amazon.whisperjoin.softap.pojos.WifiConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import com.google.gson.GsonBuilder;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class SmartHomeIoTSoftApPresenterImpl implements IoTSoftApPresenter {
    private static final String TAG = "SmartHomeIoTSoftApPresenterImpl";
    private final SoftApActionsController controller;
    private final IoTSoftApResponseCallback ioTSoftAPResponseCallback;
    private final ProvisioningData provisioningData;
    private RegistrationToken registrationToken;
    private final RegistrationTokenProvider registrationTokenProvider;

    public SmartHomeIoTSoftApPresenterImpl(Context context, IoTSoftApConfigurationProvider ioTSoftApConfigurationProvider, IoTSoftApResponseCallback ioTSoftApResponseCallback) {
        this(ioTSoftApConfigurationProvider, new AsyncSoftApActionsController(context), ioTSoftApResponseCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectAndGetDeviceDetails(String str) {
        this.controller.establishNetworkConnection(str, new DeviceConnectionEstablishedCallback<Void>() { // from class: com.amazon.dee.sdk.iotsoftap.impl.SmartHomeIoTSoftApPresenterImpl.3
            @Override // com.amazon.whisperjoin.softap.callbacks.DeviceConnectionEstablishedCallback
            public void unableToEstablishConnection(Throwable th) {
                SmartHomeIoTSoftApPresenterImpl.this.publishError(th);
            }

            @Override // com.amazon.whisperjoin.softap.callbacks.DeviceConnectionEstablishedCallback
            public void onConnectionEstablished(Void r1) {
                SmartHomeIoTSoftApPresenterImpl.this.getDeviceDetails();
            }
        }, new DeviceConnectionStateChangedListener() { // from class: com.amazon.dee.sdk.iotsoftap.impl.-$$Lambda$SmartHomeIoTSoftApPresenterImpl$0zO6p2i6nRnLKLlsdYKp8wgecOM
            @Override // com.amazon.whisperjoin.softap.callbacks.DeviceConnectionStateChangedListener
            public final void onConnectionLost(Throwable th) {
                SmartHomeIoTSoftApPresenterImpl.this.publishError(th);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getDeviceDetails() {
        this.controller.getDeviceDetails(new OnDeviceDetailsFetchedCallback<DeviceDetails>() { // from class: com.amazon.dee.sdk.iotsoftap.impl.SmartHomeIoTSoftApPresenterImpl.4
            @Override // com.amazon.whisperjoin.softap.callbacks.OnDeviceDetailsFetchedCallback
            public void unableToFetchDeviceDetails(Throwable th) {
                SmartHomeIoTSoftApPresenterImpl.this.publishError(th);
            }

            @Override // com.amazon.whisperjoin.softap.callbacks.OnDeviceDetailsFetchedCallback
            public void onDeviceDetailsFetched(DeviceDetails deviceDetails) {
                try {
                    SmartHomeIoTSoftApPresenterImpl.this.ioTSoftAPResponseCallback.success(new GsonBuilder().create().toJson(deviceDetails), Constants.EVENT_SOFTAP_DEVICE_DETAILS_FETCHED);
                } catch (Exception e) {
                    IoTSoftApResponseCallback ioTSoftApResponseCallback = SmartHomeIoTSoftApPresenterImpl.this.ioTSoftAPResponseCallback;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Constants.EVENT_ERROR_UNEXPECTED);
                    outline107.append(e.toString());
                    ioTSoftApResponseCallback.error(outline107.toString());
                }
            }
        });
    }

    private ProvisioningData getProvisioningData(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return ProvisioningData.builder().utcTime(getUTCTime()).marketplace((String) map.get(Constants.PROVISIONING_DATA_MARKETPLACE_ID)).countryCode((String) map.get(Constants.PROVISIONING_DATA_COUNTRY_CODE)).build();
    }

    private static RegistrationTokenProvider getRegistrationTokenProvider(Map<String, Object> map) {
        if (map == null) {
            return null;
        }
        return (RegistrationTokenProvider) map.get(Constants.REGISTRATION_TOKEN_PROVIDER);
    }

    private static String getUTCTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(Constants.UTC));
        return simpleDateFormat.format(new Date());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishError(Throwable th) {
        try {
            this.ioTSoftAPResponseCallback.error(new JSONObject().put(Constants.SOFTAP_EXCEPTION_ERROR_CODE, ErrorCodeMapper.getErrorCode(th)).toString(), Constants.EVENT_SOFTAP_EXCEPTION);
        } catch (Exception e) {
            IoTSoftApResponseCallback ioTSoftApResponseCallback = this.ioTSoftAPResponseCallback;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Constants.EVENT_SOFTAP_EXCEPTION);
            outline107.append(e.toString());
            ioTSoftApResponseCallback.error(outline107.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendTokenAndProvisionDevice(final WifiConfiguration wifiConfiguration) {
        this.controller.saveRegistrationToken(this.registrationToken, new OnRegistrationTokenSavedCallback() { // from class: com.amazon.dee.sdk.iotsoftap.impl.SmartHomeIoTSoftApPresenterImpl.5
            @Override // com.amazon.whisperjoin.softap.callbacks.OnRegistrationTokenSavedCallback
            public void onErrorSavingRegistrationToken(Throwable th) {
                SmartHomeIoTSoftApPresenterImpl.this.publishError(th);
            }

            @Override // com.amazon.whisperjoin.softap.callbacks.OnRegistrationTokenSavedCallback
            public void onRegistrationTokenSaved() {
                SmartHomeIoTSoftApPresenterImpl.this.sendWifiConfiguration(wifiConfiguration);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendWifiConfiguration(WifiConfiguration wifiConfiguration) {
        this.controller.provisionDevice(wifiConfiguration, new OnWifiConfigurationSavedCallback() { // from class: com.amazon.dee.sdk.iotsoftap.impl.SmartHomeIoTSoftApPresenterImpl.6
            @Override // com.amazon.whisperjoin.softap.callbacks.OnWifiConfigurationSavedCallback
            public void onErrorSavingWifiConfiguration(Throwable th) {
                SmartHomeIoTSoftApPresenterImpl.this.publishError(th);
            }

            @Override // com.amazon.whisperjoin.softap.callbacks.OnWifiConfigurationSavedCallback
            public void onWifiConfigurationSaved() {
                SmartHomeIoTSoftApPresenterImpl.this.ioTSoftAPResponseCallback.success(Constants.EVENT_SOFTAP_WIFI_CONFIGURATION_SAVED);
            }
        });
    }

    @Override // com.amazon.dee.sdk.iotsoftap.IoTSoftApPresenter
    public void connect(final String str) {
        RegistrationTokenProvider registrationTokenProvider = this.registrationTokenProvider;
        if (registrationTokenProvider == null) {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("RegistrationTokenProvider can't be null");
            IoTSoftApResponseCallback ioTSoftApResponseCallback = this.ioTSoftAPResponseCallback;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107(Constants.EVENT_ERROR_UNEXPECTED);
            outline107.append(illegalArgumentException.toString());
            ioTSoftApResponseCallback.error(outline107.toString());
            return;
        }
        registrationTokenProvider.get().subscribe(new SingleObserver<String>() { // from class: com.amazon.dee.sdk.iotsoftap.impl.SmartHomeIoTSoftApPresenterImpl.1
            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onError(@NonNull Throwable th) {
                IoTSoftApResponseCallback ioTSoftApResponseCallback2 = SmartHomeIoTSoftApPresenterImpl.this.ioTSoftAPResponseCallback;
                StringBuilder outline1072 = GeneratedOutlineSupport1.outline107(Constants.EVENT_ERROR_UNEXPECTED);
                outline1072.append(th.toString());
                ioTSoftApResponseCallback2.error(outline1072.toString());
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSubscribe(@NonNull Disposable disposable) {
            }

            @Override // io.reactivex.rxjava3.core.SingleObserver
            public void onSuccess(@NonNull String str2) {
                SmartHomeIoTSoftApPresenterImpl.this.registrationToken = RegistrationToken.builder().token(str2).build();
                SmartHomeIoTSoftApPresenterImpl.this.connectAndGetDeviceDetails(str);
            }
        });
    }

    @Override // com.amazon.dee.sdk.iotsoftap.IoTSoftApPresenter
    public void disconnect() {
        this.controller.disconnect();
    }

    @Override // com.amazon.dee.sdk.iotsoftap.IoTSoftApPresenter
    public void provisionDevice(final WifiConfiguration wifiConfiguration) {
        this.controller.saveProvisioningData(this.provisioningData, new OnProvisioningDataSavedCallback() { // from class: com.amazon.dee.sdk.iotsoftap.impl.SmartHomeIoTSoftApPresenterImpl.2
            @Override // com.amazon.whisperjoin.softap.callbacks.OnProvisioningDataSavedCallback
            public void onErrorSavingProvisioningData(Throwable th) {
                SmartHomeIoTSoftApPresenterImpl.this.publishError(th);
            }

            @Override // com.amazon.whisperjoin.softap.callbacks.OnProvisioningDataSavedCallback
            public void onProvisioningDataSaved() {
                SmartHomeIoTSoftApPresenterImpl.this.sendTokenAndProvisionDevice(wifiConfiguration);
            }
        });
    }

    @VisibleForTesting
    SmartHomeIoTSoftApPresenterImpl(IoTSoftApConfigurationProvider ioTSoftApConfigurationProvider, SoftApActionsController softApActionsController, IoTSoftApResponseCallback ioTSoftApResponseCallback) {
        this.provisioningData = ioTSoftApConfigurationProvider.getProvisioningData();
        this.registrationTokenProvider = ioTSoftApConfigurationProvider.getRegistrationTokenProvider();
        this.ioTSoftAPResponseCallback = ioTSoftApResponseCallback;
        this.controller = softApActionsController;
    }
}
