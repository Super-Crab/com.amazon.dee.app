package com.amazon.deecomms.common.sip;

import com.amazon.comms.calling.service.DeviceCallingService;
/* loaded from: classes12.dex */
public interface SipStatusListener {
    void onSipStatusChanged(DeviceCallingService.State state);
}
