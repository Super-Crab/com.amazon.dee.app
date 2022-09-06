package com.amazon.alexa.accessory.avsclient.nearmiss;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import com.amazon.alexa.accessory.avsclient.nearmiss.DefaultNearMissSpeechSession;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSession;
import com.amazon.alexa.accessory.capabilities.speech.SpeechSettings;
import com.amazon.alexa.accessory.internal.util.IOUtils;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.ByteArraySource;
import com.amazon.alexa.accessory.io.Pipe;
import com.amazon.alexa.accessory.io.Sink;
import com.amazon.alexa.accessory.nearmiss.Audio;
import com.amazon.alexa.accessory.nearmiss.AudioContentType;
import com.amazon.alexa.accessory.nearmiss.Data;
import com.amazon.alexa.accessory.nearmiss.DataPart;
import com.amazon.alexa.accessory.nearmiss.Format;
import com.amazon.alexa.accessory.nearmiss.MetaData;
import com.amazon.alexa.accessory.nearmiss.MetaDataPart;
import com.amazon.alexa.accessory.nearmiss.MetaDataPayload;
import com.amazon.alexa.accessory.nearmiss.MlisClient;
import com.amazon.alexa.accessory.nearmiss.NearMissManifest;
import com.amazon.alexa.accessory.nearmiss.Parts;
import com.amazon.alexa.accessory.protocol.Speech;
import com.amazon.alexa.accessory.registration.DeviceRegistration;
import com.amazon.alexa.accessory.registration.RegistrationSupplier;
import com.amazon.alexa.comms.mediaInsights.Trace;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class DefaultNearMissSpeechSession implements NearMissSpeechSession {
    private static final long ENDPOINT_DELAY = 3500;
    private final List<SpeechSession.SpeechSessionCallback> callbackList;
    private Handler handler;
    private final MlisClient mlisClient;
    private final Pipe pipe;
    private final RegistrationSupplier registrationSupplier;
    private boolean released;
    private final SpeechSettings settings;

    /* loaded from: classes.dex */
    public static final class Builder {
        private MlisClient mlisClient;
        private RegistrationSupplier registrationSupplier;
        private SpeechSettings settings;

        public DefaultNearMissSpeechSession build() {
            Preconditions.notNull(this.mlisClient, "mlisHttpClient");
            Preconditions.notNull(this.settings, "settings");
            Preconditions.notNull(this.registrationSupplier, "registrationSupplier");
            return new DefaultNearMissSpeechSession(this);
        }

        public Builder setMlisClient(MlisClient mlisClient) {
            this.mlisClient = mlisClient;
            return this;
        }

        public Builder setRegistrationSupplier(RegistrationSupplier registrationSupplier) {
            this.registrationSupplier = registrationSupplier;
            return this;
        }

        public Builder setSettings(SpeechSettings speechSettings) {
            this.settings = speechSettings;
            return this;
        }

        private Builder() {
        }
    }

    /* loaded from: classes.dex */
    public static final class NearMissSpeechDeviceInfo {
        private String deviceSerialNumber;
        private String deviceType;

        public NearMissSpeechDeviceInfo(String str, String str2) {
            this.deviceType = str;
            this.deviceSerialNumber = str2;
        }

        public String getDeviceSerialNumber() {
            return this.deviceSerialNumber;
        }

        public String getDeviceType() {
            return this.deviceType;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: createNearMissManifest */
    public NearMissManifest lambda$start$0$DefaultNearMissSpeechSession(Speech.SpeechInitiator.WakeWord wakeWord, int i, NearMissSpeechDeviceInfo nearMissSpeechDeviceInfo) {
        return new NearMissManifest(1, NearMissManifest.UploadType.WAKE_WORD_NEAR_MISS, new Parts.Builder().audio(new Audio.Builder().data(new Data.Builder().dataPart(new DataPart.Builder().type("AUDIO").audioContentType(new AudioContentType.Builder().name("audio/x-cbr-opus-with-preamble").rate(16000).preambleSize(wakeWord.getMetadata().toByteArray().length).bitRate(i).frameSize(20).build()).id(2).build()).payload(this.pipe).build()).metaData(new MetaData.Builder().metadataPart(new MetaDataPart.Builder().type("JSON").format(new Format.Builder().version(1).name(Format.ALEXA_WWNM_METADATA).build()).id(4).build()).payload(new MetaDataPayload.Builder().appVersion(Build.VERSION.RELEASE).locale(this.settings.getLocale()).deviceType(nearMissSpeechDeviceInfo.getDeviceType()).deviceSerialNumber(nearMissSpeechDeviceInfo.getDeviceSerialNumber()).wwStartSeqnum(wakeWord.getStartIndexInSamples()).wwEndSeqnum(wakeWord.getEndIndexInSamples()).build()).build()).build()).build());
    }

    private Single<NearMissSpeechDeviceInfo> extractDeviceInfoFromRegistration() {
        final String identifier = this.settings.getAccessoryIdentifierProvider().getIdentifier();
        return this.registrationSupplier.getDeviceRegistration(identifier).map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.nearmiss.-$$Lambda$DefaultNearMissSpeechSession$YATGBWbjfqpMjzusAK76tOpojcI
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultNearMissSpeechSession.this.lambda$extractDeviceInfoFromRegistration$3$DefaultNearMissSpeechSession((DeviceRegistration) obj);
            }
        }).onErrorReturn(new Function() { // from class: com.amazon.alexa.accessory.avsclient.nearmiss.-$$Lambda$DefaultNearMissSpeechSession$yUGgvg4pUlaeTlzsmuwixIX4LSE
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return DefaultNearMissSpeechSession.this.lambda$extractDeviceInfoFromRegistration$4$DefaultNearMissSpeechSession(identifier, (Throwable) obj);
            }
        });
    }

    private Handler getMainThreadHandler() {
        Preconditions.mainThread();
        if (this.handler == null) {
            this.handler = new Handler();
        }
        return this.handler;
    }

    private void internalRelease() {
        synchronized (this.callbackList) {
            for (int size = this.callbackList.size() - 1; size >= 0; size--) {
                this.callbackList.get(size).onRelease(this);
            }
        }
        this.settings.getCallback().onSpeechCompleted();
        IOUtils.closeQuietly(this.pipe);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public void abort() {
        release();
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public void addCallback(SpeechSession.SpeechSessionCallback speechSessionCallback) {
        synchronized (this.callbackList) {
            this.callbackList.add(speechSessionCallback);
        }
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public void endpointSpeech() {
        Preconditions.mainThread();
        if (this.released) {
            return;
        }
        this.released = true;
        Logger.d("NearMissSpeechSession endpointed.");
        synchronized (this.callbackList) {
            for (int size = this.callbackList.size() - 1; size >= 0; size--) {
                this.callbackList.get(size).onEndpointSpeech(this);
            }
        }
        this.settings.getCallback().onSpeechRecognitionFinished();
        internalRelease();
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public Sink getSink() {
        return this.pipe;
    }

    public /* synthetic */ NearMissSpeechDeviceInfo lambda$extractDeviceInfoFromRegistration$3$DefaultNearMissSpeechSession(DeviceRegistration deviceRegistration) throws Throwable {
        String clusterDeviceType = deviceRegistration.getDeviceRegistrationResponse().getClusterDeviceType();
        if (clusterDeviceType == null) {
            clusterDeviceType = this.settings.getDeviceType();
        }
        return new NearMissSpeechDeviceInfo(clusterDeviceType, this.settings.getDeviceSerialNumber());
    }

    public /* synthetic */ NearMissSpeechDeviceInfo lambda$extractDeviceInfoFromRegistration$4$DefaultNearMissSpeechSession(String str, Throwable th) throws Throwable {
        Logger.d("NearMissSpeechSession: Unable to get registration for identifier: %s", str);
        return new NearMissSpeechDeviceInfo(this.settings.getDeviceType(), this.settings.getDeviceSerialNumber());
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public void release() {
        Preconditions.mainThread();
        if (this.released) {
            return;
        }
        this.released = true;
        internalRelease();
    }

    @Override // com.amazon.alexa.accessory.capabilities.speech.SpeechSession
    public void removeCallback(SpeechSession.SpeechSessionCallback speechSessionCallback) {
        synchronized (this.callbackList) {
            this.callbackList.remove(speechSessionCallback);
        }
    }

    @Override // com.amazon.alexa.accessory.avsclient.nearmiss.NearMissSpeechSession
    @SuppressLint({"CheckResult"})
    public void start() {
        final int i;
        Preconditions.mainThread();
        Logger.d("NearMissSpeechSession started");
        final Speech.SpeechInitiator.WakeWord wakeWord = this.settings.getInitiator().getWakeWord();
        try {
            IOUtils.transfer(new ByteArraySource(wakeWord.getMetadata().toByteArray()), this.pipe);
            if (this.settings.getAudioFormat() == Speech.AudioFormat.OPUS_16KHZ_16KBPS_CBR_0_20MS) {
                i = 16000;
            } else if (this.settings.getAudioFormat() != Speech.AudioFormat.OPUS_16KHZ_32KBPS_CBR_0_20MS) {
                Logger.e("Unsupported audio format %s. Aborting near miss.", this.settings.getAudioFormat());
                endpointSpeech();
                return;
            } else {
                i = Trace.PAYLOAD_DATA_MAX_LENGTH;
            }
            Single<R> map = extractDeviceInfoFromRegistration().map(new Function() { // from class: com.amazon.alexa.accessory.avsclient.nearmiss.-$$Lambda$DefaultNearMissSpeechSession$KcyiJzc9awSETTMSddzu2yl9iRQ
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return DefaultNearMissSpeechSession.this.lambda$start$0$DefaultNearMissSpeechSession(wakeWord, i, (DefaultNearMissSpeechSession.NearMissSpeechDeviceInfo) obj);
                }
            });
            final MlisClient mlisClient = this.mlisClient;
            mlisClient.getClass();
            map.flatMapCompletable(new Function() { // from class: com.amazon.alexa.accessory.avsclient.nearmiss.-$$Lambda$RsxbNUz0Ln-Q_aOeXi_hllQCHGE
                @Override // io.reactivex.rxjava3.functions.Function
                /* renamed from: apply */
                public final Object mo10358apply(Object obj) {
                    return MlisClient.this.upload((NearMissManifest) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).subscribe($$Lambda$DefaultNearMissSpeechSession$_TcNJQwEp1bOXpHTk0P8Stw5s7A.INSTANCE, $$Lambda$DefaultNearMissSpeechSession$6_I7e8d9KUs2Zkp4mYl6uTXM.INSTANCE);
            getMainThreadHandler().postDelayed(new Runnable() { // from class: com.amazon.alexa.accessory.avsclient.nearmiss.-$$Lambda$J6HLvtz_4n38ifOqQV5X1ncfGIc
                @Override // java.lang.Runnable
                public final void run() {
                    DefaultNearMissSpeechSession.this.endpointSpeech();
                }
            }, ENDPOINT_DELAY);
        } catch (IOException e) {
            Logger.e("Failed to prepend meta data to voice stream. Aborting near miss", e);
            endpointSpeech();
        }
    }

    private DefaultNearMissSpeechSession(Builder builder) {
        this.mlisClient = builder.mlisClient;
        this.settings = builder.settings;
        this.registrationSupplier = builder.registrationSupplier;
        this.callbackList = new ArrayList();
        this.pipe = new Pipe(10000, 15000L);
    }
}
