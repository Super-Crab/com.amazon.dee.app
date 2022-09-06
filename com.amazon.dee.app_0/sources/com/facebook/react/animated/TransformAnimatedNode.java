package com.facebook.react.animated;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JavaOnlyArray;
import com.facebook.react.bridge.JavaOnlyMap;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public class TransformAnimatedNode extends AnimatedNode {
    private final NativeAnimatedNodesManager mNativeAnimatedNodesManager;
    private final List<TransformConfig> mTransformConfigs;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class AnimatedTransformConfig extends TransformConfig {
        public int mNodeTag;

        private AnimatedTransformConfig() {
            super();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class StaticTransformConfig extends TransformConfig {
        public double mValue;

        private StaticTransformConfig() {
            super();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class TransformConfig {
        public String mProperty;

        private TransformConfig() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TransformAnimatedNode(ReadableMap readableMap, NativeAnimatedNodesManager nativeAnimatedNodesManager) {
        ReadableArray array = readableMap.getArray("transforms");
        this.mTransformConfigs = new ArrayList(array.size());
        for (int i = 0; i < array.size(); i++) {
            ReadableMap mo6944getMap = array.mo6944getMap(i);
            String string = mo6944getMap.getString("property");
            if (mo6944getMap.getString("type").equals("animated")) {
                AnimatedTransformConfig animatedTransformConfig = new AnimatedTransformConfig();
                animatedTransformConfig.mProperty = string;
                animatedTransformConfig.mNodeTag = mo6944getMap.getInt("nodeTag");
                this.mTransformConfigs.add(animatedTransformConfig);
            } else {
                StaticTransformConfig staticTransformConfig = new StaticTransformConfig();
                staticTransformConfig.mProperty = string;
                staticTransformConfig.mValue = mo6944getMap.getDouble("value");
                this.mTransformConfigs.add(staticTransformConfig);
            }
        }
        this.mNativeAnimatedNodesManager = nativeAnimatedNodesManager;
    }

    public void collectViewUpdates(JavaOnlyMap javaOnlyMap) {
        double d;
        ArrayList arrayList = new ArrayList(this.mTransformConfigs.size());
        for (TransformConfig transformConfig : this.mTransformConfigs) {
            if (transformConfig instanceof AnimatedTransformConfig) {
                AnimatedNode nodeById = this.mNativeAnimatedNodesManager.getNodeById(((AnimatedTransformConfig) transformConfig).mNodeTag);
                if (nodeById != null) {
                    if (nodeById instanceof ValueAnimatedNode) {
                        d = ((ValueAnimatedNode) nodeById).getValue();
                    } else {
                        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unsupported type of node used as a transform child node ");
                        outline107.append(nodeById.getClass());
                        throw new IllegalArgumentException(outline107.toString());
                    }
                } else {
                    throw new IllegalArgumentException("Mapped style node does not exists");
                }
            } else {
                d = ((StaticTransformConfig) transformConfig).mValue;
            }
            arrayList.add(JavaOnlyMap.of(transformConfig.mProperty, Double.valueOf(d)));
        }
        javaOnlyMap.putArray(ViewProps.TRANSFORM, JavaOnlyArray.from(arrayList));
    }

    @Override // com.facebook.react.animated.AnimatedNode
    public String prettyPrint() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TransformAnimatedNode[");
        outline107.append(this.mTag);
        outline107.append("]: mTransformConfigs: ");
        List<TransformConfig> list = this.mTransformConfigs;
        outline107.append(list != null ? list.toString() : "null");
        return outline107.toString();
    }
}
