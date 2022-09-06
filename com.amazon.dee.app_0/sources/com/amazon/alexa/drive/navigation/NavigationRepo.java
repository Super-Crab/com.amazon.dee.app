package com.amazon.alexa.drive.navigation;

import android.util.Log;
import com.amazon.alexa.drive.navigation.SavedLocations;
import com.amazon.alexa.eventbus.api.EventBus;
import com.amazon.alexa.eventbus.api.Message;
import com.amazon.alexa.eventbus.api.MessageHandler;
import com.amazon.alexa.eventbus.message.EventTypeMessageFilter;
import com.amazon.alexa.identity.api.IdentityEvent;
import com.amazon.alexa.identity.api.IdentityService;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes7.dex */
public class NavigationRepo {
    private static final String TAG = "NavigationRepo";
    private SavedLocations.Item mHome;
    private List<SavedLocations.Item> mSavedLocations;
    private SavedLocations.Item mWork;

    public NavigationRepo() {
        setupUserLogoutListener();
    }

    private void setupUserLogoutListener() {
        final IdentityService identityService = (IdentityService) GeneratedOutlineSupport1.outline21(IdentityService.class);
        ((EventBus) GeneratedOutlineSupport1.outline21(EventBus.class)).getSubscriber().subscribeFilter(new EventTypeMessageFilter(IdentityEvent.IDENTITY_CHANGED), new MessageHandler() { // from class: com.amazon.alexa.drive.navigation.-$$Lambda$NavigationRepo$YGoH65Lz9OBdZhD14m67H4m0zHI
            @Override // com.amazon.alexa.eventbus.api.MessageHandler
            public final void handle(Message message) {
                NavigationRepo.this.lambda$setupUserLogoutListener$0$NavigationRepo(identityService, message);
            }
        });
    }

    public SavedLocations.Item getHomeAddress() {
        return this.mHome;
    }

    public List<SavedLocations.Item> getSavedLocations() {
        return this.mSavedLocations;
    }

    public SavedLocations.Item getWorkAddress() {
        return this.mWork;
    }

    public /* synthetic */ void lambda$setupUserLogoutListener$0$NavigationRepo(IdentityService identityService, Message message) {
        if (identityService.getUser(NavigationRepo.class.getSimpleName()) == null) {
            Log.i(TAG, "onUserChanged | user logged out so clearing navigation repo");
            resetNavRepo();
        }
    }

    public void resetNavRepo() {
        this.mHome = null;
        this.mWork = null;
        this.mSavedLocations = new ArrayList();
    }

    public void setHomeAddress(SavedLocations.Item item) {
        this.mHome = item;
    }

    public void setSavedLocations(List<SavedLocations.Item> list) {
        this.mSavedLocations = list;
    }

    public void setWorkAddress(SavedLocations.Item item) {
        this.mWork = item;
    }
}
