
### Laboratorio 1 - ARSW
### Integrantes: Camilo Fajardo y Andrea Duran

**Parte I - Introducción a Hilos en Java**

2. Complete el método __main__ de la clase CountMainThreads para que:
	
	4. Cambie el incio con 'start()' por 'run()'. ¿Cómo cambia la salida?, por qué?.
		
		Cuando se usa el método run(), la ejecución de los hilos es en orden, es decir, 
        no se hace uso del paralelismo porque apenas acaba el proceso de un hilo, inicia el proceso del otro hilo; 
		encambio cuando se usa start() sí se hace de manera paralela, siendo un programa no determinista
   
**Parte II - Ejercicio Black List Search**

**Parte VI - Ejercicio Black List Search**

1. Según la fórmula, cuando n tiende a infinito, la función tenderá al límite 1/(1-P), por lo que se podría 
	suponer que entre mayor cantidad de hilos podríamos tener mejor desempeño. Sin embargo, no se logra el 
    mejor desempeño porque no siempre es muy notoria la mejoría a mayor cantidad de hilos,
	porque la curva se estabiliza mucho antes de llegar al hilo 500 o 200. Realmente el desempeño entre
	500 hilos y 200 hilos no varía significativamente, por lo que consideramos que es un desperdicio de recursos
	usar esta cantidad de hilos. 

2. Hay una mejora de resultados debido a que se aprovecha en mayor medida los recursos computacionales haciendo uso
	del paralelismo al usar todos los núcleos del computador para realizar tareas sencillas.

3. Sí, al tener 100 computadores, cada uno corriendo un hilo, se hace un uso más provechoso de la Ley de Amdahls debido
	a que las tareas son sencillas y rápidas por lo que se pueden correr al mismo tiempo de forma paralela. Si las tareas 
	fueran muy complejas, el uso de paralelismo no sería la mejor opción porque tardaría más tiempo.

	Sí, porque se optimizarían los recursos al cada núcleo usar un solo hilo, en distintas máquinas


