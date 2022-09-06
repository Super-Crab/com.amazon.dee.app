package com.amazon.alexa.drive.navigation.location;

import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.navigation.location.Geolocation;
import com.amazon.alexa.drive.util.IdentityUtils;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.dee.sdk.iotsoftap.Constants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Collections;
import java.util.TimeZone;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes7.dex */
public class LocationPublisher {
    public static final String ACCESS_TOKEN_KEY = "x-amz-access-token";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String CONTENT_TYPE_JSON = "application/json; charset=utf-8";
    public static final String POST_REQUEST = "POST";
    private static final String TAG = "LocationPublisher";
    public static final int TIMEOUT_MS = 15000;
    private ReportLocationAsyncTask locationAsyncTask;
    private final ISO8601TimeSupplier mIso8601TimeSupplier = new ISO8601TimeSupplier("yyyy-MM-dd'T'HH:mm:ss'Z'", TimeZone.getTimeZone(Constants.UTC));
    private final NavigationMetrics mNavigationMetrics;

    /* loaded from: classes7.dex */
    public interface LocationStatusCallback {
        void onLocationFinished(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class ReportLocationAsyncTask extends AsyncTask<Void, Integer, ReportLocationsResponse> {
        private static final String TAG = ReportLocationAsyncTask.class.getSimpleName();
        private final WeakReference<LocationPublisher> mLocationPublisherReference;
        private final LocationStatusCallback mLocationStatusCallback;
        private final NavigationMetrics mNavigationMetrics;
        private final ReportLocationsRequest mReportLocationsRequest;

        ReportLocationAsyncTask(LocationPublisher locationPublisher, ReportLocationsRequest reportLocationsRequest, LocationStatusCallback locationStatusCallback, NavigationMetrics navigationMetrics) {
            this.mLocationPublisherReference = new WeakReference<>(locationPublisher);
            this.mReportLocationsRequest = reportLocationsRequest;
            this.mLocationStatusCallback = locationStatusCallback;
            this.mNavigationMetrics = navigationMetrics;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public ReportLocationsResponse doInBackground(Void... voidArr) {
            LocationPublisher locationPublisher = this.mLocationPublisherReference.get();
            if (locationPublisher == null) {
                return null;
            }
            try {
                HttpURLConnection httpsUrlConnection = locationPublisher.getHttpsUrlConnection();
                if (httpsUrlConnection != null) {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpsUrlConnection.getOutputStream());
                    byte[] bytes = this.mReportLocationsRequest.toJsonObject().toString().getBytes();
                    bufferedOutputStream.write(bytes, 0, bytes.length);
                    bufferedOutputStream.flush();
                    int responseCode = httpsUrlConnection.getResponseCode();
                    String str = TAG;
                    Log.i(str, "responseCode: " + responseCode);
                    String str2 = TAG;
                    Log.i(str2, "responseMessage: " + httpsUrlConnection.getResponseMessage());
                    String readLine = new BufferedReader(new InputStreamReader(httpsUrlConnection.getInputStream())).readLine();
                    if (readLine != null) {
                        String str3 = TAG;
                        Log.i(str3, "Output: " + readLine);
                        return ReportLocationsResponse.FACTORY.mo1239create(new JSONObject(readLine));
                    }
                    Log.e(TAG, "Error occurred when processing request");
                    return null;
                }
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline156("Error happened while tying to update location | Exception: ", e, TAG);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(ReportLocationsResponse reportLocationsResponse) {
            if (reportLocationsResponse == null) {
                this.mNavigationMetrics.logAls(NavigationMetrics.AlsApiInterface.REPORTLOCATIONS, "Failure");
                this.mLocationStatusCallback.onLocationFinished(false);
                return;
            }
            this.mNavigationMetrics.logAls(NavigationMetrics.AlsApiInterface.REPORTLOCATIONS, "Success");
            this.mLocationStatusCallback.onLocationFinished(true);
        }
    }

    public LocationPublisher(NavigationMetrics navigationMetrics) {
        this.mNavigationMetrics = navigationMetrics;
    }

    @Nullable
    private ReportLocationsRequest generateReportLocationsRequest(Location location) throws DeviceInformationException {
        Geolocation.Builder altitude = Geolocation.builder().setCoordinate(Coordinate.builder().setLatitudeInDegrees(location.getLatitude()).setLongitudeInDegrees(location.getLongitude()).setAccuracyInMeters(location.getAccuracy()).build()).setAltitude(Altitude.builder().setAltitudeInMeters(location.getAltitude()).setAccuracyInMeters(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR).build());
        int i = Build.VERSION.SDK_INT;
        altitude.setHeading(Heading.builder().setDirectionInDegrees(location.getBearingAccuracyDegrees()).setAccuracyInDegrees(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR).build()).setSpeed(Speed.builder().setSpeedInMetersPerSecond(location.getSpeedAccuracyMetersPerSecond()).setAccuracyInMetersPerSecond(FrostVideoEffectController.VIDEO_STRENGTH_CLEAR).build());
        Geolocation build = altitude.build();
        DeviceInformation deviceInformation = (DeviceInformation) GeneratedOutlineSupport1.outline21(DeviceInformation.class);
        if (deviceInformation == null) {
            Log.e(TAG, "device info cannot be null");
            return null;
        }
        return ReportLocationsRequest.create(Collections.singletonList(TrackableDevicesLocation.builder().setGeolocation(build).setTrackableDevices(Collections.singletonList(TrackableDevice.builder().setDeviceInfo(DeviceInfo.builder().setDeviceSerialNumber(deviceInformation.getDeviceSerialNumber()).setDeviceType(deviceInformation.getDeviceType()).build()).setEstimatedProximityDistance("UNKNOWN").setCause("TRAFFIC_NOTIFICATION_UPDATE").build())).setTimestamp(this.mIso8601TimeSupplier.getTime(Calendar.getInstance().getTime())).build()));
    }

    private String getReportLocationsEndpoint() {
        return new AlexaLocationEndpointFactory().getReportLocationsEndpoint();
    }

    HttpURLConnection getHttpsUrlConnection() {
        URL url;
        try {
            url = new URL(getReportLocationsEndpoint());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            url = null;
        }
        if (url == null) {
            return null;
        }
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("x-amz-access-token", IdentityUtils.getAccessToken());
            httpURLConnection.setRequestProperty("Content-Type", CONTENT_TYPE_JSON);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setDoOutput(true);
            return httpURLConnection;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public void publishLocation(Location location, @NonNull LocationStatusCallback locationStatusCallback) {
        if (location == null) {
            Log.e(TAG, "publishLocation | currentLocation is null");
            locationStatusCallback.onLocationFinished(false);
            return;
        }
        ReportLocationsRequest reportLocationsRequest = null;
        try {
            reportLocationsRequest = generateReportLocationsRequest(location);
        } catch (DeviceInformationException e) {
            String str = TAG;
            Log.e(str, "Error in generating report locations request: " + e);
        }
        if (reportLocationsRequest == null) {
            Log.e(TAG, "publishLocation | error is generating report locations request");
            locationStatusCallback.onLocationFinished(false);
            return;
        }
        this.locationAsyncTask = new ReportLocationAsyncTask(this, reportLocationsRequest, locationStatusCallback, this.mNavigationMetrics);
        this.locationAsyncTask.execute(new Void[0]);
    }
}
