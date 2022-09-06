package com.amazon.deecomms.contacts.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
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
import com.amazon.deecomms.common.util.LanguageUtil;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.ContactForSync;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.ContactUploadInfo;
import com.amazon.deecomms.contacts.model.enums.SyncOperationType;
import com.amazon.deecomms.contacts.util.CloudContactsSynchronizer;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.util.ResultInfo;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Optional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class EditNicknameFragment extends Fragment {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, EditNicknameFragment.class);
    private static final String METRIC_SOURCE_NICKNAME = "nickname";
    @Inject
    Context appContext;
    @Inject
    ApplicationManager applicationManager;
    @Inject
    ArcusConfig arcusConfig;
    private String mCompany;
    private String mContactId;
    private ContactName mContactName;
    private Menu mMenu;
    private TextView mNicknameDescription;
    private TextView mNicknameErrorView;
    private EditText mNicknameView;
    private String mOwnerCommsId;
    private ArrayList<ContactPhoneNumber> mPhoneNumbersList;
    private String mRelationship;
    private int sContactNameMaxLength;

    /* renamed from: com.amazon.deecomms.contacts.ui.EditNicknameFragment$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$contacts$ui$EditNicknameFragment$FieldError = new int[FieldError.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$contacts$ui$EditNicknameFragment$FieldError[FieldError.INVALID_CHAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$contacts$ui$EditNicknameFragment$FieldError[FieldError.EXCEEDS_MAX_LENGTH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class EditContactTask extends AsyncTask<Void, Void, ResultInfo<Boolean, AlertSource>> {
        private final ContactName name;

        public EditContactTask(ContactName contactName) {
            this.name = contactName;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public ResultInfo<Boolean, AlertSource> doInBackground(Void... voidArr) {
            EditNicknameFragment.LOG.d("Editing contact name");
            if (this.name == null) {
                EditNicknameFragment.LOG.e("Edit contact failed as the name to be updated is null");
                return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditContactTask.class.getName()));
            }
            CloudContactsSynchronizer cloudContactsSynchronizer = new CloudContactsSynchronizer();
            ContactForSync contactForNameSync = EditNicknameFragment.this.getContactForNameSync(this.name);
            List<ContactUploadInfo.PhoneNumber> numbers = contactForNameSync.getContact().getNumbers();
            if (EditNicknameFragment.this.appContext == null && (numbers == null || numbers.size() == 0)) {
                return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditContactTask.class.getName()));
            }
            EditNicknameFragment editNicknameFragment = EditNicknameFragment.this;
            if (editNicknameFragment.appContext != null && !ContactsProviderUtils.isContactChild(editNicknameFragment.mContactId, EditNicknameFragment.this.appContext) && (numbers == null || numbers.size() == 0)) {
                EditNicknameFragment.LOG.e("Contact that is missing Phone Numbers can not be edited");
                HashMap hashMap = new HashMap();
                hashMap.put("errorSource", MetricKeys.VALUE_INVALID_CONTACT_UPDATE);
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACT_EDIT_AUTO_ADDED_FAILURE, hashMap);
                return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditContactTask.class.getName()));
            }
            HashMap outline133 = GeneratedOutlineSupport1.outline133("source", EditNicknameFragment.METRIC_SOURCE_NICKNAME);
            try {
                IHttpClient.Response uploadContactsDiff = cloudContactsSynchronizer.uploadContactsDiff(contactForNameSync, EditNicknameFragment.this.mOwnerCommsId);
                ContactsProviderUtils.updateContactName(EditNicknameFragment.this.mContactId, this.name);
                EditNicknameFragment.LOG.i("Edit contact success");
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACT_EDIT_AUTO_ADDED_SUCCESS, outline133);
                ResultInfo<Boolean, AlertSource> resultInfo = new ResultInfo<>(Boolean.TRUE, null);
                if (uploadContactsDiff != null) {
                    uploadContactsDiff.close();
                }
                return resultInfo;
            } catch (IOException e) {
                EditNicknameFragment.LOG.e("Edit contact failed", e);
                MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACT_EDIT_AUTO_ADDED_FAILURE, outline133);
                return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditContactTask.class.getName()));
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(ResultInfo<Boolean, AlertSource> resultInfo) {
            super.onPostExecute((EditContactTask) resultInfo);
            if (!EditNicknameFragment.this.isVisible()) {
                return;
            }
            if (resultInfo.getStatus().booleanValue()) {
                EditNicknameFragment.this.applicationManager.navigateUpward();
                return;
            }
            MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, MetricKeys.SCREEN_NAME_CONTACT_EDIT, resultInfo.getInfo());
            EditNicknameFragment.this.showErrorDialog(R.string.edit_contact_server_error);
            EditNicknameFragment.this.updateEditButton();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class EditNameTextWatcher implements TextWatcher {
        public EditNameTextWatcher() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            EditNicknameFragment editNicknameFragment = EditNicknameFragment.this;
            EditNicknameFragment.this.applyNickNameValidationState(editNicknameFragment.validateName(editNicknameFragment.mNicknameView));
            EditNicknameFragment.this.updateEditButton();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum FieldError {
        INVALID_CHAR,
        EXCEEDS_MAX_LENGTH
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyNickNameValidationState(Optional<FieldError> optional) {
        CharSequence text;
        if (optional.isPresent()) {
            int ordinal = optional.get().ordinal();
            if (ordinal == 0) {
                text = getText(R.string.input_name_invalid_char_error);
                this.mNicknameDescription.setVisibility(0);
            } else if (ordinal != 1) {
                text = "";
            } else {
                text = getText(R.string.input_maximum_length_exceeded);
                this.mNicknameDescription.setVisibility(0);
            }
            this.mNicknameErrorView.setText(text);
            this.mNicknameErrorView.setVisibility(0);
            return;
        }
        this.mNicknameDescription.setVisibility(0);
        this.mNicknameErrorView.setVisibility(8);
    }

    private void configureFragmentRequirements() {
        this.applicationManager.configurePageForFragment(new FragmentRequirements(this).withMenu(R.menu.edit_nickname_relationship_menu, new Toolbar.OnMenuItemClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$EditNicknameFragment$HYHkhViBWZqTMboSTtAELtD_NCE
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return EditNicknameFragment.this.lambda$configureFragmentRequirements$0$EditNicknameFragment(menuItem);
            }
        }, new FragmentRequirements.MenuInflatedCallback() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$EditNicknameFragment$QueDOyCovaE588P6VFCtg3ytB_M
            @Override // com.amazon.deecomms.api.navigation.FragmentRequirements.MenuInflatedCallback
            public final void onInflated(Menu menu) {
                EditNicknameFragment.this.lambda$configureFragmentRequirements$1$EditNicknameFragment(menu);
            }
        }).hidesFooter());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContactForSync getContactForNameSync(@NonNull ContactName contactName) {
        ContactUploadInfo contactUploadInfo = new ContactUploadInfo();
        contactUploadInfo.setContactName(contactName.getFirstName(), contactName.getLastName(), contactName.getNickName());
        contactUploadInfo.setCompany(this.mCompany);
        contactUploadInfo.setDeviceContactId(null);
        contactUploadInfo.setServerContactId(this.mContactId);
        contactUploadInfo.setRelationship(this.mRelationship);
        Iterator<ContactPhoneNumber> it2 = this.mPhoneNumbersList.iterator();
        while (it2.hasNext()) {
            ContactPhoneNumber next = it2.next();
            contactUploadInfo.addPhoneNumber(next.getPhoneNumber(), next.getRawType());
        }
        return new ContactForSync(contactUploadInfo, SyncOperationType.UPDATE);
    }

    private void init(View view) {
        this.mContactName = (ContactName) getArguments().getParcelable(Constants.BUNDLE_KEY_CONTACT_NAME_KEY);
        this.mContactId = getArguments().getString(Constants.BUNDLE_KEY_CONTACT_ID);
        this.mCompany = getArguments().getString("company");
        this.mRelationship = getArguments().getString(Constants.BUNDLE_KEY_CONTACT_RELATIONSHIP);
        this.mOwnerCommsId = getArguments().getString("ownerCommsId");
        this.mPhoneNumbersList = getArguments().getParcelableArrayList(Constants.BUNDLE_KEY_PHONE_NUMBERS);
        this.mNicknameDescription = (TextView) view.findViewById(R.id.nickname_description);
        this.mNicknameErrorView = (TextView) view.findViewById(R.id.contact_nickname_error);
        this.mNicknameView = (EditText) view.findViewById(R.id.editNickname);
        this.mNicknameView.addTextChangedListener(new EditNameTextWatcher());
        updateViews();
    }

    private void onSaveClicked() {
        if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_EDIT, AlertSource.newClassSource(EditNicknameFragment.class.getName()))) {
            return;
        }
        if (CommsInternal.getInstance().isLowInternalStorage()) {
            Utils.showDialog(getActivity(), R.string.error_title, R.string.message_low_memory);
            return;
        }
        String trim = this.mNicknameView.getText().toString().trim();
        ContactName contactName = new ContactName();
        contactName.setFirstName(this.mContactName.getFirstName());
        contactName.setLastName(this.mContactName.getLastName());
        contactName.setNickName(trim);
        new EditContactTask(contactName).execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorDialog(int i) {
        new AlertDialog.Builder(getContext()).setTitle(R.string.edit_contact_title).setMessage(i).setCancelable(true).setPositiveButton(getString(R.string.dialog_ok_button), $$Lambda$EditNicknameFragment$LvatH4J9GFZllAD_27ADVlRTL7M.INSTANCE).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEditButton() {
        Optional<FieldError> validateName = validateName(this.mNicknameView);
        Menu menu = this.mMenu;
        if (menu != null) {
            menu.findItem(R.id.save_edit_contact).setEnabled(!validateName.isPresent());
        } else {
            LOG.e("menu is null; cannot update edit button");
        }
    }

    private void updateViews() {
        ContactName contactName = this.mContactName;
        if (contactName != null) {
            this.mNicknameView.setText(contactName.getNickName());
        }
        updateEditButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Optional<FieldError> validateName(EditText editText) {
        String obj = editText.getText().toString();
        if (obj.trim().length() > this.sContactNameMaxLength) {
            return Optional.of(FieldError.EXCEEDS_MAX_LENGTH);
        }
        if (!TextUtils.isEmpty(obj) && LanguageUtil.containsUnsupportedCharSet(obj)) {
            return Optional.of(FieldError.INVALID_CHAR);
        }
        return Optional.absent();
    }

    public /* synthetic */ boolean lambda$configureFragmentRequirements$0$EditNicknameFragment(MenuItem menuItem) {
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

    public /* synthetic */ void lambda$configureFragmentRequirements$1$EditNicknameFragment(Menu menu) {
        this.mMenu = menu;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        View inflate = layoutInflater.inflate(R.layout.edit_nickname_view, viewGroup, false);
        this.sContactNameMaxLength = this.arcusConfig.getConfigInteger(RemoteConfigKeys.CONTACTS_CLOUD_MAX_LENGTH).intValue();
        init(inflate);
        configureFragmentRequirements();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Utils.hideKeyboard(getActivity());
    }
}
