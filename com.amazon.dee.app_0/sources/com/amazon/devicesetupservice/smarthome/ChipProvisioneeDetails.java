package com.amazon.devicesetupservice.smarthome;

import com.amazon.CoralAndroidClient.ClientBase.ClientInput;
import com.amazon.CoralAndroidClient.ClientBase.ClientOutput;
import com.amazon.CoralAndroidClient.ClientBase.Helper;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public class ChipProvisioneeDetails extends SmartHomeProvisioneeDetails implements ClientOutput, ClientInput {
    private static final int classNameHashCode = Helper.hash("com.amazon.devicesetupservice.smarthome.ChipProvisioneeDetails");
    private Integer discriminator;
    private Integer productId;
    private ByteBuffer rotatingId;
    private String transport;
    private Integer vendorId;

    @Override // com.amazon.devicesetupservice.smarthome.SmartHomeProvisioneeDetails, com.amazon.devicesetupservice.ProvisioneeDetails
    public boolean equals(Object obj) {
        if (!(obj instanceof ChipProvisioneeDetails)) {
            return false;
        }
        ChipProvisioneeDetails chipProvisioneeDetails = (ChipProvisioneeDetails) obj;
        return super.equals(obj) && Helper.equals(this.vendorId, chipProvisioneeDetails.vendorId) && Helper.equals(this.productId, chipProvisioneeDetails.productId) && Helper.equals(this.rotatingId, chipProvisioneeDetails.rotatingId) && Helper.equals(this.transport, chipProvisioneeDetails.transport) && Helper.equals(this.discriminator, chipProvisioneeDetails.discriminator);
    }

    public Integer getDiscriminator() {
        return this.discriminator;
    }

    public Integer getProductId() {
        return this.productId;
    }

    public ByteBuffer getRotatingId() {
        return this.rotatingId;
    }

    public String getTransport() {
        return this.transport;
    }

    public Integer getVendorId() {
        return this.vendorId;
    }

    @Override // com.amazon.devicesetupservice.smarthome.SmartHomeProvisioneeDetails, com.amazon.devicesetupservice.ProvisioneeDetails
    public int hashCode() {
        return Helper.hash(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), this.vendorId, this.productId, this.rotatingId, this.transport, this.discriminator);
    }

    public void setDiscriminator(Integer num) {
        this.discriminator = num;
    }

    public void setProductId(Integer num) {
        this.productId = num;
    }

    public void setRotatingId(ByteBuffer byteBuffer) {
        this.rotatingId = byteBuffer;
    }

    public void setTransport(String str) {
        this.transport = str;
    }

    public void setVendorId(Integer num) {
        this.vendorId = num;
    }
}
