<%@ page language="java" import="java.util.*,adm.entity.Teacher"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	Teacher teacher = (Teacher) request.getSession().getAttribute(
			"teac");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>课程信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="课程信息修改页面">

		<style type="text/css">
.mdcs { /*background-color: gray;*/
	width: 70%;
}

.mdcst td {
	height: 40px;
	font-size: 18px;
	text-align: center;
}

.inp {
	width: 120px;
	height: 40px;
	text-align: center;
	line-height: 34px;
	font-size: 18px;
	font-family: 华文楷体;
}

#id_tname {
	border-style: ridge;
	background-color: gray;
}
</style>

	</head>

	<body style="font-family: 华文楷体" onload="setLang()">
		<h2>
			课程信息设置
		</h2>
		<div class="mdcs">
			<form method="post" action="md_course.action"
				onsubmit="return check()">
				<input type="hidden" id="opt" value='<s:property value='lgid'/>'>
				<table border="0" class="mdcst">
					<caption>
						课程信息修改
						<hr color="rgb(217,154,98)">
					</caption>
					<tr>
						<td>
							授课班号:
						</td>
						<td>
							<input type="text" class="inp" id="id_course" name="courid"
								maxlength="10" value="<s:property value="courid"/>" readonly="readonly"/>
						</td>
						<td>
							<span style="color: red">*</span>
						</td>
						<td>
							课程名称:
						</td>
						<td>
							<input type="text" class="inp" id="id_cname" name="cname"
								maxlength="10" value="<s:property value="cname"/>" />
						</td>
						<td>
							<span style="color: red">*</span>
						</td>
					</tr>
					<s:select id="lang" name="lgid" list="langs" listKey="langId"
						listValue="lname" cssClass="inp" label="语言类别" value="langId">
					</s:select>
					<tr>
						<td>
							教师姓名:
						</td>
						<td>
							<input type="text" class="inp" id="id_tname" name="tname"
								value="<%=teacher.getTname()%>" readonly="readonly" />
							<input type="hidden" id="id_teacid" name="teacid"
								value="<%=teacher.getTeacherId()%>" />
						</td>
					</tr>
				</table>
				<input type="submit" id="sbt" value="更新"
					style="font-size: 16px; font-family: 华文楷体" />
			</form>
		</div>
	</body>
	<script type="text/javascript">
function setLang() {
	with (document.all) {
		lang.value = opt.value;
	}
}

function check() {
	with (document.all) {
		if (id_course.value == '' || id_cname.value == '') {
			alert("信息不完整，请完善!!");
			return false;
		} else
			return true;
	}
}
</script>
</html>
