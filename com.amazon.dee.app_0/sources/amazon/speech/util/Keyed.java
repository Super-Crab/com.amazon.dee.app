package amazon.speech.util;

import java.util.Objects;
/* loaded from: classes.dex */
public abstract class Keyed<T> {
    public boolean equals(Object obj) {
        return (obj instanceof Keyed) && Objects.equals(getKey(), ((Keyed) obj).getKey());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract T getKey();

    public int hashCode() {
        return getKey().hashCode();
    }
}
