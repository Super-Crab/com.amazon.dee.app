package com.reactnativecommunity.viewpager;

import android.view.View;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import javax.annotation.Nullable;
/* loaded from: classes3.dex */
public class ReactViewPagerManager extends ViewGroupManager<ReactViewPager> {
    private static final int COMMAND_SET_PAGE = 1;
    private static final int COMMAND_SET_PAGE_WITHOUT_ANIMATION = 2;
    private static final String REACT_CLASS = "RNCViewPager";

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("setPage", 1, "setPageWithoutAnimation", 2);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(PageScrollEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageScroll"), PageScrollStateChangedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageScrollStateChanged"), PageSelectedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageSelected"));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @ReactProp(defaultFloat = 0.0f, name = "pageMargin")
    public void setPageMargin(ReactViewPager reactViewPager, float f) {
        reactViewPager.setPageMargin((int) PixelUtil.toPixelFromDIP(f));
    }

    @ReactProp(defaultBoolean = true, name = ReactProperties.SCROLL_ENABLED)
    public void setScrollEnabled(ReactViewPager reactViewPager, boolean z) {
        reactViewPager.setScrollEnabled(z);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(ReactViewPager reactViewPager, View view, int i) {
        reactViewPager.addViewToAdapter(view, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ReactViewPager mo12880createViewInstance(ThemedReactContext themedReactContext) {
        return new ReactViewPager(themedReactContext);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public View getChildAt(ReactViewPager reactViewPager, int i) {
        return reactViewPager.getViewFromAdapter(i);
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public int getChildCount(ReactViewPager reactViewPager) {
        return reactViewPager.getViewCountInAdapter();
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactViewPager reactViewPager, int i, @Nullable ReadableArray readableArray) {
        Assertions.assertNotNull(reactViewPager);
        Assertions.assertNotNull(readableArray);
        if (i == 1) {
            reactViewPager.setCurrentItemFromJs(readableArray.getInt(0), true);
        } else if (i == 2) {
            reactViewPager.setCurrentItemFromJs(readableArray.getInt(0), false);
        } else {
            throw new IllegalArgumentException(String.format("Unsupported command %d received by %s.", Integer.valueOf(i), ReactViewPagerManager.class.getSimpleName()));
        }
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void removeViewAt(ReactViewPager reactViewPager, int i) {
        reactViewPager.removeViewFromAdapter(i);
    }
}
