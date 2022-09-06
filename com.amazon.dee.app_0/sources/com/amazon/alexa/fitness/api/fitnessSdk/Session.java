package com.amazon.alexa.fitness.api.fitnessSdk;

import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.fitness.api.LocationCoordinate;
import com.amazon.alexa.fitness.api.afits.DataSource;
import com.amazon.alexa.fitness.api.util.DateTime;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\f¢\u0006\u0002\u0010\u0016J\t\u00104\u001a\u00020\u0003HÆ\u0003J\u0011\u00105\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\fHÆ\u0003J\t\u00106\u001a\u00020\u0005HÆ\u0003J\u000f\u00107\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\nHÆ\u0003J\u000f\u00109\u001a\b\u0012\u0004\u0012\u00020\r0\fHÆ\u0003J\u000f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000f0\fHÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\rHÆ\u0003J\t\u0010=\u001a\u00020\u0013HÆ\u0003J\u008d\u0001\u0010>\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u000e\b\u0002\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\fHÆ\u0001J\u0013\u0010?\u001a\u00020@2\b\u0010A\u001a\u0004\u0018\u00010BHÖ\u0003J\t\u0010C\u001a\u00020DHÖ\u0001J\t\u0010E\u001a\u00020\rHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\"\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001e\"\u0004\b(\u0010)R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001a\"\u0004\b+\u0010\u001cR \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u001a\"\u0004\b-\u0010\u001cR \u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u001a\"\u0004\b/\u0010\u001cR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103¨\u0006F"}, d2 = {"Lcom/amazon/alexa/fitness/api/fitnessSdk/Session;", "Ljava/io/Serializable;", "createdAt", "Lcom/amazon/alexa/fitness/api/util/DateTime;", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", DefaultDeliveryClient.EVENTS_DIRECTORY, "", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionEvent;", "userProfile", "Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "sensorIds", "", "", "sensorProviderTypes", "Lcom/amazon/alexa/fitness/api/fitnessSdk/SensorProviderType;", "endTime", "deviceTypeInUse", "dataSource", "Lcom/amazon/alexa/fitness/api/afits/DataSource;", "coordinates", "Lcom/amazon/alexa/fitness/api/LocationCoordinate;", "(Lcom/amazon/alexa/fitness/api/util/DateTime;Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;Ljava/util/List;Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;Ljava/util/List;Ljava/util/List;Lcom/amazon/alexa/fitness/api/util/DateTime;Ljava/lang/String;Lcom/amazon/alexa/fitness/api/afits/DataSource;Ljava/util/List;)V", "getConfiguration", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/SessionConfiguration;", "getCoordinates", "()Ljava/util/List;", "setCoordinates", "(Ljava/util/List;)V", "getCreatedAt", "()Lcom/amazon/alexa/fitness/api/util/DateTime;", "getDataSource", "()Lcom/amazon/alexa/fitness/api/afits/DataSource;", "setDataSource", "(Lcom/amazon/alexa/fitness/api/afits/DataSource;)V", "getDeviceTypeInUse", "()Ljava/lang/String;", "setDeviceTypeInUse", "(Ljava/lang/String;)V", "getEndTime", "setEndTime", "(Lcom/amazon/alexa/fitness/api/util/DateTime;)V", "getEvents", "setEvents", "getSensorIds", "setSensorIds", "getSensorProviderTypes", "setSensorProviderTypes", MetricsConstants.OperationalMetrics.GET_USER_PROFILE, "()Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;", "setUserProfile", "(Lcom/amazon/alexa/fitness/api/fitnessSdk/UserProfile;)V", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Session implements Serializable {
    @NotNull
    private final SessionConfiguration configuration;
    @Nullable
    private List<LocationCoordinate> coordinates;
    @NotNull
    private final DateTime createdAt;
    @NotNull
    private DataSource dataSource;
    @Nullable
    private String deviceTypeInUse;
    @Nullable
    private DateTime endTime;
    @NotNull
    private List<SessionEvent> events;
    @NotNull
    private List<String> sensorIds;
    @NotNull
    private List<? extends SensorProviderType> sensorProviderTypes;
    @Nullable
    private UserProfile userProfile;

    public Session(@NotNull DateTime createdAt, @NotNull SessionConfiguration configuration, @NotNull List<SessionEvent> events, @Nullable UserProfile userProfile, @NotNull List<String> sensorIds, @NotNull List<? extends SensorProviderType> sensorProviderTypes, @Nullable DateTime dateTime, @Nullable String str, @NotNull DataSource dataSource, @Nullable List<LocationCoordinate> list) {
        Intrinsics.checkParameterIsNotNull(createdAt, "createdAt");
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        Intrinsics.checkParameterIsNotNull(events, "events");
        Intrinsics.checkParameterIsNotNull(sensorIds, "sensorIds");
        Intrinsics.checkParameterIsNotNull(sensorProviderTypes, "sensorProviderTypes");
        Intrinsics.checkParameterIsNotNull(dataSource, "dataSource");
        this.createdAt = createdAt;
        this.configuration = configuration;
        this.events = events;
        this.userProfile = userProfile;
        this.sensorIds = sensorIds;
        this.sensorProviderTypes = sensorProviderTypes;
        this.endTime = dateTime;
        this.deviceTypeInUse = str;
        this.dataSource = dataSource;
        this.coordinates = list;
    }

    @NotNull
    public final DateTime component1() {
        return this.createdAt;
    }

    @Nullable
    public final List<LocationCoordinate> component10() {
        return this.coordinates;
    }

    @NotNull
    public final SessionConfiguration component2() {
        return this.configuration;
    }

    @NotNull
    public final List<SessionEvent> component3() {
        return this.events;
    }

    @Nullable
    public final UserProfile component4() {
        return this.userProfile;
    }

    @NotNull
    public final List<String> component5() {
        return this.sensorIds;
    }

    @NotNull
    public final List<SensorProviderType> component6() {
        return this.sensorProviderTypes;
    }

    @Nullable
    public final DateTime component7() {
        return this.endTime;
    }

    @Nullable
    public final String component8() {
        return this.deviceTypeInUse;
    }

    @NotNull
    public final DataSource component9() {
        return this.dataSource;
    }

    @NotNull
    public final Session copy(@NotNull DateTime createdAt, @NotNull SessionConfiguration configuration, @NotNull List<SessionEvent> events, @Nullable UserProfile userProfile, @NotNull List<String> sensorIds, @NotNull List<? extends SensorProviderType> sensorProviderTypes, @Nullable DateTime dateTime, @Nullable String str, @NotNull DataSource dataSource, @Nullable List<LocationCoordinate> list) {
        Intrinsics.checkParameterIsNotNull(createdAt, "createdAt");
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        Intrinsics.checkParameterIsNotNull(events, "events");
        Intrinsics.checkParameterIsNotNull(sensorIds, "sensorIds");
        Intrinsics.checkParameterIsNotNull(sensorProviderTypes, "sensorProviderTypes");
        Intrinsics.checkParameterIsNotNull(dataSource, "dataSource");
        return new Session(createdAt, configuration, events, userProfile, sensorIds, sensorProviderTypes, dateTime, str, dataSource, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Session)) {
                return false;
            }
            Session session = (Session) obj;
            return Intrinsics.areEqual(this.createdAt, session.createdAt) && Intrinsics.areEqual(this.configuration, session.configuration) && Intrinsics.areEqual(this.events, session.events) && Intrinsics.areEqual(this.userProfile, session.userProfile) && Intrinsics.areEqual(this.sensorIds, session.sensorIds) && Intrinsics.areEqual(this.sensorProviderTypes, session.sensorProviderTypes) && Intrinsics.areEqual(this.endTime, session.endTime) && Intrinsics.areEqual(this.deviceTypeInUse, session.deviceTypeInUse) && Intrinsics.areEqual(this.dataSource, session.dataSource) && Intrinsics.areEqual(this.coordinates, session.coordinates);
        }
        return true;
    }

    @NotNull
    public final SessionConfiguration getConfiguration() {
        return this.configuration;
    }

    @Nullable
    public final List<LocationCoordinate> getCoordinates() {
        return this.coordinates;
    }

    @NotNull
    public final DateTime getCreatedAt() {
        return this.createdAt;
    }

    @NotNull
    public final DataSource getDataSource() {
        return this.dataSource;
    }

    @Nullable
    public final String getDeviceTypeInUse() {
        return this.deviceTypeInUse;
    }

    @Nullable
    public final DateTime getEndTime() {
        return this.endTime;
    }

    @NotNull
    public final List<SessionEvent> getEvents() {
        return this.events;
    }

    @NotNull
    public final List<String> getSensorIds() {
        return this.sensorIds;
    }

    @NotNull
    public final List<SensorProviderType> getSensorProviderTypes() {
        return this.sensorProviderTypes;
    }

    @Nullable
    public final UserProfile getUserProfile() {
        return this.userProfile;
    }

    public int hashCode() {
        DateTime dateTime = this.createdAt;
        int i = 0;
        int hashCode = (dateTime != null ? dateTime.hashCode() : 0) * 31;
        SessionConfiguration sessionConfiguration = this.configuration;
        int hashCode2 = (hashCode + (sessionConfiguration != null ? sessionConfiguration.hashCode() : 0)) * 31;
        List<SessionEvent> list = this.events;
        int hashCode3 = (hashCode2 + (list != null ? list.hashCode() : 0)) * 31;
        UserProfile userProfile = this.userProfile;
        int hashCode4 = (hashCode3 + (userProfile != null ? userProfile.hashCode() : 0)) * 31;
        List<String> list2 = this.sensorIds;
        int hashCode5 = (hashCode4 + (list2 != null ? list2.hashCode() : 0)) * 31;
        List<? extends SensorProviderType> list3 = this.sensorProviderTypes;
        int hashCode6 = (hashCode5 + (list3 != null ? list3.hashCode() : 0)) * 31;
        DateTime dateTime2 = this.endTime;
        int hashCode7 = (hashCode6 + (dateTime2 != null ? dateTime2.hashCode() : 0)) * 31;
        String str = this.deviceTypeInUse;
        int hashCode8 = (hashCode7 + (str != null ? str.hashCode() : 0)) * 31;
        DataSource dataSource = this.dataSource;
        int hashCode9 = (hashCode8 + (dataSource != null ? dataSource.hashCode() : 0)) * 31;
        List<LocationCoordinate> list4 = this.coordinates;
        if (list4 != null) {
            i = list4.hashCode();
        }
        return hashCode9 + i;
    }

    public final void setCoordinates(@Nullable List<LocationCoordinate> list) {
        this.coordinates = list;
    }

    public final void setDataSource(@NotNull DataSource dataSource) {
        Intrinsics.checkParameterIsNotNull(dataSource, "<set-?>");
        this.dataSource = dataSource;
    }

    public final void setDeviceTypeInUse(@Nullable String str) {
        this.deviceTypeInUse = str;
    }

    public final void setEndTime(@Nullable DateTime dateTime) {
        this.endTime = dateTime;
    }

    public final void setEvents(@NotNull List<SessionEvent> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.events = list;
    }

    public final void setSensorIds(@NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.sensorIds = list;
    }

    public final void setSensorProviderTypes(@NotNull List<? extends SensorProviderType> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.sensorProviderTypes = list;
    }

    public final void setUserProfile(@Nullable UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Session(createdAt=");
        outline107.append(this.createdAt);
        outline107.append(", configuration=");
        outline107.append(this.configuration);
        outline107.append(", events=");
        outline107.append(this.events);
        outline107.append(", userProfile=");
        outline107.append(this.userProfile);
        outline107.append(", sensorIds=");
        outline107.append(this.sensorIds);
        outline107.append(", sensorProviderTypes=");
        outline107.append(this.sensorProviderTypes);
        outline107.append(", endTime=");
        outline107.append(this.endTime);
        outline107.append(", deviceTypeInUse=");
        outline107.append(this.deviceTypeInUse);
        outline107.append(", dataSource=");
        outline107.append(this.dataSource);
        outline107.append(", coordinates=");
        return GeneratedOutlineSupport1.outline95(outline107, this.coordinates, ")");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ Session(com.amazon.alexa.fitness.api.util.DateTime r14, com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration r15, java.util.List r16, com.amazon.alexa.fitness.api.fitnessSdk.UserProfile r17, java.util.List r18, java.util.List r19, com.amazon.alexa.fitness.api.util.DateTime r20, java.lang.String r21, com.amazon.alexa.fitness.api.afits.DataSource r22, java.util.List r23, int r24, kotlin.jvm.internal.DefaultConstructorMarker r25) {
        /*
            r13 = this;
            r0 = r24
            r1 = r0 & 4
            if (r1 == 0) goto Ld
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5 = r1
            goto Lf
        Ld:
            r5 = r16
        Lf:
            r1 = r0 & 8
            r2 = 0
            if (r1 == 0) goto L16
            r6 = r2
            goto L18
        L16:
            r6 = r17
        L18:
            r1 = r0 & 16
            if (r1 == 0) goto L22
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            r7 = r1
            goto L24
        L22:
            r7 = r18
        L24:
            r1 = r0 & 32
            if (r1 == 0) goto L2e
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            r8 = r1
            goto L30
        L2e:
            r8 = r19
        L30:
            r1 = r0 & 64
            if (r1 == 0) goto L36
            r9 = r2
            goto L38
        L36:
            r9 = r20
        L38:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L3e
            r10 = r2
            goto L40
        L3e:
            r10 = r21
        L40:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L4c
            com.amazon.alexa.fitness.api.afits.DataSource$Companion r1 = com.amazon.alexa.fitness.api.afits.DataSource.Companion
            com.amazon.alexa.fitness.api.afits.DataSource r1 = r1.getDefault()
            r11 = r1
            goto L4e
        L4c:
            r11 = r22
        L4e:
            r0 = r0 & 512(0x200, float:7.175E-43)
            if (r0 == 0) goto L54
            r12 = r2
            goto L56
        L54:
            r12 = r23
        L56:
            r2 = r13
            r3 = r14
            r4 = r15
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.api.fitnessSdk.Session.<init>(com.amazon.alexa.fitness.api.util.DateTime, com.amazon.alexa.fitness.api.fitnessSdk.SessionConfiguration, java.util.List, com.amazon.alexa.fitness.api.fitnessSdk.UserProfile, java.util.List, java.util.List, com.amazon.alexa.fitness.api.util.DateTime, java.lang.String, com.amazon.alexa.fitness.api.afits.DataSource, java.util.List, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
