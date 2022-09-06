package com.amazon.deecomms.nativemodules;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.alexa.biloba.metrics.MetricsConstants;
import com.amazon.comms.log.CommsLogger;
import com.amazon.deecomms.api.CommsIdentityManager;
import com.amazon.deecomms.calling.controller.CallHelper;
import com.amazon.deecomms.calling.controller.CallingFacade;
import com.amazon.deecomms.calling.initiation.InitiationLogicContract;
import com.amazon.deecomms.calling.initiation.InitiationLogicFactory;
import com.amazon.deecomms.calling.phonecallcontroller.DriveModeCallPermissionHandler;
import com.amazon.deecomms.calling.phonecallcontroller.MakeNativeCallHandler;
import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.amazon.deecomms.calling.util.CallInitiator;
import com.amazon.deecomms.calling.util.CallUtils;
import com.amazon.deecomms.calling.util.CoboUtils;
import com.amazon.deecomms.calling.util.DropInUtils;
import com.amazon.deecomms.common.ApplicationManager;
import com.amazon.deecomms.common.CommsMasterFragment;
import com.amazon.deecomms.common.Constants;
import com.amazon.deecomms.common.metrics.AlertSource;
import com.amazon.deecomms.common.metrics.MetricKeys;
import com.amazon.deecomms.common.network.ServiceException;
import com.amazon.deecomms.common.network.acmsrecipes.UpdateCanDropInPreference;
import com.amazon.deecomms.common.network.acmsrecipes.UpdateContactName;
import com.amazon.deecomms.common.util.ContactUtils;
import com.amazon.deecomms.common.util.Utils;
import com.amazon.deecomms.common.util.VoiceMessageTranscriptionConsent;
import com.amazon.deecomms.contacts.database.ContactEntry;
import com.amazon.deecomms.contacts.model.CallBottomSheetType;
import com.amazon.deecomms.contacts.model.Contact;
import com.amazon.deecomms.contacts.model.ContactName;
import com.amazon.deecomms.contacts.model.ContactPhoneNumber;
import com.amazon.deecomms.contacts.model.Contacts;
import com.amazon.deecomms.contacts.model.FullContactName;
import com.amazon.deecomms.contacts.model.SkypeActionType;
import com.amazon.deecomms.contacts.presence.model.PresenceDocument;
import com.amazon.deecomms.contacts.ui.CallBottomSheetDialogFragment;
import com.amazon.deecomms.contacts.util.ContactDownloader;
import com.amazon.deecomms.contacts.util.ContactStatusManager;
import com.amazon.deecomms.contacts.util.ContactsProviderUtils;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.external.skype.SkypeClient;
import com.amazon.deecomms.nativemodules.model.Person;
import com.amazon.deecomms.nativemodules.util.ContactsDataStoreUtil;
import com.amazon.deecomms.nativemodules.util.ReactBridgeSerializer;
import com.amazon.deecomms.ndt.DeviceListSubscriber;
import com.amazon.deecomms.ndt.model.DeviceModel;
import com.amazon.deecomms.ndt.ui.DeviceBottomSheet;
import com.android.tools.r8.GeneratedOutlineSupport;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import rx.functions.Action1;
/* loaded from: classes12.dex */
public class ContactsManagerBridge extends ReactContextBaseJavaModule {
    private static final String E_INVALID_EVENT = "ERROR_INVALID_EVENT_RECEIVED";
    private static final CommsLogger LOG = CommsLogger.getLogger(Constants.LOG_TAG, ContactsManagerBridge.class);
    private static final String NOT_CONNECTED_TO_INTERNET = "Not connected to internet!";
    private final UpdateContactName mACMSRecipeUpdateContactName;
    private final ApplicationManager mApplicationManager;
    private final CallInitiator mCallInitiator;
    private final CallingFacade mCallingFacade;
    private final CapabilitiesManager mCapabilitiesManager;
    private final CommsIdentityManager mCommsIdentityManager;
    private final ContactStatusManager mContactStatusManager;
    private final ContactsDataStoreUtil mContactsDataStoreUtil;
    private final DriveModeCallPermissionHandler mDriveModeCallPermissionHandler;
    private final InitiationLogicFactory mInitiationLogicFactory;
    private final MakeNativeCallHandler mMakeNativeCallHandler;
    private final ReactBridgeSerializer mReactBridgeSerializer;

    public ContactsManagerBridge(ReactApplicationContext reactApplicationContext) {
        this(reactApplicationContext, new ContactStatusManager(), new ContactsDataStoreUtil(CommsDaggerWrapper.getComponent().getContext()), new UpdateContactName(CommsDaggerWrapper.getComponent().getCommsIdentityManager(), CommsDaggerWrapper.getComponent().getCommsInternal()), CommsDaggerWrapper.getComponent().getCommsIdentityManager(), new CallInitiator(MetricKeys.CONTACT_CALL_INITIATED_FROM_CONTACT_RN), CommsDaggerWrapper.getComponent().getApplicationManager(), CommsDaggerWrapper.getComponent().getCapabilitiesManager(), CommsDaggerWrapper.getComponent().getMakeCallHandler(), CommsDaggerWrapper.getComponent().getDriveModeCallingPermissionHandler(), CommsDaggerWrapper.getComponent().getCallingFacade(), CommsDaggerWrapper.getComponent().getInitiationLogicFactory());
    }

    private void broadcastReactNativeSyncEvent(@NonNull Context context, @NonNull String str) {
        Intent intent = new Intent(Constants.REACT_NATIVE_CONTACTS_IMPORT_INTENT_ACTION);
        intent.putExtra(Constants.REACT_NATIVE_CONTACTS_IMPORT_INTENT_KEY, str);
        CommsLogger commsLogger = LOG;
        commsLogger.i("Broadcasting sync event -- " + str);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }

    @NotNull
    private InitiationLogicContract createInitiationLogic(String str, CallHelper callHelper, Activity activity) {
        return this.mInitiationLogicFactory.create(this.mCallInitiator, activity, activity, callHelper, MetricKeys.SCREEN_NAME_CONVO_LIST, str);
    }

    private Contacts fetchContacts() {
        HashMap newHashMap = Maps.newHashMap();
        String str = this.mCapabilitiesManager.isLightyearEnabled() ? "true" : PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE;
        newHashMap.put(Constants.ACMS_CONTACTS_QUERY_PREFERENCE_LEVEL, "None");
        newHashMap.put(Constants.ACMS_CONTACTS_QUERY_INCLUDE_BLOCK_STATUS, "true");
        newHashMap.put(ContactDownloader.ACMS_QUERY_PARAM_DEDUPE_MODE, ContactDownloader.ACMS_QUERY_PARAM_DEDUPE_MODE_REMOVE_CLOUD_ONLY_CONTACT_DUPLICATES);
        newHashMap.put(Constants.ACMS_CONTACTS_QUERY_INCLUDE_MERGED_CONTACTS, str);
        ContactDownloader contactDownloader = new ContactDownloader();
        contactDownloader.downloadContacts(newHashMap);
        LOG.i("getContactsDebugging: download contacts in fetchContacts");
        return contactDownloader.getContactsAndHomeGroups();
    }

    private static ContactPhoneNumber findContactPhoneNumber(@NonNull Person person, @Nullable String str) {
        if (str == null) {
            return null;
        }
        for (ContactPhoneNumber contactPhoneNumber : person.getPhoneNumbers()) {
            if (str.equals(contactPhoneNumber.getPhoneNumber())) {
                return contactPhoneNumber;
            }
        }
        return null;
    }

    private List<Contact> getBlockableContacts(@Nullable Contacts contacts) {
        if (contacts != null && contacts.getContacts() != null) {
            String commsId = this.mCommsIdentityManager.getCommsId("getBlockableContacts", false);
            String homeGroupId = this.mCommsIdentityManager.getHomeGroupId("getBlockableContacts", false);
            Set<String> myHomeGroupMembers = this.mContactsDataStoreUtil.getMyHomeGroupMembers(homeGroupId);
            LinkedHashMap newLinkedHashMap = Maps.newLinkedHashMap();
            for (Contact contact : contacts.getContacts()) {
                HashSet newHashSet = Sets.newHashSet();
                if (contact.getCommsIds() != null) {
                    newHashSet.addAll(contact.getCommsIds());
                }
                if (contact.isAlexaEnabled() && !newHashSet.isEmpty() && !newHashSet.contains(commsId) && !newHashSet.contains(homeGroupId)) {
                    if (!Sets.intersection(newHashSet, myHomeGroupMembers).isEmpty()) {
                        LOG.w("Contact in my homeGroup - ignoring");
                    } else if (!Sets.intersection(newHashSet, newLinkedHashMap.keySet()).isEmpty()) {
                        LOG.w("Duplicate contact - ignoring");
                    } else {
                        newLinkedHashMap.put((String) newHashSet.iterator().next(), contact);
                    }
                } else {
                    LOG.w("Unblockable contact - ignoring");
                }
            }
            return Lists.newArrayList(newLinkedHashMap.values());
        }
        LOG.w("contacts or getContacts null! no-op");
        return Lists.newArrayList();
    }

    private boolean isOffline() {
        return Utils.isOfflineDialogShown(getCurrentActivity(), false, MetricKeys.RNSCREEN_CONTACT_DETAILS, AlertSource.newClassSource(ContactsManagerBridge.class.getName()));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getShowCallerIdPreference$1(Promise promise, Throwable th) {
        LOG.e("Cannot retrieve CallerID preference from ACMS", th);
        promise.reject((String) null, "Cannot retrieve CallerID preference from ACMS");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setShowCallerIdPreference$3(Promise promise, Throwable th) {
        LOG.e("Cannot set CallerID preference on ACMS", th);
        promise.reject((String) null, "Cannot set CallerID preference on ACMS");
    }

    private void updateDropInStatusOnServerAndDatabase(String str, boolean z, Promise promise) {
        if (new UpdateCanDropInPreference().executeSwallowException(ContactsProviderUtils.getHomeGroupIdFromCommsIdFromDb(getReactApplicationContext(), str), z)) {
            String serverContactIdFromCommsId = this.mContactsDataStoreUtil.getServerContactIdFromCommsId(str);
            if (serverContactIdFromCommsId != null) {
                ContactsProviderUtils.setContactCanDropInOnUser(serverContactIdFromCommsId, z);
                LOG.i("dropInState updated in Database");
                promise.resolve(null);
                return;
            }
            promise.reject((String) null, "serverContactId null: query execution failure");
            return;
        }
        promise.reject((String) null, "server error");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @ReactMethod
    public void callContact(String str, String str2, String str3, String str4, Promise promise) {
        String str5;
        Person person;
        char c;
        CallHelper callHelper = CommsDaggerWrapper.getComponent().getCallHelper();
        Activity currentActivity = getCurrentActivity();
        InitiationLogicContract create = this.mInitiationLogicFactory.create(this.mCallInitiator, currentActivity, currentActivity, callHelper, MetricKeys.SCREEN_NAME_CONVO_LIST, str4);
        String str6 = "";
        if (!(Constants.GROUP_CALL.equalsIgnoreCase(str2) || Constants.CHAPMAN_CALL.equalsIgnoreCase(str2))) {
            person = this.mContactsDataStoreUtil.getPersonByServerIdFromDatabase(str);
            if (person == null) {
                LOG.e("callContact: Query execution failed!");
                promise.reject((String) null, "Invalid contact id");
                return;
            }
            str6 = !person.getCommsIds().isEmpty() ? person.getCommsIds().get(0) : null;
            str5 = ContactUtils.getFullName(new FullContactName(person.getName(), person.getCompanyName()));
        } else {
            str5 = str6;
            person = null;
        }
        LOG.i("Initiating a call. Call type: " + str2);
        switch (str2.hashCode()) {
            case -1651233675:
                if (str2.equals("DROP_IN")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 2074041:
                if (str2.equals("COBO")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 62628790:
                if (str2.equals("AUDIO")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 81665115:
                if (str2.equals("VIDEO")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 131974372:
                if (str2.equals(Constants.CHAPMAN_CALL)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 520465757:
                if (str2.equals(Constants.GROUP_CALL)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c == 2) {
                    try {
                        makeDropInCall(create, currentActivity, str6, str5, person);
                    } catch (IllegalStateException e) {
                        LOG.e("Unexpected Error occurred initiating a drop-in call", e);
                        promise.reject((String) null, "Unexpected Error during drop-in");
                        return;
                    }
                } else if (c == 3) {
                    ContactPhoneNumber findContactPhoneNumber = findContactPhoneNumber(person, str3);
                    if (findContactPhoneNumber == null) {
                        LOG.e("Starting a PSTN call failed. Phone number is not valid");
                        promise.reject((String) null, "Phone number invalid or not COBO enabled");
                        return;
                    }
                    create.initiateCoboCallFromActivity(str6, str5, findContactPhoneNumber);
                } else if (c == 4) {
                    create.initiateGroupCall(str);
                } else if (c != 5) {
                    GeneratedOutlineSupport.outline3("Initiating a call failed due to incorrect call type. Call type: ", str2, LOG);
                    promise.reject((String) null, "Invalid call type");
                    return;
                } else {
                    create.initiateGroupDropIn(str);
                }
            } else if (!str4.equals(Constants.Telemetry.CallInitScreenNames.MESSAGE_THREAD) && !str4.equals(MetricsConstants.TTCFMetrics.BILOBA_DASHBOARD)) {
                create.initiateVideoCall(str6, str5);
            } else {
                CallBottomSheetDialogFragment newInstance = CallBottomSheetDialogFragment.newInstance(ContactsProviderUtils.fetchContactEntryForCommId(getReactApplicationContext(), str6), str6, new CallBottomSheetType[]{CallBottomSheetType.VIDEO}, this.mCapabilitiesManager.isThemedUIEnabled(), this.mCapabilitiesManager.isMosaicThemingEnabled(), this.mInitiationLogicFactory);
                newInstance.show(getFragmentManagerFromActivity(getCurrentActivity()), newInstance.getTag());
            }
        } else if (!str4.equals(Constants.Telemetry.CallInitScreenNames.MESSAGE_THREAD) && !str4.equals(MetricsConstants.TTCFMetrics.BILOBA_DASHBOARD)) {
            create.initiateAudioCall(str6, str5);
        } else {
            ContactEntry fetchContactEntryForCommId = ContactsProviderUtils.fetchContactEntryForCommId(getReactApplicationContext(), str6);
            CallBottomSheetType[] callBottomSheetTypeArr = {CallBottomSheetType.AUDIO, CallBottomSheetType.PhoneNumber};
            if (str4.equals(MetricsConstants.TTCFMetrics.BILOBA_DASHBOARD)) {
                callBottomSheetTypeArr = new CallBottomSheetType[]{CallBottomSheetType.AUDIO, CallBottomSheetType.VIDEO, CallBottomSheetType.PhoneNumber};
            }
            CallBottomSheetDialogFragment newInstance2 = CallBottomSheetDialogFragment.newInstance(fetchContactEntryForCommId, str6, callBottomSheetTypeArr, this.mCapabilitiesManager.isThemedUIEnabled(), this.mCapabilitiesManager.isMosaicThemingEnabled(), this.mInitiationLogicFactory);
            newInstance2.show(getFragmentManagerFromActivity(getCurrentActivity()), newInstance2.getTag());
        }
        LOG.i("Call started successfully.");
        promise.resolve(null);
    }

    @VisibleForTesting
    boolean decideAndShowDropInPopUp(List<DeviceModel> list) {
        if (DropInUtils.hasAnyDropInEnabledDevice(list)) {
            if (DropInUtils.isDropInEnabledProfile(this.mCommsIdentityManager.getHomeGroupId("ContactsManagerBridge.decideAndShowDropInPopUp", false))) {
                return false;
            }
            DropInUtils.showProfileDialog(getCurrentActivity(), getReactApplicationContext(), this.mCommsIdentityManager.getCommsId("ContactsManagerBridge.decideAndShowDropInPopUp", false), this.mApplicationManager);
            return true;
        }
        DropInUtils.showDeviceDialog(getCurrentActivity(), this.mApplicationManager);
        return true;
    }

    @ReactMethod
    public void dropInIntraHome(@NonNull String str, @NonNull String str2, @NonNull ReadableArray readableArray, @NonNull Promise promise) {
        List<DeviceModel> deviceModelListFromArray = this.mReactBridgeSerializer.getDeviceModelListFromArray(readableArray);
        CallHelper callHelper = CommsDaggerWrapper.getComponent().getCallHelper();
        Activity currentActivity = getCurrentActivity();
        InitiationLogicContract create = this.mInitiationLogicFactory.create(this.mCallInitiator, currentActivity, currentActivity, callHelper, MetricKeys.SCREEN_NAME_CONVO_LIST, str2);
        if (deviceModelListFromArray.size() == 1) {
            DeviceModel deviceModel = deviceModelListFromArray.get(0);
            create.initiateTargetedDropIn(this.mCommsIdentityManager.getCommsId("ContactsManagerBridge.dropInIntraHome", false), null, deviceModel.getDeviceGruu(), deviceModel.getDeviceName(), deviceModel.getDeviceStatus().isVideoEnabled(), false);
        } else {
            DeviceBottomSheet newInstance = DeviceBottomSheet.newInstance(create, this.mCommsIdentityManager.getCommsId("ContactsManagerBridge.dropInIntraHome", false), MetricKeys.SCREEN_NAME_CONVO_LIST, 13, 0, deviceModelListFromArray.size(), this.mContactsDataStoreUtil.getPersonByServerIdFromDatabase(this.mContactsDataStoreUtil.getServerContactIdFromCommsId(!str.isEmpty() ? str : this.mCommsIdentityManager.getCommsId("ContactsManagerBridge.dropInIntraHome", false))).isChild());
            newInstance.setDevices(deviceModelListFromArray);
            newInstance.setTargetFragment(getCommsMasterFragment(getFragmentManagerFromActivity(getCurrentActivity())), 1);
            newInstance.show(getFragmentManagerFromActivity(getCurrentActivity()), newInstance.getTag());
        }
        promise.resolve(null);
    }

    @ReactMethod
    public void getBlockContacts(Promise promise) {
        WritableArray createArray = Arguments.createArray();
        for (Contact contact : getBlockableContacts(fetchContacts())) {
            createArray.pushMap(this.mReactBridgeSerializer.getWritablePersonMapFromPersonObject(new Person(contact)));
        }
        promise.resolve(createArray);
    }

    @VisibleForTesting
    Fragment getCommsMasterFragment(@NonNull FragmentManager fragmentManager) throws IllegalStateException {
        for (Fragment fragment : fragmentManager.getFragments()) {
            if (fragment instanceof CommsMasterFragment) {
                return fragment;
            }
        }
        throw new IllegalStateException("CommsMasterFragment is missing -- cannot attach DeviceBottomSheet to null fragment");
    }

    @VisibleForTesting
    FragmentManager getFragmentManagerFromActivity(@NonNull Activity activity) throws IllegalStateException {
        if (activity instanceof FragmentActivity) {
            return ((FragmentActivity) activity).getSupportFragmentManager();
        }
        throw new IllegalStateException("currentActivity cannot create FragmentManager -- cannot attach DeviceBottomSheet to null fragment");
    }

    @ReactMethod
    public void getHomeGroupsThatCanDropIn(Promise promise) {
        ReadableArray homeGroupsThatCanDropIn = this.mContactsDataStoreUtil.getHomeGroupsThatCanDropIn();
        if (homeGroupsThatCanDropIn != null) {
            promise.resolve(homeGroupsThatCanDropIn);
        } else {
            promise.reject((String) null, "Cannot retrieve home groups from local store");
        }
    }

    @ReactMethod
    public void getMAPDeviceName(Promise promise) {
        promise.resolve(Utils.getDeviceName(getReactApplicationContext()));
    }

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "CommsContactsManager";
    }

    @ReactMethod
    public void getShowCallerIdPreference(final Promise promise) {
        if (!CoboUtils.isCallerIDToggleAvailable()) {
            promise.resolve(false);
        } else {
            CoboUtils.getShowPhoneNumberToCalleeObservable().subscribe(new Action1() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$ContactsManagerBridge$8sL8z3Y-QiXAZbqO0TgLlCcp4wc
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Promise.this.resolve((Boolean) obj);
                }
            }, new Action1() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$ContactsManagerBridge$iVVjLjrHHx_OFG7I2-aXSVJxrAw
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    ContactsManagerBridge.lambda$getShowCallerIdPreference$1(Promise.this, (Throwable) obj);
                }
            });
        }
    }

    @ReactMethod
    public void getTranscriptionImprovementPreference(Promise promise) {
        boolean z;
        try {
            if (VoiceMessageTranscriptionConsent.getACMSUserPref() != null) {
                z = VoiceMessageTranscriptionConsent.getACMSUserPref().booleanValue();
            } else {
                z = !VoiceMessageTranscriptionConsent.pfmRequiresAccess();
            }
            promise.resolve(Boolean.valueOf(z));
        } catch (ServiceException e) {
            promise.reject("Unable to get value for voice message transcription consent", e);
        }
    }

    @VisibleForTesting
    void makeDropInCall(@NonNull InitiationLogicContract initiationLogicContract, @NonNull Activity activity, @NonNull String str, @NonNull String str2, @NonNull Person person) throws IllegalStateException {
        Fragment commsMasterFragment = getCommsMasterFragment(getFragmentManagerFromActivity(activity));
        String homeGroupId = this.mCommsIdentityManager.getHomeGroupId("ContactsManagerBridge.makeDropInCall", false);
        String homeGroupCommsId = person.getHomeGroupCommsId();
        boolean isChild = person.isChild();
        if (homeGroupId.equals(homeGroupCommsId)) {
            CallUtils.doTargetedDropIn(new DeviceListSubscriber(initiationLogicContract, str, commsMasterFragment, MetricKeys.CONTACT_CALL_INITIATED_FROM_CONTACT_RN, 9, isChild));
        } else {
            initiationLogicContract.initiateContactDropIn(str, str2);
        }
    }

    @ReactMethod
    public void nativeCallAction(String str, Promise promise) {
        this.mMakeNativeCallHandler.initiateNativePhoneCall(str, this.mDriveModeCallPermissionHandler, true);
        promise.resolve(null);
    }

    @ReactMethod
    public void notifyContactsEvent(String str, Promise promise) {
        if (!Constants.REACT_NATIVE_CONTACTS_IMPORT_STARTED.equals(str) && !Constants.REACT_NATIVE_CONTACTS_IMPORT_COMPLETED.equals(str) && !Constants.REACT_NATIVE_CONTACTS_IMPORT_ERROR.equals(str)) {
            promise.reject(E_INVALID_EVENT, "Invalid event found: " + str);
            return;
        }
        Utils.writeStringPreferenceToSharedPrefs(getReactApplicationContext(), Constants.REACT_NATIVE_CONTACTS_LAST_SYNC_STATUS, str);
        if (Constants.REACT_NATIVE_CONTACTS_IMPORT_COMPLETED.equals(str)) {
            Utils.writeBooleanPreferenceToSharedPrefs(getReactApplicationContext(), Constants.REACT_NATIVE_CONTACTS_SYNC_COMPLETED, true);
        }
        broadcastReactNativeSyncEvent(getReactApplicationContext(), str);
        promise.resolve(null);
    }

    @ReactMethod
    public void setContactCanDropInOnUser(String str, boolean z, Promise promise) {
        if (isOffline()) {
            promise.reject((String) null, NOT_CONNECTED_TO_INTERNET);
        } else {
            updateDropInStatusOnServerAndDatabase(str, z, promise);
        }
    }

    @ReactMethod
    public void setContactIsBlocked(final boolean z, final String str, final Promise promise) {
        new AsyncTask<Void, Void, Boolean>() { // from class: com.amazon.deecomms.nativemodules.ContactsManagerBridge.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Boolean doInBackground(Void... voidArr) {
                return Boolean.valueOf(ContactsManagerBridge.this.mContactStatusManager.setBlockStatus(str, z));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Boolean bool) {
                if (bool.booleanValue()) {
                    promise.resolve(null);
                } else {
                    promise.reject((String) null, "Setting contact block status failed.");
                }
            }
        }.execute(new Void[0]);
    }

    @ReactMethod
    public void setShowCallerIdPreference(boolean z, final Promise promise) {
        if (isOffline()) {
            promise.reject((String) null, NOT_CONNECTED_TO_INTERNET);
        } else {
            CoboUtils.setShowPhoneNumberToCalleeObservable(z).subscribe(new Action1() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$ContactsManagerBridge$pu-sVSGmKU6_Kgiq4erLuZoZJWg
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    Boolean bool = (Boolean) obj;
                    Promise.this.resolve(null);
                }
            }, new Action1() { // from class: com.amazon.deecomms.nativemodules.-$$Lambda$ContactsManagerBridge$exVW5jhJ7HZhZ6v4s9AyiU3ckMU
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    ContactsManagerBridge.lambda$setShowCallerIdPreference$3(Promise.this, (Throwable) obj);
                }
            });
        }
    }

    @ReactMethod
    public void setTranscriptionImprovementPreference(boolean z, Promise promise) {
        if (isOffline()) {
            promise.reject((String) null, NOT_CONNECTED_TO_INTERNET);
        } else if (VoiceMessageTranscriptionConsent.setACMSUserPref(z) == null) {
            promise.reject((String) null, "Unable to set value for voice message transcription consent");
        } else {
            promise.resolve(null);
        }
    }

    @ReactMethod
    public void showDropInBottomSheet(@NonNull ReadableArray readableArray, @NonNull ReadableArray readableArray2, @NonNull Promise promise) {
        List<DeviceModel> deviceModelListFromArray = this.mReactBridgeSerializer.getDeviceModelListFromArray(readableArray);
        List<PresenceDocument> presenceDocumentsFromArray = this.mReactBridgeSerializer.getPresenceDocumentsFromArray(readableArray2);
        if (decideAndShowDropInPopUp(deviceModelListFromArray)) {
            promise.resolve(null);
            return;
        }
        CallHelper callHelper = CommsDaggerWrapper.getComponent().getCallHelper();
        Activity currentActivity = getCurrentActivity();
        DeviceBottomSheet newInstance = DeviceBottomSheet.newInstance(this.mInitiationLogicFactory.create(this.mCallInitiator, currentActivity, currentActivity, callHelper, MetricKeys.SCREEN_NAME_CONVO_LIST, Constants.Telemetry.CallInitScreenNames.CONVERSATIONS), this.mCommsIdentityManager.getCommsId("ContactsManagerBridge.showDropInBottomSheet", false), MetricKeys.SCREEN_NAME_CONVO_LIST, 13, presenceDocumentsFromArray.size(), deviceModelListFromArray.size(), false);
        newInstance.setPresenceDocuments(presenceDocumentsFromArray);
        newInstance.setDevices(deviceModelListFromArray);
        newInstance.setTargetFragment(getCommsMasterFragment(getFragmentManagerFromActivity(getCurrentActivity())), 1);
        newInstance.show(getFragmentManagerFromActivity(getCurrentActivity()), newInstance.getTag());
        promise.resolve(null);
    }

    @ReactMethod
    public void startSkypeAction(@NonNull String str, @NonNull String str2, @NonNull Promise promise) {
        if (SkypeActionType.isValidAction(str2)) {
            SkypeClient skypeClient = new SkypeClient();
            skypeClient.startSkypeActivity(getReactApplicationContext(), skypeClient.createSkypeUri(str2, str));
            promise.resolve(null);
            return;
        }
        promise.reject((String) null, "Action '" + str2 + "' is an invalid SkypeActionType");
    }

    @ReactMethod
    public void updateContact(String str, String str2, String str3, String str4, String str5, Promise promise) {
        ContactName contactName;
        boolean updateCloudContact;
        boolean z = false;
        if (str != null && str.equals(this.mCommsIdentityManager.getCommsId("ContactsManagerBridge.updateContact", false))) {
            z = true;
        }
        String serverContactIdFromCommsId = this.mContactsDataStoreUtil.getServerContactIdFromCommsId(str);
        if (serverContactIdFromCommsId == null) {
            LOG.e("Contact does not exist in the Database");
            promise.reject("updateContact error", "serverContactId null");
            return;
        }
        Person personByServerIdFromDatabase = this.mContactsDataStoreUtil.getPersonByServerIdFromDatabase(serverContactIdFromCommsId);
        if (personByServerIdFromDatabase == null) {
            LOG.e("Unable to fetch Person from Database");
            promise.reject("updateContact error", "person null");
            return;
        }
        ContactName contactName2 = new ContactName(str2, str3, str4, str5, null, null, null, null);
        if (z) {
            updateCloudContact = this.mACMSRecipeUpdateContactName.updateMyProfileContact(str, contactName2);
            contactName = contactName2;
        } else {
            String ownerCommsId = personByServerIdFromDatabase.getOwnerCommsId();
            contactName = contactName2;
            updateCloudContact = this.mACMSRecipeUpdateContactName.updateCloudContact(contactName, serverContactIdFromCommsId, personByServerIdFromDatabase.getCompanyName(), personByServerIdFromDatabase.getPhoneNumbers(), ownerCommsId);
        }
        if (updateCloudContact) {
            ContactsProviderUtils.updateContactName(serverContactIdFromCommsId, contactName);
            LOG.i("contactName updated in Database");
            Person personByServerIdFromDatabase2 = this.mContactsDataStoreUtil.getPersonByServerIdFromDatabase(serverContactIdFromCommsId);
            if (personByServerIdFromDatabase2 == null) {
                LOG.e("Unable to fetch updatedPerson from Database");
                promise.reject("getPersonByServerIdFromDatabase error", "updatedPerson null");
                return;
            }
            promise.resolve(this.mReactBridgeSerializer.getWritablePersonMapFromPersonObject(personByServerIdFromDatabase2));
            return;
        }
        promise.reject("updateContactName error", "server error");
    }

    public ContactsManagerBridge(@NonNull ReactApplicationContext reactApplicationContext, @NonNull ContactStatusManager contactStatusManager, @NonNull ContactsDataStoreUtil contactsDataStoreUtil, @NonNull UpdateContactName updateContactName, @NonNull CommsIdentityManager commsIdentityManager, @NonNull CallInitiator callInitiator, @NonNull ApplicationManager applicationManager, @NonNull CapabilitiesManager capabilitiesManager, @NonNull MakeNativeCallHandler makeNativeCallHandler, @NonNull DriveModeCallPermissionHandler driveModeCallPermissionHandler, @NonNull CallingFacade callingFacade, @NonNull InitiationLogicFactory initiationLogicFactory) {
        super(reactApplicationContext);
        this.mContactsDataStoreUtil = contactsDataStoreUtil;
        this.mContactStatusManager = contactStatusManager;
        this.mACMSRecipeUpdateContactName = updateContactName;
        this.mCommsIdentityManager = commsIdentityManager;
        this.mCallInitiator = callInitiator;
        this.mReactBridgeSerializer = new ReactBridgeSerializer(reactApplicationContext);
        this.mApplicationManager = applicationManager;
        this.mCapabilitiesManager = capabilitiesManager;
        this.mMakeNativeCallHandler = makeNativeCallHandler;
        this.mDriveModeCallPermissionHandler = driveModeCallPermissionHandler;
        this.mCallingFacade = callingFacade;
        this.mInitiationLogicFactory = initiationLogicFactory;
    }

    ContactsManagerBridge(@NonNull ReactApplicationContext reactApplicationContext, @NonNull ContactStatusManager contactStatusManager, @NonNull ContactsDataStoreUtil contactsDataStoreUtil, @NonNull UpdateContactName updateContactName, @NonNull CommsIdentityManager commsIdentityManager, @NonNull CallInitiator callInitiator, @NonNull ApplicationManager applicationManager, @NonNull CapabilitiesManager capabilitiesManager, @NonNull ReactBridgeSerializer reactBridgeSerializer, @NonNull MakeNativeCallHandler makeNativeCallHandler, @NonNull DriveModeCallPermissionHandler driveModeCallPermissionHandler, @NonNull CallingFacade callingFacade, @NonNull InitiationLogicFactory initiationLogicFactory) {
        super(reactApplicationContext);
        this.mContactsDataStoreUtil = contactsDataStoreUtil;
        this.mContactStatusManager = contactStatusManager;
        this.mACMSRecipeUpdateContactName = updateContactName;
        this.mCommsIdentityManager = commsIdentityManager;
        this.mCallInitiator = callInitiator;
        this.mReactBridgeSerializer = reactBridgeSerializer;
        this.mApplicationManager = applicationManager;
        this.mCapabilitiesManager = capabilitiesManager;
        this.mMakeNativeCallHandler = makeNativeCallHandler;
        this.mDriveModeCallPermissionHandler = driveModeCallPermissionHandler;
        this.mCallingFacade = callingFacade;
        this.mInitiationLogicFactory = initiationLogicFactory;
    }
}
