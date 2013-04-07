var myLatlng = new google.maps.LatLng(24.8668140212291, 67.0245750885677);
var mapOptions = {
    center: myLatlng, 
    zoom: 18, 
    mapTypeId: google.maps.MapTypeId.ROADMAP
};
var map = new google.maps.Map(document.getElementById("map_canvas"), mapOptions);
var markers = [ ["2012-09-24 18:07:41", 24.8668140212291, 67.0245750885677, 0, 0], ["2012-09-24 18:07:36", 24.8668160294229, 67.0245824851044,0 , 0], ["2012-09-24 18:06:36", 24.8668713941841, 67.0246533809766, 0, 0], ["2012-09-24 18:06:22", 24.8668615884506, 67.0247601269089,0 , 0], ["2012-09-24 18:06:13", 24.8668338472275, 67.0248604181688, 0, 0], ["2012-09-24 18:03:57", 24.8667554511475, 67.0249290137538, 0, 0], ["2012-09-24 18:03:51", 24.8668009248909, 67.0250219154492,0 , 0], ["2012-09-24 18:03:46", 24.8668796432715, 67.0250864232843, 0, 0], ["2012-09-24 18:03:34", 24.8669659842755, 67.0250372690714,0 , 0], ["2012-09-24 18:03:16", 24.8670302748442, 67.0249470321771, 0, 0], ["2012-09-24 18:03:06", 24.8670008700486, 67.0248525079882,0 , 0], ["2012-09-24 18:02:45", 24.8669180588212, 67.0248107048983, , 0], ["2012-09-24 18:01:23", 24.8668220008279, 67.0247808997963, 0, 0], ["2012-09-24 18:00:27", 24.8668936246452, 67.0247197352586,0 , 0] ];
var markers2 = new Array();
var circles = new Array();
var polyLine = null;
function setUp() { 
    for (var i = 5; i < markers.length; i++) 
    {
        var wlat=(markers[i-1][1]+markers[i-2][1]+markers[i-3][1]+markers[i-4][1]+markers[i][1])/15;
        var wlong=(markers[i-1][2]+markers[i-2][2]+markers[i-3][2]+markers[i-4][2]+markers[i][2])/15;
        var imgicon="http://203.170.76.245/fyp/bootstrap/img/green-marker.png";
        var markercmp = new google.maps.Marker({
            position: new google.maps.LatLng(wlat, wlong), 
            map: map, 
            icon : imgicon, 
            title: markers[i][0]
        });
        markercmp.icon="http://203.170.76.245/fyp/bootstrap/img/green-marker.png";
    }
//markers2[i] = new google.maps.LatLng(markers[i][1], markers[i][2]);
    
}
setUp();