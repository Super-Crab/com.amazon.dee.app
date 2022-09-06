package com.amazon.alexa.accessory.speech;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.AccessorySession;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsConstants;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.metrics.AccessoryMetricsServiceHolder;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.repositories.state.StateFeature;
import com.amazon.alexa.accessory.speechapi.listeners.AudioPlaybackStatusListener;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Map;
/* loaded from: classes6.dex */
public class AccessoryAudioPlaybackStatusListener implements AudioPlaybackStatusListener {
    private static final String TAG = "speech.AccessoryAudioPlaybackStatusListener:";
    private String accessoryDeviceType;
    private int cachedAudioPlaybackStatus;
    private final AccessorySession session;

    public AccessoryAudioPlaybackStatusListener(AccessorySession accessorySession) {
        Preconditions.notNull(accessorySession, "session");
        this.session = accessorySession;
        this.cachedAudioPlaybackStatus = 0;
        this.accessoryDeviceType = "Unknown";
        initializeDeviceType();
    }

    private int convertAudioPlaybackStatusToNotify(Map<AudioPlaybackStatusListener.AudioType, Boolean> map) {
        int i = 0;
        for (Map.Entry<AudioPlaybackStatusListener.AudioType, Boolean> entry : map.entrySet()) {
            AudioPlaybackStatusListener.AudioType key = entry.getKey();
            boolean booleanValue = entry.getValue().booleanValue();
            if (AudioPlaybackStatusListener.AudioType.UNKNOWN == key) {
                AccessoryMetricsServiceHolder.getInstance().get().recordCounter(AccessoryMetricsConstants.UNKNOWN_AUDIO_TYPE, this.accessoryDeviceType, 1.0d, null);
            }
            if (booleanValue) {
                i |= 1 << key.getValue();
            }
        }
        return i;
    }

    @SuppressLint({"CheckResult"})
    private void initializeDeviceType() {
        this.session.getDeviceRepositoryV2().queryDeviceInformationSet().firstOrError().map($$Lambda$HenQHgTbazd1IYnDrhy_DQG1T8w.INSTANCE).map($$Lambda$fFtSfZI18QY7Io9iFa3QkWDvcnQ.INSTANCE).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryAudioPlaybackStatusListener$SXvfkuBuDe7ixfcIIEP8atlO6l4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryAudioPlaybackStatusListener.this.lambda$initializeDeviceType$0$AccessoryAudioPlaybackStatusListener((String) obj);
            }
        }, new Consumer() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryAudioPlaybackStatusListener$3kqTXdMt0SYLGxQkFynaNOnDRIg
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AccessoryAudioPlaybackStatusListener.this.lambda$initializeDeviceType$1$AccessoryAudioPlaybackStatusListener((Throwable) obj);
            }
        });
    }

    @SuppressLint({"CheckResult"})
    private void notifyAudioPlaybackStatus(int i) {
        this.session.getStateRepository().set(StateOuterClass.State.newBuilder().setFeature(StateFeature.AUDIO_PLAYBACK_STATUS.toInteger()).setInteger(i).mo10084build()).subscribe(new Action() { // from class: com.amazon.alexa.accessory.speech.-$$Lambda$AccessoryAudioPlaybackStatusListener$CT11_L9axvp9loW-3JDlc-neBJw
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                AccessoryAudioPlaybackStatusListener.this.lambda$notifyAudioPlaybackStatus$2$AccessoryAudioPlaybackStatusListener();
            }
        }, $$Lambda$AccessoryAudioPlaybackStatusListener$Cdisc7JjkFBUMJ9dmUoy1BswM.INSTANCE);
    }

    public /* synthetic */ void lambda$initializeDeviceType$0$AccessoryAudioPlaybackStatusListener(String str) throws Throwable {
        this.accessoryDeviceType = str;
    }

    public /* synthetic */ void lambda$initializeDeviceType$1$AccessoryAudioPlaybackStatusListener(Throwable th) throws Throwable {
        this.accessoryDeviceType = "Unknown";
    }

    public /* synthetic */ void lambda$notifyAudioPlaybackStatus$2$AccessoryAudioPlaybackStatusListener() throws Throwable {
        Logger.d("%s dispatched AudioPlaybackStatus to session %s", TAG, this.session.getAccessory());
    }

    @Override // com.amazon.alexa.accessory.speechapi.listeners.AudioPlaybackStatusListener
    public void onAudioPlaybackStatusChanged(@NonNull Map<AudioPlaybackStatusListener.AudioType, Boolean> map) {
        int convertAudioPlaybackStatusToNotify = convertAudioPlaybackStatusToNotify(map);
        if (this.cachedAudioPlaybackStatus == convertAudioPlaybackStatusToNotify) {
            Logger.d("%s AudioPlaybackStatus (%d) is not changed, not notifying.", TAG, Integer.valueOf(convertAudioPlaybackStatusToNotify));
            return;
        }
        this.cachedAudioPlaybackStatus = convertAudioPlaybackStatusToNotify;
        Logger.d("%s notifying AudioPlaybackStatus= %d", TAG, Integer.valueOf(convertAudioPlaybackStatusToNotify));
        notifyAudioPlaybackStatus(convertAudioPlaybackStatusToNotify);
    }
}
