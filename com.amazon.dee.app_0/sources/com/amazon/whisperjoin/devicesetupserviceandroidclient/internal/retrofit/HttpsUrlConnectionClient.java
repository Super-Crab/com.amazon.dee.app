package com.amazon.whisperjoin.devicesetupserviceandroidclient.internal.retrofit;

import android.net.Uri;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.Constants;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.FFSWhiteListPolicyRequest;
import com.amazon.whisperjoin.devicesetupserviceandroidclient.data.FFSWhiteListPolicyResponse;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes13.dex */
public class HttpsUrlConnectionClient {
    private static final String AUTHORITY = "wl.amazon-dss.com";
    private static final String FETCH_CONFIG_PATH = "fetchConfig";
    private static final String QUERY_PARAMETER_APPLICATION = "application";
    private static final String QUERY_PARAMETER_APPLICATION_VERSION = "applicationVersion";
    private static final String QUERY_PARAMETER_CUSTOMER_ID = "customerId";
    private static final String QUERY_PARAMETER_DEVICE_MODEL = "deviceModel";
    private static final String QUERY_PARAMETER_FIRMWARE_VERSION = "firmwareVersion";
    private static final String QUERY_PARAMETER_MANUFACTURER = "manufacturer";
    private static final String QUERY_PARAMETER_MARKETPLACE = "marketplace";
    private static final String QUERY_PARAMETER_PLATFORM = "platform";
    private static final String RESPONSE_FFS_SCANNING_ENABLED_KEY = "ffsScanningEnabled";
    private static final String RESPONSE_HOURS_TO_NEXT_CALL_KEY = "hoursToNextCall";
    private static final String RESPONSE_MIN_BATTERY_LEVEL_KEY = "minBatteryLevel";
    private static final String SCHEME = "https";
    private static final String TAG = GeneratedOutlineSupport1.outline39(HttpsUrlConnectionClient.class, GeneratedOutlineSupport1.outline107(Constants.LOG_PREFIX));
    private static final String TLSV12 = "TLSv1.2";
    private HttpsURLConnection mHttpsUrlConnection;
    private SSLSocketFactory mSSLSocketFactory;
    @VisibleForTesting
    URL mURL;

    public HttpsUrlConnectionClient(FFSWhiteListPolicyRequest fFSWhiteListPolicyRequest) throws MalformedURLException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Creating URL with request: ");
        outline107.append(fFSWhiteListPolicyRequest.toString());
        outline107.toString();
        this.mURL = createUrl(fFSWhiteListPolicyRequest);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Created URL: ");
        outline1072.append(this.mURL.toString());
        outline1072.toString();
        this.mSSLSocketFactory = initializeSSLSocketFactory();
    }

    private URL createUrl(FFSWhiteListPolicyRequest fFSWhiteListPolicyRequest) throws MalformedURLException {
        return new URL(new Uri.Builder().scheme("https").authority(AUTHORITY).appendPath(FETCH_CONFIG_PATH).appendQueryParameter("deviceModel", fFSWhiteListPolicyRequest.getDeviceModel()).appendQueryParameter("manufacturer", fFSWhiteListPolicyRequest.getManufacturer()).appendQueryParameter("firmwareVersion", fFSWhiteListPolicyRequest.getFirmwareVersion()).appendQueryParameter("platform", fFSWhiteListPolicyRequest.getPlatform()).appendQueryParameter("application", fFSWhiteListPolicyRequest.getApplication()).appendQueryParameter("applicationVersion", fFSWhiteListPolicyRequest.getApplicationVersion()).appendQueryParameter("marketplace", fFSWhiteListPolicyRequest.getMarketplace()).appendQueryParameter(QUERY_PARAMETER_CUSTOMER_ID, fFSWhiteListPolicyRequest.getCustomerId()).build().toString());
    }

    private SSLSocketFactory initializeSSLSocketFactory() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
            SSLContext sSLContext = SSLContext.getInstance(TLSV12);
            sSLContext.init(null, new TrustManager[]{(X509TrustManager) trustManagers[0]}, null);
            return sSLContext.getSocketFactory();
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unexpected default trust managers: ");
        outline107.append(Arrays.toString(trustManagers));
        throw new IllegalStateException(outline107.toString());
    }

    private FFSWhiteListPolicyResponse parseResponse(HttpsURLConnection httpsURLConnection) throws IOException, JSONException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuffer.append(readLine);
            } else {
                bufferedReader.close();
                String str = "Got response: " + stringBuffer.toString();
                JSONObject jSONObject = new JSONObject(stringBuffer.toString());
                return new FFSWhiteListPolicyResponse(jSONObject.getInt(RESPONSE_MIN_BATTERY_LEVEL_KEY), jSONObject.getInt(RESPONSE_HOURS_TO_NEXT_CALL_KEY), jSONObject.getInt(RESPONSE_FFS_SCANNING_ENABLED_KEY));
            }
        }
    }

    public FFSWhiteListPolicyResponse callWhitelist() throws IOException, JSONException {
        Log.i(TAG, "callWhiteList");
        if (this.mHttpsUrlConnection == null) {
            this.mHttpsUrlConnection = (HttpsURLConnection) this.mURL.openConnection();
        }
        this.mHttpsUrlConnection.setSSLSocketFactory(this.mSSLSocketFactory);
        this.mHttpsUrlConnection.setRequestMethod("GET");
        this.mHttpsUrlConnection.setConnectTimeout(10000);
        this.mHttpsUrlConnection.setReadTimeout(HttpUrlConnectionNetworkFetcher.HTTP_DEFAULT_TIMEOUT);
        this.mHttpsUrlConnection.connect();
        if (this.mHttpsUrlConnection.getResponseCode() == 200) {
            return parseResponse(this.mHttpsUrlConnection);
        }
        return null;
    }

    @VisibleForTesting
    HttpsUrlConnectionClient(FFSWhiteListPolicyRequest fFSWhiteListPolicyRequest, HttpsURLConnection httpsURLConnection) throws MalformedURLException, KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
        this.mHttpsUrlConnection = httpsURLConnection;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Creating URL with request: ");
        outline107.append(fFSWhiteListPolicyRequest.toString());
        outline107.toString();
        this.mURL = createUrl(fFSWhiteListPolicyRequest);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Created URL: ");
        outline1072.append(this.mURL.toString());
        outline1072.toString();
        this.mSSLSocketFactory = initializeSSLSocketFactory();
    }
}
