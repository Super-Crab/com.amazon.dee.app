package com.amazon.alexa.location;

import android.content.Context;
import android.location.Location;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
/* loaded from: classes9.dex */
public class LocationProvider {
    static final String TAG = "LocationProvider";
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationPermissionService permissionService;

    public LocationProvider(Context context) {
        this.permissionService = new AlexaLocationPermissionService(context);
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$null$0(SingleEmitter singleEmitter, Task task) {
        if (task.isSuccessful() && task.getResult() != null) {
            Location location = (Location) task.getResult();
            String.format("[SUCCESS] Last location is retrieved from OS successfully. [%f , %f]", Double.valueOf(location.getLatitude()), Double.valueOf(location.getLongitude()));
            singleEmitter.onSuccess(location);
            return;
        }
        singleEmitter.onError(new LocationException(LocationErrorCode.GENERIC_ERROR, "[ERROR] Fail to retrieve last location from OS.", task.getException()));
    }

    public Single<Location> getMostRecentLocation() {
        return Single.create(new SingleOnSubscribe() { // from class: com.amazon.alexa.location.-$$Lambda$LocationProvider$Xgt3M4MzCZNRe6VlU2dydhWWsbo
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) {
                LocationProvider.this.lambda$getMostRecentLocation$1$LocationProvider(singleEmitter);
            }
        });
    }

    public /* synthetic */ void lambda$getMostRecentLocation$1$LocationProvider(final SingleEmitter singleEmitter) throws Throwable {
        if (!this.permissionService.hasAccessFineLocationPermission()) {
            singleEmitter.onError(new LocationException(LocationErrorCode.PERMISSION_ERROR, "[ERROR] Location permission is not granted."));
        } else {
            this.fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener() { // from class: com.amazon.alexa.location.-$$Lambda$LocationProvider$TggVia6HQnL-v4_B5qS5cLdOp1s
                @Override // com.google.android.gms.tasks.OnCompleteListener
                public final void onComplete(Task task) {
                    LocationProvider.lambda$null$0(SingleEmitter.this, task);
                }
            });
        }
    }
}
