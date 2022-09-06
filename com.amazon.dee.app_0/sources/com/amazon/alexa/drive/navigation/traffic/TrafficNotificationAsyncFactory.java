package com.amazon.alexa.drive.navigation.traffic;

import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.navigation.traffic.TrafficNotificationManager;
/* loaded from: classes7.dex */
public class TrafficNotificationAsyncFactory {
    public GetCommuteDestinationAsyncTask createGetCommuteDestinationAsyncTask(TrafficNotificationManager.TrafficNotificationRequestCallback trafficNotificationRequestCallback, GetCommuteDestinationRequest getCommuteDestinationRequest, NavigationMetrics navigationMetrics) {
        return new GetCommuteDestinationAsyncTask(trafficNotificationRequestCallback, getCommuteDestinationRequest, navigationMetrics);
    }

    public GetCommuteDetailsAsyncTask createGetCommuteDetailsAsyncTask(TrafficNotificationManager.TrafficNotificationRequestCallback trafficNotificationRequestCallback, GetCommuteDetailsRequest getCommuteDetailsRequest, NavigationMetrics navigationMetrics) {
        return new GetCommuteDetailsAsyncTask(trafficNotificationRequestCallback, getCommuteDetailsRequest, navigationMetrics);
    }

    public SendCommuteNotificationAsyncTask createSendCommuteNotificationAsyncTask(TrafficNotificationManager.TrafficNotificationRequestCallback trafficNotificationRequestCallback, SendCommuteNotificationRequest sendCommuteNotificationRequest, NavigationMetrics navigationMetrics) {
        return new SendCommuteNotificationAsyncTask(trafficNotificationRequestCallback, sendCommuteNotificationRequest, navigationMetrics);
    }
}
