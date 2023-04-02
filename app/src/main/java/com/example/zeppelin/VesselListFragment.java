package com.example.zeppelin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import java.util.List;

/*  The ListFragment class loads the layout file that defines the LinearLayout.
    The list of bands are obtained from BandRepository, and buttons are created for each band
    and added to the LinearLayout.
    The buttons all share the same click listener. When any of the buttons are clicked,
    the click listener gets the button's NavController and calls navigate() to navigate to DetailFragment.
*/

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vessel_list, container, false);

        // Click listener for the RecyclerView
        View.OnClickListener onClickListener = itemView -> {

            // Create fragment arguments containing the selected band ID
            int selectedVesselId = (int) itemView.getTag();
            Bundle args = new Bundle();
            args.putInt(VesselDetailFragment.ARG_VESSEL_ID, selectedVesselId);

            View detailFragmentContainer = rootView.findViewById(R.id.detail_frag_container);
            if (detailFragmentContainer == null) {
                // Replace list with details
                Navigation.findNavController(detailFragmentContainer).navigate(R.id.show_item_detail, args);
            } else {
                // Show details on the right
                Navigation.findNavController(detailFragmentContainer).navigate(R.id.fragment_detail, args);
            }
        };

        // Send bands to RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.band_list);
        List<Vessel> vessels = VesselRepository.getInstance(requireContext()).getVessels();
        recyclerView.setAdapter(new VesselAdapter(vessels, onClickListener));

        return rootView;
    }

    private class VesselAdapter extends RecyclerView.Adapter<VesselHolder> {

        private final List<Vessel> mVessels;
        private final View.OnClickListener mOnClickListener;

        public VesselAdapter(List<Vessel> vessels, View.OnClickListener onClickListener) {
            mVessels = vessels;
            mOnClickListener = onClickListener;
        }

        @NonNull
        @Override
        public BandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new VesselHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(VesselHolder holder, int position) {
            Vessel band = mVessels.get(position);
            holder.bind(vessel);
            holder.itemView.setTag(vessel.getId());
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mVessels.size();
        }
    }

    private static class BandHolder extends RecyclerView.ViewHolder {

        private final TextView mNameTextView;

        public VesselHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_vessel, parent, false));
            mNameTextView = itemView.findViewById(R.id.vessel_name);
        }

        public void bind(Vessel vessel) {
            mNameTextView.setText(vessel.getName());
        }
    }
}


/*
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VesselListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VesselListFragment.
     */
/*
    // TODO: Rename and change types and number of parameters
    public static VesselListFragment newInstance(String param1, String param2) {
        VesselListFragment fragment = new VesselListFragment();
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
        return inflater.inflate(R.layout.fragment_vessel_list, container, false);
    }
}
*/