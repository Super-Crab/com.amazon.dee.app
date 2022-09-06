package com.amazon.deecomms.contacts.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.metrics.CounterMetric;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.ui.DividerItemDecoration;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.model.Contacts;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.contacts.ui.ContactBlockFragment;
import com.amazon.deecomms.contacts.util.ContactDownloader;
import com.amazon.deecomms.contacts.util.ContactStatusManager;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ContactBlockFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<Contact>> {
    private static final int CONTACTS_BLOCK_LIST_LOADER = 1;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactBlockFragment.class);
    @Inject
    ApplicationManager applicationManager;
    @Inject
    CommsIdentityManager commsIdentityManager;
    private BlockContactsAdapter contactsAdapter;
    private Dialog mCurrentDialog;
    private OnContactSelectedListener onContactSelectedListener;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.amazon.deecomms.contacts.ui.ContactBlockFragment$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    public class AnonymousClass1 extends AsyncTask<Void, Void, Boolean> {
        final /* synthetic */ boolean val$block;
        final /* synthetic */ String val$contactCommsId;
        final /* synthetic */ String val$contactName;

        AnonymousClass1(String str, boolean z, String str2) {
            this.val$contactCommsId = str;
            this.val$block = z;
            this.val$contactName = str2;
        }

        public /* synthetic */ void lambda$onPostExecute$0$ContactBlockFragment$1(DialogInterface dialogInterface, int i) {
            ContactBlockFragment.this.dismissCurrentDialog();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Void... voidArr) {
            return Boolean.valueOf(new ContactStatusManager().setBlockStatus(this.val$contactCommsId, this.val$block));
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            int i;
            String format;
            CounterMetric generateOperational;
            if (ContactBlockFragment.this.hasActivityFinished()) {
                ContactBlockFragment.LOG.w("Missing activity");
                return;
            }
            super.onPostExecute((AnonymousClass1) bool);
            if (!bool.booleanValue()) {
                MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, MetricKeys.SCREEN_NAME_CONTACT_LIST, AlertSource.newClassSource(ContactBlockFragment.this));
                i = R.string.contact_blocking_failure_title;
                format = ContactBlockFragment.this.getString(R.string.contact_blocking_failure_message);
                generateOperational = CounterMetric.generateOperational(MetricKeys.CONTACTS_BLOCK_FAILURE);
            } else {
                ContactBlockFragment.this.getLoaderManager().restartLoader(1, null, ContactBlockFragment.this);
                i = this.val$block ? R.string.contact_blocking_feedback_title_blocked : R.string.contact_blocking_feedback_title_unblocked;
                format = String.format(ContactBlockFragment.this.getString(this.val$block ? R.string.contact_blocking_feedback_message_blocked : R.string.contact_blocking_feedback_message_unblocked), this.val$contactName);
                generateOperational = CounterMetric.generateOperational(MetricKeys.CONTACTS_BLOCK_SUCCESS);
            }
            generateOperational.getMetadata().put("source", MetricKeys.SCREEN_NAME_CONTACT_LIST);
            MetricsHelper.recordSingleOccurrence(generateOperational);
            ContactBlockFragment.access$200(ContactBlockFragment.this, new AlertDialog.Builder(ContactBlockFragment.this.getContext()).setTitle(i).setMessage(format).setPositiveButton(R.string.dialog_ok_button, new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactBlockFragment$1$ZfTUGwoRgZh2pBaXBapAqBSDDdA
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    ContactBlockFragment.AnonymousClass1.this.lambda$onPostExecute$0$ContactBlockFragment$1(dialogInterface, i2);
                }
            }).create());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum AdapterState {
        Loading { // from class: com.amazon.deecomms.contacts.ui.ContactBlockFragment.AdapterState.1
            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.AdapterState
            public int getItemCount(@NonNull BlockContactsAdapter blockContactsAdapter) {
                return 1;
            }

            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.AdapterState
            @NonNull
            public ViewType getItemViewType(@NonNull BlockContactsAdapter blockContactsAdapter, int i) {
                return ViewType.Header;
            }
        },
        Empty { // from class: com.amazon.deecomms.contacts.ui.ContactBlockFragment.AdapterState.2
            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.AdapterState
            public int getItemCount(@NonNull BlockContactsAdapter blockContactsAdapter) {
                return 1;
            }

            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.AdapterState
            @NonNull
            public ViewType getItemViewType(@NonNull BlockContactsAdapter blockContactsAdapter, int i) {
                return ViewType.Empty;
            }
        },
        Loaded { // from class: com.amazon.deecomms.contacts.ui.ContactBlockFragment.AdapterState.3
            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.AdapterState
            public Object getItem(@NonNull BlockContactsAdapter blockContactsAdapter, int i) {
                return blockContactsAdapter.contacts.get(i);
            }

            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.AdapterState
            public int getItemCount(@NonNull BlockContactsAdapter blockContactsAdapter) {
                return blockContactsAdapter.contacts.size();
            }

            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.AdapterState
            @NonNull
            public ViewType getItemViewType(@NonNull BlockContactsAdapter blockContactsAdapter, int i) {
                return ViewType.Contact;
            }
        };

        public static AdapterState fromContacts(@Nullable List<Contact> list) {
            if (list == null) {
                return Loading;
            }
            if (list.size() == 0) {
                return Empty;
            }
            return Loaded;
        }

        public Object getItem(@NonNull BlockContactsAdapter blockContactsAdapter, int i) {
            return null;
        }

        public abstract int getItemCount(@NonNull BlockContactsAdapter blockContactsAdapter);

        @NonNull
        public abstract ViewType getItemViewType(@NonNull BlockContactsAdapter blockContactsAdapter, int i);

        /* synthetic */ AdapterState(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class BlockContactsAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<Contact> contacts = null;
        @NonNull
        private AdapterState state = AdapterState.Loading;

        public BlockContactsAdapter() {
        }

        public boolean canDrawDivider(int i) {
            if (i < 0) {
                return false;
            }
            return this.state.getItemViewType(this, i).canDrawDivider();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.state.getItemCount(this);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            return this.state.getItemViewType(this, i).ordinal();
        }

        public void setContacts(@Nullable List<Contact> list) {
            this.contacts = list;
            this.state = AdapterState.fromContacts(list);
            notifyDataSetChanged();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.setItem(this.state.getItem(this, i));
            final ContactBlockFragment contactBlockFragment = ContactBlockFragment.this;
            viewHolder.setOnContactSelectedListener(new OnContactSelectedListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactBlockFragment$BlockContactsAdapter$AtOnpx3qXxQFPg4qyBaAC55QTA4
                @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.OnContactSelectedListener
                public final void onContactSelected(Contact contact) {
                    ContactBlockFragment.this.onContactClicked(contact);
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        /* renamed from: onCreateViewHolder  reason: collision with other method in class */
        public ViewHolder mo7499onCreateViewHolder(ViewGroup viewGroup, int i) {
            return ViewType.fromValue(i).createViewHolder(viewGroup);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewRecycled(ViewHolder viewHolder) {
            super.onViewRecycled((BlockContactsAdapter) viewHolder);
            viewHolder.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class ContactViewHolder extends ViewHolder {
        private final TextView actionView;
        private Contact contact;
        private final TextView nameView;

        ContactViewHolder(@NonNull ViewGroup viewGroup) {
            super(viewGroup, R.layout.contact_blocking_list_item);
            this.nameView = (TextView) this.itemView.findViewById(R.id.contact_name);
            this.actionView = (TextView) this.itemView.findViewById(R.id.contact_block_action);
        }

        private void setContact(Contact contact) {
            this.contact = contact;
            if (contact == null) {
                ContactBlockFragment.LOG.w("contact is null. Not rendering the view");
                return;
            }
            Context context = this.itemView.getContext();
            FullContactName fullContactName = new FullContactName(contact.getContactName(), contact.getCompany());
            TextView textView = this.nameView;
            if (textView != null) {
                textView.setText(ContactUtils.getFullName(fullContactName));
                this.nameView.setSingleLine(true);
                this.nameView.setTextColor(context.getResources().getColor(R.color.contact_block_list_contact_name_blocked));
            }
            if (this.actionView != null) {
                this.actionView.setTextColor(context.getResources().getColor(contact.isBlocked() ? R.color.contact_block_list_action : R.color.thread_audio_messaging_cancel_red));
            }
            if (this.actionView == null) {
                return;
            }
            if (contact.isBlocked()) {
                this.actionView.setText(R.string.contact_blocking_row_unblock);
            } else {
                this.actionView.setText(R.string.contact_blocking_row_block);
            }
        }

        public /* synthetic */ void lambda$setOnContactSelectedListener$0$ContactBlockFragment$ContactViewHolder(OnContactSelectedListener onContactSelectedListener, View view) {
            Contact contact;
            if (onContactSelectedListener == null || (contact = this.contact) == null) {
                return;
            }
            onContactSelectedListener.onContactSelected(contact);
        }

        @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.ViewHolder
        public void recycle() {
            this.itemView.setOnClickListener(null);
            this.contact = null;
        }

        @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.ViewHolder
        public void setItem(Object obj) {
            if (obj instanceof Contact) {
                setContact((Contact) obj);
            }
        }

        @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.ViewHolder
        public void setOnContactSelectedListener(final OnContactSelectedListener onContactSelectedListener) {
            this.actionView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactBlockFragment$ContactViewHolder$NctoWQ8GEIfa77fFfaFajmi6Vik
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ContactBlockFragment.ContactViewHolder.this.lambda$setOnContactSelectedListener$0$ContactBlockFragment$ContactViewHolder(onContactSelectedListener, view);
                }
            });
        }
    }

    /* loaded from: classes12.dex */
    private static class EmptyViewHolder extends ViewHolder {
        EmptyViewHolder(@NonNull ViewGroup viewGroup) {
            super(viewGroup, R.layout.contact_blocking_list_empty);
        }
    }

    /* loaded from: classes12.dex */
    private static class HeaderViewHolder extends ViewHolder {
        HeaderViewHolder(@NonNull ViewGroup viewGroup) {
            super(viewGroup, R.layout.contact_blocking_list_header);
        }
    }

    /* loaded from: classes12.dex */
    public interface OnContactSelectedListener {
        void onContactSelected(@NonNull Contact contact);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static abstract class ViewHolder extends RecyclerView.ViewHolder {
        ViewHolder(ViewGroup viewGroup, int i) {
            super(LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false));
        }

        public void recycle() {
        }

        public void setItem(Object obj) {
        }

        public void setOnContactSelectedListener(OnContactSelectedListener onContactSelectedListener) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum ViewType {
        Header { // from class: com.amazon.deecomms.contacts.ui.ContactBlockFragment.ViewType.1
            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.ViewType
            @NonNull
            public ViewHolder createViewHolder(@NonNull ViewGroup viewGroup) {
                return new HeaderViewHolder(viewGroup);
            }
        },
        Contact { // from class: com.amazon.deecomms.contacts.ui.ContactBlockFragment.ViewType.2
            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.ViewType
            public boolean canDrawDivider() {
                return true;
            }

            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.ViewType
            @NonNull
            public ViewHolder createViewHolder(@NonNull ViewGroup viewGroup) {
                return new ContactViewHolder(viewGroup);
            }
        },
        Empty { // from class: com.amazon.deecomms.contacts.ui.ContactBlockFragment.ViewType.3
            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.ViewType
            @NonNull
            public ViewHolder createViewHolder(@NonNull ViewGroup viewGroup) {
                return new EmptyViewHolder(viewGroup);
            }
        };

        public static ViewType fromValue(int i) {
            if (i >= 0 && i < values().length) {
                return values()[i];
            }
            return Header;
        }

        public boolean canDrawDivider() {
            return false;
        }

        @NonNull
        public abstract ViewHolder createViewHolder(@NonNull ViewGroup viewGroup);

        /* synthetic */ ViewType(AnonymousClass1 anonymousClass1) {
        }
    }

    static /* synthetic */ void access$200(ContactBlockFragment contactBlockFragment, Dialog dialog) {
        contactBlockFragment.dismissCurrentDialog();
        dialog.show();
        contactBlockFragment.mCurrentDialog = dialog;
    }

    private void blockContact(@NonNull final String str, @NonNull final String str2, boolean z) {
        String format;
        int i;
        DialogInterface.OnClickListener onClickListener;
        if (z) {
            format = String.format(getString(R.string.contact_blocking_message_block), str2);
            i = R.string.contact_blocking_action_block;
            onClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactBlockFragment$0xC1iE-vwK_eUjiLqLQ-6jOHEqY
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    ContactBlockFragment.this.lambda$blockContact$0$ContactBlockFragment(str, str2, dialogInterface, i2);
                }
            };
        } else {
            format = String.format(getString(R.string.contact_blocking_message_unblock), str2);
            i = R.string.contact_blocking_action_unblock;
            onClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactBlockFragment$dVEHtS78pKq3ekdNkPCEsAuuwvk
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    ContactBlockFragment.this.lambda$blockContact$1$ContactBlockFragment(str, str2, dialogInterface, i2);
                }
            };
        }
        AlertDialog create = new AlertDialog.Builder(getActivity(), R.style.AlexaContactBlockingDialogStyle).setCancelable(false).setMessage(format).setNegativeButton(R.string.cancel, $$Lambda$ContactBlockFragment$l2tpWoT6RU9aHu9fAxazbixx6g.INSTANCE).setPositiveButton(i, onClickListener).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactBlockFragment$X0TsW1eg8gDGi32ukHfl-D9LPgQ
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                ContactBlockFragment.this.lambda$blockContact$3$ContactBlockFragment(dialogInterface);
            }
        }).create();
        showDialog(create);
        setupBlockingAlertButton(create.getButton(-1));
        setupBlockingAlertButton(create.getButton(-2));
    }

    private void configureFragmentRequirements() {
        this.applicationManager.configurePageForFragment(new FragmentRequirements(this).withTitle(getString(R.string.contact_blocking_list_header_text)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissCurrentDialog() {
        Dialog dialog = this.mCurrentDialog;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.mCurrentDialog = null;
    }

    @NonNull
    private Set<String> fetchMyHomeGroupMembers(@Nullable String str) {
        Context context;
        HashSet newHashSet = Sets.newHashSet();
        if (!TextUtils.isEmpty(str) && (context = getContext()) != null) {
            Cursor fetchHomeGroupMembers = ContactsProviderUtils.fetchHomeGroupMembers(context, str);
            while (fetchHomeGroupMembers.moveToNext()) {
                try {
                    newHashSet.add(fetchHomeGroupMembers.getString(fetchHomeGroupMembers.getColumnIndex("commsId")));
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (fetchHomeGroupMembers != null) {
                            try {
                                fetchHomeGroupMembers.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            }
            fetchHomeGroupMembers.close();
            return newHashSet;
        }
        return newHashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public List<Contact> filterContacts(@Nullable Contacts contacts) {
        if (contacts != null && contacts.getContacts() != null) {
            String commsId = this.commsIdentityManager.getCommsId("ContactBlockFragment.filterContacts", false);
            String homeGroupId = this.commsIdentityManager.getHomeGroupId("ContactBlockFragment.filterContacts", false);
            Set<String> fetchMyHomeGroupMembers = fetchMyHomeGroupMembers(homeGroupId);
            LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
            for (Contact contact : contacts.getContacts()) {
                Set<String> commsIdsAsSet = getCommsIdsAsSet(contact.getCommsIds());
                if (!contact.isAlexaEnabled()) {
                    LOG.i("Ignoring contact (not Alexa enabled)");
                } else if (commsIdsAsSet.isEmpty()) {
                    LOG.i("Ignoring contact (no comms id)");
                } else if (commsIdsAsSet.contains(commsId)) {
                    LOG.i("Ignoring contact (ourself)");
                } else if (commsIdsAsSet.contains(homeGroupId)) {
                    LOG.i("Ignoring contact (our home group)");
                } else if (!Sets.intersection(commsIdsAsSet, fetchMyHomeGroupMembers).isEmpty()) {
                    LOG.i("Ignoring contact (member of our home group)");
                } else if (!Sets.intersection(commsIdsAsSet, newLinkedHashMap.keySet()).isEmpty()) {
                    LOG.i("Ignoring contact (duplicate)");
                } else {
                    LOG.v(String.format("Adding contact: isBlocked=%b", Boolean.valueOf(contact.isBlocked())));
                    newLinkedHashMap.put(commsIdsAsSet.iterator().next(), contact);
                }
            }
            return Lists.newArrayList(newLinkedHashMap.values());
        }
        LOG.w("Got null or empty contact list");
        return Lists.newArrayList();
    }

    @NonNull
    private Set<String> getCommsIdsAsSet(@Nullable List<String> list) {
        HashSet newHashSet = Sets.newHashSet();
        if (list != null) {
            newHashSet.addAll(list);
        }
        return newHashSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasActivityFinished() {
        return getActivity() == null || getActivity().isFinishing() || getContext() == null;
    }

    private void initViews(View view) {
        Context context = CommsDaggerWrapper.getComponent().getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        this.contactsAdapter = new BlockContactsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(CommsDaggerWrapper.getComponent().getContext()));
        recyclerView.setAdapter(this.contactsAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(context, R.drawable.contact_blocking_list_item_divider) { // from class: com.amazon.deecomms.contacts.ui.ContactBlockFragment.2
            @Override // com.amazon.deecomms.common.ui.DividerItemDecoration
            protected boolean canDrawDivider(int i) {
                return ContactBlockFragment.this.contactsAdapter.canDrawDivider(i);
            }
        });
        this.onContactSelectedListener = new OnContactSelectedListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactBlockFragment$nC1Vn3vFfUszVAbovBupejQUTV0
            @Override // com.amazon.deecomms.contacts.ui.ContactBlockFragment.OnContactSelectedListener
            public final void onContactSelected(Contact contact) {
                ContactBlockFragment.this.onContactSelected(contact);
            }
        };
        if (recyclerView.getAdapter() == null) {
            getLoaderManager().initLoader(1, null, this);
        } else {
            getLoaderManager().restartLoader(1, null, this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onContactClicked(@NonNull Contact contact) {
        if (isResumed() && !hasActivityFinished()) {
            OnContactSelectedListener onContactSelectedListener = this.onContactSelectedListener;
            if (onContactSelectedListener == null) {
                return;
            }
            onContactSelectedListener.onContactSelected(contact);
            return;
        }
        LOG.e("Invalid activity");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onContactSelected(@NonNull Contact contact) {
        if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_LIST, AlertSource.newClassSource(ContactBlockFragment.class.getName()))) {
            LOG.i("Offline. Cannot block or unblock contact");
            return;
        }
        String str = contact.getCommsIds().get(0);
        String fullName = ContactUtils.getFullName(contact.getContactName());
        if (contact.isBlocked()) {
            LOG.i("User selected a contact that is blocked... Unblocking");
            blockContact(str, fullName, false);
            return;
        }
        LOG.i("User selected a contact that is unblocked... Blocking");
        blockContact(str, fullName, true);
    }

    private void setContactBlockStatus(@NonNull String str, @NonNull String str2, boolean z) {
        new AnonymousClass1(str, z, str2).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private void setOnContactSelectedListener(OnContactSelectedListener onContactSelectedListener) {
        this.onContactSelectedListener = onContactSelectedListener;
    }

    private void setupBlockingAlertButton(Button button) {
        if (button == null) {
            return;
        }
        button.setAllCaps(true);
        button.setTextColor(getResources().getColor(R.color.alexaBlue));
    }

    private void showDialog(Dialog dialog) {
        dismissCurrentDialog();
        dialog.show();
        this.mCurrentDialog = dialog;
    }

    @Nullable
    Contacts fetchContacts() {
        HashMap newHashMap = Maps.newHashMap();
        newHashMap.put(Constants.ACMS_CONTACTS_QUERY_PREFERENCE_LEVEL, "None");
        newHashMap.put(Constants.ACMS_CONTACTS_QUERY_INCLUDE_BLOCK_STATUS, "true");
        newHashMap.put(ContactDownloader.ACMS_QUERY_PARAM_DEDUPE_MODE, ContactDownloader.ACMS_QUERY_PARAM_DEDUPE_MODE_REMOVE_CLOUD_ONLY_CONTACT_DUPLICATES);
        ContactDownloader contactDownloader = new ContactDownloader();
        contactDownloader.downloadContacts(newHashMap);
        LOG.i("getContactsDebugging: download contacts in fetchContacts in ContactBlockFragment");
        return contactDownloader.getContactsAndHomeGroups();
    }

    public /* synthetic */ void lambda$blockContact$0$ContactBlockFragment(String str, String str2, DialogInterface dialogInterface, int i) {
        LOG.i("User clicked block");
        setContactBlockStatus(str, str2, true);
    }

    public /* synthetic */ void lambda$blockContact$1$ContactBlockFragment(String str, String str2, DialogInterface dialogInterface, int i) {
        LOG.i("User clicked unblock");
        setContactBlockStatus(str, str2, false);
    }

    public /* synthetic */ void lambda$blockContact$3$ContactBlockFragment(DialogInterface dialogInterface) {
        dismissCurrentDialog();
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<List<Contact>> onCreateLoader(int i, Bundle bundle) {
        return new AsyncTaskLoader<List<Contact>>(getContext()) { // from class: com.amazon.deecomms.contacts.ui.ContactBlockFragment.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // androidx.loader.content.Loader
            public void onStartLoading() {
                super.onStartLoading();
                forceLoad();
            }

            @Override // androidx.loader.content.AsyncTaskLoader
            /* renamed from: loadInBackground  reason: collision with other method in class */
            public List<Contact> mo3665loadInBackground() {
                if (ContactBlockFragment.this.hasActivityFinished()) {
                    ContactBlockFragment.LOG.w("Loading data... activity has disappeared (fetch)");
                    return Lists.newArrayList();
                }
                Contacts fetchContacts = ContactBlockFragment.this.fetchContacts();
                if (fetchContacts == null || fetchContacts.getContacts() == null) {
                    ContactBlockFragment.LOG.i("Could not get contacts from service.");
                } else {
                    ContactBlockFragment.LOG.i(String.format("Got %d contacts from service.", Integer.valueOf(fetchContacts.getContacts().size())));
                }
                if (ContactBlockFragment.this.hasActivityFinished()) {
                    ContactBlockFragment.LOG.w("Loading data... activity has disappeared (fetch)");
                    return Lists.newArrayList();
                }
                List<Contact> filterContacts = ContactBlockFragment.this.filterContacts(fetchContacts);
                ContactBlockFragment.LOG.i(String.format("Displaying %d contacts", Integer.valueOf(filterContacts.size())));
                return filterContacts;
            }
        };
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        View inflate = layoutInflater.inflate(R.layout.contact_blocking_list, viewGroup, false);
        configureFragmentRequirements();
        initViews(inflate);
        return inflate;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<List<Contact>> loader) {
        this.contactsAdapter.setContacts(null);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Dialog dialog = this.mCurrentDialog;
        if (dialog != null) {
            dialog.hide();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        configureFragmentRequirements();
        this.applicationManager.loadingComplete(CommsView.BlockContactsList);
        Dialog dialog = this.mCurrentDialog;
        if (dialog != null) {
            dialog.show();
        }
    }

    public void updateContactsBlockStatusWithDB(@NonNull final List<Contact> list) {
        new AsyncTask<Void, Void, Void>() { // from class: com.amazon.deecomms.contacts.ui.ContactBlockFragment.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                Context context = CommsDaggerWrapper.getComponent().getContext();
                for (Contact contact : list) {
                    ContactsProviderUtils.updateContactBlockStatus(context, contact.getId(), contact.isBlocked());
                    if (!contact.isEverRefreshed()) {
                        contact.setEverRefreshed(true);
                        ContactsProviderUtils.updateIsContactEverRefreshed(context, contact.getId(), true);
                    }
                }
                return null;
            }
        }.execute(new Void[0]);
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoadFinished(Loader<List<Contact>> loader, List<Contact> list) {
        this.contactsAdapter.setContacts(list);
        updateContactsBlockStatusWithDB(list);
    }
}
