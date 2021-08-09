package com.sa.gograb.upload;

public interface UploadListener {
    public void OnSuccess(String fileName,String type, String uploadingFile);
    public void OnFailure();
}
