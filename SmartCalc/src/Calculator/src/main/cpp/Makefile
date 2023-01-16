.PHONY: all build replication mk install uninstall clean dvi dist tests leaks cpplint gcov_report test
GTESTFLAGS=-lstdc++ -lgtest -lgtest_main

clean:
	rm -rf test *.a *.so *.o *.cfg *.gcda *.gcno *.html *.info *.dSYM report

lib: clean
	g++ -c calc_model.cpp
	ar rcs calc_model.so *.o
	rm -rf *.o
