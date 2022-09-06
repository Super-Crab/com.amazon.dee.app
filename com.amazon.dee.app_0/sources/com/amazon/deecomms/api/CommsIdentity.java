package com.amazon.deecomms.api;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.util.IdentityValidator;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.android.tools.r8.GeneratedOutlineSupport;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.Objects;
/* loaded from: classes12.dex */
public class CommsIdentity {
    public static final CommsIdentity EMPTY = new CommsIdentity();
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, CommsIdentity.class);
    private String aor;
    private String commsId;
    private String countryOfResidence;
    private String directedId;
    private String email;
    private String firstName;
    private boolean hasDevices;
    private String hashedCommsId;
    private String homeGroupId;
    private String lastName;
    private String phoneNumber;
    private String phoneticFirstName;
    private String phoneticLastName;
    private CommsProvisionStatus provisioningStatus;
    private MarketplaceInfo userPFMInfo = new MarketplaceInfo();
    private final BehaviorSubject<CommsIdentity> subject = BehaviorSubject.createDefault(this);

    public void clear() {
        copyFrom(EMPTY);
    }

    public void copyFrom(@NonNull CommsIdentity commsIdentity) {
        this.directedId = commsIdentity.directedId;
        this.commsId = commsIdentity.commsId;
        this.hashedCommsId = commsIdentity.hashedCommsId;
        this.firstName = commsIdentity.firstName;
        this.lastName = commsIdentity.lastName;
        this.phoneticFirstName = commsIdentity.phoneticFirstName;
        this.phoneticLastName = commsIdentity.phoneticLastName;
        this.phoneNumber = commsIdentity.phoneNumber;
        this.homeGroupId = commsIdentity.homeGroupId;
        this.email = commsIdentity.email;
        this.hasDevices = commsIdentity.hasDevices;
        this.aor = commsIdentity.aor;
        this.provisioningStatus = commsIdentity.provisioningStatus;
        this.subject.onNext(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CommsIdentity.class != obj.getClass()) {
            return false;
        }
        CommsIdentity commsIdentity = (CommsIdentity) obj;
        return this.hasDevices == commsIdentity.hasDevices && Objects.equals(this.directedId, commsIdentity.directedId) && Objects.equals(this.commsId, commsIdentity.commsId) && Objects.equals(this.hashedCommsId, commsIdentity.hashedCommsId) && Objects.equals(this.firstName, commsIdentity.firstName) && Objects.equals(this.lastName, commsIdentity.lastName) && Objects.equals(this.phoneNumber, commsIdentity.phoneNumber) && Objects.equals(this.homeGroupId, commsIdentity.homeGroupId) && Objects.equals(this.email, commsIdentity.email) && Objects.equals(this.aor, commsIdentity.aor) && Objects.equals(this.provisioningStatus, commsIdentity.provisioningStatus);
    }

    public String getAor() {
        return this.aor;
    }

    public String getCommsId() {
        return this.commsId;
    }

    public String getCountryOfResidence() {
        return this.countryOfResidence;
    }

    public String getDirectedId() {
        return this.directedId;
    }

    public String getEmail() {
        return this.email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getHashedCommsId() {
        return this.hashedCommsId;
    }

    public String getHomeGroupId() {
        return this.homeGroupId;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Observable<CommsIdentity> getObservable() {
        return this.subject;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPhoneticFirstName() {
        return this.phoneticFirstName;
    }

    public String getPhoneticLastName() {
        return this.phoneticLastName;
    }

    @NonNull
    public String getPreferredMarketplace() {
        return TextUtils.isEmpty(this.userPFMInfo.getPfmCode()) ? "Default" : this.userPFMInfo.getPfmCode();
    }

    public CommsProvisionStatus getProvisioningStatus() {
        return this.provisioningStatus;
    }

    public MarketplaceInfo getUserPFMInfo() {
        MarketplaceInfo marketplaceInfo = this.userPFMInfo;
        return marketplaceInfo == null ? new MarketplaceInfo() : marketplaceInfo;
    }

    public boolean hasDevices() {
        return this.hasDevices;
    }

    public int hashCode() {
        return Objects.hash(this.directedId, this.commsId, this.hashedCommsId, this.firstName, this.lastName, this.phoneNumber, this.homeGroupId, this.email, Boolean.valueOf(this.hasDevices), this.aor, this.provisioningStatus);
    }

    public boolean isCommsIdEmpty() {
        return TextUtils.isEmpty(this.commsId);
    }

    public boolean isCommsIdPopulated() {
        return !isCommsIdEmpty();
    }

    public boolean isEmpty() {
        if (TextUtils.isEmpty(this.directedId)) {
            return true;
        }
        return !CommsProvisionStatus.DEPROVISIONED.equals(this.provisioningStatus) && (TextUtils.isEmpty(this.homeGroupId) || !IdentityValidator.isValidCommsId(this.commsId));
    }

    public boolean isPopulated() {
        return !isEmpty();
    }

    public void setAor(String str) {
        this.aor = str;
        this.subject.onNext(this);
    }

    public void setCommsId(String str, @NonNull String str2, boolean z) {
        if (str == null || str.isEmpty()) {
            GeneratedOutlineSupport.outline4("setCommsId to empty from ", str2, LOG);
            if (!z) {
                MetricsHelper.recordOperationalMetricWithSource(MetricKeys.COMMS_ID_SETTER_TO_EMPTY, str2);
            }
        }
        this.commsId = str;
        this.subject.onNext(this);
    }

    public void setCountryOfResidence(String str) {
        this.countryOfResidence = str;
    }

    public void setDirectedId(String str, @NonNull String str2, boolean z) {
        if (str == null || str.isEmpty()) {
            GeneratedOutlineSupport.outline4("setDirectedId to empty from ", str2, LOG);
            if (!z) {
                MetricsHelper.recordOperationalMetricWithSource(MetricKeys.DIRECTED_ID_SETTER_TO_EMPTY, str2);
            }
        }
        this.directedId = str;
        this.subject.onNext(this);
    }

    public void setEmail(String str) {
        this.email = str;
        this.subject.onNext(this);
    }

    public void setFirstName(String str) {
        this.firstName = str;
        this.subject.onNext(this);
    }

    public void setHasDevices(boolean z) {
        this.hasDevices = z;
        this.subject.onNext(this);
    }

    public void setHashedCommsId(String str) {
        this.hashedCommsId = str;
        this.subject.onNext(this);
    }

    public void setHomeGroupId(String str) {
        this.homeGroupId = str;
        this.subject.onNext(this);
    }

    public void setLastName(String str) {
        this.lastName = str;
        this.subject.onNext(this);
    }

    public void setName(String str, String str2) {
        this.firstName = str;
        this.lastName = str2;
        this.subject.onNext(this);
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
        this.subject.onNext(this);
    }

    public void setPhoneticFirstName(String str) {
        this.phoneticFirstName = str;
        this.subject.onNext(this);
    }

    public void setPhoneticLastName(String str) {
        this.phoneticLastName = str;
        this.subject.onNext(this);
    }

    public void setPreferredMarketplace(@NonNull String str) {
        this.userPFMInfo.setPfmCode(str);
    }

    public void setProvisioningStatus(CommsProvisionStatus commsProvisionStatus, @NonNull String str, boolean z) {
        if (commsProvisionStatus == null || commsProvisionStatus == CommsProvisionStatus.UNKNOWN) {
            if (!z) {
                CommsLogger commsLogger = LOG;
                commsLogger.i("setProvisioningStatus to UNKNOWN from " + str);
                MetricsHelper.recordOperationalMetricWithSource(MetricKeys.PROVISIONING_SETTER_TO_EMPTY, str);
            }
        } else if (commsProvisionStatus == CommsProvisionStatus.DEPROVISIONED) {
            GeneratedOutlineSupport.outline4("deprovisioning user from ", str, LOG);
            if (!z) {
                MetricsHelper.recordOperationalMetricWithSource(MetricKeys.PROVISIONING_SETTER_TO_DEPROVISIONED, str);
            }
        }
        this.provisioningStatus = commsProvisionStatus;
        this.subject.onNext(this);
    }

    public void setUserPFMInfo(MarketplaceInfo marketplaceInfo) {
        this.userPFMInfo = marketplaceInfo;
    }

    public static boolean isPopulated(CommsIdentity commsIdentity) {
        return commsIdentity != null && commsIdentity.isPopulated();
    }

    public void setName(String str, String str2, String str3, String str4) {
        this.firstName = str;
        this.lastName = str2;
        this.phoneticFirstName = str3;
        this.phoneticLastName = str4;
        this.subject.onNext(this);
    }
}
