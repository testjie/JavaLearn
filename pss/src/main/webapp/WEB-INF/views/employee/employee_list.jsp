<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/common/head.jsp" %>

</head>
<body>

<s:form action="/employee" id="domainForm" >
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>

        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">首页</a>
            </li>

            <li>
                <a href="#">员工管理</a>
            </li>
            <li class="active">员工 &amp; 管理</li>
        </ul><!-- .breadcrumb -->

        <div class="nav-search" id="nav-search">
            <form class="form-search">
								<span class="input-icon">
									<input type="text" placeholder="Search ..." class="nav-search-input"
                                           id="nav-search-input" autocomplete="off">
									<i class="icon-search nav-search-icon"></i>
								</span>
            </form>
        </div><!-- #nav-search -->
    </div>

    <div class="page-content">
        <div class="page-header">
            用户名：<s:textfield name="baseQuery.username"/>
            邮箱：<s:textfield name="baseQuery.email"/>
            <button class="btn btn-pink">查询</button>
        </div><!-- /.page-header -->

        <div class="row">
            <div class="col-xs-12">
                <!-- PAGE CONTENT BEGINS -->

                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table id="sample-table-1" class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th class="center">
                                        <label>
                                            <input type="checkbox" class="ace">
                                            <span class="lbl"></span>
                                        </label>
                                    </th>
                                    <th>头像</th>
                                    <th>姓名</th>
                                    <th>密码</th>
                                    <th>邮件</th>
                                    <th>年龄</th>
                                    <th>部门</th>
                                    <th></th>

                                </tr>
                                </thead>

                                <tbody>
                                <s:iterator value="pageList.result">
                                    <tr>
                                        <td class="center">
                                            <label>
                                                <input type="checkbox" class="ace">
                                                <span class="lbl"></span>
                                            </label>
                                        </td>

                                        <td>头像</td>
                                        <td>${username}</td>
                                        <td>${password}</td>
                                        <td>${email}</td>
                                        <td>年龄</td>
                                        <td>部门</td>

                                        <td>
                                            <button class="btn btn-xs btn-info">
                                                <i class="icon-edit bigger-120"></i>
                                            </button>

                                            <button class="btn btn-xs btn-danger">
                                                <i class="icon-trash bigger-120"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </s:iterator>

                                </tbody>
                            </table>
                            <%@ include file="/WEB-INF/views/common/page.jsp" %>

                        </div><!-- /.table-responsive -->
                    </div><!-- /span -->

                </div><!-- /row -->
            </div>
        </div>
    </div>
</div>
</s:form>
</body>
</html>
