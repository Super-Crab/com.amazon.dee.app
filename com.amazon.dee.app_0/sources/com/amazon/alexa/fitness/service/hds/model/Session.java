package com.amazon.alexa.fitness.service.hds.model;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__IndentKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HdsDataModel.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 $2\u00020\u0001:\u0001$B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bJ\u0006\u0010\u0015\u001a\u00020\u0003J\u0006\u0010\u0016\u001a\u00020\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\tHÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÆ\u0003JI\u0010\u001d\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000f¨\u0006%"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/Session;", "", "id", "", "type", "Lcom/amazon/alexa/fitness/service/hds/model/SessionType;", "startTime", "endTime", "dataSource", "Lcom/amazon/alexa/fitness/service/hds/model/DataSource;", "version", "(Ljava/lang/String;Lcom/amazon/alexa/fitness/service/hds/model/SessionType;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/alexa/fitness/service/hds/model/DataSource;Ljava/lang/String;)V", "getDataSource", "()Lcom/amazon/alexa/fitness/service/hds/model/DataSource;", "getEndTime", "()Ljava/lang/String;", "getId", "getStartTime", "getType", "()Lcom/amazon/alexa/fitness/service/hds/model/SessionType;", "getVersion", "buildCreateSessionInput", "buildSessionInput", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class Session {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final String outputFieldSelection;
    @Nullable
    private final DataSource dataSource;
    @NotNull
    private final String endTime;
    @NotNull
    private final String id;
    @NotNull
    private final String startTime;
    @NotNull
    private final SessionType type;
    @Nullable
    private final String version;

    /* compiled from: HdsDataModel.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/model/Session$Companion;", "", "()V", "outputFieldSelection", "", "getOutputFieldSelection", "()Ljava/lang/String;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes8.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final String getOutputFieldSelection() {
            return Session.outputFieldSelection;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        String trimMargin$default;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("\n                |{\n                    |id\n                    |type\n                    |startTime\n                    |endTime\n                    |version\n                    |");
        outline107.append(DataSource.Companion.getOutputFieldSelection());
        outline107.append("\n                |}\n            |");
        trimMargin$default = StringsKt__IndentKt.trimMargin$default(outline107.toString(), null, 1, null);
        outputFieldSelection = trimMargin$default;
    }

    public Session(@NotNull String id, @NotNull SessionType type, @NotNull String startTime, @NotNull String endTime, @Nullable DataSource dataSource, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(startTime, "startTime");
        Intrinsics.checkParameterIsNotNull(endTime, "endTime");
        this.id = id;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dataSource = dataSource;
        this.version = str;
    }

    public static /* synthetic */ Session copy$default(Session session, String str, SessionType sessionType, String str2, String str3, DataSource dataSource, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = session.id;
        }
        if ((i & 2) != 0) {
            sessionType = session.type;
        }
        SessionType sessionType2 = sessionType;
        if ((i & 4) != 0) {
            str2 = session.startTime;
        }
        String str5 = str2;
        if ((i & 8) != 0) {
            str3 = session.endTime;
        }
        String str6 = str3;
        if ((i & 16) != 0) {
            dataSource = session.dataSource;
        }
        DataSource dataSource2 = dataSource;
        if ((i & 32) != 0) {
            str4 = session.version;
        }
        return session.copy(str, sessionType2, str5, str6, dataSource2, str4);
    }

    @NotNull
    public final String buildCreateSessionInput() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("id:\"");
        outline1072.append(this.id);
        outline1072.append("\",");
        outline107.append(outline1072.toString());
        outline107.append("type:" + this.type.getValue() + JsonReaderKt.COMMA);
        outline107.append("startTime:\"" + this.startTime + "\",");
        outline107.append("endTime:\"" + this.endTime + "\",");
        DataSource dataSource = this.dataSource;
        if (dataSource != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("dataSource:");
            outline1073.append(dataSource.buildDataSourceInput());
            outline1073.append(JsonReaderKt.COMMA);
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        String sb = outline107.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "gqlBuilder.toString()");
        return sb;
    }

    @NotNull
    public final String buildSessionInput() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_OPEN);
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("id:\"");
        outline1072.append(this.id);
        outline1072.append("\",");
        outline107.append(outline1072.toString());
        if (this.version != null) {
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("version:\"");
            outline1073.append(this.version);
            outline1073.append("\",");
            outline107.append(outline1073.toString());
        }
        outline107.append(EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
        String sb = outline107.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb, "gqlBuilder.toString()");
        return sb;
    }

    @NotNull
    public final String component1() {
        return this.id;
    }

    @NotNull
    public final SessionType component2() {
        return this.type;
    }

    @NotNull
    public final String component3() {
        return this.startTime;
    }

    @NotNull
    public final String component4() {
        return this.endTime;
    }

    @Nullable
    public final DataSource component5() {
        return this.dataSource;
    }

    @Nullable
    public final String component6() {
        return this.version;
    }

    @NotNull
    public final Session copy(@NotNull String id, @NotNull SessionType type, @NotNull String startTime, @NotNull String endTime, @Nullable DataSource dataSource, @Nullable String str) {
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(startTime, "startTime");
        Intrinsics.checkParameterIsNotNull(endTime, "endTime");
        return new Session(id, type, startTime, endTime, dataSource, str);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Session)) {
                return false;
            }
            Session session = (Session) obj;
            return Intrinsics.areEqual(this.id, session.id) && Intrinsics.areEqual(this.type, session.type) && Intrinsics.areEqual(this.startTime, session.startTime) && Intrinsics.areEqual(this.endTime, session.endTime) && Intrinsics.areEqual(this.dataSource, session.dataSource) && Intrinsics.areEqual(this.version, session.version);
        }
        return true;
    }

    @Nullable
    public final DataSource getDataSource() {
        return this.dataSource;
    }

    @NotNull
    public final String getEndTime() {
        return this.endTime;
    }

    @NotNull
    public final String getId() {
        return this.id;
    }

    @NotNull
    public final String getStartTime() {
        return this.startTime;
    }

    @NotNull
    public final SessionType getType() {
        return this.type;
    }

    @Nullable
    public final String getVersion() {
        return this.version;
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        SessionType sessionType = this.type;
        int hashCode2 = (hashCode + (sessionType != null ? sessionType.hashCode() : 0)) * 31;
        String str2 = this.startTime;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.endTime;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        DataSource dataSource = this.dataSource;
        int hashCode5 = (hashCode4 + (dataSource != null ? dataSource.hashCode() : 0)) * 31;
        String str4 = this.version;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode5 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Session(id=");
        outline107.append(this.id);
        outline107.append(", type=");
        outline107.append(this.type);
        outline107.append(", startTime=");
        outline107.append(this.startTime);
        outline107.append(", endTime=");
        outline107.append(this.endTime);
        outline107.append(", dataSource=");
        outline107.append(this.dataSource);
        outline107.append(", version=");
        return GeneratedOutlineSupport1.outline91(outline107, this.version, ")");
    }
}
