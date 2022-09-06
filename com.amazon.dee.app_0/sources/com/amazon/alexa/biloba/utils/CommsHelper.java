package com.amazon.alexa.biloba.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.metrics.BilobaMetricsService;
import com.amazon.alexa.biloba.metrics.BilobaViewWithMetrics;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.routing.Routes;
import com.amazon.alexa.biloba.storage.CareActorsStore;
import com.amazon.alexa.biloba.view.dashboard.CommsHandler;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class CommsHelper extends BilobaViewWithMetrics {
    private static final String TAG = "CommsHelper";
    @Inject
    Lazy<CareActorsStore> careActorsStore;
    @Inject
    Lazy<CommsHandler> commsHandler;
    @VisibleForTesting
    AlertDialog errorAlertDialog;
    @Inject
    Lazy<RoutingService> routingService;
    @VisibleForTesting
    AlertDialog setupAlertDialog;

    @Inject
    public CommsHelper() {
    }

    private void displayErrorDialog(Context context, String str) {
        if (this.errorAlertDialog == null) {
            this.errorAlertDialog = new AlertDialog.Builder(context).setMessage(context.getString(R.string.comms_dialog_error_body, CareActorUtil.getDisplayName(this.careActorsStore.mo358get().getCareContact().getValue()))).setPositiveButton(context.getString(R.string.ok), (DialogInterface.OnClickListener) null).create();
        }
        this.errorAlertDialog.setTitle(str);
        this.errorAlertDialog.show();
    }

    private void displaySetupDialog(Context context, String str) {
        if (this.setupAlertDialog == null) {
            this.setupAlertDialog = new AlertDialog.Builder(context).setMessage(context.getString(R.string.comms_dialog_body)).setPositiveButton(context.getString(R.string.comms_dialog_setup), new DialogInterface.OnClickListener() { // from class: com.amazon.alexa.biloba.utils.-$$Lambda$CommsHelper$bfNFaV1aoITR1E1dtiUbXceuMfA
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    CommsHelper.this.lambda$displaySetupDialog$0$CommsHelper(dialogInterface, i);
                }
            }).setNegativeButton(context.getString(R.string.comms_dialog_close), (DialogInterface.OnClickListener) null).create();
        }
        this.setupAlertDialog.setTitle(str);
        this.setupAlertDialog.show();
    }

    private void handleCommsNotProvisioned(Context context, String str) {
        String str2 = TAG;
        LogUtils.d(str2, "Comms is not provisioned for: " + str);
        if (this.careActorsStore.mo358get().isCommsEnabledForCurrentActor() && !isCareGiver()) {
            displayErrorDialog(context, str);
        } else {
            displaySetupDialog(context, str);
        }
    }

    private boolean isCareGiver() {
        return this.careActorsStore.mo358get().getIsCareGiver().getValue() == Boolean.TRUE;
    }

    private void publishCommsEvent(String str, String str2) {
        this.commsHandler.mo358get().withMessage(str).withPayload(str2).publish();
    }

    public /* synthetic */ void lambda$displaySetupDialog$0$CommsHelper(DialogInterface dialogInterface, int i) {
        BilobaRouteUtil.routeToWithAddToBackStack(this.routingService.mo358get(), Routes.BILOBA_EMERGENCY_CONTACT);
    }

    public void onCallClicked(Context context) {
        LogUtils.d(TAG, "Call Button clicked");
        if (this.careActorsStore.mo358get().isCommsProvisionedWithContactId()) {
            String slicedCareContactContactId = this.careActorsStore.mo358get().getSlicedCareContactContactId();
            recordCommsCall(MetricsConstants.UserInteractionMetrics.DASHBOARD_COMMS_CALL_BUTTON, "");
            String str = TAG;
            LogUtils.d(str, "Initializing Call with Contact ID :" + slicedCareContactContactId);
            publishCommsEvent(CommsHandler.INITIATE_CALL, slicedCareContactContactId);
            return;
        }
        handleCommsNotProvisioned(context, context.getString(R.string.comms_dialog_title_call));
    }

    public void onDropinClicked(Context context) {
        LogUtils.d(TAG, "Drop-in Button clicked");
        if (this.careActorsStore.mo358get().isCommsProvisionedWithCommsId()) {
            String careContactCommsId = this.careActorsStore.mo358get().getCareContactCommsId();
            recordCommsCall(MetricsConstants.UserInteractionMetrics.DASHBOARD_COMMS_DROP_IN_BUTTON, "");
            String str = TAG;
            LogUtils.d(str, "Initializing Drop-in with Comms ID :" + careContactCommsId);
            publishCommsEvent(CommsHandler.INITITATE_DROP_IN, careContactCommsId);
            return;
        }
        handleCommsNotProvisioned(context, context.getString(R.string.comms_dialog_title_dropIn));
    }

    public void onMessageClicked(Context context) {
        LogUtils.d(TAG, "Share Button clicked");
        if (this.careActorsStore.mo358get().isCommsProvisionedWithCommsId()) {
            String careContactCommsId = this.careActorsStore.mo358get().getCareContactCommsId();
            recordCommsMessage(MetricsConstants.UserInteractionMetrics.DASHBOARD_COMMS_MESSAGE_BUTTON, "");
            String str = TAG;
            LogUtils.d(str, "Initializing Share with Comms ID :" + careContactCommsId);
            this.routingService.mo358get().route("v2/comms/conversation").with("id", careContactCommsId).addToBackStack().navigate();
            return;
        }
        handleCommsNotProvisioned(context, context.getString(R.string.comms_dialog_title_share));
    }

    @VisibleForTesting
    CommsHelper(Lazy<CareActorsStore> lazy, Lazy<RoutingService> lazy2, Lazy<CommsHandler> lazy3, BilobaMetricsService bilobaMetricsService) {
        this.careActorsStore = lazy;
        this.routingService = lazy2;
        this.commsHandler = lazy3;
        this.bilobaMetricsService = bilobaMetricsService;
    }
}
