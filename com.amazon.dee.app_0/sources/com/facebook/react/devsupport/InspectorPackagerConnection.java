package com.facebook.react.devsupport;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.mode.debug.EmulateConnection;
import com.amazon.device.messaging.ADMConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.Inspector;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class InspectorPackagerConnection {
    private static final String TAG = "InspectorPackagerConnection";
    private BundleStatusProvider mBundleStatusProvider;
    private final Connection mConnection;
    private final Map<String, Inspector.LocalConnection> mInspectorConnections = new HashMap();
    private final String mPackageName;

    /* loaded from: classes2.dex */
    public interface BundleStatusProvider {
        BundleStatus getBundleStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class Connection extends WebSocketListener {
        private static final int RECONNECT_DELAY_MS = 2000;
        private boolean mClosed;
        private final Handler mHandler = new Handler(Looper.getMainLooper());
        private OkHttpClient mHttpClient;
        private boolean mSuppressConnectionErrors;
        private final String mUrl;
        @Nullable
        private WebSocket mWebSocket;

        public Connection(String str) {
            this.mUrl = str;
        }

        private void abort(String str, Throwable th) {
            FLog.e(InspectorPackagerConnection.TAG, "Error occurred, shutting down websocket connection: " + str, th);
            InspectorPackagerConnection.this.closeAllConnections();
            closeWebSocketQuietly();
        }

        private void closeWebSocketQuietly() {
            WebSocket webSocket = this.mWebSocket;
            if (webSocket != null) {
                try {
                    webSocket.close(1000, "End of session");
                } catch (Exception unused) {
                }
                this.mWebSocket = null;
            }
        }

        private void reconnect() {
            if (!this.mClosed) {
                if (!this.mSuppressConnectionErrors) {
                    FLog.w(InspectorPackagerConnection.TAG, "Couldn't connect to packager, will silently retry");
                    this.mSuppressConnectionErrors = true;
                }
                this.mHandler.postDelayed(new Runnable() { // from class: com.facebook.react.devsupport.InspectorPackagerConnection.Connection.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (!Connection.this.mClosed) {
                            Connection.this.connect();
                        }
                    }
                }, 2000L);
                return;
            }
            throw new IllegalStateException("Can't reconnect closed client");
        }

        public void close() {
            this.mClosed = true;
            WebSocket webSocket = this.mWebSocket;
            if (webSocket != null) {
                try {
                    webSocket.close(1000, "End of session");
                } catch (Exception unused) {
                }
                this.mWebSocket = null;
            }
        }

        public void connect() {
            if (!this.mClosed) {
                if (this.mHttpClient == null) {
                    this.mHttpClient = new OkHttpClient.Builder().connectTimeout(10L, TimeUnit.SECONDS).writeTimeout(10L, TimeUnit.SECONDS).readTimeout(0L, TimeUnit.MINUTES).build();
                }
                this.mHttpClient.newWebSocket(new Request.Builder().url(this.mUrl).build(), this);
                return;
            }
            throw new IllegalStateException("Can't connect closed client");
        }

        @Override // okhttp3.WebSocketListener
        public void onClosed(WebSocket webSocket, int i, String str) {
            this.mWebSocket = null;
            InspectorPackagerConnection.this.closeAllConnections();
            if (!this.mClosed) {
                reconnect();
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onFailure(WebSocket webSocket, Throwable th, Response response) {
            if (this.mWebSocket != null) {
                abort("Websocket exception", th);
            }
            if (!this.mClosed) {
                reconnect();
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onMessage(WebSocket webSocket, String str) {
            try {
                InspectorPackagerConnection.this.handleProxyMessage(new JSONObject(str));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        @Override // okhttp3.WebSocketListener
        public void onOpen(WebSocket webSocket, Response response) {
            this.mWebSocket = webSocket;
        }

        public void send(final JSONObject jSONObject) {
            new AsyncTask<WebSocket, Void, Void>() { // from class: com.facebook.react.devsupport.InspectorPackagerConnection.Connection.2
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public Void doInBackground(WebSocket... webSocketArr) {
                    if (webSocketArr != null && webSocketArr.length != 0) {
                        try {
                            webSocketArr[0].send(jSONObject.toString());
                        } catch (Exception e) {
                            FLog.w(InspectorPackagerConnection.TAG, "Couldn't send event to packager", e);
                        }
                    }
                    return null;
                }
            }.execute(this.mWebSocket);
        }
    }

    public InspectorPackagerConnection(String str, String str2, BundleStatusProvider bundleStatusProvider) {
        this.mConnection = new Connection(str);
        this.mPackageName = str2;
        this.mBundleStatusProvider = bundleStatusProvider;
    }

    private JSONArray getPages() throws JSONException {
        List<Inspector.Page> pages = Inspector.getPages();
        JSONArray jSONArray = new JSONArray();
        BundleStatus bundleStatus = this.mBundleStatusProvider.getBundleStatus();
        for (Inspector.Page page : pages) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", String.valueOf(page.getId()));
            jSONObject.put("title", page.getTitle());
            jSONObject.put(ADMConstants.LowLevel.EXTRA_APPLICATION_PENDING_INTENT, this.mPackageName);
            jSONObject.put("vm", page.getVM());
            jSONObject.put("isLastBundleDownloadSuccess", bundleStatus.isLastDownloadSucess);
            jSONObject.put("bundleUpdateTimestamp", bundleStatus.updateTimestamp);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    private void handleConnect(JSONObject jSONObject) throws JSONException {
        final String string = jSONObject.getString("pageId");
        if (this.mInspectorConnections.remove(string) == null) {
            try {
                this.mInspectorConnections.put(string, Inspector.connect(Integer.parseInt(string), new Inspector.RemoteConnection() { // from class: com.facebook.react.devsupport.InspectorPackagerConnection.1
                    @Override // com.facebook.react.bridge.Inspector.RemoteConnection
                    public void onDisconnect() {
                        try {
                            InspectorPackagerConnection.this.mInspectorConnections.remove(string);
                            InspectorPackagerConnection.this.sendEvent(Metrics.DISCONNECT, InspectorPackagerConnection.this.makePageIdPayload(string));
                        } catch (JSONException e) {
                            FLog.w(InspectorPackagerConnection.TAG, "Couldn't send event to packager", e);
                        }
                    }

                    @Override // com.facebook.react.bridge.Inspector.RemoteConnection
                    public void onMessage(String str) {
                        try {
                            InspectorPackagerConnection.this.sendWrappedEvent(string, str);
                        } catch (JSONException e) {
                            FLog.w(InspectorPackagerConnection.TAG, "Couldn't send event to packager", e);
                        }
                    }
                }));
                return;
            } catch (Exception e) {
                FLog.w(TAG, "Failed to open page: " + string, e);
                sendEvent(Metrics.DISCONNECT, makePageIdPayload(string));
                return;
            }
        }
        throw new IllegalStateException(GeneratedOutlineSupport1.outline72("Already connected: ", string));
    }

    private void handleDisconnect(JSONObject jSONObject) throws JSONException {
        Inspector.LocalConnection remove = this.mInspectorConnections.remove(jSONObject.getString("pageId"));
        if (remove == null) {
            return;
        }
        remove.disconnect();
    }

    private void handleWrappedEvent(JSONObject jSONObject) throws JSONException {
        String string = jSONObject.getString("pageId");
        String string2 = jSONObject.getString("wrappedEvent");
        Inspector.LocalConnection localConnection = this.mInspectorConnections.get(string);
        if (localConnection == null) {
            FLog.w(TAG, "PageID " + string + " is disconnected. Dropping event: " + string2);
            return;
        }
        localConnection.sendMessage(string2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject makePageIdPayload(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pageId", str);
        return jSONObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(String str, Object obj) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("event", str);
        jSONObject.put("payload", obj);
        this.mConnection.send(jSONObject);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendWrappedEvent(String str, String str2) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pageId", str);
        jSONObject.put("wrappedEvent", str2);
        sendEvent("wrappedEvent", jSONObject);
    }

    void closeAllConnections() {
        for (Map.Entry<String, Inspector.LocalConnection> entry : this.mInspectorConnections.entrySet()) {
            entry.getValue().disconnect();
        }
        this.mInspectorConnections.clear();
    }

    public void closeQuietly() {
        this.mConnection.close();
    }

    public void connect() {
        this.mConnection.connect();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    void handleProxyMessage(JSONObject jSONObject) throws JSONException, IOException {
        char c;
        String string = jSONObject.getString("event");
        switch (string.hashCode()) {
            case 530405532:
                if (string.equals(Metrics.DISCONNECT)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 951351530:
                if (string.equals(EmulateConnection.EXTRA_CONNECT)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1328613653:
                if (string.equals("wrappedEvent")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1962251790:
                if (string.equals("getPages")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            sendEvent("getPages", getPages());
        } else if (c == 1) {
            handleWrappedEvent(jSONObject.getJSONObject("payload"));
        } else if (c == 2) {
            handleConnect(jSONObject.getJSONObject("payload"));
        } else if (c == 3) {
            handleDisconnect(jSONObject.getJSONObject("payload"));
        } else {
            throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unknown event: ", string));
        }
    }

    public void sendEventToAllConnections(String str) {
        for (Map.Entry<String, Inspector.LocalConnection> entry : this.mInspectorConnections.entrySet()) {
            entry.getValue().sendMessage(str);
        }
    }

    /* loaded from: classes2.dex */
    public static class BundleStatus {
        public Boolean isLastDownloadSucess;
        public long updateTimestamp;

        public BundleStatus(Boolean bool, long j) {
            this.updateTimestamp = -1L;
            this.isLastDownloadSucess = bool;
            this.updateTimestamp = j;
        }

        public BundleStatus() {
            this(false, -1L);
        }
    }
}
