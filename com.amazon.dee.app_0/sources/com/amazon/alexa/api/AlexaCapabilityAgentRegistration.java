package com.amazon.alexa.api;

import android.content.ComponentName;
import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
/* loaded from: classes6.dex */
public class AlexaCapabilityAgentRegistration {
    public ComponentName componentName;
    public String packageName;

    /* loaded from: classes6.dex */
    static class Adapter implements u<AlexaCapabilityAgentRegistration> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes6.dex */
        public enum CapabilityAgentKeys implements Bundles.Key {
            KEY_COMPONENT,
            KEY_PACKAGE
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public AlexaCapabilityAgentRegistration mo844createFromBundle(Bundle bundle) {
            return new AlexaCapabilityAgentRegistration((ComponentName) bundle.getParcelable(CapabilityAgentKeys.KEY_COMPONENT.name()), bundle.getString(CapabilityAgentKeys.KEY_PACKAGE.name()));
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(AlexaCapabilityAgentRegistration alexaCapabilityAgentRegistration) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(CapabilityAgentKeys.KEY_COMPONENT.name(), alexaCapabilityAgentRegistration.componentName);
            bundle.putString(CapabilityAgentKeys.KEY_PACKAGE.name(), alexaCapabilityAgentRegistration.packageName);
            return bundle;
        }
    }

    public AlexaCapabilityAgentRegistration(ComponentName componentName, String str) {
        this.componentName = componentName;
        this.packageName = str;
    }
}
