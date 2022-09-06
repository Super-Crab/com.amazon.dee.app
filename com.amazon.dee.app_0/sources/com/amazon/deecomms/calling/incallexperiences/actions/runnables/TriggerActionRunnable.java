package com.amazon.deecomms.calling.incallexperiences.actions.runnables;

import com.amazon.deecomms.calling.incallexperiences.model.TriggerActionResponse;
/* loaded from: classes12.dex */
public abstract class TriggerActionRunnable implements Runnable {
    protected TriggerActionResponse.TriggerStatusCode statusCode;

    public void run(TriggerActionResponse.TriggerStatusCode triggerStatusCode) {
        this.statusCode = triggerStatusCode;
        run();
    }
}
