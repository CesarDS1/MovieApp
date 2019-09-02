# MovieApp
La aplicación esta desarrollada siguiendo la arquitectura clean architecture.

## Domain
Este módulo de tipo  kotlin library contiene las clases que se encargan de realizar alguna petición de datos a al modulo de data, ademas aquí se encuentran los use cases que la capa de presentación necesita para accedera a los datos.

Clases importantes

* GetPopularMoviesUseCase: use case para acceder a la lista de películas más populares
* GetTopRatedMoviesUseCase: se utiliza para obtener la lista de películas en el top rated
* GetUpcomingMoviesUseCase: se utiliza para obtener las películas de la sección upcoming
* GetVideoInformationUseCaseUseCase: mediante el id de la película regres la información de videos relacionados
* SearchMoviesUseCase: mediante una cadena se encarga de obtener una lista de películas que coincidan con la búsqueda

## Data

En esta android library se encuentran las clases que se encargan de acceder a los servicios web para la consulta de la información de las películas, además se utiliza un patrón repository para poder acceder a los datos guardados en cache de las peliculas.

Esté modulo se encarga también de guardar los datos de las peliculas en una base de datos no relacional utilizando Realm.

Clases e interfaces importantes:

* MoviesApi: Aquí se encuentra la defición de las llamadas al api de themoviedb
* RetrofitInstance: Se utiliza para obtener una instancia de Retrofit utilizando la interfaz MoviesApi
* MovieServicesImpl: en esta clase se realizan las peticions a los servicios utilizando la instancia de retrofit y algunos de los métodos definidos en la interfaz MoviesAPI, además se mapean los datos para regresar una lista de peliculas.
* MoviesRealmDataSourceImpl: esta clase se utiliza para obtener una lista de peliculas por categoria de la base de datos de Realm, además contiene una método para guardar una lista de películas.
* MoviesRepositoryImp: en esta clase se implementó un patrón repository para poder obtener o guardar en base de datos información de las peliculas.

## app

Aquí se encuentran las activities y fragments que se muestran al usuario, así como otros componentes de android para mejorar la UI.

Clases importantes:
* MainActivity: es el activity inicial de la aplicación y contiene un fragment que se encarga de mostrar la lista de peliculas por categorias.
* MovieDetailActivity: esta activity contiene la información más importante de cada películas como el título, vide, overview y el poster de la película.
* ListMoviesFragment: contiene la lista de películas por categoría así como un buscados offline
* SearchMoviesFragment: este fragment contiene un EditText para ingresar una cadena de busqueda y así mostrar una lista de posibles resultados.

Cada uno de estos activities y fragments contienen un presenter que se encarga de solicitar a los use cases la información y mediante una view mostrar la información al usuario, se trató de separar la lógica de cada activity y fragment para que fuera mas limpio el código y solo dejar la responsabilidad del ciclo del vida a estos.

Además se agregaron pruebas unitarias a los presenters

### Principio de responsabilidad única:
El principio de responsabilidad única nos dice que cada objeto o clase deber de tener una sola razón de existir, por lo tanto debe de realizar solo una cosa para poder ser reutilizada o testeada.

## Código Limpio
Para mi un código limpio es aquel que puede ser fácil de entender y de extender o refactorizar si es necesario, ademas de que contiene pruebas unitarias y sigue algun patrón de diseño.

