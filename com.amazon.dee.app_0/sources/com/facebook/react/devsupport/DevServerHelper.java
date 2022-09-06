package com.facebook.react.devsupport;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.Nullable;
import com.amazon.alexa.auth.BuildConfig;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.common.logging.FLog;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.devsupport.BundleDownloader;
import com.facebook.react.devsupport.InspectorPackagerConnection;
import com.facebook.react.devsupport.interfaces.DevBundleDownloadListener;
import com.facebook.react.devsupport.interfaces.PackagerStatusCallback;
import com.facebook.react.devsupport.interfaces.StackFrame;
import com.facebook.react.modules.systeminfo.AndroidInfoHelpers;
import com.facebook.react.packagerconnection.FileIoHandler;
import com.facebook.react.packagerconnection.JSPackagerClient;
import com.facebook.react.packagerconnection.NotificationOnlyHandler;
import com.facebook.react.packagerconnection.ReconnectingWebSocket;
import com.facebook.react.packagerconnection.RequestHandler;
import com.facebook.react.packagerconnection.RequestOnlyHandler;
import com.facebook.react.packagerconnection.Responder;
import com.facebook.react.util.RNLog;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Okio;
import okio.Sink;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class DevServerHelper {
    private static final String DEBUGGER_MSG_DISABLE = "{ \"id\":1,\"method\":\"Debugger.disable\" }";
    private static final int HTTP_CONNECT_TIMEOUT_MS = 5000;
    private static final String PACKAGER_OK_STATUS = "packager-status:running";
    public static final String RELOAD_APP_EXTRA_JS_PROXY = "jsproxy";
    private InspectorPackagerConnection.BundleStatusProvider mBundlerStatusProvider;
    @Nullable
    private InspectorPackagerConnection mInspectorPackagerConnection;
    private final String mPackageName;
    @Nullable
    private JSPackagerClient mPackagerClient;
    private final DevInternalSettings mSettings;
    private final OkHttpClient mClient = new OkHttpClient.Builder().connectTimeout(5000, TimeUnit.MILLISECONDS).readTimeout(0, TimeUnit.MILLISECONDS).writeTimeout(0, TimeUnit.MILLISECONDS).build();
    private final BundleDownloader mBundleDownloader = new BundleDownloader(this.mClient);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public enum BundleType {
        BUNDLE("bundle"),
        MAP(BuildConfig.FLAVOR_authtype);
        
        private final String mTypeID;

        BundleType(String str) {
            this.mTypeID = str;
        }

        public String typeID() {
            return this.mTypeID;
        }
    }

    /* loaded from: classes2.dex */
    public interface OnServerContentChangeListener {
        void onServerContentChanged();
    }

    /* loaded from: classes2.dex */
    public interface PackagerCommandListener {
        @Nullable
        Map<String, RequestHandler> customCommandHandlers();

        void onCaptureHeapCommand(Responder responder);

        void onPackagerConnected();

        void onPackagerDevMenuCommand();

        void onPackagerDisconnected();

        void onPackagerReloadCommand();
    }

    /* loaded from: classes2.dex */
    public interface PackagerCustomCommandProvider {
    }

    /* loaded from: classes2.dex */
    public interface SymbolicationListener {
        void onSymbolicationComplete(@Nullable Iterable<StackFrame> iterable);
    }

    public DevServerHelper(DevInternalSettings devInternalSettings, String str, InspectorPackagerConnection.BundleStatusProvider bundleStatusProvider) {
        this.mSettings = devInternalSettings;
        this.mBundlerStatusProvider = bundleStatusProvider;
        this.mPackageName = str;
    }

    private String createBundleURL(String str, BundleType bundleType, String str2) {
        return createBundleURL(str, bundleType, str2, false, true);
    }

    private String createLaunchJSDevtoolsCommandUrl() {
        return String.format(Locale.US, "http://%s/launch-js-devtools", this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
    }

    private static String createOpenStackFrameURL(String str) {
        return String.format(Locale.US, "http://%s/open-stack-frame", str);
    }

    private static String createPackagerStatusURL(String str) {
        return String.format(Locale.US, "http://%s/status", str);
    }

    private static String createResourceURL(String str, String str2) {
        return String.format(Locale.US, "http://%s/%s", str, str2);
    }

    private String createSplitBundleURL(String str, String str2) {
        return createBundleURL(str, BundleType.BUNDLE, str2, true, false);
    }

    private static String createSymbolicateURL(String str) {
        return String.format(Locale.US, "http://%s/symbolicate", str);
    }

    private boolean getDevMode() {
        return this.mSettings.isJSDevModeEnabled();
    }

    private String getHostForJSProxy() {
        String str = (String) Assertions.assertNotNull(this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
        int lastIndexOf = str.lastIndexOf(58);
        return lastIndexOf > -1 ? GeneratedOutlineSupport1.outline55(str, lastIndexOf, GeneratedOutlineSupport1.outline107(AndroidInfoHelpers.DEVICE_LOCALHOST)) : AndroidInfoHelpers.DEVICE_LOCALHOST;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getInspectorDeviceUrl() {
        return String.format(Locale.US, "http://%s/inspector/device?name=%s&app=%s", this.mSettings.getPackagerConnectionSettings().getInspectorServerHost(), AndroidInfoHelpers.getFriendlyDeviceName(), this.mPackageName);
    }

    private boolean getJSMinifyMode() {
        return this.mSettings.isJSMinifyEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getOpenUrlEndpoint(Context context) {
        return String.format(Locale.US, "http://%s/open-url", AndroidInfoHelpers.getServerHost(context));
    }

    public void closeInspectorConnection() {
        new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                if (DevServerHelper.this.mInspectorPackagerConnection != null) {
                    DevServerHelper.this.mInspectorPackagerConnection.closeQuietly();
                    DevServerHelper.this.mInspectorPackagerConnection = null;
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void closePackagerConnection() {
        new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                if (DevServerHelper.this.mPackagerClient != null) {
                    DevServerHelper.this.mPackagerClient.close();
                    DevServerHelper.this.mPackagerClient = null;
                }
                return null;
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void disableDebugger() {
        InspectorPackagerConnection inspectorPackagerConnection = this.mInspectorPackagerConnection;
        if (inspectorPackagerConnection != null) {
            inspectorPackagerConnection.sendEventToAllConnections(DEBUGGER_MSG_DISABLE);
        }
    }

    public void downloadBundleFromURL(DevBundleDownloadListener devBundleDownloadListener, File file, String str, BundleDownloader.BundleInfo bundleInfo) {
        this.mBundleDownloader.downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo);
    }

    @Nullable
    public File downloadBundleResourceFromUrlSync(String str, File file) {
        Sink sink;
        try {
            Response execute = this.mClient.newCall(new Request.Builder().url(createResourceURL(this.mSettings.getPackagerConnectionSettings().getDebugServerHost(), str)).build()).execute();
            if (!execute.isSuccessful()) {
                execute.close();
                return null;
            }
            try {
                sink = Okio.sink(file);
            } catch (Throwable th) {
                th = th;
                sink = null;
            }
            try {
                Okio.buffer(execute.body().source()).readAll(sink);
                if (sink != null) {
                    sink.close();
                }
                execute.close();
                return file;
            } catch (Throwable th2) {
                th = th2;
                if (sink != null) {
                    sink.close();
                }
                throw th;
            }
        } catch (Exception e) {
            FLog.e(ReactConstants.TAG, "Failed to fetch resource synchronously - resourcePath: \"%s\", outputFile: \"%s\"", str, file.getAbsolutePath(), e);
            return null;
        }
    }

    public String getDevServerBundleURL(String str) {
        return createBundleURL(str, BundleType.BUNDLE, this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
    }

    public String getDevServerSplitBundleURL(String str) {
        return createSplitBundleURL(str, this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
    }

    public String getJSBundleURLForRemoteDebugging(String str) {
        return createBundleURL(str, BundleType.BUNDLE, getHostForJSProxy());
    }

    public String getSourceMapUrl(String str) {
        return createBundleURL(str, BundleType.MAP);
    }

    public String getSourceUrl(String str) {
        return createBundleURL(str, BundleType.BUNDLE);
    }

    public String getWebsocketProxyURL() {
        return String.format(Locale.US, "ws://%s/debugger-proxy?role=client", this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
    }

    public void isPackagerRunning(final PackagerStatusCallback packagerStatusCallback) {
        this.mClient.newCall(new Request.Builder().url(createPackagerStatusURL(this.mSettings.getPackagerConnectionSettings().getDebugServerHost())).build()).enqueue(new Callback() { // from class: com.facebook.react.devsupport.DevServerHelper.8
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("The packager does not seem to be running as we got an IOException requesting its status: ");
                outline107.append(iOException.getMessage());
                FLog.w(ReactConstants.TAG, outline107.toString());
                packagerStatusCallback.onPackagerStatusFetched(false);
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Got non-success http code from packager when requesting status: ");
                    outline107.append(response.code());
                    FLog.e(ReactConstants.TAG, outline107.toString());
                    packagerStatusCallback.onPackagerStatusFetched(false);
                    return;
                }
                ResponseBody body = response.body();
                if (body == null) {
                    FLog.e(ReactConstants.TAG, "Got null body response from packager when requesting status");
                    packagerStatusCallback.onPackagerStatusFetched(false);
                    return;
                }
                String string = body.string();
                if (!DevServerHelper.PACKAGER_OK_STATUS.equals(string)) {
                    FLog.e(ReactConstants.TAG, "Got unexpected response from packager when requesting status: " + string);
                    packagerStatusCallback.onPackagerStatusFetched(false);
                    return;
                }
                packagerStatusCallback.onPackagerStatusFetched(true);
            }
        });
    }

    public void launchJSDevtools() {
        this.mClient.newCall(new Request.Builder().url(createLaunchJSDevtoolsCommandUrl()).build()).enqueue(new Callback() { // from class: com.facebook.react.devsupport.DevServerHelper.9
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }

    public void openInspectorConnection() {
        if (this.mInspectorPackagerConnection != null) {
            FLog.w(ReactConstants.TAG, "Inspector connection already open, nooping.");
        } else {
            new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper.3
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... voidArr) {
                    DevServerHelper devServerHelper = DevServerHelper.this;
                    devServerHelper.mInspectorPackagerConnection = new InspectorPackagerConnection(devServerHelper.getInspectorDeviceUrl(), DevServerHelper.this.mPackageName, DevServerHelper.this.mBundlerStatusProvider);
                    DevServerHelper.this.mInspectorPackagerConnection.connect();
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void openPackagerConnection(final String str, final PackagerCommandListener packagerCommandListener) {
        if (this.mPackagerClient != null) {
            FLog.w(ReactConstants.TAG, "Packager connection already open, nooping.");
        } else {
            new AsyncTask<Void, Void, Void>() { // from class: com.facebook.react.devsupport.DevServerHelper.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(Void... voidArr) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("reload", new NotificationOnlyHandler() { // from class: com.facebook.react.devsupport.DevServerHelper.1.1
                        @Override // com.facebook.react.packagerconnection.NotificationOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
                        public void onNotification(@Nullable Object obj) {
                            packagerCommandListener.onPackagerReloadCommand();
                        }
                    });
                    hashMap.put("devMenu", new NotificationOnlyHandler() { // from class: com.facebook.react.devsupport.DevServerHelper.1.2
                        @Override // com.facebook.react.packagerconnection.NotificationOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
                        public void onNotification(@Nullable Object obj) {
                            packagerCommandListener.onPackagerDevMenuCommand();
                        }
                    });
                    hashMap.put("captureHeap", new RequestOnlyHandler() { // from class: com.facebook.react.devsupport.DevServerHelper.1.3
                        @Override // com.facebook.react.packagerconnection.RequestOnlyHandler, com.facebook.react.packagerconnection.RequestHandler
                        public void onRequest(@Nullable Object obj, Responder responder) {
                            packagerCommandListener.onCaptureHeapCommand(responder);
                        }
                    });
                    Map<String, RequestHandler> customCommandHandlers = packagerCommandListener.customCommandHandlers();
                    if (customCommandHandlers != null) {
                        hashMap.putAll(customCommandHandlers);
                    }
                    hashMap.putAll(new FileIoHandler().handlers());
                    ReconnectingWebSocket.ConnectionCallback connectionCallback = new ReconnectingWebSocket.ConnectionCallback() { // from class: com.facebook.react.devsupport.DevServerHelper.1.4
                        @Override // com.facebook.react.packagerconnection.ReconnectingWebSocket.ConnectionCallback
                        public void onConnected() {
                            packagerCommandListener.onPackagerConnected();
                        }

                        @Override // com.facebook.react.packagerconnection.ReconnectingWebSocket.ConnectionCallback
                        public void onDisconnected() {
                            packagerCommandListener.onPackagerDisconnected();
                        }
                    };
                    DevServerHelper devServerHelper = DevServerHelper.this;
                    devServerHelper.mPackagerClient = new JSPackagerClient(str, devServerHelper.mSettings.getPackagerConnectionSettings(), hashMap, connectionCallback);
                    DevServerHelper.this.mPackagerClient.init();
                    return null;
                }
            }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void openStackFrameCall(StackFrame stackFrame) {
        ((Call) Assertions.assertNotNull(this.mClient.newCall(new Request.Builder().url(createOpenStackFrameURL(this.mSettings.getPackagerConnectionSettings().getDebugServerHost())).post(RequestBody.create(MediaType.parse("application/json"), stackFrame.toJSON().toString())).build()))).enqueue(new Callback() { // from class: com.facebook.react.devsupport.DevServerHelper.7
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException iOException) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Got IOException when attempting to open stack frame: ");
                outline107.append(iOException.getMessage());
                FLog.w(ReactConstants.TAG, outline107.toString());
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
            }
        });
    }

    public void openUrl(final ReactContext reactContext, final String str, final String str2) {
        new AsyncTask<Void, String, Boolean>() { // from class: com.facebook.react.devsupport.DevServerHelper.5
            public boolean doSync() {
                try {
                    String openUrlEndpoint = DevServerHelper.this.getOpenUrlEndpoint(reactContext);
                    new OkHttpClient().newCall(new Request.Builder().url(openUrlEndpoint).post(RequestBody.create(MediaType.parse("application/json"), new JSONObject().put("url", str).toString())).build()).execute();
                    return true;
                } catch (IOException | JSONException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Failed to open URL");
                    outline107.append(str);
                    FLog.e(ReactConstants.TAG, outline107.toString(), e);
                    return false;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                return Boolean.valueOf(doSync());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                if (!bool.booleanValue()) {
                    RNLog.w(reactContext, str2);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public void symbolicateStackTrace(Iterable<StackFrame> iterable, final SymbolicationListener symbolicationListener) {
        try {
            String createSymbolicateURL = createSymbolicateURL(this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
            JSONArray jSONArray = new JSONArray();
            for (StackFrame stackFrame : iterable) {
                jSONArray.put(stackFrame.toJSON());
            }
            ((Call) Assertions.assertNotNull(this.mClient.newCall(new Request.Builder().url(createSymbolicateURL).post(RequestBody.create(MediaType.parse("application/json"), new JSONObject().put(HttpClientModule.EventEmitterObserver.ElementsErrorKey.STACK, jSONArray).toString())).build()))).enqueue(new Callback() { // from class: com.facebook.react.devsupport.DevServerHelper.6
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Got IOException when attempting symbolicate stack trace: ");
                    outline107.append(iOException.getMessage());
                    FLog.w(ReactConstants.TAG, outline107.toString());
                    symbolicationListener.onSymbolicationComplete(null);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        symbolicationListener.onSymbolicationComplete(Arrays.asList(StackTraceHelper.convertJsStackTrace(new JSONObject(response.body().string()).getJSONArray(HttpClientModule.EventEmitterObserver.ElementsErrorKey.STACK))));
                    } catch (JSONException unused) {
                        symbolicationListener.onSymbolicationComplete(null);
                    }
                }
            });
        } catch (JSONException e) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Got JSONException when attempting symbolicate stack trace: ");
            outline107.append(e.getMessage());
            FLog.w(ReactConstants.TAG, outline107.toString());
        }
    }

    private String createBundleURL(String str, BundleType bundleType, String str2, boolean z, boolean z2) {
        Locale locale = Locale.US;
        Object[] objArr = new Object[8];
        objArr[0] = str2;
        objArr[1] = str;
        objArr[2] = bundleType.typeID();
        objArr[3] = Boolean.valueOf(getDevMode());
        objArr[4] = Boolean.valueOf(getJSMinifyMode());
        objArr[5] = this.mPackageName;
        String str3 = "true";
        objArr[6] = z ? str3 : PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE;
        if (!z2) {
            str3 = PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE;
        }
        objArr[7] = str3;
        return String.format(locale, "http://%s/%s.%s?platform=android&dev=%s&minify=%s&app=%s&modulesOnly=%s&runModule=%s", objArr);
    }

    public void downloadBundleFromURL(DevBundleDownloadListener devBundleDownloadListener, File file, String str, BundleDownloader.BundleInfo bundleInfo, Request.Builder builder) {
        this.mBundleDownloader.downloadBundleFromURL(devBundleDownloadListener, file, str, bundleInfo, builder);
    }

    private String createBundleURL(String str, BundleType bundleType) {
        return createBundleURL(str, bundleType, this.mSettings.getPackagerConnectionSettings().getDebugServerHost());
    }
}
