package com.amazon.alexa.accessory.avsclient.metrics;

import android.util.SparseArray;
import com.amazon.alexa.accessory.avsclient.metrics.AccessoryMetricsObserver;
/* loaded from: classes.dex */
public final class MetricMappings {
    private final SparseArray<AccessoryMetricsObserver.Handler> map = createHandlers();

    private SparseArray<AccessoryMetricsObserver.Handler> createHandlers() {
        return new SparseArray<AccessoryMetricsObserver.Handler>() { // from class: com.amazon.alexa.accessory.avsclient.metrics.MetricMappings.1
            {
                append(MetricMappings.keyFor(0, 0), new CounterMetricsHandler("Buttons.MuteButtonPressed"));
                append(MetricMappings.keyFor(0, 1), new CounterMetricsHandler("Buttons.MuteButtonUnpressed"));
                append(MetricMappings.keyFor(0, 2), new CounterMetricsHandler("Buttons.ActionButtonNormalPress"));
                append(MetricMappings.keyFor(0, 3), new CounterMetricsHandler("Buttons.ActionButtonXLPress"));
                append(MetricMappings.keyFor(0, 4), new CounterMetricsHandler("Buttons.ActionButtonLPress"));
                append(MetricMappings.keyFor(1, 8), new CounterMetricsHandler("Alexa.ShortEndpointEvent"));
                append(MetricMappings.keyFor(1, 9), new CounterMetricsHandler("Alexa.LongEndpointEvent"));
                append(MetricMappings.keyFor(1, 10), new CounterMetricsHandler("Alexa.NearMissEndpointEvent"));
                append(MetricMappings.keyFor(1, 11), new CounterMetricsHandler("Alexa.BootUpAccessory.Earcon"));
                append(MetricMappings.keyFor(1, 12), new CounterMetricsHandler("Alexa.BootUpOOBE.Earcon"));
                append(MetricMappings.keyFor(1, 13), new CounterMetricsHandler("Alexa.MetricPrivacyModeOff.Earcon"));
                append(MetricMappings.keyFor(1, 14), new CounterMetricsHandler("Alexa.MetricPrivacyModeOn.Earcon"));
                append(MetricMappings.keyFor(1, 15), new CounterMetricsHandler("Alexa.AuxError.Earcon"));
                append(MetricMappings.keyFor(1, 16), new CounterMetricsHandler("Alexa.BTConnectionLost.Earcon"));
                append(MetricMappings.keyFor(1, 17), new CounterMetricsHandler("Alexa.DeviceBTBroadcasting.Earcon"));
                append(MetricMappings.keyFor(1, 18), new CounterMetricsHandler("Alexa.DeviceBTBroadcastingNothingFound.Earcon"));
                append(MetricMappings.keyFor(1, 19), new CounterMetricsHandler("Alexa.OOBESetupAFTHello.Earcon"));
                append(MetricMappings.keyFor(1, 20), new CounterMetricsHandler("Alexa.BTBroadcastingError1.Earcon"));
                append(MetricMappings.keyFor(1, 21), new CounterMetricsHandler("Alexa.OOBESetupError.Earcon"));
                append(MetricMappings.keyFor(1, 22), new CounterMetricsHandler("Alexa.OOBESetupHelloTTS.Earcon"));
                append(MetricMappings.keyFor(1, 23), new CounterMetricsHandler("Alexa.OOBESetupModeOff.Earcon"));
                append(MetricMappings.keyFor(1, 24), new CounterMetricsHandler("Alexa.OOBESetupModeOn.Earcon"));
                append(MetricMappings.keyFor(1, 25), new CounterMetricsHandler("Alexa.OOBESetupShortPress.Earcon"));
                append(MetricMappings.keyFor(1, 26), new CounterMetricsHandler("Alexa.OTABlocking.Earcon"));
                append(MetricMappings.keyFor(1, 27), new CounterMetricsHandler("Alexa.BTBroadcastError2.Earcon"));
                append(MetricMappings.keyFor(1, 28), new CounterMetricsHandler("Alexa.ThermalError.Earcon"));
                append(MetricMappings.keyFor(1, 29), new CounterMetricsHandler("Alexa.AppTTS.Earcon"));
                append(MetricMappings.keyFor(1, 30), new CounterMetricsHandler("Alexa.StateDeviceReset.Earcon"));
                append(MetricMappings.keyFor(1, 31), new CounterMetricsHandler("Alexa.FactoryReset.Earcon"));
                append(MetricMappings.keyFor(1, 32), new CounterMetricsHandler("Alexa.UIEndpoint.Earcon"));
                append(MetricMappings.keyFor(1, 33), new CounterMetricsHandler("Alexa.UIWakesound.Earcon"));
                append(MetricMappings.keyFor(1, 34), new CounterMetricsHandler("Alexa.AudioFromDiffDevice.Earcon"));
                append(MetricMappings.keyFor(2, 0), new TimerMetricsHandler("Boot.CompleteLatency"));
                append(MetricMappings.keyFor(2, 1), new TimerMetricsHandler("Boot.DSPReadyLatency"));
                append(MetricMappings.keyFor(2, 2), new TimerMetricsHandler("Boot.BLEPairLatency"));
                append(MetricMappings.keyFor(2, 10), new TimerMetricsHandler("Boot.ReconnectLatency"));
                append(MetricMappings.keyFor(2, 11), new TimerMetricsHandler("Boot.AlexaReadyLatency"));
                append(MetricMappings.keyFor(2, 12), new CounterMetricsHandler("Boot.NoSppAfterAcl"));
                append(MetricMappings.keyFor(2, 13), new CounterMetricsHandler("Boot.NoEapAfterIap"));
                append(MetricMappings.keyFor(3, 0), new TimerMetricsHandler("Setup.DonDuration"));
                append(MetricMappings.keyFor(3, 1), new TimerMetricsHandler("Setup.EarbudConnectedDuration"));
                append(MetricMappings.keyFor(3, 2), new TimerMetricsHandler("Setup.MicMuteDuration"));
                append(MetricMappings.keyFor(3, 3), new CounterMetricsHandler("Muffin.Setup.topologyA"));
                append(MetricMappings.keyFor(3, 4), new CounterMetricsHandler("Muffin.Setup.topologyB"));
                append(MetricMappings.keyFor(3, 5), new CounterMetricsHandler("Muffin.Setup.topologyC"));
                append(MetricMappings.keyFor(6, 0), new CounterMetricsHandler("tmp103_1_sensor_temp"));
                append(MetricMappings.keyFor(6, 1), new CounterMetricsHandler("tmp103_2_sensor_temp"));
                append(MetricMappings.keyFor(6, 2), new CounterMetricsHandler("virtual_sensor_temp"));
                append(MetricMappings.keyFor(6, 3), new CounterMetricsHandler("throttle_state"));
                append(MetricMappings.keyFor(6, 4), new CounterMetricsHandler("virtual_sensor_temp_max"));
                append(MetricMappings.keyFor(6, 5), new CounterMetricsHandler("tmp103_boot_sensor_temp"));
                append(MetricMappings.keyFor(6, 6), new CounterMetricsHandler("tmp103_3_batt_temp"));
                append(MetricMappings.keyFor(6, 7), new CounterMetricsHandler("thermal_mitigation"));
                append(MetricMappings.keyFor(6, 8), new CounterMetricsHandler("Thermal.PcbGgTemperature"));
                append(MetricMappings.keyFor(6, 9), new TimerMetricsHandler("Thermal.RegulationDuration"));
                append(MetricMappings.keyFor(6, 10), new CounterMetricsHandler("Thermal.VirtualShortLongNormal"));
                append(MetricMappings.keyFor(7, 0), new CounterMetricsHandler("AFEDiagSNRatWW"));
                append(MetricMappings.keyFor(7, 1), new CounterMetricsHandler("AFEDiagAverageERLE"));
                append(MetricMappings.keyFor(7, 2), new CounterMetricsHandler("AFEDiagBeamChangeCount"));
                append(MetricMappings.keyFor(7, 3), new CounterMetricsHandler("AFEDIagAECDivergenceCount"));
                append(MetricMappings.keyFor(7, 4), new TimerMetricsHandler("DSP.SpeakerPlaybackTimer"));
                append(MetricMappings.keyFor(7, 5), new TimerMetricsHandler("DSP.EarbudPlaybackTimer"));
                append(MetricMappings.keyFor(7, 6), new TimerMetricsHandler("DSP.MicOnTime"));
                append(MetricMappings.keyFor(7, 7), new CounterMetricsHandler("DSP.A2DPVolumeChange"));
                append(MetricMappings.keyFor(7, 8), new CounterMetricsHandler("DSP.HFPVolumeChange"));
                append(MetricMappings.keyFor(8, 0), new CounterMetricsHandler("Power.DeviceOnTime"));
                append(MetricMappings.keyFor(8, 1), new TimerMetricsHandler("Power.PowerOnDuration"));
                append(MetricMappings.keyFor(8, 2), new TimerMetricsHandler("Power.BatteryChargingDuration"));
                append(MetricMappings.keyFor(8, 3), new CounterMetricsHandler("Power.BatteryChargingStatus"));
                append(MetricMappings.keyFor(8, 4), new CounterMetricsHandler("Power.BatteryStateOfChargeLevel"));
                append(MetricMappings.keyFor(8, 5), new CounterMetricsHandler("Power.BattDischargedCycles"));
                append(MetricMappings.keyFor(8, 6), new CounterMetricsHandler("Power.DsmEntered"));
                append(MetricMappings.keyFor(9, 0), new CounterMetricsHandler("Diag.MCUCrashEvent"));
                append(MetricMappings.keyFor(9, 1), new CounterMetricsHandler("Diag.DSPCrashEvent"));
                append(MetricMappings.keyFor(10, 0), new TimerMetricsHandler("DFU.ProcessLatency"));
                append(MetricMappings.keyFor(10, 1), new TimerMetricsHandler("DFU.RealLatency"));
                append(MetricMappings.keyFor(10, 2), new TimerMetricsHandler("DFU.DownloadTime"));
                append(MetricMappings.keyFor(10, 3), new CounterMetricsHandler("DFU.LRVersionsDiffer"));
                append(MetricMappings.keyFor(10, 4), new CounterMetricsHandler("DFU.InterruptionsCount"));
                append(MetricMappings.keyFor(11, 0), new TimerMetricsHandler("BT.ConnectedDuration"));
                append(MetricMappings.keyFor(11, 1), new TimerMetricsHandler("BT.A2DPConnectionDuration"));
                append(MetricMappings.keyFor(11, 2), new TimerMetricsHandler("BT.HFPConnectionDuration"));
                append(MetricMappings.keyFor(11, 3), new TimerMetricsHandler("BT.LinkDuration"));
                append(MetricMappings.keyFor(11, 4), new CounterMetricsHandler("BT.ConnStatus"));
                append(MetricMappings.keyFor(11, 5), new CounterMetricsHandler("BT.PairingRecordCount"));
                append(MetricMappings.keyFor(11, 6), new CounterMetricsHandler("BT.HandoverTriggered"));
                append(MetricMappings.keyFor(11, 7), new CounterMetricsHandler("BT.A2dpStatus"));
                append(MetricMappings.keyFor(11, 8), new CounterMetricsHandler("BT.HfpStatus"));
                append(MetricMappings.keyFor(11, 9), new CounterMetricsHandler("BT.SppStatus"));
                append(MetricMappings.keyFor(11, 10), new CounterMetricsHandler("BT.IapStatus"));
                append(MetricMappings.keyFor(11, 11), new CounterMetricsHandler("BT.AapStatus"));
                append(MetricMappings.keyFor(11, 12), new CounterMetricsHandler("BT.EapStatus"));
                append(MetricMappings.keyFor(11, 13), new CounterMetricsHandler("BT.LinkRssi"));
                append(MetricMappings.keyFor(11, 14), new CounterMetricsHandler("BT.LinkPer"));
                append(MetricMappings.keyFor(11, 15), new CounterMetricsHandler("BT.LinkInfo"));
                append(MetricMappings.keyFor(11, 16), new CounterMetricsHandler("BT.LinkPhyRate"));
                append(MetricMappings.keyFor(11, 17), new CounterMetricsHandler("BT.BudAssociation"));
                append(MetricMappings.keyFor(11, 18), new TimerMetricsHandler("BT.ScoConnDuration"));
                append(MetricMappings.keyFor(11, 19), new TimerMetricsHandler("BT.TimeToConnectAllProfile"));
                append(MetricMappings.keyFor(11, 20), new CounterMetricsHandler("BT.NoProfileAfterBtLink"));
                append(MetricMappings.keyFor(12, 0), new TimerMetricsHandler("BLE.PairedDuration"));
                append(MetricMappings.keyFor(12, 1), new TimerMetricsHandler("BLE.SPPConnectionDuration"));
                append(MetricMappings.keyFor(12, 2), new TimerMetricsHandler("BLE.iAPConnectionDuration"));
                append(MetricMappings.keyFor(12, 3), new TimerMetricsHandler("BLE.B2BCouplingStatus"));
                append(MetricMappings.keyFor(13, 0), new CounterMetricsHandler("Case.LiquidDetectionCount"));
                append(MetricMappings.keyFor(13, 1), new CounterMetricsHandler("Case.BattTemp45To60Count"));
                append(MetricMappings.keyFor(13, 2), new CounterMetricsHandler("Case.BattTempGT60Count"));
                append(MetricMappings.keyFor(13, 3), new CounterMetricsHandler("Case.ContUSBPluggedCount"));
                append(MetricMappings.keyFor(13, 4), new CounterMetricsHandler("Case.BudHardResetsCount"));
                append(MetricMappings.keyFor(13, 5), new CounterMetricsHandler("Case.BattDischargedCycles"));
                append(MetricMappings.keyFor(13, 6), new CounterMetricsHandler("Case.BatteryStateOfHealth"));
                append(MetricMappings.keyFor(14, 0), new CounterMetricsHandler("Sensor.ActionDoubleTap"));
                append(MetricMappings.keyFor(14, 1), new CounterMetricsHandler("Sensor.ActionTapHold"));
                append(MetricMappings.keyFor(14, 2), new TimerMetricsHandler("Sensor.ActionPassthroughDuration"));
                append(MetricMappings.keyFor(14, 3), new CounterMetricsHandler("Sensor.ActionInEar"));
                append(MetricMappings.keyFor(14, 4), new TimerMetricsHandler("Sensor.ActionInEatToOutEar"));
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int keyFor(int i, int i2) {
        return (i * 65536) + i2;
    }

    public SparseArray<AccessoryMetricsObserver.Handler> getMap() {
        return this.map;
    }
}
