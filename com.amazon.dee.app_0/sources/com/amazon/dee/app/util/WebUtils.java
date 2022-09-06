package com.amazon.dee.app.util;

import android.annotation.TargetApi;
import android.net.Uri;
import android.text.TextUtils;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.routing.data.RouteUrls;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.ui.web.WebApp;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
/* loaded from: classes12.dex */
public final class WebUtils {
    private static final String A2A_LINKING_CONSENT_PATH = "/spa/skill-account-linking-consent";
    public static final String ABOUT_BLANK_PAGE = "about:blank";
    private static final String EXAMPLE_TEMP_URL = "https://www.example.com";
    private static final String KEY_FRAGMENT = "fragment";
    private static final int MAX_NUMBER_OF_AUTH_CODE_PATH_SEGMENTS = 4;
    private static final String NO_ROUTE = "";
    private static final String VENDOR_ID_IMPLICIT_QUERY_PARAM_KEY = "vendorId";
    private static final String VENDOR_ID_QUERY_PARAM_KEY = "vendor_id";
    private static final String defaultContentType = "utf-8";
    private static final String defaultMediaType = "application/x-www-form-urlencoded;charset=UTF-8";
    private static final ArrayList<String> dopplerHosts = new ArrayList<>(Arrays.asList("10.201.126.241", "192.168.11.1"));
    private static final String ACCOUNT_LINKING_AUTH_CODE_GRANT_ESTABLISHMENT_PATH = "/api/skill/link";
    private static final String ACCOUNT_LINKING_IMPLICIT_GRANT_ESTABLISHMENT_PATH = "/spa/skill/account-linking-status.html";
    private static final String[] URI_PATH_SUPPORTING_DELEGATED_PROFILES = {ACCOUNT_LINKING_AUTH_CODE_GRANT_ESTABLISHMENT_PATH, ACCOUNT_LINKING_IMPLICIT_GRANT_ESTABLISHMENT_PATH};
    static final String TAG = Utils.safeTag(WebUtils.class.getSimpleName());

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public enum RouteByPath {
        ESTABLISHMENT_AUTH_CODE(WebUtils.ACCOUNT_LINKING_AUTH_CODE_GRANT_ESTABLISHMENT_PATH, "v2/a2s/skill-account-linking-establish"),
        ESTABLISHMENT_IMPLICIT_GRANT(WebUtils.ACCOUNT_LINKING_IMPLICIT_GRANT_ESTABLISHMENT_PATH, "v2/a2s/skill-account-linking-establish"),
        A2A_3P_TO_ALEXA(WebUtils.A2A_LINKING_CONSENT_PATH, "v2/a2s/skill-account-linking-consent"),
        LAUNCH_CUSTOM_SKILLS("/apis/custom/skills", ""),
        KITCHEN("/apis/kitchen", ""),
        ROUTINES_SHARED_PATH("/routines/shared", "v2/behaviors/sharedRoutine?behaviorId=%s&routineType=shared"),
        ROUTINES_FEATURED_PATH("/routines/featured", "v2/behaviors/behaviorDetails?behaviorId=%s&isAutoEnabled=false");
        
        private String originalPath;
        private String routeDestination;

        RouteByPath(String str, String str2) {
            this.originalPath = str;
            this.routeDestination = str2;
        }

        public String processUri(@NonNull Uri uri) {
            if (this == ESTABLISHMENT_AUTH_CODE) {
                return WebUtils.processAccountLinkingAuthCodeUri(uri, this.routeDestination);
            }
            if (this == ESTABLISHMENT_IMPLICIT_GRANT) {
                return WebUtils.processAccountLinkingImplicitGrantUri(uri, this.routeDestination);
            }
            if (this == LAUNCH_CUSTOM_SKILLS || this == KITCHEN) {
                return WebUtils.processLaunchCustomSkillUri(uri);
            }
            if (this == ROUTINES_SHARED_PATH || this == ROUTINES_FEATURED_PATH) {
                return WebUtils.processRoutinesUri(uri, this.routeDestination);
            }
            Uri.Builder encodedPath = new Uri.Builder().encodedPath(this.routeDestination);
            WebUtils.appendQueryParametersExcludingRoutingParameter(uri, encodedPath);
            return encodedPath.build().toString();
        }
    }

    private WebUtils() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void appendQueryParametersExcludingRoutingParameter(@NonNull Uri uri, @NonNull Uri.Builder builder) {
        HashSet hashSet = new HashSet();
        hashSet.add(KEY_FRAGMENT);
        appendQueryParametersFromUriExceptIgnored(uri, builder, hashSet);
    }

    private static void appendQueryParametersFromUriExceptIgnored(@NonNull Uri uri, @NonNull Uri.Builder builder, HashSet<String> hashSet) {
        for (String str : uri.getQueryParameterNames()) {
            if (hashSet == null || !hashSet.contains(str)) {
                builder.appendQueryParameter(str, uri.getQueryParameter(str));
            }
        }
    }

    public static Request.Builder buildHttpRequest(String str, Map<String, String> map, String str2, String str3) {
        Request.Builder builder = new Request.Builder();
        RequestBody create = str3 == null ? null : RequestBody.create(MediaType.parse(defaultMediaType), str3);
        if ("POST".equals(str) && create == null) {
            create = RequestBody.create(MediaType.parse(defaultMediaType), "");
        }
        builder.url(str2).method(str, create);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.addHeader(entry.getKey(), entry.getValue());
        }
        return builder;
    }

    private static String buildRouteByPath(@NonNull Uri uri) {
        RouteByPath[] values;
        String path = uri.getPath();
        if (TextUtils.isEmpty(path)) {
            return "";
        }
        for (RouteByPath routeByPath : RouteByPath.values()) {
            if (path.startsWith(routeByPath.originalPath)) {
                return routeByPath.processUri(uri);
            }
        }
        return "";
    }

    private static String constructRouteFromQueryParameters(@NonNull Uri uri) {
        String queryParameter = uri.getQueryParameter(KEY_FRAGMENT);
        if (TextUtils.isEmpty(queryParameter)) {
            return null;
        }
        Uri.Builder encodedPath = new Uri.Builder().encodedPath(queryParameter);
        appendQueryParametersExcludingRoutingParameter(uri, encodedPath);
        return encodedPath.build().toString();
    }

    @NonNull
    public static String createSendableCookies(@NonNull String[] strArr) {
        StringBuilder sb = new StringBuilder();
        if (strArr == null) {
            return sb.toString();
        }
        for (String str : strArr) {
            String[] split = str.length() == 0 ? new String[0] : str.split(";", -1);
            if (split.length >= 1) {
                sb.append(split[0]);
                sb.append(';');
            } else {
                Log.e(TAG, "Cookie was malformed");
            }
        }
        return sb.toString();
    }

    public static boolean doesUriSupportDelegatedProfiles(Uri uri) {
        if (uri != null && uri.getPath() != null) {
            String path = uri.getPath();
            for (String str : URI_PATH_SUPPORTING_DELEGATED_PROFILES) {
                if (path.startsWith(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Nullable
    public static String getRoute(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return ABOUT_BLANK_PAGE.equals(str) ? str : getRoute(Uri.parse(str));
    }

    public static boolean isAnXHRRequest(String str) {
        return !str.contains(".");
    }

    public static boolean isDopplerHost(String str) {
        return dopplerHosts.contains(str);
    }

    @TargetApi(21)
    public static WebResourceResponse okHttpToPreflightResponse(WebResourceRequest webResourceRequest, @Nullable Response response, WebApp webApp) {
        ResponseBody body;
        if (response == null || (body = response.body()) == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
            hashMap.put(entry.getKey(), TextUtils.join(",", entry.getValue()).toLowerCase());
        }
        boolean z = false;
        for (Map.Entry<String, String> entry2 : webResourceRequest.getRequestHeaders().entrySet()) {
            String key = entry2.getKey();
            if (key != null) {
                String lowerCase = key.toLowerCase();
                char c = 65535;
                int hashCode = lowerCase.hashCode();
                if (hashCode != -1008619738) {
                    if (hashCode != 892651224) {
                        if (hashCode == 1742157935 && lowerCase.equals("access-control-request-headers")) {
                            c = 0;
                        }
                    } else if (lowerCase.equals("access-control-request-method")) {
                        c = 1;
                    }
                } else if (lowerCase.equals("origin")) {
                    c = 2;
                }
                if (c != 0) {
                    if (c != 1) {
                        if (c == 2 && hashMap.get("access-control-allow-origin") == null) {
                            hashMap.put("access-control-allow-origin", entry2.getValue());
                            z = true;
                        }
                    } else if (hashMap.get("allow") == null) {
                        hashMap.put("allow", entry2.getValue());
                        z = true;
                    }
                } else if (hashMap.get("access-control-allow-headers") == null) {
                    hashMap.put("access-control-allow-headers", entry2.getValue());
                    z = true;
                }
            }
        }
        if (z) {
            webApp.recordMissingCORSHeader();
        }
        return new WebResourceResponse(response.header("content-type", defaultMediaType), response.header("content-encoding", defaultContentType), response.code(), response.message(), hashMap, body.byteStream());
    }

    @TargetApi(21)
    public static WebResourceResponse okHttpToWebResponse(@Nullable Response response) {
        ResponseBody body;
        if (response == null || (body = response.body()) == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, List<String>> entry : response.headers().toMultimap().entrySet()) {
            hashMap.put(entry.getKey(), TextUtils.join(",", entry.getValue()));
        }
        return new WebResourceResponse(response.header("content-type", defaultMediaType), response.header("content-encoding", defaultContentType), response.code(), response.message(), hashMap, body.byteStream());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String processAccountLinkingAuthCodeUri(@NonNull Uri uri, @NonNull String str) {
        Uri.Builder encodedPath = new Uri.Builder().encodedPath(str);
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() == 4) {
            encodedPath.appendQueryParameter(VENDOR_ID_QUERY_PARAM_KEY, pathSegments.get(3));
        }
        appendQueryParametersExcludingRoutingParameter(uri, encodedPath);
        return encodedPath.build().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String processAccountLinkingImplicitGrantUri(@NonNull Uri uri, @NonNull String str) {
        HashSet hashSet = new HashSet();
        hashSet.add(KEY_FRAGMENT);
        hashSet.add(VENDOR_ID_QUERY_PARAM_KEY);
        Uri.Builder encodedPath = new Uri.Builder().encodedPath(str);
        String queryParameter = uri.getQueryParameter(VENDOR_ID_IMPLICIT_QUERY_PARAM_KEY);
        if (!TextUtils.isEmpty(queryParameter)) {
            encodedPath.appendQueryParameter(VENDOR_ID_QUERY_PARAM_KEY, queryParameter);
        }
        appendQueryParametersFromUriExceptIgnored(uri, encodedPath, hashSet);
        hashSet.addAll(encodedPath.build().getQueryParameterNames());
        String fragment = uri.getFragment();
        if (!TextUtils.isEmpty(fragment)) {
            appendQueryParametersFromUriExceptIgnored(Uri.parse("https://www.example.com?" + fragment), encodedPath, hashSet);
        }
        return encodedPath.build().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String processLaunchCustomSkillUri(@NonNull Uri uri) {
        Uri.Builder builder = new Uri.Builder();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(RouteUrls.ELEMENTS_A2S);
        outline107.append(uri.getEncodedPath());
        Uri.Builder encodedPath = builder.encodedPath(outline107.toString());
        for (String str : uri.getQueryParameterNames()) {
            encodedPath.appendQueryParameter(str, uri.getQueryParameter(str));
        }
        return encodedPath.build().toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String processRoutinesUri(@NonNull Uri uri, @NonNull String str) {
        return new Uri.Builder().encodedPath(String.format(str, uri.getPath().substring(uri.getPath().lastIndexOf("/") + 1))).build().toString();
    }

    @Nullable
    public static String getRoute(@Nullable Uri uri) {
        if (uri == null) {
            return null;
        }
        String buildRouteByPath = buildRouteByPath(uri);
        if (!TextUtils.isEmpty(buildRouteByPath)) {
            return buildRouteByPath;
        }
        String fragment = uri.getFragment();
        return (!TextUtils.isEmpty(fragment) || !uri.isHierarchical()) ? fragment : constructRouteFromQueryParameters(uri);
    }
}
