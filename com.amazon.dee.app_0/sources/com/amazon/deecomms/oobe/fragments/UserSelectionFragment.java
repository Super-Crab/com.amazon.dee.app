package com.amazon.deecomms.oobe.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import com.amazon.deecomms.oobe.OOBEActivity;
import com.amazon.deecomms.oobe.PCAProfile;
import com.amazon.deecomms.oobe.Person;
import com.amazon.deecomms.oobe.fragments.UserSelectionFragment;
import java.util.ArrayList;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes12.dex */
public class UserSelectionFragment extends MainOOBEFragment {
    private static final String INCLUDE_PCA_ACTORS_PATH_PARAMETER_SUFFIX = "?includeActors=true";
    private static final PersonItem PLACEHOLDER = new PersonItem();
    private PersonArrayAdapter adapter;
    private OOBEActivity mOOBEActivity;
    private ProgressBar progressBar;
    private ArrayList<PersonItem> list = new ArrayList<>();
    private boolean fetchedUsers = false;
    private final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, UserSelectionFragment.class);

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class UserListCallback implements IHttpClient.Callback {
        private UserListCallback() {
        }

        public /* synthetic */ void lambda$onFailure$0$UserSelectionFragment$UserListCallback() {
            UserSelectionFragment.this.progressBar.setVisibility(8);
        }

        @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
        public void onFailure(IHttpClient.Call call, ServiceException serviceException) {
            UserSelectionFragment.this.LOG.e("Error retrieving user list", serviceException);
            if (!UserSelectionFragment.this.isActivityAvailable()) {
                UserSelectionFragment.this.LOG.w("UserSelectionFragment seems to be detached from the OOBEActivity");
                return;
            }
            UserSelectionFragment.this.mOOBEActivity.onError(serviceException, MetricKeys.SCREEN_NAME_OOBE_USER_SELECTION, AlertSource.newRequestSource(serviceException));
            UserSelectionFragment.this.mOOBEActivity.runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.-$$Lambda$UserSelectionFragment$UserListCallback$_534hoaTcZaJw-XumdwChzVYf18
                @Override // java.lang.Runnable
                public final void run() {
                    UserSelectionFragment.UserListCallback.this.lambda$onFailure$0$UserSelectionFragment$UserListCallback();
                }
            });
        }

        @Override // com.amazon.deecomms.common.network.IHttpClient.Callback
        public void onResult(IHttpClient.Call call, IHttpClient.Response response) {
            UserSelectionFragment.this.extractJsonIntoPersonItemList(response.getBody());
            UserSelectionFragment.this.refreshList();
        }

        /* synthetic */ UserListCallback(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshList() {
        if (!isActivityAvailable()) {
            this.LOG.w("UserSelectionFragment seems to be detached from the OOBEActivity");
        } else {
            this.mOOBEActivity.runOnUiThread(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.-$$Lambda$UserSelectionFragment$1KxpYbRqYwmHVW19_soyMx7FGa8
                @Override // java.lang.Runnable
                public final void run() {
                    UserSelectionFragment.this.lambda$refreshList$1$UserSelectionFragment();
                }
            });
        }
    }

    public void extractJsonIntoPersonItemList(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                this.list.add(new PersonItem(Person.create(jSONObject), PCAProfile.create(jSONObject)));
            }
        } catch (JSONException e) {
            this.LOG.e("Error parsing name list response", e);
        }
    }

    public String getACMSPathForUserSelection() {
        return this.capabilitiesManager.isPCAProfilesEnabled() ? "/accounts?includeActors=true" : AppUrl.OOBE_ACCOUNTS;
    }

    public void handleUserSelectionClick(@NonNull PersonItem personItem) {
        if (personItem.isPlaceholder) {
            getContext().getSharedPreferences("SHARED_PREFS", 0).edit().putBoolean(Constants.SHOULD_SUPPORT_CONTACTS_ON_DEVICES, true).apply();
            this.mOOBEActivity.saveUser(new Person());
            goToNextFragment();
            return;
        }
        this.mOOBEActivity.selectUser(personItem.person, true);
        this.mOOBEActivity.savePCAProfile(personItem.pcaProfile);
    }

    public /* synthetic */ void lambda$onStart$0$UserSelectionFragment() {
        new ACMSClient(MetricKeys.OP_GET_USERS_FOR_OOBE).request(getACMSPathForUserSelection()).authenticated().get().enqueue(new UserListCallback(null));
    }

    public /* synthetic */ void lambda$refreshList$1$UserSelectionFragment() {
        this.progressBar.setVisibility(8);
        if (!this.list.contains(PLACEHOLDER)) {
            this.list.add(PLACEHOLDER);
        }
        Collections.sort(this.list);
        this.adapter.notifyDataSetChanged();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate;
        this.mOOBEActivity = (OOBEActivity) getActivity();
        Context applicationContext = this.mOOBEActivity.getApplicationContext();
        if (isThemedUIEnabled()) {
            inflate = layoutInflater.inflate(R.layout.fiesta_oobe_user_selection, viewGroup, false);
            this.adapter = new PersonArrayAdapter(applicationContext, R.layout.fiesta_oobe_user_list_item, this.list, true);
        } else {
            inflate = layoutInflater.inflate(R.layout.oobe_user_selection, viewGroup, false);
            this.adapter = new PersonArrayAdapter(applicationContext, R.layout.oobe_user_list_item, this.list, false);
        }
        this.themingUpdateUtil.applyBackgroundColorToView(inflate, applicationContext, R.attr.mosaicBackground);
        this.themingUpdateUtil.applyColorToTextView((TextView) inflate.findViewById(R.id.oobe_user_selection_title), applicationContext, R.attr.mosaicNeutral10);
        this.themingUpdateUtil.applyColorToTextView((TextView) inflate.findViewById(R.id.oobe_user_selection_subtitle), applicationContext, R.attr.mosaicNeutral10);
        ListView listView = (ListView) inflate.findViewById(R.id.oobe_user_selection_list);
        this.progressBar = (ProgressBar) inflate.findViewById(R.id.us_progress_bar);
        listView.setAdapter((ListAdapter) this.adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.amazon.deecomms.oobe.fragments.UserSelectionFragment.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                UserSelectionFragment.this.handleUserSelectionClick((PersonItem) adapterView.getItemAtPosition(i));
            }
        });
        setHeaderTitle(getResources().getString(R.string.oobe_setup_header));
        if (isActivityAvailable()) {
            this.mOOBEActivity.showHeaderBar();
            this.mOOBEActivity.hideBackButton();
            this.mOOBEActivity.hideSkipButton();
        }
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (!this.fetchedUsers) {
            AsyncTask.execute(new Runnable() { // from class: com.amazon.deecomms.oobe.fragments.-$$Lambda$UserSelectionFragment$fzlawJIecY5VuKRBsQEpSus7afs
                @Override // java.lang.Runnable
                public final void run() {
                    UserSelectionFragment.this.lambda$onStart$0$UserSelectionFragment();
                }
            });
            this.fetchedUsers = true;
        }
    }

    @Override // com.amazon.deecomms.oobe.fragments.MainOOBEFragment
    public void recordOobePageStartMetric() {
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.OOBE_PAGE_USER_SELECTION_START);
    }
}
