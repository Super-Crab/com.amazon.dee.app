package com.amazon.alexa.drive.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.mode.util.Fonts;
/* loaded from: classes7.dex */
public class DriveModeSingleLineView extends ConstraintLayout {
    public DriveModeSingleLineView(@NonNull Context context) {
        super(context);
    }

    private void init(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        LayoutInflater from = LayoutInflater.from(getContext());
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DriveModeSingleLineView, 0, 0);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.DriveModeSingleLineView_dm_single_line_image_icon, 0);
        String string = obtainStyledAttributes.getString(R.styleable.DriveModeSingleLineView_dm_single_line_text);
        obtainStyledAttributes.recycle();
        from.inflate(R.layout.dm_item_single_line_left_icon, (ViewGroup) this, true);
        ImageView imageView = (ImageView) findViewById(R.id.single_icon);
        if (resourceId != 0) {
            imageView.setImageResource(resourceId);
        }
        TextView textView = (TextView) findViewById(R.id.title);
        Fonts.EMBER_REGULAR.apply(textView);
        if (!TextUtils.isEmpty(string)) {
            textView.setText(string);
        }
    }

    public String getText() {
        return ((TextView) findViewById(R.id.title)).getText().toString();
    }

    public void setImage(Drawable drawable) {
        ((ImageView) findViewById(R.id.single_icon)).setImageDrawable(drawable);
    }

    public void setText(String str) {
        ((TextView) findViewById(R.id.title)).setText(str);
    }

    public void showUnderLine(boolean z) {
        findViewById(R.id.dm_location_underline).setVisibility(z ? 0 : 8);
    }

    public DriveModeSingleLineView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public DriveModeSingleLineView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }
}
