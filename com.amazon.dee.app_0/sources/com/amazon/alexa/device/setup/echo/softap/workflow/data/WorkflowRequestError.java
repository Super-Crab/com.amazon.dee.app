package com.amazon.alexa.device.setup.echo.softap.workflow.data;
/* loaded from: classes6.dex */
public class WorkflowRequestError {
    private final int attempt;
    private final Throwable throwable;

    public WorkflowRequestError(Throwable th, int i) {
        this.attempt = i;
        this.throwable = th;
    }

    public int getAttempt() {
        return this.attempt;
    }

    public Throwable getThrowable() {
        return this.throwable;
    }
}
