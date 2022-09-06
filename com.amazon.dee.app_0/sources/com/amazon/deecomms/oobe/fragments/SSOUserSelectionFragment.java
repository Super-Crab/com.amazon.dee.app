package com.amazon.deecomms.oobe.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.oobe.fragments.SSOUserSelectionFragment;
import org.json.JSONArray;
import org.json.JSONException;
/* loaded from: classes12.dex */
public class SSOUserSelectionFragment extends MainOOBEFragment {
    private final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, SSOUserSelectionFragment.class);
    private OOBEActivity mOOBEActivity;
    private ProgressBar progressBar;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class UserListCallback implements IHttpClient.Callback {
        private UserListCallback() {
        }

        public /* synthetic */ void lambda$onFailure$0$SSOUserSelectionFragment$UserListCallback() {
            SSOUserSelectionFragment.this.progressBar.setVisibility(8);
        }

        public /* synthetic */ void lambda$onResult$1$SSOUserSelectionFragment$UserListCallback() {
            SSOUserSelectionFragment.this.progressBar.setVisibility(8);
        }

        @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
        public void onFailure(IHttpClient.Call call, ServiceException serviceException) {
            SSOUserSelectionFragment.this.LOG.e("Error retrieving user list", serviceException);
            if (!SSOUserSelectionFragment.this.isActivityAvailable()) {
                SSOUserSelectionFragment.this.LOG.w("UserSelectionFragment seems to be detached from the OOBEActivity");
                return;
            }
            SSOUserSelectionFragment.this.mOOBEActivity.onError(serviceException, MetricKeys.SCREEN_NAME_OOBE_USER_SELECTION, AlertSource.newRequestSource(serviceException));
            SSOUserSelectionFragment.this.mOOBEActivity.runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.-$$Lambda$SSOUserSelectionFragment$UserListCallback$v4voF-FzGtQynvLbrErCMS6r6rU
                @Override // java.lang.Runnable
                public final void run() {
                    SSOUserSelectionFragment.UserListCallback.this.lambda$onFailure$0$SSOUserSelectionFragment$UserListCallback();
                }
            });
        }

        @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
        public void onResult(IHttpClient.Call call, IHttpClient.Response response) {
            if (!SSOUserSelectionFragment.this.isActivityAvailable()) {
                SSOUserSelectionFragment.this.LOG.w("UserSelectionFragment seems to be detached from the OOBEActivity");
                return;
            }
            try {
                JSONArray jSONArray = new JSONArray(response.getBody());
                for (int i = 0; i < jSONArray.length(); i++) {
                    Person create = Person.create(jSONArray.getJSONObject(i));
                    if (!create.isChild) {
                        SSOUserSelectionFragment.this.selectPrimaryPerson(create);
                        return;
                    }
                }
            } catch (JSONException e) {
                SSOUserSelectionFragment.this.LOG.e("Error parsing name list response", e);
            }
            SSOUserSelectionFragment.this.mOOBEActivity.runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.-$$Lambda$SSOUserSelectionFragment$UserListCallback$sFCnR9sM_dPE7OZe9kAMAoosc8c
                @Override // java.lang.Runnable
                public final void run() {
                    SSOUserSelectionFragment.UserListCallback.this.lambda$onResult$1$SSOUserSelectionFragment$UserListCallback();
                }
            });
        }

        /* synthetic */ UserListCallback(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void selectPrimaryPerson(@NonNull Person person) {
        String str = person.directedId;
        if (str == null || str.isEmpty()) {
            MetricsHelper.recordOperationalMetricWithSource(MetricKeys.VALUE_EMPTY_ROOT_DIRECT_ID, "selectPrimaryPerson");
        }
        this.mOOBEActivity.selectUser(person, false);
        Utils.writeStringPreferenceToSharedPrefs(this.mOOBEActivity.getApplicationContext(), Constants.ROOT_DIRECTED_ID, str);
        this.LOG.d("Stored the root directed id in shared preferences.");
    }

    public /* synthetic */ void lambda$onCreateView$0$SSOUserSelectionFragment() {
        new ACMSClient(MetricKeys.OP_GET_USERS_FOR_OOBE).request(AppUrl.OOBE_ACCOUNTS).authenticated().get().enqueue(new UserListCallback(null));
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.mOOBEActivity = (OOBEActivity) getActivity();
        View inflate = layoutInflater.inflate(R.layout.oobe_sso_user_selection, viewGroup, false);
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.us_progress_bar);
        this.progressBar.setVisibility(0);
        AsyncTask.execute(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.-$$Lambda$SSOUserSelectionFragment$-mDHo0fwlzZc4TMZYeVNfJnUyDA
            @Override // java.lang.Runnable
            public final void run() {
                SSOUserSelectionFragment.this.lambda$onCreateView$0$SSOUserSelectionFragment();
            }
        });
        return inflate;
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment
    public void recordOobePageStartMetric() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PAGE_USER_SELECTION_START);
    }
}
