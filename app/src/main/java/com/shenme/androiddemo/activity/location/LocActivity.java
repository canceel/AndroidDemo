package com.shenme.androiddemo.activity.location;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.shenme.androiddemo.R;
import com.shenme.androiddemo.activity.location.method.LocationHelper;
import com.shenme.androiddemo.utils.SharedPreUtils;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.mapsdk.raster.model.CameraPosition;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.tencentmap.mapsdk.map.CameraUpdate;
import com.tencent.tencentmap.mapsdk.map.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

/**
 * Created by CANC on 2016/9/9.
 */
public class LocActivity extends Activity implements View.OnClickListener {

    private Context mContext;
    private MapView mapView;
    private CameraUpdate cameraSigma;
    private TencentMap tencentMap;
    private Button addButton, subButton, myLocation;
    private LatLng mlatLng;
    private LocationHelper mlocationHelper;
    private int zoomNun = 16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);
        mContext = this;
        mlocationHelper = new LocationHelper(this);
        initView();
        setView();
        getMyLocation();
    }


    private void initView() {
        mapView = (MapView) findViewById(R.id.map_view);
        addButton = (Button) findViewById(R.id.add);
        subButton = (Button) findViewById(R.id.sub);
        myLocation = (Button) findViewById(R.id.my_loc);
        addButton.setOnClickListener(this);
        subButton.setOnClickListener(this);
        myLocation.setOnClickListener(this);
    }

    private void setView() {
        tencentMap = mapView.getMap();
        String lat = SharedPreUtils.getString(mContext, "lat");
        String lng = SharedPreUtils.getString(mContext, "lng");
        if (TextUtils.isEmpty(lat)) {
            cameraSigma = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(26.033282, 119.242577), zoomNun));
        } else {
            cameraSigma = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(Double.valueOf(lat), Double.valueOf(lng)), zoomNun));
        }
        tencentMap.moveCamera(cameraSigma);
    }

    private void getMyLocation() {
        //上次定位地址不为空
        if (mlocationHelper.getLastLocation() != null) {
            Loca(mlocationHelper.getLastLocation());
        } else if (mlocationHelper.isStarted()) {
            //开始定位
        } else {
            mlocationHelper.start(new Runnable() {
                @Override
                public void run() {
                    Loca(mlocationHelper.getLastLocation());
                }
            });
        }
    }

    private void Loca(TencentLocation location) {
        if (location == null) {
            return;
        }
        mlatLng = new LatLng(location.getLatitude(), location.getLongitude());
        SharedPreUtils.putString(mContext, "lat", String.valueOf(location.getLatitude()));
        SharedPreUtils.putString(mContext, "lng", String.valueOf(location.getLongitude()));
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.add:
                if (zoomNun < tencentMap.getMaxZoomLevel()) {
                    zoomNun = tencentMap.getZoomLevel() + 1;
                    tencentMap.setZoom(zoomNun);
                }
                break;
            case R.id.sub:
                if (zoomNun > tencentMap.getMinZoomLevel()) {
                    zoomNun = tencentMap.getZoomLevel() - 1;
                    tencentMap.setZoom(zoomNun);
                }
                break;
            case R.id.my_loc:
                getMyLocation();
                break;
            default:
                break;
        }
    }
}
