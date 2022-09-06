package androidx.work.impl;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.work.impl.model.WorkSpec;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public interface Scheduler {
    public static final int MAX_SCHEDULER_LIMIT = 50;

    void cancel(@NonNull String str);

    void schedule(@NonNull WorkSpec... workSpecArr);
}
