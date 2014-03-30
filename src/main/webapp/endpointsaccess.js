

function showListOfProducts(data) {
  if (data && data.items) {
    var products = data.items;
    var items = [];
    $.each(products, function(key, val) {
      var details = "<div class='beerDetails'><pre>";
      for (var prop in val) {
        details += prop + ": " + val[prop] + "<br/>";
      }
      details += "</pre></div>";
      items.push('<li><img src="img/b3_40.png"/><span class="label label-warning">' + val.prodName +
              '</span> - Id: ' + val.prodId + '<br/>' + details + '</li>');
    });

    $('<ol/>', {
      'class': 'beerItem',
      html: items.join('')
    }).appendTo('#results');
  }
}

var apiUrl = "https://endpoints-dot-jcandystore.appspot.com/_ah/api/productendpoint/v1/product?first=0&limit=12";
$.ajax({
  url: apiUrl,
  dataType: 'json',
  contentType: 'application/json',
  type: "GET",
  success: function(data) {
    $('#results').html('');
    showListOfProducts(data);
  },
  error: function(xhr, ajaxOptions, thrownError) {
    console.error("Beer list error: " + xhr.status) + " err: " + thrownError;
    $('<h3/>', {
      html: "Could not find Candies"
    }).appendTo('#results');
  }
});
