package amazon.alexa.locale;

import android.app.Activity;
import android.os.Bundle;
/* loaded from: classes.dex */
public class AlexaLocaleActivity extends Activity {
    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AlexaLocaleHelper.registerListener(this, new AlexaLocaleListener(this));
        AlexaLocaleHelper.useAlexaLocale(this);
    }
}
