package amazon.communication;

import com.amazon.communication.ParcelableConnectionPolicy;
import com.amazon.communication.SimpleConnectionPolicy;
import com.amazon.communication.SimpleConnectionPolicyBuilder;
/* loaded from: classes.dex */
public final class DeviceConnectionPolicyBuilder extends SimpleConnectionPolicyBuilder {
    @Override // com.amazon.communication.SimpleConnectionPolicyBuilder
    protected SimpleConnectionPolicy constructPolicy() {
        return new ParcelableConnectionPolicy();
    }
}
