package com.amazon.deecomms.messaging.controller;

import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.messaging.model.client.ClientMessageIdentifier;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import java.util.Observable;
/* loaded from: classes12.dex */
public class AudioStateManager {
    public static final int AUDIO_MESSAGE_DOWNLOAD_FAILED_STATE = 4;
    public static final int AUDIO_MESSAGE_SEND_FAILED_STATE = 3;
    public static final int DEFAULT_AUDIO_STATE = 2;
    public static final int DOWNLOAD_AUDIO_STATE = 0;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AudioStateManager.class);
    public static final int PLAY_AUDIO_STATE = 1;
    private Map<ClientMessageIdentifier, Integer> mAudioMessageDownloadStateMap = GeneratedOutlineSupport1.outline136();
    private ClientMessageIdentifier currentlyPlayingClientMessageIdentifier = null;
    private ClientMessageIdentifier previousPlayingClientMessageIdentifier = null;
    private AudioStateChangesObservable mAudioStateChangesObservable = new AudioStateChangesObservable(null);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public final class AudioStateChangesObservable extends Observable {
        private AudioStateChangesObservable() {
        }

        public void notifyUpdate(ClientMessageIdentifier clientMessageIdentifier) {
            if (clientMessageIdentifier != null) {
                CommsLogger commsLogger = AudioStateManager.LOG;
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("Notifying UI updation for ");
                outline1.append(AudioStateManager.LOG.sensitive(Long.toString(clientMessageIdentifier.getMessageID())));
                commsLogger.i(outline1.toString());
                setChanged();
                notifyObservers(clientMessageIdentifier);
            }
        }

        /* synthetic */ AudioStateChangesObservable(AnonymousClass1 anonymousClass1) {
        }
    }

    private static void recordClickMetric(String str, ClientMessageIdentifier clientMessageIdentifier) {
        CounterMetric generateClickstream = CounterMetric.generateClickstream(str);
        generateClickstream.getMetadata().put(MetricKeys.META_COMMS_ITEM_ID, Long.valueOf(clientMessageIdentifier.getMessageID()));
        MetricsHelper.recordSingleOccurrence(generateClickstream);
    }

    public synchronized int getAudioMessageState(ClientMessageIdentifier clientMessageIdentifier) {
        if (this.mAudioMessageDownloadStateMap.containsKey(clientMessageIdentifier)) {
            return this.mAudioMessageDownloadStateMap.get(clientMessageIdentifier).intValue();
        }
        return (clientMessageIdentifier == null || !clientMessageIdentifier.equals(getCurrentlyPlayingMessage())) ? 2 : 1;
    }

    public ClientMessageIdentifier getCurrentlyPlayingClientMessageIdentifier() {
        return this.currentlyPlayingClientMessageIdentifier;
    }

    public ClientMessageIdentifier getCurrentlyPlayingMessage() {
        return this.currentlyPlayingClientMessageIdentifier;
    }

    public synchronized void setAudioMessageState(ClientMessageIdentifier clientMessageIdentifier, int i) {
        setAudioMessageState(clientMessageIdentifier, i, false);
    }

    public synchronized void setAudioMessageState(ClientMessageIdentifier clientMessageIdentifier, int i, boolean z) {
        if (i == 0) {
            this.mAudioMessageDownloadStateMap.put(clientMessageIdentifier, 0);
        } else if (i == 1) {
            this.previousPlayingClientMessageIdentifier = this.currentlyPlayingClientMessageIdentifier;
            this.currentlyPlayingClientMessageIdentifier = clientMessageIdentifier;
            this.mAudioStateChangesObservable.notifyUpdate(this.previousPlayingClientMessageIdentifier);
        } else if (i == 2) {
            int audioMessageState = getAudioMessageState(clientMessageIdentifier);
            if (audioMessageState != 0 && audioMessageState != 4) {
                if (audioMessageState == 1) {
                    this.currentlyPlayingClientMessageIdentifier = null;
                    if (z) {
                        recordClickMetric(MetricKeys.MSG_AUD_PLAY_FULL, clientMessageIdentifier);
                    } else {
                        recordClickMetric(MetricKeys.MSG_AUD_PLAY_PARTIAL, clientMessageIdentifier);
                    }
                }
            }
            this.mAudioMessageDownloadStateMap.remove(clientMessageIdentifier);
        } else if (i != 4) {
            return;
        } else {
            this.mAudioMessageDownloadStateMap.put(clientMessageIdentifier, 4);
        }
        this.mAudioStateChangesObservable.notifyUpdate(clientMessageIdentifier);
    }
}
