package com.amazon.deecomms.util;

import android.os.Build;
/* loaded from: classes12.dex */
public class BuildVersionProviderImpl implements IBuildVersionProvider {
    @Override // com.amazon.deecomms.util.IBuildVersionProvider
    public int getCurrentBuildVersion() {
        return Build.VERSION.SDK_INT;
    }
}
