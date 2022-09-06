package com.amazon.pryon.android.asr;

import android.util.Log;
import java.util.List;
/* loaded from: classes13.dex */
public class PryonLite5000 {
    public static final int PRYON_LITE_SPEAKER_VERIFICATION_EXAMPLE_ACCEPTED = 0;
    public static final int PRYON_LITE_SPEAKER_VERIFICATION_EXAMPLE_REJECTED = 1;
    public static final int PRYON_LITE_SPEAKER_VERIFICATION_PROFILE_GENERATED = 2;
    private static final String TAG = "PryonLite5000";
    private static boolean libraryIsLoaded;
    private Callbacks callbacks;
    private long nativeMem = 0;

    /* loaded from: classes13.dex */
    public class Attributes {
        public String engineVersion;
        public String fingerprintApiVersion;
        public int fingerprintListVersion;
        public int maxMetadataBlobSize;
        public int requiredMem;
        public int samplesPerFrame;
        public String speakerVerificationApiVersion;
        public String speakerVerificationConfigVersion;
        public List<String> speakerVerificationKeywords;
        public List<String> speakerVerificationLocales;
        public int speakerVerificationMaxProfileSize;
        public byte[] speakerVerificationModelId;
        public String wakewordApiVersion;
        public String wakewordConfigVersion;
        public List<String> wakewordKeywords;
        public String watermarkApiVersion;

        public Attributes(String str, int i, int i2, int i3, String str2, String str3, String str4, int i4, String str5, String str6, int i5, byte[] bArr, List<String> list, List<String> list2, String str7, List<String> list3) {
            this.engineVersion = str;
            this.maxMetadataBlobSize = i;
            this.requiredMem = i2;
            this.samplesPerFrame = i3;
            this.wakewordKeywords = list3;
            this.wakewordApiVersion = str2;
            this.wakewordConfigVersion = str3;
            this.fingerprintApiVersion = str4;
            this.fingerprintListVersion = i4;
            this.watermarkApiVersion = str7;
            this.speakerVerificationApiVersion = str5;
            this.speakerVerificationConfigVersion = str6;
            this.speakerVerificationModelId = bArr;
            this.speakerVerificationMaxProfileSize = i5;
            this.speakerVerificationKeywords = list;
            this.speakerVerificationLocales = list2;
        }
    }

    /* loaded from: classes13.dex */
    public interface Callbacks {
        void errorEvent(int i);

        void speakerVerificationClassificationEvent(byte[] bArr, int i, byte[] bArr2, float f, float f2, float f3, int i2);

        void speakerVerificationEnrollmentEvent(byte[] bArr, int i, byte[] bArr2, byte[] bArr3);

        void speakerVerificationWakewordExampleEvent(String str, int i, int i2, short[] sArr, byte[] bArr);

        void vadStateChanged(int i);

        void wakeWordDetected(String str, long j, long j2, byte[] bArr);
    }

    /* loaded from: classes13.dex */
    public enum ClientProperty {
        AUDIO_PLAYBACK(0, 0),
        ALARM_STATE(0, 1),
        MEDIA_PLAYER_STATE(0, 2),
        EARCON_PLAYER_STATE(0, 3),
        TTS_PLAYER_STATE(0, 4),
        AUTOMOTIVE_MODE(3, 0);
        
        public final int groupId;
        public final int propertyId;

        ClientProperty(int i, int i2) {
            this.groupId = i;
            this.propertyId = i2;
        }
    }

    /* loaded from: classes13.dex */
    public static class ClientPropertyState {
        public static final int AUTOMOTIVE_ACTIVE = 1;
        public static final int AUTOMOTIVE_INACTIVE = 0;
        public static final int AUTOMOTIVE_UNKNOWN = -1;
        public static final int COMMON_NOT_PLAYING = 0;
        public static final int COMMON_PLAYING = 1;
        public static final int COMMON_UNKNOWN = -1;
    }

    /* loaded from: classes13.dex */
    public static class Config {
        public int detectThreshold;
        public byte[] fingerprintList;
        public boolean lowLatency;
        public int maxLoadableProfiles;
        public int maxProfileIdSize;
        public int minEnrollmentSnr;
        public int numEnrollmentExamples;
        public byte[] speakerVerificationModel;
        public boolean useVad;
        public byte[] wakewordModel;
        public byte[] watermarkConfig;
    }

    static {
        try {
            Log.i(TAG, "load library");
            System.loadLibrary("pryon-lite-jni");
            libraryIsLoaded = true;
        } catch (UnsatisfiedLinkError e) {
            Log.e(TAG, "unsatisfiedLinkError: ", e);
            libraryIsLoaded = false;
        }
    }

    public PryonLite5000(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    public static boolean isAvailable() {
        return libraryIsLoaded;
    }

    public static boolean libraryIsLoaded() {
        return libraryIsLoaded;
    }

    private native int nativeDestroy(long j);

    private native Attributes nativeGetAttributes(Config config);

    private native int nativeGetSamplesPerFrame(long j);

    private native int nativeInitialize(long j, Config config);

    private native int nativePushAudio(long j, short[] sArr);

    private native int nativeSetClientProperty(long j, int i, int i2, int i3);

    private native int nativeSpeakerVerificationClassificationLoadProfile(long j, byte[] bArr);

    private native int nativeSpeakerVerificationClassificationOverrideAcceptanceThreshold(long j, float f);

    private native int nativeSpeakerVerificationClassificationSetLocale(long j, String str);

    private native int nativeSpeakerVerificationClassificationUnloadProfile(long j, byte[] bArr);

    private native int nativeSpeakerVerificationEnrollmentCreateSession(long j, byte[] bArr);

    private native int nativeSpeakerVerificationEnrollmentDestroySession(long j, byte[] bArr);

    private native int nativeSpeakerVerificationEnrollmentDisable(long j, byte[] bArr);

    private native int nativeSpeakerVerificationEnrollmentEnable(long j, byte[] bArr);

    private native int nativeSpeakerVerificationEnrollmentPushExample(long j, String str, int i, int i2, short[] sArr, byte[] bArr);

    private native byte[] nativeSpeakerVerificationGetModelIdFromProfile(byte[] bArr);

    private native byte[] nativeSpeakerVerificationGetProfileIdFromProfile(byte[] bArr);

    private native int nativeWakewordEnableKeyword(long j, String str, int i);

    private native int nativeWakewordSetDetectionThreshold(long j, int i);

    public synchronized int destroy() {
        int nativeDestroy;
        Log.i(TAG, "Destroying PryonLite decoder...");
        nativeDestroy = nativeDestroy(this.nativeMem);
        if (nativeDestroy != 0) {
            String str = TAG;
            Log.e(str, "destroy returned error = " + nativeDestroy);
        }
        Log.i(TAG, "PryonLite destroy() returning to caller.");
        return nativeDestroy;
    }

    public Attributes getAttributes(Config config) {
        Log.i(TAG, "Querying PryonLite attributes...");
        Attributes nativeGetAttributes = nativeGetAttributes(config);
        if (nativeGetAttributes == null) {
            Log.e(TAG, "getAttributes failed");
        }
        Log.i(TAG, "PryonLite getAttributes() returning to caller.");
        return nativeGetAttributes;
    }

    public synchronized int getSamplesPerFrame() {
        int nativeGetSamplesPerFrame;
        nativeGetSamplesPerFrame = nativeGetSamplesPerFrame(this.nativeMem);
        String str = TAG;
        Log.i(str, "PryonLite getSamplesPerFrame returned " + nativeGetSamplesPerFrame);
        if (nativeGetSamplesPerFrame <= 0) {
            String str2 = TAG;
            Log.e(str2, "getSamplesPerFrame returned error = " + nativeGetSamplesPerFrame);
        }
        return nativeGetSamplesPerFrame;
    }

    public synchronized int initialize(Config config) {
        int nativeInitialize;
        Log.i(TAG, "Initializing PryonLite decoder...");
        nativeInitialize = nativeInitialize(this.nativeMem, config);
        if (nativeInitialize != 0) {
            String str = TAG;
            Log.e(str, "initialize returned error = " + nativeInitialize);
        }
        Log.i(TAG, "PryonLite initialize() returning to caller.");
        return nativeInitialize;
    }

    public synchronized int isInitialized() {
        return this.nativeMem != 0 ? 1 : 0;
    }

    protected void onErrorEvent(int i) {
        String str = TAG;
        Log.e(str, "error event received, code = " + i);
        this.callbacks.errorEvent(i);
    }

    protected void onSpeakerVerificationClassificationEvent(byte[] bArr, int i, byte[] bArr2, float f, float f2, float f3, int i2) {
        Log.i(TAG, "Forwarding speaker verification classification event to client...");
        this.callbacks.speakerVerificationClassificationEvent(bArr, i, bArr2, f, f2, f3, i2);
    }

    protected void onSpeakerVerificationEnrollmentEvent(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
        Log.i(TAG, "Forwarding speaker verification enrollment event to client...");
        this.callbacks.speakerVerificationEnrollmentEvent(bArr, i, bArr2, bArr3);
    }

    protected void onSpeakerVerificationWakewordExampleEvent(String str, int i, int i2, short[] sArr, byte[] bArr) {
        Log.i(TAG, "Forwarding speaker verification wakeword example event to client...");
        this.callbacks.speakerVerificationWakewordExampleEvent(str, i, i2, sArr, bArr);
    }

    protected void onVADStateChanged(int i) {
        Log.i(TAG, "Forwarding VAD event to client...");
        this.callbacks.vadStateChanged(i);
    }

    protected void onWakeWordDetected(String str, long j, long j2, byte[] bArr) {
        Log.i(TAG, "Forwarding wakeword detection event to client...");
        this.callbacks.wakeWordDetected(str, j, j2, bArr);
    }

    public synchronized int pushAudio(short[] sArr) {
        int nativePushAudio;
        nativePushAudio = nativePushAudio(this.nativeMem, sArr);
        if (nativePushAudio != 0) {
            String str = TAG;
            Log.e(str, "pushAudio returned error = " + nativePushAudio);
        }
        return nativePushAudio;
    }

    public synchronized int setClientProperty(ClientProperty clientProperty, int i) {
        int nativeSetClientProperty;
        String str = TAG;
        Log.i(str, "PryonLite setClientProperty invoked - group: " + clientProperty.groupId + " properties:" + clientProperty.propertyId + " state data:" + i);
        nativeSetClientProperty = nativeSetClientProperty(this.nativeMem, clientProperty.groupId, clientProperty.propertyId, i);
        if (nativeSetClientProperty != 0) {
            String str2 = TAG;
            Log.e(str2, "setClientProperty returned error = " + nativeSetClientProperty);
        }
        return nativeSetClientProperty;
    }

    public synchronized int speakerVerificationClassificationLoadProfile(byte[] bArr) {
        int nativeSpeakerVerificationClassificationLoadProfile;
        Log.i(TAG, "Loading Speaker Verification Voice Profile...");
        nativeSpeakerVerificationClassificationLoadProfile = nativeSpeakerVerificationClassificationLoadProfile(this.nativeMem, bArr);
        if (nativeSpeakerVerificationClassificationLoadProfile != 0) {
            String str = TAG;
            Log.e(str, "speakerVerificationClassificationLoadProfile returned error = " + nativeSpeakerVerificationClassificationLoadProfile);
        }
        return nativeSpeakerVerificationClassificationLoadProfile;
    }

    public synchronized int speakerVerificationClassificationOverrideAcceptanceThreshold(float f) {
        int nativeSpeakerVerificationClassificationOverrideAcceptanceThreshold;
        String str = TAG;
        Log.i(str, "Overriding Speaker Verification Acceptance Threshold to " + f);
        nativeSpeakerVerificationClassificationOverrideAcceptanceThreshold = nativeSpeakerVerificationClassificationOverrideAcceptanceThreshold(this.nativeMem, f);
        if (nativeSpeakerVerificationClassificationOverrideAcceptanceThreshold != 0) {
            String str2 = TAG;
            Log.e(str2, "speakerVerificationClassificationOverrideAcceptanceThreshold returned error = " + nativeSpeakerVerificationClassificationOverrideAcceptanceThreshold);
        }
        return nativeSpeakerVerificationClassificationOverrideAcceptanceThreshold;
    }

    public synchronized int speakerVerificationClassificationSetLocale(String str) {
        int nativeSpeakerVerificationClassificationSetLocale;
        Log.i(TAG, "Setting Speaker Verification Classification Locale...");
        nativeSpeakerVerificationClassificationSetLocale = nativeSpeakerVerificationClassificationSetLocale(this.nativeMem, str);
        if (nativeSpeakerVerificationClassificationSetLocale != 0) {
            String str2 = TAG;
            Log.e(str2, "speakerVerificationClassificationSetLocale returned error = " + nativeSpeakerVerificationClassificationSetLocale);
        }
        return nativeSpeakerVerificationClassificationSetLocale;
    }

    public synchronized int speakerVerificationClassificationUnloadProfile(byte[] bArr) {
        int nativeSpeakerVerificationClassificationUnloadProfile;
        Log.i(TAG, "Unloading Speaker Verification Voice Profile...");
        nativeSpeakerVerificationClassificationUnloadProfile = nativeSpeakerVerificationClassificationUnloadProfile(this.nativeMem, bArr);
        if (nativeSpeakerVerificationClassificationUnloadProfile != 0) {
            String str = TAG;
            Log.e(str, "speakerVerificationClassificationUnloadProfile returned error = " + nativeSpeakerVerificationClassificationUnloadProfile);
        }
        return nativeSpeakerVerificationClassificationUnloadProfile;
    }

    public synchronized int speakerVerificationEnrollmentCreateSession(byte[] bArr) {
        int nativeSpeakerVerificationEnrollmentCreateSession;
        Log.i(TAG, "Creating Speaker Verification Enrollment Session...");
        nativeSpeakerVerificationEnrollmentCreateSession = nativeSpeakerVerificationEnrollmentCreateSession(this.nativeMem, bArr);
        if (nativeSpeakerVerificationEnrollmentCreateSession != 0) {
            String str = TAG;
            Log.e(str, "speakerVerificationEnrollmentCreateSession returned error = " + nativeSpeakerVerificationEnrollmentCreateSession);
        }
        return nativeSpeakerVerificationEnrollmentCreateSession;
    }

    public synchronized int speakerVerificationEnrollmentDestroySession(byte[] bArr) {
        int nativeSpeakerVerificationEnrollmentDestroySession;
        Log.i(TAG, "Destroying Speaker Verification Enrollment Session...");
        nativeSpeakerVerificationEnrollmentDestroySession = nativeSpeakerVerificationEnrollmentDestroySession(this.nativeMem, bArr);
        if (nativeSpeakerVerificationEnrollmentDestroySession != 0) {
            String str = TAG;
            Log.e(str, "speakerVerificationEnrollmentDestroySession returned error = " + nativeSpeakerVerificationEnrollmentDestroySession);
        }
        return nativeSpeakerVerificationEnrollmentDestroySession;
    }

    public synchronized int speakerVerificationEnrollmentDisable(byte[] bArr) {
        int nativeSpeakerVerificationEnrollmentDisable;
        Log.i(TAG, "Disabling Speaker Verification Enrollment Session...");
        nativeSpeakerVerificationEnrollmentDisable = nativeSpeakerVerificationEnrollmentDisable(this.nativeMem, bArr);
        if (nativeSpeakerVerificationEnrollmentDisable != 0) {
            String str = TAG;
            Log.e(str, "speakerVerificationEnrollmentEnable returned error = " + nativeSpeakerVerificationEnrollmentDisable);
        }
        return nativeSpeakerVerificationEnrollmentDisable;
    }

    public synchronized int speakerVerificationEnrollmentEnable(byte[] bArr) {
        int nativeSpeakerVerificationEnrollmentEnable;
        Log.i(TAG, "Enabling Speaker Verification Enrollment Session...");
        nativeSpeakerVerificationEnrollmentEnable = nativeSpeakerVerificationEnrollmentEnable(this.nativeMem, bArr);
        if (nativeSpeakerVerificationEnrollmentEnable != 0) {
            String str = TAG;
            Log.e(str, "speakerVerificationEnrollmentEnable returned error = " + nativeSpeakerVerificationEnrollmentEnable);
        }
        return nativeSpeakerVerificationEnrollmentEnable;
    }

    public synchronized int speakerVerificationEnrollmentPushExample(String str, int i, int i2, short[] sArr, byte[] bArr) {
        int nativeSpeakerVerificationEnrollmentPushExample;
        Log.i(TAG, "Pushing Example for Speaker Verification Enrollment...");
        nativeSpeakerVerificationEnrollmentPushExample = nativeSpeakerVerificationEnrollmentPushExample(this.nativeMem, str, i, i2, sArr, bArr);
        if (nativeSpeakerVerificationEnrollmentPushExample != 0) {
            String str2 = TAG;
            Log.e(str2, "speakerVerificationEnrollmentPushExample returned error = " + nativeSpeakerVerificationEnrollmentPushExample);
        }
        return nativeSpeakerVerificationEnrollmentPushExample;
    }

    public byte[] speakerVerificationGetModelIdFromProfile(byte[] bArr) {
        Log.i(TAG, "Retrieving Speaker Verification Model ID from Voice Profile...");
        byte[] nativeSpeakerVerificationGetModelIdFromProfile = nativeSpeakerVerificationGetModelIdFromProfile(bArr);
        if (nativeSpeakerVerificationGetModelIdFromProfile == null) {
            Log.e(TAG, "speakerVerificationGetModelIdFromProfile failed");
        }
        return nativeSpeakerVerificationGetModelIdFromProfile;
    }

    public byte[] speakerVerificationGetProfileIdFromProfile(byte[] bArr) {
        Log.i(TAG, "Retrieving Speaker Verification Profile ID from Voice Profile...");
        byte[] nativeSpeakerVerificationGetProfileIdFromProfile = nativeSpeakerVerificationGetProfileIdFromProfile(bArr);
        if (nativeSpeakerVerificationGetProfileIdFromProfile == null) {
            Log.e(TAG, "speakerVerificationGetProfileIdFromProfile failed");
        }
        return nativeSpeakerVerificationGetProfileIdFromProfile;
    }

    public synchronized int wakewordEnableKeyword(String str, boolean z) {
        int nativeWakewordEnableKeyword;
        String str2 = TAG;
        Log.i(str2, "PryonLite wakewordEnableKeyword invoked, wakeword = " + str + ", enable = " + z);
        nativeWakewordEnableKeyword = nativeWakewordEnableKeyword(this.nativeMem, str, z ? 1 : 0);
        if (nativeWakewordEnableKeyword != 0) {
            String str3 = TAG;
            Log.e(str3, "wakewordEnableKeywordd returned error = " + nativeWakewordEnableKeyword);
        }
        return nativeWakewordEnableKeyword;
    }

    public synchronized int wakewordSetDetectionThreshold(int i) {
        int nativeWakewordSetDetectionThreshold;
        String str = TAG;
        Log.i(str, "PryonLite wakewordSetDetectionThreshold invoked, threshold = " + i);
        nativeWakewordSetDetectionThreshold = nativeWakewordSetDetectionThreshold(this.nativeMem, i);
        if (nativeWakewordSetDetectionThreshold != 0) {
            String str2 = TAG;
            Log.e(str2, "wakewordSetDetectionThreshold returned error = " + nativeWakewordSetDetectionThreshold);
        }
        return nativeWakewordSetDetectionThreshold;
    }
}
