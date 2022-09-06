package com.amazon.deecomms.sharing;

import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import dagger.MembersInjector;
import javax.inject.Provider;
/* loaded from: classes12.dex */
public final class ShareSheetActivity_MembersInjector implements MembersInjector<ShareSheetActivity> {
    private final Provider<ThemingUpdateUtil> themingUpdateUtilProvider;

    public ShareSheetActivity_MembersInjector(Provider<ThemingUpdateUtil> provider) {
        this.themingUpdateUtilProvider = provider;
    }

    public static MembersInjector<ShareSheetActivity> create(Provider<ThemingUpdateUtil> provider) {
        return new ShareSheetActivity_MembersInjector(provider);
    }

    public static void injectThemingUpdateUtil(ShareSheetActivity shareSheetActivity, ThemingUpdateUtil themingUpdateUtil) {
        shareSheetActivity.themingUpdateUtil = themingUpdateUtil;
    }

    @Override // dagger.MembersInjector
    public void injectMembers(ShareSheetActivity shareSheetActivity) {
        shareSheetActivity.themingUpdateUtil = this.themingUpdateUtilProvider.mo10268get();
    }
}
