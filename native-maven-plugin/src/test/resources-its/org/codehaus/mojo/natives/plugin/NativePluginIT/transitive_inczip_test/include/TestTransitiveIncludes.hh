

#ifndef TEST_TRANSITIVE_INCLUDES_HH
#define TEST_TRANSITIVE_INCLUDES_HH

#include "Circle.hh"
#include "Point.hh"

class TestTransitiveIncludes {
 private:
	Point point1;
	Circle circle;

 public:
	Point& getPoint();

	Circle& getCircle();

};

#endif
