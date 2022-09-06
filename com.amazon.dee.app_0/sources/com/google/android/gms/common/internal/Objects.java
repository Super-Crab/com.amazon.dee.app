package com.google.android.gms.common.internal;

import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlinx.serialization.json.internal.JsonReaderKt;
@KeepForSdk
/* loaded from: classes2.dex */
public final class Objects {

    @KeepForSdk
    /* loaded from: classes2.dex */
    public static final class ToStringHelper {
        private final List<String> zzer;
        private final Object zzes;

        private ToStringHelper(Object obj) {
            this.zzes = Preconditions.checkNotNull(obj);
            this.zzer = new ArrayList();
        }

        @KeepForSdk
        public final ToStringHelper add(String str, @Nullable Object obj) {
            List<String> list = this.zzer;
            String str2 = (String) Preconditions.checkNotNull(str);
            String valueOf = String.valueOf(obj);
            StringBuilder sb = new StringBuilder(valueOf.length() + GeneratedOutlineSupport1.outline6(str2, 1));
            sb.append(str2);
            sb.append(Config.Compare.EQUAL_TO);
            sb.append(valueOf);
            list.add(sb.toString());
            return this;
        }

        @KeepForSdk
        public final String toString() {
            StringBuilder sb = new StringBuilder(100);
            sb.append(this.zzes.getClass().getSimpleName());
            sb.append(JsonReaderKt.BEGIN_OBJ);
            int size = this.zzer.size();
            for (int i = 0; i < size; i++) {
                sb.append(this.zzer.get(i));
                if (i < size - 1) {
                    sb.append(", ");
                }
            }
            sb.append(JsonReaderKt.END_OBJ);
            return sb.toString();
        }
    }

    private Objects() {
        throw new AssertionError("Uninstantiable");
    }

    @KeepForSdk
    public static boolean equal(@Nullable Object obj, @Nullable Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    @KeepForSdk
    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @KeepForSdk
    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj);
    }
}
