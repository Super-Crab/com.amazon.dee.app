package com.amazon.deecomms.identity;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentity;
import com.amazon.deecomms.api.CommsIdentityStoreV2;
import com.amazon.deecomms.api.MarketplaceInfo;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class MigrationCommsIdentityStore implements CommsIdentityStoreV2 {
    private static final String CLASS_NAME = "com.amazon.deecomms.identity.MigrationCommsIdentityStore";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsIdentityStoreV2.class);
    @Inject
    Context context;
    private final CommsIdentity currentCommsIdentity;
    @Inject
    CommsIdentityStoreV2Impl mCommsIdentityStoreV2Impl;
    @Inject
    CommsIdentityStore mLegacyCommsIdentityStore;

    public MigrationCommsIdentityStore() {
        CommsDaggerWrapper.getComponent().inject(this);
        this.currentCommsIdentity = this.mLegacyCommsIdentityStore.getActiveCommsIdentity(CLASS_NAME);
    }

    private String getIdentityStoreVersion() {
        return "[V2]";
    }

    private void recordMetricIfNeeded(String str, String str2, String str3, boolean z) {
        boolean isNullOrEmpty = Utils.isNullOrEmpty(str);
        boolean isNullOrEmpty2 = Utils.isNullOrEmpty(str2);
        boolean z2 = true;
        boolean z3 = isNullOrEmpty != isNullOrEmpty2;
        if (!(!isNullOrEmpty && !isNullOrEmpty2) || str.equals(str2)) {
            z2 = false;
        }
        if (z3 && isNullOrEmpty) {
            GeneratedOutlineSupport.outline4(str3, " is empty from legacy store", LOG);
            if (z) {
                return;
            }
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.empty." + str3);
        } else if (z3) {
            GeneratedOutlineSupport.outline4(str3, " is empty from new store", LOG);
            if (z) {
                return;
            }
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.transactional.empty." + str3);
        } else if (!z2) {
        } else {
            CommsLogger commsLogger = LOG;
            StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str3, " values differ - legacy: ");
            outline113.append(LOG.sensitive(str));
            outline113.append(", new: ");
            outline113.append(LOG.sensitive(str2));
            commsLogger.i(outline113.toString());
            if (z) {
                return;
            }
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.values." + str3);
        }
    }

    private boolean useIdentityStoreV2() {
        return true;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void clearCommsIdentity(boolean z) {
        this.mCommsIdentityStoreV2Impl.clearCommsIdentity(z);
        this.mLegacyCommsIdentityStore.clearCachedIdentity();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getAor() {
        String aor;
        aor = this.mLegacyCommsIdentityStore.getActiveCommsIdentity(CLASS_NAME).getAor();
        String aor2 = this.mCommsIdentityStoreV2Impl.getAor();
        recordMetricIfNeeded(aor, aor2, ContactProviderContract.PhoneNumberEntry.COLUMN_AOR);
        if (!Utils.isNullOrEmpty(aor2) || Utils.isNullOrEmpty(aor)) {
            aor = aor2;
        } else {
            this.mCommsIdentityStoreV2Impl.setAor(aor, false);
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.aor");
        }
        return aor;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getCommsId(boolean z) {
        String commsId;
        CommsLogger commsLogger = LOG;
        commsLogger.i("[V2] getCommsId");
        commsId = this.mLegacyCommsIdentityStore.getActiveCommsIdentity(CLASS_NAME).getCommsId();
        String commsId2 = this.mCommsIdentityStoreV2Impl.getCommsId(z);
        recordMetricIfNeeded(commsId, commsId2, "commsId", z);
        if (!Utils.isNullOrEmpty(commsId2) || Utils.isNullOrEmpty(commsId)) {
            commsId = commsId2;
        } else {
            this.mCommsIdentityStoreV2Impl.setCommsId(commsId);
            LOG.i("copying commsId from legacy to new store");
            if (!z) {
                MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.commsId");
            }
        }
        return commsId;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getCountryOfResidence() {
        String countryOfResidence;
        countryOfResidence = this.mLegacyCommsIdentityStore.getActiveCommsIdentity(CLASS_NAME).getCountryOfResidence();
        String countryOfResidence2 = this.mCommsIdentityStoreV2Impl.getCountryOfResidence();
        recordMetricIfNeeded(countryOfResidence, countryOfResidence2, "cor");
        if (!Utils.isNullOrEmpty(countryOfResidence2) || Utils.isNullOrEmpty(countryOfResidence)) {
            countryOfResidence = countryOfResidence2;
        } else {
            this.mCommsIdentityStoreV2Impl.setCountryOfResidence(countryOfResidence);
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.cor");
        }
        return countryOfResidence;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized CommsIdentity getCurrentCommsIdentity() {
        return this.mCommsIdentityStoreV2Impl.getCurrentCommsIdentity();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getDirectedId() {
        String directedId;
        CommsLogger commsLogger = LOG;
        commsLogger.i("[V2] getDirectedId");
        directedId = this.mLegacyCommsIdentityStore.getDirectedId(CLASS_NAME);
        String directedId2 = this.mCommsIdentityStoreV2Impl.getDirectedId();
        recordMetricIfNeeded(directedId, directedId2, "directedId");
        if (!Utils.isNullOrEmpty(directedId2) || Utils.isNullOrEmpty(directedId)) {
            directedId = directedId2;
        } else {
            this.mCommsIdentityStoreV2Impl.setDirectedId(directedId);
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.directedId");
        }
        return directedId;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getEmail() {
        String email;
        email = this.mLegacyCommsIdentityStore.getActiveCommsIdentity(CLASS_NAME).getEmail();
        String email2 = this.mCommsIdentityStoreV2Impl.getEmail();
        recordMetricIfNeeded(email, email2, "email");
        if (!Utils.isNullOrEmpty(email2) || Utils.isNullOrEmpty(email)) {
            email = email2;
        } else {
            this.mCommsIdentityStoreV2Impl.setEmail(email);
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.email");
        }
        return email;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getFirstName() {
        String firstName;
        firstName = this.mLegacyCommsIdentityStore.getFirstName();
        String firstName2 = this.mCommsIdentityStoreV2Impl.getFirstName();
        recordMetricIfNeeded(firstName, firstName2, "firstName");
        if (!Utils.isNullOrEmpty(firstName2) || Utils.isNullOrEmpty(firstName)) {
            firstName = firstName2;
        } else {
            this.mCommsIdentityStoreV2Impl.setFirstName(firstName);
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.firstName");
        }
        return firstName;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getHashedCommsId() {
        String hashedCommsId;
        hashedCommsId = this.mLegacyCommsIdentityStore.getActiveCommsIdentity(CLASS_NAME).getHashedCommsId();
        String hashedCommsId2 = this.mCommsIdentityStoreV2Impl.getHashedCommsId();
        recordMetricIfNeeded(hashedCommsId, hashedCommsId2, MetricKeys.META_HASHED_COMMS_ID);
        if (!Utils.isNullOrEmpty(hashedCommsId2) || Utils.isNullOrEmpty(hashedCommsId)) {
            hashedCommsId = hashedCommsId2;
        } else {
            this.mCommsIdentityStoreV2Impl.setHashedCommsId(hashedCommsId);
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.hashedCommsId");
        }
        return hashedCommsId;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getHomeGroupId() {
        String homeGroupId;
        CommsLogger commsLogger = LOG;
        commsLogger.i("[V2] getHomeGroupId");
        homeGroupId = this.mLegacyCommsIdentityStore.getActiveCommsIdentity(CLASS_NAME).getHomeGroupId();
        String homeGroupId2 = this.mCommsIdentityStoreV2Impl.getHomeGroupId();
        recordMetricIfNeeded(homeGroupId, homeGroupId2, "homegroup");
        if (!Utils.isNullOrEmpty(homeGroupId2) || Utils.isNullOrEmpty(homeGroupId)) {
            homeGroupId = homeGroupId2;
        } else {
            this.mCommsIdentityStoreV2Impl.setHomeGroupId(homeGroupId, false);
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.homegroup");
        }
        return homeGroupId;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getLastName() {
        String lastName;
        lastName = this.mLegacyCommsIdentityStore.getLastName();
        String lastName2 = this.mCommsIdentityStoreV2Impl.getLastName();
        recordMetricIfNeeded(lastName, lastName2, "lastName");
        if (!Utils.isNullOrEmpty(lastName2) || Utils.isNullOrEmpty(lastName)) {
            lastName = lastName2;
        } else {
            this.mCommsIdentityStoreV2Impl.setLastName(lastName);
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.lastName");
        }
        return lastName;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getPhoneNumber() {
        String phoneNumber;
        phoneNumber = this.mLegacyCommsIdentityStore.getActiveCommsIdentity(CLASS_NAME).getPhoneNumber();
        String phoneNumber2 = this.mCommsIdentityStoreV2Impl.getPhoneNumber();
        recordMetricIfNeeded(phoneNumber, phoneNumber2, "phoneNumber");
        if (!Utils.isNullOrEmpty(phoneNumber2) || Utils.isNullOrEmpty(phoneNumber)) {
            phoneNumber = phoneNumber2;
        } else {
            this.mCommsIdentityStoreV2Impl.setPhoneNumber(phoneNumber);
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.phoneNumber");
        }
        return phoneNumber;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getPhoneticFirstName() {
        String phoneticFirstName;
        phoneticFirstName = this.mLegacyCommsIdentityStore.getActiveCommsIdentity(CLASS_NAME).getPhoneticFirstName();
        String phoneticFirstName2 = this.mCommsIdentityStoreV2Impl.getPhoneticFirstName();
        recordMetricIfNeeded(phoneticFirstName, phoneticFirstName2, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_FIRST_NAME);
        if (!Utils.isNullOrEmpty(phoneticFirstName2) || Utils.isNullOrEmpty(phoneticFirstName)) {
            phoneticFirstName = phoneticFirstName2;
        } else {
            this.mCommsIdentityStoreV2Impl.setPhoneticFirstName(phoneticFirstName);
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.phoneticFirstName");
        }
        return phoneticFirstName;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getPhoneticLastName() {
        String phoneticLastName;
        phoneticLastName = this.mLegacyCommsIdentityStore.getActiveCommsIdentity(CLASS_NAME).getPhoneticLastName();
        String phoneticLastName2 = this.mCommsIdentityStoreV2Impl.getPhoneticLastName();
        recordMetricIfNeeded(phoneticLastName, phoneticLastName2, ContactProviderContract.ContactDatabaseEntry.COLUMN_NAME_PHONETIC_LAST_NAME);
        if (!Utils.isNullOrEmpty(phoneticLastName2) || Utils.isNullOrEmpty(phoneticLastName)) {
            phoneticLastName = phoneticLastName2;
        } else {
            this.mCommsIdentityStoreV2Impl.setPhoneticFirstName(phoneticLastName);
            MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.phoneticLastName");
        }
        return phoneticLastName;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized String getProfileName() {
        String safeNameFormat;
        String profileName = this.mLegacyCommsIdentityStore.getProfileName(CLASS_NAME);
        safeNameFormat = CommsIdentityUtils.safeNameFormat(getFirstName(), getLastName());
        recordMetricIfNeeded(profileName, safeNameFormat, "profileName");
        return safeNameFormat;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005c A[Catch: all -> 0x0070, TryCatch #0 {, blocks: (B:3:0x0001, B:5:0x001b, B:7:0x002e, B:9:0x0036, B:12:0x003b, B:15:0x0044, B:18:0x0049, B:20:0x0050, B:22:0x005c, B:24:0x0062, B:6:0x0022), top: B:31:0x0001 }] */
    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public synchronized com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus getProvisionStatus(boolean r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            com.amazon.comms.log.CommsLogger r0 = com.amazon.deecomms.identity.MigrationCommsIdentityStore.LOG     // Catch: java.lang.Throwable -> L70
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L70
            r1.<init>()     // Catch: java.lang.Throwable -> L70
            java.lang.String r2 = "[V2]"
            r1.append(r2)     // Catch: java.lang.Throwable -> L70
            java.lang.String r2 = " getProvisionStatus"
            r1.append(r2)     // Catch: java.lang.Throwable -> L70
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L70
            r0.i(r1)     // Catch: java.lang.Throwable -> L70
            if (r5 == 0) goto L22
            com.amazon.deecomms.identity.CommsIdentityStore r0 = r4.mLegacyCommsIdentityStore     // Catch: java.lang.Throwable -> L70
            com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r0 = r0.getProvisionStatus()     // Catch: java.lang.Throwable -> L70
            goto L2e
        L22:
            com.amazon.deecomms.identity.CommsIdentityStore r0 = r4.mLegacyCommsIdentityStore     // Catch: java.lang.Throwable -> L70
            java.lang.String r1 = com.amazon.deecomms.identity.MigrationCommsIdentityStore.CLASS_NAME     // Catch: java.lang.Throwable -> L70
            com.amazon.deecomms.api.CommsIdentity r0 = r0.getActiveCommsIdentity(r1)     // Catch: java.lang.Throwable -> L70
            com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r0 = r0.getProvisioningStatus()     // Catch: java.lang.Throwable -> L70
        L2e:
            com.amazon.deecomms.identity.CommsIdentityStoreV2Impl r1 = r4.mCommsIdentityStoreV2Impl     // Catch: java.lang.Throwable -> L70
            com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r5 = r1.getProvisionStatus(r5)     // Catch: java.lang.Throwable -> L70
            if (r0 == 0) goto L40
            com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r1 = com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus.UNKNOWN     // Catch: java.lang.Throwable -> L70
            if (r0 != r1) goto L3b
            goto L40
        L3b:
            java.lang.String r1 = r0.toString()     // Catch: java.lang.Throwable -> L70
            goto L42
        L40:
            java.lang.String r1 = ""
        L42:
            if (r5 == 0) goto L4e
            com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus r2 = com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus.UNKNOWN     // Catch: java.lang.Throwable -> L70
            if (r5 != r2) goto L49
            goto L4e
        L49:
            java.lang.String r2 = r5.toString()     // Catch: java.lang.Throwable -> L70
            goto L50
        L4e:
            java.lang.String r2 = ""
        L50:
            java.lang.String r3 = "provisioningStatus"
            r4.recordMetricIfNeeded(r1, r2, r3)     // Catch: java.lang.Throwable -> L70
            boolean r2 = com.amazon.deecomms.common.util.Utils.isNullOrEmpty(r2)     // Catch: java.lang.Throwable -> L70
            if (r2 == 0) goto L6e
            boolean r1 = com.amazon.deecomms.common.util.Utils.isNullOrEmpty(r1)     // Catch: java.lang.Throwable -> L70
            if (r1 != 0) goto L6e
            com.amazon.deecomms.identity.CommsIdentityStoreV2Impl r5 = r4.mCommsIdentityStoreV2Impl     // Catch: java.lang.Throwable -> L70
            r1 = 1
            r5.setProvisionStatus(r0, r1)     // Catch: java.lang.Throwable -> L70
            java.lang.String r5 = "comms.identity.migration.diff.legacy.set.provisioningStatus"
            com.amazon.deecomms.common.metrics.MetricsHelper.recordSingleOccurrenceOperational(r5)     // Catch: java.lang.Throwable -> L70
            r5 = r0
        L6e:
            monitor-exit(r4)
            return r5
        L70:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.deecomms.identity.MigrationCommsIdentityStore.getProvisionStatus(boolean):com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus");
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized MarketplaceInfo getUserPFMInfo(boolean z) {
        MarketplaceInfo userPFMInfo;
        userPFMInfo = this.mLegacyCommsIdentityStore.getActiveCommsIdentity(CLASS_NAME).getUserPFMInfo();
        MarketplaceInfo userPFMInfo2 = this.mCommsIdentityStoreV2Impl.getUserPFMInfo(z);
        String pfmCode = userPFMInfo == null ? "" : userPFMInfo.getPfmCode();
        String pfmCode2 = userPFMInfo2 == null ? "" : userPFMInfo2.getPfmCode();
        recordMetricIfNeeded(pfmCode, pfmCode2, "pfm", z);
        if (!Utils.isNullOrEmpty(pfmCode2) || Utils.isNullOrEmpty(pfmCode)) {
            userPFMInfo = userPFMInfo2;
        } else {
            LOG.i("copying pfm from legacy to new store");
            this.mCommsIdentityStoreV2Impl.setUserPFMInfo(userPFMInfo);
            if (!z) {
                MetricsHelper.recordSingleOccurrenceOperational("comms.identity.migration.diff.legacy.set.pfm");
            }
        }
        return userPFMInfo;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized boolean hasDevices() {
        return this.mCommsIdentityStoreV2Impl.hasDevices();
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void legacyPersistCurrentIdentity() throws InvalidCommsIdentityException {
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void populateCommsIdentity(@NonNull CommsIdentity commsIdentity) throws InvalidCommsIdentityException {
        CommsLogger commsLogger = LOG;
        commsLogger.i("[V2] populateCommsIdentity");
        this.mCommsIdentityStoreV2Impl.populateCommsIdentity(commsIdentity);
        this.mLegacyCommsIdentityStore.setActiveIdentity(commsIdentity);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setAor(String str, boolean z) {
        this.mCommsIdentityStoreV2Impl.setAor(str, z);
        if (z) {
            this.mLegacyCommsIdentityStore.persistUserAor(str);
        } else {
            this.currentCommsIdentity.setAor(str);
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setCommsId(String str) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("[V2] setCommsId");
        this.mCommsIdentityStoreV2Impl.setCommsId(str);
        this.currentCommsIdentity.setCommsId(str, CLASS_NAME, true);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setCountryOfResidence(String str) {
        this.mCommsIdentityStoreV2Impl.setCountryOfResidence(str);
        this.currentCommsIdentity.setCountryOfResidence(str);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setDirectedId(String str) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("[V2] setDirectedId");
        this.mCommsIdentityStoreV2Impl.setDirectedId(str);
        this.currentCommsIdentity.setDirectedId(str, CLASS_NAME, true);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setEmail(String str) {
        this.mCommsIdentityStoreV2Impl.setEmail(str);
        this.currentCommsIdentity.setEmail(str);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setFirstName(String str) {
        this.mCommsIdentityStoreV2Impl.setFirstName(str);
        this.currentCommsIdentity.setFirstName(str);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setHasDevices(boolean z) {
        this.mCommsIdentityStoreV2Impl.setHasDevices(z);
        this.currentCommsIdentity.setHasDevices(z);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setHashedCommsId(String str) {
        this.mCommsIdentityStoreV2Impl.setHashedCommsId(str);
        this.currentCommsIdentity.setHashedCommsId(str);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setHomeGroupId(String str, boolean z) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("[V2] setHomeGroupId");
        this.mCommsIdentityStoreV2Impl.setHomeGroupId(str, z);
        if (z) {
            this.mLegacyCommsIdentityStore.persistHomegroupId(str);
        } else {
            this.currentCommsIdentity.setHomeGroupId(str);
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setLastName(String str) {
        this.mCommsIdentityStoreV2Impl.setLastName(str);
        this.currentCommsIdentity.setLastName(str);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setName(String str, String str2) {
        this.mCommsIdentityStoreV2Impl.setName(str, str2);
        this.currentCommsIdentity.setName(str, str2);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setPhoneNumber(String str) {
        this.mCommsIdentityStoreV2Impl.setPhoneNumber(str);
        this.currentCommsIdentity.setPhoneNumber(str);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setPhoneticFirstName(String str) {
        this.mCommsIdentityStoreV2Impl.setPhoneticFirstName(str);
        this.currentCommsIdentity.setPhoneticFirstName(str);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setPhoneticLastName(String str) {
        this.mCommsIdentityStoreV2Impl.setPhoneticLastName(str);
        this.currentCommsIdentity.setPhoneticLastName(str);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setProvisionStatus(@Nullable CommsProvisionStatus commsProvisionStatus, boolean z) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("[V2] setProvisionStatus to " + commsProvisionStatus);
        this.mCommsIdentityStoreV2Impl.setProvisionStatus(commsProvisionStatus, z);
        if (z) {
            CommsIdentityStore commsIdentityStore = this.mLegacyCommsIdentityStore;
            if (commsProvisionStatus == null) {
                commsProvisionStatus = CommsProvisionStatus.UNKNOWN;
            }
            commsIdentityStore.persistProvisionStatus(commsProvisionStatus);
        } else {
            this.currentCommsIdentity.setProvisioningStatus(commsProvisionStatus, CLASS_NAME, true);
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setUserPFMInfo(MarketplaceInfo marketplaceInfo) {
        this.mCommsIdentityStoreV2Impl.setUserPFMInfo(marketplaceInfo);
        this.currentCommsIdentity.setUserPFMInfo(marketplaceInfo);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityStoreV2
    public synchronized void setName(String str, String str2, String str3, String str4) {
        this.mCommsIdentityStoreV2Impl.setName(str, str2, str3, str4);
        this.currentCommsIdentity.setName(str, str2, str3, str4);
    }

    private void recordMetricIfNeeded(String str, String str2, String str3) {
        recordMetricIfNeeded(str, str2, str3, false);
    }
}
