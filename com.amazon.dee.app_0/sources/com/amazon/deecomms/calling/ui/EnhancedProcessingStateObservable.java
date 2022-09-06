package com.amazon.deecomms.calling.ui;

import java.util.Observable;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes12.dex */
public class EnhancedProcessingStateObservable extends Observable {
    public void onEnhancedProcessingStateChange(boolean z) {
        setChanged();
        notifyObservers(Boolean.valueOf(z));
    }
}
