package amazon.speech.nexusclient;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
/* loaded from: classes.dex */
public class NexusClient {
    static final String TAG = GeneratedOutlineSupport1.outline39(NexusClient.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private final Config mConfig;
    private final DeviceIdRetriever mDeviceIdRetriever;
    private INexusRecorder mRecorder;
    private Map<String, NexusSession> mSessionMap;

    /* loaded from: classes.dex */
    public static final class Config {
        public static final String DEFAULT_CSM_PRODUCT_ID = "echodevice";
        private String mProductId = DEFAULT_CSM_PRODUCT_ID;

        public String getProductId() {
            return this.mProductId;
        }

        public Config setProductId(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mProductId = str;
                return this;
            }
            throw new IllegalArgumentException("id must not be null");
        }
    }

    public NexusClient(Config config, INexusRecorder iNexusRecorder, Context context) {
        this(config, iNexusRecorder, new DeviceIdRetriever(context));
    }

    private NexusSession findSessionBasedOnEventId(Intent intent) {
        String stringExtra = intent.getStringExtra("event_id");
        if (TextUtils.isEmpty(stringExtra)) {
            return null;
        }
        GeneratedOutlineSupport1.outline163("Searching for session containing event id:", stringExtra, TAG);
        for (Map.Entry<String, NexusSession> entry : this.mSessionMap.entrySet()) {
            NexusSession value = entry.getValue();
            if (value.hasEvent(stringExtra)) {
                return value;
            }
        }
        return null;
    }

    public Config getConfig() {
        return this.mConfig;
    }

    public final INexusRecorder getRecorder() {
        return this.mRecorder;
    }

    Map<String, NexusSession> getSessionMap() {
        return this.mSessionMap;
    }

    public boolean isReleased() {
        return this.mRecorder == null;
    }

    public NexusSession obtainSession(Intent intent) {
        String stringExtra = intent.getStringExtra(NexusConstant.INTENT_KEY_SESSION_ID);
        if (!TextUtils.isEmpty(stringExtra)) {
            Log.i(TAG, String.format("obtain session from session id [%s] from intent", stringExtra));
            return obtainSession(stringExtra);
        }
        NexusSession findSessionBasedOnEventId = findSessionBasedOnEventId(intent);
        if (findSessionBasedOnEventId != null) {
            Log.i(TAG, "Found session based on the event id in intent");
            return findSessionBasedOnEventId;
        }
        return obtainSession((String) null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onSessionFinished(NexusSession nexusSession) {
        if (nexusSession == null) {
            return;
        }
        this.mSessionMap.remove(nexusSession.getSessionId());
    }

    public void release() {
        for (Map.Entry<String, NexusSession> entry : this.mSessionMap.entrySet()) {
            entry.getValue().release();
        }
        this.mRecorder.release();
        this.mRecorder = null;
        this.mSessionMap.clear();
    }

    NexusClient(Config config, INexusRecorder iNexusRecorder, DeviceIdRetriever deviceIdRetriever) {
        if (config != null) {
            if (iNexusRecorder == null) {
                throw new IllegalArgumentException("Recorder cannot be null");
            }
            if (deviceIdRetriever != null) {
                this.mConfig = config;
                this.mRecorder = iNexusRecorder;
                this.mSessionMap = new HashMap();
                this.mDeviceIdRetriever = deviceIdRetriever;
                return;
            }
            throw new IllegalArgumentException("DeviceIdRetriever cannot be null");
        }
        throw new IllegalArgumentException("Config cannot be null");
    }

    public NexusSession obtainSession(String str) {
        NexusSession nexusSession;
        String str2 = TAG;
        Log.i(str2, "obtaining session for " + str);
        if (TextUtils.isEmpty(str)) {
            str = UUID.randomUUID().toString();
            nexusSession = null;
        } else {
            nexusSession = this.mSessionMap.get(str);
        }
        if (nexusSession == null) {
            NexusSession nexusSession2 = new NexusSession(str, this, this.mConfig, this.mDeviceIdRetriever);
            this.mSessionMap.put(str, nexusSession2);
            return nexusSession2;
        }
        return nexusSession;
    }

    public NexusSession obtainSession() {
        return obtainSession((String) null);
    }
}
