package amazon.speech.nexusclient;

import amazon.speech.nexusclient.DeviceIdRetriever;
import amazon.speech.nexusclient.NexusClient;
import amazon.speech.nexusclient.event.CSMNexusEvent;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public class NexusSession {
    private final String TAG = GeneratedOutlineSupport1.outline39(NexusSession.class, GeneratedOutlineSupport1.outline107("SPCH-"));
    private final NexusClient.Config mConfig;
    private final DeviceIdRetriever mDeviceIdRetriever;
    private final List<String> mEventIds;
    private NexusClient mNexusClient;
    private final String mSessionId;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NexusSession(String str, NexusClient nexusClient, NexusClient.Config config, DeviceIdRetriever deviceIdRetriever) {
        if (!TextUtils.isEmpty(str)) {
            if (nexusClient == null) {
                throw new IllegalArgumentException("Nexus Client is null");
            }
            if (config == null) {
                throw new IllegalArgumentException("Config is null");
            }
            if (deviceIdRetriever != null) {
                this.mSessionId = str;
                this.mNexusClient = nexusClient;
                this.mConfig = config;
                this.mEventIds = new ArrayList();
                this.mDeviceIdRetriever = deviceIdRetriever;
                return;
            }
            throw new IllegalArgumentException("DeviceIdRetriever is null");
        }
        throw new IllegalArgumentException("Session id is null");
    }

    private void addEventToList(CSMNexusEvent cSMNexusEvent) {
        this.mEventIds.add(cSMNexusEvent.getRequestId());
    }

    private final INexusRecorder getRecorder() {
        return this.mNexusClient.getRecorder();
    }

    public synchronized void finish() {
        this.mNexusClient.onSessionFinished(this);
        release();
    }

    List<String> getEventIds() {
        return this.mEventIds;
    }

    NexusClient getNexusClient() {
        return this.mNexusClient;
    }

    public final String getSessionId() {
        return this.mSessionId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized boolean hasEvent(String str) {
        return this.mEventIds.contains(str);
    }

    public synchronized CSMNexusEvent obtainCSMNexusEvent(Intent intent) {
        String stringExtra;
        String retrieve;
        String str = null;
        stringExtra = intent == null ? null : intent.getStringExtra("event_id");
        if (!this.mEventIds.isEmpty()) {
            str = this.mEventIds.get(this.mEventIds.size() - 1);
        }
        if (TextUtils.isEmpty(stringExtra)) {
            stringExtra = str;
        }
        try {
            retrieve = this.mDeviceIdRetriever.retrieve();
        } catch (DeviceIdRetriever.DeviceIdRetrieverException e) {
            Log.e(this.TAG, "Error retrieving deviceId", e);
        }
        return new CSMNexusEvent().setProducerId(this.mConfig.getProductId()).setPreviousRequestId(stringExtra).setNexusSessionId(this.mSessionId).setDeviceId(retrieve);
    }

    public synchronized void recordEvent(CSMNexusEvent cSMNexusEvent) {
        addEventToList(cSMNexusEvent);
        INexusRecorder recorder = getRecorder();
        if (recorder != null) {
            recorder.record(cSMNexusEvent);
        } else {
            Log.e(this.TAG, "recordEvent: recorder is null. Failed to record event.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void release() {
        this.mEventIds.clear();
        this.mNexusClient = null;
    }

    public synchronized CSMNexusEvent obtainCSMNexusEvent() {
        return obtainCSMNexusEvent(null);
    }
}
