package com.deepway.navigator.component.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.baidu.mapapi.map.MapView;
import com.deepway.navigator.R;

public class MapContainer extends FrameLayout implements LifecycleObserver {

    private MapView mapView;

    public MapContainer(Context context) {
        this(context, null);
    }

    public MapContainer(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MapContainer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_map_container, this);
        mapView = findViewById(R.id.mapView);
//
//        MapStatus.Builder builder = new MapStatus.Builder();
//        builder.zoom(18.0f);
//        mapView.getMap().setMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onMapResume() {
        mapView.onResume();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onMapPause() {
        mapView.onPause();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onMapDestroy() {
        mapView.onDestroy();
    }
}
