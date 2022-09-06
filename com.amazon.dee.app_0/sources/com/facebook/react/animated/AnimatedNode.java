package com.facebook.react.animated;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.infer.annotation.Assertions;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes2.dex */
abstract class AnimatedNode {
    private static final int DEFAULT_ANIMATED_NODE_CHILD_COUNT = 1;
    public static final int INITIAL_BFS_COLOR = 0;
    @Nullable
    List<AnimatedNode> mChildren;
    int mActiveIncomingNodes = 0;
    int mBFSColor = 0;
    int mTag = -1;

    public final void addChild(AnimatedNode animatedNode) {
        if (this.mChildren == null) {
            this.mChildren = new ArrayList(1);
        }
        ((List) Assertions.assertNotNull(this.mChildren)).add(animatedNode);
        animatedNode.onAttachedToNode(this);
    }

    public void onAttachedToNode(AnimatedNode animatedNode) {
    }

    public void onDetachedFromNode(AnimatedNode animatedNode) {
    }

    public abstract String prettyPrint();

    public String prettyPrintWithChildren() {
        String str;
        List<AnimatedNode> list = this.mChildren;
        String str2 = "";
        if (list == null || list.size() <= 0) {
            str = str2;
        } else {
            str = str2;
            for (AnimatedNode animatedNode : this.mChildren) {
                StringBuilder outline113 = GeneratedOutlineSupport1.outline113(str, " ");
                outline113.append(animatedNode.mTag);
                str = outline113.toString();
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(prettyPrint());
        if (str.length() > 0) {
            str2 = GeneratedOutlineSupport1.outline72(" children: ", str);
        }
        sb.append(str2);
        return sb.toString();
    }

    public final void removeChild(AnimatedNode animatedNode) {
        if (this.mChildren == null) {
            return;
        }
        animatedNode.onDetachedFromNode(this);
        this.mChildren.remove(animatedNode);
    }

    public void update() {
    }
}
