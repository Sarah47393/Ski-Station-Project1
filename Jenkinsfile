pipeline {
    agent any

    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.33.10:8081"
        NEXUS_REPOSITORY = "maven-releases"
        NEXUS_CREDENTIAL_ID = "nexus-project-token"
    }
    stages {
        stage ('GIT') {
            steps {
                echo "Getting project from Git"
                git branch: "Gloria", 
                    credentialsId: 'd2461957-c249-41f9-87e2-49dcec9e642b',
                    url: "https://github.com/Sarah47393/Ski-Station-Project1.git";
            }
        }
        stage("Maven Build") {
            steps {
                script {
                    echo "Building with Maven..."
                    sh "mvn package -DskipTests=true"
                }
            }
        }
       
    
        stage('SonarQube'){
            steps{
                withSonarQubeEnv('SonarQube'){
                    echo "Running SonarQube analysis..."
                    sh "mvn sonar:sonar -Dsonar.projectKey=maven-jenkins-pipeline -Dsonar.host.url=http://192.168.33.10:9000 -Dsonar.login=935a41b8c82b5e52bd7c8cdea1c2d9ce6c593ced"
                    
            }
                }
                
        }

        stage("Deploy to nexus") {
            steps {
                sh "mvn deploy -DskipTests=true"
            }
        }

        stage("Build Docker image") {
            steps {
                script{
                    echo "Building Docker Image..."
                    sh "docker build -t gloria6056/ski ."
                }
                
            }
        }

        stage("Push image to Docker Hub") {
            steps {
                echo "Push image to Docker Hub...."
                sh "docker login -u='gloria6056' -p='TtYc2011?!'"
                sh "docker push gloria6056/ski:latest"
            }
        }

        stage("Start App") {
            steps {
                sh "docker-compose up -d"
            }
        }


       stage('JUnit/Mockito') {
            steps {
              script{
                    echo "Test unitaire..."
                    sh 'mvn test'
                }
            }
        }
                            

       
       
    }
}
