package com.airbnb.lottie.model.layer;

import androidx.annotation.Nullable;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.model.animatable.AnimatableFloatValue;
import com.airbnb.lottie.model.animatable.AnimatableTextFrame;
import com.airbnb.lottie.model.animatable.AnimatableTextProperties;
import com.airbnb.lottie.model.animatable.AnimatableTransform;
import com.airbnb.lottie.model.content.ContentModel;
import com.airbnb.lottie.model.content.Mask;
import com.airbnb.lottie.value.Keyframe;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class Layer {
    private final LottieComposition composition;
    private final boolean hidden;
    private final List<Keyframe<Float>> inOutKeyframes;
    private final long layerId;
    private final String layerName;
    private final LayerType layerType;
    private final List<Mask> masks;
    private final MatteType matteType;
    private final long parentId;
    private final int preCompHeight;
    private final int preCompWidth;
    @Nullable
    private final String refId;
    private final List<ContentModel> shapes;
    private final int solidColor;
    private final int solidHeight;
    private final int solidWidth;
    private final float startFrame;
    @Nullable
    private final AnimatableTextFrame text;
    @Nullable
    private final AnimatableTextProperties textProperties;
    @Nullable
    private final AnimatableFloatValue timeRemapping;
    private final float timeStretch;
    private final AnimatableTransform transform;

    /* loaded from: classes.dex */
    public enum LayerType {
        PRE_COMP,
        SOLID,
        IMAGE,
        NULL,
        SHAPE,
        TEXT,
        UNKNOWN
    }

    /* loaded from: classes.dex */
    public enum MatteType {
        NONE,
        ADD,
        INVERT,
        UNKNOWN
    }

    public Layer(List<ContentModel> list, LottieComposition lottieComposition, String str, long j, LayerType layerType, long j2, @Nullable String str2, List<Mask> list2, AnimatableTransform animatableTransform, int i, int i2, int i3, float f, float f2, int i4, int i5, @Nullable AnimatableTextFrame animatableTextFrame, @Nullable AnimatableTextProperties animatableTextProperties, List<Keyframe<Float>> list3, MatteType matteType, @Nullable AnimatableFloatValue animatableFloatValue, boolean z) {
        this.shapes = list;
        this.composition = lottieComposition;
        this.layerName = str;
        this.layerId = j;
        this.layerType = layerType;
        this.parentId = j2;
        this.refId = str2;
        this.masks = list2;
        this.transform = animatableTransform;
        this.solidWidth = i;
        this.solidHeight = i2;
        this.solidColor = i3;
        this.timeStretch = f;
        this.startFrame = f2;
        this.preCompWidth = i4;
        this.preCompHeight = i5;
        this.text = animatableTextFrame;
        this.textProperties = animatableTextProperties;
        this.inOutKeyframes = list3;
        this.matteType = matteType;
        this.timeRemapping = animatableFloatValue;
        this.hidden = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public LottieComposition getComposition() {
        return this.composition;
    }

    public long getId() {
        return this.layerId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Keyframe<Float>> getInOutKeyframes() {
        return this.inOutKeyframes;
    }

    public LayerType getLayerType() {
        return this.layerType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<Mask> getMasks() {
        return this.masks;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MatteType getMatteType() {
        return this.matteType;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getName() {
        return this.layerName;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long getParentId() {
        return this.parentId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPreCompHeight() {
        return this.preCompHeight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPreCompWidth() {
        return this.preCompWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public String getRefId() {
        return this.refId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public List<ContentModel> getShapes() {
        return this.shapes;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSolidColor() {
        return this.solidColor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSolidHeight() {
        return this.solidHeight;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSolidWidth() {
        return this.solidWidth;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getStartProgress() {
        return this.startFrame / this.composition.getDurationFrames();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public AnimatableTextFrame getText() {
        return this.text;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public AnimatableTextProperties getTextProperties() {
        return this.textProperties;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public AnimatableFloatValue getTimeRemapping() {
        return this.timeRemapping;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getTimeStretch() {
        return this.timeStretch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AnimatableTransform getTransform() {
        return this.transform;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public String toString() {
        return toString("");
    }

    public String toString(String str) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(str);
        outline107.append(getName());
        outline107.append("\n");
        Layer layerModelForId = this.composition.layerModelForId(getParentId());
        if (layerModelForId != null) {
            outline107.append("\t\tParents: ");
            outline107.append(layerModelForId.getName());
            Layer layerModelForId2 = this.composition.layerModelForId(layerModelForId.getParentId());
            while (layerModelForId2 != null) {
                outline107.append("->");
                outline107.append(layerModelForId2.getName());
                layerModelForId2 = this.composition.layerModelForId(layerModelForId2.getParentId());
            }
            outline107.append(str);
            outline107.append("\n");
        }
        if (!getMasks().isEmpty()) {
            outline107.append(str);
            outline107.append("\tMasks: ");
            outline107.append(getMasks().size());
            outline107.append("\n");
        }
        if (getSolidWidth() != 0 && getSolidHeight() != 0) {
            outline107.append(str);
            outline107.append("\tBackground: ");
            outline107.append(String.format(Locale.US, "%dx%d %X\n", Integer.valueOf(getSolidWidth()), Integer.valueOf(getSolidHeight()), Integer.valueOf(getSolidColor())));
        }
        if (!this.shapes.isEmpty()) {
            outline107.append(str);
            outline107.append("\tShapes:\n");
            for (ContentModel contentModel : this.shapes) {
                outline107.append(str);
                outline107.append("\t\t");
                outline107.append(contentModel);
                outline107.append("\n");
            }
        }
        return outline107.toString();
    }
}
