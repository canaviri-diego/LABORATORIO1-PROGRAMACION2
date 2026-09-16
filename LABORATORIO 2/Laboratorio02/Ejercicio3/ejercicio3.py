import math

class Vector3D:
    def __init__(self, x=0, y=0, z=0):
        self.x = x
        self.y = y
        self.z = z
    def __add__(self, b):
        return Vector3D(self.x + b.x, self.y + b.y, self.z + b.z)
    def __mul__(self, r):
        return Vector3D(r * self.x, r * self.y, r * self.z)
    def __rmul__(self, r):
        return self * r
    def longitud(self):
        return math.sqrt(self.x ** 2 + self.y ** 2 + self.z ** 2)
    def normal(self):
        l = self.longitud()
        return Vector3D(self.x / l, self.y / l, self.z / l)
    def producto_escalar(self, b):
        return self.x * b.x + self.y * b.y + self.z * b.z
    def producto_vectorial(self, b):
        return Vector3D(self.y * b.z - self.z * b.y, self.z * b.x - self.x * b.z, self.x * b.y - self.y * b.x)
    def __str__(self):
        return f"({self.x}, {self.y}, {self.z})"

a = Vector3D(1, 2, 3)
b = Vector3D(4, 5, 6)
print("Vector a =", a)
print("Vector b =", b)
print("a + b =", a + b)
print("3a =", 3 * a)
print("|a| =", a.longitud())
print("Normal de a =", a.normal())
print("a · b =", a.producto_escalar(b))
print("a x b =", a.producto_vectorial(b))
