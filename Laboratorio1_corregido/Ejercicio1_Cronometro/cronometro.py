import time
import random

class Cronometro:
    def __init__(self):
        self.__inicia = int(time.time() * 1000)
        self.__finaliza = 0

    def get_inicia(self):
        return self.__inicia

    def get_finaliza(self):
        return self.__finaliza

    def inicia(self):
        self.__inicia = int(time.time() * 1000)

    def detener(self):
        self.__finaliza = int(time.time() * 1000)

    def lapsoDeTiempo(self):
        return self.__finaliza - self.__inicia

def ordenar_por_seleccion(arreglo):
    n = len(arreglo)
    for i in range(n - 1):
        menor = i
        for j in range(i + 1, n):
            if arreglo[j] < arreglo[menor]:
                menor = j
        arreglo[i], arreglo[menor] = arreglo[menor], arreglo[i]

if __name__ == "__main__":
    n = 100000
    numeros = [random.randint(0, 999999) for _ in range(n)]
    
    muestra = numeros[:1000]
    cronometro_m = Cronometro()
    cronometro_m.inicia()
    ordenar_por_seleccion(muestra)
    cronometro_m.detener()
    
    tiempo_muestra = cronometro_m.lapsoDeTiempo()
    estimado_ms = tiempo_muestra * ((n / 1000) ** 2)
    estimado_horas = estimado_ms / 3600000
    
    print(f"Tiempo aproximado estimado para {n} elementos: {estimado_ms:.0f} ms ({estimado_horas:.2f} horas)")
    print("Iniciando la ordenación real de 100,000 elementos en segundo plano...")
    print("Por favor, no cierres la terminal...")
    
    cronometro = Cronometro()
    cronometro.inicia()
    
    ordenar_por_seleccion(numeros)
    
    cronometro.detener()
    
    print("\n¡Proceso real completado!")
    print("Tiempo de ordenación:", cronometro.lapsoDeTiempo(), "ms")
