package com.amazon.deecomms.contacts.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactForSync;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.ContactUploadInfo;
import com.amazon.deecomms.contacts.model.enums.SyncOperationType;
import com.amazon.deecomms.contacts.util.CloudContactsSynchronizer;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.util.ResultInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class RelationshipListFragment extends Fragment {
    private static final String METRIC_SOURCE_RELATIONSHIP = "relationship";
    @Inject
    Context appContext;
    @Inject
    ApplicationManager applicationManager;
    private String mCompany;
    private String mContactId;
    private ContactName mContactName;
    private ListView mListView;
    private Menu mMenu;
    private String mOwnerCommsId;
    private ArrayList<ContactPhoneNumber> mPhoneNumbersList;
    private String mRelationship;
    private View mRelationshipListView;
    private String selectedRelationship;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, RelationshipListFragment.class);
    private static final String[] DEFAULT_RELATIONSHIP_LIST = {"Father", "Mother", "Brother", "Sister", "Grandmother", "Grandfather", "Aunt", "Uncle"};

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class EditRelationshipTask extends AsyncTask<Void, Void, ResultInfo<Boolean, AlertSource>> {
        private final String relationship;

        public EditRelationshipTask(String str) {
            this.relationship = str;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public ResultInfo<Boolean, AlertSource> doInBackground(Void... voidArr) {
            RelationshipListFragment.LOG.d("Editing contact relationship");
            if (this.relationship == null) {
                RelationshipListFragment.LOG.e("Edit contact failed as the relationship to be updated is null");
                return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditRelationshipTask.class.getName()));
            }
            CloudContactsSynchronizer cloudContactsSynchronizer = new CloudContactsSynchronizer();
            ContactForSync contactForNameSync = RelationshipListFragment.this.getContactForNameSync();
            List<ContactUploadInfo.PhoneNumber> numbers = contactForNameSync.getContact().getNumbers();
            if (RelationshipListFragment.this.appContext == null && (numbers == null || numbers.size() == 0)) {
                return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditRelationshipTask.class.getName()));
            }
            RelationshipListFragment relationshipListFragment = RelationshipListFragment.this;
            if (relationshipListFragment.appContext != null && !ContactsProviderUtils.isContactChild(relationshipListFragment.mContactId, RelationshipListFragment.this.appContext) && (numbers == null || numbers.size() == 0)) {
                RelationshipListFragment.LOG.e("Contact that is missing Phone Numbers can not be edited");
                HashMap hashMap = new HashMap();
                hashMap.put("errorSource", MetricKeys.VALUE_INVALID_CONTACT_UPDATE);
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACT_EDIT_AUTO_ADDED_FAILURE, hashMap);
                return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditRelationshipTask.class.getName()));
            }
            HashMap outline133 = GeneratedOutlineSupport1.outline133("source", "relationship");
            try {
                IHttpClient.Response uploadContactsDiff = cloudContactsSynchronizer.uploadContactsDiff(contactForNameSync, RelationshipListFragment.this.mOwnerCommsId);
                ContactsProviderUtils.updateContactStringColumn(RelationshipListFragment.this.mContactId, "relationship", this.relationship);
                RelationshipListFragment.LOG.i("Edit contact relationship success");
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACT_EDIT_AUTO_ADDED_SUCCESS, outline133);
                ResultInfo<Boolean, AlertSource> resultInfo = new ResultInfo<>(Boolean.TRUE, null);
                if (uploadContactsDiff != null) {
                    uploadContactsDiff.close();
                }
                return resultInfo;
            } catch (IOException e) {
                RelationshipListFragment.LOG.e("Edit contact relationship failed", e);
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACT_EDIT_AUTO_ADDED_FAILURE, outline133);
                return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditRelationshipTask.class.getName()));
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(ResultInfo<Boolean, AlertSource> resultInfo) {
            super.onPostExecute((EditRelationshipTask) resultInfo);
            if (!RelationshipListFragment.this.isVisible()) {
                return;
            }
            if (resultInfo.getStatus().booleanValue()) {
                RelationshipListFragment.this.applicationManager.navigateUpward();
                return;
            }
            MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, MetricKeys.SCREEN_NAME_CONTACT_EDIT, resultInfo.getInfo());
            RelationshipListFragment.this.showErrorDialog(R.string.edit_contact_server_error);
        }
    }

    private void configureFragmentRequirements() {
        this.applicationManager.configurePageForFragment(new FragmentRequirements(this).withMenu(R.menu.edit_nickname_relationship_menu, new Toolbar.OnMenuItemClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$RelationshipListFragment$ZDOLKU9YgaIo-fPDDnEhx1jW3Mo
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return RelationshipListFragment.this.lambda$configureFragmentRequirements$0$RelationshipListFragment(menuItem);
            }
        }, new FragmentRequirements.MenuInflatedCallback() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$RelationshipListFragment$Vv9JgPK1cyAFORUnyHXrEKpj4GY
            @Override // com.amazon.deecomms.api.navigation.FragmentRequirements.MenuInflatedCallback
            public final void onInflated(Menu menu) {
                RelationshipListFragment.this.lambda$configureFragmentRequirements$1$RelationshipListFragment(menu);
            }
        }).hidesFooter());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContactForSync getContactForNameSync() {
        ContactUploadInfo contactUploadInfo = new ContactUploadInfo();
        contactUploadInfo.setContactName(this.mContactName.getFirstName(), this.mContactName.getLastName(), this.mContactName.getNickName());
        contactUploadInfo.setCompany(this.mCompany);
        contactUploadInfo.setDeviceContactId(null);
        contactUploadInfo.setServerContactId(this.mContactId);
        contactUploadInfo.setRelationship(this.selectedRelationship);
        Iterator<ContactPhoneNumber> it2 = this.mPhoneNumbersList.iterator();
        while (it2.hasNext()) {
            ContactPhoneNumber next = it2.next();
            contactUploadInfo.addPhoneNumber(next.getPhoneNumber(), next.getRawType());
        }
        return new ContactForSync(contactUploadInfo, SyncOperationType.UPDATE);
    }

    private void onSaveClicked() {
        if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_EDIT, AlertSource.newClassSource(RelationshipListFragment.class.getName()))) {
            return;
        }
        if (CommsInternal.getInstance().isLowInternalStorage()) {
            Utils.showDialog(getActivity(), R.string.error_title, R.string.message_low_memory);
        } else {
            new EditRelationshipTask(this.selectedRelationship).execute(new Void[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorDialog(int i) {
        new AlertDialog.Builder(getContext()).setTitle(R.string.edit_contact_title).setMessage(i).setCancelable(true).setPositiveButton(R.string.dialog_ok_button, $$Lambda$RelationshipListFragment$7qOZC7xC4UAIb6wrpTWuq2w9xTE.INSTANCE).create().show();
    }

    public /* synthetic */ boolean lambda$configureFragmentRequirements$0$RelationshipListFragment(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.save_edit_contact) {
            menuItem.setVisible(true);
            onSaveClicked();
            return true;
        }
        this.mMenu.setGroupEnabled(itemId, false);
        menuItem.setVisible(false);
        return false;
    }

    public /* synthetic */ void lambda$configureFragmentRequirements$1$RelationshipListFragment(Menu menu) {
        this.mMenu = menu;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        String[] strArr;
        CommsDaggerWrapper.getComponent().inject(this);
        this.mRelationshipListView = layoutInflater.inflate(R.layout.relationship_list, viewGroup, false);
        this.mContactName = (ContactName) getArguments().getParcelable(Constants.BUNDLE_KEY_CONTACT_NAME_KEY);
        this.mContactId = getArguments().getString(Constants.BUNDLE_KEY_CONTACT_ID);
        this.mCompany = getArguments().getString("company");
        this.mRelationship = getArguments().getString(Constants.BUNDLE_KEY_CONTACT_RELATIONSHIP);
        this.selectedRelationship = this.mRelationship;
        this.mOwnerCommsId = getArguments().getString("ownerCommsId");
        this.mPhoneNumbersList = getArguments().getParcelableArrayList(Constants.BUNDLE_KEY_PHONE_NUMBERS);
        this.mListView = (ListView) this.mRelationshipListView.findViewById(R.id.relationship_list_view);
        Gson gson = new Gson();
        String string = getContext().getSharedPreferences("SHARED_PREFS", 0).getString(Constants.CHILD_CONTACT_RELATIONSHIP_LIST, null);
        if (string == null) {
            strArr = DEFAULT_RELATIONSHIP_LIST;
        } else {
            strArr = (String[]) gson.fromJson(string, (Class<Object>) String[].class);
        }
        this.mListView.setAdapter((ListAdapter) new RelationshipListAdapter(getActivity(), R.layout.relationship_list_item, strArr, this.mRelationship));
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.amazon.deecomms.contacts.ui.RelationshipListFragment.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int firstVisiblePosition = RelationshipListFragment.this.mListView.getFirstVisiblePosition();
                int lastVisiblePosition = RelationshipListFragment.this.mListView.getLastVisiblePosition();
                for (int i2 = 0; i2 <= lastVisiblePosition - firstVisiblePosition; i2++) {
                    ImageView imageView = (ImageView) RelationshipListFragment.this.mListView.getChildAt(i2).findViewById(R.id.relationship_icon);
                    if (i2 != i - firstVisiblePosition) {
                        imageView.setVisibility(4);
                    } else {
                        RelationshipListFragment relationshipListFragment = RelationshipListFragment.this;
                        relationshipListFragment.selectedRelationship = (String) relationshipListFragment.mListView.getAdapter().getItem(i2);
                        imageView.setVisibility(0);
                    }
                }
            }
        });
        configureFragmentRequirements();
        return this.mRelationshipListView;
    }
}
