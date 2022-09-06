package com.amazon.alexa.location.phase3.sensor.activity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.location.phase3.SensorController;
import com.amazon.alexa.location.phase3.sensor.AbstractSensor;
import com.amazon.alexa.location.phase3.sensor.SensorIntentService;
import com.amazon.alexa.location.utils.MobilyticsUtil;
import com.amazon.alexa.location.utils.PendingIntentCompat;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognition;
import com.google.android.gms.location.ActivityRecognitionApi;
import com.google.android.gms.location.ActivityRecognitionResult;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public class ActivitySensor extends AbstractSensor {
    @VisibleForTesting
    static final long DETECT_INTERVAL_MILLS = 600000;
    private static final String SENSOR_NAME = "activity_sensor";
    private final ActivityRecognitionApi activityRecognitionApi;
    private final Context context;
    private GoogleApiClient googleApiClient;
    private final PendingIntent pendingIntent;
    public static final String ACTION_NAME = "com.amazon.alexa.location.ACTION_ACTIVITY_CHANGED";
    private static final Set<String> TARGET_ACTIONS = Collections.singleton(ACTION_NAME);
    private static final String TAG = "ActivitySensor";
    private static final String COMPONENT_NAME = MobilyticsUtil.getComponentName(TAG);

    public ActivitySensor(@NonNull Context context, @NonNull LazyComponent<Mobilytics> lazyComponent) {
        this(context, lazyComponent, ActivityRecognition.ActivityRecognitionApi, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public static String formatGoogleApiSuspensionCause(int i) {
        return i != 1 ? i != 2 ? String.format(Locale.US, "Unknown(%d)", Integer.valueOf(i)) : "NETWORK_LOST" : "SERVICE_DISCONNECTED";
    }

    private boolean initialize() {
        if (this.googleApiClient == null) {
            this.googleApiClient = new GoogleApiClient.Builder(this.context).addApi(ActivityRecognition.API).addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() { // from class: com.amazon.alexa.location.phase3.sensor.activity.ActivitySensor.1
                @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
                public void onConnected(@Nullable Bundle bundle) {
                }

                @Override // com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks
                public void onConnectionSuspended(int i) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("googleApiClient connection lost: ");
                    outline107.append(ActivitySensor.formatGoogleApiSuspensionCause(i));
                    Log.e(ActivitySensor.TAG, outline107.toString());
                    ActivitySensor.this.googleApiClient.connect();
                }
            }).addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() { // from class: com.amazon.alexa.location.phase3.sensor.activity.-$$Lambda$ActivitySensor$znh5DVrl3mKEoKU9W7UqvMSADnk
                @Override // com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
                public final void onConnectionFailed(ConnectionResult connectionResult) {
                    ActivitySensor.this.lambda$initialize$2$ActivitySensor(connectionResult);
                }
            }).build();
        }
        return this.googleApiClient.blockingConnect(10L, TimeUnit.SECONDS).isSuccess();
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    @NonNull
    public Set<String> getCorrespondingIntentActions() {
        return TARGET_ACTIONS;
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    @NonNull
    protected String getSensorName() {
        return SENSOR_NAME;
    }

    public /* synthetic */ void lambda$initialize$2$ActivitySensor(ConnectionResult connectionResult) {
        String str = COMPONENT_NAME;
        this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GMSCLIENT_CONNECTION_FAILED, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
    }

    public /* synthetic */ void lambda$startInternal$0$ActivitySensor(Status status) {
        if (!status.isSuccess()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("requestActivityUpdate failed: ");
            outline107.append(status.toString());
            Log.e(TAG, outline107.toString());
            String str = COMPONENT_NAME;
            this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GMSCLIENT_REQUEST_FAILED, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
    }

    public /* synthetic */ void lambda$stopInternal$1$ActivitySensor(Status status) {
        if (!status.isSuccess()) {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("removeActivityUpdates failed: ");
            outline107.append(status.toString());
            Log.e(TAG, outline107.toString());
            String str = COMPONENT_NAME;
            this.mobilytics.mo10268get().recordOccurrence(MobilyticsUtil.MetricsID.GMSCLIENT_REQUEST_FAILED, true, str, str, OwnerIdentifier.APP_LOCATION_LIBRARIES_ANDROID);
        }
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    protected List<ActivitySensorInput> processInternal(@NonNull Intent intent) {
        if (ACTION_NAME.equals(intent.getAction())) {
            ActivityRecognitionResult extractResult = ActivityRecognitionResult.extractResult(intent);
            if (extractResult != null) {
                return Collections.singletonList(ActivitySensorInput.fromActicityRecognitionResult(extractResult));
            }
        } else {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("processInternal: unexpected action ");
            outline107.append(intent.getAction());
            Log.e(TAG, outline107.toString());
        }
        return Collections.emptyList();
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    protected void startInternal(SensorController.SensorState sensorState) {
        if (initialize()) {
            this.activityRecognitionApi.requestActivityUpdates(this.googleApiClient, 600000L, this.pendingIntent).setResultCallback(new ResultCallback() { // from class: com.amazon.alexa.location.phase3.sensor.activity.-$$Lambda$ActivitySensor$U8cq2p7-21sIECHE4w2hTHogyU8
                @Override // com.google.android.gms.common.api.ResultCallback
                public final void onResult(Result result) {
                    ActivitySensor.this.lambda$startInternal$0$ActivitySensor((Status) result);
                }
            });
        }
    }

    @Override // com.amazon.alexa.location.phase3.sensor.AbstractSensor
    protected void stopInternal() {
        if (initialize()) {
            this.activityRecognitionApi.removeActivityUpdates(this.googleApiClient, this.pendingIntent).setResultCallback(new ResultCallback() { // from class: com.amazon.alexa.location.phase3.sensor.activity.-$$Lambda$ActivitySensor$hxl1gyK8BtNG2t1wZFGJuufA4Lo
                @Override // com.google.android.gms.common.api.ResultCallback
                public final void onResult(Result result) {
                    ActivitySensor.this.lambda$stopInternal$1$ActivitySensor((Status) result);
                }
            });
        }
    }

    @VisibleForTesting
    ActivitySensor(@NonNull Context context, @NonNull LazyComponent<Mobilytics> lazyComponent, @NonNull ActivityRecognitionApi activityRecognitionApi, @Nullable GoogleApiClient googleApiClient) {
        super(lazyComponent);
        this.context = context;
        this.activityRecognitionApi = activityRecognitionApi;
        this.googleApiClient = googleApiClient;
        Intent intent = new Intent(context, SensorIntentService.class);
        intent.setAction(ACTION_NAME);
        this.pendingIntent = PendingIntentCompat.getForegroundService(context, 12, intent, 134217728);
    }
}
