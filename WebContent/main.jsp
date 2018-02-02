<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="main.css">
<title>Ramitechs MSA Sentiment Analyzer</title> 
</head>
<body>

<img src="logo.png"/> 

<h1 class="blue">Ramitechs Sentiment Analyzer</h1>
<h3 class="blue">Demo for: Smartera 3S Solutions and Systems</h3>

<form action="analyze" method="post">

<b class="blue">Sentence 1:</b> <input type="text" name="sentence1" class="textbox" maxlength="150" onInput=adjust(1) value="${text1}">
<b id="result1">${result1}</b><br>
<b class="blue">Sentence 2:</b> <input type="text" name="sentence2" class="textbox" maxlength="150" onInput=adjust(2) value="${text2}">
<b id="result2">${result2}</b><br>
<b class="blue">Sentence 3:</b> <input type="text" name="sentence3" class="textbox" maxlength="150" onInput=adjust(3) value="${text3}">
<b id="result3">${result3}</b><br>
<b class="blue">Sentence 4:</b> <input type="text" name="sentence4" class="textbox" maxlength="150" onInput=adjust(4) value="${text4}">
<b id="result4">${result4}</b><br>
<b class="blue">Sentence 5:</b> <input type="text" name="sentence5" class="textbox" maxlength="150" onInput=adjust(5) value="${text5}">
<b id="result5">${result5}</b><br>
<input type="submit" name="submit" class="button" value="Analyze">
</form>

<script type="text/javascript">
function adjust(value){
    document.getElementById("result"+value).style.visibility='hidden';
   }
</script>

</body>
</html>