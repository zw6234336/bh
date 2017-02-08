<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BH</title>
<link rel="stylesheet" type="text/css" href="static/js/jquery-easyui-1.4.1/demo/demo.css">
<link rel="stylesheet" type="text/css" href="static/js/jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="static/js/jquery-easyui-1.4.1/themes/icon.css">
<script type="text/javascript" src="static/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="static/js/jquery-easyui-1.4.1/datagrid-cellediting.js"></script>	
<script type="text/javascript" src="static/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url : 'user/getUserInfo?userName=zhang',
			type:'post',
			success:function(data){
				$("input[name='userName']").attr("value",data.user.userName);
				$("input[name='phone']").attr("value",data.user.phone);
				$("input[name='pwd']").attr("value",data.user.pwd);
				$("input[name='userid']").attr("value",data.user.id);
				
				$('#email_dg').datagrid({
					data:data.userEmails,
					rownumbers : true,
					singleSelect : false,
					fitColumns:false,
					striped : true,
					nowrap : false,
					columns : [ [ 
						{field : 'ck',checkbox:'true'},
						{field : 'id',title : 'id',width : '10%',align : 'center'},
						{field : 'email',title : '邮箱名称',width : '60%',align : 'center'},
						{field:'action',title:'操作',width:'20%',align:'center',
				            formatter:function(value, row, index){
				            	return '<a href="#" onclick="update2rsslist(\''+row.id+'\')">查看订阅信息</a> ';
				            }
				        }
					] ]
				});
			}
		})
	})
	
	//订阅信息展示
	function update2rsslist(id){
		$('#dg').datagrid({
			url :'user/getRssInfo?id='+id,
			rownumbers : true,
			singleSelect : false,
 			rownumbers : true,
 			fitColumns:false,
 			nowrap : false,
			fitColumns:false,
			striped : true,
			nowrap : false,
			columns : [ [ 
				{field : 'ck',checkbox:'true'},
				{field : 'id',title : 'id',width : '5%',align : 'center'},
				{field : 'url',title : '地址',width : '80%',align : 'center'}
			] ]
		});
	}
	
</script>
</head>
<body>
	<div style="margin: 20px 0;"></div>
	<div class="easyui-layout" style="width: 100%; height: 500px;">
		<div data-options="region:'north'" style="height: 100px" title="用户信息">
				<form id="ff" action="form1_proc.php" method="post" enctype="multipart/form-data">
					<table width="100%" >
						<tr>
							<td>用户名:</td>
							<td><input name="userName" /><input hidden="true"  name="userid"  /></td>
							<td>电话:</td>
							<td><input name="phone" ></input></td>
							<td>密码:</td>
							<td><input name="pwd" ></input></td>
						</tr>
						<tr>
							<td colspan="6" align="right"><input type="submit" value="Submit"></input></td>
						</tr>
					</table>
				</form>
		</div>
		<div data-options="region:'south',split:true" style="height: 50px;"></div>
		<div id="p" data-options="region:'west'" title="邮箱信息" style="width: 50%; padding: 10px">
			<div>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Add</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">Remove</a>
			</div>
			<table width="100%" id="email_dg" ></table>
		</div>
		<div data-options="region:'center'" title="订阅信息">
			<div>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Add</a>
				<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">Remove</a>
			</div>
			<table id="dg"  style="width:100%;height:300px"></table>
		</div>
	</div>
</body>
</html>
