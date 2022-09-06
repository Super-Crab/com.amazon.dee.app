package com.amazon.device.crashmanager;

import android.content.pm.ApplicationInfo;
import com.amazon.device.utils.det.Domain;
import com.amazon.device.utils.det.DomainChooser;
/* loaded from: classes12.dex */
class ThirdPartyDomainChooser implements DomainChooser {
    private final boolean mAppIsDebuggable;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThirdPartyDomainChooser(ApplicationInfo applicationInfo) {
        this.mAppIsDebuggable = (applicationInfo.flags & 2) != 0;
    }

    @Override // com.amazon.device.utils.det.DomainChooser
    public Domain chooseDomain() {
        if (!this.mAppIsDebuggable) {
            return Domain.PROD;
        }
        return Domain.BETA;
    }
}
