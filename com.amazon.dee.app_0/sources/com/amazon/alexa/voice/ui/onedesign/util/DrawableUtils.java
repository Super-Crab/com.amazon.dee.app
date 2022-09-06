package com.amazon.alexa.voice.ui.onedesign.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import androidx.core.view.ViewCompat;
import com.amazon.alexa.voice.ui.tta.search.ItemPosition;
import com.amazon.alexa.vox.ui.onedesign.R;
/* loaded from: classes11.dex */
public final class DrawableUtils {

    /* renamed from: com.amazon.alexa.voice.ui.onedesign.util.DrawableUtils$1  reason: invalid class name */
    /* loaded from: classes11.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$voice$ui$tta$search$ItemPosition = new int[ItemPosition.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$tta$search$ItemPosition[ItemPosition.FIRST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$tta$search$ItemPosition[ItemPosition.LAST.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$voice$ui$tta$search$ItemPosition[ItemPosition.MID.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private DrawableUtils() {
        throw new IllegalStateException("No instances!");
    }

    public static Drawable createSeparatorDrawable(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(new int[]{R.attr.voiceOdLineSeparatorColor});
        int color = obtainStyledAttributes.getColor(0, ViewCompat.MEASURED_STATE_MASK);
        obtainStyledAttributes.recycle();
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RectShape());
        shapeDrawable.setIntrinsicHeight(DisplayUtils.transformDpiToPx(context, 1));
        shapeDrawable.getPaint().setColor(color);
        return shapeDrawable;
    }

    public static Drawable getBackgroundForInChatViewHolder(Context context, ItemPosition itemPosition) {
        int ordinal = itemPosition.ordinal();
        if (ordinal != 0) {
            if (ordinal != 2) {
                return context.getDrawable(R.drawable.text_ui_od_alexa_in_chat_mid_background);
            }
            return context.getDrawable(R.drawable.text_ui_od_alexa_in_chat_bottom_background);
        }
        return context.getDrawable(R.drawable.text_ui_od_alexa_in_chat_top_background);
    }
}
