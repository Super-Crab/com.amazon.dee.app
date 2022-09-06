package com.amazon.deecomms.calling.ui;

import androidx.annotation.NonNull;
import java.util.Observable;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes12.dex */
public class NameChangeObservable extends Observable {
    public void onCustomerNameChanged(@NonNull String str) {
        setChanged();
        notifyObservers(str);
    }
}
