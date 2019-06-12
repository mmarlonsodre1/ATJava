package com.example.atjava.models;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.atjava.R;

import java.util.ArrayList;
import java.util.List;

public class ListJson extends ArrayAdapter<Json> {
    private Activity context;
    private List<Json>jasonList;

    public ListJson(Activity context, List<Json> pastasList){
        super(context, R.layout.listview, pastasList);
        this.context = context;
        this.jasonList = pastasList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.listview, null, true);

        TextView txtName = (TextView) listViewItem.findViewById(R.id.txt);

        Json json = jasonList.get(position);

        txtName.setText(json.getMsg());

        return  listViewItem;
    }
}
