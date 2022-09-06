package com.amazon.deecomms.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.calling.controller.CallManager;
import com.amazon.deecomms.calling.ui.CallActivity;
import com.amazon.deecomms.calling.ui.NewCallActivity;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.receiver.CommsConnectivityMonitor;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.util.FragmentNavigationRouter;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.ndt.ui.DeviceBottomSheet;
import com.amazon.deecomms.notifications.util.NotificationUtils;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.util.SharedPreferencesUtils;
import com.google.common.base.Optional;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class CommsMasterFragment extends Fragment implements DeviceBottomSheetTarget, CommsConnectivityMonitor.OnConnectionTypeChangedListener {
    public static final String DO_NOT_START_FRAGMENT = "DO_NOT_START_FRAGMENT";
    @Inject
    ApplicationManager applicationManager;
    @Inject
    CommsConnectivityMonitor commsConnectivityMonitor;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    ContactsOperationsManager contactManager;
    private DeviceBottomSheet deviceBottomSheet;
    private Future mScheduledFuture;
    private ScheduledExecutorService mScheduler;
    private Intent networkConnectionIntent;
    private ActivityCompat.OnRequestPermissionsResultCallback onRequestPermissionsResultCallback;
    @Inject
    ProvisioningManager provisioningManager;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsMasterFragment.class);
    public static final int CONTAINER_ID = R.id.comms_master_fragment_container;
    private boolean mFromSavedInstanceState = false;
    private boolean mStarted = false;
    private final BroadcastReceiver provisioningChangeReceiver = new BroadcastReceiver() { // from class: com.amazon.deecomms.common.CommsMasterFragment.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                CommsMasterFragment.LOG.e("provisioning change: intent is null, ignoring");
                return;
            }
            CommsLogger commsLogger = CommsMasterFragment.LOG;
            commsLogger.i("provisioning has changed: intent=" + intent);
            if (CommsProvisionStatus.DEPROVISIONED.equals(CommsMasterFragment.this.commsIdentityManager.getProvisionStatus(true, "provisioningChangeReceiver.onReceive", false)) && CommsMasterFragment.this.mScheduledFuture != null) {
                CommsMasterFragment.this.mScheduledFuture.cancel(false);
            }
            CommsMasterFragment commsMasterFragment = CommsMasterFragment.this;
            commsMasterFragment.commsConnectivityMonitor.deRegisterListener(commsMasterFragment);
        }
    };

    private void onNetworkConnected() {
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(this.networkConnectionIntent);
    }

    private void onNetworkDisconnected() {
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(this.networkConnectionIntent);
    }

    @Override // com.amazon.deecomms.common.DeviceBottomSheetTarget
    @Nullable
    public DeviceBottomSheet getBottomSheetDialogFragment() {
        return this.deviceBottomSheet;
    }

    public /* synthetic */ void lambda$onStart$0$CommsMasterFragment() {
        LOG.i("Starting auto contact sync");
        this.contactManager.pullContacts(false);
    }

    @Override // com.amazon.deecomms.common.receiver.CommsConnectivityMonitor.OnConnectionTypeChangedListener
    public void onConnectionTypeChanged(@NonNull CommsConnectivityMonitor.ConnectionType connectionType, @NonNull CommsConnectivityMonitor.ConnectionType connectionType2) {
        if (this.commsConnectivityMonitor.isTransitioningToConnected(connectionType, connectionType2)) {
            onNetworkConnected();
        } else if (!this.commsConnectivityMonitor.isTransitioningToDisconnected(connectionType, connectionType2)) {
        } else {
            onNetworkDisconnected();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        super.onCreateView(layoutInflater, viewGroup, bundle);
        LOG.i("onCreate of Master Fragment");
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.APPLICATION_LAUNCH_COUNT);
        boolean z = false;
        View inflate = layoutInflater.inflate(R.layout.comms_master_fragment, viewGroup, false);
        if (bundle != null) {
            z = true;
        }
        this.mFromSavedInstanceState = z;
        this.mScheduler = Executors.newSingleThreadScheduledExecutor();
        this.networkConnectionIntent = new Intent(Constants.NETWORK_CONNECTION_INTENT);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        NotificationUtils.clearNotificationSuppressions();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.provisioningManager.unregisterStatusReceiver(this.provisioningChangeReceiver);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        CounterMetric generateClickstream;
        ActivityCompat.OnRequestPermissionsResultCallback onRequestPermissionsResultCallback = this.onRequestPermissionsResultCallback;
        if (onRequestPermissionsResultCallback != null) {
            onRequestPermissionsResultCallback.onRequestPermissionsResult(i, strArr, iArr);
        }
        if (i == 0 && !((Boolean) SharedPreferencesUtils.getCacheValue(getContext(), Constants.INITIAL_CONTACTS_IMPORTS_DONE_PREF, false)).booleanValue()) {
            if (iArr.length > 0 && iArr[0] == 0) {
                LOG.i("Granted Contacts Permission. React Native will perform the First Contacts sync");
                generateClickstream = CounterMetric.generateClickstream(MetricKeys.PERMS_CONTACTS_ALLOW);
            } else {
                generateClickstream = CounterMetric.generateClickstream(MetricKeys.PERMS_CONTACTS_DENY);
            }
            generateClickstream.getMetadata().put("source", MetricKeys.PERMS_SOURCE_CONTACT_LIST);
            MetricsHelper.recordSingleOccurrence(generateClickstream);
        }
        super.onRequestPermissionsResult(i, strArr, iArr);
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null) {
                    fragment.onRequestPermissionsResult(i, strArr, iArr);
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.COMMS_MASTER_FRAGMENT_RESUMED);
        this.provisioningManager.registerStatusReceiver(this.provisioningChangeReceiver);
        this.provisioningManager.checkStatus();
        if (this.commsConnectivityMonitor.isConnected()) {
            onNetworkConnected();
        } else {
            onNetworkDisconnected();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        Intent intent;
        super.onStart();
        long intValue = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigInteger(RemoteConfigKeys.CONTACTS_REFRESH_INTERVAL_SEC).intValue();
        this.mScheduledFuture = this.mScheduler.scheduleWithFixedDelay(new Runnable() { // from class: com.amazon.deecomms.common.-$$Lambda$CommsMasterFragment$eJ5rtGgIQ6hIO1ZwKeznIwRPp08
            @Override // java.lang.Runnable
            public final void run() {
                CommsMasterFragment.this.lambda$onStart$0$CommsMasterFragment();
            }
        }, intValue, intValue, TimeUnit.SECONDS);
        CallManager callManager = CommsDaggerWrapper.getComponent().getCallManager();
        if (this.sipClientState.getCallState() != SipClientState.CallState.INACTIVE && callManager.isCallUINavigatedAway() && callManager.isCallActivityLaunchedOnce()) {
            if (CommsDaggerWrapper.getComponent().getCallInitiationAuthority().isNewCallInitiationUIFlowEnabled(Optional.fromNullable(this.sipClientState.getCallProvider()), Optional.fromNullable(this.sipClientState.getCspId()))) {
                intent = new Intent(getContext(), NewCallActivity.class);
            } else {
                intent = new Intent(getContext(), CallActivity.class);
            }
            String remoteParticipantName = this.sipClientState.getRemoteParticipantName();
            if (remoteParticipantName == null) {
                remoteParticipantName = Utils.getStringFromResource(R.string.unknown_contact);
            }
            intent.putExtra(Constants.REMOTE_PARTICIPANT_NAME, remoteParticipantName);
            intent.setFlags(131072);
            LOG.i("Starting call activity via CommsMasterFragment");
            getContext().startActivity(intent);
        }
        if (!this.mStarted && !this.mFromSavedInstanceState && !getArguments().getBoolean(DO_NOT_START_FRAGMENT, false)) {
            LOG.i(" Setup conversationFragment Fragment ");
            FragmentNavigationRouter.switchToFragment(CommsView.Default, new Bundle());
            this.mStarted = true;
        }
        this.commsConnectivityMonitor.registerListener(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        Future future = this.mScheduledFuture;
        if (future != null) {
            future.cancel(false);
        }
        this.commsConnectivityMonitor.deRegisterListener(this);
    }

    @Override // com.amazon.deecomms.common.DeviceBottomSheetTarget
    public void setBottomSheetDialogFragment(@Nullable DeviceBottomSheet deviceBottomSheet) {
        this.deviceBottomSheet = deviceBottomSheet;
    }

    public void setOnRequestPermissionsResultCallback(@Nullable ActivityCompat.OnRequestPermissionsResultCallback onRequestPermissionsResultCallback) {
        this.onRequestPermissionsResultCallback = onRequestPermissionsResultCallback;
    }
}
