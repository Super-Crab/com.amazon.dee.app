package com.amazon.deecomms.identity;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.api.CommsIdentity;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.util.Consumer;
import com.android.tools.r8.GeneratedOutlineSupport;
/* loaded from: classes12.dex */
public class CommsIdentityStore {
    private static final String LOG_TAG = "CommsIdentityStore";
    @NonNull
    private final CommsIdentity activeCommsIdentity = new CommsIdentity();
    @NonNull
    private final Context context;

    public CommsIdentityStore(@NonNull Context context) {
        this.context = context;
    }

    @Nullable
    private String getProvisionStatusName(@Nullable String str) {
        return Utils.getStringPreferenceFromSharedPrefs(this.context, Constants.OOBE_USER_PROVISION_STATUS, str);
    }

    private SharedPreferences.Editor getSharedPrefEditor() {
        return this.context.getSharedPreferences("SHARED_PREFS", 0).edit();
    }

    private synchronized void persistActiveIdentity() {
        SharedPreferences.Editor sharedPrefEditor = getSharedPrefEditor();
        sharedPrefEditor.putString(Constants.DIRECTED_ID_PREF, this.activeCommsIdentity.getDirectedId());
        sharedPrefEditor.putString(Constants.COMMS_ID_PREF, this.activeCommsIdentity.getCommsId());
        sharedPrefEditor.putString(Constants.HASHED_COMMS_ID_PREF, this.activeCommsIdentity.getHashedCommsId());
        sharedPrefEditor.putString(Constants.USER_AOR_PREF, this.activeCommsIdentity.getAor());
        sharedPrefEditor.putString(Constants.FIRST_NAME_PREF, this.activeCommsIdentity.getFirstName());
        sharedPrefEditor.putString(Constants.LAST_NAME_PREF, this.activeCommsIdentity.getLastName());
        sharedPrefEditor.putString(Constants.PHONETIC_FIRST_NAME_PREF, this.activeCommsIdentity.getPhoneticFirstName());
        sharedPrefEditor.putString(Constants.PHONETIC_LAST_NAME_PREF, this.activeCommsIdentity.getPhoneticLastName());
        sharedPrefEditor.putString(Constants.PHONE_NUMBER_PREF, this.activeCommsIdentity.getPhoneNumber());
        sharedPrefEditor.putString(Constants.HOMEGROUP_ID_PREF, this.activeCommsIdentity.getHomeGroupId());
        sharedPrefEditor.putString(Constants.EMAIL_PREF, this.activeCommsIdentity.getEmail());
        sharedPrefEditor.putBoolean(Constants.HAS_DEVICES_PREF, this.activeCommsIdentity.hasDevices());
        sharedPrefEditor.apply();
        CommsProvisionStatus provisioningStatus = this.activeCommsIdentity.getProvisioningStatus();
        if (provisioningStatus == null) {
            provisioningStatus = CommsProvisionStatus.UNKNOWN;
        }
        persistProvisionStatus(provisioningStatus);
    }

    private void setIfNotEmpty(String str, Consumer<String> consumer) {
        if (!TextUtils.isEmpty(str)) {
            consumer.accept(str);
        }
    }

    void assertIdentityCompleteness(CommsIdentity commsIdentity) throws InvalidCommsIdentityException {
        if (commsIdentity != null) {
            if (!TextUtils.isEmpty(commsIdentity.getDirectedId())) {
                CommsProvisionStatus provisioningStatus = commsIdentity.getProvisioningStatus();
                if (CommsProvisionStatus.PROVISIONED.equals(provisioningStatus) && TextUtils.isEmpty(commsIdentity.getHomeGroupId())) {
                    throw new InvalidCommsIdentityException("CommsIdentity incomplete: homegroupId is null and user marked as provisioned.");
                }
                if (CommsProvisionStatus.DEPROVISIONED.equals(provisioningStatus) || !TextUtils.isEmpty(commsIdentity.getCommsId())) {
                    return;
                }
                StringBuilder outline1 = GeneratedOutlineSupport.outline1("CommsIdentity incomplete: commsId is null for use with provision status: ");
                outline1.append(provisioningStatus == null ? null : provisioningStatus.toString());
                throw new InvalidCommsIdentityException(outline1.toString());
            }
            throw new InvalidCommsIdentityException("CommsIdentity incomplete: directedId is null.");
        }
        throw new InvalidCommsIdentityException("CommsIdentity incomplete: commsIdentity is null.");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void clearCachedIdentity() {
        this.activeCommsIdentity.clear();
    }

    @NonNull
    @VisibleForTesting
    CommsIdentity fetchFromSharedPrefs(@NonNull final String str) {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("SHARED_PREFS", 0);
        String string = sharedPreferences.getString(Constants.DIRECTED_ID_PREF, "");
        String string2 = sharedPreferences.getString(Constants.COMMS_ID_PREF, "");
        String string3 = sharedPreferences.getString(Constants.HASHED_COMMS_ID_PREF, "");
        String string4 = sharedPreferences.getString(Constants.FIRST_NAME_PREF, "");
        String string5 = sharedPreferences.getString(Constants.LAST_NAME_PREF, "");
        String string6 = sharedPreferences.getString(Constants.PHONETIC_FIRST_NAME_PREF, "");
        String string7 = sharedPreferences.getString(Constants.PHONETIC_LAST_NAME_PREF, "");
        String string8 = sharedPreferences.getString(Constants.PHONE_NUMBER_PREF, "");
        String string9 = sharedPreferences.getString(Constants.HOMEGROUP_ID_PREF, "");
        String string10 = sharedPreferences.getString(Constants.EMAIL_PREF, "");
        String string11 = sharedPreferences.getString(Constants.USER_AOR_PREF, "");
        boolean z = sharedPreferences.getBoolean(Constants.HAS_DEVICES_PREF, false);
        CommsProvisionStatus valueOf = CommsProvisionStatus.valueOf(sharedPreferences.getString(Constants.OOBE_COMMS_PROVISION_STATUS, CommsProvisionStatus.UNKNOWN.name()));
        final CommsIdentity commsIdentity = new CommsIdentity();
        commsIdentity.setProvisioningStatus(CommsProvisionStatus.UNKNOWN, "fetchFromSharedPrefs", true);
        setIfNotEmpty(string, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$CommsIdentityStore$XToxeOT4YnYgRj97bY03TIJNrXU
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                CommsIdentity.this.setDirectedId((String) obj, str, true);
            }
        });
        setIfNotEmpty(string2, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$CommsIdentityStore$4wPOB3UcHiERNUFUvbdnRf2einU
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                CommsIdentity.this.setCommsId((String) obj, str, true);
            }
        });
        setIfNotEmpty(string3, new $$Lambda$M2TuQPHN9zoCr51xMbGI9HnVJUU(commsIdentity));
        setIfNotEmpty(string11, new $$Lambda$nD6kWY_ukQV9ylfYSDmusXI65TM(commsIdentity));
        setIfNotEmpty(string4, new $$Lambda$Yd04KItImoyEC3mlwaUHwcyyFU(commsIdentity));
        setIfNotEmpty(string5, new $$Lambda$1iWDTNsLuZr7v0u0h9Lfg2nY8s(commsIdentity));
        setIfNotEmpty(string6, new $$Lambda$0NsTtpHwg1h1f5Sz_zGCrzRP9BM(commsIdentity));
        setIfNotEmpty(string7, new $$Lambda$EeAizuMt7QIdaFFaWowt07aeY(commsIdentity));
        setIfNotEmpty(string8, new $$Lambda$UhUgJhG1xCQHgVtQ4HhV6dbW58(commsIdentity));
        setIfNotEmpty(string9, new $$Lambda$vOOHzck_4QnI4arR2U1CRw_mJkw(commsIdentity));
        setIfNotEmpty(string10, new $$Lambda$KLmhHz65kgZlbmSXByxkM3NzDT8(commsIdentity));
        if (!valueOf.equals(commsIdentity.getProvisioningStatus())) {
            commsIdentity.setProvisioningStatus(valueOf, str, true);
        }
        commsIdentity.setHasDevices(z);
        try {
            assertIdentityCompleteness(commsIdentity);
        } catch (InvalidCommsIdentityException unused) {
            Log.w(LOG_TAG, "Complete CommsIdentity was not found in shared prefs. populating from OOBE shared prefs");
            Person oOBEPersonFromLegacySharedPreferences = CommsDaggerWrapper.getComponent().getOOBEPersonManager().getOOBEPersonFromLegacySharedPreferences();
            setIfNotEmpty(oOBEPersonFromLegacySharedPreferences.directedId, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$CommsIdentityStore$wnFtc8EE2hMkoufTvL0GyLbEj3Q
                @Override // com.amazon.deecomms.util.Consumer
                public final void accept(Object obj) {
                    CommsIdentity.this.setDirectedId((String) obj, str, true);
                }
            });
            setIfNotEmpty(oOBEPersonFromLegacySharedPreferences.commsId, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$CommsIdentityStore$5MsobLffXECMWAt6hgag_RaEQJc
                @Override // com.amazon.deecomms.util.Consumer
                public final void accept(Object obj) {
                    CommsIdentity.this.setCommsId((String) obj, str, true);
                }
            });
            setIfNotEmpty(oOBEPersonFromLegacySharedPreferences.hashedCommsId, new $$Lambda$M2TuQPHN9zoCr51xMbGI9HnVJUU(commsIdentity));
            setIfNotEmpty(oOBEPersonFromLegacySharedPreferences.aor, new $$Lambda$nD6kWY_ukQV9ylfYSDmusXI65TM(commsIdentity));
            setIfNotEmpty(oOBEPersonFromLegacySharedPreferences.firstName, new $$Lambda$Yd04KItImoyEC3mlwaUHwcyyFU(commsIdentity));
            setIfNotEmpty(oOBEPersonFromLegacySharedPreferences.lastName, new $$Lambda$1iWDTNsLuZr7v0u0h9Lfg2nY8s(commsIdentity));
            setIfNotEmpty(oOBEPersonFromLegacySharedPreferences.phoneticFirstName, new $$Lambda$0NsTtpHwg1h1f5Sz_zGCrzRP9BM(commsIdentity));
            setIfNotEmpty(oOBEPersonFromLegacySharedPreferences.phoneticLastName, new $$Lambda$EeAizuMt7QIdaFFaWowt07aeY(commsIdentity));
            setIfNotEmpty(oOBEPersonFromLegacySharedPreferences.phoneNumber, new $$Lambda$UhUgJhG1xCQHgVtQ4HhV6dbW58(commsIdentity));
            setIfNotEmpty(oOBEPersonFromLegacySharedPreferences.homeGroupId, new $$Lambda$vOOHzck_4QnI4arR2U1CRw_mJkw(commsIdentity));
            if (!valueOf.equals(commsIdentity.getProvisioningStatus())) {
                commsIdentity.setProvisioningStatus(valueOf, str, true);
            }
        }
        return commsIdentity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public synchronized CommsIdentity getActiveCommsIdentity(@NonNull String str) {
        if (this.activeCommsIdentity.isEmpty()) {
            this.activeCommsIdentity.copyFrom(fetchFromSharedPrefs(str));
        }
        return this.activeCommsIdentity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getDirectedId(@NonNull String str) {
        String str2;
        CommsIdentity activeCommsIdentity = getActiveCommsIdentity(GeneratedOutlineSupport.outline0("getDirectedId", str));
        if (activeCommsIdentity.isPopulated()) {
            str2 = activeCommsIdentity.getDirectedId();
        } else {
            str2 = CommsDaggerWrapper.getComponent().getOOBEPersonManager().getOOBEPersonFromLegacySharedPreferences().directedId;
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getFirstName() {
        String str;
        CommsIdentity activeCommsIdentity = getActiveCommsIdentity("getFirstName");
        if (activeCommsIdentity.isPopulated()) {
            str = activeCommsIdentity.getFirstName();
        } else {
            str = CommsDaggerWrapper.getComponent().getOOBEPersonManager().getOOBEPersonFromLegacySharedPreferences().firstName;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getLastName() {
        String str;
        CommsIdentity activeCommsIdentity = getActiveCommsIdentity("getLastName");
        if (activeCommsIdentity.isPopulated()) {
            str = activeCommsIdentity.getLastName();
        } else {
            str = CommsDaggerWrapper.getComponent().getOOBEPersonManager().getOOBEPersonFromLegacySharedPreferences().lastName;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getProfileName(@NonNull String str) {
        return getProfileName(getActiveCommsIdentity(GeneratedOutlineSupport.outline0("getProfileName ", str)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public CommsProvisionStatus getProvisionStatus() {
        String provisionStatusName = getProvisionStatusName(null);
        if (provisionStatusName == null) {
            return CommsProvisionStatus.UNKNOWN;
        }
        CommsProvisionStatus valueOf = CommsProvisionStatus.valueOf(provisionStatusName);
        return valueOf == null ? CommsProvisionStatus.UNKNOWN : valueOf;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void persistHomegroupId(String str) {
        getActiveCommsIdentity("persistHomeGroupId").setHomeGroupId(str);
        getSharedPrefEditor().putString(Constants.HOMEGROUP_ID_PREF, str).apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void persistProvisionStatus(@NonNull CommsProvisionStatus commsProvisionStatus) {
        getSharedPrefEditor().putString(Constants.OOBE_USER_PROVISION_STATUS, commsProvisionStatus.name()).apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void persistUserAor(String str) {
        getActiveCommsIdentity("persistUserAor").setAor(str);
        getSharedPrefEditor().putString(Constants.OOBE_USER_AOR, str).apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public synchronized void setActiveIdentity(CommsIdentity commsIdentity) throws InvalidCommsIdentityException {
        assertIdentityCompleteness(commsIdentity);
        this.activeCommsIdentity.copyFrom(commsIdentity);
        CommsDaggerWrapper.getComponent().getApplicationManager().setUserName(getProfileName(this.activeCommsIdentity));
        persistActiveIdentity();
    }

    @Nullable
    private String getProfileName(CommsIdentity commsIdentity) {
        String safeNameFormat;
        if (commsIdentity == null) {
            return null;
        }
        if (commsIdentity.isPopulated()) {
            safeNameFormat = CommsIdentityUtils.safeNameFormat(commsIdentity.getFirstName(), commsIdentity.getLastName());
        } else {
            Person oOBEPersonFromLegacySharedPreferences = CommsDaggerWrapper.getComponent().getOOBEPersonManager().getOOBEPersonFromLegacySharedPreferences();
            safeNameFormat = CommsIdentityUtils.safeNameFormat(oOBEPersonFromLegacySharedPreferences.firstName, oOBEPersonFromLegacySharedPreferences.lastName);
        }
        if (!TextUtils.isEmpty(safeNameFormat)) {
            return safeNameFormat;
        }
        return null;
    }
}
