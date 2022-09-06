package com.amazon.deecomms.contacts.ui;

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
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsInternal;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
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
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.util.ResultInfo;
import com.google.common.base.Optional;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.inject.Inject;
@Deprecated
/* loaded from: classes12.dex */
public class EditContactFragment extends Fragment {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, EditContactFragment.class);
    @Inject
    ApplicationManager applicationManager;
    @Inject
    CommsInternal commsInternal;
    private boolean isMyProfileContact;
    private String mCommsId;
    private String mCompany;
    private String mContactId;
    private ContactName mContactName;
    private TextView mFirstNameDescription;
    private TextView mFirstNameErrorView;
    private EditText mFirstNameView;
    private TextView mLastNameDescription;
    private TextView mLastNameErrorView;
    private EditText mLastNameView;
    private Menu mMenu;
    private String mOwnerCommsId;
    private ArrayList<ContactPhoneNumber> mPhoneNumbersList;
    private int sContactNameMaxLength;
    private int sIdentityFirstNameMaxLength;
    private int sIdentityLastNameMaxLength;

    /* renamed from: com.amazon.deecomms.contacts.ui.EditContactFragment$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$deecomms$contacts$ui$EditContactFragment$FieldError = new int[FieldError.values().length];

        static {
            try {
                $SwitchMap$com$amazon$deecomms$contacts$ui$EditContactFragment$FieldError[FieldError.EMPTY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$contacts$ui$EditContactFragment$FieldError[FieldError.INVALID_CHAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$deecomms$contacts$ui$EditContactFragment$FieldError[FieldError.EXCEEDS_MAX_LENGTH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
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
            EditContactFragment.LOG.d("Editing contact name");
            if (this.name == null) {
                EditContactFragment.LOG.e("Edit contact failed as the name to be updated is null");
                return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditContactFragment.this));
            } else if (EditContactFragment.this.isMyProfileContact) {
                try {
                    IHttpClient.Response mo3640execute = new ACMSClient(MetricKeys.OP_UPDATE_IDENTITY).request(MessageFormat.format("/users/{0}/identities", EditContactFragment.this.mCommsId)).authenticatedAsCurrentCommsUser().patchJson(this.name).mo3640execute();
                    try {
                        ContactsProviderUtils.updateContactName(EditContactFragment.this.mContactId, this.name);
                        CommsDaggerWrapper.getComponent().getCommsIdentityManager().updateUserNames(this.name);
                        EditContactFragment.LOG.i("Edit contact success");
                        ResultInfo<Boolean, AlertSource> resultInfo = new ResultInfo<>(Boolean.TRUE, AlertSource.newRequestSource(mo3640execute));
                        mo3640execute.close();
                        return resultInfo;
                    } finally {
                    }
                } catch (ServiceException e) {
                    EditContactFragment.LOG.e("Edit contact failed", e);
                    return new ResultInfo<>(Boolean.FALSE, AlertSource.newRequestSource(e));
                } catch (InvalidCommsIdentityException e2) {
                    EditContactFragment.LOG.e("Edit contact failed while updating the comms identity", e2);
                    return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditContactFragment.this));
                } catch (IOException e3) {
                    EditContactFragment.LOG.e("Edit contact failed", e3);
                    return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditContactFragment.this));
                }
            } else {
                try {
                    IHttpClient.Response uploadContactsDiff = new CloudContactsSynchronizer().uploadContactsDiff(EditContactFragment.this.getContactForNameSync(this.name), EditContactFragment.this.mOwnerCommsId);
                    ContactsProviderUtils.updateContactName(EditContactFragment.this.mContactId, this.name);
                    EditContactFragment.LOG.i("Edit contact success");
                    MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.CONTACT_EDIT_AUTO_ADDED_SUCCESS);
                    ResultInfo<Boolean, AlertSource> resultInfo2 = new ResultInfo<>(Boolean.TRUE, null);
                    if (uploadContactsDiff != null) {
                        uploadContactsDiff.close();
                    }
                    return resultInfo2;
                } catch (IOException e4) {
                    EditContactFragment.LOG.e("Edit contact failed", e4);
                    MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.CONTACT_EDIT_AUTO_ADDED_FAILURE);
                    return new ResultInfo<>(Boolean.FALSE, AlertSource.newClassSource(EditContactFragment.this));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(ResultInfo<Boolean, AlertSource> resultInfo) {
            super.onPostExecute((EditContactTask) resultInfo);
            if (!EditContactFragment.this.isVisible()) {
                return;
            }
            if (resultInfo.getStatus().booleanValue()) {
                EditContactFragment.this.applicationManager.navigateUpward();
                return;
            }
            MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, MetricKeys.SCREEN_NAME_CONTACT_EDIT, resultInfo.getInfo());
            EditContactFragment.this.showErrorDialog(R.string.edit_contact_server_error);
            EditContactFragment.this.updateEditButton();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class EditNameTextWatcher implements TextWatcher {
        private boolean isFirstName;

        public EditNameTextWatcher(boolean z) {
            this.isFirstName = z;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Optional validateName;
            Optional validateName2;
            if (this.isFirstName) {
                if (EditContactFragment.this.isMyProfileContact) {
                    EditContactFragment editContactFragment = EditContactFragment.this;
                    validateName2 = editContactFragment.validateName(editContactFragment.mFirstNameView, EditContactFragment.this.sIdentityFirstNameMaxLength);
                } else {
                    EditContactFragment editContactFragment2 = EditContactFragment.this;
                    validateName2 = editContactFragment2.validateName(editContactFragment2.mFirstNameView, EditContactFragment.this.sContactNameMaxLength);
                }
                EditContactFragment.this.applyFirstNameValidationState(validateName2);
            } else {
                if (EditContactFragment.this.isMyProfileContact) {
                    EditContactFragment editContactFragment3 = EditContactFragment.this;
                    validateName = editContactFragment3.validateName(editContactFragment3.mLastNameView, EditContactFragment.this.sIdentityLastNameMaxLength);
                } else {
                    EditContactFragment editContactFragment4 = EditContactFragment.this;
                    validateName = editContactFragment4.validateName(editContactFragment4.mLastNameView, EditContactFragment.this.sContactNameMaxLength);
                }
                EditContactFragment.this.applyLastNameValidationState(validateName);
            }
            EditContactFragment.this.updateEditButton();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum FieldError {
        EMPTY,
        INVALID_CHAR,
        EXCEEDS_MAX_LENGTH
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyFirstNameValidationState(Optional<FieldError> optional) {
        CharSequence text;
        if (optional.isPresent()) {
            int ordinal = optional.get().ordinal();
            if (ordinal == 0) {
                text = getText(R.string.oobe_first_name_error);
                this.mFirstNameDescription.setVisibility(4);
            } else if (ordinal == 1) {
                text = getText(R.string.input_name_invalid_char_error);
                this.mFirstNameDescription.setVisibility(0);
            } else if (ordinal != 2) {
                text = "";
            } else {
                text = getText(R.string.input_maximum_length_exceeded);
                this.mFirstNameDescription.setVisibility(0);
            }
            this.mFirstNameErrorView.setText(text);
            this.mFirstNameErrorView.setVisibility(0);
            return;
        }
        this.mFirstNameDescription.setVisibility(0);
        this.mFirstNameErrorView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyLastNameValidationState(Optional<FieldError> optional) {
        CharSequence text;
        if (optional.isPresent()) {
            int ordinal = optional.get().ordinal();
            if (ordinal != 0) {
                if (ordinal == 1) {
                    text = getText(R.string.input_name_invalid_char_error);
                    this.mLastNameDescription.setVisibility(0);
                } else if (ordinal != 2) {
                    text = "";
                } else {
                    text = getText(R.string.input_maximum_length_exceeded);
                    this.mLastNameDescription.setVisibility(0);
                }
            } else if (this.isMyProfileContact) {
                text = getText(R.string.oobe_last_name_error);
                this.mLastNameDescription.setVisibility(4);
            } else {
                this.mLastNameDescription.setVisibility(0);
                this.mLastNameErrorView.setVisibility(8);
                return;
            }
            this.mLastNameErrorView.setText(text);
            this.mLastNameErrorView.setVisibility(0);
            return;
        }
        this.mLastNameDescription.setVisibility(0);
        this.mLastNameErrorView.setVisibility(8);
    }

    private void configureFragmentRequirements() {
        this.applicationManager.configurePageForFragment(new FragmentRequirements(this).withMenu(R.menu.edit_contact_menu, new Toolbar.OnMenuItemClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$EditContactFragment$CvTbrwUBSnB2ZuOZOnpWckCPj1w
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return EditContactFragment.this.lambda$configureFragmentRequirements$0$EditContactFragment(menuItem);
            }
        }, new FragmentRequirements.MenuInflatedCallback() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$EditContactFragment$a6Hdh90hgUtqMYhG7zaq2QOcMgs
            @Override // com.amazon.deecomms.api.navigation.FragmentRequirements.MenuInflatedCallback
            public final void onInflated(Menu menu) {
                EditContactFragment.this.lambda$configureFragmentRequirements$1$EditContactFragment(menu);
            }
        }).hidesFooter());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ContactForSync getContactForNameSync(@NonNull ContactName contactName) {
        ContactUploadInfo contactUploadInfo = new ContactUploadInfo();
        contactUploadInfo.setContactName(contactName.getFirstName(), contactName.getLastName());
        contactUploadInfo.setCompany(this.mCompany);
        contactUploadInfo.setDeviceContactId(null);
        contactUploadInfo.setServerContactId(this.mContactId);
        Iterator<ContactPhoneNumber> it2 = this.mPhoneNumbersList.iterator();
        while (it2.hasNext()) {
            ContactPhoneNumber next = it2.next();
            contactUploadInfo.addPhoneNumber(next.getPhoneNumber(), next.getRawType());
        }
        return new ContactForSync(contactUploadInfo, SyncOperationType.UPDATE);
    }

    private void init(View view) {
        this.mContactName = (ContactName) getArguments().getParcelable(Constants.BUNDLE_KEY_CONTACT_NAME_KEY);
        this.mCommsId = getArguments().getString("COMMS_ID");
        this.mContactId = getArguments().getString(Constants.BUNDLE_KEY_CONTACT_ID);
        this.mCompany = getArguments().getString("company");
        this.isMyProfileContact = TextUtils.equals(this.mCommsId, CommsInternal.getInstance().getCommsId());
        this.mOwnerCommsId = getArguments().getString("ownerCommsId");
        this.mPhoneNumbersList = getArguments().getParcelableArrayList(Constants.BUNDLE_KEY_PHONE_NUMBERS);
        this.mFirstNameDescription = (TextView) view.findViewById(R.id.first_name_description);
        this.mLastNameDescription = (TextView) view.findViewById(R.id.last_name_description);
        this.mFirstNameErrorView = (TextView) view.findViewById(R.id.contact_first_name_error);
        this.mLastNameErrorView = (TextView) view.findViewById(R.id.contact_last_name_error);
        this.mFirstNameView = (EditText) view.findViewById(R.id.editFirstName);
        this.mFirstNameView.addTextChangedListener(new EditNameTextWatcher(true));
        this.mLastNameView = (EditText) view.findViewById(R.id.editLastName);
        this.mLastNameView.addTextChangedListener(new EditNameTextWatcher(false));
        updateViews();
    }

    private void onDoneClicked() {
        if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_EDIT, AlertSource.newClassSource(EditContactFragment.class.getName()))) {
            return;
        }
        if (this.commsInternal.isLowInternalStorage()) {
            Utils.showDialog(getActivity(), R.string.error_title, R.string.message_low_memory);
            return;
        }
        String trim = this.mFirstNameView.getText().toString().trim();
        String trim2 = this.mLastNameView.getText().toString().trim();
        if (TextUtils.isEmpty(trim) && TextUtils.isEmpty(trim2)) {
            showErrorDialog(R.string.invalid_name);
            return;
        }
        ContactName contactName = new ContactName();
        contactName.setFirstName(trim);
        contactName.setLastName(trim2);
        contactName.setNickName(this.mContactName.getNickName());
        new EditContactTask(contactName).execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showErrorDialog(int i) {
        new AlertDialog.Builder(getContext()).setTitle(R.string.edit_contact_title).setMessage(i).setCancelable(true).setPositiveButton(getString(R.string.dialog_ok_button), $$Lambda$EditContactFragment$yPoaK6qLcXsFWtYhb1gYlXTlc.INSTANCE).create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEditButton() {
        Menu menu = this.mMenu;
        if (menu != null) {
            MenuItem findItem = menu.findItem(R.id.done_edit_contact);
            if (this.isMyProfileContact) {
                findItem.setEnabled(!validateName(this.mFirstNameView, this.sIdentityFirstNameMaxLength).or(validateName(this.mLastNameView, this.sIdentityLastNameMaxLength)).isPresent());
                return;
            } else {
                findItem.setEnabled(!validateName(this.mFirstNameView, this.sContactNameMaxLength).isPresent());
                return;
            }
        }
        LOG.e("menu is null; cannot update edit button");
    }

    private void updateViews() {
        ContactName contactName = this.mContactName;
        if (contactName != null) {
            this.mFirstNameView.setText(contactName.getFirstName());
            this.mLastNameView.setText(this.mContactName.getLastName());
        }
        updateEditButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Optional<FieldError> validateName(EditText editText, int i) {
        String obj = editText.getText().toString();
        int length = obj.trim().length();
        if (length == 0) {
            return Optional.of(FieldError.EMPTY);
        }
        if (length > i) {
            return Optional.of(FieldError.EXCEEDS_MAX_LENGTH);
        }
        if (LanguageUtil.containsUnsupportedCharSet(obj)) {
            return Optional.of(FieldError.INVALID_CHAR);
        }
        return Optional.absent();
    }

    public /* synthetic */ boolean lambda$configureFragmentRequirements$0$EditContactFragment(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.done_edit_contact) {
            menuItem.setVisible(true);
            onDoneClicked();
            return true;
        }
        this.mMenu.setGroupEnabled(itemId, false);
        menuItem.setVisible(false);
        return false;
    }

    public /* synthetic */ void lambda$configureFragmentRequirements$1$EditContactFragment(Menu menu) {
        this.mMenu = menu;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        View inflate = layoutInflater.inflate(R.layout.edit_contact_card_view, viewGroup, false);
        ArcusConfig arcusConfig = CommsDaggerWrapper.getComponent().getArcusConfig();
        this.sIdentityFirstNameMaxLength = arcusConfig.getConfigInteger(RemoteConfigKeys.IDENTITY_FIRST_NAME_MAX_LENGTH).intValue();
        this.sIdentityLastNameMaxLength = arcusConfig.getConfigInteger(RemoteConfigKeys.IDENTITY_LAST_NAME_MAX_LENGTH).intValue();
        this.sContactNameMaxLength = arcusConfig.getConfigInteger(RemoteConfigKeys.CONTACTS_CLOUD_MAX_LENGTH).intValue();
        init(inflate);
        configureFragmentRequirements();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        Utils.hideKeyboard(getActivity());
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        configureFragmentRequirements();
        this.applicationManager.loadingComplete(CommsView.EditContact);
    }
}
