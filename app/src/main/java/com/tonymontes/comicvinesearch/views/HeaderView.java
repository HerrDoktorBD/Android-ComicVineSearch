
package com.tonymontes.comicvinesearch.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tonymontes.comicvinesearch.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderView extends LinearLayout {

    @BindView(R.id.header_view_title) protected TextView title;

    public HeaderView(Context context) {
        this(context, null);
    }

    public HeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        ButterKnife.bind(this);
    }

    public void bindTo(CharSequence title) {

        hideOrSetText(this.title, title);
    }

    private static void hideOrSetText(TextView tv, CharSequence text) {

        if (text == null || text.equals(""))
            tv.setVisibility(GONE);
        else
            tv.setText(text);
    }
}