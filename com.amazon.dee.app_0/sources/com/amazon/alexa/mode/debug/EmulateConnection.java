package com.amazon.alexa.mode.debug;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
/* loaded from: classes9.dex */
public class EmulateConnection {
    public static final String ACTION_DEBUG = "com.amazon.alexa.mode.debug.INTENT";
    public static final String EXTRA_CONNECT = "connect";
    private final BehaviorSubject<Boolean> subject = BehaviorSubject.create();

    public EmulateConnection(Context context, final DebugInterface debugInterface) {
        LocalBroadcastManager.getInstance(context).registerReceiver(new BroadcastReceiver() { // from class: com.amazon.alexa.mode.debug.EmulateConnection.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                if (!EmulateConnection.ACTION_DEBUG.equals(intent.getAction()) || !intent.hasExtra(EmulateConnection.EXTRA_CONNECT)) {
                    return;
                }
                EmulateConnection.this.subject.onNext(Boolean.valueOf(intent.getBooleanExtra(EmulateConnection.EXTRA_CONNECT, false)));
            }
        }, new IntentFilter(ACTION_DEBUG));
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable<Boolean> observeOn = this.subject.observeOn(AndroidSchedulers.mainThread());
        debugInterface.getClass();
        compositeDisposable.add(observeOn.subscribe(new Consumer() { // from class: com.amazon.alexa.mode.debug.-$$Lambda$vAcBzSbTK3hx7-UvDg6pZ7wr6AE
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DebugInterface.this.onDebugConnection(((Boolean) obj).booleanValue());
            }
        }));
    }
}
