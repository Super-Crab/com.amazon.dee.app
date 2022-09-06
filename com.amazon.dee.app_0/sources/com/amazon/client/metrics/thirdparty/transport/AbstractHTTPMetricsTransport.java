package com.amazon.client.metrics.thirdparty.transport;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.system.ErrnoException;
import android.system.OsConstants;
import android.util.Log;
import com.amazon.client.metrics.thirdparty.configuration.CodecConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.CodecType;
import com.amazon.client.metrics.thirdparty.configuration.HttpConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.client.metrics.thirdparty.configuration.NetworkType;
import com.amazon.client.metrics.thirdparty.transport.TransportStateNotifier;
import com.amazon.device.utils.thirdparty.DeviceUtil;
import com.amazon.device.utils.thirdparty.NetworkManager;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletResponse;
/* loaded from: classes11.dex */
public abstract class AbstractHTTPMetricsTransport implements MetricsTransport, TransportStateNotifier {
    protected static final String BATCH_UPLOAD_PATH = "/metricsBatch";
    protected final String TAG = getClass().getSimpleName();
    protected final Context mContext;
    protected final DeviceUtil mDeviceUtil;
    protected final MetricsTransport mFallbackTransport;
    protected final MetricsConfiguration mMetricsConfiguration;
    protected final URL mURL;

    public AbstractHTTPMetricsTransport(Context context, MetricsConfiguration metricsConfiguration, DeviceUtil deviceUtil, MetricsTransport metricsTransport) {
        validate(context, metricsConfiguration, deviceUtil);
        this.mContext = context;
        this.mMetricsConfiguration = metricsConfiguration;
        this.mDeviceUtil = deviceUtil;
        this.mURL = resolveEndpoint();
        this.mFallbackTransport = metricsTransport;
    }

    private PowerManager.WakeLock acquireWakeLock() {
        long wakeLockTimeout = this.mMetricsConfiguration.getHttpConfiguration().getWakeLockTimeout();
        String str = this.TAG;
        Log.i(str, "Acquiring wake lock with timeout: " + wakeLockTimeout + "ms");
        PowerManager.WakeLock createWakeLock = NetworkManager.instance(this.mContext).createWakeLock("HTTPMetricsTransportWakeLock");
        if (createWakeLock != null) {
            createWakeLock.setReferenceCounted(false);
            NetworkManager.instance(this.mContext).acquireWakeLock(createWakeLock, wakeLockTimeout);
        }
        return createWakeLock;
    }

    private WifiManager.WifiLock acquireWifiLock() {
        Log.i(this.TAG, "Acquiring wifi lock");
        WifiManager.WifiLock createWifiLock = NetworkManager.instance(this.mContext).createWifiLock("HTTPMetricsTransport");
        NetworkManager.instance(this.mContext).acquireWifiLock(createWifiLock);
        return createWifiLock;
    }

    private void addProtobufHeaders(HttpURLConnection httpURLConnection) {
        CodecConfiguration codecConfiguration = this.mMetricsConfiguration.getCodecConfiguration();
        if (codecConfiguration.getCodecType() == CodecType.PROTOCOL_BUFFERS) {
            httpURLConnection.setRequestProperty("Content-Type", "application/octet-stream");
            httpURLConnection.setRequestProperty(MetricsConfiguration.PROTOCOL_BUFFER_CODEC_FORMAT_HEADER, codecConfiguration.getCodecType().getName());
            httpURLConnection.setRequestProperty(MetricsConfiguration.PROTOCOL_BUFFER_CODEC_VERSION_HEADER, codecConfiguration.getCodecVersion());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x007a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void consumeInputStream(java.net.HttpURLConnection r9) {
        /*
            r8 = this;
            java.lang.String r0 = "IOException closing response InputStream"
            r1 = 0
            java.io.InputStream r9 = r9.getInputStream()     // Catch: java.lang.Throwable -> L8 java.io.IOException -> Lc java.io.FileNotFoundException -> L19
            goto L1d
        L8:
            r9 = move-exception
            r2 = r1
            goto L78
        Lc:
            r2 = move-exception
            java.lang.String r3 = r8.TAG     // Catch: java.lang.Throwable -> L8
            java.lang.String r4 = "IOException opening response InputStream"
            android.util.Log.w(r3, r4, r2)     // Catch: java.lang.Throwable -> L8
            java.io.InputStream r9 = r9.getErrorStream()     // Catch: java.lang.Throwable -> L8
            goto L1d
        L19:
            java.io.InputStream r9 = r9.getErrorStream()     // Catch: java.lang.Throwable -> L8
        L1d:
            if (r9 == 0) goto L6b
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L8
            r2.<init>(r9)     // Catch: java.lang.Throwable -> L8
            r1 = 1024(0x400, float:1.435E-42)
            char[] r1 = new char[r1]     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r3.<init>()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            java.lang.String r5 = "UTF-8"
            r4.<init>(r9, r5)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r9 = 0
            r5 = r9
        L36:
            int r6 = r4.read(r1)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            if (r6 < 0) goto L45
            r7 = 4
            if (r5 >= r7) goto L45
            r3.append(r1, r9, r6)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            int r5 = r5 + 1
            goto L36
        L45:
            java.lang.String r9 = r8.TAG     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r1.<init>()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            java.lang.String r4 = "Response body: "
            r1.append(r4)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            r1.append(r3)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            android.util.Log.i(r9, r1)     // Catch: java.lang.Throwable -> L60 java.io.IOException -> L62
            goto L6a
        L60:
            r9 = move-exception
            goto L78
        L62:
            r9 = move-exception
            java.lang.String r1 = r8.TAG     // Catch: java.lang.Throwable -> L60
            java.lang.String r3 = "IOException reading response InputStream"
            android.util.Log.e(r1, r3, r9)     // Catch: java.lang.Throwable -> L60
        L6a:
            r1 = r2
        L6b:
            if (r1 == 0) goto L77
            r1.close()     // Catch: java.io.IOException -> L71
            goto L77
        L71:
            r9 = move-exception
            java.lang.String r1 = r8.TAG
            android.util.Log.e(r1, r0, r9)
        L77:
            return
        L78:
            if (r2 == 0) goto L84
            r2.close()     // Catch: java.io.IOException -> L7e
            goto L84
        L7e:
            r1 = move-exception
            java.lang.String r2 = r8.TAG
            android.util.Log.e(r2, r0, r1)
        L84:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.client.metrics.thirdparty.transport.AbstractHTTPMetricsTransport.consumeInputStream(java.net.HttpURLConnection):void");
    }

    private UploadResult handleResponse(HttpURLConnection httpURLConnection) throws IOException {
        int responseCode = httpURLConnection.getResponseCode();
        String responseMessage = httpURLConnection.getResponseMessage();
        consumeInputStream(httpURLConnection);
        if (responseCode != 400 && responseCode != 401) {
            switch (responseCode) {
                case 200:
                case 201:
                case 202:
                    Log.i(this.TAG, String.format("Successfully uploaded metrics data; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
                    return new UploadResult(1, responseCode);
                default:
                    switch (responseCode) {
                        case 403:
                        case 404:
                        case HttpServletResponse.SC_METHOD_NOT_ALLOWED /* 405 */:
                            break;
                        default:
                            switch (responseCode) {
                                case 500:
                                case 501:
                                case 502:
                                case 503:
                                case 504:
                                    Log.e(this.TAG, String.format("Server error while uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
                                    return new UploadResult(4, responseCode);
                                default:
                                    Log.i(this.TAG, String.format("Unexpected response code while uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
                                    return new UploadResult(6, responseCode);
                            }
                    }
            }
        }
        Log.e(this.TAG, String.format("Client error while uploading metrics; code: %s, message: %s", Integer.valueOf(responseCode), responseMessage));
        return new UploadResult(3, responseCode);
    }

    private void validate(Context context, MetricsConfiguration metricsConfiguration, DeviceUtil deviceUtil) {
        if (context != null) {
            if (metricsConfiguration == null) {
                throw new IllegalArgumentException("MetricsConfiguration may not be null");
            }
            if (deviceUtil == null) {
                throw new IllegalArgumentException("DeviceUtil may not be null");
            }
            return;
        }
        throw new IllegalArgumentException("Context may not be null");
    }

    protected UploadResult attemptTransmit(byte[] bArr) {
        PowerManager.WakeLock wakeLock;
        WifiManager.WifiLock wifiLock;
        WifiManager.WifiLock wifiLock2;
        if (bArr != null && bArr.length != 0) {
            if (!isWifiAvailable() && !isEthernetAvailable() && !isWANAvailable()) {
                Log.w(this.TAG, "Aborting metrics transmission because there is no usable connection");
                return new UploadResult(7);
            }
            HttpConfiguration httpConfiguration = this.mMetricsConfiguration.getHttpConfiguration();
            HttpURLConnection httpURLConnection = null;
            try {
                try {
                    wakeLock = acquireWakeLock();
                } catch (Throwable th) {
                    th = th;
                }
            } catch (AuthenticatedURLConnection.NoCredentialsException e) {
                e = e;
                wakeLock = null;
                wifiLock2 = null;
            } catch (SocketTimeoutException e2) {
                e = e2;
                wakeLock = null;
                wifiLock2 = null;
            } catch (UnknownHostException e3) {
                e = e3;
                wakeLock = null;
                wifiLock2 = null;
            } catch (IOException e4) {
                e = e4;
                wakeLock = null;
                wifiLock2 = null;
            } catch (Throwable th2) {
                th = th2;
                wakeLock = null;
                wifiLock = null;
            }
            try {
                wifiLock2 = acquireWifiLock();
                try {
                    HttpURLConnection openConnection = openConnection(this.mURL);
                    openConnection.setConnectTimeout(httpConfiguration.getConnectTimeout());
                    openConnection.setReadTimeout(httpConfiguration.getConnectTimeout());
                    openConnection.setFixedLengthStreamingMode(bArr.length);
                    openConnection.setDoOutput(true);
                    openConnection.setRequestMethod("POST");
                    addProtobufHeaders(openConnection);
                    if (!signRequest(openConnection)) {
                        UploadResult uploadResult = new UploadResult(5);
                        openConnection.disconnect();
                        Log.i(this.TAG, "Manually releasing wifi lock");
                        if (wifiLock2 != null) {
                            NetworkManager.instance(this.mContext).releaseWifiLock(wifiLock2);
                        }
                        Log.i(this.TAG, "Manually releasing wake lock");
                        if (wakeLock != null) {
                            NetworkManager.instance(this.mContext).releaseWakeLock(wakeLock);
                        }
                        return uploadResult;
                    }
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(openConnection.getOutputStream());
                    bufferedOutputStream.write(bArr);
                    bufferedOutputStream.flush();
                    UploadResult handleResponse = handleResponse(openConnection);
                    openConnection.disconnect();
                    Log.i(this.TAG, "Manually releasing wifi lock");
                    if (wifiLock2 != null) {
                        NetworkManager.instance(this.mContext).releaseWifiLock(wifiLock2);
                    }
                    Log.i(this.TAG, "Manually releasing wake lock");
                    if (wakeLock != null) {
                        NetworkManager.instance(this.mContext).releaseWakeLock(wakeLock);
                    }
                    return handleResponse;
                } catch (AuthenticatedURLConnection.NoCredentialsException e5) {
                    e = e5;
                    Log.w(this.TAG, "NoCredentialsException thrown while uploading", e);
                    UploadResult uploadResult2 = new UploadResult(5, e);
                    if (0 != 0) {
                        httpURLConnection.disconnect();
                    }
                    Log.i(this.TAG, "Manually releasing wifi lock");
                    if (wifiLock2 != null) {
                        NetworkManager.instance(this.mContext).releaseWifiLock(wifiLock2);
                    }
                    Log.i(this.TAG, "Manually releasing wake lock");
                    if (wakeLock != null) {
                        NetworkManager.instance(this.mContext).releaseWakeLock(wakeLock);
                    }
                    return uploadResult2;
                } catch (SocketTimeoutException e6) {
                    e = e6;
                    Log.w(this.TAG, "SocketTimeoutException thrown while uploading", e);
                    UploadResult uploadResult3 = new UploadResult(2, e);
                    if (0 != 0) {
                        httpURLConnection.disconnect();
                    }
                    Log.i(this.TAG, "Manually releasing wifi lock");
                    if (wifiLock2 != null) {
                        NetworkManager.instance(this.mContext).releaseWifiLock(wifiLock2);
                    }
                    Log.i(this.TAG, "Manually releasing wake lock");
                    if (wakeLock != null) {
                        NetworkManager.instance(this.mContext).releaseWakeLock(wakeLock);
                    }
                    return uploadResult3;
                } catch (UnknownHostException e7) {
                    e = e7;
                    Log.w(this.TAG, "UnknownHostException thrown while uploading", e);
                    UploadResult uploadResult4 = new UploadResult(7, e);
                    if (0 != 0) {
                        httpURLConnection.disconnect();
                    }
                    Log.i(this.TAG, "Manually releasing wifi lock");
                    if (wifiLock2 != null) {
                        NetworkManager.instance(this.mContext).releaseWifiLock(wifiLock2);
                    }
                    Log.i(this.TAG, "Manually releasing wake lock");
                    if (wakeLock != null) {
                        NetworkManager.instance(this.mContext).releaseWakeLock(wakeLock);
                    }
                    return uploadResult4;
                } catch (IOException e8) {
                    e = e8;
                    Log.w(this.TAG, "IOException thrown while uploading", e);
                    if (isConnectionRefusedError(e)) {
                        Log.w(this.TAG, "IOException is an ECONNREFUSED. Reporting unusable connection.");
                        UploadResult uploadResult5 = new UploadResult(7, e);
                        if (0 != 0) {
                            httpURLConnection.disconnect();
                        }
                        Log.i(this.TAG, "Manually releasing wifi lock");
                        if (wifiLock2 != null) {
                            NetworkManager.instance(this.mContext).releaseWifiLock(wifiLock2);
                        }
                        Log.i(this.TAG, "Manually releasing wake lock");
                        if (wakeLock != null) {
                            NetworkManager.instance(this.mContext).releaseWakeLock(wakeLock);
                        }
                        return uploadResult5;
                    }
                    UploadResult uploadResult6 = new UploadResult(10, e);
                    if (0 != 0) {
                        httpURLConnection.disconnect();
                    }
                    Log.i(this.TAG, "Manually releasing wifi lock");
                    if (wifiLock2 != null) {
                        NetworkManager.instance(this.mContext).releaseWifiLock(wifiLock2);
                    }
                    Log.i(this.TAG, "Manually releasing wake lock");
                    if (wakeLock != null) {
                        NetworkManager.instance(this.mContext).releaseWakeLock(wakeLock);
                    }
                    return uploadResult6;
                }
            } catch (AuthenticatedURLConnection.NoCredentialsException e9) {
                e = e9;
                wifiLock2 = null;
            } catch (SocketTimeoutException e10) {
                e = e10;
                wifiLock2 = null;
            } catch (UnknownHostException e11) {
                e = e11;
                wifiLock2 = null;
            } catch (IOException e12) {
                e = e12;
                wifiLock2 = null;
            } catch (Throwable th3) {
                th = th3;
                wifiLock = null;
                if (0 != 0) {
                    httpURLConnection.disconnect();
                }
                Log.i(this.TAG, "Manually releasing wifi lock");
                if (wifiLock != null) {
                    NetworkManager.instance(this.mContext).releaseWifiLock(wifiLock);
                }
                Log.i(this.TAG, "Manually releasing wake lock");
                if (wakeLock != null) {
                    NetworkManager.instance(this.mContext).releaseWakeLock(wakeLock);
                }
                throw th;
            }
        } else {
            Log.e(this.TAG, "Transmitted metricBatch cannot be null or empty");
            return new UploadResult(8);
        }
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public void close() {
    }

    protected boolean isConnectionRefusedError(IOException iOException) {
        if (!(iOException instanceof ConnectException)) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        Throwable cause = iOException.getCause();
        if ((cause instanceof ErrnoException) && OsConstants.ECONNREFUSED == ((ErrnoException) cause).errno) {
            return true;
        }
        String message = iOException.getMessage();
        return message != null && message.contains("ECONNREFUSED");
    }

    protected boolean isEthernetAvailable() throws IllegalStateException {
        return this.mMetricsConfiguration.getNetworkConfiguration().getNetworkTypes().contains(NetworkType.ETHERNET) && NetworkManager.instance(this.mContext).isEthernetConnected();
    }

    protected boolean isWANAvailable() throws IllegalStateException {
        boolean contains = this.mMetricsConfiguration.getNetworkConfiguration().getNetworkTypes().contains(NetworkType.WAN);
        String fetchWANSupportedDeviceMode = this.mDeviceUtil.fetchWANSupportedDeviceMode();
        if (fetchWANSupportedDeviceMode != null) {
            contains = contains || fetchWANSupportedDeviceMode.equals(this.mDeviceUtil.fetchDeviceMode());
        }
        return contains && NetworkManager.instance(this.mContext).isWanConnected();
    }

    protected boolean isWifiAvailable() throws IllegalStateException {
        return this.mMetricsConfiguration.getNetworkConfiguration().getNetworkTypes().contains(NetworkType.WIFI) && NetworkManager.instance(this.mContext).isWifiConnected();
    }

    @Override // com.amazon.client.metrics.thirdparty.transport.TransportStateNotifier
    public void listenForTransportWarmed(TransportStateNotifier.TransportWarmedListener transportWarmedListener) {
    }

    protected abstract HttpURLConnection openConnection(URL url) throws IOException;

    protected URL resolveEndpoint() {
        try {
            return new URL(this.mMetricsConfiguration.getHttpConfiguration().getUrlEndpoint() + BATCH_UPLOAD_PATH);
        } catch (MalformedURLException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Malformed URL provided: ");
            outline107.append(this.mMetricsConfiguration.getHttpConfiguration().getUrlEndpoint());
            throw new IllegalArgumentException(outline107.toString(), e);
        }
    }

    protected abstract boolean signRequest(HttpURLConnection httpURLConnection);

    @Override // com.amazon.client.metrics.thirdparty.transport.MetricsTransport
    public UploadResult transmit(byte[] bArr) {
        UploadResult attemptTransmit = attemptTransmit(bArr);
        if (this.mFallbackTransport == null || attemptTransmit.getUploadStatus() != 5) {
            return attemptTransmit;
        }
        Log.i(this.TAG, "Attempting transmission using fallback transport");
        return this.mFallbackTransport.transmit(bArr);
    }
}
