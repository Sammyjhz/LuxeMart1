package com.example.luxemart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.luxemart.R;
import com.example.luxemart.Order;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private List<Order> orderList;

    public OrdersAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);

        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        Order order = orderList.get(position);

        holder.orderId.setText("Order ID: " + order.getOrderId());
        holder.orderPrice.setText("Total: $" + order.getTotalPrice());
        holder.orderStatus.setText("Status: " + order.getStatus());
        holder.orderDate.setText("Date: " + order.getDate());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView orderId;
        TextView orderPrice;
        TextView orderStatus;
        TextView orderDate;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);

            orderId = itemView.findViewById(R.id.textOrderId);
            orderPrice = itemView.findViewById(R.id.textOrderPrice);
            orderStatus = itemView.findViewById(R.id.textOrderStatus);
            orderDate = itemView.findViewById(R.id.textOrderDate);
        }
    }
}