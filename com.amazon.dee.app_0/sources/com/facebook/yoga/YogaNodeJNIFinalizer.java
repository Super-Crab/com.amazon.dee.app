package com.facebook.yoga;
/* loaded from: classes2.dex */
public class YogaNodeJNIFinalizer extends YogaNodeJNIBase {
    public YogaNodeJNIFinalizer() {
    }

    protected void finalize() throws Throwable {
        try {
            freeNatives();
        } finally {
            super.finalize();
        }
    }

    public void freeNatives() {
        long j = this.mNativePointer;
        if (j != 0) {
            this.mNativePointer = 0L;
            YogaNative.jni_YGNodeFreeJNI(j);
        }
    }

    public YogaNodeJNIFinalizer(YogaConfig yogaConfig) {
        super(yogaConfig);
    }
}
