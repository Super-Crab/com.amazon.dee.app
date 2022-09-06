package io.ktor.http;

import com.amazon.alexa.mobilytics.configuration.KinesisEndpoint;
import com.amazon.alexa.voice.tta.Constants;
import com.amazon.alexa.voice.ui.onedesign.util.image.ImageType;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import io.ktor.http.HeaderValueWithParameters;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import org.apache.commons.fileupload.FileUploadBase;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ContentTypes.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\r\u0018\u0000 \u001d2\u00020\u0001:\b\u001b\u001c\u001d\u001e\u001f !\"B'\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bB/\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\nJ\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\u0018\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003H\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0000J\u000e\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0003J\u0016\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0003J\u0006\u0010\u001a\u001a\u00020\u0000R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006#"}, d2 = {"Lio/ktor/http/ContentType;", "Lio/ktor/http/HeaderValueWithParameters;", "contentType", "", "contentSubtype", "parameters", "", "Lio/ktor/http/HeaderValueParam;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "existingContent", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getContentSubtype", "()Ljava/lang/String;", "getContentType", "equals", "", "other", "", "hasParameter", "name", "value", "hashCode", "", "match", "pattern", "withParameter", "withoutParameters", "Application", "Audio", "Companion", "Image", AlexaMetricsConstants.EventConstants.MESSAGE, "MultiPart", Constants.Namespaces.TEXT, "Video", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class ContentType extends HeaderValueWithParameters {
    @NotNull
    private final String contentSubtype;
    @NotNull
    private final String contentType;
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final ContentType Any = new ContentType("*", "*", null, 4, null);

    /* compiled from: ContentTypes.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0019\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0011\u0010\u001b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006¨\u0006\u001d"}, d2 = {"Lio/ktor/http/ContentType$Application;", "", "()V", KinesisEndpoint.AppState.ANY, "Lio/ktor/http/ContentType;", "getAny", "()Lio/ktor/http/ContentType;", "Atom", "getAtom", "FontWoff", "getFontWoff", "FormUrlEncoded", "getFormUrlEncoded", "GZip", "getGZip", "JavaScript", "getJavaScript", "Json", "getJson", "OctetStream", "getOctetStream", "Rss", "getRss", "Xml", "getXml", "Xml_Dtd", "getXml_Dtd", "Zip", "getZip", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Application {
        public static final Application INSTANCE = new Application();
        @NotNull
        private static final ContentType Any = new ContentType("application", "*", null, 4, null);
        @NotNull
        private static final ContentType Atom = new ContentType("application", "atom+xml", null, 4, null);
        @NotNull
        private static final ContentType Json = new ContentType("application", "json", null, 4, null);
        @NotNull
        private static final ContentType JavaScript = new ContentType("application", "javascript", null, 4, null);
        @NotNull
        private static final ContentType OctetStream = new ContentType("application", "octet-stream", null, 4, null);
        @NotNull
        private static final ContentType FontWoff = new ContentType("application", "font-woff", null, 4, null);
        @NotNull
        private static final ContentType Rss = new ContentType("application", "rss+xml", null, 4, null);
        @NotNull
        private static final ContentType Xml = new ContentType("application", "xml", null, 4, null);
        @NotNull
        private static final ContentType Xml_Dtd = new ContentType("application", "xml-dtd", null, 4, null);
        @NotNull
        private static final ContentType Zip = new ContentType("application", "zip", null, 4, null);
        @NotNull
        private static final ContentType GZip = new ContentType("application", "gzip", null, 4, null);
        @NotNull
        private static final ContentType FormUrlEncoded = new ContentType("application", "x-www-form-urlencoded", null, 4, null);

        private Application() {
        }

        @NotNull
        public final ContentType getAny() {
            return Any;
        }

        @NotNull
        public final ContentType getAtom() {
            return Atom;
        }

        @NotNull
        public final ContentType getFontWoff() {
            return FontWoff;
        }

        @NotNull
        public final ContentType getFormUrlEncoded() {
            return FormUrlEncoded;
        }

        @NotNull
        public final ContentType getGZip() {
            return GZip;
        }

        @NotNull
        public final ContentType getJavaScript() {
            return JavaScript;
        }

        @NotNull
        public final ContentType getJson() {
            return Json;
        }

        @NotNull
        public final ContentType getOctetStream() {
            return OctetStream;
        }

        @NotNull
        public final ContentType getRss() {
            return Rss;
        }

        @NotNull
        public final ContentType getXml() {
            return Xml;
        }

        @NotNull
        public final ContentType getXml_Dtd() {
            return Xml_Dtd;
        }

        @NotNull
        public final ContentType getZip() {
            return Zip;
        }
    }

    /* compiled from: ContentTypes.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006¨\u0006\r"}, d2 = {"Lio/ktor/http/ContentType$Audio;", "", "()V", KinesisEndpoint.AppState.ANY, "Lio/ktor/http/ContentType;", "getAny", "()Lio/ktor/http/ContentType;", "MP4", "getMP4", "MPEG", "getMPEG", "OGG", "getOGG", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Audio {
        public static final Audio INSTANCE = new Audio();
        @NotNull
        private static final ContentType Any = new ContentType("audio", "*", null, 4, null);
        @NotNull
        private static final ContentType MP4 = new ContentType("audio", "mp4", null, 4, null);
        @NotNull
        private static final ContentType MPEG = new ContentType("audio", "mpeg", null, 4, null);
        @NotNull
        private static final ContentType OGG = new ContentType("audio", "ogg", null, 4, null);

        private Audio() {
        }

        @NotNull
        public final ContentType getAny() {
            return Any;
        }

        @NotNull
        public final ContentType getMP4() {
            return MP4;
        }

        @NotNull
        public final ContentType getMPEG() {
            return MPEG;
        }

        @NotNull
        public final ContentType getOGG() {
            return OGG;
        }
    }

    /* compiled from: ContentTypes.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lio/ktor/http/ContentType$Companion;", "", "()V", KinesisEndpoint.AppState.ANY, "Lio/ktor/http/ContentType;", "getAny", "()Lio/ktor/http/ContentType;", "parse", "value", "", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final ContentType getAny() {
            return ContentType.Any;
        }

        @NotNull
        public final ContentType parse(@NotNull String value) {
            int indexOf$default;
            CharSequence trim;
            CharSequence trim2;
            boolean contains$default;
            CharSequence trim3;
            Intrinsics.checkParameterIsNotNull(value, "value");
            HeaderValueWithParameters.Companion companion = HeaderValueWithParameters.Companion;
            HeaderValue headerValue = (HeaderValue) CollectionsKt.single((List<? extends Object>) HttpHeaderValueParserKt.parseHeaderValue(value));
            String value2 = headerValue.getValue();
            List<HeaderValueParam> params = headerValue.getParams();
            indexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) value2, '/', 0, false, 6, (Object) null);
            if (indexOf$default == -1) {
                if (value2 != null) {
                    trim3 = StringsKt__StringsKt.trim((CharSequence) value2);
                    if (Intrinsics.areEqual(trim3.toString(), "*")) {
                        return ContentType.Companion.getAny();
                    }
                    throw new BadContentTypeFormatException(value);
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            } else if (value2 != null) {
                String substring = value2.substring(0, indexOf$default);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                trim = StringsKt__StringsKt.trim((CharSequence) substring);
                String obj = trim.toString();
                boolean z = true;
                if (!(obj.length() == 0)) {
                    String substring2 = value2.substring(indexOf$default + 1);
                    Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
                    trim2 = StringsKt__StringsKt.trim((CharSequence) substring2);
                    String obj2 = trim2.toString();
                    if (obj2.length() != 0) {
                        z = false;
                    }
                    if (!z) {
                        contains$default = StringsKt__StringsKt.contains$default((CharSequence) obj2, '/', false, 2, (Object) null);
                        if (!contains$default) {
                            return new ContentType(obj, obj2, params);
                        }
                    }
                    throw new BadContentTypeFormatException(value);
                }
                throw new BadContentTypeFormatException(value);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* compiled from: ContentTypes.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006¨\u0006\u0011"}, d2 = {"Lio/ktor/http/ContentType$Image;", "", "()V", KinesisEndpoint.AppState.ANY, "Lio/ktor/http/ContentType;", "getAny", "()Lio/ktor/http/ContentType;", "GIF", "getGIF", "JPEG", "getJPEG", "PNG", "getPNG", "SVG", "getSVG", "XIcon", "getXIcon", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Image {
        public static final Image INSTANCE = new Image();
        @NotNull
        private static final ContentType Any = new ContentType("image", "*", null, 4, null);
        @NotNull
        private static final ContentType GIF = new ContentType("image", "gif", null, 4, null);
        @NotNull
        private static final ContentType JPEG = new ContentType("image", "jpeg", null, 4, null);
        @NotNull
        private static final ContentType PNG = new ContentType("image", ImageType.PNG, null, 4, null);
        @NotNull
        private static final ContentType SVG = new ContentType("image", "svg+xml", null, 4, null);
        @NotNull
        private static final ContentType XIcon = new ContentType("image", "x-icon", null, 4, null);

        private Image() {
        }

        @NotNull
        public final ContentType getAny() {
            return Any;
        }

        @NotNull
        public final ContentType getGIF() {
            return GIF;
        }

        @NotNull
        public final ContentType getJPEG() {
            return JPEG;
        }

        @NotNull
        public final ContentType getPNG() {
            return PNG;
        }

        @NotNull
        public final ContentType getSVG() {
            return SVG;
        }

        @NotNull
        public final ContentType getXIcon() {
            return XIcon;
        }
    }

    /* compiled from: ContentTypes.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/http/ContentType$Message;", "", "()V", KinesisEndpoint.AppState.ANY, "Lio/ktor/http/ContentType;", "getAny", "()Lio/ktor/http/ContentType;", "Http", "getHttp", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Message {
        public static final Message INSTANCE = new Message();
        @NotNull
        private static final ContentType Any = new ContentType("message", "*", null, 4, null);
        @NotNull
        private static final ContentType Http = new ContentType("message", "http", null, 4, null);

        private Message() {
        }

        @NotNull
        public final ContentType getAny() {
            return Any;
        }

        @NotNull
        public final ContentType getHttp() {
            return Http;
        }
    }

    /* compiled from: ContentTypes.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006¨\u0006\u0015"}, d2 = {"Lio/ktor/http/ContentType$MultiPart;", "", "()V", "Alternative", "Lio/ktor/http/ContentType;", "getAlternative", "()Lio/ktor/http/ContentType;", KinesisEndpoint.AppState.ANY, "getAny", "ByteRanges", "getByteRanges", "Encrypted", "getEncrypted", "FormData", "getFormData", "Mixed", "getMixed", "Related", "getRelated", "Signed", "getSigned", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class MultiPart {
        public static final MultiPart INSTANCE = new MultiPart();
        @NotNull
        private static final ContentType Any = new ContentType("multipart", "*", null, 4, null);
        @NotNull
        private static final ContentType Mixed = new ContentType("multipart", "mixed", null, 4, null);
        @NotNull
        private static final ContentType Alternative = new ContentType("multipart", "alternative", null, 4, null);
        @NotNull
        private static final ContentType Related = new ContentType("multipart", "related", null, 4, null);
        @NotNull
        private static final ContentType FormData = new ContentType("multipart", FileUploadBase.FORM_DATA, null, 4, null);
        @NotNull
        private static final ContentType Signed = new ContentType("multipart", "signed", null, 4, null);
        @NotNull
        private static final ContentType Encrypted = new ContentType("multipart", "encrypted", null, 4, null);
        @NotNull
        private static final ContentType ByteRanges = new ContentType("multipart", "byteranges", null, 4, null);

        private MultiPart() {
        }

        @NotNull
        public final ContentType getAlternative() {
            return Alternative;
        }

        @NotNull
        public final ContentType getAny() {
            return Any;
        }

        @NotNull
        public final ContentType getByteRanges() {
            return ByteRanges;
        }

        @NotNull
        public final ContentType getEncrypted() {
            return Encrypted;
        }

        @NotNull
        public final ContentType getFormData() {
            return FormData;
        }

        @NotNull
        public final ContentType getMixed() {
            return Mixed;
        }

        @NotNull
        public final ContentType getRelated() {
            return Related;
        }

        @NotNull
        public final ContentType getSigned() {
            return Signed;
        }
    }

    /* compiled from: ContentTypes.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006¨\u0006\u0017"}, d2 = {"Lio/ktor/http/ContentType$Text;", "", "()V", KinesisEndpoint.AppState.ANY, "Lio/ktor/http/ContentType;", "getAny", "()Lio/ktor/http/ContentType;", "CSS", "getCSS", "CSV", "getCSV", "EventStream", "getEventStream", "Html", "getHtml", "JavaScript", "getJavaScript", "Plain", "getPlain", "VCard", "getVCard", "Xml", "getXml", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Text {
        public static final Text INSTANCE = new Text();
        @NotNull
        private static final ContentType Any = new ContentType("text", "*", null, 4, null);
        @NotNull
        private static final ContentType Plain = new ContentType("text", "plain", null, 4, null);
        @NotNull
        private static final ContentType CSS = new ContentType("text", "css", null, 4, null);
        @NotNull
        private static final ContentType CSV = new ContentType("text", "csv", null, 4, null);
        @NotNull
        private static final ContentType Html = new ContentType("text", "html", null, 4, null);
        @NotNull
        private static final ContentType JavaScript = new ContentType("text", "javascript", null, 4, null);
        @NotNull
        private static final ContentType VCard = new ContentType("text", "vcard", null, 4, null);
        @NotNull
        private static final ContentType Xml = new ContentType("text", "xml", null, 4, null);
        @NotNull
        private static final ContentType EventStream = new ContentType("text", "event-stream", null, 4, null);

        private Text() {
        }

        @NotNull
        public final ContentType getAny() {
            return Any;
        }

        @NotNull
        public final ContentType getCSS() {
            return CSS;
        }

        @NotNull
        public final ContentType getCSV() {
            return CSV;
        }

        @NotNull
        public final ContentType getEventStream() {
            return EventStream;
        }

        @NotNull
        public final ContentType getHtml() {
            return Html;
        }

        @NotNull
        public final ContentType getJavaScript() {
            return JavaScript;
        }

        @NotNull
        public final ContentType getPlain() {
            return Plain;
        }

        @NotNull
        public final ContentType getVCard() {
            return VCard;
        }

        @NotNull
        public final ContentType getXml() {
            return Xml;
        }
    }

    /* compiled from: ContentTypes.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006¨\u0006\u000f"}, d2 = {"Lio/ktor/http/ContentType$Video;", "", "()V", KinesisEndpoint.AppState.ANY, "Lio/ktor/http/ContentType;", "getAny", "()Lio/ktor/http/ContentType;", "MP4", "getMP4", "MPEG", "getMPEG", "OGG", "getOGG", "QuickTime", "getQuickTime", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Video {
        public static final Video INSTANCE = new Video();
        @NotNull
        private static final ContentType Any = new ContentType("video", "*", null, 4, null);
        @NotNull
        private static final ContentType MPEG = new ContentType("video", "mpeg", null, 4, null);
        @NotNull
        private static final ContentType MP4 = new ContentType("video", "mp4", null, 4, null);
        @NotNull
        private static final ContentType OGG = new ContentType("video", "ogg", null, 4, null);
        @NotNull
        private static final ContentType QuickTime = new ContentType("video", "quicktime", null, 4, null);

        private Video() {
        }

        @NotNull
        public final ContentType getAny() {
            return Any;
        }

        @NotNull
        public final ContentType getMP4() {
            return MP4;
        }

        @NotNull
        public final ContentType getMPEG() {
            return MPEG;
        }

        @NotNull
        public final ContentType getOGG() {
            return OGG;
        }

        @NotNull
        public final ContentType getQuickTime() {
            return QuickTime;
        }
    }

    /* synthetic */ ContentType(String str, String str2, String str3, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0046 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final boolean hasParameter(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            java.util.List r0 = r5.getParameters()
            int r0 = r0.size()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L67
            if (r0 == r2) goto L48
            java.util.List r0 = r5.getParameters()
            boolean r3 = r0 instanceof java.util.Collection
            if (r3 == 0) goto L1d
            boolean r3 = r0.isEmpty()
            if (r3 == 0) goto L1d
            goto L67
        L1d:
            java.util.Iterator r0 = r0.iterator()
        L21:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L67
            java.lang.Object r3 = r0.next()
            io.ktor.http.HeaderValueParam r3 = (io.ktor.http.HeaderValueParam) r3
            java.lang.String r4 = r3.getName()
            boolean r4 = kotlin.text.StringsKt.equals(r4, r6, r2)
            if (r4 == 0) goto L43
            java.lang.String r3 = r3.getValue()
            boolean r3 = kotlin.text.StringsKt.equals(r3, r7, r2)
            if (r3 == 0) goto L43
            r3 = r2
            goto L44
        L43:
            r3 = r1
        L44:
            if (r3 == 0) goto L21
        L46:
            r1 = r2
            goto L67
        L48:
            java.util.List r0 = r5.getParameters()
            java.lang.Object r0 = r0.get(r1)
            io.ktor.http.HeaderValueParam r0 = (io.ktor.http.HeaderValueParam) r0
            java.lang.String r3 = r0.getName()
            boolean r6 = kotlin.text.StringsKt.equals(r3, r6, r2)
            if (r6 == 0) goto L67
            java.lang.String r6 = r0.getValue()
            boolean r6 = kotlin.text.StringsKt.equals(r6, r7, r2)
            if (r6 == 0) goto L67
            goto L46
        L67:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.ContentType.hasParameter(java.lang.String, java.lang.String):boolean");
    }

    public boolean equals(@Nullable Object obj) {
        boolean equals;
        boolean equals2;
        if (obj instanceof ContentType) {
            ContentType contentType = (ContentType) obj;
            equals = StringsKt__StringsJVMKt.equals(this.contentType, contentType.contentType, true);
            if (equals) {
                equals2 = StringsKt__StringsJVMKt.equals(this.contentSubtype, contentType.contentSubtype, true);
                if (equals2 && Intrinsics.areEqual(getParameters(), contentType.getParameters())) {
                    return true;
                }
            }
        }
        return false;
    }

    @NotNull
    public final String getContentSubtype() {
        return this.contentSubtype;
    }

    @NotNull
    public final String getContentType() {
        return this.contentType;
    }

    public int hashCode() {
        String str = this.contentType;
        if (str != null) {
            String lowerCase = str.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            int hashCode = lowerCase.hashCode();
            int i = hashCode * 31;
            String str2 = this.contentSubtype;
            if (str2 != null) {
                String lowerCase2 = str2.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase2, "(this as java.lang.String).toLowerCase()");
                return (getParameters().hashCode() * 31) + lowerCase2.hashCode() + i + hashCode;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x00a5, code lost:
        if (r4 != null) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean match(@org.jetbrains.annotations.NotNull io.ktor.http.ContentType r8) {
        /*
            r7 = this;
            java.lang.String r0 = "pattern"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.lang.String r0 = r8.contentType
            java.lang.String r1 = "*"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            r2 = 1
            r0 = r0 ^ r2
            r3 = 0
            if (r0 == 0) goto L1d
            java.lang.String r0 = r8.contentType
            java.lang.String r4 = r7.contentType
            boolean r0 = kotlin.text.StringsKt.equals(r0, r4, r2)
            if (r0 != 0) goto L1d
            return r3
        L1d:
            java.lang.String r0 = r8.contentSubtype
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            r0 = r0 ^ r2
            if (r0 == 0) goto L31
            java.lang.String r0 = r8.contentSubtype
            java.lang.String r4 = r7.contentSubtype
            boolean r0 = kotlin.text.StringsKt.equals(r0, r4, r2)
            if (r0 != 0) goto L31
            return r3
        L31:
            java.util.List r8 = r8.getParameters()
            java.util.Iterator r8 = r8.iterator()
        L39:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto Lb2
            java.lang.Object r0 = r8.next()
            io.ktor.http.HeaderValueParam r0 = (io.ktor.http.HeaderValueParam) r0
            java.lang.String r4 = r0.component1()
            java.lang.String r0 = r0.component2()
            int r5 = r4.hashCode()
            r6 = 42
            if (r5 == r6) goto L56
            goto L94
        L56:
            boolean r5 = r4.equals(r1)
            if (r5 == 0) goto L94
            int r4 = r0.hashCode()
            if (r4 == r6) goto L63
            goto L6a
        L63:
            boolean r4 = r0.equals(r1)
            if (r4 == 0) goto L6a
            goto La7
        L6a:
            java.util.List r4 = r7.getParameters()
            boolean r5 = r4 instanceof java.util.Collection
            if (r5 == 0) goto L79
            boolean r5 = r4.isEmpty()
            if (r5 == 0) goto L79
            goto La9
        L79:
            java.util.Iterator r4 = r4.iterator()
        L7d:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto La9
            java.lang.Object r5 = r4.next()
            io.ktor.http.HeaderValueParam r5 = (io.ktor.http.HeaderValueParam) r5
            java.lang.String r5 = r5.getValue()
            boolean r5 = kotlin.text.StringsKt.equals(r5, r0, r2)
            if (r5 == 0) goto L7d
            goto La7
        L94:
            java.lang.String r4 = r7.parameter(r4)
            int r5 = r0.hashCode()
            if (r5 == r6) goto L9f
            goto Lab
        L9f:
            boolean r5 = r0.equals(r1)
            if (r5 == 0) goto Lab
            if (r4 == 0) goto La9
        La7:
            r0 = r2
            goto Laf
        La9:
            r0 = r3
            goto Laf
        Lab:
            boolean r0 = kotlin.text.StringsKt.equals(r4, r0, r2)
        Laf:
            if (r0 != 0) goto L39
            return r3
        Lb2:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.ContentType.match(io.ktor.http.ContentType):boolean");
    }

    @NotNull
    public final ContentType withParameter(@NotNull String name, @NotNull String value) {
        List plus;
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(value, "value");
        if (hasParameter(name, value)) {
            return this;
        }
        String str = this.contentType;
        String str2 = this.contentSubtype;
        String content = getContent();
        plus = CollectionsKt___CollectionsKt.plus((Collection<? extends Object>) ((Collection) getParameters()), (Object) new HeaderValueParam(name, value));
        return new ContentType(str, str2, content, plus);
    }

    @NotNull
    public final ContentType withoutParameters() {
        return new ContentType(this.contentType, this.contentSubtype, null, 4, null);
    }

    private ContentType(String str, String str2, String str3, List<HeaderValueParam> list) {
        super(str3, list);
        this.contentType = str;
        this.contentSubtype = str2;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ContentType(@NotNull String contentType, @NotNull String contentSubtype, @NotNull List<HeaderValueParam> parameters) {
        this(contentType, contentSubtype, contentType + '/' + contentSubtype, parameters);
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        Intrinsics.checkParameterIsNotNull(contentSubtype, "contentSubtype");
        Intrinsics.checkParameterIsNotNull(parameters, "parameters");
    }

    public /* synthetic */ ContentType(String str, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }

    public final boolean match(@NotNull String pattern) {
        Intrinsics.checkParameterIsNotNull(pattern, "pattern");
        return match(Companion.parse(pattern));
    }
}
