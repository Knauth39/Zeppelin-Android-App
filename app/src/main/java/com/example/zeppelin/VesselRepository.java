package com.example.zeppelin;

import android.content.Context;
import android.content.res.Resources;
import java.util.ArrayList;
import java.util.List;

public class VesselRepository {

    private static VesselRepository instance;
    private List<Vessel> mVessels;

    public static VesselRepository getInstance(Context context) {
        if (instance == null) {
            instance = new VesselRepository(context);
        }
        return instance;
    }

    private VesselRepository(Context context) {
        mVessels = new ArrayList<>();
        Resources res = context.getResources();
        String[] vessels = res.getStringArray(R.array.vessels);
        String[] descriptions = res.getStringArray(R.array.registration_numbers);
        for (int i = 0; i < vessels.length; i++) {
            mVessels.add(new Vessel(i + 1, vessels[i], descriptions[i]));
        }
    }

    public List<Vessel> getVessels() {
        return mVessels;
    }

    public Vessel getVessel(int VesselId) {
        for (Vessel vessel : mVessels) {
            if (vessel.getId() == vessel_id) {
                return vessel;
            }
        }
        return null;
    }
}
