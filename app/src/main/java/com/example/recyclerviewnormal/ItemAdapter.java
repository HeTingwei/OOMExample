package com.example.recyclerviewnormal;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private static final String TAG = "ItemAdapter";
    List<String> list;//存放数据
    Context context;
    List<Bitmap> bmList;
    List<ImageView> imgvList;

    public ItemAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        bmList = new ArrayList<>();
        imgvList = new ArrayList<>();
    }

    //释放bitmap
    public void release(int itemId) {
        if (itemId >= 0 && itemId < bmList.size()) {
            if (!bmList.get(itemId).isRecycled())
                bmList.get(itemId).recycle();
        }

    }

    //重新加载bitmap
    public void resetItem(int itemId) {
        Bitmap bm;
        if (itemId < bmList.size() && itemId >= 0) {
            bm = bmList.get(itemId);
            if (bm != null && !bm.isRecycled()) {
                bm.recycle();
            }
        } else return;
        bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar);
        bmList.set(itemId, bm);
        imgvList.get(itemId).setImageBitmap(bm);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //设置textView显示内容为list里的对应项
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.avatar);
        if (position >= bmList.size()) {
            bmList.add(bm);
            imgvList.add(holder.img);
        }else {
            bmList.set(position,bm);
            imgvList.set(position,holder.img);
        }
        holder.img.setImageBitmap(bm);
        holder.tv.setText(position + "");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}