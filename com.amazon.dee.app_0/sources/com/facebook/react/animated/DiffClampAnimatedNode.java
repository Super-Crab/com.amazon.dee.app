package com.facebook.react.animated;

import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes2.dex */
class DiffClampAnimatedNode extends ValueAnimatedNode {
    private final int mInputNodeTag;
    private double mLastValue = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    private final double mMax;
    private final double mMin;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public DiffClampAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        this.mInputNodeTag = readableMap.getInt("input");
        this.mMin = readableMap.getDouble(ReactProperties.GEOFENCE_MINIMUM_RANGE);
        this.mMax = readableMap.getDouble(ReactProperties.GEOFENCE_MAXIMUM_RANGE);
        this.mValue = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    private double getInputNodeValue() {
        AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNodeTag);
        if (nodeById != null && (nodeById instanceof ValueAnimatedNode)) {
            return ((ValueAnimatedNode) nodeById).getValue();
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.DiffClamp node");
    }

    @Override // com.facebook.react.animated.ValueAnimatedNode, com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("DiffClampAnimatedNode[");
        outline107.append(this.mTag);
        outline107.append("]: InputNodeTag: ");
        outline107.append(this.mInputNodeTag);
        outline107.append(" min: ");
        outline107.append(this.mMin);
        outline107.append(" max: ");
        outline107.append(this.mMax);
        outline107.append(" lastValue: ");
        outline107.append(this.mLastValue);
        outline107.append(" super: ");
        outline107.append(super.prettyPrint());
        return outline107.toString();
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        double inputNodeValue = getInputNodeValue();
        double d = inputNodeValue - this.mLastValue;
        this.mLastValue = inputNodeValue;
        this.mValue = Math.min(Math.max(this.mValue + d, this.mMin), this.mMax);
    }
}
