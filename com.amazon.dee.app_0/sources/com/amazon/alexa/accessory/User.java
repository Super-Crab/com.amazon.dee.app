package com.amazon.alexa.accessory;

import androidx.annotation.Nullable;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import javax.annotation.Nonnull;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes.dex */
public final class User {
    public static final User ABSENT = new User();
    private final String accessToken;
    private final String directedCustomerId;

    private User() {
        this(null, "");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || User.class != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(this.accessToken, user.accessToken) && Objects.equals(this.directedCustomerId, user.directedCustomerId);
    }

    @Nullable
    public String getAccessToken() {
        return this.accessToken;
    }

    @Nonnull
    public String getDirectedCustomerId() {
        return this.directedCustomerId;
    }

    public int hashCode() {
        return Objects.hash(this.accessToken, this.directedCustomerId);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("User{accessToken='");
        GeneratedOutlineSupport1.outline176(outline107, this.accessToken == null ? "null" : "RESTRICTED_DATA", Chars.QUOTE, ", directedCustomerId='");
        return GeneratedOutlineSupport1.outline90(outline107, this.directedCustomerId, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }

    public User(@Nullable String str, @Nonnull String str2) {
        Preconditions.notNull(str2, "directedCustomerId");
        this.accessToken = str;
        this.directedCustomerId = str2;
    }
}
