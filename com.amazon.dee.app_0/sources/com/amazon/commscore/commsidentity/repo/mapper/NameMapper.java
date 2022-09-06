package com.amazon.commscore.commsidentity.repo.mapper;

import com.amazon.commscore.api.base.mapper.APIMapper;
import com.amazon.commscore.api.base.mapper.DatabaseMapper;
import com.amazon.commscore.commsidentity.repo.model.Name;
/* loaded from: classes12.dex */
public class NameMapper implements APIMapper<Name, com.amazon.commscore.commsidentity.remote.model.Name>, DatabaseMapper<Name, com.amazon.commscore.commsidentity.database.schema.Name> {
    @Override // com.amazon.commscore.api.base.mapper.APIMapper
    public Name fromAPIModel(com.amazon.commscore.commsidentity.remote.model.Name name) {
        Name name2 = new Name();
        if (name != null) {
            name2.setFirstName(name.getFirstName());
            name2.setLastName(name.getLastName());
            name2.setNickName(name.getNickName());
            name2.setPhoneticFirstName(name.getPhoneticFirstName());
            name2.setPhoneticLastName(name.getPhoneticLastName());
            name2.setPronunciationFirstName(name.getPronunciationFirstName());
            name2.setPronunciationLastName(name.getPronunciationLastName());
        }
        return name2;
    }

    @Override // com.amazon.commscore.api.base.mapper.DatabaseMapper
    public Name fromDatabaseModel(com.amazon.commscore.commsidentity.database.schema.Name name) {
        Name name2 = new Name();
        if (name != null) {
            name2.setFirstName(name.getFirstName());
            name2.setLastName(name.getLastName());
            name2.setNickName(name.getNickName());
            name2.setPhoneticFirstName(name.getPhoneticFirstName());
            name2.setPhoneticLastName(name.getPhoneticLastName());
            name2.setPronunciationFirstName(name.getPronunciationFirstName());
            name2.setPronunciationLastName(name.getPronunciationLastName());
        }
        return name2;
    }

    @Override // com.amazon.commscore.api.base.mapper.APIMapper
    public com.amazon.commscore.commsidentity.remote.model.Name toAPIModel(Name name) {
        com.amazon.commscore.commsidentity.remote.model.Name name2 = new com.amazon.commscore.commsidentity.remote.model.Name();
        if (name != null) {
            name2.setFirstName(name.getFirstName());
            name2.setLastName(name.getLastName());
            name2.setNickName(name.getNickName());
            name2.setPhoneticFirstName(name.getPhoneticFirstName());
            name2.setPhoneticLastName(name.getPhoneticLastName());
            name2.setPronunciationFirstName(name.getPronunciationFirstName());
            name2.setPronunciationLastName(name.getPronunciationLastName());
        }
        return name2;
    }

    @Override // com.amazon.commscore.api.base.mapper.DatabaseMapper
    public com.amazon.commscore.commsidentity.database.schema.Name toDatabaseModel(Name name) {
        com.amazon.commscore.commsidentity.database.schema.Name name2 = new com.amazon.commscore.commsidentity.database.schema.Name();
        if (name != null) {
            name2.setFirstName(name.getFirstName());
            name2.setLastName(name.getLastName());
            name2.setNickName(name.getNickName());
            name2.setPhoneticFirstName(name.getPhoneticFirstName());
            name2.setPhoneticLastName(name.getPhoneticLastName());
            name2.setPronunciationFirstName(name.getPronunciationFirstName());
            name2.setPronunciationLastName(name.getPronunciationLastName());
        }
        return name2;
    }
}
