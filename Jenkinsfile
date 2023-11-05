pipeline {
    agent any

    stages {
        stage ('GIT') {
            steps {
                git branch: "Gloria", 
                    credentialsId: 'd2461957-c249-41f9-87e2-49dcec9e642b',
                    url: "https://github.com/Sarah47393/Ski-Station-Project.git";
            }
        }

        stage('SonarQube'){
            steps{
                mvn sonar:sonar \
                    -Dsonar.projectKey=maven-jenkins-pipeline \
                    -Dsonar.host.url=http://192.168.33.10:9000 \
                    -Dsonar.login=935a41b8c82b5e52bd7c8cdea1c2d9ce6c593ced
            }
        }


       
    }
}