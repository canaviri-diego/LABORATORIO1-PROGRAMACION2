import math

class AlgebraVectorial:
    def __init__(self, *args):
        if len(args) == 0:
            self.x = 0
            self.y = 0
            self.z = 0
        elif len(args) == 2:
            self.x = args[0]
            self.y = args[1]
            self.z = 0
        elif len(args) == 3:
            self.x = args[0]
            self.y = args[1]
            self.z = args[2]
    def modulo(self):
        return math.sqrt(self.x ** 2 + self.y ** 2 + self.z ** 2)
    def perpendicular(self, *args):
        if len(args) == 1:
            b = args[0]
            return self.x * b.x + self.y * b.y + self.z * b.z == 0
        bx = args[0]
        by = args[1]
        return self.x * bx + self.y * by == 0
    def paralela(self, b):
        return self.x * b.y == self.y * b.x and self.x * b.z == self.z * b.x and self.y * b.z == self.z * b.y
    def proyeccion(self, b):
        producto = self.x * b.x + self.y * b.y + self.z * b.z
        mb = b.modulo()
        return AlgebraVectorial(producto / mb ** 2 * b.x, producto / mb ** 2 * b.y, producto / mb ** 2 * b.z)
    def componente(self, b):
        producto = self.x * b.x + self.y * b.y + self.z * b.z
        return producto / b.modulo()

a = AlgebraVectorial(2, 4, 0)
b = AlgebraVectorial(-2, 1, 0)
print("Vector a:", a.x, a.y, a.z)
print("Vector b:", b.x, b.y, b.z)
print("¿Son perpendiculares?:", a.perpendicular(b))
print("¿Son paralelos?:", a.paralela(b))
p = a.proyeccion(b)
print("Proyección de a sobre b:", p.x, p.y, p.z)
print("Componente de a en b:", a.componente(b))
