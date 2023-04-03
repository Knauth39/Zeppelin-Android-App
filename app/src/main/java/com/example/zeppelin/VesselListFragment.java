package com.example.zeppelin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/*  The ListFragment class loads the layout file that defines the LinearLayout.
    The list of vessels are obtained from VesselRepository, and buttons are created for each vessel
    and added to the LinearLayout.
    The buttons all share the same click listener. When any of the buttons are clicked,
    the click listener gets the button's NavController and calls navigate() to navigate to DetailFragment.
*/

public class ListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vessel_list, container, false);

        // Create recycler view with a linear layout
        RecyclerView recyclerView = view.findViewById(R.id.vessel_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Send vessels to RecyclerView with an adapter
        VesselAdapter adapter = new VesselAdapter(VesselRepository.getInstance(getContext()).getVessels());
        recyclerView.setAdapter(adapter);

        return view;
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
        public VesselHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new VesselHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(VesselHolder holder, int position) {
            Vessel vessel = mVessels.get(position);
            holder.bind(vessel);
            holder.itemView.setTag(vessel.getId());
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mVessels.size();
        }
    }

    private static class VesselHolder extends RecyclerView.ViewHolder {

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