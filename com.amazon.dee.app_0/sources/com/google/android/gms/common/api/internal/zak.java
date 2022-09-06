package com.google.android.gms.common.api.internal;

import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import java.util.Set;
/* loaded from: classes2.dex */
public final class zak {
    private int zadd;
    private final ArrayMap<zai<?>, String> zadb = new ArrayMap<>();
    private final TaskCompletionSource<Map<zai<?>, String>> zadc = new TaskCompletionSource<>();
    private boolean zade = false;
    private final ArrayMap<zai<?>, ConnectionResult> zaay = new ArrayMap<>();

    public zak(Iterable<? extends GoogleApi<?>> iterable) {
        for (GoogleApi<?> googleApi : iterable) {
            this.zaay.put(googleApi.zak(), null);
        }
        this.zadd = this.zaay.keySet().size();
    }

    public final Task<Map<zai<?>, String>> getTask() {
        return this.zadc.getTask();
    }

    public final void zaa(zai<?> zaiVar, ConnectionResult connectionResult, @Nullable String str) {
        this.zaay.put(zaiVar, connectionResult);
        this.zadb.put(zaiVar, str);
        this.zadd--;
        if (!connectionResult.isSuccess()) {
            this.zade = true;
        }
        if (this.zadd == 0) {
            if (this.zade) {
                this.zadc.setException(new AvailabilityException(this.zaay));
                return;
            }
            this.zadc.setResult(this.zadb);
        }
    }

    public final Set<zai<?>> zap() {
        return this.zaay.keySet();
    }
}
