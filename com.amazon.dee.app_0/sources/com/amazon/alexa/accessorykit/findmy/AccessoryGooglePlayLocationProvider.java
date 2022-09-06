package com.amazon.alexa.accessorykit.findmy;

import android.content.Context;
import android.location.Location;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
/* loaded from: classes6.dex */
public class AccessoryGooglePlayLocationProvider implements LocationProvider {
    private static final String TAG = "AccessoryGooglePlayLocationProvider";
    private Context context;
    private FusedLocationProviderClient fusedLocationProviderClient;

    public AccessoryGooglePlayLocationProvider(Context context) {
        Preconditions.notNull(context, "context");
        this.context = context;
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(SingleEmitter singleEmitter, Task task) {
        if (task.isSuccessful() && task.getResult() != null) {
            Location location = (Location) task.getResult();
            Logger.d("%s: Last location is retrieved from OS: Latitude= %f, Longitude=%f", TAG, Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()));
            if (singleEmitter.isDisposed()) {
                return;
            }
            singleEmitter.onSuccess(location);
        } else if (singleEmitter.isDisposed()) {
        } else {
            singleEmitter.onError(new Exception("Fail to retrieve last location from OS.", task.getException()));
        }
    }

    @Override // com.amazon.alexa.accessorykit.findmy.LocationProvider
    public Single<Location> getLatestLocation() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$AccessoryGooglePlayLocationProvider$6q7MEFQ-lrUZyipedv-UdjYx7vU
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                AccessoryGooglePlayLocationProvider.this.lambda$getLatestLocation$1$AccessoryGooglePlayLocationProvider(singleEmitter);
            }
        });
    }

    public /* synthetic */ void lambda$getLatestLocation$1$AccessoryGooglePlayLocationProvider(final SingleEmitter singleEmitter) throws Throwable {
        if (singleEmitter.isDisposed()) {
            return;
        }
        if (LocationPermissionUtils.hasNoAccessFineLocationPermission(this.context)) {
            singleEmitter.onError(new Exception("Location permission is not granted."));
        } else {
            this.fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener() { // from class: com.amazon.alexa.accessorykit.findmy.-$$Lambda$AccessoryGooglePlayLocationProvider$7JHVzZx4WxhDvRMzJhcJyhudaUQ
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    AccessoryGooglePlayLocationProvider.lambda$null$0(SingleEmitter.this, task);
                }
            });
        }
    }
}
