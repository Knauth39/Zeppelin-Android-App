package com.example.zeppelin;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class VesselDetailFragment extends Fragment {
    public static final String ARG_VESSEL_ID = "vessel_id";
    private Vessel mVessel;

    public VesselDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int vesselId = 1;

        // Get the vessel ID from the fragment arguments
        Bundle args = getArguments();
        if (args != null) {
            vesselId = args.getInt(ARG_VESSEL_ID);
        }

        // Get the selected vessel
        mVessel = VesselRepository.getInstance(requireContext()).getVessel(vesselId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vessel_detail, container, false);

        if (mVessel != null) {
            TextView nameTextView = rootView.findViewById(R.id.vessel_name);
            nameTextView.setText(mVessel.getName());

            TextView descriptionTextView = rootView.findViewById(R.id.vessel_description);
            descriptionTextView.setText(mVessel.getDescription());
        }

        return rootView;
    }
}



/*
public class VesselDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VesselDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VesselDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
/*
    public static VesselDetailFragment newInstance(String param1, String param2) {
        VesselDetailFragment fragment = new VesselDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_vessel_detail, container, false);
    }
}
*/