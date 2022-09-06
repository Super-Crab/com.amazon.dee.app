package com.amazon.deecomms.calling.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.ToneGenerator;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.AttrRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.amazon.comms.calling.service.Call;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.sip.SipClientState;
import com.amazon.deecomms.common.ui.CommsButton;
import com.amazon.deecomms.common.ui.CommsTextView;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
/* loaded from: classes12.dex */
public class DialPad extends LinearLayout {
    private static final String DEFAULT_DIAL_MODE = "dtmf";
    public static final String DIAL_MODE_DTMF = "dtmf";
    public static final String DIAL_MODE_NUMBER_ENTRY = "numberEntry";
    public static final int DTMF_LOCAL_TONE_LENGTH = 200;
    public static final int INVALID_TONE = -1;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, DialPad.class);
    private final List<Integer> buttonIds;
    private CommsTextView dialerDisplay;
    @NonNull
    private String mode;
    String numberDialed;
    private DialPadShowCallback showCallback;
    @Inject
    @Named(Constants.Dagger.CURRENT_CALL_SIPSTATE)
    SipClientState sipClientState;
    private ToneGenerator toneGenerator;

    /* loaded from: classes12.dex */
    public interface DialPadShowCallback {
        void showDialPad(boolean z);
    }

    public DialPad(@NonNull Context context) {
        this(context, null, 0, 0);
    }

    private int charToToneId(char c) {
        char lowerCase = Character.toLowerCase(c);
        if (lowerCase != '#') {
            if (lowerCase == '*') {
                return 10;
            }
            switch (lowerCase) {
                case '0':
                    return 0;
                case '1':
                    return 1;
                case '2':
                    return 2;
                case '3':
                    return 3;
                case '4':
                    return 4;
                case '5':
                    return 5;
                case '6':
                    return 6;
                case '7':
                    return 7;
                case '8':
                    return 8;
                case '9':
                    return 9;
                default:
                    switch (lowerCase) {
                        case 'a':
                            return 12;
                        case 'b':
                            return 13;
                        case 'c':
                            return 14;
                        case 'd':
                            return 15;
                        default:
                            return -1;
                    }
            }
        }
        return 11;
    }

    private void createToneGenerator() {
        if (this.toneGenerator != null) {
            return;
        }
        LOG.d("Creating tone generator...");
        try {
            this.toneGenerator = new ToneGenerator(8, 100);
            LOG.d("Created tone generator with volume=100");
        } catch (Exception e) {
            LOG.e("Error creating the local tone generator", e);
        }
    }

    private void releaseToneGenerator() {
        if (this.toneGenerator == null) {
            return;
        }
        LOG.d("Releasing tone generator...");
        this.toneGenerator.stopTone();
        this.toneGenerator.release();
        this.toneGenerator = null;
        LOG.d("Released tone generator.");
    }

    private void sendAndPlayTones(@NonNull Call call, @NonNull String str) {
        LOG.v(String.format("sendAndPlayTones: call=%s, tones=%s", call.getCallId(), str));
        call.sendDtmfTones(str, 500, 500);
        for (int i = 0; this.toneGenerator != null && i < str.length(); i++) {
            int charToToneId = charToToneId(str.charAt(i));
            if (charToToneId != -1) {
                this.toneGenerator.startTone(charToToneId, 200);
            }
        }
    }

    private void updateDialedNumberDisplay() {
        CommsTextView commsTextView = this.dialerDisplay;
        if (commsTextView != null) {
            commsTextView.setText(this.numberDialed);
        }
    }

    public void dial(@NonNull String str) {
        if ("dtmf".equals(this.mode)) {
            Call currentActiveCall = this.sipClientState.getCurrentActiveCall();
            if (currentActiveCall != null) {
                sendAndPlayTones(currentActiveCall, str);
            } else {
                LOG.w("No active call");
            }
        }
        this.numberDialed = this.numberDialed.concat(str);
        updateDialedNumberDisplay();
    }

    @NonNull
    public String getMode() {
        return this.mode;
    }

    public /* synthetic */ void lambda$onAttachedToWindow$0$DialPad(View view) {
        DialPadShowCallback dialPadShowCallback = this.showCallback;
        if (dialPadShowCallback != null) {
            dialPadShowCallback.showDialPad(false);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LOG.d("onAttachedToWindow");
        for (Integer num : this.buttonIds) {
            ((DialPadButton) findViewById(num.intValue())).setDialPad(this);
        }
        this.dialerDisplay = (CommsTextView) findViewById(R.id.dial_pad_display);
        ((CommsButton) findViewById(R.id.dial_pad_hide)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.calling.ui.-$$Lambda$DialPad$dHaQEITxiIHI2WjwtgrO0oGwyCg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DialPad.this.lambda$onAttachedToWindow$0$DialPad(view);
            }
        });
        if (isInEditMode()) {
            this.numberDialed = "123456";
            updateDialedNumberDisplay();
        }
        if (isInEditMode() || !"dtmf".equals(getMode())) {
            return;
        }
        createToneGenerator();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        LOG.d("onDetachedFromWindow");
        releaseToneGenerator();
    }

    public void placeCall() {
    }

    public void removeDialedNumber(boolean z) {
        if (z) {
            this.numberDialed = "";
        } else if (!TextUtils.isEmpty(this.numberDialed)) {
            this.numberDialed = GeneratedOutlineSupport1.outline50(this.numberDialed, -1, 0);
        }
        updateDialedNumberDisplay();
    }

    public void setMode(@NonNull String str) {
        if (!"dtmf".equals(str) && !DIAL_MODE_NUMBER_ENTRY.equals(str)) {
            this.mode = "dtmf";
        } else {
            this.mode = str;
        }
    }

    public void setShowCallback(DialPadShowCallback dialPadShowCallback) {
        this.showCallback = dialPadShowCallback;
    }

    public DialPad(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0, 0);
    }

    public DialPad(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i) {
        this(context, attributeSet, i, 0);
    }

    public DialPad(@NonNull Context context, @Nullable AttributeSet attributeSet, @AttrRes int i, @StyleRes int i2) {
        super(context, attributeSet, i, i2);
        this.mode = "dtmf";
        this.numberDialed = "";
        this.buttonIds = Arrays.asList(Integer.valueOf(R.id.dial_pad_one), Integer.valueOf(R.id.dial_pad_two), Integer.valueOf(R.id.dial_pad_three), Integer.valueOf(R.id.dial_pad_four), Integer.valueOf(R.id.dial_pad_five), Integer.valueOf(R.id.dial_pad_six), Integer.valueOf(R.id.dial_pad_seven), Integer.valueOf(R.id.dial_pad_eight), Integer.valueOf(R.id.dial_pad_nine), Integer.valueOf(R.id.dial_pad_zero), Integer.valueOf(R.id.dial_pad_star), Integer.valueOf(R.id.dial_pad_pound));
        if (!isInEditMode()) {
            CommsDaggerWrapper.getComponent().inject(this);
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DialPad);
        String string = obtainStyledAttributes.getString(R.styleable.DialPad_mode);
        if (string != null) {
            setMode(string.toString());
        } else {
            setMode("dtmf");
        }
        obtainStyledAttributes.recycle();
    }
}
