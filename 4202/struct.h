//P1(x1,y1)
typedef struct point{
  int x;
  int y;
};

//Vector(P1,P2)
typedef struct vector{
  struct point sp;
  struct point ep;
};
