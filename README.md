App name: Task-manager
Details: Task-manager app can be used for creating tasks and managing these tasks.

Technology used: Springboot, Docker, Docker-compose, Mysql, NGINIX.

Steps to be followed to run the app: 
1. Up the containers by running docker-compose.yml file
	Command: sudo docker-compose up
	
2. When all containers are successfully up and running then you can hit the following apis:

	I. Firstly let's create some task:
	Command: 
	curl --header "Content-Type:application/json" --request POST --data '{"id":"1","taskName":"Game","taskDetails":"Playing  badminton"}' http://localhost/tasks/save
	
	curl --header "Content-Type:application/json" --request POST --data '{"id":"1","taskName":"Singing","taskDetails":"Singing Jaiho song"}' http://localhost/tasks/save
	
	II. Check the created tasks:
	Command: curl --header "Content-Type:application/json" --request GET http://localhost/tasks
	
	III. Check individual task: 
	Command: curl --header "Content-Type:application/json" --request GET http://localhost/tasks/1
	
	IV. Update the task
	Command:
	curl --header "Content-Type:application/json" --request PUT --data '{"taskName":"Dancing","taskDetails":"Classical dancing"}' http://localhost/tasks/update/1
	
	After update let's check all taksk:
	Command: curl --header "Content-Type:application/json" --request GET http://localhost/tasks

	V. Delete the task
	Command: curl --header "Content-Type:application/json" --request DELETE  http://localhost/tasks/delete/2
	
	After delete let's check all taksk:
	Command: curl --header "Content-Type:application/json" --r
