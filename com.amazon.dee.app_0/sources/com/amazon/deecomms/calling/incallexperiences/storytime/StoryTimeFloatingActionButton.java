package com.amazon.deecomms.calling.incallexperiences.storytime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.log.annotation.Log;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonPresenterContract;
import com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonViewContract;
/* loaded from: classes12.dex */
public class StoryTimeFloatingActionButton implements StoryTimeButtonViewContract {
    private final ImageButton floatingActionButton;
    private final RelativeLayout floatingActionButtonWrapper;
    private CommsLogger log = CommsLogger.getLogger(StoryTimeFloatingActionButton.class);
    private StoryTimeButtonPresenterContract storyTimeButtonPresenterContract;

    public StoryTimeFloatingActionButton(@NonNull RelativeLayout relativeLayout) {
        this.floatingActionButtonWrapper = relativeLayout;
        this.floatingActionButton = (ImageButton) relativeLayout.getChildAt(0);
        this.floatingActionButton.setClickable(false);
        this.floatingActionButton.setFocusable(true);
        this.log.d("Constructing StoryTimeFloatingActionButtonWrapper");
        this.floatingActionButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.incallexperiences.storytime.StoryTimeFloatingActionButton.1
            @Override // android.view.View.OnClickListener
            @Log(message = "Button Pressed: Start StoryTime")
            public void onClick(View view) {
                StoryTimeFloatingActionButton.this.storyTimeButtonPresenterContract.startStoryTime();
            }
        });
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonViewContract
    public void bindPresenterCallback(@NonNull StoryTimeButtonPresenterContract storyTimeButtonPresenterContract) {
        this.storyTimeButtonPresenterContract = storyTimeButtonPresenterContract;
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonViewContract
    @Log(message = "hideFloatingStoryTimeButton")
    public void hideFloatingStoryTimeButton() {
        this.floatingActionButtonWrapper.setVisibility(8);
    }

    public void setClickable(boolean z) {
        this.floatingActionButtonWrapper.setClickable(z);
        this.floatingActionButton.setClickable(z);
    }

    public void setEnabled(boolean z) {
        this.floatingActionButtonWrapper.setEnabled(z);
        this.floatingActionButton.setEnabled(z);
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonViewContract
    @Log(message = "showFloatingStoryTimeButton")
    public void showFloatingStoryTimeButton() {
        this.floatingActionButtonWrapper.setVisibility(0);
        this.floatingActionButton.setColorFilter(this.floatingActionButtonWrapper.getResources().getColor(R.color.fiesta_btn_on));
        this.floatingActionButton.setBackground(ContextCompat.getDrawable(this.floatingActionButtonWrapper.getContext(), R.drawable.fiesta_btn_enabled_default));
        setEnabled(true);
        setClickable(true);
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonViewContract
    public void showStoryTimeMessage(int i) {
        Context context = this.floatingActionButtonWrapper.getContext();
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.storytime_toast, (ViewGroup) null);
        ((TextView) linearLayout.getChildAt(0)).setText(i);
        Toast toast = new Toast(context);
        toast.setView(linearLayout);
        toast.setGravity(55, 0, 0);
        toast.setDuration(1);
        toast.show();
    }

    @Override // com.amazon.deecomms.calling.incallexperiences.storytime.contracts.StoryTimeButtonViewContract
    public void startStoryTime(boolean z) {
        if (z) {
            this.floatingActionButton.setColorFilter(this.floatingActionButtonWrapper.getResources().getColor(R.color.fiesta_btn_off));
            this.floatingActionButton.setBackground(ContextCompat.getDrawable(this.floatingActionButtonWrapper.getContext(), R.drawable.fiesta_btn_enabled_selected));
            setEnabled(false);
            setClickable(false);
            return;
        }
        hideFloatingStoryTimeButton();
    }

    @VisibleForTesting
    public StoryTimeFloatingActionButton(@NonNull RelativeLayout relativeLayout, View.OnClickListener onClickListener) {
        this.floatingActionButtonWrapper = relativeLayout;
        this.floatingActionButton = (ImageButton) relativeLayout.getChildAt(0);
        this.floatingActionButton.setClickable(false);
        this.floatingActionButton.setFocusable(true);
        this.floatingActionButton.setOnClickListener(onClickListener);
    }
}
