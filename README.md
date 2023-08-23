
### Laboratorio 1 - ARSW
### Integrantes: Camilo Fajardo y Andrea Duran

**Parte I - Introducción a Hilos en Java**

2. Complete el método __main__ de la clase CountMainThreads para que:
	
	4. Cambie el incio con 'start()' por 'run()'. ¿Cómo cambia la salida?, por qué?.
		
		Cuando se usa el método run(), la ejecución de los hilos es en orden, es decir, 
        no se hace uso del paralelismo porque apenas acaba el proceso de un hilo, inicia el proceso del otro hilo; 
		encambio cuando se usa start() sí se hace de manera paralela, siendo un programa no determinista
   



**Parte II - Ejercicio Black List Search**
1. Para mejorar la eficiencia, podríamos introducir una comunicación entre los hilos que les permita detenerse tan 
   pronto como se alcance el umbral necesario de ocurrencias. Para lograr esto, podríamos usar una barrera de comunicación, 
   como un CountDownLatch, que permita que todos los hilos detengan sus búsquedas una vez que se haya alcanzado el umbral.
   Otra técnica podría ser "Dividir y Conquistar" donde se dividiría el conjunto de servidores en segmentos más pequeños,
   donde cada segmento buscaría en paralelo. A medida que los hilos encuentren las ocurrencias necesarias, podrían detenerse 
   y dejar de buscar en ese segmento, reduciendo así la cantidad total de búsquedas realizadas. En este último caso, se haría
   uso de 'AtomicInteger' para contar las ocurrencias.


**Parte III - Evaluación de Desempeño**

La dirección IP usada fue: 202.24.34.55

Y la salida de ejecutar la búsqueda para esta dirección IP es:


1. Un solo hilo.

2. Tantos hilos como núcleos de procesamiento (haga que el programa determine esto haciendo uso del API Runtime).

3. Tantos hilos como el doble de núcleos de procesamiento.
 
4. 50 hilos.

5. 100 hilos.


**Parte VI - Ley de Amdahls**

1. Si consideramos un escenario en el que P es 0.8 (80% del algoritmo es paralelizable) y calculamos el mejoramiento teórico del desempeño para diferentes valores de n:

    - Con n = 1, S(1) = 1 (Sin paralelismo)
    - Con n = 2, S(2) =  1/0.6 = 1.67 (un mejoramiento del 67% aproximadamente)
    - Con n = 500, S(500) = 1/0.0016 = 4.96 (un mejoramiento del 396% aproximadamente)
    - Con n = 200, S(200) = 1/0.004 = 4.95 (un mejoramiento del 395% aproximadamente)
   
   Podemos notar que a medida que aumenta n, el mejoramiento teórico del desempeño se acerca a un valor límite. Sin embargo, el incremento en el desempeño se vuelve cada vez más pequeño. 
   En el caso de n=500, aunque el mejoramiento es considerable, no es proporcional al número de hilos utilizados.

    En el caso de 200 hilos, el mejoramiento del desempeño es casi idéntico al obtenido con 500 hilos, lo que ilustra cómo el incremento en el número de hilos más allá de un cierto punto 
    no resulta en mejoras significativas de desempeño, debido a la fracción no paralelizable del algoritmo según la Ley de Amdahl.

    Realmente el desempeño entre 500 hilos y 200 hilos no varía significativamente, por lo que consideramos que es un desperdicio de recursos
    usar esta cantidad de hilos. Debemos ser cuidadosos en qué número de hilos deja de ser significativa la cantidad.



2. Hay una mejora de resultados debido a que se aprovecha al máximo los recursos computacionales haciendo uso
    del paralelismo al usar todos los núcleos del computador (cada núcleo está ocupado con un hilo) para realizar tareas sencillas y no hay tiempo de espera
    para acceder al recurso.

   Por otro lado, cuando se utiliza el doble de hilos que el número de núcleos de la CPU, se está intentando realizar un nivel aún mayor de paralelismo que la cantidad de recursos 
   físicos disponibles. Esto puede resultar en un escenario en el que los hilos tengan que competir por el acceso a los núcleos de la CPU. En este caso, algunos hilos podrían 
   experimentar retrasos mientras esperan su turno para ser ejecutados en un núcleo disponible. Esto se conoce como "overhead" de planificación y conmutación de hilos. Es decir,
   puede generar sobrecarga debido a la gestión de más hilos que núcleos, lo que podría resultar en retrasos y disminución del rendimiento en comparación con el uso óptimo de hilos.
 

 
3.
    1. **Un hilo en cada una de las 100 máquinas:** Sí, al tener 100 computadores, cada uno corriendo un hilo, se hace un uso más provechoso de la Ley de Amdahls debido
    a que las tareas son sencillas y rápidas por lo que se pueden correr al mismo tiempo de forma paralela. Sin embargo, esta distribución introduce otros desafíos como la 
    comunicación y la coordinación entre las máquinas.

    2. **c hilos en 100/c máquinas distribuidas:** Sí, porque se optimizarían los recursos al cada núcleo usar un solo hilo, en distintas máquinas, se aprovecha la capacidad
    de procesamiento paralelo de cada máquina. Si el algoritmo se puede dividir en partes que pueden ejecutarse simultáneamente en cada máquina, el desempeño podría mejorar 
    en comparación con el escenario anterior.


