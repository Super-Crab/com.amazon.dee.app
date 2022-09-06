package com.amazon.commscore.commsidentity.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import com.amazon.commscore.commsidentity.database.schema.IdentityV2;
import java.util.List;
@Dao
/* loaded from: classes12.dex */
public interface IdentityV2Dao {
    @Delete
    void delete(IdentityV2 identityV2);

    @Query("SELECT * FROM identityv2_table")
    List<IdentityV2> getAll();

    @Query("SELECT * FROM identityv2_table WHERE directed_id = :directedId")
    IdentityV2 getIdentityV2ForDirectedId(String str);

    @Insert(onConflict = 1)
    Long insert(IdentityV2 identityV2);
}
