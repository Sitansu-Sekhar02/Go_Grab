package com.sauthi.gograb.upload;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.amazonaws.event.ProgressEvent;
import com.amazonaws.event.ProgressListener;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sauthi.gograb.global.GlobalVariables;

import java.util.UUID;


public class UploadImage implements ProgressListener {
    public static final String TAG = "UploadImage";
    Context context;
    String uploadFileName;
    Uri imageUri;
    UploadListener listener;
    String type = "";
    String utlFormat = "";
    String uploadImagePath = "";
    String uploadingFile = "";
    UploadTask uploadTask;

    FirebaseStorage storage;
    StorageReference storageReference;

    public UploadImage(Context context) {
        this.context = context;
        uploadTask = new UploadTask();
        if (storage == null) {
            initializeFirebaseStorage();
        }
    }

    private void initializeFirebaseStorage() {
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }


    public void startUploading(Uri imageUri, String type, String uploadingFile, UploadListener listener) {
        this.imageUri = imageUri;
        this.listener = listener;
        this.type = type;
        this.uploadingFile = uploadingFile;
        uploadTask.execute();
    }

    public void startUploadingWithPrefix(Uri imageUri, String type, String utlFormat, String uploadingFile, UploadListener listener) {
        this.imageUri = imageUri;
        this.listener = listener;
        this.type = type;
        this.utlFormat = utlFormat;
        this.uploadingFile = uploadingFile;
        uploadTask.execute();
    }

    private void uploadSingleImage() {
        if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_PROFILE_PHOTO_PATH_CODE)) {
            uploadImagePath = GlobalVariables.UPLOAD_PROFILE_PHOTO_PATH;
        } else if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_BANNER_IMAGE_PATH_CODE)) {
            uploadImagePath = GlobalVariables.UPLOAD_BANNER_IMAGE_PATH;
        } else if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_VIDEO_THUMBNAIL_PHOTO_PATH_CODE)) {
            uploadImagePath = GlobalVariables.UPLOAD_VIDEO_THUMBNAIL_PATH;
        }else if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_IDENTIFICATION_PHOTO_PATH_CODE)) {
            uploadImagePath = GlobalVariables.UPLOAD_AD_PHOTO_PATH;
        } else if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_AD_IMAGE_PATH_CODE)) {
            uploadImagePath = GlobalVariables.UPLOAD_AD_PHOTO_PATH;
        } else if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_CERTIFICATE_PHOTO_PATH_CODE)) {
            uploadImagePath = GlobalVariables.UPLOAD_CERTIFICATE_PHOTO_PATH;
        } else if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_COVER_PHOTO_PATH_CODE)) {
            uploadImagePath = GlobalVariables.UPLOAD_GALLERY_PHOTO_PATH;
        } else if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_LICENSE_PHOTO_PATH_CODE)) {
            uploadImagePath = GlobalVariables.UPLOAD_LICENSE_PHOTO_PATH;
        } else if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_SCIENTIFIC_DOC_PHOTO_PATH_CODE)) {
            uploadImagePath = GlobalVariables.UPLOAD_SCIENTIFIC_DOC_PHOTO_PATH;
        } else if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_COMMERCIAL_REG_PHOTO_PATH_CODE)) {
            uploadImagePath = GlobalVariables.UPLOAD_COMMERCIAL_REG_PHOTO_PATH;
        } else if (type.equalsIgnoreCase(GlobalVariables.UPLOAD_GALLERY_PHOTO_PATH_CODE)) {
            uploadImagePath = GlobalVariables.UPLOAD_GALLERY_PHOTO_PATH;
        } else {
            uploadImagePath = GlobalVariables.UPLOAD_PROFILE_PHOTO_PATH;
        }
        final StorageReference ref = storageReference.child(uploadImagePath + "/" + UUID.randomUUID().toString()+utlFormat);
        ref.putFile(imageUri).continueWithTask(new Continuation<com.google.firebase.storage.UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<com.google.firebase.storage.UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUrl = task.getResult();
                    Log.d(TAG, "onComplete: Url: " + downloadUrl.toString());
                    uploadFileName = downloadUrl.toString();
                    setListener(true, uploadFileName, uploadingFile);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                setListener(false, null, uploadingFile);
            }
        });

    }

    private void setListener(boolean isSuccess, String filePath, String uploadingFile) {
        if (listener != null) {
            if (isSuccess) {
                listener.OnSuccess(filePath, type, uploadingFile);
            } else {
                listener.OnFailure();
            }
        }
    }

    /*public void cancelUpload(){

    }*/

    @Override
    public void progressChanged(ProgressEvent progressEvent) {
        if (progressEvent.getEventCode() == ProgressEvent.FAILED_EVENT_CODE || progressEvent.getEventCode() == ProgressEvent.CANCELED_EVENT_CODE) {
            setListener(false, null, uploadingFile);
        } else if (progressEvent.getEventCode() == ProgressEvent.COMPLETED_EVENT_CODE) {
            setListener(true, uploadFileName, uploadingFile);
        }
    }

    class UploadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            uploadSingleImage();
            return null;
        }

        protected void onPostExecute(Void params) {
            // TODO: check this.exception
            // TODO: do something with the feed

        }
    }
}
