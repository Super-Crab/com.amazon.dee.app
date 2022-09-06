package com.amazon.alexa.voice.ui.onedesign.sports.scores;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.util.AttributesUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
import java.util.List;
/* loaded from: classes11.dex */
public class SportsScoresComponentsView extends View {
    private static final int DIVIDER_OPACITY = 25;
    private List<CharSequence> awayTeamComponents;
    private CharSequence awayTeamName;
    private final int dividerHeightPx;
    private List<CharSequence> headings;
    private List<CharSequence> homeTeamComponents;
    private CharSequence homeTeamName;
    private final int itemMarginPx;
    private final Paint itemTextPaint;
    private final int itemWidthPx;
    private final int rowHeightPx;
    private final int rowOffsetYPx;
    private int textColorPrimary;
    private final int textSizePx;
    private int width;

    public SportsScoresComponentsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Resources resources = context.getResources();
        this.rowHeightPx = resources.getDimensionPixelSize(R.dimen.voice_ui_od_sports_scores_row_height);
        this.itemWidthPx = resources.getDimensionPixelSize(R.dimen.voice_ui_od_sports_scores_item_width);
        this.textSizePx = resources.getDimensionPixelSize(R.dimen.voice_ui_od_font_body_size);
        this.rowOffsetYPx = resources.getDimensionPixelSize(R.dimen.voice_ui_od_sports_scores_row_margin) + this.textSizePx;
        this.itemMarginPx = resources.getDimensionPixelOffset(R.dimen.voice_ui_od_sports_scores_item_margin);
        this.dividerHeightPx = resources.getDimensionPixelSize(R.dimen.voice_ui_od_sports_scores_divider_height);
        this.itemTextPaint = makeItemTextPaint();
        this.textColorPrimary = AttributesUtils.getAttributesColor(context, 16842806, R.color.voice_ui_od_dark_content_text_primary);
    }

    private void drawAwayTeamComponents(Canvas canvas) {
        drawComponentsRow(canvas, this.awayTeamComponents, this.rowHeightPx + this.rowOffsetYPx);
    }

    private void drawAwayTeamName(Canvas canvas) {
        this.itemTextPaint.setTextAlign(Paint.Align.LEFT);
        CharSequence charSequence = this.awayTeamName;
        canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, this.rowHeightPx + this.rowOffsetYPx, this.itemTextPaint);
    }

    private void drawComponentsRow(Canvas canvas, List<CharSequence> list, int i) {
        if (list == null || list.size() <= 0) {
            return;
        }
        for (int i2 = 0; i2 < list.size(); i2++) {
            int size = (this.width - ((list.size() - i2) * this.itemWidthPx)) - (((list.size() - 1) - i2) * this.itemMarginPx);
            CharSequence charSequence = list.get(i2);
            this.itemTextPaint.setTextAlign(Paint.Align.CENTER);
            this.itemTextPaint.setTextSize(getResources().getDimension(R.dimen.voice_ui_od_font_subhead_size));
            this.itemTextPaint.setColor(this.textColorPrimary);
            canvas.drawText(charSequence, 0, charSequence.length(), size, i, this.itemTextPaint);
        }
    }

    private void drawDividers(Canvas canvas) {
        float[] fArr = new float[12];
        float f = this.dividerHeightPx;
        int i = 0;
        while (i < 3) {
            int i2 = i * 4;
            i++;
            float f2 = (this.rowHeightPx * i) - f;
            fArr[i2] = 0.0f;
            fArr[i2 + 1] = f2;
            fArr[i2 + 2] = this.width;
            fArr[i2 + 3] = f2;
        }
        Paint paint = new Paint();
        paint.setStrokeWidth(f);
        paint.setColor(this.textColorPrimary);
        paint.setAlpha(25);
        canvas.drawLines(fArr, 0, fArr.length, paint);
    }

    private void drawHeadings(Canvas canvas) {
        drawComponentsRow(canvas, this.headings, this.rowOffsetYPx);
    }

    private void drawHomeTeamComponents(Canvas canvas) {
        drawComponentsRow(canvas, this.homeTeamComponents, (this.rowHeightPx * 2) + this.rowOffsetYPx);
    }

    private void drawHomeTeamName(Canvas canvas) {
        this.itemTextPaint.setTextAlign(Paint.Align.LEFT);
        this.itemTextPaint.setTextSize(getResources().getDimension(R.dimen.voice_ui_od_font_subhead_size));
        this.itemTextPaint.setColor(this.textColorPrimary);
        CharSequence charSequence = this.homeTeamName;
        canvas.drawText(charSequence, 0, charSequence.length(), 0.0f, (this.rowHeightPx * 2) + this.rowOffsetYPx, this.itemTextPaint);
    }

    private Paint makeItemTextPaint() {
        Paint paint = new Paint(1);
        paint.setColor(-1);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, getContext(), paint);
        paint.setTextSize(this.textSizePx);
        return paint;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHeadings(canvas);
        drawDividers(canvas);
        drawAwayTeamName(canvas);
        drawAwayTeamComponents(canvas);
        drawHomeTeamName(canvas);
        drawHomeTeamComponents(canvas);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.width = i;
        super.onSizeChanged(i, i2, i3, i4);
    }

    public void setAwayTeamComponents(List<CharSequence> list) {
        this.awayTeamComponents = list;
        invalidate();
    }

    public void setAwayTeamName(CharSequence charSequence) {
        this.awayTeamName = charSequence;
        invalidate();
    }

    public void setHeadings(List<CharSequence> list) {
        this.headings = list;
        invalidate();
    }

    public void setHomeTeamComponents(List<CharSequence> list) {
        this.homeTeamComponents = list;
        invalidate();
    }

    public void setHomeTeamName(CharSequence charSequence) {
        this.homeTeamName = charSequence;
        invalidate();
    }

    public void setScoreComponents(List<CharSequence> list, List<CharSequence> list2, List<CharSequence> list3) {
        this.headings = list;
        this.homeTeamComponents = list2;
        this.awayTeamComponents = list3;
        invalidate();
    }
}
