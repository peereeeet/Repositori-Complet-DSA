MÍNIMO 1 - QT23 - Pere Garcés Sánchez:
La EETAC ha sido requerida para diseñar y construir una aplicación que permita gestionar una flota de drones. Por este motivo, 
propone a sus alumnos de DSA la construcción de una prueba piloto.

Versión 1:

	Parte 1:
	- Manager básico
	- Fachada implementada
	- Clases creadas para cada necesidad
	
	Parte 2:
	-Implementados algunos metodos GET, POST, PUT en clase Service
	
	Pendiente:
	- Terminar tanto fachada como el mánager
	- Acceder a la documentación de la API REST
	- Hacer Testing completo
	
Versión 2:

	Parte 1:
	- Tódos los metodos necesarios han sido completamente implementados 
	- Todos los métodos deberán tener una TRAZA (a nivel de INFO) de LOG4J que 
	  muestre el valor de los parámetros al inicio de los métodos y al final. También 
	  debe contemplarse trazas de otros niveles (ERROR o FATAL) se ha REALIZADO.
	- Implementación de los testos de las clases ManagerImpl y ManagerImplTest. 
	  Cuatro operaciones han sido testeadas.
	
	Parte 2:
	- Se ha conseguido que se pueda acceder a la documentación de la API REST a través de la URL: http://localhost:8080/swagger/.
	- Clase Service perfectamente implementada para usar los metodos de la API REST, como
	  GET, POST, PUT