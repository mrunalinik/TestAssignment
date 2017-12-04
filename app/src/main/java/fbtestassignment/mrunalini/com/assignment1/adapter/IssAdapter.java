package fbtestassignment.mrunalini.com.assignment1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fbtestassignment.mrunalini.com.assignment1.R;
import fbtestassignment.mrunalini.com.assignment1.models.ResponseModel;

public class IssAdapter extends RecyclerView.Adapter<IssViewHolder>{
    private List<ResponseModel> userDetailsModels;

    public IssAdapter(List<ResponseModel> userDetailsModels){
        this.userDetailsModels = userDetailsModels;
    }


    @Override
    public IssViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user_details_layout, parent, false);
        IssViewHolder issViewHolder = new IssViewHolder(view);
        return issViewHolder;
    }

    @Override
    public void onBindViewHolder(IssViewHolder holder, int position) {
        ResponseModel responseModel = userDetailsModels.get(position);
        holder.populateItems(responseModel, position);
    }

    @Override
    public int getItemCount() {
        return userDetailsModels != null ? userDetailsModels.size() : 0;
    }


}
