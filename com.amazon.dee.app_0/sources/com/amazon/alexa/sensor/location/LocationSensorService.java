package com.amazon.alexa.sensor.location;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.amazon.alexa.sensor.R;
import com.amazon.alexa.sensor.api.location.LocationSensorAccess;
import com.amazon.alexa.sensor.api.metrics.SensorAccessMetricsRecorder;
import com.amazon.alexa.sensor.api.metrics.events.CounterEvent;
import com.amazon.alexa.sensor.location.Metrics;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationResult;
/* loaded from: classes10.dex */
public class LocationSensorService extends Service {
    public static final String ACTION_REPORT_LOCATION = "com.amazon.alexa.sensor.location.action.REPORT_LOCATION";
    public static final String ACTION_RUN_SENSOR_SERVICE = "com.amazon.alexa.sensor.location.action.RUN_SENSOR_SERVICE";
    public static final String ACTION_RUN_SENSOR_SERVICE_ON_FOREGROUND = "com.amazon.alexa.sensor.location.action.RUN_SENSOR_SERVICE_ON_FOREGROUND";
    public static final String CHANNEL_ID = "LocationSensorNotificationChannel";
    private static final int NOTIFICATION_ID = 537202347;
    private static final int REQUEST_CODE_REPORT_LOCATION = 537202357;
    private static final String TAG = "LocationSensorService";
    boolean foregroundAcquired = false;

    public static PendingIntent buildLocationReportIntent(@NonNull Context context) {
        Intent intent = new Intent(context, LocationSensorService.class);
        intent.setAction(ACTION_REPORT_LOCATION);
        return PendingIntent.getService(context, REQUEST_CODE_REPORT_LOCATION, intent, 134217728);
    }

    @RequiresApi(26)
    private static Notification buildNotification(@NonNull Context context) {
        ((NotificationManager) context.getSystemService("notification")).createNotificationChannel(new NotificationChannel(CHANNEL_ID, context.getString(R.string.location_sensor_notification_channel_name), 2));
        return new Notification.Builder(context, CHANNEL_ID).setSmallIcon(R.drawable.ic_alexa).setContentTitle(context.getString(R.string.location_sensor_notification_content_title)).setContentText(context.getString(R.string.location_sensor_notification_content_text)).build();
    }

    public static boolean startSensorService(@NonNull Context context, boolean z) {
        Intent intent = new Intent(context, LocationSensorService.class);
        if (z) {
            try {
                int i = Build.VERSION.SDK_INT;
                intent.setAction(ACTION_RUN_SENSOR_SERVICE_ON_FOREGROUND);
                context.startForegroundService(intent);
                return true;
            } catch (Exception e) {
                Log.e(TAG, "Failed to start service in foreground; location tracking will start in background", e);
            }
        }
        intent.setAction(ACTION_RUN_SENSOR_SERVICE);
        context.startService(intent);
        return false;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("This service does not support binding, use startService() instead");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        String action = intent.getAction();
        if (ACTION_RUN_SENSOR_SERVICE_ON_FOREGROUND.equals(action)) {
            try {
                if (Build.VERSION.SDK_INT >= 29) {
                    startForeground(NOTIFICATION_ID, buildNotification(this), 8);
                    this.foregroundAcquired = true;
                } else {
                    int i3 = Build.VERSION.SDK_INT;
                    startForeground(NOTIFICATION_ID, buildNotification(this));
                    this.foregroundAcquired = true;
                }
                return 2;
            } catch (Exception e) {
                Log.e(TAG, "Failed to start service in foreground; location tracking will start in background", e);
                return 2;
            }
        } else if (ACTION_RUN_SENSOR_SERVICE.equals(action)) {
            if (!this.foregroundAcquired) {
                return 2;
            }
            Log.i(TAG, "No longer required to run in foreground; releasing service from foreground");
            stopForeground(true);
            this.foregroundAcquired = false;
            return 2;
        } else if (!ACTION_REPORT_LOCATION.equals(action)) {
            return 2;
        } else {
            processLocationReport(intent);
            return 2;
        }
    }

    @VisibleForTesting
    void processLocationReport(@NonNull Intent intent) {
        SensorAccessMetricsRecorder sensorAccessMetricsRecorder = (SensorAccessMetricsRecorder) ComponentRegistry.getInstance().getLazy(SensorAccessMetricsRecorder.class).mo10268get();
        LocationSensorAccess locationSensorAccess = (LocationSensorAccess) ComponentRegistry.getInstance().getLazy(LocationSensorAccess.class).mo10268get();
        if (!(locationSensorAccess instanceof LocationSensorAccessor)) {
            sensorAccessMetricsRecorder.recordEvent(Metrics.Events.INCOMPATIBLE_ACCESSOR, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES);
            return;
        }
        LocationSensorAccessor locationSensorAccessor = (LocationSensorAccessor) locationSensorAccess;
        LocationResult extractResult = LocationResult.extractResult(intent);
        boolean z = true;
        sensorAccessMetricsRecorder.recordOccurrence(Metrics.Events.LOCATION_RESULTS_RECEIVED, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES, extractResult != null);
        if (extractResult != null) {
            CounterEvent counterEvent = new CounterEvent(Metrics.Events.LOCATIONS_RECEIVED, Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES), Metrics.SubComponents.LOCATION_UPDATES);
            counterEvent.setValue(extractResult.getLocations().size());
            sensorAccessMetricsRecorder.recordCounter(counterEvent);
            for (Location location : extractResult.getLocations()) {
                locationSensorAccessor.didReceiveLocation(location);
            }
        }
        LocationAvailability extractLocationAvailability = LocationAvailability.extractLocationAvailability(intent);
        String fullComponentName = Metrics.fullComponentName(Metrics.SubComponents.LOCATION_UPDATES);
        if (extractLocationAvailability == null) {
            z = false;
        }
        sensorAccessMetricsRecorder.recordOccurrence(Metrics.Events.LOCATION_AVAILABILITIES_RECEIVED, fullComponentName, Metrics.SubComponents.LOCATION_UPDATES, z);
    }
}
