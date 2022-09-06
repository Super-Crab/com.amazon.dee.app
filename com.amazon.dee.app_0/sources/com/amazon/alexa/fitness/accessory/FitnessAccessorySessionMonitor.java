package com.amazon.alexa.fitness.accessory;

import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.fitness.api.afx.SensorAvailability;
import com.amazon.alexa.location.utils.MetricsUtil;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function4;
import org.jetbrains.annotations.NotNull;
/* compiled from: FitnessAccessorySessionMonitor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001Jl\u0010\u0007\u001a\u00020\b2b\u0010\t\u001a^\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0010\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0012\u0012\u0015\u0012\u0013\u0018\u00010\u0013¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u0014\u0012\u0004\u0012\u00020\b0\nH&R\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0015"}, d2 = {"Lcom/amazon/alexa/fitness/accessory/FitnessAccessorySessionMonitor;", "", "activeAccessories", "", "Lcom/amazon/alexa/fitness/accessory/FitnessAccessory;", "getActiveAccessories", "()Ljava/util/Set;", "startMonitoring", "", "stateChangeCallback", "Lkotlin/Function4;", "Lcom/amazon/alexa/accessory/Accessory;", "Lkotlin/ParameterName;", "name", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/fitness/api/afx/SensorAvailability;", MetricsUtil.LegacyMetricTypes.AVAILABILITY, "", "timestamp", "", "error", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes7.dex */
public interface FitnessAccessorySessionMonitor {
    @NotNull
    Set<FitnessAccessory> getActiveAccessories();

    void startMonitoring(@NotNull Function4<? super Accessory, ? super SensorAvailability, ? super Long, ? super Throwable, Unit> function4);
}
