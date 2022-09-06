package com.amazon.dee.app.ui.clouddrive;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.alexa.device.api.DeviceInformation;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.dee.app.R;
import com.amazon.dee.app.databinding.AlexaDeviceBackgroundImageBinding;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImage;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.photos.PhotoService;
import com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageViewModel;
import com.amazon.dee.app.ui.clouddrive.ViewBoxFragment;
import com.amazon.dee.app.ui.view.LoadingProgressDialog;
import com.amazon.dee.app.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class AlexaDeviceBackgroundImageActivity extends AppCompatActivity implements ViewBoxFragment.OnViewBoxInteractionListener {
    public static final String ACTION_MODE = "action_mode";
    public static final int REMOVE_BACKGROUND = 1;
    public static final int SET_BACKGROUND = 0;
    private static final String TAG = Utils.safeTag(AlexaDeviceBackgroundImageActivity.class.getSimpleName());
    private static final String VIEW_BOX_TAG = "ViewBox";
    private BackgroundImage backgroundImage;
    AlexaDeviceBackgroundImageBinding binding;
    private CropWindowHelper cropWindowHelper;
    @Inject
    Lazy<BackgroundImageService> deviceBackgroundImageService;
    private DeviceInformation deviceInformation;
    @Inject
    Lazy<IdentityService> identityService;
    private FragmentManager mFragmentManager;
    private LoadingProgressDialog mProgress;
    @Inject
    Lazy<MetricsService> metricsService;
    @Inject
    Lazy<PhotoService> photoService;
    AlexaDeviceBackgroundImageViewModel viewModel;
    private Uri mImageUri = null;
    private int actionMode = -1;
    private Boolean didLaunchImageGalleryFromServiceListener = false;
    AlexaDeviceBackgroundImageViewModel.InitializeServiceListener initializeServiceListener = new AlexaDeviceBackgroundImageViewModel.InitializeServiceListener() { // from class: com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageActivity.3
        @Override // com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageViewModel.InitializeServiceListener
        public void onError(Exception exc) {
            Log.e(AlexaDeviceBackgroundImageActivity.TAG, exc.getMessage());
            AlexaDeviceBackgroundImageActivity.this.dismissProgressDialog();
            AlexaDeviceBackgroundImageActivity alexaDeviceBackgroundImageActivity = AlexaDeviceBackgroundImageActivity.this;
            Utils.showToast(alexaDeviceBackgroundImageActivity, alexaDeviceBackgroundImageActivity.getString(R.string.amazon_drive_initialize_error_message));
            AlexaDeviceBackgroundImageActivity.this.onExit();
        }

        @Override // com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageViewModel.InitializeServiceListener
        public void onSucess() {
            AlexaDeviceBackgroundImageActivity.this.dismissProgressDialog();
            if (AlexaDeviceBackgroundImageActivity.this.actionMode == 1) {
                AlexaDeviceBackgroundImageActivity.this.moveStateToRemove();
            } else if (AlexaDeviceBackgroundImageActivity.this.didLaunchImageGalleryFromServiceListener.booleanValue()) {
            } else {
                AlexaDeviceBackgroundImageActivity.this.didLaunchImageGalleryFromServiceListener = true;
                AlexaDeviceBackgroundImageActivity.this.pickFromGallery();
            }
        }
    };
    AlexaDeviceBackgroundImageViewModel.RemoveFileListener removeFileListener = new AlexaDeviceBackgroundImageViewModel.RemoveFileListener() { // from class: com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageActivity.4
        @Override // com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageViewModel.RemoveFileListener
        public void onError(Exception exc) {
            Log.e(AlexaDeviceBackgroundImageActivity.TAG, exc.getMessage());
            AlexaDeviceBackgroundImageActivity.this.dismissProgressDialog();
            AlexaDeviceBackgroundImageActivity alexaDeviceBackgroundImageActivity = AlexaDeviceBackgroundImageActivity.this;
            Utils.showToast(alexaDeviceBackgroundImageActivity, alexaDeviceBackgroundImageActivity.getString(R.string.amazon_drive_remove_photo_error_message));
            AlexaDeviceBackgroundImageActivity.this.onExit();
        }

        @Override // com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageViewModel.RemoveFileListener
        public void onSucess(BackgroundImage backgroundImage) {
            AlexaDeviceBackgroundImageActivity.this.dismissProgressDialog();
            Intent intent = new Intent();
            intent.putExtra(BackgroundImage.BACKGROUND_IMAGE_MODEL, backgroundImage);
            AlexaDeviceBackgroundImageActivity.this.setResult(-1, intent);
            AlexaDeviceBackgroundImageActivity.this.finish();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void dismissProgressDialog() {
        runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageActivity.2
            @Override // java.lang.Runnable
            public void run() {
                if (AlexaDeviceBackgroundImageActivity.this.mProgress != null) {
                    AlexaDeviceBackgroundImageActivity.this.mProgress.dismiss();
                }
                AlexaDeviceBackgroundImageActivity.this.mProgress = null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveStateToRemove() {
        showProgressDialog(getString(R.string.amazon_drive_remove_photo_preogress_message));
        this.viewModel.removeBackgroundImage();
    }

    private void moveStateToSetupCDS() {
        showProgressDialog(getString(R.string.alexa_device_background_image_loading_message));
        this.viewModel.initializePhotoService();
    }

    private void moveStateToShowViewBox(Uri uri) {
        dismissProgressDialog();
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        beginTransaction.add(this.binding.fragmentContainer.getId(), ViewBoxFragment.newInstance(uri, this.backgroundImage, this.cropWindowHelper), VIEW_BOX_TAG);
        beginTransaction.commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pickFromGallery() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.addFlags(536870912);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.alexa_device_background_image_choose_image)), 99);
    }

    private synchronized void showProgressDialog(final String str) {
        runOnUiThread(new Runnable() { // from class: com.amazon.dee.app.ui.clouddrive.AlexaDeviceBackgroundImageActivity.1
            @Override // java.lang.Runnable
            public void run() {
                if (AlexaDeviceBackgroundImageActivity.this.mProgress != null) {
                    AlexaDeviceBackgroundImageActivity.this.dismissProgressDialog();
                }
                AlexaDeviceBackgroundImageActivity alexaDeviceBackgroundImageActivity = AlexaDeviceBackgroundImageActivity.this;
                alexaDeviceBackgroundImageActivity.mProgress = new LoadingProgressDialog(alexaDeviceBackgroundImageActivity);
                AlexaDeviceBackgroundImageActivity.this.mProgress.setMessage(str);
                AlexaDeviceBackgroundImageActivity.this.mProgress.show();
            }
        });
    }

    boolean handleIntent(Intent intent) {
        if (intent.getExtras() == null) {
            return false;
        }
        this.actionMode = intent.getIntExtra(ACTION_MODE, this.actionMode);
        this.backgroundImage = (BackgroundImage) intent.getParcelableExtra(BackgroundImage.BACKGROUND_IMAGE_MODEL);
        return true;
    }

    @VisibleForTesting
    protected void initializeDataBinding() {
        if (this.actionMode == 1) {
            setTheme(2132017775);
        }
        this.binding = (AlexaDeviceBackgroundImageBinding) DataBindingUtil.setContentView(this, R.layout.alexa_device_background_image);
        this.binding.setViewModel(this.viewModel);
        if (this.actionMode == 1) {
            this.binding.toolbar.setVisibility(8);
        }
        this.binding.toolbar.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$AlexaDeviceBackgroundImageActivity$TrF2OZ7mfX_HPLDMbmYErTq3y4E
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlexaDeviceBackgroundImageActivity.this.lambda$initializeDataBinding$0$AlexaDeviceBackgroundImageActivity(view);
            }
        });
    }

    @VisibleForTesting
    protected void injectThis() {
        ((AlexaApplication) getApplication()).getComponent().inject(this);
    }

    public /* synthetic */ void lambda$initializeDataBinding$0$AlexaDeviceBackgroundImageActivity(View view) {
        Fragment findFragmentByTag = this.mFragmentManager.findFragmentByTag(VIEW_BOX_TAG);
        if (findFragmentByTag != null) {
            FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
            beginTransaction.remove(findFragmentByTag);
            beginTransaction.commitAllowingStateLoss();
        }
        pickFromGallery();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        GeneratedOutlineSupport1.outline149("BackgroundImage: onActivityResult resultCode:", i2);
        if (i != 2) {
            if (i != 99) {
                onExit();
            } else if (i2 == -1) {
                this.mImageUri = intent.getData();
                moveStateToShowViewBox(this.mImageUri);
            } else {
                Utils.showToast(this, getString(R.string.alexa_device_background_image_no_image_selected_error));
                onExit();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        setResult(0);
        LoadingProgressDialog loadingProgressDialog = this.mProgress;
        if (loadingProgressDialog != null) {
            loadingProgressDialog.dismiss();
        }
        finish();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        initializeDataBinding();
        Uri uri = this.mImageUri;
        if (uri != null) {
            moveStateToShowViewBox(uri);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        injectThis();
        if (this.deviceInformation == null) {
            this.deviceInformation = (DeviceInformation) GeneratedOutlineSupport1.outline20(DeviceInformation.class);
        }
        if (this.deviceInformation.isPhoneFormFactor()) {
            setRequestedOrientation(1);
        }
        if (handleIntent(getIntent())) {
            this.viewModel = new AlexaDeviceBackgroundImageViewModel(this, this.identityService.mo358get(), this.photoService.mo358get(), this.deviceBackgroundImageService.mo358get(), this.metricsService.mo358get(), this.initializeServiceListener, this.removeFileListener, this.backgroundImage);
            this.viewModel.create(bundle == null ? null : bundle.getBundle("viewModel"));
            this.mFragmentManager = getSupportFragmentManager();
            DeviceScreenConfiguration byType = DeviceScreenConfiguration.getByType(this.backgroundImage.getDeviceType());
            if (byType == DeviceScreenConfiguration.UNKNOWN) {
                String str = TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Photo upload device not found. ID: ");
                outline107.append(this.backgroundImage.getDeviceType());
                Log.e(str, outline107.toString());
            }
            this.cropWindowHelper = new CropWindowHelper(byType, 0.0f, 0.0f);
            if (bundle != null) {
                this.didLaunchImageGalleryFromServiceListener = Boolean.valueOf(bundle.getBoolean("didLaunchImageGalleryFromServiceListener", false));
            }
            initializeDataBinding();
            moveStateToSetupCDS();
            return;
        }
        throw new IllegalStateException("Attempt to launch AlexaDeviceBackgroundImageActivity without necessary extras");
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        LoadingProgressDialog loadingProgressDialog = this.mProgress;
        if (loadingProgressDialog != null) {
            loadingProgressDialog.dismiss();
            this.mProgress = null;
        }
        super.onDestroy();
    }

    @Override // com.amazon.dee.app.ui.clouddrive.ViewBoxFragment.OnViewBoxInteractionListener
    public void onExit() {
        setResult(0);
        finish();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332 && this.actionMode == 0) {
            pickFromGallery();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override // com.amazon.dee.app.ui.clouddrive.ViewBoxFragment.OnViewBoxInteractionListener
    public void onPickImage() {
        pickFromGallery();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        BackgroundImage backgroundImage = this.backgroundImage;
        if (backgroundImage != null) {
            bundle.putParcelable(BackgroundImage.BACKGROUND_IMAGE_MODEL, backgroundImage);
        }
        if (this.mImageUri != null) {
            bundle.putBoolean("restoreState", true);
            bundle.putString("imageUri", this.mImageUri.toString());
        }
        CropWindowHelper cropWindowHelper = this.cropWindowHelper;
        if (cropWindowHelper != null) {
            bundle.putParcelable("cropWindowHelper", cropWindowHelper);
        }
        bundle.putBoolean("didLaunchImageGalleryFromServiceListener", this.didLaunchImageGalleryFromServiceListener.booleanValue());
    }

    @Override // com.amazon.dee.app.ui.clouddrive.ViewBoxFragment.OnViewBoxInteractionListener
    public void onUploadedImage(BackgroundImage backgroundImage) {
        this.backgroundImage = backgroundImage;
        Intent intent = new Intent();
        intent.putExtra(BackgroundImage.BACKGROUND_IMAGE_MODEL, backgroundImage);
        setResult(-1, intent);
        finish();
    }
}
