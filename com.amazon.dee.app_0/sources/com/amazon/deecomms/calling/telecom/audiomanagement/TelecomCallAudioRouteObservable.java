package com.amazon.deecomms.calling.telecom.audiomanagement;

import android.telecom.CallAudioState;
import androidx.annotation.NonNull;
import java.util.Observable;
/* loaded from: classes12.dex */
public class TelecomCallAudioRouteObservable extends Observable {
    public void onCallAudioStateUpdated(@NonNull CallAudioState callAudioState) {
        setChanged();
        notifyObservers(callAudioState);
    }
}
