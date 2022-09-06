package com.amazon.deecomms.calling.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
/* loaded from: classes12.dex */
public class CallInitiatedFragment extends Fragment {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CallInitiatedFragment.class);

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LOG.i(" onCreateView of CallInitiatedFragment ");
        return layoutInflater.inflate(R.layout.call_initiated_layout, viewGroup, false);
    }
}
