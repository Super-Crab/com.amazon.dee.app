package com.amazon.alexa.fitness.util;

import android.webkit.CookieManager;
import com.amazon.alexa.fitness.identity.IdentityManager;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.http.CoralService;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: AfitsHeaderUtils.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fJ#\u0010\u0011\u001a\u00020\n2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0002\u0010\u0015J\b\u0010\u0016\u001a\u0004\u0018\u00010\nR\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/fitness/util/AfitsHeaderUtils;", "", "environmentService", "Lcom/amazon/alexa/protocols/environment/EnvironmentService;", "cookieManager", "Landroid/webkit/CookieManager;", "identityManager", "Lcom/amazon/alexa/fitness/identity/IdentityManager;", "(Lcom/amazon/alexa/protocols/environment/EnvironmentService;Landroid/webkit/CookieManager;Lcom/amazon/alexa/fitness/identity/IdentityManager;)V", "basePath", "", "headerDirectedId", "headerFeatureNameKey", "headerFeatureNameValue", "addHeaders", "Lcom/dee/app/http/CoralService$Request;", "request", "addUrlParameters", "nextToken", "maxResults", "", "(Ljava/lang/String;Ljava/lang/Integer;)Ljava/lang/String;", "getUserProfileDirectedId", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class AfitsHeaderUtils {
    private final String basePath;
    private final CookieManager cookieManager;
    private final EnvironmentService environmentService;
    private final String headerDirectedId;
    private final String headerFeatureNameKey;
    private final String headerFeatureNameValue;
    private final IdentityManager identityManager;

    @Inject
    public AfitsHeaderUtils(@NotNull EnvironmentService environmentService, @NotNull CookieManager cookieManager, @NotNull IdentityManager identityManager) {
        Intrinsics.checkParameterIsNotNull(environmentService, "environmentService");
        Intrinsics.checkParameterIsNotNull(cookieManager, "cookieManager");
        Intrinsics.checkParameterIsNotNull(identityManager, "identityManager");
        this.environmentService = environmentService;
        this.cookieManager = cookieManager;
        this.identityManager = identityManager;
        this.basePath = "/api/fitness/workouts";
        this.headerDirectedId = "x-amzn-directedId";
        this.headerFeatureNameKey = "x-amzn-featureName";
        this.headerFeatureNameValue = "PB_ALEXA_FITNESS_1_1";
    }

    public static /* synthetic */ String addUrlParameters$default(AfitsHeaderUtils afitsHeaderUtils, String str, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        if ((i & 2) != 0) {
            num = null;
        }
        return afitsHeaderUtils.addUrlParameters(str, num);
    }

    @NotNull
    public final CoralService.Request addHeaders(@NotNull CoralService.Request request) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        String userProfileDirectedId = getUserProfileDirectedId();
        CookieManager cookieManager = this.cookieManager;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("https://");
        outline107.append(this.environmentService.getCoralHost());
        String cookie = cookieManager.getCookie(outline107.toString());
        if (userProfileDirectedId != null) {
            request.withHeader(this.headerDirectedId, userProfileDirectedId);
        }
        if (cookie != null) {
            request.withHeader("Cookie", cookie);
        }
        request.withHeader(this.headerFeatureNameKey, this.headerFeatureNameValue);
        return request;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0021  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0036  */
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.String addUrlParameters(@org.jetbrains.annotations.Nullable java.lang.String r4, @org.jetbrains.annotations.Nullable java.lang.Integer r5) {
        /*
            r3 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = r3.basePath
            r2 = 63
            java.lang.String r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline89(r0, r1, r2)
            java.lang.StringBuilder r0 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r0)
            if (r4 == 0) goto L1c
            boolean r1 = kotlin.text.StringsKt.isBlank(r4)
            if (r1 == 0) goto L1a
            goto L1c
        L1a:
            r1 = 0
            goto L1d
        L1c:
            r1 = 1
        L1d:
            java.lang.String r2 = ""
            if (r1 != 0) goto L28
            java.lang.String r1 = "&nextToken="
            java.lang.String r4 = com.android.tools.r8.GeneratedOutlineSupport1.outline72(r1, r4)
            goto L29
        L28:
            r4 = r2
        L29:
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            java.lang.StringBuilder r4 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r4)
            if (r5 == 0) goto L47
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "&maxResults="
            r0.append(r1)
            r0.append(r5)
            java.lang.String r2 = r0.toString()
        L47:
            r4.append(r2)
            java.lang.String r4 = r4.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.fitness.util.AfitsHeaderUtils.addUrlParameters(java.lang.String, java.lang.Integer):java.lang.String");
    }

    @Nullable
    public final String getUserProfileDirectedId() {
        return this.identityManager.getUserProfileDirectedId();
    }
}
