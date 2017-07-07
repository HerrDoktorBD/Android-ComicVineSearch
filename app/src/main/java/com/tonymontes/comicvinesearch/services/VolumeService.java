
package com.tonymontes.comicvinesearch.services;

import com.tonymontes.comicvine.Volume;
import com.tonymontes.comicvinesearch.R;
import com.tonymontes.comicvinesearch.app.ComicvineSearch;
import com.tonymontes.comicvinesearch.items.VolumeCellItem;
import com.tonymontes.comicvinesearch.items.VolumeHeaderItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public class VolumeService {

    private static VolumeService mInstance;

    private List<AbstractFlexibleItem> mItems = new ArrayList<>();

    private VolumeService() {
    }

    public static VolumeService getInstance() {

        if (mInstance == null)
            mInstance = new VolumeService();
        return mInstance;
    }

    public static void onDestroy() {
        mInstance = null;
    }

    public List<AbstractFlexibleItem> createVolumeListFrom(List<Volume> volumes) {

        int rowCount = volumes.size();
        mItems.clear();

        for (int i = 0; i < rowCount; i++) {

            Volume cvVolume = volumes.get(i);
            VolumeHeaderItem volumeHeader = newVolumeHeader(cvVolume);
            if (i == 0)
                volumeHeader.setExpanded(true);
            mItems.add(volumeHeader);
        }
        return mItems;
    }

    private static VolumeHeaderItem newVolumeHeader(Volume vm) {

        // header title
        StringBuilder sb = new StringBuilder(vm.getTitle());
        Boolean hasPublisher = vm.getPublisher() != null;
        Boolean hasYear = vm.getStartYear() != null;
        if (hasPublisher) {
            sb.append(" (");
            sb.append(vm.getPublisher().getName());
        }
        if (hasYear) {
            if (hasPublisher)
                sb.append(", ");
            sb.append(vm.getStartYear());
        }
        if (hasPublisher)
            sb.append(")");

        // volume header
        VolumeHeaderItem header = new VolumeHeaderItem();
        header.setTitle(sb.toString());

        StringBuilder sb1 = new StringBuilder();
        if (hasPublisher)
            sb1.append(vm.getPublisher().getName());
        if (hasYear) {
            if (hasPublisher)
                sb1.append(" (");
            sb1.append(vm.getStartYear());
        }
        if (hasPublisher && hasYear)
            sb1.append(")");

        // volume cell
        VolumeCellItem volumeCell = new VolumeCellItem();
        volumeCell.setTitle(sb1.toString());

        volumeCell.setCoverImageView(vm.getImage().getIconUrl());

        int bookCount = vm.getCountOfIssues();
        String books = ComicvineSearch.getInstance().getString((bookCount == 1) ? R.string.issue : R.string.issues);
        volumeCell.setBookCountLabel(String.format(Locale.getDefault(), "%d %s", bookCount, books));

        volumeCell.setIssueSearchTitle(header.getTitle());

        volumeCell.setComicvineID(vm.getComicvineID().toString());

        // In this case the Header is the same parent: VolumeHeaderItem instance
        volumeCell.setHeader(header);
        header.addCell(volumeCell);

        return header;
    }
}
