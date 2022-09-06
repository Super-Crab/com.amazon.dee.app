package com.amazon.alexa.ttcf;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;
import com.amazon.alexa.ttcf.api.TTCFCheckpoint;
import com.amazon.alexa.ttcf.api.TTCFRoute;
import com.amazon.alexa.ttcf.api.TTCFRoutingDelegate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
/* loaded from: classes10.dex */
public class TTCFService implements TTCFRoutingDelegate, TTCFCheckpoint, LifecycleObserver {
    private static final String TAG = "TTCFService";
    @NonNull
    private final TTCFClock clock;
    private final Set<String> complete;
    @NonNull
    private final TTCFRecorder recorder;
    private final PushOnlyStack<TTCFRouting> stack;

    @VisibleForTesting
    TTCFService(@NonNull TTCFRecorder tTCFRecorder, @NonNull TTCFClock tTCFClock) {
        this.stack = new PushOnlyStack<>(1);
        this.complete = new HashSet();
        this.recorder = tTCFRecorder;
        this.clock = tTCFClock;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @NonNull
    private List<TTCFRouting> findRoutings() {
        TTCFRouting peek = this.stack.peek();
        ArrayList arrayList = new ArrayList();
        if (peek != null && peek.isValid()) {
            arrayList.add(peek);
        }
        return arrayList;
    }

    @Override // com.amazon.alexa.ttcf.api.TTCFCheckpoint
    public void markComplete(String str) {
        synchronized (this) {
            long time = this.clock.getTime();
            boolean contains = this.complete.contains(str);
            if (!contains) {
                this.complete.add(str);
            }
            List<TTCFRouting> findRoutings = findRoutings();
            if (findRoutings.size() == 0) {
                String str2 = TAG;
                Log.w(str2, "No routings found for " + str);
                return;
            }
            for (TTCFRouting tTCFRouting : findRoutings) {
                tTCFRouting.invalidate();
            }
            TTCFRecord make = TTCFRecord.make(str, findRoutings, time, !contains);
            if (make != null) {
                this.recorder.record(make);
            }
        }
    }

    @VisibleForTesting
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onAppPause() {
        synchronized (this) {
            Iterator<TTCFRouting> it2 = this.stack.iterator();
            while (it2.hasNext()) {
                it2.next().invalidate();
            }
            Log.i(TAG, "Invalidated routings because app went background");
        }
    }

    @Override // com.amazon.alexa.ttcf.api.TTCFRoutingDelegate
    public void routeDidFinish() {
        synchronized (this) {
            TTCFRouting peek = this.stack.peek();
            if (peek != null) {
                peek.finishAtTime(this.clock.getTime());
            } else {
                Log.w(TAG, "routeDidFinish but no routeDidStart");
            }
        }
    }

    @Override // com.amazon.alexa.ttcf.api.TTCFRoutingDelegate
    public void routeDidStart(@NonNull TTCFRoute tTCFRoute) {
        synchronized (this) {
            this.stack.push(new TTCFRouting(tTCFRoute, this.clock.getTime()));
        }
    }

    public TTCFService(@NonNull TTCFRecorder tTCFRecorder) {
        this(tTCFRecorder, new SimpleClock());
    }
}
