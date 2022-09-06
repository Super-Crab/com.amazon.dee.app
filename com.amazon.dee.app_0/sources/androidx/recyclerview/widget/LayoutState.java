package androidx.recyclerview.widget;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes.dex */
class LayoutState {
    static final int INVALID_LAYOUT = Integer.MIN_VALUE;
    static final int ITEM_DIRECTION_HEAD = -1;
    static final int ITEM_DIRECTION_TAIL = 1;
    static final int LAYOUT_END = 1;
    static final int LAYOUT_START = -1;
    static final String TAG = "LayoutState";
    int mAvailable;
    int mCurrentPosition;
    boolean mInfinite;
    int mItemDirection;
    int mLayoutDirection;
    boolean mStopInFocusable;
    boolean mRecycle = true;
    int mStartLine = 0;
    int mEndLine = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasMore(RecyclerView.State state) {
        int i = this.mCurrentPosition;
        return i >= 0 && i < state.getItemCount();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public View next(RecyclerView.Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(this.mCurrentPosition);
        this.mCurrentPosition += this.mItemDirection;
        return viewForPosition;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LayoutState{mAvailable=");
        outline107.append(this.mAvailable);
        outline107.append(", mCurrentPosition=");
        outline107.append(this.mCurrentPosition);
        outline107.append(", mItemDirection=");
        outline107.append(this.mItemDirection);
        outline107.append(", mLayoutDirection=");
        outline107.append(this.mLayoutDirection);
        outline107.append(", mStartLine=");
        outline107.append(this.mStartLine);
        outline107.append(", mEndLine=");
        return GeneratedOutlineSupport1.outline85(outline107, this.mEndLine, JsonReaderKt.END_OBJ);
    }
}
