<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!-- 分页条 -->
<div class="row">
	<div class="col-sm-6">
		<div class="dataTables_info" id="sample-table-2_info">
			第${pageList.beginIndex}条 - 第${pageList.endIndex}条数据  共${pageList.totalCount}条数据
			<!-- 
				这里的回显就是通过 name属性完成的(这个值如果显示是负数的话，对我来说,用户体验不好)
				baseQuery中错误数据我们没有纠正 ，但是pageList中的数据咱们是纠正过的
				把name改成:pageList.currentPage
				传参的时候名称 是：baseQuery.currentPage
				回显的时候是pageList中的值
				现在咱们 传参与回显都是name
				记住:在这里有一个属性叫value,如果设置了value,就用value做回显
				   value:把里面的东西当作一个值回显的，并没有把它当前OGNL表达式来使用
				 JSTL(c)标签需要和EL表达式合用
				 Struts2标签(s)需要和OGNL表达式合用
				 	name的时候默认就是ONGL表达式 -> 它就会到值栈中取值
				 在struts2标签中怎么把一个字符串转成OGNL表达式 -> %{字符串}
			 -->
			 <s:select name="baseQuery.pageSize" list="{5,10,20,50,200}" onchange="goPage(1)" />
			&nbsp;当前第<s:textfield name="baseQuery.currentPage" 
					value="%{pageList.currentPage}" id="currentPage" size="2" /> 页
		</div>
	</div>
	<div class="col-sm-6">
		<div class="dataTables_paginate paging_bootstrap">
			<ul class="pagination">
				<!-- 
					每一个li>a就是一个小方块
						disabled：禁用的样式
						active：活动中的样式
					后面每个domain对应的功能都有分页,咱们把它抽出来就很好
					我要在PageList中去拼接这个分页条
				 -->
				${pageList.page}
						
			</ul>
			<script type="text/javascript">
				function goPage(pageNo){
					//window.location.href = "/employee?baseQuery.currentPage="+pageNo;
					//1.把当前页的值改了
					$("#currentPage").val(pageNo);
					//2.提交表单
					$("#domainForm").submit();
				}
			</script>
		</div>
	</div>
</div>