package com.appspot.mycookooapp.api;

/**
 * Created by ugurgul on 30/12/2016.
 */

public class ResponseHandler {

    private String myData;
    private String kind;
    private String etag;

    public ResponseHandler() {
    }

    public ResponseHandler(String myData, String kind, String etag) {
        this.myData = myData;
        this.kind = kind;
        this.etag = etag;
    }

    public String getMyData() {
        return myData;
    }

    public void setMyData(String myData) {
        this.myData = myData;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
