package br.com.felix.horizontalbargraph.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import br.com.felix.horizontalbargraph.R;
import br.com.felix.horizontalbargraph.interfaces.OnItemClickListener;
import br.com.felix.horizontalbargraph.model.BarItem;
import br.com.felix.horizontalbargraph.util.Util;

/**
 * Created by user on 12/01/2018.
 */

public class BarItemRecycleViewAdapter extends RecyclerView.Adapter<BarItemRecycleViewAdapter.ItemViewHolder> {

    private Double biggerValue = 0.0;
    private List<BarItem> items;
    private OnItemClickListener listener;

    public BarItemRecycleViewAdapter(List<BarItem> items, OnItemClickListener listener) {
        this.items = items;
        this.listener = listener;
        getBiggerValue(items);
    }

    private void getBiggerValue(List<BarItem> items) {
        for (BarItem item : items) {
            if (item.getValue1() > biggerValue) {
                this.biggerValue = item.getValue1();
            }

            if (item.getValue2() != null && item.getValue2() > biggerValue) {
                this.biggerValue = item.getValue2();
            }
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_item_balanco, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        BarItem viewModel = getItem(position);
        holder.txtDesciption.setText(viewModel.getDescription());

        holder.txtValue1.setText(Util.formatMoney(viewModel.getValue1()));
        chanceViewParam(holder.txtValue1, viewModel.getTextColorBar1());

        int percent = getPercent(viewModel.getValue1());
        changWidthBar(percent, holder.linearValue1, holder.linearValue1Margin);
        chanceViewParam(holder.linearValue1, viewModel.getColorBar1());

        if (viewModel.getValue2() != null && viewModel.getValue2() >= 0) {
            holder.txtValue2.setText(Util.formatMoney(viewModel.getValue2()));
            chanceViewParam(holder.txtValue2, viewModel.getTextColorBar2());

            percent = getPercent(viewModel.getValue2());
            changWidthBar(percent, holder.linearValue2, holder.linearValue2Margin);
            chanceViewParam(holder.linearValue2, viewModel.getColorBar2());
        } else {
            holder.llValur1Root.setVisibility(View.VISIBLE);
            holder.llValur2Root.setVisibility(View.GONE);
        }
    }

    private void chanceViewParam(View view, int color) {
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(color);
        } else if (view instanceof LinearLayout) {
            (view).setBackgroundColor(color);
        }
    }

    private void changWidthBar(int percent, LinearLayout layout1, LinearLayout layout2) {
        LinearLayout.LayoutParams params1 = null, params2 = null;

        if (percent == 0) {
            params1 = new LinearLayout.LayoutParams(
                    20,
                    ViewGroup.LayoutParams.MATCH_PARENT
            );

            params2 = new LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    1
            );
        } else {
            int value = percent == 1 ? 1 : 10;

            params1 = new LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    percent
            );

            params2 = new LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    value - percent
            );
        }

        layout1.setLayoutParams(params1);
        layout2.setLayoutParams(params2);
    }

    private int getPercent(Double value) {
        Double percent = (value / biggerValue);
        Double percentmAux = (value / biggerValue) * 100;

        if (percentmAux.intValue() < 20) {
            percent = 0D;
        } else {
            percent = percent < 1 ? percent * 10 : percent;
        }

        return percent.intValue();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public BarItem getItem(int position) {
        return items.get(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView txtDesciption, txtValue1, txtValue2;
        public LinearLayout linearValue1, linearValue1Margin, linearValue2Margin, linearValue2, llRoot;
        public LinearLayout llValur1Root, llValur2Root;

        public ItemViewHolder(View view) {
            super(view);
            txtDesciption = view.findViewById(R.id.txtMes);
            txtValue1 = view.findViewById(R.id.txtValorDespesa);
            txtValue2 = view.findViewById(R.id.txtValorReceita);

            linearValue1Margin = view.findViewById(R.id.linearDespesaMargin);
            linearValue1 = view.findViewById(R.id.linearDespesa);

            linearValue2Margin = view.findViewById(R.id.linearReceitaMargin);
            linearValue2 = view.findViewById(R.id.linearReceita);

            llValur1Root = view.findViewById(R.id.llValur1Root);
            llValur2Root = view.findViewById(R.id.llValur2Root);

            llRoot = view.findViewById(R.id.llRoot);
            llRoot.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onItemClick(items.get(getAdapterPosition()));
            }
        }
    }
}