package com.project.garbagecollectoruser.Callback;

import com.project.garbagecollectoruser.Model.DriverGeoModel;

public interface IFirebaseDriverInfoListener {
    void OnDriverInfoLoadSuccess(DriverGeoModel driverGeoModel);
}
