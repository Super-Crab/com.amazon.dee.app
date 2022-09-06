package com.facebook.react.animated;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes2.dex */
class ModulusAnimatedNode extends ValueAnimatedNode {
    private final int mInputNode;
    private final double mModulus;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public ModulusAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        this.mInputNode = readableMap.getInt("input");
        this.mModulus = readableMap.getDouble("modulus");
    }

    @Override // com.facebook.react.animated.ValueAnimatedNode, com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("NativeAnimatedNodesManager[");
        outline107.append(this.mTag);
        outline107.append("] inputNode: ");
        outline107.append(this.mInputNode);
        outline107.append(" modulus: ");
        outline107.append(this.mModulus);
        outline107.append(" super: ");
        outline107.append(super.prettyPrint());
        return outline107.toString();
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(this.mInputNode);
        if (nodeById != null && (nodeById instanceof ValueAnimatedNode)) {
            double value = ((ValueAnimatedNode) nodeById).getValue();
            double d = this.mModulus;
            this.mValue = ((value % d) + d) % d;
            return;
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.modulus node");
    }
}
