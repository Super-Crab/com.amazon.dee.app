package com.amazon.devicesetup.provisioning.ble.command;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
/* loaded from: classes12.dex */
public class ProvisioningCommand {
    private final byte[] data;
    private final UUID identifier;

    public ProvisioningCommand(UUID uuid) {
        this(uuid, null);
    }

    public byte[] getData() {
        byte[] bArr = this.data;
        if (bArr == null) {
            return null;
        }
        return (byte[]) bArr.clone();
    }

    public UUID getIdentifier() {
        return this.identifier;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningCommand [id=");
        outline107.append(this.identifier);
        outline107.append("]");
        return outline107.toString();
    }

    public ProvisioningCommand(UUID uuid, byte[] bArr) {
        if (uuid != null) {
            this.identifier = uuid;
            this.data = bArr == null ? null : (byte[]) bArr.clone();
            return;
        }
        throw new IllegalArgumentException("identifier cannot be null.");
    }
}
