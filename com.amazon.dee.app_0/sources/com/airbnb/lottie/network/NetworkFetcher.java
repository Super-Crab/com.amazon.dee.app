package com.airbnb.lottie.network;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.core.util.Pair;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieResult;
import com.airbnb.lottie.utils.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.ZipInputStream;
/* loaded from: classes.dex */
public class NetworkFetcher {
    private final Context appContext;
    private final NetworkCache networkCache;
    private final String url;

    private NetworkFetcher(Context context, String str) {
        this.appContext = context.getApplicationContext();
        this.url = str;
        this.networkCache = new NetworkCache(this.appContext, str);
    }

    @Nullable
    @WorkerThread
    private LottieComposition fetchFromCache() {
        LottieResult<LottieComposition> fromJsonInputStreamSync;
        Pair<FileExtension, InputStream> fetch = this.networkCache.fetch();
        if (fetch == null) {
            return null;
        }
        FileExtension fileExtension = fetch.first;
        InputStream inputStream = fetch.second;
        if (fileExtension == FileExtension.ZIP) {
            fromJsonInputStreamSync = LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(inputStream), this.url);
        } else {
            fromJsonInputStreamSync = LottieCompositionFactory.fromJsonInputStreamSync(inputStream, this.url);
        }
        if (fromJsonInputStreamSync.getValue() == null) {
            return null;
        }
        return fromJsonInputStreamSync.getValue();
    }

    @WorkerThread
    private LottieResult<LottieComposition> fetchFromNetwork() {
        try {
            return fetchFromNetworkInternal();
        } catch (IOException e) {
            return new LottieResult<>(e);
        }
    }

    @WorkerThread
    private LottieResult fetchFromNetworkInternal() throws IOException {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Fetching ");
        outline107.append(this.url);
        Logger.debug(outline107.toString());
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.url).openConnection();
        httpURLConnection.setRequestMethod("GET");
        try {
            httpURLConnection.connect();
            if (httpURLConnection.getErrorStream() == null && httpURLConnection.getResponseCode() == 200) {
                LottieResult<LottieComposition> resultFromConnection = getResultFromConnection(httpURLConnection);
                StringBuilder sb = new StringBuilder();
                sb.append("Completed fetch from network. Success: ");
                sb.append(resultFromConnection.getValue() != null);
                Logger.debug(sb.toString());
                return resultFromConnection;
            }
            String errorFromConnection = getErrorFromConnection(httpURLConnection);
            return new LottieResult((Throwable) new IllegalArgumentException("Unable to fetch " + this.url + ". Failed with " + httpURLConnection.getResponseCode() + "\n" + errorFromConnection));
        } catch (Exception e) {
            return new LottieResult((Throwable) e);
        } finally {
            httpURLConnection.disconnect();
        }
    }

    public static LottieResult<LottieComposition> fetchSync(Context context, String str) {
        return new NetworkFetcher(context, str).fetchSync();
    }

    private String getErrorFromConnection(HttpURLConnection httpURLConnection) throws IOException {
        httpURLConnection.getResponseCode();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getErrorStream()));
        StringBuilder sb = new StringBuilder();
        while (true) {
            try {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                        sb.append('\n');
                    } else {
                        try {
                            break;
                        } catch (Exception unused) {
                        }
                    }
                } catch (Exception e) {
                    throw e;
                }
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Exception unused2) {
                }
                throw th;
            }
        }
        bufferedReader.close();
        return sb.toString();
    }

    @Nullable
    private LottieResult<LottieComposition> getResultFromConnection(HttpURLConnection httpURLConnection) throws IOException {
        FileExtension fileExtension;
        LottieResult<LottieComposition> fromZipStreamSync;
        String contentType = httpURLConnection.getContentType();
        if (contentType == null) {
            contentType = "application/json";
        }
        char c = 65535;
        int hashCode = contentType.hashCode();
        if (hashCode != -1248325150) {
            if (hashCode == -43840953 && contentType.equals("application/json")) {
                c = 1;
            }
        } else if (contentType.equals("application/zip")) {
            c = 0;
        }
        if (c != 0) {
            Logger.debug("Received json response.");
            fileExtension = FileExtension.JSON;
            fromZipStreamSync = LottieCompositionFactory.fromJsonInputStreamSync(new FileInputStream(new File(this.networkCache.writeTempCacheFile(httpURLConnection.getInputStream(), fileExtension).getAbsolutePath())), this.url);
        } else {
            Logger.debug("Handling zip response.");
            fileExtension = FileExtension.ZIP;
            fromZipStreamSync = LottieCompositionFactory.fromZipStreamSync(new ZipInputStream(new FileInputStream(this.networkCache.writeTempCacheFile(httpURLConnection.getInputStream(), fileExtension))), this.url);
        }
        if (fromZipStreamSync.getValue() != null) {
            this.networkCache.renameTempFile(fileExtension);
        }
        return fromZipStreamSync;
    }

    @WorkerThread
    public LottieResult<LottieComposition> fetchSync() {
        LottieComposition fetchFromCache = fetchFromCache();
        if (fetchFromCache != null) {
            return new LottieResult<>(fetchFromCache);
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Animation for ");
        outline107.append(this.url);
        outline107.append(" not found in cache. Fetching from network.");
        Logger.debug(outline107.toString());
        return fetchFromNetwork();
    }
}
