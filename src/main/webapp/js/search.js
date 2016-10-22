  function getParameterByName(name, url) {
      if (!url) url = window.location.href;
      name = name.replace(/[\[\]]/g, "\\$&");
      var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)", "i"),
          results = regex.exec(url);
      if (!results) return null;
      if (!results[2]) return '';
      return decodeURIComponent(results[2].replace(/\+/g, " "));
  }

  function doSearch(keyword, heroType) {
  	var searchUrl; 
  	if (heroType == 'Heroes') {
  		searchUrl = '/heroes/search';
  	} else if (heroType == 'Villains') {
  		searchUrl = '/villains/search';
  	} else {
  		searchUrl = '/characters/search';
  	}
	$.ajax({
		url: searchUrl,
		data: { keyword: keyword }
	})
	.done(function(data) {
		//$('#page-title').text('Search results: '+keyword);
		$('#search-results').show();
		var searchResults = new Array();
		$.each(data, function(i, character) {
			//console.log(i);
			searchResults[searchResults.length] = {
				name: character.name,
				id: character.id,
				detailUrl: '/detail.html?id='+character.id,
				imageUrl: '/'+character.img,
				aliases: character.alias,
				style: "background: whitesmoke url('/"+character.img+"') no-repeat center center; background-size: cover;"
			}
		});

		//$('#search-results div').loadTemplate($('#search-result-template'), searchResults);
		$('#search-results div').loadTemplate('../_search_results.html', searchResults);
	});
	
  }

  $(function () {
  	$('.dropdown-menu a').on('click', function() {
  		if ($(this).text() == 'Heroes') {
	  		$('#search-text').attr('placeholder', 'Search Heroes...');
  		} else if ($(this).text() == 'Villains') {
	  		$('#search-text').attr('placeholder', 'Search Villains...');
  		} else {
  			$('#search-text').attr('placeholder', 'Search...'); 
  		}
		$('input[name="hero-type"]').val($(this).text());
  	});
  });

