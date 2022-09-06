package com.amazon.alexa.smarthomecameras.model;

import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes10.dex */
public class SessionId {
    private final String sessionId;

    private SessionId(String str) {
        this.sessionId = str;
    }

    @Nullable
    public static SessionId create(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new SessionId(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SessionId) {
            return this.sessionId.equals(((SessionId) obj).sessionId);
        }
        return false;
    }

    public final String getValue() {
        return this.sessionId;
    }

    public int hashCode() {
        return Objects.hash(this.sessionId);
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline90(GeneratedOutlineSupport1.outline107("SessionId{sessionId='"), this.sessionId, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
