package com.airbnb.lottie.model.content;

import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieDrawable;
import com.airbnb.lottie.animation.content.Content;
import com.airbnb.lottie.animation.content.MergePathsContent;
import com.airbnb.lottie.model.layer.BaseLayer;
import com.airbnb.lottie.utils.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
public class MergePaths implements ContentModel {
    private final boolean hidden;
    private final MergePathsMode mode;
    private final String name;

    /* loaded from: classes.dex */
    public enum MergePathsMode {
        MERGE,
        ADD,
        SUBTRACT,
        INTERSECT,
        EXCLUDE_INTERSECTIONS;

        public static MergePathsMode forId(int i) {
            if (i != 1) {
                if (i == 2) {
                    return ADD;
                }
                if (i == 3) {
                    return SUBTRACT;
                }
                if (i == 4) {
                    return INTERSECT;
                }
                if (i != 5) {
                    return MERGE;
                }
                return EXCLUDE_INTERSECTIONS;
            }
            return MERGE;
        }
    }

    public MergePaths(String str, MergePathsMode mergePathsMode, boolean z) {
        this.name = str;
        this.mode = mergePathsMode;
        this.hidden = z;
    }

    public MergePathsMode getMode() {
        return this.mode;
    }

    public String getName() {
        return this.name;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    @Override // com.airbnb.lottie.model.content.ContentModel
    @Nullable
    public Content toContent(LottieDrawable lottieDrawable, BaseLayer baseLayer) {
        if (!lottieDrawable.enableMergePathsForKitKatAndAbove()) {
            Logger.warning("Animation contains merge paths but they are disabled.");
            return null;
        }
        return new MergePathsContent(this);
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MergePaths{mode=");
        outline107.append(this.mode);
        outline107.append(JsonReaderKt.END_OBJ);
        return outline107.toString();
    }
}
