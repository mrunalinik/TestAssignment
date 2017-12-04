package fbtestassignment.mrunalini.com.assignment1.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import fbtestassignment.mrunalini.com.assignment1.R;
import fbtestassignment.mrunalini.com.assignment1.models.ResponseModel;

public class UserRvAdapter extends RecyclerView.Adapter<UserViewHolder>{
    private List<ResponseModel> userDetailsModels;

    public UserRvAdapter(List<ResponseModel> userDetailsModels){
        this.userDetailsModels = userDetailsModels;
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user_details_layout, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);
        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        ResponseModel responseModel = userDetailsModels.get(position);
        holder.populateItems(responseModel, position);
    }

    @Override
    public int getItemCount() {
        return userDetailsModels != null ? userDetailsModels.size() : 0;
    }


}
