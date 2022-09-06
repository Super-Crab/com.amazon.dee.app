package com.amazon.commscore.commsidentity.database.schema;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
@Entity(tableName = "identityv2_table")
/* loaded from: classes12.dex */
public class IdentityV2 {
    @ColumnInfo(name = ContactProviderContract.PhoneNumberEntry.COLUMN_AOR)
    private String aor;
    @ColumnInfo(name = MetricKeys.COMMS_ID)
    private String commsId;
    @ColumnInfo(name = "comms_provision_status")
    private String commsProvisionStatus;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "directed_id")
    private String directedId;
    @ColumnInfo(name = "hashed_comms_id")
    private String hashedCommsId;
    @ColumnInfo(name = MetricKeys.HOMEGROUP_ID)
    private String homeGroupId;
    @ColumnInfo(name = "homegroup_pce_id")
    private String homeGroupPceId;
    @Embedded
    private Name name;
    @ColumnInfo(name = "pce_id")
    private String pceId;

    public String getAor() {
        return this.aor;
    }

    public String getCommsId() {
        return this.commsId;
    }

    public String getCommsProvisionStatus() {
        return this.commsProvisionStatus;
    }

    @NonNull
    public String getDirectedId() {
        return this.directedId;
    }

    public String getHashedCommsId() {
        return this.hashedCommsId;
    }

    public String getHomeGroupId() {
        return this.homeGroupId;
    }

    public String getHomeGroupPceId() {
        return this.homeGroupPceId;
    }

    public Name getName() {
        return this.name;
    }

    public String getPceId() {
        return this.pceId;
    }

    public void setAor(String str) {
        this.aor = str;
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }

    public void setCommsProvisionStatus(String str) {
        this.commsProvisionStatus = str;
    }

    public void setDirectedId(@NonNull String str) {
        this.directedId = str;
    }

    public void setHashedCommsId(String str) {
        this.hashedCommsId = str;
    }

    public void setHomeGroupId(String str) {
        this.homeGroupId = str;
    }

    public void setHomeGroupPceId(String str) {
        this.homeGroupPceId = str;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setPceId(String str) {
        this.pceId = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IdentityV2{directedId='");
        GeneratedOutlineSupport1.outline176(outline107, this.directedId, Chars.QUOTE, ", homeGroupPceId='");
        GeneratedOutlineSupport1.outline176(outline107, this.homeGroupPceId, Chars.QUOTE, ", commsId='");
        GeneratedOutlineSupport1.outline176(outline107, this.commsId, Chars.QUOTE, ", commsProvisionStatus='");
        GeneratedOutlineSupport1.outline176(outline107, this.commsProvisionStatus, Chars.QUOTE, ", pceId='");
        GeneratedOutlineSupport1.outline176(outline107, this.pceId, Chars.QUOTE, ", aor='");
        GeneratedOutlineSupport1.outline176(outline107, this.aor, Chars.QUOTE, ", hashedCommsId='");
        GeneratedOutlineSupport1.outline176(outline107, this.hashedCommsId, Chars.QUOTE, ", name=");
        outline107.append(this.name);
        outline107.append(", homeGroupId='");
        return GeneratedOutlineSupport1.outline90(outline107, this.homeGroupId, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
