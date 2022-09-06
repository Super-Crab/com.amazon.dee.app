package com.amazon.commscore.commsidentity.database.dao;

import androidx.core.util.Pair;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import com.amazon.commscore.commsidentity.database.schema.Account;
import com.amazon.commscore.commsidentity.database.schema.IdentityV2;
import io.reactivex.rxjava3.core.Flowable;
import java.util.List;
@Dao
/* loaded from: classes12.dex */
public abstract class CommsIdentityDao {
    @Delete
    public abstract void delete(Account account);

    @Delete
    public abstract void delete(IdentityV2 identityV2);

    @Query("SELECT * FROM account WHERE directed_id = :directedId")
    public abstract Account getAccountDetailsForDirectedId(String str);

    @Query("SELECT * FROM account")
    public abstract List<Account> getAllAccounts();

    @Query("SELECT * FROM identityv2_table")
    public abstract List<IdentityV2> getAllIdentityV2();

    @Transaction
    public Pair<Flowable<IdentityV2>, Flowable<Account>> getIdentityV2AndAccountDetails(String str) {
        return new Pair<>(Flowable.just(getIdentityV2ForDirectedId(str)), Flowable.just(getAccountDetailsForDirectedId(str)));
    }

    @Query("SELECT * FROM identityv2_table WHERE directed_id = :directedId")
    public abstract IdentityV2 getIdentityV2ForDirectedId(String str);

    @Insert(onConflict = 1)
    public abstract Long insert(Account account);

    @Insert(onConflict = 1)
    public abstract Long insert(IdentityV2 identityV2);

    @Transaction
    public void insertIdentityV2AndAccountDetails(IdentityV2 identityV2, Account account) {
        insert(identityV2);
        insert(account);
    }
}
