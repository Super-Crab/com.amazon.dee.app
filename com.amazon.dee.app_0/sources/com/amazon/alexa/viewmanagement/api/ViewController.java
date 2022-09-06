package com.amazon.alexa.viewmanagement.api;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.MenuRes;
/* loaded from: classes10.dex */
public interface ViewController {
    public static final int NO_MENU_RESOURCE_ID = -1;
    public static final int SCREEN_ORIENTATION_APP_DEFAULT = -1;

    @MenuRes
    default int getMenuResourceId() {
        return -1;
    }

    default int getOrientation() {
        return -1;
    }

    default String getTitle(Context context) {
        return "";
    }

    View makeView(LayoutInflater layoutInflater, ViewGroup viewGroup);

    @Deprecated
    default void onApplicationBackground(Context context) {
    }

    @Deprecated
    default void onApplicationForeground(Context context) {
    }

    default void onAttach(View view) {
    }

    default boolean onBackPressed() {
        return false;
    }

    default void onConfigurationChanged(Configuration configuration) {
    }

    default void onCreate(Context context) {
    }

    default void onDestroy(Context context) {
    }

    default void onDestroyView(View view) {
    }

    default void onDetach(View view) {
    }

    default boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    default void onPause(Context context) {
    }

    default void onRestoreViewState(View view, Bundle bundle) {
    }

    default void onResume(Context context) {
    }

    default void onSaveViewState(View view, Bundle bundle) {
    }
}
