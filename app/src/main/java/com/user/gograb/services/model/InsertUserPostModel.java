package com.user.gograb.services.model;

import android.util.Log;

import org.json.JSONObject;

import java.io.Serializable;

public class InsertUserPostModel implements Serializable {

    private final String TAG = "InsertUserPostModel";

    private final String
            ID                  = "id",
            BOARD_ID            = "social_board_id",
            VIDEO               = "video",
            TEXT                = "text",
            TYPE                = "type";

    String
            id              = null,
            boardId         = null,
            text            = null,
            type            = null;

   ImageListModel
            imagesList = null;

    public InsertUserPostModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ImageListModel getImagesList() {
        return imagesList;
    }

    public void setImagesList(ImageListModel imagesList) {
        this.imagesList = imagesList;
    }

    public boolean toObject(String jsonObject) {
        try {
            JSONObject json = new JSONObject(jsonObject);

                if (json.has(ID)) id = json.getString(ID);
                if (json.has(BOARD_ID)) boardId = json.getString(BOARD_ID);
                if (json.has(TEXT)) text = json.getString(TEXT);
                if (json.has(TYPE)) type = json.getString(TYPE);

            return true;
        } catch (Exception ex) {
            Log.d(TAG, "Json Exception : " + ex);
        }
        return false;
    }

    @Override
    public String toString() {
        String returnString = null;
        try {
            JSONObject jsonMain = new JSONObject();
            jsonMain.put(ID, id);
            jsonMain.put(BOARD_ID, boardId);
            jsonMain.put(TEXT, text);
            jsonMain.put(TYPE, type);

            returnString = jsonMain.toString();
        } catch (Exception ex) {
            Log.d(TAG, " To String Exception : " + ex);
        }
        return returnString;
    }
}
