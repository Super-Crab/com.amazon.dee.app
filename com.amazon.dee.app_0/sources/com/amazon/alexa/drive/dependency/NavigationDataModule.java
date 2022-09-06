package com.amazon.alexa.drive.dependency;

import com.amazon.alexa.drive.navigation.NavigationRepo;
import dagger.Module;
import dagger.Provides;
/* JADX INFO: Access modifiers changed from: package-private */
@Module
/* loaded from: classes7.dex */
public class NavigationDataModule {
    @AppScope
    @Provides
    public NavigationRepo provideNavigationRepo() {
        return new NavigationRepo();
    }
}
