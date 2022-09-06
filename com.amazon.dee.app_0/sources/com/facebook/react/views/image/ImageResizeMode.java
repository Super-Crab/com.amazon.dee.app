package com.facebook.react.views.image;

import android.graphics.Shader;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
/* loaded from: classes2.dex */
public class ImageResizeMode {
    private static final String RESIZE_MODE_CENTER = "center";
    private static final String RESIZE_MODE_CONTAIN = "contain";
    private static final String RESIZE_MODE_COVER = "cover";
    private static final String RESIZE_MODE_REPEAT = "repeat";
    private static final String RESIZE_MODE_STRETCH = "stretch";

    public static Shader.TileMode defaultTileMode() {
        return Shader.TileMode.CLAMP;
    }

    public static ScalingUtils.ScaleType defaultValue() {
        return ScalingUtils.ScaleType.CENTER_CROP;
    }

    public static ScalingUtils.ScaleType toScaleType(@Nullable String str) {
        if (RESIZE_MODE_CONTAIN.equals(str)) {
            return ScalingUtils.ScaleType.FIT_CENTER;
        }
        if (RESIZE_MODE_COVER.equals(str)) {
            return ScalingUtils.ScaleType.CENTER_CROP;
        }
        if (RESIZE_MODE_STRETCH.equals(str)) {
            return ScalingUtils.ScaleType.FIT_XY;
        }
        if ("center".equals(str)) {
            return ScalingUtils.ScaleType.CENTER_INSIDE;
        }
        if ("repeat".equals(str)) {
            return ScaleTypeStartInside.INSTANCE;
        }
        if (str == null) {
            return defaultValue();
        }
        throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline75("Invalid resize mode: '", str, "'"));
    }

    public static Shader.TileMode toTileMode(@Nullable String str) {
        if (!RESIZE_MODE_CONTAIN.equals(str) && !RESIZE_MODE_COVER.equals(str) && !RESIZE_MODE_STRETCH.equals(str) && !"center".equals(str)) {
            if ("repeat".equals(str)) {
                return Shader.TileMode.REPEAT;
            }
            if (str == null) {
                return defaultTileMode();
            }
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline75("Invalid resize mode: '", str, "'"));
        }
        return Shader.TileMode.CLAMP;
    }
}
