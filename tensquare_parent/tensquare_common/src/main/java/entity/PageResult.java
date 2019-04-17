package entity;

import java.util.List;

/**
 * PageResult
 * 分页参数
 *
 * @Author wanggaian
 * @Date 2019/4/17 8:07
 */
public class PageResult<T> {

    private long total;
    private List<T> rows;

    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
