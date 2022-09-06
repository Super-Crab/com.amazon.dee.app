package amazon.speech.nexusclient;

import amazon.speech.nexusclient.Marketplace;
import amazon.speech.nexusclient.event.INexusEvent;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class DefaultNexusRecorder implements INexusRecorder {
    private static final String DATA_KEY = "data";
    private static final int DEFAULT_CONNECTION_TIMEOUT = 30000;
    private static final String DEFAULT_MARKETPLACE_ID = "ATVPDKIKX0DER";
    private static final int DEFAULT_READ_TIMEOUT = 30000;
    private static final String EVENT_KEY = "events";
    private static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_MARKETPLACE = "x-marketplace";
    private static final String HEADER_VALUE_GZIP = "gzip";
    private static final String HEADER_VALUE_JSON = "application/json";
    private static final String TAG = GeneratedOutlineSupport1.outline39(DefaultNexusRecorder.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private Config mConfig;
    private List<INexusEvent> mEvents;
    private ExecutorService mExecutor;
    private Object mListLock;

    /* renamed from: amazon.speech.nexusclient.DefaultNexusRecorder$1  reason: invalid class name */
    /* loaded from: classes.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$amazon$speech$nexusclient$Marketplace$Region = new int[Marketplace.Region.values().length];

        static {
            try {
                $SwitchMap$amazon$speech$nexusclient$Marketplace$Region[Marketplace.Region.AP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$amazon$speech$nexusclient$Marketplace$Region[Marketplace.Region.EU.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$amazon$speech$nexusclient$Marketplace$Region[Marketplace.Region.NA.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$amazon$speech$nexusclient$Marketplace$Region[Marketplace.Region.SA.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class URLConfig {
        private static final String HOST_CN = "unagi-cn.amazon.com";
        private static final String HOST_EU = "unagi-eu.amazon.com";
        private static final String HOST_FE = "unagi-fe.amazon.com";
        private static final String HOST_NA = "unagi-na.amazon.com";
        private static final String PATH_PREPROD = "/1/events/com.amazon.mbm.nexus.events.multiapps.preprod";
        private static final String PATH_PROD = "/1/events/com.amazon.mbm.nexus.events.multiapps";
        private static final int PORT = 443;
        private static final String PROTOCOL = "https";

        private URLConfig() {
        }

        public static URL getUrl(Marketplace.Region region) {
            try {
                int ordinal = region.ordinal();
                if (ordinal == 0) {
                    return new URL("https", HOST_NA, PORT, PATH_PROD);
                }
                if (ordinal == 1) {
                    return new URL("https", HOST_NA, PORT, PATH_PROD);
                }
                if (ordinal == 2) {
                    return new URL("https", HOST_EU, PORT, PATH_PROD);
                }
                if (ordinal == 3) {
                    return new URL("https", HOST_CN, PORT, PATH_PROD);
                }
                return null;
            } catch (MalformedURLException unused) {
                return null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class UploadRunnable implements Runnable {
        private boolean mIsDebug;
        private final List<INexusEvent> mList;
        private final Marketplace.Region mRegion;

        public UploadRunnable(Marketplace.Region region, List<INexusEvent> list, boolean z) {
            this.mIsDebug = false;
            this.mRegion = region;
            this.mList = list;
            this.mIsDebug = z;
        }

        /* JADX WARN: Code restructure failed: missing block: B:22:0x007b, code lost:
            if (r0 == null) goto L14;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00a3  */
        /* JADX WARN: Type inference failed for: r0v0, types: [java.net.URL] */
        /* JADX WARN: Type inference failed for: r0v1 */
        /* JADX WARN: Type inference failed for: r0v3, types: [java.net.HttpURLConnection] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void tryUpload(java.util.List<amazon.speech.nexusclient.event.INexusEvent> r8) {
            /*
                r7 = this;
                java.net.URL r0 = r7.createUrl()
                java.lang.String r1 = "ATVPDKIKX0DER"
                r2 = 1
                r3 = 0
                r4 = -1
                java.net.URLConnection r0 = r0.openConnection()     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
                java.net.HttpURLConnection r0 = (java.net.HttpURLConnection) r0     // Catch: java.lang.Throwable -> L6c java.lang.Exception -> L6f
                r5 = 30000(0x7530, float:4.2039E-41)
                r0.setConnectTimeout(r5)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                r0.setReadTimeout(r5)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                r0.setDoOutput(r2)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                java.lang.String r5 = "Content-Type"
                java.lang.String r6 = "application/json"
                r0.setRequestProperty(r5, r6)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                java.lang.String r5 = "x-marketplace"
                r0.setRequestProperty(r5, r1)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                java.lang.String r1 = "Content-Encoding"
                java.lang.String r5 = "gzip"
                r0.setRequestProperty(r1, r5)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                boolean r1 = r7.mIsDebug     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                if (r1 == 0) goto L4a
                java.lang.String r1 = amazon.speech.nexusclient.DefaultNexusRecorder.access$100()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                r5.<init>()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                java.lang.String r6 = "Uploading event file to endpoint: "
                r5.append(r6)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                r5.append(r0)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                java.lang.String r5 = r5.toString()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                android.util.Log.i(r1, r5)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
            L4a:
                java.io.BufferedOutputStream r1 = new java.io.BufferedOutputStream     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                java.io.OutputStream r5 = r0.getOutputStream()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                r1.<init>(r5)     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                r7.writeRecord(r8, r1)     // Catch: java.lang.Throwable -> L65
                r1.close()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                int r4 = r0.getResponseCode()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                java.lang.String r3 = r0.getResponseMessage()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
            L61:
                r0.disconnect()
                goto L7e
            L65:
                r8 = move-exception
                r1.close()     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
                throw r8     // Catch: java.lang.Exception -> L6a java.lang.Throwable -> La0
            L6a:
                r8 = move-exception
                goto L71
            L6c:
                r8 = move-exception
                r0 = r3
                goto La1
            L6f:
                r8 = move-exception
                r0 = r3
            L71:
                java.lang.String r1 = amazon.speech.nexusclient.DefaultNexusRecorder.access$100()     // Catch: java.lang.Throwable -> La0
                java.lang.String r5 = "something went wrong."
                android.util.Log.e(r1, r5, r8)     // Catch: java.lang.Throwable -> La0
                if (r0 == 0) goto L7e
                goto L61
            L7e:
                r8 = 400(0x190, float:5.6E-43)
                if (r4 >= r8) goto L86
                r8 = 200(0xc8, float:2.8E-43)
                if (r4 >= r8) goto L9f
            L86:
                java.lang.String r8 = amazon.speech.nexusclient.DefaultNexusRecorder.access$100()
                r0 = 2
                java.lang.Object[] r0 = new java.lang.Object[r0]
                r1 = 0
                java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
                r0[r1] = r4
                r0[r2] = r3
                java.lang.String r1 = "Failed to upload events, %d: %s"
                java.lang.String r0 = java.lang.String.format(r1, r0)
                android.util.Log.e(r8, r0)
            L9f:
                return
            La0:
                r8 = move-exception
            La1:
                if (r0 == 0) goto La6
                r0.disconnect()
            La6:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: amazon.speech.nexusclient.DefaultNexusRecorder.UploadRunnable.tryUpload(java.util.List):void");
        }

        private void write(String str, OutputStream outputStream) throws IOException {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new GZIPOutputStream(outputStream), "UTF-8");
            try {
                outputStreamWriter.write(str);
            } finally {
                outputStreamWriter.close();
            }
        }

        private void writeRecord(List<INexusEvent> list, OutputStream outputStream) throws IOException, JSONException {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONObject.put("events", jSONArray);
            int size = list.size();
            for (int i = 0; i < size; i++) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("data", list.get(i).toJSON());
                jSONArray.put(jSONObject2);
            }
            if (this.mIsDebug) {
                String unused = DefaultNexusRecorder.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("writing to output steam json:");
                outline107.append(jSONObject.toString());
                outline107.toString();
            }
            write(jSONObject.toString(), outputStream);
        }

        URL createUrl() {
            return URLConfig.getUrl(this.mRegion);
        }

        List<INexusEvent> getList() {
            return this.mList;
        }

        @Override // java.lang.Runnable
        public void run() {
            tryUpload(this.mList);
        }
    }

    public DefaultNexusRecorder(Config config) {
        this.mConfig = new Config(config);
        this.mExecutor = Executors.newCachedThreadPool();
        this.mEvents = new ArrayList();
        this.mListLock = new Object();
    }

    private boolean shouldFlush() {
        return this.mEvents.size() >= this.mConfig.getBufferSize();
    }

    @Override // amazon.speech.nexusclient.INexusRecorder
    public void flush() {
        UploadRunnable uploadRunnable;
        synchronized (this.mListLock) {
            Log.i(TAG, String.format("uploading %d events", Integer.valueOf(this.mEvents.size())));
            uploadRunnable = new UploadRunnable(this.mConfig.mRegion, this.mEvents, this.mConfig.isDebug());
            this.mEvents = new ArrayList();
        }
        this.mExecutor.submit(uploadRunnable);
    }

    List<INexusEvent> getEvents() {
        return this.mEvents;
    }

    @Override // amazon.speech.nexusclient.INexusRecorder
    public void record(INexusEvent iNexusEvent) {
        synchronized (this.mListLock) {
            this.mEvents.add(iNexusEvent);
            if (shouldFlush()) {
                flush();
            }
        }
    }

    @Override // amazon.speech.nexusclient.INexusRecorder
    public void release() {
        this.mExecutor.shutdown();
    }

    /* loaded from: classes.dex */
    public static class Config {
        private static final int DEFAULT_BUFFER_SIZE = 1;
        private int mBufferSize;
        private boolean mDebug;
        private Marketplace.Region mRegion;

        public Config() {
            this.mBufferSize = 1;
            this.mDebug = false;
            this.mRegion = Marketplace.Region.NA;
        }

        public int getBufferSize() {
            return this.mBufferSize;
        }

        public Marketplace.Region getRegion() {
            return this.mRegion;
        }

        public boolean isDebug() {
            return this.mDebug;
        }

        public Config setBufferSize(int i) {
            if (i >= 1) {
                this.mBufferSize = i;
                return this;
            }
            throw new IllegalArgumentException("buffer size must be greater than 1");
        }

        public Config setDebug(boolean z) {
            this.mDebug = z;
            return this;
        }

        public Config setRegion(Marketplace.Region region) {
            if (region != null) {
                this.mRegion = region;
                return this;
            }
            throw new IllegalArgumentException("region cannot be null");
        }

        public Config(Config config) {
            this.mBufferSize = 1;
            this.mDebug = false;
            this.mRegion = Marketplace.Region.NA;
            if (config != null) {
                this.mBufferSize = config.mBufferSize;
                this.mDebug = config.mDebug;
                return;
            }
            throw new IllegalArgumentException();
        }
    }

    DefaultNexusRecorder(Config config, ExecutorService executorService) {
        this.mConfig = config;
        this.mExecutor = executorService;
        this.mEvents = new ArrayList();
        this.mListLock = new Object();
    }
}
