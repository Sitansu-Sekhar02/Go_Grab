package com.sauthi.gograb.global;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.snackbar.Snackbar;
import com.sauthi.gograb.AppController;
import com.sauthi.gograb.MainActivity;
import com.sauthi.gograb.R;
import com.sauthi.gograb.SplashActivity;
import com.sauthi.gograb.addon.PasswordValidator;
import com.sauthi.gograb.login.LoginActivity;
import com.sauthi.gograb.login.OtpActivity;
import com.sauthi.gograb.map.MapActivity;
import com.sauthi.gograb.services.model.AddressModel;
import com.sauthi.gograb.services.model.CountryModel;
import com.sauthi.gograb.services.model.FilterDistanceModel;
import com.sauthi.gograb.services.model.HomeTopCategoryModel;
import com.sauthi.gograb.services.model.KeyValueListModel;
import com.sauthi.gograb.services.model.NotificationSettingsModel;
import com.sauthi.gograb.services.model.OfferModel;
import com.sauthi.gograb.services.model.ProfileModel;
import com.sauthi.gograb.view.AlertDialog;
import com.sauthi.gograb.view.ProgressDialog;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

import static android.os.FileUtils.copy;

/*import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;*/

public class GlobalFunctions {

    public static String TAG = "GlobalFunctions";

    GlobalVariables globalVariables = AppController.getInstance().getGlobalVariables();

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static ProgressDialog progressDialog = null;

    private static GlobalFunctions instance;

    private static final String uniqueID = UUID.randomUUID().toString();

    private static int navigationSelectedPosition = -1;
    private static Context context = null;
    public static String DISTANCE="";

    public GlobalFunctions(Context globalContext) {
        context = globalContext;
    }

    public static GlobalFunctions getInstance(Context globalContext){
        if(instance == null){instance = new GlobalFunctions(globalContext);}
        return instance;
    }

    public static String getDatefromTimestamp(long timestamp, String format, Locale locale){
        Log.d(TAG, "Date:"  + new Date(timestamp*1000).getDate());
        String date = new SimpleDateFormat(format, locale).format(new Date(timestamp*1000));
        //Log.d(TAG,"CurrentDate : "+date);
        return date;
    }

    public static String getDateFormat(String date1) {
        String date = null;
        try {
            SimpleDateFormat spf = new SimpleDateFormat(GlobalVariables.DATE_TIME_SERVER_FORMAT);
            Date newDate = spf.parse(date1);
            spf = new SimpleDateFormat("dd MMM yyyy");
            date = spf.format(newDate);
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String getDateFormatTillDate(String date1) {
        String date = null;
        try {
            SimpleDateFormat spf = new SimpleDateFormat(GlobalVariables.DATE_MONTH_SERVER_FORMAT);
            Date newDate = spf.parse(date1);
            spf = new SimpleDateFormat("dd MMM yyyy");
            date = spf.format(newDate);
            System.out.println(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    public static int getNavigationSelectedPosition(){
        return navigationSelectedPosition;
    }

    public static void setNavigationSelectedPosition(int position){
        navigationSelectedPosition = position;
        //NavigationDrawerFragment.refreshNavigationMainList();
    }

    public static String getTimefromTimestamp(long timestamp, String format, Locale locale){
        String time = new SimpleDateFormat(format, locale).format(new Date(timestamp*1000));
        // Log.d(TAG,"CurrentTime : "+time);
        return time;
    }

    public static int getCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        return year;
    }

    public static long getCurrentTimestamp(Context context){
        Long tsLong = System.currentTimeMillis()/1000;
        long diff = getSharedPreferenceLong(context, GlobalVariables.SHARED_PREFERENCE_TIME_DIFFERENCE);
        tsLong = tsLong-diff;
        Log.d(TAG,"CurrentTimestamp : "+tsLong);
        Log.d(TAG,"CurrentTimestamp elapsed: "+ SystemClock.elapsedRealtime());
        return tsLong;
    }

    public static String getTimeDifference(long newTime, long oldTime){
        String Function_TAG = "getTimeDifference";

        String differenceTime = null;
        long mills = (newTime/1000) - oldTime;
        int Mins = (int) (mills / 60);
        int Hours = (int) (Mins/60);
        int Days = (int) (Hours/24);

        Log.d(Function_TAG, newTime+"--"+oldTime);
        Log.d(Function_TAG, Mins+"--"+Hours+"--"+Days);

        if(Days>0){
            String str = Days+""; str += Days>1?" Days":" Day";
            differenceTime = differenceTime==null? str : differenceTime+str;
        }else {
            if (Hours > 0) {
                String str = Hours+""; str += Hours>1?" Hours":" Hour";
                differenceTime = differenceTime == null ? str : differenceTime + str;
            }
            else if (Mins > 0) {
                    String str = Mins +""; str += Mins>1?" Minutes":" Minute";
                    differenceTime = differenceTime == null ? str : differenceTime + str;
                }
        }

        return differenceTime;
    }

    public static void sendMail(Activity activity, String mailId, String subject, String description) {
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{mailId});
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, description);
        activity.startActivity(Intent.createChooser(email, "Choose one"));
    }

    public static String getTimeFromDate(String time) {
        String formattedtime = null;
        try {
            SimpleDateFormat format = new SimpleDateFormat(GlobalVariables.DATE_FORMAT_TIME_NEW);
            SimpleDateFormat df2 = new SimpleDateFormat("hh:mm a");
            formattedtime = df2.format(format.parse(time));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return formattedtime;
    }

    public static void saveProfilePicture(Context context, String folder, String fileName, Bitmap bmp){
        File file = getProfilePicturePath(context, folder, fileName);
        if(file.exists()){
            file.delete();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, out); // bmp is your Bitmap instance
            // PNG is a lossless format, the compression factor (100) is ignored
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean isGPSEnabled (Context mContext){
        LocationManager locationManager = (LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
            return true;
        }else{return false;}
    }

    public static String getLocationfromLatLng(Activity activity, double lat, double lng){
        Geocoder geocoder;
        String fullAddress = null;
        List<Address> addresses;
        geocoder = new Geocoder(activity, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            fullAddress = state + ", "+ city + ", " +address;
            //String country = addresses.get(0).getCountryName();
            //String postalCode = addresses.get(0).getPostalCode();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return fullAddress;

    }

    public static Address getAddressfromLatLng(Activity activity, double lat, double lng){
        Geocoder geocoder;
        Address address = null;
        List<Address> addresses;
        geocoder = new Geocoder(activity, Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(lat, lng, 1);
            if (addresses==null){addresses=new ArrayList<>();}
            if (addresses.size()>0) {
                address = addresses.get(0);
            }else {
                address=null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return address;

    }
    public static AddressModel getAddressModelFromAdderess(Address address){

        AddressModel addressModel = null;
        if (address != null ) {
            addressModel = new AddressModel();
            String addressIndex0 = (address.getAddressLine(0) != null) ? address
                    .getAddressLine(0) : null;
            String addressIndex1 = (address.getAddressLine(1) != null) ? address
                    .getAddressLine(1) : null;
            String addressIndex2 = (address.getAddressLine(2) != null) ? address
                    .getAddressLine(2) : null;
            String addressIndex3 = (address.getAddressLine(3) != null) ? address
                    .getAddressLine(3) : null;

            String completeAddress = addressIndex0;

            addressModel.setLongitude(address.getLongitude());
            addressModel.setLatitude(address.getLatitude());
            addressModel.setStreet(address.getSubAdminArea());
            addressModel.setCity(address.getLocality() != null ? address.getLocality() : address.getSubAdminArea());
            addressModel.setState(address.getAdminArea());
            addressModel.setCountry(address.getCountryName());
            addressModel.setPincode(address.getPostalCode());
            if (address.getFeatureName() != null) addressModel.setName(address.getFeatureName());
            if (address.getPhone() != null) addressModel.setPhone(address.getPhone());
            addressModel.setAddress(completeAddress);
        }
        return addressModel;
    }
    public static String getAddressTextFromModelExcludingNameAndAddresswithComma(Context context, AddressModel addressModel) {
        String returnAddress = null;

        if (addressModel.getAddress() != null && !addressModel.getAddress().equalsIgnoreCase("null") && !addressModel.getAddress().equals("")) {
            returnAddress = returnAddress == null ? addressModel.getAddress() : returnAddress + ", " + addressModel.getAddress();
        }
        if (addressModel.getCity() != null && !addressModel.getCity().equalsIgnoreCase("null") && !addressModel.getCity().equals("")) {
            returnAddress = returnAddress == null ? addressModel.getCity() : returnAddress + ", " + addressModel.getCity();
            if (addressModel.getPincode() != null && !addressModel.getPincode().equalsIgnoreCase("null") && !addressModel.getPincode().equals("")) {
                returnAddress += "-" + addressModel.getPincode();
            }
        } else if (addressModel.getPincode() != null && !addressModel.getPincode().equalsIgnoreCase("null") && !addressModel.getPincode().equals("")) {
            returnAddress = returnAddress == null ? addressModel.getPincode() : returnAddress + ", " + addressModel.getPincode();
        }
        return returnAddress;
    }

    public static AddressModel getAddressModelFromAddress(Address address) {

        AddressModel addressModel = null;
        if (address != null) {
            addressModel = new AddressModel();
            String addressIndex0 = (address.getAddressLine(0) != null) ? address
                    .getAddressLine(0) : null;
            String addressIndex1 = (address.getAddressLine(1) != null) ? address
                    .getAddressLine(1) : null;
            String addressIndex2 = (address.getAddressLine(2) != null) ? address
                    .getAddressLine(2) : null;
            String addressIndex3 = (address.getAddressLine(3) != null) ? address
                    .getAddressLine(3) : null;

            String completeAddress = addressIndex0;

            addressModel.setLongitude(address.getLongitude());
            addressModel.setLatitude(address.getLatitude());
            addressModel.setStreet(address.getSubAdminArea());
            addressModel.setCity(address.getLocality() != null ? address.getLocality() : address.getSubAdminArea());
            addressModel.setState(address.getAdminArea());
            addressModel.setCountry(address.getCountryName());
            addressModel.setPincode(address.getPostalCode());
            if (address.getFeatureName() != null) addressModel.setName(address.getFeatureName());
            if (address.getPhone() != null) addressModel.setPhone(address.getPhone());
            addressModel.setAddress(completeAddress);
        }
        return addressModel;
    }

    public static void setMargin(){
        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
        );
        /*params.setMargins(left, top, right, bottom);
        yourbutton.setLayoutParams(params);*/
    }
    /*public static KeyValueListModel getBannerDetails(Context context){
        String profileString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_BANNER);
        if(profileString!=null){
            KeyValueListModel model = new KeyValueListModel();
            model.toObject(profileString);
            return model;
        }
        return null;
    }

    public static void setCategoryDetails(Context context, KeyValueListModel model){
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_CATEGORIES, model.toString());
    }

    public static void removeCategoryDetails(Context context){
        removeSharedPreferenceKey(context, GlobalVariables.SHARED_PREFERENCE_CATEGORIES);
    }

    public static KeyValueListModel getCategoryDetails(Context context){
        String profileString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_CATEGORIES);
        if(profileString!=null){
            KeyValueListModel model = new KeyValueListModel();
            model.toObject(profileString);
            return model;
        }
        return null;
    }

    public static void setBannerDetails(Context context, KeyValueListModel model){
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_BANNER, model.toString());
    }

    public static void removeBannerDetails(Context context){
        removeSharedPreferenceKey(context, GlobalVariables.SHARED_PREFERENCE_BANNER);
    }
*/
    public static Bitmap getProfilePicture(Context context, String folder, String fileName){
        File file = getProfilePicturePath(context, folder, fileName);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile(file.getPath(), options);
        return bitmap;
    }

    public static void deleteProfilePicture(Context context, String folder, String fileName){
        File file = getProfilePicturePath(context, folder, fileName);
        if(file.exists()){
            file.delete();
        }
    }
    public static File getProfilePicturePath(Context context, String folder, String fileName){
        File file = new File(context.getFilesDir(), folder);
        if(!file.exists()){file.mkdir();}
        file = new File(file,fileName);
        return file;
    }

    public static int convertDPtoPixels(Context context, int sizeInDp){
        float scale = context.getResources().getDisplayMetrics().density;
        int dpAsPixels = (int) (sizeInDp*scale + 0.5f);
        return dpAsPixels;
    }

    public static File getExternalStorageDirectory(Context context){
        File returnFile = null;
        if(Environment.isExternalStorageEmulated()){
            File CardPathFolder = new File(Environment.getExternalStorageDirectory().toString());//(System.getenv("SECONDARY_STORAGE"));
            if(CardPathFolder.getFreeSpace()>0) {returnFile = CardPathFolder;}
        }else{returnFile = new File(Environment.getExternalStorageDirectory().toString());}
        return returnFile;
    }

    public static File getInternalStorageDirectory(){
        File returnFile = null;
        if(!Environment.isExternalStorageEmulated()){
            File internalFolder = new File(Environment.getDataDirectory().toString());
            returnFile = internalFolder;
        }else{returnFile = new File(Environment.getExternalStorageDirectory().toString());}
        return returnFile;
    }

    public String getRealPathFromURI(Context context, Uri contentUri)
    {
        try{
            String[] proj = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        catch (Exception e){
            return contentUri.getPath();
        }
    }

    public static File getImageFilePath(Context context, String fileName){
        File imageDirectory;

        String fileDirectory = null;
        imageDirectory = getExternalStorageDirectory(context);
        if(imageDirectory==null){
            fileDirectory = getInternalStorageDirectory().toString();
        }else{
            fileDirectory = getExternalStorageDirectory(context).toString();
        }
        fileDirectory += "/" + context.getResources().getString(R.string.app_name) + "/Images";

        imageDirectory = new File(fileDirectory);

        if(!imageDirectory.exists()){
            imageDirectory.mkdirs();
        }
        File outputFile = new File(imageDirectory, fileName);
        Log.d(TAG,"SDCARD : "+outputFile.toString());

        return outputFile;
    }

    public static String getDevice(){
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        int version = Build.VERSION.SDK_INT;
        String versionRelease = Build.VERSION.RELEASE;

        return versionRelease+"("+version+"),"+manufacturer+"("+model+")";
    }
    public static void deleteTempImages(Context context){
        String path = getExternalStorageDirectory(context).toString()+"/" + context.getResources().getString(R.string.app_name) + "/Images";
        String path1 = getInternalStorageDirectory().toString()+"/" + context.getResources().getString(R.string.app_name) + "/Images";

        File dir = new File(path);

        if (dir.isDirectory())
        {
            String[] children = dir.list();
            if(children!=null){for (int i = 0; i < children.length; i++) {new File(dir, children[i]).delete();}}
        }

        dir = new File(path1);
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            if(children!=null){for (int i = 0; i < children.length; i++) {new File(dir, children[i]).delete();}}
        }
    }

    public static String getUniqueID(Context context) {
        String uuid = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_GUID);
        if(uuid == null){
            setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_GUID, uniqueID);
            uuid = uniqueID;
        }
        Log.d(TAG,"UUID : "+uuid);
        return uuid;
    }

    public static void setProfile(Context context, ProfileModel profileModel) {
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_PROFILE, profileModel.toString());
    }

    public static void setAddress(Context context, AddressModel addressModel) {
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_ADDRESS, addressModel.toString());
    }


    public static void removeSharedPreferenceAll(Context context){
        sharedPreferences=context.getSharedPreferences(GlobalVariables.SHARED_PREFERENCE_KEY, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

   /* public static void logoutSocilNetworks(){
        if(LoginManager.getInstance()!=null){
            LoginManager.getInstance().logOut();
        }
        if(LoginMainActivity.mGoogleApiClient!=null){
            if(LoginMainActivity.mGoogleApiClient.isConnected()) {
                Auth.GoogleSignInApi.signOut(LoginMainActivity.mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                LoginMainActivity.mGoogleApiClient.disconnect();
                            }
                        });
            }
        }
    }*/
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public static int getToolBarHeight(Context context) {
        int result = 0;
        TypedValue tv = new TypedValue();
        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true))
        {
            result = TypedValue.complexToDimensionPixelSize(tv.data,context.getResources().getDisplayMetrics());
        }
        return result;
    }

    public static void setTranslucentStatusFlag(Window window, boolean on) {
        if (Build.VERSION.SDK_INT >= 19) {

            WindowManager.LayoutParams winParams = window.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            window.setAttributes(winParams);
        }
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

/*
    public static boolean isEmailValid(String email){
        String emailPattern = "^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        String emailPattern2 = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+\\.+[a-z]+";
        if (email.matches(emailPattern)) { return true;}
        else if (email.matches(emailPattern2)) { return true;}
        else { return false;}
    }
*/


    public static boolean isPasswordValid(String password){
        PasswordValidator validator = new PasswordValidator();
        if (validator.validate(password)) { return true;}
        else { return false;}
    }


    public final static boolean isPhoneNumberValid(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            if (target.length() < 9 || target.length() > 10) {
                return false;
            } else {
                return Patterns.PHONE.matcher(target).matches();
            }
        }
    }

    public final static boolean isPhoneNumberValid(CharSequence target, int selectCountryCodeLength) {
        if (target == null) {
            return false;
        } else {
            if (target.length() < selectCountryCodeLength || target.length() > selectCountryCodeLength) {
                return false;
            } else {
                return Patterns.PHONE.matcher(target).matches();
            }
        }
    }

    public static boolean isShowingProgress(){
        boolean showProgress = false;
        if(progressDialog!=null){
            if(progressDialog.isShowing()){
                showProgress = true;
            }
        }
        return showProgress;
    }

    public static void hideProgress(){
        if(progressDialog!=null) {
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
                progressDialog=null;
            }
        }
    }

    public static void showProgress(Context context, @Nullable String msg){
        if(context!=null){
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage(msg);
            progressDialog.setIsCancelable(false);
            progressDialog.show();
        }
    }

    public RotateAnimation rotateAnimation(float degree, int duration) {
        final RotateAnimation rotateAnim = new RotateAnimation(0.0f, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotateAnim.setDuration(duration);
        rotateAnim.setFillAfter(true);
        return rotateAnim;
    }

    public static void setSharedPreferenceString(Context context, String key, String value){
        sharedPreferences=context.getSharedPreferences(GlobalVariables.SHARED_PREFERENCE_KEY, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static void setSharedPreferenceLong(Context context, String key, long value){
        sharedPreferences=context.getSharedPreferences(GlobalVariables.SHARED_PREFERENCE_KEY, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public static void removeSharedPreferenceKey(Context context, String key){
        sharedPreferences=context.getSharedPreferences(GlobalVariables.SHARED_PREFERENCE_KEY, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.remove(key);
        editor.commit();
    }

    public  static String getSharedPreferenceString(Context context, String key){
        String returnString = null;
        if(context==null){context = MainActivity.mainContext;}
        sharedPreferences=context.getSharedPreferences(GlobalVariables.SHARED_PREFERENCE_KEY, Activity.MODE_PRIVATE);
        if(sharedPreferences.contains(key)){
            returnString = sharedPreferences.getString(key,"");
        }
        return returnString;
    }

    public  static String getSharedPreferenceString(String key){
        String returnString = null;
        if(context==null){context = MainActivity.mainContext;}
        sharedPreferences=context.getSharedPreferences(GlobalVariables.SHARED_PREFERENCE_KEY, Activity.MODE_PRIVATE);
        if(sharedPreferences.contains(key)){
            returnString = sharedPreferences.getString(key,"");
        }
        return returnString;
    }

    public  static long getSharedPreferenceLong(Context context, String key){
        long returnLong = 0;
        if(context==null){context = MainActivity.mainContext;}
        sharedPreferences=context.getSharedPreferences(GlobalVariables.SHARED_PREFERENCE_KEY, Activity.MODE_PRIVATE);
        if(sharedPreferences.contains(key)){
            returnLong = sharedPreferences.getLong(key,0);
        }
        return returnLong;
    }


/*
    public static void setSelectedCity(Context context, KeyValueModel keyValueModel){
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_SELECTED_CITY, keyValueModel.toString());
    }

    public static void removeSelectedCity(Context context){
        removeSharedPreferenceKey(context, GlobalVariables.SHARED_PREFERENCE_SELECTED_CITY);
    }

    public static KeyValueModel getSelectedCity(Context context){
        KeyValueModel keyValueModel = new KeyValueModel();
        String cityString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_SELECTED_CITY);
        if(cityString!=null){keyValueModel.toObject(cityString);}
        return keyValueModel;
    }

    public static int getSelectedCityID(Context context){
        KeyValueModel keyValueModel = getSelectedCity(context);
        if(keyValueModel.getId()!=null){return Integer.parseInt(keyValueModel.getId());}
        return -1;
    }

    public static String getSelectedCityName(Context context){
        KeyValueModel keyValueModel = getSelectedCity(context);
        return keyValueModel.getName();
    }
*/


    public static KeyValueListModel getBannerDetails(Context context){
        String profileString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_BANNER);
        if(profileString!=null){
            KeyValueListModel model = new KeyValueListModel();
            model.toObject(profileString);
            return model;
        }
        return null;
    }

    public static void setCategoryDetails(Context context, KeyValueListModel model){
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_CATEGORIES, model.toString());
    }

    public static void removeCategoryDetails(Context context){
        removeSharedPreferenceKey(context, GlobalVariables.SHARED_PREFERENCE_CATEGORIES);
    }

    public static KeyValueListModel getCategoryDetails(Context context){
        String profileString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_CATEGORIES);
        if(profileString!=null){
            KeyValueListModel model = new KeyValueListModel();
            model.toObject(profileString);
            return model;
        }
        return null;
    }

    public static void setBannerDetails(Context context, KeyValueListModel model){
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_BANNER, model.toString());
    }

    public static void removeBannerDetails(Context context){
        removeSharedPreferenceKey(context, GlobalVariables.SHARED_PREFERENCE_BANNER);
    }

    public static Map<String, String> getTokenHeader(Context context){
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("X-CSRF-Token", getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_TOKEN));
        headers.put("Content-Type", "application/json; charset=utf-8");
        headers.put("Cache-Control", "no-cache");
        headers.put("Cookie", getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_COOKIE));
        return headers;
    }

    public static int getOfferPercentage(Double oldRate, Double newRate){
        int offerPercentage = 0;
        if(oldRate>newRate){
            Double percentageValue = ((oldRate-newRate)/oldRate)*100;
            offerPercentage = percentageValue.intValue();
        }
        return offerPercentage;
    }

    public static String getToken(Context context){
        String token = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_TOKEN);
        return token;
    }

    public static String getUserID(Context context){
        String userID = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_USERID);
        return userID;
    }

    public static String getUserToken(Context context){
        String userToken = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_GCM_TOKEN);
        return userToken;
    }


    public static String getWishlistORCartID(Context context){
        String id = getToken(context);
        if(id==null||id.equalsIgnoreCase("null")){id = getUniqueID(context);}
        return id;
    }

    public static String getAuthenticationDateString(Context context, boolean isEncryption){
        String dateTimeString;
        if(!isEncryption){
            dateTimeString = getDatefromTimestamp(getCurrentTimestamp(context),GlobalVariables.AUTHENTICATION_DATE_FORMAT, Locale.ENGLISH)+getTimefromTimestamp(getCurrentTimestamp(context), GlobalVariables.AUTHENTICATION_TIME_FORMAT, Locale.ENGLISH);
        }
        else{dateTimeString = getUTCTime(context, Locale.ENGLISH);}
        return dateTimeString;
    }

    public static String getAuthenticationUsername (){
        return GlobalVariables.AUTHENTICATION_USERNAME;
    }

    public static boolean isGCM_Regestered(Context context){
        if(getUserToken(context)==null){
            return false;
        }else{
            return true;
        }
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static void setTimeDifference(Context context, long timestamp){
        long systemTime = System.currentTimeMillis()/1000;
        long diff = systemTime - timestamp;
        Log.d("Time Difference", "SystemTime : "+systemTime+"");
        Log.d("Time Difference", "TimeStamp : "+timestamp+"");
        Log.d("Time Difference", "DiffTime : "+diff+"");
        setSharedPreferenceLong(context, GlobalVariables.SHARED_PREFERENCE_TIME_DIFFERENCE, diff);
    }

    public static String getUTCTime(Context context, Locale locale){
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat(GlobalVariables.ENCRYPTION_TIME_FORMAT_UTC, locale);
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormatGmt.format(getUTCCalender(context));
    }

    /*public static Intent getNotificationIntent(Context context, JSONObject jsonObject){
        Intent intent = new Intent(context, NotificationListFragment.class);

        QuotationMainModel quotationMainModel = new QuotationMainModel();
        OrderModel orderModel = new OrderModel();

        if(quotationMainModel.toObject(jsonObject.toString())){
            if(getUserType(context)== GlobalVariables.USER_TYPE.VENDOR){
                intent = VendorQuotationsTabsActivity.newInstance(context, quotationMainModel);
            }else{
                intent = QuotationsTabsActivity.newInstance(context, quotationMainModel);
            }
        }else if(orderModel.toObject(jsonObject.toString())){
            intent = OrderDetailActivity.newInstance(context, orderModel);
        }

        return intent;
    }*/

    public static long getUTCTimeStamp(Context context){
        return getUTCCalender(context).getTime();
    }

    public static long getUTCTimeStampInSec(Context context){
        long timeStamp = (getUTCCalender(context).getTime())/1000;
        return timeStamp;
    }

    public static int[] getRGBColors(String rawData){
        String subStr = null;
        int[] rgbInts = new int[1];
        try{
            subStr = rawData.substring(rawData.indexOf("(")+1,rawData.indexOf(")"));
            final String[] strings = subStr.split(",");
            rgbInts = new int[strings.length];
            for (int i=0; i < strings.length; i++) {
                rgbInts[i] = Integer.parseInt(strings[i]);
            }

        }catch (Exception ex){
            rgbInts = new int[1];
        }

        return rgbInts;
    }

    public static Date getUTCCalender(Context context){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.setTimeInMillis(getCurrentTimestamp(context)*1000);
        Log.d(TAG,"CurrentTimestamp elapsed: "+ SystemClock.elapsedRealtime());
        return calendar.getTime();
    }
    public static String getAuthenticationPassword(Context context, String serviceURL){
        String textToConvert = serviceURL+"|"+GlobalVariables.AUTHENTICATION_USERNAME+"|"+GlobalVariables.AUTHENTICATION_PASSWORD+"|"+getAuthenticationDateString(context, true);
        Log.d(TAG, "Text to Convert : " + textToConvert);
        byte[] sha1hash = new byte[40];
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(textToConvert.getBytes("UTF-8"), 0, textToConvert.length());
            sha1hash = md.digest();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return convertToHex(sha1hash);
    }

    public static String md5(String s) {
        try {
            // Create MD5 Hash
            Log.d(TAG, s);
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            return convertToHex(messageDigest);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getAutherization(Context context, String serviceURL){
        String token = getToken(context);
        Log.d(TAG,"Token 1 :"+token);
        if(token==null){token = getUniqueID(context);}
        Log.d(TAG,"Token 2 :"+token);
        String authorization = getAuthenticationUsername()+" "+getAuthenticationPassword(context, serviceURL)+" "+getAuthenticationDateString(context, true)+" "+token;
        return authorization;
    }


    public static void setNotification(Context context, boolean isNotify){
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_NOTIFICATION_COUNT, isNotify?"1":"0");
       // MainActivity.setNotification(isNotify);
        //ProductDetail.setNotification(isNotify);
    }

    public static boolean getNotificationCount(Context context){
        String countString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_NOTIFICATION_COUNT);
        if(countString==null){
            return false;
        }
        return countString.equals("0")?false:true;
    }

    public static int getCartCount(Context context){
        String countString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_CART_COUNT);
        if(countString==null){
            return 0;
        }
        return Integer.parseInt(countString);
    }

    /*public static void setProfile(Context context, ProfileModel profileModel){
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_PROFILE, profileModel.toString());
        NavigationDrawerFragment.navigationHeaderRefresh(profileModel);
    }*/

    public static ProfileModel getProfile(Context context){
        String profileString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_PROFILE);
        if(profileString!=null){
            ProfileModel model = new ProfileModel();
            model.toObject(profileString);
            return model;
        }
        return null;
    }

    public static AddressModel getAddress(Context context){
        String addressString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_ADDRESS);
        if(addressString!=null){
            AddressModel model = new AddressModel();
            model.toObject(addressString);
            return model;
        }
        return null;
    }

    public static double getDoubleValue(String stringValue){
        double double_val=0.0;
        try {
            double_val= Double.parseDouble(stringValue);
            return  double_val;

        }catch (Exception e){
            return  double_val;

        }
    }

    public static String getDistance(Activity context, String latitude, String longitude) {
        String distance="";

        String addressString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_ADDRESS);
        if(addressString!=null) {

            AddressModel model = new AddressModel();
            model.toObject(addressString);

            Location loc1 = new Location("");
            loc1.setLatitude(model.getLatitude());
            loc1.setLongitude(model.getLongitude());

            Location loc2 = new Location("");
            loc2.setLatitude(Double.parseDouble(latitude));
            loc2.setLongitude(Double.parseDouble(longitude));

            //distance = loc1.distanceTo(loc2)/1000;   //in km

             double distanceInMeters = loc1.distanceTo(loc2);
            // double i2=distanceInMeters/60000;
          //  String  km =new DecimalFormat("##.##").format(i2));
             String  km = String.valueOf(distanceInMeters / 0.000621371);
            // String  km_indis =new DecimalFormat("##.##").format(km);

            Log.d("distanceInMeters","=="+km);


            return km;
        }
        return  null;


    }
    public static int findDistance(Activity context, String latitude, String longitude) {

        String addressString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_ADDRESS);
        if(addressString!=null) {

            AddressModel model = new AddressModel();
            model.toObject(addressString);
            int earthRadious = 6371;// radius of earth in Km
            double latFrom = model.getLatitude();
            double latTo = Double.parseDouble(latitude);
            double lonFrom = model.getLongitude();
            double lonTo = Double.parseDouble(longitude);

            double diffLat = Math.toRadians(latTo - latFrom);
            double diffLon = Math.toRadians(lonTo - lonFrom);

            double aValue = Math.sin(diffLat / 2) * Math.sin(diffLat / 2)
                    + Math.cos(Math.toRadians(latFrom))
                    * Math.cos(Math.toRadians(latTo)) * Math.sin(diffLon / 2)
                    * Math.sin(diffLon / 2);

            double c = 2 * Math.asin(Math.sqrt(aValue));
            double results = earthRadious * c;

            double kms = results / 1;
            DecimalFormat newFormat = new DecimalFormat("####");
            int killoMeter = Integer.valueOf(newFormat.format(kms));

            double meter = results % 1000;
            int metersDistance = Integer.valueOf(newFormat.format(meter));


            Log.d("Value", "" + results + "   KilloMeter  " + killoMeter
                    + " Meter   " + metersDistance);

            return killoMeter;
        }
        return 0;
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }

    public static String getDistanceFromAddress(Activity context, String latitude, String longitude){
        String addressString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_ADDRESS);
        String distance="";
        if(addressString!=null){
            AddressModel model = new AddressModel();
            model.toObject(addressString);

             LatLng mOrigin;
             LatLng mDestination;

            mOrigin = new LatLng(model.getLatitude(), model.getLongitude());
            mDestination = new LatLng(getDoubleValue(latitude), getDoubleValue(longitude));

            new TaskDirectionRequest().execute(buildRequestUrl(context,mOrigin, mDestination));
            distance=DISTANCE;

            Log.d("adress00","=="+model);
            Log.d("latitude00","=="+latitude);
            Log.d("longitude00","=="+longitude);

            return distance;

        }
        return null;
    }


    public static String buildRequestUrl(Activity activity,LatLng origin, LatLng destination) {
        String strOrigin = "origin=" + origin.latitude + "," + origin.longitude;
        String strDestination = "destination=" + destination.latitude + "," + destination.longitude;
        String sensor = "sensor=false";
        String mode = "mode=driving";

        String param = strOrigin + "&" + strDestination + "&" + sensor + "&" + mode;
        String output = "json";
        String APIKEY = activity.getResources().getString(R.string.google_maps_key);

        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + param + "&key=" + APIKEY;
        Log.d("TAG", url);
        return url;
    }


    public static String requestDirection(String requestedUrl) {
        String responseString = "";
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {
            URL url = new URL(requestedUrl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            inputStream = httpURLConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuffer stringBuffer = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
            responseString = stringBuffer.toString();
            bufferedReader.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        httpURLConnection.disconnect();
        return responseString;
    }

    //Get JSON data from Google Direction
    public static class TaskDirectionRequest extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String responseString = "";
            try {
                responseString = requestDirection(strings[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return responseString;
        }

        @Override
        protected void onPostExecute(String responseString) {
            super.onPostExecute(responseString);
            //Json object parsing
            setDistanceAndTime(responseString);
        }
    }

    public static void setDistanceAndTime(String distanceTimeString) {

        try {
            JSONObject json = new JSONObject(distanceTimeString);
            if (json.has("routes")) {
                JSONArray array = json.getJSONArray("routes");
                JSONObject distanceObject = array.getJSONObject(0);
                if (distanceObject.has("legs")) {
                    JSONArray distanceArray = distanceObject.getJSONArray("legs");
                    JSONObject nestedDistanceObject = distanceArray.getJSONObject(0);
                    JSONObject tempDistanceObject = nestedDistanceObject.getJSONObject("distance");
                    String distnace = tempDistanceObject.getString("text");

                    JSONObject tempDurationObject = nestedDistanceObject.getJSONObject("duration");
                    String time = tempDurationObject.getString("text");

                    Log.d("DISTANCE00","=="+distnace);
                    DISTANCE=distnace;

//                    distance_tv.setText(distnace);
//                    distance_tv.setText(String.format("%.02f", distnace) + " " + activity.getString(R.string.km));
//                    time_tv.setText(time);

//                    selectedDistance=distnace.replaceAll("km","");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public static void setFragmentCreated(Context context, String value){
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_FRAGMENT_CREATED, value);
    }

    public static String getFragmentCreated(Context context){
        String value = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_FRAGMENT_CREATED);
        return value;
    }
    public static void setUserType(Context context, GlobalVariables.USER_TYPE user_type){
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_USER_TYPE, user_type.toString());
    }

    public static GlobalVariables.USER_TYPE getUserType(Context context){
        String typeString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_USER_TYPE);
        if(typeString!=null){
            if(typeString.equals(GlobalVariables.USER_TYPE.USER.toString())){return GlobalVariables.USER_TYPE.USER;}
            return GlobalVariables.USER_TYPE.VENDOR;
        }
        return GlobalVariables.USER_TYPE.NONE;
    }

    public static void setBidTiming(Context context){
        setSharedPreferenceString(context, GlobalVariables.SHARED_BID_CHECKER_TIMESTAMP, getCurrentTimestamp(context)+"");
    }

    public static long getBidTiming(Context context){
        String typeString = getSharedPreferenceString(context, GlobalVariables.SHARED_BID_CHECKER_TIMESTAMP);
        try{
            return Long.parseLong(typeString);
        }catch (Exception ex){
            return 0;
        }
    }

    public static void setLanguage(Context context, GlobalVariables.LANGUAGE language){
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_SELECTED_LANGUAGE, language.toString());
    }

    public static GlobalVariables.LANGUAGE getLanguage(Context context){
        String langString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_SELECTED_LANGUAGE);
        GlobalVariables.LANGUAGE returnLanguage = GlobalVariables.LANGUAGE.ENGLISH;
        if(langString!=null){
            returnLanguage = langString.equalsIgnoreCase(GlobalVariables.LANGUAGE.ARABIC.toString())? GlobalVariables.LANGUAGE.ARABIC : GlobalVariables.LANGUAGE.ENGLISH;
        }
        return returnLanguage;
    }

    public Spanned getHTMLString(String unFormatedString) {
        if (Build.VERSION.SDK_INT >= 24) {
            return Html.fromHtml(unFormatedString, Html.FROM_HTML_MODE_LEGACY);
        } else {
            return Html.fromHtml(unFormatedString);
        }
    }

    public static String html2text(String html) {
        return Jsoup.parse(html).text();
    }

    public static Map<String, String> getHeader(Context context, String serviceURL){
        Map<String, String> headers = new HashMap<String, String>();
        String authorization = getAuthenticationPassword(context, serviceURL);
        Log.d(TAG, "Authorization : " + authorization);
        headers.put(GlobalVariables.CONTENT_TYPE, GlobalVariables.CONTENT_TYPE_VALUE);
        headers.put(GlobalVariables.AUTHORIZATION, authorization);
        headers.put(GlobalVariables.ACCEPT_LANGUAGE, getLanguage(context).toString());
        headers.put("Cache-Control", "no-cache");
        headers.put("Token", getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_TOKEN));
        AddressModel addressModel=getAddress(context);
        if (addressModel!=null && addressModel.getAddress()!=null){
            headers.put("latitude", String.valueOf(addressModel.getLatitude()));
            headers.put("longitude", String.valueOf(addressModel.getLongitude()));
        }
        Log.d(TAG, "Header : "+headers);
        return headers;
    }
    public static Map<String, String> postHeader(Context context, String serviceURL){
        Map<String, String> headers = new HashMap<String, String>();
        String authorization = getAuthenticationPassword(context, serviceURL);
        Log.d(TAG, "Authorization : " + authorization);
        headers.put(GlobalVariables.CONTENT_TYPE, GlobalVariables.CONTENT_TYPE_VALUE);
        headers.put(GlobalVariables.AUTHORIZATION, authorization);
        headers.put(GlobalVariables.ACCEPT_LANGUAGE, getLanguage(context).toString());
        headers.put("Cache-Control", "no-cache");
        headers.put("Token", getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_TOKEN));
        AddressModel addressModel=getAddress(context);
        if (addressModel!=null && addressModel.getAddress()!=null){
            headers.put("latitude", String.valueOf(addressModel.getLatitude()));
            headers.put("longitude", String.valueOf(addressModel.getLongitude()));
        }

        Log.d(TAG, "Header : "+headers);
        return headers;
    }

    public static boolean isLoggedIn(Context context){
        ProfileModel model = getProfile(context);
        return model!=null?true:false;
    }

    public NotificationSettingsModel getNotificationSettings(){
        String notificationSettingsString = getSharedPreferenceString( globalVariables.SHARED_PREFERENCE_NOTIFICATION_SETTINGS);
        NotificationSettingsModel notificationSettingsModel = new NotificationSettingsModel();
        if(notificationSettingsString!=null){ notificationSettingsModel.toObject(notificationSettingsString); }
        return notificationSettingsModel;
    }

    public static int getColor(int resID){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? context.getColor(resID):context.getResources().getColor(resID);
    }

    public static int[] convertTexttoDate(String dateString){
        int[] dateArray= {0,0,0};
        if(!dateString.isEmpty()&&dateString!=null){
            String[] splitString = dateString.split("-");
            if(splitString.length==3){
                dateArray[0] = Integer.parseInt(splitString[0]);
                dateArray[1] = Integer.parseInt(splitString[1]);
                dateArray[2] = Integer.parseInt(splitString[2]);
            }
        }
        return dateArray;
    }

    public static void refreshAfterLogin(Context context){
        //NavigationDrawerFragment.navigationHeaderRefresh(getProfile(context));
    }

    public static void closeAllActivities(){
        LoginActivity.closeThisActivity();
        //ProfileMainActivity.closeThisActivity();
      //  CitySelectingActivity.closeThisActivity();
       // MainActivity.closeThisActivity();
        OtpActivity.closeThisActivity();
      // AccountActivity.closeThisActivity();
       MapActivity.closeThisActivity();

       // RegistrationActivity.closeThisActivity();
       // LoginActivity.closeThisActivity();

    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Bitmap scaled=Bitmap.createScaledBitmap(myBitmap,100,100,true);
            return scaled;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

    public static Bitmap getBitmapFromLink(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            try {
                connection.connect();
            } catch (Exception e) {
                Log.v("asfwqeds", e.getMessage());
            }
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            Log.v("asfwqeds", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }


    public static void logoutApplication(Context context, boolean isSessionExpired) {
        //remove saved details.
        GlobalVariables.LANGUAGE lang = getLanguage(context);
        removeSharedPreferenceKey(context, GlobalVariables.SHARED_PREFERENCE_PROFILE);
        removeSharedPreferenceAll(context);
        setLanguage(context, lang);

        if (isSessionExpired) {
//            closeAllActivities();
            restartEntireApp(context, false);
        }
    }

    public static void restartEntireApp(Context mainContext, boolean isLanguageChange) {
        if (isLanguageChange) {
            SharedPreferences shared_preference = PreferenceManager.getDefaultSharedPreferences(mainContext
                    .getApplicationContext());

            String mCustomerLanguage = shared_preference.getString(
                    GlobalVariables.SHARED_PREFERENCE_SELECTED_LANGUAGE, "null");
            String mCurrentlanguage;
            if ((mCustomerLanguage.equalsIgnoreCase("en"))) {
                setLanguage(mainContext, GlobalVariables.LANGUAGE.ARABIC);

                mCurrentlanguage = "ar";
            } else {
                mCurrentlanguage = "en";
                setLanguage(mainContext, GlobalVariables.LANGUAGE.ENGLISH);

            }
            SharedPreferences.Editor editor = shared_preference.edit();
            editor.putString(GlobalVariables.SHARED_PREFERENCE_SELECTED_LANGUAGE, mCurrentlanguage);
            editor.commit();
        }
        closeAllActivities();

        Intent i = new Intent(mainContext, SplashActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mainContext.startActivity(i);
        System.exit(0);
    }

    public static void playStoreAction(Context mContext){
       // mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + mContext.getString(R.string.playstore_ID))));
    }

    public static int getSoftButtonsBarHeight(Window window) {
        // getRealMetrics is only available with API 17 and +
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            DisplayMetrics metrics = new DisplayMetrics();
            window.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int usableHeight = metrics.heightPixels;
            window.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            int realHeight = metrics.heightPixels;
            if (realHeight > usableHeight)
                return realHeight - usableHeight;
            else
                return 0;
        }
        return 0;
    }

    public static void shareLinkwithFriends(Context context, String title, String description, String link){
        try
        { Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, title);
            String sAux = "\n"+description+"\n\n";
            if(link!=null){sAux = sAux +link+" \n\n";}
            i.putExtra(Intent.EXTRA_TEXT, sAux);
            context.startActivity(Intent.createChooser(i, "choose one"));
        }
        catch(Exception e) { Log.e(TAG,"Exception on Sharing : "+e);}
    }

   /* public static void reloadCartAndWishCount(String productID, @Nullable String colorCode, int type, int quantity, boolean isSuccess){
        if(type == globalVariables.TYPE_WISHLIST){
            ItemsListMain.changeWishlistIcon(productID, isSuccess);
            WishListActivity.changeDeleteIcon(productID, isSuccess);
        }

        if(type==globalVariables.TYPE_CART&&quantity>0){CartActivity.refreshPrice(productID, colorCode, quantity, isSuccess);}
        else{CartActivity.refreshIcons(productID, colorCode, type, isSuccess);}

        MainActivity.updateCartAndWishlist(type, isSuccess);
        ProductDetail.updateCartAndWishlist(type, isSuccess);
    }*/

    /*Animation Functions*/
    public static Animation inFromRightAnimation() {

        Animation inFromRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
        return inFromRight;
    }

    public static Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(500);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }

    public static Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }

    public static Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }

   /* public static KeyValueListModel getCityDetails(Context context){
        String cityString = getSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_COUNTRY_MODEL);
        if(cityString!=null){
            KeyValueListModel cityModel = new KeyValueListModel();
            cityModel.toObject(cityString);
            return cityModel;
        }
        return null;
    }*/

    public static void callPhone(Activity activity, String number){
        if(number!=null && !number.equalsIgnoreCase("")){
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:"+number));
            activity.startActivity(intent);
        }
    }

    public  void setCountryDetail( CountryModel cityModel){
        setSharedPreferenceString( context, globalVariables.SHARED_PREFERENCE_COUNTRY_MODEL, cityModel.toString());
    }

    public  boolean isSetCityDetail( boolean isCitySelected){

        return  isCitySelected;
    }

    public  void removeCityDetail(){
        removeSharedPreferenceKey( context, globalVariables.SHARED_PREFERENCE_COUNTRY_MODEL);
    }

    public  CountryModel getCountryDetail(){
        String countryString = getSharedPreferenceString( globalVariables.SHARED_PREFERENCE_COUNTRY_MODEL);
        if(countryString!=null){
            CountryModel countryModel = new CountryModel();
            countryModel .toObject(countryString);
            return countryModel ;
        }
        return null;
    }

    public static void showOnMap(Context context, String label, String latitude, String longitude){
        String uriBegin = "geo:" + latitude + "," + longitude;
        String query = latitude + "," + longitude + "(" + label + ")";
        String encodedQuery = Uri.encode(query);
        String uriString = uriBegin + "?q=" + encodedQuery + "&z=16";
        Uri uri = Uri.parse(uriString);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

  /*  public static void setCityDetails(Context context, KeyValueListModel cityModel){
        setSharedPreferenceString(context, GlobalVariables.SHARED_PREFERENCE_COUNTRY_MODEL, cityModel.toString());
    }

    public static void removeCityDetails(Context context){
        removeSharedPreferenceKey(context, GlobalVariables.SHARED_PREFERENCE_COUNTRY_MODEL);
    }
*/

    public static long getTimeStampFromFBTimeString(String timeString){
        try {
            DateFormat formatLocal = new SimpleDateFormat(GlobalVariables.DATE_FORMAT, Locale.ENGLISH);
            Date date = formatLocal.parse(timeString);
            long timestamp = date.getTime()/1000;
            return timestamp;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void displayMessaage(Context context, View view, String msg){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
            Snackbar.SnackbarLayout layout = (Snackbar.SnackbarLayout) snackbar.getView();
            Activity activity = (Activity) context;
            int height = getToolBarHeight(context)+getStatusBarHeight(context);//getSoftButtonsBarHeight(activity.getWindow());
            //if(height>0){height -= 1;}
            //layout.setPadding(0,0,0,height);

            final FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) layout.getLayoutParams();
            params.setMargins(0, height, 0, 0);
            params.gravity = Gravity.TOP;
            layout.setLayoutParams(params);

            TextView textView = (TextView) layout.findViewById(R.id.snackbar_text);
            textView.setMaxLines(5);

            snackbar.show();
        }else{
            Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
            toast.show();
        }

    }

    public static void displayErrorDialog(Context context, String message){
        final AlertDialog alertDialog = new AlertDialog(context);
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.drawable.app_icon);
        alertDialog.setTitle(context.getString(R.string.app_name));
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(context.getString(R.string.ok), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public static void displayDialog(Context context, String message){
        final AlertDialog alertDialog = new AlertDialog(context);
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.drawable.app_icon);
        alertDialog.setTitle(context.getString(R.string.app_name));
        alertDialog.setMessage(message);
        alertDialog.setPositiveButton(context.getString(R.string.ok), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    public static void closeKeyboard(Activity activity){
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public static boolean isKeyboardOpen(Activity activity){
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            if (imm.isAcceptingText()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void displayErrorMessaagePopUp(Activity activity, String title, String msg) {
        if (title == null) {
            title = context.getString(R.string.app_name);
        }
        final AlertDialog alertDialog = new AlertDialog(activity);
        alertDialog.setCancelable(false);
        alertDialog.setIcon(R.drawable.app_icon);
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setPositiveButton(context.getString(R.string.ok), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();

    }


    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        if(getLanguage(context).equals(GlobalVariables.LANGUAGE.ARABIC)){
            fileName = fileName+"_ar";
        }
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static boolean isNotNullValue(String value) {
        boolean result = false;
        if (value != null && !value.isEmpty() && !value.equals("null") && !value.isEmpty() && value.trim()
                .length() > 0) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static boolean isNotZeroValue(String value) {
        boolean result = false;
        try {
            double temp = Double.parseDouble(value);
            if (temp > 0) {
                result = true;
            } else {
                result = false;
            }
        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public static String getStringFromList(List<String> list) {
        String idList = "";
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    idList += list.get(i);
                } else {
                    idList += list.get(i) + "," + " ";
                }
            }
        }
        return idList;
    }


    public static List<HomeTopCategoryModel> getWishlist(List<HomeTopCategoryModel> list, boolean isAllow) {
        List<HomeTopCategoryModel> updatedList;
        updatedList=new ArrayList<>();
        updatedList.clear();
        try {
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    HomeTopCategoryModel offerModel = list.get(i);
                    if (isAllow){
                        if (isNotNullValue(offerModel.getWishlist()) && offerModel.getWishlist().equalsIgnoreCase("1")) {
                            updatedList.add(offerModel);
                        }

                    }else {
                        if (isNotNullValue(offerModel.getWishlist()) && offerModel.getWishlist().equalsIgnoreCase("0")) {
                            updatedList.add(offerModel);
                        }
                    }
                }
            }
        } catch (Exception e) {
            return updatedList;
        }

        return updatedList;
    }

    public static OfferModel getSelectedModelFromList(List<OfferModel> list,String selectedOfferList) {
        OfferModel resultvariantModel = null;

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                OfferModel variantModel = list.get(i);
                if (variantModel.getId().equalsIgnoreCase(selectedOfferList)) {
                    return variantModel;
                }
            }
        } else {
            return resultvariantModel;
        }

        return resultvariantModel;
    }
    public static String getSelectedIdsFromList(List<FilterDistanceModel> list,String type) {
        String idList = "";
        List<FilterDistanceModel> updatedList=new ArrayList<>();
        updatedList.clear();

        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                FilterDistanceModel subCategoryModel = list.get(i);
                if (isNotNullValue(subCategoryModel.getSelected()) && subCategoryModel.getSelected().equalsIgnoreCase("1")) {
                    updatedList.add(subCategoryModel);
                }
            }
        }

        if (updatedList.size() > 0) {
            for (int i = 0; i < updatedList.size(); i++) {
                FilterDistanceModel subCategoryModel = updatedList.get(i);
                if (i == updatedList.size() - 1) {
                    if (isNotNullValue(subCategoryModel.getSelected()) && subCategoryModel.getSelected().equalsIgnoreCase("1")) {
                        if (type.equalsIgnoreCase("0")){
                            idList += subCategoryModel.getDistance();
                        }else if (type.equalsIgnoreCase("1")){
                            idList += subCategoryModel.getId();
                        }else if (type.equalsIgnoreCase("2")){
                            idList += subCategoryModel.getPreTime();
                        }
                    }
                } else {
                    if (type.equalsIgnoreCase("0")){
                        idList += subCategoryModel.getDistance() + ",";
                    }else if (type.equalsIgnoreCase("1")){
                        idList += subCategoryModel.getId() + ",";
                    }else if (type.equalsIgnoreCase("2")){
                        idList += subCategoryModel.getPreTime() + ",";
                    }
//                    idList += subCategoryModel.getId() + ",";
                }
            }
        }

        return idList;
    }
}
