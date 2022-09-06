package com.facebook.react.views.picker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.view.ViewCompat;
import com.facebook.react.common.annotations.VisibleForTesting;
import java.util.List;
/* loaded from: classes2.dex */
public class ReactPicker extends AppCompatSpinner {
    private final AdapterView.OnItemSelectedListener mItemSelectedListener;
    @Nullable
    private List<ReactPickerItem> mItems;
    private int mMode;
    @Nullable
    private OnSelectListener mOnSelectListener;
    @Nullable
    private Integer mStagedBackgroundColor;
    @Nullable
    private List<ReactPickerItem> mStagedItems;
    @Nullable
    private Integer mStagedPrimaryTextColor;
    @Nullable
    private Integer mStagedSelection;
    private final Runnable measureAndLayout;

    /* loaded from: classes2.dex */
    public interface OnSelectListener {
        void onItemSelected(int i);
    }

    public ReactPicker(Context context) {
        super(context);
        this.mMode = 0;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.facebook.react.views.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(i);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.facebook.react.views.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void commitStagedData() {
        setOnItemSelectedListener(null);
        ReactPickerAdapter reactPickerAdapter = (ReactPickerAdapter) getAdapter();
        int selectedItemPosition = getSelectedItemPosition();
        List<ReactPickerItem> list = this.mStagedItems;
        if (list != null && list != this.mItems) {
            this.mItems = list;
            this.mStagedItems = null;
            if (reactPickerAdapter == null) {
                reactPickerAdapter = new ReactPickerAdapter(getContext(), this.mItems);
                setAdapter((SpinnerAdapter) reactPickerAdapter);
            } else {
                reactPickerAdapter.clear();
                reactPickerAdapter.addAll(this.mItems);
                reactPickerAdapter.notifyDataSetChanged();
            }
        }
        Integer num = this.mStagedSelection;
        if (num != null && num.intValue() != selectedItemPosition) {
            setSelection(this.mStagedSelection.intValue(), false);
            this.mStagedSelection = null;
        }
        Integer num2 = this.mStagedPrimaryTextColor;
        if (num2 != null && reactPickerAdapter != null && num2 != reactPickerAdapter.getPrimaryTextColor()) {
            reactPickerAdapter.setPrimaryTextColor(this.mStagedPrimaryTextColor);
            ViewCompat.setBackgroundTintList(this, ColorStateList.valueOf(this.mStagedPrimaryTextColor.intValue()));
            this.mStagedPrimaryTextColor = null;
        }
        Integer num3 = this.mStagedBackgroundColor;
        if (num3 != null && reactPickerAdapter != null && num3 != reactPickerAdapter.getBackgroundColor()) {
            reactPickerAdapter.setBackgroundColor(this.mStagedBackgroundColor);
            this.mStagedBackgroundColor = null;
        }
        setOnItemSelectedListener(this.mItemSelectedListener);
    }

    @VisibleForTesting
    public int getMode() {
        return this.mMode;
    }

    @Nullable
    public OnSelectListener getOnSelectListener() {
        return this.mOnSelectListener;
    }

    @Override // android.widget.Spinner, android.widget.AdapterView, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (getOnItemSelectedListener() == null) {
            setOnItemSelectedListener(this.mItemSelectedListener);
        }
    }

    @Override // android.widget.AbsSpinner, android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        post(this.measureAndLayout);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setImmediateSelection(int i) {
        if (i != getSelectedItemPosition()) {
            setOnItemSelectedListener(null);
            setSelection(i, false);
            setOnItemSelectedListener(this.mItemSelectedListener);
        }
    }

    public void setOnSelectListener(@Nullable OnSelectListener onSelectListener) {
        this.mOnSelectListener = onSelectListener;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStagedBackgroundColor(@Nullable Integer num) {
        this.mStagedBackgroundColor = num;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStagedItems(@Nullable List<ReactPickerItem> list) {
        this.mStagedItems = list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStagedPrimaryTextColor(@Nullable Integer num) {
        this.mStagedPrimaryTextColor = num;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStagedSelection(int i) {
        this.mStagedSelection = Integer.valueOf(i);
    }

    public ReactPicker(Context context, int i) {
        super(context, i);
        this.mMode = 0;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.facebook.react.views.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(i2);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.facebook.react.views.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
        this.mMode = i;
    }

    public ReactPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mMode = 0;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.facebook.react.views.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(i2);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.facebook.react.views.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
    }

    public ReactPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mMode = 0;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.facebook.react.views.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(i2);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.facebook.react.views.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
    }

    public ReactPicker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mMode = 0;
        this.mItemSelectedListener = new AdapterView.OnItemSelectedListener() { // from class: com.facebook.react.views.picker.ReactPicker.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i22, long j) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(i22);
                }
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                if (ReactPicker.this.mOnSelectListener != null) {
                    ReactPicker.this.mOnSelectListener.onItemSelected(-1);
                }
            }
        };
        this.measureAndLayout = new Runnable() { // from class: com.facebook.react.views.picker.ReactPicker.2
            @Override // java.lang.Runnable
            public void run() {
                ReactPicker reactPicker = ReactPicker.this;
                reactPicker.measure(View.MeasureSpec.makeMeasureSpec(reactPicker.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(ReactPicker.this.getHeight(), 1073741824));
                ReactPicker reactPicker2 = ReactPicker.this;
                reactPicker2.layout(reactPicker2.getLeft(), ReactPicker.this.getTop(), ReactPicker.this.getRight(), ReactPicker.this.getBottom());
            }
        };
        this.mMode = i2;
    }
}
