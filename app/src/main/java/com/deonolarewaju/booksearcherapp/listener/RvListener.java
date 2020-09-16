package com.deonolarewaju.booksearcherapp.listener;

import com.deonolarewaju.booksearcherapp.models.Volume;

public interface RvListener {
    void onItemClick(Volume volume, int position);
}
