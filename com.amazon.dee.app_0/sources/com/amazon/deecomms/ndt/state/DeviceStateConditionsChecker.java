package com.amazon.deecomms.ndt.state;

import androidx.annotation.NonNull;
import com.amazon.deecomms.ndt.model.DeviceModel;
/* compiled from: DeviceState.java */
/* loaded from: classes12.dex */
interface DeviceStateConditionsChecker {
    boolean checkMainCondition(@NonNull DeviceModel deviceModel);

    boolean checkPreConditions(@NonNull DeviceModel deviceModel);
}
