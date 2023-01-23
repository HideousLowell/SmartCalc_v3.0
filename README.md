# SmartCalc_v3.0
Graphical calculator with the ability to build function graphs  
Model–view–viewmodel pattern  
The core of computing in C-language
_____
## Technology stack
``Java 8`` ``JavaFX`` ``JNA`` ``Maven``
____
## Information

There is installer for MacOS in, so you can install and use it simple with JRE 8+ on board  
``libfunctions.so`` compiled for MacOS, for another OS - in progress  
You can compile the library for your OS using the following code (from project root)  
``gcc -c -fPIC src/main/c/*.c``  
``gcc -shared -o src/main/resources/libs/libfunctions.so *.o``  
``rm *.o``   
____



