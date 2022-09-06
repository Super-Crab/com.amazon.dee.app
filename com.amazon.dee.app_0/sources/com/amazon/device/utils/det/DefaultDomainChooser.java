package com.amazon.device.utils.det;

import android.os.Build;
/* loaded from: classes12.dex */
public class DefaultDomainChooser implements DomainChooser {
    @Override // com.amazon.device.utils.det.DomainChooser
    public Domain chooseDomain() {
        if (!Build.TYPE.equals("user") && !Build.TYPE.equals("userdebug")) {
            return Domain.BETA;
        }
        return Domain.PROD;
    }
}
