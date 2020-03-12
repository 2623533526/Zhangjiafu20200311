package com.bawei.zhangjiafu20200311;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;

/**
 * @author: 张家辅
 * @date: 2020/03/11
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyHv>{
    Context context;
    List<Entity.OrderListBean> list;

    public Adapter(Context context, List<Entity.OrderListBean> list) {
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
    holder.code.setText("快递号："+list.get(position).getOrderId());
        Entity.OrderListBean orderListBean = list.get(position);
        List<Entity.OrderListBean.DetailListBean> detailList = orderListBean.getDetailList();
        Two_Adapter two_adapter = new Two_Adapter(context, detailList);
        holder.rv_layout.setLayoutManager(new StaggeredGridLayoutManager(1,RecyclerView.VERTICAL));
        holder.rv_layout.setAdapter(two_adapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHv extends RecyclerView.ViewHolder{

        private final RecyclerView rv_layout;
        private final TextView code;


        public MyHv(@NonNull View itemView) {
            super(itemView);
            rv_layout = itemView.findViewById(R.id.rv_layout);
            code = itemView.findViewById(R.id.code);
        }
    }
}
