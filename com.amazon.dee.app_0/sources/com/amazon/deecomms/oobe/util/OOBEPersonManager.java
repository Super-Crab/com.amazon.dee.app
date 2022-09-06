package com.amazon.deecomms.oobe.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.oobe.Person;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class OOBEPersonManager {
    @Inject
    Context mContext;

    public OOBEPersonManager() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    @NonNull
    public Person getOOBEPerson() {
        Person person = new Person();
        CommsIdentityManager commsIdentityManager = CommsDaggerWrapper.getComponent().getCommsIdentityManager();
        person.directedId = commsIdentityManager.getDirectedId("getOOBEPerson", false);
        person.commsId = commsIdentityManager.getCommsId("getOOBEPerson", false);
        person.hashedCommsId = commsIdentityManager.getHashedCommsId();
        person.homeGroupId = commsIdentityManager.getHomeGroupId("getOOBEPerson", false);
        person.aor = commsIdentityManager.getAor();
        person.firstName = commsIdentityManager.getFirstName();
        person.lastName = commsIdentityManager.getLastName();
        person.phoneticFirstName = commsIdentityManager.getPhoneticFirstName();
        person.phoneticLastName = commsIdentityManager.getPhoneticLastName();
        person.phoneNumber = commsIdentityManager.getPhoneNumber();
        person.commsProvisionStatus = commsIdentityManager.getProvisionStatus(false, "getOOBEPerson", true);
        person.isCommsProvisioned = CommsProvisionStatus.PROVISIONED.equals(person.commsProvisionStatus);
        return person;
    }

    @NonNull
    public Person getOOBEPersonFromLegacySharedPreferences() {
        Person person = new Person();
        SharedPreferences oOBESharedPreferences = getOOBESharedPreferences();
        person.directedId = oOBESharedPreferences.getString(Constants.OOBE_USER_DIRECTEDID, "");
        person.commsId = oOBESharedPreferences.getString(Constants.OOBE_COMMS_ID, "");
        person.hashedCommsId = oOBESharedPreferences.getString(Constants.OOBE_HASHED_COMMS_ID, "");
        person.homeGroupId = oOBESharedPreferences.getString(Constants.OOBE_USER_HOMEGROUP_ID, "");
        person.aor = oOBESharedPreferences.getString(Constants.OOBE_USER_AOR, "");
        person.firstName = oOBESharedPreferences.getString(Constants.OOBE_FNAME, "");
        person.lastName = oOBESharedPreferences.getString(Constants.OOBE_LNAME, "");
        person.phoneticFirstName = oOBESharedPreferences.getString(Constants.OOBE_PHONETIC_FIRST_NAME, "");
        person.phoneticLastName = oOBESharedPreferences.getString(Constants.OOBE_PHONETIC_LAST_NAME, "");
        person.phoneCountryCode = oOBESharedPreferences.getString(Constants.OOBE_PHONE_CODE, "");
        person.phoneNumber = oOBESharedPreferences.getString(Constants.OOBE_PHONE_NUM, "");
        person.isCommsProvisioned = oOBESharedPreferences.getBoolean(Constants.OOBE_COMMS_PROV, false);
        person.commsProvisionStatus = CommsProvisionStatus.valueOf(oOBESharedPreferences.getString(Constants.OOBE_COMMS_PROVISION_STATUS, CommsProvisionStatus.UNKNOWN.name()));
        return person;
    }

    public SharedPreferences getOOBESharedPreferences() {
        return this.mContext.getSharedPreferences("SHARED_PREFS", 0);
    }

    public void saveOOBEPerson(@NonNull Person person) {
        CommsIdentityManager commsIdentityManager = CommsDaggerWrapper.getComponent().getCommsIdentityManager();
        commsIdentityManager.setDirectedId(person.directedId, "saveOOBEPerson", false);
        commsIdentityManager.setCommsId(person.commsId, "saveOOBEPerson", false);
        commsIdentityManager.setHashedCommsId(person.hashedCommsId);
        commsIdentityManager.setAor(person.aor, false);
        commsIdentityManager.setName(person.firstName, person.lastName, person.phoneticFirstName, person.phoneticLastName);
        String str = person.phoneNumber;
        if (!TextUtils.isEmpty(person.phoneCountryCode) && str != null && !str.startsWith("+")) {
            str = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport.outline1("+"), person.phoneCountryCode, str);
        }
        commsIdentityManager.setPhoneNumber(str);
        commsIdentityManager.setHomeGroupId(person.homeGroupId, false, "saveOOBEPerson", false);
        commsIdentityManager.setProvisionStatus(person.commsProvisionStatus, "saveOOBEPerson", false, false);
        saveOOBESharedPreferences(person);
    }

    public void saveOOBESharedPreferences(@NonNull Person person) {
        getOOBESharedPreferences().edit().putString(Constants.OOBE_USER_DIRECTEDID, person.directedId).putString(Constants.OOBE_COMMS_ID, person.commsId).putString(Constants.OOBE_HASHED_COMMS_ID, person.hashedCommsId).putString(Constants.OOBE_USER_AOR, person.aor).putString(Constants.OOBE_FNAME, person.firstName).putString(Constants.OOBE_LNAME, person.lastName).putString(Constants.OOBE_PHONETIC_FIRST_NAME, person.phoneticFirstName).putString(Constants.OOBE_PHONETIC_LAST_NAME, person.phoneticLastName).putString(Constants.OOBE_PHONE_CODE, person.phoneCountryCode).putString(Constants.OOBE_PHONE_NUM, person.phoneNumber).putBoolean(Constants.OOBE_IS_FNF, person.isFriendsAndFamily).putBoolean(Constants.OOBE_COMMS_PROV, person.isCommsProvisioned).putBoolean(Constants.OOBE_SPEAKER_PROV, person.isSpeakerProvisioned).putString(Constants.OOBE_USER_HOMEGROUP_ID, person.homeGroupId).putString(Constants.OOBE_COMMS_PROVISION_STATUS, person.commsProvisionStatus.toString()).apply();
    }

    public void setAorForOOBEUser(@Nullable String str) {
        getOOBESharedPreferences().edit().putString(Constants.OOBE_USER_AOR, str).apply();
    }

    public void setProvisionStatusForOOBEUser(@Nullable CommsProvisionStatus commsProvisionStatus) {
        String str;
        if (commsProvisionStatus == null) {
            str = CommsProvisionStatus.UNKNOWN.toString();
        } else {
            str = commsProvisionStatus.toString();
        }
        getOOBESharedPreferences().edit().putString(Constants.OOBE_COMMS_PROVISION_STATUS, str).apply();
    }
}
