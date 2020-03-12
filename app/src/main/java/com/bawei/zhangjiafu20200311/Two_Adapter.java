package com.bawei.zhangjiafu20200311;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * @author: 张家辅
 * @date: 2020/03/11
 */
public class Two_Adapter extends RecyclerView.Adapter<Two_Adapter.MyHv>{
    Context context;
    List<Entity.OrderListBean.DetailListBean>list;

    public Two_Adapter(Context context, List<Entity.OrderListBean.DetailListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyHv onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.myfragment_layout, null);
        MyHv myHv = new MyHv(inflate);
        return myHv;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHv holder, int position) {
    holder.two_title.setText(list.get(position).getCommodityName());
        holder.two_pri.setText(""+list.get(position).getCommodityPrice());
        String commodityPic = list.get(position).getCommodityPic();
        String[] split = commodityPic.split(",");
        Glide.with(context).load(split[0]).into(holder.two_image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHv extends RecyclerView.ViewHolder{

        private final ImageView two_image;
        private final TextView two_title;
        private final TextView two_pri;

        public MyHv(@NonNull View itemView) {
            super(itemView);
            two_image = itemView.findViewById(R.id.two_image);
            two_title = itemView.findViewById(R.id.two_title);
            two_pri = itemView.findViewById(R.id.two_pri);
        }
    }
}
