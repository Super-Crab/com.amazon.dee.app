package amazon.alexa.locale;

import amazon.alexa.locale.AlexaLocaleHelper;
import amazon.alexa.locale.util.LogUtil;
import android.content.Context;
import android.util.Log;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class AlexaLocaleListener implements AlexaLocaleHelper.IAlexaLocaleListener {
    private static final String LOG_TAG = LogUtil.getTag(AlexaLocaleHelper.class);
    private final WeakReference<Context> mContext;

    public AlexaLocaleListener(Context context) {
        this.mContext = new WeakReference<>(context);
    }

    @Override // amazon.alexa.locale.AlexaLocaleHelper.IAlexaLocaleListener
    public void onAlexaLocaleChanged() {
        Context context = this.mContext.get();
        if (context != null) {
            AlexaLocaleHelper.useAlexaLocale(context);
        } else {
            Log.i(LOG_TAG, "onAlexaLocaleChanged() was called, but the context is gone.");
        }
    }
}
