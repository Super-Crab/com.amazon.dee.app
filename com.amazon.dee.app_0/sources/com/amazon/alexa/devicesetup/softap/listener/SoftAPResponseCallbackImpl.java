package com.amazon.alexa.devicesetup.softap.listener;

import com.amazon.alexa.device.setup.echo.softap.workflow.data.WorkflowStatusUpdate;
import com.amazon.alexa.device.setup.echo.softap.workflow.manager.SoftAPResponseCallback;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.protocols.service.api.LazyComponent;
import com.google.gson.Gson;
/* loaded from: classes7.dex */
public class SoftAPResponseCallbackImpl implements SoftAPResponseCallback {
    private static final String TAG = "SOFTAP::RESPONSE";
    private LazyComponent<EventBus> eventBus;
    private Gson gson;

    public SoftAPResponseCallbackImpl(Gson gson, LazyComponent<EventBus> lazyComponent) {
        this.eventBus = lazyComponent;
        this.gson = gson;
    }

    private void logAndPublish(WorkflowStatusUpdate workflowStatusUpdate) {
        this.eventBus.mo10268get().publish(new Message.Builder().setEventType("echosoftap::response").setPayload(this.gson.toJson(workflowStatusUpdate)).build());
    }

    @Override // com.amazon.alexa.device.setup.echo.softap.workflow.manager.SoftAPResponseCallback
    public void sendResponse(WorkflowStatusUpdate workflowStatusUpdate) {
        logAndPublish(workflowStatusUpdate);
    }
}
