package com.facebook.react.views.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.TypedValue;
import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.SoftAssertions;
import com.facebook.react.uimanager.PixelUtil;
import com.swmansion.gesturehandler.react.RNGestureHandlerButtonViewManager;
/* loaded from: classes2.dex */
public class ReactDrawableHelper {
    private static final TypedValue sResolveOutValue = new TypedValue();

    @TargetApi(21)
    public static Drawable createDrawableFromJSDescription(Context context, ReadableMap readableMap) {
        String string = readableMap.getString("type");
        if ("ThemeAttrAndroid".equals(string)) {
            String string2 = readableMap.getString("attribute");
            int attrId = getAttrId(context, string2);
            if (context.getTheme().resolveAttribute(attrId, sResolveOutValue, true)) {
                return setRadius(readableMap, getDefaultThemeDrawable(context));
            }
            throw new JSApplicationIllegalArgumentException("Attribute " + string2 + " with id " + attrId + " couldn't be resolved into a drawable");
        } else if ("RippleAndroid".equals(string)) {
            return setRadius(readableMap, getRippleDrawable(context, readableMap));
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid type for android drawable: ", string));
        }
    }

    @TargetApi(21)
    private static int getAttrId(Context context, String str) {
        SoftAssertions.assertNotNull(str);
        if (RNGestureHandlerButtonViewManager.ButtonViewGroup.SELECTABLE_ITEM_BACKGROUND.equals(str)) {
            return 16843534;
        }
        if (!RNGestureHandlerButtonViewManager.ButtonViewGroup.SELECTABLE_ITEM_BACKGROUND_BORDERLESS.equals(str)) {
            return context.getResources().getIdentifier(str, "attr", "android");
        }
        return 16843868;
    }

    private static int getColor(Context context, ReadableMap readableMap) {
        if (readableMap.hasKey("color") && !readableMap.isNull("color")) {
            return readableMap.getInt("color");
        }
        if (context.getTheme().resolveAttribute(16843820, sResolveOutValue, true)) {
            return context.getResources().getColor(sResolveOutValue.resourceId);
        }
        throw new JSApplicationIllegalArgumentException("Attribute colorControlHighlight couldn't be resolved into a drawable");
    }

    private static Drawable getDefaultThemeDrawable(Context context) {
        int i = Build.VERSION.SDK_INT;
        return context.getResources().getDrawable(sResolveOutValue.resourceId, context.getTheme());
    }

    @Nullable
    private static Drawable getMask(ReadableMap readableMap) {
        if (!readableMap.hasKey("borderless") || readableMap.isNull("borderless") || !readableMap.getBoolean("borderless")) {
            return new ColorDrawable(-1);
        }
        return null;
    }

    private static RippleDrawable getRippleDrawable(Context context, ReadableMap readableMap) {
        return new RippleDrawable(new ColorStateList(new int[][]{new int[0]}, new int[]{getColor(context, readableMap)}), null, getMask(readableMap));
    }

    private static Drawable setRadius(ReadableMap readableMap, Drawable drawable) {
        int i = Build.VERSION.SDK_INT;
        if (readableMap.hasKey("rippleRadius") && (drawable instanceof RippleDrawable)) {
            ((RippleDrawable) drawable).setRadius((int) PixelUtil.toPixelFromDIP(readableMap.getDouble("rippleRadius")));
        }
        return drawable;
    }
}
