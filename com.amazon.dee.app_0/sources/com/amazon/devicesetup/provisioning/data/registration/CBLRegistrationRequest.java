package com.amazon.devicesetup.provisioning.data.registration;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes12.dex */
public class CBLRegistrationRequest {
    private final long expiration;
    private final String linkCode;

    public CBLRegistrationRequest(String str, long j) {
        this.linkCode = str;
        this.expiration = j;
        validate();
    }

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public long getExpiration() {
        return this.expiration;
    }

    public String getLinkCode() {
        return this.linkCode;
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CBLRegistrationRequest [code=");
        outline107.append(this.linkCode);
        outline107.append(", expiration=");
        return GeneratedOutlineSupport1.outline87(outline107, this.expiration, "]");
    }

    protected void validate() {
        String str = this.linkCode;
        if (str != null) {
            if (str.length() == 0) {
                throw new IllegalArgumentException("Link code cannot be empty.");
            }
            return;
        }
        throw new IllegalArgumentException("Link code cannot be null.");
    }
}
