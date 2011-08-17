$(function() {
	var keys = [];
	var animals = [
		{
			name: 'Tiger',
			img: 'images/tiger.gif',
			keys: ["1".charCodeAt(0), "2".charCodeAt(0), "9".charCodeAt(0), "0".charCodeAt(0)]
		},
		{
			name: 'Bear',
			img: 'images/bear.jpg',
			keys: ["Q".charCodeAt(0), "W".charCodeAt(0), "P".charCodeAt(0), "O".charCodeAt(0)]
		},
		{
			name: 'Monkey',
			img: 'images/monkey.jpg',
			keys: ["A".charCodeAt(0), "S".charCodeAt(0), "L".charCodeAt(0), "K".charCodeAt(0)]
		},
		{
			name: 'Monkey Reversed',
			symlink: 2, // Really just a monkey reversed
			keys: ["Z".charCodeAt(0), "X".charCodeAt(0), "C".charCodeAt(0), "V".charCodeAt(0)]
		}
	];
	var animationspeed = 1000;
	var activeanimal = -1;
	var companimal = -1;
	var ignoreselect = 0;

	$(document).bind('keydown', function(e) {
		var code = (e.keyCode ? e.keyCode : e.which);
		if (keys[code] == 1) // We already know about this key being down
			return;
		keys[code] = 1;
		if (ignoreselect)
			return;
		console.log('Key hold for ' + code + ' - ' + String.fromCharCode(code));
		for (var a = 0; a < animals.length; a++) {
			var passed = 0;
			for (var k = 0; k < animals[a]['keys'].length; k++) {
				if (keys[animals[a]['keys'][k]] == 1) {
					passed++;
				} else
					break;
			}
			if (passed == animals[a]['keys'].length) { // Activate this animal
				console.log('Trigger for ' + animals[a]['name']);
				if ('symlink' in animals[a]) {
					a = animals[a]['symlink'];
					console.log('Symlink to real animal -> ' + animals[a]['name']);
				}

				activeanimal = a;
				ignoreselect = 1;
				$('#play_0').hide();
				$('#play_0').html('<img src="' + animals[a]['img'] + '"/>');
				$('#play_0').fadeIn(animationspeed, function() {
					ignoreselect = 0;	

					// Computer plays
					companimal = Math.floor(Math.random() * 3);
					$('#play_1').hide();
					$('#play_1').html('<img src="' + animals[companimal]['img'] + '"/>');
					$('#play_1').fadeIn(animationspeed);

					if (activeanimal == companimal) { // Draw test
						$('#result').html('Draws with');
					} else if ( // Win test (Unrolled logic for the sake of simplicity)
						(activeanimal == 0 && companimal == 1) ||
						(activeanimal == 1 && companimal == 2) ||
						(activeanimal == 2 && companimal == 0)
					) {
						$('#result').html('Beats');
					} else {
						$('#result').html('Loses to');
					}
				});
				break;
			}
		}
	});
	$(document).bind('keyup', function(e) {
		var code = (e.keyCode ? e.keyCode : e.which);
		console.log('UP key ' + code);
		keys[code] = 0;
		if (ignoreselect)
			return;
		activeanimal = -1;
	});

	// Setup img pre-loader
	for (var a = 0; a < animals.length; a++)
		if (animals[a]['img'])
			$('#preload').append('<img src="' + animals[a]['img'] + '"/>');
});
