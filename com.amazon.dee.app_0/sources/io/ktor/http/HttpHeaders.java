package io.ktor.http;

import com.amazon.alexa.accessory.metrics.MetricsConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: HttpHeaders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000%\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0003\b§\u0001\n\u0002\u0010\u0011\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010Ê\u0001\u001a\u00030Ë\u00012\u0007\u0010Ì\u0001\u001a\u00020\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u0014\u0010\u001b\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0006R\u0014\u0010\u001d\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0006R\u0014\u0010\u001f\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0006R\u0014\u0010!\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0006R\u0014\u0010#\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0006R\u0014\u0010%\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0006R\u0014\u0010'\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0006R\u0014\u0010)\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0006R\u0014\u0010+\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0006R\u0014\u0010-\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0006R\u0014\u0010/\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0006R\u0014\u00101\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0006R\u0014\u00103\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0006R\u0014\u00105\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0006R\u0014\u00107\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0006R\u0014\u00109\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0006R\u0014\u0010;\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0006R\u0014\u0010=\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0006R\u0014\u0010?\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0006R\u0014\u0010A\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0006R\u0014\u0010C\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0006R\u0014\u0010E\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0006R\u0014\u0010G\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0006R\u0014\u0010I\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u0006R\u0014\u0010K\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\u0006R\u0014\u0010M\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\u0006R\u0014\u0010O\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0006R\u0014\u0010Q\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0006R\u0014\u0010S\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bT\u0010\u0006R\u0014\u0010U\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\u0006R\u0014\u0010W\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bX\u0010\u0006R\u0014\u0010Y\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0006R\u0014\u0010[\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\\\u0010\u0006R\u0014\u0010]\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u0006R\u0014\u0010_\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u0006R\u0014\u0010a\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u0006R\u0014\u0010c\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bd\u0010\u0006R\u0014\u0010e\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0006R\u0014\u0010g\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bh\u0010\u0006R\u0014\u0010i\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0006R\u0014\u0010k\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bl\u0010\u0006R\u0014\u0010m\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0006R\u0014\u0010o\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bp\u0010\u0006R\u0014\u0010q\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\br\u0010\u0006R\u0014\u0010s\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bt\u0010\u0006R\u0014\u0010u\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bv\u0010\u0006R\u0014\u0010w\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bx\u0010\u0006R\u0014\u0010y\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\bz\u0010\u0006R\u0014\u0010{\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b|\u0010\u0006R\u0014\u0010}\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b~\u0010\u0006R\u0015\u0010\u007f\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0080\u0001\u0010\u0006R\u0016\u0010\u0081\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u0006R\u0016\u0010\u0083\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0084\u0001\u0010\u0006R\u0016\u0010\u0085\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010\u0006R\u0016\u0010\u0087\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0088\u0001\u0010\u0006R\u0016\u0010\u0089\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u008a\u0001\u0010\u0006R\u0016\u0010\u008b\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u008c\u0001\u0010\u0006R\u0016\u0010\u008d\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u008e\u0001\u0010\u0006R\u0016\u0010\u008f\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0090\u0001\u0010\u0006R\u0016\u0010\u0091\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0092\u0001\u0010\u0006R\u0016\u0010\u0093\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0094\u0001\u0010\u0006R\u0016\u0010\u0095\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0096\u0001\u0010\u0006R\u0016\u0010\u0097\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u0098\u0001\u0010\u0006R\u0016\u0010\u0099\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u009a\u0001\u0010\u0006R\u0016\u0010\u009b\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u009c\u0001\u0010\u0006R\u0016\u0010\u009d\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b\u009e\u0001\u0010\u0006R\u0016\u0010\u009f\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b \u0001\u0010\u0006R\u0016\u0010¡\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¢\u0001\u0010\u0006R\u0016\u0010£\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¤\u0001\u0010\u0006R\u0016\u0010¥\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¦\u0001\u0010\u0006R\u0016\u0010§\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¨\u0001\u0010\u0006R\u0016\u0010©\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bª\u0001\u0010\u0006R\u001e\u0010«\u0001\u001a\t\u0012\u0004\u0012\u00020\u00040¬\u0001¢\u0006\r\n\u0003\u0010¯\u0001\u001a\u0006\b\u00ad\u0001\u0010®\u0001R\u0016\u0010°\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b±\u0001\u0010\u0006R\u0016\u0010²\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b³\u0001\u0010\u0006R\u0016\u0010´\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bµ\u0001\u0010\u0006R\u0016\u0010¶\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b·\u0001\u0010\u0006R\u0016\u0010¸\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¹\u0001\u0010\u0006R\u0016\u0010º\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b»\u0001\u0010\u0006R\u0016\u0010¼\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b½\u0001\u0010\u0006R\u0016\u0010¾\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\b¿\u0001\u0010\u0006R\u0016\u0010À\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÁ\u0001\u0010\u0006R\u0016\u0010Â\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÃ\u0001\u0010\u0006R\u0016\u0010Ä\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÅ\u0001\u0010\u0006R\u0016\u0010Æ\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÇ\u0001\u0010\u0006R\u0016\u0010È\u0001\u001a\u00020\u0004X\u0086D¢\u0006\t\n\u0000\u001a\u0005\bÉ\u0001\u0010\u0006¨\u0006Í\u0001"}, d2 = {"Lio/ktor/http/HttpHeaders;", "", "()V", "ALPN", "", "getALPN", "()Ljava/lang/String;", "Accept", "getAccept", "AcceptCharset", "getAcceptCharset", "AcceptEncoding", "getAcceptEncoding", "AcceptLanguage", "getAcceptLanguage", "AcceptRanges", "getAcceptRanges", "AccessControlAllowCredentials", "getAccessControlAllowCredentials", "AccessControlAllowHeaders", "getAccessControlAllowHeaders", "AccessControlAllowMethods", "getAccessControlAllowMethods", "AccessControlAllowOrigin", "getAccessControlAllowOrigin", "AccessControlExposeHeaders", "getAccessControlExposeHeaders", "AccessControlMaxAge", "getAccessControlMaxAge", "AccessControlRequestHeaders", "getAccessControlRequestHeaders", "AccessControlRequestMethod", "getAccessControlRequestMethod", com.google.common.net.HttpHeaders.AGE, "getAge", "Allow", "getAllow", "AuthenticationInfo", "getAuthenticationInfo", "Authorization", "getAuthorization", "CacheControl", "getCacheControl", "Connection", "getConnection", "ContentDisposition", "getContentDisposition", "ContentEncoding", "getContentEncoding", "ContentLanguage", "getContentLanguage", "ContentLength", "getContentLength", "ContentLocation", "getContentLocation", "ContentRange", "getContentRange", "ContentType", "getContentType", "Cookie", "getCookie", "DASL", "getDASL", "DAV", "getDAV", "Date", "getDate", "Depth", "getDepth", "Destination", "getDestination", "ETag", "getETag", "Expect", "getExpect", "Expires", "getExpires", com.google.common.net.HttpHeaders.FORWARDED, "getForwarded", com.google.common.net.HttpHeaders.FROM, "getFrom", "HTTP2Settings", "getHTTP2Settings", "Host", "getHost", "If", "getIf", "IfMatch", "getIfMatch", "IfModifiedSince", "getIfModifiedSince", "IfNoneMatch", "getIfNoneMatch", "IfRange", "getIfRange", "IfScheduleTagMatch", "getIfScheduleTagMatch", "IfUnmodifiedSince", "getIfUnmodifiedSince", "LastModified", "getLastModified", com.google.common.net.HttpHeaders.LINK, "getLink", "Location", "getLocation", "LockToken", "getLockToken", "MIMEVersion", "getMIMEVersion", "MaxForwards", "getMaxForwards", "OrderingType", "getOrderingType", com.google.common.net.HttpHeaders.ORIGIN, "getOrigin", "Overwrite", "getOverwrite", "Position", "getPosition", com.google.common.net.HttpHeaders.PRAGMA, "getPragma", "Prefer", "getPrefer", "PreferenceApplied", "getPreferenceApplied", "ProxyAuthenticate", "getProxyAuthenticate", "ProxyAuthenticationInfo", "getProxyAuthenticationInfo", "ProxyAuthorization", "getProxyAuthorization", "PublicKeyPins", "getPublicKeyPins", "PublicKeyPinsReportOnly", "getPublicKeyPinsReportOnly", "Range", "getRange", "Referrer", "getReferrer", "RetryAfter", "getRetryAfter", "SLUG", "getSLUG", "ScheduleReply", "getScheduleReply", "ScheduleTag", "getScheduleTag", "SecWebSocketAccept", "getSecWebSocketAccept", "SecWebSocketExtensions", "getSecWebSocketExtensions", "SecWebSocketKey", "getSecWebSocketKey", "SecWebSocketProtocol", "getSecWebSocketProtocol", "SecWebSocketVersion", "getSecWebSocketVersion", "Server", "getServer", "SetCookie", "getSetCookie", "StrictTransportSecurity", "getStrictTransportSecurity", com.google.common.net.HttpHeaders.TE, "getTE", MetricsConstants.KeyExchange.STATE_TRANSITION_TIMEOUT, "getTimeout", com.google.common.net.HttpHeaders.TRAILER, "getTrailer", "TransferEncoding", "getTransferEncoding", "UnsafeHeaders", "", "getUnsafeHeaders", "()[Ljava/lang/String;", "[Ljava/lang/String;", com.google.common.net.HttpHeaders.UPGRADE, "getUpgrade", "UserAgent", "getUserAgent", com.google.common.net.HttpHeaders.VARY, "getVary", com.google.common.net.HttpHeaders.VIA, "getVia", "WWWAuthenticate", "getWWWAuthenticate", com.google.common.net.HttpHeaders.WARNING, "getWarning", "XCorrelationId", "getXCorrelationId", "XForwardedFor", "getXForwardedFor", "XForwardedHost", "getXForwardedHost", "XForwardedProto", "getXForwardedProto", "XForwardedServer", "getXForwardedServer", "XHttpMethodOverride", "getXHttpMethodOverride", "XRequestId", "getXRequestId", "isUnsafe", "", "header", "ktor-http"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public final class HttpHeaders {
    public static final HttpHeaders INSTANCE = new HttpHeaders();
    @NotNull
    private static final String Accept = "Accept";
    @NotNull
    private static final String AcceptCharset = com.google.common.net.HttpHeaders.ACCEPT_CHARSET;
    @NotNull
    private static final String AcceptEncoding = "Accept-Encoding";
    @NotNull
    private static final String AcceptLanguage = "Accept-Language";
    @NotNull
    private static final String AcceptRanges = com.google.common.net.HttpHeaders.ACCEPT_RANGES;
    @NotNull
    private static final String Age = com.google.common.net.HttpHeaders.AGE;
    @NotNull
    private static final String Allow = "Allow";
    @NotNull
    private static final String ALPN = "ALPN";
    @NotNull
    private static final String AuthenticationInfo = "Authentication-Info";
    @NotNull
    private static final String Authorization = "Authorization";
    @NotNull
    private static final String CacheControl = "Cache-Control";
    @NotNull
    private static final String Connection = "Connection";
    @NotNull
    private static final String ContentDisposition = "Content-Disposition";
    @NotNull
    private static final String ContentEncoding = "Content-Encoding";
    @NotNull
    private static final String ContentLanguage = "Content-Language";
    @NotNull
    private static final String ContentLength = "Content-Length";
    @NotNull
    private static final String ContentLocation = com.google.common.net.HttpHeaders.CONTENT_LOCATION;
    @NotNull
    private static final String ContentRange = "Content-Range";
    @NotNull
    private static final String ContentType = "Content-Type";
    @NotNull
    private static final String Cookie = "Cookie";
    @NotNull
    private static final String DASL = "DASL";
    @NotNull
    private static final String Date = "Date";
    @NotNull
    private static final String DAV = "DAV";
    @NotNull
    private static final String Depth = "Depth";
    @NotNull
    private static final String Destination = "Destination";
    @NotNull
    private static final String ETag = "ETag";
    @NotNull
    private static final String Expect = "Expect";
    @NotNull
    private static final String Expires = "Expires";
    @NotNull
    private static final String From = com.google.common.net.HttpHeaders.FROM;
    @NotNull
    private static final String Forwarded = com.google.common.net.HttpHeaders.FORWARDED;
    @NotNull
    private static final String Host = "Host";
    @NotNull
    private static final String HTTP2Settings = com.google.common.net.HttpHeaders.HTTP2_SETTINGS;
    @NotNull
    private static final String If = "If";
    @NotNull
    private static final String IfMatch = "If-Match";
    @NotNull
    private static final String IfModifiedSince = "If-Modified-Since";
    @NotNull
    private static final String IfNoneMatch = "If-None-Match";
    @NotNull
    private static final String IfRange = com.google.common.net.HttpHeaders.IF_RANGE;
    @NotNull
    private static final String IfScheduleTagMatch = "If-Schedule-Tag-Match";
    @NotNull
    private static final String IfUnmodifiedSince = "If-Unmodified-Since";
    @NotNull
    private static final String LastModified = "Last-Modified";
    @NotNull
    private static final String Location = "Location";
    @NotNull
    private static final String LockToken = "Lock-Token";
    @NotNull
    private static final String Link = com.google.common.net.HttpHeaders.LINK;
    @NotNull
    private static final String MaxForwards = com.google.common.net.HttpHeaders.MAX_FORWARDS;
    @NotNull
    private static final String MIMEVersion = "MIME-Version";
    @NotNull
    private static final String OrderingType = "Ordering-Type";
    @NotNull
    private static final String Origin = com.google.common.net.HttpHeaders.ORIGIN;
    @NotNull
    private static final String Overwrite = "Overwrite";
    @NotNull
    private static final String Position = "Position";
    @NotNull
    private static final String Pragma = com.google.common.net.HttpHeaders.PRAGMA;
    @NotNull
    private static final String Prefer = "Prefer";
    @NotNull
    private static final String PreferenceApplied = "Preference-Applied";
    @NotNull
    private static final String ProxyAuthenticate = com.google.common.net.HttpHeaders.PROXY_AUTHENTICATE;
    @NotNull
    private static final String ProxyAuthenticationInfo = "Proxy-Authentication-Info";
    @NotNull
    private static final String ProxyAuthorization = com.google.common.net.HttpHeaders.PROXY_AUTHORIZATION;
    @NotNull
    private static final String PublicKeyPins = com.google.common.net.HttpHeaders.PUBLIC_KEY_PINS;
    @NotNull
    private static final String PublicKeyPinsReportOnly = com.google.common.net.HttpHeaders.PUBLIC_KEY_PINS_REPORT_ONLY;
    @NotNull
    private static final String Range = "Range";
    @NotNull
    private static final String Referrer = com.google.common.net.HttpHeaders.REFERER;
    @NotNull
    private static final String RetryAfter = com.google.common.net.HttpHeaders.RETRY_AFTER;
    @NotNull
    private static final String ScheduleReply = "Schedule-Reply";
    @NotNull
    private static final String ScheduleTag = "Schedule-Tag";
    @NotNull
    private static final String SecWebSocketAccept = com.google.common.net.HttpHeaders.SEC_WEBSOCKET_ACCEPT;
    @NotNull
    private static final String SecWebSocketExtensions = com.google.common.net.HttpHeaders.SEC_WEBSOCKET_EXTENSIONS;
    @NotNull
    private static final String SecWebSocketKey = com.google.common.net.HttpHeaders.SEC_WEBSOCKET_KEY;
    @NotNull
    private static final String SecWebSocketProtocol = com.google.common.net.HttpHeaders.SEC_WEBSOCKET_PROTOCOL;
    @NotNull
    private static final String SecWebSocketVersion = com.google.common.net.HttpHeaders.SEC_WEBSOCKET_VERSION;
    @NotNull
    private static final String Server = "Server";
    @NotNull
    private static final String SetCookie = com.google.common.net.HttpHeaders.SET_COOKIE;
    @NotNull
    private static final String SLUG = "SLUG";
    @NotNull
    private static final String StrictTransportSecurity = com.google.common.net.HttpHeaders.STRICT_TRANSPORT_SECURITY;
    @NotNull
    private static final String TE = com.google.common.net.HttpHeaders.TE;
    @NotNull
    private static final String Timeout = MetricsConstants.KeyExchange.STATE_TRANSITION_TIMEOUT;
    @NotNull
    private static final String Trailer = com.google.common.net.HttpHeaders.TRAILER;
    @NotNull
    private static final String TransferEncoding = com.google.common.net.HttpHeaders.TRANSFER_ENCODING;
    @NotNull
    private static final String Upgrade = com.google.common.net.HttpHeaders.UPGRADE;
    @NotNull
    private static final String UserAgent = "User-Agent";
    @NotNull
    private static final String Vary = com.google.common.net.HttpHeaders.VARY;
    @NotNull
    private static final String Via = com.google.common.net.HttpHeaders.VIA;
    @NotNull
    private static final String Warning = com.google.common.net.HttpHeaders.WARNING;
    @NotNull
    private static final String WWWAuthenticate = com.google.common.net.HttpHeaders.WWW_AUTHENTICATE;
    @NotNull
    private static final String AccessControlAllowOrigin = com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
    @NotNull
    private static final String AccessControlAllowMethods = com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS;
    @NotNull
    private static final String AccessControlAllowCredentials = com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS;
    @NotNull
    private static final String AccessControlAllowHeaders = com.google.common.net.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS;
    @NotNull
    private static final String AccessControlRequestMethod = com.google.common.net.HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD;
    @NotNull
    private static final String AccessControlRequestHeaders = com.google.common.net.HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS;
    @NotNull
    private static final String AccessControlExposeHeaders = com.google.common.net.HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS;
    @NotNull
    private static final String AccessControlMaxAge = com.google.common.net.HttpHeaders.ACCESS_CONTROL_MAX_AGE;
    @NotNull
    private static final String XHttpMethodOverride = "X-Http-Method-Override";
    @NotNull
    private static final String XForwardedHost = com.google.common.net.HttpHeaders.X_FORWARDED_HOST;
    @NotNull
    private static final String XForwardedServer = "X-Forwarded-Server";
    @NotNull
    private static final String XForwardedProto = com.google.common.net.HttpHeaders.X_FORWARDED_PROTO;
    @NotNull
    private static final String XForwardedFor = com.google.common.net.HttpHeaders.X_FORWARDED_FOR;
    @NotNull
    private static final String XRequestId = "X-Request-ID";
    @NotNull
    private static final String XCorrelationId = "X-Correlation-ID";
    @NotNull
    private static final String[] UnsafeHeaders = {ContentLength, ContentType, TransferEncoding, Upgrade};

    private HttpHeaders() {
    }

    @NotNull
    public final String getALPN() {
        return ALPN;
    }

    @NotNull
    public final String getAccept() {
        return Accept;
    }

    @NotNull
    public final String getAcceptCharset() {
        return AcceptCharset;
    }

    @NotNull
    public final String getAcceptEncoding() {
        return AcceptEncoding;
    }

    @NotNull
    public final String getAcceptLanguage() {
        return AcceptLanguage;
    }

    @NotNull
    public final String getAcceptRanges() {
        return AcceptRanges;
    }

    @NotNull
    public final String getAccessControlAllowCredentials() {
        return AccessControlAllowCredentials;
    }

    @NotNull
    public final String getAccessControlAllowHeaders() {
        return AccessControlAllowHeaders;
    }

    @NotNull
    public final String getAccessControlAllowMethods() {
        return AccessControlAllowMethods;
    }

    @NotNull
    public final String getAccessControlAllowOrigin() {
        return AccessControlAllowOrigin;
    }

    @NotNull
    public final String getAccessControlExposeHeaders() {
        return AccessControlExposeHeaders;
    }

    @NotNull
    public final String getAccessControlMaxAge() {
        return AccessControlMaxAge;
    }

    @NotNull
    public final String getAccessControlRequestHeaders() {
        return AccessControlRequestHeaders;
    }

    @NotNull
    public final String getAccessControlRequestMethod() {
        return AccessControlRequestMethod;
    }

    @NotNull
    public final String getAge() {
        return Age;
    }

    @NotNull
    public final String getAllow() {
        return Allow;
    }

    @NotNull
    public final String getAuthenticationInfo() {
        return AuthenticationInfo;
    }

    @NotNull
    public final String getAuthorization() {
        return Authorization;
    }

    @NotNull
    public final String getCacheControl() {
        return CacheControl;
    }

    @NotNull
    public final String getConnection() {
        return Connection;
    }

    @NotNull
    public final String getContentDisposition() {
        return ContentDisposition;
    }

    @NotNull
    public final String getContentEncoding() {
        return ContentEncoding;
    }

    @NotNull
    public final String getContentLanguage() {
        return ContentLanguage;
    }

    @NotNull
    public final String getContentLength() {
        return ContentLength;
    }

    @NotNull
    public final String getContentLocation() {
        return ContentLocation;
    }

    @NotNull
    public final String getContentRange() {
        return ContentRange;
    }

    @NotNull
    public final String getContentType() {
        return ContentType;
    }

    @NotNull
    public final String getCookie() {
        return Cookie;
    }

    @NotNull
    public final String getDASL() {
        return DASL;
    }

    @NotNull
    public final String getDAV() {
        return DAV;
    }

    @NotNull
    public final String getDate() {
        return Date;
    }

    @NotNull
    public final String getDepth() {
        return Depth;
    }

    @NotNull
    public final String getDestination() {
        return Destination;
    }

    @NotNull
    public final String getETag() {
        return ETag;
    }

    @NotNull
    public final String getExpect() {
        return Expect;
    }

    @NotNull
    public final String getExpires() {
        return Expires;
    }

    @NotNull
    public final String getForwarded() {
        return Forwarded;
    }

    @NotNull
    public final String getFrom() {
        return From;
    }

    @NotNull
    public final String getHTTP2Settings() {
        return HTTP2Settings;
    }

    @NotNull
    public final String getHost() {
        return Host;
    }

    @NotNull
    public final String getIf() {
        return If;
    }

    @NotNull
    public final String getIfMatch() {
        return IfMatch;
    }

    @NotNull
    public final String getIfModifiedSince() {
        return IfModifiedSince;
    }

    @NotNull
    public final String getIfNoneMatch() {
        return IfNoneMatch;
    }

    @NotNull
    public final String getIfRange() {
        return IfRange;
    }

    @NotNull
    public final String getIfScheduleTagMatch() {
        return IfScheduleTagMatch;
    }

    @NotNull
    public final String getIfUnmodifiedSince() {
        return IfUnmodifiedSince;
    }

    @NotNull
    public final String getLastModified() {
        return LastModified;
    }

    @NotNull
    public final String getLink() {
        return Link;
    }

    @NotNull
    public final String getLocation() {
        return Location;
    }

    @NotNull
    public final String getLockToken() {
        return LockToken;
    }

    @NotNull
    public final String getMIMEVersion() {
        return MIMEVersion;
    }

    @NotNull
    public final String getMaxForwards() {
        return MaxForwards;
    }

    @NotNull
    public final String getOrderingType() {
        return OrderingType;
    }

    @NotNull
    public final String getOrigin() {
        return Origin;
    }

    @NotNull
    public final String getOverwrite() {
        return Overwrite;
    }

    @NotNull
    public final String getPosition() {
        return Position;
    }

    @NotNull
    public final String getPragma() {
        return Pragma;
    }

    @NotNull
    public final String getPrefer() {
        return Prefer;
    }

    @NotNull
    public final String getPreferenceApplied() {
        return PreferenceApplied;
    }

    @NotNull
    public final String getProxyAuthenticate() {
        return ProxyAuthenticate;
    }

    @NotNull
    public final String getProxyAuthenticationInfo() {
        return ProxyAuthenticationInfo;
    }

    @NotNull
    public final String getProxyAuthorization() {
        return ProxyAuthorization;
    }

    @NotNull
    public final String getPublicKeyPins() {
        return PublicKeyPins;
    }

    @NotNull
    public final String getPublicKeyPinsReportOnly() {
        return PublicKeyPinsReportOnly;
    }

    @NotNull
    public final String getRange() {
        return Range;
    }

    @NotNull
    public final String getReferrer() {
        return Referrer;
    }

    @NotNull
    public final String getRetryAfter() {
        return RetryAfter;
    }

    @NotNull
    public final String getSLUG() {
        return SLUG;
    }

    @NotNull
    public final String getScheduleReply() {
        return ScheduleReply;
    }

    @NotNull
    public final String getScheduleTag() {
        return ScheduleTag;
    }

    @NotNull
    public final String getSecWebSocketAccept() {
        return SecWebSocketAccept;
    }

    @NotNull
    public final String getSecWebSocketExtensions() {
        return SecWebSocketExtensions;
    }

    @NotNull
    public final String getSecWebSocketKey() {
        return SecWebSocketKey;
    }

    @NotNull
    public final String getSecWebSocketProtocol() {
        return SecWebSocketProtocol;
    }

    @NotNull
    public final String getSecWebSocketVersion() {
        return SecWebSocketVersion;
    }

    @NotNull
    public final String getServer() {
        return Server;
    }

    @NotNull
    public final String getSetCookie() {
        return SetCookie;
    }

    @NotNull
    public final String getStrictTransportSecurity() {
        return StrictTransportSecurity;
    }

    @NotNull
    public final String getTE() {
        return TE;
    }

    @NotNull
    public final String getTimeout() {
        return Timeout;
    }

    @NotNull
    public final String getTrailer() {
        return Trailer;
    }

    @NotNull
    public final String getTransferEncoding() {
        return TransferEncoding;
    }

    @NotNull
    public final String[] getUnsafeHeaders() {
        return UnsafeHeaders;
    }

    @NotNull
    public final String getUpgrade() {
        return Upgrade;
    }

    @NotNull
    public final String getUserAgent() {
        return UserAgent;
    }

    @NotNull
    public final String getVary() {
        return Vary;
    }

    @NotNull
    public final String getVia() {
        return Via;
    }

    @NotNull
    public final String getWWWAuthenticate() {
        return WWWAuthenticate;
    }

    @NotNull
    public final String getWarning() {
        return Warning;
    }

    @NotNull
    public final String getXCorrelationId() {
        return XCorrelationId;
    }

    @NotNull
    public final String getXForwardedFor() {
        return XForwardedFor;
    }

    @NotNull
    public final String getXForwardedHost() {
        return XForwardedHost;
    }

    @NotNull
    public final String getXForwardedProto() {
        return XForwardedProto;
    }

    @NotNull
    public final String getXForwardedServer() {
        return XForwardedServer;
    }

    @NotNull
    public final String getXHttpMethodOverride() {
        return XHttpMethodOverride;
    }

    @NotNull
    public final String getXRequestId() {
        return XRequestId;
    }

    public final boolean isUnsafe(@NotNull String header) {
        boolean equals;
        Intrinsics.checkParameterIsNotNull(header, "header");
        for (String str : UnsafeHeaders) {
            equals = StringsKt__StringsJVMKt.equals(str, header, true);
            if (equals) {
                return true;
            }
        }
        return false;
    }
}
