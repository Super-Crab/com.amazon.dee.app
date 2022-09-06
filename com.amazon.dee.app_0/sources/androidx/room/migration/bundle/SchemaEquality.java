package androidx.room.migration.bundle;

import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
/* loaded from: classes.dex */
interface SchemaEquality<T> {
    boolean isSchemaEqual(T t);
}
