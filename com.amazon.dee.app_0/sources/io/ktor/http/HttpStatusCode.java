package io.ktor.http;

import com.amazon.alexa.comms.mediaInsights.service.transport.HttpRetryHelper;
import com.amazon.clouddrive.internal.CloudDriveExceptionBuilder;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.apache.logging.log4j.util.Chars;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: HttpStatusCode.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0005J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\b\u0010\u0012\u001a\u00020\u0005H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lio/ktor/http/HttpStatusCode;", "", "value", "", "description", "", "(ILjava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getValue", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpStatusCode {
    private static final HttpStatusCode[] byValue;
    @NotNull
    private final String description;
    private final int value;
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final HttpStatusCode Continue = new HttpStatusCode(100, "Continue");
    @NotNull
    private static final HttpStatusCode SwitchingProtocols = new HttpStatusCode(101, "Switching Protocols");
    @NotNull
    private static final HttpStatusCode Processing = new HttpStatusCode(102, "Processing");
    @NotNull
    private static final HttpStatusCode OK = new HttpStatusCode(200, "OK");
    @NotNull
    private static final HttpStatusCode Created = new HttpStatusCode(201, "Created");
    @NotNull
    private static final HttpStatusCode Accepted = new HttpStatusCode(202, "Accepted");
    @NotNull
    private static final HttpStatusCode NonAuthoritativeInformation = new HttpStatusCode(203, "Non-Authoritative Information");
    @NotNull
    private static final HttpStatusCode NoContent = new HttpStatusCode(204, "No Content");
    @NotNull
    private static final HttpStatusCode ResetContent = new HttpStatusCode(205, "Reset Content");
    @NotNull
    private static final HttpStatusCode PartialContent = new HttpStatusCode(HttpServletResponse.SC_PARTIAL_CONTENT, "Partial Content");
    @NotNull
    private static final HttpStatusCode MultiStatus = new HttpStatusCode(207, "Multi-Status");
    @NotNull
    private static final HttpStatusCode MultipleChoices = new HttpStatusCode(300, "Multiple Choices");
    @NotNull
    private static final HttpStatusCode MovedPermanently = new HttpStatusCode(301, "Moved Permanently");
    @NotNull
    private static final HttpStatusCode Found = new HttpStatusCode(302, "Found");
    @NotNull
    private static final HttpStatusCode SeeOther = new HttpStatusCode(303, "See Other");
    @NotNull
    private static final HttpStatusCode NotModified = new HttpStatusCode(304, "Not Modified");
    @NotNull
    private static final HttpStatusCode UseProxy = new HttpStatusCode(305, "Use Proxy");
    @NotNull
    private static final HttpStatusCode SwitchProxy = new HttpStatusCode(306, "Switch Proxy");
    @NotNull
    private static final HttpStatusCode TemporaryRedirect = new HttpStatusCode(307, "Temporary Redirect");
    @NotNull
    private static final HttpStatusCode PermanentRedirect = new HttpStatusCode(308, "Permanent Redirect");
    @NotNull
    private static final HttpStatusCode BadRequest = new HttpStatusCode(400, "Bad Request");
    @NotNull
    private static final HttpStatusCode Unauthorized = new HttpStatusCode(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    @NotNull
    private static final HttpStatusCode PaymentRequired = new HttpStatusCode(HttpServletResponse.SC_PAYMENT_REQUIRED, "Payment Required");
    @NotNull
    private static final HttpStatusCode Forbidden = new HttpStatusCode(403, "Forbidden");
    @NotNull
    private static final HttpStatusCode NotFound = new HttpStatusCode(404, "Not Found");
    @NotNull
    private static final HttpStatusCode MethodNotAllowed = new HttpStatusCode(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "Method Not Allowed");
    @NotNull
    private static final HttpStatusCode NotAcceptable = new HttpStatusCode(HttpServletResponse.SC_NOT_ACCEPTABLE, "Not Acceptable");
    @NotNull
    private static final HttpStatusCode ProxyAuthenticationRequired = new HttpStatusCode(HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED, "Proxy Authentication Required");
    @NotNull
    private static final HttpStatusCode RequestTimeout = new HttpStatusCode(408, "Request Timeout");
    @NotNull
    private static final HttpStatusCode Conflict = new HttpStatusCode(HttpServletResponse.SC_CONFLICT, "Conflict");
    @NotNull
    private static final HttpStatusCode Gone = new HttpStatusCode(HttpServletResponse.SC_GONE, "Gone");
    @NotNull
    private static final HttpStatusCode LengthRequired = new HttpStatusCode(411, "Length Required");
    @NotNull
    private static final HttpStatusCode PreconditionFailed = new HttpStatusCode(412, "Precondition Failed");
    @NotNull
    private static final HttpStatusCode PayloadTooLarge = new HttpStatusCode(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE, "Payload Too Large");
    @NotNull
    private static final HttpStatusCode RequestURITooLong = new HttpStatusCode(HttpServletResponse.SC_REQUEST_URI_TOO_LONG, "Request-URI Too Long");
    @NotNull
    private static final HttpStatusCode UnsupportedMediaType = new HttpStatusCode(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Unsupported Media Type");
    @NotNull
    private static final HttpStatusCode RequestedRangeNotSatisfiable = new HttpStatusCode(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE, "Requested Range Not Satisfiable");
    @NotNull
    private static final HttpStatusCode ExpectationFailed = new HttpStatusCode(HttpServletResponse.SC_EXPECTATION_FAILED, "Expectation Failed");
    @NotNull
    private static final HttpStatusCode UnprocessableEntity = new HttpStatusCode(422, "Unprocessable Entity");
    @NotNull
    private static final HttpStatusCode Locked = new HttpStatusCode(423, "Locked");
    @NotNull
    private static final HttpStatusCode FailedDependency = new HttpStatusCode(424, "Failed Dependency");
    @NotNull
    private static final HttpStatusCode UpgradeRequired = new HttpStatusCode(426, "Upgrade Required");
    @NotNull
    private static final HttpStatusCode TooManyRequests = new HttpStatusCode(CloudDriveExceptionBuilder.TOO_MANY_REQUEST_CODE, "Too Many Requests");
    @NotNull
    private static final HttpStatusCode RequestHeaderFieldTooLarge = new HttpStatusCode(HttpRetryHelper.UHE_HTTP_CODE, "Request Header Fields Too Large");
    @NotNull
    private static final HttpStatusCode InternalServerError = new HttpStatusCode(500, "Internal Server Error");
    @NotNull
    private static final HttpStatusCode NotImplemented = new HttpStatusCode(501, "Not Implemented");
    @NotNull
    private static final HttpStatusCode BadGateway = new HttpStatusCode(502, "Bad Gateway");
    @NotNull
    private static final HttpStatusCode ServiceUnavailable = new HttpStatusCode(503, "Service Unavailable");
    @NotNull
    private static final HttpStatusCode GatewayTimeout = new HttpStatusCode(504, "Gateway Timeout");
    @NotNull
    private static final HttpStatusCode VersionNotSupported = new HttpStatusCode(HttpServletResponse.SC_HTTP_VERSION_NOT_SUPPORTED, "HTTP Version Not Supported");
    @NotNull
    private static final HttpStatusCode VariantAlsoNegotiates = new HttpStatusCode(506, "Variant Also Negotiates");
    @NotNull
    private static final HttpStatusCode InsufficientStorage = new HttpStatusCode(507, "Insufficient Storage");
    @NotNull
    private static final List<HttpStatusCode> allStatusCodes = HttpStatusCodeKt.allStatusCodes();

    /* compiled from: HttpStatusCode.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\bi\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010t\u001a\u00020\u00042\u0006\u0010u\u001a\u00020vR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0011\u0010\u000f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0011\u0010\u0019\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0011\u0010\u001b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0011\u0010\u001d\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0011\u0010\u001f\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0011\u0010!\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0011\u0010#\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0011\u0010%\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0011\u0010'\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u0011\u0010)\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0006R\u0011\u0010+\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0011\u0010-\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u0011\u0010/\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0006R\u0011\u00101\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0006R\u0011\u00103\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0006R\u0011\u00105\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0006R\u0011\u00107\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0006R\u0011\u00109\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0006R\u0011\u0010;\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0006R\u0011\u0010=\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0006R\u0011\u0010?\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0006R\u0011\u0010A\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0006R\u0011\u0010C\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0006R\u0011\u0010E\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0006R\u0011\u0010G\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0006R\u0011\u0010I\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u0006R\u0011\u0010K\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\u0006R\u0011\u0010M\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\u0006R\u0011\u0010O\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0006R\u0011\u0010Q\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0006R\u0011\u0010S\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010\u0006R\u0011\u0010U\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\u0006R\u0011\u0010W\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010\u0006R\u0011\u0010Y\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0006R\u0011\u0010[\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010\u0006R\u0011\u0010]\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u0006R\u0011\u0010_\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u0006R\u0011\u0010a\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u0006R\u0011\u0010c\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0006R\u0011\u0010e\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0006R\u0011\u0010g\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\u0006R\u0011\u0010i\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0006R\u0011\u0010k\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010\u0006R\u0017\u0010m\u001a\b\u0012\u0004\u0012\u00020\u00040n¢\u0006\b\n\u0000\u001a\u0004\bo\u0010pR\u0018\u0010q\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040rX\u0082\u0004¢\u0006\u0004\n\u0002\u0010s¨\u0006w"}, d2 = {"Lio/ktor/http/HttpStatusCode$Companion;", "", "()V", "Accepted", "Lio/ktor/http/HttpStatusCode;", "getAccepted", "()Lio/ktor/http/HttpStatusCode;", "BadGateway", "getBadGateway", "BadRequest", "getBadRequest", "Conflict", "getConflict", "Continue", "getContinue", "Created", "getCreated", "ExpectationFailed", "getExpectationFailed", "FailedDependency", "getFailedDependency", "Forbidden", "getForbidden", "Found", "getFound", "GatewayTimeout", "getGatewayTimeout", "Gone", "getGone", "InsufficientStorage", "getInsufficientStorage", "InternalServerError", "getInternalServerError", "LengthRequired", "getLengthRequired", "Locked", "getLocked", "MethodNotAllowed", "getMethodNotAllowed", "MovedPermanently", "getMovedPermanently", "MultiStatus", "getMultiStatus", "MultipleChoices", "getMultipleChoices", "NoContent", "getNoContent", "NonAuthoritativeInformation", "getNonAuthoritativeInformation", "NotAcceptable", "getNotAcceptable", "NotFound", "getNotFound", "NotImplemented", "getNotImplemented", "NotModified", "getNotModified", "OK", "getOK", "PartialContent", "getPartialContent", "PayloadTooLarge", "getPayloadTooLarge", "PaymentRequired", "getPaymentRequired", "PermanentRedirect", "getPermanentRedirect", "PreconditionFailed", "getPreconditionFailed", "Processing", "getProcessing", "ProxyAuthenticationRequired", "getProxyAuthenticationRequired", "RequestHeaderFieldTooLarge", "getRequestHeaderFieldTooLarge", "RequestTimeout", "getRequestTimeout", "RequestURITooLong", "getRequestURITooLong", "RequestedRangeNotSatisfiable", "getRequestedRangeNotSatisfiable", "ResetContent", "getResetContent", "SeeOther", "getSeeOther", "ServiceUnavailable", "getServiceUnavailable", "SwitchProxy", "getSwitchProxy", "SwitchingProtocols", "getSwitchingProtocols", "TemporaryRedirect", "getTemporaryRedirect", "TooManyRequests", "getTooManyRequests", "Unauthorized", "getUnauthorized", "UnprocessableEntity", "getUnprocessableEntity", "UnsupportedMediaType", "getUnsupportedMediaType", "UpgradeRequired", "getUpgradeRequired", "UseProxy", "getUseProxy", "VariantAlsoNegotiates", "getVariantAlsoNegotiates", "VersionNotSupported", "getVersionNotSupported", "allStatusCodes", "", "getAllStatusCodes", "()Ljava/util/List;", "byValue", "", "[Lio/ktor/http/HttpStatusCode;", "fromValue", "value", "", "ktor-http"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final HttpStatusCode fromValue(int i) {
            HttpStatusCode httpStatusCode = (1 <= i && 1000 > i) ? HttpStatusCode.byValue[i] : null;
            return httpStatusCode != null ? httpStatusCode : new HttpStatusCode(i, "Unknown Status Code");
        }

        @NotNull
        public final HttpStatusCode getAccepted() {
            return HttpStatusCode.Accepted;
        }

        @NotNull
        public final List<HttpStatusCode> getAllStatusCodes() {
            return HttpStatusCode.allStatusCodes;
        }

        @NotNull
        public final HttpStatusCode getBadGateway() {
            return HttpStatusCode.BadGateway;
        }

        @NotNull
        public final HttpStatusCode getBadRequest() {
            return HttpStatusCode.BadRequest;
        }

        @NotNull
        public final HttpStatusCode getConflict() {
            return HttpStatusCode.Conflict;
        }

        @NotNull
        public final HttpStatusCode getContinue() {
            return HttpStatusCode.Continue;
        }

        @NotNull
        public final HttpStatusCode getCreated() {
            return HttpStatusCode.Created;
        }

        @NotNull
        public final HttpStatusCode getExpectationFailed() {
            return HttpStatusCode.ExpectationFailed;
        }

        @NotNull
        public final HttpStatusCode getFailedDependency() {
            return HttpStatusCode.FailedDependency;
        }

        @NotNull
        public final HttpStatusCode getForbidden() {
            return HttpStatusCode.Forbidden;
        }

        @NotNull
        public final HttpStatusCode getFound() {
            return HttpStatusCode.Found;
        }

        @NotNull
        public final HttpStatusCode getGatewayTimeout() {
            return HttpStatusCode.GatewayTimeout;
        }

        @NotNull
        public final HttpStatusCode getGone() {
            return HttpStatusCode.Gone;
        }

        @NotNull
        public final HttpStatusCode getInsufficientStorage() {
            return HttpStatusCode.InsufficientStorage;
        }

        @NotNull
        public final HttpStatusCode getInternalServerError() {
            return HttpStatusCode.InternalServerError;
        }

        @NotNull
        public final HttpStatusCode getLengthRequired() {
            return HttpStatusCode.LengthRequired;
        }

        @NotNull
        public final HttpStatusCode getLocked() {
            return HttpStatusCode.Locked;
        }

        @NotNull
        public final HttpStatusCode getMethodNotAllowed() {
            return HttpStatusCode.MethodNotAllowed;
        }

        @NotNull
        public final HttpStatusCode getMovedPermanently() {
            return HttpStatusCode.MovedPermanently;
        }

        @NotNull
        public final HttpStatusCode getMultiStatus() {
            return HttpStatusCode.MultiStatus;
        }

        @NotNull
        public final HttpStatusCode getMultipleChoices() {
            return HttpStatusCode.MultipleChoices;
        }

        @NotNull
        public final HttpStatusCode getNoContent() {
            return HttpStatusCode.NoContent;
        }

        @NotNull
        public final HttpStatusCode getNonAuthoritativeInformation() {
            return HttpStatusCode.NonAuthoritativeInformation;
        }

        @NotNull
        public final HttpStatusCode getNotAcceptable() {
            return HttpStatusCode.NotAcceptable;
        }

        @NotNull
        public final HttpStatusCode getNotFound() {
            return HttpStatusCode.NotFound;
        }

        @NotNull
        public final HttpStatusCode getNotImplemented() {
            return HttpStatusCode.NotImplemented;
        }

        @NotNull
        public final HttpStatusCode getNotModified() {
            return HttpStatusCode.NotModified;
        }

        @NotNull
        public final HttpStatusCode getOK() {
            return HttpStatusCode.OK;
        }

        @NotNull
        public final HttpStatusCode getPartialContent() {
            return HttpStatusCode.PartialContent;
        }

        @NotNull
        public final HttpStatusCode getPayloadTooLarge() {
            return HttpStatusCode.PayloadTooLarge;
        }

        @NotNull
        public final HttpStatusCode getPaymentRequired() {
            return HttpStatusCode.PaymentRequired;
        }

        @NotNull
        public final HttpStatusCode getPermanentRedirect() {
            return HttpStatusCode.PermanentRedirect;
        }

        @NotNull
        public final HttpStatusCode getPreconditionFailed() {
            return HttpStatusCode.PreconditionFailed;
        }

        @NotNull
        public final HttpStatusCode getProcessing() {
            return HttpStatusCode.Processing;
        }

        @NotNull
        public final HttpStatusCode getProxyAuthenticationRequired() {
            return HttpStatusCode.ProxyAuthenticationRequired;
        }

        @NotNull
        public final HttpStatusCode getRequestHeaderFieldTooLarge() {
            return HttpStatusCode.RequestHeaderFieldTooLarge;
        }

        @NotNull
        public final HttpStatusCode getRequestTimeout() {
            return HttpStatusCode.RequestTimeout;
        }

        @NotNull
        public final HttpStatusCode getRequestURITooLong() {
            return HttpStatusCode.RequestURITooLong;
        }

        @NotNull
        public final HttpStatusCode getRequestedRangeNotSatisfiable() {
            return HttpStatusCode.RequestedRangeNotSatisfiable;
        }

        @NotNull
        public final HttpStatusCode getResetContent() {
            return HttpStatusCode.ResetContent;
        }

        @NotNull
        public final HttpStatusCode getSeeOther() {
            return HttpStatusCode.SeeOther;
        }

        @NotNull
        public final HttpStatusCode getServiceUnavailable() {
            return HttpStatusCode.ServiceUnavailable;
        }

        @NotNull
        public final HttpStatusCode getSwitchProxy() {
            return HttpStatusCode.SwitchProxy;
        }

        @NotNull
        public final HttpStatusCode getSwitchingProtocols() {
            return HttpStatusCode.SwitchingProtocols;
        }

        @NotNull
        public final HttpStatusCode getTemporaryRedirect() {
            return HttpStatusCode.TemporaryRedirect;
        }

        @NotNull
        public final HttpStatusCode getTooManyRequests() {
            return HttpStatusCode.TooManyRequests;
        }

        @NotNull
        public final HttpStatusCode getUnauthorized() {
            return HttpStatusCode.Unauthorized;
        }

        @NotNull
        public final HttpStatusCode getUnprocessableEntity() {
            return HttpStatusCode.UnprocessableEntity;
        }

        @NotNull
        public final HttpStatusCode getUnsupportedMediaType() {
            return HttpStatusCode.UnsupportedMediaType;
        }

        @NotNull
        public final HttpStatusCode getUpgradeRequired() {
            return HttpStatusCode.UpgradeRequired;
        }

        @NotNull
        public final HttpStatusCode getUseProxy() {
            return HttpStatusCode.UseProxy;
        }

        @NotNull
        public final HttpStatusCode getVariantAlsoNegotiates() {
            return HttpStatusCode.VariantAlsoNegotiates;
        }

        @NotNull
        public final HttpStatusCode getVersionNotSupported() {
            return HttpStatusCode.VersionNotSupported;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        Object obj;
        boolean z;
        HttpStatusCode[] httpStatusCodeArr = new HttpStatusCode[1000];
        int length = httpStatusCodeArr.length;
        for (int i = 0; i < length; i++) {
            Iterator<T> it2 = allStatusCodes.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it2.next();
                if (((HttpStatusCode) obj).value == i) {
                    z = true;
                    continue;
                } else {
                    z = false;
                    continue;
                }
                if (z) {
                    break;
                }
            }
            httpStatusCodeArr[i] = (HttpStatusCode) obj;
        }
        byValue = httpStatusCodeArr;
    }

    public HttpStatusCode(int i, @NotNull String description) {
        Intrinsics.checkParameterIsNotNull(description, "description");
        this.value = i;
        this.description = description;
    }

    @NotNull
    public static /* synthetic */ HttpStatusCode copy$default(HttpStatusCode httpStatusCode, int i, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = httpStatusCode.value;
        }
        if ((i2 & 2) != 0) {
            str = httpStatusCode.description;
        }
        return httpStatusCode.copy(i, str);
    }

    public final int component1() {
        return this.value;
    }

    @NotNull
    public final String component2() {
        return this.description;
    }

    @NotNull
    public final HttpStatusCode copy(int i, @NotNull String description) {
        Intrinsics.checkParameterIsNotNull(description, "description");
        return new HttpStatusCode(i, description);
    }

    @NotNull
    public final HttpStatusCode description(@NotNull String value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        return copy$default(this, 0, value, 1, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof HttpStatusCode) {
                HttpStatusCode httpStatusCode = (HttpStatusCode) obj;
                if (!(this.value == httpStatusCode.value) || !Intrinsics.areEqual(this.description, httpStatusCode.description)) {
                }
            }
            return false;
        }
        return true;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    public final int getValue() {
        return this.value;
    }

    public int hashCode() {
        int i = this.value * 31;
        String str = this.description;
        return i + (str != null ? str.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return this.value + Chars.SPACE + this.description;
    }
}
