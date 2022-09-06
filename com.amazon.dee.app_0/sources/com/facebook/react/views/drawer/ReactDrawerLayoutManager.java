package com.facebook.react.views.drawer;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewManagerDelegate;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerDelegate;
import com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface;
import com.facebook.react.views.drawer.events.DrawerClosedEvent;
import com.facebook.react.views.drawer.events.DrawerOpenedEvent;
import com.facebook.react.views.drawer.events.DrawerSlideEvent;
import com.facebook.react.views.drawer.events.DrawerStateChangedEvent;
import java.util.Map;
@ReactModule(name = ReactDrawerLayoutManager.REACT_CLASS)
/* loaded from: classes2.dex */
public class ReactDrawerLayoutManager extends ViewGroupManager<ReactDrawerLayout> implements AndroidDrawerLayoutManagerInterface<ReactDrawerLayout> {
    public static final int CLOSE_DRAWER = 2;
    public static final int OPEN_DRAWER = 1;
    public static final String REACT_CLASS = "AndroidDrawerLayout";
    private final ViewManagerDelegate<ReactDrawerLayout> mDelegate = new AndroidDrawerLayoutManagerDelegate(this);

    /* loaded from: classes2.dex */
    public static class DrawerEventEmitter implements DrawerLayout.DrawerListener {
        private final DrawerLayout mDrawerLayout;
        private final EventDispatcher mEventDispatcher;

        public DrawerEventEmitter(DrawerLayout drawerLayout, EventDispatcher eventDispatcher) {
            this.mDrawerLayout = drawerLayout;
            this.mEventDispatcher = eventDispatcher;
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerClosed(@NonNull View view) {
            this.mEventDispatcher.dispatchEvent(new DrawerClosedEvent(this.mDrawerLayout.getId()));
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerOpened(@NonNull View view) {
            this.mEventDispatcher.dispatchEvent(new DrawerOpenedEvent(this.mDrawerLayout.getId()));
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerSlide(@NonNull View view, float f) {
            this.mEventDispatcher.dispatchEvent(new DrawerSlideEvent(this.mDrawerLayout.getId(), f));
        }

        @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
        public void onDrawerStateChanged(int i) {
            this.mEventDispatcher.dispatchEvent(new DrawerStateChangedEvent(this.mDrawerLayout.getId(), i));
        }
    }

    private void setDrawerPositionInternal(ReactDrawerLayout reactDrawerLayout, String str) {
        if (str.equals("left")) {
            reactDrawerLayout.setDrawerPosition(GravityCompat.START);
        } else if (str.equals("right")) {
            reactDrawerLayout.setDrawerPosition(GravityCompat.END);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("drawerPosition must be 'left' or 'right', received", str));
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of("openDrawer", 1, "closeDrawer", 2);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ViewManagerDelegate<ReactDrawerLayout> getDelegate() {
        return this.mDelegate;
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(DrawerSlideEvent.EVENT_NAME, MapBuilder.of("registrationName", "onDrawerSlide"), DrawerOpenedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onDrawerOpen"), DrawerClosedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onDrawerClose"), DrawerStateChangedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onDrawerStateChanged"));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    @Nullable
    public Map getExportedViewConstants() {
        return MapBuilder.of("DrawerPosition", MapBuilder.of("Left", Integer.valueOf((int) GravityCompat.START), "Right", Integer.valueOf((int) GravityCompat.END)));
    }

    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    @NonNull
    public String getName() {
        return REACT_CLASS;
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager, com.facebook.react.uimanager.IViewManagerWithChildren
    public boolean needsCustomLayoutForChildren() {
        return true;
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    public void setDrawerBackgroundColor(ReactDrawerLayout reactDrawerLayout, @Nullable Integer num) {
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    public void setKeyboardDismissMode(ReactDrawerLayout reactDrawerLayout, @Nullable String str) {
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    public void setStatusBarBackgroundColor(ReactDrawerLayout reactDrawerLayout, @Nullable Integer num) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    public void addEventEmitters(ThemedReactContext themedReactContext, ReactDrawerLayout reactDrawerLayout) {
        UIManagerModule uIManagerModule = (UIManagerModule) themedReactContext.getNativeModule(UIManagerModule.class);
        if (uIManagerModule == null) {
            return;
        }
        reactDrawerLayout.addDrawerListener(new DrawerEventEmitter(reactDrawerLayout, uIManagerModule.mo6949getEventDispatcher()));
    }

    @Override // com.facebook.react.uimanager.ViewGroupManager
    public void addView(ReactDrawerLayout reactDrawerLayout, View view, int i) {
        if (getChildCount(reactDrawerLayout) < 2) {
            if (i != 0 && i != 1) {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline52("The only valid indices for drawer's child are 0 or 1. Got ", i, " instead."));
            }
            reactDrawerLayout.addView(view, i);
            reactDrawerLayout.setDrawerProperties();
            return;
        }
        throw new JSApplicationIllegalArgumentException("The Drawer cannot have more than two children");
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    public void closeDrawer(ReactDrawerLayout reactDrawerLayout) {
        reactDrawerLayout.closeDrawer();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.ViewManager
    @NonNull
    /* renamed from: createViewInstance  reason: collision with other method in class */
    public ReactDrawerLayout mo12880createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        return new ReactDrawerLayout(themedReactContext);
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    public void openDrawer(ReactDrawerLayout reactDrawerLayout) {
        reactDrawerLayout.openDrawer();
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    @ReactProp(name = "drawerLockMode")
    public void setDrawerLockMode(ReactDrawerLayout reactDrawerLayout, @Nullable String str) {
        if (str != null && !"unlocked".equals(str)) {
            if ("locked-closed".equals(str)) {
                reactDrawerLayout.setDrawerLockMode(1);
                return;
            } else if ("locked-open".equals(str)) {
                reactDrawerLayout.setDrawerLockMode(2);
                return;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Unknown drawerLockMode ", str));
            }
        }
        reactDrawerLayout.setDrawerLockMode(0);
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    public void setDrawerPosition(ReactDrawerLayout reactDrawerLayout, @Nullable String str) {
        if (str == null) {
            reactDrawerLayout.setDrawerPosition(GravityCompat.START);
        } else {
            setDrawerPositionInternal(reactDrawerLayout, str);
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = "drawerWidth")
    public void setDrawerWidth(ReactDrawerLayout reactDrawerLayout, float f) {
        reactDrawerLayout.setDrawerWidth(Float.isNaN(f) ? -1 : Math.round(PixelUtil.toPixelFromDIP(f)));
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.BaseViewManagerInterface
    public void setElevation(@NonNull ReactDrawerLayout reactDrawerLayout, float f) {
        reactDrawerLayout.setDrawerElevation(PixelUtil.toPixelFromDIP(f));
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(ReactDrawerLayout reactDrawerLayout, int i, @Nullable ReadableArray readableArray) {
        if (i == 1) {
            reactDrawerLayout.openDrawer();
        } else if (i != 2) {
        } else {
            reactDrawerLayout.closeDrawer();
        }
    }

    @ReactProp(name = "drawerPosition")
    public void setDrawerPosition(ReactDrawerLayout reactDrawerLayout, Dynamic dynamic) {
        if (dynamic.isNull()) {
            reactDrawerLayout.setDrawerPosition(GravityCompat.START);
        } else if (dynamic.getType() == ReadableType.Number) {
            int asInt = dynamic.asInt();
            if (8388611 != asInt && 8388613 != asInt) {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline49("Unknown drawerPosition ", asInt));
            }
            reactDrawerLayout.setDrawerPosition(asInt);
        } else if (dynamic.getType() == ReadableType.String) {
            setDrawerPositionInternal(reactDrawerLayout, dynamic.asString());
        } else {
            throw new JSApplicationIllegalArgumentException("drawerPosition must be a string or int");
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(@NonNull ReactDrawerLayout reactDrawerLayout, String str, @Nullable ReadableArray readableArray) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -258774775) {
            if (hashCode == -83186725 && str.equals("openDrawer")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals("closeDrawer")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            reactDrawerLayout.openDrawer();
        } else if (c != 1) {
        } else {
            reactDrawerLayout.closeDrawer();
        }
    }

    @Override // com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface
    public void setDrawerWidth(ReactDrawerLayout reactDrawerLayout, @Nullable Float f) {
        reactDrawerLayout.setDrawerWidth(f == null ? -1 : Math.round(PixelUtil.toPixelFromDIP(f.floatValue())));
    }
}
