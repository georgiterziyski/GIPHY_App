$(function(){

	var getCurrentUser = function(){
		$.ajax({
			method: "GET",
			url: "getCurrentUser"
		})
		.done(function(response) {
			if(!response){
                window.location = "login.html";
				return;
			}
			console.log(response);
			$("#status").text("Изход");
			$(".navbar-brand").text("Здравей, "+response.username)
		});
	}
    getCurrentUser();
    
	$("#status").on("click", function(e){
		e.preventDefault();
		if($("#status").val() === "Вход"){
			return;
		}
		$.ajax({
            method : "POST",
			url : "logout",
			data: null
        }).done(function(response) {
			window.location = response;
        });
	})

})