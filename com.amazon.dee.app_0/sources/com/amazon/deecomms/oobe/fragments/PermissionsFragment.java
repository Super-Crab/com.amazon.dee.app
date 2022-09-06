package com.amazon.deecomms.oobe.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.ui.util.OnSingleClickListener;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
/* loaded from: classes12.dex */
public class PermissionsFragment extends MainOOBEFragment {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, PermissionsFragment.class);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        final boolean checkPermission = PermissionsHelper.checkPermission(getActivity(), "android.permission.READ_CONTACTS");
        boolean z = true;
        boolean z2 = getContext().getSharedPreferences("SHARED_PREFS", 0).getBoolean(Constants.SHOULD_SUPPORT_CONTACTS_ON_DEVICES, true);
        if (!checkPermission && z2) {
            z = false;
        }
        if (z) {
            LOG.d("Hiding Permissions Fragment because,contactsPermission: " + checkPermission + " isContactsSupportedOnDevice: " + z2);
            goToNextFragment();
            return new View(getActivity());
        }
        if (isThemedUIEnabled()) {
            inflate = layoutInflater.inflate(R.layout.fiesta_oobe_permissions, viewGroup, false);
        } else {
            inflate = layoutInflater.inflate(R.layout.oobe_permissions, viewGroup, false);
        }
        Context applicationContext = getActivity().getApplicationContext();
        this.themingUpdateUtil.applyBackgroundColorToView(inflate, applicationContext, R.attr.mosaicBackground);
        this.themingUpdateUtil.applyColorToTextView(inflate, R.id.oobe_permissions_title, applicationContext, R.attr.mosaicNeutral10);
        this.themingUpdateUtil.applyColorToTextView(inflate, R.id.oobe_permissions_contacts_title, applicationContext, R.attr.mosaicNeutral10);
        this.themingUpdateUtil.applyColorToTextView(inflate, R.id.oobe_permissions_subtitle, applicationContext, R.attr.mosaicNeutral20);
        TextView textView = (TextView) inflate.findViewById(R.id.oobe_permissions_learn_more);
        this.themingUpdateUtil.applyColorToTextView(textView, applicationContext, R.attr.mosaicAction10);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.oobe.fragments.PermissionsFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Utils.openUrlInExternalBrowser(CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.FAQ_URL_KEY), PermissionsFragment.this);
            }
        });
        Button button = (Button) inflate.findViewById(R.id.oobe_permissions_deny_btn);
        this.themingUpdateUtil.applyColorToTextView(button, applicationContext, R.attr.mosaicAction10);
        button.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.oobe.fragments.PermissionsFragment.2
            @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
            public void onSingleClick(View view) {
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_CONTACT_IMPORT_LATER);
                PermissionsFragment.this.goToNextFragment();
            }
        });
        Button button2 = (Button) inflate.findViewById(R.id.oobe_permissions_accept_btn);
        this.themingUpdateUtil.applyBackgroundToButton(button2, applicationContext, R.drawable.mosaic_button_primary);
        int integer = getResources().getInteger(R.integer.size);
        if (integer == 7) {
            inflate.findViewById(R.id.oobe_button_left_spacing).setVisibility(0);
            inflate.findViewById(R.id.oobe_permissions_button_container).setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.oobe_buttons_layout);
            linearLayout.removeView(button2);
            linearLayout.addView(button2, 0);
        } else if (integer == 10) {
            inflate.findViewById(R.id.oobe_buttons_layout_left_spacing).setVisibility(0);
        }
        button2.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.oobe.fragments.PermissionsFragment.3
            @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
            public void onSingleClick(View view) {
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_CONTACT_IMPORT_YES);
                if (!checkPermission) {
                    PermissionsHelper.requestPermissions(PermissionsFragment.this.getActivity(), new String[]{"android.permission.READ_CONTACTS"}, 134);
                    return;
                }
                PermissionsFragment.this.goToNextFragment();
            }
        });
        setHeaderTitle(getResources().getString(R.string.oobe_setup_header));
        if (getActivity() != null && !getActivity().isFinishing()) {
            ((OOBEActivity) getActivity()).showHeaderBar();
            ((OOBEActivity) getActivity()).hideBackButton();
            ((OOBEActivity) getActivity()).hideSkipButton();
        }
        return inflate;
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment
    public void recordOobePageStartMetric() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PAGE_PERM_CONTACT_IMPORT_START);
    }
}
