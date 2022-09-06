package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.Objects;
/* loaded from: classes6.dex */
public class PaginationContext {
    @SerializedName("nextToken")
    private String nextToken = null;
    @SerializedName("previousToken")
    private String previousToken = null;

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PaginationContext.class != obj.getClass()) {
            return false;
        }
        PaginationContext paginationContext = (PaginationContext) obj;
        return Objects.equals(this.nextToken, paginationContext.nextToken) && Objects.equals(this.previousToken, paginationContext.previousToken);
    }

    public String getNextToken() {
        return this.nextToken;
    }

    public String getPreviousToken() {
        return this.previousToken;
    }

    public int hashCode() {
        return Objects.hash(this.nextToken, this.previousToken);
    }

    public PaginationContext nextToken(String str) {
        this.nextToken = str;
        return this;
    }

    public PaginationContext previousToken(String str) {
        this.previousToken = str;
        return this;
    }

    public void setNextToken(String str) {
        this.nextToken = str;
    }

    public void setPreviousToken(String str) {
        this.previousToken = str;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class PaginationContext {\n", "    nextToken: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.nextToken), "\n", "    previousToken: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.previousToken), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
