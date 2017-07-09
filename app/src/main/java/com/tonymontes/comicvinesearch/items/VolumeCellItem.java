
package com.tonymontes.comicvinesearch.items;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tonymontes.comicvinesearch.R;
import com.tonymontes.comicvinesearch.utils.Utils;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IHeader;
import eu.davidea.flexibleadapter.items.ISectionable;
import eu.davidea.viewholders.FlexibleViewHolder;

public class VolumeCellItem
    extends
        AbstractItem<VolumeCellItem.ChildViewHolder>
    implements
        ISectionable<VolumeCellItem.ChildViewHolder, IHeader> {

    private IHeader header;

    private String coverImageView;
    private String bookCountLabel;
    private String comicvineID;
    private String issueSearchTitle;

    @Override
    public IHeader getHeader() {
        return header;
    }

    @Override
    public void setHeader(IHeader header) {
        this.header = header;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.recycler_volume_cell_item;
    }

    @Override
    public ChildViewHolder createViewHolder(View view, FlexibleAdapter adapter) {
        return new ChildViewHolder(view, adapter);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ChildViewHolder holder, int position, List payload) {

        if (payload.size() > 0)
            return;

        Context context = holder.context;

        holder.comicvineID = getComicvineID();
        holder.issueSearchTitle = getIssueSearchTitle();

        Utils.setImageForItem(context, getCoverImageView(), holder.coverImageView);

        int clColor = ContextCompat.getColor(context, R.color.colorComicland);

        // text data
        holder.headerLabel.setText(getTitle());
        holder.bookCountLabel.setText(getBookCountLabel());
        holder.viewOnComicvineLabel.setText(R.string.view_on_cv);

        // status icon
        holder.statusImageView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.uipreferences_check));
        holder.statusImageView.setColorFilter(clColor);

        // worldView icon
        holder.worldView.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.global_portrait));
        holder.worldView.setColorFilter(clColor);
    }

    static final class ChildViewHolder extends FlexibleViewHolder {

        private String comicvineID;
        private String issueSearchTitle;
        private Context context;

        private ImageView coverImageView;
        private TextView headerLabel;
        private TextView bookCountLabel;
        private TextView viewOnComicvineLabel;
        private ImageView worldView;
        private ImageView statusImageView;

        ChildViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);

            context = view.getContext();

            this.coverImageView = view.findViewById(R.id.coverImageView);
            this.headerLabel = view.findViewById(R.id.title);
            this.bookCountLabel = view.findViewById(R.id.bookCountLabel);
            this.viewOnComicvineLabel = view.findViewById(R.id.viewOnComicvineLabel);
            this.worldView = view.findViewById(R.id.worldView);
            this.statusImageView = view.findViewById(R.id.statusImageView);
        }

        @Override
        public float getActivationElevation() {
            return Utils.dpToPx(itemView.getContext(), 4f);
        }
    }

    private String getCoverImageView() {
        return coverImageView;
    }

    public void setCoverImageView(String coverImageView) {
        this.coverImageView = coverImageView;
    }

    public String getIssueSearchTitle() {
        return issueSearchTitle;
    }

    public void setIssueSearchTitle(String issueSearchTitle) {
        this.issueSearchTitle = issueSearchTitle;
    }

    private String getBookCountLabel() {
        return bookCountLabel;
    }

    public void setBookCountLabel(String bookCountLabel) {
        this.bookCountLabel = bookCountLabel;
    }

    public String getComicvineID() {
        return comicvineID;
    }

    public void setComicvineID(String comicvineID) {
        this.comicvineID = comicvineID;
    }
}