package com.example.marisanity.ghibliapp;

import android.support.multidex.MultiDexApplication;

import com.example.marisanity.ghibliapp.service.GhibliApiService;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Marisanity on 8/28/2017.
 */

public class GhibliApplication extends MultiDexApplication {

    private static GhibliApplication singleton;
    private GhibliApiService service;

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;
        service = new GhibliApiService();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }

    public static GhibliApplication getInstance() {
        return singleton;
    }

    public GhibliApiService getService() {
        return service;
    }

}
