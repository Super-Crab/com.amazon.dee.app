package com.facebook.react.uimanager;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.JSIModuleType;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactNoCrashSoftException;
import com.facebook.react.bridge.ReactSoftException;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.uimanager.common.ViewUtil;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.EventDispatcherProvider;
/* loaded from: classes2.dex */
public class UIManagerHelper {
    public static final int PADDING_BOTTOM_INDEX = 3;
    public static final int PADDING_END_INDEX = 1;
    public static final int PADDING_START_INDEX = 0;
    public static final int PADDING_TOP_INDEX = 2;

    public static float[] getDefaultTextInputPadding(ThemedReactContext themedReactContext) {
        EditText editText = new EditText(themedReactContext);
        return new float[]{PixelUtil.toDIPFromPixel(ViewCompat.getPaddingStart(editText)), PixelUtil.toDIPFromPixel(ViewCompat.getPaddingEnd(editText)), PixelUtil.toDIPFromPixel(editText.getPaddingTop()), PixelUtil.toDIPFromPixel(editText.getPaddingBottom())};
    }

    @Nullable
    public static EventDispatcher getEventDispatcher(ReactContext reactContext, int i) {
        if (reactContext.isBridgeless()) {
            if (reactContext instanceof ThemedReactContext) {
                reactContext = ((ThemedReactContext) reactContext).getReactApplicationContext();
            }
            return ((EventDispatcherProvider) reactContext).getEventDispatcher();
        }
        UIManager uIManager = getUIManager(reactContext, i, false);
        if (uIManager != null) {
            return (EventDispatcher) uIManager.mo6949getEventDispatcher();
        }
        return null;
    }

    @Nullable
    public static EventDispatcher getEventDispatcherForReactTag(ReactContext reactContext, int i) {
        return getEventDispatcher(reactContext, ViewUtil.getUIManagerType(i));
    }

    public static ReactContext getReactContext(View view) {
        Context context = view.getContext();
        if (!(context instanceof ReactContext) && (context instanceof ContextWrapper)) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return (ReactContext) context;
    }

    @Nullable
    public static UIManager getUIManager(ReactContext reactContext, int i) {
        return getUIManager(reactContext, i, true);
    }

    @Nullable
    public static UIManager getUIManagerForReactTag(ReactContext reactContext, int i) {
        return getUIManager(reactContext, ViewUtil.getUIManagerType(i));
    }

    @Nullable
    private static UIManager getUIManager(ReactContext reactContext, int i, boolean z) {
        if (reactContext.isBridgeless()) {
            UIManager uIManager = reactContext.getJSIModule(JSIModuleType.UIManager) != null ? (UIManager) reactContext.getJSIModule(JSIModuleType.UIManager) : null;
            if (uIManager != null) {
                return uIManager;
            }
            ReactSoftException.logSoftException("UIManagerHelper", new ReactNoCrashSoftException("Cannot get UIManager because the instance hasn't been initialized yet."));
            return null;
        } else if (!reactContext.hasCatalystInstance()) {
            ReactSoftException.logSoftException("UIManagerHelper", new ReactNoCrashSoftException("Cannot get UIManager because the context doesn't contain a CatalystInstance."));
            return null;
        } else {
            if (!reactContext.hasActiveCatalystInstance()) {
                ReactSoftException.logSoftException("UIManagerHelper", new ReactNoCrashSoftException("Cannot get UIManager because the context doesn't contain an active CatalystInstance."));
                if (z) {
                    return null;
                }
            }
            CatalystInstance catalystInstance = reactContext.getCatalystInstance();
            try {
                if (i == 2) {
                    return (UIManager) catalystInstance.getJSIModule(JSIModuleType.UIManager);
                }
                return (UIManager) catalystInstance.getNativeModule(UIManagerModule.class);
            } catch (IllegalArgumentException unused) {
                ReactSoftException.logSoftException("UIManagerHelper", new ReactNoCrashSoftException(GeneratedOutlineSupport1.outline49("Cannot get UIManager for UIManagerType: ", i)));
                return (UIManager) catalystInstance.getNativeModule(UIManagerModule.class);
            }
        }
    }
}
