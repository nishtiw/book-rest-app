# book-rest-app 
## This application contains endpoints for retrieving data in a paginated and sorted way. 

- `@GetMapping("/books/{pageNumber}/{pageSize}")` is the endpoint for retrieving data in a paginated way.
- `@GetMapping("/books/{pageNumber}/{pageSize}/{sortProperty}")` is the endpoint for retrieving data in a sorted way page-wise. 

## Output :

- pageNumber = 0, pageSize = 3

![image](https://user-images.githubusercontent.com/89537080/184566579-ba0bafdc-27cb-4b06-9777-d5100233a2d2.png)

- pageNumber = 0, pageSize = 3, sortProperty = "title"

![image](https://user-images.githubusercontent.com/89537080/184566718-481f6557-605e-4974-a8bd-59743f39c5fe.png)


