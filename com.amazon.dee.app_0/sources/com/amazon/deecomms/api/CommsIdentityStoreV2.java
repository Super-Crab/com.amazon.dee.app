package com.amazon.deecomms.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
/* loaded from: classes12.dex */
public interface CommsIdentityStoreV2 {
    void clearCommsIdentity(boolean z);

    String getAor();

    String getCommsId(boolean z);

    String getCountryOfResidence();

    CommsIdentity getCurrentCommsIdentity();

    String getDirectedId();

    String getEmail();

    String getFirstName();

    String getHashedCommsId();

    String getHomeGroupId();

    String getLastName();

    String getPhoneNumber();

    String getPhoneticFirstName();

    String getPhoneticLastName();

    String getProfileName();

    CommsProvisionStatus getProvisionStatus(boolean z);

    MarketplaceInfo getUserPFMInfo(boolean z);

    boolean hasDevices();

    void legacyPersistCurrentIdentity() throws InvalidCommsIdentityException;

    void populateCommsIdentity(@NonNull CommsIdentity commsIdentity) throws InvalidCommsIdentityException;

    void setAor(String str, boolean z);

    void setCommsId(String str);

    void setCountryOfResidence(String str);

    void setDirectedId(String str);

    void setEmail(String str);

    void setFirstName(String str);

    void setHasDevices(boolean z);

    void setHashedCommsId(String str);

    void setHomeGroupId(String str, boolean z);

    void setLastName(String str);

    void setName(String str, String str2);

    void setName(String str, String str2, String str3, String str4);

    void setPhoneNumber(String str);

    void setPhoneticFirstName(String str);

    void setPhoneticLastName(String str);

    void setProvisionStatus(@Nullable CommsProvisionStatus commsProvisionStatus, boolean z);

    void setUserPFMInfo(MarketplaceInfo marketplaceInfo);
}
