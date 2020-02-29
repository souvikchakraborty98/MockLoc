package com.hfad.mockloc;

import android.content.Context;
import android.webkit.JavascriptInterface;

import static com.hfad.mockloc.MainActivity.SourceChange.CHANGE_FROM_MAP;


public class WebAppInterface {
    MainActivity mainActivity;

    WebAppInterface(Context c, MainActivity mA) {
        mainActivity = mA;
    }

    @JavascriptInterface
    public void setPosition(final String str) {

        mainActivity.runOnUiThread(new Runnable() {

            @Override
            public void run() {
                String lat = str.substring(str.indexOf('(') + 1, str.indexOf(','));
                String lng = str.substring(str.indexOf(',') + 2, str.indexOf(')'));

                MainActivity.setLatLng(lat, lng, CHANGE_FROM_MAP);
            }
        });
    }

    @JavascriptInterface
    public double getLat() {

        String lat = MainActivity.getLat();

        if (lat.isEmpty()) {
            return (0);
        } else {
            return (Double.parseDouble(lat));
        }
    }

    @JavascriptInterface
    public double getLng() {

        String lng = MainActivity.getLng();

        if (lng.isEmpty()) {
            return (0);
        } else {
            return (Double.parseDouble(lng));
        }

    }

}