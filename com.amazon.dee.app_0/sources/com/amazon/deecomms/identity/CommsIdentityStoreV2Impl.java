package com.amazon.deecomms.identity;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.protocols.marketplace.MarketplaceName;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentity;
import com.amazon.deecomms.api.CommsIdentityStoreV2;
import com.amazon.deecomms.api.MarketplaceInfo;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.util.Consumer;
/* loaded from: classes12.dex */
public class CommsIdentityStoreV2Impl implements CommsIdentityStoreV2 {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsIdentityStoreV2.class);
    @VisibleForTesting
    static final String SHARED_PREFS_IDENTITY = "SHARED_PREFS_IDENTITY";
    private CommsIdentity commsIdentity;
    private Context context;

    public CommsIdentityStoreV2Impl(@NonNull Context context) {
        this.context = context;
        this.commsIdentity = new CommsIdentity();
    }

    private void ensurePopulated() {
        if (!this.commsIdentity.isPopulated()) {
            LOG.i("current CommsIdentity not populated, populating from identity shared preferences");
            populateIdentityFromSharedPreferences();
        }
        if (!this.commsIdentity.isPopulated()) {
            LOG.i("current CommsIdentity and identity shared preferences not populated, populating from OOBE user");
            populateIdentityWithOOBEUser();
        }
    }

    private SharedPreferences getIdentitySharedPreferences() {
        return this.context.getSharedPreferences(SHARED_PREFS_IDENTITY, 0);
    }

    private SharedPreferences.Editor getIdentitySharedPreferencesEditor() {
        return getIdentitySharedPreferences().edit();
    }

    private Person getOOBEPersonFromLegacySharedPreferences(boolean z) {
        if (!z) {
            MetricsHelper.recordCounterMetricOperational(MetricKeys.POPULATE_IDENTITY_FROM_OOBE, 1.0d);
        }
        return CommsDaggerWrapper.getComponent().getOOBEPersonManager().getOOBEPersonFromLegacySharedPreferences();
    }

    private synchronized CommsProvisionStatus getProvisionStatusInternal(boolean z) {
        CommsProvisionStatus provisioningStatus = this.commsIdentity.getProvisioningStatus();
        if (provisioningStatus == null || CommsProvisionStatus.UNKNOWN.equals(provisioningStatus)) {
            CommsProvisionStatus valueOf = CommsProvisionStatus.valueOf(getIdentitySharedPreferences().getString(Constants.PROVISION_STATUS_PREF, CommsProvisionStatus.UNKNOWN.toString()));
            if (valueOf == CommsProvisionStatus.UNKNOWN) {
                valueOf = getOOBEPersonFromLegacySharedPreferences(z).commsProvisionStatus;
                getIdentitySharedPreferencesEditor().putString(Constants.PROVISION_STATUS_PREF, valueOf.toString()).apply();
            }
            this.commsIdentity.setProvisioningStatus(valueOf, "getProvisionStatus", true);
            return valueOf;
        }
        return provisioningStatus;
    }

    private synchronized void populateIdentityFromSharedPreferences() {
        SharedPreferences identitySharedPreferences = getIdentitySharedPreferences();
        String string = identitySharedPreferences.getString(Constants.DIRECTED_ID_PREF, "");
        String string2 = identitySharedPreferences.getString(Constants.COMMS_ID_PREF, "");
        String string3 = identitySharedPreferences.getString(Constants.HASHED_COMMS_ID_PREF, "");
        String string4 = identitySharedPreferences.getString(Constants.FIRST_NAME_PREF, "");
        String string5 = identitySharedPreferences.getString(Constants.LAST_NAME_PREF, "");
        String string6 = identitySharedPreferences.getString(Constants.PHONETIC_FIRST_NAME_PREF, "");
        String string7 = identitySharedPreferences.getString(Constants.PHONETIC_LAST_NAME_PREF, "");
        String string8 = identitySharedPreferences.getString(Constants.PHONE_NUMBER_PREF, "");
        String string9 = identitySharedPreferences.getString(Constants.HOMEGROUP_ID_PREF, "");
        String string10 = identitySharedPreferences.getString(Constants.EMAIL_PREF, "");
        String string11 = identitySharedPreferences.getString(Constants.USER_AOR_PREF, "");
        boolean z = identitySharedPreferences.getBoolean(Constants.HAS_DEVICES_PREF, false);
        String string12 = identitySharedPreferences.getString(Constants.MARKETPLACE_ID_PREF, "");
        String string13 = identitySharedPreferences.getString(Constants.COUNTRY_CODE_PREF, "");
        String string14 = identitySharedPreferences.getString(Constants.RESIDENCE_COUNTRY_PREF, "");
        String string15 = identitySharedPreferences.getString(Constants.PROVISION_STATUS_PREF, CommsProvisionStatus.UNKNOWN.toString());
        if (CommsProvisionStatus.UNKNOWN.toString().equals(string15)) {
            string15 = getOOBEPersonFromLegacySharedPreferences(false).commsProvisionStatus.toString();
            getIdentitySharedPreferencesEditor().putString(Constants.PROVISION_STATUS_PREF, string15).apply();
        }
        CommsProvisionStatus valueOf = CommsProvisionStatus.valueOf(string15);
        this.commsIdentity.setDirectedId(string, "populateIdentityFromSharedPreferences", true);
        this.commsIdentity.setCommsId(string2, "populateIdentityFromSharedPreferences", true);
        this.commsIdentity.setHashedCommsId(string3);
        this.commsIdentity.setAor(string11);
        this.commsIdentity.setFirstName(string4);
        this.commsIdentity.setLastName(string5);
        this.commsIdentity.setPhoneticFirstName(string6);
        this.commsIdentity.setPhoneticLastName(string7);
        this.commsIdentity.setPhoneNumber(string8);
        this.commsIdentity.setHomeGroupId(string9);
        this.commsIdentity.setEmail(string10);
        this.commsIdentity.setHasDevices(z);
        this.commsIdentity.setProvisioningStatus(valueOf, "populateIdentityFromSharedPreferences", true);
        this.commsIdentity.setCountryOfResidence(string14);
        if (!TextUtils.isEmpty(string12)) {
            this.commsIdentity.setUserPFMInfo(new MarketplaceInfo(string12, string13, CommsIdentityUtils.getPfmCodeFromMarketplace(string12, MarketplaceName.US.toString()), true));
        }
    }

    private void populateIdentityWithOOBEUser() {
        Person oOBEPersonFromLegacySharedPreferences = getOOBEPersonFromLegacySharedPreferences(false);
        if (!oOBEPersonFromLegacySharedPreferences.commsProvisionStatus.equals(CommsProvisionStatus.DEPROVISIONED)) {
            this.commsIdentity.setCommsId(oOBEPersonFromLegacySharedPreferences.commsId, "populateIdentityWithOOBEUser", true);
            this.commsIdentity.setHashedCommsId(oOBEPersonFromLegacySharedPreferences.hashedCommsId);
            this.commsIdentity.setHomeGroupId(oOBEPersonFromLegacySharedPreferences.homeGroupId);
        }
        this.commsIdentity.setDirectedId(oOBEPersonFromLegacySharedPreferences.directedId, "populateIdentityWithOOBEUser", true);
        this.commsIdentity.setFirstName(oOBEPersonFromLegacySharedPreferences.firstName);
        this.commsIdentity.setLastName(oOBEPersonFromLegacySharedPreferences.lastName);
        this.commsIdentity.setPhoneticFirstName(oOBEPersonFromLegacySharedPreferences.phoneticFirstName);
        this.commsIdentity.setPhoneticLastName(oOBEPersonFromLegacySharedPreferences.phoneticLastName);
        this.commsIdentity.setPhoneNumber(oOBEPersonFromLegacySharedPreferences.phoneNumber);
        this.commsIdentity.setProvisioningStatus(oOBEPersonFromLegacySharedPreferences.commsProvisionStatus, "populateIdentityWithOOBEUser", true);
        saveToSharedPreferences();
    }

    private synchronized void saveToSharedPreferences() {
        CommsProvisionStatus provisioningStatus = this.commsIdentity.getProvisioningStatus();
        if (provisioningStatus == null) {
            provisioningStatus = CommsProvisionStatus.UNKNOWN;
        }
        String str = "";
        String str2 = "";
        MarketplaceInfo userPFMInfo = this.commsIdentity.getUserPFMInfo();
        if (userPFMInfo != null) {
            str = userPFMInfo.getMarketplaceId();
            str2 = userPFMInfo.getMarketplaceIdCode();
        }
        getIdentitySharedPreferencesEditor().putString(Constants.DIRECTED_ID_PREF, this.commsIdentity.getDirectedId()).putString(Constants.COMMS_ID_PREF, this.commsIdentity.getCommsId()).putString(Constants.HASHED_COMMS_ID_PREF, this.commsIdentity.getHashedCommsId()).putString(Constants.USER_AOR_PREF, this.commsIdentity.getAor()).putString(Constants.FIRST_NAME_PREF, this.commsIdentity.getFirstName()).putString(Constants.LAST_NAME_PREF, this.commsIdentity.getLastName()).putString(Constants.PHONETIC_FIRST_NAME_PREF, this.commsIdentity.getPhoneticFirstName()).putString(Constants.PHONETIC_LAST_NAME_PREF, this.commsIdentity.getPhoneticLastName()).putString(Constants.PHONE_NUMBER_PREF, this.commsIdentity.getPhoneNumber()).putString(Constants.HOMEGROUP_ID_PREF, this.commsIdentity.getHomeGroupId()).putString(Constants.EMAIL_PREF, this.commsIdentity.getEmail()).putBoolean(Constants.HAS_DEVICES_PREF, this.commsIdentity.hasDevices()).putString(Constants.MARKETPLACE_ID_PREF, str).putString(Constants.COUNTRY_CODE_PREF, str2).putString(Constants.RESIDENCE_COUNTRY_PREF, this.commsIdentity.getCountryOfResidence()).putString(Constants.PROVISION_STATUS_PREF, provisioningStatus.toString()).apply();
        CommsDaggerWrapper.getComponent().getOOBEPersonManager().setProvisionStatusForOOBEUser(provisioningStatus);
    }

    private void setIfNotEmpty(String str, Consumer<String> consumer) {
        if (!TextUtils.isEmpty(str)) {
            consumer.accept(str);
        }
    }

    private void validateCommsIdentity(CommsIdentity commsIdentity) throws InvalidCommsIdentityException {
        if (commsIdentity != null) {
            if (!TextUtils.isEmpty(commsIdentity.getDirectedId())) {
                if (commsIdentity.getProvisioningStatus() == CommsProvisionStatus.PROVISIONED && TextUtils.isEmpty(commsIdentity.getHomeGroupId())) {
                    throw new InvalidCommsIdentityException("CommsIdentity incomplete: homegroupId is null and user marked as provisioned.");
                }
                if (!CommsProvisionStatus.DEPROVISIONED.equals(commsIdentity.getProvisioningStatus()) && TextUtils.isEmpty(commsIdentity.getCommsId())) {
                    throw new InvalidCommsIdentityException("CommsIdentity incomplete: commsId is null.");
                }
                return;
            }
            throw new InvalidCommsIdentityException("CommsIdentity incomplete: directedId is null.");
        }
        throw new InvalidCommsIdentityException("CommsIdentity incomplete: commsIdentity is null.");
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void clearCommsIdentity(boolean z) {
        this.commsIdentity.clear();
        if (z) {
            getIdentitySharedPreferencesEditor().clear().apply();
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getAor() {
        String aor = this.commsIdentity.getAor();
        if (!TextUtils.isEmpty(aor)) {
            return aor;
        }
        String string = getIdentitySharedPreferences().getString(Constants.USER_AOR_PREF, "");
        if (TextUtils.isEmpty(string)) {
            string = getOOBEPersonFromLegacySharedPreferences(false).aor;
            getIdentitySharedPreferencesEditor().putString(Constants.USER_AOR_PREF, string).apply();
        }
        CommsIdentity commsIdentity = this.commsIdentity;
        commsIdentity.getClass();
        setIfNotEmpty(string, new $$Lambda$nD6kWY_ukQV9ylfYSDmusXI65TM(commsIdentity));
        return string;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getCommsId(boolean z) {
        String commsId = this.commsIdentity.getCommsId();
        if (!TextUtils.isEmpty(commsId)) {
            return commsId;
        }
        String string = getIdentitySharedPreferences().getString(Constants.COMMS_ID_PREF, "");
        if (TextUtils.isEmpty(string) && !CommsProvisionStatus.DEPROVISIONED.equals(getProvisionStatusInternal(z))) {
            string = getOOBEPersonFromLegacySharedPreferences(z).commsId;
            getIdentitySharedPreferencesEditor().putString(Constants.COMMS_ID_PREF, string).apply();
        }
        setIfNotEmpty(string, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$CommsIdentityStoreV2Impl$6rQug4SVsHYdHh8RSHw6tuRjXx4
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                CommsIdentityStoreV2Impl.this.lambda$getCommsId$1$CommsIdentityStoreV2Impl((String) obj);
            }
        });
        return string;
    }

    @VisibleForTesting
    CommsIdentity getCommsIdentity() {
        return this.commsIdentity;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getCountryOfResidence() {
        String countryOfResidence = this.commsIdentity.getCountryOfResidence();
        if (!TextUtils.isEmpty(countryOfResidence)) {
            return countryOfResidence;
        }
        String string = getIdentitySharedPreferences().getString(Constants.RESIDENCE_COUNTRY_PREF, "");
        final CommsIdentity commsIdentity = this.commsIdentity;
        commsIdentity.getClass();
        setIfNotEmpty(string, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$j8wS2V228u8f4YMNAGno4u2mv68
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                CommsIdentity.this.setCountryOfResidence((String) obj);
            }
        });
        return string;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized CommsIdentity getCurrentCommsIdentity() {
        CommsIdentity commsIdentity;
        ensurePopulated();
        commsIdentity = new CommsIdentity();
        commsIdentity.copyFrom(this.commsIdentity);
        return commsIdentity;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getDirectedId() {
        String directedId = this.commsIdentity.getDirectedId();
        if (!TextUtils.isEmpty(directedId)) {
            return directedId;
        }
        String string = getIdentitySharedPreferences().getString(Constants.DIRECTED_ID_PREF, "");
        if (TextUtils.isEmpty(string)) {
            string = getOOBEPersonFromLegacySharedPreferences(false).directedId;
            getIdentitySharedPreferencesEditor().putString(Constants.DIRECTED_ID_PREF, string).apply();
        }
        setIfNotEmpty(string, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$CommsIdentityStoreV2Impl$bquBJhm3MnHBrMMzrQE6qCs2dVM
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                CommsIdentityStoreV2Impl.this.lambda$getDirectedId$0$CommsIdentityStoreV2Impl((String) obj);
            }
        });
        return string;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getEmail() {
        String email = this.commsIdentity.getEmail();
        if (!TextUtils.isEmpty(email)) {
            return email;
        }
        String string = getIdentitySharedPreferences().getString(Constants.EMAIL_PREF, "");
        CommsIdentity commsIdentity = this.commsIdentity;
        commsIdentity.getClass();
        setIfNotEmpty(string, new $$Lambda$KLmhHz65kgZlbmSXByxkM3NzDT8(commsIdentity));
        return string;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getFirstName() {
        String firstName = this.commsIdentity.getFirstName();
        if (!TextUtils.isEmpty(firstName)) {
            return firstName;
        }
        String string = getIdentitySharedPreferences().getString(Constants.FIRST_NAME_PREF, "");
        if (TextUtils.isEmpty(string)) {
            string = getOOBEPersonFromLegacySharedPreferences(false).firstName;
            getIdentitySharedPreferencesEditor().putString(Constants.FIRST_NAME_PREF, string).apply();
        }
        CommsIdentity commsIdentity = this.commsIdentity;
        commsIdentity.getClass();
        setIfNotEmpty(string, new $$Lambda$Yd04KItImoyEC3mlwaUHwcyyFU(commsIdentity));
        return string;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getHashedCommsId() {
        String hashedCommsId = this.commsIdentity.getHashedCommsId();
        if (!TextUtils.isEmpty(hashedCommsId)) {
            return hashedCommsId;
        }
        String string = getIdentitySharedPreferences().getString(Constants.HASHED_COMMS_ID_PREF, "");
        if (TextUtils.isEmpty(string)) {
            string = getOOBEPersonFromLegacySharedPreferences(false).hashedCommsId;
            getIdentitySharedPreferencesEditor().putString(Constants.HASHED_COMMS_ID_PREF, string).apply();
        }
        CommsIdentity commsIdentity = this.commsIdentity;
        commsIdentity.getClass();
        setIfNotEmpty(string, new $$Lambda$M2TuQPHN9zoCr51xMbGI9HnVJUU(commsIdentity));
        return string;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getHomeGroupId() {
        String homeGroupId = this.commsIdentity.getHomeGroupId();
        if (!TextUtils.isEmpty(homeGroupId)) {
            return homeGroupId;
        }
        String string = getIdentitySharedPreferences().getString(Constants.HOMEGROUP_ID_PREF, "");
        if (TextUtils.isEmpty(string)) {
            string = getOOBEPersonFromLegacySharedPreferences(false).homeGroupId;
            getIdentitySharedPreferencesEditor().putString(Constants.HOMEGROUP_ID_PREF, string).apply();
        }
        CommsIdentity commsIdentity = this.commsIdentity;
        commsIdentity.getClass();
        setIfNotEmpty(string, new $$Lambda$vOOHzck_4QnI4arR2U1CRw_mJkw(commsIdentity));
        return string;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getLastName() {
        String lastName = this.commsIdentity.getLastName();
        if (!TextUtils.isEmpty(lastName)) {
            return lastName;
        }
        String string = getIdentitySharedPreferences().getString(Constants.LAST_NAME_PREF, "");
        if (TextUtils.isEmpty(string)) {
            string = getOOBEPersonFromLegacySharedPreferences(false).lastName;
            getIdentitySharedPreferencesEditor().putString(Constants.LAST_NAME_PREF, string).apply();
        }
        CommsIdentity commsIdentity = this.commsIdentity;
        commsIdentity.getClass();
        setIfNotEmpty(string, new $$Lambda$1iWDTNsLuZr7v0u0h9Lfg2nY8s(commsIdentity));
        return string;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getPhoneNumber() {
        String phoneNumber = this.commsIdentity.getPhoneNumber();
        if (!TextUtils.isEmpty(phoneNumber)) {
            return phoneNumber;
        }
        String string = getIdentitySharedPreferences().getString(Constants.PHONE_NUMBER_PREF, "");
        if (TextUtils.isEmpty(string)) {
            String str = getOOBEPersonFromLegacySharedPreferences(false).phoneNumber;
            String str2 = getOOBEPersonFromLegacySharedPreferences(false).phoneCountryCode;
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                string = str;
            } else {
                string = "+" + str2 + str;
            }
            getIdentitySharedPreferencesEditor().putString(Constants.PHONE_NUMBER_PREF, string).apply();
        }
        CommsIdentity commsIdentity = this.commsIdentity;
        commsIdentity.getClass();
        setIfNotEmpty(string, new $$Lambda$UhUgJhG1xCQHgVtQ4HhV6dbW58(commsIdentity));
        return string;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getPhoneticFirstName() {
        String phoneticFirstName = this.commsIdentity.getPhoneticFirstName();
        if (!TextUtils.isEmpty(phoneticFirstName)) {
            return phoneticFirstName;
        }
        String string = getIdentitySharedPreferences().getString(Constants.PHONETIC_FIRST_NAME_PREF, "");
        if (TextUtils.isEmpty(string)) {
            string = getOOBEPersonFromLegacySharedPreferences(false).phoneticFirstName;
            getIdentitySharedPreferencesEditor().putString(Constants.PHONETIC_FIRST_NAME_PREF, string).apply();
        }
        CommsIdentity commsIdentity = this.commsIdentity;
        commsIdentity.getClass();
        setIfNotEmpty(string, new $$Lambda$0NsTtpHwg1h1f5Sz_zGCrzRP9BM(commsIdentity));
        return string;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getPhoneticLastName() {
        String phoneticLastName = this.commsIdentity.getPhoneticLastName();
        if (!TextUtils.isEmpty(phoneticLastName)) {
            return phoneticLastName;
        }
        String string = getIdentitySharedPreferences().getString(Constants.PHONETIC_LAST_NAME_PREF, "");
        if (TextUtils.isEmpty(string)) {
            string = getOOBEPersonFromLegacySharedPreferences(false).phoneticLastName;
            getIdentitySharedPreferencesEditor().putString(Constants.PHONETIC_LAST_NAME_PREF, string).apply();
        }
        CommsIdentity commsIdentity = this.commsIdentity;
        commsIdentity.getClass();
        setIfNotEmpty(string, new $$Lambda$EeAizuMt7QIdaFFaWowt07aeY(commsIdentity));
        return string;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getProfileName() {
        return CommsIdentityUtils.safeNameFormat(getFirstName(), getLastName());
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized CommsProvisionStatus getProvisionStatus(boolean z) {
        return getProvisionStatusInternal(false);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized MarketplaceInfo getUserPFMInfo(boolean z) {
        MarketplaceInfo userPFMInfo;
        userPFMInfo = this.commsIdentity.getUserPFMInfo();
        if (userPFMInfo == null || TextUtils.isEmpty(userPFMInfo.getPfmCode())) {
            SharedPreferences identitySharedPreferences = getIdentitySharedPreferences();
            String string = identitySharedPreferences.getString(Constants.MARKETPLACE_ID_PREF, "");
            String string2 = identitySharedPreferences.getString(Constants.COUNTRY_CODE_PREF, "");
            if (!TextUtils.isEmpty(string)) {
                userPFMInfo = new MarketplaceInfo(string, string2, CommsIdentityUtils.getPfmCodeFromMarketplace(string, MarketplaceName.US.toString()), true);
                this.commsIdentity.setUserPFMInfo(userPFMInfo);
            }
        }
        return userPFMInfo;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized boolean hasDevices() {
        boolean hasDevices;
        hasDevices = this.commsIdentity.hasDevices();
        if (!hasDevices) {
            hasDevices = getIdentitySharedPreferences().getBoolean(Constants.HAS_DEVICES_PREF, false);
            this.commsIdentity.setHasDevices(hasDevices);
        }
        return hasDevices;
    }

    public /* synthetic */ void lambda$getCommsId$1$CommsIdentityStoreV2Impl(String str) {
        this.commsIdentity.setCommsId(str, "getCommsId", true);
    }

    public /* synthetic */ void lambda$getDirectedId$0$CommsIdentityStoreV2Impl(String str) {
        this.commsIdentity.setDirectedId(str, "getDirectedId", true);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public void legacyPersistCurrentIdentity() throws InvalidCommsIdentityException {
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void populateCommsIdentity(@NonNull CommsIdentity commsIdentity) throws InvalidCommsIdentityException {
        validateCommsIdentity(commsIdentity);
        this.commsIdentity.clear();
        this.commsIdentity.copyFrom(commsIdentity);
        saveToSharedPreferences();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setAor(String str, boolean z) {
        CommsDaggerWrapper.getComponent().getOOBEPersonManager().setAorForOOBEUser(str);
        this.commsIdentity.setAor(str);
        getIdentitySharedPreferencesEditor().putString(Constants.USER_AOR_PREF, this.commsIdentity.getAor()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setCommsId(String str) {
        this.commsIdentity.setCommsId(str, "", true);
        getIdentitySharedPreferencesEditor().putString(Constants.COMMS_ID_PREF, this.commsIdentity.getCommsId()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setCountryOfResidence(String str) {
        this.commsIdentity.setCountryOfResidence(str);
        getIdentitySharedPreferencesEditor().putString(Constants.RESIDENCE_COUNTRY_PREF, this.commsIdentity.getCountryOfResidence()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setDirectedId(String str) {
        this.commsIdentity.setDirectedId(str, "", true);
        getIdentitySharedPreferencesEditor().putString(Constants.DIRECTED_ID_PREF, this.commsIdentity.getDirectedId()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setEmail(String str) {
        this.commsIdentity.setEmail(str);
        getIdentitySharedPreferencesEditor().putString(Constants.EMAIL_PREF, this.commsIdentity.getEmail()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setFirstName(String str) {
        this.commsIdentity.setFirstName(str);
        getIdentitySharedPreferencesEditor().putString(Constants.FIRST_NAME_PREF, this.commsIdentity.getFirstName()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setHasDevices(boolean z) {
        this.commsIdentity.setHasDevices(z);
        getIdentitySharedPreferencesEditor().putBoolean(Constants.HAS_DEVICES_PREF, this.commsIdentity.hasDevices()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setHashedCommsId(String str) {
        this.commsIdentity.setHashedCommsId(str);
        getIdentitySharedPreferencesEditor().putString(Constants.HASHED_COMMS_ID_PREF, this.commsIdentity.getHashedCommsId()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setHomeGroupId(String str, boolean z) {
        this.commsIdentity.setHomeGroupId(str);
        getIdentitySharedPreferencesEditor().putString(Constants.HOMEGROUP_ID_PREF, this.commsIdentity.getHomeGroupId()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setLastName(String str) {
        this.commsIdentity.setLastName(str);
        getIdentitySharedPreferencesEditor().putString(Constants.LAST_NAME_PREF, this.commsIdentity.getLastName()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setName(String str, String str2) {
        this.commsIdentity.setName(str, str2);
        getIdentitySharedPreferencesEditor().putString(Constants.FIRST_NAME_PREF, this.commsIdentity.getFirstName()).putString(Constants.LAST_NAME_PREF, this.commsIdentity.getLastName()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setPhoneNumber(String str) {
        this.commsIdentity.setPhoneNumber(str);
        getIdentitySharedPreferencesEditor().putString(Constants.PHONE_NUMBER_PREF, this.commsIdentity.getPhoneNumber()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setPhoneticFirstName(String str) {
        this.commsIdentity.setPhoneticFirstName(str);
        getIdentitySharedPreferencesEditor().putString(Constants.PHONETIC_FIRST_NAME_PREF, this.commsIdentity.getPhoneticFirstName()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setPhoneticLastName(String str) {
        this.commsIdentity.setPhoneticLastName(str);
        getIdentitySharedPreferencesEditor().putString(Constants.PHONETIC_LAST_NAME_PREF, this.commsIdentity.getPhoneticLastName()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setProvisionStatus(@Nullable CommsProvisionStatus commsProvisionStatus, boolean z) {
        if (commsProvisionStatus == null) {
            commsProvisionStatus = CommsProvisionStatus.UNKNOWN;
        }
        CommsDaggerWrapper.getComponent().getOOBEPersonManager().setProvisionStatusForOOBEUser(commsProvisionStatus);
        this.commsIdentity.setProvisioningStatus(commsProvisionStatus, "", true);
        getIdentitySharedPreferencesEditor().putString(Constants.PROVISION_STATUS_PREF, this.commsIdentity.getProvisioningStatus().toString()).apply();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setUserPFMInfo(MarketplaceInfo marketplaceInfo) {
        this.commsIdentity.setUserPFMInfo(marketplaceInfo);
        getIdentitySharedPreferencesEditor().putString(Constants.MARKETPLACE_ID_PREF, this.commsIdentity.getUserPFMInfo().getMarketplaceId()).putString(Constants.COUNTRY_CODE_PREF, this.commsIdentity.getUserPFMInfo().getMarketplaceIdCode()).apply();
    }

    CommsIdentityStoreV2Impl(@NonNull Context context, @NonNull CommsIdentity commsIdentity) {
        this.context = context;
        this.commsIdentity = commsIdentity;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setName(String str, String str2, String str3, String str4) {
        this.commsIdentity.setName(str, str2, str3, str4);
        getIdentitySharedPreferencesEditor().putString(Constants.FIRST_NAME_PREF, this.commsIdentity.getFirstName()).putString(Constants.LAST_NAME_PREF, this.commsIdentity.getLastName()).putString(Constants.PHONETIC_FIRST_NAME_PREF, this.commsIdentity.getPhoneticFirstName()).putString(Constants.PHONETIC_LAST_NAME_PREF, this.commsIdentity.getPhoneticLastName()).apply();
    }
}
