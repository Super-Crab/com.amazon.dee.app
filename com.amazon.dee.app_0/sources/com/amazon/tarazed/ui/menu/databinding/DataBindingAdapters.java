package com.amazon.tarazed.ui.menu.databinding;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.RawRes;
import androidx.databinding.BindingAdapter;
import com.amazon.tarazed.R;
import com.amazon.tarazed.ui.FontHelper;
import com.amazon.tarazed.ui.html.HtmlUtil;
import com.facebook.react.modules.appstate.AppStateModule;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
/* compiled from: DataBindingAdapters.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0007H\u0001¢\u0006\u0002\b\bJ\u001d\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001¢\u0006\u0002\b\rJ\u001d\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0007H\u0001¢\u0006\u0002\b\u0011J\u001d\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0001¢\u0006\u0002\b\u0016J\u001d\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0001¢\u0006\u0002\b\u0016J\u001d\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0019H\u0001¢\u0006\u0002\b\u001aJ\u001f\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010\u001c\u001a\u00020\u001dH\u0001¢\u0006\u0002\b\u001eJ\u001f\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\n2\b\b\u0001\u0010 \u001a\u00020\u001dH\u0001¢\u0006\u0002\b!¨\u0006\""}, d2 = {"Lcom/amazon/tarazed/ui/menu/databinding/DataBindingAdapters;", "", "()V", "addOnTextChangedListener", "", "view", "Landroid/widget/TextView;", "", "addOnTextChangedListener$TarazedAndroidLibrary_release", "setAlpha", "Landroid/widget/ImageView;", "alpha", "", "setAlpha$TarazedAndroidLibrary_release", "setButtonEnabled", "Landroid/widget/ImageButton;", "isEnabled", "setButtonEnabled$TarazedAndroidLibrary_release", "setDynamicBackground", "Landroid/view/View;", AppStateModule.APP_STATE_BACKGROUND, "Landroid/graphics/drawable/Drawable;", "setDynamicBackground$TarazedAndroidLibrary_release", "setFont", "fontName", "", "setFont$TarazedAndroidLibrary_release", "setHtml", "htmlResource", "", "setHtml$TarazedAndroidLibrary_release", "setImageResource", "drawable", "setImageResource$TarazedAndroidLibrary_release", "TarazedAndroidLibrary_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class DataBindingAdapters {
    public static final DataBindingAdapters INSTANCE = new DataBindingAdapters();

    private DataBindingAdapters() {
    }

    @BindingAdapter({"app:addOnTextChangedListener"})
    @JvmStatic
    public static final void addOnTextChangedListener$TarazedAndroidLibrary_release(@NotNull TextView view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (z) {
            Context context = view.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "view.context");
            view.addTextChangedListener(new StatusTextChangedAnnouncer(context, view));
        }
    }

    @BindingAdapter({"app:image_alpha"})
    @JvmStatic
    public static final void setAlpha$TarazedAndroidLibrary_release(@NotNull ImageView view, float f) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        view.setImageAlpha((int) (f * 255));
    }

    @BindingAdapter({"app:buttonEnabled"})
    @JvmStatic
    public static final void setButtonEnabled$TarazedAndroidLibrary_release(@NotNull ImageButton view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        view.setEnabled(z);
    }

    @BindingAdapter({"app:dynamic_background"})
    @JvmStatic
    public static final void setDynamicBackground$TarazedAndroidLibrary_release(@NotNull View view, @NotNull Drawable background) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(background, "background");
        view.setBackground(background);
    }

    @BindingAdapter({"app:font"})
    @JvmStatic
    public static final void setFont$TarazedAndroidLibrary_release(@NotNull TextView view, @NotNull String fontName) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(fontName, "fontName");
        FontHelper fontHelper = FontHelper.INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "view.context");
        AssetManager assets = context.getAssets();
        Intrinsics.checkExpressionValueIsNotNull(assets, "view.context.assets");
        Typeface createTypeface$TarazedAndroidLibrary_release = fontHelper.createTypeface$TarazedAndroidLibrary_release(assets, fontName);
        if (createTypeface$TarazedAndroidLibrary_release != null) {
            view.setTypeface(createTypeface$TarazedAndroidLibrary_release);
        }
    }

    @BindingAdapter({"app:html"})
    @JvmStatic
    public static final void setHtml$TarazedAndroidLibrary_release(@NotNull TextView view, @RawRes int i) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        InputStream openRawResource = view.getResources().openRawResource(i);
        Intrinsics.checkExpressionValueIsNotNull(openRawResource, "view.resources.openRawResource(htmlResource)");
        InputStreamReader inputStreamReader = new InputStreamReader(openRawResource, Charsets.UTF_8);
        BufferedReader bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        try {
            String readText = TextStreamsKt.readText(bufferedReader);
            CloseableKt.closeFinally(bufferedReader, null);
            HtmlUtil.INSTANCE.applyHtmlToTextView(readText, view);
        } finally {
        }
    }

    @BindingAdapter({"app:srcAndSetDescription"})
    @JvmStatic
    public static final void setImageResource$TarazedAndroidLibrary_release(@NotNull ImageView view, @DrawableRes int i) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        view.setImageResource(i);
        if (i == R.drawable.ic_pause_btn) {
            view.setContentDescription(view.getContext().getString(R.string.pause_button));
        } else if (i == R.drawable.ic_play_btn_enabled) {
            view.setContentDescription(view.getContext().getString(R.string.resume_button));
        } else if (i != R.drawable.ic_loading_spinner) {
        } else {
            view.setContentDescription(null);
        }
    }

    @BindingAdapter({"app:dynamic_background"})
    @JvmStatic
    public static final void setDynamicBackground$TarazedAndroidLibrary_release(@NotNull ImageButton view, @NotNull Drawable background) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        Intrinsics.checkParameterIsNotNull(background, "background");
        view.setBackground(background);
    }
}
