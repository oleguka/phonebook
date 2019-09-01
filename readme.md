Веб приложение стартует на 9000 порту.

## API

POST
```http://localhost:9000/phones```  - Добавить телефон, пример телефона ```+7-9993334444```
Тело
```{"name": "nike", "number": "+7-9993334444"}```

PUT
```http://localhost:9000/phones/id```  - Изменить телефон/имя по id.
Тело
```{"name": "nike", "number": "+7-9993334444"}```

GET
```http://localhost:9000/phones``` - список всех телефонов

GET
```http://localhost:9000/phones/id```  - Информация по конкретному телефону

DELETE
```http://localhost:9000/phones/id```  - Удалить телефон по id

GET 
```http://localhost:9000/phones/searchByName?name=oleg``` - список всех телефонов с данным именем

GET 
```http://localhost:9000/phones/searchByNumber?number=%2B7-9994318900``` - список всех телефонов с данным номером. %2B это код +

GET
```http://localhost:9000/phones/saveJson```  - Асинхронное? сохранение всех данных на ЖД