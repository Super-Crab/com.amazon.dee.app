package com.amazon.whisperjoin.credentiallocker;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import com.amazon.client.metrics.common.AndroidMetricsFactoryImpl;
import com.amazon.client.metrics.common.Channel;
import com.amazon.client.metrics.common.MetricEvent;
import com.amazon.client.metrics.common.MetricsFactory;
import com.amazon.client.metrics.common.Priority;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.deecomms.calling.accessibility.RealTimeTextConstants;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.whisperjoin.credentiallocker.metrics.TargetDevice;
import com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics;
import com.amazon.whisperjoin.provisioning.metrics.internal.operational.CredentialLockerMetricConstants;
import com.amazon.whisperjoin.provisioning.metrics.internal.operational.InternalMetricsHelper;
import com.amazon.whisperjoin.wifi.WifiConfiguration;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Locale;
/* loaded from: classes13.dex */
public class CredentialLockerClient {
    private static final String CRED_LOCKER_ENDPOINT = "https://credential-locker-service.amazon.com";
    private static final String TAG = "CredentialLockerClient";
    private final Gson gson;
    private String mClientManufacturer;
    private String mClientModel;
    private String mClientName;
    private String mClientOs;
    private String mClientOsVersion;
    private String mClientProduct;
    private String mClientVersion;
    private final HttpUrlConnectionFactory mConnectionFactory;
    private MetricsFactory mMetricsFactory;
    private final SetupAttemptMetricHelper mSetupAttemptMetricHelper;
    private final CredLockerUrlBuilder mUrlBuilder;
    private final WifiConfigurationTypeAdapter wifiConfigurationTypeAdapter;

    public CredentialLockerClient(Context context, AuthenticationMethod authenticationMethod) throws PackageManager.NameNotFoundException {
        this.wifiConfigurationTypeAdapter = new WifiConfigurationTypeAdapter();
        this.gson = new GsonBuilder().registerTypeAdapter(WifiConfiguration.class, this.wifiConfigurationTypeAdapter).registerTypeAdapter(com.amazon.credentiallocker.WifiConfiguration.class, this.wifiConfigurationTypeAdapter).create();
        this.mConnectionFactory = defaultConnectionFactory(authenticationMethod);
        this.mUrlBuilder = defaultUrlBuilder(defaultConnectionFactory(authenticationMethod));
        this.mSetupAttemptMetricHelper = SetupAttemptMetricHelper.getInstance();
        InternalMetricsHelper.init(context, AndroidMetricsFactoryImpl.getInstance(context));
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        this.mClientName = context.getPackageName();
        this.mClientVersion = packageInfo.versionName;
        this.mClientProduct = Build.PRODUCT;
        this.mClientModel = Build.MODEL;
        this.mClientManufacturer = Build.MANUFACTURER;
        this.mClientOs = "Android";
        this.mClientOsVersion = Build.VERSION.RELEASE;
        this.mMetricsFactory = AndroidMetricsFactoryImpl.getInstance(context);
    }

    private static HttpUrlConnectionFactory defaultConnectionFactory(AuthenticationMethod authenticationMethod) {
        return new AuthenticatedUrlConnectionFactory(authenticationMethod);
    }

    private static CredLockerEndpointResolver defaultEndpointResolver(HttpUrlConnectionFactory httpUrlConnectionFactory) {
        return new CredLockerEndpointResolverImpl(httpUrlConnectionFactory, CRED_LOCKER_ENDPOINT);
    }

    private static CredLockerUrlBuilder defaultUrlBuilder(HttpUrlConnectionFactory httpUrlConnectionFactory) {
        return new CredLockerUrlBuilderImpl(defaultEndpointResolver(httpUrlConnectionFactory));
    }

    private void deleteWifiCredentials(URL url) throws WhisperJoinException {
        String str = "Deleting " + url;
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                httpURLConnection = this.mConnectionFactory.newConnection(url);
                httpURLConnection.setRequestMethod(com.amazon.alexa.redesign.utils.Constants.REQUEST_METHOD_DELETE);
                addClientMetrics(httpURLConnection);
                String.format(Locale.ENGLISH, "Response: %d %s", Integer.valueOf(httpURLConnection.getResponseCode()), httpURLConnection.getResponseMessage());
                httpURLConnection.getInputStream();
                httpURLConnection.disconnect();
            } catch (IOException e) {
                Log.e(TAG, "I/O error: " + e.getMessage());
                throw new WhisperJoinRemoteException(getErrorMessage(httpURLConnection), e);
            }
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private String getErrorMessage(HttpURLConnection httpURLConnection) {
        Throwable th;
        InputStream inputStream;
        IOException e;
        JsonParseException e2;
        InputStream inputStream2;
        String str = "";
        String str2 = "Unknown error";
        if (httpURLConnection != null) {
            InputStreamReader inputStreamReader = null;
            try {
                try {
                    str = httpURLConnection.getResponseMessage() + RealTimeTextConstants.COLON_SPACE;
                    inputStream = httpURLConnection.getErrorStream();
                    try {
                        InputStreamReader inputStreamReader2 = new InputStreamReader(inputStream, Charsets.UTF_8);
                        try {
                            WifiConfigurationsErrorResponse wifiConfigurationsErrorResponse = (WifiConfigurationsErrorResponse) this.gson.fromJson(CharStreams.toString(inputStreamReader2), (Class<Object>) WifiConfigurationsErrorResponse.class);
                            if (wifiConfigurationsErrorResponse.getMessage() != null) {
                                str2 = wifiConfigurationsErrorResponse.getMessage();
                            }
                            Closeables.closeQuietly(inputStreamReader2);
                            inputStream2 = inputStream;
                        } catch (JsonParseException e3) {
                            e2 = e3;
                            inputStreamReader = inputStreamReader2;
                            String str3 = "JSON error: " + e2.getMessage();
                            httpURLConnection = inputStream;
                            Closeables.closeQuietly(inputStreamReader);
                            inputStream2 = httpURLConnection;
                            Closeables.closeQuietly(inputStream2);
                            return GeneratedOutlineSupport1.outline72(str, str2);
                        } catch (IOException e4) {
                            e = e4;
                            inputStreamReader = inputStreamReader2;
                            String str4 = "I/O error: " + e.getMessage();
                            httpURLConnection = inputStream;
                            Closeables.closeQuietly(inputStreamReader);
                            inputStream2 = httpURLConnection;
                            Closeables.closeQuietly(inputStream2);
                            return GeneratedOutlineSupport1.outline72(str, str2);
                        } catch (Throwable th2) {
                            th = th2;
                            inputStreamReader = inputStreamReader2;
                            Closeables.closeQuietly(inputStreamReader);
                            Closeables.closeQuietly(inputStream);
                            throw th;
                        }
                    } catch (JsonParseException e5) {
                        e2 = e5;
                    } catch (IOException e6) {
                        e = e6;
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    inputStreamReader = inputStreamReader;
                    inputStream = httpURLConnection;
                }
            } catch (JsonParseException e7) {
                e2 = e7;
                inputStream = null;
            } catch (IOException e8) {
                e = e8;
                inputStream = null;
            } catch (Throwable th5) {
                th = th5;
                inputStream = null;
            }
            Closeables.closeQuietly(inputStream2);
        }
        return GeneratedOutlineSupport1.outline72(str, str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:65:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0158  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0163  */
    /* JADX WARN: Type inference failed for: r18v0, types: [com.amazon.whisperjoin.credentiallocker.CredentialLockerClient] */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.amazon.whisperjoin.credentiallocker.metrics.TargetDevice] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v12 */
    /* JADX WARN: Type inference failed for: r3v14, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<com.amazon.whisperjoin.wifi.WifiConfiguration> getWifiConfigurations(java.net.URL r19, com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics r20, com.amazon.whisperjoin.credentiallocker.metrics.TargetDevice r21) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.credentiallocker.CredentialLockerClient.getWifiConfigurations(java.net.URL, com.amazon.whisperjoin.provisioning.metrics.client.SetupAttemptMetrics, com.amazon.whisperjoin.credentiallocker.metrics.TargetDevice):java.util.List");
    }

    public static String hashString(String str, String str2) {
        try {
            return Base64.encodeToString(MessageDigest.getInstance(str2).digest(str.getBytes("UTF-8")), 2);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException("Could not generate hash from String", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void postWifiConfigurations(java.net.URL r7, com.amazon.whisperjoin.credentiallocker.WifiConfigurationsRequest r8) throws com.amazon.whisperjoin.credentiallocker.WhisperJoinException {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "Posting "
            r0.append(r1)
            r0.append(r7)
            r0.toString()
            r0 = 0
            com.amazon.whisperjoin.credentiallocker.HttpUrlConnectionFactory r1 = r6.mConnectionFactory     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L75
            java.net.HttpURLConnection r7 = r1.newConnection(r7)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L75
            java.lang.String r1 = "POST"
            r7.setRequestMethod(r1)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.lang.String r1 = "Content-Type"
            java.lang.String r2 = "application/json"
            r7.setRequestProperty(r1, r2)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r1 = 1
            r7.setDoOutput(r1)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r6.addClientMetrics(r7)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.io.OutputStreamWriter r2 = new java.io.OutputStreamWriter     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.io.OutputStream r3 = r7.getOutputStream()     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            java.nio.charset.Charset r4 = com.google.common.base.Charsets.UTF_8     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L6a java.io.IOException -> L6d
            com.google.gson.Gson r0 = r6.gson     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            java.lang.String r8 = r0.toJson(r8)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r2.write(r8)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r2.flush()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            java.util.Locale r8 = java.util.Locale.ENGLISH     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            java.lang.String r0 = "Response: %d %s"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r4 = 0
            int r5 = r7.getResponseCode()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r3[r4] = r5     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            java.lang.String r4 = r7.getResponseMessage()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r3[r1] = r4     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            java.lang.String.format(r8, r0, r3)     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r7.getInputStream()     // Catch: java.lang.Throwable -> L66 java.io.IOException -> L68
            r7.disconnect()
            r2.close()     // Catch: java.io.IOException -> L65
        L65:
            return
        L66:
            r8 = move-exception
            goto L9d
        L68:
            r8 = move-exception
            goto L6f
        L6a:
            r8 = move-exception
            r2 = r0
            goto L9d
        L6d:
            r8 = move-exception
            r2 = r0
        L6f:
            r0 = r7
            goto L77
        L71:
            r8 = move-exception
            r7 = r0
            r2 = r7
            goto L9d
        L75:
            r8 = move-exception
            r2 = r0
        L77:
            java.lang.String r7 = "CredentialLockerClient"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9b
            r1.<init>()     // Catch: java.lang.Throwable -> L9b
            java.lang.String r3 = "I/O error: "
            r1.append(r3)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r3 = r8.getMessage()     // Catch: java.lang.Throwable -> L9b
            r1.append(r3)     // Catch: java.lang.Throwable -> L9b
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L9b
            android.util.Log.e(r7, r1)     // Catch: java.lang.Throwable -> L9b
            com.amazon.whisperjoin.credentiallocker.WhisperJoinRemoteException r7 = new com.amazon.whisperjoin.credentiallocker.WhisperJoinRemoteException     // Catch: java.lang.Throwable -> L9b
            java.lang.String r1 = r6.getErrorMessage(r0)     // Catch: java.lang.Throwable -> L9b
            r7.<init>(r1, r8)     // Catch: java.lang.Throwable -> L9b
            throw r7     // Catch: java.lang.Throwable -> L9b
        L9b:
            r8 = move-exception
            r7 = r0
        L9d:
            if (r7 == 0) goto La2
            r7.disconnect()
        La2:
            if (r2 == 0) goto La7
            r2.close()     // Catch: java.io.IOException -> La7
        La7:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.whisperjoin.credentiallocker.CredentialLockerClient.postWifiConfigurations(java.net.URL, com.amazon.whisperjoin.credentiallocker.WifiConfigurationsRequest):void");
    }

    void addClientMetrics(HttpURLConnection httpURLConnection) {
        Log.i(TAG, "Setting client metrics as headers");
        httpURLConnection.setRequestProperty(Constants.CLIENT_NAME_HEADER, this.mClientName);
        httpURLConnection.setRequestProperty(Constants.CLIENT_VERSION_HEADER, this.mClientVersion);
        httpURLConnection.setRequestProperty(Constants.CLIENT_PRODUCT_HEADER, this.mClientProduct);
        httpURLConnection.setRequestProperty(Constants.CLIENT_MODEL_HEADER, this.mClientModel);
        httpURLConnection.setRequestProperty(Constants.CLIENT_MANUFACTURER_HEADER, this.mClientManufacturer);
        httpURLConnection.setRequestProperty(Constants.CLIENT_OS_HEADER, this.mClientOs);
        httpURLConnection.setRequestProperty(Constants.CLIENT_OS_VERSION_HEADER, this.mClientOsVersion);
    }

    void addTargetMetrics(HttpURLConnection httpURLConnection, TargetDevice targetDevice) {
        if (targetDevice == null) {
            return;
        }
        httpURLConnection.setRequestProperty(Constants.TARGET_NAME_HEADER, targetDevice.getTargetName());
        httpURLConnection.setRequestProperty(Constants.TARGET_VERSION_HEADER, targetDevice.getTargetVersion());
        httpURLConnection.setRequestProperty(Constants.TARGET_MANUFACTURER_HEADER, targetDevice.getTargetManufacturer());
    }

    public void deleteAllWifiCredentials() throws WhisperJoinException {
        try {
            deleteWifiCredentials(this.mUrlBuilder.getWifiConfigurationsUrl());
            this.mSetupAttemptMetricHelper.onLockerNetworksDeleted();
        } catch (IOException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("URL error: ");
            outline107.append(e.getMessage());
            Log.e(TAG, outline107.toString());
            throw new WhisperJoinRemoteException(e);
        }
    }

    public List<WifiConfiguration> getAllWifiConfigurations(TargetDevice targetDevice) throws WhisperJoinException {
        return getAllWifiConfigurations(null, targetDevice);
    }

    public void optOutForWifiConfiguration(WifiConfiguration wifiConfiguration) {
        optOutFromLocker(true, wifiConfiguration);
    }

    void optOutFromLocker(boolean z, WifiConfiguration wifiConfiguration) {
        MetricEvent createMetricEvent = this.mMetricsFactory.createMetricEvent("CredentialLockerDeviceClient", "OptOut");
        createMetricEvent.addCounter("OptOut", z ? 1.0d : FrostVideoEffectController.VIDEO_STRENGTH_CLEAR);
        createMetricEvent.addString("SSID", hashString(wifiConfiguration.getSsid(), "SHA-256"));
        createMetricEvent.addString("KeyMgmt", wifiConfiguration.getKeyMgmt().toString());
        createMetricEvent.addString("LockerClient", this.mClientName);
        createMetricEvent.addString("LockerClientOS", this.mClientOs);
        this.mMetricsFactory.record(createMetricEvent, Priority.NORMAL, Channel.NON_ANONYMOUS);
    }

    public void saveWifiConfigurations(List<WifiConfiguration> list) throws WhisperJoinException {
        saveWifiConfigurations(null, null, list);
    }

    public List<WifiConfiguration> getAllWifiConfigurations(SetupAttemptMetrics setupAttemptMetrics, TargetDevice targetDevice) throws WhisperJoinException {
        try {
            return getWifiConfigurations(this.mUrlBuilder.getWifiConfigurationsUrl(), setupAttemptMetrics, targetDevice);
        } catch (IOException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("I/O error: ");
            outline107.append(e.getMessage());
            Log.e(TAG, outline107.toString());
            throw new WhisperJoinRemoteException(e);
        }
    }

    public void saveWifiConfigurations(String str, String str2, List<WifiConfiguration> list) throws WhisperJoinException {
        saveWifiConfigurations(str, str2, list, null);
    }

    public void saveWifiConfigurations(List<WifiConfiguration> list, SetupAttemptMetrics setupAttemptMetrics) throws WhisperJoinException {
        saveWifiConfigurations(null, null, list, setupAttemptMetrics);
    }

    public void saveWifiConfigurations(String str, String str2, List<WifiConfiguration> list, SetupAttemptMetrics setupAttemptMetrics) throws WhisperJoinException {
        IOException e;
        MetricEvent newMetricEvent = InternalMetricsHelper.newMetricEvent("WhisperJoinAndroid", CredentialLockerMetricConstants.CREDENTIAL_LOCKER_SAVE_WIFI_NETWORKS_SOURCE_NAME);
        long currentTimeMillis = System.currentTimeMillis();
        WifiConfigurationsRequest wifiConfigurationsRequest = new WifiConfigurationsRequest(str2, str, list);
        String str3 = "SuccessLatency";
        double d = 1.0d;
        boolean z = false;
        try {
            try {
                if (str == null && str2 == null) {
                    postWifiConfigurations(this.mUrlBuilder.getWifiConfigurationsUrl(), wifiConfigurationsRequest);
                } else {
                    postWifiConfigurations(this.mUrlBuilder.getWifiConfigurationsUrl(str, str2), wifiConfigurationsRequest);
                }
                if (setupAttemptMetrics != null) {
                    try {
                        this.mSetupAttemptMetricHelper.onLockerNetworksSaved(setupAttemptMetrics, list);
                    } catch (IOException e2) {
                        e = e2;
                        Log.e(TAG, "I/O error: " + e.getMessage());
                        throw new WhisperJoinRemoteException(e);
                    } catch (Throwable th) {
                        z = true;
                        th = th;
                        if (!z) {
                            d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
                        }
                        newMetricEvent.addCounter("Success", d);
                        if (!z) {
                            str3 = "FailureLatency";
                        }
                        newMetricEvent.addTimer(str3, System.currentTimeMillis() - currentTimeMillis);
                        InternalMetricsHelper.record(newMetricEvent);
                        throw th;
                    }
                }
                for (WifiConfiguration wifiConfiguration : list) {
                    optOutFromLocker(false, wifiConfiguration);
                }
                newMetricEvent.addCounter("Success", 1.0d);
                newMetricEvent.addTimer(str3, System.currentTimeMillis() - currentTimeMillis);
                InternalMetricsHelper.record(newMetricEvent);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    @VisibleForTesting
    CredentialLockerClient(HttpUrlConnectionFactory httpUrlConnectionFactory, CredLockerUrlBuilder credLockerUrlBuilder, String str, String str2, MetricsFactory metricsFactory) {
        this.wifiConfigurationTypeAdapter = new WifiConfigurationTypeAdapter();
        this.gson = new GsonBuilder().registerTypeAdapter(WifiConfiguration.class, this.wifiConfigurationTypeAdapter).registerTypeAdapter(com.amazon.credentiallocker.WifiConfiguration.class, this.wifiConfigurationTypeAdapter).create();
        this.mConnectionFactory = httpUrlConnectionFactory;
        this.mUrlBuilder = credLockerUrlBuilder;
        this.mSetupAttemptMetricHelper = SetupAttemptMetricHelper.getInstance();
        this.mClientName = str;
        this.mClientVersion = str2;
        this.mClientProduct = Build.PRODUCT;
        this.mClientModel = Build.MODEL;
        this.mClientManufacturer = Build.MANUFACTURER;
        this.mClientOs = "Android";
        this.mClientOsVersion = Build.VERSION.RELEASE;
        this.mMetricsFactory = metricsFactory;
    }
}
