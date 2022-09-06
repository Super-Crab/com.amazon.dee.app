package com.amazon.dee.app.ui.main;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.amazon.alexa.identity.api.AccountService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.comms.config.util.DeviceConfigConstants;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.logging.Log;
import com.android.tools.r8.GeneratedOutlineSupport1;
import javax.inject.Provider;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class LoginForTestUi {
    private static final String TAG = Log.tag(LoginForTestUi.class);
    private final Provider<AccountService> accountService;

    /* loaded from: classes12.dex */
    private static abstract class TextWatcherBase implements TextWatcher {
        private TextWatcherBase() {
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }
    }

    public LoginForTestUi(Provider<AccountService> provider) {
        this.accountService = provider;
    }

    private void addHiddenWidget(FrameLayout frameLayout, TextView textView, float f) {
        textView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        textView.setX(-1000.0f);
        textView.setY(f);
        frameLayout.addView(textView);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$loginForTesting$0(String str, Activity activity, UserIdentity userIdentity) {
        String str2 = TAG;
        Log.i(str2, "User logged in for test:" + str);
        activity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loginForTesting(final Activity activity, final String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        Log.i(TAG, "Starting login for test");
        this.accountService.mo10268get().signInForTesting(str, str2).subscribe(new Action1() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$LoginForTestUi$1CW9qFBGzlk4lOPcSJD4jO7KXv4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LoginForTestUi.lambda$loginForTesting$0(str, activity, (UserIdentity) obj);
            }
        }, new Action1() { // from class: com.amazon.dee.app.ui.main.-$$Lambda$LoginForTestUi$-Ad5rrGyNm_19SAXz3zGGOS7O40
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                Log.e(LoginForTestUi.TAG, (Throwable) obj, GeneratedOutlineSupport1.outline72("Failed to log in for test:", str), new Object[0]);
            }
        });
    }

    public void addHiddenLoginWidgets(final Activity activity) {
        View findViewById = activity.findViewById(16908290);
        if (findViewById instanceof FrameLayout) {
            Context context = findViewById.getContext();
            final EditText editText = new EditText(context);
            final EditText editText2 = new EditText(context);
            editText.setId(R.id.test_login_id);
            editText.setText("");
            editText.setWidth(DeviceConfigConstants.VIDEO_BITRATE_600_KBPS);
            editText.addTextChangedListener(new TextWatcherBase() { // from class: com.amazon.dee.app.ui.main.LoginForTestUi.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    Log.i(LoginForTestUi.TAG, "LoginId set");
                    LoginForTestUi.this.loginForTesting(activity, editText.getText().toString(), editText2.getText().toString());
                }
            });
            FrameLayout frameLayout = (FrameLayout) findViewById;
            addHiddenWidget(frameLayout, editText, -2000.0f);
            editText2.setId(R.id.test_login_password);
            editText2.setText("");
            editText2.setWidth(DeviceConfigConstants.VIDEO_BITRATE_600_KBPS);
            editText2.addTextChangedListener(new TextWatcherBase() { // from class: com.amazon.dee.app.ui.main.LoginForTestUi.2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super();
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                    Log.i(LoginForTestUi.TAG, "Password set");
                    LoginForTestUi.this.loginForTesting(activity, editText.getText().toString(), editText2.getText().toString());
                }
            });
            addHiddenWidget(frameLayout, editText2, -1800.0f);
        }
    }
}
