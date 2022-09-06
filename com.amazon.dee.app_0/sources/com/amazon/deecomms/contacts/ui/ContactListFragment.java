package com.amazon.deecomms.contacts.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.ListFragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.calling.util.CoboUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.service.ProvisioningManager;
import com.amazon.deecomms.common.ui.CommsTextView;
import com.amazon.deecomms.common.ui.util.OnSingleClickListener;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.CallBottomSheetType;
import com.amazon.deecomms.contacts.model.ContactSourceType;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.contacts.operations.ContactsOperationsManager;
import com.amazon.deecomms.contacts.util.ContactNameHelper;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.media.photos.FileTransmitter;
import com.amazon.deecomms.ndt.DevicesSource;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.DeviceStatusModel;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.amazon.deecomms.rx.SimpleSubscriber;
import com.amazon.deecomms.util.DeviceInfo;
import com.amazon.deecomms.util.SharedPreferencesUtils;
import com.amazon.photos.autosave.internal.dao.AutosaveItemDao;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.ActivityEventListener;
import com.google.common.base.Strings;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import rx.Subscriber;
/* loaded from: classes12.dex */
public class ContactListFragment extends ListFragment implements ActivityEventListener {
    private static final String CONTACT_CURRENT_OPERATION = "CONTACT_CURRENT_OPERATION";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactListFragment.class);
    private static String mFragmentToBeLaunched;
    @Inject
    ApplicationManager applicationManager;
    private CallBottomSheetDialogFragment callBottomSheet;
    @Inject
    CallingFacade callingFacade;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    private View contactListView;
    @Inject
    ContactsOperationsManager contactManager;
    @Inject
    ContactsOperationsManager contactsManager;
    @Inject
    DevicesSource devicesSource;
    @Inject
    InitiationLogicFactory initiationLogicFactory;
    private boolean isDataSetObserverRegistered;
    private boolean isLowStorage;
    private Dialog lowStorageDialog;
    SimpleSubscriber<Boolean> lowStorageSubscriber;
    private CursorAdapter mContactsAdapter;
    private Dialog mCurrentDialog;
    private View mFooterView;
    private String mFragmentTitle;
    private View mHeaderView;
    private FileTransmitter mImageUploader;
    private Uri mImageUri;
    private ListView mListView;
    private TextView mLoadingView;
    private Menu mMenu;
    private String mOperation;
    private SearchView mSearchView;
    private boolean mShouldShowManageMenu;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    ProvisioningManager provisioningManager;
    private final int GENERAL_CONTACTS_LOADER_ID = 0;
    private final int MY_CONTACTS_LOADER_ID = 1;
    private BroadcastReceiver mContactOperationReceiver = new ContactOperationResponseReceiver(null);
    private BroadcastReceiver mReactNativeSyncReceiver = new ReactNativeContactSyncStatusReceiver(null);
    private final DataSetObserver mContactsChangedObserver = new DataSetObserver() { // from class: com.amazon.deecomms.contacts.ui.ContactListFragment.1
        @Override // android.database.DataSetObserver
        public void onChanged() {
            MenuItem findItem;
            super.onChanged();
            if (ContactListFragment.this.mMenu == null || ContactListFragment.this.mContactsAdapter == null || (findItem = ContactListFragment.this.mMenu.findItem(R.id.manage_contacts)) == null) {
                return;
            }
            ContactListFragment.LOG.i("Contacts have changed, refreshing manage menu");
            ContactListFragment contactListFragment = ContactListFragment.this;
            contactListFragment.mShouldShowManageMenu = contactListFragment.shouldShowManageMenu(contactListFragment.mContactsAdapter.getCursor());
            findItem.setVisible(ContactListFragment.this.mShouldShowManageMenu);
        }
    };
    private final BroadcastReceiver provisioningStatusReceiver = new BroadcastReceiver() { // from class: com.amazon.deecomms.contacts.ui.ContactListFragment.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            MenuItem findItem;
            ContactListFragment.this.provisioningManager.unregisterStatusReceiver(this);
            if (intent == null) {
                ContactListFragment.LOG.e("Provision Status: intent is null, ignoring");
                return;
            }
            CommsLogger commsLogger = ContactListFragment.LOG;
            commsLogger.i("Provisioning status: intent=" + intent);
            if (ContactListFragment.this.isProvisionedForContactList()) {
                if (!ContactListFragment.this.isAdded()) {
                    return;
                }
                ContactListFragment.this.setupHeader();
                ContactListFragment.this.setupFooter();
                ContactListFragment.this.configureFragmentRequirements();
                if (ContactListFragment.this.mMenu != null && (findItem = ContactListFragment.this.mMenu.findItem(R.id.manage_contacts)) != null) {
                    findItem.setVisible(ContactListFragment.this.mShouldShowManageMenu);
                }
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.SCREEN_CONTACT_LIST_SHOWN);
                ContactListFragment.this.applicationManager.loadingComplete(CommsView.ContactList);
                return;
            }
            MetricsHelper.stopAppDurationTimers();
            ContactListFragment.this.applicationManager.navigateHome();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class ContactOperationResponseReceiver extends BroadcastReceiver {
        private ContactOperationResponseReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ContactListFragment.LOG.i("Received broadcast from Contacts sync service.");
            if (!ContactListFragment.this.isAdded()) {
                ContactListFragment.LOG.i("Fragment not added to any activity");
                return;
            }
            ContactListFragment.this.unregisterServiceReceiver();
            String action = intent.getAction();
            boolean booleanExtra = intent.getBooleanExtra(ContactsOperationsManager.OPERATION_RESULT_VALUE, false);
            if (!ContactsOperationsManager.PULL_CONTACT_RESULT.equals(action)) {
                return;
            }
            if (booleanExtra) {
                ContactListFragment.this.getLoaderManager().restartLoader(1, null, new ContactsLoaderCallbacks());
                ContactListFragment.this.dismissCurrentDialog();
                return;
            }
            MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, MetricKeys.SCREEN_NAME_CONTACT_LIST, AlertSource.newClassSource(ContactListFragment.this));
            ContactListFragment contactListFragment = ContactListFragment.this;
            contactListFragment.showResultsDialog(contactListFragment.getString(R.string.sync_contacts_failure_msg));
        }

        /* synthetic */ ContactOperationResponseReceiver(AnonymousClass1 anonymousClass1) {
        }
    }

    /* loaded from: classes12.dex */
    public class ContactsLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {
        public ContactsLoaderCallbacks() {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            String str;
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("%");
            outline1.append(ContactListFragment.this.mSearchView.getQuery().toString().trim().replaceAll("\\s", ""));
            outline1.append("%");
            String sb2 = outline1.toString();
            ArrayList arrayList2 = new ArrayList();
            if (i == 0) {
                if (ContactListFragment.this.commsIdentityManager.getPreferredMarketplace(false).equals("JP")) {
                    str = ContactsProviderUtils.getContactsSortOrderByJP(ContactListFragment.this.getContext());
                    ContactListFragment.this.customizeQuerySelectionBuilderForPFM(sb, true);
                    arrayList2.add(sb2);
                } else {
                    str = ContactsProviderUtils.getContactsSortOrder(ContactListFragment.this.getContext());
                    ContactListFragment.this.customizeQuerySelectionBuilderForPFM(sb, false);
                }
                arrayList2.add(sb2);
                arrayList2.add(sb2);
                arrayList2.add(sb2);
                arrayList2.add(sb2);
                if (!TextUtils.equals(ContactListFragment.mFragmentToBeLaunched, Constants.FRAGMENT_START_NEW_CONVERSATION)) {
                    if (TextUtils.equals(ContactListFragment.mFragmentToBeLaunched, Constants.FRAGMENT_CALL_BOTTOM_SHEET)) {
                        sb.append(" AND ");
                        if (CoboUtils.shouldShowPSTNForNonCOBORegion() && DeviceInfo.isPhone(ContactListFragment.this.getContext())) {
                            GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(sb, " ((", "number", " IS NOT NULL AND ", "LENGTH(number)"), " > 0", ") OR ", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD, " = 1").append(")");
                        } else if (CoboUtils.isCoboCallingAvailable()) {
                            sb.append("(");
                            sb.append(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED);
                            sb.append(" = 1");
                            if (Utils.isFireOS()) {
                                StringBuilder outline2 = GeneratedOutlineSupport.outline2(sb, " OR (", ContactProviderContract.PhoneNumberEntry.COLUMN_IS_COBO_CALLABLE, " = 1", " AND ");
                                outline2.append(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_CONTACT_SOURCE_TYPE);
                                outline2.append(" != ");
                                StringBuilder outline12 = GeneratedOutlineSupport.outline1(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                                outline12.append(ContactSourceType.SkypeContact.getName());
                                outline12.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
                                outline2.append(outline12.toString());
                                outline2.append("))");
                            } else {
                                GeneratedOutlineSupport1.outline180(sb, " OR ", ContactProviderContract.PhoneNumberEntry.COLUMN_IS_COBO_CALLABLE, " = 1) ");
                            }
                        } else {
                            sb.append(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED);
                            sb.append(" = 1");
                        }
                    } else {
                        StringBuilder outline22 = GeneratedOutlineSupport.outline2(sb, " AND ((", "commsId", " IS NOT NULL AND LENGTH(", "commsId");
                        outline22.append(") > 0");
                        outline22.append(" AND ");
                        outline22.append(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED);
                        outline22.append(" = 1)");
                        if (CoboUtils.isCoboCallingAvailable() || CoboUtils.shouldShowPSTNForNonCOBORegion()) {
                            GeneratedOutlineSupport1.outline180(sb, " OR (", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED, " = 0)");
                        }
                        sb.append(" )");
                    }
                } else {
                    StringBuilder outline23 = GeneratedOutlineSupport.outline2(sb, " AND ", "commsId", " IS NOT NULL AND LENGTH(", "commsId");
                    outline23.append(") > 0");
                    outline23.append(" AND ");
                    outline23.append(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED);
                    outline23.append(" = 1");
                }
                GeneratedOutlineSupport1.outline180(sb, " AND ", "ownerCommsId", " IN (?, ?)");
                arrayList2.add(ContactListFragment.this.commsIdentityManager.getCommsId("ContactsLoaderCallbacks", false));
                arrayList2.add(ContactListFragment.this.commsIdentityManager.getHomeGroupId("ContactsLoaderCallbacks", false));
            } else {
                str = null;
                sb.append("commsId");
                sb.append(" IN (?) ");
            }
            GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(sb, " AND ", ContactProviderContract.PhoneNumberEntry.COLUMN_IS_HOME_GROUP, "= 0", " AND "), ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SHOULD_DISPLAY, "= 1", " GROUP BY ", ContactProviderContract.ContactDatabaseEntry.TABLE_NAME_PREFIX).append("serverContactId");
            arrayList.add(ContactListFragment.this.commsIdentityManager.getCommsId("ContactsLoaderCallbacks", false));
            arrayList.addAll(arrayList2);
            String[] strArr = new String[arrayList.size()];
            arrayList.toArray(strArr);
            return new CursorLoader(ContactListFragment.this.getActivity().getApplicationContext(), ContactProviderContract.getContactJoinPhoneNumberUri(ContactListFragment.this.getContext()), null, sb.toString(), strArr, str);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<Cursor> loader) {
            if (loader.getId() == 0) {
                ContactListFragment.this.mContactsAdapter.changeCursor(null);
            }
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            MenuItem findItem;
            ContactListFragment.this.mLoadingView.setVisibility(8);
            if (cursor == null) {
                return;
            }
            if (loader.getId() == 0) {
                ContactListFragment.this.mContactsAdapter.changeCursor(cursor);
                cursor.setNotificationUri(ContactListFragment.this.getActivity().getContentResolver(), ContactProviderContract.getContactsUri(ContactListFragment.this.getContext()));
                if (ContactListFragment.this.mMenu != null && (findItem = ContactListFragment.this.mMenu.findItem(R.id.manage_contacts)) != null) {
                    ContactListFragment contactListFragment = ContactListFragment.this;
                    contactListFragment.mShouldShowManageMenu = contactListFragment.shouldShowManageMenu(cursor);
                    findItem.setVisible(ContactListFragment.this.mShouldShowManageMenu);
                }
            } else {
                ContactListFragment.this.setMyProfileData(cursor);
            }
            if (!TextUtils.equals(ContactListFragment.mFragmentToBeLaunched, Constants.FRAGMENT_CALL_BOTTOM_SHEET)) {
                return;
            }
            List<DeviceModel> list = null;
            if (ContactListFragment.this.devicesSource.getMemoryData() != null) {
                list = ContactListFragment.this.devicesSource.getMemoryData().getDeviceModels();
            }
            if (list == null) {
                return;
            }
            ContactListFragment.this.refreshMyDevices(list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class ReactNativeContactSyncStatusReceiver extends BroadcastReceiver {
        private ReactNativeContactSyncStatusReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String stringExtra = intent.getStringExtra(Constants.REACT_NATIVE_CONTACTS_IMPORT_INTENT_KEY);
            if (Constants.REACT_NATIVE_CONTACTS_IMPORT_STARTED.equals(stringExtra)) {
                ContactListFragment.this.showReactNativeSyncLoader();
            } else if (!Constants.REACT_NATIVE_CONTACTS_IMPORT_COMPLETED.equals(stringExtra) && !Constants.REACT_NATIVE_CONTACTS_IMPORT_ERROR.equals(stringExtra)) {
            } else {
                ContactListFragment.this.reloadFragment();
            }
        }

        /* synthetic */ ReactNativeContactSyncStatusReceiver(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class SearchViewFilter implements SearchView.OnQueryTextListener {
        private SearchViewFilter() {
        }

        @Override // android.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextChange(String str) {
            ContactListFragment.this.getLoaderManager().restartLoader(0, null, new ContactsLoaderCallbacks());
            ContactListFragment.this.getLoaderManager().restartLoader(1, null, new ContactsLoaderCallbacks());
            ContactListFragment.this.mContactsAdapter.notifyDataSetChanged();
            ContactListFragment.this.mFooterView.setVisibility(8);
            if (Strings.isNullOrEmpty(str)) {
                ContactListFragment.this.setupHeader();
            } else {
                ContactListFragment.this.mHeaderView.findViewById(R.id.add_contact_view).setVisibility(8);
            }
            return true;
        }

        @Override // android.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextSubmit(String str) {
            ContactListFragment.this.mSearchView.clearFocus();
            ContactListFragment.this.mListView.requestFocus();
            return true;
        }

        /* synthetic */ SearchViewFilter(AnonymousClass1 anonymousClass1) {
        }
    }

    public ContactListFragment() {
    }

    private void checkContactsPermissionsAndShowDialog() {
        if (PermissionsHelper.checkPermission(getContext(), "android.permission.READ_CONTACTS") || ((Boolean) SharedPreferencesUtils.getCacheValue(getContext(), Constants.INITIAL_CONTACTS_PERMISSION_PROMPT_SHOWN_PREF, false)).booleanValue()) {
            return;
        }
        SharedPreferencesUtils.persistCacheValues(getContext(), Constants.INITIAL_CONTACTS_PERMISSION_PROMPT_SHOWN_PREF, true);
        if (shouldShowRequestPermissionRationale("android.permission.READ_CONTACTS")) {
            PermissionsHelper.showPermissionNotAvailableDialog(getActivity(), null, MetricKeys.ALERT_PERM_CONTACTS, MetricKeys.SCREEN_NAME_CONTACT_LIST, AlertSource.newClassSource(ContactListFragment.class.getName()), R.style.AlexaCustomFiestaDialogStyle, R.layout.contact_permission_dialog, false, null);
        } else {
            getParentFragment().requestPermissions(new String[]{"android.permission.READ_CONTACTS"}, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configureFragmentRequirements() {
        this.applicationManager.configurePageForFragment(new FragmentRequirements(this).withTitle(this.mFragmentTitle).withMenu(R.menu.contacts_list_menu, new Toolbar.OnMenuItemClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$UeKlLQRpp1r1Mogon_lY7RtrUGk
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return ContactListFragment.this.lambda$configureFragmentRequirements$3$ContactListFragment(menuItem);
            }
        }, new FragmentRequirements.MenuInflatedCallback() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$Ky0qp2g9hMhxJ0WGx5r11AzkqDY
            @Override // com.amazon.deecomms.api.navigation.FragmentRequirements.MenuInflatedCallback
            public final void onInflated(Menu menu) {
                ContactListFragment.this.lambda$configureFragmentRequirements$4$ContactListFragment(menu);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void customizeQuerySelectionBuilderForPFM(StringBuilder sb, boolean z) {
        if (z) {
            GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(sb, "commsId", " NOT IN (?) ", " AND ((", "IFNULL("), "lastName", ",", "''", ")"), " || ", "IFNULL(", "firstName", ","), "''", ") LIKE (?) ) OR (", "firstName", " IS NULL AND "), "lastName", " IS NULL AND ", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, " LIKE (?) ) OR ("), "firstName", " IS NULL AND ", "lastName", " IS NULL AND "), ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, " IS NULL AND ", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, " LIKE (?) ) OR ("), "firstName", " IS NULL AND ", "lastName", " IS NULL AND "), ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, " IS NULL AND ", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, " IS NULL AND "), "company", " LIKE (?) ) OR (", "IFNULL(", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME), ",", "''", ")", " || "), "IFNULL(", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME, ",", "''").append(") LIKE (?) ))");
            return;
        }
        StringBuilder outline2 = GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(sb, "commsId", " NOT IN (?) ", " AND ((", "IFNULL("), "firstName", ",", "''", ")"), " || ", "IFNULL(", "lastName", ","), "''", ") LIKE (?) ) OR (", "firstName", " IS NULL AND "), "lastName", " IS NULL AND ", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, " LIKE (?) ) OR ("), "firstName", " IS NULL AND ", "lastName", " IS NULL AND "), ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, " IS NULL AND ", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, " LIKE (?) ) OR ("), "firstName", " IS NULL AND ", "lastName", " IS NULL AND "), ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, " IS NULL AND ", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_SOURCE_NICK_NAME, " IS NULL AND ");
        outline2.append("company");
        outline2.append(" LIKE (?) ))");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissCurrentDialog() {
        Dialog dialog = this.mCurrentDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.mCurrentDialog = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void downloadContactsFromServer() {
        if (this.isLowStorage) {
            showLowStorageDialog();
            LOG.i("downloadContactsFromServer - low storage - do not sync");
        } else if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_LIST, AlertSource.newClassSource(ContactListFragment.class.getName()))) {
            this.mSwipeRefreshLayout.setRefreshing(false);
            LOG.i("downloadContactsFromServer - offline - do not sync");
        } else {
            LOG.i("downloadContactsFromServer - sync contacts via pullContacts");
            this.contactManager.pullContacts(true);
            this.mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private void fetchMyDeviceList() {
        new AsyncTask<Void, Void, List<DeviceModel>>() { // from class: com.amazon.deecomms.contacts.ui.ContactListFragment.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public List<DeviceModel> doInBackground(Void... voidArr) {
                return Utils.fetchMyDevices(ContactListFragment.this.devicesSource, false);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(List<DeviceModel> list) {
                if (list != null) {
                    ContactListFragment.this.refreshMyDevices(list);
                }
            }
        }.execute(new Void[0]);
    }

    private String getEducationalTextString() {
        if (!this.capabilitiesManager.isEducationalTextEnabled()) {
            return "";
        }
        String str = mFragmentToBeLaunched;
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != 1069467150) {
            if (hashCode != 1097650749) {
                if (hashCode == 1209030014 && str.equals(Constants.FRAGMENT_CONTACT_CARD)) {
                    c = 0;
                }
            } else if (str.equals(Constants.FRAGMENT_CALL_BOTTOM_SHEET)) {
                c = 2;
            }
        } else if (str.equals(Constants.FRAGMENT_START_NEW_CONVERSATION)) {
            c = 1;
        }
        if (c == 0) {
            return getResources().getString(R.string.contact_list_educational);
        }
        if (c == 1) {
            return getResources().getString(R.string.contact_list_educational_message);
        }
        return c != 2 ? "" : getResources().getString(R.string.contact_list_educational_call);
    }

    private void initCallBottomSheet(@Nullable ContactEntry contactEntry, @Nullable String str) {
        if (contactEntry == null) {
            LOG.e("Can't initialize call bottom sheet, contact is null");
            return;
        }
        this.callBottomSheet = CallBottomSheetDialogFragment.newInstance(contactEntry, str, new CallBottomSheetType[]{CallBottomSheetType.AUDIO, CallBottomSheetType.VIDEO, CallBottomSheetType.PhoneNumber}, this.capabilitiesManager.isThemedUIEnabled(), this.capabilitiesManager.isMosaicThemingEnabled(), this.initiationLogicFactory);
        this.callBottomSheet.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$pp4VI8rW-POAZ4xJrDzRiOKy19Y
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                ContactListFragment.this.lambda$initCallBottomSheet$8$ContactListFragment(dialogInterface);
            }
        });
        this.callBottomSheet.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$fm9GfD_A5m84PQpNVoRCC_W3hVI
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                ContactListFragment.this.lambda$initCallBottomSheet$9$ContactListFragment(dialogInterface);
            }
        });
        this.callBottomSheet.show(getFragmentManager(), this.callBottomSheet.getTag());
    }

    private void initViews() {
        this.mLoadingView = (TextView) this.contactListView.findViewById(R.id.loading_contacts);
        this.mListView = (ListView) this.contactListView.findViewById(16908298);
        this.mContactsAdapter = new ContactListAdapter(getActivity().getApplicationContext(), false, this.capabilitiesManager.isThemedUIEnabled());
        this.mContactsAdapter.registerDataSetObserver(this.mContactsChangedObserver);
        this.isDataSetObserverRegistered = true;
        this.mListView.addHeaderView(this.mHeaderView, null, true);
        this.mListView.addFooterView(this.mFooterView, null, false);
        this.mListView.setAdapter((ListAdapter) this.mContactsAdapter);
        this.mListView.setHeaderDividersEnabled(false);
        this.mListView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$3zFmZUlkiAh2cIKdlVPNgha3_Z4
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return ContactListFragment.this.lambda$initViews$6$ContactListFragment(view, motionEvent);
            }
        });
        this.mLoadingView.setVisibility(0);
        this.mSearchView = (SearchView) this.contactListView.findViewById(R.id.searchView);
        setUpSearchView();
        if (TextUtils.equals(mFragmentToBeLaunched, Constants.FRAGMENT_CALL_BOTTOM_SHEET)) {
            fetchMyDeviceList();
        }
        getLoaderManager().initLoader(0, null, new ContactsLoaderCallbacks());
        getLoaderManager().initLoader(1, null, new ContactsLoaderCallbacks());
        this.mListView.setOnScrollListener(new AbsListView.OnScrollListener() { // from class: com.amazon.deecomms.contacts.ui.ContactListFragment.4
            @Override // android.widget.AbsListView.OnScrollListener
            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                boolean z = false;
                int top = (absListView == null || absListView.getChildCount() == 0) ? 0 : ContactListFragment.this.mListView.getChildAt(0).getTop();
                SwipeRefreshLayout swipeRefreshLayout = ContactListFragment.this.mSwipeRefreshLayout;
                if (top >= 0) {
                    z = true;
                }
                swipeRefreshLayout.setEnabled(z);
            }

            @Override // android.widget.AbsListView.OnScrollListener
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }
        });
        this.mListView.setItemsCanFocus(true);
    }

    private boolean isContactPermissionsGranted() {
        return PermissionsHelper.checkPermission(getContext(), "android.permission.READ_CONTACTS");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isProvisionedForContactList() {
        CommsProvisionStatus provisionStatus = this.commsIdentityManager.getProvisionStatus(true, "ContactListFragment.isProvisionedForContactList", false);
        return CommsProvisionStatus.PROVISIONED.equals(provisionStatus) || (this.capabilitiesManager.isAutoProvisioningEnabled() && CommsProvisionStatus.AUTO_PROVISIONED.equals(provisionStatus));
    }

    private boolean isSyncPreferenceEnabled() {
        String stringPreferenceFromSharedPrefs = Utils.getStringPreferenceFromSharedPrefs(getContext(), Constants.CONTACT_SYNC_PREF, null);
        return "true".equals(stringPreferenceFromSharedPrefs) || ("none".equals(stringPreferenceFromSharedPrefs) && isContactPermissionsGranted());
    }

    private ProgressDialog newProgressDialog(Context context, String str) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(str);
        progressDialog.setCancelable(false);
        return progressDialog;
    }

    private void onContactListItemClicked(ContactEntry contactEntry, String str, boolean z) {
        char c;
        String str2 = mFragmentToBeLaunched;
        int hashCode = str2.hashCode();
        if (hashCode == 91484943) {
            if (str2.equals(Constants.FRAGMENT_START_IMAGE_SHARE)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == 1069467150) {
            if (str2.equals(Constants.FRAGMENT_START_NEW_CONVERSATION)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 1097650749) {
            if (hashCode == 1209030014 && str2.equals(Constants.FRAGMENT_CONTACT_CARD)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str2.equals(Constants.FRAGMENT_CALL_BOTTOM_SHEET)) {
                c = 3;
            }
            c = 65535;
        }
        if (c == 0) {
            switchToMessageThread(contactEntry, str, z);
        } else if (c == 1) {
            startShareActivityForContact(contactEntry);
        } else if (c == 2) {
            switchToContactCard(contactEntry);
        } else if (c != 3) {
            LOG.i("Trying to move to an invalid fragment from contact list");
        } else {
            initCallBottomSheet(contactEntry, str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLowStorageEvent(boolean z) {
        if (z) {
            this.mSwipeRefreshLayout.setEnabled(false);
        } else {
            this.mSwipeRefreshLayout.setEnabled(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshMyDevices(@NonNull List<DeviceModel> list) {
        if (getActivity() != null && !getActivity().isFinishing()) {
            if (this.capabilitiesManager.isThemedUIEnabled()) {
                LOG.i("Do not show 'My Devices' in Call Screen");
                return;
            }
            Utils.filterDevices(list);
            LinearLayout linearLayout = (LinearLayout) this.mHeaderView.findViewById(R.id.my_devices_container);
            CommsTextView commsTextView = (CommsTextView) this.mHeaderView.findViewById(R.id.device_list_title);
            if (list.size() == 0) {
                linearLayout.setVisibility(8);
                commsTextView.setVisibility(8);
                return;
            }
            linearLayout.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                final DeviceModel deviceModel = list.get(i);
                final DeviceStatusModel deviceStatus = deviceModel.getDeviceStatus();
                boolean z = true;
                if (i != list.size() - 1) {
                    z = false;
                }
                View inflate = getActivity().getLayoutInflater().inflate(R.layout.my_device_list_item, (ViewGroup) linearLayout, false);
                inflate.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.contacts.ui.ContactListFragment.6
                    @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
                    public void onSingleClick(View view) {
                        CallInitiator callInitiator = new CallInitiator(MetricKeys.CALL_INITIATED_FROM_CALL_DIALOG);
                        FragmentActivity activity = ContactListFragment.this.getActivity();
                        ContactListFragment.this.initiationLogicFactory.create(callInitiator, activity, activity, new CallHelper(), MetricKeys.CALL_INITIATED_FROM_CALL_DIALOG, Constants.Telemetry.CallInitScreenNames.CALL_DIALOG).initiateTargetedDropIn(ContactListFragment.this.commsIdentityManager.getCommsId("ContactListFragment.refreshMyDevices", false), null, deviceModel.getDeviceGruu(), deviceModel.getDeviceName(), deviceStatus.isVideoEnabled(), true);
                    }
                });
                ((TextView) inflate.findViewById(R.id.device_name)).setText(deviceModel.getDeviceName());
                View findViewById = inflate.findViewById(R.id.device_list_divider);
                if (!z) {
                    findViewById.setVisibility(0);
                } else {
                    findViewById.setVisibility(4);
                }
                linearLayout.addView(inflate);
            }
            linearLayout.setVisibility(0);
            commsTextView.setVisibility(0);
            return;
        }
        LOG.d("Not refreshing my devices as the activity object is null / finishing");
    }

    private void registerReactNativeSyncReceiver() {
        if (this.mReactNativeSyncReceiver == null) {
            this.mReactNativeSyncReceiver = new ReactNativeContactSyncStatusReceiver(null);
        }
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mReactNativeSyncReceiver, new IntentFilter(Constants.REACT_NATIVE_CONTACTS_IMPORT_INTENT_ACTION));
    }

    private void registerServiceReceiver(String str) {
        if (this.mContactOperationReceiver == null) {
            this.mContactOperationReceiver = new ContactOperationResponseReceiver(null);
        }
        this.mOperation = str;
        LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).registerReceiver(this.mContactOperationReceiver, new IntentFilter(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reloadFragment() {
        getFragmentManager().beginTransaction().detach(this).attach(this).commit();
    }

    private void restoreContactOperation(String str) {
        if (this.contactsManager.isImportInProgress()) {
            registerServiceReceiver(ContactsOperationsManager.CONTACT_IMPORT_RESULT);
            ProgressDialog newProgressDialog = newProgressDialog(getActivity(), getString(R.string.importing_contacts));
            dismissCurrentDialog();
            newProgressDialog.show();
            this.mCurrentDialog = newProgressDialog;
        } else if (!this.contactsManager.isContactsPullInProgress()) {
        } else {
            if (!ContactsOperationsManager.PULL_CONTACT_RESULT.equals(str) && this.contactsManager.isInitialContactsDownloadDone()) {
                return;
            }
            registerServiceReceiver(ContactsOperationsManager.PULL_CONTACT_RESULT);
            ProgressDialog newProgressDialog2 = newProgressDialog(getActivity(), getString(R.string.loading_contacts));
            dismissCurrentDialog();
            newProgressDialog2.show();
            this.mCurrentDialog = newProgressDialog2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
        r9 = new com.amazon.deecomms.contacts.model.ContactName();
        r9.setFirstName(getActivity().getResources().getString(com.amazon.deecomms.R.string.default_my_profile_name));
        r9.setLastName(null);
        r3 = new com.amazon.deecomms.contacts.database.ContactEntry();
        r3.setContactName(r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void setMyProfileData(android.database.Cursor r9) {
        /*
            r8 = this;
            if (r9 != 0) goto L3
            return
        L3:
            android.view.View r0 = r8.mHeaderView
            int r1 = com.amazon.deecomms.R.id.my_profile_view
            android.view.View r0 = r0.findViewById(r1)
            java.lang.String r1 = com.amazon.deecomms.contacts.ui.ContactListFragment.mFragmentToBeLaunched
            java.lang.String r2 = "FRAGMENT_CALL_BOTTOM_SHEET"
            boolean r1 = android.text.TextUtils.equals(r1, r2)
            r2 = 8
            if (r1 == 0) goto L1b
            r0.setVisibility(r2)
            return
        L1b:
            r9.moveToFirst()
            r1 = 0
            r3 = r1
        L20:
            boolean r4 = r9.isAfterLast()
            java.lang.String r5 = "ContactListFragment.setMyProfileData"
            r6 = 0
            if (r4 != 0) goto L44
            com.amazon.deecomms.contacts.database.ContactEntry r3 = com.amazon.deecomms.contacts.util.ContactsProviderUtils.getContactEntryFromCursor(r9)
            if (r3 == 0) goto L44
            java.lang.String r4 = r3.getOwnerCommsId()
            com.amazon.deecomms.api.CommsIdentityManager r7 = r8.commsIdentityManager
            java.lang.String r7 = r7.getHomeGroupId(r5, r6)
            boolean r4 = android.text.TextUtils.equals(r4, r7)
            if (r4 == 0) goto L40
            goto L44
        L40:
            r9.moveToNext()
            goto L20
        L44:
            if (r3 != 0) goto L67
            com.amazon.deecomms.contacts.model.ContactName r9 = new com.amazon.deecomms.contacts.model.ContactName
            r9.<init>()
            androidx.fragment.app.FragmentActivity r3 = r8.getActivity()
            android.content.res.Resources r3 = r3.getResources()
            int r4 = com.amazon.deecomms.R.string.default_my_profile_name
            java.lang.String r3 = r3.getString(r4)
            r9.setFirstName(r3)
            r9.setLastName(r1)
            com.amazon.deecomms.contacts.database.ContactEntry r3 = new com.amazon.deecomms.contacts.database.ContactEntry
            r3.<init>()
            r3.setContactName(r9)
        L67:
            android.widget.SearchView r9 = r8.mSearchView
            java.lang.CharSequence r9 = r9.getQuery()
            java.lang.String r9 = r9.toString()
            java.lang.String r9 = r9.trim()
            boolean r9 = android.text.TextUtils.isEmpty(r9)
            if (r9 != 0) goto L7f
            r0.setVisibility(r2)
            goto L8b
        L7f:
            r0.setVisibility(r6)
            com.amazon.deecomms.api.CommsIdentityManager r9 = r8.commsIdentityManager
            java.lang.String r9 = r9.getCommsId(r5, r6)
            r8.setupMyProfileDataView(r0, r3, r9)
        L8b:
            com.amazon.deecomms.api.CommsIdentityManager r9 = r8.commsIdentityManager
            java.lang.String r9 = r9.getCommsId(r5, r6)
            r8.setupMyProfileDataView(r0, r3, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.contacts.ui.ContactListFragment.setMyProfileData(android.database.Cursor):void");
    }

    private void setUpSearchView() {
        this.mSearchView.setOnQueryTextListener(new SearchViewFilter(null));
        this.mSearchView.setSubmitButtonEnabled(true);
        int identifier = getResources().getIdentifier("android:id/search_src_text", null, null);
        int identifier2 = getResources().getIdentifier("android:id/search_go_btn", null, null);
        Typeface font = FontResolver.getFont(getContext(), Font.AMAZON_EMBER_REGULAR);
        ImageView imageView = (ImageView) this.mSearchView.findViewById(identifier2);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        imageView.setVisibility(8);
        EditText editText = (EditText) this.mSearchView.findViewById(identifier);
        editText.setTypeface(font);
        if (this.capabilitiesManager.isThemedUIEnabled()) {
            editText.setTextColor(ContextCompat.getColor(getContext(), R.color.fiesta_text_primary));
            editText.setHintTextColor(ContextCompat.getColor(getContext(), R.color.fiesta_text_primary));
            return;
        }
        editText.setTextColor(ContextCompat.getColor(getContext(), R.color.ultra_dark));
        editText.setHintTextColor(ContextCompat.getColor(getContext(), R.color.lightGray));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupFooter() {
        View view = this.mFooterView;
        if (view == null) {
            return;
        }
        View findViewById = view.findViewById(R.id.contacts_no_perms_footer);
        CommsTextView commsTextView = (CommsTextView) this.mFooterView.findViewById(R.id.contacts_prompt_footer);
        showImportContactIngress();
        if (isSyncPreferenceEnabled() && isContactPermissionsGranted()) {
            if (getContext().getSharedPreferences("SHARED_PREFS", 0).getBoolean(Constants.SHOULD_SUPPORT_CONTACTS_ON_DEVICES, true) && !isContactPermissionsGranted()) {
                findViewById.setVisibility(0);
                commsTextView.setVisibility(8);
                CommsTextView commsTextView2 = (CommsTextView) this.mFooterView.findViewById(R.id.footer_text);
                CommsTextView commsTextView3 = (CommsTextView) this.mFooterView.findViewById(R.id.settings_link);
                if (shouldShowRequestPermissionRationale("android.permission.READ_CONTACTS")) {
                    commsTextView2.setText(getResources().getString(R.string.list_footer_text_with_contacts_permissions_denied));
                    commsTextView3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$O6wv6M-tlD_ar5HzUWNie3n603A
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            ContactListFragment.this.lambda$setupFooter$12$ContactListFragment(view2);
                        }
                    });
                    return;
                }
                commsTextView2.setText(getResources().getString(R.string.list_footer_text_with_contacts_permision_selection_not_made));
                commsTextView3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$9kHDF2LBukIEOyV1C3I-1s0FC28
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        ContactListFragment.this.lambda$setupFooter$13$ContactListFragment(view2);
                    }
                });
                return;
            }
            findViewById.setVisibility(8);
            commsTextView.setVisibility(0);
            if (DeviceInfo.isPhone(getContext())) {
                commsTextView.setText(getString(R.string.list_footer_text_with_contacts_permissions));
                return;
            } else {
                commsTextView.setText(getString(R.string.tablet_list_footer_text_with_contacts_permissions));
                return;
            }
        }
        commsTextView.setVisibility(8);
        findViewById.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupHeader() {
        showAddContactIngress(this.mHeaderView.findViewById(R.id.add_contact_view));
    }

    private void setupMyProfileDataView(View view, final ContactEntry contactEntry, final String str) {
        TextView textView = (TextView) view.findViewById(R.id.my_profile_name);
        TextView textView2 = (TextView) view.findViewById(R.id.my_profile_description);
        if (textView == null || textView2 == null) {
            return;
        }
        textView.setText(ContactUtils.getFullName(ContactNameHelper.getActiveContactName()));
        textView2.setText(getResources().getText(R.string.my_profile));
        textView.setSingleLine(true);
        view.setClickable(true);
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$eVUA_gN09qCwfTn6PFUcEBXZqFQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ContactListFragment.this.lambda$setupMyProfileDataView$7$ContactListFragment(contactEntry, str, view2);
            }
        });
    }

    private void setupSubscriber() {
        this.lowStorageSubscriber = new SimpleSubscriber<Boolean>() { // from class: com.amazon.deecomms.contacts.ui.ContactListFragment.2
            @Override // com.amazon.deecomms.rx.SimpleSubscriber
            public void onCall(Boolean bool) {
                ContactListFragment.this.isLowStorage = bool.booleanValue();
                ContactListFragment contactListFragment = ContactListFragment.this;
                contactListFragment.onLowStorageEvent(contactListFragment.isLowStorage);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldShowManageMenu(Cursor cursor) {
        if (!TextUtils.equals(mFragmentToBeLaunched, Constants.FRAGMENT_START_NEW_CONVERSATION) && !TextUtils.equals(mFragmentToBeLaunched, Constants.FRAGMENT_CALL_BOTTOM_SHEET)) {
            if (this.capabilitiesManager.isContactsManagementEnabled()) {
                return true;
            }
            if (cursor != null && cursor.getCount() != 0) {
                String commsId = this.commsIdentityManager.getCommsId("ContactListFragment.shouldShowManageMenu", false);
                String homeGroupId = this.commsIdentityManager.getHomeGroupId("ContactListFragment.shouldShowManageMenu", false);
                cursor.moveToFirst();
                while (!cursor.isAfterLast()) {
                    boolean z = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED))) != 0;
                    String string = cursor.getString(cursor.getColumnIndex("commsId"));
                    if (z && !TextUtils.equals(string, commsId) && !TextUtils.equals(string, homeGroupId)) {
                        return true;
                    }
                    cursor.moveToNext();
                }
            }
        }
        return false;
    }

    private boolean shouldShowReactNativeSyncLoader() {
        return !Utils.getBooleanPreferenceFromSharedPrefs(getActivity(), Constants.REACT_NATIVE_CONTACTS_SYNC_COMPLETED, false);
    }

    private void showDialog(Dialog dialog) {
        dismissCurrentDialog();
        dialog.show();
        this.mCurrentDialog = dialog;
    }

    private void showLowStorageDialog() {
        if (this.lowStorageDialog == null) {
            this.lowStorageDialog = new AlertDialog.Builder(getContext()).setTitle(R.string.error_title).setMessage(R.string.message_low_memory).setPositiveButton(R.string.dialog_ok_button, $$Lambda$ContactListFragment$bj8vIeeHd0_GxHv_3l2LS_TYogs.INSTANCE).create();
        }
        if (!this.lowStorageDialog.isShowing()) {
            this.lowStorageDialog.show();
        }
        this.mSwipeRefreshLayout.setRefreshing(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showReactNativeSyncLoader() {
        if (!shouldShowReactNativeSyncLoader()) {
            return;
        }
        ProgressDialog newProgressDialog = newProgressDialog(getActivity(), getString(R.string.loading_contacts));
        dismissCurrentDialog();
        newProgressDialog.show();
        this.mCurrentDialog = newProgressDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showResultsDialog(String str) {
        showDialog(new AlertDialog.Builder(getActivity()).setTitle(R.string.import_contact_dialog_title).setMessage(str).setPositiveButton(getString(R.string.dialog_ok_button), new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$HNjyRl3P2rJoo2U3eckCkuZ9nrI
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ContactListFragment.this.lambda$showResultsDialog$5$ContactListFragment(dialogInterface, i);
            }
        }).setCancelable(true).create());
    }

    private void startShareActivityForContact(ContactEntry contactEntry) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.addFlags(536870912);
        startActivityForResult(Intent.createChooser(intent, "Choose a photo FFS"), AutosaveItemDao.QUERY_WHERE_IN_BATCH_SIZE);
    }

    private void switchToContactSyncPermissionsPage() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACT_LIST_IMPORT_BUTTON_CLICK);
        this.applicationManager.navigateToView(CommsView.ReactNativeImportContacts, new Bundle(), true);
    }

    private void switchToMessageThread(ContactEntry contactEntry, String str, boolean z) {
        Bundle outline11 = GeneratedOutlineSupport1.outline11(Constants.BUNDLE_KEY_RECIPIENT_ID, str);
        outline11.putString(Constants.BUNDLE_KEY_VIEW_AS_COMMS_ID, this.commsIdentityManager.getCommsId("ContactListFragment.switchToMessageThread", false));
        if (z) {
            outline11.putBoolean(Constants.BUNDLE_KEY_MY_PROFILE, true);
        }
        outline11.putParcelable(Constants.BUNDLE_KEY_CONTACT_NAME_KEY, contactEntry.getContactName());
        this.applicationManager.navigateToView(CommsView.ReactNativeConversation, outline11, true);
    }

    private void syncContactsOnImport() {
        ProgressDialog newProgressDialog = newProgressDialog(getActivity(), getString(R.string.loading_contacts));
        dismissCurrentDialog();
        newProgressDialog.show();
        this.mCurrentDialog = newProgressDialog;
        registerServiceReceiver(ContactsOperationsManager.PULL_CONTACT_RESULT);
        if (!this.contactsManager.pullContacts(true)) {
            unregisterServiceReceiver();
            LOG.w("Contact sync not run after import.");
            MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_OFFLINE, MetricKeys.SCREEN_NAME_CONTACT_LIST, AlertSource.newClassSource(ContactListFragment.class.getName()));
            showResultsDialog(getString(R.string.sync_contacts_failure_msg));
        }
    }

    private void unregisterReactNativeSyncReceiver() {
        if (this.mReactNativeSyncReceiver != null) {
            LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mReactNativeSyncReceiver);
            this.mReactNativeSyncReceiver = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unregisterServiceReceiver() {
        if (this.mContactOperationReceiver != null) {
            LocalBroadcastManager.getInstance(getActivity().getApplicationContext()).unregisterReceiver(this.mContactOperationReceiver);
            this.mContactOperationReceiver = null;
            this.mOperation = null;
        }
    }

    public /* synthetic */ boolean lambda$configureFragmentRequirements$3$ContactListFragment(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.manage_contacts) {
            switchToManageContacts();
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$configureFragmentRequirements$4$ContactListFragment(Menu menu) {
        this.mMenu = menu;
    }

    public /* synthetic */ void lambda$initCallBottomSheet$8$ContactListFragment(DialogInterface dialogInterface) {
        this.callBottomSheet = null;
    }

    public /* synthetic */ void lambda$initCallBottomSheet$9$ContactListFragment(DialogInterface dialogInterface) {
        this.callBottomSheet = null;
    }

    public /* synthetic */ boolean lambda$initViews$6$ContactListFragment(View view, MotionEvent motionEvent) {
        Utils.hideKeyboard(getActivity());
        return false;
    }

    public /* synthetic */ void lambda$setupFooter$12$ContactListFragment(View view) {
        LOG.i("Navigating to app settings for permissions");
        Utils.navigateToAppSettingsPage(getContext());
    }

    public /* synthetic */ void lambda$setupFooter$13$ContactListFragment(View view) {
        getParentFragment().requestPermissions(new String[]{"android.permission.READ_CONTACTS"}, 0);
    }

    public /* synthetic */ void lambda$setupMyProfileDataView$7$ContactListFragment(ContactEntry contactEntry, String str, View view) {
        onContactListItemClicked(contactEntry, str, true);
    }

    public /* synthetic */ void lambda$showAddContactIngress$10$ContactListFragment(View view) {
        switchToAddContacts();
    }

    public /* synthetic */ void lambda$showImportContactIngress$11$ContactListFragment(View view) {
        switchToContactSyncPermissionsPage();
    }

    public /* synthetic */ void lambda$showResultsDialog$5$ContactListFragment(DialogInterface dialogInterface, int i) {
        dismissCurrentDialog();
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
        if ((i >= 0 || i2 >= 0) && this.capabilitiesManager.isSharingEnabled()) {
            this.mImageUri = intent.getData();
            this.mImageUploader.uploadImage(this.mImageUri);
        }
    }

    @Override // androidx.fragment.app.ListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        char c;
        CommsDaggerWrapper.getComponent().inject(this);
        String str = null;
        if (this.capabilitiesManager.isThemedUIEnabled()) {
            this.contactListView = layoutInflater.inflate(R.layout.fiesta_contacts_list, viewGroup, false);
            this.mHeaderView = layoutInflater.inflate(R.layout.fiesta_contact_list_header, (ViewGroup) null);
            this.mFooterView = layoutInflater.inflate(R.layout.fiesta_contact_list_footer, (ViewGroup) null);
        } else {
            this.contactListView = layoutInflater.inflate(R.layout.activity_contacts_list, viewGroup, false);
            this.mHeaderView = layoutInflater.inflate(R.layout.contact_list_header, (ViewGroup) null);
            this.mFooterView = layoutInflater.inflate(R.layout.contact_list_footer, (ViewGroup) null);
        }
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) this.contactListView.findViewById(R.id.swipeToRefresh);
        this.mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$uViaiNFSLFNJ9N-6xSKQoQVFRW0
            @Override // androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
            public final void onRefresh() {
                ContactListFragment.this.downloadContactsFromServer();
            }
        });
        mFragmentToBeLaunched = getArguments() != null ? getArguments().getString(Constants.CONTACT_LIST_LAUNCH_FRAGMENT, Constants.FRAGMENT_CONTACT_CARD) : Constants.FRAGMENT_CONTACT_CARD;
        String str2 = mFragmentToBeLaunched;
        int hashCode = str2.hashCode();
        if (hashCode == 91484943) {
            if (str2.equals(Constants.FRAGMENT_START_IMAGE_SHARE)) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode == 1069467150) {
            if (str2.equals(Constants.FRAGMENT_START_NEW_CONVERSATION)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 1097650749) {
            if (hashCode == 1209030014 && str2.equals(Constants.FRAGMENT_CONTACT_CARD)) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str2.equals(Constants.FRAGMENT_CALL_BOTTOM_SHEET)) {
                c = 2;
            }
            c = 65535;
        }
        if (c == 0) {
            this.mFragmentTitle = getResources().getString(R.string.select_contact);
        } else if (c == 1) {
            this.mFragmentTitle = getResources().getString(R.string.message_a_contact);
        } else if (c != 2) {
            this.mFragmentTitle = getResources().getString(R.string.select_contact);
        } else {
            this.mFragmentTitle = getResources().getString(R.string.start_call);
        }
        setupHeader();
        setupEducationalText();
        if (this.commsIdentityManager.isCommsIdPopulated("ContactListFragment.onCreateView", false) && isProvisionedForContactList()) {
            configureFragmentRequirements();
            initViews();
            if (bundle != null) {
                str = bundle.getString(CONTACT_CURRENT_OPERATION);
            }
            restoreContactOperation(str);
        }
        Utils.configureAccessibilityFocus(getActivity(), this.mHeaderView, this.mFragmentTitle);
        setupSubscriber();
        Utils.getLowStorageObservable(getContext()).subscribe((Subscriber<? super Boolean>) this.lowStorageSubscriber);
        setUpImageUploader();
        return this.contactListView;
    }

    @Override // androidx.fragment.app.ListFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        SwipeRefreshLayout swipeRefreshLayout = this.mSwipeRefreshLayout;
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
            this.mSwipeRefreshLayout.destroyDrawingCache();
            this.mSwipeRefreshLayout.clearAnimation();
        }
        CursorAdapter cursorAdapter = this.mContactsAdapter;
        if (cursorAdapter != null && this.isDataSetObserverRegistered) {
            this.isDataSetObserverRegistered = false;
            cursorAdapter.unregisterDataSetObserver(this.mContactsChangedObserver);
        }
        this.lowStorageSubscriber.unsubscribe();
        unregisterServiceReceiver();
        dismissCurrentDialog();
    }

    @Override // androidx.fragment.app.ListFragment
    public void onListItemClick(ListView listView, View view, int i, long j) {
        int i2;
        super.onListItemClick(this.mListView, view, i, j);
        if (i <= this.mContactsAdapter.getCount() && i - 1 >= 0) {
            ContactEntry contactEntryFromCursor = ContactsProviderUtils.getContactEntryFromCursor((Cursor) this.mContactsAdapter.getItem(i2));
            onContactListItemClicked(contactEntryFromCursor, ContactsProviderUtils.getCommsIdFromServerContactId(getContext(), contactEntryFromCursor.getId()), false);
        }
    }

    @Override // com.facebook.react.bridge.ActivityEventListener
    public void onNewIntent(Intent intent) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        unregisterReactNativeSyncReceiver();
        this.provisioningManager.unregisterStatusReceiver(this.provisioningStatusReceiver);
        Utils.hideKeyboard(getActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (!PermissionsHelper.checkGrantedPermissions(iArr)) {
            LOG.i(" Permission denied ");
        } else if (i != 0) {
            CommsLogger commsLogger = LOG;
            commsLogger.e(" Unknown permission code granted " + i);
        } else {
            setupHeader();
            setupFooter();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        registerReactNativeSyncReceiver();
        this.provisioningManager.registerStatusReceiver(this.provisioningStatusReceiver);
        this.provisioningManager.checkStatus();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.mContactOperationReceiver != null) {
            bundle.putString(CONTACT_CURRENT_OPERATION, this.mOperation);
        }
    }

    @Override // androidx.fragment.app.ListFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (Constants.REACT_NATIVE_CONTACTS_IMPORT_STARTED.equals(Utils.getStringPreferenceFromSharedPrefs(getContext(), Constants.REACT_NATIVE_CONTACTS_LAST_SYNC_STATUS, null))) {
            showReactNativeSyncLoader();
        }
        if (Utils.getBooleanPreferenceFromSharedPrefs(getContext(), Constants.SHOULD_SUPPORT_CONTACTS_ON_DEVICES, true)) {
            checkContactsPermissionsAndShowDialog();
        }
    }

    @VisibleForTesting
    void setUpImageUploader() {
        if (this.capabilitiesManager.isSharingEnabled()) {
            this.mImageUploader = CommsDaggerWrapper.getComponent().getFileTransmitter();
            this.mImageUploader.initialize().subscribe($$Lambda$ContactListFragment$_UZrUKFddl_mvd10JdgAVipYgw.INSTANCE, $$Lambda$ContactListFragment$Wd6eHDWn961RDUIB_ZqFNxDGnps.INSTANCE);
        }
    }

    void setupEducationalText() {
        CommsTextView commsTextView = (CommsTextView) this.mHeaderView.findViewById(R.id.educationalText);
        if (commsTextView != null) {
            String educationalTextString = getEducationalTextString();
            commsTextView.setText(educationalTextString);
            commsTextView.setVisibility(StringUtils.isEmpty(educationalTextString) ? 8 : 0);
        }
    }

    @VisibleForTesting
    void showAddContactIngress(View view) {
        if (!this.capabilitiesManager.isContactsManagementEnabled()) {
            view.setVisibility(8);
        } else if (isSyncPreferenceEnabled() && isContactPermissionsGranted()) {
            view.setVisibility(8);
        } else {
            view.setVisibility(0);
            view.setClickable(true);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$uHZbrajYajQrSSazsZwbY4k_bOc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ContactListFragment.this.lambda$showAddContactIngress$10$ContactListFragment(view2);
                }
            });
            MetricsHelper.recordCounterMetricOperational(MetricKeys.CONTACT_LIST_ADD_BUTTON_SHOWN, 1.0d);
        }
    }

    @VisibleForTesting
    void showImportContactIngress() {
        LinearLayout linearLayout = (LinearLayout) this.mFooterView.findViewById(R.id.footer_contact_sync);
        if (!this.capabilitiesManager.isContactsManagementEnabled()) {
            linearLayout.setVisibility(8);
        } else if (isSyncPreferenceEnabled() && isContactPermissionsGranted()) {
            linearLayout.setVisibility(8);
        } else {
            linearLayout.setVisibility(0);
            CommsTextView commsTextView = (CommsTextView) this.mFooterView.findViewById(R.id.footer_contact_sync_link);
            commsTextView.setClickable(true);
            commsTextView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactListFragment$CNZzndSBfEVcBRiz9DsuRxwwcLA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ContactListFragment.this.lambda$showImportContactIngress$11$ContactListFragment(view);
                }
            });
            MetricsHelper.recordCounterMetricOperational(MetricKeys.CONTACT_LIST_IMPORT_BUTTON_SHOWN, 1.0d);
        }
    }

    @VisibleForTesting
    void switchToAddContacts() {
        if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_LIST, AlertSource.newClassSource(ContactListFragment.class.getName()))) {
            return;
        }
        Bundle bundle = new Bundle();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACT_LIST_ADD_BUTTON_CLICK);
        this.applicationManager.navigateToView(CommsView.EditContact, bundle, true);
    }

    @VisibleForTesting
    void switchToContactCard(ContactEntry contactEntry) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.CONTACT_ENTRY_KEY, contactEntry);
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACT_LIST_CONTACT_CARD_CLICK);
        this.applicationManager.navigateToView(CommsView.ReactNativeContactCard, bundle, true);
    }

    @VisibleForTesting
    void switchToManageContacts() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACT_LIST_MANAGE_BUTTON_CLICK);
        this.applicationManager.navigateToView(CommsView.ReactNativeManageContacts, new Bundle(), true);
    }

    @SuppressLint({"ValidFragment"})
    public ContactListFragment(View view, View view2) {
        this.mHeaderView = view;
        this.mFooterView = view2;
    }
}
