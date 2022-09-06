package com.facebook.react.animated;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes2.dex */
class TrackingAnimatedNode extends AnimatedNode {
    private final JavaOnlyMap mAnimationConfig;
    private final int mAnimationId;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final int mToValueNode;
    private final int mValueNode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrackingAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        this.mAnimationId = readableMap.getInt("animationId");
        this.mToValueNode = readableMap.getInt("toValue");
        this.mValueNode = readableMap.getInt("value");
        this.mAnimationConfig = JavaOnlyMap.deepClone(readableMap.mo6945getMap("animationConfig"));
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TrackingAnimatedNode[");
        outline107.append(this.mTag);
        outline107.append("]: animationID: ");
        outline107.append(this.mAnimationId);
        outline107.append(" toValueNode: ");
        outline107.append(this.mToValueNode);
        outline107.append(" valueNode: ");
        outline107.append(this.mValueNode);
        outline107.append(" animationConfig: ");
        outline107.append(this.mAnimationConfig);
        return outline107.toString();
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        this.mAnimationConfig.putDouble("toValue", ((ValueAnimatedNode) this.mNativeAnimatedNodesManager.getNodeById(this.mToValueNode)).getValue());
        this.mNativeAnimatedNodesManager.startAnimatingNode(this.mAnimationId, this.mValueNode, this.mAnimationConfig, null);
    }
}
