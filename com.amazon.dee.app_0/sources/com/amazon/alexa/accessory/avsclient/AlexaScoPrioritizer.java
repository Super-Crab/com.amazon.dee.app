package com.amazon.alexa.accessory.avsclient;

import android.content.Context;
import android.media.AudioManager;
import com.amazon.alexa.accessory.SessionSupplier;
import com.amazon.alexa.accessory.avsclient.context.BluetoothProfileWatcher;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
/* loaded from: classes.dex */
public final class AlexaScoPrioritizer implements ScoPrioritizer {
    private final AudioManager audioManager;
    private final BluetoothProfileWatcher bluetoothProfileWatcher;
    private final AlexaScoSessionCache scoSessionCache;
    private final SessionSupplier sessionSupplier;

    public AlexaScoPrioritizer(SessionSupplier sessionSupplier, BluetoothProfileWatcher bluetoothProfileWatcher, Context context) {
        Preconditions.notNull(sessionSupplier, "sessionSupplier");
        Preconditions.notNull(bluetoothProfileWatcher, "bluetoothProfileWatcher");
        Preconditions.notNull(context, "context");
        this.sessionSupplier = sessionSupplier;
        this.bluetoothProfileWatcher = bluetoothProfileWatcher;
        this.bluetoothProfileWatcher.ensureActive(2);
        this.scoSessionCache = new AlexaScoSessionCache(this.sessionSupplier);
        sessionSupplier.addSessionListener(this.scoSessionCache);
        this.audioManager = (AudioManager) context.getSystemService("audio");
    }

    private boolean isExternalSpeakerConnected() {
        return this.audioManager.isWiredHeadsetOn() || (this.bluetoothProfileWatcher.getActiveDevices().isEmpty() ^ true);
    }

    @Override // com.amazon.alexa.accessory.sco.ScoPrioritizer
    public boolean shouldUseSco() {
        Preconditions.mainThread();
        boolean z = this.scoSessionCache.getNumAccessoriesThatDoNotPrioritizeSco() == 0;
        boolean hasActiveSessions = this.sessionSupplier.hasActiveSessions();
        boolean isExternalSpeakerConnected = isExternalSpeakerConnected();
        Logger.d("AlexaScoPrioritizer: There are active sessions [" + hasActiveSessions + "], all accessories prefer sco [" + z + "], external speaker connected [" + isExternalSpeakerConnected + "]");
        return hasActiveSessions && z && !isExternalSpeakerConnected;
    }
}
