package com.amazon.deecomms.calling.ui;

import java.util.Observable;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes12.dex */
public class PipVisibilityObservable extends Observable {
    public void onPipVisibilityUpdated(String str) {
        setChanged();
        notifyObservers(str);
    }
}
