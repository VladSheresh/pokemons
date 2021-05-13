package by.grsu.edu.model;

public class Pagination {

    private final int page;
    private final int pageSize;

    private Pagination(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public static Pagination of(int page, int pageSize){
        return new Pagination(page, pageSize);
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }
}
