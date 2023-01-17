windows:
	gcc -c -fPIC src/main/c/*.c
	gcc -shared -o src/main/resources/libs/libfunctions.so *.o
	rm *.o

linux:
	gcc -c -fPIC src/main/c/*.c
	gcc -shared -o src/main/resources/libs/libfunctions_linux.so *.o
	rm *.o

mac:
	gcc -c -fPIC src/main/c/*.c
	gcc -shared -o src/main/resources/libs/libfunctions_mac.so *.o
	rm *.o