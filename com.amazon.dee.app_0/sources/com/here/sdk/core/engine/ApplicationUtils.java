package com.here.sdk.core.engine;

import androidx.annotation.NonNull;
import com.facebook.imageutils.JfifUtil;
import com.here.NativeBase;
import java.util.Objects;
/* loaded from: classes3.dex */
class ApplicationUtils extends NativeBase {

    /* loaded from: classes3.dex */
    static final class ApplicationInformation {
        @NonNull
        String applicationVersion;

        ApplicationInformation() {
            this.applicationVersion = "Unknown";
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ApplicationInformation(@NonNull String str) {
            this.applicationVersion = str;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ApplicationInformation) {
                return Objects.equals(this.applicationVersion, ((ApplicationInformation) obj).applicationVersion);
            }
            return false;
        }

        public int hashCode() {
            String str = this.applicationVersion;
            return (str != null ? str.hashCode() : 0) + JfifUtil.MARKER_EOI;
        }
    }

    protected ApplicationUtils(long j, Object obj) {
        super(j, new NativeBase.Disposer() { // from class: com.here.sdk.core.engine.ApplicationUtils.1
            @Override // com.here.NativeBase.Disposer
            public void disposeNative(long j2) {
                ApplicationUtils.disposeNativeHandle(j2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static native void disposeNativeHandle(long j);

    @NonNull
    static native ApplicationInformation getApplicationInformation();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void setApplicationInformation(@NonNull ApplicationInformation applicationInformation);
}
