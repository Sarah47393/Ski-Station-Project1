pipeline {
    agent { label 'ubuntu' }

    stages {
        stage ('GIT') {
            steps {
                git branch: "amine", 
                    credentialsId: 'github-credentials',
                    url: "https://github.com/Sarah47393/Ski-Station-Project.git";
            }
        }

        stage("Build") {
            steps {
                sh "mvn clean package -DskipTests"
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
