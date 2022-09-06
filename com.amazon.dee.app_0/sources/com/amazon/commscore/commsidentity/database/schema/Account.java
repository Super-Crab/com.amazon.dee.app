package com.amazon.commscore.commsidentity.database.schema;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
@Entity(tableName = "account")
/* loaded from: classes12.dex */
public class Account {
    @ColumnInfo(name = MetricKeys.COMMS_ID)
    private String commsId;
    @ColumnInfo(name = "comms_provision_status")
    private String commsProvisionStatus;
    @ColumnInfo(name = "comms_provisioned")
    private boolean commsProvisioned;
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "directed_id")
    private String directedId;
    @ColumnInfo(name = "enrolled_in_alexa")
    private boolean enrolledInAlexa;
    @ColumnInfo(name = "first_name")
    private String firstName;
    @ColumnInfo(name = "is_child")
    private boolean isChild;
    @ColumnInfo(name = "last_name")
    private String lastName;
    @ColumnInfo(name = "person_id_v2")
    private String personIdV2;
    @ColumnInfo(name = "phone_country_code")
    private String phoneCountryCode;
    @ColumnInfo(name = "phone_number")
    private String phoneNumber;
    @ColumnInfo(name = "phonetic_first_name")
    private String phoneticFirstName;
    @ColumnInfo(name = "phonetic_last_name")
    private String phoneticLastName;
    @ColumnInfo(name = "signed_in_user")
    private boolean signedInUser;
    @ColumnInfo(name = "speaker_provisioned")
    private boolean speakerProvisioned;

    public String getCommsId() {
        return this.commsId;
    }

    public String getCommsProvisionStatus() {
        return this.commsProvisionStatus;
    }

    public String getDirectedId() {
        return this.directedId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPersonIdV2() {
        return this.personIdV2;
    }

    public String getPhoneCountryCode() {
        return this.phoneCountryCode;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getPhoneticFirstName() {
        return this.phoneticFirstName;
    }

    public String getPhoneticLastName() {
        return this.phoneticLastName;
    }

    public boolean isCommsProvisioned() {
        return this.commsProvisioned;
    }

    public boolean isEnrolledInAlexa() {
        return this.enrolledInAlexa;
    }

    public boolean isIsChild() {
        return this.isChild;
    }

    public boolean isSignedInUser() {
        return this.signedInUser;
    }

    public boolean isSpeakerProvisioned() {
        return this.speakerProvisioned;
    }

    public void setCommsId(String str) {
        this.commsId = str;
    }

    public void setCommsProvisionStatus(String str) {
        this.commsProvisionStatus = str;
    }

    public void setCommsProvisioned(boolean z) {
        this.commsProvisioned = z;
    }

    public void setDirectedId(String str) {
        this.directedId = str;
    }

    public void setEnrolledInAlexa(boolean z) {
        this.enrolledInAlexa = z;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setIsChild(boolean z) {
        this.isChild = z;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setPersonIdV2(String str) {
        this.personIdV2 = str;
    }

    public void setPhoneCountryCode(String str) {
        this.phoneCountryCode = str;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public void setPhoneticFirstName(String str) {
        this.phoneticFirstName = str;
    }

    public void setPhoneticLastName(String str) {
        this.phoneticLastName = str;
    }

    public void setSignedInUser(boolean z) {
        this.signedInUser = z;
    }

    public void setSpeakerProvisioned(boolean z) {
        this.speakerProvisioned = z;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Account{lastName='");
        GeneratedOutlineSupport1.outline176(outline107, this.lastName, Chars.QUOTE, ", speakerProvisioned=");
        outline107.append(this.speakerProvisioned);
        outline107.append(", personIdV2='");
        GeneratedOutlineSupport1.outline176(outline107, this.personIdV2, Chars.QUOTE, ", commsId='");
        GeneratedOutlineSupport1.outline176(outline107, this.commsId, Chars.QUOTE, ", signedInUser=");
        outline107.append(this.signedInUser);
        outline107.append(", firstName='");
        GeneratedOutlineSupport1.outline176(outline107, this.firstName, Chars.QUOTE, ", commsProvisioned=");
        outline107.append(this.commsProvisioned);
        outline107.append(", phoneticLastName='");
        GeneratedOutlineSupport1.outline176(outline107, this.phoneticLastName, Chars.QUOTE, ", phoneNumber='");
        GeneratedOutlineSupport1.outline176(outline107, this.phoneNumber, Chars.QUOTE, ", phoneCountryCode='");
        GeneratedOutlineSupport1.outline176(outline107, this.phoneCountryCode, Chars.QUOTE, ", commsProvisionStatus='");
        GeneratedOutlineSupport1.outline176(outline107, this.commsProvisionStatus, Chars.QUOTE, ", enrolledInAlexa=");
        outline107.append(this.enrolledInAlexa);
        outline107.append(", directedId='");
        GeneratedOutlineSupport1.outline176(outline107, this.directedId, Chars.QUOTE, ", isChild=");
        outline107.append(this.isChild);
        outline107.append(", phoneticFirstName='");
        return GeneratedOutlineSupport1.outline90(outline107, this.phoneticFirstName, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
