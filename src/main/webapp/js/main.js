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
            "&api_key=DBFMyEd3Qs9z4ht48oGNqyk7E0GCMMzs&limit=1",
            type: "GET",
        })
        .done(function( response ) {
            console.log("Success", response);
            var url = response.data[0].images.original.url;
            var title = response.data[0].title;
    		var view = document.getElementById("view");
    		if(view.innerHTML === "Grid"){
    			var fig = createFigure(url, title);
                $("#result").prepend(fig);
    		} else {
                var media = createMedia(url, title);
                $("#result").prepend(media);
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
	
	var createFigure = function (url, title){
		var fig = document.createElement("figure");
		fig.classList.add("figure");
		var img = document.createElement("img");
		img.classList.add("figure-img");
		img.src = url;
		var figc = document.createElement("figcaption");
		figc.classList.add("figure-caption");
		figc.innerText = title;
		fig.appendChild(figc);
		fig.appendChild(img);
		return fig;
	}
	
	function changeView(view){
        $('#result').children().each(function () {
        	var $gif = $(this);
        	if(view === "Grid"){
        		var url = $gif.find('img').attr('src');
        		var title = $gif.find('p').text();
        		$gif.remove();
        		var fig = createFigure(url, title);
        		$('#result').append(fig);
        	} else {
        		var url = $gif.find('img').attr('src');
        		var title = $gif.find('figcaption').text();
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



