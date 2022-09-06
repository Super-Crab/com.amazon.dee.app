package com.amazon.deecomms.contacts.model;

import android.content.Context;
import android.database.Cursor;
import androidx.annotation.Nullable;
import androidx.loader.content.CursorLoader;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import java.util.List;
import java.util.concurrent.Callable;
import rx.Observable;
/* loaded from: classes12.dex */
public class ContactCardInfo extends ContactInfo {
    private boolean canDropInOnMe;
    private boolean canIDropInOnHim;
    private String commsId;
    private boolean isAlexaEnabled;
    private boolean isNameEmpty;
    private String ownerCommsId;
    private String parentHomeGroupId;

    public ContactCardInfo(String str) {
        super(str);
        this.canDropInOnMe = false;
        this.canIDropInOnHim = false;
        this.isNameEmpty = false;
        this.isAlexaEnabled = false;
    }

    @Nullable
    public static ContactCardInfo createContactCardInfo(String str) {
        ContactCardInfo contactCardInfo;
        boolean z = true;
        String[] strArr = new String[1];
        strArr[0] = !Utils.isNullOrEmpty(str) ? str : "";
        String[] strArr2 = {"firstName", "lastName", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP, "company", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_NAME_EMPTY, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED, ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME, ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_I_DROP_IN_ON_HIM, "ownerCommsId", ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_CONTACT_SOURCE_TYPE, ContactProviderContract.PhoneNumberEntry.COLUMN_NAME_WITH_TABLE_NAME_SERVER_CONTACT_ID, "commsId", "number", ContactProviderContract.PhoneNumberEntry.COLUMN_NUMBER_TYPE, ContactProviderContract.PhoneNumberEntry.COLUMN_NUMBER_RAW_TYPE, ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP, ContactProviderContract.PhoneNumberEntry.COLUMN_IS_COBO_CALLABLE, ContactProviderContract.PhoneNumberEntry.COLUMN_HASHED_PHONE_NUMBER, "email", ContactProviderContract.EmailAddressEntry.COLUMN_EMAIL_ADDRESS_TYPE, ContactProviderContract.EmailAddressEntry.COLUMN_EMAIL_ADDRESS_RAW_TYPE};
        Context context = CommsDaggerWrapper.getComponent().getContext();
        Cursor mo3665loadInBackground = new CursorLoader(CommsDaggerWrapper.getComponent().getContext(), ContactProviderContract.getCommsIdToContactsToEmailAddressUri(context), strArr2, "PhoneNumbers.serverContactId = ?", strArr, null).mo3665loadInBackground();
        if (mo3665loadInBackground != null) {
            try {
                List<String> commsIdsFromCursor = ContactsProviderUtils.getCommsIdsFromCursor(mo3665loadInBackground);
                contactCardInfo = new ContactCardInfo(str);
                if (!commsIdsFromCursor.isEmpty()) {
                    contactCardInfo.setCommsId(commsIdsFromCursor.get(0));
                }
                contactCardInfo.setPhoneList(ContactsProviderUtils.getPhoneNumbersFromCursor(mo3665loadInBackground));
                contactCardInfo.setEmailAddressList(ContactsProviderUtils.getEmailAddressesFromCursor(mo3665loadInBackground));
                if (mo3665loadInBackground.moveToFirst()) {
                    String string = mo3665loadInBackground.getString(mo3665loadInBackground.getColumnIndex("firstName"));
                    String string2 = mo3665loadInBackground.getString(mo3665loadInBackground.getColumnIndex("lastName"));
                    String string3 = mo3665loadInBackground.getString(mo3665loadInBackground.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_NICK_NAME));
                    String string4 = mo3665loadInBackground.getString(mo3665loadInBackground.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_RELATIONSHIP));
                    String string5 = mo3665loadInBackground.getString(mo3665loadInBackground.getColumnIndex("company"));
                    contactCardInfo.setFirstName(string);
                    contactCardInfo.setLastName(string2);
                    contactCardInfo.setNickName(string3);
                    contactCardInfo.setRelationship(string4);
                    contactCardInfo.setCompany(string5);
                    contactCardInfo.setCanDropInOnMe(mo3665loadInBackground.getInt(mo3665loadInBackground.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME)) == 1);
                    contactCardInfo.setOwnerCommsId(mo3665loadInBackground.getString(mo3665loadInBackground.getColumnIndex("ownerCommsId")));
                    contactCardInfo.setNameEmpty(Integer.parseInt(mo3665loadInBackground.getString(mo3665loadInBackground.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_IS_NAME_EMPTY))) == 1);
                    if (mo3665loadInBackground.getInt(mo3665loadInBackground.getColumnIndex(ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_ALEXA_ENABLED)) != 1) {
                        z = false;
                    }
                    contactCardInfo.setAlexaEnabled(z);
                    String string6 = mo3665loadInBackground.getString(mo3665loadInBackground.getColumnIndex(ContactProviderContract.PhoneNumberEntry.COLUMN_PARENT_HOME_GROUP));
                    if (string6 == null) {
                        string6 = ContactsProviderUtils.getHomeGroupIdFromCommsIdFromDb(context, contactCardInfo.getCommsId());
                    }
                    contactCardInfo.setParentHomeGroupId(string6);
                    contactCardInfo.setCanIDropInOnHim(ContactsProviderUtils.canIDropInOnHomeGroup(string6));
                }
            } finally {
            }
        } else {
            contactCardInfo = null;
        }
        if (mo3665loadInBackground != null) {
            mo3665loadInBackground.close();
        }
        return contactCardInfo;
    }

    public static Observable<ContactCardInfo> createContactCardInfoObservable(Context context, final String str) {
        return Observable.fromCallable(new Callable<ContactCardInfo>() { // from class: com.amazon.deecomms.contacts.model.ContactCardInfo.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public ContactCardInfo mo3648call() throws Exception {
                return ContactCardInfo.createContactCardInfo(str);
            }
        });
    }

    public boolean canDropInOnMe() {
        return this.canDropInOnMe;
    }

    public boolean canIDropInOnHim() {
        return this.canIDropInOnHim;
    }

    public String getCommsId() {
        return this.commsId;
    }

    public String getOwnerCommsId() {
        return this.ownerCommsId;
    }

    public String getParentHomeGroupId() {
        return this.parentHomeGroupId;
    }

    public boolean isAlexaEnabled() {
        return this.isAlexaEnabled;
    }

    public boolean isNameEmpty() {
        return this.isNameEmpty;
    }

    public void setAlexaEnabled(boolean z) {
        this.isAlexaEnabled = z;
    }

    public void setCanDropInOnMe(boolean z) {
        this.canDropInOnMe = z;
    }

    public void setCanIDropInOnHim(boolean z) {
        this.canIDropInOnHim = z;
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }

    public void setNameEmpty(boolean z) {
        this.isNameEmpty = z;
    }

    public void setOwnerCommsId(String str) {
        this.ownerCommsId = str;
    }

    public void setParentHomeGroupId(String str) {
        this.parentHomeGroupId = str;
    }
}
