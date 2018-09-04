package cn.itblacklist.pss.page;

import cn.itblacklist.pss.domain.Employee;

import java.util.List;
import java.util.ArrayList;

public class PageList<T> {

    /**
     *当数据从前台传过来之后，后台需要接收到它
     * 把前台过来的所有数据封装成一个对象（这个对象 拿到前台传过来的所有参数）
     *
     */
    //每页显示多少条数据(每页条数) -> 从前台传过来的
    private int pageSize;
    //当前页 -> 从前台传过来的
    private int currentPage;
    // 总条数 -> 数据库中传过来的
    private int totalCount;
    //总条数 -> 计算完成的
    private int totalPage;
    //当前页的所有数据 -> 也是从数据库中传过来的
    private List<Employee> result = new ArrayList<>();

    /**
     * 问题已：这个无参的构造器有什么用？
     *  只要查询，我就一定要返回一个PageList对象（不反悔就需要解决很多空指针问题，很麻烦）
     *  查询的时候可能一条数据都查不到-》这时候直接返回一个空得PageList就行了
     *
     *  问题二：有参的构造器，为什么是穿着三个参数呢？
     *      currentPage, pageSize -> 前台传过来的，必须给它
     *      totalCount -> 从数据库中查询出来的，必须给
     *      totalPage为什么不需-》计算出来的
     *
     *  问题三：result也是从数据库中传过来的，为什么它不传到参数中来？
     *
     */
    public PageList(){}

    public PageList(int currentPage, int pageSize, int totalCount){
        this.pageSize = pageSize < 1 ? 10: pageSize;
        this.totalCount = totalCount;
        this.currentPage = currentPage < 1 ? 1: currentPage;
        // 计算总页数
        // 公式：总条数%每页条数 > 0 ?(总条数/每页条数+1): (总条数/每页条数)
        // this.totalPage = this.totalCount % this.pageSize >0 ?this.totalCount/this.pageSize+1:this.totalCount/this.pageSize;
        this.totalPage = (int) Math.ceil((double) this.totalCount/this.pageSize);

        this.currentPage = this.currentPage > this.totalPage ? this.totalPage : this.currentPage ;
    }

    /**
     *   <li class="prev disabled"><a href="#"><i class="icon-double-angle-left"></i></a></li>
     *   <li class="active"><a href="#">1</a></li>
     *   <li><a href="#">2</a></li>
     *   <li><a href="#">3</a></li>
     *   <li class="next"><a href="#"><i class="icon-double-angle-right"></i></a></li>
     * @return
     */
    public String getPage(){
        StringBuffer page = new StringBuffer();
        page.append("<li><a href=\"#\" onclick='goPage(1)'>首页</a></li>");
        page.append("<li><a href=\"#\" onclick='goPage("+(currentPage-1)+")'>上一页</a></li>");

        // 使用PageIndex中的工具方法，帮我们完成页数的计算
        PageIndex pageIndex = PageIndex.getPageIndex(5, currentPage, totalPage);

        for (int i=pageIndex.getBeginIndex(); i<=pageIndex.getEndIndex(); i++){
            if (i==currentPage){
                page.append("<li class='active'><a href=\"#\">"+i+"</a></li>");

            }else{
                page.append("<li><a href=\"#\" onclick='goPage("+i+")'>"+i+"</a></li>");
            }
        }
        page.append("<li><a href=\"#\" onclick='goPage("+(currentPage+1)+")'>下一页</a></li>");
        page.append("<li><a href=\"#\" onclick='goPage("+(totalPage)+")'>尾页</a></li>");

        return page.toString();
    }

    private int beginIndex;

    public int getBeginIndex() {
        beginIndex= (currentPage-1) * pageSize + 1;
        return beginIndex;
    }

    private int endIndex;

    public int getEndIndex(){
        endIndex = currentPage * pageSize;
        endIndex = endIndex >totalCount?totalCount:endIndex;
        return endIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Employee> getResult() {
        return result;
    }

    public void setResult(List<Employee> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PageList{" +
                "pageSize=" + pageSize +
                ", currentPage=" + currentPage +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", result=" + result +
                '}';
    }
}


