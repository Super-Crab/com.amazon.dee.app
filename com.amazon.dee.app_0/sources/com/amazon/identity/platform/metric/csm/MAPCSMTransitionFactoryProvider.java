package com.amazon.identity.platform.metric.csm;

import com.amazon.identity.auth.device.io;
/* compiled from: DCP */
/* loaded from: classes12.dex */
final class MAPCSMTransitionFactoryProvider {
    private static final String TAG = "MAPCSMTransitionFactoryProvider";
    private MAPCSMTransitionFactory vH;
    private Class vI;

    /* compiled from: DCP */
    /* loaded from: classes12.dex */
    static class a {
        private static final MAPCSMTransitionFactoryProvider vJ = new MAPCSMTransitionFactoryProvider();
    }

    public static MAPCSMTransitionFactoryProvider getInstance() {
        return a.vJ;
    }

    private void getMAPCSMTransitionFactoryImpl() {
        try {
            this.vI = Class.forName("com.amazon.csm.map.MAPCSMTransitionFactoryImpl", false, MAPCSMTransitionFactoryProvider.class.getClassLoader());
            if (this.vI == null) {
                return;
            }
            this.vH = (MAPCSMTransitionFactory) this.vI.newInstance();
            io.dm(TAG);
        } catch (ClassNotFoundException unused) {
            io.dm(TAG);
        } catch (IllegalAccessException unused2) {
            io.dn(TAG);
        } catch (InstantiationException unused3) {
            io.dn(TAG);
        }
    }

    public MAPCSMTransitionFactory getMAPCSMTransitionFactory() {
        return this.vH;
    }

    private MAPCSMTransitionFactoryProvider() {
        getMAPCSMTransitionFactoryImpl();
    }
}
