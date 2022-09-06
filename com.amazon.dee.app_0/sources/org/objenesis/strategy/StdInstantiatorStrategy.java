package org.objenesis.strategy;

import org.objenesis.instantiator.ObjectInstantiator;
import org.objenesis.instantiator.android.Android10Instantiator;
import org.objenesis.instantiator.android.Android17Instantiator;
import org.objenesis.instantiator.android.Android18Instantiator;
import org.objenesis.instantiator.gcj.GCJInstantiator;
import org.objenesis.instantiator.jrockit.JRockitLegacyInstantiator;
import org.objenesis.instantiator.perc.PercInstantiator;
import org.objenesis.instantiator.sun.SunReflectionFactoryInstantiator;
import org.objenesis.instantiator.sun.UnsafeFactoryInstantiator;
/* loaded from: classes5.dex */
public class StdInstantiatorStrategy extends BaseInstantiatorStrategy {
    @Override // org.objenesis.strategy.InstantiatorStrategy
    public <T> ObjectInstantiator<T> newInstantiatorOf(Class<T> cls) {
        String str;
        if (PlatformDescription.isThisJVM(PlatformDescription.SUN) || PlatformDescription.isThisJVM(PlatformDescription.OPENJDK)) {
            return new SunReflectionFactoryInstantiator(cls);
        }
        if (PlatformDescription.isThisJVM(PlatformDescription.JROCKIT)) {
            return (!PlatformDescription.VM_VERSION.startsWith("1.4") || PlatformDescription.VENDOR_VERSION.startsWith("R") || ((str = PlatformDescription.VM_INFO) != null && str.startsWith("R25.1") && PlatformDescription.VM_INFO.startsWith("R25.2"))) ? new SunReflectionFactoryInstantiator(cls) : new JRockitLegacyInstantiator(cls);
        } else if (!PlatformDescription.isThisJVM(PlatformDescription.DALVIK)) {
            return PlatformDescription.isThisJVM(PlatformDescription.GNU) ? new GCJInstantiator(cls) : PlatformDescription.isThisJVM(PlatformDescription.PERC) ? new PercInstantiator(cls) : new UnsafeFactoryInstantiator(cls);
        } else {
            int i = PlatformDescription.ANDROID_VERSION;
            return i <= 10 ? new Android10Instantiator(cls) : i <= 17 ? new Android17Instantiator(cls) : new Android18Instantiator(cls);
        }
    }
}
