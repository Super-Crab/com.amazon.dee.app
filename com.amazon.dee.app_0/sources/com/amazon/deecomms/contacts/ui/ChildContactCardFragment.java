package com.amazon.deecomms.contacts.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
import com.amazon.deecomms.calling.ui.CallViewUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.acmsrecipes.GetRelationships;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.FragmentNavigationRouter;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.model.ContactCardInfo;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.contacts.model.LocalizedRelationshp;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.rx.SimpleSubscriber;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.inject.Inject;
import rx.Subscriber;
/* loaded from: classes12.dex */
public class ChildContactCardFragment extends Fragment {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ChildContactCardFragment.class);
    @Inject
    Context appContext;
    @Inject
    ApplicationManager applicationManager;
    private LinearLayout mChildContactInfoLayout;
    private LinearLayout mChildContactNicknameLayout;
    private LinearLayout mChildContactPhoneNumberLayout;
    private LinearLayout mChildContactRelationshipLayout;
    @Inject
    CommsIdentityManager mCommsIdentityManager;
    private Contact mContact;
    private SimpleSubscriber<ContactCardInfo> mContactCardInfoSubscriber;
    private String mContactId;
    private TextView mContactName;
    private ImageView mEditNicknameImage;
    private TextView mEditNicknameText;
    private ImageView mEditRelationshipImage;
    private TextView mEditRelationshipText;
    private TextView mNickName;
    private ArrayList<ContactPhoneNumber> mPhoneNumberList;
    private String mRawRelationship;
    private TextView mRelationship;
    private String mTitleName;

    private void configureFragmentRequirements() {
        this.applicationManager.configurePageForFragment(new FragmentRequirements(this).withTitle(getString(R.string.child_contact_title)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public String getDisplayName(@NonNull ContactCardInfo contactCardInfo) {
        return ContactUtils.getFullName(new FullContactName(this.mContact.getContactName(), this.mContact.getCompany()));
    }

    private void getRelationshipsFromServer() {
        new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.deecomms.contacts.ui.ChildContactCardFragment.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                LocalizedRelationshp[] relationships = new GetRelationships(ChildContactCardFragment.this.mCommsIdentityManager.getCommsId("getRelationshipsFromServer", false)).getRelationships();
                if (relationships != null) {
                    ArrayList arrayList = new ArrayList();
                    for (LocalizedRelationshp localizedRelationshp : relationships) {
                        arrayList.add(localizedRelationshp.getRawValue());
                        SharedPreferences.Editor edit = ChildContactCardFragment.this.appContext.getSharedPreferences("SHARED_PREFS", 0).edit();
                        StringBuilder outline1 = GeneratedOutlineSupport.outline1(Constants.CHILD_CONTACT_RELATIONSHIP_BASE_PREF);
                        outline1.append(localizedRelationshp.getRawValue().toUpperCase());
                        edit.putString(outline1.toString(), localizedRelationshp.getLocalizedValue());
                        edit.apply();
                    }
                    String json = new Gson().toJson(arrayList);
                    SharedPreferences.Editor edit2 = ChildContactCardFragment.this.appContext.getSharedPreferences("SHARED_PREFS", 0).edit();
                    edit2.putString(Constants.CHILD_CONTACT_RELATIONSHIP_LIST, json);
                    edit2.apply();
                    return true;
                }
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                if (bool == null || !bool.booleanValue() || TextUtils.isEmpty(ChildContactCardFragment.this.mRawRelationship)) {
                    return;
                }
                ChildContactCardFragment.this.updateRelationship();
            }
        }.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNicknameAndRelationship(ContactCardInfo contactCardInfo) {
        String nickName = contactCardInfo.getNickName();
        Contact contact = this.mContact;
        contact.setContactName(new ContactName(contact.getContactName().getFirstName(), this.mContact.getContactName().getLastName(), nickName));
        if (!TextUtils.isEmpty(nickName)) {
            this.mNickName.setText(nickName);
            this.mEditNicknameText.setVisibility(0);
            this.mEditNicknameImage.setVisibility(8);
        } else {
            this.mNickName.setText(getString(R.string.nickname_description));
            this.mEditNicknameText.setVisibility(8);
            this.mEditNicknameImage.setVisibility(0);
        }
        this.mRawRelationship = contactCardInfo.getRelationship();
        this.mContact.setRelationship(this.mRawRelationship);
        if (!TextUtils.isEmpty(this.mRawRelationship)) {
            updateRelationship();
            this.mEditRelationshipImage.setVisibility(8);
            this.mEditRelationshipText.setVisibility(0);
            return;
        }
        this.mEditRelationshipImage.setVisibility(0);
        this.mEditRelationshipText.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePhoneNumber(@NonNull ContactCardInfo contactCardInfo) {
        this.mChildContactPhoneNumberLayout.removeAllViews();
        this.mPhoneNumberList = new ArrayList<>(contactCardInfo.getPhoneList());
        ArrayList<ContactPhoneNumber> arrayList = this.mPhoneNumberList;
        if (arrayList != null && arrayList.size() > 0) {
            updatePhoneNumberUI(this.mPhoneNumberList.get(0));
            this.mChildContactPhoneNumberLayout.setVisibility(0);
            return;
        }
        this.mChildContactPhoneNumberLayout.setVisibility(8);
    }

    private void updatePhoneNumberUI(@NonNull ContactPhoneNumber contactPhoneNumber) {
        String phoneNumber = contactPhoneNumber.getPhoneNumber();
        if (TextUtils.isEmpty(phoneNumber)) {
            LOG.e("Invalid phone number is empty or null");
        } else if (getActivity() != null && !getActivity().isFinishing()) {
            View inflate = getActivity().getLayoutInflater().inflate(R.layout.card_user_info_view, (ViewGroup) this.mChildContactPhoneNumberLayout, false);
            ((TextView) inflate.findViewById(R.id.card_user_info_title)).setText(CallViewUtils.getPhoneNumberType(getActivity(), contactPhoneNumber));
            ((TextView) inflate.findViewById(R.id.card_user_info_description)).setText(Utils.formatPhoneNumber(phoneNumber));
            this.mChildContactPhoneNumberLayout.addView(inflate);
        } else {
            LOG.d("Not updating child contact phone number as the activity object is null / finishing");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRelationship() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SHARED_PREFS", 0);
        StringBuilder outline1 = GeneratedOutlineSupport.outline1(Constants.CHILD_CONTACT_RELATIONSHIP_BASE_PREF);
        outline1.append(this.mRawRelationship.toUpperCase());
        this.mRelationship.setText(sharedPreferences.getString(outline1.toString(), this.mRawRelationship));
    }

    public /* synthetic */ void lambda$onCreateView$0$ChildContactCardFragment(View view) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BUNDLE_KEY_CONTACT_NAME_KEY, this.mContact.getContactName());
        bundle.putString(Constants.BUNDLE_KEY_CONTACT_ID, this.mContact.getId());
        bundle.putParcelableArrayList(Constants.BUNDLE_KEY_PHONE_NUMBERS, this.mPhoneNumberList);
        bundle.putString("company", this.mContact.getCompany());
        bundle.putString("ownerCommsId", this.mContact.getOwnerCommsId());
        bundle.putString(Constants.BUNDLE_KEY_CONTACT_RELATIONSHIP, this.mContact.getRelationship());
        FragmentNavigationRouter.switchToFragment(CommsView.EditNickname, bundle);
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACTS_EDIT_NICKNAME_CLICK);
    }

    public /* synthetic */ void lambda$onCreateView$1$ChildContactCardFragment(View view) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.BUNDLE_KEY_CONTACT_NAME_KEY, this.mContact.getContactName());
        bundle.putString(Constants.BUNDLE_KEY_CONTACT_ID, this.mContact.getId());
        bundle.putParcelableArrayList(Constants.BUNDLE_KEY_PHONE_NUMBERS, this.mPhoneNumberList);
        bundle.putString("company", this.mContact.getCompany());
        bundle.putString("ownerCommsId", this.mContact.getOwnerCommsId());
        bundle.putString(Constants.BUNDLE_KEY_CONTACT_RELATIONSHIP, this.mContact.getRelationship());
        FragmentNavigationRouter.switchToFragment(CommsView.RelationshipList, bundle);
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACTS_EDIT_RELATIONSHIP_CLICK);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        View inflate = layoutInflater.inflate(R.layout.child_contact_card, viewGroup, false);
        this.mContactName = (TextView) inflate.findViewById(R.id.child_contact_name);
        this.mChildContactPhoneNumberLayout = (LinearLayout) inflate.findViewById(R.id.child_contact_card_phone_number_layout);
        this.mChildContactInfoLayout = (LinearLayout) inflate.findViewById(R.id.child_contact_information_layout);
        this.mChildContactNicknameLayout = (LinearLayout) this.mChildContactInfoLayout.findViewById(R.id.child_contact_nickname);
        this.mChildContactRelationshipLayout = (LinearLayout) this.mChildContactInfoLayout.findViewById(R.id.child_contact_relationship);
        this.mChildContactNicknameLayout = (LinearLayout) this.mChildContactInfoLayout.findViewById(R.id.child_contact_nickname);
        this.mNickName = (TextView) this.mChildContactInfoLayout.findViewById(R.id.child_contact_nickname_description);
        this.mRelationship = (TextView) this.mChildContactInfoLayout.findViewById(R.id.child_contact_relationship_description);
        this.mEditNicknameImage = (ImageView) this.mChildContactInfoLayout.findViewById(R.id.edit_nickname_icon);
        this.mEditNicknameText = (TextView) this.mChildContactInfoLayout.findViewById(R.id.edit_nickname_text);
        this.mEditRelationshipImage = (ImageView) this.mChildContactInfoLayout.findViewById(R.id.edit_relationship_icon);
        this.mEditRelationshipText = (TextView) this.mChildContactInfoLayout.findViewById(R.id.edit_relationship_text);
        this.mContact = new Contact();
        this.mTitleName = getArguments().getString(Constants.CHILD_CONTACT_OWNER_NAME);
        ContactEntry contactEntry = (ContactEntry) getArguments().getParcelable(Constants.CHILD_CONTACT_ENTRY_KEY);
        if (contactEntry != null) {
            contactEntry.toContact(this.mContact);
            this.mContactId = contactEntry.getId();
        }
        this.mChildContactNicknameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ChildContactCardFragment$kxihRXWCEM2YccmnelZtwPw7sfg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChildContactCardFragment.this.lambda$onCreateView$0$ChildContactCardFragment(view);
            }
        });
        this.mChildContactRelationshipLayout = (LinearLayout) this.mChildContactInfoLayout.findViewById(R.id.child_contact_relationship);
        this.mChildContactRelationshipLayout.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ChildContactCardFragment$mNpZUMLejm5nv71zAes2GDmHrLw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChildContactCardFragment.this.lambda$onCreateView$1$ChildContactCardFragment(view);
            }
        });
        this.mContactCardInfoSubscriber = new SimpleSubscriber<ContactCardInfo>() { // from class: com.amazon.deecomms.contacts.ui.ChildContactCardFragment.1
            @Override // com.amazon.deecomms.rx.SimpleSubscriber
            public void onCall(ContactCardInfo contactCardInfo) {
                if (contactCardInfo == null) {
                    ChildContactCardFragment.LOG.e("Null contact card info!!!");
                    return;
                }
                ChildContactCardFragment.this.mContactName.setText(ChildContactCardFragment.this.getDisplayName(contactCardInfo));
                ChildContactCardFragment.this.updatePhoneNumber(contactCardInfo);
                ChildContactCardFragment.this.updateNicknameAndRelationship(contactCardInfo);
            }
        };
        ContactCardInfo.createContactCardInfoObservable(getContext(), this.mContactId).subscribe((Subscriber<? super ContactCardInfo>) this.mContactCardInfoSubscriber);
        configureFragmentRequirements();
        getRelationshipsFromServer();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        SimpleSubscriber<ContactCardInfo> simpleSubscriber = this.mContactCardInfoSubscriber;
        if (simpleSubscriber != null) {
            simpleSubscriber.unsubscribe();
        }
        super.onDestroyView();
    }
}
