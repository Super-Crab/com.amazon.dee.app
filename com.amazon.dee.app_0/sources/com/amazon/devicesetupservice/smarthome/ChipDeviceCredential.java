package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class ChipDeviceCredential implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.ChipDeviceCredential");
    private Integer discriminator;
    private Integer passcode;
    private Integer productId;
    private ByteBuffer uniqueId;
    private Integer vendorId;

    public boolean equals(Object obj) {
        if (!(obj instanceof ChipDeviceCredential)) {
            return false;
        }
        ChipDeviceCredential chipDeviceCredential = (ChipDeviceCredential) obj;
        return Helper.equals(this.productId, chipDeviceCredential.productId) && Helper.equals(this.discriminator, chipDeviceCredential.discriminator) && Helper.equals(this.uniqueId, chipDeviceCredential.uniqueId) && Helper.equals(this.vendorId, chipDeviceCredential.vendorId) && Helper.equals(this.passcode, chipDeviceCredential.passcode);
    }

    public Integer getDiscriminator() {
        return this.discriminator;
    }

    public Integer getPasscode() {
        return this.passcode;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public ByteBuffer getUniqueId() {
        return this.uniqueId;
    }

    public Integer getVendorId() {
        return this.vendorId;
    }

    public int hashCode() {
        return Helper.hash(Integer.valueOf(classNameHashCode), this.productId, this.discriminator, this.uniqueId, this.vendorId, this.passcode);
    }

    public void setDiscriminator(Integer num) {
        this.discriminator = num;
    }

    public void setPasscode(Integer num) {
        this.passcode = num;
    }

    public void setProductId(Integer num) {
        this.productId = num;
    }

    public void setUniqueId(ByteBuffer byteBuffer) {
        this.uniqueId = byteBuffer;
    }

    public void setVendorId(Integer num) {
        this.vendorId = num;
    }
}
