package com.example.admin.joketeller.Sync;

/**
 * Created by admin on 2/7/2017.
 */
//interface that implements method of async task
public interface RetrieveResults {
    void OnResultRetrieved(String Result);
    void OnPreRetrieving();

}
