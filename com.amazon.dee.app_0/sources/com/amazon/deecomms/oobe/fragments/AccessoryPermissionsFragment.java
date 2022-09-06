package com.amazon.deecomms.oobe.fragments;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
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
import java.util.ArrayList;
/* loaded from: classes12.dex */
public class AccessoryPermissionsFragment extends MainOOBEFragment {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, AccessoryPermissionsFragment.class);

    @TargetApi(26)
    public static String[] getRequiredAccessoryPermissions() {
        ArrayList arrayList = new ArrayList();
        if (Utils.isOreoAndAbove()) {
            arrayList.add("android.permission.ANSWER_PHONE_CALLS");
        }
        arrayList.add("android.permission.SEND_SMS");
        arrayList.add("android.permission.READ_SMS");
        arrayList.add("android.permission.RECEIVE_SMS");
        arrayList.add("android.permission.RECEIVE_MMS");
        arrayList.add("android.permission.READ_PHONE_STATE");
        arrayList.add("android.permission.CALL_PHONE");
        arrayList.add("android.permission.RECORD_AUDIO");
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public /* synthetic */ void lambda$onCreateView$0$AccessoryPermissionsFragment(String str, View view) {
        Utils.openUrlInExternalBrowser(str, this);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Bundle extras;
        final String[] checkPermissions = PermissionsHelper.checkPermissions(getActivity(), getRequiredAccessoryPermissions());
        if (checkPermissions.length == 0) {
            LOG.d("User granted required accessory permissions! Hide Accessory permissions screen!");
            goToNextFragment();
            return new View(getActivity());
        }
        Intent intent = getActivity().getIntent();
        if (intent != null && (extras = intent.getExtras()) != null && !Constants.ACCESSORY_OOBE.equals(extras.getString(Constants.OOBE_STARTED_FROM))) {
            LOG.d("Default OOBE! Hide Accessory permission screen!");
            goToNextFragment();
            return new View(getActivity());
        }
        View inflate = layoutInflater.inflate(R.layout.echo_auto_permissions, viewGroup, false);
        Context applicationContext = getActivity().getApplicationContext();
        this.themingUpdateUtil.applyBackgroundColorToView(inflate, applicationContext, R.attr.mosaicBackground);
        this.themingUpdateUtil.applyTintColorToImageView((ImageView) inflate.findViewById(R.id.muffin_permissions_icon), applicationContext, R.attr.mosaicNeutral10);
        this.themingUpdateUtil.applyColorToTextView(inflate, R.id.muffin_permissions_title, applicationContext, R.attr.mosaicNeutral10);
        this.themingUpdateUtil.applyColorToTextView(inflate, R.id.muffin_permissions_desc, applicationContext, R.attr.mosaicNeutral10);
        this.themingUpdateUtil.applyColorToTextView(inflate, R.id.call_msg_ul1, applicationContext, R.attr.mosaicNeutral10);
        this.themingUpdateUtil.applyColorToTextView(inflate, R.id.call_msg_ul2, applicationContext, R.attr.mosaicNeutral10);
        this.themingUpdateUtil.applyColorToTextView(inflate, R.id.call_msg_ul3, applicationContext, R.attr.mosaicNeutral10);
        this.themingUpdateUtil.applyColorToTextView(inflate, R.id.call_msg_ul4, applicationContext, R.attr.mosaicNeutral10);
        this.themingUpdateUtil.applyColorToTextView(inflate, R.id.call_msg_ul5, applicationContext, R.attr.mosaicNeutral10);
        Button button = (Button) inflate.findViewById(R.id.oobe_permissions_deny_btn);
        this.themingUpdateUtil.applyColorToTextView(button, applicationContext, R.attr.mosaicAction10);
        button.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.oobe.fragments.AccessoryPermissionsFragment.1
            @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
            public void onSingleClick(View view) {
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_ACCESSORY_IMPORT_LATER);
                AccessoryPermissionsFragment.this.goToNextFragment();
            }
        });
        Button button2 = (Button) inflate.findViewById(R.id.oobe_permissions_accept_btn);
        this.themingUpdateUtil.applyBackgroundToButton(button2, applicationContext, R.drawable.mosaic_button_primary);
        button2.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.oobe.fragments.AccessoryPermissionsFragment.2
            @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
            public void onSingleClick(View view) {
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_ACCESSORY_IMPORT_YES);
                if (checkPermissions.length > 0) {
                    ActivityCompat.requestPermissions(AccessoryPermissionsFragment.this.getActivity(), AccessoryPermissionsFragment.getRequiredAccessoryPermissions(), PermissionsHelper.SEND_SMS_AND_PHONE_CODE);
                } else {
                    AccessoryPermissionsFragment.this.goToNextFragment();
                }
            }
        });
        setHeaderTitle(getResources().getString(R.string.oobe_setup_header));
        OOBEActivity oOBEActivity = (OOBEActivity) getActivity();
        if (oOBEActivity != null && !oOBEActivity.isFinishing()) {
            oOBEActivity.showHeaderBar();
            oOBEActivity.hideBackButton();
            oOBEActivity.hideSkipButton();
        }
        final String configString = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.FAQ_URL_KEY);
        Button button3 = (Button) inflate.findViewById(R.id.call_msg_learn_more_1);
        button3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.oobe.fragments.-$$Lambda$AccessoryPermissionsFragment$ufXDf5CvZsN808Ui80zmdW_UZ5k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AccessoryPermissionsFragment.this.lambda$onCreateView$0$AccessoryPermissionsFragment(configString, view);
            }
        });
        this.themingUpdateUtil.applyColorToTextView(button3, applicationContext, R.attr.mosaicAction10);
        this.themingUpdateUtil.applyColorToTextView(inflate, R.id.call_msg_learn_more_2, applicationContext, R.attr.mosaicNeutral10);
        return inflate;
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment
    public void recordOobePageStartMetric() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PAGE_PERM_ACCESSORY_START);
    }
}
