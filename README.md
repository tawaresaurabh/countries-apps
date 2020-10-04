1. Download the repo
2. Repo has 2 projects  -  countries  and  countries-ui
3. countries is backend spring boot project (maven based)  
4. countries-ui is a react front end project  -  This will run in dev mode for simplicity 
5. The projects are made assuming no other processes are running on port 8080 and 3000

## Steps for testing

1. Open terminal and cd into the  countries project
2. Make sure java and maven is configured correctly on the system and no applications are running on port 8080 and 
3. run command  ### `mvn clean install`
4. This will create a .jar file in target folder of the project
5. cd into target folder 
6. Run command   `java -jar countries-0.0.1-SNAPSHOT.jar`
7. This should start the backend project on port 8080 and can be accessed by http://localhost:8080/countries/ using cURL or browser
8. Rest endpoints can now be tested


1. Open another terminal and cd into  countries-ui project
2. Make sure node is installed on the machine 
3. run command   `npm install`
4. once completed run command  `npm start`
5. This will start the countries-ui on port 3000 and can be accessed by http://localhost:3000
6. It should show a list of countries
7. The names of countries in the list are clickable and on clicking them , a modal should diplay more information about the countries
8. OK button on modal should take the user back to the list.
