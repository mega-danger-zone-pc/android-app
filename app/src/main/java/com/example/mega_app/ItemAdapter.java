package com.example.mega_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;

import java.text.DecimalFormat;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    enum TypeAdapter {
        TYPE_VERTICAL(0),
        TYPE_HORIZONTAL(1),
        TYPE_CARD(2);

        private final int type;

        TypeAdapter(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }
    }

    private final List<Item> itemList;
    private final Context context;
    private final OnItemClickListener listener;

    private TypeAdapter typeAdapter;

    public interface OnItemClickListener {
        void onOrderClick(Item item);
        void onBuyClick(Item item);
        void onDeleteClick(Item item);
    }

    public ItemAdapter(Context context, TypeAdapter typeAdapter, List<Item> itemList, OnItemClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
        this.typeAdapter = typeAdapter;
    }

    @Override
    public int getItemViewType(int position) {
        return typeAdapter.getType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        switch (viewType) {
            case 0: // TYPE_VERTICAL
                view = inflater.inflate(R.layout.item_vertical_main, parent, false);
                return new VerticalViewHolder(view);
            case 1: // TYPE_HORIZONTAL
                view = inflater.inflate(R.layout.item_horizontal_main, parent, false);
                return new HorizontalViewHolder(view);
            case 2: // TYPE_CARD
                view = inflater.inflate(R.layout.item_card, parent, false);
                return new CardViewHolder(view);
            default:
                throw new IllegalStateException("Unexpected value: " + viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Item item = itemList.get(position);

        if (item == null)
            return;

        if (holder instanceof VerticalViewHolder) {
            VerticalViewHolder verticalHolder = (VerticalViewHolder) holder;
            bindVerticalView(verticalHolder, item);
        } else if (holder instanceof HorizontalViewHolder) {
            HorizontalViewHolder horizontalHolder = (HorizontalViewHolder) holder;
            bindHorizontalView(horizontalHolder, item);
        } else if (holder instanceof CardViewHolder) {
            CardViewHolder cardHolder = (CardViewHolder) holder;
            bindCardView(cardHolder, item);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    private void bindVerticalView(VerticalViewHolder holder, Item item) {
        holder.title.setText(item.getName());
        holder.group.setText(item.getCategory());
        holder.description.setText(item.getDescription());
        holder.price.setText(item.getPriceText());
        Glide.with(context).load(item.getImageUrl()).error(R.drawable.error_image).into(holder.imageView);
        holder.buyButton.setOnClickListener(v -> listener.onBuyClick(item));
        //holder.orderButton.setOnClickListener(v -> listener.onOrderClick(item));

        holder.detailButton.setOnClickListener(v -> showItemDetails(item));
    }

    private void bindHorizontalView(HorizontalViewHolder holder, Item item) {
        holder.title.setText(item.getName());
        holder.price.setText(item.getPriceText());
        Glide.with(context).load(item.getImageUrl()).error(R.drawable.error_image).into(holder.imageView);
        //holder.orderButton.setOnClickListener(v -> listener.onOrderClick(item));
        holder.buyButton.setOnClickListener(v -> listener.onBuyClick(item));

        holder.detailButton.setOnClickListener(v -> showItemDetails(item));
    }

    private void bindCardView(CardViewHolder holder, Item item) {
        holder.title.setText(item.getName());
        holder.description.setText(item.getDescription());
        holder.price.setText(item.getPriceText());
        Glide.with(context).load(item.getImageUrl()).error(R.drawable.error_image).into(holder.imageView);
        holder.deleteButton.setOnClickListener(v -> listener.onDeleteClick(item));
    }

    private void showItemDetails(Item item) {
        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
        CardShowFragment fragment = CardShowFragment.newInstance(item);
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public static class VerticalViewHolder extends RecyclerView.ViewHolder {
        TextView title, group, description, price;
        ShapeableImageView imageView;
        Button buyButton, detailButton/*, orderButton*/;

        public VerticalViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            group = itemView.findViewById(R.id.textViewGroup);
            description = itemView.findViewById(R.id.textViewDesc);
            price = itemView.findViewById(R.id.textViewCeil);
            imageView = itemView.findViewById(R.id.imageView);
            buyButton = itemView.findViewById(R.id.buttonBuy);
            detailButton = itemView.findViewById(R.id.buttonDetails);
            //orderButton = itemView.findViewById(R.id.buttonOrder);
        }
    }

    public static class HorizontalViewHolder extends RecyclerView.ViewHolder {
        TextView title, price;
        ShapeableImageView imageView;
        Button buyButton, detailButton/*, orderButton*/;

        public HorizontalViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            price = itemView.findViewById(R.id.textViewCeil);
            imageView = itemView.findViewById(R.id.imageView);
            buyButton = itemView.findViewById(R.id.buttonBuy);
            detailButton = itemView.findViewById(R.id.buttonDetails);
            //orderButton = itemView.findViewById(R.id.buttonOrder);
        }
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, price;
        ShapeableImageView imageView;
        ImageButton deleteButton;

        public CardViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textViewTitle);
            description = itemView.findViewById(R.id.textViewDesc);
            price = itemView.findViewById(R.id.textViewCeil);
            imageView = itemView.findViewById(R.id.imageView);
            deleteButton = itemView.findViewById(R.id.imageButton);
        }
    }
}