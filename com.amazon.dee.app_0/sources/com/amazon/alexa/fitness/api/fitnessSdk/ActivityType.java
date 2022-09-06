package com.amazon.alexa.fitness.api.fitnessSdk;

import com.amazon.alexa.drive.navigation.MappingApplication;
import com.amazon.alexa.presence.bleconn.service.PresenceBleService;
import com.amazon.devicesetup.common.v1.WifiConnectionState;
import kotlin.Metadata;
/* compiled from: Units.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", "", "(Ljava/lang/String;I)V", "UNKNOWN", WifiConnectionState.IDLE, MappingApplication.WALKING, PresenceBleService.ServiceState.RUNNING, "BICYCLING", MappingApplication.DRIVING, "UNRECOGNIZED", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public enum ActivityType {
    UNKNOWN,
    IDLE,
    WALKING,
    RUNNING,
    BICYCLING,
    DRIVING,
    UNRECOGNIZED
}
