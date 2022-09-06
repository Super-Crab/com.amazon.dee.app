package com.amazon.alexa.biloba.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AlertDialog;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.view.dashboard.RemoteManagementInactivityHandler;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class RemoteAssistHelper {
    private static final String RM_ROUTING_CONTEXT_KEY = "routingContext";
    private static final String TAG = "RemoteAssistHelper";
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<DeviceInformation> deviceInformation;
    @VisibleForTesting
    AlertDialog fireOSDialog;
    @Inject
    Lazy<BilobaMetricsService> metricService;
    @Inject
    Lazy<RemoteManagementInactivityHandler> remoteManagementInactivityHandler;
    @Inject
    Lazy<RoutingService> routingService;

    @Inject
    public RemoteAssistHelper() {
    }

    private void displayFireOSDialog(Context context) {
        this.metricService.mo358get().recordUserView(MetricsConstants.UserInteractionMetrics.RM_ASSIST_FOS_COMING_SOON, "");
        if (this.fireOSDialog == null) {
            this.fireOSDialog = new AlertDialog.Builder(context).setMessage(R.string.remote_management_fireos_popup_body).setTitle(R.string.remote_management_fireos_popup_title).setPositiveButton(R.string.remote_management_fireos_popup_button, (DialogInterface.OnClickListener) null).create();
        }
        LogUtils.d(TAG, "Showing FireOS dialog.");
        this.fireOSDialog.show();
    }

    public void startRemoteAssist(Context context) {
        this.metricService.mo358get().recordUserClick(MetricsConstants.UserInteractionMetrics.RM_ASSIST, MetricsConstants.CLICK_EVENT);
        if (this.deviceInformation.mo358get().isFireOS()) {
            displayFireOSDialog(context);
            return;
        }
        String remoteManagementRoutingContext = this.careActorsStore.mo358get().getRemoteManagementRoutingContext();
        if (TextUtils.isEmpty(remoteManagementRoutingContext)) {
            LogUtils.e(TAG, "RM routing context is empty.");
            return;
        }
        this.routingService.mo358get().route(Routes.REMOTE_MANAGEMENT_ROUTE).with(RM_ROUTING_CONTEXT_KEY, remoteManagementRoutingContext).addToBackStack().navigate();
        this.remoteManagementInactivityHandler.mo358get().startRMInactivityTimer(context);
    }

    @VisibleForTesting
    RemoteAssistHelper(Lazy<RoutingService> lazy, Lazy<DeviceInformation> lazy2, Lazy<RemoteManagementInactivityHandler> lazy3, Lazy<BilobaMetricsService> lazy4, Lazy<CareActorsStore> lazy5) {
        this.routingService = lazy;
        this.deviceInformation = lazy2;
        this.remoteManagementInactivityHandler = lazy3;
        this.metricService = lazy4;
        this.careActorsStore = lazy5;
    }
}
