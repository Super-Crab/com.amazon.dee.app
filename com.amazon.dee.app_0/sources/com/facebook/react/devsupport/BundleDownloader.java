package com.facebook.react.devsupport;

import androidx.annotation.Nullable;
import com.amazon.alexa.fitness.utils.MetricName;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.cache.disk.DefaultDiskStorage;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.common.DebugServerException;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.MultipartStreamReader;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import org.apache.commons.fileupload.FileUploadBase;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class BundleDownloader {
    private static final int FILES_CHANGED_COUNT_NOT_BUILT_BY_BUNDLER = -2;
    private static final String TAG = "BundleDownloader";
    private final OkHttpClient mClient;
    @Nullable
    private Call mDownloadBundleFromURLCall;

    /* loaded from: classes2.dex */
    public static class BundleInfo {
        private int mFilesChangedCount;
        @Nullable
        private String mUrl;

        @Nullable
        public static BundleInfo fromJSONString(String str) {
            if (str == null) {
                return null;
            }
            BundleInfo bundleInfo = new BundleInfo();
            try {
                JSONObject jSONObject = new JSONObject(str);
                bundleInfo.mUrl = jSONObject.getString("url");
                bundleInfo.mFilesChangedCount = jSONObject.getInt("filesChangedCount");
                return bundleInfo;
            } catch (JSONException e) {
                FLog.e(BundleDownloader.TAG, "Invalid bundle info: ", e);
                return null;
            }
        }

        public int getFilesChangedCount() {
            return this.mFilesChangedCount;
        }

        public String getUrl() {
            String str = this.mUrl;
            return str != null ? str : "unknown";
        }

        @Nullable
        public String toJSONString() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("url", this.mUrl);
                jSONObject.put("filesChangedCount", this.mFilesChangedCount);
                return jSONObject.toString();
            } catch (JSONException e) {
                FLog.e(BundleDownloader.TAG, "Can't serialize bundle info: ", e);
                return null;
            }
        }
    }

    public BundleDownloader(OkHttpClient okHttpClient) {
        this.mClient = okHttpClient;
    }

    private static void populateBundleInfo(String str, Headers headers, BundleInfo bundleInfo) {
        bundleInfo.mUrl = str;
        String str2 = headers.get("X-Metro-Files-Changed-Count");
        if (str2 != null) {
            try {
                bundleInfo.mFilesChangedCount = Integer.parseInt(str2);
            } catch (NumberFormatException unused) {
                bundleInfo.mFilesChangedCount = -2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processBundleResult(String str, int i, Headers headers, BufferedSource bufferedSource, File file, BundleInfo bundleInfo, DevBundleDownloadListener devBundleDownloadListener) throws IOException {
        if (i != 200) {
            String readUtf8 = bufferedSource.readUtf8();
            DebugServerException parse = DebugServerException.parse(str, readUtf8);
            if (parse != null) {
                devBundleDownloadListener.onFailure(parse);
                return;
            }
            devBundleDownloadListener.onFailure(new DebugServerException("The development server returned response error code: " + i + "\n\nURL: " + str + "\n\nBody:\n" + readUtf8));
            return;
        }
        if (bundleInfo != null) {
            populateBundleInfo(str, headers, bundleInfo);
        }
        File file2 = new File(file.getPath() + DefaultDiskStorage.FileType.TEMP);
        if (storePlainJSInFile(bufferedSource, file2) && !file2.renameTo(file)) {
            throw new IOException("Couldn't rename " + file2 + " to " + file);
        }
        devBundleDownloadListener.onSuccess();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processMultipartResponse(final String str, final Response response, String str2, final File file, @Nullable final BundleInfo bundleInfo, final DevBundleDownloadListener devBundleDownloadListener) throws IOException {
        if (!new MultipartStreamReader(response.body().source(), str2).readAllParts(new MultipartStreamReader.ChunkListener() { // from class: com.facebook.react.devsupport.BundleDownloader.2
            @Override // com.facebook.react.devsupport.MultipartStreamReader.ChunkListener
            public void onChunkComplete(Map<String, String> map, Buffer buffer, boolean z) throws IOException {
                if (z) {
                    int code = response.code();
                    if (map.containsKey("X-Http-Status")) {
                        code = Integer.parseInt(map.get("X-Http-Status"));
                    }
                    BundleDownloader.this.processBundleResult(str, code, Headers.of(map), buffer, file, bundleInfo, devBundleDownloadListener);
                } else if (!map.containsKey("Content-Type") || !map.get("Content-Type").equals("application/json")) {
                } else {
                    try {
                        JSONObject jSONObject = new JSONObject(buffer.readUtf8());
                        String string = jSONObject.has("status") ? jSONObject.getString("status") : "Bundling";
                        Integer num = null;
                        Integer valueOf = jSONObject.has(MetricName.DONE) ? Integer.valueOf(jSONObject.getInt(MetricName.DONE)) : null;
                        if (jSONObject.has("total")) {
                            num = Integer.valueOf(jSONObject.getInt("total"));
                        }
                        devBundleDownloadListener.onProgress(string, valueOf, num);
                    } catch (JSONException e) {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error parsing progress JSON. ");
                        outline107.append(e.toString());
                        FLog.e(ReactConstants.TAG, outline107.toString());
                    }
                }
            }

            @Override // com.facebook.react.devsupport.MultipartStreamReader.ChunkListener
            public void onChunkProgress(Map<String, String> map, long j, long j2) {
                if ("application/javascript".equals(map.get("Content-Type"))) {
                    devBundleDownloadListener.onProgress("Downloading", Integer.valueOf((int) (j / 1024)), Integer.valueOf((int) (j2 / 1024)));
                }
            }
        })) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Error while reading multipart response.\n\nResponse code: ");
            outline107.append(response.code());
            outline107.append("\n\nURL: ");
            outline107.append(str.toString());
            outline107.append("\n\n");
            devBundleDownloadListener.onFailure(new DebugServerException(outline107.toString()));
        }
    }

    private static boolean storePlainJSInFile(BufferedSource bufferedSource, File file) throws IOException {
        Sink sink;
        try {
            sink = Okio.sink(file);
        } catch (Throwable th) {
            th = th;
            sink = null;
        }
        try {
            bufferedSource.readAll(sink);
            if (sink == null) {
                return true;
            }
            sink.close();
            return true;
        } catch (Throwable th2) {
            th = th2;
            if (sink != null) {
                sink.close();
            }
            throw th;
        }
    }

    public void downloadBundleFromURL(DevBundleDownloadListener devBundleDownloadListener, File file, String str, @Nullable BundleInfo bundleInfo) {
        downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo, new Request.Builder());
    }

    public void downloadBundleFromURL(final DevBundleDownloadListener devBundleDownloadListener, final File file, String str, @Nullable final BundleInfo bundleInfo, Request.Builder builder) {
        this.mDownloadBundleFromURLCall = (Call) Assertions.assertNotNull(this.mClient.newCall(builder.url(str).addHeader("Accept", FileUploadBase.MULTIPART_MIXED).build()));
        this.mDownloadBundleFromURLCall.enqueue(new Callback() { // from class: com.facebook.react.devsupport.BundleDownloader.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                if (BundleDownloader.this.mDownloadBundleFromURLCall == null || BundleDownloader.this.mDownloadBundleFromURLCall.isCanceled()) {
                    BundleDownloader.this.mDownloadBundleFromURLCall = null;
                    return;
                }
                BundleDownloader.this.mDownloadBundleFromURLCall = null;
                String httpUrl = call.request().url().toString();
                DevBundleDownloadListener devBundleDownloadListener2 = devBundleDownloadListener;
                devBundleDownloadListener2.onFailure(DebugServerException.makeGeneric(httpUrl, "Could not connect to development server.", "URL: " + httpUrl, iOException));
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (BundleDownloader.this.mDownloadBundleFromURLCall == null || BundleDownloader.this.mDownloadBundleFromURLCall.isCanceled()) {
                    BundleDownloader.this.mDownloadBundleFromURLCall = null;
                    return;
                }
                BundleDownloader.this.mDownloadBundleFromURLCall = null;
                String httpUrl = response.request().url().toString();
                Matcher matcher = Pattern.compile("multipart/mixed;.*boundary=\"([^\"]+)\"").matcher(response.header("content-type"));
                try {
                    if (matcher.find()) {
                        BundleDownloader.this.processMultipartResponse(httpUrl, response, matcher.group(1), file, bundleInfo, devBundleDownloadListener);
                    } else {
                        BundleDownloader.this.processBundleResult(httpUrl, response.code(), response.headers(), Okio.buffer(response.body().source()), file, bundleInfo, devBundleDownloadListener);
                    }
                    response.close();
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        try {
                            response.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                        throw th2;
                    }
                }
            }
        });
    }
}
