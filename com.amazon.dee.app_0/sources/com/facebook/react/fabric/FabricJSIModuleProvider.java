package com.facebook.react.fabric;

import androidx.annotation.NonNull;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.JSIModuleProvider;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UIManager;
import com.facebook.react.fabric.events.EventBeatManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.events.FabricEventEmitter;
import com.facebook.react.fabric.mounting.LayoutMetricsConversions;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.mountitems.BatchMountItem;
import com.facebook.react.fabric.mounting.mountitems.CreateMountItem;
import com.facebook.react.fabric.mounting.mountitems.DispatchCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.DispatchIntCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.DispatchStringCommandMountItem;
import com.facebook.react.fabric.mounting.mountitems.InsertMountItem;
import com.facebook.react.fabric.mounting.mountitems.MountItem;
import com.facebook.react.fabric.mounting.mountitems.PreAllocateViewMountItem;
import com.facebook.react.fabric.mounting.mountitems.RemoveDeleteMultiMountItem;
import com.facebook.react.fabric.mounting.mountitems.SendAccessibilityEvent;
import com.facebook.react.fabric.mounting.mountitems.UpdateEventEmitterMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdateLayoutMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdatePaddingMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdatePropsMountItem;
import com.facebook.react.fabric.mounting.mountitems.UpdateStateMountItem;
import com.facebook.react.uimanager.StateWrapper;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.BatchEventDispatchedListener;
import com.facebook.systrace.Systrace;
/* loaded from: classes2.dex */
public class FabricJSIModuleProvider implements JSIModuleProvider<UIManager> {
    @NonNull
    private final ComponentFactory mComponentFactory;
    @NonNull
    private final ReactNativeConfig mConfig;
    @NonNull
    private final ReactApplicationContext mReactApplicationContext;

    public FabricJSIModuleProvider(@NonNull ReactApplicationContext reactApplicationContext, @NonNull ComponentFactory componentFactory, @NonNull ReactNativeConfig reactNativeConfig) {
        this.mReactApplicationContext = reactApplicationContext;
        this.mComponentFactory = componentFactory;
        this.mConfig = reactNativeConfig;
    }

    private FabricUIManager createUIManager(@NonNull EventBeatManager eventBeatManager) {
        Systrace.beginSection(0L, "FabricJSIModuleProvider.createUIManager");
        UIManagerModule uIManagerModule = (UIManagerModule) Assertions.assertNotNull(this.mReactApplicationContext.getNativeModule(UIManagerModule.class));
        FabricUIManager fabricUIManager = new FabricUIManager(this.mReactApplicationContext, uIManagerModule.getViewManagerRegistry_DO_NOT_USE(), uIManagerModule.mo6949getEventDispatcher(), eventBeatManager);
        Systrace.endSection(0L);
        return fabricUIManager;
    }

    private static void loadClasses() {
        EventBeatManager.class.getClass();
        EventEmitterWrapper.class.getClass();
        FabricEventEmitter.class.getClass();
        BatchMountItem.class.getClass();
        CreateMountItem.class.getClass();
        DispatchCommandMountItem.class.getClass();
        DispatchIntCommandMountItem.class.getClass();
        DispatchStringCommandMountItem.class.getClass();
        InsertMountItem.class.getClass();
        MountItem.class.getClass();
        PreAllocateViewMountItem.class.getClass();
        RemoveDeleteMultiMountItem.class.getClass();
        SendAccessibilityEvent.class.getClass();
        UpdateEventEmitterMountItem.class.getClass();
        UpdateLayoutMountItem.class.getClass();
        UpdatePaddingMountItem.class.getClass();
        UpdatePropsMountItem.class.getClass();
        UpdateStateMountItem.class.getClass();
        LayoutMetricsConversions.class.getClass();
        MountingManager.class.getClass();
        Binding.class.getClass();
        ComponentFactory.class.getClass();
        FabricComponents.class.getClass();
        FabricSoLoader.class.getClass();
        FabricUIManager.class.getClass();
        GuardedFrameCallback.class.getClass();
        StateWrapper.class.getClass();
        StateWrapperImpl.class.getClass();
        BatchEventDispatchedListener.class.getClass();
        ReactNativeConfig.class.getClass();
    }

    @Override // com.facebook.react.bridge.JSIModuleProvider
    public UIManager get() {
        EventBeatManager eventBeatManager = new EventBeatManager(this.mReactApplicationContext);
        FabricUIManager createUIManager = createUIManager(eventBeatManager);
        Systrace.beginSection(0L, "FabricJSIModuleProvider.registerBinding");
        Binding binding = new Binding();
        loadClasses();
        binding.register(this.mReactApplicationContext.getCatalystInstance().getRuntimeExecutor(), createUIManager, eventBeatManager, this.mReactApplicationContext.getCatalystInstance().getReactQueueConfiguration().getJSQueueThread(), this.mComponentFactory, this.mConfig);
        Systrace.endSection(0L);
        return createUIManager;
    }
}
