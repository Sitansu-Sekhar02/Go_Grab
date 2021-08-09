package com.sa.gograb.services;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.sa.gograb.AppController;
import com.sa.gograb.R;
import com.sa.gograb.global.GlobalFunctions;
import com.sa.gograb.global.GlobalVariables;
import com.sa.gograb.services.model.StatusModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;


public class VolleyServices implements Response.Listener<JSONObject>,
        Response.ErrorListener {

    private final String TAG = "VolleyServices";
    private final int mSocketTimeout = 120000;// 30 seconds
    private RetryPolicy mRetryPolicy;

    private Map<String, String> mParams;
    private String jsonString;
    private ResposeCallBack mCallBack;

    private String cookie;
    private Context context;

    public VolleyServices(Context context) {
        // defines max try and max timeout
        this.context = context;
        mRetryPolicy = new DefaultRetryPolicy(mSocketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    }

    /**
     *
     * @param callBack
     *            callback for responce
     */
    public void setCallbacks(ResposeCallBack callBack) {
        mCallBack = callBack;
    }

    /**
     *
     * @param params
     *            body of the HTTP request
     */
    public void setBody(Map<String, String> params) {
        mParams = params;
    }

    public void setBody(String jsonObject) {
        jsonString = jsonObject;
    }

    private void makeJsonObjectGetRequest(String url, String tag) {
    }

    private void makeJsonArrayGetRequest(String tag) {
    }

    /**
     *
     * @param url
     *            URL
     * @param tag
     *            as to identify the request in queue
     *
     *
     *            need to be called after calling setBody and setCallbacks
     */
    public void makeJsonPostRequest(@NonNull final Context context, final String url, final String query, String tag) {
        String finalUrl = url;
        if(query!=null){if(!query.equalsIgnoreCase("")){finalUrl += "?"+query;}}
        Log.d(TAG, "Final URL (POST) : "+finalUrl);
        JSONObject object = new JSONObject();
        if(mParams!=null){
            object =  new JSONObject(mParams);
        }else{
            try {
                object =  new JSONObject(jsonString);
            } catch (JSONException e) {
                object = new JSONObject();
            }
        }
        Log.d(TAG, object.toString());
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.POST, finalUrl,
                object, this, this){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return GlobalFunctions.postHeader(context, url);
            }

            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                Map headers = response.headers;
                String cookie = (String)headers.get("Set-Cookie");
                setCookie(cookie);

                return super.parseNetworkResponse(response);
            }

            @Override
            public String getBodyContentType() {
                return GlobalVariables.CONTENT_TYPE_VALUE;
            }

        };
        addToqueue(jsonObjReq, tag);
    }

    public void makeJsonGETRequest(@NonNull final Context context, @NonNull final String url, @Nullable String query, @Nullable String tag) {
        String finalUrl = url;
        if(query!=null){if(!query.equalsIgnoreCase("")){finalUrl += "?"+query;}}
        Log.d(TAG, "Final URL (GET) : "+finalUrl);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Method.GET, finalUrl,
                null, this, this){

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return GlobalFunctions.getHeader(context, url);
            }


        };
        addToqueue(jsonObjReq, tag);
    }


    @Override
    public void onResponse(JSONObject arg0) {
        // TODO Auto-generated method stub
       /* try {
            String jsonString = new String(arg0.toString().getBytes("UTF-8"), "UTF-8");
            arg0 = new JSONObject(jsonString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        mCallBack.OnSuccess(arg0);
    }


    @Override
    public void onErrorResponse(VolleyError error) {

        // TODO Auto-generated method stub

        NetworkResponse networkResponse = error.networkResponse;
        if(networkResponse!=null){
            if(networkResponse.statusCode == GlobalVariables.RESPONSE_HTTP_METHOD_FORBIDDEN){
                mCallBack.OnFailure(R.string.AuthFailedError);
            }else if(networkResponse.statusCode == GlobalVariables.RESPONSE_HTTP_METHOD_SESSION_EXPIRED){
                GlobalFunctions.logoutApplication(context,true);
            }else  if(networkResponse.statusCode == GlobalVariables.RESPONSE_HTTP_METHOD_ERROR){
                if(networkResponse.data!=null){
                    String msg = new String(networkResponse.data);
                    Log.d(TAG, "Error Status : "+msg);
                    StatusModel statusModel = new StatusModel();
                    if(statusModel.toObject(msg)){
                        if(!statusModel.isStatus()){
                            GlobalFunctions.logoutApplication(context,true);
                        }
                        mCallBack.OnFailure(statusModel.getMessage());
                    }else{
                        mCallBack.OnFailure(R.string.ServerInternalError);
                    }
                }else{
                    mCallBack.OnFailure(R.string.ServerInternalError);
                }

            }else{
                mCallBack.OnFailure(R.string.UnexpectedResponse);
            }
        }else if (error instanceof NetworkError) {
            mCallBack.OnFailure(R.string.NetworkError);
        } else if (error instanceof ServerError) {
            mCallBack.OnFailure(R.string.ServerError);
        } else if (error instanceof AuthFailureError) {
            mCallBack.OnFailure(R.string.AuthFailedError);
        } else if (error instanceof ParseError) {
            mCallBack.OnFailure(R.string.ParseError);
        } else if (error instanceof NoConnectionError) {
            mCallBack.OnFailure(R.string.NoConnectionError);
        } else if (error instanceof TimeoutError) {
            mCallBack.OnFailure(R.string.TimeOutError);
        }else {
            mCallBack.OnFailure(R.string.UnknownError);
        }
    }

    private void addToqueue(JsonObjectRequest jsonObjReq, String tag) {

        jsonObjReq.setRetryPolicy(mRetryPolicy);
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag);

    }


    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    /**
     *
     * @author Spurthi
     *
     *         interface to get the calllback
     */
    public interface ResposeCallBack {

        /**
         *
         * @param arg0
         *            json object from http call
         */
        public void OnSuccess(JSONObject arg0);

        /**
         *
         * @param cause
         *            R.String.Cause
         */
        public void OnFailure(int cause);
        public void OnFailure(String cause);
    }

}