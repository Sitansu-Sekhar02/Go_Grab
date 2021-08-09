package com.sa.gograb.profile;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.hbb20.CountryCodePicker;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.sa.gograb.AppController;
import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.image_picker.ImagePickerActivity;
import com.sa.gograb.registration.RegistrationActivity;
import com.sa.gograb.services.ServerResponseInterface;
import com.sa.gograb.services.ServicesMethodsManager;
import com.sa.gograb.services.model.ProfileMainModel;
import com.sa.gograb.services.model.ProfileModel;
import com.sa.gograb.upload.UploadImage;
import com.sa.gograb.upload.UploadListener;
import com.sa.gograb.view.AlertDialog;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfileActivity extends AppCompatActivity implements UploadListener {

    private static final String TAG ="EditProfileActivity" ;

    public static final String BUNDLE_EDIT_MODEL= "BundleEditProfile";

    private static final int PERMISSION_REQUEST_CODE = 200;
    public static final int REQUEST_IMAGE = 100;

    Context context;
    private static Activity activity;
    static Window mainWindow = null;
    View mainView;

    GlobalFunctions globalFunctions;
    GlobalVariables globalVariables;

    static Toolbar toolbar;
    static ActionBar actionBar;
    static String mTitle;
    static int mResourceID;
    static int titleResourseID;
    static TextView toolbar_title;
    static ImageView toolbar_logo, tool_bar_back_icon;
    TextView name_tv, therapist_tv,tv_edit,tv_vehicle;
    EditText etv_full_name, etv_mobile_no, mobile_etv, email_etv, etv_vehicle,etv_gender;
    ImageView iv_edit;
    Button btn_save;
    CircleImageView profile_image;
    private CountryCodePicker country_code_picker;

    List<String> profileImageList;
    List<Uri> uriProfileImageList;
    List<String> downloadProfileImageList;
    String imagePath = "";

    ProfileModel profileModel = null;
    String selected_country_code = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile_activity);

        context = this;
        activity = this;
        mainWindow = getWindow();

        globalFunctions = AppController.getInstance().getGlobalFunctions();
        globalVariables = AppController.getInstance().getGlobalVariables();

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
        tool_bar_back_icon = (ImageView) toolbar.findViewById(R.id.toolbar_icon);
        tool_bar_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mainView = toolbar;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(ContextCompat.getColor(this, R.color.ColorStatusBar));
        }


        // name_tv = ( TextView ) view.findViewById( R.id.name_tv );

        etv_full_name = findViewById( R.id.etv_full_name );
        etv_mobile_no =  findViewById( R.id.etv_mobile_no );
        email_etv =  findViewById( R.id.email_etv );
        tv_vehicle = findViewById( R.id.tv_vehicle );
        etv_vehicle = findViewById( R.id.etv_vehicle );
        etv_gender = findViewById( R.id.etv_gender );
        iv_edit = findViewById( R.id.iv_edit );
        btn_save =findViewById( R.id.btn_save );
        profile_image = ( CircleImageView ) findViewById( R.id.iv_profile_image);


      /*  country_code_picker.setCountryForPhoneCode(+91);
        country_code_picker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                selected_country_code = country_code_picker.getSelectedCountryCodeWithPlus();
                etv_mobile_no.setText("");
            }
        });

        country_code_picker.registerCarrierNumberEditText(mobile_etv);

        country_code_picker.setPhoneNumberValidityChangeListener(new CountryCodePicker.PhoneNumberValidityChangeListener() {
            @Override
            public void onValidityChanged(boolean isValidNumber) {
            }
        });

        selected_country_code = country_code_picker.getSelectedCountryCodeWithPlus();*/

        if (getIntent().hasExtra(BUNDLE_EDIT_MODEL)) {
            profileModel = (ProfileModel) getIntent().getSerializableExtra(BUNDLE_EDIT_MODEL);
        } else {
            profileModel = null;
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateProfile();
                // updateProfile(context, profileModel);
            }
        });

        iv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCropFuctionalImage();

            }
        });
        ProfileModel profileModel=GlobalFunctions.getProfile(activity);
        if (profileModel!=null) {
            setThisPage(profileModel);
        }


        downloadProfileImageList = new ArrayList<>();
        uriProfileImageList = new ArrayList<>();
        profileImageList = new ArrayList<>();

        profileImageList.clear();
        uriProfileImageList.clear();
        downloadProfileImageList.clear();




        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        setTitle(getString(R.string.edit_profile), 0, 0);


    }
    private void setThisPage(ProfileModel profileModel) {

        if (profileModel != null) {
            if (GlobalFunctions.isNotNullValue(profileModel.getProfileImg())) {
                Picasso.with(context).load(profileModel.getProfileImg()).placeholder(R.drawable.image_grp).into(profile_image);

            }
            if (GlobalFunctions.isNotNullValue(profileModel.getFullname())) {
                etv_full_name.setText(profileModel.getFullname());
            }
            if (GlobalFunctions.isNotNullValue(profileModel.getPhone())) {
                String Mobile_no = profileModel.getPhone();
                if (GlobalFunctions.isNotNullValue(profileModel.getCountry_code())) {
                    Mobile_no = profileModel.getCountry_code()+" "+Mobile_no ;
                    etv_mobile_no.setText(Mobile_no);

                } else {
                    etv_mobile_no.setText(Mobile_no);
                }
            }
            if (GlobalFunctions.isNotNullValue(profileModel.getEmail())) {
                email_etv.setText(profileModel.getEmail());
            }
            if (GlobalFunctions.isNotNullValue(profileModel.getVehicle_number())) {
                etv_vehicle.setText(profileModel.getVehicle_number());
            }
            if (GlobalFunctions.isNotNullValue(profileModel.getGender())) {
                etv_gender.setText(profileModel.getGender());
            }
        }
    }

    private void validateProfile() {
        if (etv_full_name != null || email_etv != null  ) {
            String
                    fullName = etv_full_name.getText().toString().trim(),
                    gender = etv_gender.getText().toString().trim(),
                    vehicle_no = etv_vehicle.getText().toString().trim(),
                    email = email_etv.getText().toString().trim();

            if (fullName.isEmpty()) {
                etv_full_name.setError( getString( R.string.pleaseFillMandatoryDetails) );
                etv_full_name.setFocusableInTouchMode( true );
                etv_full_name.requestFocus();
            }  else if (email.isEmpty()) {
                email_etv.setError(getString(R.string.pleaseFillMandatoryDetails));
                email_etv.setFocusableInTouchMode(true);
                email_etv.requestFocus();
            } else if (!globalFunctions.isEmailValid(email)) {
                globalFunctions.displayMessaage(activity, mainView, getString(R.string.emailNotValid));
                email_etv.setFocusableInTouchMode(true);
                email_etv.requestFocus();
            } else {

                if (profileModel == null) {
                    profileModel = new ProfileModel();
                }
                profileModel=GlobalFunctions.getProfile(activity);

                profileModel.setFullname( fullName );
                profileModel.setEmail( email );
                profileModel.setVehicle_number( vehicle_no );
                profileModel.setGender( gender );
                if (profileImageList.size()>0){
                    uploadImage(GlobalVariables.UPLOAD_PROFILE_PHOTO_PATH_CODE);

                }else {
                    updateProfile(context, profileModel);
                }

            }
        }
    }

    private void updateProfile(Context context, ProfileModel profileModel) {

        GlobalFunctions.showProgress(context, getString( R.string.updating_profile ) );
        ServicesMethodsManager servicesMethodsManager = new ServicesMethodsManager();
        servicesMethodsManager.updateUser(context,profileModel, new ServerResponseInterface() {
            @Override
            public void OnSuccessFromServer(Object arg0) {
                GlobalFunctions.hideProgress();
                Log.d( TAG, "Response : " + arg0.toString() );
                outputAfterUpdate( arg0);
            }

            @Override
            public void OnFailureFromServer(String msg) {
                GlobalFunctions.hideProgress();
                GlobalFunctions.displayMessaage(EditProfileActivity.this.context, mainView, msg );
                Log.d( TAG, "Failure : " + msg );
            }

            @Override
            public void OnError(String msg) {
                GlobalFunctions.hideProgress();
                GlobalFunctions.displayMessaage(EditProfileActivity.this.context, mainView, msg );
                Log.d( TAG, "Error : " + msg );
            }
        }, "Get Profile" );
    }

    private void outputAfterUpdate(Object arg0) {
        if (arg0 instanceof ProfileMainModel) {
            ProfileMainModel profileMainModel = (ProfileMainModel) arg0;
            ProfileModel profileModel = profileMainModel.getProfileModel();
            GlobalFunctions.setProfile(activity,profileModel);
            if (profileMainModel.isStatus()) {
                showAlertDialog(profileMainModel);
            }else {
                ///
            }
//            setThisPage(profileModel);

        }
    }

    private void showAlertDialog(ProfileMainModel profileMainModel) {
        final AlertDialog alertDialog = new AlertDialog(context);
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.drawable.app_icon);
        alertDialog.setTitle(getString(R.string.app_name));
        alertDialog.setMessage(activity.getString(R.string.profile_updated_success));
        alertDialog.setPositiveButton(getString(R.string.ok), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                closeThisActivity();

              /*  if (profileMainModel.isStatus()){
                    setThisPage(profileMainModel.getProfileModel());
                }*/
            }
        });

        alertDialog.show();


    }

    private void openCropFuctionalImage() {
     /*   CropImage.activity()
                .setCropShape(CropImageView.CropShape.RECTANGLE)
                .setAspectRatio(2, 2)
                .start(activity);*/

        Dexter.withActivity(this)
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(this, new ImagePickerActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void showSettingsDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(activity);
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), (dialog, which) -> {
            dialog.cancel();
            openSettings();
        });
        builder.setNegativeButton(getString(android.R.string.cancel), (dialog, which) -> dialog.cancel());
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(activity, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(activity, ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = null;
        Uri selectUri = null;
        GlobalFunctions.hideProgress();
        if (resultCode == RESULT_OK && requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            try {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                Uri selectedImageUri = result.getUri();//data.getData();
                String selectedImagePath = globalFunctions.getRealPathFromURI(context, selectedImageUri);
                Log.d(TAG, "Path = " + selectedImagePath);
                File imagePath = new File(selectedImagePath);
                if (imagePath.exists()) {
                    Bitmap bmp = result.getBitmap();
                    if (bmp == null) {
                        BitmapFactory.Options options = new BitmapFactory.Options();
                        options.inJustDecodeBounds = true;
                        BitmapFactory.decodeFile(selectedImagePath, options);
                        final int REQUIRED_SIZE = 200;
                        int scale = 1;
                        while (options.outWidth / scale / 2 >= REQUIRED_SIZE
                                && options.outHeight / scale / 2 >= REQUIRED_SIZE)
                            scale *= 2;
                        options.inSampleSize = scale;
                        options.inJustDecodeBounds = false;
                        bmp = BitmapFactory.decodeFile(selectedImagePath, options);
                        bitmap = bmp;
                    }
                }
                selectUri = selectedImageUri;
                this.imagePath = selectedImagePath;
                setProfileImageToModel(bitmap, selectUri);

            } catch (Exception exccc) {
                globalFunctions.displayMessaage(context, mainView, getString(R.string.something_went_wrong_message));
            }
        }else if (resultCode == RESULT_OK && requestCode == REQUEST_IMAGE) {
            Uri uri = data.getParcelableExtra("path");
            try {
                // You can update this bitmap to your server
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                selectUri = uri;
                setProfileImageToModel(bitmap,selectUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setProfileImageToModel(Bitmap bitmap, Uri selectUri) {
        profileImageList.clear();
        uriProfileImageList.clear();
        profile_image.setImageBitmap(bitmap);
        profileImageList.add("image_one_iv");
        uriProfileImageList.add(selectUri);
    }

    private void uploadImage(String type) {
        if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_PROFILE_PHOTO_PATH_CODE)) {
            if (uriProfileImageList != null) {
                if (uriProfileImageList.size() != 0) {
                    globalFunctions.showProgress(activity, context.getString(R.string.uploading_image));
                    for (int j = 0; j < uriProfileImageList.size(); j++) {
                        Uri uri = (uriProfileImageList.get(j));
                        UploadImage uploadImage = new UploadImage(context);
                        uploadImage.startUploading(uri, type, "image", this);
                    }
                } else {
                    GlobalFunctions.hideProgress();
                    updateProfile(activity,profileModel);
                }
            } else {
                GlobalFunctions.hideProgress();
                updateProfile(activity,profileModel);
            }
        }
    }

    @Override
    public void OnSuccess(String fileName, String type, String uploadingFile) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                globalFunctions.displayMessaage(activity, mainView, "Uploaded");
                // GlobalFunctions.hideProgress();
                if (uploadingFile.equalsIgnoreCase("image")) {
                    setUploadedImageToModel(type, fileName);
                }
            }
        });
    }
    private void setUploadedImageToModel(String type, String imagePath) {
        if (type != null) {
            if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_PROFILE_PHOTO_PATH_CODE)) {
                if (profileModel == null) {
                    profileModel = new ProfileModel();
                }
                profileModel.setProfileImg(imagePath);
                GlobalFunctions.hideProgress();
                updateProfile(activity,profileModel);
            }
        }
    }

    @Override
    public void OnFailure() {
        GlobalFunctions.hideProgress();
        globalFunctions.displayErrorDialog(activity, context.getString(R.string.failed_upload_image));
    }
    @Override
    public void onStop () {
        super.onStop();
    }

    public static void setTitle (String title,int titleImageID, int backgroundResourceID){
        mTitle = title;
        if (backgroundResourceID != 0) {
            mResourceID = backgroundResourceID;
        } else {
            mResourceID = 0;
        }
        if (titleImageID != 0) {
            titleResourseID = titleImageID;
        } else {
            titleResourseID = 0;
        }
        restoreToolbar();
    }

    @SuppressLint("LongLogTag")
    private static void restoreToolbar () {
        //toolbar = (Toolbar) findViewById(R.id.tool_bar);
        Log.d(TAG, "Restore Tool Bar");
        if (actionBar != null) {
            Log.d(TAG, "Restore Action Bar not Null");
            Log.d(TAG, "Title : " + mTitle);
            toolbar_title.setText(mTitle);
            if (mResourceID != 0) toolbar.setBackgroundResource(mResourceID);
            //actionBar.setTitle("");
            // actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public void onBackPressed () {

        closeThisActivity();
        super.onBackPressed();
    }

    public static void closeThisActivity () {
        if (activity != null) {
            activity.finish();
            //activity.overridePendingTransition(R.anim.zoom_in,R.anim.zoom_out);
        }
    }
    @Override
    public void onPause () {
        super.onPause();
        if (getFragmentManager().findFragmentByTag(TAG) != null)
            getFragmentManager().findFragmentByTag(TAG).setRetainInstance(true);
    }

    @Override
    public void onStart () {

       /* if(hint != null) {
            hint.launchAutomaticHintForCall(activity.findViewById(R.id.action_call));
        }*/
//       globalFunctions.launchAutomaticHintForSearch(mainView, getString(R.string.search_title),  getString(R.string.search_description));
        super.onStart();
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    }
}
