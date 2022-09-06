package com.amazon.alexa.voice.handsfree;

import android.os.Build;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.amazon.alexa.handsfree.protocols.Initializer;
import com.amazon.alexa.handsfree.protocols.InitializerProvider;
import com.amazon.regulator.ControllerTransaction;
import com.amazon.regulator.Router;
import java.util.Locale;
/* loaded from: classes11.dex */
public class HandsFreePermissionsActivity extends AppCompatActivity {
    private Initializer mInitializer;
    private HandsFreePermissionsActivityPresenter presenter;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        this.mInitializer = InitializerProvider.getInitializer();
        this.mInitializer.initialize(this);
        setContentView(R.layout.handsfree_oobe);
        int i = Build.VERSION.SDK_INT;
        Locale locale = getResources().getConfiguration().getLocales().get(0);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.handsfree_oobe_container);
        this.presenter = new HandsFreePermissionsActivityPresenter(getIntent());
        this.presenter.setupPermissionsRouter(this, locale, bundle, viewGroup, new Router.OnTransactionAdapter() { // from class: com.amazon.alexa.voice.handsfree.HandsFreePermissionsActivity.1
            @Override // com.amazon.regulator.Router.OnTransactionAdapter, com.amazon.regulator.Router.OnTransactionListener
            public void onAfterTransition(ControllerTransaction controllerTransaction) {
                HandsFreePermissionsActivity.this.finish();
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        this.presenter.sendFTUECompleteMessage();
        finish();
    }
}
