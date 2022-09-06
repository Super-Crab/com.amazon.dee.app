package com.amazon.alexa.logging;

import com.amazon.identity.auth.device.api.MultipleAccountManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Tag.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\u0018\u0000 \r2\u00020\u0001:\u0002\r\u000eB\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00038@X\u0080\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\b¨\u0006\u000f"}, d2 = {"Lcom/amazon/alexa/logging/Tag;", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "", "group", "(Ljava/lang/String;Ljava/lang/String;)V", "safeShort", "getSafeShort$AlexaMobileAndroidLogging_release", "()Ljava/lang/String;", "safeShort$delegate", "Lkotlin/Lazy;", "short", "getShort$AlexaMobileAndroidLogging_release", "Companion", "Maker", "AlexaMobileAndroidLogging_release"}, k = 1, mv = {1, 1, 10})
/* loaded from: classes9.dex */
public final class Tag {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Tag.class), "safeShort", "getSafeShort$AlexaMobileAndroidLogging_release()Ljava/lang/String;"))};
    public static final Companion Companion = new Companion(null);
    private static final int MAX_SAFE_TAG_LENGTH = 23;
    private final String group;
    private final String owner;
    @NotNull
    private final Lazy safeShort$delegate;

    /* compiled from: Tag.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/logging/Tag$Companion;", "", "()V", "MAX_SAFE_TAG_LENGTH", "", "safe", "", "tag", "safe$AlexaMobileAndroidLogging_release", "AlexaMobileAndroidLogging_release"}, k = 1, mv = {1, 1, 10})
    /* loaded from: classes9.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final String safe$AlexaMobileAndroidLogging_release(@NotNull String tag) {
            int lastIndexOf$default;
            Intrinsics.checkParameterIsNotNull(tag, "tag");
            if (tag.length() <= 23) {
                return tag;
            }
            lastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default((CharSequence) tag, '.', 0, false, 6, (Object) null);
            int i = lastIndexOf$default + 1;
            if (tag.length() - i > 23) {
                String substring = tag.substring(i, i + 23);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return substring;
            }
            String substring2 = tag.substring(tag.length() - 23);
            Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
            return substring2;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: Tag.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bJ\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0003R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/alexa/logging/Tag$Maker;", "", "group", "", "(Ljava/lang/String;)V", "tag", "Lcom/amazon/alexa/logging/Tag;", "clazz", "Ljava/lang/Class;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "AlexaMobileAndroidLogging_release"}, k = 1, mv = {1, 1, 10})
    /* loaded from: classes9.dex */
    public static final class Maker {
        private final String group;

        public Maker(@Nullable String str) {
            this.group = str;
        }

        @NotNull
        public final Tag tag(@NotNull String owner) {
            Intrinsics.checkParameterIsNotNull(owner, "owner");
            return new Tag(owner, this.group, null);
        }

        @NotNull
        public final Tag tag(@NotNull Class<?> clazz) {
            Intrinsics.checkParameterIsNotNull(clazz, "clazz");
            String simpleName = clazz.getSimpleName();
            Intrinsics.checkExpressionValueIsNotNull(simpleName, "clazz.simpleName");
            return new Tag(simpleName, this.group, null);
        }
    }

    private Tag(String str, String str2) {
        this.owner = str;
        this.group = str2;
        this.safeShort$delegate = LazyKt.lazy(new Tag$safeShort$2(this));
    }

    @NotNull
    public final String getSafeShort$AlexaMobileAndroidLogging_release() {
        Lazy lazy = this.safeShort$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (String) lazy.getValue();
    }

    @NotNull
    public final String getShort$AlexaMobileAndroidLogging_release() {
        return this.owner;
    }

    public /* synthetic */ Tag(@NotNull String str, @Nullable String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }
}
