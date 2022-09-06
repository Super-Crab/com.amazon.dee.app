package com.amazon.alexa.biloba.view.webview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.utils.AndroidUtils;
import com.amazon.alexa.biloba.utils.BilobaRouteUtil;
import com.amazon.alexa.biloba.utils.UrlHelper;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.routing.api.RoutingService;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class WebviewView extends BilobaViewController {
    private static final String TAG = "WebviewView";
    private final Context context;
    @Inject
    public Lazy<FeatureServiceV2> featureServiceV2;
    @Inject
    public Lazy<RoutingService> routingService;
    private final String uri;
    @Inject
    public Lazy<UrlHelper> urlHelper;

    public WebviewView(Context context, String str) {
        BilobaDependencies.inject(this);
        this.context = context;
        this.uri = str;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        return null;
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(View view) {
        recordViewMetric("WebviewView", "");
        AndroidUtils.startWebview(this.uri.startsWith("http") ? this.uri : this.urlHelper.mo358get().getUrl(this.uri, this.context.getResources().getConfiguration()), this.context);
        if (this.routingService.mo358get().canNavigateBackward()) {
            this.routingService.mo358get().navigateBackward();
        } else {
            BilobaRouteUtil.routeTo(this.routingService.mo358get(), "biloba");
        }
    }
}
