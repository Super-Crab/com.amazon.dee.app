package com.amazon.alexa.biloba.view.gettingStartedV3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.alexa.biloba.R;
import com.amazon.alexa.biloba.databinding.GettingStartedViewV3Binding;
import com.amazon.alexa.biloba.dependency.BilobaDependencies;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.alexa.biloba.utils.LogUtils;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.alexa.biloba.view.BilobaViewController;
import com.amazon.alexa.imageloader.api.ImageLoader;
import com.amazon.alexa.mosaic.view.ViewUtils;
import dagger.Lazy;
import java.net.URI;
import javax.inject.Inject;
/* loaded from: classes6.dex */
public class GettingStartedViewV3 extends BilobaViewController {
    private static final String GETTING_STARTED_IMAGE_URL = "https://m.media-amazon.com/images/G/01/ACH/value_prop_crop_1x.png";
    private static final String TAG = "GettingStartedViewV3";
    private Context context;
    private ImageView gettingStartedImage;
    @Inject
    Lazy<ImageLoader> imageLoader;
    private View view;
    private GettingStartedViewModelV3 viewModel;

    public GettingStartedViewV3(Context context) {
        BilobaDependencies.inject(this);
        this.context = context;
        this.viewModel = new GettingStartedViewModelV3();
    }

    public static void handleLandscapeWidth(ViewGroup viewGroup, boolean z, Context context) {
        ViewGroup.LayoutParams layoutParams = viewGroup.getLayoutParams();
        Resources resources = context.getResources();
        int integer = (int) (resources.getInteger(R.integer.max_width_landscape_mode) * resources.getDisplayMetrics().density);
        if (layoutParams != null) {
            if (!z) {
                integer = -1;
            }
            layoutParams.width = integer;
            viewGroup.setLayoutParams(layoutParams);
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public String getTitle(@NonNull Context context) {
        return context.getString(R.string.main_title);
    }

    public boolean isLandscape() {
        return this.context.getResources().getConfiguration().orientation == 2;
    }

    public void loadImage() {
        this.imageLoader.mo358get().loadInto(URI.create(GETTING_STARTED_IMAGE_URL), this.gettingStartedImage);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NonNull
    public View makeView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.context = viewGroup.getContext();
        GettingStartedViewV3Binding gettingStartedViewV3Binding = (GettingStartedViewV3Binding) DataBindingUtil.inflate(LayoutInflater.from(ViewUtils.getThemeWrapper(this.context)), R.layout.getting_started_view_v3, viewGroup, false);
        gettingStartedViewV3Binding.setViewModel(this.viewModel);
        gettingStartedViewV3Binding.setHandler(this);
        gettingStartedViewV3Binding.setLifecycleOwner((LifecycleOwner) this.context);
        this.viewModel.stopRecordingTTCF();
        this.view = gettingStartedViewV3Binding.getRoot();
        this.gettingStartedImage = (ImageView) this.view.findViewById(R.id.getting_started_image);
        loadImage();
        registerViewAttributes((LinearLayout) this.view.findViewById(R.id.no_connection_banner), this.viewModel);
        return this.view;
    }

    public void navigateToLearnMore() {
        recordClickMetric(MetricsConstants.UserInteractionMetrics.LEARN_MORE, "");
        String externalWebViewUrl = this.viewModel.getExternalWebViewUrl();
        String str = TAG;
        LogUtils.i(str, "Starting webview with url " + externalWebViewUrl);
        Intent intent = new Intent(WebConstants.WebviewConstants.EXTERNAL_WEBVIEW_INTENT);
        intent.putExtra("android.intent.extra.TEXT", externalWebViewUrl);
        intent.putExtra("IS_DOM_STORAGE_ENABLED", true);
        intent.putExtra("CONTINUE_ON_NON_FATAL_ERRORS", true);
        intent.putExtra("URL_REGEX_KEY", ".*");
        intent.putExtra("BRIDGE_ACTION_KEY", "launchExternal");
        intent.putExtra("WEBVIEW_TITLE_ID_KEY", R.string.main_title);
        ((Activity) this.context).startActivity(intent);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onAttach(@NonNull View view) {
        recordViewMetric("GettingStartedViewV3", "");
        subscribeToNetworkChange();
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onConfigurationChanged(Configuration configuration) {
        handleLandscapeWidth((ConstraintLayout) this.view.findViewById(R.id.getting_started_layout), configuration.orientation == 2, this.context);
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onCreate(Context context) {
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    public void onDetach(@NonNull View view) {
        unSubscribeToNetworkChange();
    }
}
