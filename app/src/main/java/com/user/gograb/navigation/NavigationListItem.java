package com.user.gograb.navigation;

public class NavigationListItem {
    private String title;
    private String categoryID;
    private int imageID;

    public NavigationListItem(String categoryID, String name, int imageID) {
        this.categoryID = categoryID;
        this.title = name;
        this.imageID = imageID;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }



}
