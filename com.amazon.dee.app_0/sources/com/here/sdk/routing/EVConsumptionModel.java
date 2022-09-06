package com.here.sdk.routing;

import androidx.annotation.NonNull;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.imageutils.JfifUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes3.dex */
public final class EVConsumptionModel {
    public double ascentConsumptionInWattHoursPerMeter;
    public double auxiliaryConsumptionInWattHoursPerSecond;
    public double descentRecoveryInWattHoursPerMeter;
    @NonNull
    public Map<Integer, Double> freeFlowSpeedTable;
    @NonNull
    public Map<Integer, Double> trafficSpeedTable;

    public EVConsumptionModel() {
        this.ascentConsumptionInWattHoursPerMeter = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.descentRecoveryInWattHoursPerMeter = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        this.freeFlowSpeedTable = new HashMap();
        this.trafficSpeedTable = new HashMap();
        this.auxiliaryConsumptionInWattHoursPerSecond = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
    }

    public EVConsumptionModel(double d, double d2, @NonNull Map<Integer, Double> map, @NonNull Map<Integer, Double> map2, double d3) {
        this.ascentConsumptionInWattHoursPerMeter = d;
        this.descentRecoveryInWattHoursPerMeter = d2;
        this.freeFlowSpeedTable = map;
        this.trafficSpeedTable = map2;
        this.auxiliaryConsumptionInWattHoursPerSecond = d3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EVConsumptionModel)) {
            return false;
        }
        EVConsumptionModel eVConsumptionModel = (EVConsumptionModel) obj;
        return Double.compare(this.ascentConsumptionInWattHoursPerMeter, eVConsumptionModel.ascentConsumptionInWattHoursPerMeter) == 0 && Double.compare(this.descentRecoveryInWattHoursPerMeter, eVConsumptionModel.descentRecoveryInWattHoursPerMeter) == 0 && Objects.equals(this.freeFlowSpeedTable, eVConsumptionModel.freeFlowSpeedTable) && Objects.equals(this.trafficSpeedTable, eVConsumptionModel.trafficSpeedTable) && Double.compare(this.auxiliaryConsumptionInWattHoursPerSecond, eVConsumptionModel.auxiliaryConsumptionInWattHoursPerSecond) == 0;
    }

    public int hashCode() {
        int doubleToLongBits = (((((int) (Double.doubleToLongBits(this.ascentConsumptionInWattHoursPerMeter) ^ (Double.doubleToLongBits(this.ascentConsumptionInWattHoursPerMeter) >>> 32))) + JfifUtil.MARKER_EOI) * 31) + ((int) (Double.doubleToLongBits(this.descentRecoveryInWattHoursPerMeter) ^ (Double.doubleToLongBits(this.descentRecoveryInWattHoursPerMeter) >>> 32)))) * 31;
        Map<Integer, Double> map = this.freeFlowSpeedTable;
        int i = 0;
        int hashCode = (doubleToLongBits + (map != null ? map.hashCode() : 0)) * 31;
        Map<Integer, Double> map2 = this.trafficSpeedTable;
        if (map2 != null) {
            i = map2.hashCode();
        }
        return ((hashCode + i) * 31) + ((int) (Double.doubleToLongBits(this.auxiliaryConsumptionInWattHoursPerSecond) ^ (Double.doubleToLongBits(this.auxiliaryConsumptionInWattHoursPerSecond) >>> 32)));
    }
}
