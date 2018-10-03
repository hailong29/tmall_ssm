<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>天猫主页</title>
		
		<script type="text/javascript" src="http://libs.baidu.com/jquery/1.9.1/jquery.min.js"></script>
		
		<style >

			ul.row {
				list-style: none;
				width: 100%;
				padding: 0px;
				overflow: auto;
				position: absolute;
				top: 0%;
				left: 10%;
				background-color: red;
			}
			ul.row a{
				float: left;
				padding:5px 20px 5px 20px;
				text-decoration: none;
				color: white;
				font-family: "微软雅黑";
			}
			
			ul.row a:hover:not(.mainRow){
				background: #111;
			}
			
			/*1列格式*/
			ul.column {
				list-style: none;
				width: 10%;
				padding: 0px;
				border: 0px;
				background: #eeeeee;
			}
			
			
			ul.column a{
				color: black;
				text-decoration: none;
				display: block;
				padding: 5px 15px;
				font-family: "微软雅黑";
				font-size: small;
			}
			ul.column a.mainColumn{

				padding: 5px 0px 5px 15% ;
				background: #dd3333;
				color: white;
				text-decoration: none;
				font-size: medium;
			}
			
			ul.column a:hover:not(.mainColumn){
				background: gray;
			}
			
			 /*隐藏表格 */
			table.hideContent {
				position: absolute;
				left: 10%;
				top:10%;
				background: white;
				padding: 3%;
				opacity: 0.9;
				

				display: none;
			}
			
			table.hideContent a{
				text-decoration:none;
				color:black;
				font-family:""微软雅黑"";
			}
			
			/* 必须使用td, 不能使用tr,因为ie8好像不支持tr的该变*/
			table.hideContent td {
				display: inline-block;
				border-bottom:1px dashed black;
				padding: 10px 10px;
			}
			
			#homePage{
				position: absolute;
				top: 47px;
				left: 10%;
			}
			
			.image{
				background: #eeeeee;
				margin: 0px 10%;
				width: 80%;
			}
			
			.imgTable{
				padding: 50px 10px;
			}
			

			
			
			
		</style>
		
		
		<script>
		$(document).ready(function(){
			$("ul.column a:not(.mainColumn)").hover(function(){
				$("table.hideContent").toggle();
			});
		});
		</script>
		
	</head>
	<body>
		
		
		
		<ul class="row">
			<li><a class="mainRow" href="">天猫国际</a></li>
			<li><a class="otherRow" href="" onfocus="">平板电脑</a></li>
			<li><a class="otherRow" href="">马桶</a></li>
			<li><a class="otherROw" href="">沙发</a></li>
			<li><a class="otherRow" href="">电热水器</a></li>
		</ul>
		
		<ul class="column">
			<li><a class="mainColumn" href="">商品分类</a></li>
			<li><a class="otherColumn" href="" onfocus="show()" >平板电脑</a></li>
			<li><a class="otherColumn" href="">马桶</a></li>
			<li><a class="otherColumn" href="">沙发</a></li>
			<li><a class="otherColumn" href="">电热水器</a></li>
			<li><a class="otherColumn" href="">平衡车</a></li>
			<li><a class="otherColumn" href="">扫地机器人</a></li>
			<li><a class="otherColumn" href="">原汁机</a></li>
			<li><a class="otherColumn" href="">冰箱</a></li>
			<li><a class="otherColumn" href="">空调</a></li>
			<li><a class="otherColumn" href="">女表</a></li>
			<li><a class="otherColumn" href="">男表</a></li>
			<li><a class="otherColumn" href="">男士手拿包</a></li>
			<li><a class="otherColumn" href="">男士西服</a></li>
			<li><a class="otherColumn" href="">时尚男鞋</a></li>
			<li><a class="otherColumn" href="">品牌女装</a></li>
			<li><a class="otherColumn" href="">太阳镜</a></li>
			<li><a class="otherColumn" href="">安全座椅</a></li>

		</ul>
		
		

		<%String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		String imgPath = request.getContextPath() + "/img/productSingle_middle/";%>
		
 		<!-- 导航框旁边图片 -->
		<img id="homePage" src="<%=basePath%>/img/lunbo/1.jpg" width="90%" height="490px" alt="图片未加载" />
<!--
		<img src="<%=basePath%>/img/productSingle_small/10146.jpg" alt="图片未加载" />
		<img  src="/HelloWorld/img/productSingle_small/10145.jpg" alt="图片未加载" />
-->
		

		<table class="hideContent">
			<tr class="rowA">
				<c:forEach var="label" items="${labels}" >  
        		<td><a href="">${label.content }</a><br />
				</c:forEach>
			</tr>
			<tr class="rowB">
				<td>新品上市</td>
				<td>仿人跪式</td>
				<td>非常夏日</td>
				<td>纯铜电机</td>
				<td>保修三年</td>
				<td>经典爆款</td>
				<td>无刷电机</td>	
			</tr>
			<tr class="rowB">
				<td>新品上市</td>
				<td>仿人跪式</td>
				<td>非常夏日</td>
				<td>纯铜电机</td>
				<td>保修三年</td>
				<td>经典爆款</td>
				<td>无刷电机</td>	
			</tr>
		</table>
		


		<div class="image">
			
			<table class="imgTable" >
				<tr><td>电视机</td></tr>
				<tr>
				<td><img class="tableImg" src="<%=imgPath%>629.jpg" width="100%" alt="未加载"></img>  </td>
				<td><img class="tableImg" src="<%=imgPath%>630.jpg" width="100%" alt="未加载"></img>  </td>
				<td><img class="tableImg" src="<%=imgPath%>631.jpg" width="100%" alt="未加载"></img>  </td>
				<td><img class="tableImg" src="<%=imgPath%>632.jpg" width="100%" alt="未加载"></img>  </td>
				<td><img class="tableImg" src="<%=imgPath%>639.jpg" width="100%" alt="未加载"></img>  </td>
				</tr>
			</table>
				
			<table class="imgTable" >
				<tr><td>马桶</td></tr>
				<tr>
				<td><img class="tableImg" src="<%=imgPath%>1276.jpg" width="100%" alt="未加载"></img>  </td>
				<td><img class="tableImg" src="<%=imgPath%>1277.jpg" width="100%" alt="未加载"></img>  </td>
				<td><img class="tableImg" src="<%=imgPath%>1278.jpg" width="100%" alt="未加载"></img>  </td>
				<td><img class="tableImg" src="<%=imgPath%>1279.jpg" width="100%" alt="未加载"></img>  </td>
				<td><img class="tableImg" src="<%=imgPath%>1280.jpg" width="100%" alt="未加载"></img>  </td>
				</tr>
			</table>


			
		</div>

			

	</body>
</html>