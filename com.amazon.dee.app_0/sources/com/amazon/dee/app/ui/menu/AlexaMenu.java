package com.amazon.dee.app.ui.menu;

import android.content.Context;
import android.os.Handler;
import androidx.annotation.NonNull;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.util.ResourceUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import rx.Observable;
import rx.subjects.PublishSubject;
/* loaded from: classes12.dex */
public class AlexaMenu {
    private static final String TAG = Log.tag(AlexaMenu.class);
    private static final Set<String> supportedBadgeIcons = new HashSet(Collections.singleton("ic_badge_notification_unread_light"));
    private final List<MenuItem> channelsMenuItems;
    private final Handler mainHandler;
    private final List<MenuItem> menuItems;
    private final Runnable updatedBadgeIconRunnable;
    private final PublishSubject<Void> didUpdateBadgeIcon = PublishSubject.create();
    private Map<MenuIdentifier, MenuItem> menuMap = new HashMap();

    public AlexaMenu(@NonNull Context context) {
        this.menuItems = new ArrayList(Arrays.asList(new MenuItem(context, MenuIdentifier.ENTERTAINMENT, R.string.main_menu_music_books, false, true), new MenuItem(context, MenuIdentifier.LISTS, R.string.main_menu_lists, false, true), new MenuItem(context, MenuIdentifier.TIMERS_AND_ALARMS, R.string.main_menu_timers_alarms, false, true), new MenuItem(context, MenuIdentifier.CONTACTS, R.string.main_menu_contacts, true, false), new MenuItem(context, MenuIdentifier.DEVICES, R.string.main_menu_elements_device_settings, false, false), new MenuItem(context, MenuIdentifier.ROUTINES, R.string.main_menu_routines, false, false), new MenuItem(context, MenuIdentifier.ACTIVITY, R.string.main_menu_activity, false, false), new MenuItem(context, MenuIdentifier.SMART_HOME, R.string.main_menu_smart_home, true, true), new MenuItem(context, MenuIdentifier.SKILLS, R.string.main_menu_skills, false, true), new MenuItem(context, MenuIdentifier.SETTINGS, R.string.main_menu_settings, false, true), new MenuItem(context, MenuIdentifier.THINGS_TO_TRY, R.string.main_menu_things_to_try, false, shouldShowTTT().booleanValue()), new MenuItem(context, MenuIdentifier.BLUEPRINTS, R.string.main_menu_blueprints, true, false), new MenuItem(context, MenuIdentifier.HELP_AND_FEEDBACK, R.string.header_title_help, false, true)));
        this.channelsMenuItems = new ArrayList(Arrays.asList(new MenuItem(context, MenuIdentifier.ADDDEVICE_CHANNELS, R.string.header_title_add_device, false, false), new MenuItem(context, MenuIdentifier.ENTERTAINMENT_CHANNELS, R.string.main_menu_music_books, false, true), new MenuItem(context, MenuIdentifier.LISTS_CHANNELS, R.string.main_menu_lists, false, true), new MenuItem(context, MenuIdentifier.LISTS_NOTES, R.string.main_menu_lists_notes, false, false), new MenuItem(context, MenuIdentifier.TIMERS_AND_ALARMS_CHANNELS, R.string.main_menu_timers_alarms, false, true), new MenuItem(context, MenuIdentifier.CONTACTS_CHANNELS, R.string.main_menu_contacts, false, false), new MenuItem(context, MenuIdentifier.DEVICES_CHANNELS, R.string.main_menu_elements_device_settings, false, false), new MenuItem(context, MenuIdentifier.ROUTINES_CHANNELS, R.string.main_menu_routines, true, true), new MenuItem(context, MenuIdentifier.SMART_HOME_CHANNELS, R.string.main_menu_smart_home, false, true), new MenuItem(context, MenuIdentifier.THINGS_TO_TRY_CHANNELS, R.string.main_menu_things_to_try, false, shouldShowTTT().booleanValue()), new MenuItem(context, MenuIdentifier.SKILLS_CHANNELS, R.string.main_menu_skills, true, true), new MenuItem(context, MenuIdentifier.BLUEPRINTS_CHANNELS, R.string.main_menu_blueprints, true, false), new MenuItem(context, MenuIdentifier.ACTIVITY_CHANNELS, R.string.main_menu_activity, false, true), new MenuItem(context, MenuIdentifier.HELP_AND_FEEDBACK_CHANNELS, R.string.header_title_help, false, true), new MenuItem(context, MenuIdentifier.SETTINGS_CHANNELS, R.string.main_menu_settings, false, true)));
        for (MenuItem menuItem : this.menuItems) {
            this.menuMap.put(menuItem.getIdentifier(), menuItem);
        }
        for (MenuItem menuItem2 : this.channelsMenuItems) {
            this.menuMap.put(menuItem2.getIdentifier(), menuItem2);
        }
        this.mainHandler = new Handler(context.getMainLooper());
        this.updatedBadgeIconRunnable = new Runnable() { // from class: com.amazon.dee.app.ui.menu.-$$Lambda$AlexaMenu$VsYUdRWQx227EBJ0GlRz6chx42c
            @Override // java.lang.Runnable
            public final void run() {
                AlexaMenu.this.lambda$new$0$AlexaMenu();
            }
        };
    }

    private Boolean shouldShowTTT() {
        return Boolean.valueOf(!Locale.getDefault().getLanguage().equals(new Locale("ar").getLanguage()));
    }

    @NonNull
    public List<MenuItem> getChannelsMenuItems() {
        return this.channelsMenuItems;
    }

    @NonNull
    public List<MenuItem> getItems() {
        return this.menuItems;
    }

    public /* synthetic */ void lambda$new$0$AlexaMenu() {
        this.didUpdateBadgeIcon.onNext(null);
    }

    @NonNull
    public Observable<Void> onBadgeIconUpdated() {
        return this.didUpdateBadgeIcon;
    }

    @NonNull
    public MenuOperationStatus removeBadgeFor(@NonNull String str) {
        MenuItem menuItem = this.menuMap.get(MenuIdentifier.named(str));
        if (menuItem == null) {
            Log.e(TAG, "Could not remove badge icon for unknown menu item: %s", str);
            return MenuOperationStatus.MENU_ITEM_NOT_FOUND;
        }
        menuItem.removeBadgeId();
        this.mainHandler.post(this.updatedBadgeIconRunnable);
        return MenuOperationStatus.SUCCESS;
    }

    @NonNull
    public MenuOperationStatus setBadgeFor(@NonNull String str, @NonNull String str2) {
        int resourceId;
        MenuItem menuItem = this.menuMap.get(MenuIdentifier.named(str));
        if (menuItem == null) {
            Log.e(TAG, "Could not set badge icon for unknown menu item: %s", str);
            return MenuOperationStatus.MENU_ITEM_NOT_FOUND;
        } else if (supportedBadgeIcons.contains(str2) && (resourceId = ResourceUtils.getResourceId(str2, R.drawable.class)) > 0) {
            menuItem.setBadgeId(resourceId);
            this.mainHandler.post(this.updatedBadgeIconRunnable);
            return MenuOperationStatus.SUCCESS;
        } else {
            Log.e(TAG, "Could not set badge icon for unknown badge icon: %s", str2);
            return MenuOperationStatus.BADGE_ICON_NOT_FOUND;
        }
    }

    public void setDividerBelow(@NonNull MenuIdentifier menuIdentifier, boolean z) {
        MenuItem menuItem = this.menuMap.get(menuIdentifier);
        if (menuItem == null) {
            Log.e(TAG, "Could not set divider below %s because it is not present in the menu", menuIdentifier);
        } else {
            menuItem.setDivider(z);
        }
    }

    public void setTextFor(@NonNull MenuIdentifier menuIdentifier, int i) {
        MenuItem menuItem = this.menuMap.get(menuIdentifier);
        if (menuItem == null) {
            Log.e(TAG, "Could not set set text for %s because it is not present in the menu", menuIdentifier);
        } else {
            menuItem.setText(i);
        }
    }

    public void setVisibilityFor(@NonNull MenuIdentifier menuIdentifier, boolean z) {
        MenuItem menuItem = this.menuMap.get(menuIdentifier);
        if (menuItem == null) {
            Log.e(TAG, "Could not set visibility for %s because it is not present in the menu", menuIdentifier);
        } else {
            menuItem.setVisible(z);
        }
    }
}
