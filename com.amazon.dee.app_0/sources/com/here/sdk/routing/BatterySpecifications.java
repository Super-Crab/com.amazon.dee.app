package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class BatterySpecifications {
    @NonNull
    public Map<Double, Double> chargingCurve;
    @NonNull
    public List<ChargingConnectorType> connectorTypes;
    public double initialChargeInKilowattHours;
    public double targetChargeInKilowattHours;
    public double totalCapacityInKilowattHours;

    public BatterySpecifications() {
        this.totalCapacityInKilowattHours = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.initialChargeInKilowattHours = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.targetChargeInKilowattHours = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.chargingCurve = new HashMap();
        this.connectorTypes = new ArrayList();
    }

    public BatterySpecifications(double d, double d2, double d3, @NonNull Map<Double, Double> map, @NonNull List<ChargingConnectorType> list) {
        this.totalCapacityInKilowattHours = d;
        this.initialChargeInKilowattHours = d2;
        this.targetChargeInKilowattHours = d3;
        this.chargingCurve = map;
        this.connectorTypes = list;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BatterySpecifications)) {
            return false;
        }
        BatterySpecifications batterySpecifications = (BatterySpecifications) obj;
        return Double.compare(this.totalCapacityInKilowattHours, batterySpecifications.totalCapacityInKilowattHours) == 0 && Double.compare(this.initialChargeInKilowattHours, batterySpecifications.initialChargeInKilowattHours) == 0 && Double.compare(this.targetChargeInKilowattHours, batterySpecifications.targetChargeInKilowattHours) == 0 && Objects.equals(this.chargingCurve, batterySpecifications.chargingCurve) && Objects.equals(this.connectorTypes, batterySpecifications.connectorTypes);
    }

    public int hashCode() {
        int doubleToLongBits = (((((((int) (Double.doubleToLongBits(this.totalCapacityInKilowattHours) ^ (Double.doubleToLongBits(this.totalCapacityInKilowattHours) >>> 32))) + JfifUtil.MARKER_EOI) * 31) + ((int) (Double.doubleToLongBits(this.initialChargeInKilowattHours) ^ (Double.doubleToLongBits(this.initialChargeInKilowattHours) >>> 32)))) * 31) + ((int) (Double.doubleToLongBits(this.targetChargeInKilowattHours) ^ (Double.doubleToLongBits(this.targetChargeInKilowattHours) >>> 32)))) * 31;
        Map<Double, Double> map = this.chargingCurve;
        int i = 0;
        int hashCode = (doubleToLongBits + (map != null ? map.hashCode() : 0)) * 31;
        List<ChargingConnectorType> list = this.connectorTypes;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }
}
