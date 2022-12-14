package androidx.core.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes.dex */
public class Pair<F, S> {
    @Nullable
    public final F first;
    @Nullable
    public final S second;

    public Pair(@Nullable F f, @Nullable S s) {
        this.first = f;
        this.second = s;
    }

    @NonNull
    public static <A, B> Pair<A, B> create(@Nullable A a, @Nullable B b) {
        return new Pair<>(a, b);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        return ObjectsCompat.equals(pair.first, this.first) && ObjectsCompat.equals(pair.second, this.second);
    }

    public int hashCode() {
        F f = this.first;
        int i = 0;
        int hashCode = f == null ? 0 : f.hashCode();
        S s = this.second;
        if (s != null) {
            i = s.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Pair{");
        outline107.append(String.valueOf(this.first));
        outline107.append(" ");
        outline107.append(String.valueOf(this.second));
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
