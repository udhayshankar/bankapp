# bankapp
### simple bank application
It is spring boot application which performs account creation and bank transfer from customer side. Here we have assumed the customer(user) will send the money instantly and implemented PESSIMISTIC_READ lock in order to ensure multiple requests/transactions occurs in sequential manner(added delay for 10 seconds to see the sequential execution of transactions). Test cases are written for couple of service classes. When we perform transactions, we are saving the transactions as logs for future reference. This application can be scalable by adding caching mechanism, queuing capabilities for bulk processing and also can add security layers as well.

The h2 database file is stored in **bankapp/src/main/java/com/mastercard/bankapp/db/bankappdb**

To load the persisted data from the h2 database, please change the value of **spring.datasource.url=jdbc:h2:file:{Your entire path for the db that can be found in the above project folder file}** 

The tech stack used are as follows

  1. Spring boot(Java framework)
  
  2. H2 database(pesisted in the project path)
  
  3. gradle
  
  4. REST
  
  
  

The H2 database bankapp looks as follows. The database schema has been designed in such a way that it can be as scalable as possible by storing branch details, status and type details for the accounts, and adding currency, types, status to the transactions. Customer table is to store customer details


<img width="550" alt="image" src="https://user-images.githubusercontent.com/42512377/205918623-81a9c245-1840-4676-82c8-e20500992747.png">


  
 ### REST APIS  as follows
 
 ### Curl commands
 
 **Customer**
 
 curl --request POST \
  --url http://localhost:8080/api/v1/customer/create \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F \
  --data '{"name": "subraman",
	"gender": "male",
	"phoneNumber": "0896789011",
	"email": "bama@gmail.com",
	"password": "password",
	"address": "No 99, icon living apartment",
	"city": "Cork",
	"county": "cork",
	"eirCode": "C02 KY67",
	"country": "Ireland"
}


curl --request GET \
  --url http://localhost:8080/api/v1/customer/1 \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
curl --request GET \
  --url http://localhost:8080/api/v1/customer/all \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
 
 
  **Branch**
  
  
  curl --request POST \
  --url http://localhost:8080/api/v1/branch/create \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F \
  --data '{
	"branchName":"Grafton street Dublin Branch",
	"branchAddress":"No 78, Grafton Street,Eden'\''s Quay",
	"branchCity":"Dublin",
	"branchCounty":"Dublin",
	"branchCountry":"Ireland",
	"branchEirCode":"D02 YU98",
	"branchPhoneNumber":"0897658901"
	
}'
curl --request GET \
  --url http://localhost:8080/api/v1/branch/BR285 \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
  curl --request GET \
  --url http://localhost:8080/api/v1/branch/all \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
  
  **Account**
  
  
  curl --request POST \
  --url http://localhost:8080/api/v1/account/create \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F \
  --data '{
	"customerId":3,
	"branchId": "BR285",
	"currencyId": "CURR799",
	"accountStatusId": "ACCSTAT925",
	"accountTypeId": "ACCTYP569"
	
}'

curl --request GET \
  --url http://localhost:8080/api/v1/account/D0G3AS4 \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
 curl --request GET \
  --url http://localhost:8080/api/v1/account/all \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
  curl --request DELETE \
  --url http://localhost:8080/api/v1/account/delete/P1BABVX \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
  
  **Currency**
  
  
  curl --request POST \
  --url http://localhost:8080/api/v1/currency/create \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F \
  --data '{
"currencyValue":"Dollar"
}'

curl --request GET \
  --url http://localhost:8080/api/v1/currency/CURR598 \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
  
  **Account status**
  
  
  curl --request POST \
  --url http://localhost:8080/api/v1/accountstatus/create \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F \
  --data '{
	"accountStatusDescription": "Inactive"
}'

curl --request GET \
  --url http://localhost:8080/api/v1/accountstatus/ACCSTAT461 \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
 
 
  **AccountType**
  
  
  curl --request POST \
  --url http://localhost:8080/api/v1/accounttype/create \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F \
  --data '{
	"accountTypeDescription":"Salary"
}'

curl --request GET \
  --url http://localhost:8080/api/v1/accounttype/ACCTYP346 \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
 
  **TransactionType**
  
  
  curl --request POST \
  --url http://localhost:8080/api/v1/transactiontype/create \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F \
  --data '{
	"transactionTypeDescription":"NEFT"
}'

curl --request GET \
  --url http://localhost:8080/api/v1/transactiontype/TRATYP915 \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
  
  
  **TransactionStatus**
  
  
  curl --request POST \
  --url http://localhost:8080/api/v1/transactionstatus/create \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F \
  --data '{
	"transactionStatusDescription":"INSUFFICIENT_BALANCE"
}'

curl --request GET \
  --url http://localhost:8080/api/v1/transactionstatus/TRASTAT728 \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
  
  curl --request GET \
  --url http://localhost:8080/api/v1/transactionstatus/all \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
  
  **Transaction**
  
  
  curl --request GET \
  --url http://localhost:8080/api/v1/transaction/ACCTYP738 \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
  
  curl --request GET \
  --url http://localhost:8080/api/v1/transaction/all \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F
  
  
  
  
  To execute and see the transactions executed in sequential manner, please hit the following three endpoints simultaneously
  
  
  curl --request POST \
  --url http://localhost:8080/api/v1/transaction/transfer \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F \
  --data '{
	"senderUserId":"YIQWTN1",
	"receiverUserId":"V8F4F2I",
	"transactionAmount":5,
	"transactionTypeCode":"TRATYP247",
	"transactionDescription":"education"
}'


curl --request POST \
  --url http://localhost:8080/api/v1/transaction/transfer \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F \
  --data '{
	"senderUserId":"YIQWTN1",
	"receiverUserId":"V8F4F2I",
	"transactionAmount":10,
	"transactionTypeCode":"TRATYP247",
	"transactionDescription":"jkl"
}'

curl --request POST \
  --url http://localhost:8080/api/v1/transaction/transfer \
  --header 'Content-Type: application/json' \
  --cookie JSESSIONID=E8A74E4BB25448491D981D7AE59B3D6F \
  --data '{
	"senderUserId":"YIQWTN1",
	"receiverUserId":"V8F4F2I",
	"transactionAmount":15,
	"transactionTypeCode":"TRATYP247",
	"transactionDescription":"jkl"
}'

