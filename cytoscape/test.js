

var cytoscape = require('cytoscape');



var cy = cytoscape({
	// very commonly used options:
	container: undefined,
	elements: [ /* ... */ ],
	style: [ /* ... */ ],
	layout: { name: 'grid' /* , ... */ },

	// initial viewport state:
	zoom: 1,
	pan: { x: 0, y: 0 },

	// interaction options:
	minZoom: 1e-50,
	maxZoom: 1e50,
	zoomingEnabled: true,
	userZoomingEnabled: true,
	panningEnabled: true,
	userPanningEnabled: true,
	boxSelectionEnabled: false,
	selectionType: 'single',
	touchTapThreshold: 8,
	desktopTapThreshold: 4,
	autolock: false,
	autoungrabify: false,
	autounselectify: false,

	// rendering options:
	headless: false,
	styleEnabled: true,
	hideEdgesOnViewport: false,
	hideLabelsOnViewport: false,
	textureOnViewport: false,
	motionBlur: false,
	motionBlurOpacity: 0.2,
	wheelSensitivity: 1,
	pixelRatio: 'auto',
	renderer: { /* ... */ }
});



cy.add({
    group: "nodes",
    data: { weight: 75 },
    position: { x: 200, y: 200 }
});


	var http = require('http');
	http.createServer(function (req, res) {
		res.writeHead(200, {'Content-Type': 'text/plain'});
		res.add("FUCK");
		res.add("<body><div id='cy'></div></body>");
	}).listen(1337, "127.0.0.1");
	console.log('Server running at http://127.0.0.1:1337/');
