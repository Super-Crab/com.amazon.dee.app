package com.amazon.whisperjoin.common.sharedtypes.provisioning.data.registration;

import com.android.tools.r8.GeneratedOutlineSupport1;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
/* loaded from: classes13.dex */
public class CBLRegistrationRequest {
    private final long mExpiration;
    private final String mLinkCode;

    public CBLRegistrationRequest(String str, long j) {
        this.mLinkCode = str;
        this.mExpiration = j;
        validate();
    }

    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    public long getExpiration() {
        return this.mExpiration;
    }

    public String getLinkCode() {
        return this.mLinkCode;
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CBLRegistrationRequest [code=");
        outline107.append(this.mLinkCode);
        outline107.append(", expiration=");
        return GeneratedOutlineSupport1.outline87(outline107, this.mExpiration, "]");
    }

    protected void validate() {
        String str = this.mLinkCode;
        if (str != null) {
            if (str.length() == 0) {
                throw new IllegalArgumentException("Link code cannot be empty.");
            }
            return;
        }
        throw new IllegalArgumentException("Link code cannot be null.");
    }
}
