IP Geolocation es una aplicación que permite obtener información de una IP.

Para poder ejecutar dicha aplicación se necesita:
* Java 16
* Apache Maven 3.9.1
* PostgreSQL
* Docker

Se tendrán las siguientes opciones para ejecutar dicha aplicación incluída en un contenedor docker:
* *traceip “ip”*: Devolverá toda la información referida a una ip.

Ejemplo:

###############################################

IP: 116.206.49.254, Fecha Actual: 18/04/2023 09:39:46

País: Bangladesh

Código ISO: BD

Idiomas:  Bengali

Moneda: "Bangladeshi taka" (1 "Bangladeshi taka" = 0.009414 U$S)

Hora: 12:40:30 (UTC) 18:40:30 (UTC+06:00) 

###############################################


* *statistics*: Devolverá estadísticas de las consultas realizadas a la aplicación

Ejemplo:
 
--------------------------------
 
 ESTADÍSTICAS         
 
 Distancia promedio de las ejecuciones         

--------------------------------

| PAÍS       | DISTANCIA | INVOCACIONES |

--------------------------------

| Bangladesh | 16773.496106556813 | 0001 |

| Argentina  | 2.314832340544402 | 0012 |

| India      | 15220.818142311036 | 0001 |

| United States | 8598.15887603888 | 0001 |

--------------------------------

* *statisticNearestDistance*: Distancia más cercana a Buenos Aires desde la cual se haya consultado el servicio

Ejemplo:

--------------------------------

Distancia más cercana a Buenos Aires desde la cual se haya consultado el servicio 

| PAÍS       | DISTANCIA |

| Argentina  | 2.314832340544402 |

--------------------------------

* *statisticFurthestDistance*: Distancia más lejana a Buenos Aires desde la cual se haya consultado el servicio

Ejemplo:

Distancia más lejana a Buenos Aires desde la cual se haya consultado el servicio 

| PAÍS       | DISTANCIA |

| Bangladesh | 16773.496106556813 |

--------------------------------

* *statisticAverageInvocations*: Distancia promedio de todas las ejecuciones que se hayan hecho del servicio. 

Ejemplo:

--------------------------------

Promedio de todas las invocaciones: 10148.696989311818--------------------------------

*default*: La aplicación por default nos devuelve las estadísticas de las consultas realizadas al servicio como en *statistics*.

La manera para ejecutarlo es la siguiente: (contando previamente con las instalaciones antes mencionadas y descargado el código presente)

1. Copiar el archivo apikeys.properties (provisto por correo electrónico) que contiene las keys para poder ejecutar las APIs externas a \ip_geolocation\src\main\java\com\ipgeolocation\properties 

2. Ejecutar las queries para crear las tablas (cuyos scripts fueron enviados también por correo electrónico)
3. En la carpeta raíz del proyecto: (ipgeolocation) ejecutar: 
`docker build -t ipgeolocation`
4. Luego, ejecutar:
	`docker run --rm -e DATASOURCE_URL='jdbc:postgresql://host.docker.internal:5432/ip_geolocation' ipgeolocation traceip “119.42.39.254” `


