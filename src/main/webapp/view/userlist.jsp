<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="jquery,ui,easy,easyui,web">
    <meta name="description" content="easyui help you build your web page easily!">
    <title>jQuery EasyUI CRUD Demo</title>
    <link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="http://www.w3cschool.cc/try/jeasyui/demo/demo.css">
    <style type="text/css">
        #fm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            color:#666;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
    </style>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.6.min.js"></script>
    <script type="text/javascript" src="http://www.w3cschool.cc/try/jeasyui/jquery.easyui.min.js"></script>
    <script type="text/javascript">
        var url;
        function newUser(){
            $('#dlg').dialog('open').dialog('setTitle','New User');
            $('#fm').form('clear');
            /*单选默认选中*/
            $('input[name=sex]').get(0).checked = true;
        }
        /* 方法 表单数据转成json对象 */
        $.fn.serializeObject = function () {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function () {
                if (o[this.name]) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };
        function editUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                /*表格中的性别属性如何转换为数值,会修改表格数据---*/
                if(row['sex']=='Male' || row['sex']==1){
                    row['sex']=1;
                }else
                    row['sex']=0;
                $('#fm').form('clear');
                $('#dlg').dialog('open').dialog('setTitle','Edit User');
                $('#fm').form('load',row);
                url = 'update_user.php?id='+row.id;
            }
        }
        function saveUser(){
            var data = $('#fm').serializeObject();
            $.ajax({
                dataType: 'json', /* 请求数据类型 */
                type: 'post',
                contentType: 'application/json; charset=utf-8', /* 定义返回数据类型 */
                data: JSON.stringify(data), /* 格式化json */
                url: '/mongo/receive',
                success: function(result){
                    if (result.status==200){
                        $('#dlg').dialog('close');		// close the dialog
                        window.location.reload()// reload the user data
                    } else {
                        $.messager.show({
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                }
            });
        }
        function removeUser(){
            var row = $('#dg').datagrid('getSelected');
            if (row){
                $.messager.confirm('Confirm','Are you sure you want to remove this user?',function(r){
                    if (r){
                        $.post('remove_user.php',{id:row.id},function(result){
                            if (result.success){
                                $('#dg').datagrid('reload');	// reload the user data
                            } else {
                                $.messager.show({	// show error message
                                    title: 'Error',
                                    msg: result.msg
                                });
                            }
                        },'json');
                    }
                });
            }
        }
    </script>
</head>
<body>
<h2>Basic CRUD Application</h2>
<div class="demo-info" style="margin-bottom:10px">
    <div class="demo-tip icon-tip">&nbsp;</div>
    <div>Click the buttons on datagrid toolbar to do crud actions.</div>
</div>

<table id="dg" title="My Users" class="easyui-datagrid" style="width:700px;height:250px"
       toolbar="#toolbar" pagination="true"
       rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="userId" width="50" hidden="hidden">userId</th>
        <th field="name" width="50">name</th>
        <th field="age" width="50">age</th>
        <th field="sex" width="50">sex</th>
        <th field="registTime" width="90">registTime</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <tr>
            <td hidden="hidden">${user.userId}</td>
            <td>${user.name}</td>
            <td>${user.age}</td>
            <c:if test="${user.sex==1}"><td id="sex">Male</td></c:if>
            <c:if test="${user.sex==0}"><td id="sex">Female</td></c:if>
            <td>${user.registTime}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="toolbar">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="removeUser()">Remove User</a>
</div>

<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding:10px 20px"
     closed="true" buttons="#dlg-buttons">
    <div class="ftitle">User Information</div>
    <form id="fm" novalidate>
        <div class="fitem" hidden>
            <label>UserId:</label>
            <input name="userId" class="easyui-validatebox">
        </div>
        <div class="fitem">
            <label>Name:</label>
            <input name="name" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>Age:</label>
            <input name="age" class="easyui-validatebox" required="true">
        </div>
        <div class="fitem">
            <label>Sex:</label>
            <input type="radio" name="sex" value="1" />Male
            <input type="radio" name="sex" value="0" />Female<br>
        </div>
        <div class="fitem" hidden>
            <label>RegistTime:</label>
            <input name="registTime" class="easyui-validatebox">
        </div>
    </form>
</div>
<div id="dlg-buttons">
    <a href="#" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()">Save</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
</div>
</body>
</html>