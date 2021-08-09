package com.sa.gograb.location;


import android.location.Location;

public interface LocationListener  {
    void OnLocationFetch(Location location);
    void OnProviderDisabled(String provider);
    void OnProviderEnabled(String provider);
    void OnLocationFailure(String msg);
}
