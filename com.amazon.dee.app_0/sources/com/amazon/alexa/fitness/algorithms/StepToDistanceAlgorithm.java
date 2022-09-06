package com.amazon.alexa.fitness.algorithms;

import android.util.Log;
import com.amazon.alexa.fitness.algorithms.BiometricProfile;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
/* loaded from: classes.dex */
public class StepToDistanceAlgorithm {
    private Object algoObject = initializeNativeAlgoObject();

    /* loaded from: classes.dex */
    public enum UnitSystem {
        IMPERIAL_SYSTEM,
        METRIC_SYSTEM
    }

    static {
        if (System.getProperty("java.vendor.url").contains("android")) {
            Log.i("StepToDistanceAlgorithm", "loading jni lib");
            System.loadLibrary("algos_jni");
            Log.i("StepToDistanceAlgorithm", "jni lib loaded");
            return;
        }
        Log.w("StepToDistanceAlgorithm", "Not on Android device, skip loading jni lib");
    }

    private native int addEvents(Object obj, ActivityEvent[] activityEventArr);

    private void checkInitialization() throws IllegalStateException {
        if (this.algoObject != null) {
            return;
        }
        throw new IllegalStateException("Not Initialized. Call initialize() first.");
    }

    private native int completeActivity(Object obj);

    private native int getMetrics(Object obj, ArrayList<ActivityMetrics> arrayList);

    private native Object initializeNativeAlgoObject();

    private native int startActivity(Object obj, double d, int i, int i2);

    private native void uninitializeNativeAlgoObject(Object obj);

    public void addEvents(ActivityEvent[] activityEventArr) throws Exception {
        checkInitialization();
        int addEvents = addEvents(this.algoObject, activityEventArr);
        if (addEvents == 0) {
            return;
        }
        throw new Exception(GeneratedOutlineSupport1.outline49("Failed to add events, error = ", addEvents));
    }

    public void completeActivity() throws Exception {
        checkInitialization();
        int completeActivity = completeActivity(this.algoObject);
        if (completeActivity == 0) {
            return;
        }
        throw new Exception(GeneratedOutlineSupport1.outline49("Failed to complete activity, error = ", completeActivity));
    }

    protected void finalize() {
        Object obj = this.algoObject;
        if (obj != null) {
            uninitializeNativeAlgoObject(obj);
        }
    }

    public ActivityMetrics[] getMetrics() throws Exception {
        if (this.algoObject == null) {
            return null;
        }
        ArrayList<ActivityMetrics> arrayList = new ArrayList<>();
        int metrics = getMetrics(this.algoObject, arrayList);
        if (metrics == 0) {
            return (ActivityMetrics[]) arrayList.toArray(new ActivityMetrics[0]);
        }
        throw new Exception(GeneratedOutlineSupport1.outline49("Failed to get metrics, error = ", metrics));
    }

    public void startActivity(BiometricProfile biometricProfile, UnitSystem unitSystem) throws Exception {
        startActivity(biometricProfile.height, biometricProfile.sex, unitSystem);
    }

    public void startActivity(double d, BiometricProfile.Sex sex, UnitSystem unitSystem) throws Exception {
        checkInitialization();
        int startActivity = startActivity(this.algoObject, d, sex.ordinal(), unitSystem.ordinal());
        if (startActivity == 0) {
            return;
        }
        throw new Exception(GeneratedOutlineSupport1.outline49("Failed to add events, error = ", startActivity));
    }
}
