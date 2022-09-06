package com.bumptech.glide.load.engine.bitmap_recycle;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Map;
import java.util.TreeMap;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes6.dex */
class PrettyPrintTreeMap<K, V> extends TreeMap<K, V> {
    @Override // java.util.AbstractMap
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("( ");
        for (Map.Entry<K, V> entry : entrySet()) {
            outline107.append(JsonReaderKt.BEGIN_OBJ);
            outline107.append(entry.getKey());
            outline107.append(JsonReaderKt.COLON);
            outline107.append(entry.getValue());
            outline107.append("}, ");
        }
        if (!isEmpty()) {
            outline107.replace(outline107.length() - 2, outline107.length(), "");
        }
        outline107.append(" )");
        return outline107.toString();
    }
}
