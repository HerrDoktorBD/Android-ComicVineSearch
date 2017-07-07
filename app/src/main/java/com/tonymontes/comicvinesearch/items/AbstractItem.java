
package com.tonymontes.comicvinesearch.items;

import java.util.UUID;

import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.viewholders.FlexibleViewHolder;

abstract class AbstractItem<VH extends FlexibleViewHolder>
        extends
        AbstractFlexibleItem<VH> {

    protected String id;
    protected String title;

    AbstractItem() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public boolean equals(Object inObject) {

        if (inObject instanceof AbstractItem) {

            AbstractItem inItem = (AbstractItem) inObject;
            return this.id.equals(inItem.id);
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}