package com.amazon.alexa.fitness.api.afx;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.i18n.ErrorBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AFITSTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\u0011\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0003J=\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0010\b\u0002\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0019\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001f"}, d2 = {"Lcom/amazon/alexa/fitness/api/afx/AFITSFitnessSession;", "", "version", "", PCCConstants.PHONE_CALL_CONTROLLER_CONFIGURATION_KEY, "Lcom/amazon/alexa/fitness/api/afx/Configuration;", ErrorBundle.SUMMARY_ENTRY, "Lcom/amazon/alexa/fitness/api/afx/AFITSWorkoutSummary;", "routeData", "", "Lcom/amazon/alexa/fitness/api/afx/LocationSample;", "(Ljava/lang/String;Lcom/amazon/alexa/fitness/api/afx/Configuration;Lcom/amazon/alexa/fitness/api/afx/AFITSWorkoutSummary;Ljava/util/List;)V", "getConfiguration", "()Lcom/amazon/alexa/fitness/api/afx/Configuration;", "getRouteData", "()Ljava/util/List;", "getSummary", "()Lcom/amazon/alexa/fitness/api/afx/AFITSWorkoutSummary;", "getVersion", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidFitnessAPI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AFITSFitnessSession {
    @Nullable
    private final Configuration configuration;
    @Nullable
    private final List<LocationSample> routeData;
    @NotNull
    private final AFITSWorkoutSummary summary;
    @Nullable
    private final String version;

    public AFITSFitnessSession(@Nullable String str, @Nullable Configuration configuration, @NotNull AFITSWorkoutSummary summary, @Nullable List<LocationSample> list) {
        Intrinsics.checkParameterIsNotNull(summary, "summary");
        this.version = str;
        this.configuration = configuration;
        this.summary = summary;
        this.routeData = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AFITSFitnessSession copy$default(AFITSFitnessSession aFITSFitnessSession, String str, Configuration configuration, AFITSWorkoutSummary aFITSWorkoutSummary, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = aFITSFitnessSession.version;
        }
        if ((i & 2) != 0) {
            configuration = aFITSFitnessSession.configuration;
        }
        if ((i & 4) != 0) {
            aFITSWorkoutSummary = aFITSFitnessSession.summary;
        }
        if ((i & 8) != 0) {
            list = aFITSFitnessSession.routeData;
        }
        return aFITSFitnessSession.copy(str, configuration, aFITSWorkoutSummary, list);
    }

    @Nullable
    public final String component1() {
        return this.version;
    }

    @Nullable
    public final Configuration component2() {
        return this.configuration;
    }

    @NotNull
    public final AFITSWorkoutSummary component3() {
        return this.summary;
    }

    @Nullable
    public final List<LocationSample> component4() {
        return this.routeData;
    }

    @NotNull
    public final AFITSFitnessSession copy(@Nullable String str, @Nullable Configuration configuration, @NotNull AFITSWorkoutSummary summary, @Nullable List<LocationSample> list) {
        Intrinsics.checkParameterIsNotNull(summary, "summary");
        return new AFITSFitnessSession(str, configuration, summary, list);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof AFITSFitnessSession)) {
                return false;
            }
            AFITSFitnessSession aFITSFitnessSession = (AFITSFitnessSession) obj;
            return Intrinsics.areEqual(this.version, aFITSFitnessSession.version) && Intrinsics.areEqual(this.configuration, aFITSFitnessSession.configuration) && Intrinsics.areEqual(this.summary, aFITSFitnessSession.summary) && Intrinsics.areEqual(this.routeData, aFITSFitnessSession.routeData);
        }
        return true;
    }

    @Nullable
    public final Configuration getConfiguration() {
        return this.configuration;
    }

    @Nullable
    public final List<LocationSample> getRouteData() {
        return this.routeData;
    }

    @NotNull
    public final AFITSWorkoutSummary getSummary() {
        return this.summary;
    }

    @Nullable
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.version;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Configuration configuration = this.configuration;
        int hashCode2 = (hashCode + (configuration != null ? configuration.hashCode() : 0)) * 31;
        AFITSWorkoutSummary aFITSWorkoutSummary = this.summary;
        int hashCode3 = (hashCode2 + (aFITSWorkoutSummary != null ? aFITSWorkoutSummary.hashCode() : 0)) * 31;
        List<LocationSample> list = this.routeData;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("AFITSFitnessSession(version=");
        outline107.append(this.version);
        outline107.append(", configuration=");
        outline107.append(this.configuration);
        outline107.append(", summary=");
        outline107.append(this.summary);
        outline107.append(", routeData=");
        return GeneratedOutlineSupport1.outline95(outline107, this.routeData, ")");
    }
}
