package com.bawei.listview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yekh on 2016/11/1.
 */

public class MyAdapter extends BaseAdapter {

    private List<Data> list;
    private List<Data> listAll = new ArrayList<>();
    private Context mContext;
    private boolean isHide;

    public MyAdapter(List<Data> list, Context context,boolean isHide) {
        this.list = list;
        listAll.addAll(list);
        mContext = context;
        this.isHide = isHide;
    }

    public void setHide(boolean isHide){
        this.isHide = isHide;
    }

    @Override
    public void notifyDataSetChanged() {
        list.clear();
        list.addAll(listAll);

        if(isHide){
            List<Data> listDel = new ArrayList<>();
            for(Data data:list){
                if(data.isCheck)
                    listDel.add(data);
            }

            for(Data data:listDel)
                list.remove(data);

        }
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if(convertView==null){
            convertView = View.inflate(mContext,R.layout.lv_item,null);
            holder =new ViewHolder();
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            holder.content = (TextView) convertView.findViewById(R.id.content);

            holder.create = (TextView) convertView.findViewById(R.id.create);
            holder.index = (TextView) convertView.findViewById(R.id.index);

            holder.create.setText(position+"");

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }
        Data data = list.get(position);

        holder.content.setText(data.text);
        holder.index.setText(data.index+"");

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(data.isCheck);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("check","check--->"+position+"/"+isChecked);
                list.get(position).isCheck = isChecked;
                notifyDataSetChanged();
            }
        });

        return convertView;
    }



    class ViewHolder {
        CheckBox checkBox;
        TextView content;

        TextView create;
        TextView index;
    }
}
