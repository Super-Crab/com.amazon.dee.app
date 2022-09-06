package com.amazon.deecomms.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.enums.CommsProvisionStatus;
import com.amazon.deecomms.exceptions.InvalidCommsIdentityException;
import com.amazon.deecomms.oobe.Person;
/* loaded from: classes12.dex */
public interface CommsIdentityManager {
    void deprovisionCurrentUser(@NonNull String str, boolean z, @Nullable Person person, boolean z2);

    String getAor();

    @NonNull
    String getCommsId(@NonNull String str, boolean z);

    String getCountryOfResidence();

    CommsIdentity getCurrentCommsIdentity();

    @NonNull
    String getDirectedId(@NonNull String str, boolean z);

    String getEmail();

    String getFirstName();

    String getHashedCommsId();

    @NonNull
    String getHomeGroupId(@NonNull String str, boolean z);

    String getLastName();

    String getPhoneNumber();

    String getPhoneticFirstName();

    String getPhoneticLastName();

    String getPreferredMarketplace(boolean z);

    String getProfileName();

    CommsProvisionStatus getProvisionStatus(boolean z, @NonNull String str, boolean z2);

    MarketplaceInfo getUserPFMInfo(boolean z);

    boolean isCommsIdPopulated(@NonNull String str, boolean z);

    boolean isCoreIdentityPopulated(@NonNull String str, boolean z);

    void onCurrentUserUpdated() throws InvalidCommsIdentityException;

    void setAor(String str, boolean z);

    void setCommsId(String str, @NonNull String str2, boolean z);

    void setCountryOfResidence(String str);

    void setCurrentUser(@NonNull CommsIdentity commsIdentity) throws InvalidCommsIdentityException;

    void setDirectedId(String str, @NonNull String str2, boolean z);

    void setHasDevices(boolean z);

    void setHashedCommsId(String str);

    void setHomeGroupId(String str, boolean z, @NonNull String str2, boolean z2);

    void setMinimumInfo(Person person, String str, boolean z);

    void setName(String str, String str2, String str3, String str4);

    void setNoCommsUser(Person person, String str, boolean z, boolean z2);

    void setPhoneNumber(String str);

    void setProvisionStatus(@Nullable CommsProvisionStatus commsProvisionStatus, @NonNull String str, boolean z, boolean z2);

    void setUserPFMInfo(MarketplaceInfo marketplaceInfo);

    void signoutCurrentUser();

    void updateUserNames(@NonNull ContactName contactName) throws InvalidCommsIdentityException;
}
