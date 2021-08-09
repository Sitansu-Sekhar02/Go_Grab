package com.sa.gograb.fcm.analytics;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Size;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.sa.gograb.AppController;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.services.model.CountryModel;
import com.sa.gograb.services.model.OrderModel;
import com.sa.gograb.services.model.ProfileModel;
import com.sa.gograb.services.model.RegisterModel;
import com.sa.gograb.services.model.SearchModel;

import java.util.HashMap;
import java.util.Map;


/*import static com.brick2wall.app.brick2wall.global.GlobalVariables.CONTENT_TYPE;
import static com.google.firebase.analytics.FirebaseAnalytics.Param.ITEM_NAME;*/

public class AnalyticsReport {

    GlobalVariables globalVariables = AppController.getInstance().getGlobalVariables();
    GlobalFunctions globalFunctions = AppController.getInstance().getGlobalFunctions();

    ProfileModel profile = null;
    public AnalyticsReport() {
        //profile = globalFunctions.getProfile(null);
        //if(profile!=null)
        // Appsee.setUserId(profile.getId());
    }

    public ProfileModel getProfile() {
        return profile;
    }

    /**
     * @desc this class will hold functions for user interaction
     * examples include user_pass(), user_username(), user_age(), user_regdate()
     * @author IMCRINOX Technologies Pvt Ltd info@imcrinox.com
     * @param  detail
     * @required ProductModel
     */

    /*public void addToCart(ProductModel detail){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getBrand_title());
        bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, detail.getBrand_title());
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, detail.getCategory_title());
        bundle.putString(FirebaseAnalytics.Param.ITEM_LOCATION_ID, detail.getDescription());
        bundle.putString(FirebaseAnalytics.Param.LOCATION, detail.getName());
        if(getProfile()!=null) {
            bundle.putString(Param.USER_ID, getProfile().getId());
            bundle.putString(Param.USER_NAME, getProfile().getFullname());
        }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_ADD_TO_CART);
        addToLogQueue(FirebaseAnalytics.Event.ADD_TO_CART, bundle);


        //Appsee
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(Param.ITEM_ID, detail.getId());
        props.put(Param.ITEM_NAME, detail.getBrand_title());
        props.put(Param.ITEM_BRAND, detail.getBrand_title());
        props.put(Param.ITEM_CATEGORY, detail.getCategory_title());
        props.put(Param.ITEM_LOCATION_ID, detail.getDescription());
        props.put(Param.LOCATION, detail.getName());
        if(getProfile()!=null) {
            //Appsee.setUserId(profile.getId());
            props.put(Param.USER_ID, getProfile().getId());
            props.put(Param.USER_NAME, getProfile().getFullname());
        }
        props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_ADD_TO_CART);
        addToAppsee(globalVariables.ANALYTICS_TYPE_ADD_TO_CART, props);
    }*/

    public void orderDetaill(OrderModel detail){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
        /*bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getOrder_number());
        if(  detail.getShipping_detail()!=null && detail.getShipping_detail().getAddress()!=null)
        bundle.putString(FirebaseAnalytics.Param.LOCATION, detail.getShipping_detail().getAddress());
        if(getProfile()!=null) {
            bundle.putString(Param.USER_ID, getProfile().getId());
            bundle.putString(Param.USER_NAME, getProfile().getUser_name());
        }*/
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_ADD_TO_CART);
        addToLogQueue(FirebaseAnalytics.Event.ADD_TO_CART, bundle);


        //Appsee
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(Param.ITEM_ID, detail.getId());
      //  props.put(Param.ITEM_NAME, detail.getOrder_number());
       // props.put(Param.ITEM_LOCATION_ID, detail.getShipping_detail().getAddress());
      //  props.put(Param.LOCATION, detail.getCity_title());
        if(getProfile()!=null) {
       //     Appsee.setUserId(profile.getId());
            props.put(Param.USER_ID, getProfile().getId());
          //  props.put(Param.USER_NAME, getProfile().getUser_name());
        }
       // props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_ORDER_DETAIL);
       // addToAppsee(globalVariables.ANALYTICS_TYPE_ORDER_DETAIL, props);
    }

    public void selectCountry(CountryModel detail){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_LOCATION_ID, detail.getId());
        bundle.putString(FirebaseAnalytics.Param.LOCATION, detail.getName());
        if(getProfile()!=null) {
            bundle.putString(Param.USER_ID, getProfile().getId());
          //  bundle.putString(Param.USER_NAME, getProfile().getUser_name());
        }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_CITY);
        addToLogQueue(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //appSee
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(Param.ITEM_LOCATION_ID, detail.getId());
        props.put(Param.LOCATION, detail.getName());
        if(getProfile()!=null) {
            //Appsee.setUserId(profile.getId());
            props.put(Param.USER_ID, getProfile().getId());
           // props.put(Param.USER_NAME, getProfile().getUser_name());
        }
        props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_CITY);
        addToAppsee(globalVariables.ANALYTICS_TYPE_CITY, props);
    }

    public void login(ProfileModel detail){
        Bundle bundle = new Bundle();
     ///   bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getUser_name());
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, detail.getAgencyName());
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, detail.getPhone());
        if(getProfile()!=null) {
            bundle.putString(Param.USER_ID, getProfile().getId());
          //  bundle.putString(Param.USER_NAME, getProfile().getUser_name());
        }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_LOGIN);
        addToLogQueue(FirebaseAnalytics.Event.LOGIN, bundle);

        //Appsee
        Map<String, Object> props = new HashMap<String, Object>();
       // props.put(Param.ITEM_NAME, detail.getUser_name());
        props.put(Param.ITEM_ID, detail.getId());
        props.put(Param.ITEM_BRAND, detail.getAgencyName());
        props.put(Param.ITEM_CATEGORY, detail.getPhone());
        if(getProfile()!=null) {
           // Appsee.setUserId(profile.getId());
            props.put(Param.USER_ID, getProfile().getId());
            //props.put(Param.USER_NAME, getProfile().getUser_name());
        }
        props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_LOGIN);
        addToAppsee(globalVariables.ANALYTICS_TYPE_LOGIN, props);
    }

   public void logout(ProfileModel detail){
        Bundle bundle = new Bundle();
        profile = detail;
      //  bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getUser_name());
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, detail.getAgencyName());
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, detail.getPhone());
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_LOGOUT);
        addToLogQueue(FirebaseAnalytics.Event.LOGIN, bundle);

        //APPSee
       Map<String, Object> props = new HashMap<String, Object>();
     //  props.put(Param.ITEM_NAME, detail.getUser_name());
       props.put(Param.ITEM_ID, detail.getId());
       props.put(Param.ITEM_BRAND, detail.getAgencyName());
       props.put(Param.ITEM_CATEGORY, detail.getPhone());
       props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_LOGOUT);
       addToAppsee(globalVariables.ANALYTICS_TYPE_LOGOUT, props);
    }


    public void search(SearchModel detail){
        Bundle bundle = new Bundle();
       /* bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getTitle());
        bundle.putString(FirebaseAnalytics.Param.SEARCH_TERM, detail.getTitle());
        bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, detail.getBrandType().toString());
        bundle.putString(FirebaseAnalytics.Param.LOCATION, detail.getCityID());
        if(getProfile()!=null) {
            bundle.putString(Param.USER_ID, getProfile().getId());
            bundle.putString(Param.USER_NAME, getProfile().getUser_name());
        }*/
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_SEARCH);
        addToLogQueue(FirebaseAnalytics.Event.SEARCH, bundle);

        //Appsee
        Map<String, Object> props = new HashMap<String, Object>();
        /*props.put(Param.ITEM_ID, detail.getId());
        props.put(Param.ITEM_NAME, detail.getTitle());
        props.put(Param.SEARCH_TERM, detail.getTitle());
        props.put(Param.ITEM_BRAND, detail.getBrandType().toString());
        props.put(Param.LOCATION, detail.getCityID());
        if(getProfile()!=null) {
            Appsee.setUserId(profile.getId());
            props.put(Param.USER_ID, getProfile().getId());
            props.put(Param.USER_NAME, getProfile().getUser_name());
        }*/
        props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_SEARCH);
        addToAppsee(globalVariables.ANALYTICS_TYPE_SEARCH, props);
    }

    public void searchList(SearchModel detail){
        Bundle bundle = new Bundle();
        /*bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getTitle());
        bundle.putString(FirebaseAnalytics.Param.SEARCH_TERM, detail.getTitle());
        bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, detail.getBrandType().toString());
        bundle.putString(FirebaseAnalytics.Param.LOCATION, detail.getCityID());
        if(getProfile()!=null) {
            bundle.putString(Param.USER_ID, getProfile().getId());
            bundle.putString(Param.USER_NAME, getProfile().getUser_name());
        }*/
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_SEARCH_LIST);
        addToLogQueue(FirebaseAnalytics.Event.SEARCH, bundle);

        //Appsee
        Map<String, Object> props = new HashMap<String, Object>();
       /* props.put(Param.ITEM_ID, detail.getId());
        props.put(Param.ITEM_ID, detail.getId());
        props.put(Param.ITEM_NAME, detail.getTitle());
        props.put(Param.SEARCH_TERM, detail.getTitle());
        props.put(Param.ITEM_BRAND, detail.getBrandType().toString());
        props.put(Param.LOCATION, detail.getCityID());
        if(getProfile()!=null) {
            Appsee.setUserId(profile.getId());
            props.put(Param.USER_ID, getProfile().getId());
            props.put(Param.USER_NAME, getProfile().getUser_name());
        }*/
        props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_SEARCH_LIST);
        addToAppsee(globalVariables.ANALYTICS_TYPE_SEARCH_LIST, props);
    }

    public void openApp(ProfileModel detail){
        Bundle bundle = new Bundle();
        if(detail!=null) {
          ///  bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getUser_name());
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
            bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, detail.getAgencyName());
            bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, detail.getPhone());
        }

        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_OPEN);
        addToLogQueue(FirebaseAnalytics.Event.APP_OPEN, bundle);

        //APPsee
        Map<String, Object> props = new HashMap<String, Object>();
        if(detail!=null) {
            //Appsee.setUserId(detail.getId());
            props.put(Param.ITEM_ID, detail.getId());
           // props.put(Param.ITEM_NAME, detail.getUser_name());
            props.put(Param.ITEM_BRAND, detail.getAgencyName());
            props.put(Param.ITEM_CATEGORY, detail.getPhone());
        }

        props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_OPEN);
        addToAppsee(globalVariables.ANALYTICS_TYPE_OPEN, props);
    }

    public void closeApp(ProfileModel detail){
        Bundle bundle = new Bundle();
        if(detail!=null) {
           // bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getUser_name());
            bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
            bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, detail.getAgencyName());
            bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, detail.getPhone());
        }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_CLOSE);
        addToLogQueue(FirebaseAnalytics.Event.APP_OPEN, bundle);

        //Appsee
        Map<String, Object> props = new HashMap<String, Object>();
        if(detail!=null) {
            //Appsee.setUserId(detail.getId());
            props.put(Param.ITEM_ID, detail.getId());
            //props.put(Param.ITEM_NAME, detail.getUser_name());
            props.put(Param.ITEM_BRAND, detail.getAgencyName());
            props.put(Param.ITEM_CATEGORY, detail.getPhone());
        }
        props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_CLOSE);
        addToAppsee(globalVariables.ANALYTICS_TYPE_CLOSE, props);

    }

    /*public void checkout(CartModel detail){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.CREATIVE_NAME, detail.getUserID());
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
        bundle.putString(FirebaseAnalytics.Param.LOCATION, detail.getCityID());
        bundle.putString(FirebaseAnalytics.Param.COUPON, detail.getCouponCode());
        bundle.putString(FirebaseAnalytics.Param.VALUE, detail.getGrandTotal()+"");
        bundle.putString(FirebaseAnalytics.Param.SHIPPING, detail.getShippingCharge()+"");
        bundle.putString(FirebaseAnalytics.Param.TAX, detail.getGst()+"");
        bundle.putString(FirebaseAnalytics.Param.PRICE, detail.getTotalPrice()+"");
        if(getProfile()!=null) {
            bundle.putString(Param.USER_ID, getProfile().getId());
          //  bundle.putString(Param.USER_NAME, getProfile().getUser_name());
        }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_CHECKOUT_BEGIN);
        addToLogQueue(FirebaseAnalytics.Event.BEGIN_CHECKOUT, bundle);

        //Appsee
        Map<String, Object> props = new HashMap<String, Object>();
        if (detail != null) {
            props.put(Param.ITEM_ID, detail.getId());
            props.put(Param.CREATIVE_NAME, detail.getUserID());
            props.put(Param.ITEM_ID, detail.getId());
            props.put(Param.LOCATION, detail.getCityID());
            props.put(Param.COUPON, detail.getCouponCode());
            props.put(Param.VALUE, detail.getGrandTotal() + "");
            props.put(Param.SHIPPING, detail.getShippingCharge() + "");
            props.put(Param.TAX, detail.getGst() + "");
            props.put(Param.PRICE, detail.getTotalPrice() + "");
            if (getProfile() != null) {
               // Appsee.setUserId(profile.getId());
                props.put(Param.USER_ID, getProfile().getId());
              //  props.put(Param.USER_NAME, getProfile().getUser_name());
            }
            props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_CHECKOUT_BEGIN);
            addToAppsee( globalVariables.ANALYTICS_TYPE_CHECKOUT_BEGIN, props);
        }*/

   /*public void beginPayment(OrderModel detail, TransactionModel transactionModel){
        Bundle bundle = new Bundle();
       // bundle.putString(FirebaseAnalytics.Param.CREATIVE_NAME, detail.getProfile().getUser_name());
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());

       String productTitles = null;
       String productIDS = null;
       *//*for (OrderProductModel model:detail.getOrderList()) {
           productIDS = productIDS==null?model.getProduct_id():productIDS+", "+model.getProduct_id();
           productTitles = productTitles==null?model.getProduct_title():productTitles+", "+model.getProduct_title();
       }*//*
       bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, productTitles);
       bundle.putString(FirebaseAnalytics.Param.ITEM_LIST, productIDS);

       *//* bundle.putString(FirebaseAnalytics.Param.LOCATION, detail.getCity_title().getTitle());
        bundle.putString(FirebaseAnalytics.Param.COUPON, detail.getCoupon_code());
        bundle.putString(FirebaseAnalytics.Param.VALUE, detail.getOrder_total()+"");
        bundle.putString(FirebaseAnalytics.Param.SHIPPING, detail.getShipping_charges()+"");
        bundle.putString(FirebaseAnalytics.Param.PRICE, detail.getPayment_total()+"");
        bundle.putString(FirebaseAnalytics.Param.TAX, detail.getTotal_gst()+"");*//*
        if(transactionModel!=null)bundle.putString(FirebaseAnalytics.Param.TRANSACTION_ID, transactionModel.getId());
      //  bundle.putString(FirebaseAnalytics.Param.CHECKOUT_OPTION, detail.getPayment_type()+"");
       if(getProfile()!=null) {
           bundle.putString(Param.USER_ID, getProfile().getId());
          // bundle.putString(Param.USER_NAME, getProfile().getUser_name());
       }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_TRANASACTION_START);
        addToLogQueue(FirebaseAnalytics.Event.ECOMMERCE_PURCHASE, bundle);

        //Appsee
       Map<String, Object> props = new HashMap<String, Object>();
     //  props.put(Param.CREATIVE_NAME, detail.getProfile().getUser_name());
       props.put(Param.ITEM_ID, detail.getId());

      *//* for (OrderProductModel model:detail.getOrderList()) {
           productIDS = productIDS==null?model.getProduct_id():productIDS+", "+model.getProduct_id();
           productTitles = productTitles==null?model.getProduct_title():productTitles+", "+model.getProduct_title();
       }
       props.put(Param.ITEM_NAME, productTitles);
       props.put(Param.ITEM_LIST, productIDS);

       props.put(Param.LOCATION, detail.getCity_title().getTitle());
       props.put(Param.COUPON, detail.getCoupon_code());
       props.put(Param.VALUE, detail.getOrder_total()+"");
       props.put(Param.SHIPPING, detail.getShipping_charges()+"");
       props.put(Param.PRICE, detail.getPayment_total()+"");
       props.put(Param.TAX, detail.getTotal_gst()+"");
       if(transactionModel!=null)props.put(Param.TRANSACTION_ID, transactionModel.getId());
       props.put(Param.CHECKOUT_OPTION, detail.getPayment_type()+"");
       if(getProfile()!=null) {
           Appsee.setUserId(profile.getId());
           props.put(Param.USER_ID, getProfile().getId());
           props.put(Param.USER_NAME, getProfile().getUser_name());
       }*//*
       props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_TRANASACTION_START);
       addToAppsee(globalVariables.ANALYTICS_TYPE_TRANASACTION_START, props);
     }*/

   /*public void endPayment(OrderModel detail, TransactionModel transactionModel, boolean isSuccess){
        Bundle bundle = new Bundle();
     //  bundle.putString(FirebaseAnalytics.Param.CREATIVE_NAME, detail.getProfile().getUser_name());
       bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());

       String productTitles = null;
       String productIDS = null;
      *//* for (OrderProductModel model:detail.getOrderList()) {
           productIDS = productIDS==null?model.getProduct_id():productIDS+", "+model.getProduct_id();
           productTitles = productTitles==null?model.getProduct_title():productTitles+", "+model.getProduct_title();
       }
       bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, productTitles);
       bundle.putString(FirebaseAnalytics.Param.ITEM_LIST, productIDS);

       bundle.putString(FirebaseAnalytics.Param.LOCATION, detail.getCity_title().getTitle());
       bundle.putString(FirebaseAnalytics.Param.COUPON, detail.getCoupon_code());
       bundle.putString(FirebaseAnalytics.Param.VALUE, detail.getOrder_total()+"");
       bundle.putString(FirebaseAnalytics.Param.SHIPPING, detail.getShipping_charges()+"");
       bundle.putString(FirebaseAnalytics.Param.PRICE, detail.getPayment_total()+"");
       bundle.putString(FirebaseAnalytics.Param.TAX, detail.getTotal_gst()+"");
       if(transactionModel!=null)bundle.putString(FirebaseAnalytics.Param.TRANSACTION_ID, transactionModel.getId());
       bundle.putString(FirebaseAnalytics.Param.CHECKOUT_OPTION, detail.getPayment_type()+"");*//*
       bundle.putString(FirebaseAnalytics.Param.DESTINATION, isSuccess?"SUCCESS":"FAILURE");
       if(getProfile()!=null) {
           bundle.putString(Param.USER_ID, getProfile().getId());
          // bundle.putString(Param.USER_NAME, getProfile().getUser_name());
       }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_TRANASACTION_END);
        addToLogQueue(FirebaseAnalytics.Event.ECOMMERCE_PURCHASE, bundle);

        *//*Appsee*//*
       Map<String, Object> props = new HashMap<String, Object>();
     //  props.put(Param.CREATIVE_NAME, detail.getProfile().getUser_name());
       props.put(Param.ITEM_ID, detail.getId());

     *//*  for (OrderProductModel model:detail.getOrderList()) {
           productIDS = productIDS==null?model.getProduct_id():productIDS+", "+model.getProduct_id();
           productTitles = productTitles==null?model.getProduct_title():productTitles+", "+model.getProduct_title();
       }
       props.put(Param.ITEM_NAME, productTitles);
       props.put(Param.ITEM_LIST, productIDS);

       props.put(Param.LOCATION, detail.getCity_title().getTitle());
       props.put(Param.COUPON, detail.getCoupon_code());
       props.put(Param.VALUE, detail.getOrder_total()+"");
       props.put(Param.SHIPPING, detail.getShipping_charges()+"");
       props.put(Param.PRICE, detail.getPayment_total()+"");
       props.put(Param.TAX, detail.getTotal_gst()+"");
       if(transactionModel!=null) props.put(Param.TRANSACTION_ID, transactionModel.getId());
       props.put(Param.CHECKOUT_OPTION, detail.getPayment_type()+"");
       props.put(Param.DESTINATION, isSuccess?"SUCCESS":"FAILURE");
       if(getProfile()!=null) {
           Appsee.setUserId(profile.getId());
           props.put(Param.USER_ID, getProfile().getId());
           props.put(Param.USER_NAME, getProfile().getUser_name());
       }*//*
       props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_TRANASACTION_END);
       addToAppsee(globalVariables.ANALYTICS_TYPE_TRANASACTION_END, props);

    }*/

   public void register(RegisterModel detail){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getFullName());
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getEmailId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, detail.getSystemInfo());
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, detail.getMobileNumber());
       if(getProfile()!=null) {
           bundle.putString(Param.USER_ID, getProfile().getId());
       //    bundle.putString(Param.USER_NAME, getProfile().getUser_name());
       }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_REGISTER);
        addToLogQueue(FirebaseAnalytics.Event.SIGN_UP, bundle);

        //APpsee
       Map<String, Object> props = new HashMap<String, Object>();
       props.put(Param.ITEM_NAME, detail.getFullName());
       props.put(Param.ITEM_ID, detail.getEmailId());
       props.put(Param.ITEM_BRAND, detail.getSystemInfo());
       props.put(Param.ITEM_CATEGORY, detail.getMobileNumber());
       if(getProfile()!=null) {
           props.put(Param.USER_ID, getProfile().getId());
        //   props.put(Param.USER_NAME, getProfile().getUser_name());
       }
       props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_REGISTER);
       addToAppsee(Event.SIGN_UP, props);
    }


    /*public void deleteFromCart(ProductModel detail){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getName());
        //bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, detail.getBrand_id());
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, detail.getId());
        //bundle.putString(FirebaseAnalytics.Param.ITEM_LOCATION_ID, detail.getP());
        if(getProfile()!=null) {
            bundle.putString(Param.USER_ID, getProfile().getId());
           // bundle.putString(Param.USER_NAME, getProfile().getUser_name());
        }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_REMOVE_FROM_CART);
        addToLogQueue(FirebaseAnalytics.Event.REMOVE_FROM_CART, bundle);

        //APPSee
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(Param.ITEM_ID, detail.getId());
        props.put(Param.ITEM_NAME, detail.getName());
        //bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, detail.getBrand_id());
        props.put(Param.ITEM_CATEGORY, detail.getId());
        //bundle.putString(FirebaseAnalytics.Param.ITEM_LOCATION_ID, detail.getP());
        if(getProfile()!=null) {
           // Appsee.setUserId(profile.getId());
            props.put(Param.USER_ID, getProfile().getId());
            //props.put(Param.USER_NAME, getProfile().getUser_name());
        }
        props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_REMOVE_FROM_CART);
        addToAppsee(globalVariables.ANALYTICS_TYPE_REMOVE_FROM_CART, props);
    }*/

    /*public void productView(ProductModel detail){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getName());
        bundle.putString(FirebaseAnalytics.Param.ITEM_BRAND, detail.getBrand_title());
        bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, detail.getCategory_title());
        bundle.putString(FirebaseAnalytics.Param.ITEM_LOCATION_ID, detail.getPrice()+"");
        bundle.putString(FirebaseAnalytics.Param.LOCATION, detail.getQuantity()+"");
        if(getProfile()!=null) {
            bundle.putString(Param.USER_ID, getProfile().getId());
           // bundle.putString(Param.USER_NAME, getProfile().getUser_name());
        }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_PRODUCT);
        addToLogQueue(FirebaseAnalytics.Event.VIEW_ITEM, bundle);

        //Appsee
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(Param.ITEM_ID, detail.getId());
        props.put(Param.ITEM_NAME, detail.getName());
        props.put(Param.ITEM_BRAND, detail.getBrand_title());
        props.put(Param.ITEM_CATEGORY, detail.getCategory_title());
        props.put(Param.ITEM_LOCATION_ID, detail.getPrice()+"");
        props.put(Param.LOCATION, detail.getQuantity()+"");
        if(getProfile()!=null) {
           // Appsee.setUserId(profile.getId());
            props.put(Param.USER_ID, getProfile().getId());
          //  props.put(Param.USER_NAME, getProfile().getUser_name());
        }
        props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_PRODUCT);
        addToAppsee( globalVariables.ANALYTICS_TYPE_PRODUCT, props);
    }*/

    /*public void categoryList(CategoryModel detail){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getTitle());
        if(getProfile()!=null) {
            bundle.putString(Param.USER_ID, getProfile().getId());
            bundle.putString(Param.USER_NAME, getProfile().getUser_name());
        }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_CATEGORY);
        addToLogQueue(FirebaseAnalytics.Event.VIEW_ITEM_LIST, bundle);

        //Appsee
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(Param.ITEM_ID, detail.getId());
        props.put(Param.ITEM_NAME, detail.getTitle());
        if(getProfile()!=null) {
            Appsee.setUserId(profile.getId());
            props.put(Param.USER_ID, getProfile().getId());
            props.put(Param.USER_NAME, getProfile().getUser_name());
        }
        props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_CATEGORY);
        addToAppsee( globalVariables.ANALYTICS_TYPE_CATEGORY, props);
    }*/

    /*public void subCategoryList(SubCategoryModel detail){
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, detail.getId());
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, detail.getTitle());
        if(getProfile()!=null) {
            bundle.putString(Param.USER_ID, getProfile().getId());
          //  bundle.putString(Param.USER_NAME, getProfile().getUser_name());
        }
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_SUBCATEGORY);
        addToLogQueue(FirebaseAnalytics.Event.VIEW_ITEM_LIST, bundle);

        //Appsee
        Map<String, Object> props = new HashMap<String, Object>();
        props.put(Param.ITEM_ID, detail.getId());
        props.put(Param.ITEM_NAME, detail.getTitle());
        if(getProfile()!=null) {
         //   Appsee.setUserId(profile.getId());
            props.put(Param.USER_ID, getProfile().getId());
         //   props.put(Param.USER_NAME, getProfile().getUser_name());
        }
        props.put(Param.CONTENT_TYPE, globalVariables.ANALYTICS_TYPE_SUBCATEGORY);
        addToAppsee(globalVariables.ANALYTICS_TYPE_SUBCATEGORY, props);
    }*/

    private void addToLogQueue(@NonNull @Size(min = 1L,max = 40L) String eventName, Bundle req) {
        AppController.getInstance().addToAnalyticsLog(eventName, req);
    }

    private void addToAppsee(@NonNull @Size(min = 1L,max = 40L) String eventName, Map<String, Object> req) {
       // AppController.getInstance().addAppseeEvents(eventName, req);
    }

    public static class Param {
        public static final String USER_NAME = "user_name";
        public static final String USER_ID = "user_id";
        public static final String ACHIEVEMENT_ID = "achievement_id";
        public static final String CHARACTER = "character";
        public static final String TRAVEL_CLASS = "travel_class";
        public static final String CONTENT_TYPE = "content_type";
        public static final String CURRENCY = "currency";
        public static final String COUPON = "coupon";
        public static final String START_DATE = "start_date";
        public static final String END_DATE = "end_date";
        public static final String FLIGHT_NUMBER = "flight_number";
        public static final String GROUP_ID = "group_id";
        public static final String ITEM_CATEGORY = "item_category";
        public static final String ITEM_ID = "item_id";
        public static final String ITEM_LOCATION_ID = "item_location_id";
        public static final String ITEM_NAME = "item_name";
        public static final String LOCATION = "location";
        public static final String LEVEL = "level";
        public static final String SIGN_UP_METHOD = "sign_up_method";
        public static final String NUMBER_OF_NIGHTS = "number_of_nights";
        public static final String NUMBER_OF_PASSENGERS = "number_of_passengers";
        public static final String NUMBER_OF_ROOMS = "number_of_rooms";
        public static final String DESTINATION = "destination";
        public static final String ORIGIN = "origin";
        public static final String PRICE = "price";
        public static final String QUANTITY = "quantity";
        public static final String SCORE = "score";
        public static final String SHIPPING = "shipping";
        public static final String TRANSACTION_ID = "transaction_id";
        public static final String SEARCH_TERM = "search_term";
        public static final String TAX = "tax";
        public static final String VALUE = "value";
        public static final String VIRTUAL_CURRENCY_NAME = "virtual_currency_name";
        public static final String CAMPAIGN = "campaign";
        public static final String SOURCE = "source";
        public static final String MEDIUM = "medium";
        public static final String TERM = "term";
        public static final String CONTENT = "content";
        public static final String ACLID = "aclid";
        public static final String CP1 = "cp1";
        public static final String ITEM_BRAND = "item_brand";
        public static final String ITEM_VARIANT = "item_variant";
        public static final String ITEM_LIST = "item_list";
        public static final String CHECKOUT_STEP = "checkout_step";
        public static final String CHECKOUT_OPTION = "checkout_option";
        public static final String CREATIVE_NAME = "creative_name";
        public static final String CREATIVE_SLOT = "creative_slot";
        public static final String AFFILIATION = "affiliation";
        public static final String INDEX = "index";

        protected Param() {
        }
    }

    public static class Event {
        public static final String ADD_PAYMENT_INFO = "add_payment_info";
        public static final String ADD_TO_CART = "add_to_cart";
        public static final String ADD_TO_WISHLIST = "add_to_wishlist";
        public static final String APP_OPEN = "app_open";
        public static final String BEGIN_CHECKOUT = "begin_checkout";
        public static final String CAMPAIGN_DETAILS = "campaign_details";
        public static final String ECOMMERCE_PURCHASE = "ecommerce_purchase";
        public static final String GENERATE_LEAD = "generate_lead";
        public static final String JOIN_GROUP = "join_group";
        public static final String LEVEL_UP = "level_up";
        public static final String LOGIN = "login";
        public static final String POST_SCORE = "post_score";
        public static final String PRESENT_OFFER = "present_offer";
        public static final String PURCHASE_REFUND = "purchase_refund";
        public static final String SEARCH = "search";
        public static final String SELECT_CONTENT = "select_content";
        public static final String SHARE = "share";
        public static final String SIGN_UP = "sign_up";
        public static final String SPEND_VIRTUAL_CURRENCY = "spend_virtual_currency";
        public static final String TUTORIAL_BEGIN = "tutorial_begin";
        public static final String TUTORIAL_COMPLETE = "tutorial_complete";
        public static final String UNLOCK_ACHIEVEMENT = "unlock_achievement";
        public static final String VIEW_ITEM = "view_item";
        public static final String VIEW_ITEM_LIST = "view_item_list";
        public static final String VIEW_SEARCH_RESULTS = "view_search_results";
        public static final String EARN_VIRTUAL_CURRENCY = "earn_virtual_currency";
        public static final String REMOVE_FROM_CART = "remove_from_cart";
        public static final String CHECKOUT_PROGRESS = "checkout_progress";
        public static final String SET_CHECKOUT_OPTION = "set_checkout_option";

        protected Event() {
        }
    }
}
