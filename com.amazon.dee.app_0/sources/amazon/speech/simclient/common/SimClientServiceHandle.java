package amazon.speech.simclient.common;

import amazon.speech.simclient.common.BaseClient;
import android.content.Context;
/* loaded from: classes.dex */
public abstract class SimClientServiceHandle<T extends BaseClient> {
    private final Class<T> mClientClass;

    public SimClientServiceHandle(Class<T> cls) {
        this.mClientClass = cls;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: createClient */
    public abstract T mo50createClient(Context context);

    public boolean equals(Object obj) {
        if (!(obj instanceof SimClientServiceHandle)) {
            return false;
        }
        return this.mClientClass.equals(((SimClientServiceHandle) obj).mClientClass);
    }

    public int hashCode() {
        return this.mClientClass.hashCode();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean serviceExists(Context context);
}
