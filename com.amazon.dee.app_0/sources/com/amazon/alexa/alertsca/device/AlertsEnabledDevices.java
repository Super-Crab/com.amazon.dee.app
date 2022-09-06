package com.amazon.alexa.alertsca.device;

import java.util.HashSet;
import java.util.Set;
/* loaded from: classes6.dex */
public class AlertsEnabledDevices {
    private static final Set<Device> alertsEnabledDevices = new HashSet();

    public AlertsEnabledDevices() {
        alertsEnabledDevices.add(Device.create("motorola", "payton", "motorola", "moto x4", "payton"));
        alertsEnabledDevices.add(Device.create("motorola", "payton", "motorola", "moto x4", "payton_playpl"));
        alertsEnabledDevices.add(Device.create("motorola", "payton", "motorola", "moto x4", "payton_tmopl"));
        alertsEnabledDevices.add(Device.create("motorola", "payton", "motorola", "moto x4", "payton_retru"));
        alertsEnabledDevices.add(Device.create("motorola", "payton", "motorola", "moto x4", "payton_amz"));
        alertsEnabledDevices.add(Device.create("motorola", "payton_sprout", "motorola", "moto x4", "payton_fi"));
        alertsEnabledDevices.add(Device.create("motorola", "messi", "motorola", "moto z3", "messi"));
        alertsEnabledDevices.add(Device.create("motorola", "messi", "motorola", "moto z3", "messi_verizon"));
        alertsEnabledDevices.add(Device.create("motorola", "messi", "motorola", "moto z3", "messi_retcn"));
        alertsEnabledDevices.add(Device.create("motorola", "messi", "motorola", "moto z3", "messi_cmcc"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "moto z3 play", "beckham"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "moto z3 play", "beckham_3uk"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "moto z3 play", "beckham_retru"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "moto z3 play", "beckham_amz"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "moto z3 play", "beckham_sprint"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "moto z3 play", "beckham_amxla"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "moto z3 play", "beckham_amx"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "moto z3 play", "beckham_dteu"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "moto z3 play", "beckham_ora"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "moto z3 play", "beckham_playpl"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "moto z3 play", "beckham_vzw"));
        alertsEnabledDevices.add(Device.create("motorola", "beckham", "motorola", "XT1929-10", "beckham_retcn"));
    }

    public boolean isAlertsEnabled(Device device) {
        return alertsEnabledDevices.contains(device);
    }
}
