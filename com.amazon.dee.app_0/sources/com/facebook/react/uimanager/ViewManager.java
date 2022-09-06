package com.facebook.react.uimanager;

import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.config.ReactFeatureFlags;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.annotations.ReactPropertyHolder;
import com.facebook.yoga.YogaMeasureMode;
import java.util.Map;
@ReactPropertyHolder
/* loaded from: classes2.dex */
public abstract class ViewManager<T extends View, C extends ReactShadowNode> extends BaseJavaModule {
    @NonNull
    private final T createView(@NonNull ThemedReactContext themedReactContext, JSResponderHandler jSResponderHandler) {
        return createView(themedReactContext, null, null, jSResponderHandler);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addEventEmitters(@NonNull ThemedReactContext themedReactContext, @NonNull T t) {
    }

    /* renamed from: createShadowNodeInstance */
    public C mo10136createShadowNodeInstance() {
        throw new RuntimeException("ViewManager subclasses must implement createShadowNodeInstance()");
    }

    @NonNull
    /* renamed from: createViewInstance */
    protected abstract T mo12880createViewInstance(@NonNull ThemedReactContext themedReactContext);

    @NonNull
    protected T createViewInstance(@NonNull ThemedReactContext themedReactContext, @Nullable ReactStylesDiffMap reactStylesDiffMap, @Nullable StateWrapper stateWrapper) {
        Object updateState;
        T mo12880createViewInstance = mo12880createViewInstance(themedReactContext);
        addEventEmitters(themedReactContext, mo12880createViewInstance);
        if (reactStylesDiffMap != null) {
            updateProperties(mo12880createViewInstance, reactStylesDiffMap);
        }
        if (stateWrapper != null && (updateState = updateState(mo12880createViewInstance, reactStylesDiffMap, stateWrapper)) != null) {
            updateExtraData(mo12880createViewInstance, updateState);
        }
        return mo12880createViewInstance;
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public ViewManagerDelegate<T> getDelegate() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedViewConstants() {
        return null;
    }

    @Override // com.facebook.react.bridge.NativeModule
    @NonNull
    public abstract String getName();

    public Map<String, String> getNativeProps() {
        return ViewManagerPropertyUpdater.getNativeProps(getClass(), getShadowNodeClass());
    }

    public abstract Class<? extends C> getShadowNodeClass();

    public long measure(Context context, ReadableMap readableMap, ReadableMap readableMap2, ReadableMap readableMap3, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2, @Nullable float[] fArr) {
        return 0L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void onAfterUpdateTransaction(@NonNull T t) {
    }

    public void onDropViewInstance(@NonNull T t) {
    }

    @Deprecated
    public void receiveCommand(@NonNull T t, int i, @Nullable ReadableArray readableArray) {
    }

    public void receiveCommand(@NonNull T t, String str, @Nullable ReadableArray readableArray) {
    }

    public void setPadding(T t, int i, int i2, int i3, int i4) {
    }

    public abstract void updateExtraData(@NonNull T t, Object obj);

    public void updateProperties(@NonNull T t, ReactStylesDiffMap reactStylesDiffMap) {
        ViewManagerDelegate<T> delegate;
        if (ReactFeatureFlags.useViewManagerDelegates && (delegate = getDelegate()) != null) {
            ViewManagerPropertyUpdater.updateProps(delegate, t, reactStylesDiffMap);
        } else {
            ViewManagerPropertyUpdater.updateProps(this, t, reactStylesDiffMap);
        }
        onAfterUpdateTransaction(t);
    }

    @Nullable
    public Object updateState(@NonNull T t, ReactStylesDiffMap reactStylesDiffMap, @Nullable StateWrapper stateWrapper) {
        return null;
    }

    @NonNull
    public C createShadowNodeInstance(@NonNull ReactApplicationContext reactApplicationContext) {
        return mo10136createShadowNodeInstance();
    }

    @NonNull
    public T createView(@NonNull ThemedReactContext themedReactContext, @Nullable ReactStylesDiffMap reactStylesDiffMap, @Nullable StateWrapper stateWrapper, JSResponderHandler jSResponderHandler) {
        T createViewInstance = createViewInstance(themedReactContext, reactStylesDiffMap, stateWrapper);
        if (createViewInstance instanceof ReactInterceptingViewGroup) {
            ((ReactInterceptingViewGroup) createViewInstance).setOnInterceptTouchEventListener(jSResponderHandler);
        }
        return createViewInstance;
    }
}
