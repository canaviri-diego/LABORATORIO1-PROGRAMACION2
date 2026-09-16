import math

class MiPunto:
    def __init__(self, x=0, y=0):
        self.__x = x
        self.__y = y
    def getX(self):
        return self.__x
    def getY(self):
        return self.__y
    def distancia(self, *args):
        if len(args) == 1:
            x2 = args[0].getX()
            y2 = args[0].getY()
        else:
            x2 = args[0]
            y2 = args[1]
        return math.sqrt((self.__x - x2) ** 2 + (self.__y - y2) ** 2)

p1 = MiPunto()
p2 = MiPunto(10, 30.5)
print("Punto 1:", p1.getX(), p1.getY())
print("Punto 2:", p2.getX(), p2.getY())
print("Distancia:", p1.distancia(p2))
print("Distancia:", p1.distancia(10, 30.5))
