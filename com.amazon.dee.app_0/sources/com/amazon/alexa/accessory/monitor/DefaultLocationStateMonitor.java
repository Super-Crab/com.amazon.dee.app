package com.amazon.alexa.accessory.monitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.LocationManager;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.monitor.LocationStateMonitor;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class DefaultLocationStateMonitor implements LocationStateMonitor {
    private final Context context;
    private final IntentFilter intentFilter;
    private final BroadcastReceiver locationReceiver;
    private final Set<LocationStateMonitor.Observer> observers;

    /* loaded from: classes.dex */
    private final class LocationReceiver extends BroadcastReceiver {
        private final LocationManager locationManager;

        public LocationReceiver(Context context) {
            this.locationManager = (LocationManager) context.getSystemService("location");
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (!"android.location.PROVIDERS_CHANGED".equals(intent.getAction())) {
                return;
            }
            if (this.locationManager.isProviderEnabled("gps")) {
                Logger.d("LocationStateMonitor: Location turned on.");
                DefaultLocationStateMonitor.this.notifyLocationStateOn();
                return;
            }
            Logger.d("LocationStateMonitor: Location turned off.");
            DefaultLocationStateMonitor.this.notifyLocationStateOff();
        }
    }

    public DefaultLocationStateMonitor(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.observers = new HashSet();
        this.locationReceiver = new LocationReceiver(context);
        this.intentFilter = createLocationIntentFilter();
    }

    private IntentFilter createLocationIntentFilter() {
        return GeneratedOutlineSupport1.outline10("android.location.PROVIDERS_CHANGED");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyLocationStateOff() {
        for (LocationStateMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onLocationDisabled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyLocationStateOn() {
        for (LocationStateMonitor.Observer observer : new ArrayList(this.observers)) {
            observer.onLocationEnabled();
        }
    }

    @Override // com.amazon.alexa.accessory.monitor.LocationStateMonitor
    public void addObserver(LocationStateMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (this.observers.isEmpty()) {
            this.context.registerReceiver(this.locationReceiver, this.intentFilter);
        }
        this.observers.add(observer);
    }

    @Override // com.amazon.alexa.accessory.monitor.LocationStateMonitor
    public void removeObserver(LocationStateMonitor.Observer observer) {
        Preconditions.mainThread();
        Preconditions.notNull(observer, "observer");
        if (!this.observers.remove(observer) || !this.observers.isEmpty()) {
            return;
        }
        this.context.unregisterReceiver(this.locationReceiver);
    }
}
