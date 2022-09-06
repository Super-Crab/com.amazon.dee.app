package com.amazon.alexa.redesign.view.templates;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.alexa.redesign.actions.Action;
import com.amazon.alexa.redesign.entity.CardModel;
import com.amazon.alexa.redesign.utils.HomeMetricsRecorder;
import java.util.HashMap;
/* loaded from: classes10.dex */
public final class TemplateHelperUtil {
    private static final int MIN_CLICKS = 1;
    public static Boolean isFireOs;
    private int numClicks = 0;

    public static boolean isFireOS() {
        if (isFireOs == null) {
            try {
                Class.forName("amazon.os.Build$VERSION");
                isFireOs = true;
            } catch (ClassNotFoundException unused) {
                isFireOs = false;
            }
        }
        return isFireOs.booleanValue();
    }

    public static void scaleTextViewWithFontFireOS(TextView textView, Context context, int i) {
        Resources resources = context.getResources();
        float f = Settings.System.getFloat(context.getContentResolver(), "font_scale", 1.0f);
        float f2 = resources.getConfiguration().fontScale;
        if (isFireOS() || f != f2) {
            textView.setTextSize(1, resources.getInteger(i) * f);
        }
    }

    public static void scaleWithFont(View view, Context context, int i) {
        Resources resources = context.getResources();
        ContentResolver contentResolver = context.getContentResolver();
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = Math.round(resources.getInteger(i) * resources.getDisplayMetrics().density * Math.max(resources.getConfiguration().fontScale, Settings.System.getFloat(contentResolver, "font_scale", 1.0f)));
        }
    }

    public static void setHeightToWrapContent(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = -2;
            view.setLayoutParams(layoutParams);
        }
    }

    public static void setTopAndBottomMargin(View view, Context context, int i) {
        Resources resources = context.getResources();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
        int round = Math.round(resources.getInteger(i) * resources.getDisplayMetrics().density * resources.getConfiguration().fontScale);
        marginLayoutParams.topMargin = round;
        marginLayoutParams.bottomMargin = round;
    }

    public void emitClickMetrics(HomeMetricsRecorder homeMetricsRecorder, CardModel cardModel, Action action) {
        HashMap hashMap = new HashMap(cardModel.getTopLevelMetricsObject());
        hashMap.putAll(new HashMap(action.getMetricData()));
        if (!action.getOnClickUserLeavesHome()) {
            this.numClicks++;
            if (this.numClicks > 1) {
                homeMetricsRecorder.recordViewEvent(hashMap, true);
            }
        }
        homeMetricsRecorder.recordClickEvent(hashMap);
    }
}
