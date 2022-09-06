package com.amazon.photos.discovery.internal.util;

import android.os.SystemClock;
import com.amazon.clouddrive.android.core.interfaces.SystemUtil;
import kotlin.Metadata;
/* compiled from: SystemUtilsImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/amazon/photos/discovery/internal/util/SystemUtilsImpl;", "Lcom/amazon/clouddrive/android/core/interfaces/SystemUtil;", "()V", "currentTimeMillis", "", "elapsedRealTimeMillis", "AndroidPhotosDiscovery_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SystemUtilsImpl implements SystemUtil {
    @Override // com.amazon.clouddrive.android.core.interfaces.SystemUtil
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    @Override // com.amazon.clouddrive.android.core.interfaces.SystemUtil
    public long elapsedRealTimeMillis() {
        return SystemClock.elapsedRealtime();
    }
}
