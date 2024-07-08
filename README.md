REST приложение Spring + Hibernate.


Actor:

GET http://localhost:8080/api/actors  -получить список всехактеров
GET http://localhost:8080/api/actors/{id} - получить актера по id
POST http://localhost:8080/api/actors - добавить актера
{ 
	«firstName»: «actor firstname»,
   	«lastName»: «actor lastname»,
  	 «phoneNumber»: «phoneNumber» 
}

Получим:
{ 
	«id»: (присвоенный id)
	«firstName»: «firstname»,
   	«lastName»: «lastname»,
  	 «phoneNumber»: «3548765846» 
}

PUT http://localhost:8080/api/actors - изменить данные актера
 { 
	«id»: (существующий id)
	«firstName»: «another firstname»,
   	«lastName»: «another lastname»,
  	 «phoneNumber»: «4634635653» 
}

DELETE http://localhost:8080/api/actors/{id} - удалить актера по id

«Actor with id (указанный id) deleted»

Если актер с таким id не существует для методов GET, PUT, DELETE будет обработаны исключение и выведено:

{
    "info": "Actor with id (указанный id) not found"
}

POST http://localhost:8080/api/actors/{actorId}/addPerformance/{performanceId} - добавить спектакль актеру

Если удалось, то сообщение "Performance with id (performanceId) could not be added to actor with id (actorId)"
Если 
Если нет, то "Performance with id (performanceId) could not be removed from actor with id (actorId)"

DELETE http://localhost:8080/api/actors/{actorId}/removePerformance/{performanceId} - удалить спектакль у актера

Если удалось удалить, то "Performance with id (performanceId) removed from actor with id (actorId)"
Если нет, то "Performance with id (performanceId) could not be removed from actor with id (actorId)"


Hall:

GET http://localhost:8080/api/halls - получить список всех залов
GET http://localhost:8080/api/halls/{id} - получить зал по id
POST http://localhost:8080/api/halls -добавить зал

{
	«name»: «name»,
	«address»: «address»,
	«phoneNumber»: «34236487236»
}

Получим:
{
	«id»: (присвоенный id),
	«name»: «name»,
	«address»: «address»,
	«phoneNumber»: «34236487236»
}

PUT  http://localhost:8080/api/halls 

{
	«id»: (существующий id),
	«name»: «another name»,
	«address»: «another address»,
	«phoneNumber»: «356546546»
}

DELETE http://localhost:8080/api/halls/{id} - удалить по id

«Hall with id (указанный id) deleted»

Для методов GET, PUT, DELETE будет обработаны исключение, если в базе нет зала с таким id, и выведено:

{
    "info": «Hall with id (указанный id) not found"
}

Performance:

GET http://localhost:8080/api/performances - получить список всех спектаклей
GET http://localhost:8080/api/performances/{id} - получить спектакль по id
POST http://localhost:8080/api/performances - добавить спектакль
{
	«name»: «name»,
	«description»: «description»,
	«hallId»: 1
}
 Получим:
{
	 «id»: (присвоенный id),
	«name»: «name»,
	«description»: «description»,
	«hallId»: 1
}

PUT http://localhost:8080/api/performances  - изменить данные спектакля
{
	 «id»: (существующий id),
	«name»: «another name»,
	«description»: «another description»,
	«hallId»: 2
}

DELETE http://localhost:8080/api/performances/{id}  - удалить спектакль по  id 

«Performance with id (указанный id) deleted»

Для методов GET, PUT, DELETE будет обработаны исключение, если в базе нет зала с таким id, и выведено:

{
    "info": «Performance with id (указанный id) not found"
}


POST http://localhost:8080/api/performances/{performanceId}/addActor/{actorId} - добавить актера спектаклю

Если удалось, то "Actor with id (actorId) added to performance with id (performanceId)"
Если нет, то ""Actor with id (actorId) could not be added to performance with id (performanceId)"

DEETE POST http://localhost:8080/api/performances/{performanceId}/removeActor/{actorId} - удалить актера у спектакля

Если удалось, то "Actor with id (actorId) removed from performance with id (performanceId)"
Если нет, то ""Actor with id (actorId) could not be removed from performance with id (performanceId)"


