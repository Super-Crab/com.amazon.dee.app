package com.amazon.whisperjoin.deviceprovisioningservice.workflow.zerotouch;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Objects;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes13.dex */
public class ControllerUpdate {
    private final Event mEvent;
    private final String mMessage;
    private final Throwable mThrowable;

    /* loaded from: classes13.dex */
    public enum Event {
        DISCOVERY_STARTED,
        WORKFLOW_STARTED,
        WORKFLOW_UPDATE,
        WORKFLOW_SUCCESS,
        WORKFLOW_FAILURE,
        DISCOVERY_STOPPED,
        BACKING_OFF,
        TERMINATED
    }

    public ControllerUpdate(Event event, String str) {
        this.mEvent = event;
        this.mMessage = str;
        this.mThrowable = null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ControllerUpdate.class != obj.getClass()) {
            return false;
        }
        ControllerUpdate controllerUpdate = (ControllerUpdate) obj;
        return this.mEvent == controllerUpdate.mEvent && Objects.equal(this.mMessage, controllerUpdate.mMessage) && Objects.equal(this.mThrowable, controllerUpdate.mThrowable);
    }

    public Event getEvent() {
        return this.mEvent;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public Throwable getThrowable() {
        return this.mThrowable;
    }

    public int hashCode() {
        return Objects.hashCode(this.mEvent, this.mMessage, this.mThrowable);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Update{mEvent=");
        outline107.append(this.mEvent);
        outline107.append(", mMessage='");
        GeneratedOutlineSupport1.outline176(outline107, this.mMessage, Chars.QUOTE, ", mThrowable=");
        outline107.append(this.mThrowable);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    public ControllerUpdate(Event event) {
        this.mEvent = event;
        this.mMessage = "";
        this.mThrowable = null;
    }

    public ControllerUpdate(Event event, String str, Throwable th) {
        this.mEvent = event;
        this.mMessage = str;
        this.mThrowable = th;
    }
}
