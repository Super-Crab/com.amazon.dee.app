package com.amazon.deecomms.media.audio;

import androidx.annotation.NonNull;
import com.amazon.deecomms.calling.enums.AudioRoutes;
import java.util.Observable;
/* loaded from: classes12.dex */
public class AudioStateObservable extends Observable {
    public void onCallAudioRouteChanged(@NonNull AudioRoutes audioRoutes) {
        setChanged();
        notifyObservers(audioRoutes);
    }
}
