package androidx.room.util;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class SneakyThrow {
    private SneakyThrow() {
    }

    public static void reThrow(@NonNull Exception e) {
        sneakyThrow(e);
    }

    private static <E extends Throwable> void sneakyThrow(@NonNull Throwable e) throws Throwable {
        throw e;
    }
}
