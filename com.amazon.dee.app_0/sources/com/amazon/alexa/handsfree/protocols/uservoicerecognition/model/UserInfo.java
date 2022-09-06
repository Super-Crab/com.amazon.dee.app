package com.amazon.alexa.handsfree.protocols.uservoicerecognition.model;

import androidx.annotation.NonNull;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class UserInfo {
    public static final UserInfo DEFAULT_USER = new UserInfo("0");
    private final String mUserId;

    public UserInfo(@NonNull String str) {
        this.mUserId = (String) Objects.requireNonNull(str, "User ID must not be null.");
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != UserInfo.class) {
            return false;
        }
        return Objects.equals(this.mUserId, ((UserInfo) obj).mUserId);
    }

    @NonNull
    public String getUserId() {
        return this.mUserId;
    }

    public int hashCode() {
        return Objects.hash(this.mUserId);
    }

    public String toString() {
        return String.format("[userId = %s]", this.mUserId);
    }
}
