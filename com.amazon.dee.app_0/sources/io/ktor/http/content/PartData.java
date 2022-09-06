package io.ktor.http.content;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.dee.app.data.reactnative.bridges.HttpClientModule;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlinx.io.core.Input;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Multipart.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\"#$B\u001d\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u001d\u0010\b\u001a\u0004\u0018\u00010\t8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u000f8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\r\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u0004\u0018\u00010\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u00068FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u0016R\u001c\u0010\u001f\u001a\u0004\u0018\u00010\u00188FX\u0087\u0004¢\u0006\f\u0012\u0004\b \u0010\u001d\u001a\u0004\b!\u0010\u001a\u0082\u0001\u0003%&'¨\u0006("}, d2 = {"Lio/ktor/http/content/PartData;", "", "dispose", "Lkotlin/Function0;", "", HttpClientModule.ElementsRequestKey.HEADERS, "Lio/ktor/http/Headers;", "(Lkotlin/jvm/functions/Function0;Lio/ktor/http/Headers;)V", "contentDisposition", "Lio/ktor/http/ContentDisposition;", "getContentDisposition", "()Lio/ktor/http/ContentDisposition;", "contentDisposition$delegate", "Lkotlin/Lazy;", "contentType", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "contentType$delegate", "getDispose", "()Lkotlin/jvm/functions/Function0;", "getHeaders", "()Lio/ktor/http/Headers;", "name", "", "getName", "()Ljava/lang/String;", "partHeaders", "partHeaders$annotations", "()V", "getPartHeaders", "partName", "partName$annotations", "getPartName", "BinaryItem", "FileItem", "FormItem", "Lio/ktor/http/content/PartData$FormItem;", "Lio/ktor/http/content/PartData$FileItem;", "Lio/ktor/http/content/PartData$BinaryItem;", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public abstract class PartData {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(PartData.class), "contentDisposition", "getContentDisposition()Lio/ktor/http/ContentDisposition;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(PartData.class), "contentType", "getContentType()Lio/ktor/http/ContentType;"))};
    @Nullable
    private final Lazy contentDisposition$delegate;
    @Nullable
    private final Lazy contentType$delegate;
    @NotNull
    private final Function0<Unit> dispose;
    @NotNull
    private final Headers headers;

    /* compiled from: Multipart.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/http/content/PartData$BinaryItem;", "Lio/ktor/http/content/PartData;", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER, "Lkotlin/Function0;", "Lkotlinx/io/core/Input;", "dispose", "", "partHeaders", "Lio/ktor/http/Headers;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lio/ktor/http/Headers;)V", "getProvider", "()Lkotlin/jvm/functions/Function0;", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class BinaryItem extends PartData {
        @NotNull
        private final Function0<Input> provider;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public BinaryItem(@NotNull Function0<? extends Input> provider, @NotNull Function0<Unit> dispose, @NotNull Headers partHeaders) {
            super(dispose, partHeaders, null);
            Intrinsics.checkParameterIsNotNull(provider, "provider");
            Intrinsics.checkParameterIsNotNull(dispose, "dispose");
            Intrinsics.checkParameterIsNotNull(partHeaders, "partHeaders");
            this.provider = provider;
        }

        @NotNull
        public final Function0<Input> getProvider() {
            return this.provider;
        }
    }

    /* compiled from: Multipart.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lio/ktor/http/content/PartData$FileItem;", "Lio/ktor/http/content/PartData;", EntertainmentConstants.PLAYER_INFO_JSON_ATTRIBUTE_PROVIDER, "Lkotlin/Function0;", "Lkotlinx/io/core/Input;", "dispose", "", "partHeaders", "Lio/ktor/http/Headers;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lio/ktor/http/Headers;)V", "originalFileName", "", "getOriginalFileName", "()Ljava/lang/String;", "getProvider", "()Lkotlin/jvm/functions/Function0;", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class FileItem extends PartData {
        @Nullable
        private final String originalFileName;
        @NotNull
        private final Function0<Input> provider;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public FileItem(@NotNull Function0<? extends Input> provider, @NotNull Function0<Unit> dispose, @NotNull Headers partHeaders) {
            super(dispose, partHeaders, null);
            Intrinsics.checkParameterIsNotNull(provider, "provider");
            Intrinsics.checkParameterIsNotNull(dispose, "dispose");
            Intrinsics.checkParameterIsNotNull(partHeaders, "partHeaders");
            String str = null;
            this.provider = provider;
            ContentDisposition contentDisposition = getContentDisposition();
            this.originalFileName = contentDisposition != null ? contentDisposition.parameter(ContentDisposition.Parameters.FileName) : str;
        }

        @Nullable
        public final String getOriginalFileName() {
            return this.originalFileName;
        }

        @NotNull
        public final Function0<Input> getProvider() {
            return this.provider;
        }
    }

    /* compiled from: Multipart.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lio/ktor/http/content/PartData$FormItem;", "Lio/ktor/http/content/PartData;", "value", "", "dispose", "Lkotlin/Function0;", "", "partHeaders", "Lio/ktor/http/Headers;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lio/ktor/http/Headers;)V", "getValue", "()Ljava/lang/String;", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class FormItem extends PartData {
        @NotNull
        private final String value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FormItem(@NotNull String value, @NotNull Function0<Unit> dispose, @NotNull Headers partHeaders) {
            super(dispose, partHeaders, null);
            Intrinsics.checkParameterIsNotNull(value, "value");
            Intrinsics.checkParameterIsNotNull(dispose, "dispose");
            Intrinsics.checkParameterIsNotNull(partHeaders, "partHeaders");
            this.value = value;
        }

        @NotNull
        public final String getValue() {
            return this.value;
        }
    }

    private PartData(Function0<Unit> function0, Headers headers) {
        Lazy lazy;
        Lazy lazy2;
        this.dispose = function0;
        this.headers = headers;
        lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new PartData$contentDisposition$2(this));
        this.contentDisposition$delegate = lazy;
        lazy2 = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new PartData$contentType$2(this));
        this.contentType$delegate = lazy2;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use headers property instead", replaceWith = @ReplaceWith(expression = HttpClientModule.ElementsRequestKey.HEADERS, imports = {}))
    public static /* synthetic */ void partHeaders$annotations() {
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use name property instead", replaceWith = @ReplaceWith(expression = "name", imports = {}))
    public static /* synthetic */ void partName$annotations() {
    }

    @Nullable
    public final ContentDisposition getContentDisposition() {
        Lazy lazy = this.contentDisposition$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (ContentDisposition) lazy.getValue();
    }

    @Nullable
    public final ContentType getContentType() {
        Lazy lazy = this.contentType$delegate;
        KProperty kProperty = $$delegatedProperties[1];
        return (ContentType) lazy.getValue();
    }

    @NotNull
    public final Function0<Unit> getDispose() {
        return this.dispose;
    }

    @NotNull
    public final Headers getHeaders() {
        return this.headers;
    }

    @Nullable
    public final String getName() {
        ContentDisposition contentDisposition = getContentDisposition();
        if (contentDisposition != null) {
            return contentDisposition.getName();
        }
        return null;
    }

    @NotNull
    public final Headers getPartHeaders() {
        return this.headers;
    }

    @Nullable
    public final String getPartName() {
        return getName();
    }

    public /* synthetic */ PartData(Function0 function0, Headers headers, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, headers);
    }
}
