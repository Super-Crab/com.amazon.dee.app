package com.amazonaws.mobileconnectors.remoteconfiguration.internal;
/* loaded from: classes13.dex */
public class Arn {
    private final String account;
    private final String id;
    private final String region;
    private final String type;
    private final String vendor;

    public Arn(String str, String str2, String str3, String str4, String str5) {
        this.vendor = str;
        this.region = str2;
        this.account = str3;
        this.type = str4;
        this.id = str5;
    }

    public static Arn fromArn(String str) {
        String[] split = str.split(":", 6);
        if (split.length == 6) {
            String[] split2 = split[5].split("[:/]", 2);
            return new Arn(split[2], split[3], split[4], split2[0], split2[1]);
        }
        throw new IllegalArgumentException("Invalid ARN");
    }

    public String getAccount() {
        return this.account;
    }

    public String getId() {
        return this.id;
    }

    public String getRegion() {
        return this.region;
    }

    public String getType() {
        return this.type;
    }

    public String getVendor() {
        return this.vendor;
    }

    public String toArn() {
        return String.format("arn:aws:%s:%s:%s:%s:%s", this.vendor, this.region, this.account, this.type, this.id);
    }
}
