package com.amazon.alexa.fitness.view.workoutTab;

import android.view.View;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.accessory.notificationpublisher.utils.NotificationConstants;
import com.amazon.alexa.fitness.ui.R;
import com.amazon.alexa.fitness.utils.AfitsDataHelper;
import com.amazon.alexa.fitness.utils.Fonts;
import com.amazon.alexa.fitness.utils.FormatUtilKt;
import com.amazon.alexa.fitness.utils.FullScreenUtil;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ViewProps;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: FitnessViewHolders.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/fitness/view/workoutTab/ListViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "afitsDataHelper", "Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;", "(Landroid/view/View;Lcom/amazon/alexa/fitness/utils/AfitsDataHelper;)V", "divider", "getDivider", "()Landroid/view/View;", "setDivider", "(Landroid/view/View;)V", NotificationConstants.PRIMARY_TEXT, "Landroid/widget/TextView;", "getPrimaryText", "()Landroid/widget/TextView;", "setPrimaryText", "(Landroid/widget/TextView;)V", NotificationConstants.SECONDARY_TEXT, "getSecondaryText", "setSecondaryText", "updateListItemView", "", "viewHolder", ViewProps.POSITION, "", "AlexaMobileAndroidFitnessUI_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class ListViewHolder extends RecyclerView.ViewHolder {
    private AfitsDataHelper afitsDataHelper;
    @Nullable
    private View divider;
    @Nullable
    private TextView primaryText;
    @Nullable
    private TextView secondaryText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ListViewHolder(@NotNull View itemView, @NotNull AfitsDataHelper afitsDataHelper) {
        super(itemView);
        Intrinsics.checkParameterIsNotNull(itemView, "itemView");
        Intrinsics.checkParameterIsNotNull(afitsDataHelper, "afitsDataHelper");
        this.afitsDataHelper = afitsDataHelper;
        this.primaryText = (TextView) itemView.findViewById(R.id.primaryText);
        this.secondaryText = (TextView) itemView.findViewById(R.id.secondaryText);
        this.divider = itemView.findViewById(R.id.divider);
        CardView cardView = (CardView) itemView.findViewById(R.id.historyItem);
        if (cardView != null) {
            cardView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.fitness.view.workoutTab.ListViewHolder.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Clicked item at position ");
                    outline107.append(ListViewHolder.this.getLayoutPosition());
                    outline107.toString();
                    if (ListViewHolder.this.afitsDataHelper.getWorkoutHistoryList().get(ListViewHolder.this.getLayoutPosition()).getItemType() == FitnessListType.LIST_ITEM) {
                        FullScreenUtil.Companion.goDetailedView(ListViewHolder.this.afitsDataHelper.getWorkoutHistoryList().get(ListViewHolder.this.getLayoutPosition()));
                    }
                }
            });
        }
    }

    @Nullable
    public final View getDivider() {
        return this.divider;
    }

    @Nullable
    public final TextView getPrimaryText() {
        return this.primaryText;
    }

    @Nullable
    public final TextView getSecondaryText() {
        return this.secondaryText;
    }

    public final void setDivider(@Nullable View view) {
        this.divider = view;
    }

    public final void setPrimaryText(@Nullable TextView textView) {
        this.primaryText = textView;
    }

    public final void setSecondaryText(@Nullable TextView textView) {
        this.secondaryText = textView;
    }

    public final void updateListItemView(@NotNull ListViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        WorkoutItem workoutItem = this.afitsDataHelper.getWorkoutHistoryList().get(i);
        TextView textView = viewHolder.primaryText;
        if (textView != null) {
            if (workoutItem != null) {
                textView.setText(FormatUtilKt.formatPrimaryText(FormatUtilKt.convertUTCToLocalTimeZoneDateTime(((ListItem) workoutItem).getFitnessSession().getStartTime())));
                Fonts.EMBER_REGULAR.apply(textView);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.view.workoutTab.ListItem");
            }
        }
        TextView textView2 = viewHolder.secondaryText;
        if (textView2 != null) {
            if (workoutItem != null) {
                String formatSecondaryText = FormatUtilKt.formatSecondaryText((ListItem) workoutItem);
                if (formatSecondaryText == null) {
                    formatSecondaryText = "";
                }
                textView2.setText(formatSecondaryText);
                Fonts.EMBER_REGULAR.apply(textView2);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.amazon.alexa.fitness.view.workoutTab.ListItem");
        }
    }
}
