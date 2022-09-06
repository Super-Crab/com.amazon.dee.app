package com.amazon.alexa.drive.navigation;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.metrics.NavigationMetrics;
import com.amazon.alexa.drive.navigation.SavedLocations;
import com.amazon.alexa.drive.navigation.location.AlexaLocationEndpointFactory;
import com.amazon.alexa.drive.util.IdentityUtils;
import com.amazon.alexa.drive.util.LocalizedStrings;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.identity.api.UserProfile;
import com.amazon.identity.auth.device.api.AuthenticatedURLConnection;
import com.amazon.identity.auth.device.api.AuthenticationMethod;
import com.amazon.identity.auth.device.api.AuthenticationMethodFactory;
import com.amazon.identity.auth.device.api.AuthenticationType;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.gson.GsonBuilder;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
/* loaded from: classes7.dex */
public class NavigationDataProvider {
    private static final String BEARER = "Bearer ";
    private static final int MAX_REQUEST_ATTEMPTS = 5;
    private static final String TAG = "NavigationDataProvider";
    private static final int TIMEOUT_MS = 45000;
    private final String labelHome;
    private final String labelWork;
    private SavedLocationAsyncTask locationAsyncTask;
    private final Context mContext;
    private final NavigationMetrics mNavigationMetrics;
    private NavigationRepo mNavigationRepo;
    @VisibleForTesting
    int requestAttemptCounter;
    private boolean savedLocationFetched;
    private boolean unauthorized;
    private List<SavedLocations.Item> mSavedLocations = null;
    private final Subject<Boolean> locationsSubject = BehaviorSubject.create();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes7.dex */
    public static class SavedLocationAsyncTask extends AsyncTask<Void, Integer, SavedLocations> {
        private final WeakReference<NavigationDataProvider> dataProviderWeakReference;

        SavedLocationAsyncTask(NavigationDataProvider navigationDataProvider) {
            this.dataProviderWeakReference = new WeakReference<>(navigationDataProvider);
        }

        private SavedLocations parseCustomerAddressFromServerResponse(InputStream inputStream) {
            String str;
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    } else {
                        inputStreamReader.close();
                        bufferedReader.close();
                        str = sb.toString();
                        try {
                            String unused = NavigationDataProvider.TAG;
                            String str2 = "parseCustomerAddressFromServerResponse: " + str;
                            return (SavedLocations) new GsonBuilder().registerTypeAdapterFactory(AutoValueAdapterFactory.create()).create().fromJson(str, (Class<Object>) SavedLocations.class);
                        } catch (Exception unused2) {
                            GeneratedOutlineSupport1.outline162("Unable to parse server response from CAB API! Server response json: ", str, NavigationDataProvider.TAG);
                            return null;
                        }
                    }
                }
            } catch (Exception unused3) {
                str = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public SavedLocations doInBackground(Void... voidArr) {
            NavigationDataProvider navigationDataProvider = this.dataProviderWeakReference.get();
            if (navigationDataProvider == null) {
                return null;
            }
            try {
                HttpsURLConnection httpsUrlConnection = navigationDataProvider.getHttpsUrlConnection();
                if (httpsUrlConnection != null) {
                    httpsUrlConnection.connect();
                    int responseCode = httpsUrlConnection.getResponseCode();
                    navigationDataProvider.unauthorized = responseCode == 401;
                    navigationDataProvider.requestAttemptCounter++;
                    if (responseCode != 200) {
                        Log.e(NavigationDataProvider.TAG, "Response Code : " + httpsUrlConnection.getResponseCode() + " Reason : " + httpsUrlConnection.getResponseMessage() + " & response headers ");
                    } else {
                        String unused = NavigationDataProvider.TAG;
                        navigationDataProvider.setLocationFetchedStatus(true);
                        return parseCustomerAddressFromServerResponse(httpsUrlConnection.getInputStream());
                    }
                }
            } catch (Exception e) {
                GeneratedOutlineSupport1.outline156("Unable to read server response from CAB API | Exception: ", e, NavigationDataProvider.TAG);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(SavedLocations savedLocations) {
            String unused = NavigationDataProvider.TAG;
            String str = "onPostExecute | savedLocations: " + savedLocations;
            NavigationDataProvider navigationDataProvider = this.dataProviderWeakReference.get();
            if (navigationDataProvider != null) {
                navigationDataProvider.updateLocations(savedLocations);
            }
        }
    }

    public NavigationDataProvider(Context context, NavigationRepo navigationRepo, NavigationMetrics navigationMetrics) {
        this.mContext = context;
        this.mNavigationRepo = navigationRepo;
        this.mNavigationMetrics = navigationMetrics;
        this.labelHome = this.mContext.getResources().getString(R.string.nav_home);
        this.labelWork = this.mContext.getResources().getString(R.string.nav_work);
        requestNavigationSavedLocations();
    }

    private void findHomeOrWorkAddress(List<SavedLocations.Item> list) {
        if (this.mNavigationRepo.getHomeAddress() == null) {
            this.mNavigationRepo.setHomeAddress(findMatchAddress(R.string.nav_home, list));
        }
        if (this.mNavigationRepo.getWorkAddress() == null) {
            this.mNavigationRepo.setWorkAddress(findMatchAddress(R.string.nav_work, list));
        }
    }

    @Nullable
    private SavedLocations.Item findMatchAddress(int i, List<SavedLocations.Item> list) {
        for (String str : LocalizedStrings.getLocalizedStringList(this.mContext, i)) {
            for (SavedLocations.Item item : list) {
                if (item.getAddressLabel().equals(str)) {
                    this.mSavedLocations.remove(item);
                    return item;
                }
            }
        }
        return null;
    }

    private URL getCabApiUrl(String str) {
        try {
            return new URL(getCustomerAddressEndPoint(str));
        } catch (MalformedURLException unused) {
            Log.e(TAG, "MalformedURLException when generating authenticated URL");
            return null;
        }
    }

    private String getCustomerAddressEndPoint(String str) {
        return new AlexaLocationEndpointFactory().getCustomerAddressEndPoint(str);
    }

    private UserIdentity getUserFromIdentityService() {
        IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        if (identityService != null) {
            return identityService.getUser(NavigationDataProvider.class.getSimpleName());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLocationFetchedStatus(boolean z) {
        this.savedLocationFetched = z;
    }

    public SavedLocations.Item getHome() {
        return this.mNavigationRepo.getHomeAddress();
    }

    @VisibleForTesting
    HttpsURLConnection getHttpsUrlConnection() {
        HttpsURLConnection httpsURLConnection;
        String directedId;
        String accessToken;
        UserIdentity userFromIdentityService = getUserFromIdentityService();
        if (userFromIdentityService == null) {
            return null;
        }
        try {
            UserProfile userProfile = userFromIdentityService.getUserProfile();
            directedId = userProfile != null ? userProfile.getDirectedId() : userFromIdentityService.getDirectedId();
            accessToken = IdentityUtils.getAccessToken();
        } catch (Exception unused) {
            httpsURLConnection = null;
        }
        if (accessToken == null) {
            return null;
        }
        String str = BEARER + accessToken;
        AuthenticationMethod newAuthenticationMethod = new AuthenticationMethodFactory(this.mContext, directedId).newAuthenticationMethod(AuthenticationType.OAuth);
        URL cabApiUrl = getCabApiUrl(directedId);
        if (cabApiUrl == null) {
            return null;
        }
        httpsURLConnection = (HttpsURLConnection) AuthenticatedURLConnection.openConnection(cabApiUrl, newAuthenticationMethod);
        try {
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.setRequestProperty("Authorization", str);
            httpsURLConnection.setReadTimeout(TIMEOUT_MS);
            httpsURLConnection.setConnectTimeout(TIMEOUT_MS);
        } catch (Exception unused2) {
            Log.e(TAG, "Unable to create HttpsUrlConnection");
            return httpsURLConnection;
        }
        return httpsURLConnection;
    }

    public List<SavedLocations.Item> getSavedLocations() {
        return this.mNavigationRepo.getSavedLocations();
    }

    public SavedLocations.Item getWork() {
        return this.mNavigationRepo.getWorkAddress();
    }

    public boolean isSavedLocationFetched() {
        return this.savedLocationFetched;
    }

    public /* synthetic */ void lambda$updateLocations$0$NavigationDataProvider() {
        this.locationAsyncTask = new SavedLocationAsyncTask(this);
        this.locationAsyncTask.execute(new Void[0]);
    }

    public Observable<Boolean> queryLocations() {
        return this.locationsSubject;
    }

    public void requestNavigationSavedLocations() {
        SavedLocationAsyncTask savedLocationAsyncTask = this.locationAsyncTask;
        if (savedLocationAsyncTask != null && savedLocationAsyncTask.getStatus() == AsyncTask.Status.RUNNING) {
            this.locationAsyncTask.cancel(true);
        }
        this.requestAttemptCounter = 0;
        this.locationAsyncTask = new SavedLocationAsyncTask(this);
        this.locationAsyncTask.execute(new Void[0]);
    }

    void updateLocations(SavedLocations savedLocations) {
        if (savedLocations == null) {
            this.mNavigationMetrics.logCABApi("Failure");
            if (!this.unauthorized || this.requestAttemptCounter >= 5) {
                return;
            }
            new Handler().post(new Runnable() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationDataProvider$lBollPbLV66wtGyQFpyP-rS7EoY
                @Override // java.lang.Runnable
                public final void run() {
                    NavigationDataProvider.this.lambda$updateLocations$0$NavigationDataProvider();
                }
            });
            return;
        }
        this.mNavigationMetrics.logCABApi("Success");
        this.mNavigationRepo.resetNavRepo();
        List<SavedLocations.Item> items = savedLocations.getItems();
        this.mSavedLocations = new ArrayList();
        for (SavedLocations.Item item : items) {
            if (item.getAddressLabel().equals(this.labelHome)) {
                this.mNavigationRepo.setHomeAddress(item);
            } else if (item.getAddressLabel().equals(this.labelWork)) {
                this.mNavigationRepo.setWorkAddress(item);
            } else {
                this.mSavedLocations.add(item);
            }
        }
        if (this.mSavedLocations.size() != 0) {
            this.mNavigationRepo.setSavedLocations(this.mSavedLocations);
        }
        findHomeOrWorkAddress(items);
        Subject<Boolean> subject = this.locationsSubject;
        if (subject == null) {
            return;
        }
        subject.onNext(true);
    }
}
