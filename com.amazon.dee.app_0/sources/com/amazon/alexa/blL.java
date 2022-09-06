package com.amazon.alexa;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.client.alexaservice.eventing.AlexaClientEventBus;
import com.amazon.alexa.utils.TimeProvider;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import java.util.concurrent.ExecutorService;
import javax.inject.Singleton;
/* compiled from: GooglePlayLocationComponent.java */
@Singleton
/* loaded from: classes.dex */
public class blL extends eCj implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static final String yPL = "blL";
    public final GoogleApiClient Mlj;
    public final ExecutorService zzR;

    public blL(AlexaClientEventBus alexaClientEventBus, GoogleApiClient googleApiClient, Context context, LocationManager locationManager, vYS vys, TimeProvider timeProvider, Dtt dtt, ExecutorService executorService) {
        super(alexaClientEventBus, context, locationManager, vys, timeProvider, dtt);
        this.Mlj = googleApiClient;
        this.zzR = executorService;
        synchronized (this.Mlj) {
            this.Mlj.registerConnectionCallbacks(this);
            this.Mlj.registerConnectionFailedListener(this);
        }
        this.zzR.execute(new eNj(this));
    }

    @Override // com.amazon.alexa.eCj
    public void LPk() {
        Log.i(yPL, "Location is available - connecting to the Google Play apis");
        synchronized (this.Mlj) {
            if (!this.Mlj.isConnecting() && !this.Mlj.isConnected()) {
                this.Mlj.connect();
            }
        }
    }

    @Override // com.amazon.alexa.eCj
    public void Mlj() {
        Log.i(yPL, "teardown - disconnecting from the Google Play apis");
        synchronized (this.Mlj) {
            if (this.Mlj.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(this.Mlj, this);
                this.Mlj.unregisterConnectionCallbacks(this);
                this.Mlj.unregisterConnectionFailedListener(this);
                this.Mlj.disconnect();
            }
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    @MainThread
    public void onConnected(@Nullable Bundle bundle) {
        if (jiA()) {
            zZm(new LocationRequest().setInterval(60000L).setSmallestDisplacement(10.0f).setPriority(100));
            Location zzR = zzR();
            if (zzR == null) {
                return;
            }
            onLocationChanged(zzR);
        }
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
    @MainThread
    public void onConnectionFailed(ConnectionResult connectionResult) {
        int errorCode = connectionResult.getErrorCode();
        Log.e(yPL, "Connection to Play Services failed.");
        if (errorCode != 1 && errorCode != 2) {
            if (errorCode != 4 && errorCode != 5) {
                if (errorCode != 7) {
                    if (errorCode != 11) {
                        if (errorCode != 13) {
                            if (errorCode != 20) {
                                switch (errorCode) {
                                    case 15:
                                        break;
                                    case 16:
                                    case 18:
                                        break;
                                    case 17:
                                        break;
                                    default:
                                        String str = yPL;
                                        Log.e(str, "Error: " + errorCode);
                                        return;
                                }
                            }
                        }
                    }
                }
                String str2 = yPL;
                Log.e(str2, "Network related error: " + errorCode);
                return;
            }
            String str3 = yPL;
            Log.e(str3, "User related error: " + errorCode);
            return;
        }
        String str4 = yPL;
        Log.e(str4, "Error related to Service: " + errorCode);
    }

    @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
    @MainThread
    public void onConnectionSuspended(int i) {
        if (i != 1) {
        }
        synchronized (this.Mlj) {
            this.Mlj.connect();
        }
    }

    @Override // com.amazon.alexa.eCj
    public void yPL() {
        Log.i(yPL, "Location is unavailable due to missing permissions - disconnecting from the Google Play apis");
        synchronized (this.Mlj) {
            if (this.Mlj.isConnected()) {
                LocationServices.FusedLocationApi.removeLocationUpdates(this.Mlj, this);
            }
            this.Mlj.disconnect();
        }
    }

    @SuppressLint({"MissingPermission"})
    @MainThread
    @VisibleForTesting
    public void zZm(LocationRequest locationRequest) {
        synchronized (this.Mlj) {
            LocationServices.FusedLocationApi.requestLocationUpdates(this.Mlj, locationRequest, this);
        }
    }

    @SuppressLint({"MissingPermission"})
    @VisibleForTesting
    public Location zzR() {
        Location lastLocation;
        synchronized (this.Mlj) {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(this.Mlj);
        }
        return lastLocation;
    }
}
