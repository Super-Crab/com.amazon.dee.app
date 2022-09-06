package com.amazon.alexa.client.core.capabilities;

import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.JsonObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public final class AutoValue_Capability extends Capability {
    private final JsonObject configurations;
    private final CapabilityInterface interface0;
    private final CapabilityType type;
    private final String version;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_Capability(CapabilityType capabilityType, CapabilityInterface capabilityInterface, String str, @Nullable JsonObject jsonObject) {
        if (capabilityType != null) {
            this.type = capabilityType;
            if (capabilityInterface != null) {
                this.interface0 = capabilityInterface;
                if (str != null) {
                    this.version = str;
                    this.configurations = jsonObject;
                    return;
                }
                throw new NullPointerException("Null version");
            }
            throw new NullPointerException("Null interface");
        }
        throw new NullPointerException("Null type");
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Capability)) {
            return false;
        }
        Capability capability = (Capability) obj;
        if (this.type.equals(capability.getType()) && this.interface0.equals(capability.getInterface()) && this.version.equals(capability.getVersion())) {
            JsonObject jsonObject = this.configurations;
            if (jsonObject == null) {
                if (capability.getConfigurations() == null) {
                    return true;
                }
            } else if (jsonObject.equals(capability.getConfigurations())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.amazon.alexa.client.core.capabilities.Capability
    @Nullable
    public JsonObject getConfigurations() {
        return this.configurations;
    }

    @Override // com.amazon.alexa.client.core.capabilities.Capability
    public CapabilityInterface getInterface() {
        return this.interface0;
    }

    @Override // com.amazon.alexa.client.core.capabilities.Capability
    public CapabilityType getType() {
        return this.type;
    }

    @Override // com.amazon.alexa.client.core.capabilities.Capability
    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int hashCode = (((((this.type.hashCode() ^ 1000003) * 1000003) ^ this.interface0.hashCode()) * 1000003) ^ this.version.hashCode()) * 1000003;
        JsonObject jsonObject = this.configurations;
        return hashCode ^ (jsonObject == null ? 0 : jsonObject.hashCode());
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Capability{type=");
        outline107.append(this.type);
        outline107.append(", interface=");
        outline107.append(this.interface0);
        outline107.append(", version=");
        outline107.append(this.version);
        outline107.append(", configurations=");
        outline107.append(this.configurations);
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        return outline107.toString();
    }
}
