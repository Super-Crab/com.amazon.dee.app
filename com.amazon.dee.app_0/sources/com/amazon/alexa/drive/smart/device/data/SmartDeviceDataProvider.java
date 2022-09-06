package com.amazon.alexa.drive.smart.device.data;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.smart.device.contract.SmartDeviceContract;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
/* loaded from: classes7.dex */
public abstract class SmartDeviceDataProvider {
    private static final String HEADER_ACCEPT = "Accept";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_COOKIE = "Cookie";
    public static final String METHOD_HTTP_GET = "GET";
    public static final String METHOD_HTTP_POST = "POST";
    public static final String METHOD_HTTP_PUT = "PUT";
    private static final String TAG = "SmartDeviceDataProvider";
    private static final int TIMEOUT_MILLIS = (int) TimeUnit.SECONDS.toMillis(10);
    private static final String VALUE_APPLICATION_JSON = "application/json";
    protected final Context context;
    protected SmartDeviceAsyncTask smartDeviceAsyncTask;
    private final SmartDeviceContract.ServerResponseListener updateListener;

    @VisibleForTesting
    /* loaded from: classes7.dex */
    static class SmartDeviceAsyncTask extends AsyncTask<Void, Integer, String> {
        private final WeakReference<SmartDeviceDataProvider> dataProvider;
        private final Task task;

        SmartDeviceAsyncTask(SmartDeviceDataProvider smartDeviceDataProvider, Task task) {
            this.dataProvider = new WeakReference<>(smartDeviceDataProvider);
            this.task = task;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Not initialized variable reg: 1, insn: 0x00a4: MOVE  (r0 I:??[OBJECT, ARRAY]) = (r1 I:??[OBJECT, ARRAY]), block:B:36:0x00a4 */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00a7  */
        @Override // android.os.AsyncTask
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public java.lang.String doInBackground(java.lang.Void... r5) {
            /*
                r4 = this;
                java.lang.ref.WeakReference<com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider> r5 = r4.dataProvider
                java.lang.Object r5 = r5.get()
                com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider r5 = (com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider) r5
                if (r5 != 0) goto Ld
                java.lang.String r5 = ""
                return r5
            Ld:
                r0 = 0
                com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider.access$000()     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                r1.<init>()     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                java.lang.String r2 = "Requesting Url "
                r1.append(r2)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider$Task r2 = r4.task     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                java.net.URL r2 = com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider.Task.access$100(r2)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                r1.append(r2)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                java.lang.String r2 = ", method = "
                r1.append(r2)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider$Task r2 = r4.task     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                java.lang.String r2 = com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider.Task.access$200(r2)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                r1.append(r2)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                java.lang.String r2 = ", message = "
                r1.append(r2)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider$Task r2 = r4.task     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                java.lang.String r2 = com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider.Task.access$300(r2)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                r1.append(r2)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                r1.toString()     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider$Task r1 = r4.task     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                java.net.URL r1 = com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider.Task.access$100(r1)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                java.net.HttpURLConnection r1 = r5.getDefaultAuthenticatedConnection(r1)     // Catch: java.lang.Throwable -> L8e java.io.IOException -> L90
                com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider$Task r2 = r4.task     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
                java.lang.String r2 = com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider.Task.access$200(r2)     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
                r5.setRequestProperties(r1, r2)     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
                com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider$Task r5 = r4.task     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
                java.lang.String r5 = com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider.Task.access$300(r5)     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
                if (r5 == 0) goto L80
                java.io.OutputStreamWriter r5 = new java.io.OutputStreamWriter     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
                java.io.OutputStream r2 = r1.getOutputStream()     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
                r5.<init>(r2)     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
                com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider$Task r2 = r4.task     // Catch: java.lang.Throwable -> L74
                java.lang.String r2 = com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider.Task.access$300(r2)     // Catch: java.lang.Throwable -> L74
                r5.write(r2)     // Catch: java.lang.Throwable -> L74
                r5.close()     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
                goto L80
            L74:
                r2 = move-exception
                throw r2     // Catch: java.lang.Throwable -> L76
            L76:
                r3 = move-exception
                r5.close()     // Catch: java.lang.Throwable -> L7b
                goto L7f
            L7b:
                r5 = move-exception
                r2.addSuppressed(r5)     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
            L7f:
                throw r3     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
            L80:
                java.io.InputStream r5 = r1.getInputStream()     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
                java.lang.String r5 = com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider.parseServerResponse(r5)     // Catch: java.io.IOException -> L8c java.lang.Throwable -> La3
                r1.disconnect()
                return r5
            L8c:
                r5 = move-exception
                goto L92
            L8e:
                r5 = move-exception
                goto La5
            L90:
                r5 = move-exception
                r1 = r0
            L92:
                java.lang.String r2 = com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider.access$000()     // Catch: java.lang.Throwable -> La3
                java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> La3
                android.util.Log.e(r2, r5)     // Catch: java.lang.Throwable -> La3
                if (r1 == 0) goto La2
                r1.disconnect()
            La2:
                return r0
            La3:
                r5 = move-exception
                r0 = r1
            La5:
                if (r0 == 0) goto Laa
                r0.disconnect()
            Laa:
                throw r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.drive.smart.device.data.SmartDeviceDataProvider.SmartDeviceAsyncTask.doInBackground(java.lang.Void[]):java.lang.String");
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String str) {
            this.dataProvider.get().updateListener.onResponse(this.task.taskName != null ? this.task.taskName : "", str);
        }
    }

    /* loaded from: classes7.dex */
    public static final class Task {
        private String message;
        private String method;
        private String taskName;
        private URL url;

        /* loaded from: classes7.dex */
        public static class Builder {
            private String message;
            private String method;
            private String taskName;
            private URL url;

            public static Builder newInstance() {
                return new Builder();
            }

            public Task build() {
                return new Task(this.taskName, this.method, this.url, this.message);
            }

            public Builder message(String str) {
                this.message = str;
                return this;
            }

            public Builder method(String str) {
                this.method = str;
                return this;
            }

            public Builder taskName(String str) {
                this.taskName = str;
                return this;
            }

            public Builder url(URL url) {
                this.url = url;
                return this;
            }
        }

        private Task(String str, String str2, URL url, String str3) {
            this.taskName = str;
            this.method = str2;
            this.url = url;
            this.message = str3;
        }
    }

    public SmartDeviceDataProvider(Context context, SmartDeviceContract.ServerResponseListener serverResponseListener) {
        this.context = context;
        this.updateListener = serverResponseListener;
    }

    static String parseServerResponse(InputStream inputStream) {
        String str;
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    sb.append(readLine);
                } else {
                    inputStreamReader.close();
                    bufferedReader.close();
                    str = sb.toString();
                    try {
                        String str2 = "Server response: " + str;
                        return str;
                    } catch (Exception unused) {
                        GeneratedOutlineSupport1.outline162("Unable to parse server response ", str, TAG);
                        return null;
                    }
                }
            }
        } catch (Exception unused2) {
            str = null;
        }
    }

    protected HttpURLConnection getDefaultAuthenticatedConnection(URL url) throws IOException {
        String account = new MAPAccountManager(this.context).getAccount();
        if (!TextUtils.isEmpty(account)) {
            HttpURLConnection openConnection = AuthenticatedURLConnection.openConnection(url, new AuthenticationMethodFactory(this.context, account).newAuthenticationMethod(AuthenticationType.OAuth));
            openConnection.setReadTimeout(TIMEOUT_MILLIS);
            openConnection.setConnectTimeout(TIMEOUT_MILLIS);
            return openConnection;
        }
        throw new IllegalStateException("Must be signed into Amazon account!");
    }

    protected void setRequestProperties(HttpURLConnection httpURLConnection, String str) {
        try {
            httpURLConnection.setRequestMethod(str);
        } catch (ProtocolException e) {
            Log.e(TAG, e.getMessage());
        }
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        httpURLConnection.setRequestProperty("Cookie", SmartDeviceCookie.getInstance().getCookie());
    }

    public void startTask(Task task) {
        SmartDeviceAsyncTask smartDeviceAsyncTask = this.smartDeviceAsyncTask;
        if (smartDeviceAsyncTask != null && smartDeviceAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            this.smartDeviceAsyncTask.cancel(true);
        }
        this.smartDeviceAsyncTask = new SmartDeviceAsyncTask(this, task);
        this.smartDeviceAsyncTask.execute(new Void[0]);
    }
}
