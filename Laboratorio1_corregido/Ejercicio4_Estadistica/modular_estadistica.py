import math
import sys

datos = []


def promedio():
    return sum(datos) / len(datos)


def desviacion():
    media = promedio()
    suma = sum((x - media) ** 2 for x in datos)
    return math.sqrt(suma / (len(datos) - 1))


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
    for i in range(1, 11):
        datos.append(leer_float(f"Ingrese el número {i}: "))

    print("El promedio es %.2f" % promedio())
    print("La desviación estándar es %.5f" % desviacion())
