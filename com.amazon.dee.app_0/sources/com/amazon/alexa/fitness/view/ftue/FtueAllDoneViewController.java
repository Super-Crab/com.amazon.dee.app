package com.amazon.alexa.fitness.view.ftue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.viewmanagement.api.ViewController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: FtueAllDoneViewController.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0002¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/fitness/view/ftue/FtueAllDoneViewController;", "Lcom/amazon/alexa/viewmanagement/api/ViewController;", "()V", "makeView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "viewGroup", "Landroid/view/ViewGroup;", "setTextFont", "", "view", "setupAllDoneView", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class FtueAllDoneViewController implements ViewController {
    private final void setTextFont(View view) {
        if (((TextView) view.findViewById(R.id.all_done)) != null) {
            Fonts fonts = Fonts.EMBER_BOLD;
        }
        if (((TextView) view.findViewById(R.id.to_start_workout)) != null) {
            Fonts fonts2 = Fonts.EMBER_REGULAR;
        }
        if (((TextView) view.findViewById(R.id.hint1)) != null) {
            Fonts fonts3 = Fonts.BOOKERLY_ITALIC;
        }
        if (((TextView) view.findViewById(R.id.hint2)) != null) {
            Fonts fonts4 = Fonts.BOOKERLY_ITALIC;
        }
        if (((TextView) view.findViewById(R.id.hint3)) != null) {
            Fonts fonts5 = Fonts.BOOKERLY_ITALIC;
        }
        if (((Button) view.findViewById(R.id.get_started)) != null) {
            Fonts fonts6 = Fonts.EMBER_BOLD;
        }
    }

    private final void setupAllDoneView(View view) {
        setTextFont(view);
        Button button = (Button) view.findViewById(R.id.get_started);
        if (button != null) {
            button.setOnClickListener(FtueAllDoneViewController$setupAllDoneView$1.INSTANCE);
        }
    }

    @Override // com.amazon.alexa.viewmanagement.api.ViewController
    @NotNull
    public View makeView(@NotNull LayoutInflater inflater, @NotNull ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(inflater, "inflater");
        Intrinsics.checkParameterIsNotNull(viewGroup, "viewGroup");
        View view = inflater.inflate(R.layout.ftue_all_done, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(view, "view");
        setupAllDoneView(view);
        return view;
    }
}
