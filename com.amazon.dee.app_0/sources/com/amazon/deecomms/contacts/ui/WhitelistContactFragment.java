package com.amazon.deecomms.contacts.ui;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.ListFragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.font.FontResolver;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.ContactSourceType;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.nativemodules.CommsEventEmitterBridge;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class WhitelistContactFragment extends ListFragment {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, WhitelistContactFragment.class);
    @Inject
    Context appContext;
    @Inject
    ApplicationManager applicationManager;
    @Inject
    CapabilitiesManager capabilitiesManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    private View contactListView;
    private String mChildCommsId;
    private String mChildContactId;
    private ListView mListView;
    private Menu mMenu;
    private SearchView mSearchView;
    private CursorAdapter mWhiteListContactAdapter;
    @Inject
    ThemingUpdateUtil themingUpdateUtil;
    private Set<String> contactIdsToBeWhitelisted = new HashSet();
    private final int LOADER_ID = 0;

    /* loaded from: classes12.dex */
    public class ContactLoaderCallbacks implements LoaderManager.LoaderCallbacks<Cursor> {
        private final List<String> referenceContactIds;

        public ContactLoaderCallbacks(@NonNull List<String> list) {
            this.referenceContactIds = list;
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            StringBuilder sb = new StringBuilder();
            ArrayList arrayList = new ArrayList();
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("%");
            outline1.append(WhitelistContactFragment.this.mSearchView.getQuery().toString().trim().replaceAll("\\s", ""));
            outline1.append("%");
            String sb2 = outline1.toString();
            ArrayList arrayList2 = new ArrayList();
            String contactsSortOrder = ContactsProviderUtils.getContactsSortOrder(WhitelistContactFragment.this.getContext());
            StringBuilder outline2 = GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(sb, "commsId", " NOT IN (?) ", " AND ((", "IFNULL("), "firstName", ",", "''", ")"), " || ", "IFNULL(", "lastName", ","), "''", ") LIKE (?) ) OR (", "firstName", " IS NULL AND "), "lastName", " IS NULL AND ", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, " LIKE (?) ) OR ("), "firstName", " IS NULL AND ", "lastName", " IS NULL AND ");
            outline2.append(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME);
            outline2.append(" IS NULL AND ");
            outline2.append("company");
            outline2.append(" LIKE (?) ))");
            arrayList2.add(sb2);
            arrayList2.add(sb2);
            arrayList2.add(sb2);
            StringBuilder outline22 = GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(GeneratedOutlineSupport.outline2(sb, " AND ((", "commsId", " IS NOT NULL AND LENGTH(", "commsId"), ") > 0", " AND ", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED, " = 1)"), " OR (", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED, " = 0)", " )"), " AND ", "ownerCommsId", " IN (?, ?)", " AND ( ");
            outline22.append(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP);
            outline22.append(" IS NULL OR ");
            outline22.append(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP);
            outline22.append(" NOT IN (?))");
            arrayList2.add(WhitelistContactFragment.this.commsIdentityManager.getCommsId("ContactLoaderCallbacks", false));
            arrayList2.add(WhitelistContactFragment.this.commsIdentityManager.getHomeGroupId("ContactLoaderCallbacks", false));
            arrayList2.add(WhitelistContactFragment.this.commsIdentityManager.getHomeGroupId("ContactLoaderCallbacks", false));
            if (this.referenceContactIds.size() > 0) {
                StringBuilder sb3 = new StringBuilder();
                for (String str : this.referenceContactIds) {
                    sb3.append("?, ");
                    arrayList2.add(str);
                }
                String substring = sb3.substring(0, sb3.length() - 2);
                StringBuilder outline23 = GeneratedOutlineSupport.outline2(sb, " AND ", ContactProviderContract.ContactDatabaseEntry.TABLE_NAME_PREFIX, "serverContactId", " NOT IN (");
                outline23.append(substring);
                outline23.append(")");
            }
            GeneratedOutlineSupport1.outline181(sb, " AND ", ContactProviderContract.ContactDatabaseEntry.TABLE_NAME_PREFIX, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_CHILD, " = 0 ");
            GeneratedOutlineSupport1.outline181(sb, " AND ", ContactProviderContract.PhoneNumberEntry.COLUMN_IS_HOME_GROUP, "= 0", " AND ");
            sb.append(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_CONTACT_SOURCE_TYPE);
            sb.append(" IN ");
            sb.append("(");
            StringBuilder outline12 = GeneratedOutlineSupport.outline1(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline12.append(ContactSourceType.AutoAddedContact.getName());
            outline12.append("\", ");
            sb.append(outline12.toString());
            StringBuilder outline13 = GeneratedOutlineSupport.outline1(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline13.append(ContactSourceType.BulkImportedContact.getName());
            outline13.append("\", ");
            sb.append(outline13.toString());
            StringBuilder outline14 = GeneratedOutlineSupport.outline1(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline14.append(ContactSourceType.ManuallyAddedContact.getName());
            outline14.append("\", ");
            sb.append(outline14.toString());
            StringBuilder outline15 = GeneratedOutlineSupport.outline1(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline15.append(ContactSourceType.SyncedContact.getName());
            outline15.append("\", ");
            sb.append(outline15.toString());
            StringBuilder outline16 = GeneratedOutlineSupport.outline1(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline16.append(ContactSourceType.VoiceAddedContact.getName());
            outline16.append("\", ");
            sb.append(outline16.toString());
            StringBuilder outline17 = GeneratedOutlineSupport.outline1(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            outline17.append(ContactSourceType.WhitelistedContact.getName());
            outline17.append(EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PLAYER_INFO_PAYLOAD_DESERIALIZED);
            sb.append(outline17.toString());
            sb.append(")");
            GeneratedOutlineSupport1.outline180(sb, " GROUP BY ", ContactProviderContract.ContactDatabaseEntry.TABLE_NAME_PREFIX, "serverContactId");
            arrayList.add(WhitelistContactFragment.this.commsIdentityManager.getCommsId("ContactLoaderCallbacks", false));
            arrayList.addAll(arrayList2);
            String[] strArr = new String[arrayList.size()];
            arrayList.toArray(strArr);
            return new CursorLoader(WhitelistContactFragment.this.getActivity().getApplicationContext(), ContactProviderContract.getContactJoinPhoneNumberUri(WhitelistContactFragment.this.getContext()), null, sb.toString(), strArr, contactsSortOrder);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<Cursor> loader) {
            WhitelistContactFragment.this.mWhiteListContactAdapter.changeCursor(null);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
            MenuItem findItem;
            if (cursor == null) {
                return;
            }
            WhitelistContactFragment.this.mWhiteListContactAdapter.changeCursor(cursor);
            cursor.setNotificationUri(WhitelistContactFragment.this.getActivity().getContentResolver(), ContactProviderContract.getContactsUri(WhitelistContactFragment.this.getContext()));
            WhitelistContactFragment.this.updateSaveButton();
            if (WhitelistContactFragment.this.mMenu == null || (findItem = WhitelistContactFragment.this.mMenu.findItem(R.id.whitelist_contact_list)) == null) {
                return;
            }
            findItem.setVisible(cursor.getCount() > 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class SearchViewFilter implements SearchView.OnQueryTextListener {
        private List<String> referenceContactIds;

        public SearchViewFilter(List<String> list) {
            this.referenceContactIds = list;
        }

        @Override // android.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextChange(String str) {
            WhitelistContactFragment.this.getLoaderManager().restartLoader(0, null, new ContactLoaderCallbacks(this.referenceContactIds));
            WhitelistContactFragment.this.mWhiteListContactAdapter.notifyDataSetChanged();
            return true;
        }

        @Override // android.widget.SearchView.OnQueryTextListener
        public boolean onQueryTextSubmit(String str) {
            return false;
        }
    }

    private void configureFragmentRequirements() {
        this.applicationManager.configurePageForFragment(new FragmentRequirements(this).withTitle(getString(R.string.add_contacts_title)).withMenu(R.menu.whitelist_contact_menu, new Toolbar.OnMenuItemClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$WhitelistContactFragment$pHeAKlJYcoexl0fiG16WGgzHJn8
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return WhitelistContactFragment.this.lambda$configureFragmentRequirements$0$WhitelistContactFragment(menuItem);
            }
        }, new FragmentRequirements.MenuInflatedCallback() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$WhitelistContactFragment$Swiegq9S1WgVPc1rPtUMXKnv-kM
            @Override // com.amazon.deecomms.api.navigation.FragmentRequirements.MenuInflatedCallback
            public final void onInflated(Menu menu) {
                WhitelistContactFragment.this.lambda$configureFragmentRequirements$1$WhitelistContactFragment(menu);
            }
        }));
    }

    private void initViews() {
        this.mListView = (ListView) this.contactListView.findViewById(16908298);
        this.mWhiteListContactAdapter = new ContactListAdapter(getActivity().getApplicationContext(), true, this.capabilitiesManager.isThemedUIEnabled());
        this.mListView.setAdapter((ListAdapter) this.mWhiteListContactAdapter);
        this.mSearchView = (SearchView) this.contactListView.findViewById(R.id.whitelist_contact_search_view);
        if (isMosaicThemingEnabled()) {
            this.themingUpdateUtil.applyBackgroundColorToView(this.mSearchView, getActivity().getApplicationContext(), R.attr.mosaicNeutral50);
        }
        new AsyncTask<Void, Void, List<String>>() { // from class: com.amazon.deecomms.contacts.ui.WhitelistContactFragment.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public List<String> doInBackground(Void... voidArr) {
                if (WhitelistContactFragment.this.mChildCommsId == null) {
                    return null;
                }
                return ContactsProviderUtils.getReferenceContactIdsFromChild(WhitelistContactFragment.this.mChildCommsId, WhitelistContactFragment.this.appContext);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(List<String> list) {
                if (list == null) {
                    WhitelistContactFragment.LOG.e("Unable to retrieve child commsId, can't load whitelist contacts list.");
                    return;
                }
                WhitelistContactFragment.this.setUpSearchView(list);
                WhitelistContactFragment.this.getLoaderManager().initLoader(0, null, new ContactLoaderCallbacks(list));
            }
        }.execute(new Void[0]);
    }

    private boolean isMosaicThemingEnabled() {
        return this.capabilitiesManager.isThemedUIEnabled() && this.themingUpdateUtil.isMosaicThemingEnabled();
    }

    private void onSaveClicked() {
        if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_WHITELIST_CONTACT, AlertSource.newClassSource(WhitelistContactFragment.class.getName()))) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.mChildContactId);
        for (String str : this.contactIdsToBeWhitelisted) {
            arrayList.add(str);
        }
        CommsEventEmitterBridge.sendStartWhitelistContactsNotification(arrayList);
        this.applicationManager.navigateBackward();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUpSearchView(List<String> list) {
        this.mSearchView.setOnQueryTextListener(new SearchViewFilter(list));
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
            if (isMosaicThemingEnabled()) {
                this.themingUpdateUtil.applyTintColorToImageView(imageView, getActivity().getApplicationContext(), R.attr.mosaicBackground);
                editText.setTextColor(ThemeUtil.getColorFromAttribute(getActivity().getApplicationContext(), R.attr.mosaicNeutral10));
                editText.setHintTextColor(ThemeUtil.getColorFromAttribute(getActivity().getApplicationContext(), R.attr.mosaicNeutral10));
                return;
            }
            editText.setTextColor(ContextCompat.getColor(getContext(), R.color.fiesta_text_primary));
            editText.setHintTextColor(ContextCompat.getColor(getContext(), R.color.fiesta_text_primary));
            return;
        }
        editText.setTextColor(ContextCompat.getColor(getContext(), R.color.ultra_dark));
        editText.setHintTextColor(ContextCompat.getColor(getContext(), R.color.lightGray));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSaveButton() {
        Menu menu = this.mMenu;
        if (menu != null) {
            MenuItem findItem = menu.findItem(R.id.whitelist_contact_list);
            if (findItem != null) {
                findItem.setEnabled(this.contactIdsToBeWhitelisted.size() > 0);
            } else {
                LOG.e("updateSaveButton: menuItem is null");
            }
        }
    }

    public /* synthetic */ boolean lambda$configureFragmentRequirements$0$WhitelistContactFragment(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.whitelist_contact_list) {
            onSaveClicked();
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$configureFragmentRequirements$1$WhitelistContactFragment(Menu menu) {
        this.mMenu = menu;
    }

    @Override // androidx.fragment.app.ListFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        GeneratedOutlineSupport1.outline184(GeneratedOutlineSupport.outline1("[WhitelistContactFragment] is mosaic weblab enabled:  "), isMosaicThemingEnabled(), LOG);
        if (isMosaicThemingEnabled()) {
            ThemeUtil.setTheme(getActivity().getApplicationContext());
        }
        if (this.capabilitiesManager.isThemedUIEnabled()) {
            this.contactListView = layoutInflater.inflate(R.layout.fiesta_whitelist_contact_list, viewGroup, false);
        } else {
            this.contactListView = layoutInflater.inflate(R.layout.whitelist_contact_list, viewGroup, false);
        }
        if (isMosaicThemingEnabled()) {
            this.themingUpdateUtil.applyBackgroundColorToView(this.contactListView, getActivity().getApplicationContext(), R.attr.mosaicBackground);
        }
        this.mChildCommsId = getArguments().getString(Constants.CHILD_COMMS_ID);
        this.mChildContactId = getArguments().getString(Constants.CHILD_CONTACT_ID);
        configureFragmentRequirements();
        initViews();
        return this.contactListView;
    }

    @Override // androidx.fragment.app.ListFragment
    public void onListItemClick(ListView listView, View view, int i, long j) {
        super.onListItemClick(this.mListView, view, i, j);
        ImageView imageView = (ImageView) view.findViewById(R.id.contact_selected_checkbox);
        ContactEntry contactEntryFromCursor = ContactsProviderUtils.getContactEntryFromCursor((Cursor) this.mWhiteListContactAdapter.getItem(i));
        if (contactEntryFromCursor != null) {
            String id = contactEntryFromCursor.getId();
            if (this.contactIdsToBeWhitelisted.remove(id)) {
                imageView.setActivated(false);
            } else {
                this.contactIdsToBeWhitelisted.add(id);
                imageView.setActivated(true);
            }
        }
        updateSaveButton();
    }
}
