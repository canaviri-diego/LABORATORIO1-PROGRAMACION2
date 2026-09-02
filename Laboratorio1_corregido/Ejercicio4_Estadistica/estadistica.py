import math
import sys


class Estadistica:
    def __init__(self, datos):
        self.datos = datos

    def promedio(self):
        return sum(self.datos) / len(self.datos)

    def desviacion(self):
        media = self.promedio()
        suma = sum((x - media) ** 2 for x in self.datos)
        return math.sqrt(suma / (len(self.datos) - 1))


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
    datos = []
    for i in range(1, 11):
        datos.append(leer_float(f"Ingrese el número {i}: "))

    estadistica = Estadistica(datos)
    print("El promedio es %.2f" % estadistica.promedio())
    print("La desviación estándar es %.5f" % estadistica.desviacion())
