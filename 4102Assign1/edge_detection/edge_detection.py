import cv2
import numpy as np
import math
#initialize
imgori = cv2.imread('cat2.png',0)
#grey = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY())
#cv2.imwrite('greyCat2.png',grey)

#Gaussian Kernel
sigma=2
hsize=2*(math.ceil(3*sigma))+1

def gau_fun(x,y,sig):
    return (1/(2*math.pi*sig**2)) * math.exp(-(x**2 + y**2)/(2*sig**2))

def gau_fil(hsize,sig):
    w= np.zeros((hsize,hsize))
    for i in range(hsize):
        for j in range(hsize):
            w[i,j] = gau_fun(i-hsize,j-hsize,sigma)
    w /= w.sum()
    return w

#def gaussianFilter(image,sigma):

#img = np.random.random([5,5])

#sobel Operater
def sobel(image):
    #pick data from original image
    y,x = image.shape
    new = np.zeros((y,x))
    newX = np.zeros(image.shape)
    newY = np.zeros(image.shape)
    #sobel kernel
    sobel_X = np.array([[-1,0,1],[-2,0,2],[-1,0,1]])
    sobel_Y = np.array([[-1,-2,-1],[0,0,0],[1,2,1]])
    for i in range(y-2):
        for j in range(x-2):
            #doing convolution(image, kernel)
            newX[i+1,j+1] = abs(np.sum(image[i:i+3,j:j+3]*sobel_X))
            newY[i+1,j+1] = abs(np.sum(image[i:i+3,j:j+3]*sobel_Y))
            new [i+1,j+1] = (newX[i+1,j+1]*newX[i+1,j+1] + newY[i+1,j+1]*newX[i+1,j+1])**0.5
    return np.uint8(new)

def convolution(image,kernel):
    m,n=kernel.shape
    print(m,n)
    if(m==n):
        y,x = image.shape
        y=y-m+1
        x=x-m+1
        new = np.zeros((y,x))
        for i in range(y):
            for j in range(x):
                new[i,j]=np.sum(image[i:i+m, j:j+m]*kernel)
    return new
gaukernel = gau_fil(hsize,sigma)
#print(gaukernel)
gau = convolution(imgori, gaukernel)
sob = sobel(gau)

cv2.imwrite('gradient_orientation.jpg',gau)
cv2.imwrite('gradient_magnitue.jpg',sob)
#cv2.imshow('SOB',sob)

cv2.waitKey(0)
cv2.destroyAllWindows()
