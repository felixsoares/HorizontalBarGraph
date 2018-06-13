package br.com.felix.horizontalbargraph;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.felix.horizontalbargraph.adapter.BarItemRecycleViewAdapter;
import br.com.felix.horizontalbargraph.interfaces.OnItemClickListener;
import br.com.felix.horizontalbargraph.model.BarItem;

/**
 * Created by user on 16/01/2018.
 */

public class HorizontalBar extends LinearLayout {

    private OnItemClickListener listener;
    private Context context;
    private List<BarItem> items;
    private boolean hasAnimation = false;
    private RecyclerView recyclerView;
    private Locale localeDefault;

    public HorizontalBar(Context context) {
        super(context);
        init(context);
    }

    public HorizontalBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public HorizontalBar(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public HorizontalBar init(Context context) {
        this.context = context;
        return this;
    }

    public HorizontalBar setListner(OnItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    public HorizontalBar init(Context context, List<BarItem> items) {
        this.context = context;
        this.addAll(items);
        notifyList();
        return this;
    }

    public HorizontalBar hasAnimation(boolean hasAnimation) {
        this.hasAnimation = hasAnimation;
        return this;
    }

    public HorizontalBar addAll(List<BarItem> items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }

        this.items.addAll(items);
        notifyList();
        return this;
    }

    public void removeAll() {
        if (this.items != null && this.items.size() > 0) {
            this.items.clear();
        }
        notifyRemove();
    }

    public void removeOne(int position) {
        if (this.items != null && this.items.size() > 0) {
            this.items.remove(position);
        }
        notifyRemove();
    }

    public HorizontalBar add(BarItem items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }

        this.items.add(items);
        notifyList();
        return this;
    }

    public HorizontalBar build(Locale locale) {
        this.recyclerView = new RecyclerView(context);
        this.recyclerView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        this.localeDefault = locale;

        if (this.hasAnimation) {
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_fall_down);
            this.recyclerView.setLayoutAnimation(animation);
        } else {
            this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        }

        this.recyclerView.setAdapter(new BarItemRecycleViewAdapter(items, listener, localeDefault));
        this.addView(this.recyclerView);
        return this;
    }

    public HorizontalBar build() {
        return build(null);
    }

    private void notifyList() {
        if (this.recyclerView != null && this.recyclerView.getAdapter() != null) {
            this.recyclerView.setAdapter(new BarItemRecycleViewAdapter(items, listener, localeDefault));
        }
    }

    private void notifyRemove() {
        if (this.recyclerView != null && this.recyclerView.getAdapter() != null) {
            this.recyclerView.getAdapter().notifyDataSetChanged();
        }
    }
}
