package com.amazon.alexa.accessory.capabilities.inputevents;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Input;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public final class SimpleInputEvent implements InputEvent {
    private final int deviceId;
    private final Input.InputAction inputAction;
    private final Input.InputBehavior inputBehavior;
    private final Input.InputSource inputSource;

    public SimpleInputEvent(Input.InputAction inputAction, Input.InputSource inputSource, Input.InputBehavior inputBehavior, int i) {
        Preconditions.notNull(inputAction, "inputAction");
        Preconditions.notNull(inputSource, "inputSource");
        Preconditions.notNull(inputBehavior, "inputBehavior");
        Preconditions.notNegative(i, "deviceId");
        this.inputAction = inputAction;
        this.inputSource = inputSource;
        this.inputBehavior = inputBehavior;
        this.deviceId = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SimpleInputEvent.class != obj.getClass()) {
            return false;
        }
        SimpleInputEvent simpleInputEvent = (SimpleInputEvent) obj;
        return this.deviceId == simpleInputEvent.deviceId && this.inputAction == simpleInputEvent.inputAction && this.inputBehavior == simpleInputEvent.inputBehavior && this.inputSource == simpleInputEvent.inputSource;
    }

    @Override // com.amazon.alexa.accessory.capabilities.inputevents.InputEvent
    public int getDeviceId() {
        return this.deviceId;
    }

    @Override // com.amazon.alexa.accessory.capabilities.inputevents.InputEvent
    public Input.InputAction getInputAction() {
        return this.inputAction;
    }

    @Override // com.amazon.alexa.accessory.capabilities.inputevents.InputEvent
    public Input.InputBehavior getInputBehavior() {
        return this.inputBehavior;
    }

    @Override // com.amazon.alexa.accessory.capabilities.inputevents.InputEvent
    public Input.InputSource getInputSource() {
        return this.inputSource;
    }

    public int hashCode() {
        int hashCode = this.inputBehavior.hashCode();
        return ((this.inputSource.hashCode() + ((hashCode + (this.inputAction.hashCode() * 31)) * 31)) * 31) + this.deviceId;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SimpleInputEvent{inputAction=");
        outline107.append(this.inputAction);
        outline107.append(", inputBehavior=");
        outline107.append(this.inputBehavior);
        outline107.append(", inputSource=");
        outline107.append(this.inputSource);
        outline107.append(", deviceId=");
        return GeneratedOutlineSupport1.outline85(outline107, this.deviceId, JsonReaderKt.END_OBJ);
    }
}
