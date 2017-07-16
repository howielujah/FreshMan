$(document).ready(function(){
	$("#accordion").accordion({ header: "h3" });
});

function goIndustry() {
	$.ajax({
		url : "industryList.do",
		success : function(data) {
			if ($(data).filter("title").text() == "404") {
				$("html").html(data);
			} else {
				$("#mainPage").html(data);
			}
		}
	});
}