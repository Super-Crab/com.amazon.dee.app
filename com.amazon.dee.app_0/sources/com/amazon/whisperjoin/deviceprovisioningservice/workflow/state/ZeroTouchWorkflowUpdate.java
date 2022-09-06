package com.amazon.whisperjoin.deviceprovisioningservice.workflow.state;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Objects;
import java.util.Locale;
import javax.annotation.Nullable;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.apache.logging.log4j.util.Chars;
/* loaded from: classes13.dex */
public class ZeroTouchWorkflowUpdate {
    private final String mLocalWorkflowIdentifier;
    private final Radio mRadio;
    private final State mState;
    private final Throwable mThrowable;
    private final WorkflowType mWorkflowType;

    /* loaded from: classes13.dex */
    public static class Builder {
        private String mLocalWorkflowIdentifier;
        private Radio mRadio;
        private State mState;
        private Throwable mThrowable;
        private WorkflowType mWorkflowType;

        public ZeroTouchWorkflowUpdate createZeroTouchWorkflowUpdate() {
            return new ZeroTouchWorkflowUpdate(this.mState, this.mRadio, this.mWorkflowType, this.mLocalWorkflowIdentifier, this.mThrowable);
        }

        public Builder setLocalWorkflowIdentifier(String str) {
            this.mLocalWorkflowIdentifier = str;
            return this;
        }

        public Builder setRadio(Radio radio) {
            this.mRadio = radio;
            return this;
        }

        public Builder setState(State state) {
            this.mState = state;
            return this;
        }

        public Builder setThrowable(Throwable th) {
            this.mThrowable = th;
            return this;
        }

        public Builder setWorkflowType(WorkflowType workflowType) {
            this.mWorkflowType = workflowType;
            return this;
        }
    }

    /* loaded from: classes13.dex */
    public enum Radio {
        BLE
    }

    /* loaded from: classes13.dex */
    public enum State {
        PREPARING,
        CONNECTING,
        PROVISIONING,
        SUCCESS,
        FAILURE
    }

    /* loaded from: classes13.dex */
    public enum WorkflowType {
        WSS,
        PHILIPS_ZIGBEE
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ZeroTouchWorkflowUpdate.class != obj.getClass()) {
            return false;
        }
        ZeroTouchWorkflowUpdate zeroTouchWorkflowUpdate = (ZeroTouchWorkflowUpdate) obj;
        return this.mState == zeroTouchWorkflowUpdate.mState && this.mRadio == zeroTouchWorkflowUpdate.mRadio && this.mWorkflowType == zeroTouchWorkflowUpdate.mWorkflowType && Objects.equal(this.mLocalWorkflowIdentifier, zeroTouchWorkflowUpdate.mLocalWorkflowIdentifier) && Objects.equal(this.mThrowable, zeroTouchWorkflowUpdate.mThrowable);
    }

    public String getFriendlyMessage() {
        return String.format(Locale.ENGLISH, "[%s] [%s-%s-%s]", this.mState, this.mWorkflowType.name(), this.mRadio.name(), this.mLocalWorkflowIdentifier);
    }

    public String getLocalWorkflowIdentifier() {
        return this.mLocalWorkflowIdentifier;
    }

    public Radio getRadio() {
        return this.mRadio;
    }

    public State getState() {
        return this.mState;
    }

    public Throwable getThrowable() {
        return this.mThrowable;
    }

    public WorkflowType getWorkflowType() {
        return this.mWorkflowType;
    }

    public int hashCode() {
        return Objects.hashCode(this.mState, this.mRadio, this.mWorkflowType, this.mLocalWorkflowIdentifier, this.mThrowable);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ZeroTouchWorkflowUpdate{mState=");
        outline107.append(this.mState);
        outline107.append(", mRadio=");
        outline107.append(this.mRadio);
        outline107.append(", mWorkflowType=");
        outline107.append(this.mWorkflowType);
        outline107.append(", mLocalWorkflowIdentifier='");
        GeneratedOutlineSupport1.outline176(outline107, this.mLocalWorkflowIdentifier, Chars.QUOTE, ", mThrowable=");
        outline107.append(this.mThrowable);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }

    private ZeroTouchWorkflowUpdate(State state, Radio radio, WorkflowType workflowType, String str, @Nullable Throwable th) {
        this.mState = state;
        this.mRadio = radio;
        this.mWorkflowType = workflowType;
        this.mLocalWorkflowIdentifier = str;
        this.mThrowable = th;
    }
}
