package com.amazon.deecomms.common;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.DefaultCommsUIDelegate;
import com.amazon.deecomms.api.CommsDelegateBase;
import com.amazon.deecomms.api.CommsFeature;
import com.amazon.deecomms.api.CommsUIDelegateBase;
import com.amazon.deecomms.api.metrics.CommsMetric;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.metrics.TimerMetric;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
import com.amazon.identity.auth.device.api.MAPAccountManager;
/* loaded from: classes12.dex */
public class ApplicationManager {
    @NonNull
    private final CommsDelegateBase commsDelegate;
    @NonNull
    private final Context context;
    @NonNull
    private CommsUIDelegateBase visualDelegate = COMMS_UI_DELEGATE_NON_NULL;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ApplicationManager.class);
    private static final CommsUIDelegateBase COMMS_UI_DELEGATE_NON_NULL = new DefaultCommsUIDelegate();

    public ApplicationManager(@NonNull Context context, @NonNull CommsDelegateBase commsDelegateBase) {
        this.context = context;
        this.commsDelegate = commsDelegateBase;
    }

    public void addHomeToBackStackIfEmpty() {
        this.visualDelegate.addHomeToBackStackIfEmpty();
    }

    public void appLogout() {
        this.visualDelegate.signOut();
    }

    public void callEnded() {
        this.commsDelegate.callEnded();
    }

    public void commsLogout() {
        this.commsDelegate.commsLogout();
    }

    public void configurePageForFragment(FragmentRequirements fragmentRequirements) {
        this.visualDelegate.configurePageForFragment(fragmentRequirements);
    }

    public void fragmentNavigationRequested(CommsView commsView, Fragment fragment, Bundle bundle, boolean z) {
        this.visualDelegate.fragmentNavigationRequested(commsView, fragment, bundle, z);
    }

    public MAPAccountManager getAccountManager() {
        return this.commsDelegate.getAccountManager();
    }

    @NonNull
    public String getDeviceTypeId() {
        String deviceTypeId = this.commsDelegate.getDeviceTypeId();
        if (TextUtils.isEmpty(deviceTypeId)) {
            LOG.e("commsDelegate was null or returned an invalid deviceTypeId. Using default value");
            return "A1WS7SWYYT4UG4";
        }
        return deviceTypeId;
    }

    public void incomingCall() {
        this.commsDelegate.incomingCall();
    }

    public boolean isCurrentlyOnCommsScreen() {
        return this.visualDelegate.isCurrentlyOnCommsScreen();
    }

    public boolean isFeatureEnabled(CommsFeature commsFeature) {
        return this.commsDelegate.isFeatureEnabled(commsFeature);
    }

    public void loadingComplete(@NonNull CommsView commsView) {
        this.commsDelegate.loadingComplete(commsView);
    }

    public void loadingCompleteOobe() {
        this.commsDelegate.loadingCompleteOobe();
    }

    public void navigateBackward() {
        this.visualDelegate.navigateBackward();
    }

    public void navigateHome() {
        this.visualDelegate.navigateHome();
    }

    public void navigateSettings() {
        this.visualDelegate.navigateSettings();
    }

    public void navigateToView(CommsView commsView, Bundle bundle, boolean z) {
        if (!bundle.getBoolean(Constants.ORIGINATED_FROM_VOICE, false) || !this.commsDelegate.startMainActivity(this.context, commsView)) {
            this.visualDelegate.navigateToView(commsView, bundle, z);
        }
    }

    public void navigateUpward() {
        this.visualDelegate.navigateUpward();
    }

    public void recordCounterMetric(CounterMetric counterMetric) {
        this.commsDelegate.recordCounterMetric(counterMetric);
    }

    public void recordTimerMetric(TimerMetric timerMetric) {
        this.commsDelegate.recordTimerMetric(timerMetric);
    }

    public boolean removeCommsRoutes() {
        return this.visualDelegate.removeCommsRoutesFromBackstack();
    }

    public void setIndicatorIconVisible(boolean z) {
        this.visualDelegate.setIndicatorIconVisible(z);
    }

    public void setUIDelegate(CommsUIDelegateBase commsUIDelegateBase) {
        if (commsUIDelegateBase == null) {
            this.visualDelegate = COMMS_UI_DELEGATE_NON_NULL;
        } else {
            this.visualDelegate = commsUIDelegateBase;
        }
    }

    public void setUserName(String str) {
        this.visualDelegate.setUserName(str);
    }

    public void startTimerMetric(CommsMetric commsMetric) {
        CommsLogger commsLogger = LOG;
        commsLogger.v("startTimerMetric: " + commsMetric);
        this.commsDelegate.startTimerMetric(commsMetric);
    }

    public void stopTimerMetric(CommsMetric commsMetric) {
        CommsLogger commsLogger = LOG;
        commsLogger.v("stopTimerMetric: " + commsMetric);
        this.commsDelegate.stopTimerMetric(commsMetric);
    }
}
