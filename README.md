KungFooZoo is a simple game which recreates rock-paper-scissors using a Microsoft Kinect.

KungFooZoo functions by using the [FAAST project](http://projects.ict.usc.edu/mxr/faast) (University of Southern California) which in turn relies on [OpenNI](http://www.openni.org/) and [SensorKinect project](https://github.com/avin2/SensorKinect).

Full documentation, including game rules can be found in the Docs folder.


Installation
============

* Run Install/01-OpenNI-Win32-1.1.0.41-Dev.msi
* Run Install/02-NITE-Win32-1.3.1.5-Dev.msi (Use license code: 0KOIk2JeIBYClPWVnMoRKn5cdY4= for trial version)
* Run Install/03-SensorKinect-Win-OpenSource32-5.0.1.msi

You should now be able to run FAAST/FAAST.exe without complaint.

If you have any problems with the FAAST program complaining about not finding .DLL files you might also want to run Install/vcredist_x86.exe to make sure your C++ redistributable files are up to date.


Usage
=====

Run Fast by opening the FAAST/FAAST.exe program. FAAST will interpret the commands specified in its config file as inputs to any of the frontend programs.

Pick one of the two frontend program options:
* Java - Runs as a regular program on most operating systems
* Web - Runs as a webpage powered by JQuery. You should be able to open the index.html file in any browser without needing additional software such as a WAMP stack.


Java Usage
----------

The Java client is a stand alone game which works alongside FAAST to provide the core gameplay. 

* Open the FAAST folder and rename FAAST.Java.cfg to FAAST.cfg to use the Java clients FAAST configuration. You only need to do this once
* Run FAAST
* Connect to the Kinect
* Start the emulator
* Run the Java/KFP.bat file to start the Java client

It is important to keep the focus on to the Java client program as FAAST will otherwise be sending keystrokes elsewhere.


Web Usage
---------

* Open the FAAST folder and rename FAAST.Java.cfg to FAAST.cfg to use the Java clients FAAST configuration. You only need to do this once
* Run FAAST
* Connect to the Kinect
* Start the emulator
* Open the Web/index.html in any browser

Like the Java client it is important to keep the focus on the webbrowser so FAAST can correctly pass the keystrokes to the frontend webpage.
