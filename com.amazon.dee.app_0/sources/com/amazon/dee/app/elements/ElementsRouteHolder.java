package com.amazon.dee.app.elements;

import android.net.Uri;
import androidx.annotation.NonNull;
import com.amazon.alexa.routing.api.RouteParameter;
import com.amazon.alexa.routing.api.RoutingService;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes12.dex */
public final class ElementsRouteHolder {
    private static final String TAG = Log.tag(ElementsRouteHolder.class);
    @NonNull
    public final String uri;

    public ElementsRouteHolder(String str) {
        if (!str.startsWith(ElementsUtils.V2_PREFIX)) {
            Log.w(TAG, "Uri passed to route holder does not have V2 prefix.");
            this.uri = str;
            return;
        }
        this.uri = str.substring(3);
    }

    public RoutingService.RoutingBuilder configure(RoutingService.RoutingBuilder routingBuilder) {
        RoutingService.RoutingBuilder with = routingBuilder.with(RouteParameter.ROUTE, Uri.encode(this.uri));
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(ElementsUtils.V2_PREFIX);
        outline107.append(this.uri);
        return with.with("name", outline107.toString());
    }

    public String toNativeUri(boolean z) {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107(ElementsUtils.V2_PREFIX);
        outline107.append(z ? Uri.encode(this.uri) : this.uri);
        return outline107.toString();
    }

    public String toNativeUri() {
        return toNativeUri(true);
    }
}
