package com.facebook.react;

import androidx.annotation.NonNull;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMarker;
import com.facebook.react.bridge.ReactMarkerConstants;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.systrace.SystraceMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public abstract class LazyReactPackage implements ReactPackage {
    public static ReactModuleInfoProvider getReactModuleInfoProviderViaReflection(LazyReactPackage lazyReactPackage) {
        try {
            Class<?> cls = Class.forName(lazyReactPackage.getClass().getCanonicalName() + "$$ReactModuleInfoProvider");
            if (cls != null) {
                try {
                    return (ReactModuleInfoProvider) cls.newInstance();
                } catch (IllegalAccessException e) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Unable to instantiate ReactModuleInfoProvider for ");
                    outline107.append(lazyReactPackage.getClass());
                    throw new RuntimeException(outline107.toString(), e);
                } catch (InstantiationException e2) {
                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("Unable to instantiate ReactModuleInfoProvider for ");
                    outline1072.append(lazyReactPackage.getClass());
                    throw new RuntimeException(outline1072.toString(), e2);
                }
            }
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("ReactModuleInfoProvider class for ");
            outline1073.append(lazyReactPackage.getClass().getCanonicalName());
            outline1073.append(" not found.");
            throw new RuntimeException(outline1073.toString());
        } catch (ClassNotFoundException unused) {
            return new ReactModuleInfoProvider() { // from class: com.facebook.react.LazyReactPackage.1
                @Override // com.facebook.react.module.model.ReactModuleInfoProvider
                public Map<String, ReactModuleInfo> getReactModuleInfos() {
                    return Collections.emptyMap();
                }
            };
        }
    }

    @Override // com.facebook.react.ReactPackage
    public final List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        ArrayList arrayList = new ArrayList();
        for (ModuleSpec moduleSpec : getNativeModules(reactApplicationContext)) {
            SystraceMessage.beginSection(0L, "createNativeModule").arg(HttpClientModule.ElementsRequestKey.MODULE, moduleSpec.getType()).flush();
            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, moduleSpec.getName());
            try {
                NativeModule mo10268get = moduleSpec.getProvider().mo10268get();
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                SystraceMessage.endSection(0L).flush();
                arrayList.add(mo10268get);
            } catch (Throwable th) {
                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                SystraceMessage.endSection(0L).flush();
                throw th;
            }
        }
        return arrayList;
    }

    @Override // com.facebook.react.ReactPackage
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        List<ModuleSpec> viewManagers = getViewManagers(reactApplicationContext);
        if (viewManagers != null && !viewManagers.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            for (ModuleSpec moduleSpec : viewManagers) {
                arrayList.add((ViewManager) moduleSpec.getProvider().mo10268get());
            }
            return arrayList;
        }
        return Collections.emptyList();
    }

    public Iterable<ModuleHolder> getNativeModuleIterator(ReactApplicationContext reactApplicationContext) {
        final Map<String, ReactModuleInfo> reactModuleInfos = getReactModuleInfoProvider().getReactModuleInfos();
        final List<ModuleSpec> nativeModules = getNativeModules(reactApplicationContext);
        return new Iterable<ModuleHolder>() { // from class: com.facebook.react.LazyReactPackage.2
            @Override // java.lang.Iterable
            @NonNull
            public Iterator<ModuleHolder> iterator() {
                return new Iterator<ModuleHolder>() { // from class: com.facebook.react.LazyReactPackage.2.1
                    int position = 0;

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.position < nativeModules.size();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException("Cannot remove native modules from the list");
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    /* renamed from: next */
                    public ModuleHolder mo6926next() {
                        List list = nativeModules;
                        int i = this.position;
                        this.position = i + 1;
                        ModuleSpec moduleSpec = (ModuleSpec) list.get(i);
                        String name = moduleSpec.getName();
                        ReactModuleInfo reactModuleInfo = (ReactModuleInfo) reactModuleInfos.get(name);
                        if (reactModuleInfo == null) {
                            ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_START, name);
                            try {
                                NativeModule mo10268get = moduleSpec.getProvider().mo10268get();
                                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                                return new ModuleHolder(mo10268get);
                            } catch (Throwable th) {
                                ReactMarker.logMarker(ReactMarkerConstants.CREATE_MODULE_END);
                                throw th;
                            }
                        }
                        return new ModuleHolder(reactModuleInfo, moduleSpec.getProvider());
                    }
                };
            }
        };
    }

    protected abstract List<ModuleSpec> getNativeModules(ReactApplicationContext reactApplicationContext);

    public abstract ReactModuleInfoProvider getReactModuleInfoProvider();

    public List<ModuleSpec> getViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
