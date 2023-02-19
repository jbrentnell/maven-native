

#include "Point.hh"

Point::Point() {
	x = 0;
	y = 0;
}

int
Point::getX() {
	return x;
}

int
Point::getY() {
	return y;
}

void
Point::setX(int x) {
	this->x = x;
}

void
Point::setY(int y) {
	this->y = y;
}
