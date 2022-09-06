package com.amazon.deecomms.calling.incallexperiences.actions.runnables;

import com.amazon.deecomms.calling.incallexperiences.model.ApplicationAction;
import java.util.List;
/* loaded from: classes12.dex */
public abstract class ApplicationActionsRunnable implements Runnable {
    protected List<ApplicationAction> applicationActions;

    public void run(List<ApplicationAction> list) {
        this.applicationActions = list;
        run();
    }
}
