package com.example.firebase_upload_and_download_images;

public class Model {

    private String imageUrl;
    private String key;
    private String storageKey;

    public String getKey() {
        return key;
    }

    public String getStorageKey() {
        return storageKey;
    }

    public void setStorageKey(String storageKey) {
        this.storageKey = storageKey;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public Model(){

    }
    public Model(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
