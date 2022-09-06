package com.amazon.commscore.commsidentity.database.roomdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.amazon.commscore.commsidentity.database.dao.AccountDao;
import com.amazon.commscore.commsidentity.database.dao.CommsIdentityDao;
import com.amazon.commscore.commsidentity.database.dao.IdentityV2Dao;
import com.amazon.commscore.commsidentity.database.schema.Account;
import com.amazon.commscore.commsidentity.database.schema.IdentityV2;
@Database(entities = {IdentityV2.class, Account.class}, exportSchema = false, version = 1)
/* loaded from: classes12.dex */
public abstract class CommsCoreIdentityDatabase extends RoomDatabase {
    public abstract AccountDao accountDao();

    public abstract CommsIdentityDao commsIdentityDao();

    public abstract IdentityV2Dao identityV2Dao();
}
