package com.example.huynhvinh.applazada_java.view.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huynhvinh.applazada_java.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentNoiBat extends Fragment {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_noibat,container,false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyleNoiBat);

        List<String> list = new ArrayList<String>();
        list.add("dsf");
        list.add("dsf"); list.add("dsf");
        list.add("dsf");
        list.add("dsf");
        list.add("dsf");
        list.add("dsf");





        return view;
    }
}
