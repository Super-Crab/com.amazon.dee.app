package com.amazon.communication;

import com.amazon.communication.PowerManagerWrapper;
import com.dp.utils.DpBackgroundThreadFactory;
import com.dp.utils.DpThreadFactory;
/* loaded from: classes12.dex */
public class ProtocolSocketAffinitizedBackgroundWorkExecutor extends ProtocolSocketAffinitizedWorkExecutor {
    public ProtocolSocketAffinitizedBackgroundWorkExecutor(int i, PowerManagerWrapper.WakeLock wakeLock) {
        super(i, wakeLock);
    }

    @Override // com.amazon.communication.ProtocolSocketAffinitizedWorkExecutor
    protected DpThreadFactory newThreadFactory(String str) {
        return new DpBackgroundThreadFactory(str);
    }
}
