--- Review questions ---

A)
The default lifecycle handles the project deployment.
It is composed of the following phases (that are executed sequentially):
validate - validates that the project is correct and all necessary information is available
compile - compile the project's source code
test - test the compiled code using a unit testing framework (it shouldn't be necessary for the code to be packaged or deployed)
package - package the compiled code in its distributable format (e.g., jar)
verify - run checks on results of integration tests to ensure quality criteria are met
install - install the package into the local repository, so it can be used as a dependency in other projects locally
deploy - done in the build environment, copies the final package to the remote repo for sharing with other devs and projects

B)
While building is Maven's main purpose, some Maven plugins can be used for run-related objectives such as testing. This is one of the cases where Maven is a suitable tool for running projects.

C)
At least the following commands:
git clone - clone the repo locally
git add - add files to the staging area
git commit - commit added files
git push - send the commited changes to the remote repo

And likely some of these:
git pull - fetch and merge changes on the remote repo to the working directory
git merge - integrate changes from other branches
git rm - delete files from the working directory and stage their deletion
git status - list all the changes not staged for commit
git diff - show the file differences that aren't staged yet

D)
1. Begin the commit message with a single short (fewer than 50 characters) line (commit title) summarizing the change, followed by a blank line and then a more thorough description.
2. Capitalize the subject line
3. Don't use periods in the subject line
4. Use the imperative mood in the subject line (for example, "add feature" instead of "added feature")
5. Wrap the body at 72 characters
6. Use the body to explain what changed, as well as why and how.

E)
To prevent erasure of the data when the container is deleted and facilitate the backup of the database.

--- 1.1 ---
JDK 11 installed due to the recommendation.
Maven installed and environment variable set.
Output of the "mvn --version" command:
- WSL
Apache Maven 3.8.3 (ff8e977a158738155dc465c6a97ffaf31982d739)
Maven home: /mnt/c/apache-maven-3.8.3
Java version: 11.0.11, vendor: Ubuntu, runtime: /usr/lib/jvm/java-11-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "5.10.16.3-microsoft-standard-wsl2", arch: "amd64", family: "unix"
- Windows
Apache Maven 3.8.3 (ff8e977a158738155dc465c6a97ffaf31982d739)                                                           Maven home: C:\apache-maven-3.8.3                                                                                       Java version: 11.0.12, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk-11.0.12                           Default locale: en_US, platform encoding: Cp1252                                                                        OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

--- 1.2 ---
Maven archetype: a template project from which other projects are created

--- 1.4 ---
Followed Docker "Orientation and Setup" tutorial parts 1 through 8.
Tried Portainer, but used Docker Desktop + WSL integration on Windows after it.
Completed the alternative exercise in d), using plsql and pgAdmin.
Completed the Docker compose tutorial exercise.

--- DOCKER ---

DOCKER DOCS TUTORIAL

Run: docker run -d -p 80:80 docker/getting-started

-d - detached mode (in the background)
-p - 80:80 map port 80 of the host to port 80 of the container
docker/getting-started - the image

On Windows, Docker Dashboard can be used to easily manage containers through a GUI.

To build apps, a Dockerfile is necessary.

Build: docker build -t getting-started .

-t - tags the image (human-readable name for the final image)

Tutorial Dockerfile notes

"FROM node:12-alpine" instructs the builder to start from the node:12-alpine image, which will be downloaded if not present in our machine.
After that, the app is copied in and yarn is used to install its dependencies.
"CMD ["node", "src/index.js"]" specifies the default command to run when starting a container from this image.

Start app container: docker run -dp 3000:3000 getting-started

Without port mapping, the app would be inaccessible

The app is now available at http://localhost:3000

Updating the app

After making our changes, build the updated version
docker build -t getting-started

Remove the old container to free up port 3000
docker ps # show the IDs of containers
docker stop the-container-id # stop the old container
docker rm the-container-id # remove the old container

Alternatively, it's possible to remove a container in a single command using the "force" flag
docker rm -f the-container-id

... or use Docker Desktop and click the trash can icon

Start a new container using the updated code
docker run -dp 3000:3000 getting-started

Sharing the app

A Docker registry is used to share Docker images (the default is Docker Hub)

First, create Docker Hub (https://hub.docker.com/) repo.
Then, login to Docker Hub: docker login -u YOUR-USER-NAME
User the docker tag command to give the image a new name, using your Docker ID
docker tag getting-started YOUR-USER-NAME/getting-started
Finally, push: docker push YOUR-USER-NAME/getting-started

Running the image on a new instance

Open "Play with Docker" (https://labs.play-with-docker.com/), login, select docker, login with the Docker Hub account.
Click on "ADD NEW INSTANCE", a terminal window should open.
Start the pushed app in the terminal: docker run -dp 3000:3000 YOUR-USER-NAME/getting-started
Click on the port number (3000) badge to access the app.

Persist the DB

Each container has its own "scratch space" to create/update/remove files. Changes aren't seen in other containers, even if they are using the same image. Let's see this in practice.

Start an ubuntu container that will create a "/data.txt" file with a random number.
docker run -d ubuntu bash -c "shuf -i 1-10000 -n 1 -o /data.txt && tail -f /dev/null"
Through Docker Desktop or the "docker exec" command, enter the container's CLI, check the content of the file: cat /data.txt
Start another ubuntu container using the same image: docker run -it ubuntu ls /
No data.txt file is there. Remove the containers.

The todo app stores its data in a SQLite DB at /etc/todos/todo.db in the container's filesystem. If we persist that file on the host and make it available to the next container, it will pick up where the last one left off. This can be achieved by creating and mounting a volume to the directory the data is stored in.

Create a volume using the "docker volume create" command
docker volume create todo-db
Remove the todo app container: docker -rm -f todo-container-id
Start the container with the -v flag to specify a volume mount
docker run -dp 3000:3000 -v todo-db:/etc/todos getting-started
Open the app, add items, remove the container, start a new one, the items should still be there.

Use the "docker volume inspect" command to know where Docker is actually storing the data when using named volumes (Mountpoint)

Container Networking

Creating a network: docker network create todo-app

Start a MySQL container and attach it to the network, with some environment variables the DB will use to initialize the DB
docker run -d --network todo-app --network-alias mysql -v todo-mysql-data:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=secret -e MYSQL_DATABASE=todos mysql:5.7
Note: Docker recognizes we want to use a named volume and creates it automatically (todo-mysql-data)

Confirm the DB is up and running: docker exec -it mysql-container-id mysql -u root -p
Type in the password, use "show databases;", check for the "todo" DB

Connecting to MySQL

Start a new container using the nicolaka/netshoot image (useful tools)
docker run -it --network todo-app nicolaka/netshoot
Inside the container, run "dig mysql" to look up the IP address

Start the dev-ready container
docker run -dp 3000:3000 -w /app -v "$(pwd):/app" --network todo-app -e MYSQL_HOST=mysql -e MYSQL_USER=root -e MYSQL_PASSWORD=secret -e MYSQL_DB=todos node:12-alpine sh -c "yarn install && yarn run dev"

Open the app, add items
Connect to the MySQL DB and prove the items are being written
docker exec -it mysql-container-id mysql -p todos
mysql> select * from todo_items;
The items should be there

Using Docker Compose

At the root of the app project, create docker-compose.yml
Start by defining the schema version: version: "3.7"
Define the list of services/containers to run as part of the app

services:
  app:
    image: node:12-alpine
    command: sh -c "yarn install && yarn run dev"
    ports:
      - 3000:3000
    working_dir: /app
    volumes:
      - ./:/app
    environment:
      MYSQL_HOST: mysql
      MYSQL_USER: root
      MYSQL_PASSWORD: secret
      MYSQL_DB: todos

  mysql:
    image: mysql:5.7
    volumes:
      - todo-mysql-data:/var/lib/mysql
    environment:
      MYSQL_ROOT_PASSWORD: secret
      MYSQL_DATABASE: todos

volumes:
  todo-mysql-data:


DEFINITIONS

Container: sandboxed process on your machine, isolated from all other processes on the host machine; 

Container image: provides the custom filesystem containers use as an isolated filesystem; must contain everything needed to run the app (dependencies, configuration, scripts, binaries, etc.); also contains other configuration for the container, such as environment variables, a default command to run, and other metadata.

Dockerfile: text-based script of instructions used to create a container image.

Volume: provides the ability to connect specific filesystem paths of the container back to the host machine; changes in a mounted directory in the container are also seen on the host machine; if the same directory is mounted across container restards, the same files will be seen; there are 2 types of volumes, starting with named volumes.

Bind mounts: unlike mounted volumes, they allow us to control the mountpoint on the host; this can be used to persist data, but it's often used to provide additional data into containers; they can be used to mount source code into a container to let it see code changes, respond and let us see the changes right away

Docker Compose: tool that helps define and share multi-container apps; its big advantage is that you can define your app stack in a file, keep it at the root of our project repo and easily enable someone else to contribute to your project
