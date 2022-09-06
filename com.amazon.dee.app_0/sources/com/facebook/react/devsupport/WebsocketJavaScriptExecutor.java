package com.facebook.react.devsupport;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JavaJSExecutor;
import com.facebook.react.devsupport.JSDebuggerWebSocketClient;
import java.util.HashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes2.dex */
public class WebsocketJavaScriptExecutor implements JavaJSExecutor {
    private static final int CONNECT_RETRY_COUNT = 3;
    private static final long CONNECT_TIMEOUT_MS = 5000;
    private final HashMap<String, String> mInjectedObjects = new HashMap<>();
    @Nullable
    private JSDebuggerWebSocketClient mWebSocketClient;

    /* loaded from: classes2.dex */
    private static class JSExecutorCallbackFuture implements JSDebuggerWebSocketClient.JSDebuggerCallback {
        @Nullable
        private Throwable mCause;
        @Nullable
        private String mResponse;
        private final Semaphore mSemaphore;

        private JSExecutorCallbackFuture() {
            this.mSemaphore = new Semaphore(0);
        }

        @Nullable
        public String get() throws Throwable {
            this.mSemaphore.acquire();
            Throwable th = this.mCause;
            if (th == null) {
                return this.mResponse;
            }
            throw th;
        }

        @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
        public void onFailure(Throwable th) {
            this.mCause = th;
            this.mSemaphore.release();
        }

        @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
        public void onSuccess(@Nullable String str) {
            this.mResponse = str;
            this.mSemaphore.release();
        }
    }

    /* loaded from: classes2.dex */
    public interface JSExecutorConnectCallback {
        void onFailure(Throwable th);

        void onSuccess();
    }

    /* loaded from: classes2.dex */
    public static class WebsocketExecutorTimeoutException extends Exception {
        public WebsocketExecutorTimeoutException(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void connectInternal(String str, final JSExecutorConnectCallback jSExecutorConnectCallback) {
        final JSDebuggerWebSocketClient jSDebuggerWebSocketClient = new JSDebuggerWebSocketClient();
        final Handler handler = new Handler(Looper.getMainLooper());
        jSDebuggerWebSocketClient.connect(str, new JSDebuggerWebSocketClient.JSDebuggerCallback() { // from class: com.facebook.react.devsupport.WebsocketJavaScriptExecutor.2
            private boolean didSendResult = false;

            @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
            public void onFailure(Throwable th) {
                handler.removeCallbacksAndMessages(null);
                if (!this.didSendResult) {
                    jSExecutorConnectCallback.onFailure(th);
                    this.didSendResult = true;
                }
            }

            @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
            public void onSuccess(@Nullable String str2) {
                jSDebuggerWebSocketClient.prepareJSRuntime(new JSDebuggerWebSocketClient.JSDebuggerCallback() { // from class: com.facebook.react.devsupport.WebsocketJavaScriptExecutor.2.1
                    @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
                    public void onFailure(Throwable th) {
                        handler.removeCallbacksAndMessages(null);
                        if (!AnonymousClass2.this.didSendResult) {
                            jSExecutorConnectCallback.onFailure(th);
                            AnonymousClass2.this.didSendResult = true;
                        }
                    }

                    @Override // com.facebook.react.devsupport.JSDebuggerWebSocketClient.JSDebuggerCallback
                    public void onSuccess(@Nullable String str3) {
                        handler.removeCallbacksAndMessages(null);
                        AnonymousClass2 anonymousClass2 = AnonymousClass2.this;
                        WebsocketJavaScriptExecutor.this.mWebSocketClient = jSDebuggerWebSocketClient;
                        if (!AnonymousClass2.this.didSendResult) {
                            jSExecutorConnectCallback.onSuccess();
                            AnonymousClass2.this.didSendResult = true;
                        }
                    }
                });
            }
        });
        handler.postDelayed(new Runnable() { // from class: com.facebook.react.devsupport.WebsocketJavaScriptExecutor.3
            @Override // java.lang.Runnable
            public void run() {
                jSDebuggerWebSocketClient.closeQuietly();
                jSExecutorConnectCallback.onFailure(new WebsocketExecutorTimeoutException("Timeout while connecting to remote debugger"));
            }
        }, 5000L);
    }

    @Override // com.facebook.react.bridge.JavaJSExecutor
    public void close() {
        JSDebuggerWebSocketClient jSDebuggerWebSocketClient = this.mWebSocketClient;
        if (jSDebuggerWebSocketClient != null) {
            jSDebuggerWebSocketClient.closeQuietly();
        }
    }

    public void connect(final String str, final JSExecutorConnectCallback jSExecutorConnectCallback) {
        final AtomicInteger atomicInteger = new AtomicInteger(3);
        connectInternal(str, new JSExecutorConnectCallback() { // from class: com.facebook.react.devsupport.WebsocketJavaScriptExecutor.1
            @Override // com.facebook.react.devsupport.WebsocketJavaScriptExecutor.JSExecutorConnectCallback
            public void onFailure(Throwable th) {
                if (atomicInteger.decrementAndGet() > 0) {
                    WebsocketJavaScriptExecutor.this.connectInternal(str, this);
                } else {
                    jSExecutorConnectCallback.onFailure(th);
                }
            }

            @Override // com.facebook.react.devsupport.WebsocketJavaScriptExecutor.JSExecutorConnectCallback
            public void onSuccess() {
                jSExecutorConnectCallback.onSuccess();
            }
        });
    }

    @Override // com.facebook.react.bridge.JavaJSExecutor
    @Nullable
    public String executeJSCall(String str, String str2) throws JavaJSExecutor.ProxyExecutorException {
        JSExecutorCallbackFuture jSExecutorCallbackFuture = new JSExecutorCallbackFuture();
        ((JSDebuggerWebSocketClient) Assertions.assertNotNull(this.mWebSocketClient)).executeJSCall(str, str2, jSExecutorCallbackFuture);
        try {
            return jSExecutorCallbackFuture.get();
        } catch (Throwable th) {
            throw new JavaJSExecutor.ProxyExecutorException(th);
        }
    }

    @Override // com.facebook.react.bridge.JavaJSExecutor
    public void loadBundle(String str) throws JavaJSExecutor.ProxyExecutorException {
        JSExecutorCallbackFuture jSExecutorCallbackFuture = new JSExecutorCallbackFuture();
        ((JSDebuggerWebSocketClient) Assertions.assertNotNull(this.mWebSocketClient)).loadBundle(str, this.mInjectedObjects, jSExecutorCallbackFuture);
        try {
            jSExecutorCallbackFuture.get();
        } catch (Throwable th) {
            throw new JavaJSExecutor.ProxyExecutorException(th);
        }
    }

    @Override // com.facebook.react.bridge.JavaJSExecutor
    public void setGlobalVariable(String str, String str2) {
        this.mInjectedObjects.put(str, str2);
    }
}
