package com.amazon.tarazed.appinfo.sessioninfo;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import com.amazon.tarazed.core.appInfo.sessioninfo.SessionInfo;
import com.amazon.tarazed.core.type.ProfileType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationConstructorMarker;
import kotlinx.serialization.internal.ArrayListSerializer;
import kotlinx.serialization.internal.EnumSerializer;
import kotlinx.serialization.internal.StringSerializer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SessionInfoFireTV.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0087\b\u0018\u0000 42\u00020\u0001:\u000234B\u007f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013¢\u0006\u0002\u0010\u0014B[\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\u0005\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0015J\t\u0010#\u001a\u00020\u0005HÆ\u0003J\t\u0010$\u001a\u00020\u0011HÆ\u0003J\t\u0010%\u001a\u00020\u0005HÆ\u0003J\t\u0010&\u001a\u00020\u0005HÆ\u0003J\t\u0010'\u001a\u00020\u0005HÆ\u0003J\u000f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003J\t\u0010)\u001a\u00020\u0005HÆ\u0003J\t\u0010*\u001a\u00020\rHÆ\u0003J\t\u0010+\u001a\u00020\rHÆ\u0003J\t\u0010,\u001a\u00020\u0005HÆ\u0003Js\u0010-\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00052\b\b\u0002\u0010\u0010\u001a\u00020\u0011HÆ\u0001J\u0013\u0010.\u001a\u00020\r2\b\u0010/\u001a\u0004\u0018\u000100HÖ\u0003J\t\u00101\u001a\u00020\u0003HÖ\u0001J\t\u00102\u001a\u00020\u0005HÖ\u0001R\u0014\u0010\u0007\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u000b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u001bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0017R\u0014\u0010\u0006\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017R\u0014\u0010\b\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0017R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001b¨\u00065"}, d2 = {"Lcom/amazon/tarazed/appinfo/sessioninfo/SessionInfoFireTV;", "Lcom/amazon/tarazed/core/appInfo/sessioninfo/SessionInfo;", "seen1", "", "operatingSystem", "", "operatingSystemVersion", "applicationName", "tarazedVersion", "handledEventTypes", "", MetricsConfiguration.DEVICE_LANGUAGE, "voiceView", "", "isFireTV", "remoteType", "profileType", "Lcom/amazon/tarazed/core/type/ProfileType;", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZZLjava/lang/String;Lcom/amazon/tarazed/core/type/ProfileType;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;ZZLjava/lang/String;Lcom/amazon/tarazed/core/type/ProfileType;)V", "getApplicationName", "()Ljava/lang/String;", "getDeviceLanguage", "getHandledEventTypes", "()Ljava/util/List;", "()Z", "getOperatingSystem", "getOperatingSystemVersion", "getProfileType", "()Lcom/amazon/tarazed/core/type/ProfileType;", "getRemoteType", "getTarazedVersion", "getVoiceView", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "", "hashCode", "toString", "$serializer", "Companion", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class SessionInfoFireTV implements SessionInfo {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String applicationName;
    @NotNull
    private final String deviceLanguage;
    @NotNull
    private final List<String> handledEventTypes;
    private final boolean isFireTV;
    @NotNull
    private final String operatingSystem;
    @NotNull
    private final String operatingSystemVersion;
    @NotNull
    private final ProfileType profileType;
    @NotNull
    private final String remoteType;
    @NotNull
    private final String tarazedVersion;
    private final boolean voiceView;

    /* compiled from: SessionInfoFireTV.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/appinfo/sessioninfo/SessionInfoFireTV$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/appinfo/sessioninfo/SessionInfoFireTV;", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<SessionInfoFireTV> serializer() {
            return SessionInfoFireTV$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SessionInfoFireTV(int i, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable List<String> list, @Nullable String str5, boolean z, boolean z2, @Nullable String str6, @Nullable ProfileType profileType, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.operatingSystem = str;
            if ((i & 2) == 0) {
                throw new MissingFieldException("operatingSystemVersion");
            }
            this.operatingSystemVersion = str2;
            if ((i & 4) == 0) {
                throw new MissingFieldException("applicationName");
            }
            this.applicationName = str3;
            if ((i & 8) == 0) {
                throw new MissingFieldException("tarazedVersion");
            }
            this.tarazedVersion = str4;
            if ((i & 16) == 0) {
                throw new MissingFieldException("handledEventTypes");
            }
            this.handledEventTypes = list;
            if ((i & 32) == 0) {
                throw new MissingFieldException(MetricsConfiguration.DEVICE_LANGUAGE);
            }
            this.deviceLanguage = str5;
            if ((i & 64) == 0) {
                throw new MissingFieldException("voiceView");
            }
            this.voiceView = z;
            if ((i & 128) == 0) {
                throw new MissingFieldException("isFireTV");
            }
            this.isFireTV = z2;
            if ((i & 256) == 0) {
                throw new MissingFieldException("remoteType");
            }
            this.remoteType = str6;
            if ((i & 512) == 0) {
                throw new MissingFieldException("profileType");
            }
            this.profileType = profileType;
            return;
        }
        throw new MissingFieldException("operatingSystem");
    }

    public SessionInfoFireTV(@NotNull String operatingSystem, @NotNull String operatingSystemVersion, @NotNull String applicationName, @NotNull String tarazedVersion, @NotNull List<String> handledEventTypes, @NotNull String deviceLanguage, boolean z, boolean z2, @NotNull String remoteType, @NotNull ProfileType profileType) {
        Intrinsics.checkParameterIsNotNull(operatingSystem, "operatingSystem");
        Intrinsics.checkParameterIsNotNull(operatingSystemVersion, "operatingSystemVersion");
        Intrinsics.checkParameterIsNotNull(applicationName, "applicationName");
        Intrinsics.checkParameterIsNotNull(tarazedVersion, "tarazedVersion");
        Intrinsics.checkParameterIsNotNull(handledEventTypes, "handledEventTypes");
        Intrinsics.checkParameterIsNotNull(deviceLanguage, "deviceLanguage");
        Intrinsics.checkParameterIsNotNull(remoteType, "remoteType");
        Intrinsics.checkParameterIsNotNull(profileType, "profileType");
        this.operatingSystem = operatingSystem;
        this.operatingSystemVersion = operatingSystemVersion;
        this.applicationName = applicationName;
        this.tarazedVersion = tarazedVersion;
        this.handledEventTypes = handledEventTypes;
        this.deviceLanguage = deviceLanguage;
        this.voiceView = z;
        this.isFireTV = z2;
        this.remoteType = remoteType;
        this.profileType = profileType;
    }

    @JvmStatic
    public static final void write$Self(@NotNull SessionInfoFireTV self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeStringElement(serialDesc, 0, self.getOperatingSystem());
        output.encodeStringElement(serialDesc, 1, self.getOperatingSystemVersion());
        output.encodeStringElement(serialDesc, 2, self.getApplicationName());
        output.encodeStringElement(serialDesc, 3, self.getTarazedVersion());
        output.encodeSerializableElement(serialDesc, 4, new ArrayListSerializer(StringSerializer.INSTANCE), self.getHandledEventTypes());
        output.encodeStringElement(serialDesc, 5, self.getDeviceLanguage());
        output.encodeBooleanElement(serialDesc, 6, self.voiceView);
        output.encodeBooleanElement(serialDesc, 7, self.isFireTV);
        output.encodeStringElement(serialDesc, 8, self.remoteType);
        output.encodeSerializableElement(serialDesc, 9, new EnumSerializer("com.amazon.tarazed.core.type.ProfileType", ProfileType.values()), self.profileType);
    }

    @NotNull
    public final String component1() {
        return getOperatingSystem();
    }

    @NotNull
    public final ProfileType component10() {
        return this.profileType;
    }

    @NotNull
    public final String component2() {
        return getOperatingSystemVersion();
    }

    @NotNull
    public final String component3() {
        return getApplicationName();
    }

    @NotNull
    public final String component4() {
        return getTarazedVersion();
    }

    @NotNull
    public final List<String> component5() {
        return getHandledEventTypes();
    }

    @NotNull
    public final String component6() {
        return getDeviceLanguage();
    }

    public final boolean component7() {
        return this.voiceView;
    }

    public final boolean component8() {
        return this.isFireTV;
    }

    @NotNull
    public final String component9() {
        return this.remoteType;
    }

    @NotNull
    public final SessionInfoFireTV copy(@NotNull String operatingSystem, @NotNull String operatingSystemVersion, @NotNull String applicationName, @NotNull String tarazedVersion, @NotNull List<String> handledEventTypes, @NotNull String deviceLanguage, boolean z, boolean z2, @NotNull String remoteType, @NotNull ProfileType profileType) {
        Intrinsics.checkParameterIsNotNull(operatingSystem, "operatingSystem");
        Intrinsics.checkParameterIsNotNull(operatingSystemVersion, "operatingSystemVersion");
        Intrinsics.checkParameterIsNotNull(applicationName, "applicationName");
        Intrinsics.checkParameterIsNotNull(tarazedVersion, "tarazedVersion");
        Intrinsics.checkParameterIsNotNull(handledEventTypes, "handledEventTypes");
        Intrinsics.checkParameterIsNotNull(deviceLanguage, "deviceLanguage");
        Intrinsics.checkParameterIsNotNull(remoteType, "remoteType");
        Intrinsics.checkParameterIsNotNull(profileType, "profileType");
        return new SessionInfoFireTV(operatingSystem, operatingSystemVersion, applicationName, tarazedVersion, handledEventTypes, deviceLanguage, z, z2, remoteType, profileType);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SessionInfoFireTV)) {
                return false;
            }
            SessionInfoFireTV sessionInfoFireTV = (SessionInfoFireTV) obj;
            return Intrinsics.areEqual(getOperatingSystem(), sessionInfoFireTV.getOperatingSystem()) && Intrinsics.areEqual(getOperatingSystemVersion(), sessionInfoFireTV.getOperatingSystemVersion()) && Intrinsics.areEqual(getApplicationName(), sessionInfoFireTV.getApplicationName()) && Intrinsics.areEqual(getTarazedVersion(), sessionInfoFireTV.getTarazedVersion()) && Intrinsics.areEqual(getHandledEventTypes(), sessionInfoFireTV.getHandledEventTypes()) && Intrinsics.areEqual(getDeviceLanguage(), sessionInfoFireTV.getDeviceLanguage()) && this.voiceView == sessionInfoFireTV.voiceView && this.isFireTV == sessionInfoFireTV.isFireTV && Intrinsics.areEqual(this.remoteType, sessionInfoFireTV.remoteType) && Intrinsics.areEqual(this.profileType, sessionInfoFireTV.profileType);
        }
        return true;
    }

    @Override // com.amazon.tarazed.core.appInfo.sessioninfo.SessionInfo
    @NotNull
    public String getApplicationName() {
        return this.applicationName;
    }

    @Override // com.amazon.tarazed.core.appInfo.sessioninfo.SessionInfo
    @NotNull
    public String getDeviceLanguage() {
        return this.deviceLanguage;
    }

    @Override // com.amazon.tarazed.core.appInfo.sessioninfo.SessionInfo
    @NotNull
    public List<String> getHandledEventTypes() {
        return this.handledEventTypes;
    }

    @Override // com.amazon.tarazed.core.appInfo.sessioninfo.SessionInfo
    @NotNull
    public String getOperatingSystem() {
        return this.operatingSystem;
    }

    @Override // com.amazon.tarazed.core.appInfo.sessioninfo.SessionInfo
    @NotNull
    public String getOperatingSystemVersion() {
        return this.operatingSystemVersion;
    }

    @NotNull
    public final ProfileType getProfileType() {
        return this.profileType;
    }

    @NotNull
    public final String getRemoteType() {
        return this.remoteType;
    }

    @Override // com.amazon.tarazed.core.appInfo.sessioninfo.SessionInfo
    @NotNull
    public String getTarazedVersion() {
        return this.tarazedVersion;
    }

    public final boolean getVoiceView() {
        return this.voiceView;
    }

    public int hashCode() {
        String operatingSystem = getOperatingSystem();
        int i = 0;
        int hashCode = (operatingSystem != null ? operatingSystem.hashCode() : 0) * 31;
        String operatingSystemVersion = getOperatingSystemVersion();
        int hashCode2 = (hashCode + (operatingSystemVersion != null ? operatingSystemVersion.hashCode() : 0)) * 31;
        String applicationName = getApplicationName();
        int hashCode3 = (hashCode2 + (applicationName != null ? applicationName.hashCode() : 0)) * 31;
        String tarazedVersion = getTarazedVersion();
        int hashCode4 = (hashCode3 + (tarazedVersion != null ? tarazedVersion.hashCode() : 0)) * 31;
        List<String> handledEventTypes = getHandledEventTypes();
        int hashCode5 = (hashCode4 + (handledEventTypes != null ? handledEventTypes.hashCode() : 0)) * 31;
        String deviceLanguage = getDeviceLanguage();
        int hashCode6 = (hashCode5 + (deviceLanguage != null ? deviceLanguage.hashCode() : 0)) * 31;
        boolean z = this.voiceView;
        if (z) {
            z = true;
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = (hashCode6 + i2) * 31;
        boolean z2 = this.isFireTV;
        if (z2) {
            z2 = true;
        }
        int i5 = z2 ? 1 : 0;
        int i6 = z2 ? 1 : 0;
        int i7 = (i4 + i5) * 31;
        String str = this.remoteType;
        int hashCode7 = (i7 + (str != null ? str.hashCode() : 0)) * 31;
        ProfileType profileType = this.profileType;
        if (profileType != null) {
            i = profileType.hashCode();
        }
        return hashCode7 + i;
    }

    public final boolean isFireTV() {
        return this.isFireTV;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SessionInfoFireTV(operatingSystem=");
        outline107.append(getOperatingSystem());
        outline107.append(", operatingSystemVersion=");
        outline107.append(getOperatingSystemVersion());
        outline107.append(", applicationName=");
        outline107.append(getApplicationName());
        outline107.append(", tarazedVersion=");
        outline107.append(getTarazedVersion());
        outline107.append(", handledEventTypes=");
        outline107.append(getHandledEventTypes());
        outline107.append(", deviceLanguage=");
        outline107.append(getDeviceLanguage());
        outline107.append(", voiceView=");
        outline107.append(this.voiceView);
        outline107.append(", isFireTV=");
        outline107.append(this.isFireTV);
        outline107.append(", remoteType=");
        outline107.append(this.remoteType);
        outline107.append(", profileType=");
        outline107.append(this.profileType);
        outline107.append(")");
        return outline107.toString();
    }
}
