package com.amazon.deecomms.common.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.UserInfo;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.identity.CommsIdentityUtils;
import com.amazon.deecomms.util.ThreadUtils;
import com.google.common.base.Strings;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ProvisioningManager {
    @NonNull
    private final Context context;
    @NonNull
    private final DeprovisioningTask deprovisioningTask;
    @Inject
    CommsIdentityManager mCommsIdentityManager;
    private LocalBroadcastManager mLocalBroadcastManager;
    private final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ProvisioningManager.class);
    private final Set<Integer> registeredProvisioningStatusReceivers = Collections.synchronizedSet(new HashSet());
    private boolean isRunning = false;
    private AtomicBoolean isDeprovisionTaskRunning = new AtomicBoolean();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.deecomms.common.service.ProvisioningManager$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass1 extends AsyncTask<Void, Void, Void> {
        AnonymousClass1() {
        }

        public /* synthetic */ void lambda$onPostExecute$0$ProvisioningManager$1() {
            ProvisioningManager.this.broadcastProvisionStatusCheckedEvent();
        }

        @Override // android.os.AsyncTask
        protected void onPreExecute() {
            ProvisioningManager.this.isRunning = true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(@Nullable Void... voidArr) {
            ProvisioningManager.this.checkProvisioningStatusInternal();
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(@Nullable Void r2) {
            ProvisioningManager.this.isRunning = false;
            ProvisioningManager.this.isDeprovisionTaskRunning.set(false);
            ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.common.service.-$$Lambda$ProvisioningManager$1$IICkuodfwHuLxQBDKyWh37SoWtA
                @Override // java.lang.Runnable
                public final void run() {
                    ProvisioningManager.AnonymousClass1.this.lambda$onPostExecute$0$ProvisioningManager$1();
                }
            });
        }
    }

    public ProvisioningManager(@NonNull Context context, @NonNull EventBus eventBus, @NonNull CapabilitiesManager capabilitiesManager) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.context = context;
        this.deprovisioningTask = new DeprovisioningTask(context, this.mCommsIdentityManager, eventBus);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void broadcastProvisionStatusCheckedEvent() {
        this.LOG.i("Sending broadcast after provision status check");
        getLocalBroadcastManager().sendBroadcast(new Intent(Constants.USER_PROVISIONED_STATUS_CHECKED_ACTION));
    }

    private LocalBroadcastManager getLocalBroadcastManager() {
        if (this.mLocalBroadcastManager == null) {
            this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(this.context);
        }
        return this.mLocalBroadcastManager;
    }

    void checkProvisioningStatusInternal() {
        ThreadUtils.checkNotMainThread();
        if (Utils.isSSO(this.context) && !Utils.isMapAccountRegistered(this.mCommsIdentityManager.getDirectedId("ProvisioningManager.checkStatus", false))) {
            this.mCommsIdentityManager.setProvisionStatus(CommsProvisionStatus.UNKNOWN, "ProvisioningManager.checkStatus", false, true);
            this.isDeprovisionTaskRunning.set(true);
            this.deprovisioningTask.handleProvisioningChange();
            return;
        }
        String commsId = this.mCommsIdentityManager.getCommsId("ProvisioningManager.checkStatus", false);
        if (Strings.isNullOrEmpty(commsId)) {
            this.LOG.w("Ignoring for empty commsId");
            return;
        }
        try {
            UserInfo fetchUserInfo = CommsIdentityUtils.fetchUserInfo(this.context, commsId, "checkProvisioningStatusInternal() in ProvisioningManager");
            if (fetchUserInfo == null) {
                this.LOG.e("Fetched user info is null -- No more work can be done.");
                return;
            }
            CommsProvisionStatus commsProvisionStatus = fetchUserInfo.getCommsProvisionStatus();
            if (commsProvisionStatus == CommsProvisionStatus.DEPROVISIONED) {
                CommsLogger commsLogger = this.LOG;
                commsLogger.d("User is deprovisioned: " + commsProvisionStatus);
                this.isDeprovisionTaskRunning.set(true);
                this.deprovisioningTask.handleDeprovisioning();
            }
            this.mCommsIdentityManager.setProvisionStatus(commsProvisionStatus, "ProvisioningManager.checkStatus", false, true);
        } catch (ServiceException unused) {
            this.LOG.e("ServiceException occurred during fetchUserInfo -- No more work can be done.");
        }
    }

    public void checkStatus() {
        if (this.mCommsIdentityManager.isCommsIdPopulated("ProvisioningManager.checkStatus", false) && !this.isRunning) {
            this.LOG.i("Triggering check for Comms-user provisioning change");
            new AnonymousClass1().execute(new Void[0]);
            return;
        }
        this.LOG.d("No Comms-registered user.");
        ThreadUtils.runOnMainThread(new Runnable() { // from class: com.amazon.deecomms.common.service.-$$Lambda$ProvisioningManager$R1pcUBu112xDrpKDH1pP-SK57fE
            @Override // java.lang.Runnable
            public final void run() {
                ProvisioningManager.this.broadcastProvisionStatusCheckedEvent();
            }
        });
    }

    public boolean isDeprovisionInProgress() {
        return this.isDeprovisionTaskRunning.get();
    }

    public void registerStatusReceiver(@NonNull BroadcastReceiver broadcastReceiver) {
        if (this.registeredProvisioningStatusReceivers.add(Integer.valueOf(broadcastReceiver.hashCode()))) {
            CommsLogger commsLogger = this.LOG;
            commsLogger.d("Registering provision status check receiver: " + broadcastReceiver);
            getLocalBroadcastManager().registerReceiver(broadcastReceiver, new IntentFilter(Constants.USER_PROVISIONED_STATUS_CHECKED_ACTION));
        }
    }

    public void unregisterStatusReceiver(@NonNull BroadcastReceiver broadcastReceiver) {
        if (this.registeredProvisioningStatusReceivers.remove(Integer.valueOf(broadcastReceiver.hashCode()))) {
            CommsLogger commsLogger = this.LOG;
            commsLogger.d("Unregistering provision status check receiver: " + broadcastReceiver);
            getLocalBroadcastManager().unregisterReceiver(broadcastReceiver);
        }
    }

    public ProvisioningManager(@NonNull Context context, @NonNull EventBus eventBus, @NonNull CapabilitiesManager capabilitiesManager, @NonNull LocalBroadcastManager localBroadcastManager) {
        CommsDaggerWrapper.getComponent().inject(this);
        this.context = context;
        this.deprovisioningTask = new DeprovisioningTask(context, this.mCommsIdentityManager, eventBus);
        this.mLocalBroadcastManager = localBroadcastManager;
    }
}
