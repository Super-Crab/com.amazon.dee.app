package com.amazon.deecomms.calling.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Strings;
/* loaded from: classes12.dex */
public class EnhancedProcessingPermissionFragment extends Fragment {
    @NonNull
    final EnvironmentService environmentService = (EnvironmentService) GeneratedOutlineSupport1.outline20(EnvironmentService.class);

    private void openUrlInExternalBrowser(@NonNull String str) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public /* synthetic */ void lambda$onCreateView$0$EnhancedProcessingPermissionFragment(View view) {
        String sb;
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            ((CallPermissionActivity) activity).setLearnMoreSelected(true);
        }
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.LEARN_MORE_TAPPED);
        String retailEndpoint = this.environmentService.getRetailEndpoint();
        if (Strings.isNullOrEmpty(retailEndpoint)) {
            sb = CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.ENHANCED_PROCESSING_LEARN_MORE_PFM);
        } else {
            StringBuilder outline1 = GeneratedOutlineSupport.outline1(retailEndpoint);
            outline1.append(CommsDaggerWrapper.getComponent().getArcusConfig().getConfigString(RemoteConfigKeys.ENHANCED_PROCESSING_LEARN_MORE));
            sb = outline1.toString();
        }
        openUrlInExternalBrowser(sb);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.enhanced_processing_opt_in, viewGroup, false);
        inflate.findViewById(R.id.learn_more).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$EnhancedProcessingPermissionFragment$l_x6nZsg_ORC231UibEa248u4WU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EnhancedProcessingPermissionFragment.this.lambda$onCreateView$0$EnhancedProcessingPermissionFragment(view);
            }
        });
        return inflate;
    }
}
