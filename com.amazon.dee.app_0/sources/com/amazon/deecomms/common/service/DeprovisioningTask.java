package com.amazon.deecomms.common.service;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.EventBusEventType;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetAccounts;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.remoteConfig.PeriodicConfigSyncUtils;
import com.amazon.deecomms.util.ThreadUtils;
import java.io.IOException;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes12.dex */
public class DeprovisioningTask {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DeprovisioningTask.class);
    @NonNull
    private final CommsIdentityManager commsIdentityManager;
    @NonNull
    private final Context context;
    @NonNull
    private final EventBus eventBus;

    public DeprovisioningTask(@NonNull Context context, @NonNull CommsIdentityManager commsIdentityManager, @NonNull EventBus eventBus) {
        this.context = context;
        this.commsIdentityManager = commsIdentityManager;
        this.eventBus = eventBus;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleDeprovisioning() {
        ThreadUtils.checkNotMainThread();
        if (CommsProvisionStatus.DEPROVISIONED.equals(this.commsIdentityManager.getProvisionStatus(true, "handleDeprovisioning", false))) {
            LOG.d("User is already deprovisioned, ignoring");
            return;
        }
        LOG.i("Starting user deprovisioning...");
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.USER_DEPROVISIONED);
        try {
            List<Person> execute = new GetAccounts().execute();
            if (execute != null) {
                String directedId = this.commsIdentityManager.getDirectedId("handleDeprovisioning", false);
                for (Person person : execute) {
                    if (person.directedId.equals(directedId)) {
                        boolean isEmpty = TextUtils.isEmpty(person.phoneNumber);
                        LOG.i("Deprovisioning user.");
                        CommsLogger commsLogger = LOG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("Is phone number available in the account: ");
                        sb.append(!isEmpty);
                        commsLogger.i(sb.toString());
                        this.commsIdentityManager.deprovisionCurrentUser("DeprovisioningTask", isEmpty, person, false);
                        handleProvisioningChange();
                    }
                }
                return;
            }
            LOG.e("Get accounts call returned null");
        } catch (ServiceException | IOException e) {
            LOG.e("Unable to retrieve the person list after deprovisioning", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void handleProvisioningChange() {
        LOG.d("Stopping the calling service");
        Context context = this.context;
        context.stopService(new Intent(context, DeviceCallingAndroidService.class));
        LOG.d("Clearing comms information");
        Utils.clearCommsInfo(false);
        this.eventBus.publish(new Message.Builder().setEventType(EventBusEventType.USER_DEPROVISIONED.toString()).build());
        MetricsHelper.recordCounterMetric(CounterMetric.generateOperational("comms.eventbus.event.published." + EventBusEventType.USER_DEPROVISIONED.toString()), Double.valueOf(1.0d));
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.common.service.-$$Lambda$DeprovisioningTask$s26H7-wkaWkscpPQLKDtxbCVOi8
            @Override // java.lang.Runnable
            public final void run() {
                DeprovisioningTask.this.lambda$handleProvisioningChange$0$DeprovisioningTask();
            }
        });
    }

    public /* synthetic */ void lambda$handleProvisioningChange$0$DeprovisioningTask() {
        LOG.i("Sending broadcast after de-registering account successfully");
        LocalBroadcastManager.getInstance(this.context).sendBroadcast(new Intent(Constants.ACCOUNT_DEREGISTER_ACTION));
        LOG.i("Cancelling all the existing alarms");
        PeriodicConfigSyncUtils.cancelPeriodicConfigSyncing(this.context);
        LOG.i("User provisioning has changed. notifying the application.");
        LocalBroadcastManager.getInstance(this.context).sendBroadcast(new Intent(Constants.USER_PROVISIONING_CHANGE_ACTION));
        ApplicationManager applicationManager = CommsDaggerWrapper.getComponent().getApplicationManager();
        applicationManager.navigateHome();
        applicationManager.setIndicatorIconVisible(false);
    }
}
