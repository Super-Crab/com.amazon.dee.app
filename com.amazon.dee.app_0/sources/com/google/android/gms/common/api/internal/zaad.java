package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public final class zaad implements OnCompleteListener<TResult> {
    private final /* synthetic */ zaab zafn;
    private final /* synthetic */ TaskCompletionSource zafo;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zaad(zaab zaabVar, TaskCompletionSource taskCompletionSource) {
        this.zafn = zaabVar;
        this.zafo = taskCompletionSource;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(@NonNull Task<TResult> task) {
        Map map;
        map = this.zafn.zafl;
        map.remove(this.zafo);
    }
}
