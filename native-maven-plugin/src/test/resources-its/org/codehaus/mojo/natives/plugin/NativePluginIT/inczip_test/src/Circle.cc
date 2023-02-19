

#include "Circle.hh"

Circle::Circle() {
}

Point&
Circle::getCenter() {
	return center;
}

int
Circle::getRadius() {
	return radius;
}

void
Circle::setCenter(Point& center) {
	this->center = center;
}

void
Circle::setRadius(int radius) {
	this->radius = radius;
}
