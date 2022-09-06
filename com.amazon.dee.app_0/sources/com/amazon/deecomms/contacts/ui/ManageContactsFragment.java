package com.amazon.deecomms.contacts.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.util.FragmentNavigationRouter;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ManageContactsFragment extends Fragment {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ManageContactsFragment.class);
    @Inject
    ApplicationManager applicationManager;

    private void configureFragmentRequirements() {
        this.applicationManager.configurePageForFragment(new FragmentRequirements(this).withTitle(getString(R.string.manage_contacts)));
    }

    private void init(View view) {
        view.findViewById(R.id.block_contacts).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.ManageContactsFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                ManageContactsFragment.this.launchBlockContactsFragment();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchBlockContactsFragment() {
        if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_LIST, AlertSource.newClassSource(ManageContactsFragment.class.getName()))) {
            LOG.i("Offline. Cannot show block contacts");
        } else {
            FragmentNavigationRouter.switchToFragment(CommsView.BlockContactsList, new Bundle());
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        View inflate = layoutInflater.inflate(R.layout.manage_contacts_list, viewGroup, false);
        init(inflate);
        configureFragmentRequirements();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        configureFragmentRequirements();
        this.applicationManager.loadingComplete(CommsView.ManageContactsList);
    }
}
