
package com.tonymontes.comicvinesearch.items;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonymontes.comicvinesearch.R;
import com.tonymontes.comicvinesearch.items.VolumeHeaderItem.ExpandableHeaderViewHolder;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IExpandable;
import eu.davidea.flexibleadapter.items.IHeader;
import eu.davidea.viewholders.ExpandableViewHolder;

public class VolumeHeaderItem
    extends
        AbstractItem<ExpandableHeaderViewHolder>
    implements
        IExpandable<ExpandableHeaderViewHolder, VolumeCellItem>,
        IHeader<ExpandableHeaderViewHolder> {

    private boolean mExpanded = false;
    private List<VolumeCellItem> cells;
    private ExpandableHeaderViewHolder holder;

    public VolumeHeaderItem() {
        super();

        setSelectable(false);
    }

    @Override
    public boolean isExpanded() {
        return mExpanded;
    }

    @Override
    public void setExpanded(boolean expanded) {

        mExpanded = expanded;
        if (holder != null)
            holder.carat.setImageResource(isExpanded() ? R.drawable.carat_open : R.drawable.carat);
    }

    @Override
    public int getExpansionLevel() {
        return 0;
    }

    @Override
    public List<VolumeCellItem> getSubItems() {
        return cells;
    }

    public void addCell(VolumeCellItem cell) {

        if (cells == null)
            cells = new ArrayList<>();
        cells.add(cell);
    }

    @Override
    public int getSpanSize(int spanCount, int position) {
        return spanCount;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.recycler_volume_header_item;
    }

    @Override
    public ExpandableHeaderViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new ExpandableHeaderViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ExpandableHeaderViewHolder holder, int position, List payloads) {

        if (payloads.size() > 0) {
            Log.d(this.getClass().getSimpleName(), "VolumeHeaderItem Payload " + payloads + " - " + getTitle());
        } else {
            Log.d(this.getClass().getSimpleName(), "VolumeHeaderItem NoPayload - " + getTitle());
            holder.mTitle.setText(getTitle());
        }

        if (this.holder == null || !this.holder.equals(holder))
            this.holder = holder;
    }

    static class ExpandableHeaderViewHolder extends ExpandableViewHolder {

        ImageView carat;
        TextView mTitle;

        ExpandableHeaderViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter, true);

            carat = view.findViewById(R.id.image_carat);
            mTitle = view.findViewById(R.id.title);
        }

        /**
         * Allows to notify change and rebound this itemView on expanding and collapsing events,
         * in order to update the content (so, user can decide to display the current expanding status).
         * <p>This method returns always false; Override with {@code "return true"} to trigger the
         * notification.</p>
         *
         * @return true to rebound the content of this itemView on expanding and collapsing events,
         * false to ignore the events
         * @see #expandView(int)
         * @see #collapseView(int)
         * @since 5.0.0-rc1
         */
        @Override
        protected boolean shouldNotifyParentOnClick() {
            return true; // default=false
        }
    }
}