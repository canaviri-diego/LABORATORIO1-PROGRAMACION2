import sys


class EcuacionLineal:
    def __init__(self, a, b, c, d, e, f):
        self.a = a
        self.b = b
        self.c = c
        self.d = d
        self.e = e
        self.f = f

    def tiene_solucion(self):
        return (self.a * self.d - self.b * self.c) != 0

    def get_x(self):
        return (self.e * self.d - self.b * self.f) / (self.a * self.d - self.b * self.c)

    def get_y(self):
        return (self.a * self.f - self.e * self.c) / (self.a * self.d - self.b * self.c)


def leer_float(mensaje, intentos=3):
    for intento in range(intentos):
        valor = input(mensaje)
        try:
            return float(valor)
        except ValueError:
            restantes = intentos - intento - 1
            if restantes > 0:
                print(f"Valor inválido. Ingrese un número. Intentos restantes: {restantes}")
    print("Se alcanzó el número máximo de intentos. Programa finalizado.")
    sys.exit()


if __name__ == "__main__":
    a = leer_float("Ingrese el valor de a: ")
    b = leer_float("Ingrese el valor de b: ")
    c = leer_float("Ingrese el valor de c: ")
    d = leer_float("Ingrese el valor de d: ")
    e = leer_float("Ingrese el valor de e: ")
    f = leer_float("Ingrese el valor de f: ")

    ecuacion = EcuacionLineal(a, b, c, d, e, f)

    if ecuacion.tiene_solucion():
        print("x =", ecuacion.get_x(), ", y =", ecuacion.get_y())
    else:
        print("La ecuación no tiene solución")
