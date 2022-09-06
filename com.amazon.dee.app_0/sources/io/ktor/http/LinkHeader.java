package io.ktor.http;

import com.amazon.alexa.drive.metrics.EntertainmentMetrics;
import com.amazon.alexa.mobilytics.configuration.Config;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
/* compiled from: LinkHeader.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006\"\u00020\u0003¢\u0006\u0002\u0010\u0007B%\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bB\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\b¢\u0006\u0002\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0013"}, d2 = {"Lio/ktor/http/LinkHeader;", "Lio/ktor/http/HeaderValueWithParameters;", "uri", "", Parameters.Rel, "(Ljava/lang/String;Ljava/lang/String;)V", "", "(Ljava/lang/String;[Ljava/lang/String;)V", "", "type", "Lio/ktor/http/ContentType;", "(Ljava/lang/String;Ljava/util/List;Lio/ktor/http/ContentType;)V", "params", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Ljava/util/List;)V", "getUri", "()Ljava/lang/String;", "Parameters", "Rel", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class LinkHeader extends HeaderValueWithParameters {

    /* compiled from: LinkHeader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lio/ktor/http/LinkHeader$Parameters;", "", "()V", "Anchor", "", "HrefLang", "Media", "Rel", Parameters.Rev, "Title", "Type", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Parameters {
        @NotNull
        public static final String Anchor = "anchor";
        @NotNull
        public static final String HrefLang = "hreflang";
        public static final Parameters INSTANCE = new Parameters();
        @NotNull
        public static final String Media = "media";
        @NotNull
        public static final String Rel = "rel";
        @NotNull
        public static final String Rev = "Rev";
        @NotNull
        public static final String Title = "title";
        @NotNull
        public static final String Type = "type";

        private Parameters() {
        }
    }

    /* compiled from: LinkHeader.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lio/ktor/http/LinkHeader$Rel;", "", "()V", "DnsPrefetch", "", EntertainmentMetrics.Button.NEXT, "PreConnect", "PreLoad", "PreRender", AlexaMetricsConstants.MetricsComponents.PREFETCH, "Stylesheet", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Rel {
        @NotNull
        public static final String DnsPrefetch = "dns-prefetch";
        public static final Rel INSTANCE = new Rel();
        @NotNull
        public static final String Next = "next";
        @NotNull
        public static final String PreConnect = "preconnect";
        @NotNull
        public static final String PreLoad = "preload";
        @NotNull
        public static final String PreRender = "prerender";
        @NotNull
        public static final String Prefetch = "prefetch";
        @NotNull
        public static final String Stylesheet = "stylesheet";

        private Rel() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LinkHeader(@NotNull String uri, @NotNull List<HeaderValueParam> params) {
        super(Typography.less + uri + Typography.greater, params);
        Intrinsics.checkParameterIsNotNull(uri, "uri");
        Intrinsics.checkParameterIsNotNull(params, "params");
    }

    @NotNull
    public final String getUri() {
        String removePrefix;
        String removeSuffix;
        removePrefix = StringsKt__StringsKt.removePrefix(getContent(), (CharSequence) Config.Compare.LESS_THAN);
        removeSuffix = StringsKt__StringsKt.removeSuffix(removePrefix, (CharSequence) Config.Compare.GREATER_THAN);
        return removeSuffix;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public LinkHeader(@org.jetbrains.annotations.NotNull java.lang.String r3, @org.jetbrains.annotations.NotNull java.lang.String r4) {
        /*
            r2 = this;
            java.lang.String r0 = "uri"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            java.lang.String r0 = "rel"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            io.ktor.http.HeaderValueParam r1 = new io.ktor.http.HeaderValueParam
            r1.<init>(r0, r4)
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r1)
            r2.<init>(r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.LinkHeader.<init>(java.lang.String, java.lang.String):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public LinkHeader(@org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull java.lang.String... r13) {
        /*
            r11 = this;
            java.lang.String r0 = "uri"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r0)
            java.lang.String r0 = "rel"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r13, r0)
            io.ktor.http.HeaderValueParam r1 = new io.ktor.http.HeaderValueParam
            java.lang.String r3 = " "
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 62
            r10 = 0
            r2 = r13
            java.lang.String r13 = kotlin.collections.ArraysKt.joinToString$default(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            r1.<init>(r0, r13)
            java.util.List r13 = kotlin.collections.CollectionsKt.listOf(r1)
            r11.<init>(r12, r13)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.LinkHeader.<init>(java.lang.String, java.lang.String[]):void");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public LinkHeader(@org.jetbrains.annotations.NotNull java.lang.String r16, @org.jetbrains.annotations.NotNull java.util.List<java.lang.String> r17, @org.jetbrains.annotations.NotNull io.ktor.http.ContentType r18) {
        /*
            r15 = this;
            r0 = r16
            java.lang.String r1 = "uri"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r1)
            java.lang.String r1 = "rel"
            r2 = r17
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r2, r1)
            java.lang.String r11 = "type"
            r12 = r18
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r12, r11)
            r3 = 2
            io.ktor.http.HeaderValueParam[] r13 = new io.ktor.http.HeaderValueParam[r3]
            io.ktor.http.HeaderValueParam r14 = new io.ktor.http.HeaderValueParam
            java.lang.String r3 = " "
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 62
            r10 = 0
            java.lang.String r2 = kotlin.collections.CollectionsKt.joinToString$default(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            r14.<init>(r1, r2)
            r1 = 0
            r13[r1] = r14
            io.ktor.http.HeaderValueParam r1 = new io.ktor.http.HeaderValueParam
            java.lang.String r2 = r18.toString()
            r1.<init>(r11, r2)
            r2 = 1
            r13[r2] = r1
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r13)
            r2 = r15
            r15.<init>(r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.LinkHeader.<init>(java.lang.String, java.util.List, io.ktor.http.ContentType):void");
    }
}
