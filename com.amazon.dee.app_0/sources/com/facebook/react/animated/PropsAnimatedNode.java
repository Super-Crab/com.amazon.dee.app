package com.facebook.react.animated;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.uimanager.common.ViewUtil;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
class PropsAnimatedNode extends AnimatedNode {
    private int mConnectedViewTag = -1;
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final JavaOnlyMap mPropMap;
    private final Map<String, Integer> mPropNodeMapping;
    @Nullable
    private UIManager mUIManager;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PropsAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        ReadableMap mo6945getMap = readableMap.mo6945getMap("props");
        ReadableMapKeySetIterator keySetIterator = mo6945getMap.keySetIterator();
        this.mPropNodeMapping = new HashMap();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            this.mPropNodeMapping.put(nextKey, Integer.valueOf(mo6945getMap.getInt(nextKey)));
        }
        this.mPropMap = new JavaOnlyMap();
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void connectToView(int i, UIManager uIManager) {
        if (this.mConnectedViewTag == -1) {
            this.mConnectedViewTag = i;
            this.mUIManager = uIManager;
            return;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Animated node ");
        outline107.append(this.mTag);
        outline107.append(" is already attached to a view: ");
        outline107.append(this.mConnectedViewTag);
        throw new JSApplicationIllegalArgumentException(outline107.toString());
    }

    public void disconnectFromView(int i) {
        int i2 = this.mConnectedViewTag;
        if (i2 != i && i2 != -1) {
            StringBuilder outline109 = GeneratedOutlineSupport1.outline109("Attempting to disconnect view that has not been connected with the given animated node: ", i, " but is connected to view ");
            outline109.append(this.mConnectedViewTag);
            throw new JSApplicationIllegalArgumentException(outline109.toString());
        }
        this.mConnectedViewTag = -1;
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("PropsAnimatedNode[");
        outline107.append(this.mTag);
        outline107.append("] connectedViewTag: ");
        outline107.append(this.mConnectedViewTag);
        outline107.append(" mPropNodeMapping: ");
        Map<String, Integer> map = this.mPropNodeMapping;
        String str = "null";
        outline107.append(map != null ? map.toString() : str);
        outline107.append(" mPropMap: ");
        JavaOnlyMap javaOnlyMap = this.mPropMap;
        if (javaOnlyMap != null) {
            str = javaOnlyMap.toString();
        }
        outline107.append(str);
        return outline107.toString();
    }

    public void restoreDefaultValues() {
        int i = this.mConnectedViewTag;
        if (i == -1 || ViewUtil.getUIManagerType(i) == 2) {
            return;
        }
        ReadableMapKeySetIterator keySetIterator = this.mPropMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            this.mPropMap.putNull(keySetIterator.nextKey());
        }
        this.mUIManager.synchronouslyUpdateViewOnUIThread(this.mConnectedViewTag, this.mPropMap);
    }

    public final void updateView() {
        if (this.mConnectedViewTag == -1) {
            return;
        }
        for (Map.Entry<String, Integer> entry : this.mPropNodeMapping.entrySet()) {
            AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(entry.getValue().intValue());
            if (nodeById != null) {
                if (nodeById instanceof StyleAnimatedNode) {
                    ((StyleAnimatedNode) nodeById).collectViewUpdates(this.mPropMap);
                } else if (nodeById instanceof ValueAnimatedNode) {
                    ValueAnimatedNode valueAnimatedNode = (ValueAnimatedNode) nodeById;
                    Object animatedObject = valueAnimatedNode.getAnimatedObject();
                    if (animatedObject instanceof String) {
                        this.mPropMap.putString(entry.getKey(), (String) animatedObject);
                    } else {
                        this.mPropMap.putDouble(entry.getKey(), valueAnimatedNode.getValue());
                    }
                } else {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported type of node used in property node ");
                    outline107.append(nodeById.getClass());
                    throw new IllegalArgumentException(outline107.toString());
                }
            } else {
                throw new IllegalArgumentException("Mapped property node does not exists");
            }
        }
        this.mUIManager.synchronouslyUpdateViewOnUIThread(this.mConnectedViewTag, this.mPropMap);
    }
}
