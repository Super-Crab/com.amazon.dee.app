package com.amazon.alexa.location.networking.alps.models;

import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Calendar;
import java.util.Collections;
/* loaded from: classes9.dex */
public final class ReportLocationsRequestGenerator {
    private static final String TAG = "ReportLocationsRequestGenerator";

    private ReportLocationsRequestGenerator() {
    }

    @Nullable
    public static ReportLocationsRequestBody generateReportLocationsRequest(double d, double d2, double d3, @NonNull String str, ISO8601TimeSupplier iSO8601TimeSupplier) throws DeviceInformationException {
        Geolocation build = Geolocation.builder().setCoordinate(Coordinate.builder().setLatitudeInDegrees(d).setLongitudeInDegrees(d2).setAccuracyInMeters(d3).build()).build();
        DeviceInformation deviceInformation = (DeviceInformation) GeneratedOutlineSupport1.outline21(DeviceInformation.class);
        if (deviceInformation == null) {
            Log.e(TAG, "device info cannot be null");
            return null;
        } else if (TextUtils.isEmpty(str)) {
            Log.e(TAG, "Cause cannot be empty");
            return null;
        } else {
            return ReportLocationsRequestBody.create(Collections.singletonList(TrackableDevicesLocation.builder().setGeolocation(build).setTrackableDevices(Collections.singletonList(TrackableDevice.builder().setDeviceInfo(DeviceInfo.builder().setDeviceSerialNumber(deviceInformation.getDeviceSerialNumber()).setDeviceType(deviceInformation.getDeviceType()).build()).setEstimatedProximityDistance("UNKNOWN").setCause(str).build())).setTimestamp(iSO8601TimeSupplier.getTime(Calendar.getInstance().getTime())).build()));
        }
    }
}
