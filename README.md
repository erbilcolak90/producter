# Producter
 
Proje içerisindeki data klasöründe örnek bir SQL tablosu mevcut.
Docker-Desktop kullanıp
projeyi ayağa kaldırmak için klasörün bulunduğu path e giderek, docker-compose up diyerek uygulamayı ayağa kaldırabilirsiniz.

GraphQL sorgularını göndermek için postman collectionu buradan indirebilirsiniz.
[Postman collection u için tıklayın.zip](https://github.com/erbilcolak90/producter/files/10832191/producter.postman_collection.zip)

Login sorgusunu gönderdikten sonra responstan dönen tokenı diğer sorgularda kullanmalısınız.

----
Admin yetkisine sahip kullanıcı bilgileri :

username: producter

password: 123456

----

Login olduktan sonra response'tan gelen JWT token ı kopyalayıp, diğer sorgular için
Header kısmındaki Authorization değerine Bearer'dan sonra eklemeniz gerekmektedir.


#### Kullanılan teknolojiler

- Spring GraphQL
- Spring Security
- JWT
- Authentication
- Spring boot validation
- Lombok
- MySql
- Slf4j logger
- Java version 17
- Graphql Scalar

## Entities

- Player
- Role
- Team
- User
- UserRole


### Player

- id: ``String``
- name: ``String``
- surname: ``String``
- position: ``Enum``
- teamId: ``String``
- isDeleted:``boolean``
- createdDate: ``OffsetDateTime``
- updateDate: ``OffsetDateTime``


### Team

- id: ``String``
- name: ``String``
- capacity: ``int``
- isDeleted:``boolean``
- createdDate: ``OffsetDateTime``
- updateDate: ``OffsetDateTime``

### User

- id: ``String``
- username: ``String``
- email: ``String``
- password: ``String``
- isDeleted:``boolean``
- createdDate: ``OffsetDateTime``
- updateDate: ``OffsetDateTime``

### Role

- id: ``String``
- name: ``String``


### UserRole

- id: ``String``
- userId: ``String``
- roleId: ``String``

---


## Inputs

### LoginInput

- username: ``String``
- password: ``String``

### PaginationInput

- page: ``int``
- size: ``ìnt``
- fieldName: ``String``
- sortBy: ``enum`` : ASC,DESC

### PlayerInput

- name: ``String``
- surname: ``String``
- position: ``enum``
- teamId: ``String``

### TeamInput

- name: ``String``
- capacity: ``int``

### UpdatePlayerInput

- id: ``String``
- name: ``String``
- surname: ``String``
- position: ``enum``

### UpdateTeamInput

- id: ``String``
- name: ``String``
- capacity: ``String``

### UserInput

- id: ``String``
- username: ``String``
- email: ``String``
- password: ``String``


### GraphQL API

````
-AuthController
-PlayerController
-RoleController
-TeamController
-UserController
-UserRoleController
````

---

### Mutation Operations



#### Authorization Mutations


#### SignUp

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
schema: mutation
input: UserInput
schema type:
    mutation{
    signUp(userInput: {
        username: "halikarnasbalikcisi"
        password: "123456"
        email:"balikcisi@gmail.com"})
    {
        message
        status
        result
    }
}
````

#### Response

````
{
    "data": {
        "signUp": {
            "message": "Success",
            "status": true,
            "result": "25f1b0a8-c2b7-441b-b862-7ac445018956"
        }
    }
}
````


### Login

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
input: loginInput
schema: mutation
schema type: 
 mutation{
    login(loginInput: {
        username: "producter"
        password: "123456"})
        {
        message
        status
        result
    }
}
````

#### Response

````
{
    "data": {
        "login": {
            "message": "Success",
            "status": true,
            "result": "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiIyZDlmM2RiMy02OGNjLTQ1ZDEtOWJlZC00ZDQ1YTg3N2I4ZmMiLCJpc3MiOiJqd3RUdXRvcmlhbCIsImlhdCI6MTY3NzMzNzA1OCwiZXhwIjoxNjc3MzM4ODU4fQ.N-dnhbcDc_X26wTg71A6JR9S2dRHYMmdp8K4d1dPNz0"
        }
    }
}
````

### Logout

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: token: String
schema: mutation
schema type: 
    mutation{
    logout(token: "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOi
    IyZDlmM2RiMy02OGNjLTQ1ZDEtOWJlZC
    00ZDQ1YTg3N2I4ZmMiLCJpc3MiOiJqd3
    RUdXRvcmlhbCIsImlhdCI6MTY3NzI2NT
    k5OSwiZXhwIjoxNjc3MjY3Nzk5fQ.
    vVjAgGxuqiL0CDnThNQEeUQEmiJIw6Vc6oul2s7RoI4")
}

````

#### Response
````
{
    "data": {
        "logout": true
    }
}
````

---

### Player Mutations


#### Create Player

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: playerInput
schema: mutation
schema type: 
    mutation{
    createPlayer(playerInput:{
        name: "cj"
        surname: "mcCollum"
        position: SHOOTING_GUARD
        teamId:"3fbad642-29fe-4046-8bc9-af72ab72bd36"

    }){
        message
        status
        result{
            id
            name
            surname
            position
            teamId
            isDeleted
            createdDate
            updateDate

        }
    }
}
````

#### Response
````
{
    "data": {
        "createPlayer": {
            "message": "Success",
            "status": true,
            "result": {
                "id": "f2f8c8f4-a81f-45c3-9a84-1afb0dc20c0c",
                "name": "cj",
                "surname": "mcCollum",
                "position": "SHOOTING_GUARD",
                "teamId": "3fbad642-29fe-4046-8bc9-af72ab72bd36",
                "isDeleted": false,
                "createdDate": "2023-02-25T03:31:06.5016005+03:00",
                "updateDate": "2023-02-25T03:31:06.5016005+03:00"
            }
        }
    }
}
````

---

#### Update Player

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: updatePlayerInput
schema: mutation
schema type: 
   mutation{
    updatePlayer(updatePlayerInput:{
        id: "33005306-fc04-4d31-8bdc-5ef143bfc1ff"
        name: "lebron"
        position: POWER_FORWARD
        surname: "james"
    }){
        message
        status
        result{
            id
            name
            position
            surname
            createdDate
            updateDate
        }
    }
}
````

#### Response
````
{
    "data": {
        "updatePlayer": {
            "message": "Success",
            "status": true,
            "result": {
                "id": "33005306-fc04-4d31-8bdc-5ef143bfc1ff",
                "name": "lebron",
                "position": "POWER_FORWARD",
                "surname": "james",
                "createdDate": "2023-02-24T18:20:41+03:00",
                "updateDate": "2023-02-24T22:47:43.6402422+03:00"
            }
        }
    }
}
````

---

#### UpdatePlayerTeam

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: playerId: String, teamId: String
schema: mutation
schema type: 
    mutation{
    updatePlayerTeam(playerId: "33005306-fc04-4d31-8bdc-5ef143bfc1ff", teamId: "8a0af5bf-f956-4320-98e3-3d5f3f69b83a" ){
        message
        status
        result{
            id
            name
            position
            teamId
        }
    }
}
````

#### Response
````
{
    "data": {
        "updatePlayerTeam": {
            "message": "Success",
            "status": true,
            "result": {
                "id": "33005306-fc04-4d31-8bdc-5ef143bfc1ff",
                "name": "charles",
                "position": "CENTER",
                "teamId": "8a0af5bf-f956-4320-98e3-3d5f3f69b83a"
            }
        }
    }
}
````

---

#### DeletePlayerById

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: playerId: String
schema: mutation
schema type: 
    mutation{
    deletePlayerById(playerId: "2422aa4d-6b9b-4c5c-ba88-1b0b34badedf"){
        message
        status
        result
    }
}
````

#### Response
````
{
    "data": {
        "deletePlayerById": {
            "message": "Player deleted",
            "status": true,
            "result": null
        }
    }
}
````

---

### Team Mutations

#### CreateTeam

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: teamInput
schema: mutation
schema type: 
    mutation{
    createTeam(teamInput: {
        name: "atlanta hawks"
        capacity: 13
    }){
        message
        status
        result{
            id
            name
            capacity
            isDeleted
            createdDate
            updateDate
        }
    }
}
````

#### Response
````
{
    "data": {
        "createTeam": {
            "message": "Success",
            "status": true,
            "result": {
                "id": "f5098845-d9dc-49bb-a8df-7d3cee2435ba",
                "name": "atlanta hawks",
                "capacity": 13,
                "isDeleted": false,
                "createdDate": "2023-02-24T23:04:19.4718513+03:00",
                "updateDate": "2023-02-24T23:04:19.4718513+03:00"
            }
        }
    }
}
````

---

#### UpdateTeam

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: updateTeamInput
schema: mutation
schema type: 
    mutation{
    updateTeam(updateTeamInput: {
        id: "f5098845-d9dc-49bb-a8df-7d3cee2435ba"
        name:"Atlanta Hawks"
        capacity: 20
    }){
        message
        status
        result{
            id
            name
            capacity
            isDeleted
            createdDate
            updateDate
        }
    }
}
````

#### Response
````
{
    "data": {
        "updateTeam": {
            "message": "Success",
            "status": true,
            "result": {
                "id": "f5098845-d9dc-49bb-a8df-7d3cee2435ba",
                "name": "Atlanta Hawks",
                "capacity": 20,
                "isDeleted": false,
                "createdDate": "2023-02-24T23:04:19+03:00",
                "updateDate": "2023-02-25T18:36:09.5125393+03:00"
            }
        }
    }
}
````

---

#### DeleteTeamById

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: teamId: String
schema: mutation
schema type: 
    mutation{
    deleteTeamById(teamId:"d2434c78-d9c0-49e8-a1ee-d18ed67a6e7f" ){
        message
        status
        result
    }
}
````

#### Response
````
{
    "data": {
        "deleteTeamById": {
            "message": "Success",
            "status": true,
            "result": null
        }
    }
}
````

---

### Admin Mutations

#### AddRoleToUser

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: userId: String , roleName: String
schema: mutation
schema type: 
    mutation{
    addRoleToUser(userId: "33a8d34a-74de-403e-8b5d-a02532cbb9d5", roleName:"ADMIN" ){
        message
        status
        result
    }
}
````

#### Response
````
{
    "data": {
        "addRoleToUser": {
            "message": "Success",
            "status": true,
            "result": "ADMIN"
        }
    }
}
````

---

#### CreateRole

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: roleName: String
schema: mutation
schema type: 
    mutation{
    createRole(roleName: "MANAGER"){
        message
        status
        result
    }
}
````

#### Response
````
{
    "data": {
        "createRole": {
            "message": "Success",
            "status": true,
            "result": "9ef030f8-4e9e-4da9-a068-57fbca3cedf7"
        }
    }
}
````

---


### Query Operations

### Admin Queries

#### getUserRolesByUserId

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: userId: String
schema: query
schema type: 
    query{
    getUserRolesByUserId(userId:"f4676b6e-8724-4515-bd72-6c82ff92e991")
}
````

#### Response
````
{
    "data": {
        "getUserRolesByUserId": [
            "001af9ad-b55c-44c0-af9c-6fafb029ba1a",
            "10c3aa7d-b10b-4ccc-821d-88e8a05016bb"
        ]
    }
}
````

---

#### GetByRoleId

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: roleName: String
schema: query
schema type: 
    query{
    getByRoleId(roleName: "ADMIN")
}
````

#### Response
````
{
    "data": {
        "getByRoleId": "10c3aa7d-b10b-4ccc-821d-88e8a05016bb"
    }
}
````

---


#### User Queries


#### GetUserById

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: userId: String
schema: query
schema type: 
    query{
    getUserById(userId: "25f1b0a8-c2b7-441b-b862-7ac445018956"){
    message
    status
    result{
        id
        username
        email
        createdDate
        updateDate
        isDeleted
    }
}
}
````

#### Response
````
{
    "data": {
        "getUserById": {
            "message": "Success",
            "status": true,
            "result": {
                "id": "25f1b0a8-c2b7-441b-b862-7ac445018956",
                "username": "halikarnasbalikcisi",
                "email": "balikcisi@gmail.com",
                "createdDate": "2023-02-25T18:03:19+03:00",
                "updateDate": "2023-02-25T18:03:19+03:00",
                "isDeleted": false
            }
        }
    }
}
````

---

#### GetAllPlayers

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: paginationInput
schema: query
schema type: 
    query{
    getAllPlayers(paginationInput:
    { page: 0
    size: 10
    fieldName: "surname"
    sortBy: DESC}
     ) { 
         id
         name
         surname
         position
         teamId
         isDeleted
         createdDate
         updateDate  
     }
}
````

#### Response
````
{
    "data": {
        "getAllPlayers": [
            {
                "id": "3f8791bb-a2b7-4bcc-be41-23f7b6c73f7e",
                "name": "şemsi",
                "surname": "paşa",
                "position": "POINT_GUARD",
                "teamId": "4ce9689a-b2b1-11ed-afa1-0242ac120002",
                "isDeleted": false,
                "createdDate": "2023-02-22T17:15:46+03:00",
                "updateDate": "2023-02-22T17:15:46+03:00"
            },
            {
                "id": "aa1c795e-ffeb-4840-9096-82c7ba437b55",
                "name": "Shaquile",
                "surname": "oneal",
                "position": "CENTER",
                "teamId": "27d6a857-6d5a-4368-8636-0a3edc84c0c4",
                "isDeleted": false,
                "createdDate": "2023-02-24T18:11:11+03:00",
                "updateDate": "2023-02-24T18:11:11+03:00"
            },
            ...
        ]
    }
}
````

---

#### GetPlayersByTeamId

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: paginationInput
schema: query
schema type: 
    query{
    getPlayersByTeamId(paginationInput: { page: 0,size:10,fieldName: "name",sortBy: ASC}, teamId: "27d6a857-6d5a-4368-8636-0a3edc84c0c4" ){
        id
        name
        surname
        teamId
        isDeleted
        createdDate
        updateDate
    }
}
````

#### Response
````
{
    "data": {
        "getPlayersByTeamId": [
            {
                "id": "3ec8eccc-605f-497c-a16c-61fc7d725ad6",
                "name": "recaizade mahmut",
                "surname": "ekrem",
                "teamId": "27d6a857-6d5a-4368-8636-0a3edc84c0c4",
                "isDeleted": false,
                "createdDate": "2023-02-22T17:16:49+03:00",
                "updateDate": "2023-02-22T22:01:10+03:00"
            },
            {
                "id": "aa1c795e-ffeb-4840-9096-82c7ba437b55",
                "name": "Shaquile",
                "surname": "oneal",
                "teamId": "27d6a857-6d5a-4368-8636-0a3edc84c0c4",
                "isDeleted": false,
                "createdDate": "2023-02-24T18:11:11+03:00",
                "updateDate": "2023-02-24T18:11:11+03:00"
            },
            ...
        ]
    }
}
````

---

#### GetPlayerById

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: playerId: String
schema: query
schema type: 
    query{
    getPlayerById(playerId: "2422aa4d-6b9b-4c5c-ba88-1b0b34badedf"){
        message
        status
        result{
            id
            name
            surname
            position
            teamId
            isDeleted
            createdDate
            updateDate
            
        }
    }
}
````

#### Response
````
{
    "data": {
        "getPlayerById": {
            "message": "Success",
            "status": true,
            "result": {
                "id": "2422aa4d-6b9b-4c5c-ba88-1b0b34badedf",
                "name": "shawn",
                "surname": "marion",
                "position": "CENTER",
                "teamId": "27d6a857-6d5a-4368-8636-0a3edc84c0c4",
                "isDeleted": true,
                "createdDate": "2023-02-24T18:20:02+03:00",
                "updateDate": "2023-02-24T22:32:25+03:00"
            }
        }
    }
}
````

---

#### GetTeamById

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: teamId: String
schema: query
schema type: 
query{
    getTeamById(teamId:"27d6a857-6d5a-4368-8636-0a3edc84c0c4"){
        message
        status
        result{
            id
            name
            capacity
        }
    }
}
````

#### Response
````
{
    "data": {
        "getTeamById": {
            "message": "Success",
            "status": true,
            "result": {
                "id": "27d6a857-6d5a-4368-8636-0a3edc84c0c4",
                "name": "Golden State",
                "capacity": 4
            }
        }
    }
}
````

---

#### GetAllTeams

#### Request
````
method: POST
url: /graphql
requestSample: http://localhost:8080/graphql
Authorization: Bearer JWT
input: paginationInput
schema: query
schema type: 
    query{
    getAllTeams(paginationInput: {page: 0, size: 10 , fieldName: "name", sortBy: ASC}){
        id
        name
        capacity
    }
}
````

#### Response
````
{
    "data": {
        "getAllTeams": [
            {
                "id": "f5098845-d9dc-49bb-a8df-7d3cee2435ba",
                "name": "Atlanta Hawks",
                "capacity": 20
            },
            {
                "id": "3fbad642-29fe-4046-8bc9-af72ab72bd36",
                "name": "Charlotte",
                "capacity": 15
            },
            {
                "id": "27d6a857-6d5a-4368-8636-0a3edc84c0c4",
                "name": "Golden State",
                "capacity": 4
            },
            {
                "id": "8a0af5bf-f956-4320-98e3-3d5f3f69b83a",
                "name": "Houston Rockets",
                "capacity": 15
            },
            {
                "id": "dc6d28b6-384e-490c-b7e6-ed43b66cd5e7",
                "name": "Lakers",
                "capacity": 15
            },
            {
                "id": "7aa04b7c-c807-4181-b8db-ff1425457b54",
                "name": "Orlando Magic",
                "capacity": 15
            },
            {
                "id": "d4f3a578-338c-4077-a58b-6e279cedf9ad",
                "name": "Phoneix Suns",
                "capacity": 15
            },
            {
                "id": "d2434c78-d9c0-49e8-a1ee-d18ed67a6e7f",
                "name": "San Antonio Spurs",
                "capacity": 15
            }
        ]
    }
}
````

---


















