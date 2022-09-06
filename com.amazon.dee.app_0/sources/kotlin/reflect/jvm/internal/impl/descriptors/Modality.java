package kotlin.reflect.jvm.internal.impl.descriptors;

import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: Modality.kt */
/* loaded from: classes2.dex */
public enum Modality {
    FINAL,
    SEALED,
    OPEN,
    ABSTRACT;
    
    public static final Companion Companion = new Companion(null);

    /* compiled from: Modality.kt */
    /* loaded from: classes2.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final Modality convertFromFlags(boolean z, boolean z2) {
            if (z) {
                return Modality.ABSTRACT;
            }
            if (z2) {
                return Modality.OPEN;
            }
            return Modality.FINAL;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
