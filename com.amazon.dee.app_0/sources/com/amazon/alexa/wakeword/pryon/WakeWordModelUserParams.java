package com.amazon.alexa.wakeword.pryon;

import com.amazon.alexa.accessorykit.accessories.session.system.SystemModelI18nConfigTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: WakeWordDownloadManager.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0003J#\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/wakeword/pryon/WakeWordModelUserParams;", "", "locale", "", SystemModelI18nConfigTransformer.KEY_WAKEWORDS, "", "(Ljava/lang/String;Ljava/util/List;)V", "getLocale", "()Ljava/lang/String;", "getWakeWords", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AVSAndroidClient-pryonprovider_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public final class WakeWordModelUserParams {
    @NotNull
    private final String locale;
    @NotNull
    private final List<String> wakeWords;

    @JvmOverloads
    public WakeWordModelUserParams(@NotNull String str) {
        this(str, null, 2, null);
    }

    @JvmOverloads
    public WakeWordModelUserParams(@NotNull String locale, @NotNull List<String> wakeWords) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(wakeWords, "wakeWords");
        this.locale = locale;
        this.wakeWords = wakeWords;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WakeWordModelUserParams copy$default(WakeWordModelUserParams wakeWordModelUserParams, String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            str = wakeWordModelUserParams.locale;
        }
        if ((i & 2) != 0) {
            list = wakeWordModelUserParams.wakeWords;
        }
        return wakeWordModelUserParams.copy(str, list);
    }

    @NotNull
    public final String component1() {
        return this.locale;
    }

    @NotNull
    public final List<String> component2() {
        return this.wakeWords;
    }

    @NotNull
    public final WakeWordModelUserParams copy(@NotNull String locale, @NotNull List<String> wakeWords) {
        Intrinsics.checkParameterIsNotNull(locale, "locale");
        Intrinsics.checkParameterIsNotNull(wakeWords, "wakeWords");
        return new WakeWordModelUserParams(locale, wakeWords);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof WakeWordModelUserParams)) {
                return false;
            }
            WakeWordModelUserParams wakeWordModelUserParams = (WakeWordModelUserParams) obj;
            return Intrinsics.areEqual(this.locale, wakeWordModelUserParams.locale) && Intrinsics.areEqual(this.wakeWords, wakeWordModelUserParams.wakeWords);
        }
        return true;
    }

    @NotNull
    public final String getLocale() {
        return this.locale;
    }

    @NotNull
    public final List<String> getWakeWords() {
        return this.wakeWords;
    }

    public int hashCode() {
        String str = this.locale;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        List<String> list = this.wakeWords;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("WakeWordModelUserParams(locale=");
        outline107.append(this.locale);
        outline107.append(", wakeWords=");
        return GeneratedOutlineSupport1.outline95(outline107, this.wakeWords, ")");
    }

    public /* synthetic */ WakeWordModelUserParams(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? CollectionsKt__CollectionsJVMKt.listOf("alexa") : list);
    }
}
