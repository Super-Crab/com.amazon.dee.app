package com.amazon.identity.auth.device.workflow;

import android.content.Context;
import android.net.Uri;
import com.amazon.identity.auth.device.interactive.InteractiveListener;
import com.amazon.identity.auth.device.interactive.InteractiveRequestRecord;
import com.amazon.identity.auth.map.device.utils.MAPLog;
import com.android.tools.r8.GeneratedOutlineSupport1;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public abstract class BaseWorkflowListener<T, U, V> implements InteractiveListener<T, U, V> {
    private static final String LOG_TAG = "com.amazon.identity.auth.device.workflow.BaseWorkflowListener";

    @Override // com.amazon.identity.auth.device.interactive.InternalInteractiveListener
    public final void onRequestCancel(Context context, InteractiveRequestRecord interactiveRequestRecord, WorkflowCancellation workflowCancellation) {
        MAPLog.d(LOG_TAG, "onRequestCancel");
        onWorkflowCancel(context, interactiveRequestRecord, workflowCancellation);
    }

    @Override // com.amazon.identity.auth.device.interactive.InternalInteractiveListener
    public final void onRequestCompletion(Context context, InteractiveRequestRecord interactiveRequestRecord, Uri uri) {
        WorkflowResponse workflowResponse = new WorkflowResponse(uri);
        if (workflowResponse.isError()) {
            String str = LOG_TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onRequestCompletion failure: ");
            outline107.append(workflowResponse.getError().getMessage());
            MAPLog.d(str, outline107.toString());
            onWorkflowError(context, interactiveRequestRecord, workflowResponse.getError());
            return;
        }
        String str2 = LOG_TAG;
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("result=");
        outline1072.append(workflowResponse.getResultJson());
        MAPLog.pii(str2, "onRequestCompletion success", outline1072.toString());
        onWorkflowSuccess(context, interactiveRequestRecord, workflowResponse.getResultJson());
    }

    @Override // com.amazon.identity.auth.device.interactive.InternalInteractiveListener
    public final void onRequestError(Context context, InteractiveRequestRecord interactiveRequestRecord, Exception exc) {
        String str = LOG_TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("onRequestError: ");
        outline107.append(exc.getMessage());
        MAPLog.d(str, outline107.toString());
        onWorkflowError(context, interactiveRequestRecord, exc);
    }

    protected abstract void onWorkflowCancel(Context context, InteractiveRequestRecord interactiveRequestRecord, WorkflowCancellation workflowCancellation);

    protected abstract void onWorkflowError(Context context, InteractiveRequestRecord interactiveRequestRecord, Exception exc);

    protected abstract void onWorkflowSuccess(Context context, InteractiveRequestRecord interactiveRequestRecord, JSONObject jSONObject);
}
