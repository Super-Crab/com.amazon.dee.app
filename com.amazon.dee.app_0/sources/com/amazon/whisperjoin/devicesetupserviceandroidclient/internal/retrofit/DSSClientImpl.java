package com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.clouddrive.internal.CloudDriveExceptionBuilder;
import com.amazon.devicesetupservice.ProvisioneeIdentifier;
import com.amazon.devicesetupservice.pwsync.v1.DistressDiscoveryInputParameters;
import com.amazon.devicesetupservice.pwsync.v1.DistressDiscoveryOutputParameters;
import com.amazon.devicesetupservice.pwsync.v1.DistressedProvisioneeDetails;
import com.amazon.devicesetupservice.pwsync.v1.GetWifiSyncAuthTokenInput;
import com.amazon.devicesetupservice.pwsync.v1.GetWifiSyncAuthTokenOutput;
import com.amazon.devicesetupservice.pwsync.v1.LocalNotificationInputParameters;
import com.amazon.devicesetupservice.pwsync.v1.LocalNotificationOutputParameters;
import com.amazon.devicesetupservice.pwsync.v1.LocalNotificationProvisioneeDetails;
import com.amazon.devicesetupservice.pwsync.v1.ValidateWifiSyncAuthTokenInput;
import com.amazon.devicesetupservice.pwsync.v1.ValidateWifiSyncAuthTokenOutput;
import com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsInput;
import com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsOutput;
import com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsV2Output;
import com.amazon.devicesetupservice.smarthome.MacIdentifier;
import com.amazon.devicesetupservice.smarthome.ProtocolType;
import com.amazon.devicesetupservice.smarthome.ReportSmartHomeEventInput;
import com.amazon.devicesetupservice.smarthome.ReportSmartHomeEventOutput;
import com.amazon.devicesetupservice.smarthome.SmartHomeDiscoveryInputParameters;
import com.amazon.devicesetupservice.smarthome.SmartHomeProvisioneeDetails;
import com.amazon.devicesetupservice.v1.AuthMaterialIdentifier;
import com.amazon.devicesetupservice.v1.BarcodeIdentifier;
import com.amazon.devicesetupservice.v1.ComputeConfigurationDataInput;
import com.amazon.devicesetupservice.v1.ComputeConfigurationDataOutput;
import com.amazon.devicesetupservice.v1.DeviceRegistrationState;
import com.amazon.devicesetupservice.v1.DiscoveredProvisionableDeviceInput;
import com.amazon.devicesetupservice.v1.DiscoveredProvisionableDeviceOutput;
import com.amazon.devicesetupservice.v1.DiscoveredProvisioneeInput;
import com.amazon.devicesetupservice.v1.DiscoveredProvisioneeOutput;
import com.amazon.devicesetupservice.v1.FinalizeEcdheAuthenticationSessionInput;
import com.amazon.devicesetupservice.v1.FinalizeEcdheAuthenticationSessionOutput;
import com.amazon.devicesetupservice.v1.FirstPartyProvisionerDetails;
import com.amazon.devicesetupservice.v1.GetCustomerProvisioneesSetupStatusInput;
import com.amazon.devicesetupservice.v1.GetCustomerProvisioneesSetupStatusOutput;
import com.amazon.devicesetupservice.v1.GetDeviceRegistrationStatusInput;
import com.amazon.devicesetupservice.v1.GetDeviceRegistrationStatusOutput;
import com.amazon.devicesetupservice.v1.GetWiFiNetworksInput;
import com.amazon.devicesetupservice.v1.GetWiFiNetworksOutput;
import com.amazon.devicesetupservice.v1.LegacyIdentifier;
import com.amazon.devicesetupservice.v1.ProvisioneeSetupInformation;
import com.amazon.devicesetupservice.v1.RecordDevicePossessionIntentInnerBarcodeInput;
import com.amazon.devicesetupservice.v1.RecordDevicePossessionIntentInnerBarcodeOutput;
import com.amazon.devicesetupservice.v1.ReportEventInput;
import com.amazon.devicesetupservice.v1.ReportEventOutput;
import com.amazon.devicesetupservice.v1.SaveWifiNetworkInput;
import com.amazon.devicesetupservice.v1.SaveWifiNetworkOutput;
import com.amazon.devicesetupservice.v1.StartEcdheAuthenticationSessionInput;
import com.amazon.devicesetupservice.v1.StartEcdheAuthenticationSessionOutput;
import com.amazon.devicesetupservice.wss1p.CreateAPForWifiProvisioneeInput;
import com.amazon.devicesetupservice.wss1p.CreateAPForWifiProvisioneeOutput;
import com.amazon.devicesetupservice.wss1p.DiscoveredDevice;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.Constants;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSOperation;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.components.DaggerDSSComponent;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.AuthModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.ClockModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.ContextModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.NetworkingModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.RxModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.dagger.modules.SharedPreferencesModule;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.AbstractDiscoveredProvisioneeRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.AuthMaterialIdentifier;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.BarcodeIdentifier;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ComputeConfigurationDataRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ComputeConfigurationDataResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.CreateAPForWifiProvisioneeRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.CreateAPForWifiProvisioneeResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredDistressedProvisioneeRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredLocalNotificationProvisioneeRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredLocalNotificationProvisioneeResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredProvisionableDeviceRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredSmartHomeProvisioneeRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.DiscoveredSmartHomeProvisioneeResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.FFSWhiteListPolicyRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.FFSWhiteListPolicyResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.FinalizeEcdheAuthenticationSessionRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.FinalizeEcdheAuthenticationSessionResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GenericDSSDiscoveryResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerDevicesCredentialsRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerDevicesCredentialsResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerProvisioneesSetupStatusRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetCustomerProvisioneesSetupStatusResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetRegistrationStatusRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetRegistrationStatusResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetWiFiSyncAuthTokenRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.GetWiFiSyncAuthTokenResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.LegacyIdentifierData;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ProvisioneeSetupStatus;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.RecordDevicePossessionIntentInnerBarcodeRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ReportEventRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ReportSmartHomeEventRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.StartEcdheAuthenticationSessionRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.StartEcdheAuthenticationSessionResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ValidateWiFiSyncAuthTokenRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ValidateWiFiSyncAuthTokenResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSClientError;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.error.DSSServiceError;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.identity.MapAccessTokenProvider;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Clock;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.Iso8601TimeConverter;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.util.SharedPreferencesProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.net.HttpHeaders;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import java.nio.ByteBuffer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Named;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.converter.jackson.JacksonConverterFactory;
/* loaded from: classes13.dex */
public class DSSClientImpl implements DSSClient {
    private static final int MAX_RETRIES = 5;
    private static final String NEXT_CONTACT_TIMESTAMP_MS_KEY = "NextContactTimestampMs";
    private static final int RETRY_DELAY_SEC = 30;
    private static final String SHARED_PREFERENCES_SCOPE = "DssClient";
    private static final String TAG = GeneratedOutlineSupport1.outline39(DSSClientImpl.class, GeneratedOutlineSupport1.outline107(Constants.LOG_PREFIX));
    private static DSSClient sStaticInstance;
    @Inject
    JacksonConverterFactory jacksonConverterFactory;
    @Inject
    MapAccessTokenProvider mAccessTokenProvider;
    @Inject
    @Named(RxModule.BACKGROUND_SCHEDULER)
    Scheduler mBackgroundScheduler;
    @Inject
    Clock mClock;
    @Inject
    DSSRetrofitInterface mDssApi;
    @Inject
    FFSApiGatewayInterface mFFSApiGatewayInterface;
    @Inject
    @Named(RxModule.MAIN_THREAD_SCHEDULER)
    Scheduler mMainThreadScheduler;
    String mRegionSpecificUrl;
    @Inject
    SharedPreferencesProvider mSharedPreferencesProvider;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes13.dex */
    public class CallDssOnSubscribe<T> implements SingleOnSubscribe<Response<T>> {
        private final Call<T> mCall;
        private final DSSOperation mDSSOperation;

        public CallDssOnSubscribe(Call<T> call, DSSOperation dSSOperation) {
            this.mCall = call;
            this.mDSSOperation = dSSOperation;
        }

        private Long getPersistedRetryAfterTime() {
            return Long.valueOf(DSSClientImpl.this.mSharedPreferencesProvider.get(DSSClientImpl.SHARED_PREFERENCES_SCOPE).getLong(DSSClientImpl.NEXT_CONTACT_TIMESTAMP_MS_KEY, 0L));
        }

        private Long getRetryAfterTime(Response response) {
            String str = response.headers().get(HttpHeaders.RETRY_AFTER);
            if (str == null) {
                return null;
            }
            if (str.matches("[0-9]+")) {
                return Long.valueOf((Long.parseLong(str) * 1000) + DSSClientImpl.this.mClock.currentTimeMillis());
            }
            try {
                return Long.valueOf(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US).parse(str).getTime());
            } catch (ParseException e) {
                Log.e(DSSClientImpl.TAG, String.format(Locale.ENGLISH, "Unable to parse retry-after header value. String: [%s]", str), e);
                return null;
            }
        }

        private void persistOrCleanRetryAfterTime(Long l) {
            SharedPreferences sharedPreferences = DSSClientImpl.this.mSharedPreferencesProvider.get(DSSClientImpl.SHARED_PREFERENCES_SCOPE);
            if (l != null) {
                String str = DSSClientImpl.TAG;
                Log.i(str, "Persisting retry after call on " + sharedPreferences);
                sharedPreferences.edit().putLong(DSSClientImpl.NEXT_CONTACT_TIMESTAMP_MS_KEY, l.longValue()).apply();
                return;
            }
            String str2 = DSSClientImpl.TAG;
            Log.i(str2, "Clearing retry after call on " + sharedPreferences);
            sharedPreferences.edit().remove(DSSClientImpl.NEXT_CONTACT_TIMESTAMP_MS_KEY).apply();
        }

        @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
        public void subscribe(@NonNull SingleEmitter<Response<T>> singleEmitter) throws Exception {
            Log.i(DSSClientImpl.TAG, String.format(Locale.ENGLISH, "Making request - Operation: [%s]", this.mDSSOperation.name()));
            Long persistedRetryAfterTime = getPersistedRetryAfterTime();
            long currentTimeMillis = DSSClientImpl.this.mClock.currentTimeMillis();
            if (persistedRetryAfterTime.longValue() > currentTimeMillis) {
                Log.e(DSSClientImpl.TAG, String.format(Locale.ENGLISH, "Request throttled - requested callback after time %d is after current time %d", persistedRetryAfterTime, Long.valueOf(currentTimeMillis)));
                if (singleEmitter.isDisposed()) {
                    return;
                }
                singleEmitter.onError(new DSSServiceError(CloudDriveExceptionBuilder.TOO_MANY_REQUEST_CODE, "Request Throttled By Client", persistedRetryAfterTime));
                return;
            }
            try {
                Response<T> execute = this.mCall.mo13082clone().execute();
                String str = execute.headers().get("x-amzn-RequestId");
                int code = execute.code();
                String message = execute.message();
                Log.i(DSSClientImpl.TAG, String.format(Locale.ENGLISH, "Response - Code: [%d]", Integer.valueOf(code)));
                String str2 = DSSClientImpl.TAG;
                Locale locale = Locale.ENGLISH;
                Object[] objArr = new Object[1];
                if (str == null) {
                    str = "NONE";
                }
                objArr[0] = str;
                Log.i(str2, String.format(locale, "Response - RequestID: [%s]", objArr));
                if (!execute.isSuccessful()) {
                    Long retryAfterTime = getRetryAfterTime(execute);
                    if (retryAfterTime != null) {
                        Log.i(DSSClientImpl.TAG, String.format(Locale.ENGLISH, "Response - Retry After: [%d]", Long.valueOf(retryAfterTime.longValue())));
                    } else {
                        Log.i(DSSClientImpl.TAG, "No retry-after value provided");
                    }
                    persistOrCleanRetryAfterTime(retryAfterTime);
                    if (singleEmitter.isDisposed()) {
                        return;
                    }
                    singleEmitter.onError(new DSSServiceError(code, message, retryAfterTime));
                } else if (singleEmitter.isDisposed()) {
                } else {
                    singleEmitter.onSuccess(execute);
                }
            } catch (Exception e) {
                Log.e(DSSClientImpl.TAG, String.format(Locale.ENGLISH, "An Exception occurred while making request - ExceptionClass: [%s]", e.getClass().getSimpleName()));
                if (singleEmitter.isDisposed()) {
                    return;
                }
                singleEmitter.onError(e);
            }
        }
    }

    /* loaded from: classes13.dex */
    private static class WrapFailureCauseWithDSSClientError<T> implements Function<Throwable, SingleSource<? extends T>> {
        private final DSSOperation mDSSOperation;

        public WrapFailureCauseWithDSSClientError(DSSOperation dSSOperation) {
            this.mDSSOperation = dSSOperation;
        }

        @Override // io.reactivex.rxjava3.functions.Function
        /* renamed from: apply  reason: avoid collision after fix types in other method */
        public SingleSource<? extends T> mo10358apply(@NonNull Throwable th) throws Exception {
            return Single.error(new DSSClientError(th, this.mDSSOperation));
        }
    }

    private DSSClientImpl(Context context, DSSServiceConfiguration dSSServiceConfiguration) {
        this(new ContextModule(context), new AuthModule(), new NetworkingModule(dSSServiceConfiguration), new RxModule(), new ClockModule(), new SharedPreferencesModule());
    }

    public static DSSClient create(Context context, DSSServiceConfiguration dSSServiceConfiguration) {
        return new DSSClientImpl(context, dSSServiceConfiguration);
    }

    public static DSSClient createOrGetStaticInstance(Context context, DSSServiceConfiguration dSSServiceConfiguration) {
        DSSClient dSSClient = sStaticInstance;
        if (dSSClient != null) {
            return dSSClient;
        }
        sStaticInstance = new DSSClientImpl(context, dSSServiceConfiguration);
        return sStaticInstance;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ProvisioneeSetupStatus createProvisioneeSetupStatus(ProvisioneeSetupInformation provisioneeSetupInformation) {
        ProvisioneeSetupStatus.Builder provisioningStatus = new ProvisioneeSetupStatus.Builder().setErrorCode(provisioneeSetupInformation.getErrorCode()).setLastUpdatedTime(provisioneeSetupInformation.getLastUpdatedTime()).setProvisioningMethod(provisioneeSetupInformation.getProvisioningMethod()).setProvisioningState(provisioneeSetupInformation.getProvisioningState()).setProvisioningStatus(provisioneeSetupInformation.getProvisioningStatus());
        AuthMaterialIdentifier authMaterialIdentifier = provisioneeSetupInformation.getAuthMaterialIdentifier();
        if (authMaterialIdentifier != null) {
            provisioningStatus.setAuthMaterialIdentifier(new AuthMaterialIdentifier.Builder().setAuthMaterialIndex(authMaterialIdentifier.getAuthMaterialIndex()).setProductIndex(authMaterialIdentifier.getProductIndex()).build());
        }
        LegacyIdentifier provisionerInformation = provisioneeSetupInformation.getProvisionerInformation();
        if (provisionerInformation != null) {
            provisioningStatus.setProvisionerInformation(new LegacyIdentifierData.Builder().setDeviceType(provisionerInformation.getDeviceType()).setDsn(provisionerInformation.getDsn()).build());
        }
        ProvisioneeIdentifier provisioneeIdentifier = provisioneeSetupInformation.getProvisioneeIdentifier();
        if (provisioneeIdentifier instanceof com.amazon.devicesetupservice.v1.AuthMaterialIdentifier) {
            com.amazon.devicesetupservice.v1.AuthMaterialIdentifier authMaterialIdentifier2 = (com.amazon.devicesetupservice.v1.AuthMaterialIdentifier) provisioneeIdentifier;
            provisioningStatus.setProvisioneeAuthMaterialIdentifier(new AuthMaterialIdentifier.Builder().setAuthMaterialIndex(authMaterialIdentifier2.getAuthMaterialIndex()).setProductIndex(authMaterialIdentifier2.getProductIndex()).build());
        } else if (provisioneeIdentifier instanceof BarcodeIdentifier) {
            provisioningStatus.setProvisioneeBarcodeIdentifier(new BarcodeIdentifier.Builder().setBarcodeData(((com.amazon.devicesetupservice.v1.BarcodeIdentifier) provisioneeIdentifier).getBarcodeData()).build());
        } else {
            String str = TAG;
            Log.w(str, "Unrecognized ProvisioneeIdentifier" + provisioneeIdentifier);
        }
        return provisioningStatus.build();
    }

    static ReportSmartHomeEventInput createReportSmartHomeEventInput(ReportSmartHomeEventRequest reportSmartHomeEventRequest) {
        ReportSmartHomeEventInput reportSmartHomeEventInput = new ReportSmartHomeEventInput();
        reportSmartHomeEventInput.setEvent(reportSmartHomeEventRequest.getEvent());
        reportSmartHomeEventInput.setState(reportSmartHomeEventRequest.getState());
        reportSmartHomeEventInput.setSequenceNumber(reportSmartHomeEventRequest.getSequenceNumber());
        reportSmartHomeEventInput.setProvisionerInfo(reportSmartHomeEventRequest.getProvisionerInfo());
        reportSmartHomeEventInput.setErrorInfo(reportSmartHomeEventRequest.getErrorInfo());
        reportSmartHomeEventInput.setProductIndex(reportSmartHomeEventRequest.getProductIndex());
        reportSmartHomeEventInput.setRadio(reportSmartHomeEventRequest.getRadio());
        reportSmartHomeEventInput.setProvisioningMethod(reportSmartHomeEventRequest.getProvisioningMethod());
        ArrayList arrayList = new ArrayList();
        if (reportSmartHomeEventRequest.getBleMac() != null) {
            MacIdentifier macIdentifier = new MacIdentifier();
            macIdentifier.setProtocolType(ProtocolType.BLE);
            macIdentifier.setMacAddress(reportSmartHomeEventRequest.getBleMac());
            arrayList.add(macIdentifier);
        }
        if (reportSmartHomeEventRequest.getZigbeeMac() != null) {
            MacIdentifier macIdentifier2 = new MacIdentifier();
            macIdentifier2.setProtocolType(ProtocolType.ZIGBEE);
            macIdentifier2.setMacAddress(reportSmartHomeEventRequest.getZigbeeMac());
            arrayList.add(macIdentifier2);
        }
        reportSmartHomeEventInput.setProvisioneeMacIdentifiers(arrayList);
        return reportSmartHomeEventInput;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DiscoveredProvisioneeInput getDiscoveredDistressedProvisioneeInput(DiscoveredDistressedProvisioneeRequest discoveredDistressedProvisioneeRequest) {
        DiscoveredProvisioneeInput discoveredProvisioneeInput = new DiscoveredProvisioneeInput();
        FirstPartyProvisionerDetails provisionerDetails = getProvisionerDetails(discoveredDistressedProvisioneeRequest);
        DistressDiscoveryInputParameters distressDiscoveryInputParameters = new DistressDiscoveryInputParameters();
        distressDiscoveryInputParameters.setTrustMethod(discoveredDistressedProvisioneeRequest.getTrustMethod());
        distressDiscoveryInputParameters.setProvisioningMethod(discoveredDistressedProvisioneeRequest.getProvisioningMethod());
        distressDiscoveryInputParameters.setNonce(discoveredDistressedProvisioneeRequest.getNonce());
        DistressedProvisioneeDetails distressedProvisioneeDetails = new DistressedProvisioneeDetails();
        distressedProvisioneeDetails.setConnectivityErrorCode(discoveredDistressedProvisioneeRequest.getConnectivityErrorCode());
        distressedProvisioneeDetails.setProvisioneeInfo(discoveredDistressedProvisioneeRequest.getProvisioneeInfo());
        distressedProvisioneeDetails.setRssi(discoveredDistressedProvisioneeRequest.getRssi());
        distressedProvisioneeDetails.setProductIndex(discoveredDistressedProvisioneeRequest.getProductIndex());
        distressedProvisioneeDetails.setAuthMaterialIndex(discoveredDistressedProvisioneeRequest.getAuthMaterialIndex());
        distressedProvisioneeDetails.setRadio(discoveredDistressedProvisioneeRequest.getRadio());
        distressedProvisioneeDetails.setAdvertisedSdkVersionIndex(discoveredDistressedProvisioneeRequest.getAdvertisedSdkVersionIndex());
        discoveredProvisioneeInput.setProvisionerDetails(provisionerDetails);
        discoveredProvisioneeInput.setProvisioneeDetails(distressedProvisioneeDetails);
        discoveredProvisioneeInput.setDiscoveryInputParameters(distressDiscoveryInputParameters);
        return discoveredProvisioneeInput;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DiscoveredProvisioneeInput getDiscoveredLocalNotificatioinProvisioneeInput(DiscoveredLocalNotificationProvisioneeRequest discoveredLocalNotificationProvisioneeRequest) {
        DiscoveredProvisioneeInput discoveredProvisioneeInput = new DiscoveredProvisioneeInput();
        FirstPartyProvisionerDetails provisionerDetails = getProvisionerDetails(discoveredLocalNotificationProvisioneeRequest);
        LocalNotificationInputParameters localNotificationInputParameters = new LocalNotificationInputParameters();
        LocalNotificationProvisioneeDetails localNotificationProvisioneeDetails = new LocalNotificationProvisioneeDetails();
        localNotificationProvisioneeDetails.setRadio(discoveredLocalNotificationProvisioneeRequest.getRadio());
        localNotificationProvisioneeDetails.setRssi(discoveredLocalNotificationProvisioneeRequest.getRssi());
        localNotificationProvisioneeDetails.setProductIndex(discoveredLocalNotificationProvisioneeRequest.getProductIndex());
        localNotificationProvisioneeDetails.setAuthMaterialIndex(discoveredLocalNotificationProvisioneeRequest.getAuthMaterialIndex());
        localNotificationProvisioneeDetails.setProvisioneeInfo(discoveredLocalNotificationProvisioneeRequest.getProvisioneeInfo());
        localNotificationProvisioneeDetails.setConnectivityErrorCode(discoveredLocalNotificationProvisioneeRequest.getConnectivityErrorCode());
        localNotificationProvisioneeDetails.setNonce(discoveredLocalNotificationProvisioneeRequest.getNonce());
        localNotificationProvisioneeDetails.setAuthenticationMode(discoveredLocalNotificationProvisioneeRequest.getAuthenticationMode());
        localNotificationProvisioneeDetails.setAdvertisementVersion(discoveredLocalNotificationProvisioneeRequest.getAdvertisementVersion());
        localNotificationProvisioneeDetails.setAdvertisedSdkVersionIndex(discoveredLocalNotificationProvisioneeRequest.getAdvertisedSdkVersionIndex());
        discoveredProvisioneeInput.setProvisionerDetails(provisionerDetails);
        discoveredProvisioneeInput.setProvisioneeDetails(localNotificationProvisioneeDetails);
        discoveredProvisioneeInput.setDiscoveryInputParameters(localNotificationInputParameters);
        return discoveredProvisioneeInput;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DiscoveredProvisioneeInput getDiscoveredSmartHomeProvisioneeInput(DiscoveredSmartHomeProvisioneeRequest discoveredSmartHomeProvisioneeRequest) {
        DiscoveredProvisioneeInput discoveredProvisioneeInput = new DiscoveredProvisioneeInput();
        FirstPartyProvisionerDetails provisionerDetails = getProvisionerDetails(discoveredSmartHomeProvisioneeRequest);
        SmartHomeDiscoveryInputParameters smartHomeDiscoveryInputParameters = new SmartHomeDiscoveryInputParameters();
        smartHomeDiscoveryInputParameters.setProtocolType(discoveredSmartHomeProvisioneeRequest.getProtocolType());
        SmartHomeProvisioneeDetails smartHomeProvisioneeDetails = new SmartHomeProvisioneeDetails();
        smartHomeProvisioneeDetails.setMac(discoveredSmartHomeProvisioneeRequest.getMacAddress());
        smartHomeProvisioneeDetails.setRssi(discoveredSmartHomeProvisioneeRequest.getRssi());
        discoveredProvisioneeInput.setProvisionerDetails(provisionerDetails);
        discoveredProvisioneeInput.setProvisioneeDetails(smartHomeProvisioneeDetails);
        discoveredProvisioneeInput.setDiscoveryInputParameters(smartHomeDiscoveryInputParameters);
        return discoveredProvisioneeInput;
    }

    private Single<FFSWhiteListPolicyResponse> getFFSWhiteListPolicyBasedOnDeviceVersion(FFSWhiteListPolicyRequest fFSWhiteListPolicyRequest) {
        int i = Build.VERSION.SDK_INT;
        return getPolicyForNewDevices(fFSWhiteListPolicyRequest);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GetDeviceRegistrationStatusInput getGetRegistrationStatusInput(GetRegistrationStatusRequest getRegistrationStatusRequest) {
        GetDeviceRegistrationStatusInput getDeviceRegistrationStatusInput = new GetDeviceRegistrationStatusInput();
        if (getRegistrationStatusRequest.getBarcodeIdentifier() != null) {
            getDeviceRegistrationStatusInput.setBarcodeIdentifier(getRegistrationStatusRequest.getBarcodeIdentifier());
        } else if (getRegistrationStatusRequest.getAuthMaterialIdentifier() != null) {
            com.amazon.devicesetupservice.v1.AuthMaterialIdentifier authMaterialIdentifier = new com.amazon.devicesetupservice.v1.AuthMaterialIdentifier();
            authMaterialIdentifier.setAuthMaterialIndex(getRegistrationStatusRequest.getAuthMaterialIdentifier().getAuthMaterialIndex());
            authMaterialIdentifier.setProductIndex(getRegistrationStatusRequest.getAuthMaterialIdentifier().getProductIndex());
            getDeviceRegistrationStatusInput.setAuthMaterialIdentifier(authMaterialIdentifier);
        } else if (getRegistrationStatusRequest.getLegacyIdentifier() != null) {
            LegacyIdentifier legacyIdentifier = new LegacyIdentifier();
            legacyIdentifier.setDeviceType(getRegistrationStatusRequest.getLegacyIdentifier().getDeviceType());
            legacyIdentifier.setDsn(getRegistrationStatusRequest.getLegacyIdentifier().getDsn());
        }
        return getDeviceRegistrationStatusInput;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public GetRegistrationStatusResponse getGetRegistrationStatusResponse(GetDeviceRegistrationStatusOutput getDeviceRegistrationStatusOutput) {
        char c;
        GetRegistrationStatusResponse.Status status;
        String registrationState = getDeviceRegistrationStatusOutput.getRegistrationState();
        int hashCode = registrationState.hashCode();
        if (hashCode == -1404600839) {
            if (registrationState.equals(DeviceRegistrationState.RECENTLY_REGISTERED)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != -526380146) {
            if (hashCode == 922363087 && registrationState.equals(DeviceRegistrationState.PAST_REGISTERED)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (registrationState.equals("NOT_REGISTERED")) {
                c = 2;
            }
            c = 65535;
        }
        if (c == 0) {
            status = GetRegistrationStatusResponse.Status.PAST_REGISTERED;
        } else if (c == 1) {
            status = GetRegistrationStatusResponse.Status.RECENTLY_REGISTERED;
        } else if (c == 2) {
            status = GetRegistrationStatusResponse.Status.NOT_REGISTERED;
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected DSS registration state value ");
            outline107.append(getDeviceRegistrationStatusOutput.getRegistrationState());
            throw new IllegalArgumentException(outline107.toString());
        }
        return new GetRegistrationStatusResponse(status, Iso8601TimeConverter.convertDurationToMs(getDeviceRegistrationStatusOutput.getDurationToWait()), getDeviceRegistrationStatusOutput.getLastRegisteredTime());
    }

    private Single<FFSWhiteListPolicyResponse> getPolicyForNewDevices(FFSWhiteListPolicyRequest fFSWhiteListPolicyRequest) {
        return Single.create(new CallDssOnSubscribe(this.mFFSApiGatewayInterface.getFFSWhiteListPolicy(fFSWhiteListPolicyRequest.getDeviceModel(), fFSWhiteListPolicyRequest.getManufacturer(), fFSWhiteListPolicyRequest.getFirmwareVersion(), fFSWhiteListPolicyRequest.getPlatform(), fFSWhiteListPolicyRequest.getApplication(), fFSWhiteListPolicyRequest.getApplicationVersion(), fFSWhiteListPolicyRequest.getMarketplace(), fFSWhiteListPolicyRequest.getCustomerId()), DSSOperation.GET_FFS_WHITELIST_POLICY)).map(new Function<Response<FFSWhiteListPolicyResponse>, FFSWhiteListPolicyResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.15
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public FFSWhiteListPolicyResponse mo10358apply(@NonNull Response<FFSWhiteListPolicyResponse> response) throws Exception {
                return response.body();
            }
        });
    }

    private Single<FFSWhiteListPolicyResponse> getPolicyForOldDevices(final FFSWhiteListPolicyRequest fFSWhiteListPolicyRequest) {
        return Single.defer(new Supplier<SingleSource<? extends FFSWhiteListPolicyResponse>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.14
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // io.reactivex.rxjava3.functions.Supplier
            /* renamed from: get */
            public SingleSource<? extends FFSWhiteListPolicyResponse> mo10365get() throws Exception {
                return Single.just(new HttpsUrlConnectionClient(fFSWhiteListPolicyRequest).callWhitelist());
            }
        });
    }

    private FirstPartyProvisionerDetails getProvisionerDetails(AbstractDiscoveredProvisioneeRequest abstractDiscoveredProvisioneeRequest) {
        FirstPartyProvisionerDetails firstPartyProvisionerDetails = new FirstPartyProvisionerDetails();
        firstPartyProvisionerDetails.setApplication(abstractDiscoveredProvisioneeRequest.getProvisionerInfo().getApplication());
        firstPartyProvisionerDetails.setApplicationVersion(abstractDiscoveredProvisioneeRequest.getProvisionerInfo().getApplicationVersion());
        firstPartyProvisionerDetails.setDeviceModel(abstractDiscoveredProvisioneeRequest.getProvisionerInfo().getDeviceModel());
        firstPartyProvisionerDetails.setFirmwareVersion(abstractDiscoveredProvisioneeRequest.getProvisionerInfo().getFirmwareVersion());
        firstPartyProvisionerDetails.setManufacturer(abstractDiscoveredProvisioneeRequest.getProvisionerInfo().getManufacturer());
        return firstPartyProvisionerDetails;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getRegionalizedPath(String str) {
        return GeneratedOutlineSupport1.outline91(new StringBuilder(), this.mRegionSpecificUrl, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRegionSpecificUrlIfNecessary(String str) {
        if (str == null) {
            return;
        }
        if (str.endsWith("/")) {
            this.mRegionSpecificUrl = GeneratedOutlineSupport1.outline50(str, -1, 0);
        } else {
            this.mRegionSpecificUrl = str;
        }
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<ComputeConfigurationDataResponse> computeConfigurationData(final ComputeConfigurationDataRequest computeConfigurationDataRequest) {
        if (computeConfigurationDataRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.COMPUTE_CONFIGURATION_DATA;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<ComputeConfigurationDataOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.19
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<ComputeConfigurationDataOutput>> mo10358apply(@NonNull String str) throws Exception {
                    Call<ComputeConfigurationDataOutput> computeConfigurationData;
                    ComputeConfigurationDataInput computeConfigurationDataInput = new ComputeConfigurationDataInput();
                    computeConfigurationDataInput.setNonce(computeConfigurationDataRequest.getNonce());
                    computeConfigurationDataInput.setDeviceDetails(computeConfigurationDataRequest.getDeviceDetails());
                    computeConfigurationDataInput.setConfigurationDataMap(computeConfigurationDataRequest.getConfiguration());
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        computeConfigurationData = dSSClientImpl.mDssApi.computeConfigurationData(str, dSSClientImpl.getRegionalizedPath("/v1/computeConfigurationData"), computeConfigurationDataInput);
                    } else {
                        computeConfigurationData = dSSClientImpl.mDssApi.computeConfigurationData(str, computeConfigurationDataInput);
                    }
                    return Single.create(new CallDssOnSubscribe(computeConfigurationData, dSSOperation));
                }
            }).map(new Function<Response<ComputeConfigurationDataOutput>, ComputeConfigurationDataResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.18
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public ComputeConfigurationDataResponse mo10358apply(@NonNull Response<ComputeConfigurationDataOutput> response) throws Exception {
                    ComputeConfigurationDataOutput body = response.body();
                    return new ComputeConfigurationDataResponse(body.getNonce(), body.getConfiguration(), body.getSignature(), body.getRegistrationDetails());
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<CreateAPForWifiProvisioneeResponse> createAPForWifiProvisionee(final CreateAPForWifiProvisioneeRequest createAPForWifiProvisioneeRequest) {
        if (createAPForWifiProvisioneeRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.CREATE_AP_FOR_WIFI_PROVISIONEE;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<CreateAPForWifiProvisioneeOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.32
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<CreateAPForWifiProvisioneeOutput>> mo10358apply(@NonNull String str) throws Exception {
                    Call<CreateAPForWifiProvisioneeOutput> createAPForWifiProvisionee;
                    DiscoveredDevice discoveredDevice = new DiscoveredDevice();
                    discoveredDevice.setEncodedSsid(createAPForWifiProvisioneeRequest.getDiscoveredWifiProvisionee().getEncodedSsid());
                    discoveredDevice.setMacAddress(createAPForWifiProvisioneeRequest.getDiscoveredWifiProvisionee().getMacAddress());
                    discoveredDevice.setRssi(Integer.parseInt(createAPForWifiProvisioneeRequest.getDiscoveredWifiProvisionee().getRssi()));
                    CreateAPForWifiProvisioneeInput createAPForWifiProvisioneeInput = new CreateAPForWifiProvisioneeInput();
                    createAPForWifiProvisioneeInput.setActiveConnectionsCount(createAPForWifiProvisioneeRequest.getActiveConnectionsCount());
                    createAPForWifiProvisioneeInput.setDiscoveredDevice(discoveredDevice);
                    createAPForWifiProvisioneeInput.setProvisionerInfo(createAPForWifiProvisioneeRequest.getProvisionerInfo());
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        createAPForWifiProvisionee = dSSClientImpl.mDssApi.createAPForWifiProvisionee(str, dSSClientImpl.getRegionalizedPath("/v1/createAPForWifiProvisionee"), createAPForWifiProvisioneeInput);
                    } else {
                        createAPForWifiProvisionee = dSSClientImpl.mDssApi.createAPForWifiProvisionee(str, createAPForWifiProvisioneeInput);
                    }
                    return Single.create(new CallDssOnSubscribe(createAPForWifiProvisionee, dSSOperation));
                }
            }).map(new Function<Response<CreateAPForWifiProvisioneeOutput>, CreateAPForWifiProvisioneeResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.31
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public CreateAPForWifiProvisioneeResponse mo10358apply(@NonNull Response<CreateAPForWifiProvisioneeOutput> response) throws Exception {
                    CreateAPForWifiProvisioneeOutput body = response.body();
                    return new CreateAPForWifiProvisioneeResponse(body.isCanProceed(), body.getSsid(), body.getPassphrase(), body.getDeviceId(), (int) Iso8601TimeConverter.convertDurationToMs(body.getTimeout()), body.getHostPortList(), body.getBlacklistTimeout());
                }
            }).doOnError(new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.30
                @Override // io.reactivex.rxjava3.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    Log.e(DSSClientImpl.TAG, "Error", th);
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<GenericDSSDiscoveryResponse> discoveredDistressedProvisionee(final DiscoveredDistressedProvisioneeRequest discoveredDistressedProvisioneeRequest) {
        if (discoveredDistressedProvisioneeRequest != null) {
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<DiscoveredProvisioneeOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.4
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<DiscoveredProvisioneeOutput>> mo10358apply(String str) throws Exception {
                    Call<DiscoveredProvisioneeOutput> discoveredProvisionee;
                    DiscoveredProvisioneeInput discoveredDistressedProvisioneeInput = DSSClientImpl.this.getDiscoveredDistressedProvisioneeInput(discoveredDistressedProvisioneeRequest);
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        discoveredProvisionee = dSSClientImpl.mDssApi.discoveredProvisionee(str, dSSClientImpl.getRegionalizedPath("/v1/discoveredProvisionee"), discoveredDistressedProvisioneeInput);
                    } else {
                        discoveredProvisionee = dSSClientImpl.mDssApi.discoveredProvisionee(str, discoveredDistressedProvisioneeInput);
                    }
                    return Single.create(new CallDssOnSubscribe(discoveredProvisionee, DSSOperation.DISCOVERED_PROVISIONEE));
                }
            }).map(new Function<Response<DiscoveredProvisioneeOutput>, GenericDSSDiscoveryResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.3
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public GenericDSSDiscoveryResponse mo10358apply(Response<DiscoveredProvisioneeOutput> response) throws Exception {
                    DSSClientImpl.this.setRegionSpecificUrlIfNecessary(response.headers().get("x-amzn-endpoint"));
                    DiscoveredProvisioneeOutput body = response.body();
                    return new GenericDSSDiscoveryResponse(body.getProvisionerReportUrl(), body.getProvisioneeReportUrl(), body.isCanProceed(), Iso8601TimeConverter.convertDurationToMs(body.getWaitTime()), body.getDiscoveryOutputParameters() != null ? ((DistressDiscoveryOutputParameters) body.getDiscoveryOutputParameters()).getSessionToken() : null);
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(DSSOperation.DISCOVERED_PROVISIONEE)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<DiscoveredLocalNotificationProvisioneeResponse> discoveredLocalNotificationProvisionee(final DiscoveredLocalNotificationProvisioneeRequest discoveredLocalNotificationProvisioneeRequest) {
        if (discoveredLocalNotificationProvisioneeRequest != null) {
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<DiscoveredProvisioneeOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.6
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<DiscoveredProvisioneeOutput>> mo10358apply(String str) throws Exception {
                    Call<DiscoveredProvisioneeOutput> discoveredProvisionee;
                    DiscoveredProvisioneeInput discoveredLocalNotificatioinProvisioneeInput = DSSClientImpl.this.getDiscoveredLocalNotificatioinProvisioneeInput(discoveredLocalNotificationProvisioneeRequest);
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        discoveredProvisionee = dSSClientImpl.mDssApi.discoveredProvisionee(str, dSSClientImpl.getRegionalizedPath("/v1/discoveredProvisionee"), discoveredLocalNotificatioinProvisioneeInput);
                    } else {
                        discoveredProvisionee = dSSClientImpl.mDssApi.discoveredProvisionee(str, discoveredLocalNotificatioinProvisioneeInput);
                    }
                    return Single.create(new CallDssOnSubscribe(discoveredProvisionee, DSSOperation.DISCOVERED_PROVISIONEE));
                }
            }).map(new Function<Response<DiscoveredProvisioneeOutput>, DiscoveredLocalNotificationProvisioneeResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.5
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public DiscoveredLocalNotificationProvisioneeResponse mo10358apply(Response<DiscoveredProvisioneeOutput> response) throws Exception {
                    LegacyIdentifierData legacyIdentifierData;
                    DSSClientImpl.this.setRegionSpecificUrlIfNecessary(response.headers().get("x-amzn-endpoint"));
                    DiscoveredProvisioneeOutput body = response.body();
                    if (body.getDiscoveryOutputParameters() != null) {
                        LegacyIdentifier provisioneeInformation = ((LocalNotificationOutputParameters) body.getDiscoveryOutputParameters()).getProvisioneeInformation();
                        legacyIdentifierData = new LegacyIdentifierData.Builder().setDeviceType(provisioneeInformation.getDeviceType()).setDsn(provisioneeInformation.getDsn()).build();
                    } else {
                        legacyIdentifierData = null;
                    }
                    return new DiscoveredLocalNotificationProvisioneeResponse(body.isCanProceed(), legacyIdentifierData);
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(DSSOperation.DISCOVERED_PROVISIONEE)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<GenericDSSDiscoveryResponse> discoveredProvisionableDevice(final DiscoveredProvisionableDeviceRequest discoveredProvisionableDeviceRequest) {
        if (discoveredProvisionableDeviceRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.DISCOVERED_PROVISIONABLE_DEVICE;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<DiscoveredProvisionableDeviceOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.2
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<DiscoveredProvisionableDeviceOutput>> mo10358apply(@NonNull String str) throws Exception {
                    Call<DiscoveredProvisionableDeviceOutput> discoveredProvisionableDevice;
                    DiscoveredProvisionableDeviceInput discoveredProvisionableDeviceInput = new DiscoveredProvisionableDeviceInput();
                    discoveredProvisionableDeviceInput.setProvisionerApplication(discoveredProvisionableDeviceRequest.getProvisionerApplicationName());
                    discoveredProvisionableDeviceInput.setProvisionerApplicationVersion(discoveredProvisionableDeviceRequest.getProvisionerApplicationVersion());
                    discoveredProvisionableDeviceInput.setProductIndex(discoveredProvisionableDeviceRequest.getProductIndex());
                    discoveredProvisionableDeviceInput.setDeviceName(discoveredProvisionableDeviceRequest.getDeviceName());
                    discoveredProvisionableDeviceInput.setAuthMaterialIndex(discoveredProvisionableDeviceRequest.getAuthMaterialIndex());
                    discoveredProvisionableDeviceInput.setSoftwareVersionIndex(discoveredProvisionableDeviceRequest.getSoftwareVersionIndex());
                    discoveredProvisionableDeviceInput.setNonce(discoveredProvisionableDeviceRequest.getNonce());
                    discoveredProvisionableDeviceInput.setRssi(discoveredProvisionableDeviceRequest.getRssi());
                    discoveredProvisionableDeviceInput.setRadio(discoveredProvisionableDeviceRequest.getRadio());
                    discoveredProvisionableDeviceInput.setProvisioningMethod(discoveredProvisionableDeviceRequest.getProvisioningMethod());
                    discoveredProvisionableDeviceInput.setTrustMethod(discoveredProvisionableDeviceRequest.getTrustMethod());
                    discoveredProvisionableDeviceInput.setProvisionerInfo(discoveredProvisionableDeviceRequest.getProvisionerInfo());
                    discoveredProvisionableDeviceInput.setAdvertisedSdkVersionIndex(discoveredProvisionableDeviceRequest.getAdvertisedSdkVersionIndex());
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        discoveredProvisionableDevice = dSSClientImpl.mDssApi.discoveredProvisionableDevice(str, dSSClientImpl.getRegionalizedPath("/v1/discoveredProvisionableDevice"), discoveredProvisionableDeviceInput);
                    } else {
                        discoveredProvisionableDevice = dSSClientImpl.mDssApi.discoveredProvisionableDevice(str, discoveredProvisionableDeviceInput);
                    }
                    return Single.create(new CallDssOnSubscribe(discoveredProvisionableDevice, dSSOperation));
                }
            }).map(new Function<Response<DiscoveredProvisionableDeviceOutput>, GenericDSSDiscoveryResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.1
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public GenericDSSDiscoveryResponse mo10358apply(@NonNull Response<DiscoveredProvisionableDeviceOutput> response) throws Exception {
                    DSSClientImpl.this.setRegionSpecificUrlIfNecessary(response.headers().get("x-amzn-endpoint"));
                    DiscoveredProvisionableDeviceOutput body = response.body();
                    return new GenericDSSDiscoveryResponse(body.getProvisionerReportUrl(), body.getProvisionableReportUrl(), body.isCanProceed(), body.getWaitTime(), null);
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<DiscoveredSmartHomeProvisioneeResponse> discoveredSmartHomeProvisionee(final DiscoveredSmartHomeProvisioneeRequest discoveredSmartHomeProvisioneeRequest) {
        if (discoveredSmartHomeProvisioneeRequest != null) {
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<DiscoveredProvisioneeOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.8
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<DiscoveredProvisioneeOutput>> mo10358apply(String str) throws Exception {
                    Call<DiscoveredProvisioneeOutput> discoveredProvisionee;
                    DiscoveredProvisioneeInput discoveredSmartHomeProvisioneeInput = DSSClientImpl.this.getDiscoveredSmartHomeProvisioneeInput(discoveredSmartHomeProvisioneeRequest);
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        discoveredProvisionee = dSSClientImpl.mDssApi.discoveredProvisionee(str, dSSClientImpl.getRegionalizedPath("/v1/discoveredProvisionee"), discoveredSmartHomeProvisioneeInput);
                    } else {
                        discoveredProvisionee = dSSClientImpl.mDssApi.discoveredProvisionee(str, discoveredSmartHomeProvisioneeInput);
                    }
                    return Single.create(new CallDssOnSubscribe(discoveredProvisionee, DSSOperation.DISCOVERED_PROVISIONEE));
                }
            }).map(new Function<Response<DiscoveredProvisioneeOutput>, DiscoveredSmartHomeProvisioneeResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.7
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public DiscoveredSmartHomeProvisioneeResponse mo10358apply(Response<DiscoveredProvisioneeOutput> response) throws Exception {
                    DSSClientImpl.this.setRegionSpecificUrlIfNecessary(response.headers().get("x-amzn-endpoint"));
                    DiscoveredProvisioneeOutput body = response.body();
                    return new DiscoveredSmartHomeProvisioneeResponse(body.getProvisionerReportUrl(), body.isCanProceed(), Iso8601TimeConverter.convertDurationToMs(body.getWaitTime()));
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(DSSOperation.DISCOVERED_PROVISIONEE)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<FinalizeEcdheAuthenticationSessionResponse> finalizeEcdheAuthenticationSession(final FinalizeEcdheAuthenticationSessionRequest finalizeEcdheAuthenticationSessionRequest) {
        if (finalizeEcdheAuthenticationSessionRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.FINALIZE_ECDHE_AUTHENTICATION_SESSION;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<FinalizeEcdheAuthenticationSessionOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.12
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<FinalizeEcdheAuthenticationSessionOutput>> mo10358apply(@NonNull String str) throws Exception {
                    Call<FinalizeEcdheAuthenticationSessionOutput> finalizeEcdheAuthSession;
                    FinalizeEcdheAuthenticationSessionInput finalizeEcdheAuthenticationSessionInput = new FinalizeEcdheAuthenticationSessionInput();
                    finalizeEcdheAuthenticationSessionInput.setContinuationToken(finalizeEcdheAuthenticationSessionRequest.getContinuationToken());
                    finalizeEcdheAuthenticationSessionInput.setPayload(ByteBuffer.wrap(finalizeEcdheAuthenticationSessionRequest.getAuthEcdheKeyExchangeResponse()));
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        finalizeEcdheAuthSession = dSSClientImpl.mDssApi.finalizeEcdheAuthSession(str, dSSClientImpl.getRegionalizedPath("/v1/finalizeEcdheAuthenticationSession"), finalizeEcdheAuthenticationSessionInput);
                    } else {
                        finalizeEcdheAuthSession = dSSClientImpl.mDssApi.finalizeEcdheAuthSession(str, finalizeEcdheAuthenticationSessionInput);
                    }
                    return Single.create(new CallDssOnSubscribe(finalizeEcdheAuthSession, dSSOperation));
                }
            }).map(new Function<Response<FinalizeEcdheAuthenticationSessionOutput>, FinalizeEcdheAuthenticationSessionResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.11
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public FinalizeEcdheAuthenticationSessionResponse mo10358apply(@NonNull Response<FinalizeEcdheAuthenticationSessionOutput> response) throws Exception {
                    return new FinalizeEcdheAuthenticationSessionResponse(response.body().getSessionKey().array());
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<GetCustomerDevicesCredentialsResponse> getCustomerDevicesCredentials(final GetCustomerDevicesCredentialsRequest getCustomerDevicesCredentialsRequest) {
        if (getCustomerDevicesCredentialsRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.GET_CUSTOMER_DEVICES_CREDENTIALS;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<GetCustomerDevicesCredentialsOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.34
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<GetCustomerDevicesCredentialsOutput>> mo10358apply(@NonNull String str) throws Exception {
                    Call<GetCustomerDevicesCredentialsOutput> customerDevicesCredentials;
                    GetCustomerDevicesCredentialsInput getCustomerDevicesCredentialsInput = new GetCustomerDevicesCredentialsInput();
                    getCustomerDevicesCredentialsInput.setProvisionerInfo(getCustomerDevicesCredentialsRequest.getProvisionerInfo());
                    getCustomerDevicesCredentialsInput.setCredentialRequests(getCustomerDevicesCredentialsRequest.getCredentialRequests());
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        customerDevicesCredentials = dSSClientImpl.mDssApi.getCustomerDevicesCredentials(str, dSSClientImpl.getRegionalizedPath("/v1/getCustomerDevicesCredentials"), getCustomerDevicesCredentialsInput);
                    } else {
                        customerDevicesCredentials = dSSClientImpl.mDssApi.getCustomerDevicesCredentials(str, getCustomerDevicesCredentialsInput);
                    }
                    return Single.create(new CallDssOnSubscribe(customerDevicesCredentials, dSSOperation));
                }
            }).map(new Function<Response<GetCustomerDevicesCredentialsOutput>, GetCustomerDevicesCredentialsResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.33
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public GetCustomerDevicesCredentialsResponse mo10358apply(Response<GetCustomerDevicesCredentialsOutput> response) throws Exception {
                    DSSClientImpl.this.setRegionSpecificUrlIfNecessary(response.headers().get("x-amzn-endpoint"));
                    return new GetCustomerDevicesCredentialsResponse(response.body().getCredentialsList());
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<GetCustomerDevicesCredentialsV2Output> getCustomerDevicesCredentialsV2(final GetCustomerDevicesCredentialsRequest getCustomerDevicesCredentialsRequest) {
        if (getCustomerDevicesCredentialsRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.GET_CUSTOMER_DEVICES_CREDENTIALS_V2;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<GetCustomerDevicesCredentialsV2Output>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.40
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<GetCustomerDevicesCredentialsV2Output>> mo10358apply(@NonNull String str) throws Exception {
                    Call<GetCustomerDevicesCredentialsV2Output> customerDevicesCredentialsV2;
                    GetCustomerDevicesCredentialsInput getCustomerDevicesCredentialsInput = new GetCustomerDevicesCredentialsInput();
                    getCustomerDevicesCredentialsInput.setProvisionerInfo(getCustomerDevicesCredentialsRequest.getProvisionerInfo());
                    getCustomerDevicesCredentialsInput.setCredentialRequests(getCustomerDevicesCredentialsRequest.getCredentialRequests());
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        customerDevicesCredentialsV2 = dSSClientImpl.mDssApi.getCustomerDevicesCredentialsV2(str, dSSClientImpl.getRegionalizedPath("/v2/getCustomerDevicesCredentials"), getCustomerDevicesCredentialsInput);
                    } else {
                        customerDevicesCredentialsV2 = dSSClientImpl.mDssApi.getCustomerDevicesCredentialsV2(str, getCustomerDevicesCredentialsInput);
                    }
                    return Single.create(new CallDssOnSubscribe(customerDevicesCredentialsV2, dSSOperation));
                }
            }).map(new Function<Response<GetCustomerDevicesCredentialsV2Output>, GetCustomerDevicesCredentialsV2Output>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.39
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public GetCustomerDevicesCredentialsV2Output mo10358apply(Response<GetCustomerDevicesCredentialsV2Output> response) throws Exception {
                    DSSClientImpl.this.setRegionSpecificUrlIfNecessary(response.headers().get("x-amzn-endpoint"));
                    return response.body();
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<GetCustomerProvisioneesSetupStatusResponse> getCustomerProvisioneesSetupStatus(final GetCustomerProvisioneesSetupStatusRequest getCustomerProvisioneesSetupStatusRequest) {
        Preconditions.checkArgument(getCustomerProvisioneesSetupStatusRequest != null, "Request can not be null");
        final DSSOperation dSSOperation = DSSOperation.GET_CUSTOMER_PROVISIONEES_SETUP_STATUS;
        return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<GetCustomerProvisioneesSetupStatusOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.23
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public SingleSource<Response<GetCustomerProvisioneesSetupStatusOutput>> mo10358apply(@NonNull String str) {
                Call<GetCustomerProvisioneesSetupStatusOutput> customerProvisioneesSetupStatus;
                ImmutableList.Builder builder = ImmutableList.builder();
                for (com.amazon.whisperjoin.devicesetupserviceandroidclient.data.AuthMaterialIdentifier authMaterialIdentifier : getCustomerProvisioneesSetupStatusRequest.getAuthMaterialIdentifiers()) {
                    com.amazon.devicesetupservice.v1.AuthMaterialIdentifier authMaterialIdentifier2 = new com.amazon.devicesetupservice.v1.AuthMaterialIdentifier();
                    authMaterialIdentifier2.setAuthMaterialIndex(authMaterialIdentifier.getAuthMaterialIndex());
                    authMaterialIdentifier2.setProductIndex(authMaterialIdentifier.getProductIndex());
                    builder.mo7849add((ImmutableList.Builder) authMaterialIdentifier2);
                }
                for (com.amazon.whisperjoin.devicesetupserviceandroidclient.data.BarcodeIdentifier barcodeIdentifier : getCustomerProvisioneesSetupStatusRequest.getBarcodeIdentifiers()) {
                    com.amazon.devicesetupservice.v1.BarcodeIdentifier barcodeIdentifier2 = new com.amazon.devicesetupservice.v1.BarcodeIdentifier();
                    barcodeIdentifier2.setBarcodeData(barcodeIdentifier.getBarcodeData());
                    builder.mo7849add((ImmutableList.Builder) barcodeIdentifier2);
                }
                GetCustomerProvisioneesSetupStatusInput getCustomerProvisioneesSetupStatusInput = new GetCustomerProvisioneesSetupStatusInput();
                getCustomerProvisioneesSetupStatusInput.setProvisioneeIdentifiers(builder.mo7852build());
                DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                if (dSSClientImpl.mRegionSpecificUrl != null) {
                    customerProvisioneesSetupStatus = dSSClientImpl.mDssApi.getCustomerProvisioneesSetupStatus(str, dSSClientImpl.getRegionalizedPath("/v1/getCustomerProvisioneesSetupStatus"), getCustomerProvisioneesSetupStatusInput);
                } else {
                    customerProvisioneesSetupStatus = dSSClientImpl.mDssApi.getCustomerProvisioneesSetupStatus(str, getCustomerProvisioneesSetupStatusInput);
                }
                return Single.create(new CallDssOnSubscribe(customerProvisioneesSetupStatus, dSSOperation));
            }
        }).map(new Function<Response<GetCustomerProvisioneesSetupStatusOutput>, GetCustomerProvisioneesSetupStatusResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.22
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply  reason: avoid collision after fix types in other method */
            public GetCustomerProvisioneesSetupStatusResponse mo10358apply(@NonNull Response<GetCustomerProvisioneesSetupStatusOutput> response) {
                GetCustomerProvisioneesSetupStatusOutput body = response.body();
                GetCustomerProvisioneesSetupStatusResponse.Builder searchIntervalUsed = new GetCustomerProvisioneesSetupStatusResponse.Builder().setSearchIntervalUsed(body.getSearchIntervalUsed());
                for (ProvisioneeSetupInformation provisioneeSetupInformation : body.getProvisioneeSetupInformationList()) {
                    searchIntervalUsed.addProvisioneeSetupStatus(DSSClientImpl.this.createProvisioneeSetupStatus(provisioneeSetupInformation));
                }
                return searchIntervalUsed.createResponse();
            }
        }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<FFSWhiteListPolicyResponse> getFFSWhiteListPolicy(FFSWhiteListPolicyRequest fFSWhiteListPolicyRequest) {
        if (fFSWhiteListPolicyRequest != null) {
            return getFFSWhiteListPolicyBasedOnDeviceVersion(fFSWhiteListPolicyRequest).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(DSSOperation.GET_FFS_WHITELIST_POLICY)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<GetRegistrationStatusResponse> getRegistrationStatus(final GetRegistrationStatusRequest getRegistrationStatusRequest) {
        if (getRegistrationStatusRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.GET_DEVICE_REGISTRATION_STATUS;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<GetDeviceRegistrationStatusOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.21
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<GetDeviceRegistrationStatusOutput>> mo10358apply(@NonNull String str) throws Exception {
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    return Single.create(new CallDssOnSubscribe(dSSClientImpl.mRegionSpecificUrl != null ? dSSClientImpl.mDssApi.getDeviceRegistrationStatus(str, dSSClientImpl.getRegionalizedPath("/v1/getDeviceRegistrationStatus"), DSSClientImpl.this.getGetRegistrationStatusInput(getRegistrationStatusRequest)) : dSSClientImpl.mDssApi.getDeviceRegistrationStatus(str, dSSClientImpl.getGetRegistrationStatusInput(getRegistrationStatusRequest)), dSSOperation));
                }
            }).map(new Function<Response<GetDeviceRegistrationStatusOutput>, GetRegistrationStatusResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.20
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public GetRegistrationStatusResponse mo10358apply(@NonNull Response<GetDeviceRegistrationStatusOutput> response) throws Exception {
                    return DSSClientImpl.this.getGetRegistrationStatusResponse(response.body());
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<GetWiFiSyncAuthTokenResponse> getWiFiSyncAuthToken(final GetWiFiSyncAuthTokenRequest getWiFiSyncAuthTokenRequest) {
        if (getWiFiSyncAuthTokenRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.GET_WIFI_SYNC_AUTH_TOKEN;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<GetWifiSyncAuthTokenOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.36
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<GetWifiSyncAuthTokenOutput>> mo10358apply(String str) {
                    Call<GetWifiSyncAuthTokenOutput> wiFiSyncAuthToken;
                    GetWifiSyncAuthTokenInput getWifiSyncAuthTokenInput = new GetWifiSyncAuthTokenInput();
                    getWifiSyncAuthTokenInput.setCertificate(getWiFiSyncAuthTokenRequest.getPemCertificate());
                    getWifiSyncAuthTokenInput.setPublicKey(getWiFiSyncAuthTokenRequest.getPublicKey());
                    getWifiSyncAuthTokenInput.setIsCertificateChainPresent(Boolean.valueOf(getWiFiSyncAuthTokenRequest.getIsCertificateChainPresent()));
                    getWifiSyncAuthTokenInput.setTimestamp(getWiFiSyncAuthTokenRequest.getTimestamp());
                    getWifiSyncAuthTokenInput.setSignature(getWiFiSyncAuthTokenRequest.getSignature());
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        wiFiSyncAuthToken = dSSClientImpl.mDssApi.getWiFiSyncAuthToken(str, dSSClientImpl.getRegionalizedPath("/v1/getWifiSyncAuthToken"), getWifiSyncAuthTokenInput);
                    } else {
                        wiFiSyncAuthToken = dSSClientImpl.mDssApi.getWiFiSyncAuthToken(str, getWifiSyncAuthTokenInput);
                    }
                    return Single.create(new CallDssOnSubscribe(wiFiSyncAuthToken, dSSOperation));
                }
            }).map(new Function<Response<GetWifiSyncAuthTokenOutput>, GetWiFiSyncAuthTokenResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.35
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public GetWiFiSyncAuthTokenResponse mo10358apply(Response<GetWifiSyncAuthTokenOutput> response) {
                    DSSClientImpl.this.setRegionSpecificUrlIfNecessary(response.headers().get("x-amzn-endpoint"));
                    return new GetWiFiSyncAuthTokenResponse(response.body().getWifiSyncAuthToken());
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<GetWiFiNetworksOutput> getWifiNetworks(final GetWiFiNetworksInput getWiFiNetworksInput) {
        if (getWiFiNetworksInput != null) {
            final DSSOperation dSSOperation = DSSOperation.GET_WIFI_NETWORKS;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<GetWiFiNetworksOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.26
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<GetWiFiNetworksOutput>> mo10358apply(@NonNull String str) throws Exception {
                    Call<GetWiFiNetworksOutput> wifiNetworks;
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        wifiNetworks = dSSClientImpl.mDssApi.getWifiNetworks(str, dSSClientImpl.getRegionalizedPath("/v1/GetWiFiNetworks"), getWiFiNetworksInput);
                    } else {
                        wifiNetworks = dSSClientImpl.mDssApi.getWifiNetworks(str, getWiFiNetworksInput);
                    }
                    return Single.create(new CallDssOnSubscribe(wifiNetworks, dSSOperation));
                }
            }).map(new Function<Response<GetWiFiNetworksOutput>, GetWiFiNetworksOutput>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.25
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public GetWiFiNetworksOutput mo10358apply(@NonNull Response<GetWiFiNetworksOutput> response) throws Exception {
                    return response.body();
                }
            }).doOnError(new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.24
                @Override // io.reactivex.rxjava3.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    Log.e(DSSClientImpl.TAG, "Error", th);
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Completable recordDevicePossessionIntentInnerBarcode(final RecordDevicePossessionIntentInnerBarcodeRequest recordDevicePossessionIntentInnerBarcodeRequest) {
        if (recordDevicePossessionIntentInnerBarcodeRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.RECORD_DEVICE_POSSESSION_INTENT_WITH_INNER_BARCODE;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<RecordDevicePossessionIntentInnerBarcodeOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.17
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<RecordDevicePossessionIntentInnerBarcodeOutput>> mo10358apply(@NonNull String str) throws Exception {
                    Call<RecordDevicePossessionIntentInnerBarcodeOutput> recordDevicePossessionIntentInnerBarcode;
                    RecordDevicePossessionIntentInnerBarcodeInput recordDevicePossessionIntentInnerBarcodeInput = new RecordDevicePossessionIntentInnerBarcodeInput();
                    recordDevicePossessionIntentInnerBarcodeInput.setBarcodeData(recordDevicePossessionIntentInnerBarcodeRequest.getBarcodeData());
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        recordDevicePossessionIntentInnerBarcode = dSSClientImpl.mDssApi.recordDevicePossessionIntentInnerBarcode(str, dSSClientImpl.getRegionalizedPath("/v1/recordDevicePossessionIntentInnerBarcode"), recordDevicePossessionIntentInnerBarcodeInput);
                    } else {
                        recordDevicePossessionIntentInnerBarcode = dSSClientImpl.mDssApi.recordDevicePossessionIntentInnerBarcode(str, recordDevicePossessionIntentInnerBarcodeInput);
                    }
                    return Single.create(new CallDssOnSubscribe(recordDevicePossessionIntentInnerBarcode, dSSOperation));
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).flatMapCompletable(new Function<Response<RecordDevicePossessionIntentInnerBarcodeOutput>, CompletableSource>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.16
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public CompletableSource mo10358apply(@NonNull Response<RecordDevicePossessionIntentInnerBarcodeOutput> response) throws Exception {
                    return Completable.complete();
                }
            }).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Completable reportEvent(ReportEventRequest reportEventRequest) {
        if (reportEventRequest != null) {
            DSSOperation dSSOperation = DSSOperation.REPORT_EVENT;
            ReportEventInput reportEventInput = new ReportEventInput();
            reportEventInput.setEvent(reportEventRequest.getEvent());
            reportEventInput.setState(reportEventRequest.getState());
            reportEventInput.setSequenceNumber(reportEventRequest.getSequenceNumber());
            reportEventInput.setProvisioningMethod(reportEventRequest.getProvisioningMethod());
            reportEventInput.setKeyExchangeMethod(reportEventRequest.getKeyExchangeMethod());
            reportEventInput.setTrustMethod(reportEventRequest.getTrustMethod());
            reportEventInput.setRadio(reportEventRequest.getRadio());
            reportEventInput.setProvisionableInfo(reportEventRequest.getProvisionableInfo());
            reportEventInput.setProvisionerInfo(reportEventRequest.getProvisionerInfo());
            reportEventInput.setRegistrationState(reportEventRequest.getRegistrationState());
            reportEventInput.setWifiNetworkInfo(reportEventRequest.getWifiNetworkInfo());
            reportEventInput.setErrorInfo(reportEventRequest.getErrorInfo());
            reportEventInput.setCredentialLockerUsageInfo(reportEventRequest.getCredentialLockerUsageInfo());
            return Single.create(new CallDssOnSubscribe(this.mDssApi.reportEvent(reportEventRequest.getReportingUrl(), reportEventInput), dSSOperation)).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).flatMapCompletable(new Function<Response<ReportEventOutput>, CompletableSource>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.13
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public CompletableSource mo10358apply(@NonNull Response<ReportEventOutput> response) throws Exception {
                    return Completable.complete();
                }
            }).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Completable reportSmartHomeEvent(final ReportSmartHomeEventRequest reportSmartHomeEventRequest) {
        if (reportSmartHomeEventRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.REPORT_SMART_HOME_EVENT;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<ReportSmartHomeEventOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.42
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<ReportSmartHomeEventOutput>> mo10358apply(String str) throws Exception {
                    return Single.create(new CallDssOnSubscribe(DSSClientImpl.this.mDssApi.reportSmartHomeEvent(str, reportSmartHomeEventRequest.getReportEventIdentifier(), DSSClientImpl.createReportSmartHomeEventInput(reportSmartHomeEventRequest)), dSSOperation));
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).flatMapCompletable(new Function<Response<ReportSmartHomeEventOutput>, CompletableSource>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.41
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public CompletableSource mo10358apply(@NonNull Response<ReportSmartHomeEventOutput> response) throws Exception {
                    return Completable.complete();
                }
            }).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<SaveWifiNetworkOutput> saveWifiNetwork(final SaveWifiNetworkInput saveWifiNetworkInput) {
        if (saveWifiNetworkInput != null) {
            final DSSOperation dSSOperation = DSSOperation.SAVE_WIFI_NETWORK;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<SaveWifiNetworkOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.29
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<SaveWifiNetworkOutput>> mo10358apply(@NonNull String str) throws Exception {
                    Call<SaveWifiNetworkOutput> saveWifiNetwork;
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        saveWifiNetwork = dSSClientImpl.mDssApi.saveWifiNetwork(str, dSSClientImpl.getRegionalizedPath("/v1/SaveWifiNetwork"), saveWifiNetworkInput);
                    } else {
                        saveWifiNetwork = dSSClientImpl.mDssApi.saveWifiNetwork(str, saveWifiNetworkInput);
                    }
                    return Single.create(new CallDssOnSubscribe(saveWifiNetwork, dSSOperation));
                }
            }).map(new Function<Response<SaveWifiNetworkOutput>, SaveWifiNetworkOutput>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.28
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SaveWifiNetworkOutput mo10358apply(@NonNull Response<SaveWifiNetworkOutput> response) throws Exception {
                    return response.body();
                }
            }).doOnError(new Consumer<Throwable>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.27
                @Override // io.reactivex.rxjava3.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    Log.e(DSSClientImpl.TAG, "Error", th);
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public void setDssApi(DSSServiceConfiguration dSSServiceConfiguration) {
        Log.w(TAG, "The setDssApi method may only be called in a debug build. Please remove any references to this method that are used in release builds.");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<StartEcdheAuthenticationSessionResponse> startEcdheAuthenticationSession(final StartEcdheAuthenticationSessionRequest startEcdheAuthenticationSessionRequest) {
        if (startEcdheAuthenticationSessionRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.START_ECDHE_AUTHENTICATION_SESSION;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<StartEcdheAuthenticationSessionOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.10
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<StartEcdheAuthenticationSessionOutput>> mo10358apply(@NonNull String str) throws Exception {
                    Call<StartEcdheAuthenticationSessionOutput> startEcdheAuthSession;
                    StartEcdheAuthenticationSessionInput startEcdheAuthenticationSessionInput = new StartEcdheAuthenticationSessionInput();
                    startEcdheAuthenticationSessionInput.setProductIndex(startEcdheAuthenticationSessionRequest.getProductIndex());
                    startEcdheAuthenticationSessionInput.setAuthMaterialIndex(startEcdheAuthenticationSessionRequest.getAuthMaterialIndex());
                    startEcdheAuthenticationSessionInput.setSoftwareVersionIndex(startEcdheAuthenticationSessionRequest.getSoftwareVersionIndex());
                    startEcdheAuthenticationSessionInput.setNonce(startEcdheAuthenticationSessionRequest.getNonce());
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        startEcdheAuthSession = dSSClientImpl.mDssApi.startEcdheAuthSession(str, dSSClientImpl.getRegionalizedPath("/v1/startEcdheAuthenticationSession"), startEcdheAuthenticationSessionInput);
                    } else {
                        startEcdheAuthSession = dSSClientImpl.mDssApi.startEcdheAuthSession(str, startEcdheAuthenticationSessionInput);
                    }
                    return Single.create(new CallDssOnSubscribe(startEcdheAuthSession, dSSOperation));
                }
            }).map(new Function<Response<StartEcdheAuthenticationSessionOutput>, StartEcdheAuthenticationSessionResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.9
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public StartEcdheAuthenticationSessionResponse mo10358apply(@NonNull Response<StartEcdheAuthenticationSessionOutput> response) throws Exception {
                    StartEcdheAuthenticationSessionOutput body = response.body();
                    return new StartEcdheAuthenticationSessionResponse(body.getContinuationToken(), body.getEcdhePublicKey(), body.getEcdsaSignature());
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @Override // com.amazon.whisperjoin.devicesetupserviceandroidclient.DSSClient
    public Single<ValidateWiFiSyncAuthTokenResponse> validateWiFiSyncAuthToken(final ValidateWiFiSyncAuthTokenRequest validateWiFiSyncAuthTokenRequest) {
        if (validateWiFiSyncAuthTokenRequest != null) {
            final DSSOperation dSSOperation = DSSOperation.VALIDATE_WIFI_SYNC_AUTH_TOKEN;
            return this.mAccessTokenProvider.getAccessToken().flatMap(new Function<String, SingleSource<Response<ValidateWifiSyncAuthTokenOutput>>>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.38
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public SingleSource<Response<ValidateWifiSyncAuthTokenOutput>> mo10358apply(String str) {
                    Call<ValidateWifiSyncAuthTokenOutput> validateWiFiSyncAuthToken;
                    ValidateWifiSyncAuthTokenInput validateWifiSyncAuthTokenInput = new ValidateWifiSyncAuthTokenInput();
                    validateWifiSyncAuthTokenInput.setWifiSyncAuthToken(validateWiFiSyncAuthTokenRequest.getWifiSyncAuthToken());
                    validateWifiSyncAuthTokenInput.setAuthMaterialIndex(validateWiFiSyncAuthTokenRequest.getAuthMaterialIndex());
                    validateWifiSyncAuthTokenInput.setProductIndex(validateWiFiSyncAuthTokenRequest.getProductIndex());
                    validateWifiSyncAuthTokenInput.setSessionToken(validateWiFiSyncAuthTokenRequest.getSessionToken());
                    DSSClientImpl dSSClientImpl = DSSClientImpl.this;
                    if (dSSClientImpl.mRegionSpecificUrl != null) {
                        validateWiFiSyncAuthToken = dSSClientImpl.mDssApi.validateWiFiSyncAuthToken(str, dSSClientImpl.getRegionalizedPath("/v1/validateWifiSyncAuthToken"), validateWifiSyncAuthTokenInput);
                    } else {
                        validateWiFiSyncAuthToken = dSSClientImpl.mDssApi.validateWiFiSyncAuthToken(str, validateWifiSyncAuthTokenInput);
                    }
                    return Single.create(new CallDssOnSubscribe(validateWiFiSyncAuthToken, dSSOperation));
                }
            }).map(new Function<Response<ValidateWifiSyncAuthTokenOutput>, ValidateWiFiSyncAuthTokenResponse>() { // from class: com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSClientImpl.37
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply  reason: avoid collision after fix types in other method */
                public ValidateWiFiSyncAuthTokenResponse mo10358apply(Response<ValidateWifiSyncAuthTokenOutput> response) {
                    DSSClientImpl.this.setRegionSpecificUrlIfNecessary(response.headers().get("x-amzn-endpoint"));
                    return new ValidateWiFiSyncAuthTokenResponse(response.body().isValidToken().booleanValue());
                }
            }).onErrorResumeNext(new WrapFailureCauseWithDSSClientError(dSSOperation)).subscribeOn(this.mBackgroundScheduler).observeOn(this.mMainThreadScheduler);
        }
        throw new IllegalArgumentException("Request can not be null");
    }

    @VisibleForTesting
    DSSClientImpl(ContextModule contextModule, AuthModule authModule, NetworkingModule networkingModule, RxModule rxModule, ClockModule clockModule, SharedPreferencesModule sharedPreferencesModule) {
        this.mRegionSpecificUrl = null;
        DaggerDSSComponent.builder().contextModule(contextModule).authModule(authModule).networkingModule(networkingModule).rxModule(rxModule).clockModule(clockModule).sharedPreferencesModule(sharedPreferencesModule).build().inject(this);
    }
}
