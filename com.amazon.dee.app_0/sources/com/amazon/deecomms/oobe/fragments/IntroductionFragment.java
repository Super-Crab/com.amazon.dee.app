package com.amazon.deecomms.oobe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.oobe.OOBEActivity;
/* loaded from: classes12.dex */
public class IntroductionFragment extends MainOOBEFragment {
    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.oobe_introduction, viewGroup, false);
        ((Button) inflate.findViewById(R.id.oobe_intro_continue_btn)).setOnClickListener(nextFragmentClickHandler());
        setHeaderTitle(getResources().getString(R.string.oobe_setup_header));
        if (getActivity() != null && !getActivity().isFinishing()) {
            ((OOBEActivity) getActivity()).hideHeaderBar();
            ((OOBEActivity) getActivity()).hideBackButton();
            ((OOBEActivity) getActivity()).hideSkipButton();
        }
        return inflate;
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment
    public void recordOobePageStartMetric() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PAGE_INTRODUCTION_START);
    }
}
