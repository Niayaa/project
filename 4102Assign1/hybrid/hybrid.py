import cv2
import numpy as np
import math

img1 = cv2.imread('left.png')
img2 = cv2.imread('right.png')

#Gaussian Kernel
sig_H=0.5
sig_L=20
lsize=20
hsize=4
def gau_fun(x,y,sig):
    value=(1/(2*math.pi*sig**2)) * math.exp(-(x**2 + y**2)/(2*sig**2))
    return value

def gau_Blur(hsize,sigma):
    w= np.zeros((hsize,hsize))
    for i in range(hsize):
        for j in range(hsize):
            w[i,j] = gau_fun(i-hsize,j-hsize,sigma)

    w /= w.sum()
    return w

def convolution(image,kernel):
    m,n=kernel.shape
    print(m,n)
    if(m==n):
        y,x = image.shape[0],image.shape[1]
        y=y-m+1
        x=x-m+1
        new = np.zeros((image.shape))
        for i in range(y):
            for j in range(x):
                for k in range(3):
                    new[i,j,k]=np.sum(image[i:i+m,j:j+m,k]*kernel)
    return new

def low_pass(image,sigma,hsize):
    gaukernel = gau_Blur(hsize,sigma)
    gautmp = convolution(image,gaukernel)
    return gautmp

def high_pass(image,sigma,hsize):
    gaukernel = gau_Blur(hsize,sigma)
    gautmp = convolution(image,gaukernel)
    new = image - gautmp
    return new

def cross_correlation(image1,image2,sigH,sigL,hsize,lsize):
    hig = high_pass(image1,sigH,hsize)
    low = low_pass(image2,sigL,lsize)
    borderl = int(lsize*0.8)
    borderh = int(hsize*0.5)
    croppedl = low[0:-(borderl+1),0:-(borderl+1)]
    hig = cv2.resize(hig, (croppedl.shape[1],croppedl.shape[0]),cv2.INTER_AREA)
    new = croppedl + hig
    new = new[0:-(borderh+1),0:-(borderh+1)]
    return new
#cv2.imshow('ORIGINAL',img0)
#cv2.imshow('SOB',sob)
result = cross_correlation(img1,img2,sig_H,sig_L,hsize,lsize)

cv2.imwrite('hybrid.png',result)

cv2.waitKey(0)
cv2.destroyAllWindows()
