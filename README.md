## Deal analyzer assignment
this project is a part of a data where house for analyze FX deals,
as it's objective is to accept amount conversion deals and save them in a database.

Used Technologies
- Spring Boot 3
- Maven
- eclipse-temurin jdk 17
- docker

How to Install :
- you need eclipse-temurin jdk 17 or open jdk 17 to be installed or use IDE local jdk
- clone the project using this command `git clone https://github.com/abdelkarim-moussi/deal-analyzer-assignement.git`
- run the project locally using `mvn clean package` or use IDE
- have docker desktop installed
- run the project using docker using this commands `docker-compose up`
- project port listening is "8080" access the api endpoint "http://localhost::8080/api/v1/deals"

How to Use :

to add a deal :
- using postman or other tool : use "http://localhost::8080/api/v1/deals" with post method
`
    {
      "fromCurrency":"MAD",
      "toCurrency": "JOB",
      "dealAmount": 200
    }
`
- using curl: 

    `curl -X POST "http://localhost::8080/api/v1/deals" --header{Content-Type/application.json} -d '{"fromCurrency": "MAD","toCurrency":"JOD","amount":100}'`