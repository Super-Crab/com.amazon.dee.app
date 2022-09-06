package com.amazon.alexa.location.phase3;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.crashreporting.api.CrashReporter;
import com.amazon.alexa.location.models.ALSGeofence;
import com.amazon.alexa.location.phase3.sensor.alexageofence.LocationOfInterest;
import com.amazon.alexa.location.phase3.sensor.nativelocation.NativeLocationInput;
import com.amazon.alexa.location.utils.ExceptionRecordUtil;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.google.common.collect.ImmutableList;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes9.dex */
public class DwellTracker {
    private static final long DWELL_RECORDING_THRESHOLD_IN_MS = 600000;
    private static final String STORE_KEY_ONGOING_DWELL_LOCATION = "ongoing_dwell_location";
    private static final String TAG = "DwellTracker";
    private static final long TOTAL_DWELL_THRESHOLD_FOR_FENCE_IN_MS = 7200000;
    private final LazyComponent<CrashReporter> crashReporter;
    private final LocationDataStore<LocationOfInterest> locationOfInterestStore;
    @VisibleForTesting
    final Map<String, LocationOfInterest> locationsOfInterest;
    @VisibleForTesting
    NativeLocationInput ongoingDwellLocation;
    private final LocationDataStore<NativeLocationInput> ongoingDwellLocationStore;

    public DwellTracker(@NonNull LocationDataStoreService locationDataStoreService, @NonNull LazyComponent<CrashReporter> lazyComponent) {
        this(locationDataStoreService.getDataStore(LocationDataStoreService.LOCATION_OF_INTEREST_TABLE, LocationOfInterest.class), locationDataStoreService.getDataStore(LocationDataStoreService.NATIVE_LOCATION_INPUT_TABLE, NativeLocationInput.class), lazyComponent);
    }

    static /* synthetic */ void lambda$resetCurrentDwellSession$4() throws Throwable {
    }

    static /* synthetic */ void lambda$updateDwellSession$0() throws Throwable {
    }

    static /* synthetic */ void lambda$updateDwellSession$2() throws Throwable {
    }

    @VisibleForTesting
    long calculateTotalDwellTime(@NonNull ALSGeofence aLSGeofence) {
        long j = 0;
        for (LocationOfInterest locationOfInterest : this.locationsOfInterest.values()) {
            if (locationOfInterest.associateWithFence(aLSGeofence)) {
                j = locationOfInterest.getScore() + j;
            }
        }
        return j;
    }

    public void clearData() {
        this.ongoingDwellLocationStore.removeAll();
        this.locationOfInterestStore.removeAll();
    }

    public boolean hasDwelledEnoughIn(@NonNull ALSGeofence aLSGeofence) {
        return calculateTotalDwellTime(aLSGeofence) >= TOTAL_DWELL_THRESHOLD_FOR_FENCE_IN_MS;
    }

    public /* synthetic */ void lambda$resetCurrentDwellSession$5$DwellTracker(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "ongoingDwellLocationStore", th, this.crashReporter);
    }

    public /* synthetic */ void lambda$updateDwellSession$1$DwellTracker(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "ongoingDwellLocationStore", th, this.crashReporter);
    }

    public /* synthetic */ void lambda$updateDwellSession$3$DwellTracker(Throwable th) throws Throwable {
        ExceptionRecordUtil.recordExceptions(TAG, "locationOfInterestStore", th, this.crashReporter);
    }

    public void resetCurrentDwellSession() {
        this.ongoingDwellLocation = null;
        this.ongoingDwellLocationStore.removeValuesAsRx(ImmutableList.of(STORE_KEY_ONGOING_DWELL_LOCATION)).subscribe($$Lambda$DwellTracker$I8pcrA1u61pNDBM6yHa_mDDjY.INSTANCE, new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$DwellTracker$k0dFLhFu1QAhjWwmAe9zz6SRRjc
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DwellTracker.this.lambda$resetCurrentDwellSession$5$DwellTracker((Throwable) obj);
            }
        });
    }

    public void restoreLocations() {
        this.locationsOfInterest.clear();
        this.locationsOfInterest.putAll(this.locationOfInterestStore.retrieveAll());
        this.ongoingDwellLocation = this.ongoingDwellLocationStore.retrieveValue(STORE_KEY_ONGOING_DWELL_LOCATION);
    }

    @VisibleForTesting
    boolean shouldStartNewDwellSession(@NonNull NativeLocationInput nativeLocationInput) {
        return this.ongoingDwellLocation == null || !LocationOfInterest.generateKey(nativeLocationInput).equals(LocationOfInterest.generateKey(this.ongoingDwellLocation)) || nativeLocationInput.timestamp < this.ongoingDwellLocation.timestamp;
    }

    public void updateDwellSession(@NonNull NativeLocationInput nativeLocationInput) {
        LocationOfInterest fromLocationInput;
        String generateKey = LocationOfInterest.generateKey(nativeLocationInput);
        if (this.locationsOfInterest.containsKey(generateKey)) {
            fromLocationInput = this.locationsOfInterest.get(generateKey);
        } else {
            fromLocationInput = LocationOfInterest.fromLocationInput(nativeLocationInput);
        }
        LocationOfInterest locationOfInterest = (LocationOfInterest) Objects.requireNonNull(fromLocationInput);
        if (shouldStartNewDwellSession(nativeLocationInput)) {
            this.locationsOfInterest.put(generateKey, locationOfInterest);
            this.ongoingDwellLocation = nativeLocationInput;
            this.ongoingDwellLocationStore.storeValueAsRx(STORE_KEY_ONGOING_DWELL_LOCATION, this.ongoingDwellLocation).subscribe($$Lambda$DwellTracker$UIMwgsPQe7WmVfIDyeNorgVKCss.INSTANCE, new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$DwellTracker$MrC2NN-SWs_hj_H4DermvqMDgqY
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    DwellTracker.this.lambda$updateDwellSession$1$DwellTracker((Throwable) obj);
                }
            });
        } else {
            long j = nativeLocationInput.timestamp;
            NativeLocationInput nativeLocationInput2 = this.ongoingDwellLocation;
            long j2 = j - nativeLocationInput2.timestamp;
            if (j2 >= 600000) {
                locationOfInterest.updateSupportingSignal(nativeLocationInput2, j2);
            }
        }
        this.locationOfInterestStore.storeValueAsRx(generateKey, locationOfInterest).subscribe($$Lambda$DwellTracker$LwFMPmtpYnl4iall7SyxoIAM.INSTANCE, new Consumer() { // from class: com.amazon.alexa.location.phase3.-$$Lambda$DwellTracker$Xu2QmD0LGN0aZni1leK7-N4X-u8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                DwellTracker.this.lambda$updateDwellSession$3$DwellTracker((Throwable) obj);
            }
        });
    }

    @VisibleForTesting
    DwellTracker(@NonNull LocationDataStore<LocationOfInterest> locationDataStore, @NonNull LocationDataStore<NativeLocationInput> locationDataStore2, @NonNull LazyComponent<CrashReporter> lazyComponent) {
        this.locationsOfInterest = new HashMap();
        this.locationOfInterestStore = locationDataStore;
        this.ongoingDwellLocationStore = locationDataStore2;
        this.crashReporter = lazyComponent;
    }
}
