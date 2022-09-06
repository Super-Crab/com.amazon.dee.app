package com.facebook.react.uimanager;

import android.widget.ImageView;
import bolts.BuildConfig;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.events.TouchEventType;
import com.reactnativecommunity.webview.events.TopLoadingErrorEvent;
import com.reactnativecommunity.webview.events.TopLoadingFinishEvent;
import com.reactnativecommunity.webview.events.TopLoadingStartEvent;
import com.reactnativecommunity.webview.events.TopMessageEvent;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes2.dex */
class UIManagerModuleConstants {
    public static final String ACTION_DISMISSED = "dismissed";
    public static final String ACTION_ITEM_SELECTED = "itemSelected";

    UIManagerModuleConstants() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map getBubblingEventTypeConstants() {
        return MapBuilder.builder().put("topChange", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onChange", "captured", "onChangeCapture"))).put("topSelect", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onSelect", "captured", "onSelectCapture"))).put(TouchEventType.getJSEventName(TouchEventType.START), MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onTouchStart", "captured", "onTouchStartCapture"))).put(TouchEventType.getJSEventName(TouchEventType.MOVE), MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onTouchMove", "captured", "onTouchMoveCapture"))).put(TouchEventType.getJSEventName(TouchEventType.END), MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onTouchEnd", "captured", "onTouchEndCapture"))).put(TouchEventType.getJSEventName(TouchEventType.CANCEL), MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onTouchCancel", "captured", "onTouchCancelCapture"))).build();
    }

    public static Map<String, Object> getConstants() {
        HashMap newHashMap = MapBuilder.newHashMap();
        newHashMap.put("UIView", MapBuilder.of("ContentMode", MapBuilder.of("ScaleAspectFit", Integer.valueOf(ImageView.ScaleType.FIT_CENTER.ordinal()), "ScaleAspectFill", Integer.valueOf(ImageView.ScaleType.CENTER_CROP.ordinal()), "ScaleAspectCenter", Integer.valueOf(ImageView.ScaleType.CENTER_INSIDE.ordinal()))));
        newHashMap.put("StyleConstants", MapBuilder.of("PointerEventsValues", MapBuilder.of("none", Integer.valueOf(PointerEvents.NONE.ordinal()), "boxNone", Integer.valueOf(PointerEvents.BOX_NONE.ordinal()), "boxOnly", Integer.valueOf(PointerEvents.BOX_ONLY.ordinal()), BuildConfig.VERSION_NAME, Integer.valueOf(PointerEvents.AUTO.ordinal()))));
        newHashMap.put("PopupMenu", MapBuilder.of("dismissed", "dismissed", ACTION_ITEM_SELECTED, ACTION_ITEM_SELECTED));
        newHashMap.put("AccessibilityEventTypes", MapBuilder.of("typeWindowStateChanged", 32, "typeViewFocused", 8, "typeViewClicked", 1));
        return newHashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Map getDirectEventTypeConstants() {
        return MapBuilder.builder().put("topContentSizeChange", MapBuilder.of("registrationName", "onContentSizeChange")).put("topLayout", MapBuilder.of("registrationName", ViewProps.ON_LAYOUT)).put(TopLoadingErrorEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingError")).put(TopLoadingFinishEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingFinish")).put(TopLoadingStartEvent.EVENT_NAME, MapBuilder.of("registrationName", "onLoadingStart")).put("topSelectionChange", MapBuilder.of("registrationName", "onSelectionChange")).put(TopMessageEvent.EVENT_NAME, MapBuilder.of("registrationName", "onMessage")).put("topClick", MapBuilder.of("registrationName", "onClick")).put("topScrollBeginDrag", MapBuilder.of("registrationName", "onScrollBeginDrag")).put("topScrollEndDrag", MapBuilder.of("registrationName", "onScrollEndDrag")).put("topScroll", MapBuilder.of("registrationName", "onScroll")).put("topMomentumScrollBegin", MapBuilder.of("registrationName", "onMomentumScrollBegin")).put("topMomentumScrollEnd", MapBuilder.of("registrationName", "onMomentumScrollEnd")).build();
    }
}
