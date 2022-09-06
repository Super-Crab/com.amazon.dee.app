package com.amazon.alexa.fitness.view.ftue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.amazon.alexa.viewmanagement.api.ViewController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FtueIntroViewController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/view/ftue/FtueIntroViewController;", "Lcom/amazon/alexa/viewmanagement/api/ViewController;", "()V", "makeView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "viewGroup", "Landroid/view/ViewGroup;", "setTextFont", "", "view", "setupIntroView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FtueIntroViewController implements ViewController {
    private final void setTextFont(View view) {
        if (((TextView) view.findViewById(R.id.welcome_title)) != null) {
            Fonts fonts = Fonts.EMBER_BOLD;
        }
        if (((TextView) view.findViewById(R.id.welcome_message1)) != null) {
            Fonts fonts2 = Fonts.EMBER_REGULAR;
        }
        if (((TextView) view.findViewById(R.id.welcome_message2)) != null) {
            Fonts fonts3 = Fonts.EMBER_REGULAR;
        }
        if (((TextView) view.findViewById(R.id.welcome_not_you_text)) != null) {
            Fonts fonts4 = Fonts.EMBER_REGULAR;
        }
        if (((Button) view.findViewById(R.id.welcome_signout)) != null) {
            Fonts fonts5 = Fonts.EMBER_BOLD;
        }
        if (((Button) view.findViewById(R.id.welcome_continue)) != null) {
            Fonts fonts6 = Fonts.EMBER_BOLD;
        }
    }

    private final void setupIntroView(View view) {
        setTextFont(view);
        String userFirstName = FullScreenUtil.Companion.getUserFirstName();
        TextView textView = (TextView) view.findViewById(R.id.welcome_title);
        String str = null;
        if (textView != null) {
            Context context = view.getContext();
            textView.setText(context != null ? context.getString(R.string.welcome_title, userFirstName) : null);
        }
        TextView textView2 = (TextView) view.findViewById(R.id.welcome_not_you_text);
        if (textView2 != null) {
            Context context2 = view.getContext();
            if (context2 != null) {
                str = context2.getString(R.string.welcome_not_user_text, userFirstName);
            }
            textView2.setText(str);
        }
        Button button = (Button) view.findViewById(R.id.welcome_continue);
        if (button != null) {
            button.setOnClickListener(FtueIntroViewController$setupIntroView$1.INSTANCE);
        }
        Button button2 = (Button) view.findViewById(R.id.welcome_signout);
        if (button2 != null) {
            button2.setOnClickListener(FtueIntroViewController$setupIntroView$2.INSTANCE);
        }
        Button button3 = (Button) view.findViewById(R.id.closeButton);
        if (button3 != null) {
            button3.setOnClickListener(FtueIntroViewController$setupIntroView$3.INSTANCE);
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NotNull
    public View makeView(@NotNull LayoutInflater inflater, @NotNull ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Intrinsics.checkParameterIsNotNull(viewGroup, "viewGroup");
        View view = inflater.inflate(R.layout.ftue_intro_screen, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        setupIntroView(view);
        return view;
    }
}
