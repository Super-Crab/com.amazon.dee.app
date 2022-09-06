package androidx.room.migration.bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
class SchemaEqualityUtil {
    private SchemaEqualityUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T, K extends SchemaEquality<K>> boolean checkSchemaEquality(@Nullable Map<T, K> map, @Nullable Map<T, K> map2) {
        if (map == null) {
            return map2 == null;
        } else if (map2 == null || map.size() != map2.size()) {
            return false;
        } else {
            for (Map.Entry<T, K> entry : map.entrySet()) {
                if (!checkSchemaEquality(entry.getValue(), map2.get(entry.getKey()))) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K extends SchemaEquality<K>> boolean checkSchemaEquality(@Nullable List<K> list, @Nullable List<K> list2) {
        boolean z;
        if (list == null) {
            return list2 == null;
        } else if (list2 == null || list.size() != list2.size()) {
            return false;
        } else {
            for (K k : list) {
                Iterator<K> it2 = list2.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (checkSchemaEquality(k, it2.next())) {
                            z = true;
                            continue;
                            break;
                        }
                    } else {
                        z = false;
                        continue;
                        break;
                    }
                }
                if (!z) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <K extends SchemaEquality<K>> boolean checkSchemaEquality(@Nullable K k, @Nullable K k2) {
        if (k == null) {
            return k2 == null;
        } else if (k2 != null) {
            return k.isSchemaEqual(k2);
        } else {
            return false;
        }
    }
}
