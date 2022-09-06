package com.amazon.alexa.accessory.avsclient.sco;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.avsclient.context.BluetoothProfileWatcher;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.ObservableUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.StateOuterClass;
import com.amazon.alexa.accessory.sco.ScoPrioritizer;
import com.amazon.alexa.accessoryclient.client.accessories.SessionSupplier;
import com.android.tools.r8.GeneratedOutlineSupport1;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes.dex */
public final class AlexaClientScoPrioritizer implements ScoPrioritizer {
    private static final String TAG = "AlexaClientScoPrioritizer";
    private final AudioManager audioManager;
    private final BluetoothProfileWatcher bluetoothProfileWatcher;
    private final SessionSupplier clientSessionSupplier;
    private Disposable currentStatusDisposable;
    private final Object lock;
    private int numAccessoriesConnected;
    private int numAccessoriesConnectedPreferSco;

    public AlexaClientScoPrioritizer(SessionSupplier sessionSupplier, BluetoothProfileWatcher bluetoothProfileWatcher, Context context) {
        Preconditions.notNull(sessionSupplier, "clientSessionSupplier");
        Preconditions.notNull(bluetoothProfileWatcher, "bluetoothProfileWatcher");
        Preconditions.notNull(context, "context");
        this.clientSessionSupplier = sessionSupplier;
        this.bluetoothProfileWatcher = bluetoothProfileWatcher;
        this.bluetoothProfileWatcher.ensureActive(2);
        this.audioManager = (AudioManager) context.getSystemService("audio");
        this.lock = new Object();
        observeSessions();
        determineCurrentStatus();
    }

    private void determineCurrentStatus() {
        Single list = this.clientSessionSupplier.getActiveSessions().flatMapObservable($$Lambda$tNLLyz36wpjmL1kezURjOHIEA.INSTANCE).flatMapSingle($$Lambda$AlexaClientScoPrioritizer$RI1yFKQFcN6b6XsuwDzFY5DoE.INSTANCE).toList();
        synchronized (this.lock) {
            ObservableUtils.dispose(this.currentStatusDisposable);
            this.currentStatusDisposable = list.subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.sco.-$$Lambda$AlexaClientScoPrioritizer$ya3omLn-E7xytvJ3EV4wXZTUW2s
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    AlexaClientScoPrioritizer.this.lambda$determineCurrentStatus$3$AlexaClientScoPrioritizer((List) obj);
                }
            }, $$Lambda$AlexaClientScoPrioritizer$0DXQhJ5n360217JzJ_GGQAx9Jc4.INSTANCE);
        }
    }

    private boolean isExternalSpeakerConnected() {
        return this.audioManager.isWiredHeadsetOn() || (this.bluetoothProfileWatcher.getActiveDevices().isEmpty() ^ true);
    }

    @SuppressLint({"CheckResult"})
    private void observeSessions() {
        Observable.merge(this.clientSessionSupplier.observeSessionConnected(), this.clientSessionSupplier.observeSessionReleased()).subscribe(new Consumer() { // from class: com.amazon.alexa.accessory.avsclient.sco.-$$Lambda$AlexaClientScoPrioritizer$CvtT9JBRYw-fAGCjGCpbdhTOa9I
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                AlexaClientScoPrioritizer.this.lambda$observeSessions$0$AlexaClientScoPrioritizer((Accessory) obj);
            }
        }, $$Lambda$AlexaClientScoPrioritizer$wOKZ7Bz855C3XsE05fpADdx4fGY.INSTANCE);
    }

    public /* synthetic */ void lambda$determineCurrentStatus$3$AlexaClientScoPrioritizer(List list) throws Throwable {
        synchronized (this.lock) {
            this.numAccessoriesConnected = list.size();
            this.numAccessoriesConnectedPreferSco = 0;
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                if (((StateOuterClass.State) it2.next()).getBoolean()) {
                    this.numAccessoriesConnectedPreferSco++;
                }
            }
        }
    }

    public /* synthetic */ void lambda$observeSessions$0$AlexaClientScoPrioritizer(Accessory accessory) throws Throwable {
        determineCurrentStatus();
    }

    @Override // com.amazon.alexa.accessory.sco.ScoPrioritizer
    public boolean shouldUseSco() {
        boolean z;
        boolean z2;
        synchronized (this.lock) {
            z = this.numAccessoriesConnectedPreferSco == this.numAccessoriesConnected;
            z2 = this.numAccessoriesConnected != 0;
        }
        boolean isExternalSpeakerConnected = isExternalSpeakerConnected();
        StringBuilder sb = new StringBuilder();
        sb.append("%s: There are active sessions [");
        sb.append(z2);
        sb.append("], all accessories prefer sco [");
        sb.append(z);
        sb.append("], external speaker connected [");
        Logger.d(GeneratedOutlineSupport1.outline97(sb, isExternalSpeakerConnected, "]"), TAG);
        return z2 && z && !isExternalSpeakerConnected;
    }
}
