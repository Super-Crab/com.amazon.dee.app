package com.facebook.react;

import androidx.annotation.NonNull;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ModuleHolder;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.ReactConstants;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes2.dex */
public class ReactPackageHelper {
    public static Iterable<ModuleHolder> getNativeModuleIterator(ReactPackage reactPackage, ReactApplicationContext reactApplicationContext, ReactInstanceManager reactInstanceManager) {
        final List<NativeModule> createNativeModules;
        FLog.d(ReactConstants.TAG, reactPackage.getClass().getSimpleName() + " is not a LazyReactPackage, falling back to old version.");
        if (reactPackage instanceof ReactInstancePackage) {
            createNativeModules = ((ReactInstancePackage) reactPackage).createNativeModules(reactApplicationContext, reactInstanceManager);
        } else {
            createNativeModules = reactPackage.createNativeModules(reactApplicationContext);
        }
        return new Iterable<ModuleHolder>() { // from class: com.facebook.react.ReactPackageHelper.1
            @Override // java.lang.Iterable
            @NonNull
            public Iterator<ModuleHolder> iterator() {
                return new Iterator<ModuleHolder>() { // from class: com.facebook.react.ReactPackageHelper.1.1
                    int position = 0;

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.position < createNativeModules.size();
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                        throw new UnsupportedOperationException("Cannot remove methods ");
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    /* renamed from: next */
                    public ModuleHolder mo6927next() {
                        List list = createNativeModules;
                        int i = this.position;
                        this.position = i + 1;
                        return new ModuleHolder((NativeModule) list.get(i));
                    }
                };
            }
        };
    }
}
