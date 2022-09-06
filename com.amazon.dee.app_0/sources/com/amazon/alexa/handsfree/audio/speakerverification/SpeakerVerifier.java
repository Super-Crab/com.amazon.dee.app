package com.amazon.alexa.handsfree.audio.speakerverification;

import android.media.AudioManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.audio.AlexaAudioSinkWrapper;
import com.amazon.alexa.handsfree.audio.metrics.AudioMetricsReporter;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.wakeword.davs.ArtifactModel;
import com.amazon.alexa.wakeword.pryon.AudioPlaybackConfigurationHelper;
import com.amazon.alexa.wakeword.speakerverification.profile.ProfileContentProviderHelper;
import com.amazon.alexa.wakeword.speakerverification.pryon.SpeakerVerificationConfigProvider;
import com.amazon.pryon.android.asr.PryonLite5000;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Map;
/* loaded from: classes8.dex */
public class SpeakerVerifier {
    @VisibleForTesting
    static final int AUDIO_SAMPLES_PER_MILLI_SECOND = 16;
    @VisibleForTesting
    static final String DEFAULT_LOCALE_FOR_SPEAKER_VERIFICATION = "en-US";
    @VisibleForTesting
    static final int ENGINE_INITIALIZED_STATE = 1;
    @VisibleForTesting
    static final int MAX_READ_AUDIO_MILLI_SECONDS = 3000;
    @VisibleForTesting
    static final int PRYON_METHOD_SUCCESS_RETURN_CODE = 0;
    private static final int SV_ENROLLMENT_EXAMPLES = 4;
    @VisibleForTesting
    static final String TAG = "SpeakerVerifier";
    private final AudioPlaybackConfigurationHelper mAudioPlaybackConfigurationHelper;
    private byte[] mFrameBytes;
    private byte[] mFrameBytesPrev;
    private short[] mFrameShorts;
    private boolean mIsReadingAudio;
    private final AudioMetricsReporter mMetricsReporter;
    private final AlexaAudioSinkWrapper mOriginalAudioSinkWrapper;
    private final ProfileContentProviderHelper mProfileContentProviderHelper;
    private PryonLite5000 mPryonLite;
    private long mPushedFrameCount;
    private boolean mReceivedWakewordDetectedEvent = false;
    private SpeakerVerificationClassificationData mSpeakerVerificationClassificationData;
    private int mTotalFramesToPush;
    private VerificationCallbacks mVerificationCallbacks;
    private final AlexaAudioSinkWrapper mVerifiedAudioSinkWrapper;
    private final WakeWordModelProvider mWakeWordModelProvider;

    public SpeakerVerifier(@NonNull WakeWordModelProvider wakeWordModelProvider, @NonNull ProfileContentProviderHelper profileContentProviderHelper, @NonNull VerificationCallbacks verificationCallbacks, @NonNull AlexaAudioSinkWrapper alexaAudioSinkWrapper, @NonNull AlexaAudioSinkWrapper alexaAudioSinkWrapper2, @NonNull AudioMetricsReporter audioMetricsReporter, @NonNull AudioPlaybackConfigurationHelper audioPlaybackConfigurationHelper) {
        this.mWakeWordModelProvider = wakeWordModelProvider;
        this.mProfileContentProviderHelper = profileContentProviderHelper;
        this.mVerificationCallbacks = verificationCallbacks;
        this.mOriginalAudioSinkWrapper = alexaAudioSinkWrapper;
        this.mVerifiedAudioSinkWrapper = alexaAudioSinkWrapper2;
        this.mMetricsReporter = audioMetricsReporter;
        this.mAudioPlaybackConfigurationHelper = audioPlaybackConfigurationHelper;
    }

    @NonNull
    private short[] convertBytesToShorts(@NonNull byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.asShortBuffer().get(this.mFrameShorts);
        return this.mFrameShorts;
    }

    @NonNull
    private PryonLite5000.Callbacks createPryonCallbacks() {
        return new PryonLite5000.Callbacks() { // from class: com.amazon.alexa.handsfree.audio.speakerverification.SpeakerVerifier.1
            @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
            public void errorEvent(int i) {
                String str = SpeakerVerifier.TAG;
                Log.e(str, "errorEvent from PryonLite callback. Error code: " + i);
            }

            @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
            public void speakerVerificationClassificationEvent(byte[] bArr, int i, byte[] bArr2, float f, float f2, float f3, int i2) {
                SpeakerVerifier.this.mSpeakerVerificationClassificationData = new SpeakerVerificationClassificationData(i, bArr, bArr2, f, f2, f3, i2);
                Log.d(SpeakerVerifier.TAG, SpeakerVerifier.this.mSpeakerVerificationClassificationData.toString());
            }

            @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
            public void speakerVerificationEnrollmentEvent(byte[] bArr, int i, byte[] bArr2, byte[] bArr3) {
                Log.w(SpeakerVerifier.TAG, "speakerVerificationEnrollmentEvent called unexpected in classification flow.");
            }

            @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
            public void speakerVerificationWakewordExampleEvent(String str, int i, int i2, short[] sArr, byte[] bArr) {
                Log.d(SpeakerVerifier.TAG, "speakerVerificationWakewordExampleEvent called. ignore in classification flow.");
            }

            @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
            public void vadStateChanged(int i) {
                String str = SpeakerVerifier.TAG;
                Log.w(str, "vadStateChanged called unexpectedly in classification flow. state: " + i);
            }

            @Override // com.amazon.pryon.android.asr.PryonLite5000.Callbacks
            public void wakeWordDetected(String str, long j, long j2, byte[] bArr) {
                SpeakerVerifier.this.mReceivedWakewordDetectedEvent = true;
                String str2 = SpeakerVerifier.TAG;
                StringBuilder outline111 = GeneratedOutlineSupport1.outline111("Wakeword detected between ", j, " and ");
                outline111.append(j2);
                outline111.append(", duration = ");
                outline111.append((j2 - j) / 16);
                outline111.append(" ms");
                Log.d(str2, outline111.toString());
                SpeakerVerifier.this.mVerificationCallbacks.onWakewordDetected(SpeakerVerifier.this.mSpeakerVerificationClassificationData, new WakeWordData(str, j, j2, bArr));
            }
        };
    }

    @NonNull
    private byte[] getProfile() {
        byte[] profileAsBytes = this.mProfileContentProviderHelper.getProfileAsBytes();
        if (profileAsBytes != null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("retrieved profile. size: ");
            outline107.append(profileAsBytes.length);
            Log.i(str, outline107.toString());
            return profileAsBytes;
        }
        this.mMetricsReporter.sendProfileNull(TAG);
        throw new IllegalStateException("profile is null");
    }

    @NonNull
    private byte[] getSVModel(@NonNull ArtifactModel artifactModel) {
        byte[] artifactData = artifactModel.getArtifactData();
        if (artifactData != null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("retrieved speaker verification model. size: ");
            outline107.append(artifactData.length);
            Log.i(str, outline107.toString());
            return artifactData;
        }
        this.mMetricsReporter.sendSVModelNull(TAG);
        throw new IllegalStateException("speaker verification model is null");
    }

    @Nullable
    private String getSVModelLocale(@NonNull ArtifactModel artifactModel) {
        String locale = artifactModel.getLocale();
        if (locale == null) {
            this.mMetricsReporter.sendSVModelLocaleNull(TAG);
            Log.i(TAG, "locale for SV model is null.");
        } else {
            String str = TAG;
            Log.i(str, "locale for SV model is: " + locale);
        }
        return locale;
    }

    @NonNull
    private byte[] getWakewordModel() {
        byte[] modelAsBytes = this.mWakeWordModelProvider.getModelAsBytes();
        if (modelAsBytes != null) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("retrieved wake word model. size: ");
            outline107.append(modelAsBytes.length);
            Log.i(str, outline107.toString());
            return modelAsBytes;
        }
        this.mMetricsReporter.sendWWModelNull(TAG);
        throw new IllegalStateException("wake word model is null.");
    }

    @NonNull
    private String getWakewordModelLocale() {
        String modelLocale = this.mWakeWordModelProvider.getModelLocale();
        if (modelLocale != null) {
            String str = TAG;
            Log.i(str, "retrieved wake word model locale: " + modelLocale);
            return modelLocale;
        }
        this.mMetricsReporter.sendWWModelLocaleNull(TAG);
        throw new IllegalStateException("locale for wake word model is null.");
    }

    private boolean hasPushedEnoughAudio() {
        return this.mPushedFrameCount >= ((long) this.mTotalFramesToPush);
    }

    private boolean isPryonInitialized() {
        PryonLite5000 pryonLite5000 = this.mPryonLite;
        return pryonLite5000 != null && pryonLite5000.isInitialized() == 1;
    }

    private int pushToPryon(boolean z) {
        if (z) {
            return this.mPryonLite.pushAudio(convertBytesToShorts(this.mFrameBytesPrev));
        }
        return this.mPryonLite.pushAudio(convertBytesToShorts(this.mFrameBytes));
    }

    private int transferData(@NonNull InputStream inputStream, boolean z) throws IOException {
        if (z) {
            return inputStream.read(this.mFrameBytesPrev);
        }
        int read = inputStream.read(this.mFrameBytes);
        if (read <= 0) {
            return read;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(this.mFrameBytesPrev);
        byteArrayOutputStream.write(this.mFrameBytes);
        this.mVerifiedAudioSinkWrapper.recordAudio(byteArrayOutputStream.toByteArray(), read * 2);
        return read;
    }

    @NonNull
    @VisibleForTesting
    PryonLite5000 createPryonEngine(@NonNull PryonLite5000.Callbacks callbacks) {
        return new PryonLite5000(callbacks);
    }

    public void destroy() {
        if (this.mPryonLite != null) {
            Log.i(TAG, "destroying pryonLite.");
            this.mPryonLite.destroy();
            this.mPryonLite = null;
            this.mVerificationCallbacks = null;
            this.mFrameBytes = null;
            this.mFrameShorts = null;
        }
    }

    public void initialize(@NonNull ArtifactModel artifactModel, @NonNull AudioManager audioManager) {
        Log.i(TAG, "initialize SpeakerVerifier.");
        if (isPryonLiteLibraryLoaded()) {
            this.mPushedFrameCount = 0L;
            this.mSpeakerVerificationClassificationData = null;
            if (isPryonInitialized()) {
                Log.w(TAG, "SpeakerVerifier already initialized.");
                return;
            }
            String wakewordModelLocale = getWakewordModelLocale();
            byte[] wakewordModel = getWakewordModel();
            String sVModelLocale = getSVModelLocale(artifactModel);
            byte[] sVModel = getSVModel(artifactModel);
            byte[] profile = getProfile();
            if (!wakewordModelLocale.equals(sVModelLocale)) {
                Log.e(TAG, "locale for wakeword model and sv model doesn't match.");
                this.mMetricsReporter.sendLocaleMismatchMetric(TAG);
            }
            this.mPryonLite = createPryonEngine(createPryonCallbacks());
            PryonLite5000.Config createPryonConfig = SpeakerVerificationConfigProvider.createPryonConfig(wakewordModel, sVModel, 4);
            int initialize = this.mPryonLite.initialize(createPryonConfig);
            if (initialize == 0) {
                Log.i(TAG, "PryonLite engine initialized.");
                for (Map.Entry<PryonLite5000.ClientProperty, Integer> entry : this.mAudioPlaybackConfigurationHelper.getClientPropertiesMap(audioManager).entrySet()) {
                    String str = TAG;
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Set client property | group id: ");
                    outline107.append(entry.getKey().groupId);
                    outline107.append(" property id: ");
                    outline107.append(entry.getKey().propertyId);
                    outline107.append(" | clientPropertyState: ");
                    outline107.append(entry.getValue());
                    Log.i(str, outline107.toString());
                    this.mPryonLite.setClientProperty(entry.getKey(), entry.getValue().intValue());
                }
                int samplesPerFrame = this.mPryonLite.getSamplesPerFrame();
                if (samplesPerFrame > 0) {
                    String str2 = TAG;
                    Log.i(str2, "get frameSize: " + samplesPerFrame);
                    this.mTotalFramesToPush = 48000 / samplesPerFrame;
                    this.mFrameShorts = new short[samplesPerFrame];
                    int i = samplesPerFrame * 2;
                    this.mFrameBytesPrev = new byte[i];
                    this.mFrameBytes = new byte[i];
                    if (!this.mPryonLite.getAttributes(createPryonConfig).speakerVerificationLocales.contains(wakewordModelLocale)) {
                        Log.w(TAG, "current locale is not supported by pryon for speaker verification. use default locale.");
                        this.mMetricsReporter.sendPryonNotSupportwwLocale(TAG);
                        wakewordModelLocale = DEFAULT_LOCALE_FOR_SPEAKER_VERIFICATION;
                    }
                    int speakerVerificationClassificationSetLocale = this.mPryonLite.speakerVerificationClassificationSetLocale(wakewordModelLocale);
                    if (speakerVerificationClassificationSetLocale == 0) {
                        String str3 = TAG;
                        Log.i(str3, "set speakerVerificationClassificationSetLocale to: " + wakewordModelLocale);
                        int speakerVerificationClassificationLoadProfile = this.mPryonLite.speakerVerificationClassificationLoadProfile(profile);
                        if (speakerVerificationClassificationLoadProfile == 0) {
                            Log.i(TAG, "speakerVerificationClassificationLoadProfile success.");
                            this.mIsReadingAudio = true;
                            return;
                        }
                        String str4 = TAG;
                        Log.e(str4, "failed to speakerVerificationClassificationLoadProfile, status: " + speakerVerificationClassificationLoadProfile);
                        throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Failed to load profile. status: ", speakerVerificationClassificationLoadProfile));
                    }
                    String str5 = TAG;
                    Log.e(str5, "failed to speakerVerificationClassificationSetLocale, status: " + speakerVerificationClassificationSetLocale);
                    throw new IllegalStateException("Failed to speakerVerificationClassificationSetLocale");
                }
                throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Illegal frame size from pryon: ", samplesPerFrame));
            }
            String str6 = TAG;
            Log.e(str6, "failed to initialize PryonLite engine. status: " + initialize);
            throw new IllegalStateException(GeneratedOutlineSupport1.outline49("Failed to initialize PryonLite. status: ", initialize));
        }
        Log.e(TAG, "Cannot load PryonLite5000 native library.");
        throw new IllegalStateException("PryonLite5000 native library not loaded.");
    }

    @VisibleForTesting
    boolean isPryonLiteLibraryLoaded() {
        return PryonLite5000.libraryIsLoaded();
    }

    public void stopAudioReading() {
        this.mIsReadingAudio = false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x00b7, code lost:
        if (r0 == null) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x00b9, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00bc, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:?, code lost:
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void verify() throws java.io.IOException {
        /*
            r6 = this;
            boolean r0 = r6.isPryonInitialized()
            if (r0 == 0) goto Lcb
            com.amazon.alexa.handsfree.audio.AlexaAudioSinkWrapper r0 = r6.mOriginalAudioSinkWrapper
            com.amazon.alexa.handsfree.audio.ReadableAlexaAudioSink r0 = r0.getReadableAlexaAudioSink()
            java.io.InputStream r0 = r0.openForReading()
            java.lang.String r1 = com.amazon.alexa.handsfree.audio.speakerverification.SpeakerVerifier.TAG     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r2 = "Successfully opened AlexaAudioSink for reading"
            com.amazon.alexa.handsfree.protocols.utils.Log.d(r1, r2)     // Catch: java.lang.Throwable -> Lbd
            r1 = 1
        L18:
            boolean r2 = r6.mIsReadingAudio     // Catch: java.lang.Throwable -> Lbd
            if (r2 == 0) goto Lb7
            int r2 = r6.transferData(r0, r1)     // Catch: java.lang.Throwable -> Lbd
            if (r2 > 0) goto L3a
            java.lang.String r1 = com.amazon.alexa.handsfree.audio.speakerverification.SpeakerVerifier.TAG     // Catch: java.lang.Throwable -> Lbd
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbd
            r3.<init>()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r4 = "Bytes read is "
            r3.append(r4)     // Catch: java.lang.Throwable -> Lbd
            r3.append(r2)     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r2 = r3.toString()     // Catch: java.lang.Throwable -> Lbd
            com.amazon.alexa.handsfree.protocols.utils.Log.d(r1, r2)     // Catch: java.lang.Throwable -> Lbd
            goto Lb7
        L3a:
            byte[] r3 = r6.mFrameBytes     // Catch: java.lang.Throwable -> Lbd
            int r3 = r3.length     // Catch: java.lang.Throwable -> Lbd
            if (r2 != r3) goto Laf
            boolean r2 = r6.mReceivedWakewordDetectedEvent     // Catch: java.lang.Throwable -> Lbd
            if (r2 == 0) goto L46
        L43:
            r1 = r1 ^ 1
            goto L18
        L46:
            boolean r2 = r6.hasPushedEnoughAudio()     // Catch: java.lang.Throwable -> Lbd
            if (r2 != 0) goto L8e
            int r2 = r6.pushToPryon(r1)     // Catch: java.lang.Throwable -> Lbd
            if (r2 != 0) goto L5a
            long r2 = r6.mPushedFrameCount     // Catch: java.lang.Throwable -> Lbd
            r4 = 1
            long r2 = r2 + r4
            r6.mPushedFrameCount = r2     // Catch: java.lang.Throwable -> Lbd
            goto L43
        L5a:
            java.lang.String r1 = com.amazon.alexa.handsfree.audio.speakerverification.SpeakerVerifier.TAG     // Catch: java.lang.Throwable -> Lbd
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbd
            r3.<init>()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r4 = "pryonLite pushAudio failed! status = "
            r3.append(r4)     // Catch: java.lang.Throwable -> Lbd
            r3.append(r2)     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lbd
            com.amazon.alexa.handsfree.protocols.utils.Log.e(r1, r3)     // Catch: java.lang.Throwable -> Lbd
            com.amazon.alexa.handsfree.audio.metrics.AudioMetricsReporter r1 = r6.mMetricsReporter     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r3 = com.amazon.alexa.handsfree.audio.speakerverification.SpeakerVerifier.TAG     // Catch: java.lang.Throwable -> Lbd
            r1.sendPryonlitePushAudioFailed(r3)     // Catch: java.lang.Throwable -> Lbd
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> Lbd
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbd
            r3.<init>()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r4 = "pryonLite engine pushAudio failure, return code: "
            r3.append(r4)     // Catch: java.lang.Throwable -> Lbd
            r3.append(r2)     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r2 = r3.toString()     // Catch: java.lang.Throwable -> Lbd
            r1.<init>(r2)     // Catch: java.lang.Throwable -> Lbd
            throw r1     // Catch: java.lang.Throwable -> Lbd
        L8e:
            java.lang.String r1 = com.amazon.alexa.handsfree.audio.speakerverification.SpeakerVerifier.TAG     // Catch: java.lang.Throwable -> Lbd
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbd
            r2.<init>()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r3 = "reached limit for total frames to push: "
            r2.append(r3)     // Catch: java.lang.Throwable -> Lbd
            long r3 = r6.mPushedFrameCount     // Catch: java.lang.Throwable -> Lbd
            r2.append(r3)     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> Lbd
            com.amazon.alexa.handsfree.protocols.utils.Log.i(r1, r2)     // Catch: java.lang.Throwable -> Lbd
            com.amazon.alexa.handsfree.audio.speakerverification.VerificationCallbacks r1 = r6.mVerificationCallbacks     // Catch: java.lang.Throwable -> Lbd
            r1.onWakewordNotDetected()     // Catch: java.lang.Throwable -> Lbd
            r0.close()
            return
        Laf:
            java.io.IOException r1 = new java.io.IOException     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r2 = "under read data from input stream"
            r1.<init>(r2)     // Catch: java.lang.Throwable -> Lbd
            throw r1     // Catch: java.lang.Throwable -> Lbd
        Lb7:
            if (r0 == 0) goto Lbc
            r0.close()
        Lbc:
            return
        Lbd:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> Lbf
        Lbf:
            r2 = move-exception
            if (r0 == 0) goto Lca
            r0.close()     // Catch: java.lang.Throwable -> Lc6
            goto Lca
        Lc6:
            r0 = move-exception
            r1.addSuppressed(r0)
        Lca:
            throw r2
        Lcb:
            java.lang.String r0 = com.amazon.alexa.handsfree.audio.speakerverification.SpeakerVerifier.TAG
            java.lang.String r1 = "pryonLite engine not initialized"
            com.amazon.alexa.handsfree.protocols.utils.Log.e(r0, r1)
            com.amazon.alexa.handsfree.audio.metrics.AudioMetricsReporter r0 = r6.mMetricsReporter
            java.lang.String r2 = com.amazon.alexa.handsfree.audio.speakerverification.SpeakerVerifier.TAG
            r0.sendPryonliteInitFailed(r2)
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.handsfree.audio.speakerverification.SpeakerVerifier.verify():void");
    }
}
