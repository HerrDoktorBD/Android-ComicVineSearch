
package com.tonymontes.comicvinesearch.adapters;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

public class SearchVolumesAdapter
        extends
        FlexibleAdapter<AbstractFlexibleItem> {

    public SearchVolumesAdapter(List<AbstractFlexibleItem> items, Object listeners) {
        super(items, listeners, true);
    }
}