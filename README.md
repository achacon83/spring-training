# spring-training
This is a project created to test the implementation of multiple modules using different strategies with Spring


## Pruebas con curl

curl --location --request POST 'http://localhost:8081/persons' \
--header 'Content-Type: application/json' \
--data-raw '{
"name": "Pedro",
"age": 20
}'