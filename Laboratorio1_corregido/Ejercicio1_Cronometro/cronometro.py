import time
import random

class Cronometro:
    def __init__(self):
        self.__inicia = 0
        self.__finaliza = 0

    def inicia(self):
        self.__inicia = int(time.time() * 1000)

    def detener(self):
        self.__finaliza = int(time.time() * 1000)

    def lapsoDeTiempo(self):
        return self.__finaliza - self.__inicia

if __name__ == "__main__":
    n = 1000000
    print(f"Generando {n} números aleatorios...")
    numeros = [random.randint(0, 999999) for _ in range(n)]

    cronometro = Cronometro()
    print("Ordenando los elementos...")
    
    cronometro.inicia()
    numeros.sort()
    cronometro.detener()

    print(f"\n¡Proceso completado!")
    print(f"Tiempo de ordenación: {cronometro.lapsoDeTiempo()} ms")
