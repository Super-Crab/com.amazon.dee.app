package com.amazon.alexa.accessory.avsclient.context;

import com.amazon.alexa.accessory.internal.util.JsonObjectSerializable;
import com.amazon.alexa.accessory.internal.util.JsonUtils;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public final class IOComponents implements JsonObjectSerializable {
    public static final IOComponents EMPTY = new IOComponents(Collections.emptyList(), Collections.emptyList());
    private final List<IOComponent> activeIOComponents;
    private final List<IOComponent> allIOComponents;

    public IOComponents(List<IOComponent> list, List<IOComponent> list2) {
        Preconditions.notNull(list, "activeIOComponents");
        Preconditions.notNull(list2, "allIOComponents");
        this.activeIOComponents = Collections.unmodifiableList(list);
        this.allIOComponents = Collections.unmodifiableList(list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || IOComponents.class != obj.getClass()) {
            return false;
        }
        IOComponents iOComponents = (IOComponents) obj;
        return Objects.equals(new HashSet(this.activeIOComponents), new HashSet(iOComponents.activeIOComponents)) && Objects.equals(new HashSet(this.allIOComponents), new HashSet(iOComponents.allIOComponents));
    }

    public List<IOComponent> getActiveIOComponentList() {
        return this.activeIOComponents;
    }

    public List<IOComponent> getAllIOComponentList() {
        return this.allIOComponents;
    }

    public int hashCode() {
        return Objects.hash(new HashSet(this.activeIOComponents), new HashSet(this.allIOComponents));
    }

    @Override // com.amazon.alexa.accessory.internal.util.JsonObjectSerializable
    public JSONObject toJsonObject() throws JSONException {
        return new JSONObject().put("activeIOComponents", JsonUtils.toJsonArray(this.activeIOComponents)).put("allIOComponents", JsonUtils.toJsonArray(this.allIOComponents));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("IOComponents{activeIOComponents=");
        outline107.append(this.activeIOComponents);
        outline107.append(", allIOComponents=");
        return GeneratedOutlineSupport1.outline94(outline107, this.allIOComponents, JsonReaderKt.END_OBJ);
    }
}
