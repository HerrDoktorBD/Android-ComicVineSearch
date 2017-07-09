
package com.tonymontes.comicvinesearch.adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tonymontes.comicvine.Issue;
import com.tonymontes.comicvinesearch.R;
import com.tonymontes.comicvinesearch.utils.Utils;

import java.util.List;

public class SearchIssuesAdapter
    extends
        RecyclerView.Adapter<SearchIssuesAdapter.MyViewHolder> {

    private Context context;
    private List<Issue> issues;
    private IssueCellListener listener;
    private SparseBooleanArray selectedItems;
    private String[] covers;

    class MyViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout issueContainer;
        private ImageView coverImageView;
        private TextView headerLabel;
        private TextView plotLabel;
        private ImageView iconOwned;

        private RelativeLayout iconCheck;

        MyViewHolder(View view) {
            super(view);

            issueContainer = view.findViewById(R.id.comicvine_issue_container);
            coverImageView = view.findViewById(R.id.cover_imageView);
            headerLabel = view.findViewById(R.id.title);
            plotLabel = view.findViewById(R.id.plot_label);
            iconOwned = view.findViewById(R.id.icon_owned);
            iconCheck = view.findViewById(R.id.check_container);
        }
    }

    public SearchIssuesAdapter(Context context, List<Issue> issues, IssueCellListener listener) {

        this.context = context;
        this.issues = issues;
        this.listener = listener;

        selectedItems = new SparseBooleanArray();

        int count = issues.size();
        covers = new String[count];
        for (int pos = 0; pos < count; pos++) {

            Issue issue = issues.get(pos);
            String imageUrl = issue.getImage().getMediumUrl();
            covers[pos] = imageUrl;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_issue_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Issue issue = issues.get(position);

        final String imageUrl = issue.getImage().getMediumUrl();
        Utils.setImageForItem(context, imageUrl, holder.coverImageView);

        // text data
        String seriesTitle = issue.getVolume().getTitle();
        int isCollectedEdition = 0;
        String issueTitle = issue.getTitle();
        String issueSubtitle = "";
        int issueNo = Integer.parseInt(issue.getIssueNumber());
        String t = Utils.textForLabel(seriesTitle, isCollectedEdition, issueTitle, issueSubtitle, issueNo).toString();

        holder.headerLabel.setText(t);

        String plotLabel = issue.getDescription();
        if (plotLabel != null && !plotLabel.isEmpty()) {

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
                holder.plotLabel.setText(Html.fromHtml(plotLabel, Html.FROM_HTML_MODE_COMPACT));
            else
                holder.plotLabel.setText(Html.fromHtml(plotLabel));
        }

        // owned icon
        holder.iconOwned.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_owned));
        holder.iconOwned.setColorFilter(ContextCompat.getColor(context, R.color.colorComicland));

        // checkmark icon
        applyIconAnimation(holder, position);

        holder.issueContainer.setOnClickListener(v -> listener.onIssueRowClicked(position));
        holder.issueContainer.setOnLongClickListener(v -> {
            listener.onIssueRowClicked(position);
            return true;
        });
        holder.coverImageView.setOnClickListener(v -> listener.onIssueCoverClicked(covers, position));
    }

    private void applyIconAnimation(MyViewHolder holder, int position) {

        if (selectedItems.get(position, false)) {

            holder.iconCheck.setVisibility(View.VISIBLE);
            holder.iconCheck.setAlpha(1);
        } else

            holder.iconCheck.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return issues.size();
    }

    public void toggleSelection(int pos) {

        if (selectedItems.get(pos, false))
            selectedItems.delete(pos);
        else
            selectedItems.put(pos, true);

        notifyItemChanged(pos);
    }

    public void clearSelections() {

        selectedItems.clear();

        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selectedItems.size();
    }

    public interface IssueCellListener {

        void onIssueRowClicked(int position);

        void onIssueCoverClicked(String[] covers, int position);
    }
}