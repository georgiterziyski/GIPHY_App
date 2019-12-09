    $("#login-button").on("click", function() {

	    var email = $("#email").val();
        var password = $("#password").val();
        $.ajax({
            method : "POST",
            url : "login",
            data : {
                email : email,
                password : password
            }
        }).done(function(response) {
            window.location = response;
        });
    })


    $("#confirm-register").on("click", function(e){
	    $("#register-form").submit();
    })