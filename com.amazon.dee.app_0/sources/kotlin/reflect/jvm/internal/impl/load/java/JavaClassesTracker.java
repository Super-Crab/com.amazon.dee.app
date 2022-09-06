package kotlin.reflect.jvm.internal.impl.load.java;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.descriptors.JavaClassDescriptor;
import org.jetbrains.annotations.NotNull;
/* compiled from: JavaClassesTracker.kt */
/* loaded from: classes3.dex */
public interface JavaClassesTracker {

    /* compiled from: JavaClassesTracker.kt */
    /* loaded from: classes3.dex */
    public static final class Default implements JavaClassesTracker {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // kotlin.reflect.jvm.internal.impl.load.java.JavaClassesTracker
        public void reportClass(@NotNull JavaClassDescriptor classDescriptor) {
            Intrinsics.checkParameterIsNotNull(classDescriptor, "classDescriptor");
        }
    }

    void reportClass(@NotNull JavaClassDescriptor javaClassDescriptor);
}
