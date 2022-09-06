package com.amazon.whisperjoin.deviceprovisioningservice.util;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;
@TargetApi(21)
/* loaded from: classes13.dex */
public class JobInfoProvider {
    public JobInfo provide(Context context, int i, Class cls, long j, long j2, long j3, boolean z) {
        return provide(context, i, cls, j, j2, j3, z, new PersistableBundle());
    }

    public JobInfo provide(Context context, int i, Class cls, long j, long j2, long j3, boolean z, PersistableBundle persistableBundle) {
        return new JobInfo.Builder(i, new ComponentName(context, cls)).setRequiredNetworkType(1).setBackoffCriteria(j3, 1).setMinimumLatency(j).setOverrideDeadline(j2).setPersisted(z).setExtras(persistableBundle).build();
    }
}
