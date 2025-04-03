package com.example.mega_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends Fragment {

    private RecyclerView recyclerViewOrdered;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_order, container, false);

        recyclerViewOrdered = rootView.findViewById(R.id.RecyclerViewOrdered);
        recyclerViewOrdered.setLayoutManager(new LinearLayoutManager(getContext()));

        List<Item> hotItemList = new ArrayList<>();
        hotItemList.add(new Item("Сборка Ахмед",  "Игровая", "Игровая сборка с мощной видеокартой.", 119999.0, "https://andpro.ru/upload/iblock/bc4/Computer_assembly.jpg"));
        hotItemList.add(new Item("Сборка Разрыв", "Рабочая", "Для работы и игр. Современный процессор.", 89999.0, "https://i.ytimg.com/vi/09lEqOMbITI/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLB0cgJCFy1yexfW4y5_VT2ndfujpQ"));
        hotItemList.add(new Item("Сборка Бюджет", "Бюджетная", "Бюджетная сборка для повседневных задач.", 39999.0, "https://img.ixbt.site/live/topics/preview/00/00/67/03/a42edaa2e9.jpg"));

        recyclerViewOrdered.setHasFixedSize(true);
        recyclerViewOrdered.setVerticalScrollBarEnabled(false);
        recyclerViewOrdered.setHorizontalScrollBarEnabled(false);

        recyclerViewOrdered.setAdapter(new ItemAdapter(getContext(), ItemAdapter.TypeAdapter.TYPE_CARD, hotItemList, new ItemAdapter.OnItemClickListener() {
            @Override
            public void onOrderClick(Item item) {
                // Логика для кнопки "Order"
            }

            @Override
            public void onBuyClick(Item item) {
                // Логика для кнопки "Buy"
            }

            @Override
            public void onDeleteClick(Item item) {
                // Логика для кнопки "Delete"
            }
        }));

        return rootView;
    }
}