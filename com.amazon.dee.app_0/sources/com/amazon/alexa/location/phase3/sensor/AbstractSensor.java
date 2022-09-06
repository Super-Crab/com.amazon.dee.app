package com.amazon.alexa.location.phase3.sensor;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.location.phase3.LocationSignal;
import com.amazon.alexa.location.phase3.SensorController;
import com.amazon.alexa.location.utils.MetricsUtil;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
/* loaded from: classes9.dex */
public abstract class AbstractSensor {
    protected final LazyComponent<Mobilytics> mobilytics;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSensor(@NonNull LazyComponent<Mobilytics> lazyComponent) {
        this.mobilytics = lazyComponent;
    }

    public boolean canHandleIntent(@Nullable Intent intent) {
        return intent != null && getCorrespondingIntentActions().contains(intent.getAction());
    }

    @NonNull
    public abstract Set<String> getCorrespondingIntentActions();

    @NonNull
    protected abstract String getSensorName();

    public List<? extends LocationSignal> processInput(@NonNull Intent intent) {
        if (!canHandleIntent(intent)) {
            return new ArrayList(0);
        }
        this.mobilytics.mo10268get().recordOccurrence(String.format("%s_invoked", getSensorName()), true, "location", MetricsUtil.buildSubComponentName("location", "phase3"), OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        return processInternal(intent);
    }

    protected abstract List<? extends LocationSignal> processInternal(@NonNull Intent intent);

    protected abstract void startInternal(SensorController.SensorState sensorState);

    protected abstract void stopInternal();

    public void toggleSensorState(SensorController.SensorState sensorState) {
        if (!SensorController.SensorState.OFF.equals(sensorState) && !SensorController.SensorState.DEFAULT.equals(sensorState)) {
            startInternal(sensorState);
            this.mobilytics.mo10268get().recordOccurrence(String.format("%s_started", getSensorName()), true, "location", MetricsUtil.buildSubComponentName("location", "phase3"), OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
            return;
        }
        stopInternal();
        this.mobilytics.mo10268get().recordOccurrence(String.format("%s_stopped", getSensorName()), true, "location", MetricsUtil.buildSubComponentName("location", "phase3"), OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }
}
