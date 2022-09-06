package com.amazon.alexa.drive.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.R;
/* loaded from: classes7.dex */
public class AutoModeSingleIconGridView extends RelativeLayout {
    public AutoModeSingleIconGridView(Context context) {
        super(context);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        LayoutInflater from = LayoutInflater.from(getContext());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AutoModeSingleIconGridView, 0, 0);
        String string = obtainStyledAttributes.getString(R.styleable.AutoModeSingleIconGridView_single_icon_grid_title);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.AutoModeSingleIconGridView_single_icon_grid_icon, 0);
        obtainStyledAttributes.recycle();
        from.inflate(R.layout.dm_item_single_icon_grid, (ViewGroup) this, true);
        if (!TextUtils.isEmpty(string)) {
            setTitle(string);
        }
        if (resourceId != 0) {
            setIcon(resourceId);
        }
    }

    public void setIcon(int i) {
        ((ImageView) findViewById(R.id.single_icon)).setImageResource(i);
    }

    public void setTitle(String str) {
        ((TextView) findViewById(R.id.title)).setText(str);
    }

    public AutoModeSingleIconGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public AutoModeSingleIconGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }
}
