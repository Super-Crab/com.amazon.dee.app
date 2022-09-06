package com.amazon.alexa.utils.validation;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/alexa/utils/validation/AlexaUriValidator;", "", "()V", "Companion", "AVSAndroidClient-utils_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes10.dex */
public final class AlexaUriValidator {
    public static final a Companion = new a(null);
    @JvmField
    @NotNull
    public static final String[] SUPPORTED_DOMAINS = {".amazon.com", ".amazonalexa.com"};
    @NotNull
    public static final String SUPPORTED_SCHEME = "https";

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/utils/validation/AlexaUriValidator$Companion;", "", "()V", "SUPPORTED_DOMAINS", "", "", "[Ljava/lang/String;", "SUPPORTED_SCHEME", "hasValidHost", "", "uri", "Landroid/net/Uri;", "hasValidScheme", "AVSAndroidClient-utils_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes10.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final boolean a(@NotNull Uri uri) {
            Intrinsics.checkParameterIsNotNull(uri, "uri");
            return Intrinsics.areEqual("https", uri.getScheme());
        }

        @JvmStatic
        public final boolean b(@NotNull Uri uri) {
            String[] strArr;
            boolean endsWith$default;
            Intrinsics.checkParameterIsNotNull(uri, "uri");
            String host = uri.getHost();
            for (String str : AlexaUriValidator.SUPPORTED_DOMAINS) {
                if (host == null) {
                    Intrinsics.throwNpe();
                }
                endsWith$default = StringsKt__StringsJVMKt.endsWith$default(host, str, false, 2, null);
                if (endsWith$default) {
                    return true;
                }
            }
            return false;
        }
    }

    private AlexaUriValidator() {
        throw new UnsupportedOperationException();
    }

    @JvmStatic
    public static final boolean hasValidHost(@NotNull Uri uri) {
        return Companion.b(uri);
    }

    @JvmStatic
    public static final boolean hasValidScheme(@NotNull Uri uri) {
        return Companion.a(uri);
    }
}
