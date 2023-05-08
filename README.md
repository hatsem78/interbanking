# interbanking

Generar los siguientes 3 endpoints:
1.- Uno que traiga las empresas que hicieron transferencias el último mes
2.- Otro que traiga las empresas que se adhirieron el último mes.
3.- El ultimo que haga la adhesión de una empresa.
Deseable: usar hexagonal.

Base: puede usarse relacional o no relacional
Datos de la empresa: Cuit, razón social, fecha adhesión
Datos de la transferencia: importe, id empresa, cuenta débito, cuenta crédito
Si falto algo asumirlo y cuando envías el texto comentar lo asumido-

1.- se creó el endpoint:
http://localhost:8080/api/companiesMadeTransfersLastMonth
trae todas las empreas que hicieron trasferencia el último mes, ejemplo si estamos en mayo trae todos los
movimientos de abril resultado:
---
    [
      {
        "transferId": 1,
        "amount": 300,
        "transferDate": "2023-04-07",
        "company": {
          "companyId": 1,
          "companyName": "prueba1"
        }
      },
      {
        "transferId": 2,
        "amount": 1300,
        "transferDate": "2023-04-07",
        "company": {
          "companyId": 2,
          "companyName": "prueba2"
        }
      },
    ]
---

2.- Se creo el enpoint:
http://localhost/companiesJoinedLastMonth
Trae todas las empresas que se adhirieron en el último mes, ejemplo si estamos en mayo trae todos los
movimientos de abril resultado:

---
    [
      {
        "memberDate": "2023-04-08",
        "company": {
          "companyId": 1,
          "companyName": "prueba1"
        }
      },
      {
        "memberDate": "2023-04-08",
        "company": {
          "companyId": 2,
          "companyName": "prueba2"
        }
      }
    ]
---

3.- Se creo el endpoint
http://localhost:8080/api/add/MemberCompany

parametros:
---
    {
      "companyId": 1
    }
---

resultado
---
    {
      "code": 101,
      "message": "Add Member Company ok"
    }
---

Se creo un objeto MemberCompanies, donde se guardan todas las empresas que se adhirieron en el mes, también
se creó objecto Account que son los estados:
DEBIT_ACCOUNT
CREDIT_CCOUNT

Se realizo una validación del C.U.I.T con lo cual se creó un decorador @ValidCuil

## Run Spring Boot application
```
mvn spring-boot:run
```
    
