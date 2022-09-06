package com.amazon.alexa.biloba.model;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes6.dex */
public class ResourceOwner {
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("id")
    private String id;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("personIdV1")
    private String personIdV1;
    @SerializedName("type")
    private String resourceType;

    public ResourceOwner(String str, String str2, String str3, String str4, String str5) {
        this.id = str;
        this.resourceType = str2;
        this.firstName = str3;
        this.lastName = str4;
        this.personIdV1 = str5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ResourceOwner.class != obj.getClass()) {
            return false;
        }
        ResourceOwner resourceOwner = (ResourceOwner) obj;
        return this.id.equals(resourceOwner.id) && this.resourceType.equals(resourceOwner.resourceType) && this.firstName.equals(resourceOwner.firstName) && this.lastName.equals(resourceOwner.lastName) && this.personIdV1.equals(resourceOwner.personIdV1);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getId() {
        return this.id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPersonIdV1() {
        return this.personIdV1;
    }

    public String getResourceType() {
        return this.resourceType;
    }

    public int hashCode() {
        return Objects.hash(this.id, this.resourceType, this.firstName, this.lastName, this.personIdV1);
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setPersonIdV1(String str) {
        this.personIdV1 = str;
    }

    public void setResourceType(String str) {
        this.resourceType = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ResourceOwner{directedPersonIdV2='");
        GeneratedOutlineSupport1.outline176(outline107, this.id, Chars.QUOTE, ", resourceType='");
        GeneratedOutlineSupport1.outline176(outline107, this.resourceType, Chars.QUOTE, ", firstName='");
        GeneratedOutlineSupport1.outline176(outline107, this.firstName, Chars.QUOTE, ", lastName='");
        GeneratedOutlineSupport1.outline176(outline107, this.lastName, Chars.QUOTE, ", personIdV1='");
        return GeneratedOutlineSupport1.outline90(outline107, this.personIdV1, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
