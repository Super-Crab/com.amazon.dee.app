package com.amazon.deecomms.contacts.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Messenger;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.content.CursorLoader;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.R;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.api.navigation.CommsView;
import com.amazon.deecomms.api.navigation.FragmentRequirements;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.enums.CallProvider;
import com.amazon.deecomms.calling.enums.DeviceCommsAvailability;
import com.amazon.deecomms.calling.enums.DropInAvailability;
import com.amazon.deecomms.calling.initiation.InitiationLogicContract;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.ui.CallViewUtils;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.util.CoboUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.DeviceBottomSheetTarget;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.metrics.MetricsHelper;
import com.amazon.deecomms.common.network.ACMSClient;
import com.amazon.deecomms.common.network.AppUrl;
import com.amazon.deecomms.common.network.IHttpClient;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.GetContactsForChild;
import com.amazon.deecomms.common.network.acmsrecipes.RefreshContact;
import com.amazon.deecomms.common.ui.CustomDialogBuilder;
import com.amazon.deecomms.common.ui.util.OnSingleClickListener;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.DeviceUtils;
import com.amazon.deecomms.common.util.FragmentNavigationRouter;
import com.amazon.deecomms.common.util.IdentityValidator;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.common.util.VoiceMessageTranscriptionConsent;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.database.provider.ContactProviderContract;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.model.ContactCardInfo;
import com.amazon.deecomms.contacts.model.ContactEmailAddress;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.DropInState;
import com.amazon.deecomms.contacts.model.EmailAddressType;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.contacts.model.UserInfo;
import com.amazon.deecomms.contacts.presence.ContactPresenceServiceBinding;
import com.amazon.deecomms.contacts.presence.PresenceReplyHandler;
import com.amazon.deecomms.contacts.presence.model.PresenceDocument;
import com.amazon.deecomms.contacts.ui.ContactCardFragment;
import com.amazon.deecomms.contacts.util.BlockContactHelper;
import com.amazon.deecomms.contacts.util.BlockContactListener;
import com.amazon.deecomms.contacts.util.ContactNameHelper;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.contacts.util.GetOrCreateContact;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.ndt.DeviceListSubscriber;
import com.amazon.deecomms.ndt.DevicesSource;
import com.amazon.deecomms.ndt.enums.ActiveState;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.model.GetDevicesResponse;
import com.amazon.deecomms.ndt.ui.DeviceBottomSheet;
import com.amazon.deecomms.perms.PermissionsHelper;
import com.amazon.deecomms.remoteConfig.ArcusConfig;
import com.amazon.deecomms.remoteConfig.RemoteConfigKeys;
import com.amazon.deecomms.rx.SimpleSubscriber;
import com.amazon.deecomms.util.DeviceInfo;
import com.amazon.deecomms.util.SharedPreferencesUtils;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
/* loaded from: classes12.dex */
public class ContactCardFragment extends Fragment implements DeviceBottomSheetTarget, BlockContactListener {
    private static final int CONTACT_LOCAL_ADDRESS_BOOK_CODE = 2000;
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactCardFragment.class);
    private Button abtAlexaComms;
    @Inject
    Context appContext;
    @Inject
    ApplicationManager applicationManager;
    @Inject
    ArcusConfig arcusConfig;
    @Inject
    CallingFacade callingFacade;
    @Inject
    CapabilitiesManager capabilitiesManager;
    private ArrayList<ContactPhoneNumber> contactPhoneNumberList;
    private DeviceBottomSheet deviceBottomSheet;
    @Inject
    DeviceUtils deviceUtils;
    @Inject
    DevicesSource devicesSource;
    private AppCompatDialog dropInConfirmDialog;
    @Inject
    InitiationLogicFactory initiationLogicFactory;
    private boolean mAlexaEnabled;
    private ImageButton mAudioCall;
    private BlockContactHelper mBlockContactHelper;
    private TextView mBlockContactLink;
    private ViewGroup mCallerIdLayout;
    private ToggleButton mCallerIdToggle;
    private ViewGroup mChildAccountListLayout;
    private SimpleSubscriber<List<ContactEntry>> mChildAccountListUpdateSubscriber;
    private ViewGroup mChildContactListLayout;
    private SimpleSubscriber<List<ContactEntry>> mChildContactListUpdateSubscriber;
    @Inject
    CommsIdentityManager mCommsIdentityManager;
    private Contact mContact;
    @Nullable
    private ContactCardInfo mContactCardInfo;
    private SimpleSubscriber<ContactCardInfo> mContactCardInfoSubscriber;
    private TextView mContactName;
    private ViewGroup mContactUnreachableView;
    private SimpleSubscriber<Boolean> mDiagnosticsFeatureSubscriber;
    private LinearLayout mDiagnosticsLayout;
    private ImageButton mDropIn;
    private ViewGroup mDropInListLayout;
    private SimpleSubscriber<List<ContactEntry>> mDropInListUpdateSubscriber;
    private SimpleSubscriber<ContactEntry> mDropInStateSubscriber;
    private ToggleButton mDropInToggle;
    private ViewGroup mDropInToggleLayout;
    private GetCallerIdSubscriber mGetCallerIdSubscriber;
    private LinearLayout mImageButtonsLayout;
    private boolean mIsChildProfile;
    private boolean mIsContactActive;
    private boolean mIsNameEmpty;
    private Menu mMenu;
    private ImageButton mMessage;
    private TextView mMyProfileText;
    private String mParentContactId;
    private String mParentHGId;
    private LinearLayout mPermissionsLayout;
    private ContactPhoneNumber mPhoneNumber;
    private SetCallerIdSubscriber mSetCallerIdSubscriber;
    private ToggleButton mShareDataConsentToggle;
    private SimpleSubscriber<Boolean> mSmsRelayFeatureSubscriber;
    private SimpleSubscriber<String> mSmsRelayStateSubscriber;
    private LinearLayout mUserContactInfoLayout;
    private ViewGroup mUserInfoContentLayout;
    private LinearLayout mUserInfoLayout;
    private ImageButton mVideoCall;
    private AppCompatDialog smsConfirmDialog;
    private String mContactId = null;
    private String mCommsId = null;
    private String mSmsRelayPreferenceValue = null;
    private boolean mIsDropInCall = false;
    private final ContactPresenceServiceBinding contactPresenceServiceBinding = new ContactPresenceServiceBinding();
    @SuppressLint({"HandlerLeak"})
    private final Messenger presenceReplyMessenger = new Messenger(new PresenceReplyHandler() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.1
        @Override // com.amazon.deecomms.contacts.presence.PresenceReplyHandler
        protected void handleReplyActiveContacts(List<PresenceDocument> list) {
        }

        @Override // com.amazon.deecomms.contacts.presence.PresenceReplyHandler
        protected void handleReplyActiveCount(int i) {
        }

        @Override // com.amazon.deecomms.contacts.presence.PresenceReplyHandler
        protected void handleReplyActivityForBanner(int i, int i2, String str, String str2) {
        }

        @Override // com.amazon.deecomms.contacts.presence.PresenceReplyHandler
        protected void handleReplyAllContacts(List<PresenceDocument> list) {
        }

        @Override // com.amazon.deecomms.contacts.presence.PresenceReplyHandler
        protected void handleReplyGetContactPresence(PresenceDocument presenceDocument) {
        }

        @Override // com.amazon.deecomms.contacts.presence.PresenceReplyHandler
        protected void handleReplyIsContactActive(String str, boolean z) {
            ContactCardFragment.this.mIsContactActive = z;
            ContactCardFragment.this.updateDropInButton();
        }
    });
    private final ContactPresenceServiceBinding.ServiceBindingCallback bindingCallback = new ContactPresenceServiceBinding.ServiceBindingCallback() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.2
        @Override // com.amazon.deecomms.contacts.presence.ContactPresenceServiceBinding.ServiceBindingCallback
        public void serviceBound() {
            ContactCardFragment.this.dropInButtonHandler.post(ContactCardFragment.this.dropInButtonRunnable);
        }

        @Override // com.amazon.deecomms.contacts.presence.ContactPresenceServiceBinding.ServiceBindingCallback
        public void serviceUnbound() {
            ContactCardFragment.this.dropInButtonHandler.removeCallbacks(ContactCardFragment.this.dropInButtonRunnable);
        }
    };
    private final Handler dropInButtonHandler = new Handler();
    private final Runnable dropInButtonRunnable = new Runnable() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.3
        private static final int REPEAT_INTERVAL = 10000;

        @Override // java.lang.Runnable
        public void run() {
            if (ContactCardFragment.this.getHost() == null || ContactCardFragment.this.getActivity().isFinishing()) {
                return;
            }
            if (!Utils.isRegisteredCommsId(ContactCardFragment.this.mCommsId) && !Utils.isHomeGroupId(ContactCardFragment.this.mParentHGId)) {
                if (ContactCardFragment.this.mParentHGId != null && !ContactCardFragment.this.contactPresenceServiceBinding.isContactActive(ContactCardFragment.this.mParentHGId, ContactCardFragment.this.presenceReplyMessenger)) {
                    ContactCardFragment.this.mIsContactActive = false;
                    ContactCardFragment.this.updateDropInButton();
                }
            } else {
                ContactCardFragment.this.devicesSource.getDevicesObservable(false).subscribe((Subscriber<? super GetDevicesResponse>) new DeviceStatusSubscriber());
            }
            ContactCardFragment.this.dropInButtonHandler.postDelayed(this, 10000L);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public abstract class CallerIdSubscriber<T> extends SimpleSubscriber<T> {
        private CallerIdSubscriber() {
        }

        @Override // com.amazon.deecomms.rx.SimpleSubscriber
        public void onCall(T t) {
            ContactCardFragment.this.mCallerIdToggle.setEnabled(true);
        }

        @Override // com.amazon.deecomms.rx.SimpleSubscriber, rx.Observer
        public void onError(Throwable th) {
            super.onError(th);
            ContactCardFragment.this.mCallerIdToggle.setEnabled(true);
        }

        @Override // rx.Subscriber
        public void onStart() {
            super.onStart();
            ContactCardFragment.this.mCallerIdToggle.setEnabled(false);
        }

        /* synthetic */ CallerIdSubscriber(AnonymousClass1 anonymousClass1) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class ChildAccountListUpdateSubscriber extends SimpleSubscriber<List<ContactEntry>> {
        private ChildAccountListUpdateSubscriber() {
        }

        public /* synthetic */ void lambda$onCall$0$ContactCardFragment$ChildAccountListUpdateSubscriber(ContactEntry contactEntry, View view) {
            MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.CONTACTS_CHILD_CLICK);
            ContactCardFragment.this.switchToChildAccount(contactEntry);
        }

        /* synthetic */ ChildAccountListUpdateSubscriber(AnonymousClass1 anonymousClass1) {
        }

        @Override // com.amazon.deecomms.rx.SimpleSubscriber
        public void onCall(List<ContactEntry> list) {
            if (list.isEmpty()) {
                ContactCardFragment.this.mChildAccountListLayout.setVisibility(8);
                return;
            }
            LinearLayout linearLayout = (LinearLayout) ContactCardFragment.this.mChildAccountListLayout.findViewById(R.id.child_account_members);
            linearLayout.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                final ContactEntry contactEntry = list.get(i);
                View homeGroupInfoView = ContactCardFragment.this.getHomeGroupInfoView(contactEntry, linearLayout);
                View findViewById = homeGroupInfoView.findViewById(R.id.contact_details_list_divider);
                if (i != list.size() - 1) {
                    findViewById.setVisibility(0);
                }
                homeGroupInfoView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$ChildAccountListUpdateSubscriber$AMh8Ccej_fcZyy9_dKWJ5kGSZJU
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ContactCardFragment.ChildAccountListUpdateSubscriber.this.lambda$onCall$0$ContactCardFragment$ChildAccountListUpdateSubscriber(contactEntry, view);
                    }
                });
                linearLayout.addView(homeGroupInfoView);
            }
            if (linearLayout.getChildCount() > 0) {
                ContactCardFragment.this.mChildAccountListLayout.setVisibility(0);
            } else {
                ContactCardFragment.this.mChildAccountListLayout.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class ChildContactListUpdateSubscriber extends SimpleSubscriber<List<ContactEntry>> {
        private ChildContactListUpdateSubscriber() {
        }

        public /* synthetic */ void lambda$onCall$0$ContactCardFragment$ChildContactListUpdateSubscriber(ContactEntry contactEntry, View view) {
            ContactCardFragment.this.switchToChildContact(contactEntry);
        }

        /* synthetic */ ChildContactListUpdateSubscriber(AnonymousClass1 anonymousClass1) {
        }

        @Override // com.amazon.deecomms.rx.SimpleSubscriber
        public void onCall(List<ContactEntry> list) {
            if (list.isEmpty()) {
                ContactCardFragment.this.mChildContactListLayout.setVisibility(8);
                return;
            }
            LinearLayout linearLayout = (LinearLayout) ContactCardFragment.this.mChildContactListLayout.findViewById(R.id.child_contact_member);
            linearLayout.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                final ContactEntry contactEntry = list.get(i);
                View childContactInfoView = ContactCardFragment.this.getChildContactInfoView(contactEntry, linearLayout);
                View findViewById = childContactInfoView.findViewById(R.id.child_contact_divider);
                if (i != list.size() - 1) {
                    findViewById.setVisibility(0);
                }
                childContactInfoView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$ChildContactListUpdateSubscriber$9Yt97vtYOJkt6znAmJvqMSkq2bI
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        ContactCardFragment.ChildContactListUpdateSubscriber.this.lambda$onCall$0$ContactCardFragment$ChildContactListUpdateSubscriber(contactEntry, view);
                    }
                });
                linearLayout.addView(childContactInfoView);
            }
            if (linearLayout.getChildCount() > 0) {
                ContactCardFragment.this.mChildContactListLayout.setVisibility(0);
            } else {
                ContactCardFragment.this.mChildContactListLayout.setVisibility(8);
            }
        }
    }

    /* loaded from: classes12.dex */
    private class DeviceStatusSubscriber extends Subscriber<GetDevicesResponse> {
        private DeviceStatusSubscriber() {
        }

        @Override // rx.Observer
        public void onCompleted() {
            unsubscribe();
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            ContactCardFragment.this.mIsContactActive = false;
            ContactCardFragment.this.updateDropInButton();
            unsubscribe();
        }

        @Override // rx.Observer
        public void onNext(GetDevicesResponse getDevicesResponse) {
            List<DeviceModel> deviceModels;
            if (ContactCardFragment.this.mIsChildProfile) {
                deviceModels = Utils.getDevicesForChildAccount(getDevicesResponse.getDeviceModels(), ContactCardFragment.this.mCommsId);
            } else {
                deviceModels = getDevicesResponse.getDeviceModels();
            }
            ContactCardFragment.this.mIsContactActive = false;
            Iterator<DeviceModel> it2 = deviceModels.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                DeviceModel next = it2.next();
                if (next.getDeviceStatus().getDeviceDropInAvailability() != DropInAvailability.OFF && next.getDeviceStatus().getDeviceCommsAvailability() != DeviceCommsAvailability.OFF && next.getDeviceStatus().getActiveState() == ActiveState.ACTIVE) {
                    ContactCardFragment.this.mIsContactActive = true;
                    break;
                }
            }
            ContactCardFragment.this.updateDropInButton();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class DropInListUpdateSubscriber extends SimpleSubscriber<List<ContactEntry>> {
        private DropInListUpdateSubscriber() {
        }

        public /* synthetic */ void lambda$onCall$0$ContactCardFragment$DropInListUpdateSubscriber(String str, View view) {
            ContactCardFragment.this.showRemoveDropInWarningDialog(str);
        }

        /* synthetic */ DropInListUpdateSubscriber(AnonymousClass1 anonymousClass1) {
        }

        @Override // com.amazon.deecomms.rx.SimpleSubscriber
        public void onCall(List<ContactEntry> list) {
            if (list.isEmpty()) {
                ContactCardFragment.this.mDropInListLayout.setVisibility(8);
                return;
            }
            LinearLayout linearLayout = (LinearLayout) ContactCardFragment.this.mDropInListLayout.findViewById(R.id.group_members);
            linearLayout.removeAllViews();
            for (int i = 0; i < list.size(); i++) {
                if (!TextUtils.equals(list.get(i).getId(), new GetOrCreateContact("ContactCardFragment.DropInListUpdateSubscriber.onCall").getContactEntry(ContactCardFragment.this.mCommsIdentityManager.getHomeGroupId("ContactCardFragment.DropInListUpdateSubscriber.onCall", false)).getId())) {
                    View homeGroupInfoView = ContactCardFragment.this.getHomeGroupInfoView(list.get(i), linearLayout);
                    TextView textView = (TextView) homeGroupInfoView.findViewById(R.id.card_home_group_info_link);
                    textView.setVisibility(0);
                    final String id = list.get(i).getId();
                    textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$DropInListUpdateSubscriber$Uo6zJKwO5lm6djt8O4xyZyuI2tc
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ContactCardFragment.DropInListUpdateSubscriber.this.lambda$onCall$0$ContactCardFragment$DropInListUpdateSubscriber(id, view);
                        }
                    });
                    linearLayout.addView(homeGroupInfoView);
                }
            }
            if (linearLayout.getChildCount() > 0) {
                ContactCardFragment.this.mDropInListLayout.setVisibility(0);
            } else {
                ContactCardFragment.this.mDropInListLayout.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class GetCallerIdSubscriber extends CallerIdSubscriber<Boolean> {
        private GetCallerIdSubscriber() {
            super(null);
        }

        @Override // com.amazon.deecomms.contacts.ui.ContactCardFragment.CallerIdSubscriber, com.amazon.deecomms.rx.SimpleSubscriber, rx.Observer
        public void onError(Throwable th) {
            ContactCardFragment.LOG.e("Failed to fetch user's caller id preference completed", th);
            super.onError(th);
            ContactCardFragment.this.setGetCallerIdSubscriber(null);
        }

        /* synthetic */ GetCallerIdSubscriber(AnonymousClass1 anonymousClass1) {
            super(null);
        }

        @Override // com.amazon.deecomms.contacts.ui.ContactCardFragment.CallerIdSubscriber, com.amazon.deecomms.rx.SimpleSubscriber
        public void onCall(Boolean bool) {
            CommsLogger commsLogger = ContactCardFragment.LOG;
            commsLogger.i("Fetched user's caller id preference completed: " + bool);
            ContactCardFragment.this.mCallerIdToggle.setEnabled(true);
            ContactCardFragment.this.setGetCallerIdSubscriber(null);
            ContactCardFragment.this.mCallerIdToggle.setChecked(bool.booleanValue());
            SharedPreferencesUtils.persistCacheValues(ContactCardFragment.this.getContext(), Constants.SHARED_PREF_CALLER_ID_SHOWN, bool);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public final class RemoveDropInAsyncTask extends AsyncTask<Object, Void, Boolean> {
        private ACMSClient acmsClient = new ACMSClient(MetricKeys.OP_PREF_UPDATE_PREF_CONTACT);
        private String parentContactId;
        private String parentHGId;

        RemoveDropInAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.AsyncTask
        /* renamed from: doInBackground */
        public Boolean mo3667doInBackground(Object... objArr) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            boolean booleanValue = ((Boolean) objArr[1]).booleanValue();
            this.parentContactId = (String) objArr[0];
            ContactCardInfo createContactCardInfo = ContactCardInfo.createContactCardInfo(this.parentContactId);
            if (createContactCardInfo == null) {
                ContactCardFragment.LOG.e("Could not get contact info for fetching drop-in");
                return null;
            }
            this.parentHGId = createContactCardInfo.getCommsId();
            try {
                DropInState dropInState = booleanValue ? DropInState.OFF : DropInState.ON;
                IHttpClient.Response mo3640execute = this.acmsClient.request(MessageFormat.format(AppUrl.PATCH_PREFERENCE, ContactCardFragment.this.mCommsIdentityManager.getHomeGroupId("RemoveDropInAsyncTask", false), this.parentHGId)).addMetricMetadata("source", Constants.CONTACT_PREF_DROP_IN).authenticatedAsCurrentCommsUser().patchJson(dropInState).mo3640execute();
                try {
                    ContactsProviderUtils.updateDropInState(ContactCardFragment.this.appContext, this.parentContactId, dropInState, TextUtils.equals(this.parentHGId, ContactCardFragment.this.mCommsIdentityManager.getHomeGroupId("RemoveDropInAsyncTask", false)) ? dropInState : null);
                    Boolean valueOf = Boolean.valueOf(booleanValue);
                    if (mo3640execute != null) {
                        mo3640execute.close();
                    }
                    return valueOf;
                } finally {
                }
            } catch (ServiceException | IOException e) {
                ContactCardFragment.LOG.e("Service Error occurred while fetching drop in.", e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            if (ContactCardFragment.this.getActivity() == null || ContactCardFragment.this.getActivity().isFinishing()) {
                return;
            }
            if (bool != null) {
                if (!ContactCardFragment.this.isSelfOrHg()) {
                    return;
                }
                ContactCardFragment contactCardFragment = ContactCardFragment.this;
                contactCardFragment.mDropInListUpdateSubscriber = new DropInListUpdateSubscriber(null);
                ContactCardFragment.this.updateDropInList();
                return;
            }
            MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, MetricKeys.SCREEN_NAME_CONTACT_DETAILS, AlertSource.newClassSource(ContactCardFragment.this));
            new AlertDialog.Builder(ContactCardFragment.this.getContext()).setTitle(ContactCardFragment.this.getString(R.string.dropin_failure_msg)).setMessage(ContactCardFragment.this.getString(R.string.remove_drop_in_failure_msg)).setPositiveButton(ContactCardFragment.this.getString(R.string.dialog_ok_button), $$Lambda$ContactCardFragment$RemoveDropInAsyncTask$tmI3tf3NbxhCcXXAIlKkQKDrMiU.INSTANCE).setCancelable(true).create().show();
            ContactCardFragment.this.setDropInListItemOnServer(this.parentContactId, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public class SetCallerIdSubscriber extends CallerIdSubscriber<Boolean> {
        private SetCallerIdSubscriber() {
            super(null);
        }

        @Override // com.amazon.deecomms.contacts.ui.ContactCardFragment.CallerIdSubscriber, com.amazon.deecomms.rx.SimpleSubscriber, rx.Observer
        public void onError(Throwable th) {
            ContactCardFragment.LOG.d("Failed to save user's caller id preference completed", th);
            super.onError(th);
            ContactCardFragment.this.setSetCallerIdSubscriber(null);
            ContactCardFragment.this.mCallerIdToggle.toggle();
            new AlertDialog.Builder(ContactCardFragment.this.getContext()).setTitle(R.string.generic_error_title).setMessage(R.string.generic_error_msg).setPositiveButton(R.string.dialog_ok_button, $$Lambda$ContactCardFragment$SetCallerIdSubscriber$fuhJ5l6F6lUBUR1sPSLiiiAyU.INSTANCE).setCancelable(true).show();
        }

        /* synthetic */ SetCallerIdSubscriber(AnonymousClass1 anonymousClass1) {
            super(null);
        }

        @Override // com.amazon.deecomms.contacts.ui.ContactCardFragment.CallerIdSubscriber, com.amazon.deecomms.rx.SimpleSubscriber
        public void onCall(Boolean bool) {
            ContactCardFragment.LOG.d("Saved user's caller id preference completed");
            ContactCardFragment.this.mCallerIdToggle.setEnabled(true);
            ContactCardFragment.this.setSetCallerIdSubscriber(null);
            SharedPreferencesUtils.persistCacheValues(ContactCardFragment.this.getContext(), Constants.SHARED_PREF_CALLER_ID_SHOWN, bool);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public final class SetDropInAsyncTask extends AsyncTask<Boolean, Void, Boolean> {
        public SetDropInAsyncTask() {
        }

        private String getHomeGroup() throws ServiceException, IOException {
            if (ContactCardFragment.this.mParentHGId != null) {
                return ContactCardFragment.this.mParentHGId;
            }
            try {
                IHttpClient.Response mo3640execute = new ACMSClient(MetricKeys.OP_GET_IDENTITY_V2).request(MessageFormat.format("/users/{0}/identities", ContactCardFragment.this.mCommsId)).authenticatedAsCurrentCommsUser().get().mo3640execute();
                ContactCardFragment.this.mParentHGId = ((UserInfo) mo3640execute.convert(UserInfo.class)).getHomeGroupId();
                ContactCardFragment.this.mParentContactId = new GetOrCreateContact("ContactCardFragment.SetDropInAsyncTask").getContactEntry(ContactCardFragment.this.mParentHGId).getId();
                String str = ContactCardFragment.this.mParentHGId;
                mo3640execute.close();
                return str;
            } catch (IOException e) {
                ContactCardFragment.LOG.e("Service Error occurred while fetching home group.", e);
                throw e;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Boolean doInBackground(Boolean... boolArr) {
            Boolean bool = boolArr[0];
            try {
                String homeGroup = getHomeGroup();
                ACMSClient aCMSClient = new ACMSClient(MetricKeys.OP_PREF_UPDATE_PREF_CONTACT);
                DropInState dropInState = bool.booleanValue() ? DropInState.ON : DropInState.OFF;
                IHttpClient.Response mo3640execute = aCMSClient.request(MessageFormat.format(AppUrl.PATCH_PREFERENCE, ContactCardFragment.this.mCommsIdentityManager.getHomeGroupId("SetDropInAsyncTask", false), homeGroup)).addMetricMetadata("source", Constants.CONTACT_PREF_DROP_IN).authenticatedAsCurrentCommsUser().patchJson(dropInState).mo3640execute();
                ContactsProviderUtils.updateDropInState(ContactCardFragment.this.appContext, ContactCardFragment.this.mParentContactId, dropInState, TextUtils.equals(ContactCardFragment.this.mParentHGId, ContactCardFragment.this.mCommsIdentityManager.getHomeGroupId("SetDropInAsyncTask", false)) ? dropInState : null);
                if (mo3640execute != null) {
                    mo3640execute.close();
                }
                return bool;
            } catch (ServiceException | IOException e) {
                ContactCardFragment.LOG.e("Service Error occurred while fetching drop in.", e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Boolean bool) {
            if (ContactCardFragment.this.getActivity() == null || ContactCardFragment.this.getActivity().isFinishing()) {
                return;
            }
            if (bool != null) {
                if (!ContactCardFragment.this.isSelfOrHg()) {
                    return;
                }
                ContactCardFragment contactCardFragment = ContactCardFragment.this;
                contactCardFragment.mDropInListUpdateSubscriber = new DropInListUpdateSubscriber(null);
                ContactCardFragment.this.updateDropInList();
                return;
            }
            MetricsHelper.recordAlertDialogMetric(MetricKeys.ALERT_ERROR, MetricKeys.SCREEN_NAME_CONTACT_DETAILS, AlertSource.newClassSource(ContactCardFragment.this));
            new AlertDialog.Builder(ContactCardFragment.this.getContext()).setTitle(ContactCardFragment.this.getString(R.string.dropin)).setMessage(ContactCardFragment.this.getString(R.string.dropin_failure_msg)).setPositiveButton(ContactCardFragment.this.getString(R.string.dialog_ok_button), $$Lambda$ContactCardFragment$SetDropInAsyncTask$x2JFf2yrW0qpgvEHTJ0TfuL9KE.INSTANCE).setCancelable(true).create().show();
            ContactCardFragment.this.mDropInToggle.setChecked(true ^ ContactCardFragment.this.mDropInToggle.isChecked());
        }
    }

    static /* synthetic */ void access$4700(ContactCardFragment contactCardFragment, boolean z) {
        contactCardFragment.mDropInToggle.setChecked(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void configureFragmentRequirements() {
        this.applicationManager.configurePageForFragment(new FragmentRequirements(this).withMenu(R.menu.contact_card_menu, new Toolbar.OnMenuItemClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$XINc-HoM4w7MqG0mPLs3VjtnvBo
            @Override // androidx.appcompat.widget.Toolbar.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return ContactCardFragment.this.lambda$configureFragmentRequirements$4$ContactCardFragment(menuItem);
            }
        }, new FragmentRequirements.MenuInflatedCallback() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$yCuLfcpjBeBGHbGE8iPUffMHD-A
            @Override // com.amazon.deecomms.api.navigation.FragmentRequirements.MenuInflatedCallback
            public final void onInflated(Menu menu) {
                ContactCardFragment.this.lambda$configureFragmentRequirements$5$ContactCardFragment(menu);
            }
        }).hidesFooter());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Contact> filterChildContacts(List<Contact> list) {
        String str;
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (Contact contact : list) {
                if (contact.shouldDisplay() && contact.getCommsIds() != null && ((str = this.mCommsId) == null || (!str.equals(contact.getCommsIds().get(0)) && this.mCommsId.equals(contact.getOwnerCommsId())))) {
                    arrayList.add(contact);
                }
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getChildContactInfoView(ContactEntry contactEntry, LinearLayout linearLayout) {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.child_contact_info, (ViewGroup) linearLayout, false);
        TextView textView = (TextView) inflate.findViewById(R.id.child_contact_description);
        ((TextView) inflate.findViewById(R.id.child_contact_info_text)).setText(ContactUtils.getFullName(contactEntry.getFullContactName()));
        String nickName = contactEntry.getContactName().getNickName();
        String relationship = contactEntry.getRelationship();
        if (!TextUtils.isEmpty(nickName)) {
            textView.setText(nickName);
        } else if (TextUtils.isEmpty(nickName) && !TextUtils.isEmpty(relationship)) {
            textView.setText(R.string.child_contact_description_missing_nickname);
        }
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getChildContactsFromServer() {
        new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.13
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            @Nullable
            public Boolean doInBackground(Void... voidArr) {
                try {
                    List filterChildContacts = ContactCardFragment.this.filterChildContacts(new GetContactsForChild().getContactsForChild(ContactCardFragment.this.mCommsId));
                    if (filterChildContacts.size() <= 0) {
                        return null;
                    }
                    ContactsProviderUtils.insertOrUpdateContactListForChild(ContactCardFragment.this.appContext, filterChildContacts, ContactCardFragment.this.mCommsId);
                    return true;
                } catch (ServiceException e) {
                    ContactCardFragment.LOG.e("Unable to get child's contct list from server", e);
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                if (bool == null || !bool.booleanValue()) {
                    return;
                }
                ContactCardFragment contactCardFragment = ContactCardFragment.this;
                contactCardFragment.mChildContactListUpdateSubscriber = new ChildContactListUpdateSubscriber(null);
                ContactCardFragment.this.updateChildContactList();
            }
        }.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @NonNull
    public String getDisplayName(@NonNull ContactCardInfo contactCardInfo) {
        if (contactCardInfo.isAlexaEnabled() && Utils.isRegisteredCommsId(contactCardInfo.getCommsId())) {
            return ContactUtils.getFullName(ContactNameHelper.getActiveContactName());
        }
        return ContactUtils.getFullName(new FullContactName(this.mContact.getContactName(), this.mContact.getCompany()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View getHomeGroupInfoView(ContactEntry contactEntry, LinearLayout linearLayout) {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.card_homegroup_info, (ViewGroup) linearLayout, false);
        ((TextView) inflate.findViewById(R.id.card_home_group_info_text)).setText(ContactUtils.getFullName(contactEntry.getFullContactName()));
        return inflate;
    }

    private void init(View view) {
        this.mAudioCall = (ImageButton) view.findViewById(R.id.audio_call_button);
        this.mVideoCall = (ImageButton) view.findViewById(R.id.video_call_button);
        this.mMessage = (ImageButton) view.findViewById(R.id.message_button);
        this.mDropIn = (ImageButton) view.findViewById(R.id.drop_in_button);
        this.mDropIn.setActivated(false);
        this.mContactName = (TextView) view.findViewById(R.id.contact_name);
        this.mMyProfileText = (TextView) view.findViewById(R.id.myprofile_text);
        this.mPermissionsLayout = (LinearLayout) view.findViewById(R.id.contact_card_permissions);
        ((ImageView) view.findViewById(R.id.permissions_learn_more)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$OI2HXX2Aa8S7_z4ygijvKsHhNvY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ContactCardFragment.this.lambda$init$8$ContactCardFragment(view2);
            }
        });
        ((ImageView) view.findViewById(R.id.diagnostic_learn_more)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$Kfh4gjurjEyjP1GdWdNj22ZhsIs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ContactCardFragment.this.lambda$init$9$ContactCardFragment(view2);
            }
        });
        ((ImageView) view.findViewById(R.id.contact_card_drop_in_learn_more)).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$7IbVKu27rVl8Z74wK82w-sxYuiM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ContactCardFragment.this.lambda$init$10$ContactCardFragment(view2);
            }
        });
        this.mDropInListLayout = (ViewGroup) view.findViewById(R.id.drop_in_list_layout);
        this.mChildAccountListLayout = (ViewGroup) view.findViewById(R.id.child_account_list_layout);
        this.mChildContactListLayout = (ViewGroup) view.findViewById(R.id.child_contact_list_layout);
        this.mDropInToggleLayout = (ViewGroup) view.findViewById(R.id.people_dropin);
        this.mDropInToggle = (ToggleButton) this.mDropInToggleLayout.findViewById(R.id.drop_in_toggle);
        this.mUserContactInfoLayout = (LinearLayout) view.findViewById(R.id.contact_card_user_info_layout);
        this.mUserInfoLayout = (LinearLayout) view.findViewById(R.id.information_layout);
        this.mUserInfoContentLayout = (ViewGroup) this.mUserInfoLayout.findViewById(R.id.contact_card_information_content);
        this.mContactUnreachableView = (ViewGroup) this.mUserInfoLayout.findViewById(R.id.contact_unreachable_info);
        this.mImageButtonsLayout = (LinearLayout) view.findViewById(R.id.buttonLayout);
        this.mContact = new Contact();
        ContactEntry contactEntry = (ContactEntry) getArguments().getParcelable(Constants.CONTACT_ENTRY_KEY);
        if (contactEntry != null) {
            contactEntry.toContact(this.mContact);
            this.mContactId = contactEntry.getId();
            this.mIsNameEmpty = contactEntry.getIsNameEmpty();
            this.mIsChildProfile = contactEntry.isChild();
        }
        if (!this.mContact.isChild()) {
            refreshContactFromServer();
        }
        this.mBlockContactLink = (TextView) view.findViewById(R.id.contact_card_block_contact);
        this.mBlockContactLink.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$X4BrL6mGTUJuylbvquDOkJKFChM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ContactCardFragment.this.onBlockContactLinkClicked(view2);
            }
        });
        this.mCallerIdLayout = (ViewGroup) view.findViewById(R.id.cobo_caller_id_layout);
        this.mCallerIdToggle = (ToggleButton) view.findViewById(R.id.cobo_caller_id_toggle);
        this.mCallerIdToggle.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$gJz7uMtwkbRZTeQD9q1OUqGf6gQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ContactCardFragment.this.onToggleCallerId(view2);
            }
        });
        this.mDiagnosticsLayout = (LinearLayout) view.findViewById(R.id.contact_card_diagnostics);
        this.mShareDataConsentToggle = (ToggleButton) view.findViewById(R.id.voice_msg_consent_toggle);
        this.mShareDataConsentToggle.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$A0v9JKYMsyg0xBq3Gcf7rvKmOuk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ContactCardFragment.this.onToggleVoiceMsgConsent(view2);
            }
        });
        this.abtAlexaComms = (Button) view.findViewById(R.id.about_alexa_comms_button);
        this.abtAlexaComms.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$moarjxzyqKWEJuociozFxg7eocg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ContactCardFragment.this.lambda$init$11$ContactCardFragment(view2);
            }
        });
        setupSubscribers();
    }

    private boolean isBulkImportedContact() {
        return this.mContact.isBulkImport();
    }

    private boolean isMemberOfHG() {
        return TextUtils.equals(this.mCommsIdentityManager.getHomeGroupId("ContactCardFragment.isMemberOfHG", false), this.mParentHGId);
    }

    private boolean isOwnHomeGroup(@NonNull ContactCardInfo contactCardInfo) {
        return IdentityValidator.isMyHousehold(contactCardInfo.getCommsId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSelf(@NonNull ContactCardInfo contactCardInfo) {
        return IdentityValidator.isSelf(contactCardInfo.getCommsId());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSelfOrHg() {
        return TextUtils.equals(this.mCommsIdentityManager.getCommsId("ContactCardFragment.isSelfOrHg", false), this.mCommsId) || TextUtils.equals(this.mCommsIdentityManager.getHomeGroupId("ContactCardFragment.isSelfOrHg", false), this.mCommsId);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showRemoveDropInWarningDialog$19(DialogInterface dialogInterface, int i) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void makeACall(boolean z, boolean z2, boolean z3, ContactPhoneNumber contactPhoneNumber) {
        if (getHost() != null && !getActivity().isFinishing()) {
            CallHelper callHelper = new CallHelper();
            this.mIsDropInCall = z2;
            this.mPhoneNumber = contactPhoneNumber;
            if ((z2 && CallUtils.isNamedDeviceTargetedDropInCall(this.mParentHGId)) | z3) {
                CallInitiator callInitiator = new CallInitiator(MetricKeys.CONTACT_CALL_INITIATED_FROM_CONTACT_RN);
                FragmentActivity activity = getActivity();
                InitiationLogicContract create = this.initiationLogicFactory.create(callInitiator, activity, activity, callHelper, MetricKeys.CONTACT_CALL_INITIATED_FROM_CONTACT_RN, Constants.Telemetry.CallInitScreenNames.CONTACT_CARD);
                if (DeviceListSubscriber.isProcessing) {
                    return;
                }
                DeviceListSubscriber.isProcessing = true;
                CallUtils.doTargetedDropIn(new DeviceListSubscriber(create, this.mCommsId, this, MetricKeys.SCREEN_NAME_CONTACT_DETAILS, 9, this.mIsChildProfile));
                return;
            }
            callHelper.withRecipientID(this.mCommsId).withDisplayTitleName(ContactUtils.getFullName(new FullContactName(this.mContact.getContactName(), this.mContact.getCompany()))).withDropInCall(z2).withDeviceGruu(null).withPageSourceName(MetricKeys.SCREEN_NAME_CONTACT_DETAILS).withCallInitScreenName(Constants.Telemetry.CallInitScreenNames.CONTACT_CARD).withAlertSource(AlertSource.newClassSource(ContactCardFragment.class.getName())).withNDTCall(false);
            if (z) {
                CommsLogger commsLogger = LOG;
                commsLogger.i(" Placing a video call with dropIn " + z2);
                callHelper.withVideoCall(true).withCallProvider(CallProvider.A2A);
            } else if (contactPhoneNumber == null) {
                LOG.i(" Placing an audio call ");
                callHelper.withVideoCall(false).withCallProvider(CallProvider.A2A);
            } else {
                LOG.i(" Placing an PSTN call ");
                CallHelper withVideoCall = callHelper.withVideoCall(false);
                String str = this.mCommsId;
                if (str == null) {
                    str = "";
                }
                withVideoCall.withRecipientID(str).withCallProvider(CallProvider.COBO).withRecipientPhoneNumber(contactPhoneNumber);
            }
            callHelper.makeACall(getActivity());
            MetricsHelper.recordSingleOccurrenceClickstreamByChild(MetricKeys.CONTACT_CALL_INITIATED_FROM_CONTACT, this.mIsChildProfile);
            return;
        }
        LOG.e("Attempting to makeACall from an illegal Fragment state");
    }

    private void makePstnCall(@NonNull final ContactPhoneNumber contactPhoneNumber) {
        CallUtils.showCOBOWarningAlert(getActivity(), new Runnable() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$gMyuhad9UvKAJDQehGBJwZYlw8E
            @Override // java.lang.Runnable
            public final void run() {
                ContactCardFragment.this.lambda$makePstnCall$3$ContactCardFragment(contactPhoneNumber);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onBlockContactLinkClicked(View view) {
        if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_DETAILS, AlertSource.newClassSource(ContactCardFragment.class.getName()))) {
            return;
        }
        this.mBlockContactHelper = new BlockContactHelper(this, this.mContact, this.mCommsId, MetricKeys.SCREEN_NAME_CONTACT_DETAILS);
        this.mBlockContactHelper.blockContact(!this.mContact.isBlocked());
    }

    private void onClickLearnMore(@NonNull String str) {
        LOG.i(String.format("User pressed %s", str));
        Utils.openUrlInExternalBrowser(this.arcusConfig.getConfigString(RemoteConfigKeys.FAQ_URL_KEY), getParentFragment());
    }

    private void onEditClicked() {
        if (!Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_DETAILS, AlertSource.newClassSource(ContactCardFragment.class.getName()))) {
            Bundle bundle = new Bundle();
            bundle.putString("COMMS_ID", this.mCommsId);
            if (!this.mIsNameEmpty) {
                if (Utils.isRegisteredCommsId(this.mCommsId)) {
                    bundle.putParcelable(Constants.BUNDLE_KEY_CONTACT_NAME_KEY, ContactNameHelper.getActiveContactName());
                } else {
                    bundle.putParcelable(Constants.BUNDLE_KEY_CONTACT_NAME_KEY, this.mContact.getContactName());
                }
            }
            bundle.putString(Constants.BUNDLE_KEY_CONTACT_ID, this.mContact.getId());
            bundle.putParcelableArrayList(Constants.BUNDLE_KEY_PHONE_NUMBERS, this.contactPhoneNumberList);
            bundle.putString("company", this.mContact.getCompany());
            bundle.putString("ownerCommsId", this.mContact.getOwnerCommsId());
            FragmentNavigationRouter.switchToFragment(CommsView.EditContact, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onToggleCallerId(@NonNull View view) {
        ToggleButton toggleButton = (ToggleButton) view;
        boolean isChecked = toggleButton.isChecked();
        CommsLogger commsLogger = LOG;
        commsLogger.d("User pressed caller ID toggle: " + isChecked);
        if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_DETAILS, AlertSource.newClassSource(ContactCardFragment.class.getName()))) {
            toggleButton.toggle();
            return;
        }
        setSetCallerIdSubscriber(new SetCallerIdSubscriber(null));
        CoboUtils.setShowPhoneNumberToCalleeObservable(isChecked).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super Boolean>) this.mSetCallerIdSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onToggleVoiceMsgConsent(@NonNull View view) {
        ToggleButton toggleButton = (ToggleButton) view;
        boolean isChecked = toggleButton.isChecked();
        CommsLogger commsLogger = LOG;
        commsLogger.d("User pressed voice message consent toggle: " + isChecked);
        if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_DETAILS, AlertSource.newClassSource(ContactCardFragment.class.getName()))) {
            toggleButton.toggle();
        } else {
            setVoiceMsgConsent(isChecked);
        }
    }

    private void refreshContactFromServer() {
        new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.12
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                Contact contact = new RefreshContact(ContactCardFragment.this.mCommsIdentityManager.getCommsId("ContactCardFragment.refreshContactFromServer", false), ContactCardFragment.this.mContactId).getContact();
                if (contact != null) {
                    ContactCardFragment.this.updateContact(contact);
                    return true;
                }
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                if (bool == null || !bool.booleanValue()) {
                    return;
                }
                ContactCardFragment.this.updateBlockLink();
                ContactCardInfo createContactCardInfo = ContactCardInfo.createContactCardInfo(ContactCardFragment.this.mContactId);
                if (createContactCardInfo == null) {
                    return;
                }
                ContactCardFragment.this.updateUserPhoneNumbers(createContactCardInfo);
                ContactCardFragment.this.mContactName.setText(ContactCardFragment.this.getDisplayName(createContactCardInfo));
            }
        }.execute(new Void[0]);
    }

    private void setCanDropInValueOnServer() {
        if (Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_DETAILS, AlertSource.newClassSource(ContactCardFragment.class.getName()))) {
            this.mDropInToggle.toggle();
        } else {
            new SetDropInAsyncTask().execute(Boolean.valueOf(this.mDropInToggle.isChecked()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDiagnosticsEnabled(boolean z) {
        if (z) {
            this.mContactName.setOnLongClickListener($$Lambda$ContactCardFragment$KkuDNZZgnSKiELxo0GEE_6FA38.INSTANCE);
        } else {
            this.mContactName.setOnLongClickListener(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDropInListItemOnServer(String str, boolean z) {
        if (!Utils.isOfflineDialogShown(getActivity(), false, MetricKeys.SCREEN_NAME_CONTACT_DETAILS, AlertSource.newClassSource(ContactCardFragment.class.getName()))) {
            new RemoveDropInAsyncTask().execute(str, Boolean.valueOf(z));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setMenuItemVisibility() {
        MenuItem findItem;
        Menu menu = this.mMenu;
        if (menu == null || (findItem = menu.findItem(R.id.edit_contact)) == null) {
            return;
        }
        findItem.setVisible(showEditButton(this.mContactCardInfo));
    }

    private void setVoiceMsgConsent(final boolean z) {
        final FragmentActivity activity = getActivity();
        new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.11
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            @Nullable
            public Boolean doInBackground(@Nullable Void... voidArr) {
                return VoiceMessageTranscriptionConsent.setACMSUserPref(z);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(@Nullable Boolean bool) {
                MetricsHelper.recordVoiceMsgConsentMetrics(bool, MetricKeys.VMC_TOGGLE, MetricKeys.SCREEN_NAME_CONTACT_DETAILS, AlertSource.newClassSource(ContactCardFragment.this));
                if (bool == null) {
                    VoiceMessageTranscriptionConsent.showAlertErrorDialog(activity);
                } else {
                    VoiceMessageTranscriptionConsent.setSharedPref(bool.booleanValue(), activity);
                }
                boolean booleanValue = ((Boolean) SharedPreferencesUtils.getCacheValue(ContactCardFragment.this.getContext(), Constants.SHARED_PREF_VOICE_MSG_CONSENT, false)).booleanValue();
                if (ContactCardFragment.this.mShareDataConsentToggle.isChecked() != booleanValue) {
                    ContactCardFragment.this.mShareDataConsentToggle.setChecked(booleanValue);
                }
            }
        }.execute(new Void[0]);
    }

    private void setVoiceMsgConsentToggleValue() {
        new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.10
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            @Nullable
            public Boolean doInBackground(Void... voidArr) {
                try {
                    Boolean aCMSUserPref = VoiceMessageTranscriptionConsent.getACMSUserPref();
                    return aCMSUserPref != null ? aCMSUserPref : Boolean.valueOf(!VoiceMessageTranscriptionConsent.pfmRequiresAccess());
                } catch (ServiceException e) {
                    ContactCardFragment.LOG.e("Unable to get toggle value for voice message transcription consent", e);
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(@Nullable Boolean bool) {
                if (bool != null) {
                    if (bool.booleanValue() == ((Boolean) SharedPreferencesUtils.getCacheValue(ContactCardFragment.this.getContext(), Constants.SHARED_PREF_VOICE_MSG_CONSENT, false)).booleanValue()) {
                        return;
                    }
                    ContactCardFragment.this.mShareDataConsentToggle.setChecked(bool.booleanValue());
                    SharedPreferencesUtils.persistCacheValues(ContactCardFragment.this.getContext(), Constants.SHARED_PREF_VOICE_MSG_CONSENT, bool);
                }
            }
        }.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupCallerId(@NonNull ContactCardInfo contactCardInfo) {
        if (CoboUtils.isCallerIDToggleAvailable() && isSelf(contactCardInfo)) {
            CommsLogger commsLogger = LOG;
            StringBuilder outline1 = GeneratedOutlineSupport.outline1("Displaying COBO preferences to user ");
            outline1.append(LOG.sensitive(this.mCommsId));
            commsLogger.i(outline1.toString());
            this.mCallerIdLayout.setVisibility(0);
            this.mCallerIdToggle.setChecked(((Boolean) SharedPreferencesUtils.getCacheValue(getContext(), Constants.SHARED_PREF_CALLER_ID_SHOWN, true)).booleanValue());
            setGetCallerIdSubscriber(new GetCallerIdSubscriber(null));
            CoboUtils.getShowPhoneNumberToCalleeObservable().observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super Boolean>) this.mGetCallerIdSubscriber);
            return;
        }
        CommsLogger commsLogger2 = LOG;
        StringBuilder outline12 = GeneratedOutlineSupport.outline1("Hiding COBO preferences from user ");
        outline12.append(LOG.sensitive(this.mCommsId));
        commsLogger2.i(outline12.toString());
        this.mCallerIdLayout.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupDropInToggleState() {
        Observable.fromCallable(new Callable() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$kXRMVWYom1PRzTHM7EUx1I68SOM
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ContactCardFragment.this.lambda$setupDropInToggleState$21$ContactCardFragment();
            }
        }).subscribe((Subscriber) this.mDropInStateSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupMyProfile(@NonNull ContactCardInfo contactCardInfo) {
        boolean isSelf = isSelf(contactCardInfo);
        boolean isOwnHomeGroup = isOwnHomeGroup(contactCardInfo);
        if (this.mIsChildProfile) {
            this.mMyProfileText.setText(getResources().getText(R.string.free_time_profile));
            this.mMyProfileText.setVisibility(0);
        } else if (!isSelf && !isOwnHomeGroup) {
        } else {
            this.mMyProfileText.setText(getResources().getText(isSelf ? R.string.my_profile : R.string.my_home));
            this.mMyProfileText.setVisibility(0);
        }
    }

    private void setupSubscribers() {
        this.mContactCardInfoSubscriber = new SimpleSubscriber<ContactCardInfo>() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.4
            @Override // com.amazon.deecomms.rx.SimpleSubscriber
            public void onCall(ContactCardInfo contactCardInfo) {
                if (contactCardInfo == null) {
                    ContactCardFragment.LOG.e("Null contact card info!!!");
                    return;
                }
                ContactCardFragment.this.mContactCardInfo = contactCardInfo;
                ContactCardFragment.this.mAlexaEnabled = contactCardInfo.isAlexaEnabled();
                ContactCardFragment.this.mParentHGId = contactCardInfo.getParentHomeGroupId();
                String displayName = ContactCardFragment.this.getDisplayName(contactCardInfo);
                if (ContactCardFragment.this.mAlexaEnabled) {
                    ContactCardFragment.this.mCommsId = contactCardInfo.getCommsId();
                }
                int i = 0;
                if (!ContactCardFragment.this.mAlexaEnabled || ContactCardFragment.this.mIsChildProfile) {
                    ContactCardFragment.this.mPermissionsLayout.setVisibility(8);
                } else {
                    if (!Utils.isRegisteredCommsId(ContactCardFragment.this.mCommsId)) {
                        ContactCardFragment.this.updateBlockLink();
                    }
                    ContactCardFragment.this.mPermissionsLayout.setVisibility(0);
                }
                ContactCardFragment.this.configureFragmentRequirements();
                ContactCardFragment.this.setupCallerId(contactCardInfo);
                ContactCardFragment.this.updateViews(displayName);
                ContactCardFragment.this.setupVoiceMsgConsent(contactCardInfo);
                ContactCardFragment.this.setupMyProfile(contactCardInfo);
                ContactCardFragment.this.updateUserPhoneNumbers(contactCardInfo);
                if (contactCardInfo.getEmailAddressList() != null) {
                    for (ContactEmailAddress contactEmailAddress : contactCardInfo.getEmailAddressList()) {
                        ContactCardFragment.this.updateUserEmailInfo(contactEmailAddress);
                    }
                }
                if (!ContactCardFragment.this.isSelf(contactCardInfo) && !ContactCardFragment.this.mIsChildProfile) {
                    ContactCardFragment.this.updateContactSource();
                }
                if (!TextUtils.isEmpty(ContactCardFragment.this.mParentHGId) && !ContactCardFragment.this.mIsChildProfile) {
                    ContactCardFragment.this.setupDropInToggleState();
                }
                boolean isSelf = IdentityValidator.isSelf(contactCardInfo.getCommsId());
                boolean isMyHousehold = IdentityValidator.isMyHousehold(contactCardInfo.getCommsId());
                if ((isSelf || isMyHousehold) && !ContactCardFragment.this.mIsChildProfile) {
                    ContactCardFragment.this.updateDropInList();
                    ContactCardFragment.this.updateChildAccountList();
                } else {
                    ContactCardFragment.this.mDropInListLayout.setVisibility(8);
                    ContactCardFragment.this.mChildAccountListLayout.setVisibility(8);
                }
                if (ContactCardFragment.this.mIsChildProfile) {
                    ContactCardFragment.this.updateChildContactList();
                    ContactCardFragment.this.getChildContactsFromServer();
                } else {
                    ContactCardFragment.this.mChildContactListLayout.setVisibility(8);
                }
                if (contactCardInfo.canIDropInOnHim()) {
                    ContactCardFragment.this.mDropIn.setVisibility(0);
                } else {
                    ContactCardFragment.this.mDropIn.setVisibility(8);
                }
                Button button = ContactCardFragment.this.abtAlexaComms;
                if (!isSelf) {
                    i = 8;
                }
                button.setVisibility(i);
                ContactCardFragment.this.setMenuItemVisibility();
            }
        };
        this.mDiagnosticsFeatureSubscriber = new SimpleSubscriber<Boolean>("Error computing diagnostics feature flag") { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.5
            @Override // com.amazon.deecomms.rx.SimpleSubscriber
            public void onCall(Boolean bool) {
                ContactCardFragment.this.setDiagnosticsEnabled(bool.booleanValue());
            }
        };
        this.mDropInListUpdateSubscriber = new DropInListUpdateSubscriber(null);
        this.mChildAccountListUpdateSubscriber = new ChildAccountListUpdateSubscriber(null);
        this.mChildContactListUpdateSubscriber = new ChildContactListUpdateSubscriber(null);
        this.mDropInStateSubscriber = new SimpleSubscriber<ContactEntry>() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.6
            @Override // com.amazon.deecomms.rx.SimpleSubscriber
            public void onCall(ContactEntry contactEntry) {
                if (contactEntry == null) {
                    ContactCardFragment.LOG.w("We were unable to find a db entry for the provided hg id");
                    return;
                }
                ContactCardFragment.this.mParentContactId = contactEntry.getId();
                ContactCardFragment.access$4700(ContactCardFragment.this, contactEntry.getDropInState() != null && contactEntry.getDropInState() == DropInState.ON);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupVoiceMsgConsent(@NonNull ContactCardInfo contactCardInfo) {
        if (isSelf(contactCardInfo)) {
            this.mShareDataConsentToggle.setChecked(((Boolean) SharedPreferencesUtils.getCacheValue(getContext(), Constants.SHARED_PREF_VOICE_MSG_CONSENT, !VoiceMessageTranscriptionConsent.pfmRequiresAccess())).booleanValue());
            this.mDiagnosticsLayout.setVisibility(0);
            setVoiceMsgConsentToggleValue();
            return;
        }
        this.mDiagnosticsLayout.setVisibility(8);
    }

    private void showDropInWarningDialog(int i) {
        this.dropInConfirmDialog = new CustomDialogBuilder(getActivity()).withTitle(R.string.drop_in_popup_title).withBody(i).withPositiveClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$YYHUusJisSXqberHdjf-uWS1hbg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContactCardFragment.this.lambda$showDropInWarningDialog$14$ContactCardFragment(view);
            }
        }).withNegativeClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$O9OKFVAAPokI6xzpP_LBBStt6hc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContactCardFragment.this.lambda$showDropInWarningDialog$15$ContactCardFragment(view);
            }
        }).withCancelListener(new DialogInterface.OnCancelListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$MI4eO2G9zkHDjSAI6FTYvXdQrmA
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                ContactCardFragment.this.lambda$showDropInWarningDialog$16$ContactCardFragment(dialogInterface);
            }
        }).withDismissListener(new DialogInterface.OnDismissListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$Nm9NENs3R4Jy_0bkeOq5eOg9iMk
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                ContactCardFragment.this.lambda$showDropInWarningDialog$17$ContactCardFragment(dialogInterface);
            }
        }).build();
        this.dropInConfirmDialog.show();
    }

    private boolean showEditButton(@Nullable ContactCardInfo contactCardInfo) {
        if (this.mContact == null || contactCardInfo == null) {
            return false;
        }
        if (isSelf(contactCardInfo)) {
            return true;
        }
        if (!TextUtils.equals(this.mContact.getOwnerCommsId(), this.mCommsIdentityManager.getHomeGroupId("ContactCardFragment.showEditButton", false))) {
            return !this.mContact.isBulkImport();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRemoveDropInWarningDialog(final String str) {
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$9TAu-YWdbyUZfb-vLMnESwusEEo
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ContactCardFragment.this.lambda$showRemoveDropInWarningDialog$18$ContactCardFragment(str, dialogInterface, i);
            }
        };
        new AlertDialog.Builder(new ContextThemeWrapper(getActivity(), R.style.AlexaCustomDialogStyle)).setTitle(R.string.dropin_list_remove_title).setMessage(R.string.remove_drop_in_disclosure).setPositiveButton(17039370, onClickListener).setNegativeButton(17039360, $$Lambda$ContactCardFragment$q6ALe8o5pkSOMfUHIrJAhfhuO8.INSTANCE).setInverseBackgroundForced(false).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchToChildAccount(ContactEntry contactEntry) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.CONTACT_ENTRY_KEY, contactEntry);
        FragmentNavigationRouter.switchToFragment(CommsView.ContactCard, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void switchToChildContact(ContactEntry contactEntry) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.CHILD_CONTACT_ENTRY_KEY, contactEntry);
        bundle.putString(Constants.CHILD_CONTACT_OWNER_NAME, ContactUtils.getFullName(this.mContact.getContactName()));
        FragmentNavigationRouter.switchToFragment(CommsView.ChildContactCard, bundle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateChildAccountList() {
        Observable.fromCallable(new Callable() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$fTtzrnCQIV1H_ijv0JP7CFTwZHU
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ContactCardFragment.this.lambda$updateChildAccountList$22$ContactCardFragment();
            }
        }).subscribe((Subscriber) this.mChildAccountListUpdateSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateChildContactList() {
        Observable.fromCallable(new Callable() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$JXr6CBEouwipHUlFlEsJvDgwBZk
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ContactCardFragment.this.lambda$updateChildContactList$23$ContactCardFragment();
            }
        }).subscribe((Subscriber) this.mChildContactListUpdateSubscriber);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContact(@NonNull Contact contact) {
        this.mContact.setBlocked(contact.isBlocked());
        this.mContact.setPhoneNumbers(contact.getPhoneNumbers());
        updateContactName(contact);
        ContactsProviderUtils.updateContactBlockStatus(this.appContext, contact.getId(), contact.isBlocked());
        ContactsProviderUtils.updateContactPhoneNumbers(this.appContext, contact);
        if (!this.mContact.isEverRefreshed()) {
            this.mContact.setEverRefreshed(true);
            ContactsProviderUtils.updateIsContactEverRefreshed(this.appContext, contact.getId(), true);
        }
    }

    private void updateContactName(@NonNull Contact contact) {
        ContactName contactName = contact.getContactName();
        if (contactName != null) {
            this.mContact.setContactName(contactName);
            ContactEntry contactEntry = (ContactEntry) getArguments().getParcelable(Constants.CONTACT_ENTRY_KEY);
            if (contactEntry == null) {
                return;
            }
            contactEntry.setContactName(contactName);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContactSource() {
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.contact_card_device_source_info, this.mUserInfoContentLayout, false);
        TextView textView = (TextView) inflate.findViewById(R.id.user_info_line_one);
        TextView textView2 = (TextView) inflate.findViewById(R.id.user_info_line_two);
        String sourceDeviceId = this.mContact.getSourceDeviceId();
        if (!isBulkImportedContact()) {
            textView.setText(R.string.contact_source_cloud_title);
            textView2.setText(R.string.contact_source_cloud_description);
        } else {
            textView.setText(R.string.contact_source_lab_title);
            if (new DeviceInfo().getUniqueDeviceId(getContext()).equals(sourceDeviceId)) {
                textView2.setText(R.string.contact_source_lab_master_device);
            } else {
                textView2.setText(R.string.contact_source_lab_other_device);
            }
        }
        textView.setVisibility(0);
        textView2.setVisibility(0);
        this.mUserInfoLayout.setVisibility(0);
        this.mUserInfoContentLayout.addView(inflate);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDropInButton() {
        this.mDropIn.setActivated(this.mIsContactActive);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDropInList() {
        Observable.fromCallable(new Callable() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$MHziQ2ld3LguFbn03mxLcYZGZa4
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return ContactCardFragment.this.lambda$updateDropInList$20$ContactCardFragment();
            }
        }).subscribe((Subscriber) this.mDropInListUpdateSubscriber);
    }

    private void updateDropInToggle(boolean z) {
        this.mDropInToggle.setChecked(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUserEmailInfo(@NonNull ContactEmailAddress contactEmailAddress) {
        String emailAddress = contactEmailAddress.getEmailAddress();
        if (TextUtils.isEmpty(emailAddress)) {
            LOG.e("Invalid email address is empty or null");
            return;
        }
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.contact_card_email_info, this.mUserInfoContentLayout, false);
        TextView textView = (TextView) inflate.findViewById(R.id.email_title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.email_address);
        EmailAddressType type = contactEmailAddress.getType();
        String rawType = contactEmailAddress.getRawType();
        if (type != EmailAddressType.Custom || TextUtils.isEmpty(rawType)) {
            rawType = getString(type.getDisplayResId());
        }
        textView.setText(rawType);
        textView2.setText(emailAddress);
        this.mUserInfoContentLayout.addView(inflate);
        this.mUserInfoLayout.setVisibility(0);
    }

    private void updateUserInfo(@NonNull final ContactPhoneNumber contactPhoneNumber) {
        String phoneNumber = contactPhoneNumber.getPhoneNumber();
        if (TextUtils.isEmpty(phoneNumber)) {
            LOG.e("Invalid phone number is empty or null");
        } else if (getActivity() != null && !getActivity().isFinishing()) {
            View inflate = getActivity().getLayoutInflater().inflate(R.layout.card_user_info_view, (ViewGroup) this.mUserContactInfoLayout, false);
            TextView textView = (TextView) inflate.findViewById(R.id.card_user_info_description);
            ((TextView) inflate.findViewById(R.id.card_user_info_title)).setText(CallViewUtils.getPhoneNumberType(getActivity(), contactPhoneNumber));
            textView.setText(Utils.formatPhoneNumber(phoneNumber));
            this.mUserContactInfoLayout.addView(inflate);
            if (CoboUtils.isCallerIDToggleAvailable()) {
                textView.setContentDescription(String.format(getString(R.string.audiocall_button), contactPhoneNumber.getPhoneNumber()));
                if (contactPhoneNumber.isCOBOEnabled() && CoboUtils.isCoboCallingAvailable()) {
                    textView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$g6qgpTvTKpHVXW7_9FD0-NQxQww
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            ContactCardFragment.this.lambda$updateUserInfo$7$ContactCardFragment(contactPhoneNumber, view);
                        }
                    });
                    textView.setTextColor(ContextCompat.getColor(getContext(), R.color.control_active_mobile_number));
                    return;
                }
                textView.setOnClickListener(null);
                textView.setTextColor(ContextCompat.getColor(getContext(), R.color.control_inactive_mobile_number));
                return;
            }
            textView.setOnClickListener(null);
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.control_inactive_mobile_number));
        } else {
            LOG.d("Not updating user info as the activity object is null / finishing");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateUserPhoneNumbers(@NonNull ContactCardInfo contactCardInfo) {
        boolean z;
        this.mUserContactInfoLayout.removeAllViews();
        if (contactCardInfo.getPhoneList() != null) {
            this.contactPhoneNumberList = new ArrayList<>(contactCardInfo.getPhoneList());
            z = false;
            for (ContactPhoneNumber contactPhoneNumber : contactCardInfo.getPhoneList()) {
                updateUserInfo(contactPhoneNumber);
                z |= CoboUtils.isCoboCallingAvailable() && contactPhoneNumber.isCOBOEnabled();
            }
        } else {
            z = false;
        }
        if (CoboUtils.isCallerIDToggleAvailable()) {
            if (this.mUserInfoContentLayout.getChildCount() > 0) {
                this.mUserInfoLayout.setVisibility(0);
            } else {
                this.mUserInfoLayout.setVisibility(8);
            }
            this.mContactUnreachableView.setVisibility(8);
        } else if (!contactCardInfo.isAlexaEnabled() && !z) {
            this.mUserInfoLayout.setVisibility(0);
            this.mContactUnreachableView.setVisibility(0);
        } else if (this.mUserInfoContentLayout.getChildCount() > 0) {
            this.mUserInfoLayout.setVisibility(0);
            this.mContactUnreachableView.setVisibility(8);
        } else {
            this.mUserInfoLayout.setVisibility(8);
            this.mContactUnreachableView.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateViews(String str) {
        this.mContactName.setText(str);
        if (!this.mAlexaEnabled) {
            this.mDropInToggleLayout.setVisibility(8);
            this.mImageButtonsLayout.setVisibility(8);
            return;
        }
        this.mImageButtonsLayout.setVisibility(0);
        this.mDropInToggleLayout.setVisibility(0);
        this.mAudioCall.setContentDescription(String.format(Locale.getDefault(), getResources().getString(R.string.audiocall_button), str));
        this.mVideoCall.setContentDescription(String.format(Locale.getDefault(), getResources().getString(R.string.videocall_button), str));
        this.mDropIn.setContentDescription(String.format(Locale.getDefault(), getResources().getString(R.string.dropin_button), str));
        this.mMessage.setContentDescription(String.format(Locale.getDefault(), getResources().getString(R.string.message_button), str));
        this.mDropInToggle.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$cm8g-3HN0RtopWhFTgfKgeBPsdg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContactCardFragment.this.lambda$updateViews$12$ContactCardFragment(view);
            }
        });
        this.mDropInToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$92uPkXFZY_DFA2DbYf2jyTPYLJU
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ContactCardFragment.this.lambda$updateViews$13$ContactCardFragment(compoundButton, z);
            }
        });
        if (!this.mIsChildProfile) {
            return;
        }
        this.mDropInToggle.setVisibility(8);
    }

    @Override // com.amazon.deecomms.common.DeviceBottomSheetTarget
    @Nullable
    public DeviceBottomSheet getBottomSheetDialogFragment() {
        return this.deviceBottomSheet;
    }

    public /* synthetic */ boolean lambda$configureFragmentRequirements$4$ContactCardFragment(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.edit_contact) {
            onEditClicked();
            return true;
        }
        return false;
    }

    public /* synthetic */ void lambda$configureFragmentRequirements$5$ContactCardFragment(Menu menu) {
        this.mMenu = menu;
        setMenuItemVisibility();
    }

    public /* synthetic */ void lambda$init$10$ContactCardFragment(View view) {
        onClickLearnMore("contact card drop-in help icon");
    }

    public /* synthetic */ void lambda$init$11$ContactCardFragment(View view) {
        onClickLearnMore("About Alexa Communications link in My profile");
    }

    public /* synthetic */ void lambda$init$8$ContactCardFragment(View view) {
        onClickLearnMore("contact card permissions help icon");
    }

    public /* synthetic */ void lambda$init$9$ContactCardFragment(View view) {
        onClickLearnMore("contact card diagnostic help icon");
    }

    public /* synthetic */ void lambda$makePstnCall$3$ContactCardFragment(ContactPhoneNumber contactPhoneNumber) {
        makeACall(false, false, false, contactPhoneNumber);
    }

    public /* synthetic */ void lambda$onCreateView$0$ContactCardFragment(View view) {
        FragmentNavigationRouter.goToConversationThread(getActivity(), this.mCommsId, this.mIsChildProfile);
    }

    public /* synthetic */ ContactCardInfo lambda$onCreateView$1$ContactCardFragment(ContactCardInfo contactCardInfo) {
        if (contactCardInfo == null) {
            if (getArguments() == null || !getArguments().getBoolean(Constants.BUNDLE_KEY_MY_PROFILE)) {
                return null;
            }
            LOG.i("Contact card info null, displaying identity info");
            ContactCardInfo contactCardInfo2 = new ContactCardInfo(null);
            contactCardInfo2.setCommsId(this.mCommsIdentityManager.getCommsId("ContactCardFragment.onCreateView", false));
            contactCardInfo2.setParentHomeGroupId(this.mCommsIdentityManager.getHomeGroupId("ContactCardFragment.onCreateView", false));
            return contactCardInfo2;
        }
        return contactCardInfo;
    }

    public /* synthetic */ ContactEntry lambda$setupDropInToggleState$21$ContactCardFragment() throws Exception {
        Cursor mo3665loadInBackground = new CursorLoader(getActivity().getApplicationContext(), ContactProviderContract.getContactJoinPhoneNumberUri(getContext()), (String[]) ImmutableList.builder().mo7848add((Object[]) ContactsProviderUtils.getColumnsForContactEntry()).mo7849add((ImmutableList.Builder) ContactProviderContract.ContactDatabaseEntry.COLUMN_CAN_DROP_IN_ON_ME).mo7852build().toArray(new String[2]), "commsId = ?", new String[]{this.mParentHGId}, null).mo3665loadInBackground();
        if (mo3665loadInBackground != null) {
            try {
                if (mo3665loadInBackground.moveToFirst()) {
                    ContactEntry contactEntryFromCursor = ContactsProviderUtils.getContactEntryFromCursor(mo3665loadInBackground);
                    mo3665loadInBackground.close();
                    return contactEntryFromCursor;
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        mo3665loadInBackground.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (mo3665loadInBackground != null) {
            mo3665loadInBackground.close();
            return null;
        }
        return null;
    }

    public /* synthetic */ void lambda$showDropInWarningDialog$14$ContactCardFragment(View view) {
        setCanDropInValueOnServer();
        this.dropInConfirmDialog.dismiss();
    }

    public /* synthetic */ void lambda$showDropInWarningDialog$15$ContactCardFragment(View view) {
        this.mDropInToggle.setChecked(false);
        this.dropInConfirmDialog.dismiss();
    }

    public /* synthetic */ void lambda$showDropInWarningDialog$16$ContactCardFragment(DialogInterface dialogInterface) {
        this.mDropInToggle.setChecked(false);
    }

    public /* synthetic */ void lambda$showDropInWarningDialog$17$ContactCardFragment(DialogInterface dialogInterface) {
        this.dropInConfirmDialog = null;
    }

    public /* synthetic */ void lambda$showRemoveDropInWarningDialog$18$ContactCardFragment(String str, DialogInterface dialogInterface, int i) {
        setDropInListItemOnServer(str, true);
    }

    public /* synthetic */ List lambda$updateChildAccountList$22$ContactCardFragment() throws Exception {
        Cursor mo3665loadInBackground = new CursorLoader(getContext(), ContactProviderContract.getContactsUri(getContext()), ContactsProviderUtils.getColumnsForContactEntry(), "isChild = ? AND ownerCommsId IN (?, ?) ", new String[]{"1", this.mCommsIdentityManager.getCommsId("ContactCardFragment.updateChildAccountList", false), this.mCommsIdentityManager.getHomeGroupId("ContactCardFragment.updateChildAccountList", false)}, null).mo3665loadInBackground();
        if (mo3665loadInBackground == null) {
            if (mo3665loadInBackground == null) {
                return null;
            }
            mo3665loadInBackground.close();
            return null;
        }
        try {
            List<ContactEntry> contactEntriesFromCursor = ContactsProviderUtils.getContactEntriesFromCursor(mo3665loadInBackground);
            mo3665loadInBackground.close();
            return contactEntriesFromCursor;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    mo3665loadInBackground.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public /* synthetic */ List lambda$updateChildContactList$23$ContactCardFragment() throws Exception {
        if (TextUtils.isEmpty(this.mCommsId)) {
            return null;
        }
        Cursor mo3665loadInBackground = new CursorLoader(getContext(), ContactProviderContract.getContactsUri(getContext()), ContactsProviderUtils.getColumnsForContactEntry(), "ownerCommsId = ?", new String[]{this.mCommsId}, null).mo3665loadInBackground();
        if (mo3665loadInBackground == null) {
            if (mo3665loadInBackground != null) {
                mo3665loadInBackground.close();
            }
            return null;
        }
        try {
            List<ContactEntry> contactEntriesFromCursor = ContactsProviderUtils.getContactEntriesFromCursor(mo3665loadInBackground);
            mo3665loadInBackground.close();
            return contactEntriesFromCursor;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    mo3665loadInBackground.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public /* synthetic */ List lambda$updateDropInList$20$ContactCardFragment() throws Exception {
        Cursor mo3665loadInBackground = new CursorLoader(getContext(), ContactProviderContract.getContactsUri(getContext()), ContactsProviderUtils.getColumnsForContactEntry(), "canDropInOnMe = ?", new String[]{"1"}, null).mo3665loadInBackground();
        if (mo3665loadInBackground == null) {
            if (mo3665loadInBackground == null) {
                return null;
            }
            mo3665loadInBackground.close();
            return null;
        }
        try {
            List<ContactEntry> contactEntriesFromCursor = ContactsProviderUtils.getContactEntriesFromCursor(mo3665loadInBackground);
            mo3665loadInBackground.close();
            return contactEntriesFromCursor;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    mo3665loadInBackground.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    public /* synthetic */ void lambda$updateUserInfo$7$ContactCardFragment(ContactPhoneNumber contactPhoneNumber, View view) {
        makePstnCall(contactPhoneNumber);
    }

    public /* synthetic */ void lambda$updateViews$12$ContactCardFragment(View view) {
        if (this.mDropInToggle.isChecked()) {
            if (!IdentityValidator.isSelf(this.mCommsId) && !IdentityValidator.isMyHousehold(this.mParentHGId)) {
                showDropInWarningDialog(R.string.drop_in_disclosure_default);
                return;
            } else {
                showDropInWarningDialog(R.string.drop_in_disclosure_homegroup_member);
                return;
            }
        }
        setCanDropInValueOnServer();
    }

    public /* synthetic */ void lambda$updateViews$13$ContactCardFragment(CompoundButton compoundButton, boolean z) {
        if (isSelfOrHg() || isMemberOfHG()) {
            this.mDropIn.setVisibility(z ? 0 : 8);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        if (2000 == i) {
            this.applicationManager.navigateUpward();
        }
    }

    @Override // com.amazon.deecomms.contacts.util.BlockContactListener
    public void onBlockContactTaskCompleted(boolean z) {
        if (z) {
            updateBlockLink();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CommsDaggerWrapper.getComponent().inject(this);
        View inflate = layoutInflater.inflate(R.layout.contact_card_view, viewGroup, false);
        init(inflate);
        this.mAudioCall.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.7
            @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
            public void onSingleClick(View view) {
                ContactCardFragment.this.makeACall(false, false, false, null);
            }
        });
        this.mVideoCall.setVisibility(Utils.isCameraPresent(getContext()) ? 0 : 8);
        this.mVideoCall.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.8
            @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
            public void onSingleClick(View view) {
                ContactCardFragment.this.makeACall(true, false, false, null);
            }
        });
        if (this.mIsChildProfile) {
            this.mVideoCall.setVisibility(8);
        } else {
            this.mVideoCall.setVisibility(0);
        }
        this.mDropIn.setOnClickListener(new OnSingleClickListener() { // from class: com.amazon.deecomms.contacts.ui.ContactCardFragment.9
            @Override // com.amazon.deecomms.common.ui.util.OnSingleClickListener
            public void onSingleClick(View view) {
                ContactCardFragment.this.makeACall(true, true, false, null);
                MetricsHelper.recordSingleOccurrenceClickstreamByChild(MetricKeys.CONTACTS_DROPIN_INITIATE, ContactCardFragment.this.mIsChildProfile);
            }
        });
        this.mMessage.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$-_lwlPz6AlDMR4s6Q34W_rSysvo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContactCardFragment.this.lambda$onCreateView$0$ContactCardFragment(view);
            }
        });
        Observable<R> map = ContactCardInfo.createContactCardInfoObservable(getContext(), this.mContactId).map(new Func1() { // from class: com.amazon.deecomms.contacts.ui.-$$Lambda$ContactCardFragment$dVVfZdQjaNjBmFZTypguxVqIRtM
            @Override // rx.functions.Func1
            /* renamed from: call */
            public final Object mo13102call(Object obj) {
                return ContactCardFragment.this.lambda$onCreateView$1$ContactCardFragment((ContactCardInfo) obj);
            }
        });
        map.subscribe((Subscriber<? super R>) this.mContactCardInfoSubscriber);
        Observable.combineLatest(map, this.capabilitiesManager.getDiagnosticsObservable(), $$Lambda$ContactCardFragment$wht_14lQ0kHqWMkYaXcXvSkNx4.INSTANCE).subscribe((Subscriber) this.mDiagnosticsFeatureSubscriber);
        Utils.configureAccessibilityFocus(getActivity(), this.mContactName, getResources().getString(R.string.contact_card));
        this.contactPresenceServiceBinding.startConnection(getActivity(), this.bindingCallback);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.contactPresenceServiceBinding.stopConnection(getActivity(), false);
        SimpleSubscriber<ContactCardInfo> simpleSubscriber = this.mContactCardInfoSubscriber;
        if (simpleSubscriber != null) {
            simpleSubscriber.unsubscribe();
        }
        SimpleSubscriber<Boolean> simpleSubscriber2 = this.mDiagnosticsFeatureSubscriber;
        if (simpleSubscriber2 != null) {
            simpleSubscriber2.unsubscribe();
        }
        SimpleSubscriber<List<ContactEntry>> simpleSubscriber3 = this.mDropInListUpdateSubscriber;
        if (simpleSubscriber3 != null) {
            simpleSubscriber3.unsubscribe();
        }
        SimpleSubscriber<ContactEntry> simpleSubscriber4 = this.mDropInStateSubscriber;
        if (simpleSubscriber4 != null) {
            simpleSubscriber4.unsubscribe();
        }
        SimpleSubscriber<Boolean> simpleSubscriber5 = this.mDiagnosticsFeatureSubscriber;
        if (simpleSubscriber5 != null) {
            simpleSubscriber5.unsubscribe();
        }
        SimpleSubscriber<List<ContactEntry>> simpleSubscriber6 = this.mChildAccountListUpdateSubscriber;
        if (simpleSubscriber6 != null) {
            simpleSubscriber6.unsubscribe();
        }
        SimpleSubscriber<List<ContactEntry>> simpleSubscriber7 = this.mChildContactListUpdateSubscriber;
        if (simpleSubscriber7 != null) {
            simpleSubscriber7.unsubscribe();
        }
        setGetCallerIdSubscriber(null);
        setSetCallerIdSubscriber(null);
        setBottomSheetDialogFragment(null);
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        AppCompatDialog appCompatDialog = this.dropInConfirmDialog;
        if (appCompatDialog != null) {
            appCompatDialog.cancel();
        }
        AppCompatDialog appCompatDialog2 = this.smsConfirmDialog;
        if (appCompatDialog2 != null) {
            appCompatDialog2.cancel();
        }
        BlockContactHelper blockContactHelper = this.mBlockContactHelper;
        if (blockContactHelper != null) {
            blockContactHelper.hideBlockContactDialog();
        }
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        if (!PermissionsHelper.checkGrantedPermissions(iArr)) {
            CommsLogger commsLogger = LOG;
            commsLogger.i(" Permissions denied for code " + i);
        } else if (i == 3) {
            LOG.i(" Mic Permission granted. ");
            makeACall(false, false, false, null);
        } else if (i == 5) {
            LOG.i(" Mic and camera Permissions granted ");
            makeACall(true, this.mIsDropInCall, false, null);
        } else if (i == 15) {
            makePstnCall(this.mPhoneNumber);
        } else if (i == 9) {
            DeviceBottomSheet deviceBottomSheet = this.deviceBottomSheet;
            if (deviceBottomSheet == null) {
                return;
            }
            deviceBottomSheet.onRequestPermissionsResult(i, strArr, iArr);
        } else if (i != 10) {
            CommsLogger commsLogger2 = LOG;
            commsLogger2.e(" Unknown permission code granted " + i);
        } else {
            makeACall(true, true, false, null);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        configureFragmentRequirements();
        setMenuItemVisibility();
        MetricsHelper.recordSingleOccurrenceClickstream(MetricKeys.SCREEN_CONTACT_DETAIL_SHOWN);
        this.applicationManager.loadingComplete(CommsView.ContactCard);
        BlockContactHelper blockContactHelper = this.mBlockContactHelper;
        if (blockContactHelper != null) {
            blockContactHelper.showBlockContactDialog();
        }
    }

    @Override // com.amazon.deecomms.common.DeviceBottomSheetTarget
    public void setBottomSheetDialogFragment(@Nullable DeviceBottomSheet deviceBottomSheet) {
        this.deviceBottomSheet = deviceBottomSheet;
    }

    public void setGetCallerIdSubscriber(GetCallerIdSubscriber getCallerIdSubscriber) {
        GetCallerIdSubscriber getCallerIdSubscriber2 = this.mGetCallerIdSubscriber;
        if (getCallerIdSubscriber2 != null && !getCallerIdSubscriber2.isUnsubscribed()) {
            this.mGetCallerIdSubscriber.unsubscribe();
        }
        this.mGetCallerIdSubscriber = getCallerIdSubscriber;
    }

    public void setSetCallerIdSubscriber(SetCallerIdSubscriber setCallerIdSubscriber) {
        SetCallerIdSubscriber setCallerIdSubscriber2 = this.mSetCallerIdSubscriber;
        if (setCallerIdSubscriber2 != null && !setCallerIdSubscriber2.isUnsubscribed()) {
            this.mSetCallerIdSubscriber.unsubscribe();
        }
        this.mSetCallerIdSubscriber = setCallerIdSubscriber;
    }

    public void updateBlockLink() {
        if (this.mContact.isAlexaEnabled() && this.mContact.isEverRefreshed() && !isSelfOrHg() && !isMemberOfHG()) {
            this.mBlockContactLink.setVisibility(0);
            if (this.mContact.isBlocked()) {
                this.mBlockContactLink.setText(R.string.contact_card_unblocking_link);
                return;
            } else {
                this.mBlockContactLink.setText(R.string.contact_card_blocking_link);
                return;
            }
        }
        this.mBlockContactLink.setVisibility(8);
    }
}
