package chip.setuppayload;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class SetupPayload {
    public int commissioningFlow;
    public Set<DiscoveryCapability> discoveryCapabilities;
    public int discriminator;
    public Map<Integer, OptionalQRCodeInfo> optionalQRCodeInfo = new HashMap();
    public int productId;
    public long setupPinCode;
    public int vendorId;
    public int version;

    public SetupPayload() {
    }

    public void addOptionalQRCodeInfo(OptionalQRCodeInfo optionalQRCodeInfo) {
        this.optionalQRCodeInfo.put(Integer.valueOf(optionalQRCodeInfo.tag), optionalQRCodeInfo);
    }

    public SetupPayload(int i, int i2, int i3, int i4, Set<DiscoveryCapability> set, int i5, long j) {
        this.version = i;
        this.vendorId = i2;
        this.productId = i3;
        this.commissioningFlow = i4;
        this.discoveryCapabilities = set;
        this.discriminator = i5;
        this.setupPinCode = j;
    }
}
