package com.fawadkhanse.mvvmdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fawadkhanse.mvvmdemo.R;
import com.fawadkhanse.mvvmdemo.databinding.NicePlaceRowBinding;
import com.fawadkhanse.mvvmdemo.models.NicePlace;

import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {
    Context mContext;
    List<NicePlace> mPlaceList;

    public PlaceAdapter(Context context, List<NicePlace> placeList) {
        mContext = context;
        mPlaceList = placeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NicePlaceRowBinding nicePlaceRowBinding = NicePlaceRowBinding.inflate ( LayoutInflater.from ( parent.getContext () ), parent, false );
        return new ViewHolder ( nicePlaceRowBinding );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //NicePlace nicePlace = mPlaceList.get ( position );
        Glide.with ( mContext ).load ( mPlaceList.get ( position ).getImageUrl () ).into ( holder.mNicePlaceRowBinding.plcaeImg );
        holder.mNicePlaceRowBinding.placeName.setText ( mPlaceList.get ( position ).getTitle () );

    }

    @Override
    public int getItemCount() {
        return mPlaceList.size ();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {
        NicePlaceRowBinding mNicePlaceRowBinding;

        public ViewHolder(@NonNull NicePlaceRowBinding nicePlaceRowBinding) {
            super ( nicePlaceRowBinding.getRoot () );
            mNicePlaceRowBinding = nicePlaceRowBinding;
        }
    }

}
