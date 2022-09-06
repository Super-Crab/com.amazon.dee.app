package com.amazon.whisperjoin.devicesetupserviceandroidclient;

import com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsV2Output;
import com.amazon.devicesetupservice.v1.GetWiFiNetworksInput;
import com.amazon.devicesetupservice.v1.GetWiFiNetworksOutput;
import com.amazon.devicesetupservice.v1.SaveWifiNetworkInput;
import com.amazon.devicesetupservice.v1.SaveWifiNetworkOutput;
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
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.RecordDevicePossessionIntentInnerBarcodeRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ReportEventRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ReportSmartHomeEventRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.StartEcdheAuthenticationSessionRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.StartEcdheAuthenticationSessionResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ValidateWiFiSyncAuthTokenRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.ValidateWiFiSyncAuthTokenResponse;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit.DSSServiceConfiguration;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
/* loaded from: classes13.dex */
public interface DSSClient {
    Single<ComputeConfigurationDataResponse> computeConfigurationData(ComputeConfigurationDataRequest computeConfigurationDataRequest);

    Single<CreateAPForWifiProvisioneeResponse> createAPForWifiProvisionee(CreateAPForWifiProvisioneeRequest createAPForWifiProvisioneeRequest);

    Single<GenericDSSDiscoveryResponse> discoveredDistressedProvisionee(DiscoveredDistressedProvisioneeRequest discoveredDistressedProvisioneeRequest);

    Single<DiscoveredLocalNotificationProvisioneeResponse> discoveredLocalNotificationProvisionee(DiscoveredLocalNotificationProvisioneeRequest discoveredLocalNotificationProvisioneeRequest);

    Single<GenericDSSDiscoveryResponse> discoveredProvisionableDevice(DiscoveredProvisionableDeviceRequest discoveredProvisionableDeviceRequest);

    Single<DiscoveredSmartHomeProvisioneeResponse> discoveredSmartHomeProvisionee(DiscoveredSmartHomeProvisioneeRequest discoveredSmartHomeProvisioneeRequest);

    Single<FinalizeEcdheAuthenticationSessionResponse> finalizeEcdheAuthenticationSession(FinalizeEcdheAuthenticationSessionRequest finalizeEcdheAuthenticationSessionRequest);

    Single<GetCustomerDevicesCredentialsResponse> getCustomerDevicesCredentials(GetCustomerDevicesCredentialsRequest getCustomerDevicesCredentialsRequest);

    Single<GetCustomerDevicesCredentialsV2Output> getCustomerDevicesCredentialsV2(GetCustomerDevicesCredentialsRequest getCustomerDevicesCredentialsRequest);

    Single<GetCustomerProvisioneesSetupStatusResponse> getCustomerProvisioneesSetupStatus(GetCustomerProvisioneesSetupStatusRequest getCustomerProvisioneesSetupStatusRequest);

    Single<FFSWhiteListPolicyResponse> getFFSWhiteListPolicy(FFSWhiteListPolicyRequest fFSWhiteListPolicyRequest);

    Single<GetRegistrationStatusResponse> getRegistrationStatus(GetRegistrationStatusRequest getRegistrationStatusRequest);

    Single<GetWiFiSyncAuthTokenResponse> getWiFiSyncAuthToken(GetWiFiSyncAuthTokenRequest getWiFiSyncAuthTokenRequest);

    Single<GetWiFiNetworksOutput> getWifiNetworks(GetWiFiNetworksInput getWiFiNetworksInput);

    Completable recordDevicePossessionIntentInnerBarcode(RecordDevicePossessionIntentInnerBarcodeRequest recordDevicePossessionIntentInnerBarcodeRequest);

    Completable reportEvent(ReportEventRequest reportEventRequest);

    Completable reportSmartHomeEvent(ReportSmartHomeEventRequest reportSmartHomeEventRequest);

    Single<SaveWifiNetworkOutput> saveWifiNetwork(SaveWifiNetworkInput saveWifiNetworkInput);

    void setDssApi(DSSServiceConfiguration dSSServiceConfiguration);

    Single<StartEcdheAuthenticationSessionResponse> startEcdheAuthenticationSession(StartEcdheAuthenticationSessionRequest startEcdheAuthenticationSessionRequest);

    Single<ValidateWiFiSyncAuthTokenResponse> validateWiFiSyncAuthToken(ValidateWiFiSyncAuthTokenRequest validateWiFiSyncAuthTokenRequest);
}