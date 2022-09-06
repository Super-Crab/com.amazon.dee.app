package com.amazon.alexa.device.setup.echo.softap.workflow.data;

import com.amazon.alexa.device.setup.echo.softap.workflow.data.WorkflowStateData;
import com.amazon.alexa.device.setup.echo.softap.workflow.manager.WorkflowState;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
public class WorkflowStatusUpdate {
    final WorkflowState workflowState;
    final WorkflowStateData workflowStateData;

    public WorkflowStatusUpdate(WorkflowState workflowState, WorkflowStateData workflowStateData) {
        this.workflowState = workflowState;
        this.workflowStateData = workflowStateData;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WorkflowStatusUpdate : { workflowState: ");
        outline107.append(this.workflowState.toString());
        outline107.append(", ");
        outline107.append(this.workflowStateData.toString());
        outline107.append(" }");
        return outline107.toString();
    }

    public WorkflowStatusUpdate(WorkflowState workflowState, Throwable th) {
        this.workflowState = workflowState;
        this.workflowStateData = new WorkflowStateData(th, WorkflowStateData.State.ERROR);
    }
}
