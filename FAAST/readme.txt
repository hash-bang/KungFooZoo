Flexible Action and Articulated Skeleton Toolkit (FAAST) 0.08
University of Southern California Institute for Creative Techologies
Author: Evan A. Suma, Ph.D.
Email: faast@ict.usc.edu
Website: http://projects.ict.usc.edu/mxr/faast

Copyright (c) 2011, University of Southern California
All rights reserved.
Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
	* Redistributions of source code must retain the above copyright
	  notice, this list of conditions and the following disclaimer.
	* Redistributions in binary form must reproduce the above copyright
	  notice, this list of conditions and the following disclaimer in the
	  documentation and/or other materials provided with the distribution.
	* Neither the name of the University of Southern California nor the
	  names of its contributors may be used to endorse or promote products
	  derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE UNIVERSITY OF SOUTHERN CALIFORNIA  BE LIABLE FOR ANY
DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.



Developed at the University of Southern California Institute for Creative Technologies in collaboration with Belinda Lange, Skip Rizzo, David Krum, and Mark Bolas.

FAAST is middleware to facilitate integration of full-body control with games and VR applications. The toolkit relies upon software from OpenNI and PrimeSense to track the user's motion using the PrimeSensor or the Microsoft Kinect sensors. FAAST includes a custom VRPN server to stream the user's skeleton over a network, allowing VR applications to read the skeletal joints as trackers using any VRPN client. Additionally, the toolkit can also emulate keyboard input triggered by body posture and specific gestures. This allows the user add custom body-based control mechanisms to existing off-the-shelf games that do not provide official support for depth sensors. 

FAAST is free to use and distribute. If you use FAAST to support your research project, we request that any publications resulting from the use of this software include a reference to the toolkit.  Additionally, we encourage you to send us an email about your project, so we can compile a list of projects that use FAAST. This will be help us pursue funding to maintain the software and add new functionality.  The publication to reference is:

E. Suma, B. Lange, A. Rizzo, D. Krum, and M. Bolas, "FAAST: The Flexible Action and Articulated Skeleton Toolkit," Proceedings of IEEE Virtual Reality, pp. 247-248, 2011.

Please see the project website for installation and usage instructions.  This development version of FAAST is currently available for Windows only.


Version History
---------------

0.08
- Upgraded OpenNI version to 1.1.0.41
- Upgraded NITE version to 1.3.1.5
- Removed sensor resolution settings, since this is now set automatically by OpenNI.
- Added capability to save/load calibration files.
- Added a new key_type event

0.07
- Mouse control has been removed from the action list, and is now configured using a GUI.
- Mouse control is more sophisticated, and now can be controlled using the hands without calibration (using a focus gesture).
- Multiple monitor support for absolute mouse control.
- Smoothing on skeleton joints and hand positions. This allows for much more precise mouse control.
- Multiple skeleton tracking modes: full body, upper body only, and lower body only
- Ability to save calibration to memory and load to different users.
- New actions: body_turn, crouch
- The following output events are now supported: key_press, key_hold, mouse_click, mouse_double_click, mouse_hold
- Actions can also be bound to the following FAAST control events: pause, resume, stop
- Tooltips that explain various GUI functions and parameters.

0.06
Added mouse support.  Fixed issue with keyboard events not being registered in games that use DirectInput.  Also removed key_special command; special keys now all have their own plain text keywords instead of having to use virtual key codes.

0.05
Minor bugfix release to fix compability with OpenNI's new Kinect drivers.  Changed skeleton joint units in VRPN from millimeters to meters, in order to fit with the VRPN standard.  Please note that if you are upgrading from an older version you will need to uninstall your current version of OpenNI, NITE, and the sensor driver, and then reinstall them using the new versions listed on our website.

0.04
Added support for 10 new actions, including jump, walk, and directional foot poses.  Fixed sensor initialization so that FAAST won't crash if the sensor is not hooked up or NITE is configured incorrectly.

0.03
Initial public release.