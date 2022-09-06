package com.amazon.alexa.wakeword.davs;

import amazon.speech.simclient.settings.Settings;
import com.amazon.alexa.accessorykit.accessories.session.system.SystemModelI18nConfigTransformer;
import com.amazon.alexa.utils.validation.Assertions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: WakeWordModelArtifactInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B'\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\u0010\bR&\u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00070\nX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/WakeWordModelArtifactInfo;", "Lcom/amazon/alexa/wakeword/davs/ArtifactInfo;", "locale", "", "isCurrentDeviceHandsFree", "", SystemModelI18nConfigTransformer.KEY_WAKEWORDS, "", "(Ljava/lang/String;ZLjava/util/List;)V", "davsFilters", "", "getDavsFilters", "()Ljava/util/Map;", "getLocale", "()Ljava/lang/String;", "getWakeWords", "()Ljava/util/List;", "Companion", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class WakeWordModelArtifactInfo extends ArtifactInfo {
    private static final List<String> AMPD_USER_GROUP;
    private static final String ARTIFACT_KEY = "multiwakeword";
    private static final String ARTIFACT_TYPE = "lowpower-wakeword";
    public static final Companion Companion = new Companion(null);
    private static final List<String> DEFAULT_USER_GROUP;
    private static final String DEFAULT_WAKE_WORD = "alexa";
    private static final List<String> ENGINE_COMPATIBILITY_ID_LIST;
    private static final String FILTER_KEY_ENGINE_COMPATIBILITY_ID_LIST = "engineCompatibilityIdList";
    private static final String FILTER_KEY_FILTER_VERSION = "filterVersion";
    private static final String FILTER_KEY_LOCALE = "locale";
    private static final String FILTER_KEY_USER_GROUP = "userGroup";
    private static final String FILTER_KEY_USE_CASE = "useCase";
    private static final List<String> USE_CASE;
    @NotNull
    private static final String engineCompatibilityId;
    @NotNull
    private final Map<String, List<String>> davsFilters;
    @NotNull
    private final String locale;
    @NotNull
    private final List<String> wakeWords;

    /* compiled from: WakeWordModelArtifactInfo.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u000f\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/wakeword/davs/WakeWordModelArtifactInfo$Companion;", "", "()V", "AMPD_USER_GROUP", "", "", "ARTIFACT_KEY", "ARTIFACT_TYPE", "DEFAULT_USER_GROUP", "DEFAULT_WAKE_WORD", "ENGINE_COMPATIBILITY_ID_LIST", "FILTER_KEY_ENGINE_COMPATIBILITY_ID_LIST", "FILTER_KEY_FILTER_VERSION", "FILTER_KEY_LOCALE", "FILTER_KEY_USER_GROUP", "FILTER_KEY_USE_CASE", "USE_CASE", "engineCompatibilityId", "getEngineCompatibilityId", "()Ljava/lang/String;", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes11.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final String getEngineCompatibilityId() {
            return WakeWordModelArtifactInfo.engineCompatibilityId;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        List<String> listOf;
        List<String> listOf2;
        List<String> listOf3;
        List<String> listOf4;
        listOf = CollectionsKt__CollectionsJVMKt.listOf("9");
        ENGINE_COMPATIBILITY_ID_LIST = listOf;
        listOf2 = CollectionsKt__CollectionsJVMKt.listOf("production");
        DEFAULT_USER_GROUP = listOf2;
        listOf3 = CollectionsKt__CollectionsJVMKt.listOf("AMPD-production");
        AMPD_USER_GROUP = listOf3;
        listOf4 = CollectionsKt__CollectionsJVMKt.listOf("D-far-field-standalone-arm");
        USE_CASE = listOf4;
        engineCompatibilityId = ENGINE_COMPATIBILITY_ID_LIST.get(0);
    }

    @JvmOverloads
    public WakeWordModelArtifactInfo(@NotNull String str, boolean z) {
        this(str, z, null, 4, null);
    }

    public /* synthetic */ WakeWordModelArtifactInfo(String str, boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, z, (i & 4) != 0 ? CollectionsKt__CollectionsJVMKt.listOf("alexa") : list);
    }

    @Override // com.amazon.alexa.wakeword.davs.ArtifactInfo
    @NotNull
    protected Map<String, List<String>> getDavsFilters() {
        return this.davsFilters;
    }

    @NotNull
    public final String getLocale() {
        return this.locale;
    }

    @NotNull
    public final List<String> getWakeWords() {
        return this.wakeWords;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public WakeWordModelArtifactInfo(@NotNull String locale, boolean z, @NotNull List<String> wakeWords) {
        super(ARTIFACT_TYPE, wakeWords.size() == 1 ? wakeWords.get(0) : ARTIFACT_KEY);
        List listOf;
        List listOf2;
        Map mapOf;
        Map<String, List<String>> plus;
        List listOf3;
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(wakeWords, "wakeWords");
        int i = 0;
        this.locale = locale;
        this.wakeWords = wakeWords;
        Assertions.notEmpty(this.locale, "locale is empty");
        Pair[] pairArr = new Pair[5];
        listOf = CollectionsKt__CollectionsJVMKt.listOf("3");
        pairArr[0] = TuplesKt.to(FILTER_KEY_FILTER_VERSION, listOf);
        pairArr[1] = TuplesKt.to(FILTER_KEY_ENGINE_COMPATIBILITY_ID_LIST, ENGINE_COMPATIBILITY_ID_LIST);
        listOf2 = CollectionsKt__CollectionsJVMKt.listOf(this.locale);
        pairArr[2] = TuplesKt.to("locale", listOf2);
        pairArr[3] = TuplesKt.to(FILTER_KEY_USER_GROUP, z ? AMPD_USER_GROUP : DEFAULT_USER_GROUP);
        pairArr[4] = TuplesKt.to(FILTER_KEY_USE_CASE, USE_CASE);
        mapOf = MapsKt__MapsKt.mapOf(pairArr);
        HashMap hashMap = new HashMap();
        if (this.wakeWords.size() > 1) {
            for (Object obj : this.wakeWords) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                String outline49 = GeneratedOutlineSupport1.outline49(Settings.ListeningSettings.REASON_WAKEWORD, i2);
                listOf3 = CollectionsKt__CollectionsJVMKt.listOf((String) obj);
                hashMap.put(outline49, listOf3);
                i = i2;
            }
        }
        plus = MapsKt__MapsKt.plus(mapOf, hashMap);
        this.davsFilters = plus;
    }
}
