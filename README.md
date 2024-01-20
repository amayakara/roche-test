# roche-test

Steps to run:
Executing below command starts running the application:
1. docker-compose up
2. download the postman collections json file from docs folder.
3. import the collection to postman or Talend API tester.
4. hit the available requests.

Below steps are not required but i have used them for creating the image and push it to dockerhub. Before running the 'docker-compose up' command.

If any changes are added and to build locally and create docker image, versions need to be changed accordingly in the command as well as in docker-compose.yml
1. For maven build: mvn clean install
2. for docker build: docker build -t mmayakara/fizzbuzz:0.0.2 .
3. to push the image to dockerhub: docker push mmayakara/fizzbuzz:0.0.2


