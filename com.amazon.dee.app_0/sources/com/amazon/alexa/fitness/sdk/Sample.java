package com.amazon.alexa.fitness.sdk;

import com.amazon.alexa.client.metrics.kinesis.session.client.AppDefaultSessionClient;
import com.amazon.alexa.fitness.api.fitnessSdk.ActivityType;
import com.amazon.alexa.fitness.api.fitnessSdk.Units;
import com.amazon.alexa.fitness.metrics.Metrics;
import com.amazon.alexa.fitness.sdk.Measurement;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.io.Serializable;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SampleDataModels.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0013\u0014\u0015B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u0082\u0001\u0003\u0016\u0017\u0018¨\u0006\u0019"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/Sample;", "Ljava/io/Serializable;", "()V", "receivedAtTimestamp", "", "getReceivedAtTimestamp", "()J", "sampleType", "Lcom/amazon/alexa/fitness/sdk/SampleType;", "getSampleType", "()Lcom/amazon/alexa/fitness/sdk/SampleType;", "sensorId", "", "getSensorId", "()Ljava/lang/String;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "getSessionId", "()Ljava/util/UUID;", "EchoBudSample", "LocationSample", "MeasurementSample", "Lcom/amazon/alexa/fitness/sdk/Sample$EchoBudSample;", "Lcom/amazon/alexa/fitness/sdk/Sample$MeasurementSample;", "Lcom/amazon/alexa/fitness/sdk/Sample$LocationSample;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public abstract class Sample implements Serializable {

    /* compiled from: SampleDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u000eJ\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\u0007HÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003J\t\u0010$\u001a\u00020\fHÆ\u0003J\t\u0010%\u001a\u00020\fHÆ\u0003JO\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\fHÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020\fHÖ\u0001J\t\u0010,\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\r\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012¨\u0006-"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/Sample$EchoBudSample;", "Lcom/amazon/alexa/fitness/sdk/Sample;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "sensorId", "", "receivedAtTimestamp", "", "collectionTimestamp", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", Metrics.STEPS, "", "cadence", "(Ljava/util/UUID;Ljava/lang/String;JJLcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;II)V", "getActivity", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/ActivityType;", "getCadence", "()I", "getCollectionTimestamp", "()J", "getReceivedAtTimestamp", "sampleType", "Lcom/amazon/alexa/fitness/sdk/SampleType;", "getSampleType", "()Lcom/amazon/alexa/fitness/sdk/SampleType;", "getSensorId", "()Ljava/lang/String;", "getSessionId", "()Ljava/util/UUID;", "getSteps", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class EchoBudSample extends Sample {
        @NotNull
        private final ActivityType activity;
        private final int cadence;
        private final long collectionTimestamp;
        private final long receivedAtTimestamp;
        @NotNull
        private final SampleType sampleType;
        @NotNull
        private final String sensorId;
        @NotNull
        private final UUID sessionId;
        private final int steps;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EchoBudSample(@NotNull UUID sessionId, @NotNull String sensorId, long j, long j2, @NotNull ActivityType activity, int i, int i2) {
            super(null);
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            Intrinsics.checkParameterIsNotNull(sensorId, "sensorId");
            Intrinsics.checkParameterIsNotNull(activity, "activity");
            this.sessionId = sessionId;
            this.sensorId = sensorId;
            this.receivedAtTimestamp = j;
            this.collectionTimestamp = j2;
            this.activity = activity;
            this.steps = i;
            this.cadence = i2;
            this.sampleType = SampleType.EchoBud;
        }

        @NotNull
        public final UUID component1() {
            return getSessionId();
        }

        @NotNull
        public final String component2() {
            return getSensorId();
        }

        public final long component3() {
            return getReceivedAtTimestamp();
        }

        public final long component4() {
            return this.collectionTimestamp;
        }

        @NotNull
        public final ActivityType component5() {
            return this.activity;
        }

        public final int component6() {
            return this.steps;
        }

        public final int component7() {
            return this.cadence;
        }

        @NotNull
        public final EchoBudSample copy(@NotNull UUID sessionId, @NotNull String sensorId, long j, long j2, @NotNull ActivityType activity, int i, int i2) {
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            Intrinsics.checkParameterIsNotNull(sensorId, "sensorId");
            Intrinsics.checkParameterIsNotNull(activity, "activity");
            return new EchoBudSample(sessionId, sensorId, j, j2, activity, i, i2);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof EchoBudSample)) {
                    return false;
                }
                EchoBudSample echoBudSample = (EchoBudSample) obj;
                return Intrinsics.areEqual(getSessionId(), echoBudSample.getSessionId()) && Intrinsics.areEqual(getSensorId(), echoBudSample.getSensorId()) && getReceivedAtTimestamp() == echoBudSample.getReceivedAtTimestamp() && this.collectionTimestamp == echoBudSample.collectionTimestamp && Intrinsics.areEqual(this.activity, echoBudSample.activity) && this.steps == echoBudSample.steps && this.cadence == echoBudSample.cadence;
            }
            return true;
        }

        @NotNull
        public final ActivityType getActivity() {
            return this.activity;
        }

        public final int getCadence() {
            return this.cadence;
        }

        public final long getCollectionTimestamp() {
            return this.collectionTimestamp;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        public long getReceivedAtTimestamp() {
            return this.receivedAtTimestamp;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        @NotNull
        public SampleType getSampleType() {
            return this.sampleType;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        @NotNull
        public String getSensorId() {
            return this.sensorId;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        @NotNull
        public UUID getSessionId() {
            return this.sessionId;
        }

        public final int getSteps() {
            return this.steps;
        }

        public int hashCode() {
            UUID sessionId = getSessionId();
            int i = 0;
            int hashCode = (sessionId != null ? sessionId.hashCode() : 0) * 31;
            String sensorId = getSensorId();
            int hashCode2 = sensorId != null ? sensorId.hashCode() : 0;
            long receivedAtTimestamp = getReceivedAtTimestamp();
            long j = this.collectionTimestamp;
            int i2 = (((((hashCode + hashCode2) * 31) + ((int) (receivedAtTimestamp ^ (receivedAtTimestamp >>> 32)))) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
            ActivityType activityType = this.activity;
            if (activityType != null) {
                i = activityType.hashCode();
            }
            return ((((i2 + i) * 31) + this.steps) * 31) + this.cadence;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("EchoBudSample(sessionId=");
            outline107.append(getSessionId());
            outline107.append(", sensorId=");
            outline107.append(getSensorId());
            outline107.append(", receivedAtTimestamp=");
            outline107.append(getReceivedAtTimestamp());
            outline107.append(", collectionTimestamp=");
            outline107.append(this.collectionTimestamp);
            outline107.append(", activity=");
            outline107.append(this.activity);
            outline107.append(", steps=");
            outline107.append(this.steps);
            outline107.append(", cadence=");
            return GeneratedOutlineSupport1.outline86(outline107, this.cadence, ")");
        }
    }

    /* compiled from: SampleDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003J1\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fHÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006#"}, d2 = {"Lcom/amazon/alexa/fitness/sdk/Sample$LocationSample;", "Lcom/amazon/alexa/fitness/sdk/Sample;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "sensorId", "", "receivedAtTimestamp", "", "sampleData", "Lcom/amazon/alexa/fitness/sdk/LocationSampleData;", "(Ljava/util/UUID;Ljava/lang/String;JLcom/amazon/alexa/fitness/sdk/LocationSampleData;)V", "getReceivedAtTimestamp", "()J", "getSampleData", "()Lcom/amazon/alexa/fitness/sdk/LocationSampleData;", "sampleType", "Lcom/amazon/alexa/fitness/sdk/SampleType;", "getSampleType", "()Lcom/amazon/alexa/fitness/sdk/SampleType;", "getSensorId", "()Ljava/lang/String;", "getSessionId", "()Ljava/util/UUID;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class LocationSample extends Sample {
        private final long receivedAtTimestamp;
        @NotNull
        private final LocationSampleData sampleData;
        @NotNull
        private final SampleType sampleType;
        @NotNull
        private final String sensorId;
        @NotNull
        private final UUID sessionId;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LocationSample(@NotNull UUID sessionId, @NotNull String sensorId, long j, @NotNull LocationSampleData sampleData) {
            super(null);
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            Intrinsics.checkParameterIsNotNull(sensorId, "sensorId");
            Intrinsics.checkParameterIsNotNull(sampleData, "sampleData");
            this.sessionId = sessionId;
            this.sensorId = sensorId;
            this.receivedAtTimestamp = j;
            this.sampleData = sampleData;
            this.sampleType = SampleType.Location;
        }

        public static /* synthetic */ LocationSample copy$default(LocationSample locationSample, UUID uuid, String str, long j, LocationSampleData locationSampleData, int i, Object obj) {
            if ((i & 1) != 0) {
                uuid = locationSample.getSessionId();
            }
            if ((i & 2) != 0) {
                str = locationSample.getSensorId();
            }
            String str2 = str;
            if ((i & 4) != 0) {
                j = locationSample.getReceivedAtTimestamp();
            }
            long j2 = j;
            if ((i & 8) != 0) {
                locationSampleData = locationSample.sampleData;
            }
            return locationSample.copy(uuid, str2, j2, locationSampleData);
        }

        @NotNull
        public final UUID component1() {
            return getSessionId();
        }

        @NotNull
        public final String component2() {
            return getSensorId();
        }

        public final long component3() {
            return getReceivedAtTimestamp();
        }

        @NotNull
        public final LocationSampleData component4() {
            return this.sampleData;
        }

        @NotNull
        public final LocationSample copy(@NotNull UUID sessionId, @NotNull String sensorId, long j, @NotNull LocationSampleData sampleData) {
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            Intrinsics.checkParameterIsNotNull(sensorId, "sensorId");
            Intrinsics.checkParameterIsNotNull(sampleData, "sampleData");
            return new LocationSample(sessionId, sensorId, j, sampleData);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof LocationSample)) {
                    return false;
                }
                LocationSample locationSample = (LocationSample) obj;
                return Intrinsics.areEqual(getSessionId(), locationSample.getSessionId()) && Intrinsics.areEqual(getSensorId(), locationSample.getSensorId()) && getReceivedAtTimestamp() == locationSample.getReceivedAtTimestamp() && Intrinsics.areEqual(this.sampleData, locationSample.sampleData);
            }
            return true;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        public long getReceivedAtTimestamp() {
            return this.receivedAtTimestamp;
        }

        @NotNull
        public final LocationSampleData getSampleData() {
            return this.sampleData;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        @NotNull
        public SampleType getSampleType() {
            return this.sampleType;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        @NotNull
        public String getSensorId() {
            return this.sensorId;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        @NotNull
        public UUID getSessionId() {
            return this.sessionId;
        }

        public int hashCode() {
            UUID sessionId = getSessionId();
            int i = 0;
            int hashCode = (sessionId != null ? sessionId.hashCode() : 0) * 31;
            String sensorId = getSensorId();
            int hashCode2 = sensorId != null ? sensorId.hashCode() : 0;
            long receivedAtTimestamp = getReceivedAtTimestamp();
            int i2 = (((hashCode + hashCode2) * 31) + ((int) (receivedAtTimestamp ^ (receivedAtTimestamp >>> 32)))) * 31;
            LocationSampleData locationSampleData = this.sampleData;
            if (locationSampleData != null) {
                i = locationSampleData.hashCode();
            }
            return i2 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LocationSample(sessionId=");
            outline107.append(getSessionId());
            outline107.append(", sensorId=");
            outline107.append(getSensorId());
            outline107.append(", receivedAtTimestamp=");
            outline107.append(getReceivedAtTimestamp());
            outline107.append(", sampleData=");
            outline107.append(this.sampleData);
            outline107.append(")");
            return outline107.toString();
        }
    }

    /* compiled from: SampleDataModels.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0007HÆ\u0003J\t\u0010\"\u001a\u00020\tHÆ\u0003J\t\u0010#\u001a\u00020\u000bHÆ\u0003J\t\u0010$\u001a\u00020\rHÆ\u0003J\t\u0010%\u001a\u00020\u000fHÆ\u0003JO\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000fHÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0003J\t\u0010+\u001a\u00020,HÖ\u0001J\t\u0010-\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006."}, d2 = {"Lcom/amazon/alexa/fitness/sdk/Sample$MeasurementSample;", "Lcom/amazon/alexa/fitness/sdk/Sample;", AppDefaultSessionClient.CRASH_REPORTER_SESSION_ID_KEY, "Ljava/util/UUID;", "sensorId", "", "receivedAtTimestamp", "", "sampleType", "Lcom/amazon/alexa/fitness/sdk/SampleType;", "value", "Lcom/amazon/alexa/fitness/sdk/Measurement$Discrete;", "units", "Lcom/amazon/alexa/fitness/api/fitnessSdk/Units;", "aggregation", "Lcom/amazon/alexa/fitness/sdk/Aggregation;", "(Ljava/util/UUID;Ljava/lang/String;JLcom/amazon/alexa/fitness/sdk/SampleType;Lcom/amazon/alexa/fitness/sdk/Measurement$Discrete;Lcom/amazon/alexa/fitness/api/fitnessSdk/Units;Lcom/amazon/alexa/fitness/sdk/Aggregation;)V", "getAggregation", "()Lcom/amazon/alexa/fitness/sdk/Aggregation;", "getReceivedAtTimestamp", "()J", "getSampleType", "()Lcom/amazon/alexa/fitness/sdk/SampleType;", "getSensorId", "()Ljava/lang/String;", "getSessionId", "()Ljava/util/UUID;", "getUnits", "()Lcom/amazon/alexa/fitness/api/fitnessSdk/Units;", "getValue", "()Lcom/amazon/alexa/fitness/sdk/Measurement$Discrete;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "", "hashCode", "", "toString", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class MeasurementSample extends Sample {
        @NotNull
        private final Aggregation aggregation;
        private final long receivedAtTimestamp;
        @NotNull
        private final SampleType sampleType;
        @NotNull
        private final String sensorId;
        @NotNull
        private final UUID sessionId;
        @NotNull
        private final Units units;
        @NotNull
        private final Measurement.Discrete value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MeasurementSample(@NotNull UUID sessionId, @NotNull String sensorId, long j, @NotNull SampleType sampleType, @NotNull Measurement.Discrete value, @NotNull Units units, @NotNull Aggregation aggregation) {
            super(null);
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            Intrinsics.checkParameterIsNotNull(sensorId, "sensorId");
            Intrinsics.checkParameterIsNotNull(sampleType, "sampleType");
            Intrinsics.checkParameterIsNotNull(value, "value");
            Intrinsics.checkParameterIsNotNull(units, "units");
            Intrinsics.checkParameterIsNotNull(aggregation, "aggregation");
            this.sessionId = sessionId;
            this.sensorId = sensorId;
            this.receivedAtTimestamp = j;
            this.sampleType = sampleType;
            this.value = value;
            this.units = units;
            this.aggregation = aggregation;
        }

        @NotNull
        public final UUID component1() {
            return getSessionId();
        }

        @NotNull
        public final String component2() {
            return getSensorId();
        }

        public final long component3() {
            return getReceivedAtTimestamp();
        }

        @NotNull
        public final SampleType component4() {
            return getSampleType();
        }

        @NotNull
        public final Measurement.Discrete component5() {
            return this.value;
        }

        @NotNull
        public final Units component6() {
            return this.units;
        }

        @NotNull
        public final Aggregation component7() {
            return this.aggregation;
        }

        @NotNull
        public final MeasurementSample copy(@NotNull UUID sessionId, @NotNull String sensorId, long j, @NotNull SampleType sampleType, @NotNull Measurement.Discrete value, @NotNull Units units, @NotNull Aggregation aggregation) {
            Intrinsics.checkParameterIsNotNull(sessionId, "sessionId");
            Intrinsics.checkParameterIsNotNull(sensorId, "sensorId");
            Intrinsics.checkParameterIsNotNull(sampleType, "sampleType");
            Intrinsics.checkParameterIsNotNull(value, "value");
            Intrinsics.checkParameterIsNotNull(units, "units");
            Intrinsics.checkParameterIsNotNull(aggregation, "aggregation");
            return new MeasurementSample(sessionId, sensorId, j, sampleType, value, units, aggregation);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (!(obj instanceof MeasurementSample)) {
                    return false;
                }
                MeasurementSample measurementSample = (MeasurementSample) obj;
                return Intrinsics.areEqual(getSessionId(), measurementSample.getSessionId()) && Intrinsics.areEqual(getSensorId(), measurementSample.getSensorId()) && getReceivedAtTimestamp() == measurementSample.getReceivedAtTimestamp() && Intrinsics.areEqual(getSampleType(), measurementSample.getSampleType()) && Intrinsics.areEqual(this.value, measurementSample.value) && Intrinsics.areEqual(this.units, measurementSample.units) && Intrinsics.areEqual(this.aggregation, measurementSample.aggregation);
            }
            return true;
        }

        @NotNull
        public final Aggregation getAggregation() {
            return this.aggregation;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        public long getReceivedAtTimestamp() {
            return this.receivedAtTimestamp;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        @NotNull
        public SampleType getSampleType() {
            return this.sampleType;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        @NotNull
        public String getSensorId() {
            return this.sensorId;
        }

        @Override // com.amazon.alexa.fitness.sdk.Sample
        @NotNull
        public UUID getSessionId() {
            return this.sessionId;
        }

        @NotNull
        public final Units getUnits() {
            return this.units;
        }

        @NotNull
        public final Measurement.Discrete getValue() {
            return this.value;
        }

        public int hashCode() {
            UUID sessionId = getSessionId();
            int i = 0;
            int hashCode = (sessionId != null ? sessionId.hashCode() : 0) * 31;
            String sensorId = getSensorId();
            int hashCode2 = sensorId != null ? sensorId.hashCode() : 0;
            long receivedAtTimestamp = getReceivedAtTimestamp();
            int i2 = (((hashCode + hashCode2) * 31) + ((int) (receivedAtTimestamp ^ (receivedAtTimestamp >>> 32)))) * 31;
            SampleType sampleType = getSampleType();
            int hashCode3 = (i2 + (sampleType != null ? sampleType.hashCode() : 0)) * 31;
            Measurement.Discrete discrete = this.value;
            int hashCode4 = (hashCode3 + (discrete != null ? discrete.hashCode() : 0)) * 31;
            Units units = this.units;
            int hashCode5 = (hashCode4 + (units != null ? units.hashCode() : 0)) * 31;
            Aggregation aggregation = this.aggregation;
            if (aggregation != null) {
                i = aggregation.hashCode();
            }
            return hashCode5 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MeasurementSample(sessionId=");
            outline107.append(getSessionId());
            outline107.append(", sensorId=");
            outline107.append(getSensorId());
            outline107.append(", receivedAtTimestamp=");
            outline107.append(getReceivedAtTimestamp());
            outline107.append(", sampleType=");
            outline107.append(getSampleType());
            outline107.append(", value=");
            outline107.append(this.value);
            outline107.append(", units=");
            outline107.append(this.units);
            outline107.append(", aggregation=");
            outline107.append(this.aggregation);
            outline107.append(")");
            return outline107.toString();
        }
    }

    private Sample() {
    }

    public abstract long getReceivedAtTimestamp();

    @NotNull
    public abstract SampleType getSampleType();

    @NotNull
    public abstract String getSensorId();

    @NotNull
    public abstract UUID getSessionId();

    public /* synthetic */ Sample(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
