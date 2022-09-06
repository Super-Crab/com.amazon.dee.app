package amazon.speech.simclient;

import amazon.speech.model.DeviceContext;
import amazon.speech.model.Event;
import amazon.speech.model.Payload;
import amazon.speech.model.PayloadDeviceContext;
import amazon.speech.simclient.capability.CapabilityClient;
import amazon.speech.simclient.capability.CapabilityQueryCallback;
import amazon.speech.util.HandlerWrapper;
import amazon.speech.util.Log;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.JsonWriter;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public class SpeechEventSender {
    static final String ACTIVITY_TOKEN_V2 = "activity";
    static final String ACTIVITY_TOKEN_VSUP = "playerActivity";
    static final String CONTENT_TOKEN_V2 = "contentToken";
    static final String CONTENT_TOKEN_VSUP = "token";
    private static final boolean DEBUG = false;
    static final String HEADER = "header";
    private static final String INTERFACE_VERSION_SEPARATOR_PATTERN = "\\.";
    static final String NAME_SPEECH_STATE = "SpeechState";
    static final String OFFSET_IN_MS = "offsetInMilliseconds";
    private static final String SPEECH_SYNTHESIZER_NAMESPACE = "SpeechSynthesizer";
    private static final String TAG = "SpeechEventSender";
    private static final String mEmptyPayloadString = "{}";
    static HandlerWrapper mHandler;
    private static boolean sSpeechSynthesizerSupportsSuperbowl;
    private static boolean sSuperBowlSupportSetFlag;
    private final Context mContext;
    private String mQueriedInterfaceVersion;
    private boolean mQuerySucceeded;
    final SimClient mSimClient;
    private static final Pattern VSUPERBOWL_VERSION_PATTERN = Pattern.compile("1\\.\\d");
    private static final Pattern v2_VERSION_PATTERN = Pattern.compile("0\\.\\d");

    /* loaded from: classes.dex */
    public enum SpeechEventActivityType {
        PLAYING { // from class: amazon.speech.simclient.SpeechEventSender.SpeechEventActivityType.1
            @Override // amazon.speech.simclient.SpeechEventSender.SpeechEventActivityType
            String getEventName() {
                return "SpeechStarted";
            }
        },
        INTERRUPTED { // from class: amazon.speech.simclient.SpeechEventSender.SpeechEventActivityType.2
            @Override // amazon.speech.simclient.SpeechEventSender.SpeechEventActivityType
            String getEventName() {
                return "SpeechInterrupted";
            }
        },
        FINISHED { // from class: amazon.speech.simclient.SpeechEventSender.SpeechEventActivityType.3
            @Override // amazon.speech.simclient.SpeechEventSender.SpeechEventActivityType
            String getEventName() {
                return "SpeechFinished";
            }
        };

        abstract String getEventName();
    }

    public SpeechEventSender(SimClient simClient, Context context) {
        if (simClient != null && context != null) {
            this.mContext = context;
            this.mSimClient = simClient;
            createSchedulingThread();
            return;
        }
        throw new IllegalArgumentException("Constructor arguments must be non-null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addDeviceContext(DeviceContext deviceContext) {
        this.mSimClient.addDeviceContext(new Header(deviceContext.getNamespace(), deviceContext.getName()), deviceContext.getPayload());
    }

    private int compareInterfaceVersions(String str, String str2) {
        InterfaceVersion parseInterfaceVersion = parseInterfaceVersion(str);
        InterfaceVersion parseInterfaceVersion2 = parseInterfaceVersion(str2);
        int i = parseInterfaceVersion.majorVersion;
        int i2 = parseInterfaceVersion2.majorVersion;
        if (i > i2) {
            return 1;
        }
        if (i != i2) {
            return -1;
        }
        int i3 = parseInterfaceVersion.minorVersion;
        int i4 = parseInterfaceVersion2.minorVersion;
        if (i3 > i4) {
            return 1;
        }
        return i3 == i4 ? 0 : -1;
    }

    private void createSchedulingThread() {
        if (mHandler == null) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("scheduler-");
            outline107.append(toString());
            HandlerThread handlerThread = new HandlerThread(outline107.toString());
            handlerThread.start();
            mHandler = new HandlerWrapper(new Handler(handlerThread.getLooper()));
        }
    }

    private synchronized String getQueriedInterfaceVersion() {
        if (this.mQueriedInterfaceVersion == null) {
            runSuperBowlInterfaceVersionQuery();
        }
        return this.mQueriedInterfaceVersion;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean onSuperBowlInterfaceVersion() {
        if (!sSuperBowlSupportSetFlag) {
            try {
                sSpeechSynthesizerSupportsSuperbowl = runSuperBowlInterfaceVersionQuery();
                if (sSpeechSynthesizerSupportsSuperbowl) {
                    sSuperBowlSupportSetFlag = true;
                }
            } catch (Exception e) {
                Log.e(TAG, "Unable to query for interface version", e);
                return false;
            }
        }
        return sSpeechSynthesizerSupportsSuperbowl;
    }

    private InterfaceVersion parseInterfaceVersion(String str) {
        int i;
        if (str == null) {
            return new InterfaceVersion();
        }
        String[] split = str.split("\\.");
        int i2 = 0;
        try {
            i = (split.length < 1 || split[0] == null) ? 0 : Integer.parseInt(split[0]);
        } catch (NumberFormatException unused) {
            i = 0;
        }
        try {
            if (split.length > 1 && split[1] != null) {
                i2 = Integer.parseInt(split[1]);
            }
        } catch (NumberFormatException unused2) {
            Log.w(TAG, "Version has unsupported format: version = " + str);
            return new InterfaceVersion(i, i2);
        }
        return new InterfaceVersion(i, i2);
    }

    private boolean runSuperBowlInterfaceVersionQuery() {
        Log.i(TAG, "runSuperBowlInterfaceVersionQuery");
        if (!CapabilityClient.isCapabilityServiceAvailable(this.mContext)) {
            Log.w(TAG, "Capability Service Not Available");
            return false;
        }
        CapabilityClient capabilityClient = new CapabilityClient(this.mContext);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        capabilityClient.getInterfaceVersion(SPEECH_SYNTHESIZER_NAMESPACE, new CapabilityQueryCallback() { // from class: amazon.speech.simclient.SpeechEventSender.5
            @Override // amazon.speech.simclient.capability.CapabilityQueryCallback
            public void onResult(boolean z, String str) {
                SpeechEventSender.this.mQuerySucceeded = z;
                SpeechEventSender.this.mQueriedInterfaceVersion = str;
                countDownLatch.countDown();
            }
        });
        try {
            try {
                boolean await = countDownLatch.await(5L, TimeUnit.SECONDS);
                capabilityClient.teardown();
                if (this.mQuerySucceeded && await) {
                    String str = TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("runSuperBowlInterfaceVersionQuery: ");
                    outline107.append(this.mQueriedInterfaceVersion);
                    Log.i(str, outline107.toString());
                    return VSUPERBOWL_VERSION_PATTERN.matcher(this.mQueriedInterfaceVersion).matches();
                }
                throw new RuntimeException(String.format("Interface Version Query Failed; succeeded (%s) triggered (%s)", Boolean.valueOf(this.mQuerySucceeded), Boolean.valueOf(await)));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } catch (Throwable th) {
            capabilityClient.teardown();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendEvent(Event event) {
        this.mSimClient.sendEvent(new Header(event.getNamespace(), event.getName()), event.getPayload(), 0);
    }

    private void sendSpeechEvent(SpeechEventActivityType speechEventActivityType, String str) {
        sendSpeechEvent(speechEventActivityType, str, 0L);
    }

    Event getSpeechEvent(SpeechEventActivityType speechEventActivityType, String str, long j) {
        return getSpeechEvent(speechEventActivityType, str, j, new Event());
    }

    DeviceContext getSpeechEventDeviceContext(final SpeechEventActivityType speechEventActivityType, final String str, final long j) {
        return new PayloadDeviceContext(SPEECH_SYNTHESIZER_NAMESPACE, NAME_SPEECH_STATE, new Payload() { // from class: amazon.speech.simclient.SpeechEventSender.4
            @Override // amazon.speech.model.Payload
            protected void writeContents(JsonWriter jsonWriter) throws IOException {
                jsonWriter.beginObject();
                jsonWriter.name(SpeechEventSender.this.onSuperBowlInterfaceVersion() ? "token" : "contentToken").value(str);
                jsonWriter.name(SpeechEventSender.OFFSET_IN_MS).value(Long.toString(j));
                jsonWriter.name(SpeechEventSender.this.onSuperBowlInterfaceVersion() ? SpeechEventSender.ACTIVITY_TOKEN_VSUP : "activity").value(speechEventActivityType.name());
                jsonWriter.endObject();
            }
        }.getPayload());
    }

    public void sendSpeechEndEvent(String str) {
        sendSpeechEvent(SpeechEventActivityType.FINISHED, str);
    }

    public void sendSpeechInterruptEvent(String str, long j) {
        if (!onSuperBowlInterfaceVersion() || compareInterfaceVersions(getQueriedInterfaceVersion(), "1.1") >= 0) {
            sendSpeechEvent(SpeechEventActivityType.INTERRUPTED, str, j);
        }
    }

    public void sendSpeechStartEvent(String str) {
        sendSpeechEvent(SpeechEventActivityType.PLAYING, str);
    }

    public void updateDeviceContextAudioStopped(final String str, final long j) {
        if (!TextUtils.isEmpty(str)) {
            mHandler.post(new Runnable() { // from class: amazon.speech.simclient.SpeechEventSender.3
                @Override // java.lang.Runnable
                public void run() {
                    SpeechEventSender.this.addDeviceContext(SpeechEventSender.this.getSpeechEventDeviceContext(SpeechEventActivityType.FINISHED, str, j));
                }
            });
            return;
        }
        throw new IllegalArgumentException("contentToken must be non-empty");
    }

    private void sendSpeechEvent(final SpeechEventActivityType speechEventActivityType, final String str, final long j) {
        if (!TextUtils.isEmpty(str)) {
            mHandler.post(new Runnable() { // from class: amazon.speech.simclient.SpeechEventSender.1
                @Override // java.lang.Runnable
                public void run() {
                    SpeechEventSender.this.addDeviceContext(SpeechEventSender.this.getSpeechEventDeviceContext(speechEventActivityType, str, j));
                    SpeechEventSender.this.sendEvent(SpeechEventSender.this.getSpeechEvent(speechEventActivityType, str, j));
                }
            });
            return;
        }
        throw new IllegalArgumentException("contentToken must be non-empty");
    }

    Event getSpeechEvent(final SpeechEventActivityType speechEventActivityType, final String str, final long j, Event event) {
        event.setNamespace(SPEECH_SYNTHESIZER_NAMESPACE);
        event.setName(speechEventActivityType.getEventName());
        boolean onSuperBowlInterfaceVersion = onSuperBowlInterfaceVersion();
        String str2 = mEmptyPayloadString;
        if (onSuperBowlInterfaceVersion) {
            String payload = new Payload() { // from class: amazon.speech.simclient.SpeechEventSender.2
                @Override // amazon.speech.model.Payload
                protected void writeContents(JsonWriter jsonWriter) throws IOException {
                    jsonWriter.beginObject();
                    jsonWriter.name("token").value(str);
                    if (speechEventActivityType == SpeechEventActivityType.INTERRUPTED) {
                        jsonWriter.name(SpeechEventSender.OFFSET_IN_MS).value(Long.toString(j));
                    }
                    jsonWriter.endObject();
                }
            }.getPayload();
            if (payload == null) {
                Log.e(TAG, "Failed creating event payload");
            } else {
                str2 = payload;
            }
        }
        event.setPayload(str2);
        return event;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes.dex */
    public static class InterfaceVersion {
        final int majorVersion;
        final int minorVersion;

        InterfaceVersion() {
            this.majorVersion = 0;
            this.minorVersion = 0;
        }

        InterfaceVersion(int i, int i2) {
            this.majorVersion = i;
            this.minorVersion = i2;
        }
    }
}
