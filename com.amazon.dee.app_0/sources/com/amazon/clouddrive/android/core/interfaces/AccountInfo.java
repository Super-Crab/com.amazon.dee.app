package com.amazon.clouddrive.android.core.interfaces;

import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
/* loaded from: classes11.dex */
public interface AccountInfo {
    @Nullable
    String getAccountId();

    @Nullable
    @WorkerThread
    String getCountryOfResidence();

    @Nullable
    @WorkerThread
    String getPreferredMarketplace();
}
