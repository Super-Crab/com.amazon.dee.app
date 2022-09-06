package com.amazon.alexa;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import androidx.annotation.MainThread;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.ApiThreadHelper;
import com.amazon.alexa.utils.TimeProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Singleton;
/* compiled from: AndroidLocationComponent.java */
@Singleton
/* loaded from: classes.dex */
public class SCB extends eCj implements LocationListener {
    public static final long Mlj = TimeUnit.NANOSECONDS.convert(180000, TimeUnit.MILLISECONDS);
    public static final String yPL = "SCB";

    @Inject
    public SCB(AlexaClientEventBus alexaClientEventBus, LocationManager locationManager, vYS vys, TimeProvider timeProvider, Dtt dtt, Context context) {
        super(alexaClientEventBus, context, locationManager, vys, timeProvider, dtt);
    }

    public static /* synthetic */ Location zZm(SCB scb) {
        Location location = null;
        for (String str : scb.zQM().getProviders(true)) {
            if (scb.jiA() && scb.zQM().getLastKnownLocation(str) != null) {
                Location lastKnownLocation = scb.zQM().getLastKnownLocation(str);
                long elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos() - lastKnownLocation.getElapsedRealtimeNanos();
                if (location == null || (location.getAccuracy() > lastKnownLocation.getAccuracy() && elapsedRealtimeNanos <= Mlj)) {
                    location = lastKnownLocation;
                }
            }
        }
        return location;
    }

    @Override // com.amazon.alexa.eCj
    public void LPk() {
        Log.i(yPL, "Location is available - registering for location updates");
        ApiThreadHelper.runOnUiThread(new vZM(this));
    }

    @Override // com.amazon.alexa.eCj
    public void Mlj() {
        Log.i(yPL, "teardown - deregistering location listener");
        zQM().removeUpdates(this);
    }

    @Override // android.location.LocationListener
    @MainThread
    public void onProviderDisabled(String str) {
        String str2 = "Provider " + str + " has been disabled";
    }

    @Override // android.location.LocationListener
    @MainThread
    public void onProviderEnabled(String str) {
        String str2 = "Provider " + str + " has been enabled";
    }

    @Override // android.location.LocationListener
    @MainThread
    public void onStatusChanged(String str, int i, Bundle bundle) {
        if (i == 0) {
            GeneratedOutlineSupport1.outline158("Listener status changed to OUT_OF_SERVICE for", str);
        } else if (i == 1) {
            GeneratedOutlineSupport1.outline158("Listener status changed to TEMPORARILY_UNAVAILABLE for", str);
        } else if (i != 2) {
            String str2 = "Listener status changed for provider " + str + " " + i;
        } else {
            GeneratedOutlineSupport1.outline158("Listener status changed to AVAILABLE for", str);
        }
    }

    @Override // com.amazon.alexa.eCj
    public void yPL() {
        Log.i(yPL, "Location is unavailable due to missing permissions - deregistering location listener");
        zQM().removeUpdates(this);
    }
}
