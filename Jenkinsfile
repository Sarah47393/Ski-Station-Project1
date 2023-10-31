pipeline {
    agent any

    stages {
        stage ('GIT') {
            steps {
                git branch: "amine", 
                    credentialsId: 'github-credentials',
                    url: "https://github.com/Sarah47393/Ski-Station-Project.git";
            }
        }

        stage("JUnit/Mockito") {
            steps {
                sh "mvn test"
            }
        }

        stage("Build") {
            steps {
                sh "mvn clean package"
            }
        }

	    stage("Deploy to nexus") {
            steps {
                sh "mvn deploy"
            }
        }


        stage("Build Docker image") {
            steps {
                sh "docker build -t ski ."
            }
        }

        stage("Start App") {
            steps {
                sh "docker-compose up -d"
            }
        }
    }
}
