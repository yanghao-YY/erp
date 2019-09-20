package com.saltyfish.erp.util;

import com.saltyfish.erp.domain.Shoes;

import java.util.List;

public class shoeData {
    private long total;
    private List<Shoes> rows;

    public shoeData(long total, List<Shoes> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Shoes> getRows() {
        return rows;
    }

    public void setRows(List<Shoes> rows) {
        this.rows = rows;
    }

}
