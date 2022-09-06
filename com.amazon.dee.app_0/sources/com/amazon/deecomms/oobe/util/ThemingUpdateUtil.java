package com.amazon.deecomms.oobe.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.mosaic.components.ThemeUtil;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
/* loaded from: classes12.dex */
public class ThemingUpdateUtil {
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ThemingUpdateUtil.class);

    public void applyBackgroundColorToView(View view, Context context, int i) {
        if (!isMosaicThemingEnabled()) {
            return;
        }
        if (view == null) {
            LOG.e("Skipped theming due to null view received while processing applyBackgroundColorToView.");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.OOBE_THEMING_VIEW_NULL_COUNT);
            return;
        }
        view.setBackgroundColor(getColorFromAttribute(context, i));
    }

    public void applyBackgroundToButton(Button button, Context context, int i) {
        if (!isMosaicThemingEnabled()) {
            return;
        }
        if (button == null) {
            LOG.e("Skipped theming due to null button view received while processing applyBackgroundToButton.");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.OOBE_THEMING_BUTTON_VIEW_NULL_COUNT);
            return;
        }
        button.setBackground(ContextCompat.getDrawable(context, i));
    }

    public void applyColorToTextView(View view, int i, Context context, int i2) {
        if (!isMosaicThemingEnabled()) {
            return;
        }
        if (view == null) {
            LOG.e("Skipped theming due to null view received while processing applyColorToTextView.");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.OOBE_THEMING_VIEW_NULL_COUNT);
            return;
        }
        applyColorToTextView((TextView) view.findViewById(i), context, i2);
    }

    public void applyTintColorToDrawable(Drawable drawable, Context context, int i) {
        if (!isMosaicThemingEnabled()) {
            return;
        }
        if (drawable == null) {
            LOG.e("Skipped theming due to null drawable received while processing applyTintColorToDrawable.");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.OOBE_THEMING_DRAWABLE_NULL_COUNT);
            return;
        }
        drawable.setTint(getColorFromAttribute(context, i));
    }

    public void applyTintColorToImageView(ImageView imageView, Context context, int i) {
        if (!isMosaicThemingEnabled()) {
            return;
        }
        if (imageView == null) {
            LOG.e("Skipped theming due to null image view received while processing applyTintColorToImageView.");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.OOBE_THEMING_IMAGE_VIEW_NULL_COUNT);
            return;
        }
        imageView.setColorFilter(getColorFromAttribute(context, i));
    }

    public int getColorFromAttribute(Context context, int i) {
        return ThemeUtil.getColorFromAttribute(context, i);
    }

    public int getSystemUiVisibility(Context context) {
        return (!isMosaicThemingEnabled() || !isLightMode(context)) ? 0 : 8192;
    }

    public boolean isLightMode(Context context) {
        int i = context.getResources().getConfiguration().uiMode & 48;
        return i == 16 || i != 32;
    }

    public boolean isMosaicThemingEnabled() {
        return true;
    }

    public void applyColorToTextView(TextView textView, Context context, int i) {
        if (!isMosaicThemingEnabled()) {
            return;
        }
        if (textView == null) {
            LOG.e("Skipped theming due to null text view received while processing applyColorToTextView.");
            MetricsHelper.recordSingleOccurrenceOperational(MetricKeys.OOBE_THEMING_TEXT_VIEW_NULL_COUNT);
            return;
        }
        textView.setTextColor(getColorFromAttribute(context, i));
    }
}
