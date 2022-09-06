package io.ktor.http;

import com.amazon.identity.auth.map.device.token.MAPCookie;
import io.ktor.http.HeaderValueWithParameters;
import java.util.Collection;
import java.util.List;
import javax.mail.Part;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ContentDisposition.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u00172\u00020\u0001:\u0002\u0017\u0018B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\u0016\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003J\u0014\u0010\u0015\u001a\u00020\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0011\u0010\u0002\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0013\u0010\n\u001a\u0004\u0018\u00010\u00038F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t¨\u0006\u0019"}, d2 = {"Lio/ktor/http/ContentDisposition;", "Lio/ktor/http/HeaderValueWithParameters;", "disposition", "", "parameters", "", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Ljava/util/List;)V", "getDisposition", "()Ljava/lang/String;", "name", "getName", "equals", "", "other", "", "hashCode", "", "withParameter", "key", "value", "withParameters", "newParameters", "Companion", "Parameters", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ContentDisposition extends HeaderValueWithParameters {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final ContentDisposition File = new ContentDisposition("file", null, 2, null);
    @NotNull
    private static final ContentDisposition Mixed = new ContentDisposition("mixed", null, 2, null);
    @NotNull
    private static final ContentDisposition Attachment = new ContentDisposition("attachment", null, 2, null);
    @NotNull
    private static final ContentDisposition Inline = new ContentDisposition(Part.INLINE, null, 2, null);

    /* compiled from: ContentDisposition.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\u0010"}, d2 = {"Lio/ktor/http/ContentDisposition$Companion;", "", "()V", "Attachment", "Lio/ktor/http/ContentDisposition;", "getAttachment", "()Lio/ktor/http/ContentDisposition;", "File", "getFile", "Inline", "getInline", "Mixed", "getMixed", "parse", "value", "", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ContentDisposition getAttachment() {
            return ContentDisposition.Attachment;
        }

        @NotNull
        public final ContentDisposition getFile() {
            return ContentDisposition.File;
        }

        @NotNull
        public final ContentDisposition getInline() {
            return ContentDisposition.Inline;
        }

        @NotNull
        public final ContentDisposition getMixed() {
            return ContentDisposition.Mixed;
        }

        @NotNull
        public final ContentDisposition parse(@NotNull String value) {
            Intrinsics.checkParameterIsNotNull(value, "value");
            HeaderValueWithParameters.Companion companion = HeaderValueWithParameters.Companion;
            HeaderValue headerValue = (HeaderValue) CollectionsKt.single((List<? extends Object>) HttpHeaderValueParserKt.parseHeaderValue(value));
            return new ContentDisposition(headerValue.getValue(), headerValue.getParams());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: ContentDisposition.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lio/ktor/http/ContentDisposition$Parameters;", "", "()V", "CreationDate", "", "FileName", "FileNameAsterisk", "Handling", "ModificationDate", MAPCookie.KEY_NAME, "ReadDate", "Size", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Parameters {
        @NotNull
        public static final String CreationDate = "creation-date";
        @NotNull
        public static final String FileName = "filename";
        @NotNull
        public static final String FileNameAsterisk = "filename*";
        @NotNull
        public static final String Handling = "handling";
        public static final Parameters INSTANCE = new Parameters();
        @NotNull
        public static final String ModificationDate = "modification-date";
        @NotNull
        public static final String Name = "name";
        @NotNull
        public static final String ReadDate = "read-date";
        @NotNull
        public static final String Size = "size";

        private Parameters() {
        }
    }

    public /* synthetic */ ContentDisposition(String str, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ContentDisposition) {
            ContentDisposition contentDisposition = (ContentDisposition) obj;
            if (Intrinsics.areEqual(getDisposition(), contentDisposition.getDisposition()) && Intrinsics.areEqual(getParameters(), contentDisposition.getParameters())) {
                return true;
            }
        }
        return false;
    }

    @NotNull
    public final String getDisposition() {
        return getContent();
    }

    @Nullable
    public final String getName() {
        return parameter("name");
    }

    public int hashCode() {
        return getParameters().hashCode() + (getDisposition().hashCode() * 31);
    }

    @NotNull
    public final ContentDisposition withParameter(@NotNull String key, @NotNull String value) {
        List plus;
        Intrinsics.checkParameterIsNotNull(key, "key");
        Intrinsics.checkParameterIsNotNull(value, "value");
        String disposition = getDisposition();
        plus = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) getParameters()), (Object) new HeaderValueParam(key, value));
        return new ContentDisposition(disposition, plus);
    }

    @NotNull
    public final ContentDisposition withParameters(@NotNull List<HeaderValueParam> newParameters) {
        List plus;
        Intrinsics.checkParameterIsNotNull(newParameters, "newParameters");
        String disposition = getDisposition();
        plus = CollectionsKt___CollectionsKt.plus((Collection) getParameters(), (Iterable) newParameters);
        return new ContentDisposition(disposition, plus);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentDisposition(@NotNull String disposition, @NotNull List<HeaderValueParam> parameters) {
        super(disposition, parameters);
        Intrinsics.checkParameterIsNotNull(disposition, "disposition");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
    }
}
