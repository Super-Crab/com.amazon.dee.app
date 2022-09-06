package com.facebook.react.uimanager;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.facebook.common.logging.FLog;
import com.facebook.react.R;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.MatrixMathHelper;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.util.ReactFindViewUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class BaseViewManager<T extends View, C extends LayoutShadowNode> extends ViewManager<T, C> implements BaseViewManagerInterface<T> {
    private static final int PERSPECTIVE_ARRAY_INVERTED_CAMERA_DISTANCE_INDEX = 2;
    private static final String STATE_BUSY = "busy";
    private static final String STATE_CHECKED = "checked";
    private static final String STATE_EXPANDED = "expanded";
    private static final String STATE_MIXED = "mixed";
    private static final float CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER = (float) Math.sqrt(5.0d);
    private static MatrixMathHelper.MatrixDecompositionContext sMatrixDecompositionContext = new MatrixMathHelper.MatrixDecompositionContext();
    private static double[] sTransformDecompositionArray = new double[16];
    public static final Map<String, Integer> sStateDescription = new HashMap();

    static {
        sStateDescription.put(STATE_BUSY, Integer.valueOf(R.string.state_busy_description));
        sStateDescription.put(STATE_EXPANDED, Integer.valueOf(R.string.state_expanded_description));
        sStateDescription.put("collapsed", Integer.valueOf(R.string.state_collapsed_description));
    }

    private void logUnsupportedPropertyWarning(String str) {
        FLog.w(ReactConstants.TAG, "%s doesn't support property '%s'", getName(), str);
    }

    private static void resetTransformProperty(@NonNull View view) {
        view.setTranslationX(PixelUtil.toPixelFromDIP(0.0f));
        view.setTranslationY(PixelUtil.toPixelFromDIP(0.0f));
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setCameraDistance(0.0f);
    }

    private static float sanitizeFloatPropertyValue(float f) {
        if (f < -3.4028235E38f || f > Float.MAX_VALUE) {
            if (f < -3.4028235E38f || f == Float.NEGATIVE_INFINITY) {
                return -3.4028235E38f;
            }
            if (f > Float.MAX_VALUE || f == Float.POSITIVE_INFINITY) {
                return Float.MAX_VALUE;
            }
            if (Float.isNaN(f)) {
                return 0.0f;
            }
            throw new IllegalStateException("Invalid float property value: " + f);
        }
        return f;
    }

    private static void setTransformProperty(@NonNull View view, ReadableArray readableArray) {
        sMatrixDecompositionContext.reset();
        TransformHelper.processTransform(readableArray, sTransformDecompositionArray);
        MatrixMathHelper.decomposeMatrix(sTransformDecompositionArray, sMatrixDecompositionContext);
        view.setTranslationX(PixelUtil.toPixelFromDIP(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.translation[0])));
        view.setTranslationY(PixelUtil.toPixelFromDIP(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.translation[1])));
        view.setRotation(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.rotationDegrees[2]));
        view.setRotationX(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.rotationDegrees[0]));
        view.setRotationY(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.rotationDegrees[1]));
        view.setScaleX(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.scale[0]));
        view.setScaleY(sanitizeFloatPropertyValue((float) sMatrixDecompositionContext.scale[1]));
        double[] dArr = sMatrixDecompositionContext.perspective;
        if (dArr.length > 2) {
            float f = (float) dArr[2];
            if (f == 0.0f) {
                f = 7.8125E-4f;
            }
            float f2 = (-1.0f) / f;
            float f3 = DisplayMetricsHolder.getScreenDisplayMetrics().density;
            view.setCameraDistance(sanitizeFloatPropertyValue(f3 * f3 * f2 * CAMERA_DISTANCE_NORMALIZATION_MULTIPLIER));
        }
    }

    private void updateViewAccessibility(@NonNull T t) {
        ReactAccessibilityDelegate.setDelegate(t);
    }

    private void updateViewContentDescription(@NonNull T t) {
        Dynamic dynamic;
        String str = (String) t.getTag(R.id.accessibility_label);
        ReadableMap readableMap = (ReadableMap) t.getTag(R.id.accessibility_state);
        String str2 = (String) t.getTag(R.id.accessibility_hint);
        ArrayList arrayList = new ArrayList();
        ReadableMap readableMap2 = (ReadableMap) t.getTag(R.id.accessibility_value);
        if (str != null) {
            arrayList.add(str);
        }
        if (readableMap != null) {
            ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
            while (keySetIterator.hasNextKey()) {
                String nextKey = keySetIterator.nextKey();
                Dynamic dynamic2 = readableMap.getDynamic(nextKey);
                if (nextKey.equals(STATE_CHECKED) && dynamic2.getType() == ReadableType.String && dynamic2.asString().equals(STATE_MIXED)) {
                    arrayList.add(t.getContext().getString(R.string.state_mixed_description));
                } else if (nextKey.equals(STATE_BUSY) && dynamic2.getType() == ReadableType.Boolean && dynamic2.asBoolean()) {
                    arrayList.add(t.getContext().getString(R.string.state_busy_description));
                } else if (nextKey.equals(STATE_EXPANDED) && dynamic2.getType() == ReadableType.Boolean) {
                    arrayList.add(t.getContext().getString(dynamic2.asBoolean() ? R.string.state_expanded_description : R.string.state_collapsed_description));
                }
            }
        }
        if (readableMap2 != null && readableMap2.hasKey("text") && (dynamic = readableMap2.getDynamic("text")) != null && dynamic.getType() == ReadableType.String) {
            arrayList.add(dynamic.asString());
        }
        if (str2 != null) {
            arrayList.add(str2);
        }
        if (arrayList.size() > 0) {
            t.setContentDescription(TextUtils.join(", ", arrayList));
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put(ReactAccessibilityDelegate.TOP_ACCESSIBILITY_ACTION_EVENT, MapBuilder.of("registrationName", "onAccessibilityAction")).build();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(@NonNull T t) {
        super.onAfterUpdateTransaction(t);
        updateViewAccessibility(t);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.ACCESSIBILITY_ACTIONS)
    public void setAccessibilityActions(T t, ReadableArray readableArray) {
        if (readableArray == null) {
            return;
        }
        t.setTag(R.id.accessibility_actions, readableArray);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.ACCESSIBILITY_HINT)
    public void setAccessibilityHint(@NonNull T t, @Nullable String str) {
        t.setTag(R.id.accessibility_hint, str);
        updateViewContentDescription(t);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.ACCESSIBILITY_LABEL)
    public void setAccessibilityLabel(@NonNull T t, @Nullable String str) {
        t.setTag(R.id.accessibility_label, str);
        updateViewContentDescription(t);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.ACCESSIBILITY_LIVE_REGION)
    public void setAccessibilityLiveRegion(@NonNull T t, @Nullable String str) {
        if (str != null && !str.equals("none")) {
            if (str.equals("polite")) {
                ViewCompat.setAccessibilityLiveRegion(t, 1);
                return;
            } else if (!str.equals("assertive")) {
                return;
            } else {
                ViewCompat.setAccessibilityLiveRegion(t, 2);
                return;
            }
        }
        ViewCompat.setAccessibilityLiveRegion(t, 0);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.ACCESSIBILITY_ROLE)
    public void setAccessibilityRole(@NonNull T t, @Nullable String str) {
        if (str == null) {
            return;
        }
        t.setTag(R.id.accessibility_role, ReactAccessibilityDelegate.AccessibilityRole.fromValue(str));
    }

    @ReactProp(name = ViewProps.ACCESSIBILITY_VALUE)
    public void setAccessibilityValue(T t, ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        t.setTag(R.id.accessibility_value, readableMap);
        if (!readableMap.hasKey("text")) {
            return;
        }
        updateViewContentDescription(t);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(customType = "Color", defaultInt = 0, name = "backgroundColor")
    public void setBackgroundColor(@NonNull T t, int i) {
        t.setBackgroundColor(i);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderBottomLeftRadius(T t, float f) {
        logUnsupportedPropertyWarning(ViewProps.BORDER_BOTTOM_LEFT_RADIUS);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderBottomRightRadius(T t, float f) {
        logUnsupportedPropertyWarning(ViewProps.BORDER_BOTTOM_RIGHT_RADIUS);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderRadius(T t, float f) {
        logUnsupportedPropertyWarning(ViewProps.BORDER_RADIUS);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderTopLeftRadius(T t, float f) {
        logUnsupportedPropertyWarning(ViewProps.BORDER_TOP_LEFT_RADIUS);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderTopRightRadius(T t, float f) {
        logUnsupportedPropertyWarning(ViewProps.BORDER_TOP_RIGHT_RADIUS);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.ELEVATION)
    public void setElevation(@NonNull T t, float f) {
        ViewCompat.setElevation(t, PixelUtil.toPixelFromDIP(f));
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.IMPORTANT_FOR_ACCESSIBILITY)
    public void setImportantForAccessibility(@NonNull T t, @Nullable String str) {
        if (str != null && !str.equals("auto")) {
            if (str.equals("yes")) {
                ViewCompat.setImportantForAccessibility(t, 1);
                return;
            } else if (str.equals("no")) {
                ViewCompat.setImportantForAccessibility(t, 2);
                return;
            } else if (!str.equals("no-hide-descendants")) {
                return;
            } else {
                ViewCompat.setImportantForAccessibility(t, 4);
                return;
            }
        }
        ViewCompat.setImportantForAccessibility(t, 0);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.NATIVE_ID)
    public void setNativeId(@NonNull T t, @Nullable String str) {
        t.setTag(R.id.view_tag_native_id, str);
        ReactFindViewUtil.notifyViewRendered(t);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.OPACITY)
    public void setOpacity(@NonNull T t, float f) {
        t.setAlpha(f);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.RENDER_TO_HARDWARE_TEXTURE)
    public void setRenderToHardwareTexture(@NonNull T t, boolean z) {
        t.setLayerType(z ? 2 : 0, null);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.ROTATION)
    @Deprecated
    public void setRotation(@NonNull T t, float f) {
        t.setRotation(f);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.SCALE_X)
    @Deprecated
    public void setScaleX(@NonNull T t, float f) {
        t.setScaleX(f);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 1.0f, name = ViewProps.SCALE_Y)
    @Deprecated
    public void setScaleY(@NonNull T t, float f) {
        t.setScaleY(f);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(customType = "Color", defaultInt = ViewCompat.MEASURED_STATE_MASK, name = ViewProps.SHADOW_COLOR)
    public void setShadowColor(@NonNull T t, int i) {
        if (Build.VERSION.SDK_INT >= 28) {
            t.setOutlineAmbientShadowColor(i);
            t.setOutlineSpotShadowColor(i);
        }
    }

    @ReactProp(name = ViewProps.TEST_ID)
    public void setTestId(@NonNull T t, @Nullable String str) {
        t.setTag(R.id.react_test_id, str);
        t.setTag(str);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.TRANSFORM)
    public void setTransform(@NonNull T t, @Nullable ReadableArray readableArray) {
        if (readableArray == null) {
            resetTransformProperty(t);
        } else {
            setTransformProperty(t, readableArray);
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 0.0f, name = ViewProps.TRANSLATE_X)
    @Deprecated
    public void setTranslateX(@NonNull T t, float f) {
        t.setTranslationX(PixelUtil.toPixelFromDIP(f));
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(defaultFloat = 0.0f, name = ViewProps.TRANSLATE_Y)
    @Deprecated
    public void setTranslateY(@NonNull T t, float f) {
        t.setTranslationY(PixelUtil.toPixelFromDIP(f));
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.ACCESSIBILITY_STATE)
    public void setViewState(@NonNull T t, @Nullable ReadableMap readableMap) {
        if (readableMap == null) {
            return;
        }
        t.setTag(R.id.accessibility_state, readableMap);
        t.setSelected(false);
        t.setEnabled(true);
        ReadableMapKeySetIterator keySetIterator = readableMap.keySetIterator();
        while (keySetIterator.hasNextKey()) {
            String nextKey = keySetIterator.nextKey();
            if (!nextKey.equals(STATE_BUSY) && !nextKey.equals(STATE_EXPANDED) && (!nextKey.equals(STATE_CHECKED) || readableMap.getType(STATE_CHECKED) != ReadableType.String)) {
                if (t.isAccessibilityFocused()) {
                    t.sendAccessibilityEvent(1);
                }
            } else {
                updateViewContentDescription(t);
                return;
            }
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    @ReactProp(name = ViewProps.Z_INDEX)
    public void setZIndex(@NonNull T t, float f) {
        ViewGroupManager.setViewZIndex(t, Math.round(f));
        ViewParent parent = t.getParent();
        if (parent instanceof ReactZIndexedViewGroup) {
            ((ReactZIndexedViewGroup) parent).updateDrawingOrder();
        }
    }
}
