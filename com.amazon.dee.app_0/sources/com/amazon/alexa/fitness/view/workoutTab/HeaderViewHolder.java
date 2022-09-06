package com.amazon.alexa.fitness.view.workoutTab;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.AfitsDataHelper;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.fitness.utils.FormatUtilKt;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessViewHolders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/HeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "afitsDataHelper", "Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "(Landroid/view/View;Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;)V", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "setText", "(Landroid/widget/TextView;)V", "updateHeaderView", "", "viewHolder", ViewProps.POSITION, "", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HeaderViewHolder extends RecyclerView.ViewHolder {
    private AfitsDataHelper afitsDataHelper;
    @Nullable
    private TextView text;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HeaderViewHolder(@NotNull View itemView, @NotNull AfitsDataHelper afitsDataHelper) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        Intrinsics.checkParameterIsNotNull(afitsDataHelper, "afitsDataHelper");
        this.afitsDataHelper = afitsDataHelper;
        this.text = (TextView) itemView.findViewById(R.id.headerText);
    }

    @Nullable
    public final TextView getText() {
        return this.text;
    }

    public final void setText(@Nullable TextView textView) {
        this.text = textView;
    }

    public final void updateHeaderView(@NotNull HeaderViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        WorkoutItem workoutItem = this.afitsDataHelper.getWorkoutHistoryList().get(i);
        if (workoutItem.getItemType() != FitnessListType.WEEKLY_VIEW) {
            TextView textView = viewHolder.text;
            if (textView != null) {
                textView.setText(FormatUtilKt.getFormattedFullDate(((HeaderItem) workoutItem).getText()));
            }
            Fonts.EMBER_BOLD.apply(textView);
        }
    }
}
