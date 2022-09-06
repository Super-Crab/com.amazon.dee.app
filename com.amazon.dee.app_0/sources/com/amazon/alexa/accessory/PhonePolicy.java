package com.amazon.alexa.accessory;

import android.os.Build;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
/* loaded from: classes.dex */
public final class PhonePolicy {
    static final long AWAIT_LE_ENCRYPTION_DELAY_MILLIS = 3000;
    static final boolean SUSPEND_LE_WRITES_WHILE_CONNECTING_RFCOMM = true;
    private static final PhonePolicy[] WHITE_LIST = {of("Samsung S8").manufacturer("SAMSUNG").model("SM-G950U").doNotAwaitLeEncryption().build(), of("Samsung S7").manufacturer("SAMSUNG").model("SM-G930U").doNotAwaitLeEncryption().build()};
    private final long awaitLeEncryptionDelayMillis;
    private final String manufacturer;
    private final String model;
    private final boolean suspendLeWritesWhileConnectingRfcomm;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static final class Builder {
        String manufacturer;
        String model;
        String name;
        long awaitLeEncryptionDelayMillis = 3000;
        boolean suspendLeWritesWhileConnectingRfcomm = true;

        Builder(String str) {
            this.name = str;
        }

        public Builder allowLeWritesWhileConnectingRfcomm() {
            this.suspendLeWritesWhileConnectingRfcomm = false;
            return this;
        }

        public PhonePolicy build() {
            Preconditions.notNull(this.name, "name");
            Preconditions.notNull(this.manufacturer, "manufacturer");
            Preconditions.notNull(this.model, "model");
            return new PhonePolicy(this);
        }

        public Builder doNotAwaitLeEncryption() {
            this.awaitLeEncryptionDelayMillis = 0L;
            return this;
        }

        public Builder manufacturer(String str) {
            this.manufacturer = str;
            return this;
        }

        public Builder model(String str) {
            this.model = str;
            return this;
        }
    }

    static {
        Logger.d("Device details manufacturer=%s, model=%s", Build.MANUFACTURER, Build.MODEL);
    }

    PhonePolicy(Builder builder) {
        this.manufacturer = builder.manufacturer;
        this.model = builder.model;
        this.awaitLeEncryptionDelayMillis = builder.awaitLeEncryptionDelayMillis;
        this.suspendLeWritesWhileConnectingRfcomm = builder.suspendLeWritesWhileConnectingRfcomm;
    }

    public static long awaitLeEncryptionDelayMillis() {
        PhonePolicy[] phonePolicyArr;
        for (PhonePolicy phonePolicy : WHITE_LIST) {
            if (phonePolicy.isApplicable()) {
                return phonePolicy.getAwaitLeEncryptionDelayMillis();
            }
        }
        return 3000L;
    }

    static Builder of(String str) {
        return new Builder(str);
    }

    public static boolean suspendLeWritesWhileConnectingRfcomm() {
        PhonePolicy[] phonePolicyArr;
        for (PhonePolicy phonePolicy : WHITE_LIST) {
            if (phonePolicy.isApplicable()) {
                return phonePolicy.isSuspendLeWritesWhileConnectingRfcomm();
            }
        }
        return true;
    }

    public long getAwaitLeEncryptionDelayMillis() {
        return this.awaitLeEncryptionDelayMillis;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }

    public String getModel() {
        return this.model;
    }

    public boolean isApplicable() {
        return Build.MANUFACTURER.equalsIgnoreCase(this.manufacturer) && Build.MODEL.equals(this.model);
    }

    public boolean isSuspendLeWritesWhileConnectingRfcomm() {
        return this.suspendLeWritesWhileConnectingRfcomm;
    }
}
