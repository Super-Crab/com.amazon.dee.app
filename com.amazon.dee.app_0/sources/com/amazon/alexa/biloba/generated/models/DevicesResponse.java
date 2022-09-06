package com.amazon.alexa.biloba.generated.models;

import com.amazon.alexa.biloba.model.Device;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes6.dex */
public class DevicesResponse {
    @SerializedName("devices")
    private List<Device> devices = new ArrayList();
    @SerializedName("messages")
    private Map<String, Message> messages = new HashMap();

    private String toIndentedString(Object obj) {
        return obj == null ? "null" : obj.toString().replace("\n", "\n    ");
    }

    public DevicesResponse addDevicesItem(Device device) {
        this.devices.add(device);
        return this;
    }

    public DevicesResponse devices(List<Device> list) {
        this.devices = list;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || DevicesResponse.class != obj.getClass()) {
            return false;
        }
        DevicesResponse devicesResponse = (DevicesResponse) obj;
        return Objects.equals(this.devices, devicesResponse.devices) && Objects.equals(this.messages, devicesResponse.messages);
    }

    public List<Device> getDevices() {
        return this.devices;
    }

    public Map<String, Message> getMessages() {
        return this.messages;
    }

    public int hashCode() {
        return Objects.hash(this.devices, this.messages);
    }

    public DevicesResponse messages(Map<String, Message> map) {
        this.messages = map;
        return this;
    }

    public DevicesResponse putMessagesItem(String str, Message message) {
        this.messages.put(str, message);
        return this;
    }

    public void setDevices(List<Device> list) {
        this.devices = list;
    }

    public void setMessages(Map<String, Message> map) {
        this.messages = map;
    }

    public String toString() {
        StringBuilder outline113 = GeneratedOutlineSupport1.outline113("class DevicesResponse {\n", "    devices: ");
        GeneratedOutlineSupport1.outline180(outline113, toIndentedString(this.devices), "\n", "    messages: ");
        return GeneratedOutlineSupport1.outline92(outline113, toIndentedString(this.messages), "\n", EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
