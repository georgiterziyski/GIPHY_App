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
			loadUserFavourites();
		});
	}
	getCurrentUser();
	
	loadUserFavourites = function() {
		$.ajax({
			method : "GET",
			url : "getFavourites"
		}).done(
				function(response) {

					for (var i = 0; i < response.length; i++) {
						var favourite = response[i];
						renderObject(favourite.url, favourite.title);
					}

				}).fail(function(response) {
			console.log(response);
		})
	}
	var renderObject = function(url, title){
        var $list = $('#view-temp').html();
        $list = $($list);
        $list.find('h5').text(title);
		$list.find('img').attr('src', url);
        $list.addClass("card");
        $list.find('div').filter(":first").addClass("card-body");
        $list.find('img').addClass("card-img-top");
        $(".container").prepend($list);
    }
})