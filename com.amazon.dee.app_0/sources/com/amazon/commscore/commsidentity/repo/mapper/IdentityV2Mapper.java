package com.amazon.commscore.commsidentity.repo.mapper;

import com.amazon.commscore.api.base.mapper.APIMapper;
import com.amazon.commscore.api.base.mapper.DatabaseMapper;
import com.amazon.commscore.commsidentity.repo.model.IdentityV2;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class IdentityV2Mapper implements APIMapper<IdentityV2, com.amazon.commscore.commsidentity.remote.model.IdentityV2>, DatabaseMapper<IdentityV2, com.amazon.commscore.commsidentity.database.schema.IdentityV2> {
    private NameMapper nameMapper;

    @Inject
    public IdentityV2Mapper(NameMapper nameMapper) {
        this.nameMapper = nameMapper;
    }

    @Override // com.amazon.commscore.api.base.mapper.APIMapper
    public IdentityV2 fromAPIModel(com.amazon.commscore.commsidentity.remote.model.IdentityV2 identityV2) {
        IdentityV2 identityV22 = new IdentityV2();
        identityV22.setAor(identityV2.getAor());
        identityV22.setCommsId(identityV2.getCommsId());
        identityV22.setCommsProvisionStatus(identityV2.getCommsProvisionStatus());
        identityV22.setHashedCommsId(identityV2.getHashedCommsId());
        identityV22.setHomeGroupId(identityV2.getHomeGroupId());
        identityV22.setName(this.nameMapper.fromAPIModel(identityV2.getName()));
        identityV22.setPceId(identityV2.getPceId());
        return identityV22;
    }

    @Override // com.amazon.commscore.api.base.mapper.DatabaseMapper
    public IdentityV2 fromDatabaseModel(com.amazon.commscore.commsidentity.database.schema.IdentityV2 identityV2) {
        IdentityV2 identityV22 = new IdentityV2();
        identityV22.setAor(identityV2.getAor());
        identityV22.setCommsId(identityV2.getCommsId());
        identityV22.setCommsProvisionStatus(identityV2.getCommsProvisionStatus());
        identityV22.setHashedCommsId(identityV2.getHashedCommsId());
        identityV22.setHomeGroupId(identityV2.getHomeGroupId());
        identityV22.setName(this.nameMapper.fromDatabaseModel(identityV2.getName()));
        identityV22.setPceId(identityV2.getPceId());
        return identityV22;
    }

    @Override // com.amazon.commscore.api.base.mapper.APIMapper
    public com.amazon.commscore.commsidentity.remote.model.IdentityV2 toAPIModel(IdentityV2 identityV2) {
        com.amazon.commscore.commsidentity.remote.model.IdentityV2 identityV22 = new com.amazon.commscore.commsidentity.remote.model.IdentityV2();
        identityV22.setAor(identityV2.getAor());
        identityV22.setCommsId(identityV2.getCommsId());
        identityV22.setCommsProvisionStatus(identityV2.getCommsProvisionStatus());
        identityV22.setHashedCommsId(identityV2.getHashedCommsId());
        identityV22.setHomeGroupId(identityV2.getHomeGroupId());
        identityV22.setName(this.nameMapper.toAPIModel(identityV2.getName()));
        identityV22.setPceId(identityV2.getPceId());
        return identityV22;
    }

    @Override // com.amazon.commscore.api.base.mapper.DatabaseMapper
    public com.amazon.commscore.commsidentity.database.schema.IdentityV2 toDatabaseModel(IdentityV2 identityV2) {
        com.amazon.commscore.commsidentity.database.schema.IdentityV2 identityV22 = new com.amazon.commscore.commsidentity.database.schema.IdentityV2();
        identityV22.setAor(identityV2.getAor());
        identityV22.setCommsId(identityV2.getCommsId());
        identityV22.setCommsProvisionStatus(identityV2.getCommsProvisionStatus());
        identityV22.setHashedCommsId(identityV2.getHashedCommsId());
        identityV22.setHomeGroupId(identityV2.getHomeGroupId());
        identityV22.setName(this.nameMapper.toDatabaseModel(identityV2.getName()));
        identityV22.setPceId(identityV2.getPceId());
        return identityV22;
    }
}
