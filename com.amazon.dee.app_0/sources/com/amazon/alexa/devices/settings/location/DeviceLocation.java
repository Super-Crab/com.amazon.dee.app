package com.amazon.alexa.devices.settings.location;
/* loaded from: classes6.dex */
public class DeviceLocation {
    private static final int VERSION = 0;
    private final String address1;
    private final String city;
    private final String countryCode;
    private final String postalCode;
    private final String state;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DeviceLocation(String str, String str2, String str3, String str4, String str5) {
        this.postalCode = str;
        this.address1 = str2;
        this.city = str3;
        this.state = str4;
        this.countryCode = str5;
    }

    public String getAddress() {
        return this.address1;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getState() {
        return this.state;
    }
}
