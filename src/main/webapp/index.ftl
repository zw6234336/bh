<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BH</title>
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/demo/demo.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css">
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="js/jquery-easyui-1.4.1/datagrid-cellediting.js"></script>	
<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript">
	$(function(){
		$.ajax({
			url : 'mvc/user/getUserInfo?userName=zhang',
			type:'post',
			success:function(data){
				alert(data);
			}
		})
	})
        var data = [
            {"productid":"FI-SW-01","productname":"Koi","unitcost":10.00,"status":"P","listprice":36.50,"attr1":"Large","itemid":"EST-1"},
            {"productid":"K9-DL-01","productname":"Dalmation","unitcost":12.00,"status":"P","listprice":18.50,"attr1":"Spotted Adult Female","itemid":"EST-10"},
            {"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"P","listprice":38.50,"attr1":"Venomless","itemid":"EST-11"},
            {"productid":"RP-SN-01","productname":"Rattlesnake","unitcost":12.00,"status":"N","listprice":26.50,"attr1":"Rattleless","itemid":"EST-12"},
            {"productid":"RP-LI-02","productname":"Iguana","unitcost":12.00,"status":"N","listprice":35.50,"attr1":"Green Adult","itemid":"EST-13"},
            {"productid":"FL-DSH-01","productname":"Manx","unitcost":12.00,"status":"P","listprice":158.50,"attr1":"Tailless","itemid":"EST-14"},
            {"productid":"FL-DSH-01","productname":"Manx","unitcost":12.00,"status":"P","listprice":83.50,"attr1":"With tail","itemid":"EST-15"},
            {"productid":"FL-DLH-02","productname":"Persian","unitcost":12.00,"status":"N","listprice":23.50,"attr1":"Adult Female","itemid":"EST-16"},
            {"productid":"FL-DLH-02","productname":"Persian","unitcost":12.00,"status":"P","listprice":89.50,"attr1":"Adult Male","itemid":"EST-17"},
            {"productid":"AV-CB-01","productname":"Amazon Parrot","unitcost":92.00,"status":"N","listprice":63.50,"attr1":"Adult Male","itemid":"EST-18"}
        ];
        $(function(){
            var dg = $('#dg').datagrid({
                data: data
            });
            dg.datagrid('enableCellEditing').datagrid('gotoCell', {
                index: 0,
                field: 'productid'
            });
        });
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
							<td><input name="userName" class="f1 easyui-textbox"></input></td>
							<td>电话:</td>
							<td><input name="phone" class="f1 easyui-textbox"></input></td>
							<td>密码:</td>
							<td><input name="pwd" class="f1 easyui-textbox"></input></td>
						</tr>
						<tr>
							<td colspan="6" align="right"><input type="submit" value="Submit"></input></td>
						</tr>
					</table>
				</form>
		</div>
		<div data-options="region:'south',split:true" style="height: 50px;"></div>
		<div id="p" data-options="region:'west'" title="邮箱信息" style="width: 30%; padding: 10px">
			<form id="ff" action="form1_proc.php" method="post" enctype="multipart/form-data">
					<table width="100%" >
						<tr>
							<td>邮箱名称:</td>
							<td><input name="userName" class="f1 easyui-textbox"></input></td>
							</tr>
						<tr>
							<td colspan="2" align="right"><input type="submit" value="Submit"></input></td>
						</tr>
					</table>
				</form>
		</div>
		<div data-options="region:'center'" title="订阅信息">
		<table id="dg"  style="width:100%;height:300px">
	        <thead>
	            <tr>
	                <th data-options="field:'itemid',width:100">Item ID</th>
	                <th data-options="field:'productid',width:100,editor:'text'">Product</th>
	                <th data-options="field:'listprice',width:80,align:'right',editor:{type:'numberbox',options:{precision:1}}">List Price</th>
	                <th data-options="field:'unitcost',width:80,align:'right',editor:'numberbox'">Unit Cost</th>
	                <th data-options="field:'attr1',width:250,editor:'text'">Attribute</th>
	                <th data-options="field:'status',width:60,align:'center',editor:{type:'checkbox',options:{on:'P',off:''}}">Status</th>
	            </tr>
	        </thead>
    	</table>
		</div>
	</div>
</body>
</html>
