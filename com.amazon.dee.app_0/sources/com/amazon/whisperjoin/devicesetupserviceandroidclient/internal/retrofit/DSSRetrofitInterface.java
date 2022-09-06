package com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit;

import com.amazon.devicesetupservice.pwsync.v1.GetWifiSyncAuthTokenInput;
import com.amazon.devicesetupservice.pwsync.v1.GetWifiSyncAuthTokenOutput;
import com.amazon.devicesetupservice.pwsync.v1.ValidateWifiSyncAuthTokenInput;
import com.amazon.devicesetupservice.pwsync.v1.ValidateWifiSyncAuthTokenOutput;
import com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsInput;
import com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsOutput;
import com.amazon.devicesetupservice.smarthome.GetCustomerDevicesCredentialsV2Output;
import com.amazon.devicesetupservice.smarthome.ReportSmartHomeEventInput;
import com.amazon.devicesetupservice.smarthome.ReportSmartHomeEventOutput;
import com.amazon.devicesetupservice.v1.ComputeConfigurationDataInput;
import com.amazon.devicesetupservice.v1.ComputeConfigurationDataOutput;
import com.amazon.devicesetupservice.v1.DiscoveredProvisionableDeviceInput;
import com.amazon.devicesetupservice.v1.DiscoveredProvisionableDeviceOutput;
import com.amazon.devicesetupservice.v1.DiscoveredProvisioneeInput;
import com.amazon.devicesetupservice.v1.DiscoveredProvisioneeOutput;
import com.amazon.devicesetupservice.v1.FinalizeEcdheAuthenticationSessionInput;
import com.amazon.devicesetupservice.v1.FinalizeEcdheAuthenticationSessionOutput;
import com.amazon.devicesetupservice.v1.GetCustomerProvisioneesSetupStatusInput;
import com.amazon.devicesetupservice.v1.GetCustomerProvisioneesSetupStatusOutput;
import com.amazon.devicesetupservice.v1.GetDeviceRegistrationStatusInput;
import com.amazon.devicesetupservice.v1.GetDeviceRegistrationStatusOutput;
import com.amazon.devicesetupservice.v1.GetWiFiNetworksInput;
import com.amazon.devicesetupservice.v1.GetWiFiNetworksOutput;
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
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;
/* loaded from: classes13.dex */
public interface DSSRetrofitInterface {
    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/computeConfigurationData")
    Call<ComputeConfigurationDataOutput> computeConfigurationData(@Header("x-amz-access-token") String str, @Body ComputeConfigurationDataInput computeConfigurationDataInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<ComputeConfigurationDataOutput> computeConfigurationData(@Header("x-amz-access-token") String str, @Url String str2, @Body ComputeConfigurationDataInput computeConfigurationDataInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/createAPForWifiProvisionee")
    Call<CreateAPForWifiProvisioneeOutput> createAPForWifiProvisionee(@Header("x-amz-access-token") String str, @Body CreateAPForWifiProvisioneeInput createAPForWifiProvisioneeInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<CreateAPForWifiProvisioneeOutput> createAPForWifiProvisionee(@Header("x-amz-access-token") String str, @Url String str2, @Body CreateAPForWifiProvisioneeInput createAPForWifiProvisioneeInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/discoveredProvisionableDevice")
    Call<DiscoveredProvisionableDeviceOutput> discoveredProvisionableDevice(@Header("x-amz-access-token") String str, @Body DiscoveredProvisionableDeviceInput discoveredProvisionableDeviceInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<DiscoveredProvisionableDeviceOutput> discoveredProvisionableDevice(@Header("x-amz-access-token") String str, @Url String str2, @Body DiscoveredProvisionableDeviceInput discoveredProvisionableDeviceInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/discoveredProvisionee")
    Call<DiscoveredProvisioneeOutput> discoveredProvisionee(@Header("x-amz-access-token") String str, @Body DiscoveredProvisioneeInput discoveredProvisioneeInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<DiscoveredProvisioneeOutput> discoveredProvisionee(@Header("x-amz-access-token") String str, @Url String str2, @Body DiscoveredProvisioneeInput discoveredProvisioneeInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/finalizeEcdheAuthenticationSession")
    Call<FinalizeEcdheAuthenticationSessionOutput> finalizeEcdheAuthSession(@Header("x-amz-access-token") String str, @Body FinalizeEcdheAuthenticationSessionInput finalizeEcdheAuthenticationSessionInput);

    @POST
    Call<FinalizeEcdheAuthenticationSessionOutput> finalizeEcdheAuthSession(@Header("x-amz-access-token") String str, @Url String str2, @Body FinalizeEcdheAuthenticationSessionInput finalizeEcdheAuthenticationSessionInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/getCustomerDevicesCredentials")
    Call<GetCustomerDevicesCredentialsOutput> getCustomerDevicesCredentials(@Header("x-amz-access-token") String str, @Body GetCustomerDevicesCredentialsInput getCustomerDevicesCredentialsInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<GetCustomerDevicesCredentialsOutput> getCustomerDevicesCredentials(@Header("x-amz-access-token") String str, @Url String str2, @Body GetCustomerDevicesCredentialsInput getCustomerDevicesCredentialsInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v2/getCustomerDevicesCredentials")
    Call<GetCustomerDevicesCredentialsV2Output> getCustomerDevicesCredentialsV2(@Header("x-amz-access-token") String str, @Body GetCustomerDevicesCredentialsInput getCustomerDevicesCredentialsInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<GetCustomerDevicesCredentialsV2Output> getCustomerDevicesCredentialsV2(@Header("x-amz-access-token") String str, @Url String str2, @Body GetCustomerDevicesCredentialsInput getCustomerDevicesCredentialsInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/getCustomerProvisioneesSetupStatus")
    Call<GetCustomerProvisioneesSetupStatusOutput> getCustomerProvisioneesSetupStatus(@Header("x-amz-access-token") String str, @Body GetCustomerProvisioneesSetupStatusInput getCustomerProvisioneesSetupStatusInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/getCustomerProvisioneesSetupStatus")
    Call<GetCustomerProvisioneesSetupStatusOutput> getCustomerProvisioneesSetupStatus(@Header("x-amz-access-token") String str, @Url String str2, @Body GetCustomerProvisioneesSetupStatusInput getCustomerProvisioneesSetupStatusInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/getDeviceRegistrationStatus")
    Call<GetDeviceRegistrationStatusOutput> getDeviceRegistrationStatus(@Header("x-amz-access-token") String str, @Body GetDeviceRegistrationStatusInput getDeviceRegistrationStatusInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<GetDeviceRegistrationStatusOutput> getDeviceRegistrationStatus(@Header("x-amz-access-token") String str, @Url String str2, @Body GetDeviceRegistrationStatusInput getDeviceRegistrationStatusInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/getWifiSyncAuthToken")
    Call<GetWifiSyncAuthTokenOutput> getWiFiSyncAuthToken(@Header("x-amz-access-token") String str, @Body GetWifiSyncAuthTokenInput getWifiSyncAuthTokenInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<GetWifiSyncAuthTokenOutput> getWiFiSyncAuthToken(@Header("x-amz-access-token") String str, @Url String str2, @Body GetWifiSyncAuthTokenInput getWifiSyncAuthTokenInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/GetWiFiNetworks")
    Call<GetWiFiNetworksOutput> getWifiNetworks(@Header("x-amz-access-token") String str, @Body GetWiFiNetworksInput getWiFiNetworksInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<GetWiFiNetworksOutput> getWifiNetworks(@Header("x-amz-access-token") String str, @Url String str2, @Body GetWiFiNetworksInput getWiFiNetworksInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/recordDevicePossessionIntentInnerBarcode")
    Call<RecordDevicePossessionIntentInnerBarcodeOutput> recordDevicePossessionIntentInnerBarcode(@Header("x-amz-access-token") String str, @Body RecordDevicePossessionIntentInnerBarcodeInput recordDevicePossessionIntentInnerBarcodeInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<RecordDevicePossessionIntentInnerBarcodeOutput> recordDevicePossessionIntentInnerBarcode(@Header("x-amz-access-token") String str, @Url String str2, @Body RecordDevicePossessionIntentInnerBarcodeInput recordDevicePossessionIntentInnerBarcodeInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<ReportEventOutput> reportEvent(@Url String str, @Body ReportEventInput reportEventInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<ReportSmartHomeEventOutput> reportSmartHomeEvent(@Header("x-amz-access-token") String str, @Url String str2, @Body ReportSmartHomeEventInput reportSmartHomeEventInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/SaveWifiNetwork")
    Call<SaveWifiNetworkOutput> saveWifiNetwork(@Header("x-amz-access-token") String str, @Body SaveWifiNetworkInput saveWifiNetworkInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<SaveWifiNetworkOutput> saveWifiNetwork(@Header("x-amz-access-token") String str, @Url String str2, @Body SaveWifiNetworkInput saveWifiNetworkInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/startEcdheAuthenticationSession")
    Call<StartEcdheAuthenticationSessionOutput> startEcdheAuthSession(@Header("x-amz-access-token") String str, @Body StartEcdheAuthenticationSessionInput startEcdheAuthenticationSessionInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<StartEcdheAuthenticationSessionOutput> startEcdheAuthSession(@Header("x-amz-access-token") String str, @Url String str2, @Body StartEcdheAuthenticationSessionInput startEcdheAuthenticationSessionInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST("/v1/validateWifiSyncAuthToken")
    Call<ValidateWifiSyncAuthTokenOutput> validateWiFiSyncAuthToken(@Header("x-amz-access-token") String str, @Body ValidateWifiSyncAuthTokenInput validateWifiSyncAuthTokenInput);

    @Headers({"Content-Type: application/json", "Accept: application/json", "Accept-Language: en-US"})
    @POST
    Call<ValidateWifiSyncAuthTokenOutput> validateWiFiSyncAuthToken(@Header("x-amz-access-token") String str, @Url String str2, @Body ValidateWifiSyncAuthTokenInput validateWifiSyncAuthTokenInput);
}
