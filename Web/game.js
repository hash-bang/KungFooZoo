$(function() {
	var keys = [];
	var animals = [
		{
			name: 'Tiger',
			keys: [65, 66, 67]
		},
		{
			name: 'Panda',
			keys: [99, 100, 101]
		},
		{
			name: 'Turtle',
			keys: [102, 103, 104]
		}
	];
	var activeanimal = -1;

	$(document).bind('keydown', function(e) {
		var code = (e.keyCode ? e.keyCode : e.which);
		if (keys[code] == 1) // We already know about this key being down
			return;
		keys[code] = 1;
		console.log('DN key ' + code);
		for (var a = 0; a < animals.length; a++) {
			var passed = 0;
			for (var k = 0; k < animals[a]['keys'].length; k++) {
				console.log('Check for animal ' + a + ' @ key ' + animals[a]['keys'][k] + ' == ' + keys[animals[a]['keys'][k]]);
				if (keys[animals[a]['keys'][k]] == 1) {
					passed++;
				} else
					break;
			}
			if (passed == animals[a]['keys'].length) { // Activate this animal
				console.log('Trigger for animal ' + animals[a]['name']);
				activeanimal = a;
				break;
			}
		}
	});
	$(document).bind('keyup', function(e) {
		var code = (e.keyCode ? e.keyCode : e.which);
		console.log('UP key ' + code);
		keys[code] = 0;
		activeanimal = -1;
	});
});
