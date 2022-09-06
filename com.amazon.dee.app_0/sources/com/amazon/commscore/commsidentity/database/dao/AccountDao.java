package com.amazon.commscore.commsidentity.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.amazon.commscore.commsidentity.database.schema.Account;
import java.util.List;
@Dao
/* loaded from: classes12.dex */
public interface AccountDao {
    @Delete
    void delete(Account account);

    @Query("SELECT * FROM account WHERE directed_id = :directedId")
    Account getAccountDetailsForDirectedId(String str);

    @Query("SELECT * FROM account")
    List<Account> getAll();

    @Insert(onConflict = 1)
    Long insert(Account account);
}
