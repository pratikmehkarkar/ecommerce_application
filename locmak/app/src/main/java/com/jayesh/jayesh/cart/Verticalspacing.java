package com.jayesh.jayesh.cart;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Shubham on 11/28/2017.
 */

public class Verticalspacing extends RecyclerView.ItemDecoration {

    public int space;

    public Verticalspacing(int space) {
        this.space = space;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = space;
        }
    }
}
