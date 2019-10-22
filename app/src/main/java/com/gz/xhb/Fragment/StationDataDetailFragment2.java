package com.gz.xhb.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gz.xhb.MVP.Model.Entity.Map;
import com.gz.xhb.MVP.Model.Entity.MapStationDataDetail;
import com.gz.xhb.R;

import java.util.List;

/**
 * Created by zdj on 2019/10/21.
 */
public class StationDataDetailFragment2 extends android.support.v4.app.Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_station_data_detail2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            List<MapStationDataDetail> mapStationDataDetails=  bundle.getParcelableArrayList("data");
        }
    }
}
