package com.amazon.deecomms.notifications;

import android.os.Bundle;
import com.amazon.deecomms.common.processor.Processor;
import com.amazon.deecomms.common.processor.Task;
/* loaded from: classes12.dex */
public final class PushProcessor extends Processor<PushProcessStatus, Bundle> {
    private PushProcessor() {
    }

    public static PushProcessor create() {
        return new PushProcessor();
    }

    @Override // com.amazon.deecomms.common.processor.Processor
    public PushProcessor add(Task<PushProcessStatus, Bundle> task) {
        super.add((Task) task);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.deecomms.common.processor.Processor
    public boolean terminateEarly(PushProcessStatus pushProcessStatus) {
        return pushProcessStatus != PushProcessStatus.CONTINUE;
    }
}
