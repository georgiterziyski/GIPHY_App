$(function(){
	
	$("#button").on("click", function(e){
		e.preventDefault()
		var input = $("#input").val();
		if(input == ""){
			return;
		}
		$("#input").val("");
		var url = "http://api.giphy.com/v1/gifs/search?q=";
		var apiKey = "DBFMyEd3Qs9z4ht48oGNqyk7E0GCMMzs";
        $.ajax({
            url: "http://api.giphy.com/v1/gifs/search?q="+
            input +
            "&api_key=DBFMyEd3Qs9z4ht48oGNqyk7E0GCMMzs&limit=32",
            type: "GET",
        })
        .done(function( response ) {
            console.log("Success", response);
            for(var i in response.data){
            	var url = response.data[i].images.original.url;
            	var title = response.data[i].title;
            	var view = document.getElementById("view");
            	if(view.innerHTML === "Grid"){
            		var card = createCard(url, title);
            		$("#result").prepend(card);
            	} else {
            		var media = createMedia(url, title);
            		$("#result").prepend(media);
            	}
            }
        })
	})
	
    var createMedia = function(url, title){
        var $list = $('#list-view').html();
        $list = $($list);
        $list.find('img').attr('src', url);
        $list.find('p').text(title);        
        return $list;
    }
	
	var createCard = function (url, title){
        var $grid = $('#grid-view').html();
        $grid = $($grid);
        $grid.find('img').attr('src', url);
        $grid.find('h5').text(title);        
        return $grid;
	}
	
	function changeView(view){
        $('#result').children().each(function () {
        	var $gif = $(this);
        	if(view === "Grid"){
        		var url = $gif.find('img').attr('src');
        		var title = $gif.find('p').text();
        		$gif.remove();
        		var card = createCard(url, title);
        		$('#result').append(card);
        	} else {
        		var url = $gif.find('img').attr('src');
        		var title = $gif.find('h5').text();
        		$gif.remove();
                var media = createMedia(url, title);
                $("#result").append(media);      		
        	}
        });
	}	
	
	function toggleView(){
		var view = document.getElementById("view");
		if(view.innerHTML === "Grid"){
			view.innerHTML = " List";
		} else {
			view.innerHTML = "Grid";
		}
		if( $('#result').is(':empty') ) {
			return;
		}
		changeView(view.innerHTML);	
	}
	
	$('.btn-view').on("click", function(){
	  toggleView();	
	})
	
})



