package com.google.android.gms.tasks;

import androidx.annotation.NonNull;
/* loaded from: classes2.dex */
interface zzq<TResult> {
    void cancel();

    void onComplete(@NonNull Task<TResult> task);
}
