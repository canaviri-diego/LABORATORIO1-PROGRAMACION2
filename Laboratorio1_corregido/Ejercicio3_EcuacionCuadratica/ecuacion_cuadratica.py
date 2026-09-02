import math
import sys


class EcuacionCuadratica:
    def __init__(self, a, b, c):
        self.a = a
        self.b = b
        self.c = c

    def get_discriminante(self):
        return self.b ** 2 - 4 * self.a * self.c

    def get_raiz1(self):
        discriminante = self.get_discriminante()
        if discriminante < 0:
            return 0
        return (-self.b + math.sqrt(discriminante)) / (2 * self.a)

    def get_raiz2(self):
        discriminante = self.get_discriminante()
        if discriminante < 0:
            return 0
        return (-self.b - math.sqrt(discriminante)) / (2 * self.a)


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

    ecuacion = EcuacionCuadratica(a, b, c)
    discriminante = ecuacion.get_discriminante()

    if discriminante > 0:
        print("La ecuación tiene dos raíces", ecuacion.get_raiz1(), "y", ecuacion.get_raiz2())
    elif discriminante == 0:
        print("La ecuación tiene una raíz", ecuacion.get_raiz1())
    else:
        print("La ecuación no tiene raíces reales")
