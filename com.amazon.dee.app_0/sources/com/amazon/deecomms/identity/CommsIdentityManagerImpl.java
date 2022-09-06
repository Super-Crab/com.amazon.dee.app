package com.amazon.deecomms.identity;

import android.text.TextUtils;
import androidx.annotation.GuardedBy;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.identity.api.CommsProfile;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentity;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.MarketplaceInfo;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.util.Consumer;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.io.Closeable;
import java.util.concurrent.locks.ReentrantLock;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class CommsIdentityManagerImpl implements CommsIdentityManager {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsIdentityManager.class);
    private final ReentrantLock commsIdentityStoreLock = new ReentrantLock();
    @VisibleForTesting
    boolean enableLockingCheck = false;
    @Inject
    Lazy<IdentityService> identityServiceLazy;
    @Inject
    @GuardedBy("commsIdentityStoreLock")
    MigrationCommsIdentityStore migrationCommsIdentityStore;

    public CommsIdentityManagerImpl() {
        CommsDaggerWrapper.getComponent().inject(this);
    }

    private void logAndRecordMetric(@Nullable String str, @NonNull String str2, @NonNull String str3, @NonNull String str4, @NonNull String str5, boolean z) {
        if (this.enableLockingCheck && !z && this.commsIdentityStoreLock.isHeldByCurrentThread()) {
            throw new IllegalStateException("Do not record metrics while holding lock");
        }
        if (!TextUtils.isEmpty(str)) {
            return;
        }
        LOG.w(GeneratedOutlineSupport1.outline77(str2, " is empty in ", str3, " - called from ", str4));
        if (z) {
            return;
        }
        MetricsHelper.recordOperationalMetricWithSource(str5, str4 + ":" + str3);
    }

    private void restoreFromIdentityService() {
        String str = null;
        UserIdentity user = this.identityServiceLazy.mo358get() == null ? null : this.identityServiceLazy.mo358get().getUser(CommsIdentityManager.class.getSimpleName());
        if (user == null) {
            LOG.w("user is null from IdentityService");
            return;
        }
        LOG.i("restoring identity from IdentityService");
        String marketplaceId = user.getOriginalMarketplace() == null ? null : user.getOriginalMarketplace().getObfuscatedId().toString();
        String countryCode = user.getOriginalMarketplace() == null ? null : user.getOriginalMarketplace().getCountryCode().toString();
        if (!TextUtils.isEmpty(marketplaceId) && !TextUtils.isEmpty(countryCode)) {
            Marketplace findMarketplaceById = Marketplace.findMarketplaceById(marketplaceId, null);
            MigrationCommsIdentityStore migrationCommsIdentityStore = this.migrationCommsIdentityStore;
            if (findMarketplaceById != null) {
                str = findMarketplaceById.getMarketplaceName().toString();
            }
            migrationCommsIdentityStore.setUserPFMInfo(new MarketplaceInfo(marketplaceId, countryCode, str, true));
        }
        String countryOfResidence = user.getCountryOfResidence();
        final MigrationCommsIdentityStore migrationCommsIdentityStore2 = this.migrationCommsIdentityStore;
        migrationCommsIdentityStore2.getClass();
        setIfNotEmpty(countryOfResidence, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$8UQ26ylxgS8fiQufFkZKs7WQi_4
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                MigrationCommsIdentityStore.this.setCountryOfResidence((String) obj);
            }
        });
        String directedId = user.getDirectedId();
        final MigrationCommsIdentityStore migrationCommsIdentityStore3 = this.migrationCommsIdentityStore;
        migrationCommsIdentityStore3.getClass();
        setIfNotEmpty(directedId, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$PYfgKodqcgHnd0wjCWxmbKa6y5A
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                MigrationCommsIdentityStore.this.setDirectedId((String) obj);
            }
        });
        UserProfile userProfile = user.getUserProfile();
        if (userProfile != null) {
            LOG.i("restoring user profile from IdentityService");
            String directedId2 = userProfile.getDirectedId();
            final MigrationCommsIdentityStore migrationCommsIdentityStore4 = this.migrationCommsIdentityStore;
            migrationCommsIdentityStore4.getClass();
            setIfNotEmpty(directedId2, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$PYfgKodqcgHnd0wjCWxmbKa6y5A
                @Override // com.amazon.deecomms.util.Consumer
                public final void accept(Object obj) {
                    MigrationCommsIdentityStore.this.setDirectedId((String) obj);
                }
            });
            String firstName = userProfile.getFirstName();
            final MigrationCommsIdentityStore migrationCommsIdentityStore5 = this.migrationCommsIdentityStore;
            migrationCommsIdentityStore5.getClass();
            setIfNotEmpty(firstName, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$Inphc_vSA_zBxTy-B5IN_WqzqNU
                @Override // com.amazon.deecomms.util.Consumer
                public final void accept(Object obj) {
                    MigrationCommsIdentityStore.this.setFirstName((String) obj);
                }
            });
            String lastName = userProfile.getLastName();
            final MigrationCommsIdentityStore migrationCommsIdentityStore6 = this.migrationCommsIdentityStore;
            migrationCommsIdentityStore6.getClass();
            setIfNotEmpty(lastName, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$wI9CV9xAoZR4AzIN6cy3iPMiFow
                @Override // com.amazon.deecomms.util.Consumer
                public final void accept(Object obj) {
                    MigrationCommsIdentityStore.this.setLastName((String) obj);
                }
            });
        }
        if (userProfile == null || userProfile.getCommsProfile() == null) {
            return;
        }
        LOG.i("restoring Comms profile from IdentityService");
        CommsProfile commsProfile = userProfile.getCommsProfile();
        String commsId = commsProfile.getCommsId();
        final MigrationCommsIdentityStore migrationCommsIdentityStore7 = this.migrationCommsIdentityStore;
        migrationCommsIdentityStore7.getClass();
        setIfNotEmpty(commsId, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$z8w4Rqt8iTZy8iHQ10wb79KBQLw
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                MigrationCommsIdentityStore.this.setCommsId((String) obj);
            }
        });
        String hashedCommsId = commsProfile.getHashedCommsId();
        final MigrationCommsIdentityStore migrationCommsIdentityStore8 = this.migrationCommsIdentityStore;
        migrationCommsIdentityStore8.getClass();
        setIfNotEmpty(hashedCommsId, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$wNEV8HnLbcKlaQ7tz_xExEd5330
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                MigrationCommsIdentityStore.this.setHashedCommsId((String) obj);
            }
        });
        setIfNotEmpty(commsProfile.getAor(), new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$CommsIdentityManagerImpl$UPKsVuiTi0Qg5q0MnsSpUeXdHHU
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                CommsIdentityManagerImpl.this.lambda$restoreFromIdentityService$0$CommsIdentityManagerImpl((String) obj);
            }
        });
        setIfNotEmpty(commsProfile.getHouseholdId(), new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$CommsIdentityManagerImpl$-21pGsFH_0RgZE8v4-5T33r1efo
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                CommsIdentityManagerImpl.this.lambda$restoreFromIdentityService$1$CommsIdentityManagerImpl((String) obj);
            }
        });
        String phoneNumber = commsProfile.getPhoneNumber();
        final MigrationCommsIdentityStore migrationCommsIdentityStore9 = this.migrationCommsIdentityStore;
        migrationCommsIdentityStore9.getClass();
        setIfNotEmpty(phoneNumber, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$P9421AWqc777z8afhE_1yfGZYG4
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                MigrationCommsIdentityStore.this.setPhoneNumber((String) obj);
            }
        });
        String email = commsProfile.getEmail();
        final MigrationCommsIdentityStore migrationCommsIdentityStore10 = this.migrationCommsIdentityStore;
        migrationCommsIdentityStore10.getClass();
        setIfNotEmpty(email, new Consumer() { // from class: com.amazon.deecomms.identity.-$$Lambda$OHQcVsMgoqBc5x6ToPzrz57z9LI
            @Override // com.amazon.deecomms.util.Consumer
            public final void accept(Object obj) {
                MigrationCommsIdentityStore.this.setEmail((String) obj);
            }
        });
    }

    private void setIfNotEmpty(String str, Consumer<String> consumer) {
        if (!TextUtils.isEmpty(str)) {
            consumer.accept(str);
        }
    }

    private void setUserNameInMenu(String str) {
        CommsDaggerWrapper.getComponent().getApplicationManager().setUserName(str);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void deprovisionCurrentUser(@NonNull String str, boolean z, @Nullable Person person, boolean z2) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setProvisionStatus(CommsProvisionStatus.DEPROVISIONED, true);
            setCommsId("", str + ":deprovisionCurrentUser", true);
            setHashedCommsId("");
            setHomeGroupId("", true, str + ":deprovisionCurrentUser", true);
            setAor("", false);
            if (z) {
                setPhoneNumber("");
            }
            this.migrationCommsIdentityStore.clearCommsIdentity(false);
            acquiredLock.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public String getAor() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String aor = this.migrationCommsIdentityStore.getAor();
            if (TextUtils.isEmpty(aor) && this.migrationCommsIdentityStore.getProvisionStatus(true) != CommsProvisionStatus.DEPROVISIONED) {
                restoreFromIdentityService();
                aor = this.migrationCommsIdentityStore.getAor();
            }
            acquiredLock.close();
            return aor;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    @NonNull
    public String getCommsId(@NonNull String str, boolean z) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String commsId = this.migrationCommsIdentityStore.getCommsId(z);
            if (TextUtils.isEmpty(commsId) && this.migrationCommsIdentityStore.getProvisionStatus(true) != CommsProvisionStatus.DEPROVISIONED) {
                restoreFromIdentityService();
                commsId = this.migrationCommsIdentityStore.getCommsId(z);
            }
            acquiredLock.close();
            if (commsId == null) {
                commsId = "";
            }
            logAndRecordMetric(commsId, "commsId", "getCommsId", str, MetricKeys.COMMS_ID_GETTER_TO_EMPTY, z);
            return commsId;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public String getCountryOfResidence() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String countryOfResidence = this.migrationCommsIdentityStore.getCountryOfResidence();
            if (TextUtils.isEmpty(countryOfResidence)) {
                restoreFromIdentityService();
                countryOfResidence = this.migrationCommsIdentityStore.getCountryOfResidence();
            }
            acquiredLock.close();
            return countryOfResidence;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public CommsIdentity getCurrentCommsIdentity() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            CommsIdentity currentCommsIdentity = this.migrationCommsIdentityStore.getCurrentCommsIdentity();
            acquiredLock.close();
            return currentCommsIdentity;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    @NonNull
    public String getDirectedId(@NonNull String str, boolean z) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String directedId = this.migrationCommsIdentityStore.getDirectedId();
            if (TextUtils.isEmpty(directedId)) {
                restoreFromIdentityService();
                directedId = this.migrationCommsIdentityStore.getDirectedId();
            }
            acquiredLock.close();
            if (directedId == null) {
                directedId = "";
            }
            logAndRecordMetric(directedId, "directedId", "getDirectedId", str, MetricKeys.DIRECTED_ID_GETTER_TO_EMPTY, z);
            return directedId;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public String getEmail() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String email = this.migrationCommsIdentityStore.getEmail();
            acquiredLock.close();
            return email;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public String getFirstName() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String firstName = this.migrationCommsIdentityStore.getFirstName();
            acquiredLock.close();
            return firstName;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public String getHashedCommsId() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String hashedCommsId = this.migrationCommsIdentityStore.getHashedCommsId();
            if (TextUtils.isEmpty(hashedCommsId) && this.migrationCommsIdentityStore.getProvisionStatus(true) != CommsProvisionStatus.DEPROVISIONED) {
                restoreFromIdentityService();
                hashedCommsId = this.migrationCommsIdentityStore.getHashedCommsId();
            }
            acquiredLock.close();
            return hashedCommsId;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    @NonNull
    public String getHomeGroupId(@NonNull String str, boolean z) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String homeGroupId = this.migrationCommsIdentityStore.getHomeGroupId();
            if (TextUtils.isEmpty(homeGroupId) && this.migrationCommsIdentityStore.getProvisionStatus(true) != CommsProvisionStatus.DEPROVISIONED) {
                restoreFromIdentityService();
                homeGroupId = this.migrationCommsIdentityStore.getHomeGroupId();
            }
            acquiredLock.close();
            if (homeGroupId == null) {
                homeGroupId = "";
            }
            logAndRecordMetric(homeGroupId, "homegroupId", "getHomeGroupId", str, MetricKeys.HOMEGROUP_ID_GETTER_TO_EMPTY, z);
            return homeGroupId;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public String getLastName() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String lastName = this.migrationCommsIdentityStore.getLastName();
            acquiredLock.close();
            return lastName;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public String getPhoneNumber() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String phoneNumber = this.migrationCommsIdentityStore.getPhoneNumber();
            acquiredLock.close();
            return phoneNumber;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public String getPhoneticFirstName() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String phoneticFirstName = this.migrationCommsIdentityStore.getPhoneticFirstName();
            acquiredLock.close();
            return phoneticFirstName;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public String getPhoneticLastName() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String phoneticLastName = this.migrationCommsIdentityStore.getPhoneticLastName();
            acquiredLock.close();
            return phoneticLastName;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public String getPreferredMarketplace(boolean z) {
        MarketplaceInfo userPFMInfo = getUserPFMInfo(z);
        if (userPFMInfo == null) {
            return "Default";
        }
        String pfmCode = userPFMInfo.getPfmCode();
        return TextUtils.isEmpty(pfmCode) ? "Default" : pfmCode;
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public String getProfileName() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String profileName = this.migrationCommsIdentityStore.getProfileName();
            acquiredLock.close();
            return profileName;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public CommsProvisionStatus getProvisionStatus(boolean z, @NonNull String str, boolean z2) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            CommsProvisionStatus provisionStatus = this.migrationCommsIdentityStore.getProvisionStatus(z);
            acquiredLock.close();
            if (CommsProvisionStatus.DEPROVISIONED.equals(provisionStatus)) {
                GeneratedOutlineSupport.outline3("provisionStatus is DEPROVISIONED in getProvisionStatus called from ", str, LOG);
                if (!z2) {
                    MetricsHelper.recordOperationalMetricWithSource(MetricKeys.PROVISIONING_GETTER_TO_DEPROVISIONED, str);
                }
            }
            if (CommsProvisionStatus.UNKNOWN.equals(provisionStatus)) {
                GeneratedOutlineSupport.outline3("provisionStatus is UNKNOWN in getProvisionStatus called from ", str, LOG);
                if (!z2) {
                    MetricsHelper.recordOperationalMetricWithSource(MetricKeys.PROVISIONING_GETTER_TO_EMPTY, str);
                }
            }
            return provisionStatus;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public MarketplaceInfo getUserPFMInfo(boolean z) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            MarketplaceInfo userPFMInfo = this.migrationCommsIdentityStore.getUserPFMInfo(z);
            if (userPFMInfo == null || TextUtils.isEmpty(userPFMInfo.getPfmCode())) {
                restoreFromIdentityService();
                userPFMInfo = this.migrationCommsIdentityStore.getUserPFMInfo(z);
            }
            acquiredLock.close();
            return userPFMInfo;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public boolean isCommsIdPopulated(@NonNull String str, boolean z) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String commsId = this.migrationCommsIdentityStore.getCommsId(z);
            acquiredLock.close();
            logAndRecordMetric(commsId, "commsId", "isCommsIdPopulated", str, MetricKeys.COMMS_ID_GETTER_TO_EMPTY, z);
            return !TextUtils.isEmpty(commsId);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public boolean isCoreIdentityPopulated(@NonNull String str, boolean z) {
        String commsId = getCommsId(str + ":isCoreIdentityPopulated", z);
        String directedId = getDirectedId(str + ":isCoreIdentityPopulated", z);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(":isCoreIdentityPopulated");
        return !TextUtils.isEmpty(commsId) && !TextUtils.isEmpty(directedId) && !TextUtils.isEmpty(getHomeGroupId(sb.toString(), z));
    }

    public /* synthetic */ void lambda$restoreFromIdentityService$0$CommsIdentityManagerImpl(String str) {
        this.migrationCommsIdentityStore.setAor(str, true);
    }

    public /* synthetic */ void lambda$restoreFromIdentityService$1$CommsIdentityManagerImpl(String str) {
        this.migrationCommsIdentityStore.setHomeGroupId(str, true);
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void onCurrentUserUpdated() throws InvalidCommsIdentityException {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.legacyPersistCurrentIdentity();
            String profileName = this.migrationCommsIdentityStore.getProfileName();
            acquiredLock.close();
            setUserNameInMenu(profileName);
            CommsDaggerWrapper.getComponent().getPushNotificationManager().registerForPush();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setAor(String str, boolean z) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setAor(str, z);
            acquiredLock.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setCommsId(String str, @NonNull String str2, boolean z) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String commsId = this.migrationCommsIdentityStore.getCommsId(true);
            this.migrationCommsIdentityStore.setCommsId(str);
            if (!TextUtils.isEmpty(commsId) && TextUtils.isEmpty(str)) {
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("commsId changed to empty by ");
                sb.append(str2);
                commsLogger.w(sb.toString());
            }
            acquiredLock.close();
            logAndRecordMetric(str, "commsId", "setCommsId", str2, MetricKeys.COMMS_ID_SETTER_TO_EMPTY, z);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setCountryOfResidence(String str) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setCountryOfResidence(str);
            acquiredLock.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setCurrentUser(@NonNull CommsIdentity commsIdentity) throws InvalidCommsIdentityException {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.populateCommsIdentity(commsIdentity);
            acquiredLock.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setDirectedId(String str, @NonNull String str2, boolean z) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            String directedId = this.migrationCommsIdentityStore.getDirectedId();
            this.migrationCommsIdentityStore.setDirectedId(str);
            if (!TextUtils.isEmpty(directedId) && TextUtils.isEmpty(str)) {
                CommsLogger commsLogger = LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("directedId changed to empty by ");
                sb.append(str2);
                commsLogger.w(sb.toString());
            }
            acquiredLock.close();
            logAndRecordMetric(str, "directedId", "setDirectedId", str2, MetricKeys.DIRECTED_ID_SETTER_TO_EMPTY, z);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setHasDevices(boolean z) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setHasDevices(z);
            acquiredLock.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setHashedCommsId(String str) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setHashedCommsId(str);
            acquiredLock.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setHomeGroupId(String str, boolean z, @NonNull String str2, boolean z2) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setHomeGroupId(str, z);
            acquiredLock.close();
            logAndRecordMetric(str, "homegroupId", "setHomeGroupId", str2, MetricKeys.HOMEGROUP_ID_SETTER_TO_EMPTY, z2);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setMinimumInfo(Person person, String str, boolean z) {
        CommsLogger commsLogger = LOG;
        commsLogger.i("setting minimum info with source " + str);
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setDirectedId(person.directedId);
            this.migrationCommsIdentityStore.setProvisionStatus(person.commsProvisionStatus, z);
            acquiredLock.close();
            GeneratedOutlineSupport.outline4("done setting minimum info with source ", str, LOG);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setName(String str, String str2, String str3, String str4) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setName(str, str2, str3, str4);
            acquiredLock.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setNoCommsUser(Person person, String str, boolean z, boolean z2) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setDirectedId(person.directedId);
            this.migrationCommsIdentityStore.setProvisionStatus(person.commsProvisionStatus, z);
            this.migrationCommsIdentityStore.setName(person.firstName, person.lastName, person.phoneticFirstName, person.phoneticLastName);
            this.migrationCommsIdentityStore.setPhoneNumber(person.phoneNumber);
            this.migrationCommsIdentityStore.setCommsId("");
            acquiredLock.close();
            logAndRecordMetric(person.directedId, "directedId", "setNoCommsUser", str, MetricKeys.DIRECTED_ID_SETTER_TO_EMPTY, z2);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setPhoneNumber(String str) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setPhoneNumber(str);
            acquiredLock.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setProvisionStatus(@Nullable CommsProvisionStatus commsProvisionStatus, @NonNull String str, boolean z, boolean z2) {
        if (CommsProvisionStatus.DEPROVISIONED.equals(commsProvisionStatus)) {
            CommsLogger commsLogger = LOG;
            commsLogger.w("provisionStatus is DEPROVISIONED in setProvisionStatus called from " + str);
            if (!z) {
                MetricsHelper.recordOperationalMetricWithSource(MetricKeys.PROVISIONING_SETTER_TO_DEPROVISIONED, str);
            }
            deprovisionCurrentUser(str, false, null, false);
            return;
        }
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setProvisionStatus(commsProvisionStatus, z2);
            acquiredLock.close();
            if (!CommsProvisionStatus.UNKNOWN.equals(commsProvisionStatus)) {
                return;
            }
            GeneratedOutlineSupport.outline3("provisionStatus is UNKNOWN in setProvisionStatus called from ", str, LOG);
            if (z) {
                return;
            }
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.PROVISIONING_SETTER_TO_EMPTY, str);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void setUserPFMInfo(MarketplaceInfo marketplaceInfo) {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setUserPFMInfo(marketplaceInfo);
            acquiredLock.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void signoutCurrentUser() {
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.clearCommsIdentity(true);
            acquiredLock.close();
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    @Override // com.amazon.deecomms.api.CommsIdentityManager
    public void updateUserNames(@NonNull ContactName contactName) throws InvalidCommsIdentityException {
        if (contactName == null) {
            return;
        }
        AcquiredLock acquiredLock = new AcquiredLock(null);
        try {
            this.migrationCommsIdentityStore.setName(contactName.getFirstName(), contactName.getLastName(), contactName.getPhoneticFirstName(), contactName.getPhoneticLastName());
            this.migrationCommsIdentityStore.legacyPersistCurrentIdentity();
            String profileName = this.migrationCommsIdentityStore.getProfileName();
            acquiredLock.close();
            setUserNameInMenu(profileName);
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    acquiredLock.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public final class AcquiredLock implements Closeable {
        private AcquiredLock() {
            CommsIdentityManagerImpl.this.commsIdentityStoreLock.lock();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            CommsIdentityManagerImpl.this.commsIdentityStoreLock.unlock();
        }

        /* synthetic */ AcquiredLock(AnonymousClass1 anonymousClass1) {
            CommsIdentityManagerImpl.this.commsIdentityStoreLock.lock();
        }
    }
}
