package com.facebook.react.animated;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JSApplicationCausedNativeException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes2.dex */
class SubtractionAnimatedNode extends ValueAnimatedNode {
    private final int[] mInputNodes;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;

    public SubtractionAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
        ReadableArray array = readableMap.getArray("input");
        this.mInputNodes = new int[array.size()];
        int i = 0;
        while (true) {
            int[] iArr = this.mInputNodes;
            if (i < iArr.length) {
                iArr[i] = array.getInt(i);
                i++;
            } else {
                return;
            }
        }
    }

    @Override // com.facebook.react.animated.ValueAnimatedNode, com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SubtractionAnimatedNode[");
        outline107.append(this.mTag);
        outline107.append("]: input nodes: ");
        int[] iArr = this.mInputNodes;
        outline107.append(iArr != null ? iArr.toString() : "null");
        outline107.append(" - super: ");
        outline107.append(super.prettyPrint());
        return outline107.toString();
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public void update() {
        int i = 0;
        while (true) {
            int[] iArr = this.mInputNodes;
            if (i >= iArr.length) {
                return;
            }
            AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(iArr[i]);
            if (nodeById == null || !(nodeById instanceof ValueAnimatedNode)) {
                break;
            }
            ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
            double value = valueAnimatedNode.getValue();
            if (i == 0) {
                this.mValue = value;
            } else {
                this.mValue -= valueAnimatedNode.getValue();
            }
            i++;
        }
        throw new JSApplicationCausedNativeException("Illegal node ID set as an input for Animated.subtract node");
    }
}
