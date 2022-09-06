package com.amazon.commscore.commsidentity.repo.mapper;

import com.amazon.commscore.api.base.mapper.APIMapper;
import com.amazon.commscore.api.base.mapper.DatabaseMapper;
import com.amazon.commscore.commsidentity.database.schema.Account;
import com.amazon.commscore.commsidentity.repo.model.AccountForDirectedId;
/* loaded from: classes12.dex */
public class AccountForDirectedIdMapper implements APIMapper<AccountForDirectedId, com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId>, DatabaseMapper<AccountForDirectedId, Account> {
    @Override // com.amazon.commscore.api.base.mapper.APIMapper
    public AccountForDirectedId fromAPIModel(com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId accountForDirectedId) {
        AccountForDirectedId accountForDirectedId2 = new AccountForDirectedId();
        accountForDirectedId2.setLastName(accountForDirectedId.getLastName());
        accountForDirectedId2.setSpeakerProvisioned(accountForDirectedId.isSpeakerProvisioned());
        accountForDirectedId2.setPersonIdV2(accountForDirectedId.getPersonIdV2());
        accountForDirectedId2.setCommsId(accountForDirectedId.getCommsId());
        accountForDirectedId2.setSignedInUser(accountForDirectedId.isSignedInUser());
        accountForDirectedId2.setFirstName(accountForDirectedId.getFirstName());
        accountForDirectedId2.setCommsProvisioned(accountForDirectedId.isCommsProvisioned());
        accountForDirectedId2.setPhoneticLastName(accountForDirectedId.getPhoneticLastName());
        accountForDirectedId2.setPhoneNumber(accountForDirectedId.getPhoneNumber());
        accountForDirectedId2.setPhoneCountryCode(accountForDirectedId.getPhoneCountryCode());
        accountForDirectedId2.setCommsProvisionStatus(accountForDirectedId.getCommsProvisionStatus());
        accountForDirectedId2.setEnrolledInAlexa(accountForDirectedId.isEnrolledInAlexa());
        accountForDirectedId2.setDirectedId(accountForDirectedId.getDirectedId());
        accountForDirectedId2.setIsChild(accountForDirectedId.isIsChild());
        accountForDirectedId2.setPhoneticFirstName(accountForDirectedId.getPhoneticFirstName());
        return accountForDirectedId2;
    }

    @Override // com.amazon.commscore.api.base.mapper.DatabaseMapper
    public AccountForDirectedId fromDatabaseModel(Account account) {
        AccountForDirectedId accountForDirectedId = new AccountForDirectedId();
        accountForDirectedId.setLastName(account.getLastName());
        accountForDirectedId.setSpeakerProvisioned(account.isSpeakerProvisioned());
        accountForDirectedId.setPersonIdV2(account.getPersonIdV2());
        accountForDirectedId.setCommsId(account.getCommsId());
        accountForDirectedId.setSignedInUser(account.isSignedInUser());
        accountForDirectedId.setFirstName(account.getFirstName());
        accountForDirectedId.setCommsProvisioned(account.isCommsProvisioned());
        accountForDirectedId.setPhoneticLastName(account.getPhoneticLastName());
        accountForDirectedId.setPhoneNumber(account.getPhoneNumber());
        accountForDirectedId.setPhoneCountryCode(account.getPhoneCountryCode());
        accountForDirectedId.setCommsProvisionStatus(account.getCommsProvisionStatus());
        accountForDirectedId.setEnrolledInAlexa(account.isEnrolledInAlexa());
        accountForDirectedId.setDirectedId(account.getDirectedId());
        accountForDirectedId.setIsChild(account.isIsChild());
        accountForDirectedId.setPhoneticFirstName(account.getPhoneticFirstName());
        return accountForDirectedId;
    }

    @Override // com.amazon.commscore.api.base.mapper.APIMapper
    public com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId toAPIModel(AccountForDirectedId accountForDirectedId) {
        com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId accountForDirectedId2 = new com.amazon.commscore.commsidentity.remote.model.AccountForDirectedId();
        accountForDirectedId2.setLastName(accountForDirectedId.getLastName());
        accountForDirectedId2.setSpeakerProvisioned(accountForDirectedId.isSpeakerProvisioned());
        accountForDirectedId2.setPersonIdV2(accountForDirectedId.getPersonIdV2());
        accountForDirectedId2.setCommsId(accountForDirectedId.getCommsId());
        accountForDirectedId2.setSignedInUser(accountForDirectedId.isSignedInUser());
        accountForDirectedId2.setFirstName(accountForDirectedId.getFirstName());
        accountForDirectedId2.setCommsProvisioned(accountForDirectedId.isCommsProvisioned());
        accountForDirectedId2.setPhoneticLastName(accountForDirectedId.getPhoneticLastName());
        accountForDirectedId2.setPhoneNumber(accountForDirectedId.getPhoneNumber());
        accountForDirectedId2.setPhoneCountryCode(accountForDirectedId.getPhoneCountryCode());
        accountForDirectedId2.setCommsProvisionStatus(accountForDirectedId.getCommsProvisionStatus());
        accountForDirectedId2.setEnrolledInAlexa(accountForDirectedId.isEnrolledInAlexa());
        accountForDirectedId2.setDirectedId(accountForDirectedId.getDirectedId());
        accountForDirectedId2.setIsChild(accountForDirectedId.isIsChild());
        accountForDirectedId2.setPhoneticFirstName(accountForDirectedId.getPhoneticFirstName());
        return accountForDirectedId2;
    }

    @Override // com.amazon.commscore.api.base.mapper.DatabaseMapper
    public Account toDatabaseModel(AccountForDirectedId accountForDirectedId) {
        Account account = new Account();
        account.setLastName(accountForDirectedId.getLastName());
        account.setSpeakerProvisioned(accountForDirectedId.isSpeakerProvisioned());
        account.setPersonIdV2(accountForDirectedId.getPersonIdV2());
        account.setCommsId(accountForDirectedId.getCommsId());
        account.setSignedInUser(accountForDirectedId.isSignedInUser());
        account.setFirstName(accountForDirectedId.getFirstName());
        account.setCommsProvisioned(accountForDirectedId.isCommsProvisioned());
        account.setPhoneticLastName(accountForDirectedId.getPhoneticLastName());
        account.setPhoneNumber(accountForDirectedId.getPhoneNumber());
        account.setPhoneCountryCode(accountForDirectedId.getPhoneCountryCode());
        account.setCommsProvisionStatus(accountForDirectedId.getCommsProvisionStatus());
        account.setEnrolledInAlexa(accountForDirectedId.isEnrolledInAlexa());
        account.setDirectedId(accountForDirectedId.getDirectedId());
        account.setIsChild(accountForDirectedId.isIsChild());
        account.setPhoneticFirstName(accountForDirectedId.getPhoneticFirstName());
        return account;
    }
}
