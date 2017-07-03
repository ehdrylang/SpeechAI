package com.example.ambidext.speechai;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by PUREUM on 2017-07-01.
 */

public class MyLocationService {
    public static String getAddress(Context context) {
        String address = "현재의 위치를 알 수 없습니다.";
        Location location = getLocation(context);
        if (location == null) return address;

        Geocoder geocoder = new Geocoder(context, Locale.KOREA);
        try {
            List<Address> addrs = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1);
            if (addrs != null && addrs.size() > 0)
                address = addrs.get(0).getAddressLine(0).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }

    public static String getLocationStr(Context context) {
        Location location = getLocation(context);
        if (location == null) return "";
        return "위도=" + location.getLatitude() + "," + "경도=" + location.getLongitude();
    }

    private static Location getLocation(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        try {

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) return location;

            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, listener);
            return locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }
}

