package com.amazon.deecomms.calling.controller;

import java.util.Observable;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes12.dex */
public class CallDowngradedObservable extends Observable {
    public void onCallDowngradeStateUpdated(boolean z) {
        setChanged();
        notifyObservers(Boolean.valueOf(z));
    }
}
