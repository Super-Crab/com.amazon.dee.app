package com.amazon.deecomms.common.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.amazon.comms.calling.service.DeviceCallingService;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.DeviceInfo;
import com.amazon.deecomms.util.LogsUtil;
import java.util.ArrayList;
import java.util.Collections;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class DiagnosticScreen extends Fragment {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DiagnosticScreen.class);
    @Inject
    ApplicationManager applicationManager;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    @Inject
    CommsInternal commsInternal;
    @Inject
    DeviceCallingService deviceCallingService;
    private DeviceInfo mDeviceInfo;
    private TextView mDeviceName;

    private void configureFragmentRequirements() {
        this.applicationManager.configurePageForFragment(new FragmentRequirements(this).withTitle(getResources().getString(R.string.diagnostic_title)).withMenu(R.menu.diagnostic_screen_menu, new Toolbar.OnMenuItemClickListener() { // from class: com.amazon.deecomms.common.ui.-$$Lambda$DiagnosticScreen$_mxdrg3n-Th-zMP__6DMmjdlpC4
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return DiagnosticScreen.this.lambda$configureFragmentRequirements$0$DiagnosticScreen(menuItem);
            }
        }, null));
    }

    private void displayCurrentSipState() {
        displayCurrentSipState(getView());
    }

    private void init(View view) {
        this.mDeviceName = (TextView) view.findViewById(R.id.account);
        new AsyncTask<Void, Void, String>() { // from class: com.amazon.deecomms.common.ui.DiagnosticScreen.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(Void... voidArr) {
                return Utils.getDeviceName(DiagnosticScreen.this.getContext());
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String str) {
                DiagnosticScreen.this.mDeviceName.setText(str);
            }
        }.execute(new Void[0]);
        ((TextView) view.findViewById(R.id.app_unique_id)).setText(this.mDeviceInfo.getUniqueDeviceId(getActivity()));
        ((TextView) view.findViewById(R.id.app_client_id)).setText(this.commsInternal.getClientID());
        ((TextView) view.findViewById(R.id.app_version)).setText(Utils.getAppVersion(getContext()));
        ((TextView) view.findViewById(R.id.locale_id)).setText(this.commsInternal.getLocale());
        ((TextView) view.findViewById(R.id.pfm)).setText(this.commsIdentityManager.getPreferredMarketplace(false));
        displayCurrentSipState(view);
        ((TextView) view.findViewById(R.id.comms_id)).setText(this.commsIdentityManager.getCommsId("DiagnosticScreen.init", false));
        ((TextView) view.findViewById(R.id.aor_id)).setText(Utils.getSipURIforRegisteredUser(getActivity()));
        ArrayList<String> allFeatures = this.capabilitiesManager.getAllFeatures();
        Collections.sort(allFeatures);
        ((TextView) view.findViewById(R.id.feature_flags_list)).setText(allFeatures.toString());
    }

    public /* synthetic */ boolean lambda$configureFragmentRequirements$0$DiagnosticScreen(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_get_logs) {
            LogsUtil.saveLogsAndSendViaEmail(getActivity());
            return true;
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        View inflate = layoutInflater.inflate(R.layout.diagnostics_screen, viewGroup, false);
        this.mDeviceInfo = new DeviceInfo();
        init(inflate);
        configureFragmentRequirements();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        configureFragmentRequirements();
        displayCurrentSipState();
        this.applicationManager.loadingComplete(CommsView.Diagnostics);
    }

    private void displayCurrentSipState(@Nullable View view) {
        if (view == null) {
            return;
        }
        DeviceCallingService.State state = this.deviceCallingService.getState();
        TextView textView = (TextView) view.findViewById(R.id.sip_status);
        if (textView != null && state != null) {
            textView.setText(state.toString());
        } else {
            LOG.e("Unable to set SIP state");
        }
    }
}
