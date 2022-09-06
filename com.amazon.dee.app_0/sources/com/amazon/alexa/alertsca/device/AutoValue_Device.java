package com.amazon.alexa.alertsca.device;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
final class AutoValue_Device extends Device {
    private final String brand;
    private final String device;
    private final String manufacturer;
    private final String model;
    private final String product;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Device(String str, String str2, String str3, String str4, String str5) {
        if (str != null) {
            this.brand = str;
            if (str2 != null) {
                this.device = str2;
                if (str3 != null) {
                    this.manufacturer = str3;
                    if (str4 != null) {
                        this.model = str4;
                        if (str5 != null) {
                            this.product = str5;
                            return;
                        }
                        throw new NullPointerException("Null product");
                    }
                    throw new NullPointerException("Null model");
                }
                throw new NullPointerException("Null manufacturer");
            }
            throw new NullPointerException("Null device");
        }
        throw new NullPointerException("Null brand");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return this.brand.equals(device.getBrand()) && this.device.equals(device.getDevice()) && this.manufacturer.equals(device.getManufacturer()) && this.model.equals(device.getModel()) && this.product.equals(device.getProduct());
    }

    @Override // com.amazon.alexa.alertsca.device.Device
    public String getBrand() {
        return this.brand;
    }

    @Override // com.amazon.alexa.alertsca.device.Device
    public String getDevice() {
        return this.device;
    }

    @Override // com.amazon.alexa.alertsca.device.Device
    public String getManufacturer() {
        return this.manufacturer;
    }

    @Override // com.amazon.alexa.alertsca.device.Device
    public String getModel() {
        return this.model;
    }

    @Override // com.amazon.alexa.alertsca.device.Device
    public String getProduct() {
        return this.product;
    }

    public int hashCode() {
        return ((((((((this.brand.hashCode() ^ 1000003) * 1000003) ^ this.device.hashCode()) * 1000003) ^ this.manufacturer.hashCode()) * 1000003) ^ this.model.hashCode()) * 1000003) ^ this.product.hashCode();
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Device{brand=");
        outline107.append(this.brand);
        outline107.append(", device=");
        outline107.append(this.device);
        outline107.append(", manufacturer=");
        outline107.append(this.manufacturer);
        outline107.append(", model=");
        outline107.append(this.model);
        outline107.append(", product=");
        return GeneratedOutlineSupport1.outline91(outline107, this.product, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
