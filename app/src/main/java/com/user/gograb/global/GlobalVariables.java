package com.user.gograb.global;

import android.util.Log;


import com.user.gograb.AppController;
import com.user.gograb.R;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sivasabharish Chinnaswamy on 29-09-2015.
 */
public class GlobalVariables {

    public static final int BID_REFRESH_TIME_MS = 1000*60;//1min
    public static final int SOCKET_TIMEOUT_MS = 120000;
    public static final int VENDOR_RADIUS = 5;
    public static final int OTP_LENGTH = 4;
    public static String SUPPORT_DEFAULT_NUMBER = "1234567890";
    public static String SUPPORT_DEFAULT_EMAIl = "support_yin@imcrinox.com";
    public static String SUPPORT_DEFAULT_SUBJECT = "abc";
    public static String DEFAULT_COUNTRY_CODE    = "+91";
    public static String SUPPORT_DEFAULT_DESCRIPTION = "abc";
    public static String DATE_FORMAT_TIME_NEW          ="HH:mm:ss";
    private static GlobalVariables thisInstance;


    public static final int WAITING                    = 20;
    public static final int START_RIDE                 = 21;
    public static final int ON_THE_WAY_TO_THERIPAST    = 22;
    public static final int REACHED_THERAPIST_LOCATION = 23;
    public static final int WAY_TO_CUSYOMER_LOCATION   = 24;
    public static final int COMPLETE                   = 25;

    public static final int LOCATION_INTERVAL         = 10000;
    public static final int FASTEST_LOCATION_INTERVAL = 5000;

    public static synchronized GlobalVariables getInstance(){
        if(thisInstance == null){thisInstance = new GlobalVariables();}
        return thisInstance;
    }

    public GlobalVariables() {
    }

    public static final int ANIMATION_SCROLL_DURATION = 400;/* milliseconds */

    public enum PROVIDERS {
        ADMIN("admin"),
        FACEBOOK("facebook"),
        GOOGLE("google");

        private final String text;
        private PROVIDERS(final String text) {
            this.text = text;
        }
        @Override
        public String toString() {
            return text;
        }
    }

    public enum USER_TYPE {
        NONE("none"),
        USER("user"),
        VENDOR("vendor");

        private final String text;
        private USER_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public int toInt(){
            Log.d("Enum", this.text);
            if(this.text.equals(USER.toString())){return 1;}
            else{return 2;}
        }
    }

    public enum CART {
        MINIMUM_QUANTITY(1),
        MAXIMUM_QUANTITY(99);

        private final int value;
        private CART(final int value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return value+"";
        }

        public int toInt() {
            return value;
        }
    }

    public enum QUANTITY {
        MINIMUM_QUANTITY(0),
        MAXIMUM_QUANTITY(99);

        private final int value;
        private QUANTITY(final int value) {
            this.value = value;
        }
        @Override
        public String toString() {
            return value+"";
        }

        public int toInt() {
            return value;
        }
    }

    public enum WEBVIEW_TYPE {
        ABOUT_US("about_us"),
        TERMS("terms"),
        PRIVACY("privacy");

        private final String text;
        private WEBVIEW_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum FILE_TYPE {
        URL("1"),
        BASE64("2");

        private final String text;
        private FILE_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public int toInt(){
            Log.d("Enum", this.text);
            if(this.text.equals(BASE64.toString())){return 2;}
            else{return 1;}
        }
    }

    public enum DOCUMENT_TYPE {
        IMAGE("101"),
        VEHICLE("102"),
        AADHAR("103"),
        LICENCE("104"),
        RC("105");

        private final String text;
        private DOCUMENT_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public int toInt(){
            Log.d("Enum", this.text);
            if(this.text.equals(RC.toString())){return 105;}
            if(this.text.equals(LICENCE.toString())){return 104;}
            if(this.text.equals(AADHAR.toString())){return 103;}
            if(this.text.equals(VEHICLE.toString())){return 102;}
            else{return 101;}
        }

        /*public int getResource(){
            Log.d("Enum", this.text);
            if(this.text.equals(RC.toString())){return R.string.rc_details;}
            if(this.text.equals(LICENCE.toString())){return R.string.licence_details;}
            else if(this.text.equals(AADHAR.toString())){return R.string.aadhar_details;}
            else if(this.text.equals(VEHICLE.toString())){return R.string.vehicle_detail;}
            else{return R.string.not_available;}
        }

        public int getTitleResource(){
            Log.d("Enum", this.text);
            if(this.text.equals(RC.toString())){return R.string.rc_number;}
            if(this.text.equals(LICENCE.toString())){return R.string.licence_number;}
            else if(this.text.equals(AADHAR.toString())){return R.string.aadhar_number;}
            else if(this.text.equals(VEHICLE.toString())){return R.string.rc_details;}
            else{return R.string.not_available;}
        }*/

    }
    public enum LOCATION_TYPE {
        NONE("0"),
        PICKUP("1"),
        DROP("2");

        private final String text;
        private LOCATION_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public int toInt(){
            Log.d("Enum", this.text);
            if(this.text.equals(PICKUP.toString())){return 1;}
            else if(this.text.equals(DROP.toString())){return 2;}
            else{return 0;}
        }

        /*public int getResource(){
            Log.d("Enum", this.text);
            if(this.text.equals(BATTING.toString())){return R.string.debit;}
            else if(this.text.equals(CREDIT.toString())){return R.string.credit;}
            else{return R.string.debit;}
        }*/

        public LOCATION_TYPE toTYPE(String val){
            if(val.equalsIgnoreCase(PICKUP.toString())){
                return PICKUP;
            }else if(val.equalsIgnoreCase(DROP.toString())){
                return DROP;
            }else{
                return NONE;
            }
        }
    }

    public enum IMAGE_UPLOAD_TYPE {
        NONE("none"),
        POST_ADS("post_ads"),
        PROFILE("profile");

        private final String text;
        private IMAGE_UPLOAD_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public enum GENDER {
        NONE("0"),
        MALE("1"),
        FEMALE("2"),
        OTHERS("3");

        private final String text;
        private GENDER(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public int toInt(){
            Log.d("Enum", this.text);
            if(this.text.equals(MALE.toString())){return 1;}
            else if(this.text.equals(FEMALE.toString())){return 2;}
            else if(this.text.equals(OTHERS.toString())){return 3;}
            else{return 0;}
        }

        public int getResource(){
            Log.d("Enum", this.text);
            if(this.text.equals(MALE.toString())){return R.string.male;}
            else if(this.text.equals(FEMALE.toString())){return R.string.female;}
            else if(this.text.equals(OTHERS.toString())){return R.string.others;}
            else{return R.string.not_available;}
        }

        public GENDER toGender(String val){
            if(val.equalsIgnoreCase(GENDER.MALE.toString())){
                return GENDER.MALE;
            }else if(val.equalsIgnoreCase(GENDER.FEMALE.toString())){
                return GENDER.FEMALE;
            }else if(val.equalsIgnoreCase(GENDER.OTHERS.toString())){
                return GENDER.OTHERS;
            }else{
                return GENDER.NONE;
            }
        }
    }

    public enum HOUSE_TYPE {
        APARTMENT("1"),
        INDEPENDENT_HOUSE("2");

        private static final Map<String, HOUSE_TYPE> map = new HashMap<>();
        static {
            for (HOUSE_TYPE en : values()) {
                map.put(en.text, en);
            }
        }

        public static HOUSE_TYPE valueFor(String name) {
            return map.get(name);
        }

        private enum CODE {
            CODE_APARTMENT("1"),
            CODE_INDEPENDENT_HOUSE("2");

            private final String code;

            private CODE(final String code) {
                this.code = code;
            }

            @Override
            public String toString() {
                return code+"";
            }

            public String toCode(){return this.code;}

            private static final Map<String, CODE> map = new HashMap<>();
            static {
                for (CODE en : values()) {
                    map.put(en.code, en);
                }
            }

            public static CODE valueFor(String name) {
                return map.get(name);
            }
        }

        private final String text;

        private HOUSE_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public String getCode(){
            if(this.text.equals(APARTMENT.toString())){return CODE.CODE_APARTMENT.toCode();}
            else if(this.text.equals(INDEPENDENT_HOUSE.toString())){return CODE.CODE_INDEPENDENT_HOUSE.toCode();}
            else {return CODE.CODE_APARTMENT.toCode();}
        }

        public HOUSE_TYPE getHOUSE_TYPE(String code){
            CODE codeEnum = CODE.CODE_APARTMENT;
            try{
                codeEnum = CODE.valueFor(code);
            }catch (Exception ex){
                codeEnum = CODE.CODE_APARTMENT;
            }
            if(codeEnum!=null){
                switch (codeEnum){
                    case CODE_APARTMENT:
                        return APARTMENT;
                    case CODE_INDEPENDENT_HOUSE:
                        return INDEPENDENT_HOUSE;
                    default :
                        return APARTMENT;
                }
            }else{
                return APARTMENT;
            }

        }
/*
        public int getStringResources(){
            int resourceInt = R.string.singleCar;
            if(text.equalsIgnoreCase(APARTMENT.toString())){resourceInt = R.string.apartment;}
            else if(text.equalsIgnoreCase(INDEPENDENT_HOUSE.toString())){resourceInt = R.string.independent_house;}
            return resourceInt;
        }*/

        public double getValue(){
            GlobalVariables globalVariables = AppController.getInstance().getGlobalVariables();
            double resourceInt = globalVariables.HOUSE_TYPE_APARTMENT;
            if(text.equalsIgnoreCase(APARTMENT.toString())){resourceInt = globalVariables.HOUSE_TYPE_APARTMENT;}
            else if(text.equalsIgnoreCase(INDEPENDENT_HOUSE.toString())){resourceInt = globalVariables.HOUSE_TYPE_INDEPENDENT_HOUSE;}
            return resourceInt;
        }
    }

    public enum CAR_SERVICE_TYPE {
        SINGLE_CAR("1"),
        MULTIPLE_CAR("2");

        private static final Map<String, CAR_SERVICE_TYPE> map = new HashMap<>();
        static {
            for (CAR_SERVICE_TYPE en : values()) {
                map.put(en.text, en);
            }
        }

        public static CAR_SERVICE_TYPE valueFor(String name) {
            return map.get(name);
        }

        private enum CODE {
            CODE_SINGLE_CAR("1"),
            CODE_MULTIPLE_CAR("2");

            private final String code;

            private CODE(final String code) {
                this.code = code;
            }

            @Override
            public String toString() {
                return code+"";
            }

            public String toCode(){return this.code;}

            private static final Map<String, CODE> map = new HashMap<>();
            static {
                for (CODE en : values()) {
                    map.put(en.code, en);
                }
            }

            public static CODE valueFor(String name) {
                return map.get(name);
            }
        }

        private final String text;

        private CAR_SERVICE_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public String getCode(){
            if(this.text.equals(SINGLE_CAR.toString())){return CODE.CODE_SINGLE_CAR.toCode();}
            else if(this.text.equals(MULTIPLE_CAR.toString())){return CODE.CODE_MULTIPLE_CAR.toCode();}
            else {return CODE.CODE_SINGLE_CAR.toCode();}
        }

        public CAR_SERVICE_TYPE getCAR_SERVICE_TYPE(String code){
            CODE codeEnum = CODE.CODE_SINGLE_CAR;
            try{
                codeEnum = CODE.valueFor(code);
            }catch (Exception ex){
                codeEnum = CODE.CODE_SINGLE_CAR;
            }
            if(codeEnum!=null){
                switch (codeEnum){
                    case CODE_SINGLE_CAR:
                        return SINGLE_CAR;
                    case CODE_MULTIPLE_CAR:
                        return MULTIPLE_CAR;
                    default :
                        return SINGLE_CAR;
                }
            }else{
                return SINGLE_CAR;
            }

        }
/*
        public int getStringResources(){
            int resourceInt = R.string.singleCar;
            if(text.equalsIgnoreCase(SINGLE_CAR.toString())){resourceInt = R.string.singleCar;}
            else if(text.equalsIgnoreCase(MULTIPLE_CAR.toString())){resourceInt = R.string.multipleCar;}
            return resourceInt;
        }*/

        public double getValue(){
            GlobalVariables globalVariables = AppController.getInstance().getGlobalVariables();
            double resourceInt = globalVariables.CAR_SERVICE_TYPE_SINGLE_CAR;
            if(text.equalsIgnoreCase(SINGLE_CAR.toString())){resourceInt = globalVariables.CAR_SERVICE_TYPE_SINGLE_CAR;}
            else if(text.equalsIgnoreCase(MULTIPLE_CAR.toString())){resourceInt = globalVariables.CAR_SERVICE_TYPE_MULTIPLE_CAR;}
            return resourceInt;
        }
    }

    public enum LANGUAGE {
        ARABIC("ar"),
        ENGLISH("en");

        private final String text;
        private LANGUAGE(final String text) {
            this.text = text;
        }
        @Override
        public String toString() {
            return text;
        }
    }

    public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    //HttpResponse Results
    public static String RESPONSE_HTTP_SUCCESS             = "3";
    public static String RESPONSE_HTTP_NO_RECORDS_FOUND    = "404";
    public static String RESPONSE_HTTP_BAD_REQUEST         = "2";
    public static String RESPONSE_HTTP_SESSION_EXPIRED     = "401";
    public static String RESPONSE_HTTP_METHOD_NOT_ALLOWED  = "405";
    public static int  RESPONSE_HTTP_METHOD_FORBIDDEN       = 403;
    public static int  RESPONSE_HTTP_METHOD_SESSION_EXPIRED = 503;
    public static int  RESPONSE_HTTP_METHOD_ERROR           = 504;
    public static String RESPONSE_HTTP_RECORD_EXIST        = "409";
    public static String RESPONSE_HTTP_BAD_REQUEST_5       = "5";

    public static String MIN_REQURIED_QUANTITY = "0";
    public static int MIN_REQURIED_QUANTITY_INT = 0;

    public static final int LIST_REQUEST_SIZE           =100;
    public static final int REQUEST_TYPE                = 1;
    public static final int LIST_REQUEST_SIZE_SMALL     = 4;

    public static final String  PAGE_FROM_LOGIN                   = "10";
    public static final String  PAGE_FROM_REGISTRATION            = "20";
    public static final String  PAGE_FROM_EDIT_NUMBER             = "30";
    public static final String  PAGE_FROM_OTP                     = "40";
    public static final String  PAGE_FROM_LOGIN_WITH_PASSWORD     = "50";
    public static final String  PAGE_TYPE_FORGOT_PASSWORD         = "60";
    public static final String  PAGE_TYPE_MATERIAL                = "61";
    public static final String  PAGE_TYPE_CAT                     = "70";
    public static final String  PAGE_TYPE_SUB_CAT                 = "80";
    public static final String  PAGE_TYPE_SHOP_WISH               = "81";
    public static final String  PAGE_TYPE_RENTAL_WISH             = "82";
    public static final String  PAGE_TYPE_PRODUCT_DETAILS         = "83";
    public static final String  PAGE_TYPE_REVIEW                  = "84";
    public static final String  PAGE_TYPE_TC                      = "85";
    public static final String  PAGE_TYPE_OPEN_RFP                = "86";
    public static final String  PAGE_TYPE_ACCEPTED_RFP            = "87";
    public static final String  PAGE_TYPE_MANAGE_SERVICE          = "88";
    public static final String  PAGE_TYPE_ACTIVE                  = "89";
    public static final String  PAGE_TYPE_DELIVERED               = "90";


    public static final String UPLOAD_PROFILE_PHOTO_PATH         = "GrabGoUser/user_profile";
    public static final String UPLOAD_LICENSE_PHOTO_PATH         = "GrabGoUser/insert/professional_license";
    public static final String UPLOAD_SCIENTIFIC_DOC_PHOTO_PATH  = "GrabGoUser/insert/scientific_documents";
    public static final String UPLOAD_AD_PHOTO_PATH              = "GrabGoUser/insert_ad/ad";
    public static final String UPLOAD_COMMERCIAL_REG_PHOTO_PATH  = "GrabGoUser/insert/commercial_registration";
    public static final String UPLOAD_CERTIFICATE_PHOTO_PATH     = "GrabGoUser/insert/certificates";

    public static final String UPLOAD_GALLERY_PHOTO_PATH        = "GrabGoUser/insert/gallery";
    public static final String UPLOAD_VIDEO_THUMBNAIL_PATH      = "GrabGoUser/insert_video_thumbnail";
    public static final String UPLOAD_BANNER_IMAGE_PATH         = "GrabGoUser/profile/banner";

    public static final String UPLOAD_GALLERY_PHOTO_PATH_CODE         = "4";
    public static final String UPLOAD_VIDEO_THUMBNAIL_PHOTO_PATH_CODE  = "20";
    public static final String UPLOAD_AD_IMAGE_PATH_CODE              = "12";
    public static final String UPLOAD_VIDEO_PATH_CODE                 = "12";
    public static final String UPLOAD_BANNER_IMAGE_PATH_CODE          = "15";

    public static final String UPLOAD_PROFILE_PHOTO_PATH_CODE         = "1";
    public static final String UPLOAD_LICENSE_PHOTO_PATH_CODE         = "2";
    public static final String UPLOAD_IDENTIFICATION_PHOTO_PATH_CODE  = "3";
    public static final String UPLOAD_COVER_PHOTO_PATH_CODE           = "5";
    public static final String UPLOAD_COMMERCIAL_REG_PHOTO_PATH_CODE  = "6";
    public static final String UPLOAD_SCIENTIFIC_DOC_PHOTO_PATH_CODE  = "7";
    public static final String UPLOAD_CERTIFICATE_PHOTO_PATH_CODE     = "8";


    public static String CITY_LIST_DEFAULT_CITY_ID = "1";

    public static String DEVICE_TYPE = "2";
    public static final int REQUEST_MAIN_PICK_UP_LOCATION_CODE     = 231;

    public static final String  ADDRESS_TYPE_HOME        = "Home";
    public static final String  ADDRESS_TYPE_WORK        = "Office";
    public static final String  ADDRESS_TYPE_OTHERS      = "Other";


    public static final String
            AUTHORIZATION   = "Authentication",
            CONTENT_TYPE    = "Content-Type",
            ACCEPT_LANGUAGE = "Accept-Language";


    public static String DATE_FORMAT                   ="E, d MMM,yy";
    public static String DATE_FORMAT_MONTH             ="dd-mm-yyyy";
    public static String TIME_FORMAT                   ="hh:mm a";
    public static String ENCRYPTION_TIME_FORMAT_UTC    ="yyyyMMddHH";
    public static String DATE_TIME_SERVER_FORMAT       ="yyyy-MM-dd HH:mm:ss";
    public static String DATE_MONTH_SERVER_FORMAT       ="yyyy-MM-dd";

    public static String AUTHENTICATION_DATE_FORMAT    ="yyyyMMdd";
    public static String AUTHENTICATION_TIME_FORMAT    ="HHmm";

    public final static String AUTHENTICATION_USERNAME ="Al@-marid";
    public final static String AUTHENTICATION_PASSWORD ="aL@m@rid";

    public final static String CONTENT_TYPE_VALUE      ="application/json; charset=UTF-8";


    public static final String FROM_LOGIN                   = "1";
    public static final String FROM_REGISTERATON            = "2";

    public static final String  GENDER_NONE                 = "0";
    public static final String  GENDER_MALE                 = "1";
    public static final String  GENDER_FEMALE               = "2";


    public static String SHARED_PREFERENCE_KEY                  =  "Yin-Therapist-Android-IMCRINOX@2019";
    public static String SHARED_PREFERENCE_TIME_DIFFERENCE      =  "SharedPreferenceTimeDifference";
    public static String SHARED_PREFERENCE_USERID               =  "SharedPreferenceUserID";
    public static String SHARED_PREFERENCE_PROFILE              =  "SharedPreferenceUserProfile";
    public static String SHARED_PREFERENCE_ADDRESS              =  "SharedPreferenceUserAddress";
    public static String SHARED_PREFERENCE_PROFILE_MEMBERSHIP    =  "SharedPreferenceUserProfileMembership";
    public static String SHARED_PREFERENCE_USER_TYPE            =  "SharedPreferenceUserType";
    public static String SHARED_BID_CHECKER_TIMESTAMP           =  "SharedPreferenceBidCheckerTimestamp";
    public static String SHARED_PREFERENCE_SELECTED_CITY        =  "SharedPreferenceSelectedCity";
    public static String SHARED_PREFERENCE_SELECTED_LANGUAGE    =  "SharedPreferenceSelectedLanguage";

    public static String SHARED_PREFERENCE_CATEGORY_LIST        =  "SharedPreferenceCategoryList";

    public static String SHARED_PREFERENCE_COUNTRY_MODEL        =  "SharedPreferenceTherapistCountryModel";

    public static String SHARED_PREFERENCE_BANNER               =  "SharedPreferenceBanner";
    public static String SHARED_PREFERENCE_CATEGORIES           =  "SharedPreferenceCategories";

    public static String SHARED_PREFERENCE_GCM_TOKEN            =  "SharedPreferenceGCMTOKEN";

    public static String SHARED_PREFERENCE_ACCOUNT_ID           =  "SharedPreferenceAccountId";
    public static String SHARED_PREFERENCE_LATLONG              =  "SharedPreferenceLatLong";
    public static String SHARED_PREFERENCE_CART_COUNT           =  "SharedPreferenceCartCount";
    public static String SHARED_PREFERENCE_NOTIFICATION_COUNT   =  "SharedPreferenceNotificationCount";

    public static String SHARED_PREFERENCE_TOKEN                =  "SharedPreferenceToken";
    public static String SHARED_PREFERENCE_GUID                 =  "SharedPreferenceGUID";
    public static String SHARED_PREFERENCE_COOKIE               = "SharedPreferenceCOOKIE";
    public String SHARED_PREFERENCE_NOTIFICATION_SETTINGS =  "SharedPreferenceNotificationSettings";

    public static String SHARED_PREFERENCE_TERMS_AND_CONDITIONS_SELECTED =  "SharedPreferenceTermsAndConditionsSelected";

    public static String SHARED_PREFERENCE_FRAGMENT_CREATED =  "SharedPreferenceFragmentCreated";



    public static final int REQUEST_FOR_LOGIN               = 101;
    public static final int REQUEST_FOR_CAMERA              = 222;
    public static final int REQUEST_FOR_SELECTFILE          = 223;
    public static final int REQUEST_FOR_GOOGLE              = 224;
    public static final int REQUEST_FOR_PAYMENT             = 225;
    public static final int REQUEST_FOR_FILTER_INNER        = 226;
    public static final int REQUEST_FOR_FILTER_MAIN         = 227;

    public static final int PERMISSIONS_REQUEST_CAMERA      = 151;
    public static final int PERMISSIONS_REQUEST_CALENDER    = 152;
    public static final int PERMISSIONS_REQUEST_PRIMARY     = 153;


    public static int
            TYPE_HOME_MENU              = 1,
            TYPE_QUOTATION              = 2,
            TYPE_ORDER                  = 3,
            TYPE_NOTIFICATION           = 4,
            TYPE_PROFILE                = 5,
            TYPE_SHARE                  = 6,
            TYPE_RATE                   = 7,

            TYPE_VENDOR_HOME_MENU       = 1,
            TYPE_VENDOR_ORDER           = 2,
            TYPE_VENDOR_NOTIFICATION    = 3,
            TYPE_VENDOR_PROFILE         = 4,
            TYPE_VENDOR_SHARE           = 5,
            TYPE_VENDOR_RATE            = 6;


    public static final int
            PLACE_AUTOCOMPLETE_REQUEST_CODE = 222,
            PLACE_PICKER_REQUEST            = 223,
            REQUEST_PREDICTION_CODE         = 224,
            REQUEST_LOCATION_CODE           = 225,
            REQUEST_GPS_ENABLED             = 226,
            REQUEST_COLOR_CODE              = 227,
            REQUEST_CODE_FOR_SEARCH         = 227;

    public static final int
               CAR_SERVICE_TYPE_SINGLE_CAR        = 1,
               CAR_SERVICE_TYPE_MULTIPLE_CAR      = 2,

               HOUSE_TYPE_APARTMENT               = 1,
               HOUSE_TYPE_INDEPENDENT_HOUSE       = 2;



    //Profile Photo Variables
    public static String PROFILE_PICTURE_FOLDER    = "Profile";
    public static String PROFILE_PICTURE_NAME      = "profile_picture.png";
    public static String FRAGMENT_CREATED      = "created";

    public  final String CHANNEL_ID = "RapidCarSpa_channel_01";

    public  final String
            ANALYTICS_TYPE_PRODUCT = "product",
            ANALYTICS_TYPE_OPEN = "open",
            ANALYTICS_TYPE_CLOSE = "close",
            ANALYTICS_TYPE_LOGIN = "login",
            ANALYTICS_TYPE_LOGOUT = "logout",
            ANALYTICS_TYPE_REGISTER = "register",
            ANALYTICS_TYPE_CATEGORY = "category",
            ANALYTICS_TYPE_SUBCATEGORY = "sub_category",
            ANALYTICS_TYPE_SEARCH = "search",
            ANALYTICS_TYPE_SEARCH_LIST = "search_list",
            ANALYTICS_TYPE_ADD_TO_CART = "add_to_cart",
            ANALYTICS_TYPE_REMOVE_FROM_CART = "remove_from_cart",
            ANALYTICS_TYPE_CHECKOUT_BEGIN = "checkout_begin",
            ANALYTICS_TYPE_TRANASACTION_START = "transaction_start",
            ANALYTICS_TYPE_TRANASACTION_END = "transaction_end",
            ANALYTICS_TYPE_CITY = "city",
            ANALYTICS_TYPE_COUNTRY = "country";

    public  final String
            NOTIFICATION_LOCAL_BROADCAST_KEY                    = "Brick2WallNotificationLocalBroadcastKey",
            NOTIFICATION_LOCAL_BROADCAST_NOTIFICATION_MODEL     = "Brick2WallNotificationLocalBroadcastNotificationModel" ;


    public enum LIST_TYPE {
        CURRENT("1"),
        HISTORY("2");

        private final String text;
        private LIST_TYPE(final String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

        public int toInt(){
            Log.d("Enum", this.text);
            if(this.text.equals(CURRENT.toString())){return 1;}
            else if(this.text.equals(HISTORY.toString())){return 2;}
            else{return 0;}
        }
        public LIST_TYPE toTYPE(String val){
            if(val.equalsIgnoreCase(CURRENT.toString())){
                return CURRENT;
            }else /*if(val.equalsIgnoreCase(DROP.toString()))*/{
                return HISTORY;
            }
        }
    }
}
