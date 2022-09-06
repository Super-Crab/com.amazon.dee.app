package com.amazon.deecomms.calling.ui;

import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.deecomms.R;
import com.amazon.deecomms.calling.controller.EndCallActionClickListener;
/* loaded from: classes12.dex */
public class EndCallFragmentViewHolder {
    private TextView mAcknowledgeText;
    private TextView mCallDuration;
    private RelativeLayout mCallRatingLayout;
    private RelativeLayout mCallRatingSheet;
    private TextView mDescription;
    private TextView mEndCallStatus;
    private final EndCallActionClickListener mListener;
    private TextView mParticipantName;
    private Button mRateCallButton;
    private RatingBar mRatingBar;
    private final View mRoot;
    private Button mSkipButton;

    public EndCallFragmentViewHolder(EndCallActionClickListener endCallActionClickListener, View view) {
        this.mListener = endCallActionClickListener;
        this.mRoot = view;
        initializeViews();
        initializeListeners();
    }

    private void downscaleDescription() {
        this.mDescription.setAlpha(0.8f);
        this.mDescription.setTextSize(14.0f);
        this.mDescription.setVisibility(0);
    }

    private void initializeListeners() {
        this.mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$EndCallFragmentViewHolder$egQHV9Wo0EcBycwDCaGDOqbi7pk
            @Override // android.widget.RatingBar.OnRatingBarChangeListener
            public final void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
                EndCallFragmentViewHolder.this.lambda$initializeListeners$0$EndCallFragmentViewHolder(ratingBar, f, z);
            }
        });
        this.mRateCallButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$EndCallFragmentViewHolder$XQUYUqbVSoFdK5ZQHzkwcRlzpzw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EndCallFragmentViewHolder.this.lambda$initializeListeners$1$EndCallFragmentViewHolder(view);
            }
        });
        this.mSkipButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$EndCallFragmentViewHolder$d8eGSrecLtBIxLlUsuGJ7cOzSjc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EndCallFragmentViewHolder.this.lambda$initializeListeners$2$EndCallFragmentViewHolder(view);
            }
        });
    }

    private void initializeViews() {
        this.mCallRatingLayout = (RelativeLayout) this.mRoot.findViewById(R.id.call_rating_layout);
        this.mRateCallButton = (Button) this.mRoot.findViewById(R.id.rate_call_button);
        this.mCallRatingSheet = (RelativeLayout) this.mRoot.findViewById(R.id.call_rating_sheet);
        this.mDescription = (TextView) this.mRoot.findViewById(R.id.description);
        this.mRatingBar = (RatingBar) this.mRoot.findViewById(R.id.rating_bar);
        this.mAcknowledgeText = (TextView) this.mRoot.findViewById(R.id.acknowledge_text);
        this.mSkipButton = (Button) this.mRoot.findViewById(R.id.skip_button);
        this.mParticipantName = (TextView) this.mRoot.findViewById(R.id.callParticipantName);
        this.mCallDuration = (TextView) this.mRoot.findViewById(R.id.screen_title);
        this.mEndCallStatus = (TextView) this.mRoot.findViewById(R.id.callStatus);
    }

    private void upscaleDescription() {
        this.mDescription.setAlpha(1.0f);
        this.mDescription.setTextSize(18.0f);
        this.mDescription.setVisibility(0);
    }

    public void disableBarRating() {
        this.mRatingBar.setIsIndicator(true);
    }

    public void hideBottomSheet() {
        this.mCallRatingLayout.setVisibility(4);
        this.mRateCallButton.setVisibility(8);
        this.mCallRatingSheet.setVisibility(8);
    }

    public void hideRateCallAndShowCallRatingSheet() {
        this.mCallRatingLayout.setVisibility(0);
        this.mRateCallButton.setVisibility(8);
        this.mCallRatingSheet.setVisibility(0);
    }

    public void hideSkipAndShowThanks(@NonNull String str) {
        this.mDescription.setText(str);
        upscaleDescription();
        this.mSkipButton.setVisibility(8);
        this.mAcknowledgeText.setVisibility(0);
    }

    public /* synthetic */ void lambda$initializeListeners$0$EndCallFragmentViewHolder(RatingBar ratingBar, float f, boolean z) {
        if (z) {
            this.mListener.onStarsClicked(Math.round(f));
        }
    }

    public /* synthetic */ void lambda$initializeListeners$1$EndCallFragmentViewHolder(View view) {
        this.mListener.onRateCallClicked();
    }

    public /* synthetic */ void lambda$initializeListeners$2$EndCallFragmentViewHolder(View view) {
        this.mListener.onSkipClicked();
    }

    public void showRateCallAndHideCallRatingSheet() {
        this.mCallRatingLayout.setVisibility(0);
        this.mRateCallButton.setVisibility(0);
        this.mCallRatingSheet.setVisibility(8);
    }

    public void showSkipAndHideThanks(@NonNull String str) {
        this.mDescription.setText(str);
        downscaleDescription();
        this.mSkipButton.setVisibility(0);
        this.mAcknowledgeText.setVisibility(8);
    }

    public void updateViews(String str, String str2, String str3) {
        this.mParticipantName.setText(str);
        this.mCallDuration.setText(str2);
        this.mEndCallStatus.setText(str3);
        TextView textView = this.mParticipantName;
        textView.setContentDescription(str + " " + str3);
    }
}
