package cn.itblacklist.pss.page;


/**
 * 页码的开始索引和接受索引
 */
public class PageIndex {
	private Integer beginIndex;
	private Integer endIndex;
	
	/**
	 * 首页  上页  [1] 2 3 4 5 6 7 8 9 10 下页  尾页  当前第1/18页 一共53条记录  每页5 条
	 * @param totalIndexCount	总索引数
	 * @param currentPage		当前页
	 * @param totalPage			总页数
	 * @return
	 */
	public static PageIndex getPageIndex(Integer totalIndexCount,
			Integer currentPage, Integer totalPage) {
		Integer startPage = currentPage
				- (totalIndexCount % 2 == 0 ? totalIndexCount / 2 - 1 : totalIndexCount / 2);
		Integer endPage = currentPage + totalIndexCount / 2;
		if (startPage < 1) {
			startPage = 1;
			if (totalPage >= totalIndexCount)
				endPage = totalIndexCount;
			else
				endPage = totalPage;
		}
		if (endPage > totalPage) {
			endPage = totalPage;
			if ((endPage - totalIndexCount) > 0)
				startPage = endPage - totalIndexCount + 1;
			else
				startPage = 1;
		}
		return new PageIndex(startPage, endPage);
	}

	public PageIndex(Integer beginIndex, Integer endIndex) {
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
	}

	public Integer getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(Integer beginIndex) {
		this.beginIndex = beginIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
}
