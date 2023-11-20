# Data-Engineering-Challenge
Nordeus - Job Fair 2023

## My approach
I started from loading data from "events.jsonl" file.
<br>
After that i cleaned some data such as transactions with invalid amounts, registrations which countries are unknown.
I also clean sufficient number of login-s and logout-s of some users and make session(GameSession) for each user who logged in.
<br>
I saved that data in local first and then transfer them to database like separated tables "registrations", "transactions", "log_in_outs", and "sessions" using Hibernate.
<br>
For implementation of RestApi i used JAX-RS specification and Jersey implementation.
<br>
I have defined controllers for each of the resources that will be our access points to our data (resources).
<br>
After the controller, we have a service layer for processing additional business logic if needed (I didn't need it).
<br>
After the service layer, we have repositories, which will directly supply us with data from the database, and for that we use Jdbc Api so we can use sql queries.
<br>
For the calculation of user level stats, I mostly used typescript on the frontend, while for the calculation of game level stats, I did majority with sql queries on backend.

## Project setup

### Backend
> If u using Intellij...

**Dependencies**
<br>
On loading project load maven build script.
<br><br>
**Tomcat**
<br>
I used Tomcat 9.0.62 for server. Download Tomcat (version 9 is recommended).<br>
Add it to your configuration as Tomcat Server - Local. <br>
In application server field put Tomcat 9.0.62(path to it).<br>
In deployment tab add "Backend:war exploded"(or just click on Fix pop up), and delete path from application context field to remain only '/'.<br><br>
**MySql**
<br>
Download mysql server (I used community).<br>
I used DataGrip database client which is integrated in Intellij.<br>
Add new mySql server and test the connection (enter the username and password of our mySql server).<br>
Create a new schema, I named it entity_database, but it can be named differently and then configured.<br>
In the properties of our server, in the Database field, enter the name of our scheme (entity_database).<br>
In hibernate.cfg.xml add your data about the database - username, password, name of database.<br>
Also add the necessary data to the MySqlAbstractRepository class(its needed for jdbc connection).<br>
<br>

### Frontend
**Node Js**
<br>
Download and install Node js.
<br><br>
**Angular**
<br>
Install angular with the command:
```sh
npm install -g @angular/cli
```
Run the `npm install` command to install the node package.
<br><br>
**Bootstrap**
<br>
If you don't have a bootstrap install it with:
```sh
npm install bootstrap
```

## Run project
**Backend**<br>
Start the tomcat server on the backend.
<br><br>
**Frontend**<br>
Use terminal to navigate to your frontend folder and run command:
```sh
ng serve
```



