pipeline {
    agent any

    environment {
        dockerimage = ""
    }

    stages {
        stage ('GIT') {
            steps {
                git branch: "amine", 
                    credentialsId: 'github-credentials',
                    url: "https://github.com/Sarah47393/Ski-Station-Project.git";
            }
        }

        stage("SonarQube") {
            steps {
                sh "mvn sonar:sonar -Dsonar.projectKey=maven-jenkins-pipeline -Dsonar.host.url=http://localhost:9000 -Dsonar.login=e338e97519d5e0bf8ea0647db97a099c76fa1634"
            }
        }

        stage("Build") {
            steps {
                sh "mvn clean package -DskipTests"
            }
        }

	    stage("Deploy to nexus") {
            steps {
                sh "mvn deploy -DskipTests"
            }
        }

        stage("Build Docker image") {
            steps {
                script {
                    dockerimage = docker.build('mohamedaminetaieb/ski', 'mohamedaminetaieb/ski')
                }
            }
        }

        stage("Push image to Docker Hub") {
            environment {
                docker.withRegistry( 'https://registry.hub.docker.com', 'dockerhub-credentials' ) {
            }
            steps {
                script {
                    docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
                    dockerimage.push("latest")
                }
            }
        }

        stage("Start App") {
            steps {
                sh "docker-compose up -d"
            }
        }

        stage("JUnit/Mockito") {
            steps {
                sh "mvn test"
            }
        }
    }
}
