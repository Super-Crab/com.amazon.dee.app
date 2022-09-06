package com.facebook.react.animated;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import java.util.HashMap;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class StyleAnimatedNode extends AnimatedNode {
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final Map<String, Integer> mPropMapping;

    /* JADX INFO: Access modifiers changed from: package-private */
    public StyleAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        ReadableMap mo6945getMap = readableMap.mo6945getMap(TtmlNode.TAG_STYLE);
        ReadableMapKeySetIterator keySetIterator = mo6945getMap.keySetIterator();
        this.mPropMapping = new HashMap();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            this.mPropMapping.put(nextKey, Integer.valueOf(mo6945getMap.getInt(nextKey)));
        }
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void collectViewUpdates(JavaOnlyMap javaOnlyMap) {
        for (Map.Entry<String, Integer> entry : this.mPropMapping.entrySet()) {
            AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(entry.getValue().intValue());
            if (nodeById != null) {
                if (nodeById instanceof TransformAnimatedNode) {
                    ((TransformAnimatedNode) nodeById).collectViewUpdates(javaOnlyMap);
                } else if (nodeById instanceof ValueAnimatedNode) {
                    javaOnlyMap.putDouble(entry.getKey(), ((ValueAnimatedNode) nodeById).getValue());
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported type of node used in property node ");
                    outline107.append(nodeById.getClass());
                    throw new IllegalArgumentException(outline107.toString());
                }
            } else {
                throw new IllegalArgumentException("Mapped style node does not exists");
            }
        }
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("StyleAnimatedNode[");
        outline107.append(this.mTag);
        outline107.append("] mPropMapping: ");
        Map<String, Integer> map = this.mPropMapping;
        outline107.append(map != null ? map.toString() : "null");
        return outline107.toString();
    }
}
