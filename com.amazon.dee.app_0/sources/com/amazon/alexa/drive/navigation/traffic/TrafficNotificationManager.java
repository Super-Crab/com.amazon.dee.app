package com.amazon.alexa.drive.navigation.traffic;

import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.device.api.DeviceInformationException;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.navigation.location.ISO8601TimeSupplier;
import com.amazon.alexa.drive.util.IdentityUtils;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.protocols.service.api.ComponentRegistry;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;
/* loaded from: classes7.dex */
public class TrafficNotificationManager {
    public static final String CLIENT_ID = "AlexaMobileApp_Android_Drive_Mode";
    public static final String CONTENT_TYPE = "Content-Type";
    private static final String TAG = "TrafficNotificationManager";
    private static final String ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZZZZZ";
    private final NavigationMetrics mNavigationMetrics;
    private final ISO8601TimeSupplier mTimeSupplier = new ISO8601TimeSupplier(ZONED_DATE_TIME_FORMAT, TimeZone.getDefault());
    private TrafficNotificationAsyncFactory mTrafficNotificationAsyncFactory = new TrafficNotificationAsyncFactory();

    /* loaded from: classes7.dex */
    public interface TrafficNotificationRequestCallback {
        void onCommuteDestinationReceived(GetCommuteDestinationResponse getCommuteDestinationResponse);

        void onCommuteDetailsReceived(GetCommuteDetailsResponse getCommuteDetailsResponse);

        void onSendCommuteNotificationError();
    }

    public TrafficNotificationManager(NavigationMetrics navigationMetrics) {
        this.mNavigationMetrics = navigationMetrics;
    }

    @VisibleForTesting
    String getRandomGeneratedUUID() {
        return UUID.randomUUID().toString();
    }

    public void requestTrafficDestination(TrafficNotificationRequestCallback trafficNotificationRequestCallback) {
        GetCommuteDestinationRequest getCommuteDestinationRequest;
        UserIdentity userFromIdentityService = IdentityUtils.getUserFromIdentityService();
        DeviceInformation deviceInformation = (DeviceInformation) GeneratedOutlineSupport1.outline21(DeviceInformation.class);
        Preconditions.checkNotNull(deviceInformation);
        try {
            getCommuteDestinationRequest = GetCommuteDestinationRequest.builder().setRequestId(getRandomGeneratedUUID()).setClientId(CLIENT_ID).setCustomerId(userFromIdentityService.getId()).setPersonId(IdentityUtils.getPersonIdFromUserIdentity(userFromIdentityService)).setDeviceSerialNumber(deviceInformation.getDeviceSerialNumber()).setDeviceType(deviceInformation.getDeviceType()).setZonedDateTime(this.mTimeSupplier.getTime(Calendar.getInstance().getTime())).build();
        } catch (DeviceInformationException unused) {
            Log.e(TAG, "Error getting commute destination request");
            getCommuteDestinationRequest = null;
        }
        String str = TAG;
        Log.i(str, "requestTrafficDestination | request: " + getCommuteDestinationRequest);
        this.mTrafficNotificationAsyncFactory.createGetCommuteDestinationAsyncTask(trafficNotificationRequestCallback, getCommuteDestinationRequest, this.mNavigationMetrics).execute(new Void[0]);
    }

    public void requestTrafficDetails(TrafficNotificationRequestCallback trafficNotificationRequestCallback, String str) {
        this.mTrafficNotificationAsyncFactory.createGetCommuteDetailsAsyncTask(trafficNotificationRequestCallback, GetCommuteDetailsRequest.builder().setRequestId(getRandomGeneratedUUID()).setClientId(CLIENT_ID).setCorrelationId(str).build(), this.mNavigationMetrics).execute(new Void[0]);
    }

    public void requestTrafficUpdate(TrafficNotificationRequestCallback trafficNotificationRequestCallback, String str, String str2) {
        Log.i(TAG, "traffic request updated");
        try {
            UserIdentity userFromIdentityService = IdentityUtils.getUserFromIdentityService();
            DeviceInformation deviceInformation = (DeviceInformation) ComponentRegistry.getInstance().get(DeviceInformation.class).orNull();
            Preconditions.checkNotNull(deviceInformation);
            this.mTrafficNotificationAsyncFactory.createSendCommuteNotificationAsyncTask(trafficNotificationRequestCallback, SendCommuteNotificationRequest.builder().setRequestId(getRandomGeneratedUUID()).setClientId(CLIENT_ID).setPersonId(IdentityUtils.getPersonIdFromUserIdentity(userFromIdentityService)).setCustomerId(userFromIdentityService.getId()).setDeviceSerialNumber(deviceInformation.getDeviceSerialNumber()).setDeviceType(deviceInformation.getDeviceType()).setCommuteDestinationLabel(str).setCommuteDestinationType(str2).build(), this.mNavigationMetrics).execute(new Void[0]);
        } catch (DeviceInformationException e) {
            String str3 = TAG;
            Log.e(str3, "Error requesting traffic update | exception: " + e);
        }
    }

    void setTrafficNotificationAsyncFactory(TrafficNotificationAsyncFactory trafficNotificationAsyncFactory) {
        this.mTrafficNotificationAsyncFactory = trafficNotificationAsyncFactory;
    }
}
