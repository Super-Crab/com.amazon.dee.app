package com.amazon.dee.app.elements;

import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.nativemodules.SendSMSBridge;
import com.facebook.react.LazyReactPackage;
import com.facebook.react.bridge.ModuleSpec;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.model.ReactModuleInfo;
import com.facebook.react.module.model.ReactModuleInfoProvider;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public class SMSMessagingPackage extends LazyReactPackage {
    private static final String MODULE_NAME = "NativeMessaging";
    @VisibleForTesting
    volatile boolean constructorCalled = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Map lambda$getReactModuleInfoProvider$1() {
        HashMap hashMap = new HashMap();
        hashMap.put(MODULE_NAME, new ReactModuleInfo(MODULE_NAME, SendSMSBridge.class.getName(), false, false, true, false, false));
        return hashMap;
    }

    @Override // com.facebook.react.LazyReactPackage
    protected List<ModuleSpec> getNativeModules(final ReactApplicationContext reactApplicationContext) {
        return Arrays.asList(ModuleSpec.nativeModuleSpec(MODULE_NAME, new Provider() { // from class: com.amazon.dee.app.elements.-$$Lambda$SMSMessagingPackage$hJDsEbwWpi_cIvHLeH1jnv2NSIA
            @Override // javax.inject.Provider
            /* renamed from: get */
            public final Object mo10268get() {
                return SMSMessagingPackage.this.lambda$getNativeModules$0$SMSMessagingPackage(reactApplicationContext);
            }
        }));
    }

    @Override // com.facebook.react.LazyReactPackage
    public ReactModuleInfoProvider getReactModuleInfoProvider() {
        return $$Lambda$SMSMessagingPackage$GaednhHQe9agu03QdknKOP85YgM.INSTANCE;
    }

    public /* synthetic */ NativeModule lambda$getNativeModules$0$SMSMessagingPackage(ReactApplicationContext reactApplicationContext) {
        SendSMSBridge sendSMSBridge = new SendSMSBridge(reactApplicationContext);
        this.constructorCalled = true;
        return sendSMSBridge;
    }
}
