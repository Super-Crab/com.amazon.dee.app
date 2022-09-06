package com.amazon.dee.app.ui.clouddrive;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.identity.api.UserIdentity;
import com.amazon.alexa.imageloader.GlideApp;
import com.amazon.alexa.marketplace.api.Marketplace;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.dee.app.R;
import com.amazon.dee.app.databinding.ViewboxFragmentBinding;
import com.amazon.dee.app.framework.AlexaApplication;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImage;
import com.amazon.dee.app.services.alexadevicebackground.BackgroundImageService;
import com.amazon.dee.app.services.alexadevicebackground.DeviceDownloadFailedException;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.photos.PhotoService;
import com.amazon.dee.app.ui.clouddrive.ViewBoxViewModel;
import com.amazon.dee.app.ui.view.LoadingProgressDialog;
import com.amazon.dee.app.util.Utils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.dee.app.metrics.MetricsService;
import dagger.Lazy;
import java.io.IOException;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public class ViewBoxFragment extends Fragment {
    private static final int EGL_CONTEXT_CLIENT_VERSION = 12440;
    private static final String IMAGE_URI = "imageUri";
    private static final String TAG = Log.tag(ViewBoxFragment.class);
    private BackgroundImage backgroundImage;
    ViewboxFragmentBinding binding;
    private CropWindowHelper cropWindowHelper;
    @Inject
    Lazy<BackgroundImageService> deviceBackgroundImageService;
    @Inject
    Lazy<EnvironmentService> environmentService;
    @Inject
    Lazy<IdentityService> identityService;
    private OnViewBoxInteractionListener mListener;
    private LoadingProgressDialog mProgress;
    @Inject
    Lazy<MetricsService> metricsService;
    @Inject
    Lazy<PhotoService> photoService;
    ViewBoxViewModel viewModel;
    private Uri mImageUri = null;
    ViewBoxViewModel.UploadFileListener uploadFileListener = new ViewBoxViewModel.UploadFileListener() { // from class: com.amazon.dee.app.ui.clouddrive.ViewBoxFragment.1
        @Override // com.amazon.dee.app.ui.clouddrive.ViewBoxViewModel.UploadFileListener
        public void onDownloading(BackgroundImage backgroundImage) {
            ViewBoxFragment.this.dismissProgressDialog();
            ViewBoxFragment viewBoxFragment = ViewBoxFragment.this;
            viewBoxFragment.showProgressDialog(viewBoxFragment.getString(R.string.amazon_drive_downloading_on_device_progress_message));
        }

        @Override // com.amazon.dee.app.ui.clouddrive.ViewBoxViewModel.UploadFileListener
        public void onError(Exception exc) {
            ViewBoxFragment.this.dismissProgressDialog();
            String string = ViewBoxFragment.this.getString(R.string.amazon_drive_file_upload_error_message);
            if ((exc instanceof DeviceDownloadFailedException) && ((DeviceDownloadFailedException) exc).getErrorCode() == -1) {
                string = ViewBoxFragment.this.getString(R.string.amazon_drive_downloading_on_device_timeout_message);
            }
            if (ViewBoxFragment.this.isVisible()) {
                Utils.showToast(ViewBoxFragment.this.getActivity(), string);
            }
            if (ViewBoxFragment.this.mListener != null) {
                ViewBoxFragment.this.mListener.onExit();
            }
        }

        @Override // com.amazon.dee.app.ui.clouddrive.ViewBoxViewModel.UploadFileListener
        public void onSuccess(BackgroundImage backgroundImage) {
            ViewBoxFragment.this.dismissProgressDialog();
            if (ViewBoxFragment.this.isVisible()) {
                Utils.showToast(ViewBoxFragment.this.getActivity(), ViewBoxFragment.this.getString(R.string.amazon_drive_upload_complete_message));
            }
            if (ViewBoxFragment.this.mListener != null) {
                ViewBoxFragment.this.mListener.onUploadedImage(backgroundImage);
            }
        }
    };

    /* loaded from: classes12.dex */
    class ImageBoxHelper {
        Integer actualWidth = null;
        Integer actualHeight = null;

        ImageBoxHelper() {
        }

        public ImageBoxHelper getActualSize(@NonNull Uri uri) {
            ImageBoxHelper imageBoxHelper = new ImageBoxHelper();
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeStream(ViewBoxFragment.this.getContext().getContentResolver().openInputStream(uri), null, options);
                imageBoxHelper.actualWidth = Integer.valueOf(options.outWidth);
                imageBoxHelper.actualHeight = Integer.valueOf(options.outHeight);
            } catch (IOException unused) {
                String str = ViewBoxFragment.TAG;
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("invalid image input uri: ");
                outline107.append(uri.getPath());
                Log.e(str, outline107.toString());
            }
            String unused2 = ViewBoxFragment.TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("actual size of image, width: ");
            outline1072.append(imageBoxHelper.actualWidth);
            outline1072.append(" height: ");
            outline1072.append(imageBoxHelper.actualHeight);
            outline1072.toString();
            return imageBoxHelper;
        }

        public float getScaleFactor(Integer num, Integer num2) {
            Integer num3 = 104857600;
            Integer valueOf = Integer.valueOf(num2.intValue() * num.intValue() * 4);
            return (float) Math.sqrt(valueOf.intValue() > num3.intValue() ? num3.intValue() / valueOf.intValue() : 1.0d);
        }
    }

    /* loaded from: classes12.dex */
    public interface OnViewBoxInteractionListener {
        void onExit();

        void onPickImage();

        void onUploadedImage(BackgroundImage backgroundImage);
    }

    private void determineDeviceSpecificSettings() {
        DeviceScreenConfiguration byType = DeviceScreenConfiguration.getByType(this.backgroundImage.getDeviceType());
        if (this.binding.image.getWidth() > 0 && this.binding.image.getHeight() > 0) {
            this.cropWindowHelper.setImageViewSize(this.binding.image.getWidth(), this.binding.image.getHeight());
        }
        this.cropWindowHelper.initializeCropView(this.binding.overlay.getCropWindowRect());
        this.binding.overlay.setCropRect(this.cropWindowHelper.getCropRect());
        this.binding.overlay.setCropGeometry(this.cropWindowHelper.getCropGeometry());
        this.binding.description.setText(byType.description);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissProgressDialog() {
        if (this.mProgress == null || getActivity() == null) {
            return;
        }
        this.mProgress.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeImageView(Drawable drawable) {
        determineDeviceSpecificSettings();
        RectF cropWindowRect = this.binding.overlay.getCropWindowRect();
        float minimumScaleToFit = this.binding.image.setMinimumScaleToFit(drawable, cropWindowRect.width(), cropWindowRect.height());
        this.binding.image.setMaximumScale(3.0f * minimumScaleToFit);
        this.binding.image.setMediumScale(1.5f * minimumScaleToFit);
        this.binding.image.setScale(minimumScaleToFit);
        this.binding.image.setCropWindowRect(cropWindowRect);
        this.binding.image.setImageDrawable(drawable);
    }

    public static ViewBoxFragment newInstance(Uri uri, BackgroundImage backgroundImage, @NonNull CropWindowHelper cropWindowHelper) {
        ViewBoxFragment viewBoxFragment = new ViewBoxFragment();
        Bundle bundle = new Bundle();
        if (uri != null) {
            bundle.putParcelable(IMAGE_URI, uri);
        }
        bundle.putParcelable(BackgroundImage.BACKGROUND_IMAGE_MODEL, backgroundImage);
        bundle.putParcelable("cropWindowHelper", cropWindowHelper);
        viewBoxFragment.setArguments(bundle);
        return viewBoxFragment;
    }

    private void saveUploadCroppedImage() {
        showProgressDialog(getString(R.string.amazon_drive_uploading_to_clouddrive_toast_message));
        this.viewModel.saveUploadCroppedImage(this.binding.image.getCroppedImage(this.backgroundImage.getDeviceType()));
    }

    private boolean shouldUseAlexaPhotoService(UserIdentity userIdentity) {
        if (userIdentity == null || userIdentity.getMarketplace() == null) {
            return false;
        }
        Marketplace marketplace = userIdentity.getMarketplace();
        return Marketplace.INDIA.equals(marketplace) || Marketplace.MEXICO_PRODUCTION.equals(marketplace) || Marketplace.MEXICO_DEVELOPMENT.equals(marketplace);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showProgressDialog(String str) {
        this.mProgress.setMessage(str);
        this.mProgress.show();
    }

    private Spanned spannableFromHtml(String str) {
        return Html.fromHtml(str, 0);
    }

    public /* synthetic */ void lambda$onCreateView$0$ViewBoxFragment(View view) {
        saveUploadCroppedImage();
    }

    public /* synthetic */ Rect lambda$onCreateView$1$ViewBoxFragment() {
        return this.binding.overlay.getImageBounds();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        this.binding.image.setImageDrawable(null);
        if (this.mImageUri != null) {
            this.mProgress = new LoadingProgressDialog(getActivity());
            ImageBoxHelper imageBoxHelper = new ImageBoxHelper();
            ImageBoxHelper actualSize = imageBoxHelper.getActualSize(this.mImageUri);
            Integer num = actualSize.actualWidth;
            Integer num2 = actualSize.actualHeight;
            Size size = new Size(num.intValue(), num2.intValue());
            float scaleFactor = imageBoxHelper.getScaleFactor(num, num2);
            Size size2 = new Size((int) (num.intValue() * scaleFactor), (int) (num2.intValue() * scaleFactor));
            if (scaleFactor < 1.0d) {
                Object[] objArr = {Float.valueOf(scaleFactor), Integer.valueOf(size.getWidth()), Integer.valueOf(size.getHeight()), Integer.valueOf(size2.getWidth()), Integer.valueOf(size2.getHeight())};
            }
            showProgressDialog(getString(R.string.alexa_device_background_image_loading_message));
            GlideApp.with(getContext()).mo6758load(this.mImageUri).mo1619listener(new RequestListener<Drawable>() { // from class: com.amazon.dee.app.ui.clouddrive.ViewBoxFragment.2
                @Override // com.bumptech.glide.request.RequestListener
                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    String str = ViewBoxFragment.TAG;
                    Object[] objArr2 = new Object[1];
                    objArr2[0] = glideException == null ? "null" : glideException.getMessage();
                    Log.e(str, String.format("Image load failed. Error message: %s", objArr2));
                    ViewBoxFragment.this.dismissProgressDialog();
                    Utils.showToast(ViewBoxFragment.this.getActivity(), ViewBoxFragment.this.getString(R.string.alexa_device_background_image_load_selected_image_error));
                    ViewBoxFragment.this.mListener.onExit();
                    return false;
                }

                @Override // com.bumptech.glide.request.RequestListener
                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    ViewBoxFragment.this.dismissProgressDialog();
                    ViewBoxFragment.this.initializeImageView(drawable);
                    return false;
                }
            }).mo1615apply(new RequestOptions().mo1588fitCenter().mo1600override(size2.getWidth(), size2.getHeight())).into(this.binding.image);
            return;
        }
        OnViewBoxInteractionListener onViewBoxInteractionListener = this.mListener;
        if (onViewBoxInteractionListener == null) {
            return;
        }
        onViewBoxInteractionListener.onPickImage();
    }

    @Override // androidx.fragment.app.Fragment
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof OnViewBoxInteractionListener) {
            this.mListener = (OnViewBoxInteractionListener) activity;
            return;
        }
        throw new RuntimeException(activity.toString() + " must implement OnViewBoxInteractionListener");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ((AlexaApplication) getActivity().getApplication()).getComponent().inject(this);
        setRetainInstance(true);
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(IMAGE_URI)) {
            this.mImageUri = (Uri) arguments.getParcelable(IMAGE_URI);
        }
        if (arguments.containsKey(BackgroundImage.BACKGROUND_IMAGE_MODEL)) {
            this.backgroundImage = (BackgroundImage) arguments.getParcelable(BackgroundImage.BACKGROUND_IMAGE_MODEL);
        }
        if (arguments.containsKey("cropWindowHelper")) {
            this.cropWindowHelper = (CropWindowHelper) arguments.getParcelable("cropWindowHelper");
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Spanned spannableFromHtml;
        Spanned spannableFromHtml2;
        this.viewModel = new ViewBoxViewModel(getActivity(), this.identityService.mo358get(), this.photoService.mo358get(), this.environmentService.mo358get(), this.deviceBackgroundImageService.mo358get(), this.metricsService.mo358get(), this.mImageUri, this.uploadFileListener, this.backgroundImage);
        this.viewModel.create(bundle == null ? null : bundle.getBundle("viewModel"));
        this.binding = (ViewboxFragmentBinding) DataBindingUtil.inflate(layoutInflater, R.layout.viewbox_fragment, viewGroup, false);
        this.binding.setViewModel(this.viewModel);
        this.binding.btnDone.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$ViewBoxFragment$iN65k3qLA-XVHC3T6KYJRjQG5sA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewBoxFragment.this.lambda$onCreateView$0$ViewBoxFragment(view);
            }
        });
        this.binding.image.setImageBoundsListener(new IGetImageBounds() { // from class: com.amazon.dee.app.ui.clouddrive.-$$Lambda$ViewBoxFragment$igWUm-tqAeH3KN8nnHaSqpZAHbA
            @Override // com.amazon.dee.app.ui.clouddrive.IGetImageBounds
            public final Rect getImageBounds() {
                return ViewBoxFragment.this.lambda$onCreateView$1$ViewBoxFragment();
            }
        });
        if (shouldUseAlexaPhotoService(this.identityService.mo358get().getUser(TAG))) {
            spannableFromHtml = spannableFromHtml(getString(R.string.alexa_device_background_image_terms_of_use_photos_service));
            spannableFromHtml2 = new SpannableString("");
        } else {
            spannableFromHtml = spannableFromHtml(getString(R.string.alexa_device_background_image_terms_of_use));
            spannableFromHtml2 = spannableFromHtml(getString(R.string.alexa_device_background_image_prime_photos));
        }
        this.binding.primePhotos.setText(spannableFromHtml2);
        this.binding.tac.setText(spannableFromHtml);
        return this.binding.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDetach() {
        super.onDetach();
        this.mProgress = null;
        this.mListener = null;
    }
}
