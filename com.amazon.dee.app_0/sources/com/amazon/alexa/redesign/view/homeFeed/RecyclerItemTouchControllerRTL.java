package com.amazon.alexa.redesign.view.homeFeed;

import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.redesign.R;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes10.dex */
public class RecyclerItemTouchControllerRTL extends RecyclerItemTouchController {
    private ButtonState buttonState;
    private final List<View> foregroundViewList;
    private RecyclerView.ViewHolder previousViewHolder;
    private boolean swipeBack;

    public RecyclerItemTouchControllerRTL() {
        super(0, 8);
        this.swipeBack = false;
        this.buttonState = ButtonState.GONE;
        this.previousViewHolder = null;
        this.foregroundViewList = new ArrayList();
    }

    private void setTouchListener(final RecyclerView recyclerView, final float f) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() { // from class: com.amazon.alexa.redesign.view.homeFeed.-$$Lambda$RecyclerItemTouchControllerRTL$tUKNUqIZo-khtuohMplDaY304Qg
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return RecyclerItemTouchControllerRTL.this.lambda$setTouchListener$0$RecyclerItemTouchControllerRTL(recyclerView, f, view, motionEvent);
            }
        });
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerItemTouchController, androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        ItemTouchHelper.Callback.getDefaultUIUtil().clearView(((BaseCardViewHolder) viewHolder).getCardForegroundView());
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerItemTouchController, androidx.recyclerview.widget.ItemTouchHelper.Callback
    public int convertToAbsoluteDirection(int i, int i2) {
        if (this.swipeBack) {
            this.swipeBack = false;
            return 0;
        }
        return super.convertToAbsoluteDirection(i, i2);
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerItemTouchController, androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
    public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerViewItem viewItem = ((HomeAdapter) recyclerView.getAdapter()).getViewItem(viewHolder.getLayoutPosition());
        if (viewItem == null || !viewItem.isDismissible()) {
            return 0;
        }
        return super.getSwipeDirs(recyclerView, viewHolder);
    }

    public /* synthetic */ boolean lambda$setTouchListener$0$RecyclerItemTouchControllerRTL(RecyclerView recyclerView, float f, View view, MotionEvent motionEvent) {
        boolean z = true;
        if (motionEvent.getAction() != 3 && motionEvent.getAction() != 1) {
            z = false;
        }
        this.swipeBack = z;
        if (this.swipeBack && f > recyclerView.getResources().getDimension(R.dimen.amahc_width_large_80)) {
            this.buttonState = ButtonState.VISIBLE;
        }
        return false;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerItemTouchController, androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        View cardForegroundView = ((BaseCardViewHolder) viewHolder).getCardForegroundView();
        if (!this.foregroundViewList.contains(cardForegroundView)) {
            this.foregroundViewList.add(cardForegroundView);
        }
        if (i == 1) {
            RecyclerView.ViewHolder viewHolder2 = this.previousViewHolder;
            if (viewHolder != viewHolder2) {
                if (viewHolder2 != null && ((BaseCardViewHolder) viewHolder2).getCardForegroundView().isAttachedToWindow()) {
                    ((BaseCardViewHolder) this.previousViewHolder).getCardForegroundView().setX(0.0f);
                }
                this.previousViewHolder = viewHolder;
            }
            int i2 = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
            if (i2 <= 0 && this.buttonState == ButtonState.GONE) {
                ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(canvas, recyclerView, cardForegroundView, 0.0f, f2, i, z);
                return;
            } else if (this.buttonState != ButtonState.GONE) {
                if (i2 < 0) {
                    setTouchListener(recyclerView, f);
                    float max = Math.max(recyclerView.getResources().getDimension(R.dimen.amahc_width_large_80) + f, 0.0f);
                    ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(canvas, recyclerView, cardForegroundView, max, f2, i, z);
                    if (max != 0.0f) {
                        return;
                    }
                    setDefaultSwipeDirs(8);
                    this.buttonState = ButtonState.GONE;
                    return;
                }
                ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(canvas, recyclerView, cardForegroundView, Math.max(f, recyclerView.getResources().getDimension(R.dimen.amahc_width_large_80)), f2, i, z);
                setDefaultSwipeDirs(12);
                return;
            } else {
                setTouchListener(recyclerView, f);
            }
        }
        if (this.buttonState == ButtonState.GONE) {
            ItemTouchHelper.Callback.getDefaultUIUtil().onDraw(canvas, recyclerView, cardForegroundView, f, f2, i, z);
        }
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerItemTouchController, androidx.recyclerview.widget.ItemTouchHelper.Callback
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        return false;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerItemTouchController, androidx.recyclerview.widget.ItemTouchHelper.Callback
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerItemTouchController
    public void resetButtonState() {
        this.buttonState = ButtonState.GONE;
        this.swipeBack = false;
    }

    @Override // com.amazon.alexa.redesign.view.homeFeed.RecyclerItemTouchController
    public void revokeSwipeExperience() {
        for (View view : this.foregroundViewList) {
            if (view.isAttachedToWindow()) {
                view.setX(0.0f);
            }
        }
    }
}
