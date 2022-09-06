package com.amazon.devicesetup.provisioning.ble.command;

import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.UUID;
/* loaded from: classes12.dex */
public class ProvisioningCommandResponse {
    private final byte[] data;
    private final UUID identifier;
    private final int status;

    public ProvisioningCommandResponse(UUID uuid, int i) {
        this(uuid, i, null);
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

    public int getStatus() {
        return this.status;
    }

    public String getStatusMessage() {
        if (!ProvisioningCommands.PROVISIONING_COMMANDS_STATUS_MAPS.containsKey(this.identifier) || !ProvisioningCommands.PROVISIONING_COMMANDS_STATUS_MAPS.get(this.identifier).containsKey(Integer.valueOf(this.status))) {
            return null;
        }
        return ProvisioningCommands.PROVISIONING_COMMANDS_STATUS_MAPS.get(this.identifier).get(Integer.valueOf(this.status));
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ProvisioningCommandResponse [id=");
        outline107.append(this.identifier);
        outline107.append(", status=");
        outline107.append(getStatusMessage());
        outline107.append("]");
        return outline107.toString();
    }

    public ProvisioningCommandResponse(UUID uuid, int i, byte[] bArr) {
        if (uuid != null) {
            this.identifier = uuid;
            this.status = i;
            this.data = bArr == null ? null : (byte[]) bArr.clone();
            return;
        }
        throw new IllegalArgumentException("identifier cannot be null.");
    }
}
