

#ifndef CIRCLE_HH
#define CIRCLE_HH

#include "Point.hh"

class Circle {
 private:
	Point center;
	int radius;

 public:
	Circle();

	Point& getCenter();

	void setCenter(Point& center);

	int getRadius();

	void setRadius(int radius);

};

#endif
