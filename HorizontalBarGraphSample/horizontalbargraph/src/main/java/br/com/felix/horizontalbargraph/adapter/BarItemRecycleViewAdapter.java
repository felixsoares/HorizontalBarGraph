package br.com.felix.horizontalbargraph.adapter;

import android.animation.ValueAnimator;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;
import java.util.Locale;

import br.com.felix.horizontalbargraph.R;
import br.com.felix.horizontalbargraph.interfaces.OnItemClickListener;
import br.com.felix.horizontalbargraph.model.BarItem;

/**
 * Created by user on 12/01/2018.
 */

public class BarItemRecycleViewAdapter extends RecyclerView.Adapter<BarItemRecycleViewAdapter.ItemViewHolder> {

    private Double biggerValue = 0.0;
    private List<BarItem> items;
    private OnItemClickListener listener;
    private Locale locale;
    public int size;

    public BarItemRecycleViewAdapter(List<BarItem> items, OnItemClickListener listener, Locale locale) {
        this.items = items;
        this.listener = listener;
        this.locale = locale;
        getBiggerValue(items);
        size =Math.min(items.size(), 3);
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

        holder.txtValue1.setText(viewModel.getValue1().intValue() + "");
        chanceViewParam(holder.txtValue1, viewModel.getTextColorBar1());

        double percent = getPercent(viewModel.getValue1());
        changWidthBar(percent, holder.linearValue1, holder.linearValue1Margin);
        chanceViewParam(holder.linearValue1, viewModel.getColorBar1());

        if (viewModel.getValue2() != null && viewModel.getValue2() >= 0) {
            holder.txtValue2.setText(viewModel.getValue2().intValue() + "");
            chanceViewParam(holder.txtValue2, viewModel.getTextColorBar2());

            percent = getPercent(viewModel.getValue2());
            changWidthBar(percent, holder.linearValue2, holder.linearValue2Margin);
            chanceViewParam(holder.linearValue2, viewModel.getColorBar2());
        } else {
            holder.llValur1Root.setVisibility(View.VISIBLE);
            holder.llValur2Root.setVisibility(View.GONE);
        }

          if (holder.linearValue1 != null &&viewModel.getRadius()!=0 ) {
            GradientDrawable shape = new GradientDrawable();
            shape.setCornerRadius(viewModel.getRadius());
            shape.setColor(viewModel.getColorBar1());
            (holder.linearValue1).setBackground(shape);
        }

        if (holder.linearValue2 != null &&viewModel.getRadius()!=0 ) {
            GradientDrawable shape = new GradientDrawable();
            shape.setCornerRadius(viewModel.getRadius());
            shape.setColor(viewModel.getColorBar2());
            (holder.linearValue2).setBackground(shape);
        }
    }

    private void chanceViewParam(View view, int color) {
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(color);
        }
    }

    private void changWidthBar(double percent, LinearLayout layout1, LinearLayout layout2) {
        LinearLayout.LayoutParams params1, params2;

        if (percent == 0) {
            params1 = new LinearLayout.LayoutParams(
                    0,
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
                    (float) percent
            );

            params2 = new LinearLayout.LayoutParams(
                    0,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    (float) (value - percent)
            );
        }

        ValueAnimator m1 = ValueAnimator.ofFloat(0.0f, (float) percent); //fromWeight, toWeight
        m1.setDuration(400);
        m1.setStartDelay(100); //Optional Delay
        m1.setInterpolator(new LinearInterpolator());
        m1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                ((LinearLayout.LayoutParams) layout1.getLayoutParams()).weight = (float) animation.getAnimatedValue();
                layout1.requestLayout();
            }

        });
        m1.start();


        layout1.setLayoutParams(params1);
        layout2.setLayoutParams(params2);
    }

    private double getPercent(Double value) {
        Double percent = (value / biggerValue);
        Double percentmAux = (value / biggerValue) * 100;

//        if (percentmAux.intValue() < 2) {
//            percent = 0D;
//        } else {
//            percent = percent < 1 ? percent * 10 : percent;
//        }
        percent = percent < 1 ? percent * 10 : percent;
        return percent;
    }

    @Override
    public int getItemCount() {
        return size;
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