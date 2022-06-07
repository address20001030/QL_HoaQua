package com.example.ql_hoaqua;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class HQAdapter extends ArrayAdapter<HoaQua> {
    private Context context;
    private int resource;
    private List<HoaQua> listHQ;



    public HQAdapter(Context context, int resource,List<HoaQua> objects) {
        super(context, resource,objects);
        this.context = context;
        this.resource = resource;
        this.listHQ = objects;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodel viewHodel;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view,parent,false);
            viewHodel = new ViewHodel();
            viewHodel.tv_id = (TextView) convertView.findViewById(R.id.TV_ID);
            viewHodel.tv_name = (TextView) convertView.findViewById(R.id.TV_NAME);
            viewHodel.tv_loai = (TextView) convertView.findViewById(R.id.TV_LHQ);
            viewHodel.tv_dvt = (TextView) convertView.findViewById(R.id.TV_DVT);
            viewHodel.tv_dg = (TextView) convertView.findViewById(R.id.TV_DG);
            viewHodel.tv_nsx = (TextView) convertView.findViewById(R.id.TV_NXX);

            convertView.setTag(viewHodel);
        }else{
            viewHodel = (ViewHodel) convertView.getTag();
        }
        HoaQua hoaQua = listHQ.get(position);
        viewHodel.tv_id.setText(String.valueOf(hoaQua.getId()));
        viewHodel.tv_name.setText(hoaQua.getName());
        viewHodel.tv_loai.setText(hoaQua.getLoai());
        viewHodel.tv_dvt.setText(hoaQua.getDvt());
        viewHodel.tv_dg.setText(String.valueOf(hoaQua.getDongia()));
        viewHodel.tv_nsx.setText(hoaQua.getNsx());

        return convertView;

    }


    class ViewHodel{
        private TextView tv_id,tv_name,tv_loai,tv_dvt,tv_dg,tv_nsx;
    }
}
