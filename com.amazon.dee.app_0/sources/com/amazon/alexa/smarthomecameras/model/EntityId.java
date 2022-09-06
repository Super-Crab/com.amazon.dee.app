package com.amazon.alexa.smarthomecameras.model;

import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Objects;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes10.dex */
public class EntityId {
    private final String entityId;

    private EntityId(String str) {
        this.entityId = str;
    }

    @Nullable
    public static EntityId create(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new EntityId(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EntityId) {
            return this.entityId.equals(((EntityId) obj).entityId);
        }
        return false;
    }

    public final String getValue() {
        return this.entityId;
    }

    public int hashCode() {
        return Objects.hash(this.entityId);
    }

    public String toString() {
        return GeneratedOutlineSupport1.outline90(GeneratedOutlineSupport1.outline107("EntityId{entityId='"), this.entityId, Chars.QUOTE, JsonReaderKt.END_OBJ);
    }
}
