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
		$("#emailId").val(id);
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
				{field : 'url',title : '地址',width : '70%',align : 'center'},
				{field : 'rssEncode',title : '编码',width : '10%',align : 'center'}
			] ]
		});
	}
	
	
	/**
	* 增加订阅信息
	* 得到当前邮箱id
	*
	*/
	function addRss(){
		
		$('#rss_add_dd').dialog({
		    closed: false,
		    cache: false,
		    modal: true
		});
	}
	
	/**
	* 删除订阅信息可以多选
	*/
	function deleteRss(){
		var rssId = new Array();
	    var selRows = $('#dg').datagrid('getChecked');  
	    for(var i=0;i<selRows.length;i++){
	    	rssId.push(selRows[i].id);
	    }
	   
	    
	    $.ajax({
			url : 'user/deleteRss',
			type:'post',
			data: ({rssIds:rssId.toString(),emailId:$("#emailId").val()}),
			success:function(data){
				alert(data.message);
				$('#dg').datagrid('reload');
			}
		})
	}
	
	function save_rss(){
		$('#rss_add_dd').dialog({
		    closed: true,
		    cache: false,
		    modal: true
		});
		
		$.ajax({
			url : 'user/addRss',
			type:'post',
			data: $("#rss_add_form").serialize(),
			success:function(data){
				alert(data.message);
				$('#dg').datagrid('reload');
			}
		})
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
				<a href="#" onclick="addRss()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Add</a>
				<a href="#" onclick="deleteRss()" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">Remove</a>
			</div>
			<table id="dg"  style="width:97%;height:300px"></table>
		</div>
	</div>
	<div id="rss_add_dd" class="easyui-dialog" title="新增订阅信息" closed="true" style="width:600px;height:200px;padding:10px" >
        	<form id="rss_add_form">
	        	<table width="100%" border="1">
	        		<tr>
	        			<td width="20%" align="right">订阅URL:</td>
	        			<td width="80%">
	        				<input size="90" type="text" name="url"> 
	        				<input  type="hidden" id="emailId" name="emailId"> 
	        				
	        			</td>
	        		</tr>
	        		<tr>
	        			<td width="20%" align="right">订阅描述:</td>
	        			<td width="80%">
	        				<input size="90" type="text" name="des"> 
	        			</td>
	        		</tr>
	        		<tr>
	        			<td colspan="2" align="right"> 
	        				<a href="#" class="easyui-linkbutton c1" style="width:120px">关闭</a>        
	        				<a href="#" onclick="save_rss()" class="easyui-linkbutton c1" style="width:120px">保存</a> 
	        			</td>
	        		</tr>
	        	</table>
        	</form>
    	</div>
</body>
</html>
