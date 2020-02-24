#include "sutherland_hodgman.h"
#include "struct.h"
#include <stdio.h>

//assume we already know the locations of polygon and n points
//we will store the locations as clockwise
//to calculate the cross prodect to distinguish whether inside or outside
//since we know the order is clockwise
//if the result V <= 0
//which means the direction between sp and ep with testing point
//is perpendicular to the X-Y plane and point to the inside of screen
//vice versa
static int inSide(struct vector v,struct point p){
  int result = (v.ep.x - v.sp.x) * (p.y - v.sp.y)
           - (v.ep.y - v.sp.y) * (p.x - v.sp.x);
  return result;
};

int main(){
  struct point t1;
  struct vector v1;
  v1.ep.x = 13;
  v1.ep.y = 5;
  v1.sp.x = 13;
  v1.sp.y = 10;
  t1.x = 10;
  t1.y = 7;
  int value = inSide(v1,t1);
  printf("the value would be %s\n", value);
}
