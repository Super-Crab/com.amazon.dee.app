package com.amazon.alexa.accessory.notificationpublisher.providers;

import com.amazon.alexa.accessory.notificationpublisher.consumption.StatusEventManager;
import com.amazon.alexa.accessory.notificationpublisher.storage.SettingsStorageModule;
import com.amazon.alexa.accessory.notificationpublisher.utils.FeatureAccessChecker;
import com.amazon.alexa.accessory.notificationpublisher.utils.Log;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
/* loaded from: classes.dex */
public final class DistractionModeProvider {
    private static final String TAG = "DistractionModeProvider";
    private static final BehaviorSubject<String> subject = BehaviorSubject.create();
    private static final DistractionMode distractionMode = new DistractionMode(false, false);

    private DistractionModeProvider() {
    }

    public static synchronized int getCurrentDistractionMode() {
        synchronized (DistractionModeProvider.class) {
            if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
                return MultiDeviceDistractionModeProvider.getInstance().getCurrentDistractionMode();
            }
            return distractionMode.getCurrentDistractionMode();
        }
    }

    public static Observable<String> getDistractionModeObservable() {
        return subject.distinctUntilChanged();
    }

    public static synchronized void setLowDistractionMode(boolean z) {
        synchronized (DistractionModeProvider.class) {
            String str = TAG;
            Log.i(str, "setLowDistractionMode - enabled = " + z);
            distractionMode.setLowDistractionMode(z);
            updateSubject();
        }
    }

    public static synchronized void setNoDistractionMode(boolean z) {
        synchronized (DistractionModeProvider.class) {
            String str = TAG;
            Log.i(str, "setNoDistractionMode - enabled = " + z);
            distractionMode.setNoDistractionMode(z);
            updateSubject();
        }
    }

    public static synchronized void setSilentDistractionMode(boolean z) {
        synchronized (DistractionModeProvider.class) {
            String str = TAG;
            Log.i(str, "setSilentDistractionMode - enabled = " + z);
            distractionMode.setSilentDistractionMode(z);
            StatusEventManager.getInstance().onSilentDistractionModeChanged(z);
            SettingsStorageModule.getInstance().putSilentDistractionModeValue(z);
            updateSubject();
        }
    }

    private static void updateSubject() {
        subject.onNext(String.valueOf(getCurrentDistractionMode()));
    }

    public static synchronized void setLowDistractionMode(String str, boolean z) {
        synchronized (DistractionModeProvider.class) {
            String str2 = TAG;
            Log.i(str2, "setLowDistractionMode - enabled = " + z);
            MultiDeviceDistractionModeProvider.getInstance().setLowDistractionMode(str, z);
            updateSubject();
        }
    }

    public static synchronized void setNoDistractionMode(String str, boolean z) {
        synchronized (DistractionModeProvider.class) {
            if (FeatureAccessChecker.hasOtgVipFilterAccess() && FeatureAccessChecker.hasOtgVipFilterUssSettingsAccess()) {
                String str2 = TAG;
                Log.i(str2, "setNoDistractionMode - enabled = " + z);
                MultiDeviceDistractionModeProvider.getInstance().setNoDistractionMode(str, z);
            } else {
                setNoDistractionMode(z);
            }
            updateSubject();
        }
    }

    public static synchronized void setSilentDistractionMode(String str, boolean z) {
        synchronized (DistractionModeProvider.class) {
            MultiDeviceDistractionModeProvider.getInstance().setSilentDistractionMode(str, z);
            updateSubject();
        }
    }
}
